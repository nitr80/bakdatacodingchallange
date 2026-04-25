package com.egeozturk.bakdatacodingchallenge.NodeTests;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.Models.Filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.QueryNode;

public class QueryNodeTest {

    @Test
    void shouldConvertToSqlString() {
        ArrayList<IFilter> filterList = new ArrayList<>();
        ArrayList<String> inFilterValueList = new ArrayList<>();
        filterList.add(new EqualFilter("column1", "a"));
        inFilterValueList.add("b");
        inFilterValueList.add("c");
        filterList.add(new InFilter("column2", inFilterValueList));

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("*");

        QueryNode queryNode = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");
        
        String expectedString = 
        """
        SELECT *
        FROM table1
        WHERE column1 = 'a'
            AND column2 IN ('b', 'c')
        """;

        assertEquals(
            expectedString,
            queryNode.toSql()
        );
    }

    @Test
    void shouldConvertToSqlString_whenNoFilter() {
        ArrayList<IFilter> filterList = new ArrayList<>();

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("*");

        QueryNode queryNode = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");
        
        String expectedString = 
        """
        SELECT *
        FROM table1
        """;

        assertEquals(
            expectedString,
            queryNode.toSql()
        );
    }
}
