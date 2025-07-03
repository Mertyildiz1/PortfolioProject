package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AP01_GetAllProductsList {

    @Test
    public void getAllProductsList() {

        /*
            API 1: Get All Products List
            API URL: https://automationexercise.com/api/productsList
            Request Method: GET
            Response Code: 200
            Response JSON: All products list
         */

        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("productsListEndpoint");

        Response response = given().when().get(url);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0));
    }
} 