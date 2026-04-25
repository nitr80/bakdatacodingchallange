package com.egeozturk.bakdatacodingchallenge.Mappers.Node;

import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

public interface INodeMapper {
    String getType();
    INode mapDtoToModel(BaseNodeDto dto);
}
