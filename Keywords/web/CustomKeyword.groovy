package web

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.image.BufferedImage
import java.text.SimpleDateFormat

import javax.imageio.ImageIO

import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.testng.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
/*import ru.yandex.qatools.ashot.AShot
 import ru.yandex.qatools.ashot.Screenshot
 import ru.yandex.qatools.ashot.comparison.ImageDiff
 import ru.yandex.qatools.ashot.comparison.ImageDiffer
 import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
 */
public class CustomKeyword {


	public static WebDriver driver = DriverFactory.getWebDriver();

	static FileWriter filewriter = new FileWriter(System.getProperty("user.dir")+"\\Test Output\\BrokenlinksWithStatus.txt");

	static BufferedWriter bw = new BufferedWriter(filewriter);

	/*@Keyword
	 public void getLogin(String userName, String password){
	 }*/

	@Keyword
	public static String randomPhoneNumberGenerator(){

		String phoneNo = null;

		String temp = RandomStringUtils.random(11, "12345678901234567890");

		String numbers = temp.substring(9, temp.length());

		phoneNo=9+numbers;

		println phoneNo;

		return phoneNo;
	}


	@Keyword
	public static String randomEmailIDGenerator(){

		String emailID = null;

		String tempemail =  RandomStringUtils.random(15,"abcdefghijklmnopqrstuvwxyz")

		String email = tempemail.substring(10, tempemail.length());

		return email+"@yopmail.com"
	}

	@Keyword
	public static String getTitle(){

		return driver.getTitle();
	}


	/*@Keyword
	 public static void takeScreenShot(){
	 Calendar calendar = Calendar.getInstance();
	 SimpleDateFormat simpleformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	 Screenshot screen =new AShot().takeScreenshot(driver);
	 ImageIO.write(screen.getImage(), "PNG", new File(System.getProperty("user.dir")+"\\Test Output\\"+simpleformat.format(calendar.getTime())));
	 ImageDiffer imagediffer = new ImageDiffer();
	 ImageDiff imagediff = imagediffer.makeDiff(screen, screen)
	 }
	 */



	@Keyword
	public static void isBrokenLinkchecker(URL url){

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();




		KeywordLogger log = new KeywordLogger();

		String message = null;

		Thread.sleep(300);

		try{
			//File file = new File("C:\\Users\\Dell\\Katalon Studio\\SampleTestProject\\TestData\\BrokenlinksWithStatus.txt");



			conn.connect();

			message = conn.getResponseMessage();
			println "--------------------------------------------------------------------------"
			println "The URL is : "+url+ " and the message is : "+message+" Status Code is : "+conn.getResponseCode();
			log.logInfo("The URL is : "+url+ " and the message is : "+message+" Status Code is : "+conn.getResponseCode());
			bw.write("The URL is : "+url+ " and the message is : "+message+" Status Code is : "+conn.getResponseCode());
			bw.newLine();
			bw.flush();


			println "--------------------------------------------------------------------------"
			conn.disconnect();
		}
		catch(Exception e){

			println e.getMessage();
		}

		bw.close();
	}
	/*
	 @Keyword
	 public static void CompareBothImages(TestObject testObjectoftheimage, String pathoftheimage){
	 try{
	 BufferedImage actualImag = ImageIO.read(new File(pathoftheimage))
	 Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, WebUiCommonHelper.findWebElement(testObjectoftheimage, 0));
	 BufferedImage bfrimage = screen.getImage();
	 ImageDiffer imgdiffer = new ImageDiffer();
	 ImageDiff imgdiff = imgdiffer.makeDiff(bfrimage, actualImag);
	 Assert.assertTrue(imgdiff.hasDiff(), "Both Images are matched");
	 }
	 catch(Exception e){
	 println "Exception : "+e.getMessage()
	 }
	 }
	 */
	@Keyword
	public boolean checkListAscendingOrder(List<Object> listOfString){

		println listOfString
		List<Object> tempString = new ArrayList<Object>(listOfString);
		println tempString
		Collections.sort(tempString);
		println tempString
		return tempString.equals(listOfString);
	}


	@Keyword
	public boolean checkListDescendingOrder(List<Object> listOfString, List<Object> listOfStringWithDesc){

		List<Object> tempString = new ArrayList<Object>(listOfStringWithDesc);

		Collections.sort(listOfString, Collections.reverseOrder());

		return tempString.equals(listOfString);
	}

	@Keyword
	public boolean checkListDescending(List<String> listOfString){

		List<String> tempString = new ArrayList<String>(listOfString);

		Collections.sort(tempString, Collections.reverseOrder());

		return tempString.equals(listOfString);
	}

	@Keyword
	public static String getrandomStingValue(List<String> listOfString){

		Random random = new Random();

		//return listOfString.get(random.nextInt(listOfString.size()))
		return listOfString.get(random.nextInt(3))
	}

	// Used to get cookies domain.
	@Keyword
	public static String getCookiesValue(){


		Set<Cookie> cookies = driver.manage().getCookies();

		String domainname;

		for(Cookie cookie : cookies){

			println cookie.getName()+"--"+cookie.getValue()+"--"+cookie.getDomain();

			domainname = cookie.getDomain();
		}

		return domainname;
	}

	@Keyword
	public boolean selectDropDownValue(List<WebElement> webelements, String value){

		for(int i=0; i<webelements.size();i++){
			if(value.equalsIgnoreCase(webelements.get(i).getText())){
				webelements.get(i).click();
				return true;
			}
		}
		return false;
	}

	@Keyword
	public String getRandomString(){

		return RandomStringUtils.randomAlphabetic(100)

	}

	@Keyword
	public String getRandomName(String name){

		int number = new Random().nextInt(1000)
		return name+number;
	}

	@Keyword
	public String getTimeStampWithText(String text){

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		return text+formater.format(cal.getTime())


	}
}
