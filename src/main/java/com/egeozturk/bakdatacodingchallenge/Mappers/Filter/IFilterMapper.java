package com.egeozturk.bakdatacodingchallenge.Mappers.Filter;

import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;

public interface IFilterMapper {
    String getType();
    IFilter mapDtoToModel(BaseFilterDto dto);
}
