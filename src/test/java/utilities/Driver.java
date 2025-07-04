package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {
    private Driver(){
    }

    static WebDriver driver;
    public static WebDriver getDriver(){
        if (driver==null){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;

    }

    public static void closeDriver(){
        if (driver!=null){
            driver.close();
            driver=null;
        }
    }

    public static void quitDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }
}