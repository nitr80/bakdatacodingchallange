package com.egeozturk.bakdatacodingchallenge.dtos.nodes;


import com.egeozturk.bakdatacodingchallenge.types.NodeType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(
    use= JsonTypeInfo.Id.NAME,
    include= JsonTypeInfo.As.EXISTING_PROPERTY,
    property= "type",
    visible= true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value= QueryNodeDto.class, name= "QUERY"),
    @JsonSubTypes.Type(value= DateRestrictionNodeDto.class, name= "DATE_RESTRICTION")
})
public abstract class BaseNodeDto {
    @NotNull
    private NodeType type;

    public NodeType getType() {
        return type;
    }
}
