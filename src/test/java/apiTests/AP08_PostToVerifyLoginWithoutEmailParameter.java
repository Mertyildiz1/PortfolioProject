package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;

public class AP08_PostToVerifyLoginWithoutEmailParameter {

    /*
        API 8: POST To Verify Login without email parameter
        API URL: https://automationexercise.com/api/verifyLogin
        Request Method: POST
        Request Parameter: password
        Response Code: 400
        Response Message: Bad request, email or password parameter is missing in POST request.
     */

    @Test
    public void postToVerifyLoginWithoutEmailParameter() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("verifyLoginEndpoint");
        String password = ConfigReader.getProperty("password");

        String expectedMessage = ConfigReader.getProperty("expectedMissingEmailOrPasswordMessage");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", password)
                .when()
                .post(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 400);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
