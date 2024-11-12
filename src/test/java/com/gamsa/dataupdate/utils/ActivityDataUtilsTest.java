package com.gamsa.dataupdate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.dto.InstituteApiResponse;
import com.gamsa.dataupdate.DataUpdateErrorCode;
import com.gamsa.dataupdate.DataUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ActivityDataUtilsTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ActivityDataUtils activityDataUtils;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 목록_조회_성공() throws Exception {
        String firstResponse = """
                    {
                      "response": {
                        "body": {
                          "items": {
                            "item": [
                              { "progrmRegistNo": "12345" },
                              { "progrmRegistNo": "67890" }
                            ]
                          }
                        }
                      }
                    }
                """;
        String secondResponse = """
                    {
                      "response": {
                        "body": {
                          "items": {
                            "item": [
                            ]
                          }
                        }
                      }
                    }
                """;

        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(firstResponse, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(secondResponse, HttpStatus.OK))
        ;

        List<String> result = activityDataUtils.getVolunteerParticipationList(
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        assertEquals(2, result.size());
        assertTrue(result.contains("12345"));
        assertTrue(result.contains("67890"));
    }

    @Test
    public void 목록_조회_실패() {
        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(DataUpdateException.class, () ->
                        activityDataUtils.getVolunteerParticipationList(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                DataUpdateErrorCode.OPENAPI_NOT_RESPOND.name()
        );
    }

    @Test
    public void 기관_조회_성공() throws Exception {
        //given
        String mockResponse = """
                    {"response": {
                    "body": {
                      "items": {
                        "item": {
                            "actBeginTm": 10,
                            "actEndTm": 11,
                            "actPlace": "음성효심주간보호 프로그램실",
                            "actWkdy": 1111111,
                            "adultPosblAt": "Y",
                            "appTotal": 0,
                            "areaAddress1": "충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터",
                            "areaAddress2": "",
                            "areaAddress3": "",
                            "areaLalo1": "36.9397922285334,127.686299185056",
                            "areaLalo2": "",
                            "areaLalo3": "",
                            "email": "shon1966@naver.com",
                            "familyPosblAt": "N",
                            "fxnum": "0438732338",
                            "grpPosblAt": "N",
                            "gugunCd": 4470000,
                            "mnnstNm": "음성효심주간보호센터",
                            "nanmmbyNm": "충청북도 음성군",
                            "nanmmbyNmAdmn": "손덕기",
                            "noticeBgnde": 20240803,
                            "noticeEndde": 20241102,
                            "pbsvntPosblAt": "N",
                            "postAdres": "충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터",
                            "progrmBgnde": 20240803,
                            "progrmCn": "-어르신 민요교실 준비\\n-어르신 민요교실 프로그램 진행\\n-어르신 민요교실 마무리",
                            "progrmEndde": 20241102,
                            "progrmRegistNo": 3168803,
                            "progrmSj": "어르신 민요 공연 봉사",
                            "progrmSttusSe": 2,
                            "rcritNmpr": 2,
                            "sidoCd": 6430000,
                            "srvcClCode": "문화행사 > 공연활동",
                            "telno": "043-873-2337",
                            "yngbgsPosblAt": "N"
                            }
                        }
                    }
                }
                }
                """;

        //when
        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        InstituteApiResponse result = activityDataUtils.getInstituteApiResponse("3168803");

        //then
        assertEquals("충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터", result.getLocation());
    }

    @Test
    public void 기관_조회_실패() {
        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(DataUpdateException.class, () ->
                        activityDataUtils.getInstituteApiResponse("12345"),
                DataUpdateErrorCode.OPENAPI_NOT_RESPOND.name()
        );
    }

    @Test
    public void 활동_조회_성공() throws Exception {
        String mockResponse = """
                    {
                    "response": {
                    "body": {
                      "items": {
                        "item": {
                            "actBeginTm": 10,
                            "actEndTm": 11,
                            "actPlace": "음성효심주간보호 프로그램실",
                            "actWkdy": 1111111,
                            "adultPosblAt": "Y",
                            "appTotal": 0,
                            "areaAddress1": "충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터",
                            "areaAddress2": "",
                            "areaAddress3": "",
                            "areaLalo1": "36.9397922285334,127.686299185056",
                            "areaLalo2": "",
                            "areaLalo3": "",
                            "email": "shon1966@naver.com",
                            "familyPosblAt": "N",
                            "fxnum": "0438732338",
                            "grpPosblAt": "N",
                            "gugunCd": 4470000,
                            "mnnstNm": "음성효심주간보호센터",
                            "nanmmbyNm": "충청북도 음성군",
                            "nanmmbyNmAdmn": "손덕기",
                            "noticeBgnde": 20240803,
                            "noticeEndde": 20241102,
                            "pbsvntPosblAt": "N",
                            "postAdres": "충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터",
                            "progrmBgnde": 20240803,
                            "progrmCn": "-어르신 민요교실 준비\\n-어르신 민요교실 프로그램 진행\\n-어르신 민요교실 마무리",
                            "progrmEndde": 20241102,
                            "progrmRegistNo": 3168803,
                            "progrmSj": "어르신 민요 공연 봉사",
                            "progrmSttusSe": 2,
                            "rcritNmpr": 2,
                            "sidoCd": 6430000,
                            "srvcClCode": "문화행사 > 공연활동",
                            "telno": "043-873-2337",
                            "yngbgsPosblAt": "N"
                            }
                        }
                    }
                }
                }
                """;

        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        ActivitySaveRequest result = activityDataUtils.getVolunteerDetail("12345");

        assertEquals("어르신 민요 공연 봉사", result.getActTitle());
        assertEquals("충청북도 음성군 음성읍 음성천서길 217-1 음성효심주간보호센터", result.getActLocation());
    }

    @Test
    public void 활동_조회_실패() {
        when(restTemplate.getForEntity(any(String.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(DataUpdateException.class, () ->
                        activityDataUtils.getVolunteerDetail("12345"),
                DataUpdateErrorCode.OPENAPI_NOT_RESPOND.name()
        );
    }
}
