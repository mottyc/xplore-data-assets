package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaSchema;

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
     * Get list of schemas
     * @param databaseKey Filter by database key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaSchema>
     */
    QueryResponse<MdaSchema> find(int databaseKey, int pageNumber, int pageSize);
}
