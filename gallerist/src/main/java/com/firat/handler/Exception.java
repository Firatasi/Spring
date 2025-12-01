package com.firat.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception<E> {

    private String path;

    private Time createTime;

    private String hostName;

    private E message;
}
