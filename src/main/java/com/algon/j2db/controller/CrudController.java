package com.algon.j2db.controller;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.contract.BaseResponse;
import com.algon.j2db.contract.DeleteRequest;
import com.algon.j2db.contract.DeleteResponse;
import com.algon.j2db.contract.GenerateRequest;
import com.algon.j2db.contract.GenerateResponse;
import com.algon.j2db.contract.GetListRequest;
import com.algon.j2db.contract.GetListResponse;
import com.algon.j2db.contract.UploadRequest;
import com.algon.j2db.contract.UploadResponse;
import com.algon.j2db.service.J2DBMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CrudController {

    private final J2DBMetaService j2DBMetaService;

    @PostMapping("database")
    public ResponseEntity<BaseResponse<CreateResponseDto>> create(@RequestBody BaseRequest<CreateRequestDto> request) {
        UUID databaseId = j2DBMetaService.create(request);
        CreateResponseDto data = new CreateResponseDto();
        data.setId(databaseId);
        BaseResponse<CreateResponseDto> response = new BaseResponse<>();
        response.setRequestId(request.getRequestId());
        response.setMessage("Database successfully created");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/database/{id}")
    public ResponseEntity<DeleteResponse> delete(@RequestBody DeleteRequest request) {
        DeleteResponse response = new DeleteResponse();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/database")
    public ResponseEntity<GetListResponse> getList(@RequestBody GetListRequest request) {
        GetListResponse response = new GetListResponse();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/generation/database/{id}")
    public ResponseEntity<GenerateResponse> generate(
            @RequestParam("id") UUID id,
            @RequestBody GenerateRequest request) {
        GenerateResponse response = new GenerateResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/upload/database/{id}")
    public ResponseEntity<UploadResponse> populate(
            @RequestParam("id") UUID id,
            @RequestBody UploadRequest request) {
        UploadResponse response = new UploadResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("ping")
    public String ping(@RequestBody CreateRequestDto request) {
        System.out.println(request);
        return "pong";
    }
}
