//package com.zh.device.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.*;
//import java.util.function.Function;
//
//public class HttpClientUtil {
//    private static HttpClientUtil httpClientUtilWithoutSSL = null;
//    private static HttpClientUtil httpClientUtilWithSSL = null;
//
//    static {
//        httpClientUtilWithoutSSL = new HttpClientUtil(false);
//        httpClientUtilWithSSL = new HttpClientUtil(true);
//    }
//
//    private CloseableHttpClient httpClient = null;
//
//    private HttpClientUtil(boolean isSSL) {
//        if (!isSSL) {
//            httpClient = HttpClients.createDefault();
//        } else {
//            try {
//                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//                SSLContext sslContext = SSLContext.getInstance("SSL");
//                sslContext.init(null, new TrustManager[]{
//                        //证书信任管理器（用于https请求）
//                        new X509TrustManager() {
//                            @Override
//                            public void checkClientTrusted(X509Certificate[] arg0,
//                                                           String arg1) throws CertificateException {
//                            }
//
//                            @Override
//                            public void checkServerTrusted(X509Certificate[] arg0,
//                                                           String arg1) throws CertificateException {
//                            }
//
//                            @Override
//                            public X509Certificate[] getAcceptedIssuers() {
//                                return null;
//                            }
//                        }
//                }, new SecureRandom());
//                //获取注册建造者
//                RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
//                //注册http和https请求
//                Registry<ConnectionSocketFactory> socketFactoryRegistry = registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE)
//                        .register("https", new SSLConnectionSocketFactory(sslContext))
//                        .build();
//                //获取HttpClient池管理者
//                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//                //初始化httpClient
//                httpClient = HttpClients.custom().setConnectionManager(connManager).build();
//            } catch (KeyManagementException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static HttpClientUtil getInstanceWithSSL() {
//        return httpClientUtilWithSSL;
//    }
//
//    public static HttpClientUtil getInstanceWithoutSSL() {
//        return httpClientUtilWithoutSSL;
//    }
//
//    public void get(String url, Map<String, String> params, Map<String, String> httpHeader,
//                    RequestConfig config,
//                    Function<HttpResponse, Void> success,
//                    Function<Exception, Void> timeout) throws IOException {
//        CloseableHttpResponse response = null;
//        try {
//            String _url = url;
//            StringBuilder sb = new StringBuilder();
//            sb.append(url);
//            if (params != null && params.size() > 0) {
//                sb.append("?");
//                Set<Map.Entry<String, String>> entries = params.entrySet();
//                for (Map.Entry<String, String> entry : entries) {
//                    sb.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=")
//                            .append(URLEncoder.encode(entry.getValue(), "utf-8")).append("&");
//                }
//                _url = sb.toString().substring(0, sb.length());
//            }
//            HttpGet get = new HttpGet(_url);
//            if (httpHeader != null && httpHeader.size() > 0) {
//                Set<Map.Entry<String, String>> head = httpHeader.entrySet();
//                for (Map.Entry<String, String> entry : head) {
//                    get.setHeader(entry.getKey(), entry.getValue());
//                }
//            }
//            if (config != null) {
//                get.setConfig(config);
//            }
//            response = httpClient.execute(get);
//            success.apply(response);
//        } catch (Exception e) {
//            timeout.apply(e);
//        } finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//    }
//
//
//    //"mqttPort",mqttPort.toString()
//    public JSONObject get(String url, Map<String, String> params, Map<String, String> httpHeader
//            , RequestConfig config
//    ) throws IOException {
//        CloseableHttpResponse response = null;
//        try {
//            String _url = url;
//            StringBuilder sb = new StringBuilder();
//            sb.append(url);
//            if (params != null && params.size() > 0) {
//                sb.append("?");
//                Set<Map.Entry<String, String>> entries = params.entrySet();
//                for (Map.Entry<String, String> entry : entries) {
//                    sb.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=")
//                            .append(URLEncoder.encode(entry.getValue(), "utf-8")).append("&");
//                }
//                //sb:http://catl-test.yunext.com:80/config/verifyDomain?mqttPort=18835&
//                _url = sb.toString().substring(0, sb.length());
//                //_url:http://catl-test.yunext.com:80/config/verifyDomain?mqttPort=18835&
//            }
//            //get请求
//            HttpGet get = new HttpGet(_url);
//            System.out.println("url"+_url);
//            if (httpHeader != null && httpHeader.size() > 0) {
//                Set<Map.Entry<String, String>> head = httpHeader.entrySet();
//                for (Map.Entry<String, String> entry : head) {
//                    get.setHeader(entry.getKey(), entry.getValue());
//                }
//            }
//            if (config != null) {
//                get.setConfig(config);
//            }
//            //处理返回数据的实体，可以拿到返回的状态码、首部、实体等等我们需要的东西。
//            response = httpClient.execute(get);
//            System.out.println("response"+response);
//            HttpEntity entity = response.getEntity();
//            System.out.println("状态码"+response.getStatusLine().getStatusCode());
//            // 通过EntityUtils中的toString方法将结果转换为字符串
//            //通过CloseableHttpEntity的getEntity取得实体之后，有两种处理结果的方法，
//
////            方法一：使用EntityUtils来处理。
////            该类是官方提供的一个处理实体的工具类，toSting方法将返回的实体转换为字符串，但是官网不建议使用这个，除非响应实体从一个可信HTTP服务器发起和已知是有限长度的。
////            方法二：使用InputStream来读取
////            因为httpEntity.getContent方法返回的就是InputStream类型。这种方法是官网推荐的方式，需要记得的是要自己释放底层资源。
//            // InputStream content = entity.getContent();
//            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
//            System.out.println("entity"+entity);
//            String str = EntityUtils.toString(entity);
//            JSONObject json = JSON.parseObject(str);
//            return json;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//    }
//
//    public JSONObject post(String url, Map<String, String> params, Map<String, String> httpHeader
//            , RequestConfig config) {
//        HttpEntity reqEntity = null;
//        JSONObject jsonObject = null;
//        CloseableHttpResponse response = null;
//        try {
//            HttpPost post = new HttpPost(url);
//            if (httpHeader != null && httpHeader.size() > 0) {
//                Set<Map.Entry<String, String>> entrySet = httpHeader.entrySet();
//                for (Map.Entry<String, String> entry : entrySet) {
//                    post.addHeader(entry.getKey(), entry.getValue());
//                }
//            }
//            if (params != null && params.size() > 0) {
//                List<NameValuePair> formparams = new ArrayList<>();
//                Set<Map.Entry<String, String>> entries = params.entrySet();
//                for (Map.Entry<String, String> entry : entries) {
//                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//                }
//                reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
//            }
//            post.setEntity(reqEntity);
//            post.setConfig(config);
//            response = httpClient.execute(post);
//            HttpEntity resEntity = response.getEntity();
//            String message = EntityUtils.toString(resEntity, "utf-8");
//            jsonObject = JSON.parseObject(message);
//            return jsonObject;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }finally {
//            if(response != null){
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException{
//        //GET
////        Map<String,String> params=new HashMap<>();
////        params.put("rdbsn","1234567");
////        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)// 连接主机服务超时时间
////                .setConnectionRequestTimeout(5000)// 请求超时时间
////                .setSocketTimeout(10000)// 数据读取超时时间
////                .build();
////        JSONObject jsonObject=null;
////        try {
////            jsonObject=HttpClientUtil.getInstanceWithoutSSL().get("http://localhost:8080/validateDevice",params,null,requestConfig
////                );
////            System.out.println(jsonObject);
////        }catch (IOException e){
////            e.printStackTrace();
////        }
////        Map<String,String>params=new HashMap<>();
////        params.put("rdbsn","123456");
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)// 连接主机服务超时时间
//                .setConnectionRequestTimeout(5000)// 请求超时时间
//                .setSocketTimeout(10000)// 数据读取超时时间
//                .build();
//         HttpClientUtil.getInstanceWithoutSSL().get(
//                "https://www.baidu.com/", null, null, requestConfig,(i) ->{
//                    System.out.println(i.getStatusLine().getStatusCode());
//                    return null;
//                },(x)->{
//                    x.printStackTrace();
//                    return null;
//                });
//
//    }
//
//    public void test() {
//        Function<Integer, Integer> abc = integer -> integer + 1;
//        Function<Integer, Integer> abc2 = integer -> integer * 2;
//        System.out.println(abc.apply(5));
//        System.out.println(abc.andThen(abc2).apply(5));
//        System.out.println(abc.compose(abc2).apply(5));
//    }
//}
