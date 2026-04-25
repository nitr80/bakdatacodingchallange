package com.egeozturk.bakdatacodingchallenge.DTOs.Nodes;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.BaseFilterDto;

public class QueryNodeDto extends BaseNodeDto {
    private String table;
    private List<BaseFilterDto> filters;
    private String select;

    public String getTable() {
        return table;
    }

    public List<BaseFilterDto> getFilters() {
        return filters;
    }

    public String getSelect() {
        return select;
    }
}
