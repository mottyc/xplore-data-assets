/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaBusinessEntityConverter;
import io.xplore.assets.database.converters.MdaColumnEntityConverter;
import io.xplore.assets.database.converters.MdaSystemEntityConverter;
import io.xplore.assets.database.converters.MdaTableEntityConverter;
import io.xplore.assets.database.model.*;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;
import io.xplore.assets.service.TableService;

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
 * Table DB service
 */
@Stateless
public class DbTableServiceImpl extends _DbBaseServiceImpl<MdaTableEntity> implements TableService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Get single table by key
     *
     * @param key table key
     * @return EntityResponse<MdaTable>
     */
    @Override
    public EntityResponse<MdaTable> get(int key) {
        try {
            MdaTableEntity entity = em.find(MdaTableEntity.class, key);
            MdaTable table = MdaTableEntityConverter.get(entity);

            // Get all related columns
            List<MdaColumnEntity> columns = em.createNamedQuery("MdaColumnEntity.findByTable", MdaColumnEntity.class).setParameter("tableKey", table.tableKey).getResultList();
            columns.forEach(column -> { table.columns.add(MdaColumnEntityConverter.get(column)); });

            return new EntityResponse<MdaTable>(table);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaTable>(err);
        }
    }

    /**
     * Update table
     *
     * @param table Table to update
     * @return EntityResponse<MdaTable>
     */
    @Override
    public EntityResponse<MdaTable> set(MdaTable table) {
        try {
            MdaTableEntity entity = em.find(MdaTableEntity.class, table.tableKey);

            // Update w/r fields
            entity.setTableNameDisplay(table.displayName);
            entity.setTableDesc(table.description);

            return new EntityResponse<MdaTable>(MdaTableEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaTable>(err);
        }
    }

    /**
     * Get list of systems
     * @param schemaKey Filter by schema key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaTable>
     */
    @Override
    public QueryResponse<MdaTable> find(int schemaKey, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaTable> response = new QueryResponse<MdaTable>();

            TypedQuery<MdaTableEntity> query = (schemaKey > 0) ?
                    em.createNamedQuery("MdaTableEntity.findBySchema", MdaTableEntity.class).setParameter("schemaKey", schemaKey) :
                    em.createNamedQuery("MdaTableEntity.findAll", MdaTableEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaTableEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaTable>(err);
        }
    }

    /**
     * Get list of tables with filter and sort
     * @param schemaKey Filter by schema key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaTable>
     */
    @Override
    public QueryResponse<MdaTable> find(int schemaKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaTable> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Set filter by table
            if (schemaKey > -1) {
                if (filter == null) {
                    filter = new QueryFilter();
                }
                filter.addFilter("schemaKey", String.valueOf(schemaKey));
            }

            CriteriaQuery cq = this.buildCriteriaQuery(MdaTableEntity.class, cb, filter, sorting);
            TypedQuery<MdaTableEntity> query = em.createQuery(cq);

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
            return new QueryResponse<>(err);
        }
    }

    // ------------------ Systems related actions ----------------------------------------------------------------------

    /**
     * Get table related systems
     * @param tableKey Table key
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> getSystems(int tableKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaSystem> response = new EntitiesResponse<>();

            // First, get list of related systems keys
            List<MdaTableSystemRelEntity> list = em.createNamedQuery("MdaTableSystemRelEntity.findByTableKey", MdaTableSystemRelEntity.class)
                    .setParameter("tableKey", tableKey)
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
            return new EntitiesResponse<>(err);
        }
    }

    /**
     * Link systems to table
     * @param tableKey Table key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> linkSystems(int tableKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaTableSystemRelEntity rel = em.createNamedQuery("MdaTableSystemRelEntity.findByTableAndSystem", MdaTableSystemRelEntity.class)
                            .setParameter("tableKey", tableKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    MdaTableSystemRelEntity created = new MdaTableSystemRelEntity();
                    created.setTableKey(tableKey);
                    created.setSystemKey(systemKey);
                    em.persist(created);
                } catch (Exception ex) {

                }
            }
            return this.getSystems(tableKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    /**
     * Unlink systems from table
     * @param tableKey Table key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    @Override
    public EntitiesResponse<MdaSystem> unlinkSystems(int tableKey, int[] systemsKeys) {
        try {
            for(int systemKey : systemsKeys) {
                try {
                    MdaTableSystemRelEntity rel = em.createNamedQuery("MdaTableSystemRelEntity.findByTableAndSystem", MdaTableSystemRelEntity.class)
                            .setParameter("tableKey", tableKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {

                }
            }
            return this.getSystems(tableKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaSystem>(err);
        }
    }

    // ------------------ Business entities related actions ------------------------------------------------------------

    /**
     * Get table related entities
     * @param tableKey Table key
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> getEntities(int tableKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaBusinessEntity> response = new EntitiesResponse<>();

            // First, get list of related entities keys
            List<MdaBusinessEntityTableRelEntity> list = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByTableKey", MdaBusinessEntityTableRelEntity.class)
                    .setParameter("tableKey", tableKey)
                    .getResultList();

            list.forEach(r -> { keys.add(r.getBusinessEntityKey()); });
            if (keys.size() == 0) return response;

            // Now get all entities in the list
            List<MdaBusinessEntityEntity> entities = em.createNamedQuery("MdaBusinessEntityEntity.findByKeys", MdaBusinessEntityEntity.class)
                    .setParameter("keys", keys)
                    .getResultList();

            entities.forEach(ent -> { response.getList().add(MdaBusinessEntityConverter.get(ent)); } );
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<>(err);
        }
    }

    /**
     * Link entities to table
     * @param tableKey Table key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> linkEntities(int tableKey, int[] entitiesKeys) {
        try {
            for(int entityKey : entitiesKeys) {
                try {
                    MdaBusinessEntityTableRelEntity rel = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByEntityAndTable", MdaBusinessEntityTableRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    MdaBusinessEntityTableRelEntity created = new MdaBusinessEntityTableRelEntity();
                    created.setTableKey(tableKey);
                    created.setBusinessEntityKey(entityKey);
                    em.persist(created);
                } catch (Exception ex) {}
            }
            return this.getEntities(tableKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<>(err);
        }
    }

    /**
     * Unlink entities from table
     * @param tableKey Table key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> unlinkEntities(int tableKey, int[] entitiesKeys) {
        try {
            for(int entityKey : entitiesKeys) {
                try {
                    MdaBusinessEntityTableRelEntity rel = em.createNamedQuery("MdaBusinessEntityTableRelEntity.findByEntityAndTable", MdaBusinessEntityTableRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("tableKey", tableKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {

                }
            }
            return this.getEntities(tableKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<>(err);
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
    private void processPagination(TypedQuery<MdaTableEntity> query, QueryResponse<MdaTable> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaTableEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaTableEntityConverter.get(entity));
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
