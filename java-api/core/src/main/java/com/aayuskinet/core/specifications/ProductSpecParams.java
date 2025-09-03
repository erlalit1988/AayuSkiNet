package com.aayuskinet.core.specifications;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSpecParams {
    private static final int MAX_PAGE_SIZE = 50;
    private int pageIndex = 1;
    private int pageSize = 6;
    private List<String> brands = List.of();
    private List<String> types = List.of();
    private String sort;
    private String search;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize > MAX_PAGE_SIZE) ? MAX_PAGE_SIZE : pageSize;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        if (brands != null) {
            this.brands = brands.stream()
                    .flatMap(s -> Arrays.stream(s.split(",")))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        if (types != null) {
            this.types = types.stream()
                    .flatMap(s -> Arrays.stream(s.split(",")))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        if (search != null) {
            this.search = search.toLowerCase();
        }
    }
}