/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

/**
 * Base DB service
 */
@Stateless
public class _DbBaseServiceImpl<T, ID>  {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    private Class<T> persistentClass;

    /**
     * Get list of entities
     * @param filter filter definitions
     * @param sort sort definitions
     * @return Account query response message
     */
    public List<T> findAll(Class<T> classOfT, QueryFilter filter, QuerySort sort) {
        log.info("getList");
        String query = String.format("select x from %s x", this.persistentClass.getSimpleName());
        return em.createQuery(query).getResultList();
    }

    /**
     * Find specific entity by Id
     * @param id
     * @return
     */
    public T findById(ID id) {
        T entity = em.find(this.persistentClass, id);
        return entity;
    }
}
