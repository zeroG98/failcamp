package mr.web.myapp;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.*;

import mr.web.model.CampDto;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CampApiController {
	@RequestMapping("/")
    public String campTest(Model model) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/locationBasedList?serviceKey=%2BwbABsM7PmDRS6V%2Fj4kkP5yB5Y7gDkQHYGgbzJN4pPtdcK%2F3bRFfMwO8wVpJK2Yz2Mcy8FlnbrYsG62bBG3S5A%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json"); /*URL*/
//            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=hqEqmWQvsSnJGpSsYLDWNJ8og213oiNjT6LYPs2kOromW7%2F3B4RpeHgsJltOEBClBn5TP6slQutim75y3fZIPw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC*/
//        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("mapX", "UTF-8") + "=" + URLEncoder.encode("128.6142847", "UTF-8")); /*GPS X좌표(WGS84 경도 좌표)*/
        urlBuilder.append("&" + URLEncoder.encode("mapY", "UTF-8") + "=" + URLEncoder.encode("36.0345423", "UTF-8")); /*GPS Y좌표(WGS84 위도 좌표)*/
        urlBuilder.append("&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*거리 반경(단위:m) Max값 20000m=20km*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String jsonData = sb.toString();

        // 파싱처리
        JsonElement jsonElement = JsonParser.parseString(jsonData);

        // 자바 json 객체
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonObject responseObj = jsonObject.get("response").getAsJsonObject();
//            System.out.println(responseObj);
        JsonObject bodyObj = responseObj.get("body").getAsJsonObject();
//            System.out.println(bodyObj);
        JsonObject itemsObj = bodyObj.get("items").getAsJsonObject();
        JsonArray arrayData = itemsObj.get("item").getAsJsonArray();
        System.out.println(arrayData.size());

        Gson gson = new Gson();

//          https://www.jsonschema2pojo.org/
            CampDto campInfo = gson.fromJson(arrayData.get(0), CampDto.class);

        List<CampDto> campInfos = new ArrayList<CampDto>();


        for(int i=0; i<arrayData.size(); i++) {
//                    System.out.println(arrayData.get(i));
//            CampDto campInfo = gson.fromJson(arrayData.get(i), CampDto.class);
            campInfos.add(campInfo);
        }

//        for (CampDto cInfo: campInfos) {
//            System.out.println(cInfo.getFeatureNm());
//        }
        model.addAttribute("campInfos", campInfos);

        return "/page/home";
    }
}
