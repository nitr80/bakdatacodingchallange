package com.egeozturk.bakdatacodingchallenge.registeries;

import java.util.HashMap;
import java.util.Map;

import com.egeozturk.bakdatacodingchallenge.dtos.filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.mappers.filter.EqualFilterMapper;
import com.egeozturk.bakdatacodingchallenge.mappers.filter.InFilterMapper;
import com.egeozturk.bakdatacodingchallenge.mappers.interfaces.IFilterMapper;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;

public class FilterMapperRegistery {
    private static final Map<String, IFilterMapper> mappers = new HashMap<>();

    static {
        register(new InFilterMapper());
        register(new EqualFilterMapper());
    }

    public static void register(IFilterMapper mapper) {
        mappers.put(mapper.getType(), mapper);
    }

    public static IFilter mapDtoToModel(BaseFilterDto dto) {
        IFilterMapper mapper = mappers.get(dto.getType().toString());

        return mapper.mapDtoToModel(dto);
    }
}
