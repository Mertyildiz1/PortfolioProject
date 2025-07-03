package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;

public class AP10_PostToVerifyLoginWithInvalidDetails {

    /*
        API 10: POST To Verify Login with invalid details
        API URL: https://automationexercise.com/api/verifyLogin
        Request Method: POST
        Request Parameters: email, password (invalid values)
        Response Code: 404
        Response Message: User not found!
     */
@Test
public void postToVerifyLoginWithInvalidDetails() {
    String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("verifyLoginEndpoint");
    String invalidEmail = ConfigReader.getProperty("invalidLoginMail");
    String invalidPassword = ConfigReader.getProperty("invalidPassword");

    String expectedMessage = ConfigReader.getProperty("expectedUserNotFoundMessage");

    Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("email", invalidEmail)
            .formParam("password", invalidPassword)
            .when()
            .post(url);

    String responseBody = response.getBody().asString();
    JsonPath json = new JsonPath(responseBody);

    Assert.assertEquals(json.getInt("responseCode"), 404);
    Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
