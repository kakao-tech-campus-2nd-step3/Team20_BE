package com.gamsa.activity.controller;

import com.gamsa.activity.dto.DistrictFindAllResponse;
import com.gamsa.activity.dto.DistrictSaveRequest;
import com.gamsa.activity.service.DistrictService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody DistrictSaveRequest saveRequest) {
        districtService.save(saveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/sido")
    public List<DistrictFindAllResponse> findAllSido() {
        return districtService.findAllSido();
    }

    @GetMapping("/gungu")
    public List<DistrictFindAllResponse> findAllGungu() {
        return districtService.findAllGungu();
    }
}
