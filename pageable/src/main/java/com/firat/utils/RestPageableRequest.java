package com.firat.utils;

import lombok.Data;

@Data
public class RestPageableRequest {
    private int pageNumber;
    private int pageSize;
    private String columnName;
    private String asc;
}
