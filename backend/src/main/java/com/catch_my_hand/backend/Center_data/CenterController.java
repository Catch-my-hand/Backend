package com.catch_my_hand.backend.Center_data;

import com.catch_my_hand.backend.Center_data.Entity.Center;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CenterController {


    @GetMapping("/center")
    public String callApi() throws IOException {


        List<Object> list = null;
        list = new ArrayList<>();
        Center center = null;

        //공공데이터 포털 API에서 유기동물 정보 가지고 옴.
        try {
            StringBuilder urlStr = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic");
            urlStr.append("?" + URLEncoder.encode("bgnde", "UTF-8") + "=20211201"); //bgnde : 유기날짜 (검색 시작일) (YYYYMMDD)
            urlStr.append("&" + URLEncoder.encode("endde", "UTF-8") + "=20220401"); //endde : 유기날짜 (검색 종료일) (YYYYMMDD)
            urlStr.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=417000"); //upkind : 품종코드 (개:417000)
            urlStr.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");      //pageNo : 페이지 번호
            urlStr.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=20");  //numOfRows : 페이지당 보여줄 개수
            urlStr.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=%2BRTldbf2L3IqeIcRuy2XJ6N6j3fVTPIw5lakLVIhVFppiIZnC60PAY08Du5ERM5fFkYyN%2BHmMU7lUGKD5zOIQA%3D%3D"); //서비스 키

            String url = urlStr.toString();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(url);
            NodeList nodelist = doc.getElementsByTagName("item");

            int sequence = 0;

            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;

                    center = new Center();
                    center.setId(++sequence);
                    center.setHappenDt(getTagValue("happenDt", element));
                    center.setProcessState(getTagValue("processState", element));
                    center.setNoticeEdt(getTagValue("noticeEdt", element));
                    center.setKindCd(getTagValue("kindCd", element));
                    center.setColorCd(getTagValue("colorCd", element));
                    center.setAge(getTagValue("age", element));
                    center.setWeight(getTagValue("weight", element));
                    center.setSexCd(getTagValue("sexCd", element));
                    center.setNeuterYn(getTagValue("neuterYn", element));
                    center.setSpecialMark(getTagValue("specialMark", element));
                    center.setCareNm(getTagValue("careNm", element));
                    center.setCareAddr(getTagValue("CareAddr", element));
                    center.setOrgNm(getTagValue("orgNm", element));
                    center.setPopfile(getTagValue("popfile", element));
                    center.setFilename(getTagValue("filename", element));

                    list.add(center);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String listjson = new Gson().toJson(list);
        return listjson;
    }
    
    private static String getTagValue(String tag, Element ele) {
        try {
            NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
            Node nValue = (Node) nodeList.item(0);
            if(nValue == null) {
                return null;
            }
            return nValue.getNodeValue();
        } catch (Exception e) {return null;}
    }

}