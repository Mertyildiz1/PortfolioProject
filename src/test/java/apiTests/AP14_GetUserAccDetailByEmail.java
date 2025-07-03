package apiTests;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestDataHolder;
import static io.restassured.RestAssured.given;

public class AP14_GetUserAccDetailByEmail {
    /*
        API 14: GET user account detail by email
        API URL: https://automationexercise.com/api/getUserDetailByEmail
        Request Method: GET
        Request Parameters: email
        Response Code: 200
        Response JSON: User Detail
        (Kullanıcı bilgileri AP11'de oluşturulan hesaptan alınacak)
     */

    @Test(dependsOnMethods = "apiTests.AP11_PostToCreateRegisterUserAcc.postToCreateRegisterUserAccount")
    public void getUserAccountDetailByEmail() {

        String detailUrl = ConfigReader.getProperty("apiBaseUrl") + ConfigReader.getProperty("getUserDetailByEmailEndpoint");
        String email = TestDataHolder.lastCreatedEmail;

        Response detailResponse = given()
                .queryParam("email", email)
                .when()
                .get(detailUrl);

        String detailBody = detailResponse.getBody().asString();
        System.out.println("User detail response: " + detailBody);
        JsonPath detailJson = new JsonPath(detailBody);

        // AP11'de oluşturulan bilgilerle karşılaştır
        Assert.assertEquals(detailJson.getString("user.name"), TestDataHolder.lastCreatedName);
        Assert.assertEquals(detailJson.getString("user.email"), TestDataHolder.lastCreatedEmail);
        Assert.assertEquals(detailJson.getString("user.title"), TestDataHolder.lastCreatedTitle);
        Assert.assertEquals(detailJson.getString("user.birth_day"), TestDataHolder.lastCreatedBirthDate);
        Assert.assertEquals(detailJson.getString("user.birth_month"), TestDataHolder.lastCreatedBirthMonth);
        Assert.assertEquals(detailJson.getString("user.birth_year"), TestDataHolder.lastCreatedBirthYear);
        Assert.assertEquals(detailJson.getString("user.first_name"), TestDataHolder.lastCreatedFirstname);
        Assert.assertEquals(detailJson.getString("user.last_name"), TestDataHolder.lastCreatedLastname);
        Assert.assertEquals(detailJson.getString("user.company"), TestDataHolder.lastCreatedCompany);
        Assert.assertEquals(detailJson.getString("user.address1"), TestDataHolder.lastCreatedAddress1);
        Assert.assertEquals(detailJson.getString("user.address2"), TestDataHolder.lastCreatedAddress2);
        Assert.assertEquals(detailJson.getString("user.country"), TestDataHolder.lastCreatedCountry);
        Assert.assertEquals(detailJson.getString("user.zipcode"), TestDataHolder.lastCreatedZipcode);
        Assert.assertEquals(detailJson.getString("user.state"), TestDataHolder.lastCreatedState);
        Assert.assertEquals(detailJson.getString("user.city"), TestDataHolder.lastCreatedCity);

    }
}
