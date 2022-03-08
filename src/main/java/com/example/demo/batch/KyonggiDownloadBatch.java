package com.example.demo.batch;

import com.example.demo.domain.Kyonggi;
import com.example.demo.domain.KyonggiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KyonggiDownloadBatch {

    private final KyonggiRepository kyonggiRepository;

    @Scheduled(cron = "0 22 * * * *", zone="Asia/Seoul")
    public void kyonggiBatch() throws IOException, ParseException {
        log.info("<<TEST>>");

        // 11. 전달받은 데이터 확인.


        int count = 1;
        List<Kyonggi> kyonggis = new ArrayList<>();
        while(true) {
            URL url = new URL("https://openapi.gg.go.kr/RegionMnyFacltStus?Key=3455e79d3c3d4fbd8c73a06f414703b9&pIndex="+count+"&Type=json&pSize=1000");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            // 10. 객체 해제.
            rd.close();
            conn.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(sb.toString());
            JSONArray parseResponse = (JSONArray) jsonObj.get("RegionMnyFacltStus");
            JSONObject jsonHead = (JSONObject) parseResponse.get(0);
            JSONObject jsonBody = (JSONObject) parseResponse.get(1);
            JSONArray rawData= (JSONArray) jsonBody.get("row");
            if(jsonHead.isEmpty() || jsonHead == null) {
                break;
            }
            for (Object jsonRow : rawData) {
                JSONObject obj = (JSONObject) jsonRow;

                Kyonggi kyonggi = Kyonggi.builder()
                        .sigunNm(obj.get("SIGUN_NM").toString())
                        .cmpnmNm(obj.get("CMPNM_NM").toString())
                        .indutypeNm(obj.get("INDUTYPE_NM").toString())
                        .dateStdEd(obj.get("DATA_STD_DE").toString())
                        .refineLotnoAddr(obj.get("REFINE_LOTNO_ADDR").toString())
                        .refineRoadnmAddr(obj.get("REFINE_ROADNM_ADDR").toString())
                        .build();
                kyonggis.add(kyonggi);
            }
            kyonggiRepository.saveAll(kyonggis);
            count++;
            kyonggis.clear();
            System.out.println("count: " + count);
        }
    }

}
