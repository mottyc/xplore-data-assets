/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaDbEntityConverter;
import io.xplore.assets.database.model.MdaDbEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Database DB service
 */
@Stateless
public class DbDatabaseServiceImpl {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single database by key
     *
     * @param key Database key
     * @return EntityResponse<MdaDatabase>
     */
    public EntityResponse<MdaDatabase> get(int key) {

        try {
            MdaDbEntity entity = em.find(MdaDbEntity.class, key);
            return new EntityResponse<MdaDatabase>(MdaDbEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaDatabase>(err);
        }
    }

    /**
     * Get list of databases
     * @param serverKey Filter by server key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaDatabase>
     */
    public QueryResponse<MdaDatabase> find(int serverKey, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaDatabase> response = new QueryResponse<MdaDatabase>();

            TypedQuery<MdaDbEntity> query = (serverKey > 0) ?
                    em.createNamedQuery("MdaDbEntity.findByServer", MdaDbEntity.class).setParameter("serverKey", serverKey) :
                    em.createNamedQuery("MdaDbEntity.findAll", MdaDbEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaDbEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaDatabase>(err);
        }
    }
}
