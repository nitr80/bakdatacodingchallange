package com.egeozturk.bakdatacodingchallenge.dtos.nodes;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.dtos.filters.BaseFilterDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class QueryNodeDto extends BaseNodeDto {
    @NotNull
    private String table;
    @Valid
    @NotNull
    private List<BaseFilterDto> filters;
    @NotNull
    private List<String> select;

    public String getTable() {
        return table;
    }

    public List<BaseFilterDto> getFilters() {
        return filters;
    }

    public List<String> getSelect() {
        return select;
    }

}
