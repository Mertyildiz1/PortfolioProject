package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import utilities.ConfigReader;

public class AP06_PostToSearchProductWithoutParameter {

    /*
        API 6: POST To Search Product without search_product parameter
        API URL: https://automationexercise.com/api/searchProduct
        Request Method: POST
        Response Code: 400
        Response Message: Bad request, search_product parameter is missing in POST request.
     */

    @Test
    public void postToSearchProductWithoutParameter() {

        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("searchProductEndpoint");
        String expectedMessage = ConfigReader.getProperty("expectedMissingParamMessage");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 400);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
