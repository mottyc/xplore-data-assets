/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaBusinessEntityConverter;
import io.xplore.assets.database.converters.MdaProcessEntityConverter;
import io.xplore.assets.database.converters.MdaSystemEntityConverter;
import io.xplore.assets.database.converters.MdaTableEntityConverter;
import io.xplore.assets.database.model.*;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;
import io.xplore.assets.service.BusinessEntityService;
import io.xplore.assets.service.ProcessService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Business entity DB service
 */
@Stateless
public class DbProcessServiceImpl extends _DbBaseServiceImpl<MdaProcessEntity> implements ProcessService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Get single process by key
     *
     * @param key process key
     * @return EntityResponse<MdaProcess>
     */
    @Override
    public EntityResponse<MdaProcess> get(int key) {
        try {
            MdaProcessEntity entity = em.find(MdaProcessEntity.class, key);
            return new EntityResponse<MdaProcess>(MdaProcessEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaProcess>(err);
        }
    }

    /**
     * Update process
     *
     * @param process process to update
     * @return EntityResponse<MdaProcess>
     */
    @Override
    public EntityResponse<MdaProcess> set(MdaProcess process) {
        try {
            MdaProcessEntity ent = em.find(MdaProcessEntity.class, process.processKey);

            // Update w/r fields
            ent.setProcessNameDisplay(process.displayName);
            ent.setProcessDesc(process.description);

            return new EntityResponse<MdaProcess>(MdaProcessEntityConverter.get(ent));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaProcess>(err);
        }
    }

    /**
     * Get list of processes
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaProcess>
     */
    @Override
    public QueryResponse<MdaProcess> find(int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaProcess> response = new QueryResponse<MdaProcess>();

            TypedQuery<MdaProcessEntity> query = em.createNamedQuery("MdaProcessEntity.findAll", MdaProcessEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaProcess>(err);
        }
    }

    /**
     * Get list of processes with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaProcess>
     */
    @Override
    public QueryResponse<MdaProcess> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaProcess> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = this.buildCriteriaQuery(MdaProcessEntity.class, cb, filter, sorting);
            TypedQuery<MdaProcessEntity> query = em.createQuery(cq);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaProcess>(err);
        }
    }

    // ------------------ Entity related systems actions ---------------------------------------------------------------

    /**
     * Get process related systems
     * @param processKey Process key
     * @return EntityResponse[MdaSystem]
     */
    public EntitiesResponse<MdaSystem> getSystems(int processKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaSystem> response = new EntitiesResponse<MdaSystem>();

            // First, get list of related systems keys
            List<MdaProcessSystemRelEntity> list = em.createNamedQuery("MdaProcessSystemRelEntity.findByProcessKey", MdaProcessSystemRelEntity.class)
                    .setParameter("processKey", processKey)
                    .getResultList();

            list.forEach(r -> { keys.add(r.getSystemKey()); });
            if (keys.size() == 0) return response;

            // Now get all systems in the list
            List<MdaSystemEntity> systems = em.createNamedQuery("MdaSystemEntity.findByKeys", MdaSystemEntity.class)
                    .setParameter("keys", keys)
                    .getResultList();

            systems.forEach(sys -> { response.getList().add(MdaSystemEntityConverter.get(sys)); } );
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    /**
     * Link systems to process
     * @param processKey Process key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> linkSystems(int processKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaProcessSystemRelEntity rel = em.createNamedQuery("MdaProcessSystemRelEntity.findByProcessAndSystem", MdaProcessSystemRelEntity.class)
                            .setParameter("processKey", processKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                } catch (NoResultException ex){
                    MdaProcessSystemRelEntity created = new MdaProcessSystemRelEntity();
                    created.setProcessKey(processKey);
                    created.setSystemKey(systemKey);
                    em.persist(created);
                } catch (Exception ex) { }
            }
            return this.getSystems(processKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    /**
     * Unlink systems from process
     * @param processKey Process key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> unlinkSystems(int processKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaProcessSystemRelEntity rel = em.createNamedQuery("MdaProcessSystemRelEntity.findByProcessAndSystem", MdaProcessSystemRelEntity.class)
                            .setParameter("processKey", processKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getSystems(processKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    // ------------------ Entity related tables actions ----------------------------------------------------------------

    /**
     * Get process related table
     * @param processKey Process key
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> getTables(int processKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaTable> response = new EntitiesResponse<MdaTable>();

            // First, get list of related tables keys
            List<MdaTableProcessRelEntity> list = em.createNamedQuery("MdaTableProcessRelEntity.findByProcessKey", MdaTableProcessRelEntity.class)
                    .setParameter("processKey", processKey)
                    .getResultList();

            list.forEach(r -> { keys.add(r.getTableKey()); });
            if (keys.size() == 0) return response;

            // Now get all tables in the list
            List<MdaTableEntity> tables = em.createNamedQuery("MdaTableEntity.findByKeys", MdaTableEntity.class)
                    .setParameter("keys", keys)
                    .getResultList();

            tables.forEach(table -> { response.getList().add(MdaTableEntityConverter.get(table)); } );
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaTable>(err);
        }
    }

    /**
     * Link tables to process
     * @param processKey Process key
     * @param tablesKeys List of keys to link
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> linkTables(int processKey, int[] tablesKeys) {
        try {
            for(int tableKey : tablesKeys) {
                try {
                    MdaTableProcessRelEntity rel = em.createNamedQuery("MdaTableProcessRelEntity.findByProcessAndTable", MdaTableProcessRelEntity.class)
                            .setParameter("processKey", processKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    MdaTableProcessRelEntity created = new MdaTableProcessRelEntity();
                    created.setProcessKey(processKey);
                    created.setTableKey(tableKey);
                    em.persist(created);
                } catch (Exception ex) {}
            }
            return this.getTables(processKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaTable>(err);
        }
    }

    /**
     * Unlink tables from process
     * @param processKey Process key
     * @param tablesKeys List of keys to unlink
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> unlinkTables(int processKey, int[] tablesKeys) {
        try {
            for(int tableKey : tablesKeys) {
                try {
                    MdaTableProcessRelEntity rel = em.createNamedQuery("MdaTableProcessRelEntity.findByProcessAndTable", MdaTableProcessRelEntity.class)
                            .setParameter("processKey", processKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getTables(processKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaTable>(err);
        }
    }

    // ------ Private Section ------------------------------------------------------------------------------------------

    /**
     * Implement pagination
     * This is workaround implementation since hybernate does not support MS SQL 2014 syntax yet.
     * @param query The query object
     * @param response The response object
     * @param pageNumber Page number
     * @param pageSize Page size
     * @return
     */
    private void processPagination(TypedQuery<MdaProcessEntity> query, QueryResponse<MdaProcess> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaProcessEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaProcessEntityConverter.get(entity));
                    }
                    row += 1;
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            response.setError(err);
        }
    }
}
