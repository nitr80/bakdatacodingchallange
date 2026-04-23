package com.egeozturk.bakdatacodingchallenge.Models.Nodes;

import java.util.List;
import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

public class QueryNode implements INode {
    private final String table;
    private final List<IFilter> filterList;
    private final List<String> selectColumnList;

    public QueryNode(List<IFilter> filterList, List<String> selectColumnList, String table) {
        this.filterList = filterList;
        this.selectColumnList = selectColumnList;
        this.table = table;
    }

    @Override
    public String toSql() {
        String selectColumnsString = selectColumnList.stream()
            .collect(Collectors.joining(", "));

        String selectLine = SELECT + SPACE + selectColumnsString + NEXT_LINE;
        String fromLine = FROM + SPACE + table + NEXT_LINE;

        String filterLines = "";

        for (int i = 0; i < filterList.size(); i++)
        {
            if (i == 0)
            {
                filterLines = filterLines.concat(WHERE + SPACE + filterList.get(i).toSql() + NEXT_LINE);
                continue;
            }

            filterLines = filterLines.concat(BIG_SPACE + AND + SPACE + filterList.get(i).toSql() + NEXT_LINE);
        }

        return selectLine + fromLine + filterLines;
    }
}
