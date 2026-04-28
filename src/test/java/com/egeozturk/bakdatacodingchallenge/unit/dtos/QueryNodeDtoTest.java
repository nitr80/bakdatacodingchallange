package com.egeozturk.bakdatacodingchallenge.unit.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.dtos.nodes.QueryNodeDto;

import tools.jackson.databind.ObjectMapper;

public class QueryNodeDtoTest {

    @Test
    public void shouldSerializeJsonIntoDto_whenFilter() {
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

        assertEquals(QueryNodeDto.class, queryNodeDto.getClass());
    }

    @Test
    public void shouldSerializeJsonIntoDto_whenNoFilter() {
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

        assertEquals(QueryNodeDto.class, queryNodeDto.getClass());
    }

}
