package Testng1.Testng1;

import org.testng.annotations.Test;

public class groupdemo {
	
	
	
	
	@Test(groups = "login")
	public void data1()
	{
		System.out.println("Login1");
	}
	
	@Test(groups = "login")
	public void data2()
	{
		System.out.println("Login2");
	}
	
	@Test(groups = "rest")
	public void data3()
	{
		System.out.println("reset1");
	}
	@Test(groups = "rest")
	public void data4()
	{
		System.out.println("reset2");
	}

}
