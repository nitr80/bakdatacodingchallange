package com.egeozturk.bakdatacodingchallenge.registeries;

import java.util.HashMap;
import java.util.Map;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.mappers.node.DateRestrictionNodeMapper;
import com.egeozturk.bakdatacodingchallenge.mappers.node.INodeMapper;
import com.egeozturk.bakdatacodingchallenge.mappers.node.QueryNodeMapper;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;

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
