package com.algon.j2db.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BaseRequest<T> {

    private UUID requestId;

    T data;

}
