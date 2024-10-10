package com.algon.j2db.dto;

import lombok.Data;

@Data
public class Column {

    private String name;

    private String type;

    private Boolean required;

    private Boolean primary;

}
