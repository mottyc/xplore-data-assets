package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaRelation;

/**
 * Created by motty on 26/08/2016.
 */
public interface RelationService {
    /**
     * Get single relation by key
     *
     * @param key Relation key
     * @return EntityResponse<MdaRelation>
     */
    EntityResponse<MdaRelation> get(int key);

    /**
     * Get list of relations
     * @param parentKey Filter by parent column key (-1 for no filter)
     * @param pageNumber Page number for pagination
     * @param pageSize   Number of items per page
     * @return QueryResponse<MdaRelation>
     */
    QueryResponse<MdaRelation> find(int parentKey, int pageNumber, int pageSize);
}
