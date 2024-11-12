package com.gamsa.dataupdate.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class KakaoLocalUtilsTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private KakaoLocalUtils kakaoLocalUtils;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 좌표_반환_성공() {
        //given
        String address = "Seoul";
        Map<String, Object> document = new HashMap<>();
        document.put("x", "126.9784");
        document.put("y", "37.5665");

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("documents", List.of(document));

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        //when
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Map.class)
        )).thenReturn(responseEntity);

        Optional<Map<String, BigDecimal>> result = kakaoLocalUtils.getCoordinateByAddress(address);

        //then
        assertTrue(result.isPresent());

        Map<String, BigDecimal> coordinates = result.get();
        assertEquals(new BigDecimal("126.9784"), coordinates.get("longitude"));
        assertEquals(new BigDecimal("37.5665"), coordinates.get("latitude"));
    }

    @Test
    public void 좌표_반환_실패() {
        //given
        String address = "Seoul";
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        //when
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Map.class)
        )).thenReturn(responseEntity);

        Optional<Map<String, BigDecimal>> result = kakaoLocalUtils.getCoordinateByAddress(address);

        //then
        assertEquals(Optional.empty(), result);
    }

}
