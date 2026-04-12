package com.firat.model;
//classlar model veya entitiy adı altındaki paketin içinde tutulur bu paketi bu yüzden oluşturduk.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String id;
    private String firstName;
    private String lastName;

}

