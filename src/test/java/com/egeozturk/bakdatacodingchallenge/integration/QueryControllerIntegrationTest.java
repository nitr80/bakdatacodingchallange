package com.egeozturk.bakdatacodingchallenge.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QueryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnQueryResult_queryNodeWithFilter() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column4",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnQueryResult_queryNodeNoFilter() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1",
        "filters": [
            
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnQueryResult_queryNodeNoSelect() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column4",
            "value": "a"
            }
        ],
        "select": []
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }



    // ****************** DATE_RESTRICTION *************************

    @Test
    void shouldReturnQueryResult_dateRestrictionNodeNoDate() throws Exception {

        String requestJson = 
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


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnQueryResult_dateRestrictionNodeWithDate() throws Exception {

        String requestJson = 
        """
        {
            "type": "DATE_RESTRICTION",
            "column": "dateColumn",
            "minDate": "2021",
            "maxDate": "2022",
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


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnQueryResult_dateRestrictionNodeNoFilter() throws Exception {

        String requestJson = 
        """
        {
            "type": "DATE_RESTRICTION",
            "column": "dateColumn",
            "minDate": "2021",
            "maxDate": "2022",
            "child": {
                "type": "QUERY",
                "table": "table1",
                "filters": [
                    
                ],
                "select": ["column1", "column2"]
            }
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturnQueryResult_dateRestrictionNodeNoFilterNoDate() throws Exception {

        String requestJson = 
        """
        {
            "type": "DATE_RESTRICTION",
            "column": "dateColumn",
            "child": {
                "type": "QUERY",
                "table": "table1",
                "filters": [
                    
                ],
                "select": ["column1", "column2"]
            }
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk());
    }


    // ********************* Exceptions **************************
    
    @Test
    void shouldThrowInternalServerError_queryNodeWithWrongTableName() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table12",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column4",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldThrowInternalServerError_queryNodeWithWrongColumnName() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column44",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldThrowBadRequest_queryNodeWithoutType() throws Exception {

        String requestJson = 
        """
        {
        "table": "table1",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column4",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowBadRequest_queryNodeWithoutTable() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "filters": [
            {
            "type": "EQUAL",
            "column": "column4",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowBadRequest_queryNodeWithoutFilterColumn() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1"
        "filters": [
            {
            "type": "EQUAL",
            "value": "a"
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowBadRequest_queryNodeWithEmptyInFilterValues() throws Exception {

        String requestJson = 
        """
        {
        "type": "QUERY",
        "table": "table1"
        "filters": [
            {
            "type": "IN",
            "column": "column4",
            "values": []
            }
        ],
        "select": ["column1"]
        }
        """;


        mockMvc.perform(post("/sql/query")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isBadRequest());
    }
}
