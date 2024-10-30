package com.gamsa.datasync.utils;

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

@Component
public class KakaoLocalUtils {

    @Value("${gamsa.kakao.apikey}")
    private String kakaoApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<Map<String, BigDecimal>> getCoordinateByKeyword(String address) {
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json";
        Optional<Map<String, BigDecimal>> coordinates = fetchCoordinates(url, address);

        if (coordinates.isEmpty()) {
            // 키워드 검색 결과가 없을 때 주소 검색으로 대체
            coordinates = getCoordinateByAddress(address);
        }

        return coordinates;
    }

    public Optional<Map<String, BigDecimal>> getCoordinateByAddress(String address) {
        String url = "https://dapi.kakao.com/v2/local/search/address.json";
        return fetchCoordinates(url, address);
    }

    private Optional<Map<String, BigDecimal>> fetchCoordinates(String url, String address) {
        // 요청 URL 생성
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", address);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        // HTTP 요청
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                uriBuilder.toUriString(), HttpMethod.GET, entity, Map.class);

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

        System.out.println("API 요청 실패: " + response.getStatusCode());
        return Optional.empty();
    }
}
