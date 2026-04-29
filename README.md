# Bakdata Coding Challenge

## Overview
This project is a Java + Spring Boot REST API that accepts a graph-like JSON query structure, translates it into SQL, executes it against a SQL database, and returns the result.

## Running the API
- Make sure you have installed Java 21+ and Maven.
- Create a locally running PostreSQL database.
- Go to the project folder, open terminal and copy this:
```
DB_URL=jdbc:postgresql://localhost:{PORT}/{DATABASE_NAME} \
DB_USER={LOCAL_USER} \                  
DB_PASSWORD={PASSWORD} \
APP_SQL_DIALECT=postgres \            
./mvnw spring-boot:run 
```

- Make sure you entered PORT, DATABASE_NAME, LOCAL_USER and PASSWORD correctly. If your database doesn't have a password, you can leave it empty
- Run the project

### Alternative
If the database cannot be connected for some reason, you can also try typing the same information directly into the `application.properties` file.

Example:
```
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/querydb}
spring.datasource.username=${DB_USER:egeozturk}
spring.datasource.password=${DB_PASSWORD:}

app.sql.dialect=${APP_SQL_DIALECT:postgres}
```

## Sending Requests
To send requests, you can use the following after making sure you have entered your PORT correctly:

```
curl -X POST http://localhost:{PORT}/sql/query \
-H "Content-Type: application/json" \
-d '{
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
}'
```
This request corresponds to the following PostgreSQL query:

```
SELECT column1, column2
FROM table1
WHERE column3 IN ('foo', 'bar')
    AND column4 = 'a'
    AND dateColumn >= to_date('2021-01-01', 'YYYY-MM-DD')
    AND dateColumn <= to_date('2022-12-31', 'YYYY-MM-DD')
```

For the table 'table1'
```
 column1 | column2 | column3 | column4 | datecolumn
---------+---------+---------+---------+------------
 value1  | value2  | foo     | a       | 2021-05-01
 value3  | value4  | bar     | a       | 2022-03-15
 value5  | value6  | baz     | b       | 2023-01-10
```
the example request would return the following:
```
[
    {"column1":"value1","column2":"value2"},
    {"column1":"value3","column2":"value4"}
]
```
