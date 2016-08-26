package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSystem;

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
     * Get list of systems
     * @param typeCode Filter by server type code (empty string for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSystem>
     */
    QueryResponse<MdaSystem> find(String typeCode, int pageNumber, int pageSize);
}
