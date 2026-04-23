package com.egeozturk.bakdatacodingchallenge.Models.Interfaces;

public interface IFilter {
    String APOSTROPHE = "'";
    String COMMA = ",";
    String SPACE = " ";
    String LEFT_PARENTHESES = "(";
    String RIGHT_PARENTHESES = ")";

    public String toSql();
}
