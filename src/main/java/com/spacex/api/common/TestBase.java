package com.spacex.api.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spacex.api.enums.Endpoints;
import com.spacex.api.pojo.Core;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class TestBase {
    private static final Logger logger= Logger.getLogger(String.valueOf(TestBase.class));
public Response response;
public Gson gson=new GsonBuilder().create();
public String File_TestData= "C:\\Users\\Admin\\IdeaProjects\\SpacexAPI\\src\\main\\resources\\Spacex_Data.xlsx";

public String URI_LaunchDetails;

public Core obj;

public RequestSpecification requestSpecification;
@BeforeMethod(alwaysRun=true)
public void beforeTestMethodTestBase(Method testMethod )
{
    requestSpecification= RestAssured.given();
    URI_LaunchDetails = Endpoints.LATEST_LAUNCH_DETAILS.getResourcePath();
}
    @AfterMethod(alwaysRun = true)
    public void afterTestMethodTestBase(Method testMethod){
    logger.info("*************Test execution COMPLETED for test case" +testMethod.getName() +" ****************");
    }
@BeforeTest
    public void loadlog4j(){
    String log4jConfPath = System.getProperty("user.dir");
}

}
