package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;

public class AP13_PutToUpdateUserAcc {
    /*
        API 13: PUT METHOD To Update User Account
        API URL: https://automationexercise.com/api/updateAccount
        Request Method: PUT
        Request Parameters: name, email, password, title (for example: Mr, Mrs, Miss), birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, zipcode, state, city, mobile_number
        Response Code: 200
        Response Message: User updated!
     */

    @Test
    public void putToUpdateUserAccount() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("updateAccountEndpoint");
        String email = ConfigReader.getProperty("mail");
        String password = ConfigReader.getProperty("password");
        String name = ConfigReader.getProperty("name");
        String title = "Mr";
        String birthDate = "15";
        String birthMonth = "05";
        String birthYear = "1990";
        String firstname = "John";
        String lastname = "Doe";
        String company = "Test Company";
        String address1 = "Test Address 1";
        String address2 = "Test Address 2";
        String country = "Turkey";
        String zipcode = "34000";
        String state = "Istanbul";
        String city = "Istanbul";
        String mobileNumber = "5551234567";
        String expectedMessage = ConfigReader.getProperty("expectedUserUpdatedMessage");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", title)
                .formParam("birth_date", birthDate)
                .formParam("birth_month", birthMonth)
                .formParam("birth_year", birthYear)
                .formParam("firstname", firstname)
                .formParam("lastname", lastname)
                .formParam("company", company)
                .formParam("address1", address1)
                .formParam("address2", address2)
                .formParam("country", country)
                .formParam("zipcode", zipcode)
                .formParam("state", state)
                .formParam("city", city)
                .formParam("mobile_number", mobileNumber)
                .when()
                .put(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);

        Assert.assertEquals(json.getInt("responseCode"), 200);
        Assert.assertEquals(json.getString("message"), expectedMessage);
    }
}
