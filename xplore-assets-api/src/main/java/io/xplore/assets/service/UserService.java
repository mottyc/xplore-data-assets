package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaUser;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

/**
 * Created by motty on 26/08/2016.
 */
public interface UserService {

    /**
     * Get single user by key
     *
     * @param key user key
     * @return EntityResponse<MdaUser>
     */
    EntityResponse<MdaUser> get(int key);

    /**
     * Update user
     *
     * @param user User to update
     * @return EntityResponse<MdaUser>
     */
    EntityResponse<MdaUser> set(MdaUser user);

    /**
     * Get list of users
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaUser>
     */
    QueryResponse<MdaUser> find(int pageNumber, int pageSize);

    /**
     * Get list of users with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaUser>
     */
    QueryResponse<MdaUser> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);
}
