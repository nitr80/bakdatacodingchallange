package com.egeozturk.bakdatacodingchallenge.Models.Filters;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;

public class EqualFilter implements IFilter{
    private final String FILTER_KEYWORD = "=";

    private final String value;
    private final String column;

    public EqualFilter(String column, String value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toSql() {
        return column + " " + FILTER_KEYWORD + " " + "'" + value + "'";
    }
}
