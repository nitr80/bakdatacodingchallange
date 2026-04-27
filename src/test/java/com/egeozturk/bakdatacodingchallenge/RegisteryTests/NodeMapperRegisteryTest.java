package com.egeozturk.bakdatacodingchallenge.RegisteryTests;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.Models.Filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.Registeries.NodeMapperRegistry;

import tools.jackson.databind.ObjectMapper;

public class NodeMapperRegisteryTest {
    @Test
    public void shouldMapQueryNodeDtoToModel_whenNoFilter()
    {
        String json = 
        """
        {
            "type": "QUERY",
            "table": "table1",
            "filters": [],
            "select": ["column1", "column2"]
        }
        """;

        BaseNodeDto queryNodeDto = new ObjectMapper().readerFor(BaseNodeDto.class).readValue(json);
        
        ArrayList<IFilter> filterList = new ArrayList<>();

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("column1");
        selectColumnList.add("column2");

        QueryNode queryNode = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");

        INode mappedQueryNode = NodeMapperRegistry.mapDtoToModel(queryNodeDto);

        assertEquals(mappedQueryNode, queryNode);
    }

    @Test
    public void shouldMapQueryNodeDtoToModel_whenFilter()
    {
        String json = 
        """
        {
            "type": "QUERY",
            "table": "table1",
            "filters": [
                {
                    "type": "IN",
                    "values": ["foo", "bar"],
                    "column": "column3"
                },
                {
                    "type": "EQUAL",
                    "value": "a",
                    "column": "column4"
                }
            ],
            "select": ["column1", "column2"]
        }
        """;

        BaseNodeDto queryNodeDto = new ObjectMapper().readerFor(BaseNodeDto.class).readValue(json);
        
        ArrayList<IFilter> filterList = new ArrayList<>();
        ArrayList<String> inFilterValueList = new ArrayList<>();
        inFilterValueList.add("foo");
        inFilterValueList.add("bar");
        filterList.add(new InFilter("column3", inFilterValueList));
        filterList.add(new EqualFilter("column4", "a"));

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("column1");
        selectColumnList.add("column2");

        QueryNode queryNode = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");

        INode mappedQueryNode = NodeMapperRegistry.mapDtoToModel(queryNodeDto);

        assertEquals(mappedQueryNode, queryNode);
    }


    @Test
    public void shouldMapDateRestrictionNodeDtoToModel_whenDate()
    {
        String json = 
        """
        {
            "type": "DATE_RESTRICTION",
            "minDate": "2021",
            "maxDate": "2022",
            "column": "dateColumn",
            "child": {
                "type": "QUERY",
                "table": "table1",
                "filters": [
                    {
                        "type": "IN",
                        "values": ["foo", "bar"],
                        "column": "column3"
                    },
                    {
                        "type": "EQUAL",
                        "value": "a",
                        "column": "column4"
                    }
                ],
                "select": ["column1", "column2"]
            }
        }
        """;

        BaseNodeDto dateRestrictionNodeDto = new ObjectMapper().readerFor(BaseNodeDto.class).readValue(json);
        
        ArrayList<IFilter> filterList = new ArrayList<>();
        ArrayList<String> inFilterValueList = new ArrayList<>();
        inFilterValueList.add("foo");
        inFilterValueList.add("bar");
        filterList.add(new InFilter("column3", inFilterValueList));
        filterList.add(new EqualFilter("column4", "a"));

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("column1");
        selectColumnList.add("column2");

        QueryNode child = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");

        DateRestrictionNode dateRestrictionNode = new DateRestrictionNode("dateColumn", "2022", "2021", child);

        INode mappedDateRestrictionNode = NodeMapperRegistry.mapDtoToModel(dateRestrictionNodeDto);


        assertEquals(mappedDateRestrictionNode, dateRestrictionNode);
    }


    @Test
    public void shouldMapDateRestrictionNodeDtoToModel_whenNoDate()
    {
        String json = 
        """
        {
            "type": "DATE_RESTRICTION",
            "column": "dateColumn",
            "child": {
                "type": "QUERY",
                "table": "table1",
                "filters": [
                    {
                        "type": "IN",
                        "values": ["foo", "bar"],
                        "column": "column3"
                    },
                    {
                        "type": "EQUAL",
                        "value": "a",
                        "column": "column4"
                    }
                ],
                "select": ["column1", "column2"]
            }
        }
        """;

        BaseNodeDto dateRestrictionNodeDto = new ObjectMapper().readerFor(BaseNodeDto.class).readValue(json);
        
        ArrayList<IFilter> filterList = new ArrayList<>();
        ArrayList<String> inFilterValueList = new ArrayList<>();
        inFilterValueList.add("foo");
        inFilterValueList.add("bar");
        filterList.add(new InFilter("column3", inFilterValueList));
        filterList.add(new EqualFilter("column4", "a"));

        ArrayList<String> selectColumnList = new ArrayList<>();
        selectColumnList.add("column1");
        selectColumnList.add("column2");

        QueryNode child = new QueryNode(
            filterList, 
            selectColumnList, 
            "table1");

        DateRestrictionNode dateRestrictionNode = new DateRestrictionNode("dateColumn", null, null, child);

        INode mappedDateRestrictionNode = NodeMapperRegistry.mapDtoToModel(dateRestrictionNodeDto);

        assertEquals(mappedDateRestrictionNode, dateRestrictionNode);
    }
}
