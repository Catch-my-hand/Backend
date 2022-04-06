package com.catch_my_hand.backend.Center_data;

import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CenterController {


    @GetMapping("/center")
    public List<Object> callApi() throws IOException {


        StringBuilder result = new StringBuilder();
        String jsonPrintString = null;
        List<Object> list = null;
        try {
            String ulrStr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic" +
                    "?" + "bgnde" + "=" + "20140301" + "&" + "endde" + "=" + "20211231" + "&" + "pageNo" + "=" + "1" + "&" + "numOfRows" + "=" + "100" +
                    "&" + "ServiceKey" + "=" + "%2BRTldbf2L3IqeIcRuy2XJ6N6j3fVTPIw5lakLVIhVFppiIZnC60PAY08Du5ERM5fFkYyN%2BHmMU7lUGKD5zOIQA%3D%3D";


            URL url = new URL(ulrStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }

            org.json.JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();


            org.json.simple.JSONObject jObj = null;

            JSONParser jsonParser = new JSONParser();

            org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) jsonParser.parse(jsonPrintString);

            org.json.simple.JSONObject parseResponse = (org.json.simple.JSONObject) jsonObj.get("response");

            org.json.simple.JSONObject parseBody = (org.json.simple.JSONObject) parseResponse.get("body");

            org.json.simple.JSONObject parseItems = (org.json.simple.JSONObject) parseBody.get("items");
            org.json.simple.JSONArray array = (org.json.simple.JSONArray) parseItems.get("item");

            list = new ArrayList<Object>();



            for (int i = 0; i < array.size(); i++) {
                jObj = (org.json.simple.JSONObject) array.get(i);

                String desertionNo = String.valueOf(jObj.get("desertionNo")); //유기번호
                String happenDt = String.valueOf(jObj.get("happenDt")); //접수일
                String processState = String.valueOf(jObj.get("processState")); //상태
                String noticeSdt = String.valueOf(jObj.get("noticeSdt")); //공고일
                String noticeEdt = String.valueOf(jObj.get("noticeEdt")); //공고종료일
                String kindCd = String.valueOf(jObj.get("kindCd")); //품종
                String colorCd = String.valueOf(jObj.get("colorCd")); //색깔
                String age = String.valueOf(jObj.get("age")); //나이
                String weight = String.valueOf(jObj.get("weight")); //무게
                String sexCd = String.valueOf(jObj.get("sexCd")); // 성별
                String neuterYn = String.valueOf(jObj.get("neuterYn")); //중성화 여부
                String specialMark = String.valueOf(jObj.get("specialMark")); //특징
                String careNm = String.valueOf(jObj.get("careNm")); //보호소 명
                String careAddr = String.valueOf(jObj.get("careAddr")); //보호 장소
                String orgNm = String.valueOf(jObj.get("orgNm")); //관할기관
                String popfile = String.valueOf(jObj.get("popfile")); //이미지 파일
                String filename = String.valueOf(jObj.get("filename")); //썸네일 이미지 파일

                list.add(center1(desertionNo, happenDt, processState, noticeSdt, noticeEdt, kindCd, colorCd, age, weight, sexCd, neuterYn,
                        specialMark, careNm,careAddr, orgNm, popfile, filename));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Object center1(@RequestParam String desertionNo, @RequestParam String happenDt, @RequestParam String processState, @RequestParam String noticeSdt, @RequestParam String noticeEdt, @RequestParam String kindCd, @RequestParam String colorCd, @RequestParam String age, @RequestParam String weight, @RequestParam String sexCd, @RequestParam String neuterYn, @RequestParam String specialMark, @RequestParam String careNm, @RequestParam String careAddr, @RequestParam String orgNm, @RequestParam String popfile, @RequestParam String filename) {
        Map<String, Object> rmap = new HashMap<>();
        rmap.put("desertionNo",desertionNo);
        rmap.put("happenDt", happenDt);
        rmap.put("processState", processState);
        rmap.put("noticeSdt", noticeSdt);
        rmap.put("noticeEdt", noticeEdt);
        rmap.put("kindCd", kindCd);
        rmap.put("colorCd", colorCd);
        rmap.put("age", age);
        rmap.put("weight", weight);
        rmap.put("sexCd", sexCd);
        rmap.put("neuterYn", neuterYn);
        rmap.put("specialMark", specialMark);
        rmap.put("careNm", careNm);
        rmap.put("careAddr", careAddr);
        rmap.put("orgNm", orgNm);
        rmap.put("popfile", popfile);
        rmap.put("filename", filename);

        return rmap;
    }
}