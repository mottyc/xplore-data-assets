package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaDatabase;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

/**
 * Created by motty on 26/08/2016.
 */
public interface DatabaseService {
    /**
     * Get single database by key
     *
     * @param key Database key
     * @return EntityResponse<MdaDatabase>
     */
    EntityResponse<MdaDatabase> get(int key);

    /**
     * Get list of databases
     * @param serverKey Filter by server key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaDatabase>
     */
    QueryResponse<MdaDatabase> find(int serverKey, int pageNumber, int pageSize);

    /**
     * Get list of databases with filter and sort
     * @param serverKey Filter by server key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaDatabase>
     */
    QueryResponse<MdaDatabase> find(int serverKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);
}
