package com.egeozturk.bakdatacodingchallenge.mappers.node;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.dtos.nodes.DateRestrictionNodeDto;
import com.egeozturk.bakdatacodingchallenge.mappers.interfaces.INodeMapper;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.models.nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.registeries.NodeMapperRegistry;
import com.egeozturk.bakdatacodingchallenge.types.NodeType;

public class DateRestrictionNodeMapper implements INodeMapper {

    @Override
    public String getType() {
        return NodeType.DATE_RESTRICTION.toString();
    }

    @Override
    public INode mapDtoToModel(BaseNodeDto dto) {
        DateRestrictionNodeDto dateRestrictionNodeDto = (DateRestrictionNodeDto) dto;

        return new DateRestrictionNode(
            dateRestrictionNodeDto.getColumn(),
            dateRestrictionNodeDto.getMaxDate(),
            dateRestrictionNodeDto.getMinDate(),
            (QueryNode) NodeMapperRegistry.mapDtoToModel(dateRestrictionNodeDto.getChild())
        );
    }

}
