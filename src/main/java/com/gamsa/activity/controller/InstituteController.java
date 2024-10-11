package com.gamsa.activity.controller;

import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/institutes")
public class InstituteController {

    private final InstituteService instituteService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody InstituteSaveRequest saveRequest) {
        instituteService.save(saveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
