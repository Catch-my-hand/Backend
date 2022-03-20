package com.catch_my_hand.backend.Center_data;

import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CenterController {

    @GetMapping("/center")
    public List<CenterController> callApi() throws IOException {
        StringBuilder result = new StringBuilder();
        String jsonPrintString = null;
        List<CenterController> list = null;
        try {
            String ulrStr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic" +
                    "?" + "bgnde" + "=" + "20140301" + "&" + "endde" + "=" + "20211231" + "&" + "upkind" + "417000" + "&" + "pageNo" + "=" + "10" + "&" + "numOfRows" + "=" + "100" +
                    "&" + "ServiceKey" + "=" + "%2BRTldbf2L3IqeIcRuy2XJ6N6j3fVTPIw5lakLVIhVFppiIZnC60PAY08Du5ERM5fFkYyN%2BHmMU7lUGKD5zOIQA%3D%3D";

            URL url = new URL(ulrStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }
            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();

            JSONObject jObj;

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonPrintString);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONObject parseItems = (JSONObject) parseBody.get("items");
            JSONArray array = (JSONArray) parseItems.get("item");
            list = null;
            list = new ArrayList<CenterController>();

            for (int i = 0; i < array.size(); i++) {
                CenterController c = new CenterController();
                jObj = (JSONObject) array.get(i);
                String desertionNo = (String) jObj.get("desertionNo"); //유기번호
                String happenDt = (String) jObj.get("happenDt"); //접수일
                String processState = (String) jObj.get("processState"); //상태
                String noticeSdt = (String) jObj.get("noticeSdt"); //공고일
                String noticeEdt = (String) jObj.get("noticeEdt"); //공고종료일
                String kindCd = (String) jObj.get("kindCd"); //품종
                String colorCd = (String) jObj.get("colorCd"); //색깔
                String age = (String) jObj.get("age"); //나이
                String weight = (String) jObj.get("weight"); //무게
                String sexCd = (String) jObj.get("sexCd"); // 성별
                String neuterYn = (String) jObj.get("neuterYn"); //중성화 여부
                String specialMark = (String) jObj.get("specialMark"); //특징
                String careNm = (String) jObj.get("careNm"); //보호소 명
                String careAddr = (String) jObj.get("careAddr"); //보호 장소
                String orgNm = (String) jObj.get("orgNm"); //관할기관
                String popfile = (String) jObj.get("popfile"); //이미지 파일
                String filename = (String) jObj.get("filename"); //썸네일 이미지 파일

                c.setDesertionNo(desertionNo);
                c.setHappenDt(happenDt);
                c.ProcessState(processState);
                c.NoticeSdt(noticeSdt);
                c.NoticeEdt(noticeEdt);
                c.KindCd(kindCd);
                c.ColorCd(colorCd);
                c.Age(age);
                c.Weight(weight);
                c.SexCd(sexCd);
                c.NeuterYn(neuterYn);
                c.SpecialMark(specialMark);
                c.CareNm(careNm);
                c.CareAddr(careAddr);
                c.OrgNm(orgNm);
                c.Popfile(popfile);
                c.Filename(filename);

                if (c != null) list.add(c);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }

    private void Filename(String filename) {
    }

    private void Popfile(String popfile) {
    }

    private void OrgNm(String orgNm) {
    }

    private void CareAddr(String careAddr) {
    }

    private void CareNm(String careNm) {
    }

    private void SpecialMark(String specialMark) {
    }

    private void NeuterYn(String neuterYn) {
    }

    private void SexCd(String sexCd) {
    }

    private void Weight(String weight) {
    }

    private void Age(String age) {
    }

    private void ColorCd(String colorCd) {
    }

    private void KindCd(String kindCd) {
    }

    private void NoticeEdt(String noticeEdt) {
    }

    private void NoticeSdt(String noticeSdt) {
    }

    private void ProcessState(String processState) {
    }

    private void setHappenDt(String happenDt) {
    }

    private void setDesertionNo(String desertionNo) {
    }
}
