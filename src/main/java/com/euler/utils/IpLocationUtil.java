package com.euler.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static com.euler.constant.Constant.IPLOCATIONAK;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public class IpLocationUtil {
    /**
     * 读取
     *
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 创建链接
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    //    生成API
    private static String generateLocationApi(String ip) {
        return String.format("https://restapi.amap.com/v5/ip?key=%s&type=4&ip=%s", IPLOCATIONAK, ip);
    }

    public static String getLocationByIp(String ip) throws IOException {
        JSONObject response = readJsonFromUrl(generateLocationApi(ip));
        return response.get("country") +
                String.valueOf(response.get("province")) +
                response.get("city") +
                response.get("district");

    }
}
