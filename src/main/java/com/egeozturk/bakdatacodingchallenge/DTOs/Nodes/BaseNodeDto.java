package com.egeozturk.bakdatacodingchallenge.DTOs.Nodes;

import com.egeozturk.bakdatacodingchallenge.Models.Nodes.DateRestrictionNode;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use= JsonTypeInfo.Id.NAME,
    include= JsonTypeInfo.As.EXISTING_PROPERTY,
    property= "type",
    visible= true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value= QueryNodeDto.class, name= "QUERY"),
    @JsonSubTypes.Type(value= DateRestrictionNode.class, name= "DATE_RESTRICTION")
})
public abstract class BaseNodeDto {
    private NodeType type;

    public NodeType getType() {
        return type;
    }
}
