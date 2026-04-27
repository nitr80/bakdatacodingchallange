package com.egeozturk.bakdatacodingchallenge.models.filters;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

public record InFilter (
    String column,
    List<String> valueList
) implements IFilter {
    public InFilter(String column, List<String> valueList) {
        this.column = column;
        this.valueList = List.copyOf(valueList);
    }

    @Override
    public String toSql(ISqlDialect sqlDialect) {
        return sqlDialect.render(this);
    }

}
