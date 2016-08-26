package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaTable;

/**
 * Created by motty on 26/08/2016.
 */
public interface TableService {
    /**
     * Get single table by key
     *
     * @param key table key
     * @return EntityResponse<MdaTable>
     */
    EntityResponse<MdaTable> get(int key);

    /**
     * Get list of systems
     * @param schemaKey Filter by schema key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaTable>
     */
    QueryResponse<MdaTable> find(int schemaKey, int pageNumber, int pageSize);
}
