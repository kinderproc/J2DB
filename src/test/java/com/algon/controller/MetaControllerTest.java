package com.algon.controller;

import com.algon.j2db.controller.DefaultControllerAdvice;
import com.algon.j2db.controller.MetaController;
import com.algon.j2db.exception.RequestException;
import com.algon.j2db.service.J2DBMetaService;
import com.algon.j2db.utils.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MetaControllerTest {

    MockMvc mvc;

    @Mock
    J2DBMetaService j2DBMetaService;

    @InjectMocks
    MetaController metaController;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(metaController)
                .setControllerAdvice(new DefaultControllerAdvice())
                .build();
    }

    @Test
    void when_CreateRequestSuccess_Then_SuccessMessagePresent_IdPresent () throws Exception {
        when(j2DBMetaService.create(any())).thenReturn(UUID.randomUUID());
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/database")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Resource.asStr("data/create-request.json"))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Database successfully created"))
            .andExpect(jsonPath("$.data.id").exists());
    }

    @Test
    void when_CreateRequestException_Then_ErrorMessagePresent () throws Exception {
        String errorMessage =  "J2DBMetaRepoPostgres.getUrl exception: test exception";
        when(j2DBMetaService.create(any())).thenThrow(new RequestException(UUID.randomUUID(), errorMessage));
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/database")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Resource.asStr("data/create-request.json"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value(errorMessage));
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

}
