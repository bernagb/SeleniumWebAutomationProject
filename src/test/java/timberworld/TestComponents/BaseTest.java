package timberworld.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public HomePage homepage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
	
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"): prop.getProperty("browser");
	
			if(browserName.contains("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")) {
					options.addArguments("headless");}
					driver = new ChromeDriver(options);
					
					Dimension dimension = new Dimension(1440,900);
					
					driver.manage().window().setSize(dimension);
			}
			else if (browserName.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "C:/Users/Gökçe/SeleniumGrid/geckodriver.exe");
				driver = new FirefoxDriver();
			}
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	@BeforeMethod(alwaysRun=true)
	public HomePage launchApplication() throws IOException
	{
		driver = initializeDriver();
		homepage = new HomePage(driver);
		homepage.goTo();
		return homepage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to Hashmap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	public String getScreenshot(String testCase, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCase+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\"+testCase+".png";
		
	}
}
