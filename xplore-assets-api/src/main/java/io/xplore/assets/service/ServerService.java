package io.xplore.assets.service;

import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaServer;
import io.xplore.assets.model.MdaSystem;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

/**
 * Created by motty on 26/08/2016.
 */
public interface ServerService {
    /**
     * Get single server by key
     *
     * @param key server key
     * @return EntityResponse<MdaServer>
     */
    EntityResponse<MdaServer> get(int key);

    /**
     * Update server
     *
     * @param server Server to update
     * @return EntityResponse<MdaServer>
     */
    EntityResponse<MdaServer> set(MdaServer server);

    /**
     * Get list of servers
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaServer>
     */
    QueryResponse<MdaServer> find(String typeCode, int pageNumber, int pageSize);

    /**
     * Get list of servers with filter and sort
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaServer>
     */
    QueryResponse<MdaServer> find(String typeCode, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);

    // ------------------ Entity related systems actions ---------------------------------------------------------------

    /**
     * Get server related systems
     * @param serverKey Server key
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> getSystems(int serverKey);

    /**
     * Link systems to server
     * @param serverKey Server key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> linkSystems(int serverKey, int[] systemsKeys);

    /**
     * Unlink systems from server
     * @param serverKey Entity key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> unlinkSystems(int serverKey, int[] systemsKeys);

}
