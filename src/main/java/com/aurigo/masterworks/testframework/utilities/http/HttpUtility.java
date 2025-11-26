package com.aurigo.masterworks.testframework.utilities.http;

import com.amazonaws.http.apache.request.impl.HttpGetWithBody;
import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.api.Constants;
import com.aurigo.masterworks.testframework.api.utilities.APIUrls;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.util.Strings;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HttpUtility extends BaseFramework implements IHttpUtility {

    private static final Logger logger = LogManager.getLogger(HttpUtility.class);
    private final int DEFAULT_CONNECTION_TIMEOUT = Constants.CONNECTION_TIMEOUT;
    private final String COOKIE = "Cookie";
    private final String ASPXAUTH = ".ASPXAUTH";

    // Private members
    private SSLSocketFactory sslSocketFactory = null;
    private HostnameVerifier hnVerifier = null;
    private boolean allowHttpFailover = false;
    private boolean disableSSLandHostNameVerification = false;
    private boolean useBearerHeader = false;
    private  CookieStore httpCookieStore = null;

    private int connectionTimeoutMsec = DEFAULT_CONNECTION_TIMEOUT;
    private static Map<String, String> cookies = new HashMap<>();

    /**
     * Get Connection Timeout in Milliseconds.
     *
     * @return Connection Timeout in Milliseconds.
     */
    public int getConnectionTimeoutMsec() {
        return connectionTimeoutMsec;
    }

    /**
     * Set Connection Timeout in Milliseconds.
     *
     * @param connectionTimeoutMsec Connection Timeout in Milliseconds.
     */
    public void setConnectionTimeoutMsec(int connectionTimeoutMsec) {
        this.connectionTimeoutMsec = connectionTimeoutMsec;
    }

    /**
     * Get Http fail-over flag.
     *
     * @return
     */
    public boolean getAllowHttpFailover() {
        return (allowHttpFailover);
    }

    /**
     * Set Http fail-over flag.
     *
     * @param doHttp If true, set Http fail-over flag, else set as false.
     */
    public void setAllowHttpFailover(boolean doHttp) {
        allowHttpFailover = doHttp;
    }

    /**
     * Get SSL and Host verification flag.
     *
     * @return SSL and Host verification flag.
     */
    public boolean getDisableSSLandHostNameVerification() {
        return disableSSLandHostNameVerification;
    }

    /**
     * Set SSL and Host verification flag.
     *
     * @param disableSSLandHostNameVerification If true, set SSL and Host verification flag, else set as false.
     */
    public void setDisableSSLandHostNameVerification(boolean disableSSLandHostNameVerification) {
        this.disableSSLandHostNameVerification = disableSSLandHostNameVerification;
    }

    /**
     * Set Bearer header token.
     *
     * @param useBearerHeader If true, set bearer header token, else set as false.
     */
    public void setUseBearerHeader(boolean useBearerHeader) {
        this.useBearerHeader = useBearerHeader;
    }

    /**
     * Set up Proxy.
     *
     * @param proxyHost Proxy host.
     * @param proxyPort Proxy port.
     */
    public void setupProxy(String proxyHost, String proxyPort) {
        // Need to get through the web proxy
        Properties sysProp = System.getProperties();
        sysProp.setProperty("https.proxyHost", proxyHost);
        sysProp.setProperty("https.proxyPort", proxyPort);
    }

    /**
     * Disable SSL and Host name verification.
     *
     * @return True if set, else false.
     */
    public boolean disableSSLandHostNameVerification() {

        if (disableSSLandHostNameVerification) {

            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            try {
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new SecureRandom());
                sslSocketFactory = sslContext.getSocketFactory();
            } catch (Exception e) {
                logger().info("Could not initialize the SSL connection " + e);
                return false;
            }

            hnVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession sslSession) {
                    return true;
                }
            };
        } else {
            // Do nothing or log??
            logger().info("Using the inherited credentials for SSL");
        }
        return true;
    }

    /**
     * Invoke Https call.
     *
     * @param httpMethod        - HTTP Method to use (GET, POST, PUT, DELETE, etc.)
     * @param urlString         - URL to send the request to.
     * @param queryString       - Query string which needs to ba passed to endpoint.
     * @param message           - Message data to include in the request body. (Optional; can be set to null)
     * @param contentType       - Content type of the message body. (Optional; can be set to null)
     * @param acceptType        - Data type requested for the response data. (Optional; can be set to null)
     * @param additionalHeaders - Additional header which needs to be part of Request.
     * @return HttpResult - Data collection representing the communication result;
     */
    public HttpResult talkHttp(HttpMethodsEnum httpMethod, String urlString, String queryString, String message, String contentType, String acceptType, Map<String, String> additionalHeaders) {
        return talkHttpMaster(httpMethod, urlString, queryString, message, contentType, acceptType, additionalHeaders);
    }

    /**
     * talkHttp
     * This performs HTTP requests using BASIC authentication (username, password). It does the work of communicating
     * but does not know what it sends or how to interpret the results.
     *
     * @param httpMethod        - HTTP Method to use (GET, POST, PUT, DELETE, etc.)
     * @param urlString         - URL to send the request to.
     * @param queryString       - Query string.
     * @param message           - Message data to include in the request body. (Optional; can be set to null)
     * @param contentType       - Content type of the message body. (Optional; can be set to null)
     * @param acceptType        - Data type requested for the response data. (Optional; can be set to null)
     * @param additionalHeaders - Additional headers added the request.
     * @param mpEntityBuilder   - Multi part entity builder.
     * @return HttpResult - Data collection representing the communication result;
     */
    public HttpResult talkHttp(HttpMethodsEnum httpMethod, String urlString, String queryString, String message, String contentType, String acceptType, Map<String, String> additionalHeaders, HashMap<String, String> mpEntityBuilder) {
        return talkHttpMaster(httpMethod, urlString, queryString, message, contentType, acceptType, additionalHeaders, mpEntityBuilder);
    }

    /**
     * Invoke Http master call.
     *
     * @param httpMethod        - HTTP Method to use (GET, POST, PUT, DELETE, etc.)
     * @param urlString         - URL to send the request to.
     * @param queryString       - Query string which needs to ba passed to endpoint.
     * @param message           - Message data to include in the request body. (Optional; can be set to null)
     * @param contentType       - Content type of the message body. (Optional; can be set to null)
     * @param acceptType        - Data type requested for the response data. (Optional; can be set to null)
     * @param additionalHeaders - Additional headers added the request.
     * @return HttpResult - Data collection representing the communication result;
     */
    private HttpResult talkHttpMaster(HttpMethodsEnum httpMethod, String urlString, String queryString, String message,
                                      String contentType, String acceptType, Map<String, String> additionalHeaders) {

        HttpResult returnResult = new HttpResult();

        CloseableHttpClient httpclient = getClient(true);
        CloseableHttpResponse response = null;
        try {
            if (urlString == null) {
                returnResult.exception = new InvalidParameterException("URL can not be null.");
                return returnResult;
            }

            returnResult.url = urlString;

            if (queryString != null) {
                returnResult.url += queryString;
                urlString += queryString;
            }

            HttpUriRequest req;
            switch (httpMethod) {
                case eGet:
                    req = new HttpGet(urlString);
                    break;
                case ePost:
                    req = getPostRequest(urlString, message);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + httpMethod);
            }

            logger().info("   Executing request: " + req.getRequestLine());
            logger().info("   Message is: " + message);

            if (StringUtils.isNotBlank(contentType)) {
                req.setHeader("Content-type", contentType);
            }

            if (StringUtils.isNotBlank(acceptType)) {
                req.setHeader("Accept", acceptType);
            }

            if (additionalHeaders != null) {
                for (Map.Entry<String, String> kv : additionalHeaders.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }else {
                // For Cloud APIs, where Cookie to be set instead of Authorization-Token
                // Else response would be - 'Authorization has been denied for this request.'
                for (Map.Entry<String, String> kv : cookies.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }

            response = httpclient.execute(req);
            if(!cookies.containsKey(COOKIE)) {
                List<Cookie> headers = httpCookieStore.getCookies();
                for (Cookie h : headers) {
                    if (h.getName().equalsIgnoreCase(ASPXAUTH)) {
                        cookies.put(COOKIE, String.format("%s=%s", h.getName(), h.getValue()));
                    }
                }
            }

            returnResult.responseCode = response.getStatusLine().getStatusCode();
            returnResult.output = EntityUtils.toString(response.getEntity());
            logger().info("   Response code from server is: " + returnResult.responseCode);
            logger().info("   Response output from server is: " + returnResult.output.replaceAll("alert", "aalert"));

            // Now check the return code for something in the 200 range
            if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                returnResult.success = true;
                returnResult.usedSsl = false;
            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 400) && (returnResult.responseCode < 410)) {

            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 500) && (returnResult.responseCode < 510)) {
                //IF response is 502 bad gateway then sleep for 5 second and try once again
                Thread.sleep(5000);
                response = httpclient.execute(req);
                if ((returnResult.responseCode != null) &&
                        (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                    returnResult.success = true;
                    returnResult.usedSsl = false;
                }
            }

        } catch (ClientProtocolException cpe) {
            returnResult.exception = cpe;
            logger().info("Server got a ClientProtocolException: " + cpe.getMessage());
        } catch (IOException io) {
            returnResult.exception = io;
            logger().info("Server got a IOException: " + io.getMessage());
        } catch (Exception e) {
            returnResult.exception = e;
            logger().info("Server got a Exception: " + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
            }
        }

        return returnResult;
    }

    /**
     * Invoke Http master call.
     *
     * @param httpMethod        - HTTP Method to use (GET, POST, PUT, DELETE, etc.)
     * @param urlString         - URL to send the request to.
     * @param queryString       - Query string which needs to ba passed to endpoint.
     * @param message           - Message data to include in the request body. (Optional; can be set to null)
     * @param contentType       - Content type of the message body. (Optional; can be set to null)
     * @param acceptType        - Data type requested for the response data. (Optional; can be set to null)
     * @param additionalHeaders - Additional headers added the request.
     * @param mpEntityBuilder   - Multipart entity builder.
     * @return HttpResult - Data collection representing the communication result;
     */
    private HttpResult talkHttpMaster(HttpMethodsEnum httpMethod, String urlString, String queryString, String message,
                                      String contentType, String acceptType, Map<String, String> additionalHeaders, HashMap<String, String> mpEntityBuilder) {

        HttpResult returnResult = new HttpResult();

        CloseableHttpClient httpclient = getClient(false);
        CloseableHttpResponse response = null;
        try {
            if (urlString == null) {
                returnResult.exception = new InvalidParameterException("URL can not be null.");
                return returnResult;
            }

            returnResult.url = urlString;

            if (queryString != null) {
                returnResult.url += queryString;
                urlString += queryString;
            }

            HttpUriRequest req;
            switch (httpMethod) {
                case eGet:
                    req = new HttpGet(urlString);
                    // Not a best practise to post body for a GET request.
                    if(Strings.isNotNullAndNotEmpty(message)){
                        HttpEntity entity = new StringEntity(message, ContentType.TEXT_PLAIN);
                        HttpGetWithBody get = new HttpGetWithBody(urlString);
                        get.setEntity(entity);
                        req = get;
                    }
                    break;
                case ePost:
                    req = getPostRequest(urlString, message, mpEntityBuilder);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + httpMethod);
            }

            logger().info("   Executing request: " + req.getRequestLine());
            logger().info("   Message is: " + message);

            if (StringUtils.isNotBlank(contentType)) {
                req.setHeader("Content-type", contentType);
            }

            if (StringUtils.isNotBlank(acceptType)) {
                req.setHeader("Accept", acceptType);
            }

            if (additionalHeaders != null) {
                for (Map.Entry<String, String> kv : additionalHeaders.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }else
            {
                // For Cloud APIs, where Cookie to be set instead of Authorization-Token
                // Else response would be - 'Authorization has been denied for this request.'
                for (Map.Entry<String, String> kv : cookies.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }

            response = httpclient.execute(req);
            returnResult.responseCode = response.getStatusLine().getStatusCode();
            returnResult.output = returnResult.responseCode != 204? EntityUtils.toString(response.getEntity()) : "";
            logger().info("   Response code from server is: " + returnResult.responseCode);
            logger().info("   Response output from server is: " + returnResult.output.replaceAll("alert", "aalert"));

            // Now check the return code for something in the 200 range
            if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                returnResult.success = true;
                returnResult.usedSsl = false;
            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 400) && (returnResult.responseCode < 410)) {

            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 500) && (returnResult.responseCode < 510)) {
                //IF response is 502 bad gateway then sleep for 5 second and try once again
                Thread.sleep(5000);
                response = httpclient.execute(req);
                if ((returnResult.responseCode != null) &&
                        (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                    returnResult.success = true;
                    returnResult.usedSsl = false;
                }
            }

        } catch (ClientProtocolException cpe) {
            returnResult.exception = cpe;
            logger().info("Server got a ClientProtocolException: " + cpe.getMessage());
        } catch (IOException io) {
            returnResult.exception = io;
            logger().info("Server got a IOException: " + io.getMessage());
        } catch (Exception e) {
            returnResult.exception = e;
            logger().info("Server got a Exception: " + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
            }
        }

        return returnResult;
    }

    /**
     * Invoke Http master call.
     *
     * @param httpMethod        - HTTP Method to use (GET, POST, PUT, DELETE, etc.)
     * @param urlString         - URL to send the request to.
     * @param queryString       - Query string which needs to ba passed to endpoint.
     * @param message           - Message data to include in the request body. (Optional; can be set to null)
     * @param contentType       - Content type of the message body. (Optional; can be set to null)
     * @param acceptType        - Data type requested for the response data. (Optional; can be set to null)
     * @param additionalHeaders - Additional headers added the request.
     * @param urlParameters   - From data in Name Value Pair.
     * @return HttpResult - Data collection representing the communication result;
     */
    private HttpResult talkHttpMaster(HttpMethodsEnum httpMethod, String urlString, String queryString, String message,
                                      String contentType, String acceptType, Map<String, String> additionalHeaders, List<NameValuePair> urlParameters) {

        HttpResult returnResult = new HttpResult();

        CloseableHttpClient httpclient = getClient(false);
        CloseableHttpResponse response = null;
        try {
            if (urlString == null) {
                returnResult.exception = new InvalidParameterException("URL can not be null.");
                return returnResult;
            }

            returnResult.url = urlString;

            if (queryString != null) {
                returnResult.url += queryString;
                urlString += queryString;
            }

            HttpUriRequest req;
            switch (httpMethod) {
                case eGet:
                    req = new HttpGet(urlString);
                    break;
                case ePost:
                    HttpPost httpPost = new HttpPost(urlString);
                    String boundary = Long.toHexString(System.currentTimeMillis());
                    //httpPost.setHeader("Content-Type", "multipart/form-data; boundary="+boundary);
                    httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
                    ((UrlEncodedFormEntity)(httpPost).getEntity()).setContentType("multipart/form-data; boundary=--"+boundary);
                    req = httpPost;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + httpMethod);
            }

            logger().info("   Executing request: " + req.getRequestLine());
            logger().info("   Message is: " + message);

            if (StringUtils.isNotBlank(contentType)) {
                req.setHeader("Content-type", contentType);
            }

            if (StringUtils.isNotBlank(acceptType)) {
                req.setHeader("Accept", acceptType);
            }

            if (additionalHeaders != null) {
                for (Map.Entry<String, String> kv : additionalHeaders.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }else
            {
                // For Cloud APIs, where Cookie to be set instead of Authorization-Token
                // Else response would be - 'Authorization has been denied for this request.'
                for (Map.Entry<String, String> kv : cookies.entrySet()) {
                    req.setHeader(kv.getKey(), kv.getValue());
                }
            }

            response = httpclient.execute(req);
            returnResult.responseCode = response.getStatusLine().getStatusCode();
            returnResult.output = returnResult.responseCode != 204? EntityUtils.toString(response.getEntity()) : "";
            logger().info("   Response code from server is: " + returnResult.responseCode);
            logger().info("   Response output from server is: " + returnResult.output.replaceAll("alert", "aalert"));

            // Now check the return code for something in the 200 range
            if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                returnResult.success = true;
                returnResult.usedSsl = false;
            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 400) && (returnResult.responseCode < 410)) {

            } else if ((returnResult.responseCode != null) &&
                    (returnResult.responseCode >= 500) && (returnResult.responseCode < 510)) {
                //IF response is 502 bad gateway then sleep for 5 second and try once again
                Thread.sleep(5000);
                response = httpclient.execute(req);
                if ((returnResult.responseCode != null) &&
                        (returnResult.responseCode >= 200) && (returnResult.responseCode < 300)) {
                    returnResult.success = true;
                    returnResult.usedSsl = false;
                }
            }

        } catch (ClientProtocolException cpe) {
            returnResult.exception = cpe;
            logger().info("Server got a ClientProtocolException: " + cpe.getMessage());
        } catch (IOException io) {
            returnResult.exception = io;
            logger().info("Server got a IOException: " + io.getMessage());
        } catch (Exception e) {
            returnResult.exception = e;
            logger().info("Server got a Exception: " + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
            }
        }

        return returnResult;
    }

    /**
     * Prepare Post request.
     *
     * @param urlString -   Endpoint.
     * @param body      -   Body.
     * @return Http Post request.
     */
    private HttpPost getPostRequest(String urlString, String body) {
        HttpPost httpPost = new HttpPost(urlString);
        if (body != null) {
            if (urlString.contains(APIUrls.ATTACHMENT_DOCUMENT) ||
                    urlString.contains(APIUrls.UPLOAD_DOCUMENT) ||
                    urlString.contains(APIUrls.UPLOAD) ||
                    urlString.contains(APIUrls.V3_UPLOAD_DOCUMENT) ||
                    urlString.contains(APIUrls.DocService.UPLOAD_SINGLE_DOCUMENT) ||
                    urlString.contains(APIUrls.DocService.GET_DOCUMENT) ||
                    urlString.contains(APIUrls.DOCUMENTS_UPLOAD_METADATA_EXCEL)) {
                try {
                    File file = new File(body);
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
                    HttpEntity multipart = builder.build();
                    httpPost.setEntity(multipart);
                } catch (Exception ex) {
                    logger().info(ex.getMessage());
                }
            } else {
                HttpEntity entity = new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED);
                httpPost.setEntity(entity);
            }
        }

        return httpPost;
    }

    /**
     * Prepare Post request.
     *
     * @param urlString       -   Endpoint.
     * @param body            -   Body.
     * @param mpEntityBuilder -   Multipart entity builder.
     * @return Http Post request.
     */
    private HttpPost getPostRequest(String urlString, String body, HashMap<String, String> mpEntityBuilder) {
        HttpPost httpPost = new HttpPost(urlString);
        if (body != null) {
            if (urlString.contains(APIUrls.ATTACHMENT_DOCUMENT) ||
                    urlString.contains(APIUrls.UPLOAD_DOCUMENT) ||
                    urlString.contains(APIUrls.UPLOAD) ||
                    urlString.contains(APIUrls.V3_UPLOAD_DOCUMENT) ||
                    urlString.contains(APIUrls.DocService.UPLOAD_SINGLE_DOCUMENT) ||
                    urlString.contains(APIUrls.DocService.GET_DOCUMENT) ||
                    urlString.contains(APIUrls.POST_DOCUMENT_UPLOAD) ||
                    urlString.contains(APIUrls.DOCUMENTS_UPLOAD_METADATA_EXCEL)) {
                try {
                    File file = new File(body);
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    if (mpEntityBuilder != null) {
                        for (Map.Entry<String, String> entry : mpEntityBuilder.entrySet()) {
                            if (new File(entry.getValue()).exists()) {
                                builder.addBinaryBody(entry.getKey(), new File(entry.getValue()));
                            } else {
                                builder.addTextBody(entry.getKey(), entry.getValue().replace("\"", ""));
                            }
                        }
                    }
                    builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
                    HttpEntity multipart = builder.build();
                    httpPost.setEntity(multipart);
                } catch (Exception ex) {
                    logger().info(ex.getMessage());
                }
            }
            else if(urlString.contains(APIUrls.POST_GENERIC_XML_PICKER)){
                HttpEntity entity = new StringEntity(body, ContentType.TEXT_PLAIN);
                httpPost.setEntity(entity);
            } else {
                HttpEntity entity = new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED);
                httpPost.setEntity(entity);
            }
        } else {
            if (mpEntityBuilder != null) {
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                for (Map.Entry<String, String> entry : mpEntityBuilder.entrySet()) {
                    if (new File(entry.getValue()).exists()) {
                        builder.addBinaryBody(entry.getKey(), new File(entry.getValue()));
                    } else {
                        builder.addTextBody(entry.getKey(), entry.getValue());
                    }
                }
                HttpEntity multipart = builder.build();
                httpPost.setEntity(multipart);
            }
        }

        return httpPost;
    }

    /**
     * Get client.
     * If we are using proxy, we will have to ignore the SSL cert verification.
     * @param isCookieEnabled If enabled create and attach default cookie.
     * @return Client.
     */
    private CloseableHttpClient getClient(boolean isCookieEnabled){
        HttpClientBuilder builder = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        if (isProxyEnabled) {
            try {
                SSLContext context = SSLContext.getInstance("TLSv1.2");
                TrustManager[] trustManager = new TrustManager[]{
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }

                            public void checkClientTrusted(X509Certificate[] certificate, String str) {
                            }

                            public void checkServerTrusted(X509Certificate[] certificate, String str) {
                            }
                        }
                };
                context.init(null, trustManager, new SecureRandom());

                SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(context,
                        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                builder.setSSLSocketFactory(socketFactory);
            }
            catch(Exception ex){
                logger().info("Proxy enablement error: " + ex.getMessage());
            }
            String[] proxyDetails = EnvironmentHelper.getPropertyValue("proxyAddress").split(":");
            HttpHost proxy = new HttpHost(proxyDetails[0], Integer.parseInt(proxyDetails[1]));
            builder.setProxy(proxy);
        }
        if(isCookieEnabled){
            httpCookieStore = new BasicCookieStore();
            builder.setDefaultCookieStore(httpCookieStore);
        }

        return  builder.build();
    }

    /**
     * Makes an HTTP request and returns the response code back
     *
     * @param address - URL to which the connection is requested
     * @return HTTP Response Code
     */
    public int getResponseCode(String address) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(address).openConnection();
            httpURLConnection.setConnectTimeout(8000);
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            logger.error(String.format("Error Occurred while getting HTTP Response : %s",e.getMessage()));
        }
        return 400;//Returning 400:Bad Request as the default failure code
    }
}
