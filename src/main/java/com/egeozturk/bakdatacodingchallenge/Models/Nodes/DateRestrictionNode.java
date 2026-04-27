package com.egeozturk.bakdatacodingchallenge.models.nodes;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

import jakarta.annotation.Nullable;

public record DateRestrictionNode (
    String column,
    String maxDate,
    String minDate,
    QueryNode child
) implements INode {
    public DateRestrictionNode(String column, @Nullable String maxDate, @Nullable String minDate, QueryNode child) {
        this.column = column;
        this.maxDate = maxDate;
        this.minDate = minDate;
        this.child = child;
    }

    @Override
    public String toSql(ISqlDialect sqlDialect) {
        return sqlDialect.render(this);
    }
}
