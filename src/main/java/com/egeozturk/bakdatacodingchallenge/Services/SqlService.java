package com.egeozturk.bakdatacodingchallenge.services;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.services.interfaces.ISqlService;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

@Service
public class SqlService implements ISqlService {
    private final ISqlDialect sqlDialect;
    private final JdbcTemplate jdbcTemplate;

    public SqlService(ISqlDialect sqlDialect, JdbcTemplate jdbcTemplate) {
        this.sqlDialect = sqlDialect;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> executeQueryFromNode(INode node)
    {
        System.out.println(node);
        String sqlString = node.toSql(sqlDialect);
        System.out.println(sqlString);
        return jdbcTemplate.queryForList(sqlString);
    }
}
