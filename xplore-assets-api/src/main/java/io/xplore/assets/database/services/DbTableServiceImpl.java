/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaTableEntityConverter;
import io.xplore.assets.database.model.MdaTableEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaTable;
import io.xplore.assets.service.TableService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Table DB service
 */
@Stateless
public class DbTableServiceImpl implements TableService {

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
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaTableEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaTable>(err);
        }
    }
}
