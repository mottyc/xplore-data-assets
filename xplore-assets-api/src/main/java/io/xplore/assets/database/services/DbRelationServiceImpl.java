/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaRelationEntityConverter;
import io.xplore.assets.database.model.MdaRelationEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaRelation;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.RelationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.logging.Logger;

/**
 * Relation DB service
 */
@Stateless
public class DbRelationServiceImpl extends _DbBaseServiceImpl<MdaRelationEntity> implements RelationService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Get single relation by key
     *
     * @param key Relation key
     * @return EntityResponse<MdaRelation>
     */
    @Override
    public EntityResponse<MdaRelation> get(int key) {
        try {
            MdaRelationEntity entity = em.find(MdaRelationEntity.class, key);
            return new EntityResponse<MdaRelation>(MdaRelationEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaRelation>(err);
        }
    }

    /**
     * Update relation
     *
     * @param relation Relation to update
     * @return EntityResponse<MdaRelation>
     */
    @Override
    public EntityResponse<MdaRelation> set(MdaRelation relation) {
        try {
            MdaRelationEntity entity = em.find(MdaRelationEntity.class, relation.relationKey);

            // Update w/r fields
            entity.setRelationNameDisplay(relation.displayName);
            entity.setRelationDesc(relation.description);

            return new EntityResponse<MdaRelation>(MdaRelationEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaRelation>(err);
        }
    }

    /**
     * Get list of relations
     * @param parentKey Filter by parent column key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaRelation>
     */
    @Override
    public QueryResponse<MdaRelation> find(int parentKey, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaRelation> response = new QueryResponse<MdaRelation>();

            TypedQuery<MdaRelationEntity> query = (parentKey > 0) ?
                    em.createNamedQuery("MdaRelationEntity.findByParent", MdaRelationEntity.class).setParameter("columnKeyPar", parentKey) :
                    em.createNamedQuery("MdaRelationEntity.findAll", MdaRelationEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaRelationEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaRelation>(err);
        }
    }

    /**
     * Get list of relations with filter and sort
     * @param parentKey Filter by parent column key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaRelation>
     */
    @Override
    public QueryResponse<MdaRelation> find(int parentKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting) {
        try {
            QueryResponse<MdaRelation> response = new QueryResponse<>();

            // Set query source
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Set filter by table
            if (parentKey > -1) {
                if (filter == null) {
                    filter = new QueryFilter();
                }
                filter.addFilter("columnKeyPar", String.valueOf(parentKey));
            }

            CriteriaQuery cq = this.buildCriteriaQuery(MdaRelationEntity.class, cb, filter, sorting);
            TypedQuery<MdaRelationEntity> query = em.createQuery(cq);

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
    private void processPagination(TypedQuery<MdaRelationEntity> query, QueryResponse<MdaRelation> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaRelationEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaRelationEntityConverter.get(entity));
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
