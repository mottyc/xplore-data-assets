package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaDatabase;

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

}
