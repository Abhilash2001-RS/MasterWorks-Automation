package com.aurigo.masterworks.tests.webUI.cloud.smoke;
import com.aurigo.masterworks.testframework.utilities.DriverManager;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.annotations.TestInfo;
import com.aurigo.masterworks.testframework.utilities.helper.AssertHelper;
import com.aurigo.masterworks.testframework.webUI.common.SharedSteps;
import com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement.GlobalFundListPage;
import com.aurigo.masterworks.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class FundManagementTest extends BaseTest {

    @Test(testName = "Fund Management", description = "Verify User is able to approve fund Management")
    @TestInfo(testIds = {"71877", "71884", "71894", "269776"})
    public void FundManagementSmokeTest(){

        var login = getPage(SharedSteps.class).login("AutomationUser", "Aurigo@1234567");
        assertHelper.assertTrue( login, "Home - MasterWorks", "Login to Masterworks");

        getPage(GlobalFundListPage.class).navigateTo(true);
        String globalFundName = "GF" + TestDataUtil.getTimeStamp();
        getPage(GlobalFundListPage.class).createGlobalFundList(globalFundName, "5304", "Federal");
        assertHelper.assertTrue(getPage(GlobalFundListPage.class).validateGlobalFundListCreation("Test Fund Management"),
                "Validate creation of Global Fund");

    }
}
