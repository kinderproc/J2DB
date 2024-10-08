package com.algon.j2db.repository;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.contract.dto.CreateRequestDto;
import com.algon.j2db.utils.DbProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class J2DBMetaRepoPostgres implements J2DBMetaRepo {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UUID createDb(BaseRequest<CreateRequestDto> request) {
        String dbName = request.getData().getName();
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("CREATE DATABASE " + dbName);
        jdbcTemplate.update("INSERT INTO application.db_instance VALUES(?, ?, ?, ?)", id, dbName,
                DbProps.userDbUrl(dbName), new Date());
        return id;
    }

}
