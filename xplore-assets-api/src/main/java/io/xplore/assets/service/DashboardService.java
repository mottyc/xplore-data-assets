package io.xplore.assets.service;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.model.MdaUser;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.model.Statistics;

/**
 * Created by motty on 26/08/2016.
 */
public interface DashboardService {

    /**
     * Get statistics
     *
     * @return EntityResponse<Statistics>
     */
    EntityResponse<Statistics> get();

}
