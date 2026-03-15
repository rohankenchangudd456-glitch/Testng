package listerner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Itestlisterenedemo implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("Test case started");
		
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("login Test case started");
		
	}

	

	@Override
	public void onStart(ITestContext context) {
		System.out.println("login Test case passed");
		
	}
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("login Test case completed");
		
	}
	
	
	

}
