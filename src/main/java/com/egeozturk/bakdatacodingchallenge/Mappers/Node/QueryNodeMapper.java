package com.egeozturk.bakdatacodingchallenge.mappers.node;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.dtos.nodes.QueryNodeDto;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.registeries.FilterMapperRegistery;
import com.egeozturk.bakdatacodingchallenge.types.NodeType;

public class QueryNodeMapper implements INodeMapper {

    @Override
    public String getType() {
        return NodeType.QUERY.toString();
    }

    @Override
    public INode mapDtoToModel(BaseNodeDto dto) {
        QueryNodeDto queryNodeDto = (QueryNodeDto) dto;

        List<IFilter> filters = queryNodeDto.getFilters().stream()
            .map(FilterMapperRegistery::mapDtoToModel)
            .toList();

        return new QueryNode(filters, queryNodeDto.getSelect(), queryNodeDto.getTable());
    }

}
