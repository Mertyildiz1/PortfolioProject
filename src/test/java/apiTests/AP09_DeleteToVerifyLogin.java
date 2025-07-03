package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;

public class AP09_DeleteToVerifyLogin {
    /*
        API 9: DELETE To Verify Login
        API URL: https://automationexercise.com/api/verifyLogin
        Request Method: DELETE
        Response Code: 405
        Response Message: This request method is not supported.
     */

    @Test
    public void deleteToVerifyLogin() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("verifyLoginEndpoint");

        String expectedMessage = ConfigReader.getProperty("expectedMethodNotSupportedMessage");

        Response response = given()
                .when()
                .delete(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 405);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
