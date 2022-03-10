package com.example.demo.batch;


import com.example.demo.domain.Kyonggi;
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
        List<Kyonggi> kyonggis = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        int count = 1;

        while(true) {

            String url = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key=3455e79d3c3d4fbd8c73a06f414703b9&pIndex="+count+"&Type=json&pSize=1000";

            String result = rt.getForObject(url, String.class);
            JSONObject jsonObject = new JSONObject(result);

            JSONArray jsonFamliy = (JSONArray) jsonObject.get("RegionMnyFacltStus");
            JSONObject jsonHead = (JSONObject) jsonFamliy.get(0);
            JSONObject jsonRow = (JSONObject) jsonFamliy.get(1);
            JSONArray row = (JSONArray) jsonRow.get("row");
            JSONArray head = (JSONArray) jsonHead.get("head");

            if(head == null) {
                break;
            }

            for (int i = 0; i < row.length(); i++) {
                JSONObject jsonObj = (JSONObject) row.get(i);

                Kyonggi kyonggi = Kyonggi.builder()
                        .cmpnmNm(jsonObj.get("CMPNM_NM").toString())
                        .sigunNm(jsonObj.get("SIGUN_NM").toString())
                        .dataStdEd(jsonObj.get("DATA_STD_DE").toString())
                        .refineLotnoAddr(jsonObj.get("REFINE_LOTNO_ADDR").toString())
                        .indutypeNm(jsonObj.get("INDUTYPE_NM").toString())
                        .refineRoadNmAddr(jsonObj.get("REFINE_ROADNM_ADDR").toString())
                        .liveYn(jsonObj.get("LIVE_YN").toString())
                        .build();

                kyonggis.add(kyonggi);

            }

        }
    }
}