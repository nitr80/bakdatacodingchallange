package com.egeozturk.bakdatacodingchallenge.services;

import org.springframework.stereotype.Service;

import com.egeozturk.bakdatacodingchallenge.services.interfaces.ISqlService;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

@Service
public class SqlService implements ISqlService {
    private final ISqlDialect sqlDialect;

    public SqlService(ISqlDialect sqlDialect) {
        this.sqlDialect = sqlDialect;
    }
}
