package com.aurigo.masterworks.tests.webUI.cloud.smoke;

import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.annotations.TestInfo;
import com.aurigo.masterworks.testframework.webUI.common.SharedSteps;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.BudgetEstimatePage;
import com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement.GlobalFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundingRulesPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.PlanningPage;
import com.aurigo.masterworks.tests.BaseTest;
import org.testng.annotations.Test;

public class BudgetEstimateCreationTest extends BaseTest {

    @Test(testName = "Budget Management", description = "Validation Creation and Approval of" +
            "Budget Estimate")
    @TestInfo(testIds = {"71877", "71884", "71894", "269776"})
    public void budgetEstimateTest(){
        getPage(SharedSteps.class).login("AutomationUser", "Aurigo@1234567");
        String globalFundName = "GFL-" + TestDataUtil.getRandomName();
        String projectName = "Bus Stop Inventory and ADA Compliance Plan";

        //Create Global Fund and Approve
//        getPage(GlobalFundListPage.class).navigateTo(true);
//        getPage(GlobalFundListPage.class).createGlobalFundList(globalFundName);
//        getPage(GlobalFundListPage.class).approveGlobalFund(globalFundName);

        //Edit Planning Page
        getPage(PlanningPage.class).openPlannedProject(projectName);

        //Project Fund list creation and Approval
//        getPage(ProjectFundListPage.class).navigateTo();
//        getPage(ProjectFundListPage.class).createProjectFundList(globalFundName);
//        getPage(ProjectFundListPage.class).approveProjectFund(globalFundName);

        //Fund Rule Creation and Approval
//        String fundRuleName = "Rule - " + TestDataUtil.getRandomName();
//        getPage(ProjectFundingRulesPage.class).navigateTo();
//        getPage(ProjectFundingRulesPage.class).createFundingRule(fundRuleName, globalFundName);
//        getPage(ProjectFundingRulesPage.class).approveFundingRule(fundRuleName);

        //Budget Estimate Creation
        String budgetEstimateName = "BE - " + TestDataUtil.getRandomName();
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).createBudgetAndEstimate(budgetEstimateName);

    }
}
