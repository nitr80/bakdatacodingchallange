package com.egeozturk.bakdatacodingchallenge.models.filters;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

public record EqualFilter (
    String column,
    String value
) implements IFilter{
    public EqualFilter(String column, String value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toSql(ISqlDialect sqlDialect) {
        return sqlDialect.render(this);
    }
}
