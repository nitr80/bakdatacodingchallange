package com.egeozturk.bakdatacodingchallenge.dtos.filters;

import com.egeozturk.bakdatacodingchallenge.types.FilterType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use= JsonTypeInfo.Id.NAME,
    include= JsonTypeInfo.As.EXISTING_PROPERTY,
    property= "type",
    visible= true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value= InFilterDto.class, name= "IN"),
    @JsonSubTypes.Type(value= EqualFilterDto.class, name= "EQUAL")
})
public abstract class BaseFilterDto {
    private FilterType type;

    public FilterType getType() {
        return type;
    }
}
