
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filter definition object
 *
 * @author mottyc
 *
 */

public class QueryFilter {

	/**
	 * Filters
	 */
	private Map<String, String> filters = new HashMap<String, String>();
	public Map<String, String> getFilters() { return filters; }


	/**
	 * Constructor
	 */
	public QueryFilter() {}

	/**
	 * Create query object from query param
	 * @param queryParams Sort phrase in the format of field:direction
	 * @return
	 */
	public static QueryFilter create(List<String> queryParams) {
		if ((queryParams == null) || (queryParams.size() == 0)) return null;

		QueryFilter queryFilter = new QueryFilter();
		for (String filter : queryParams) {
			String[] params = filter.split(":");
			if (params.length == 2) {
				queryFilter.filters.put(params[0], params[1]);
			}
		}
		return queryFilter;
	}
 }
