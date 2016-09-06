package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaTable;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

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
     * Update table
     *
     * @param table Table to update
     * @return EntityResponse<MdaTable>
     */
    EntityResponse<MdaTable> set(MdaTable table);

    /**
     * Get list of tables
     * @param schemaKey Filter by schema key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaTable>
     */
    QueryResponse<MdaTable> find(int schemaKey, int pageNumber, int pageSize);

    /**
     * Get list of tables with filter and sort
     * @param schemaKey Filter by schema key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaTable>
     */
    QueryResponse<MdaTable> find(int schemaKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);
}
