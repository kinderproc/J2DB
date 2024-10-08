package com.algon.j2db.service;

import com.algon.j2db.controller.CreateRequestDto;
import com.algon.j2db.dto.Column;
import com.algon.j2db.dto.ForeignKey;
import com.algon.j2db.dto.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaCreationService {

    public void createTables(CreateRequestDto dto, String url, String userName, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Table> tables = dto.getTables();
        List<String> rootTables = dto.getForeignKeys().stream()
            .map(ForeignKey::getTable)
            .toList();
        tables.stream()
            .filter(table -> rootTables.contains(table.getName()))
            .forEach(t -> createTable(t, jdbcTemplate));
    }

    public void createTable(Table table, JdbcTemplate jdbcTemplate) {
        String tableStatement = table.getColumns().stream()
                .map(this::getColumnStatement)
                .collect(Collectors.joining(",\n\t", "CREATE TABLE \"" + table.getName() + "\" (\n\t", ");"));
        log.debug(tableStatement);
        jdbcTemplate.update(tableStatement);
    }

    private String getColumnStatement(Column column) {
        return column.getName() + " " + column.getType() + " " +
                (column.getPrimary() ? "PRIMARY KEY" : column.getRequired() ? " NOT NULL" : "");
    }

}
