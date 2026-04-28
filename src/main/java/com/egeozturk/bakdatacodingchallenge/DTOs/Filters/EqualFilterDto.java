package com.egeozturk.bakdatacodingchallenge.dtos.filters;

import jakarta.validation.constraints.NotNull;

public class EqualFilterDto extends BaseFilterDto {
    @NotNull
    private String value;
    @NotNull
    private String column;

    public String getValue() {
        return value;
    }

    public String getColumn() {
        return column;
    }
}
