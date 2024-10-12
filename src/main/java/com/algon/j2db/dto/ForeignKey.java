package com.algon.j2db.dto;

import lombok.Data;

@Data
public class ForeignKey {

    private String table;

    private String name;

    private String column;

    private String reference;

}
