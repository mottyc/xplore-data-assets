package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaColumn;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

/**
 * Created by motty on 26/08/2016.
 */
public interface ColumnService {

    /**
     * Get single column by key
     *
     * @param key Column key
     * @return EntityResponse<MdaColumn>
     */
    EntityResponse<MdaColumn> get(int key);

    /**
     * Update column
     *
     * @param column Column to update
     * @return EntityResponse<MdaDatabase>
     */
    EntityResponse<MdaColumn> set(MdaColumn column);

    /**
     * Get list of columns
     * @param tableKey Filter by table key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaColumn>
     */
    QueryResponse<MdaColumn> find(int tableKey, int pageNumber, int pageSize);

    /**
     * Get list of columns with filter and sort
     * @param tableKey Filter by table key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaColumn>
     */
    QueryResponse<MdaColumn> find(int tableKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);
}
