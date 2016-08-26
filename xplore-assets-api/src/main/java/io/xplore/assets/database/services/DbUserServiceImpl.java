/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaTableEntityConverter;
import io.xplore.assets.database.converters.MdaUserEntityConverter;
import io.xplore.assets.database.model.MdaTableEntity;
import io.xplore.assets.database.model.MdaUsernameEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaTable;
import io.xplore.assets.model.MdaUser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * User DB service
 */
@Stateless
public class DbUserServiceImpl {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single user by key
     *
     * @param key user key
     * @return EntityResponse<MdaUser>
     */
    public EntityResponse<MdaUser> get(int key) {

        try {
            MdaUsernameEntity entity = em.find(MdaUsernameEntity.class, key);
            return new EntityResponse<MdaUser>(MdaUserEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaUser>(err);
        }
    }

    /**
     * Get list of users
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaUser>
     */
    public QueryResponse<MdaUser> find(int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaUser> response = new QueryResponse<MdaUser>();

            TypedQuery<MdaUsernameEntity> query = em.createNamedQuery("MdaUsernameEntity.findAll", MdaUsernameEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaUserEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaUser>(err);
        }
    }
}
