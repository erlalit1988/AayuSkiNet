package com.aayuskinet.api.helpers;

import java.util.List;

public class Pagination<T> {
    private int pageIndex;
    private int pageSize;
    private long count; // Use long for count as it can be large
    private List<T> data;

    public Pagination(int pageIndex, int pageSize, long count, List<T> data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.count = count;
        this.data = data;
    }

    // getters
    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }
}
