/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaSchemaEntityConverter;
import io.xplore.assets.database.model.MdaSchemaEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSchema;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.SchemaService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.logging.Logger;

/**
 * Schema DB service
 */
@Stateless
public class DbSchemaServiceImpl extends _DbBaseServiceImpl<MdaSchemaEntity> implements SchemaService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single schema by key
     *
     * @param key schema key
     * @return EntityResponse<MdaSchema>
     */
    @Override
    public EntityResponse<MdaSchema> get(int key) {
        try {
            MdaSchemaEntity entity = em.find(MdaSchemaEntity.class, key);
            return new EntityResponse<MdaSchema>(MdaSchemaEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaSchema>(err);
        }
    }

    /**
     * Update schema
     *
     * @param schema Schema to update
     * @return EntityResponse<MdaSchema>
     */
    @Override
    public EntityResponse<MdaSchema> set(MdaSchema schema) {
        try {
            MdaSchemaEntity entity = em.find(MdaSchemaEntity.class, schema.schemaKey);

            // Update w/r fields
            entity.setSchemaNameDisplay(schema.displayName);
            entity.setSchemaDesc(schema.description);

            return new EntityResponse<MdaSchema>(MdaSchemaEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaSchema>(err);
        }
    }

    /**
     * Get list of schemas
     * @param databaseKey Filter by database key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSchema>
     */
    @Override
    public QueryResponse<MdaSchema> find(int databaseKey, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaSchema> response = new QueryResponse<MdaSchema>();

            TypedQuery<MdaSchemaEntity> query = (databaseKey > 0) ?
                    em.createNamedQuery("MdaSchemaEntity.findByDatabase", MdaSchemaEntity.class).setParameter("domainKey", databaseKey) :
                    em.createNamedQuery("MdaSchemaEntity.findAll", MdaSchemaEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaSchemaEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaSchema>(err);
        }
    }

    /**
     * Get list of schemas with filter and sort
     * @param databaseKey Filter by database key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaSchema>
     */
    @Override
    public QueryResponse<MdaSchema> find(int databaseKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaSchema> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Set filter by table
            if (databaseKey > -1) {
                if (filter == null) {
                    filter = new QueryFilter();
                }
                filter.getFilters().put("domainKey", String.valueOf(databaseKey));
            }

            CriteriaQuery cq = this.buildCriteriaQuery(MdaSchemaEntity.class, cb, filter, sorting);
            TypedQuery<MdaSchemaEntity> query = em.createQuery(cq);

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
            return new QueryResponse<MdaSchema>(err);
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
    private void processPagination(TypedQuery<MdaSchemaEntity> query, QueryResponse<MdaSchema> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaSchemaEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaSchemaEntityConverter.get(entity));
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
