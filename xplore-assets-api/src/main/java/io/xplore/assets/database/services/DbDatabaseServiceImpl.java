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
import io.xplore.assets.service.DatabaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Database DB service
 */
@Stateless
public class DbDatabaseServiceImpl implements DatabaseService {

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
    @Override
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
    @Override
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
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaDbEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaDatabase>(err);
        }
    }

    /**
     * Implement pagination
     * This is workaround implementation since hybernate does not support MS SQL 2014 syntax yet.
     * @param query The query object
     * @param response The response object
     * @param pageNumber Page number
     * @param pageSize Page size
     * @return
     */
    private void processPagination(TypedQuery<MdaDbEntity> query, QueryResponse<MdaDatabase> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaDbEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaDbEntityConverter.get(entity));
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
