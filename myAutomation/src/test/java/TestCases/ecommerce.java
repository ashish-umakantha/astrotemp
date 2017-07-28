package TestCases;

import org.testng.annotations.Test;
import CommonResources.ReadPropertyFile;
import CommonResources.ecommerceLogic;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ecommerce {
	public WebDriver driver;
	public ReadPropertyFile data;
	public ecommerceLogic method;
	  
  @Test
  public void ecommerceCase() throws Exception 
  {
	  method = new ecommerceLogic();
//	  method.flipkart(driver);
//	  method.shopclues(driver);
	  method.outputData(driver);
  }
  
  @BeforeClass
  public void beforeClass() throws Exception {
	  driver = new ChromeDriver();
	  data = new ReadPropertyFile();
  }
  
}
