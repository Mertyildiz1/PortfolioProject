package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import utilities.ConfigReader;

public class AP03_GetAllBrandsList {

    /*
        API 3: Get All Brands List
        API URL: https://automationexercise.com/api/brandsList
        Request Method: GET
        Response Code: 200
        Response JSON: All brands list

     */

    @Test
    public void getAllBrandsList() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("brandsListEndpoint");

        Response response = given().when().get(url);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("brands", notNullValue())
                .body("brands.size()", greaterThan(0));
    }
}
