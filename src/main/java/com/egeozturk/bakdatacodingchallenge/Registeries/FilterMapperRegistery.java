package com.egeozturk.bakdatacodingchallenge.Registeries;

import java.util.HashMap;
import java.util.Map;

import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.Mappers.Filter.EqualFilterMapper;
import com.egeozturk.bakdatacodingchallenge.Mappers.Filter.IFilterMapper;
import com.egeozturk.bakdatacodingchallenge.Mappers.Filter.InFilterMapper;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;

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
