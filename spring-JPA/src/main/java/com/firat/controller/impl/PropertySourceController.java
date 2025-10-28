package com.firat.controller.impl;

import com.firat.configuration.DataSource;
import com.firat.configuration.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/property")
public class PropertySourceController {

    @Autowired
    private GlobalProperties globalProperties;



    @GetMapping("/datasource")
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(this.globalProperties.getUrl());
        dataSource.setUsername(this.globalProperties.getUsername());
        dataSource.setPassword(this.globalProperties.getPassword());
        return dataSource;
    }

}
