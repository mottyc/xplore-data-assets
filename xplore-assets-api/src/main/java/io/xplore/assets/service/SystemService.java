package io.xplore.assets.service;

import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;

/**
 * Created by motty on 26/08/2016.
 */
public interface SystemService {
    /**
     * Get single system by key
     *
     * @param key system key
     * @return EntityResponse<MdaSystem>
     */
    EntityResponse<MdaSystem> get(int key);

    /**
     * Update system
     *
     * @param system System to update
     * @return EntityResponse<MdaSystem>
     */
    EntityResponse<MdaSystem> set(MdaSystem system);

    /**
     * Get list of systems
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSystem>
     */
    QueryResponse<MdaSystem> find(int pageNumber, int pageSize);

    /**
     * Get list of systems with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaSystem>
     */
    QueryResponse<MdaSystem> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);

    // ------------------ System related servers actions ---------------------------------------------------------------

    /**
     * Get system related servers
     * @param systemKey System key
     * @return EntityResponse[MdaServer]
     */
    EntitiesResponse<MdaServer> getServers(int systemKey);

    /**
     * Link servers to system
     * @param systemKey System key
     * @param serversKeys List of keys to link
     * @return EntityResponse[MdaServer]
     */
    EntitiesResponse<MdaServer> linkServers(int systemKey, int[] serversKeys);

    /**
     * Unlink servers from system
     * @param systemKey System key
     * @param serversKeys List of keys to unlink
     * @return EntityResponse[MdaServer]
     */
    EntitiesResponse<MdaServer> unlinkServers(int systemKey, int[] serversKeys);

    // ------------------ System related entities actions --------------------------------------------------------------

    /**
     * Get system related entities
     * @param systemKey System key
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> getEntities(int systemKey);

    /**
     * Link entities to system
     * @param systemKey System key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> linkEntities(int systemKey, int[] entitiesKeys);

    /**
     * Unlink entities from system
     * @param systemKey System key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> unlinkEntities(int systemKey, int[] entitiesKeys);
}
