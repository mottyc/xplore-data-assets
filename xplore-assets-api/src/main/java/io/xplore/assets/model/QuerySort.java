
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model;


/**
 * Sort definition object
 *
 * @author mottyc
 *
 */

public class QuerySort {

	/**
	 * Sort by field
	 */
	private String field;
	public String getField() { return field; }
	public void setField(String value) { this.field = value; }

	/**
	 * Sort direction
	 */
	private Direction direction = Direction.asc;
	public Direction getDirection() { return direction; }
	public void setDirection(Direction value) { this.direction = value; }

	/**
	 * Sort direction
	 */
	public enum Direction {asc, desc}

	/**
	 * Constructor
	 */
	public QuerySort() {}

	/**
	 * Constructor with parameters
	 * @param field
	 * @param dir
	 */
	public QuerySort(String field, String dir) {
		this.setField(field);
		this.setDirection(dir.equalsIgnoreCase("desc") ? Direction.desc : Direction.asc);
	}

	/**
	 * Create sorting object from query param
	 * @param queryParam Sort phrase in the format of field:direction
	 * @return
	 */
	public static QuerySort create(String queryParam) {
		if ((queryParam == null) || (queryParam.isEmpty())) return null;

		String[] params = queryParam.split(":");
		if (params.length == 2) {
			return new QuerySort(params[0], params[1]);
		} else {
			return new QuerySort(params[0], "asc");
		}
	}
 }
