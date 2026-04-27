package com.egeozturk.bakdatacodingchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.egeozturk.bakdatacodingchallenge.sql.ISqlDialect;
import com.egeozturk.bakdatacodingchallenge.sql.dialects.PostgreSqlDialect;

@Configuration
public class SqlDialectConfig {
    @Value("${app.sql.dialect}")
    private String dialect;

    @Bean
    public ISqlDialect sqlDialect() {
        return switch (dialect.toLowerCase()) {
            case "postgres" -> new PostgreSqlDialect();
            default -> throw new IllegalArgumentException(
                "Unsupported SQL dialect: " + dialect
            );
        };
    }
}
