package com.gamsa.activtydata;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityApiResponse;
import com.gamsa.activity.dto.InstituteApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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
    @Value("${gamsa.openapi.key}")
    private String openapiKey;

    @Value("${gamsa.openapi.url}")
    private String openapiUrl;

    @Value("${gamsa.vol.url}")
    private String volUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getVolunteerParticipationList(String startDate, String endDate) {
        String url = openapiUrl + "/getVltrPeriodSrvcList";

        List<String> volunteerList = new ArrayList<>();

        int numOfItem = 20;
        int pageNo = 0;

        while(numOfItem == 20) {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("ServiceKey", openapiKey)
                    .queryParam("progrmBgnde", startDate)
                    .queryParam("progrmEndde", endDate)
                    .queryParam("numOfRows", 20)
                    .queryParam("pageNo", pageNo);

            ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                try {
                    // XML 파싱
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    var document = builder.parse(new InputSource(new StringReader(response.getBody())));
                    var items = document.getElementsByTagName("item");
                    numOfItem = items.getLength();

                    for (int i = 0; i < items.getLength(); i++) {
                        Node item = items.item(i);
                        volunteerList.add(getTextContent(item, "progrmRegistNo").toString());
                    }
                    pageNo++;
                } catch (Exception e) {
                    e.printStackTrace();
                    // 예외 처리
                }
            } else {
                System.out.println("Error: " + response.getStatusCode());
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
                // XML 파싱
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                var document = builder.parse(new InputSource(new StringReader(response.getBody())));
                var item = document.getElementsByTagName("item").item(0);

                if (item != null) {
                    InstituteApiResponse instituteApiReponse = InstituteApiResponse.builder()
                            .name(getTextContent(item, "mnnstNm"))
                            .location(getTextContent(item, "getTextContent"))
                            .sidoCode(Integer.getInteger(getTextContent(item, "sidoCd")))
                            .sidoGunguCode(Integer.getInteger(getTextContent(item, "gugunCd")))
                            .phone(getTextContent(item, "telno"))
                            .build();
                }
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
                // XML 파싱
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                var document = builder.parse(new InputSource(new StringReader(response.getBody())));
                var item = document.getElementsByTagName("item").item(0);

                if (item != null) {
                    ActivityApiResponse activityApiResponse = ActivityApiResponse.builder()
                            .actId(Long.getLong(getTextContent(item, "progrmRegistNo")))
                            .actTitle(getTextContent(item, "progrmSj"))
                            .actLocation(getTextContent(item, "actPlace"))
                            .description(getTextContent(item, "progrmCn"))
                            .noticeStartDate(LocalDateTime.parse(getTextContent(item, "noticeBgnde")))
                            .noticeEndDate(LocalDateTime.parse(getTextContent(item, "noticeEndde")))
                            .actStartDate(LocalDateTime.parse(getTextContent(item, "progrmBgnde")))
                            .actEndDate(LocalDateTime.parse(getTextContent(item, "progrmBgnde")))
                            .actStartTime(Integer.getInteger(getTextContent(item, "actBeginTm")))
                            .actEndTime(Integer.getInteger(getTextContent(item, "actEndTm")))
                            .recruitTotalNum(Integer.getInteger(getTextContent(item, "rcritNmpr")))
                            .adultPossible(getTextContent(item, "adultPosblAt") == "y" ? true : false)
                            .teenPossible(getTextContent(item, "yngbgsPosblAt") == "y" ? true : false)
                            .groupPossible(getTextContent(item, "grpPosblAt") == "y" ? true : false)
                            .actWeek(Integer.getInteger(getTextContent(item, "actWkdy")))
                            .actManager(getTextContent(item, "nanmmbyNmAdmn"))
                            .actPhone(getTextContent(item, "telno"))
                            .url(volUrl + getTextContent(item, "progrmRegistNo"))
                            .instituteName(getTextContent(item, "mnnstNm"))
                            .category(getCategory(getTextContent(item, "srvcClCode")))
                            .sidoGunguCode(Integer.getInteger(getTextContent(item, "gugunCd")))
                            .build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 처리
            }
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
        return null;
    }

    private String getTextContent(Node node, String tagName) {
        var element = (Element) node;
        var target = element.getElementsByTagName(tagName).item(0);
        return (target != null) ? target.getTextContent() : null;
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