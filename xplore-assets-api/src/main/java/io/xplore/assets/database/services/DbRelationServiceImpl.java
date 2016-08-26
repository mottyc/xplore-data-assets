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
import io.xplore.assets.service.RelationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Relation DB service
 */
@Stateless
public class DbRelationServiceImpl implements RelationService {

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
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaRelationEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaRelation>(err);
        }
    }
}
