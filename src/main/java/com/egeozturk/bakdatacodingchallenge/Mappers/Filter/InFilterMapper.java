package com.egeozturk.bakdatacodingchallenge.mappers.filter;

import com.egeozturk.bakdatacodingchallenge.dtos.filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.dtos.filters.InFilterDto;
import com.egeozturk.bakdatacodingchallenge.models.filters.InFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.types.FilterType;

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
