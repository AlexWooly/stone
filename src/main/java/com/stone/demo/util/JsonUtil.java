package com.stone.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转字符串
     * @param data
     * @return
     */
    public static String objectToJson(Object data){
        try {
            return MAPPER.writeValueAsString(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToPojo(String jsonData,Class<T> beanType){
        try {
            return MAPPER.readValue(jsonData,beanType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
