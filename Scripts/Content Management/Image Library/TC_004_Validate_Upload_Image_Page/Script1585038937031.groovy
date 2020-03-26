import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ById as ById
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('PlatformNavigation/Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Dashboard/contentmeu'))

WebUI.click(findTestObject('Content Management/Login_Page/Dashboard/Content Sub-Navigation/div_Images'))

WebUI.delay(5)

WebUI.click(findTestObject('Content Management/CONTENT/Image_Listing/upload_image_button'))

WebUI.verifyElementText(findTestObject('Content Management/CONTENT/Upload_Images/h1_Upload Images_Header_Text'), 'Upload')

// Upload Image Name is auto generated:

String img_name = CustomKeywords.'web.CustomKeyword.getRandomName'('Automation')

print(img_name)

WebUI.setText(findTestObject('Content Management/CONTENT/Upload_Images/input_Image Name'), img_name)

WebUI.click(findTestObject('Content Management/CONTENT/Upload_Images/button_Add Description'))

WebUI.setText(findTestObject('Content Management/CONTENT/Upload_Images/textarea_Description_description'), CustomKeywords.'web.CustomKeyword.getRandomString'())

not_run: WebUI.click(findTestObject('Content Management/CONTENT/Upload_Images/label_Select Files'))

WebDriver dr = DriverFactory.getWebDriver()

List<WebElement> listofFileUpload = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Upload_Images/label_Select Files'), 
    0)

for (int i = 0; i < listofFileUpload.size(); i++) {
    String id = listofFileUpload.get(i).getAttribute('id')

    WebElement ele = dr.findElement(By.id(id))

    ele.sendKeys(((System.getProperty('user.dir') + '\\Test Files\\img') + (i + 1)) + '.jpg')
}

WebUI.verifyElementText(findTestObject('Content Management/CONTENT/Upload_Images/label_Replace'), 'Replace')

WebUI.click(findTestObject('Content Management/CONTENT/Upload_Images/language_DropDown'))

List<WebElement> dropdownlist = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Upload_Images/Select_Language_Options'), 
    0)

CustomKeywords.'web.CustomKeyword.selectDropDownValue'(dropdownlist, 'English')

List<WebElement> radiobuttonsforMarketing = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Upload_Images/maketing_categories'), 
    0)

CustomKeywords.'web.CustomKeyword.selectDropDownValue'(radiobuttonsforMarketing, 'Milestone')

WebUI.click(findTestObject('Content Management/CONTENT/Upload_Images/button_Upload'))

WebUI.waitForElementPresent(findTestObject('Content Management/CONTENT/Image_Listing/all_Image_textwithcount'), 10)

List<WebElement> lisOFImagename = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Image_Listing/colum1_Image_Name_values'), 
    0)

boolean isUpload = CustomKeywords.'web.CustomKeyword.selectDropDownValue'(lisOFImagename, img_name)

WebUI.verifyEqual(isUpload, true)

