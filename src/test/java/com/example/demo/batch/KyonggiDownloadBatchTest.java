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
    /*늦은 나이에 비전공자 개발자가 된 후기
    * 아무 생각 없는 삶이었습니다. 고등학생때 공부를 하지 않고
    * 친구들과 어울려 놀기에 바빴습니다. 고3이 된 시점에도 아무런
    * 위기 의식이 없었고 독서실비나 학원비를 삥땅 쳐서 놀기도 했네요..
    * 고3때 원서를 써보고 되는 곳이 하나 없었습니다. 당시 성적은 언수외 5/8/7
    * 아무 생각없이 부모님의 권유로 재수학원에 등록을 했죠. 친구들은 니가 할 수 있냐며
    * 놀려대고 어쩡쩡한 전문대학교를 진학하거나 아르바이트를 했죠.
    * 열심히 하려 노력했습니다. 쉬엄쉬엄하기도 했지만 초반에는 열심히 했어요.
    * 하지만 하루종일 같이 있고 합법적으로 성인이 된 20살에 유혹은 정말 많았어요.
    * 몰래 하지도 않아도 된다는 해방감 뚫리는 곳을 찾는다며 돌아다니지 않고 내 의지대로
    * 술 담배를 할 수 있다는 것에 많이 흔들렸죠.. 공부를 안 한 거는 아니지만 거의 놀다시피
    * 다녔네요 모의고사 성적도 4/5/4정도로 낮았네요
    * 수능날이 갑자기 찾아오더라구요.. 벌써 10년 이상이 지난 날이지만
    * 그때 오전에 떨림이란.. 차마 말할 수 없네요. 부모님에게 미안한 마음과 망하면 어떡하지 라는
    * 마음 하지만 정말 운이 좋게도 성적이 너무 잘나왔어요
    * 가채점을 하면서 소리를 질렀던 기억이 있네요. 언수외 1/4/3.. 한번도 나오지 않았던 등급이었는데
    * 정말 운이 좋았다 생각하며 원서를 썼습니다. 인서울 하위권 경영학과를 진학하게 되었죠
    * 당시엔 뭐가 그렇게 소극적이었는지 나는 21살이니까 어울리지 못할꺼야 생각이 들었네요
    * 또한 학과 분위기가 나이와 상관없이 선후배문화여서 조금 더 위축되는 감이 있었습니다.
    * 어울리는 친구들을 찾아서 그 무리 안에서만 놀았어요.. 맨날 술을 먹고 먹고 그러다가 입원도 했네요..
    * 거기서 제 첫 여자친구도 사귀게 되었습니다. 모든게 서툴고 그렇지만 한살 많다는 이유로 참 나이 든 척 했었네요.
    * 그 친구와 5년이상 만났어요 둘만 다니게 되면서 대학친구들과도 조금씩 멀어지고 내 가족과 그 한 사람만이 전부였어요.
    * 아니 혼자 그렇게 머리 속을 셋팅한거 같아요. 괜찮은 사람이 되고 싶었지만 그 친구에게 상처도 많이 주기도 했고
    * 방황하기라고 표현하기도 민망하게 이불 속에 갖혀있던 거 같아요.
    * 실업률 기사에 안도하며 하루를 의미 없이 보냈네요. 신기하게도 암기력은 좋은지 대학 학점은 꽤 괜찮았고 장학금을 받으며 다녔죠..
    * 그게 전부였습니다. 남들이 따는 토익도 800점을 맞췄습니다. 면접을 몇번 보고 면접이 무서워 보지 못했고 노력도 하지 않았고 눈만 높아서
    * 의미 없이 하루를 보냈어요.. 알바를 한 돈으로 여행도 다니고 취미생활하면서 인생을 안일하게 살았던 거 같아요.. 그러다보니 31살이 되었네요..
    * 남들은 다 취업했는데 이런 소리를 듣기 싫어 고등학교 친구만 간간히 연락하고 모두와 연락을 끊게 되었고 새로 만난 여자친구만 보고 살았어요.
    * 그 여친도 저를 지켜봤죠. 힘들었을꺼에요. 졸업하고 지난 어영부영의 4~5년의 시간을 날린 후에야 유망하다는 이유 하나로 적성에 맞는지 안맞는지도
    * 확인도 하지 않은 채 it 국비지원학원을 등록하게 됐습니다. 선행학습이 다 되어있는 사람이 대부분이었고 저는 자바에 대해서 하나도 알지 못했습니다.
    * 메인 메소드 안에서 "Hello, World!"를 출력하는 거에 신기했고 이걸로 어떻게 웹페이지를 만드는 건지 궁금했어요. for문과 if문을 배우면서도
    * 잘 몰랐죠. 이번엔 정말 열심히 했습니다. 제 인생에 마지막 기회라고 생각이 들더라구요. 더 늦어지면 어디도 취업을 하지 못하는 사람이 될 거 같아서..
    * 결국 부모님에 의지하며 현실에 안주하고 부모님을 갉아먹는 삶을 산다는 생각에 더더욱 열심히 했습니다. 학원 끝나고 항상 12시까지 공부를 했고 주말에도
    * 그렇게 공부를 했던 거 같아요 문과였던 저는 평생 암기만 해왔고 논리적인 사고를 한다는 생각을 하지 못했는데 그래서 많이 막혔던 거 같아요.
    * 수학을 잘하지 못했거든요. 뭐 지금도 그건 맞는 거 같습니다. 프로젝트 조장을 맡아서 프로젝트를 진행했습니다. 종강일이 다가올 수록 학원을 더 다니고 싶다는
    * 생각이 들더라구요 또 현실에 안주하려는 생각이 드는 거에요. 그래서 정신차리자 하고 종강하기 전부터 면접을 보러 다녔습니다. 첫 면접을 보러갔는데
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */
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