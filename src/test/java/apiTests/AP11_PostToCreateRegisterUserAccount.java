package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestDataHolder;
import static io.restassured.RestAssured.given;

public class AP11_PostToCreateRegisterUserAccount {
    /*
        API 11: POST To Create/Register User Account
        API URL: https://automationexercise.com/api/createAccount
        Request Method: POST
        Request Parameters: name, email, password, title (for example: Mr, Mrs, Miss), birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, zipcode, state, city, mobile_number
        Response Code: 201
        Response Message: User created!
     */

    @Test
    public void postToCreateRegisterUserAccount() {
        String url = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("createAccountEndpoint");
        String name = ConfigReader.getProperty("name");
        String lastname = ConfigReader.getProperty("lastname");
        String email = "testuser" + System.currentTimeMillis() + "@mail.com";
        String password = ConfigReader.getProperty("password");
        String title = "Mr";
        String birthDate = "15";
        String birthMonth = "05";
        String birthYear = "1990";
        String firstname = "John";
        String company = "Test Company";
        String address1 = "Test Address 1";
        String address2 = "Test Address 2";
        String country = "Turkey";
        String zipcode = "34000";
        String state = "Istanbul";
        String city = "Istanbul";
        String mobileNumber = "5551234567";
        String expectedMessage = ConfigReader.getProperty("expectedUserCreatedMessage");

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
                .post(url);

        String responseBody = response.getBody().asString();
        JsonPath json = new JsonPath(responseBody);
        Assert.assertEquals(json.getInt("responseCode"), 201);
        Assert.assertEquals(json.getString("message"), expectedMessage);

        // TestDataHolder ile diğer testlerde kullanılacak bilgileri sakla
        TestDataHolder.lastCreatedEmail = email;
        TestDataHolder.lastCreatedPassword = password;
        TestDataHolder.lastCreatedName = name;
        TestDataHolder.lastCreatedLastname = lastname;
        TestDataHolder.lastCreatedTitle = title;
        TestDataHolder.lastCreatedBirthDate = birthDate;
        TestDataHolder.lastCreatedBirthMonth = birthMonth;
        TestDataHolder.lastCreatedBirthYear = birthYear;
        TestDataHolder.lastCreatedFirstname = firstname;
        TestDataHolder.lastCreatedCompany = company;
        TestDataHolder.lastCreatedAddress1 = address1;
        TestDataHolder.lastCreatedAddress2 = address2;
        TestDataHolder.lastCreatedCountry = country;
        TestDataHolder.lastCreatedZipcode = zipcode;
        TestDataHolder.lastCreatedState = state;
        TestDataHolder.lastCreatedCity = city;
        TestDataHolder.lastCreatedMobileNumber = mobileNumber;
    }
}
