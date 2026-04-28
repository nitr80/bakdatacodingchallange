package com.egeozturk.bakdatacodingchallenge.dtos.nodes;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DateRestrictionNodeDto extends BaseNodeDto {
    @Nullable
    private String minDate;
    @Nullable
    private String maxDate;
    @NotNull
    private String column;
    @Valid
    @NotNull
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
