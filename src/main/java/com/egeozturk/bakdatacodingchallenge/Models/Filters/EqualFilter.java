package com.egeozturk.bakdatacodingchallenge.models.filters;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;

public record EqualFilter (
    String column,
    String value
) implements IFilter{
    private static final String FILTER_KEYWORD = "=";

    public EqualFilter(String column, String value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toSql() {
        return column + " " + FILTER_KEYWORD + " " + "'" + value + "'";
    }
}
