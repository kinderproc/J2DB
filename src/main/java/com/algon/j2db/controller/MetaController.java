package com.algon.j2db.controller;

import com.algon.j2db.contract.BaseRequest;
import com.algon.j2db.contract.BaseResponse;
import com.algon.j2db.contract.dto.DeleteRequestDto;
import com.algon.j2db.contract.dto.DeleteResponseDto;
import com.algon.j2db.contract.dto.GenerateRequestDto;
import com.algon.j2db.contract.dto.GenerateResponseDto;
import com.algon.j2db.contract.dto.GetListRequestDto;
import com.algon.j2db.contract.dto.GetListResponseDto;
import com.algon.j2db.contract.dto.UploadRequestDto;
import com.algon.j2db.contract.dto.UploadResponseDto;
import com.algon.j2db.contract.dto.CreateRequestDto;
import com.algon.j2db.contract.dto.CreateResponseDto;
import com.algon.j2db.service.J2DBMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class MetaController {

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
    public ResponseEntity<DeleteResponseDto> delete(@RequestBody BaseRequest<DeleteRequestDto> request) {
        DeleteResponseDto response = new DeleteResponseDto();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/database")
    public ResponseEntity<GetListResponseDto> getList(@RequestBody BaseRequest<GetListRequestDto> request) {
        GetListResponseDto response = new GetListResponseDto();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/generation/database/{id}")
    public ResponseEntity<GenerateResponseDto> generate(@RequestParam("id") UUID id,
            @RequestBody BaseRequest<GenerateRequestDto> request) {
        GenerateResponseDto response = new GenerateResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/upload/database/{id}")
    public ResponseEntity<UploadResponseDto> populate(@RequestParam("id") UUID id,
            @RequestBody BaseRequest<UploadRequestDto> request) {
        UploadResponseDto response = new UploadResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
