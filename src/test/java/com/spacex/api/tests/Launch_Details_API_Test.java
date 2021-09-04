package com.spacex.api.tests;

import com.spacex.api.common.TestBase;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.logging.Logger;


import static com.spacex.api.utils.ExcelUtils.readExcel;

public class Launch_Details_API_Test extends TestBase {
    private static final Logger logger= Logger.getLogger(String.valueOf(Launch_Details_API_Test.class));
    String methodName;

    String requestBodyPayLoad;
    String headerPayload;
    String queryParamPayload;
    String validationData1;
    String validationData2;



    @BeforeMethod
    public void beforeTestMethod(Method testMethod) {

        try {
            methodName = testMethod.getName();
            String[] testDataDBData = new String[7];
           testDataDBData = readExcel(File_TestData, "Launch_API", methodName);

            requestBodyPayLoad = testDataDBData[0];
            headerPayload = testDataDBData[1];
            queryParamPayload = testDataDBData[2];
           validationData1 = testDataDBData[3];
           validationData2 = testDataDBData[4];
           //validationData3 = testDataDBData[5];

            logger.info(">>>>>>>>>>>TESTDATA FROM EXCEL = " + " requestBodyPayLoad :- " + requestBodyPayLoad);
            logger.info(">>>>>>>>>>>TESTDATA FROM EXCEL = " + " headerPayload :- " + headerPayload);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(" We are unable to read excel file data from file :- " + File_TestData);
        }
    }




    @Test(priority = 1, description = "Validate the Launch API Details using valid parameters")
    public void TC_01_ValidateResponseOfLaunchAPI_withValidParameters() throws Throwable {
        logger.info("validate the recent launch details with valid params");

        String headerArray[] = headerPayload.split(":");
        Response response = requestSpecification
                .get(URI_LaunchDetails);
        System.out.println(URI_LaunchDetails);
        int statusCode = response.getStatusCode();
        String userResponsePayload = response.asString();


        logger.info("Status code of Response---------------------" + statusCode);
        logger.info("Output of Response---------------------" + userResponsePayload);
        String responseAsString = response.getBody().asString();


        logger.info("Launch Details validated succesfully");
        Assert.assertEquals(statusCode, 200);


        logger.info(">>>>>>>>>>>>>>>END of Execution for test case =" + methodName
                + ">>>>>>>>>>>>");
    }

    @Test(priority = 2, description = "Validate the Launch API Details without header value")
    public void TC_02_ValidateResponseOfLaunchAPI_withoutHeaderValue() throws Throwable {
        logger.info("validate the recent launch details without header");
         String headerArray[] = headerPayload.split(":");
        Response response = requestSpecification
                .headers("Xyz", headerArray[0] ).get(URI_LaunchDetails);
        int statusCode = response.getStatusCode();
        String userResponsePayload = response.getBody().asString();
      //  obj.getCore();
        logger.info("Status code of Response---------------------" + statusCode);
        logger.info("Output of Response---------------------" + userResponsePayload);
        logger.info("Launch Details validated succesfully");
        Assert.assertEquals(statusCode, 200);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>END of Execution for test case =" + methodName
                + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    @Test(priority = 3, description = "Validate the Launch API Details withInvalidHeader value")
    public void TC_03_ValidateResponseOfLaunchAPI_withInvalidHeader() throws Throwable {
        logger.info("validate the recent launch details with invalid params");

        String headerArray[] = headerPayload.split(":");
        Response response = requestSpecification
                .headers("Host", headerArray[0] ).get(URI_LaunchDetails);
        String expRespbod=response.toString();
        System.out.println(URI_LaunchDetails);
        int statusCode = response.getStatusCode();
        // Object expStatusCode=(Object)statusCode;
        String userResponsePayload = response.asString();
        logger.info("Status code of Response---------------------" + statusCode);
        logger.info("Output of Response---------------------" + userResponsePayload);
        String responseAsString = response.getBody().asString();
        logger.info("Launch validated succesfully");
        Assert.assertEquals(statusCode, 400);

        logger.info(">>>>>>>>>>>>>>>END of Execution for test case =" + methodName
                + ">>>>>>>>>>>>");
    }
}
