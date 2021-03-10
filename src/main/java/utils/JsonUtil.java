package utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class JsonUtil {
    public static JSONObject getJson(HttpServletRequest request) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        int respInt = inputStreamReader.read();
        while(respInt!=-1) {
            result.append((char) respInt);
            respInt = inputStreamReader.read();
        }
        return JSONObject.parseObject(result.toString());
    }
}
