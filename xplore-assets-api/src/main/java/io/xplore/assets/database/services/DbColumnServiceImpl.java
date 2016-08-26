/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaColumnEntityConverter;
import io.xplore.assets.database.model.MdaColumnEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaColumn;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Base DB service
 */
@Stateless
public class DbColumnServiceImpl  {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single column by key
     *
     * @param key Column key
     * @return EntityResponse<MdaColumn>
     */
    public EntityResponse<MdaColumn> get(int key) {

        try {
            MdaColumnEntity entity = em.find(MdaColumnEntity.class, key);
            return new EntityResponse<MdaColumn>(MdaColumnEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaColumn>(err);
        }
    }

    /**
     * Get list of columns
     * @param tableKey Filter by table key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaColumn>
     */
    public QueryResponse<MdaColumn> find(int tableKey, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaColumn> response = new QueryResponse<MdaColumn>();

            TypedQuery<MdaColumnEntity> query = (tableKey > 0) ?
                    em.createNamedQuery("MdaColumnEntity.findAll", MdaColumnEntity.class) :
                    em.createNamedQuery("MdaColumnEntity.findByTable", MdaColumnEntity.class).setParameter("tableKey", tableKey);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            query.getResultList().forEach(entity -> {response.getList().add(MdaColumnEntityConverter.get(entity));});
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaColumn>(err);
        }
    }
}
