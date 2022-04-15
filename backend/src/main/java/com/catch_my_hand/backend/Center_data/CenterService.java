package com.catch_my_hand.backend.Center_data;

import com.catch_my_hand.backend.Center_data.Entity.Center;
import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CenterService {

    //open api
    public static String json_list(StringBuilder urlStr) {
        List<Object> list = null;
        list = new ArrayList<>();

        //* XML 을 DATA로 옴기기 위한 코드
        //페이지에 접근해줄 Document객체 생성
        //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(String.valueOf(urlStr));
            NodeList nodelist = doc.getElementsByTagName("item");
            int sequence = 0;
            int page = 0;

            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;

                    Center center = new Center();

                    center.setId(++sequence);
                    center.setHappenPlace(getTagValue("happenPlace", element));
                    center.setProcessState(getTagValue("processState", element));
                    center.setNoticeSdt(getTagValue("noticeSdt",element));
                    center.setNoticeEdt(getTagValue("noticeEdt", element));
                    center.setKindCd(getTagValue("kindCd", element));
                    center.setColorCd(getTagValue("colorCd", element));
                    center.setAge(getTagValue("age", element));
                    center.setWeight(getTagValue("weight", element));
                    center.setSexCd(getTagValue("sexCd", element));
                    center.setNeuterYn(getTagValue("neuterYn", element));
                    center.setSpecialMark(getTagValue("specialMark", element));
                    center.setCareNm(getTagValue("careNm", element));
                    center.setCareTel(getTagValue("careTel", element));
                    center.setCareAddr(getTagValue("careAddr", element));
                    center.setPopfile(getTagValue("popfile", element));
                    center.setFilename(getTagValue("filename", element));

                    list.add(center);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        String listjson = new Gson().toJson(list);
        return listjson;
    }

    //tag 값의 정보를 가져오는 메소드
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