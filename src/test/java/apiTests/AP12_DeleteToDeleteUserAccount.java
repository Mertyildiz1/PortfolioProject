package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestDataHolder;
import static io.restassured.RestAssured.given;

public class AP12_DeleteToDeleteUserAccount {
    /*
        API 12: DELETE METHOD To Delete User Account
        API URL: https://automationexercise.com/api/deleteAccount
        Request Method: DELETE
        Request Parameters: email, password
        Response Code: 200
        Response Message: Account deleted!
     */

    @Test(dependsOnMethods = "apiTests.AP11_PostToCreateRegisterUserAcc.postToCreateRegisterUserAccount")
    public void deleteToDeleteUserAccount() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("deleteAccountEndpoint");
        String email = TestDataHolder.lastCreatedEmail;
        String password = TestDataHolder.lastCreatedPassword;
        String expectedMessage = ConfigReader.getProperty("expectedAccountDeletedMessage");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 200);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
