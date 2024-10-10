package com.algon.j2db.repository;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.contract.dto.CreateRequestDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface J2DBMetaRepo {

    UUID createDb(BaseRequest<CreateRequestDto> request);

}
