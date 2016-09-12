package io.xplore.assets.service;

import io.xplore.assets.database.model.MdaSystemEntity;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;

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

    // ------------------ Systems related actions ----------------------------------------------------------------------

    /**
     * Get table related systems
     * @param tableKey Table key
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> getSystems(int tableKey);

    /**
     * Link systems to table
     * @param tableKey Table key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> linkSystems(int tableKey, int[] systemsKeys);

    /**
     * Unlink systems from table
     * @param tableKey Table key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> unlinkSystems(int tableKey, int[] systemsKeys);

    // ------------------ Business entities related actions ------------------------------------------------------------

    /**
     * Get table related entities
     * @param tableKey Table key
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> getEntities(int tableKey);

    /**
     * Link entities to table
     * @param tableKey Table key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> linkEntities(int tableKey, int[] entitiesKeys);

    /**
     * Unlink entities from table
     * @param tableKey Table key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    EntitiesResponse<MdaBusinessEntity> unlinkEntities(int tableKey, int[] entitiesKeys);
}
