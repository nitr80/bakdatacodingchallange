package com.egeozturk.bakdatacodingchallenge.Registeries;

import java.util.HashMap;
import java.util.Map;

import com.egeozturk.bakdatacodingchallenge.DTOs.Nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.Mappers.Node.DateRestrictionNodeMapper;
import com.egeozturk.bakdatacodingchallenge.Mappers.Node.INodeMapper;
import com.egeozturk.bakdatacodingchallenge.Mappers.Node.QueryNodeMapper;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.INode;

public class NodeMapperRegistry {
    private static final Map<String, INodeMapper> mappers = new HashMap<>();

    static {
        register(new QueryNodeMapper());
        register(new DateRestrictionNodeMapper());
    }

    public static void register(INodeMapper mapper) {
        mappers.put(mapper.getType(), mapper);
    }

    public static INode mapDtoToModel(BaseNodeDto dto) {
        INodeMapper mapper = mappers.get(dto.getType().toString());

        return mapper.mapDtoToModel(dto);
    }
}
