package com.egeozturk.bakdatacodingchallenge.sql.dialects;

import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.models.filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.models.filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.models.nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

public class PostgreSqlDialect implements ISqlDialect {
    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final String WHERE = "WHERE";
    private final String AND = "AND";
    private final String BIG_SPACE = "    ";
    private final String NEXT_LINE = "\n";

    @Override
    public String render(QueryNode node) {
        String selectColumnsString = node.selectColumnList().stream()
            .collect(Collectors.joining(", "));

        String selectLine = SELECT + " " + selectColumnsString + NEXT_LINE;
        String fromLine = FROM + " " + node.table() + NEXT_LINE;

        String filterLines = "";

        for (int i = 0; i < node.filterList().size(); i++)
        {
            if (i == 0)
            {
                filterLines += WHERE + " " + node.filterList().get(i).toSql(this) + NEXT_LINE;
                continue;
            }

            filterLines += BIG_SPACE + AND + " " + node.filterList().get(i).toSql(this) + NEXT_LINE;
        }

        return selectLine + fromLine + filterLines;
    }

    @Override
    public String render(DateRestrictionNode node) {
        final String GREATER_EQUAL = ">=";
        final String LESS_EQUAL = "<=";

        String childQueryString = node.child().toSql(this);

        if (node.minDate() != null)
        {
            childQueryString += toSqlDateFilter(GREATER_EQUAL, node.minDate(), node.column());
        }

        if (node.maxDate() != null)
        {
            childQueryString += toSqlDateFilter(LESS_EQUAL, node.maxDate(), node.column());
        }

        return childQueryString;
    }

    private String toSqlDateFilter(String filterKeyword, String date, String column)
    {
        final String TO_DATE = "to_date";
        return BIG_SPACE + AND + " " + column + " " + filterKeyword + " " + TO_DATE + "(" + "'" + date + "'" + ")" + NEXT_LINE;
    }


    // ************** FILTER *********************

    @Override
    public String render(EqualFilter filter) {
        final String EQUAL = "=";
        return filter.column() + " " + EQUAL + " " + "'" + filter.value() + "'";
    }

    @Override
    public String render(InFilter filter) {
        final String IN = "IN";

        String valuesString = filter.valueList().stream()
            .map(value -> "'" + value + "'")
            .collect(Collectors.joining("," + " "));

        return filter.column() + " " + IN + " " + "(" + valuesString + ")";
    }

}
