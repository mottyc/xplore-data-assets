/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.database.converters.MdaBusinessEntityConverter;
import io.xplore.assets.database.converters.MdaServerEntityConverter;
import io.xplore.assets.database.converters.MdaSystemEntityConverter;
import io.xplore.assets.database.model.*;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;
import io.xplore.assets.service.SystemService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
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

    // ------------------ System related servers actions ---------------------------------------------------------------

    /**
     * Get system related servers
     * @param systemKey System key
     * @return EntityResponse[MdaServer]
     */
    @Override
    public EntitiesResponse<MdaServer> getServers(int systemKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaServer> response = new EntitiesResponse<>();

            // First, get list of related servers keys
            List<MdaServerSystemRelEntity> list = em.createNamedQuery("MdaServerSystemRelEntity.findBySystemKey", MdaServerSystemRelEntity.class)
                    .setParameter("systemKey", systemKey)
                    .getResultList();

            list.forEach(r -> { keys.add(r.getServerKey()); });
            if (keys.size() == 0) return response;

            // Now get all servers in the list
            List<MdaServerEntity> servers = em.createNamedQuery("MdaServerEntity.findByKeys", MdaServerEntity.class)
                    .setParameter("keys", keys)
                    .getResultList();

            servers.forEach(srv -> { response.getList().add(MdaServerEntityConverter.get(srv)); } );
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaServer>(err);
        }
    }

    /**
     * Link servers to system
     * @param systemKey System key
     * @param serversKeys List of keys to link
     * @return EntityResponse[MdaServer]
     */
    @Override
    public EntitiesResponse<MdaServer> linkServers(int systemKey, int[] serversKeys) {
        try {
            for(int serverKey : serversKeys) {
                try {
                    MdaServerSystemRelEntity rel = em.createNamedQuery("MdaServerSystemRelEntity.findByServerAndSystem", MdaServerSystemRelEntity.class)
                            .setParameter("serverKey", serverKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    MdaServerSystemRelEntity created = new MdaServerSystemRelEntity();
                    created.setServerKey(serverKey);
                    created.setSystemKey(systemKey);
                    em.persist(created);
                } catch (Exception ex) { }
            }
            return this.getServers(systemKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaServer>(err);
        }
    }

    /**
     * Unlink servers from system
     * @param systemKey System key
     * @param serversKeys List of keys to unlink
     * @return EntityResponse[MdaServer]
     */
    @Override
    public EntitiesResponse<MdaServer> unlinkServers(int systemKey, int[] serversKeys) {
        try {
            for(int serverKey : serversKeys) {
                try {
                    MdaServerSystemRelEntity rel = em.createNamedQuery("MdaServerSystemRelEntity.findByServerAndSystem", MdaServerSystemRelEntity.class)
                            .setParameter("serverKey", serverKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {}
            }
            return this.getServers(systemKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaServer>(err);
        }
    }

    // ------------------ System related entities actions --------------------------------------------------------------

    /**
     * Get system related entities
     * @param systemKey System key
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> getEntities(int systemKey) {
        try {
            List<Integer> keys = new ArrayList<>();
            EntitiesResponse<MdaBusinessEntity> response = new EntitiesResponse<>();

            // First, get list of related tables keys
            List<MdaBusinessEntitySystemRelEntity> list = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findBySystemKey", MdaBusinessEntitySystemRelEntity.class)
                    .setParameter("systemKey", systemKey)
                    .getResultList();

            list.forEach(r -> { keys.add(r.getBusinessEntityKey()); });
            if (keys.size() == 0) return response;

            // Now get all tables in the list
            List<MdaBusinessEntityEntity> entities = em.createNamedQuery("MdaBusinessEntityEntity.findByKeys", MdaBusinessEntityEntity.class)
                    .setParameter("keys", keys)
                    .getResultList();

            entities.forEach(ent -> { response.getList().add(MdaBusinessEntityConverter.get(ent)); } );
            return response;
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<>(err);
        }
    }

    /**
     * Link entities to system
     * @param systemKey System key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> linkEntities(int systemKey, int[] entitiesKeys) {
        try {
            for(int entityKey : entitiesKeys) {
                try {
                    MdaBusinessEntitySystemRelEntity rel = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findByEntityAndSystem", MdaBusinessEntitySystemRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    MdaBusinessEntitySystemRelEntity created = new MdaBusinessEntitySystemRelEntity();
                    created.setBusinessEntityKey(entityKey);
                    created.setSystemKey(systemKey);
                    em.persist(created);
                } catch (Exception ex) {}
            }
            return this.getEntities(systemKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaBusinessEntity>(err);
        }
    }

    /**
     * Unlink entities from system
     * @param systemKey System key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    @Override
    public EntitiesResponse<MdaBusinessEntity> unlinkEntities(int systemKey, int[] entitiesKeys) {
        try {
            for(int entityKey : entitiesKeys) {
                try {
                    MdaBusinessEntitySystemRelEntity rel = em.createNamedQuery("MdaBusinessEntitySystemRelEntity.findByEntityAndSystem", MdaBusinessEntitySystemRelEntity.class)
                            .setParameter("businessEntityKey", entityKey)
                            .setParameter("systemKey", systemKey)
                            .getSingleResult();

                    if (rel != null) {
                        em.remove(rel);
                    }
                } catch (Exception ex) {

                }
            }
            return this.getEntities(systemKey);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntitiesResponse<MdaBusinessEntity>(err);
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
