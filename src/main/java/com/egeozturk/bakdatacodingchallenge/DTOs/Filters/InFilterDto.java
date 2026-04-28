package com.egeozturk.bakdatacodingchallenge.dtos.filters;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InFilterDto extends BaseFilterDto {
    @NotNull
    @Size(min = 1)
    private List<String> values;
    @NotNull
    private String column;

    public List<String> getValues() {
        return values;
    }

    public String getColumn() {
        return column;
    }
}
