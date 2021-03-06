package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSchema;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;

/**
 * Created by motty on 26/08/2016.
 */
public interface SchemaService {
    /**
     * Get single schema by key
     *
     * @param key schema key
     * @return EntityResponse<MdaSchema>
     */
    EntityResponse<MdaSchema> get(int key);

    /**
     * Update schema
     *
     * @param schema Schema to update
     * @return EntityResponse<MdaSchema>
     */
    EntityResponse<MdaSchema> set(MdaSchema schema);

    /**
     * Get list of schemas
     * @param databaseKey Filter by database key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSchema>
     */
    QueryResponse<MdaSchema> find(int databaseKey, int pageNumber, int pageSize);

    /**
     * Get list of schemas with filter and sort
     * @param databaseKey Filter by database key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaSchema>
     */
    QueryResponse<MdaSchema> find(int databaseKey, int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);
}
