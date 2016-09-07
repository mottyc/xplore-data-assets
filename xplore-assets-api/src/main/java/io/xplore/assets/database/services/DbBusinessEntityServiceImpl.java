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
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.BusinessEntityService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.logging.Logger;

/**
 * Business entity DB service
 */
@Stateless
public class DbBusinessEntityServiceImpl extends _DbBaseServiceImpl<MdaBusinessEntityEntity> implements BusinessEntityService {

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
     * Update business entity
     *
     * @param entity Business entity to update
     * @return EntityResponse<MdaBusinessEntity>
     */
    @Override
    public EntityResponse<MdaBusinessEntity> set(MdaBusinessEntity entity) {
        try {
            MdaBusinessEntityEntity ent = em.find(MdaBusinessEntityEntity.class, entity.businessEntityKey);

            // Update w/r fields
            ent.setBusinessEntityNameDisplay(entity.displayName);
            ent.setBusinessEntityDesc(entity.description);

            return new EntityResponse<MdaBusinessEntity>(MdaBusinessEntityConverter.get(ent));
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
            //query.getResultList().forEach(entity -> {response.getList().add(MdaBusinessEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaBusinessEntity>(err);
        }
    }

    /**
     * Get list of business entities with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaBusinessEntity>
     */
    @Override
    public QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaBusinessEntity> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = this.buildCriteriaQuery(MdaBusinessEntityEntity.class, cb, filter, sorting);
            TypedQuery<MdaBusinessEntityEntity> query = em.createQuery(cq);

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
            return new QueryResponse<MdaBusinessEntity>(err);
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
    private void processPagination(TypedQuery<MdaBusinessEntityEntity> query, QueryResponse<MdaBusinessEntity> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaBusinessEntityEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaBusinessEntityConverter.get(entity));
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
