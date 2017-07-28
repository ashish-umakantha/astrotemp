package CommonResources;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Locators.flipkart;
import Locators.shopclues;

public class ecommerceLogic 
{
	public ReadPropertyFile data;
	public flipkart locators;
	public shopclues locators1;
	
	//Method to get Prices of iPhone 7 from Flipkart site
	public String[] getFlipkartPrices(WebDriver driver) throws Exception
	{
		ArrayList<WebElement> phonename = new ArrayList<WebElement>();
		ArrayList<WebElement> phoneprice = new ArrayList<WebElement>();
		ArrayList<WebElement> link = new ArrayList<WebElement>();
		StringBuilder build = new StringBuilder();
		locators = new flipkart(driver);
		data = new ReadPropertyFile();
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.navigate().to(data.getEcommerceUrl1());
		try{
		if(driver.switchTo().alert() != null)
		{
		    driver.switchTo().alert();
		    locators.login_pop_up_close.click();
		}
		}catch(Exception e){}
		assertTrue(locators.flipkart_text_box.isDisplayed(), "Flipkart text box is not seen");
		assertTrue(locators.flipkart_search_btn.isDisplayed(), "Flipkart serach btn is not seen");
		locators.flipkart_text_box.sendKeys(data.getSearchText());
		locators.flipkart_search_btn.click();
		wait.until(ExpectedConditions.visibilityOf(locators.phone_names));
		assertTrue(locators.phone_names.isDisplayed(), "Phone names not dispalyed");
		phonename.addAll(driver.findElements(By.xpath("//a[@class='_1UoZlX']/div[2]/div[1]/div[1]")));
		phoneprice.addAll(driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']")));
		link.addAll(driver.findElements(By.xpath("//a[@class='_1UoZlX']")));
		String array[] = new String[phonename.size()];
		for(int i=0;i<phonename.size();i++)
		{
			WebElement e3 = phonename.get(i);
			String text = e3.getText();
			WebElement e4 = phoneprice.get(i);
			//removing , from price
			String price = e4.getText();
			for(int l=0;l<=price.length()-1;l++)
			{
				if(price.charAt(l)==',')
				{
					continue;
				}
				else
				{
					build.append(price.charAt(l));
				}
			}
			String actualprice = build.toString();
			build.setLength(0);			
			String url=link.get(i).getAttribute("href");
			if(!text.contains("Plus")&&text.contains("Apple iPhone 7"))
			{
				build.append(actualprice + " ");
				build.append(text + " ");
				build.append("flipkart ");
				build.append("Link: " + url);
				String details = build.toString();
				array[i] = details;
			}
			build.setLength(0);
		}
		//Removing null items from the array
		ArrayList<String> all = new ArrayList<String>();
		for(int i=0;i<=array.length-1;i++)
		{
			if(array[i]!=null)
			{
				all.add(array[i]);
			}
		}
		//Adding contents of arraylist into new array
		String [] array2 = new String[all.size()];
		for(int i=0; i<all.size();i++)
		{
			array2[i] = all.get(i);
		}
		driver.close();
		return array2;
	}
	
	//Method to get Prices of iPhone 7 from Shopclues site
	public String[] getShopcluesPrices(WebDriver driver) throws Exception
	{
		driver = new ChromeDriver();
		ArrayList<WebElement> phonename = new ArrayList<WebElement>();
		ArrayList<WebElement> phoneprice = new ArrayList<WebElement>();
		ArrayList<WebElement> link = new ArrayList<WebElement>();
		StringBuilder build = new StringBuilder();
		locators1 = new shopclues(driver);
		data = new ReadPropertyFile();
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.navigate().to(data.getEcommerceUrl2());
		assertTrue(locators1.shopclues_text_box.isDisplayed(), "Shopclues text box is not seen");
		assertTrue(locators1.shopclues_search_btn.isDisplayed(), "Shopclues serach btn is not seen");
		locators1.shopclues_text_box.sendKeys(data.getSearchText());
		locators1.shopclues_search_btn.click();
		wait.until(ExpectedConditions.visibilityOf(locators1.shopclues_phone));
		assertTrue(locators1.shopclues_phone.isDisplayed(), "Phone names not dispalyed");
		phonename.addAll(driver.findElements(By.xpath(".//*[@id='product_list']/div/div/a/h3")));
		phoneprice.addAll(driver.findElements(By.xpath(".//span[@class='p_price']")));
		link.addAll(driver.findElements(By.xpath(".//*[@id='product_list']/div/div/a")));
		String array[]=new String[phonename.size()];
		for(int i=0;i<phonename.size();i++)
		{
			WebElement e3 = phonename.get(i);
			String text = e3.getText();
			WebElement e4=phoneprice.get(i);
			//removing , from price
			String price=e4.getText();
			for(int l=0;l<=price.length()-1;l++)
			{
				if(price.charAt(l)==',')
				{
					continue;
				}
				else
				{
					build.append(price.charAt(l));
				}
			}
			String actualprice = build.toString();
			build.setLength(0);
			String url = link.get(i).getAttribute("href");
			if(!text.contains("PLUS")&&text.contains("Apple iPhone 7"))
			{
				build.append(actualprice + " ");
				build.append(text + " ");
				build.append("Shopclues ");
				build.append("Link: " + url);
				String details=build.toString();
				array[i]=details;
			}
			build.setLength(0);
		}
		//Removing null items from the array
		ArrayList<String> all = new ArrayList<String>();
		for(int i=0;i<=array.length-1;i++)
		{
			if(array[i]!=null)
			{
				all.add(array[i]);
			}
		}
		//Adding contents of arraylist into new array
		String [] array2 = new String[all.size()];
		for(int i=0; i<all.size();i++)
		{
			array2[i] = all.get(i);
//			System.out.println(array2[i]);
		}
		driver.close();
		return array2;
//		sort(array2);
	}
	
	//Method to display the output of devices
	public void outputData(WebDriver driver) throws Exception
	{
		String [] fullList = null;
		String [] flipkart = getFlipkartPrices(driver);
		String [] shopclues = getShopcluesPrices(driver);
		fullList = ArrayUtils.addAll(flipkart,shopclues);
		for (int i=0;i<=fullList.length-1;i++)
		{
			System.out.println(fullList[i]);
		}
	}
	
	//Method for sorting the list by prices
	public void sort(String arr[])
	{
		StringBuilder sb=new StringBuilder();
		ArrayList<Integer> al=new ArrayList<Integer>();		
		for(int i=0;i<=arr.length-1;i++)
		{
			//convert string at arr[i] into char array
			char arr1[]=arr[i].toCharArray();
			for(int j=0;j<=arr1.length-1;j++)
			{
				//check for alphabets and append
				if(Character.isDigit(arr1[j]))
				{
					sb.append(arr1[j]);
				}
				//break when blank is found
				if (arr1[j]==' ')
				{
					break;
				}
				if (arr1[j]=='.')
				{
					break;
				}
			}
			//convert string builder back to string
			String str=sb.toString();
			//convert string str to integer
			int num=Integer.parseInt(str);
			//add numbers to array list
			al.add(num);
			//set string builder to length 0 after adding number to arraylist for next iteration
			sb.setLength(0);
		}
		//sort array list in ascending order
		Collections.sort(al);
		//create new string array of size of arraylist
		String array[] = new String[al.size()];
		//enter values into string array from arraylist
		for(int i=0;i<al.size();i++)
		{
			array[i]=al.get(i).toString();
		}
		System.out.println("Printing in sorted order :");
		//now compare against the sorted string array against unsorted string and print
		for(int i=0;i<=array.length-1;i++)
		{
			for(int j=0;j<=arr.length-1;j++)
			{
				if(arr[j].contains(array[i]))
				{
				System.out.println(arr[j]);
				break;
				}	
			}
		}
	}
}
