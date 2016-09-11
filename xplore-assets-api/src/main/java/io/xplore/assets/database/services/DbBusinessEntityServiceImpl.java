/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaBusinessEntityConverter;
import io.xplore.assets.database.converters.MdaSystemEntityConverter;
import io.xplore.assets.database.converters.MdaTableEntityConverter;
import io.xplore.assets.database.model.*;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;
import io.xplore.assets.service.BusinessEntityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public class DbBusinessEntityServiceImpl extends _DbBaseServiceImpl<MdaBusinessEntityEntity> implements BusinessEntityService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Get single business entity by key
     *
     * @param key entity key
     * @return EntityResponse<MdaBusinessEntity>
     */
    @Override
    public EntityResponse<MdaBusinessEntity> get(int key) {
        try {
            MdaBusinessEntityEntity entity = em.find(MdaBusinessEntityEntity.class, key);
            return new EntityResponse<MdaBusinessEntity>(MdaBusinessEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaBusinessEntity>(err);
        }
    }
    /**
     * Update business entity
     *
     * @param entity Business entity to update
     * @return EntityResponse<MdaBusinessEntity>
     */
    @Override
    public EntityResponse<MdaBusinessEntity> set(MdaBusinessEntity entity) {
        try {
            MdaBusinessEntityEntity ent = em.find(MdaBusinessEntityEntity.class, entity.businessEntityKey);

            // Update w/r fields
            ent.setBusinessEntityNameDisplay(entity.displayName);
            ent.setBusinessEntityDesc(entity.description);

            return new EntityResponse<MdaBusinessEntity>(MdaBusinessEntityConverter.get(ent));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaBusinessEntity>(err);
        }
    }

    /**
     * Get list of business entities
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaBusinessEntity>
     */
    @Override
    public QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaBusinessEntity> response = new QueryResponse<MdaBusinessEntity>();

            TypedQuery<MdaBusinessEntityEntity> query = em.createNamedQuery("MdaBusinessEntityEntity.findAll", MdaBusinessEntityEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination (currently, SQL do not support pagination)
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaBusinessEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaBusinessEntity>(err);
        }
    }

    /**
     * Get list of business entities with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaBusinessEntity>
     */
    @Override
    public QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaBusinessEntity> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = this.buildCriteriaQuery(MdaBusinessEntityEntity.class, cb, filter, sorting);
            TypedQuery<MdaBusinessEntityEntity> query = em.createQuery(cq);

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
            return new QueryResponse<MdaBusinessEntity>(err);
        }
    }

    // ------------------ Entity related systems actions ---------------------------------------------------------------

    /**
     * Get business entity related systems
     * @param entityKey Entity key
     * @return EntityResponse[MdaSystem]
     */
    public EntitiesResponse<MdaSystem> getSystems(int entityKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaSystem> response = new EntitiesResponse<MdaSystem>();

            // First, get list of related systems keys
            List<MdaBusinessEntitySystemRelEntity> list = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findByEntityKey", MdaBusinessEntitySystemRelEntity.class)
                    .setParameter("businessEntityKey", entityKey)
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
     * Link systems to business entity
     * @param entityKey Entity key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> linkSystems(int entityKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaBusinessEntitySystemRelEntity rel = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findByEntityAndSystem", MdaBusinessEntitySystemRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel == null) {
                        rel = new MdaBusinessEntitySystemRelEntity();
                        rel.setBusinessEntityKey(entityKey);
                        rel.setSystemKey(systemKey);
                        em.persist(rel);
                    }
                } catch (Exception ex) {

                }
            }
            return this.getSystems(entityKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    /**
     * Unlink systems from business entity
     * @param entityKey Entity key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> unlinkSystems(int entityKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaBusinessEntitySystemRelEntity rel = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findByEntityAndSystem", MdaBusinessEntitySystemRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getSystems(entityKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    // ------------------ Entity related tables actions ----------------------------------------------------------------

    /**
     * Get business entity related table
     * @param entityKey Entity key
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> getTables(int entityKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaTable> response = new EntitiesResponse<MdaTable>();

            // First, get list of related tables keys
            List<MdaBusinessEntityTableRelEntity> list = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByEntityKey", MdaBusinessEntityTableRelEntity.class)
                    .setParameter("businessEntityKey", entityKey)
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
     * Link tables to business entity
     * @param entityKey Entity key
     * @param tablesKeys List of keys to link
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> linkTables(int entityKey, int[] tablesKeys) {
        try {
            for(int tableKey : tablesKeys) {
                try {
                    MdaBusinessEntityTableRelEntity rel = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByEntityAndTable", MdaBusinessEntityTableRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                    if (rel == null) {
                        rel = new MdaBusinessEntityTableRelEntity();
                        rel.setBusinessEntityKey(entityKey);
                        rel.setTableKey(tableKey);
                        em.persist(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getTables(entityKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaTable>(err);
        }
    }

    /**
     * Unlink tables from business entity
     * @param entityKey Entity key
     * @param tablesKeys List of keys to unlink
     * @return EntityResponse[MdaTable]
     */
    @Override
    public EntitiesResponse<MdaTable> unlinkTables(int entityKey, int[] tablesKeys) {
        try {
            for(int tableKey : tablesKeys) {
                try {
                    MdaBusinessEntityTableRelEntity rel = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByEntityAndTable", MdaBusinessEntityTableRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getTables(entityKey);
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
    private void processPagination(TypedQuery<MdaBusinessEntityEntity> query, QueryResponse<MdaBusinessEntity> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaBusinessEntityEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaBusinessEntityConverter.get(entity));
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
