package com.egeozturk.bakdatacodingchallenge.Models.Nodes;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

import jakarta.annotation.Nullable;

public class DateRestrictionNode implements INode {
    private final String GREATER_EQUAL = ">=";
    private final String LESS_EQUAL = "<=";
    private final String TO_DATE = "to_date";

    @Nullable
    private final String minDate;
    @Nullable
    private final String maxDate;
    private final String column;
    private final QueryNode child;

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
