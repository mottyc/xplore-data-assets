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
import io.xplore.assets.service.SchemaService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Schema DB service
 */
@Stateless
public class DbSchemaServiceImpl implements SchemaService {

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
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaSchemaEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaSchema>(err);
        }
    }
}
