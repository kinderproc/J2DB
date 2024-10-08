package com.algon.j2db.dto;

import lombok.Data;

import java.util.List;

@Data
public class Table {

    private String schema;

    private String name;

    private List<Column> columns;

}
