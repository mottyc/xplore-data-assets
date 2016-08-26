package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaColumn;

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
     * Get list of columns
     * @param tableKey Filter by table key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaColumn>
     */
    QueryResponse<MdaColumn> find(int tableKey, int pageNumber, int pageSize);
}
