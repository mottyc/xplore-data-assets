/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaSystemEntityConverter;
import io.xplore.assets.database.model.MdaSystemEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSystem;
import io.xplore.assets.service.SystemService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * System DB service
 */
@Stateless
public class DbSystemServiceImpl implements SystemService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single system by key
     *
     * @param key system key
     * @return EntityResponse<MdaSystem>
     */
    @Override
    public EntityResponse<MdaSystem> get(int key) {

        try {
            MdaSystemEntity entity = em.find(MdaSystemEntity.class, key);
            return new EntityResponse<MdaSystem>(MdaSystemEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaSystem>(err);
        }
    }

    /**
     * Get list of systems
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSystem>
     */
    @Override
    public QueryResponse<MdaSystem> find(String typeCode, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaSystem> response = new QueryResponse<MdaSystem>();

            TypedQuery<MdaSystemEntity> query = em.createNamedQuery("MdaSystemEntity.findAll", MdaSystemEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaSystemEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaSystem>(err);
        }
    }
}
