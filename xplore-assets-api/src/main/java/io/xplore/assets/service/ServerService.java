package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaServer;

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
     * Get list of servers
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaServer>
     */
    QueryResponse<MdaServer> find(String typeCode, int pageNumber, int pageSize);
}
