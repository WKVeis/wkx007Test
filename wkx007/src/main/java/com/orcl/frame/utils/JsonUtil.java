package com.orcl.frame.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static ObjectMapper mapper = new ObjectMapper();

    public static String writeJson(Object obj) {
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("对象转换成json失败：{}", e.getMessage(), e);
        }
        return json;
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            logger.error("json转换成对象失败：{}", e.getMessage(), e);
        }
        return map;
    }

    public static <T> T jsonToObject(String json, Class<T> tClass) {
        T o = null;
        try {
            o = mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json转换成对象失败：{}", e.getMessage(), e);
        }
        return o;
    }

    public static JsonNode readTree(String str) {
        try {
            return mapper.readTree(str);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
