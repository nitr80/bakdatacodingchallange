package com.egeozturk.bakdatacodingchallenge.mappers.filter;

import com.egeozturk.bakdatacodingchallenge.dtos.filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;

public interface IFilterMapper {
    String getType();
    IFilter mapDtoToModel(BaseFilterDto dto);
}
