package com.egeozturk.bakdatacodingchallenge.Mappers.Filter;

import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.InFilterDto;
import com.egeozturk.bakdatacodingchallenge.Models.Filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Types.FilterType;

public class InFilterMapper implements IFilterMapper {

    @Override
    public String getType() {
        return FilterType.IN.toString();
    }

    @Override
    public IFilter mapDtoToModel(BaseFilterDto dto) {
        InFilterDto inFilterDto = (InFilterDto) dto;

        return new InFilter(inFilterDto.getColumn(), inFilterDto.getValues());
    }

}
