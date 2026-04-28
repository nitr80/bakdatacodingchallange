package com.egeozturk.bakdatacodingchallenge.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egeozturk.bakdatacodingchallenge.dtos.nodes.BaseNodeDto;
import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;
import com.egeozturk.bakdatacodingchallenge.registeries.NodeMapperRegistry;
import com.egeozturk.bakdatacodingchallenge.services.interfaces.ISqlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sql")
public class SqlController {
    
    private final ISqlService sqlService;

    public SqlController(ISqlService sqlService)
    {
        this.sqlService = sqlService;
    }

    @PostMapping("/query")
    public List<Map<String, Object>> query(@Valid @RequestBody BaseNodeDto baseNodeDto)
    {
        INode node = NodeMapperRegistry.mapDtoToModel(baseNodeDto);

        return sqlService.executeQueryFromNode(node);
    }

}
