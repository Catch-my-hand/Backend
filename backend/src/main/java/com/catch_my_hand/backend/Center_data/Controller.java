package com.catch_my_hand.backend.Center_data;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Controller {

    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();
        String jsonPrintString = null;
        try {
            String ulrStr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic" +
                    "?" + "bgnde" + "=" + "20140301" + "&" + "endde" + "=" + "20211231" + "&" + "pageNo" + "=" + "10" + "&" + "numOfRows" + "=" + "100" +
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;

    }
}
