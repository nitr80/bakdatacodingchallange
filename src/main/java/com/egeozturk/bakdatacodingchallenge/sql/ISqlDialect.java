package com.egeozturk.bakdatacodingchallenge.sql;

import com.egeozturk.bakdatacodingchallenge.models.filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.models.filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.models.nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;

public interface ISqlDialect {
    String render(QueryNode node);
    String render(DateRestrictionNode node);
    String render(EqualFilter filter);
    String render(InFilter filter);
}
