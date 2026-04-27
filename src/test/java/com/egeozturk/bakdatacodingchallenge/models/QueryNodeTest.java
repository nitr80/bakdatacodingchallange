package com.egeozturk.bakdatacodingchallenge.models;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.models.filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.models.filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.sql.dialects.PostgreSqlDialect;

public class QueryNodeTest {

    @Test
    public void shouldConvertToSqlString() {
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
            queryNode.toSql(new PostgreSqlDialect())
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
            queryNode.toSql(new PostgreSqlDialect())
        );
    }
}
