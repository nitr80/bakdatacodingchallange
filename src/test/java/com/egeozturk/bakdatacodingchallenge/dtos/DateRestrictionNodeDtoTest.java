package com.egeozturk.bakdatacodingchallenge.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.dtos.nodes.DateRestrictionNodeDto;

import tools.jackson.databind.ObjectMapper;

public class DateRestrictionNodeDtoTest {
    @Test
    public void shouldSerializeJsonIntoDto_whenMinMaxDate() {
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

        assertEquals(DateRestrictionNodeDto.class, dateRestrictionNodeDto.getClass());
    }

    @Test
    public void shouldSerializeJsonIntoDto_whenNoMinMaxDate() {
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

        assertEquals(DateRestrictionNodeDto.class, dateRestrictionNodeDto.getClass());
    }
}
