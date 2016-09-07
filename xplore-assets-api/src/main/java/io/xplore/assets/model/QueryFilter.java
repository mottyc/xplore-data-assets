
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model;


import java.util.ArrayList;
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
	private Map<String, List<String>> filters = new HashMap<String, List<String>>();
	public Map<String, List<String>> getFilters() { return filters; }


	/**
	 * Constructor
	 */
	public QueryFilter() {}

	/**
	 * Add filter expression to the map
	 * @param field
	 * @param value
     */
	public void addFilter(String field, String value) {
		List<String> fields = this.filters.get(field);
		if (fields == null) {
			fields = new ArrayList<String>();
			this.filters.put(field, fields);
		}
		fields.add(value);
	}

	/**
	 * Create query object from query param
	 * @param queryParams List of filters in the format of field:value
	 * @return QueryFilter
	 */
	public static QueryFilter createFromList(List<String> queryParams) {
		if ((queryParams == null) || (queryParams.size() == 0)) return null;

		QueryFilter queryFilter = new QueryFilter();
		for (String filter : queryParams) {
			String[] params = filter.split(":");
			if (params.length == 2) {
				queryFilter.addFilter(params[0], params[1]);
			}
		}
		return queryFilter;
	}

	/**
	 * Create query object from query param
	 * @param filters List of Filter objects
	 * @return QueryFilter
	 */
	public static QueryFilter createFromFilters(List<Filter> filters) {
		if ((filters == null) || (filters.size() == 0)) return null;

		QueryFilter queryFilter = new QueryFilter();
		for (Filter filter : filters) {
			queryFilter.addFilter(filter.getField(), filter.getValue());
		}
		return queryFilter;
	}
 }
