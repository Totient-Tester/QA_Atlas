import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.Keys as Keys
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

not_run: WebUI.callTestCase(findTestCase('PlatformNavigation/Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Dashboard/contentmeu'))

WebUI.click(findTestObject('Content Management/Login_Page/Dashboard/Content Sub-Navigation/div_Images'))

WebUI.click(findTestObject('Content Management/CONTENT/Image_Listing/all_Image_textwithcount'))

WebUI.setText(findTestObject('Content Management/CONTENT/Image_Listing/image_search_box'), 'image' + Keys.ENTER)

WebUI.delay(5)

String text = WebUI.getText(findTestObject('Content Management/CONTENT/Image_Listing/showingtextforpagination'))

int resultfound_count = Integer.parseInt(((text.split('of')[1]).split(' ')[1]).trim())

List<WebElement> elements = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Image_Listing/column1_Image_Name_Values'), 
    0)

int pageValueCount = elements.size()

if (resultfound_count > 10) {
    while (resultfound_count != pageValueCount) {
        WebUI.click(findTestObject('ROLES/Roles_page/Pagination/front_navigation'))

        elements = WebUiCommonHelper.findWebElements(findTestObject('Content Management/CONTENT/Image_Listing/column1_Image_Name_Values'), 
            0)

        pageValueCount += elements.size()

        println(pageValueCount)
    }
}

WebUI.verifyEqual(resultfound_count, pageValueCount)

