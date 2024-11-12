package com.gamsa.dataupdate.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.dto.InstituteApiResponse;
import com.gamsa.dataupdate.DataUpdateErrorCode;
import com.gamsa.dataupdate.DataUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ActivityDataUtils {
    @Value(value = "${openapi.key}")
    private String openapiKey;

    private final String openapiUrl = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/";

    @Value(value = "${openapi.volurl}")
    private String volUrl;

    private final RestTemplate restTemplate;

    public ActivityDataUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public List<String> getVolunteerParticipationList(LocalDate startDate, LocalDate endDate) {

        String url = openapiUrl + "/getVltrPeriodSrvcList";

        List<String> volunteerList = new ArrayList<>();

        int numOfItem = 20;
        int pageNo = 1;

        while (numOfItem != 0) {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("ServiceKey", openapiKey)
                    .queryParam("progrmBgnde", startDate.format(formatter))
                    .queryParam("progrmEndde", endDate.format(formatter))
                    .queryParam("numOfRows", 20)
                    .queryParam("pageNo", pageNo);

            ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                try {
                    String jsonContent = response.getBody();

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(jsonContent);

                    JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

                    numOfItem = itemsNode.size();


                    for (JsonNode item : itemsNode) {
                        String programNo = item.path("progrmRegistNo").asText();
                        volunteerList.add(programNo);
                    }

                    pageNo++;

                } catch (Exception e) {
                    log.error(e.getMessage());
                    throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_ERROR);
                }
            } else {
                throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_NOT_RESPOND);
            }
        }

        return volunteerList;
    }

    public InstituteApiResponse getInstituteApiResponse(String programNo) {
        String url = openapiUrl + "/getVltrPartcptnItem";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ServiceKey", openapiKey)
                .queryParam("progrmRegistNo", programNo);

        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        log.info("Request URI: " + uriBuilder.toUriString());
        log.info("Response URI: " + response.getBody());

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String jsonContent = response.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonContent);

                JsonNode item = rootNode.path("response").path("body").path("items").path("item");

                log.info(item.toString());

                return InstituteApiResponse.builder()
                        .name(item.path("mnnstNm").asText())
                        .location(item.path("postAdres").asText())
                        .sidoCode(item.path("sidoCd").asInt())
                        .sidoGunguCode(item.path("gugunCd").asInt())
                        .phone(item.path("telno").asText())
                        .build();

            } catch (Exception e) {
                log.error(e.getMessage());
                throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_ERROR);
            }
        } else {
            throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_NOT_RESPOND);
        }
    }

    public ActivitySaveRequest getVolunteerDetail(String programNo) {
        String url = openapiUrl + "/getVltrPartcptnItem";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ServiceKey", openapiKey)
                .queryParam("progrmRegistNo", programNo);

        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String jsonContent = response.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonContent);

                JsonNode item = rootNode.path("response").path("body").path("items").path("item");

                return ActivitySaveRequest.builder()
                        .actId(Long.parseLong(programNo))
                        .actTitle(item.path("progrmSj").asText())
                        .actLocation(item.path("actPlace").asText())
                        .description(item.path("progrmCn").asText())
                        .noticeStartDate(LocalDate.parse(item.path("noticeBgnde").asText(), formatter).atStartOfDay())
                        .noticeEndDate(LocalDate.parse(item.path("noticeEndde").asText(), formatter).atStartOfDay())
                        .actStartDate(LocalDate.parse(item.path("progrmBgnde").asText(), formatter).atStartOfDay())
                        .actEndDate(LocalDate.parse(item.path("progrmBgnde").asText(), formatter).atStartOfDay())
                        .actStartTime(item.path("actBeginTm").asInt())
                        .actEndTime(item.path("actEndTm").asInt())
                        .recruitTotalNum(item.path("rcritNmpr").asInt())
                        .actLocation(item.path("areaAddress1").asText())
                        .latitude(new BigDecimal(item.path("areaLalo1").asText().split(",")[0]))
                        .longitude(new BigDecimal(item.path("areaLalo1").asText().split(",")[1]))
                        .adultPossible(item.path("adultPosblAt").asText("").equals("Y"))
                        .teenPossible(item.path("yngbgsPosblAt").asText("").equals("Y"))
                        .groupPossible(item.path("grpPosblAt").asText("").equals("Y")).actWeek(item.path("actWkdy").asInt())
                        .actManager(item.path("nanmmbyNmAdmn").asText())
                        .actPhone(item.path("telno").asText())
                        .url(volUrl + item.path("progrmRegistNo").asText())
                        .category(getCategory(item.path("srvcClCode").asText()))
                        .instituteName(item.path("mnnstNm").asText())
                        .sidoGunguCode(item.path("gugunCd").asInt())
                        .build();

            } catch (Exception e) {
                log.error(e.getMessage());
                throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_ERROR);
            }
        } else {
            log.error("Error: " + response.getStatusCode());
            throw new DataUpdateException(DataUpdateErrorCode.OPENAPI_NOT_RESPOND);
        }
    }

    private Category getCategory(String text) {
        if ((text.contains("생활편의지원"))
                || text.contains("주거환경")
                || text.contains("농어촌 봉사")) {
            return Category.LIFE_SUPPORT_AND_HOUSING_IMPROVEMENT;
        } else if ((text.contains("교육"))
                || text.contains("멘토링")) {
            return Category.EDUCATION_AND_MENTORING;
        } else if (text.contains("행정보조")) {
            return Category.ADMINISTRATIVE_AND_OFFICE_SUPPORT;
        } else if ((text.contains("문화행사"))
                || text.contains("환경보호")
                || text.contains("국제협력")
                || text.contains("안전.예방")) {
            return Category.CULTURE_ENVIRONMENT_AND_INTERNATIONAL_COOPERATION;
        } else if ((text.contains("보건의료"))
                || text.contains("공익.인권")) {
            return Category.HEALTHCARE_AND_PUBLIC_WELFARE;
        } else if ((text.contains("상담"))
                || text.contains("자원봉사교육")) {
            return Category.COUNSELING_AND_VOLUNTEER_TRAINING;
        } else {
            return Category.OTHER_ACTIVITIES;
        }
    }
}