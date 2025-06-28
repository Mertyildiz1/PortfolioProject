package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int maxRetryCount = 3;
    private static final Map<String, Integer> retryCountMap = new HashMap<>();
    
    @Override
    public boolean retry(ITestResult iTestResult) {
        String testName = iTestResult.getName();
        int currentRetryCount = retryCountMap.getOrDefault(testName, 0);
        
        if (currentRetryCount < maxRetryCount) {
            retryCountMap.put(testName, currentRetryCount + 1);
            return true;
        }
        
        retryCountMap.remove(testName);
        return false;
    }
} 