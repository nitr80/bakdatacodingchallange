package com.egeozturk.bakdatacodingchallenge.mappers.node;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;

public interface INodeMapper {
    String getType();
    INode mapDtoToModel(BaseNodeDto dto);
}
