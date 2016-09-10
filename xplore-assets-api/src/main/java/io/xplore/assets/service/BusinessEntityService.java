package io.xplore.assets.service;

import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;

import java.util.List;

/**
 * Created by motty on 26/08/2016.
 */
public interface BusinessEntityService {

    /**
     * Get single business entity by key
     *
     * @param key entity key
     * @return EntityResponse<MdaBusinessEntity>
     */
    EntityResponse<MdaBusinessEntity> get(int key);

    /**
     * Update business entity
     *
     * @param entity Business entity to update
     * @return EntityResponse<MdaBusinessEntity>
     */
    EntityResponse<MdaBusinessEntity> set(MdaBusinessEntity entity);

    /**
     * Get list of business entities
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaBusinessEntity>
     */
    QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize);

    /**
     * Get list of business entities with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaBusinessEntity>
     */
    QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);

    // ------------------ Entity related systems actions ---------------------------------------------------------------

    /**
     * Get business entity related systems
     * @param entityKey Entity key
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> getSystems(int entityKey);

    /**
     * Link systems to business entity
     * @param entityKey Entity key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> linkSystems(int entityKey, int[] systemsKeys);

    /**
     * Unlink systems from business entity
     * @param entityKey Entity key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> unlinkSystems(int entityKey, int[] systemsKeys);

    // ------------------ Entity related tables actions ----------------------------------------------------------------

    /**
     * Get business entity related table
     * @param entityKey Entity key
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> getTables(int entityKey);

    /**
     * Link tables to business entity
     * @param entityKey Entity key
     * @param tablesKeys List of keys to link
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> linkTables(int entityKey, int[] tablesKeys);

    /**
     * Unlink tables from business entity
     * @param entityKey Entity key
     * @param tablesKeys List of keys to unlink
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> unlinkTables(int entityKey, int[] tablesKeys);
}
