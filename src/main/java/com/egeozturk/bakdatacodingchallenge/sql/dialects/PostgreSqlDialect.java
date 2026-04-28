package com.egeozturk.bakdatacodingchallenge.sql.dialects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.models.filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.models.filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.models.nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

public class PostgreSqlDialect implements ISqlDialect {
    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final String WHERE = "WHERE";
    private final String AND = "AND";
    private final String DATE_FORMAT = "'YYYY'";
    private final String BIG_SPACE = "    ";
    private final String NEXT_LINE = "\n";
    private final String FILTER_LINE_SEPERATOR = NEXT_LINE + BIG_SPACE + AND + " ";

    @Override
    public String render(QueryNode node) {
        String selectColumnsString = node.selectColumnList().stream()
            .collect(Collectors.joining(", "));

        String selectLine = SELECT + " " + selectColumnsString + NEXT_LINE;
        String fromLine = FROM + " " + node.table() + NEXT_LINE;

        List<String> filterLineList = new ArrayList<>();
        for (IFilter filter : node.filterList()) {
            filterLineList.add(filter.toSql(this));
        }

        String whereString = String.join(FILTER_LINE_SEPERATOR, filterLineList);
        if (!whereString.isEmpty()) {
            whereString = WHERE + " " + whereString;
        }

        return selectLine + fromLine + whereString;
    }

    @Override
    public String render(DateRestrictionNode node) {
        final String GREATER_EQUAL = ">=";
        final String LESS_EQUAL = "<=";

        String childQueryString = node.child().toSql(this);

        List<String> dateFilterLineList = new ArrayList<>();

        if (node.minDate() != null) {
            dateFilterLineList.add(toSqlDateFilter(GREATER_EQUAL, node.minDate(), node.column()));
        }

        if (node.maxDate() != null) {
            dateFilterLineList.add(toSqlDateFilter(LESS_EQUAL, node.maxDate(), node.column()));
        }

        String dateFilterLinesString = String.join(FILTER_LINE_SEPERATOR, dateFilterLineList);

        if (childQueryString.contains(WHERE)) {
            childQueryString += FILTER_LINE_SEPERATOR + dateFilterLinesString;
        } else {
            childQueryString += WHERE + " " + dateFilterLinesString;
        }


        return childQueryString;
    }

    private String toSqlDateFilter(String filterKeyword, String date, String column) {
        final String TO_DATE = "to_date";
        return column + " " + filterKeyword + " " + TO_DATE + "(" + "'" + date + "'" + "," + " " + DATE_FORMAT + ")";
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
