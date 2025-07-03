package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import utilities.ConfigReader;

public class AP05_PostToSearchProduct {

    /*
        API 5: POST To Search Product
        API URL: https://automationexercise.com/api/searchProduct
        Request Method: POST
        Request Parameter: search_product (For example: top, tshirt, jean)
        Response Code: 200
        Response JSON: Searched products list
     */

    @Test
    public void postToSearchProduct() {

        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("searchProductEndpoint");
        String searchProduct = ConfigReader.getProperty("searchProduct");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product", searchProduct)
                .when()
                .post(url);


        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0));
    }
}
