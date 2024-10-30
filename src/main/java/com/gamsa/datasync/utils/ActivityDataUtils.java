package com.gamsa.datasync.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityApiResponse;
import com.gamsa.activity.dto.InstituteApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityDataUtils {
    @Value(value = "${gamsa.openapi.key}")
    private String openapiKey;

    private String openapiUrl = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/";

    private String volUrl = "https://www.1365.go.kr/vols/1572247904127/partcptn/timeCptn.do?type=show&progrmRegistNo=";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getVolunteerParticipationList(String startDate, String endDate) {
        String url = openapiUrl + "/getVltrPeriodSrvcList";

        List<String> volunteerList = new ArrayList<>();

        int numOfItem = 20;
        int pageNo = 1;

        while(numOfItem != 0) {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("ServiceKey", openapiKey)
                    .queryParam("progrmBgnde", startDate)
                    .queryParam("progrmEndde", endDate)
                    .queryParam("numOfRows", 20)
                    .queryParam("pageNo", pageNo);

            ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                try {
                    String jsonContent = response.getBody();

                    // Initialize Jackson ObjectMapper
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(jsonContent);

                    // Accessing the items in the JSON
                    JsonNode itemsNode = rootNode.path("response").path("body").path("items");

                    numOfItem = itemsNode.size();
                    for (JsonNode item : itemsNode) {
                        String programNo = item.path("progrmRegistNo").asText();
                        volunteerList.add(programNo);
                    }
                    pageNo++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String jsonContent = response.getBody();

                // Initialize Jackson ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonContent);

                // Accessing the items in the JSON
                JsonNode item = rootNode.path("response").path("body").path("items").path("item");

                InstituteApiResponse instituteApiResponse = InstituteApiResponse.builder()
                        .name(item.path("mnnstNm").asText())
                        .location(item.path("getTextContent").asText())
                        .sidoCode(Integer.getInteger(item.path("sidoCd").asText()))
                        .sidoGunguCode(Integer.getInteger((item.path("gugunCd").asText())))
                        .phone(item.path("telno").asText())
                        .build();

                return instituteApiResponse;

            } catch (Exception e) {
                e.printStackTrace();
                // 예외 처리
            }
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
        return null;
    }

    public ActivityApiResponse getVolunteerDetail(String programNo) {
        String url = openapiUrl + "/getVltrPartcptnItem";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ServiceKey", openapiKey)
                .queryParam("progrmRegistNo", programNo);

        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String jsonContent = response.getBody();

                // Initialize Jackson ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonContent);

                // Accessing the items in the JSON
                JsonNode item = rootNode.path("response").path("body").path("items").path("item");

                ActivityApiResponse activityApiResponse = ActivityApiResponse.builder()
                            .actId(Long.getLong(item.path("progrmRegistNo").asText()))
                            .actTitle(item.path("progrmSj").asText())
                            .actLocation(item.path("actPlace").asText())
                            .description(item.path("progrmCn").asText())
                            .noticeStartDate(LocalDateTime.parse(item.path("noticeBgnde").asText()))
                            .noticeEndDate(LocalDateTime.parse(item.path("noticeEndde").asText()))
                            .actStartDate(LocalDateTime.parse(item.path("progrmBgnde").asText()))
                            .actEndDate(LocalDateTime.parse(item.path("progrmBgnde").asText()))
                            .actStartTime(Integer.getInteger(item.path("actBeginTm").asText()))
                            .actEndTime(Integer.getInteger(item.path("actEndTm").asText()))
                            .recruitTotalNum(Integer.getInteger(item.path("rcritNmpr").asText()))
                            .adultPossible(item.path("adultPosblAt").asText() == "y" ? true : false)
                            .teenPossible(item.path("yngbgsPosblAt").asText() == "y" ? true : false)
                            .groupPossible(item.path("grpPosblAt").asText() == "y" ? true : false)
                            .actWeek(Integer.getInteger(item.path("actWkdy").asText()))
                            .actManager(item.path("nanmmbyNmAdmn").asText())
                            .actPhone(item.path("telno").asText())
                            .url(volUrl + item.path("progrmRegistNo").asText())
                            .instituteName(item.path("mnnstNm").asText())
                            .category(getCategory(item.path("srvcClCode").asText()))
                            .sidoGunguCode(Integer.getInteger(item.path("gugunCd").asText()))
                            .build();

                    return activityApiResponse;
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 처리
            }
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
        return null;
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