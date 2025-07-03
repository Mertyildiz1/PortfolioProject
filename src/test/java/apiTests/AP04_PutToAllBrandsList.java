package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class AP04_PutToAllBrandsList {

    /*
        API 4: PUT To All Brands List
        API URL: https://automationexercise.com/api/brandsList
        Request Method: PUT
        Response Code: 405
        Response Message: This request method is not supported.

     */

    @Test
    public void PutToAllBrandsList() {

        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("brandsListEndpoint");
        String expectedMessage = ConfigReader.getProperty("expectedMethodNotSupportedMessage");

        Response response = given().when().put(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 405);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
