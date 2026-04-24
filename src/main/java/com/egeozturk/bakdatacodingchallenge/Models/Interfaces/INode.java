package com.egeozturk.bakdatacodingchallenge.Models.Interfaces;

public interface INode {
    String SELECT = "SELECT";
    String FROM = "FROM";
    String WHERE = "WHERE";
    String AND = "AND";
    String BIG_SPACE = "    ";
    String NEXT_LINE = "\n";

    public String toSql();
}
