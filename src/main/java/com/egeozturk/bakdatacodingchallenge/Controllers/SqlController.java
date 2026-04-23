package com.egeozturk.bakdatacodingchallenge.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egeozturk.bakdatacodingchallenge.Services.Interfaces.ISqlService;

@RestController
@RequestMapping("/sql")
public class SqlController {
    
    private final ISqlService sqlService;

    public SqlController(ISqlService sqlService)
    {
        this.sqlService = sqlService;
    }

}
