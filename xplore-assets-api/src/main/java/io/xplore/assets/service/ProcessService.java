package io.xplore.assets.service;

import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.*;

/**
 * Created by motty on 26/08/2016.
 */
public interface ProcessService {

    /**
     * Get single process by key
     *
     * @param key process key
     * @return EntityResponse<MdaProcess>
     */
    EntityResponse<MdaProcess> get(int key);

    /**
     * Update process
     *
     * @param process process to update
     * @return EntityResponse<MdaProcess>
     */
    EntityResponse<MdaProcess> set(MdaProcess process);

    /**
     * Get list of processes
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaProcess>
     */
    QueryResponse<MdaProcess> find(int pageNumber, int pageSize);

    /**
     * Get list of processes with filter and sort
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @param filter Query filter
     * @param sorting Query sort
     * @return QueryResponse<MdaProcess>
     */
    QueryResponse<MdaProcess> find(int pageNumber, int pageSize, QueryFilter filter, QuerySort sorting);

    // ------------------ Entity related systems actions ---------------------------------------------------------------

    /**
     * Get process related systems
     * @param processKey Process key
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> getSystems(int processKey);

    /**
     * Link systems to business entity
     * @param processKey Process key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> linkSystems(int processKey, int[] systemsKeys);

    /**
     * Unlink systems from business entity
     * @param processKey Process key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    EntitiesResponse<MdaSystem> unlinkSystems(int processKey, int[] systemsKeys);

    // ------------------ Entity related tables actions ----------------------------------------------------------------

    /**
     * Get business entity related table
     * @param processKey Process key
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> getTables(int processKey);

    /**
     * Link tables to process
     * @param processKey Process key
     * @param tablesKeys List of keys to link
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> linkTables(int processKey, int[] tablesKeys);

    /**
     * Unlink tables from process
     * @param processKey Process key
     * @param tablesKeys List of keys to unlink
     * @return EntityResponse[MdaTable]
     */
    EntitiesResponse<MdaTable> unlinkTables(int processKey, int[] tablesKeys);
}
