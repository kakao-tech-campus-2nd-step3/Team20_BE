package com.gamsa.dataupdate;

import com.gamsa.dataupdate.utils.KakaoLocalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KakaoLocalUtilsTest {

    @Autowired
    private KakaoLocalUtils kakaoLocalUtils;

    @Test
    void 주소_검색_테스트() {
        //given
        String address = "경기 광주시 중앙로175번길 14 송정문화센터 2층";

        //when
        var result = kakaoLocalUtils.getCoordinateByAddress(address).orElseThrow();

        //then
        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
