package com.algon.service;

import com.algon.j2db.service.J2DBMetaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class J2DBMetaServiceTest {

    @InjectMocks
    J2DBMetaService j2DBMetaService;

    @Test
    void when_CreateRequest_Correct_Then_SaveCalled_IdReturned() {

    }
}
