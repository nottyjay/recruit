package com.d3code.recruit.gather.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by jileilei on 2016/12/1.
 */
public class JsonUtil {

    private static JsonUtil instance = new JsonUtil();
    private static ObjectMapper mapper;

    private JsonUtil(){
        JsonUtil.mapper = new ObjectMapper();
    }

    public static <T> String toJson(T bean){

        try {
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String jsonStr, Class<T> beanClass){
        try {
            return mapper.readValue(jsonStr, beanClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
