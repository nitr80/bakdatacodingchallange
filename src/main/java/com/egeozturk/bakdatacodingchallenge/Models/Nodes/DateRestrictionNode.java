package com.egeozturk.bakdatacodingchallenge.Models.Nodes;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

import jakarta.annotation.Nullable;

public record DateRestrictionNode (
    String column,
    String maxDate,
    String minDate,
    QueryNode child
) implements INode {
    private static final String GREATER_EQUAL = ">=";
    private static final String LESS_EQUAL = "<=";
    private static final String TO_DATE = "to_date";


    public DateRestrictionNode(String column, @Nullable String maxDate, @Nullable String minDate, QueryNode child) {
        this.column = column;
        this.maxDate = maxDate;
        this.minDate = minDate;
        this.child = child;
    }

    @Override
    public String toSql() {
        String childQueryString = child.toSql();

        if (minDate != null)
        {
            childQueryString += toSqlDateFilter(GREATER_EQUAL, minDate);
        }

        if (maxDate != null)
        {
            childQueryString += toSqlDateFilter(LESS_EQUAL, maxDate);
        }

        return childQueryString;
    }

    private String toSqlDateFilter(String filterKeyword, String date)
    {
        return BIG_SPACE + AND + " " + column + " " + filterKeyword + " " + TO_DATE + "(" + "'" + date + "'" + ")" + NEXT_LINE;
    }
    
}
