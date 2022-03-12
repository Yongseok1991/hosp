package com.example.demo.batch;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class KyonggiDownloadBatchTest {
    @Test
    public void test() throws IOException, JSONException {
        log.info("<<TEST>>");
//        List<Kyonggi> kyonggis = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        int count = 1;
        String url = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key=3455e79d3c3d4fbd8c73a06f414703b9&pIndex="+count+"&Type=json&pSize=1000";

        String result = rt.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(result);

        JSONArray jsonFamliy = (JSONArray) jsonObject.get("RegionMnyFacltStus");;
        JSONObject jsonHead = (JSONObject) jsonFamliy.get(0);
        JSONObject jsonRow = (JSONObject) jsonFamliy.get(1);
        JSONArray row = (JSONArray) jsonRow.get("row");
        JSONArray head = (JSONArray) jsonHead.get("head");

        for(int i=0; i <= row.length(); i++) {

        }



//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        // 10. 객체 해제.
//        rd.close();
//        conn.disconnect();
//        // 11. 전달받은 데이터 확인.
//
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObj = (JSONObject) jsonParser.parse(sb.toString());
//        JSONArray parseResponse = (JSONArray) jsonObj.get("RegionMnyFacltStus");
//        JSONObject jsonHead = (JSONObject) parseResponse.get(0);
//        JSONObject jsonBody = (JSONObject) parseResponse.get(1);
//        JSONArray rawData= (JSONArray) jsonBody.get("row");
//
//        while(true) {
//            for (Object jsonRow : rawData) {
//                JSONObject obj = (JSONObject) jsonRow;
//
//                Kyonggi kyonggi = Kyonggi.builder()
//                        .sigunNm(obj.get("SIGUN_NM").toString())
//                        .cmpnmNm(obj.get("CMPNM_NM").toString())
//                        .indutypeNm(obj.get("INDUTYPE_NM").toString())
//                        .dateStdEd(obj.get("DATA_STD_ED").toString())
//                        .refineLotnoAddr(obj.get("REFINE_LOTNO_ADDR").toString())
//                        .refineRoadnmAddr(obj.get("REFINE_ROADNM_ADDR").toString())
//                        .build();
//                kyonggis.add(kyonggi);
//            }
//
//            count++;
//        }





//        JSONObject jsonObject = new JSONObject(result);
//      String data = a.get("row").toString();
//        System.out.println("data: " + data);
//      System.out.println("json: " + jsonObject);
//      System.out.println("\t+ result: " + result);

    }
}