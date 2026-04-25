package com.egeozturk.bakdatacodingchallenge.Mappers.Filter;

import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.BaseFilterDto;
import com.egeozturk.bakdatacodingchallenge.DTOs.Filters.EqualFilterDto;
import com.egeozturk.bakdatacodingchallenge.Models.Filters.EqualFilter;
import com.egeozturk.bakdatacodingchallenge.Models.Interfaces.IFilter;
import com.egeozturk.bakdatacodingchallenge.Types.FilterType;

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
