package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test başlıyor: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test başarılı: " + result.getName());
        Driver.closeDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test başarısız: " + result.getName());
        Driver.closeDriver();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test atlandı: " + result.getName());
        Driver.closeDriver();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Tüm testler tamamlandı");
        Driver.quitDriver();
    }
} 