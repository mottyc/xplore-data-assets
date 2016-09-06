package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSystem;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

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
}
