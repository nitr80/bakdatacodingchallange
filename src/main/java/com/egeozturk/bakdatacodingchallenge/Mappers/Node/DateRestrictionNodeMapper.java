package com.egeozturk.bakdatacodingchallenge.Mappers.Node;

import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.DateRestrictionNodeDto;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.DateRestrictionNode;
import com.egeozturk.bakdatacodingchallenge.Models.Nodes.QueryNode;
import com.egeozturk.bakdatacodingchallenge.Registeries.NodeMapperRegistry;
import com.egeozturk.bakdatacodingchallenge.Types.NodeType;

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
