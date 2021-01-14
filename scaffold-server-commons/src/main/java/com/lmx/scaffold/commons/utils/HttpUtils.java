
package com.lmx.scaffold.commons.utils;



import com.lmx.scaffold.commons.constant.Constants;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * http utils
 */
public class HttpUtils {


	public static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * get http request content
	 * @param url url
	 * @return http response
	 */
	public static String get(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(url);
        /** set timeout、request time、socket timeout */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(Constants.SOCKET_TIMEOUT)
                .setRedirectsEnabled(true)
                .build();
        httpget.setConfig(requestConfig);
        String responseContent = null;
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpget);
            //check response status is 200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseContent = EntityUtils.toString(entity, Constants.UTF_8);
                }else{
                    logger.warn("http entity is null");
                }
            }else{
                logger.error("http get:{} response status code is not 200!",url);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }

            if (!httpget.isAborted()) {
                httpget.releaseConnection();
                httpget.abort();
            }

            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return responseContent;
    }

    public static String get(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        logger.info("get url:" + url);
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

//            logger.info(uri.getHost() + ":" + uri.getPort() + "/" + uri.getPath());
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                logger.info("status:" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            logger.error("get调用:" + url, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        logger.info("http get res :" + resultString);
        return resultString;
    }

    public static String get(String url, Map<String, String> param, Header header) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        logger.info("get url:" + url);
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader(header);

//            logger.info(uri.getHost() + ":" + uri.getPort() + "/" + uri.getPath());
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                logger.info("status:" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            logger.error("get调用:" + url, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String get(String url, String cookeName, String cookieValue){
        CloseableHttpClient httpclient = getHttpClients(cookeName,cookieValue);

        HttpGet httpget = new HttpGet(url);
        /** set timeout、request time、socket timeout */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(Constants.SOCKET_TIMEOUT)
                .setRedirectsEnabled(true)
                .build();
        httpget.setConfig(requestConfig);
        String responseContent = null;
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpget);
            //check response status is 200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseContent = EntityUtils.toString(entity, Constants.UTF_8);
                }else{
                    logger.warn("http entity is null");
                }
            }else{
                logger.error("http get:{} response status code is not 200!",url);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }

            if (!httpget.isAborted()) {
                httpget.releaseConnection();
                httpget.abort();
            }

            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return responseContent;
    }

    public static String post(String url, String json, Header header) {
        logger.info("post url:" + url);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            httpPost.setHeader(header);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("postJson调用:" + url, e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String post(String url, String json, Header[] headers) {
        logger.info("post url:" + url);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            httpPost.setHeaders(headers);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("postJson调用:" + url, e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }


    public static String post(String url, String json) {
        logger.info("post url:" + url);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("postJson调用:" + url, e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static CloseableHttpClient getHttpClients(String cookieName, String cookieValue) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        BasicClientCookie cookie = new BasicClientCookie(cookieName, cookieValue);
        cookie.setVersion(0);
        cookieStore.addCookie(cookie);
        //带有cookie的httpclient
        return httpClientBuilder.setDefaultCookieStore(cookieStore).build();
    }




}
