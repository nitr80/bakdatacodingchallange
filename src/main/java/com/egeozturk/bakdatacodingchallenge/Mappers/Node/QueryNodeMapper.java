package com.egeozturk.bakdatacodingchallenge.Mappers.Node;

import java.util.List;

import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.QueryNodeDto;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.Registeries.FilterMapperRegistery;
import com.egeozturk.bakdatacodingchallenge.Types.NodeType;

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
