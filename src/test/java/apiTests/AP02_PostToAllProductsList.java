package apiTests;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import utilities.ConfigReader;

public class AP02_PostToAllProductsList {

    /*
        API 2: POST To All Products List
        API URL: https://automationexercise.com/api/productsList
        Request Method: POST
        Response Code: 405
        Response Message: This request method is not supported.
     */

    @Test
    public void postToAllProductsList() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("productsListEndpoint");
        String expectedMessage = ConfigReader.getProperty("expectedMethodNotSupportedMessage");

        Response response = given().when().post(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 405);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
