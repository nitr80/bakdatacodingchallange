package com.egeozturk.bakdatacodingchallenge.models.interfaces;

import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

public interface IFilter {
    public String toSql(ISqlDialect sqlDialect);
}
