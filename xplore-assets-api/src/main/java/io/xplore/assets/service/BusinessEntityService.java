package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaBusinessEntity;

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
     * Get list of business entities
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaBusinessEntity>
     */
    QueryResponse<MdaBusinessEntity> find(int pageNumber, int pageSize);
}
