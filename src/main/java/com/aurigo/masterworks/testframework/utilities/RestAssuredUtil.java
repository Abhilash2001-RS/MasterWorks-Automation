package com.aurigo.masterworks.testframework.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class RestAssuredUtil {
    private static final Logger logger = LogManager.getLogger(RestAssuredUtil.class);
    private final int maxRetryCount=3;

    /**
     * Method to perform HTTP GET
     *
     * @param endpoint - end point
     * @param username - username for authorization
     * @param passkey -  password for authorization
     * @return - Rest Assured response object
     */
    public Response doGetRequest(String endpoint, String username, String passkey) {
        RestAssured.defaultParser = Parser.JSON;
        Response response =null;
        int retryCount=maxRetryCount;
        do {
            try {
                var result = given().auth().preemptive().basic(username, passkey).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint);
                if(result.statusCode()==200) {
                    response = result.then().contentType(ContentType.JSON).extract().response();
                    break;
                }
            } catch (Exception ex) {
                logger.error(String.format("Exception occurred in doGetRequest => %s", ex.getMessage()));
            }
            --retryCount;
        }while (retryCount>0);
        return response;
    }
    /**
     * Method to perform HTTP POST
     *
     * @param endpoint - end point
     * @param username - username for authorization
     * @param passkey -  password for authorization
     * @param body - request body
     * @return - Rest Assured response object
     */
    public Response doPostRequest(String endpoint, String username, String passkey, String body) {
        RestAssured.defaultParser = Parser.JSON;
        Response response =null;
        int retryCount=maxRetryCount;
        do {
            try {
                var result = given().auth().preemptive().basic(username, passkey).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).body(body)
                        .post(endpoint);
                if(result.statusCode()==200) {
                    response = result.then().contentType(ContentType.JSON).extract().response();
                    break;
                }
            } catch (Exception ex) {
                logger.error(String.format("Exception occurred in doGetRequest => %s", ex.getMessage()));
            }
            --retryCount;
        }while (retryCount>0);
        return response;
    }

    /**
     * Method to perform HTTP POST without authentication and request body
     *
     * @param endpoint - end point
     * @return - Rest Assured response object
     */
    public Response doPostRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        var result = given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).post(endpoint);
        return result.then().extract().response();
    }
    /**
     * Method to perform HTTP PATCH
     *
     * @param endpoint - end point
     * @param username - username for authorization
     * @param passkey -  password for authorization
     * @param body - request body
     * @return - Rest Assured response object
     */
    public Response doPatchRequest(String endpoint, String username, String passkey, String body) {
        RestAssured.defaultParser = Parser.JSON;
        Response response =null;
        int retryCount=maxRetryCount;
        do {
            try {
                var result = given().auth().preemptive().basic(username, passkey).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).body(body)
                        .patch(endpoint);
                if(result.statusCode()==200) {
                    response = result.then().contentType(ContentType.JSON).extract().response();
                    break;
                }
            } catch (Exception ex) {
                logger.error(String.format("Exception occurred in doGetRequest => %s", ex.getMessage()));
            }
            --retryCount;
        }while (retryCount>0);
        return response;
    }

    /**
     * Method to perform HTTP POST
     *
     * @param endpoint - end point
     * @param body - request body
     * @return - Rest Assured response object
     */
    public Response doPostRequest(String endpoint, String body) {
        RestAssured.defaultParser = Parser.JSON;
        Response response =null;
        int retryCount=maxRetryCount;
        do {
            try {
                var result = given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).body(body)
                        .post(endpoint);
                if(result.statusCode()==200) {
                    response = result.then().contentType(ContentType.JSON).extract().response();
                    break;
                }
            } catch (Exception ex) {
                logger.error(String.format("Exception occurred in doGetRequest => %s", ex.getMessage()));
            }
            --retryCount;
        }while (retryCount>0);
        return response;
    }
}
