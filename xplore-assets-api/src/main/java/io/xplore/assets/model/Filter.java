
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

public class Filter {

	/**
	 * Filter field
	 */
	private String field;
	public String getField() { return field; }
	public void setField(String value) { this.field = value; }

	/**
	 * Filter by value
	 */
	private String value;
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }

	/**
	 * Constructor
	 */
	public Filter() {}

	/**
	 * Constructor with parameters
	 * @param field
	 * @param val
	 */
	public Filter(String field, String val) {
		this.setField(field);
		this.setValue(val);
	}
 }
