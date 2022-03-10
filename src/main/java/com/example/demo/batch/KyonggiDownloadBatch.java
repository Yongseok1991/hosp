package com.example.demo.batch;

import com.example.demo.domain.Kyonggi;
import com.example.demo.domain.KyonggiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Component
@Slf4j
public class KyonggiDownloadBatch {

    private final KyonggiRepository kyonggiRepository;
    @Scheduled(cron = "0 11 * * * *", zone = "Asia/Seoul")
    public void downloadBatch() throws JSONException {
        log.info("<<TEST>>");
        List<Kyonggi> kyonggis = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        int count = 1;

        while (true) {

            String url = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key=3455e79d3c3d4fbd8c73a06f414703b9&pIndex=" + count + "&Type=json&pSize=1000";

            String result = rt.getForObject(url, String.class);
            JSONObject jsonObject = new JSONObject(result);

            JSONArray jsonFamliy = (JSONArray) jsonObject.get("RegionMnyFacltStus");
            JSONObject jsonHead = (JSONObject) jsonFamliy.get(0);
            JSONObject jsonRow = (JSONObject) jsonFamliy.get(1);
            JSONArray row = (JSONArray) jsonRow.get("row");
            JSONArray head = (JSONArray) jsonHead.get("head");

            if (head.isNull(0) || head == null) {
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
            kyonggiRepository.saveAll(kyonggis);
            kyonggis.clear();
            count++;
            System.out.println("count: " + count);
        }
    }
}
