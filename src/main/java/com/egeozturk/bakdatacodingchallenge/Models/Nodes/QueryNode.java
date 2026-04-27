package com.egeozturk.bakdatacodingchallenge.Models.Nodes;

import java.util.List;
import java.util.stream.Collectors;

import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

public record QueryNode (
    List<IFilter> filterList,
    List<String> selectColumnList,
    String table
) implements INode {
    
    public QueryNode(List<IFilter> filterList, List<String> selectColumnList, String table) {
        this.filterList = List.copyOf(filterList);
        this.selectColumnList = List.copyOf(selectColumnList);
        this.table = table;
    }

    @Override
    public String toSql() {
        String selectColumnsString = selectColumnList.stream()
            .collect(Collectors.joining(", "));

        String selectLine = SELECT + " " + selectColumnsString + NEXT_LINE;
        String fromLine = FROM + " " + table + NEXT_LINE;

        String filterLines = "";

        for (int i = 0; i < filterList.size(); i++)
        {
            if (i == 0)
            {
                filterLines += WHERE + " " + filterList.get(i).toSql() + NEXT_LINE;
                continue;
            }

            filterLines += BIG_SPACE + AND + " " + filterList.get(i).toSql() + NEXT_LINE;
        }

        return selectLine + fromLine + filterLines;
    }
}
