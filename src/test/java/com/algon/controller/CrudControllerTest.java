package com.algon.controller;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.controller.CreateRequestDto;
import com.algon.j2db.controller.CrudController;
import com.algon.j2db.controller.DefaultControllerAdvice;
import com.algon.j2db.service.J2DBMetaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CrudControllerTest {

    MockMvc mvc;

    @Mock
    J2DBMetaService j2DBMetaService;

    @InjectMocks
    CrudController crudController;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(crudController)
                .setControllerAdvice(new DefaultControllerAdvice())
                .build();
    }

    @Test
    void when_CreateRequestSuccess_Then_NoError_MessagePresent_IdPresent () throws Exception {
        when(j2DBMetaService.create(any())).thenReturn(UUID.randomUUID());
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/database")
                .contentType(MediaType.APPLICATION_JSON)
                .content(resourceToStr("create-request.json"))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.error").doesNotExist())
            .andExpect(jsonPath("$.message").value("Database successfully created"))
            .andExpect(jsonPath("$.data.id").exists());
    }

    @Test
    void when_CreateRequestException_Then_ErrorPresent_MessagePresent () throws Exception {
        when(j2DBMetaService.create(any())).thenThrow(new RuntimeException("dummy"));
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/database")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceToStr("create-request.json"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        System.out.println(response);
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.error").exists())
//                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void ttt() {
        String source = "jdbc:postgresql://localhost:5432/j2dbc-meta";
//        String result = source.replaceAll("(/[a-z0-9\\-]+)(/[a-z0-9\\-]+)", "$1/" + "animals");
        String result = source.replaceAll("//(.+)/(.+)", "//$1/" + "animals");
        System.out.println(result);
    }

    @Test
    void delete() {
    }

    @Test
    void getList() {
    }

    @Test
    void generate() {
    }

    @Test
    void populate() {
    }

    private String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T resourceToObject(String name, Class clazz) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name);
            return (T) new ObjectMapper().readValue(inputStream, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String resourceToStr(String name) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name);
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}