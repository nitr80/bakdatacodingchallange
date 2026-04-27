package com.egeozturk.bakdatacodingchallenge.dtos.filters;

public class EqualFilterDto extends BaseFilterDto {
    private String value;
    private String column;

    public String getValue() {
        return value;
    }

    public String getColumn() {
        return column;
    }
}
