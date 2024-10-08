package com.algon.j2db.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateResponseDto {

    private UUID id;

    public CreateResponseDto() {
    }
}
