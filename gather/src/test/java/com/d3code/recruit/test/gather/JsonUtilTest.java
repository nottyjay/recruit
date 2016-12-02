package com.d3code.recruit.test.gather;

import com.d3code.recruit.gather.util.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jileilei on 2016/12/1.
 */
public class JsonUtilTest {

    @Test
    public void toJsonTest(){
        Map<String, Object> results = new HashMap<>();
        results.put("code", 200);
        results.put("message", "Test");
        System.out.println(JsonUtil.toJson(results));
    }
}
