package com.aurigo.masterworks.tests.webUI.cloud.smoke;

import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.annotations.TestInfo;
import com.aurigo.masterworks.testframework.webUI.common.SharedSteps;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowActions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowStatus;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.AuthorizationRequestForecastPage;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.BudgetEstimatePage;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.SpendForecastPage;
import com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement.GlobalFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundingRulesPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.PlanningPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.ProjectDetailsPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.program.ProgramPage;
import com.aurigo.masterworks.testframework.webUI.pages.projects.ProjectsPage;
import com.aurigo.masterworks.tests.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BudgetEstimateCreationTest extends BaseTest {

    @Test(testName = "Budget Management", description = "Validation Creation and Approval of" +
            "Budget Estimate")
    @TestInfo(testIds = {"718771", "711884", "713894", "269776"})
    public void budgetEstimateTest(){
        getPage(SharedSteps.class).login("AutomationUser", "Aurigo@1234567");
        String globalFundName = "GFL-" + TestDataUtil.getRandomName();
        String projectName = "FY2025 Highway-Rail Grade Crossing Safety Program";

        String programFamilyCategory = getPage(ProjectDetailsPage.class).getProgramFamilyCategory(projectName);

        //Create Global Fund and Approve
        getPage(GlobalFundListPage.class).navigateTo(true);
        getPage(GlobalFundListPage.class).createGlobalFundList(globalFundName);
        getPage(GlobalFundListPage.class).approveGlobalFund(globalFundName);

       // Edit Planning Page
        getPage(PlanningPage.class).openPlannedProject(projectName);

        //Project Fund list creation and Approval
        getPage(ProjectFundListPage.class).navigateTo();
        getPage(ProjectFundListPage.class).createProjectFundList(globalFundName);
        getPage(ProjectFundListPage.class).approveProjectFund(globalFundName);

        //Fund Rule Creation and Approval
        String fundRuleName = "Rule - " + TestDataUtil.getRandomName();
        getPage(ProjectFundingRulesPage.class).navigateTo();
        getPage(ProjectFundingRulesPage.class).createFundingRule(fundRuleName, globalFundName);
        getPage(ProjectFundingRulesPage.class).approveFundingRule(fundRuleName);

        //Budget Estimate Creation
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).createBudgetNEstimate("FR-1");
        getPage(AuthorizationRequestForecastPage.class).navigateTo();
        getPage(AuthorizationRequestForecastPage.class).enterData();
        getPage(SpendForecastPage.class).navigateTo();
        getPage(SpendForecastPage.class).enterData();

        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus( "BE-1", WorkFlowActions.Complete, WorkFlowStatus.Completed);
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus( "BE-1", WorkFlowActions.Publish, WorkFlowStatus.Published);

        //Publishing the project
        getPage(PlanningPage.class).publishProject(projectName);

        //Creating a program
        List<String> publishedProjectsList = new ArrayList<>();
        publishedProjectsList.add(projectName);
        var program = getPage(ProgramPage.class).createNewProgram(publishedProjectsList);

        //Submitting and Approving the program
        getPage(ProgramPage.class).changeProgramWorkFlowStatus(program.title, WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        getPage(ProgramPage.class).changeProgramWorkFlowStatus(program.title, WorkFlowActions.Approve, WorkFlowStatus.Approved);

        getPage(ProjectsPage.class).navigateAndOpenProject(projectName);

    }
}
