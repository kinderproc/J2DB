package com.algon.j2db.service;

import com.algon.j2db.contract.dto.CreateRequestDto;
import com.algon.j2db.dto.Column;
import com.algon.j2db.dto.ForeignKey;
import com.algon.j2db.dto.Table;
import com.algon.j2db.utils.DbProps;
import com.algon.j2db.utils.EnvProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaCreationService {

    public void createTables(CreateRequestDto createRequestDto) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(createDataSource(DbProps.userDbUrl(createRequestDto.getName()),
                EnvProps.metaUser(), EnvProps.metaPassword()));

        createRequestDto.getTables().stream()
            .map(Table::getSchema)
            .distinct()
            .forEach(schema -> createSchema(schema, jdbcTemplate));

        createRequestDto.getTables()
            .forEach(table -> createTable(table, jdbcTemplate));

        createRequestDto.getForeignKeys()
            .forEach(foreignKey -> createForeignKey(foreignKey, jdbcTemplate));
    }

    private DriverManagerDataSource createDataSource(String url, String userName, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    private void createSchema(String name, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("CREATE SCHEMA " + name + ";");
    }

    private void createForeignKey(ForeignKey foreignKey, JdbcTemplate jdbcTemplate) {

    }

    private void createTable(Table table, JdbcTemplate jdbcTemplate) {
        String tableStatement = table.getColumns().stream()
                .map(this::getColumnStatement)
                .collect(Collectors.joining(",\n\t", "CREATE TABLE " +
                        (table.getSchema() != null ? "\"" + table.getSchema() + "\"." : "") +
                        "\"" + table.getName() + "\" (\n\t", ");"));
        log.debug(tableStatement);
        jdbcTemplate.update(tableStatement);
    }

    private String getColumnStatement(Column column) {
        return column.getName() + " " + column.getType() + " " +
                (column.getPrimary() ? "PRIMARY KEY" : column.getRequired() ? " NOT NULL" : "");
    }

}
