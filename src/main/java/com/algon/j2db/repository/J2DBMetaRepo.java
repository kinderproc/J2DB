package com.algon.j2db.repository;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.controller.CreateRequestDto;
import com.algon.j2db.dto.Table;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.UUID;

@Component
public interface J2DBMetaRepo {

    UUID createDb(BaseRequest<CreateRequestDto> request);

}
