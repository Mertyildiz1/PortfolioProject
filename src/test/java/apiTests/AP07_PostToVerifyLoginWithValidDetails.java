package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.Assert;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AP07_PostToVerifyLoginWithValidDetails {

    /*
        API 7: POST To Verify Login with valid details
        API URL: https://automationexercise.com/api/verifyLogin
        Request Method: POST
        Request Parameters: email, password
        Response Code: 200
        Response Message: User exists!
     */

    @Test
    public void postToVerifyLoginWithValidDetails() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("verifyLoginEndpoint");
        String email = ConfigReader.getProperty("mail");
        String password = ConfigReader.getProperty("password");

        String expectedMessage = ConfigReader.getProperty("expectedUserExistsMessage");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 200);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
