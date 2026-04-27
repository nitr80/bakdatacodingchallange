package com.egeozturk.bakdatacodingchallenge.mappers.filter;

import com.egeozturk.bakdatacodingchallenge.dtos.filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.dtos.filters.EqualFilterDto;
import com.egeozturk.bakdatacodingchallenge.models.filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.types.FilterType;

public class EqualFilterMapper implements IFilterMapper {

    @Override
    public String getType() {
        return FilterType.EQUAL.toString();
    }

    @Override
    public IFilter mapDtoToModel(BaseFilterDto dto) {
        EqualFilterDto equalFilterDto = (EqualFilterDto) dto;

        return new EqualFilter(equalFilterDto.getColumn(), equalFilterDto.getValue());
    }

}
