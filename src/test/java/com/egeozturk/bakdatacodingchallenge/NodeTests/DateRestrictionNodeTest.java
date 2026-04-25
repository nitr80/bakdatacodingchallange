package com.egeozturk.bakdatacodingchallenge.NodeTests;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.Models.Filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.QueryNode;

class DateRestrictionNodeTest {

    @Test
    void shouldAppendDateRestriction_whenChildHasWhere() {
        ArrayList<IFilter> filterList = new ArrayList<>();
        filterList.add(new EqualFilter("column1", "a"));

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("*");

        QueryNode child = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");

        DateRestrictionNode node = new DateRestrictionNode("dateColumn", "2022", "2021", child);

        String sqlString = node.toSql();
        String expectedString = 
        """
        SELECT *
        FROM table1
        WHERE column1 = 'a'
            AND dateColumn >= to_date('2021')
            AND dateColumn <= to_date('2022')
        """;

        assertEquals(
            expectedString,
            sqlString
        );
    }
}