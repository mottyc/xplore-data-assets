/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaServerEntityConverter;
import io.xplore.assets.database.model.MdaServerEntity;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaServer;
import io.xplore.assets.service.ServerService;
import io.xplore.assets.util.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * Server DB service
 */
@Stateless
public class DbServerServiceImpl implements ServerService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get single server by key
     *
     * @param key server key
     * @return EntityResponse<MdaServer>
     */
    @Override
    public EntityResponse<MdaServer> get(int key) {

        try {
            MdaServerEntity entity = em.find(MdaServerEntity.class, key);
            return new EntityResponse<MdaServer>(MdaServerEntityConverter.get(entity));
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<MdaServer>(err);
        }
    }

    /**
     * Get list of servers
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaServer>
     */
    @Override
    public QueryResponse<MdaServer> find(String typeCode, int pageNumber, int pageSize) {
        try {
            QueryResponse<MdaServer> response = new QueryResponse<MdaServer>();

            TypedQuery<MdaServerEntity> query = (StringUtils.isNotEmpty(typeCode)) ?
                    em.createNamedQuery("MdaServerEntity.findByType", MdaServerEntity.class).setParameter("serverTypeCd", typeCode) :
                    em.createNamedQuery("MdaServerEntity.findAll", MdaServerEntity.class);

            // Set pages
            int count = query.getResultList().size();
            response.setCount(count);
            response.setPage(pageNumber);
            response.setPages((count / pageSize) + ((count % pageSize) == 0 ? 0 : 1));

            // Pagination
            //query.setFirstResult((pageNumber - 1) * pageSize);
            //query.setMaxResults(pageSize);
            //query.getResultList().forEach(entity -> {response.getList().add(MdaServerEntityConverter.get(entity));});

            // Pagination workaround
            this.processPagination(query, response, pageNumber, pageSize);

            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new QueryResponse<MdaServer>(err);
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
    private void processPagination(TypedQuery<MdaServerEntity> query, QueryResponse<MdaServer> response, int pageNumber, int pageSize ) {
        try {
            int row = 1;
            int fromRow = (pageNumber - 1) * pageSize;
            int toRow = (pageNumber) * pageSize;

            for (MdaServerEntity entity : query.getResultList()) {
                if (row <= toRow) {
                    if (row > fromRow) {
                        response.getList().add(MdaServerEntityConverter.get(entity));
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
