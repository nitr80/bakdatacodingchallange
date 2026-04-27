package com.egeozturk.bakdatacodingchallenge.models.filters;

import java.util.List;
import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;

public record InFilter (
    String column,
    List<String> valueList
) implements IFilter {
    private static final String FILTER_KEYWORD = "IN";

    public InFilter(String column, List<String> valueList) {
        this.column = column;
        this.valueList = List.copyOf(valueList);
    }

    @Override
    public String toSql() {
        String valuesString = valueList.stream()
            .map(value -> "'" + value + "'")
            .collect(Collectors.joining("," + " "));

        return column + " " + FILTER_KEYWORD + " " + "(" + valuesString + ")";
    }

}
