package com.egeozturk.bakdatacodingchallenge.Models.Interfaces;

public interface INode {
    String SELECT = "SELECT";
    String FROM = "FROM";
    String WHERE = "WHERE";
    String AND = "AND";
    String SPACE = " ";
    String BIG_SPACE = "    ";
    String NEXT_LINE = "\n";
    String APOSTROPHE = "'";
    String LEFT_PARENTHESES = "(";
    String RIGHT_PARENTHESES = ")";

    public String toSql();
}
