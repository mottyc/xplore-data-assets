/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Query result response message
 * @param <T> Type of object to query
 */
public class QueryResponse<T> extends BaseResponse {

    /**
     * Page (Bulk) number
     */
    private int page;
    public int getPage() { return page; }
    public void setPage(int value) { this.page = value; }

    /**
     * Total number of pages
     */
    private int pages;
    public int getPages() { return pages; }
    public void setPages(int value) { this.pages = value; }

    /**
     * List of objects in the current result set
     */
    private List<T> list = new ArrayList<T>();
    public List<T> getList() { return list; }
    public void setList(List<T> value) { this.list = value; }

    /**
     * Total count of items in the query
     */
    private long count;
    public long getCount() { return count; }
    public void setCount(long value) { this.count = value; }

    /**
     * Default constructor
     */
    public QueryResponse() {}

    /**
     * Constructor with paging data
     * @param page Current result page
     * @param pages Total number of pages
     */
    public QueryResponse(int page, int pages) {
        this.setPage(page);
        this.setPages(pages);
    }

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public QueryResponse(String message, Object... args) { super(message, args); }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public QueryResponse(Exception exp) { super(exp); }

    /**
     * Increase total count by value
     * @param value
     */
    public long increase(long value) {
        this.count += value;
        return this.count;
    }

    /**
     * Increase total count by one
     */
    public long increase() { return this.increase(1); }
}
