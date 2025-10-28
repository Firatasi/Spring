package com.firat.handler;

import lombok.Data;

import java.util.Date;

@Data
public class Exception <E> {

    private String hostName;

    private Integer path;

    private Date createTime;

    private E message;
}
