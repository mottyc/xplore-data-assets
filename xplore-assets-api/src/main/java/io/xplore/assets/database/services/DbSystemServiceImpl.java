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
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.SystemService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.logging.Logger;

/**
 * System DB service
 */
@Stateless
public class DbSystemServiceImpl extends _DbBaseServiceImpl<MdaSystemEntity> implements SystemService {

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
     * Update system
     *
     * @param system System to update
     * @return EntityResponse<MdaSystem>
     */
    @Override
    public EntityResponse<MdaSystem> set(MdaSystem system) {
        try {
            MdaSystemEntity entity = em.find(MdaSystemEntity.class, system.systemKey);

            // Update w/r fields
            entity.setSystemNameDisplay(system.displayName);
            entity.setSystemDesc(system.description);

            return new EntityResponse<MdaSystem>(MdaSystemEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaSystem>(err);
        }
    }

    /**
     * Get list of systems
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSystem>
     */
    @Override
    public QueryResponse<MdaSystem> find(int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaSystem> response = new QueryResponse<MdaSystem>();

            TypedQuery<MdaSystemEntity> query = em.createNamedQuery("MdaSystemEntity.findAll", MdaSystemEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaSystemEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaSystem>(err);
        }
    }

    /**
     * Get list of systems with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaSystem>
     */
    @Override
    public QueryResponse<MdaSystem> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaSystem> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = this.buildCriteriaQuery(MdaSystemEntity.class, cb, filter, sorting);
            TypedQuery<MdaSystemEntity> query = em.createQuery(cq);

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
    private void processPagination(TypedQuery<MdaSystemEntity> query, QueryResponse<MdaSystem> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaSystemEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaSystemEntityConverter.get(entity));
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
