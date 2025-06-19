package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "invalidEmailData")
    public static Object[][] invalidEmailData() {
        return new Object[][]{
                {"john>@gmail.com"},
                {"john<@gmail.com"},
                {"john£@gmail.com"},
                {"john½@gmail.com"},
                {"john[@gmail.com"},
                {"john]@gmail.com"},
                {"john @gmail.com"},
                {"john(@gmail.com"},
                {"john)@gmail.com"}
        };
    }
} 