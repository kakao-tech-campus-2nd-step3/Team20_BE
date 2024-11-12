package com.gamsa.dataupdate.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class KakaoLocalUtils {

    @Value("${kakao.localkey}")
    private String kakaoKey;

    private final RestTemplate restTemplate;

    public KakaoLocalUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Map<String, BigDecimal>> getCoordinateByAddress(String address) {
        // 요청 URL 생성
        String url = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/address.json")
                .queryParam("query", address)
                .build()
                .toUriString();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoKey);

        // HTTP 요청
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);

        try {
            // 응답 처리
            if (response.getStatusCode().is2xxSuccessful()) {

                Map<String, Object> result = response.getBody();
                if (result != null && result.containsKey("documents")) {
                    // 첫 번째 결과의 x, y 좌표 반환
                    var documents = (List<Map<String, Object>>) result.get("documents");
                    if (!documents.isEmpty()) {
                        Map<String, Object> firstDoc = documents.getFirst();
                        BigDecimal x = new BigDecimal(firstDoc.get("x").toString());
                        BigDecimal y = new BigDecimal(firstDoc.get("y").toString());

                        Map<String, BigDecimal> coordinates = new HashMap<>();
                        coordinates.put("longitude", x);
                        coordinates.put("latitude", y);
                        return Optional.of(coordinates);
                    }
                }
            }
        } catch (Exception e) {
            log.error("API 요청 실패: " + response.getStatusCode() + e.getMessage());
        }
        return Optional.empty();
    }
}
