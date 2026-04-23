package com.egeozturk.bakdatacodingchallenge.Models.Filters;

import java.util.List;
import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;

public class InFilter implements IFilter {
    private final String FILTER_KEYWORD = "IN";

    private final List<String> valueList;
    private final String column;

    public InFilter(String column, List<String> values) {
        this.column = column;
        this.valueList = values;
    }

    @Override
    public String toSql() {
        String valuesString = valueList.stream()
            .map(value -> APOSTROPHE + value + APOSTROPHE)
            .collect(Collectors.joining(COMMA + SPACE));

        return column + SPACE + FILTER_KEYWORD + SPACE + LEFT_PARENTHESES + valuesString + RIGHT_PARENTHESES;
    }

}
