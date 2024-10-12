package com.algon.j2db.contract.dto;

import com.algon.j2db.dto.ForeignKey;
import com.algon.j2db.dto.Index;
import com.algon.j2db.dto.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRequestDto {

    private String type;

    private String name;

    private List<Table> tables;

    private List<ForeignKey> foreignKeys;

    private List<Index> indexes;
}
