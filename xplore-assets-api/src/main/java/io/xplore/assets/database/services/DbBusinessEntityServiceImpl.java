/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaBusinessEntityConverter;
import io.xplore.assets.database.model.MdaBusinessEntityEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaBusinessEntity;
import io.xplore.assets.service.BusinessEntityService;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Business entity DB service
 */
@Stateless
public class DbBusinessEntityServiceImpl implements BusinessEntityService {

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

            query.getResultList().forEach(entity -> {response.getList().add(MdaBusinessEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaBusinessEntity>(err);
        }
    }
}
