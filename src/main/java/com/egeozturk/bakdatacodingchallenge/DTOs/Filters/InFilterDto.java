package com.egeozturk.bakdatacodingchallenge.DTOs.Filters;

import java.util.List;

public class InFilterDto extends BaseFilterDto {
    private  List<String> values;
    private  String column;

    public List<String> getValues() {
        return values;
    }

    public String getColumn() {
        return column;
    }
}
