package com.egeozturk.bakdatacodingchallenge.DTOs.Nodes;

import jakarta.annotation.Nullable;

public class DateRestrictionNodeDto extends BaseNodeDto {
    @Nullable
    private String minDate;
    @Nullable
    private String maxDate;
    private String column;
    private QueryNodeDto child;

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getColumn() {
        return column;
    }

    public QueryNodeDto getChild() {
        return child;
    }
}
