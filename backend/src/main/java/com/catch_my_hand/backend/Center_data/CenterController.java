package com.catch_my_hand.backend.Center_data;

import java.io.IOException;

import com.catch_my_hand.backend.Center_data.CenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "기관분양", description = "기관분양 REST API")
public class CenterController {

    @ResponseBody
    @GetMapping("/center")
    public String callApi() throws IOException {

        //공공데이터 포털 API에서 유기동물 정보 가지고 옴.

        //공공데이터 포털 API에서 유기동물 정보 가지고 옴.
        StringBuilder urlStr = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic");
        urlStr.append("?bgnde=" + "20210101"); //bgnde : 유기날짜 (검색 시작일) (YYYYMMDD)
        urlStr.append("&endde=" + "20220414"); //endde : 유기날짜 (검색 종료일) (YYYYMMDD)
        urlStr.append("&upr_cd=" + "6410000"); //upr_cd : 시군코드 (경기도 : 6410000)
        urlStr.append("&upkind=" + "417000"); //upkind : 품종코드 (개:417000)
        urlStr.append("&state=" + "notice"); //state : 상태 (공고중 : notice)
        urlStr.append("&pageNo=" + "1");      //pageNo : 페이지 번호
        urlStr.append("&numOfRows=" + "100");  //numOfRows : 페이지당 보여줄 개수
        urlStr.append("&ServiceKey=" + "%2BRTldbf2L3IqeIcRuy2XJ6N6j3fVTPIw5lakLVIhVFppiIZnC60PAY08Du5ERM5fFkYyN%2BHmMU7lUGKD5zOIQA%3D%3D"); //서비스 키

        return CenterService.json_list(urlStr);
    }

}