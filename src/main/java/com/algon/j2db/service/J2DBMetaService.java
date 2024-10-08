package com.algon.j2db.service;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.contract.dto.CreateRequestDto;
import com.algon.j2db.exception.RequestException;
import com.algon.j2db.repository.J2DBMetaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class J2DBMetaService {

    private final J2DBMetaRepo metaRepo;

    private final SchemaCreationService schemaCreationService;

    public UUID create(BaseRequest<CreateRequestDto> request) {
        try {
            UUID dbUuid = metaRepo.createDb(request);
            schemaCreationService.createTables(request.getData());
            return dbUuid;
        } catch (Exception e) {
            log.error("J2DBMetaRepoPostgres.getUrl exception: {}", e.getMessage(), e);
            throw new RequestException(request.getRequestId(), "J2DBMetaRepoPostgres.getUrl exception: " + e.getMessage());
        }
    }

}
