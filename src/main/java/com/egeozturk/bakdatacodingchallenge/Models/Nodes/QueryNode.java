package com.egeozturk.bakdatacodingchallenge.models.nodes;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;

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
    public String toSql(ISqlDialect sqlDialect) {
        return sqlDialect.render(this);
    }
}
