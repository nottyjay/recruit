package com.d3code.recruit.gather.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Nottyjay on 2016/11/22 0022.
 */
public interface WebHttpService {

    public void loadPageSourceCode(String url, Map<String, String> params) throws UnsupportedEncodingException;
}
