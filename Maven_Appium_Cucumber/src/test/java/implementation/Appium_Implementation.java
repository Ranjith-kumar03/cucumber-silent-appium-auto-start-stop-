package implementation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class Appium_Implementation {
	private  AndroidDriver<MobileElement> driver;
	private  WebElement element;

	static String NodePath="C:\\Program Files\\nodejs\\node.exe";
	static String AppiumMainJS_path="C:\\Program Files (x86)\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	static AppiumDriverLocalService service;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");
	
	
	public void Mobile_Application() throws MalformedURLException
	{
		System.out.println("Iam in mobile");
		DesiredCapabilities cap =new DesiredCapabilities();
		cap.setCapability("deviceName", "Ranjith");
		cap.setCapability("udid", "H1AXHM03E676X4Z");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.1.1");
		
		//App Information
		
		cap.setCapability("appPackage", "com.asus.calculator");
		
		cap.setCapability("appActivity", "com.asus.calculator.Calculator");
		
		//cap.setCapability("appActivity", "com.accuweather.core.CardViewPager");
		//cap.setCapability("appActivity", "com.amazon.mShop.android.home.PublicUrlActivity");
		//cap.setCapability("appActivity", "com.amazon.mShop.youraccount.YourAccountActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4728/wd/hub"),cap);
		
		
	}
	
	public void start_server() throws InterruptedException, MalformedURLException
	{
		service= AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(NodePath))
				 .withAppiumJS(new File(AppiumMainJS_path))
	             .withIPAddress("127.0.0.1")
	             .usingPort(4728)
	             .withLogFile(new File("C:\\Users\\Ranjith\\AppData\\Roaming\\Appium\\logs\\AppiumLog.txt")));
		
		System.out.println("Starting theA Appium Server -------"+"\n"+sdf.format(new Date()));
		
		service.start();
		Thread.sleep(10000);
		Mobile_Application();
	}
	
	public void Test_Server()
	{
		System.err.println("The URL is : "+service.getUrl().toString());
		System.err.println("Is server Running : "+service.isRunning());
		
	}
	
	public void Stop_Server()
	{
		if(service.isRunning()==true)
		{
			service.stop();
			System.out.println("Stoping  the Appium Server -------"+"\n"+sdf.format(new Date()));
		}
	}
	@Given("^Auto Appium start$")
	public void auto_Appium_start() throws Throwable {
	    
		
	start_server();
	Test_Server();
	
	}
	@Then("find number two")
	public void find_number_two() {
		System.out.println("Iam out of mobile");
		element =driver.findElementById("com.asus.calculator:id/digit2");
		element.click();
	   
	}

	@Then("find plus sign")
	public void find_plus_sign() {
	   
		element=driver.findElementById("com.asus.calculator:id/plus");
		element.click();
	}

	@Then("find numberfour")
	public void find_numberfour() {
		element=driver.findElementById("com.asus.calculator:id/digit4");
		element.click();
	}

	@Then("click equal to")
	public void click_equal_to() {
		element=driver.findElementById("com.asus.calculator:id/equal");
		element.click();
	}

	@Then("find results in text bar")
	public void find_results_in_text_bar() {
		element=driver.findElementById("com.asus.calculator:id/resultEditTextID");
		System.out.println("the addition of 2 and 4 is--"+element);
	    
	}
	
	@Then("^Stop Appium Server$")
	public void stop_Appium_Server() throws Throwable {
		
		Stop_Server();
	   
	}
	
}
