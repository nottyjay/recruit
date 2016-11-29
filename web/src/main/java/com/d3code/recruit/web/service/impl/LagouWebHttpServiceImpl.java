package com.d3code.recruit.web.service.impl;

import com.d3code.recruit.gather.handle.InfomationHandle;
import com.d3code.recruit.gather.service.WebHttpService;
import com.d3code.recruit.web.handle.LagouInfomationHandle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
@Service("lagouWebHttpServiceImpl")
public class LagouWebHttpServiceImpl implements WebHttpService {

    private static final Logger LOG = LoggerFactory.getLogger(LagouWebHttpServiceImpl.class);

    private InfomationHandle handle = new LagouInfomationHandle();

    private static final Integer CONNECT_TIMEOUT = 10 * 1000;
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public void loadPageSourceCode(String url, Map<String, String> params) throws UnsupportedEncodingException {
        HttpHost proxy = null;
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(CONNECT_TIMEOUT).build();
        CloseableHttpClient client = null;
        if(proxy == null) {
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        }else{
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).setProxy(proxy).build();
        }
        HttpPost request = null;

        request = new HttpPost(url);
        List<NameValuePair> formParams = new ArrayList<>();
        if(params != null & params.size() > 0){
            for(Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
        request.setEntity(urlEncodedFormEntity);

        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300){
                HttpEntity entity = response.getEntity();
                String content= entity != null ? EntityUtils.toString(entity, Charset.forName("UTF8")) : null;
                handle.handleInfo(content);
            }else{
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } catch (ClientProtocolException e) {
            LOG.error("ClientProtocolException:", e);
            LOG.warn("-----------------请求出现异常:{}-----------------", e.toString());
        } catch (IOException e) {
            LOG.error("IOException:", e);
            LOG.warn("-----------------请求出现IO异常:{}-----------------", e.toString());
        } catch (Exception e) {
            LOG.error("Exception:", e);
            LOG.warn("-----------------请求出现其他异常:{}-----------------", e.toString());
        } finally {
            //abort the request
            if (null != request && !request.isAborted()) {
                request.abort();
            }
            //close the connection
            HttpClientUtils.closeQuietly(client);
            HttpClientUtils.closeQuietly(response);
        }
    }
}
