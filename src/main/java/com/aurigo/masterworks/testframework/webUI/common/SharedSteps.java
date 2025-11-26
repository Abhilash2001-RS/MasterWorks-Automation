package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.enums.FundTransactionType;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowActions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowStatus;
import com.aurigo.masterworks.testframework.webUI.pages.autodesk.Navigations;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.BudgetEstimateItemsPage;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.BudgetEstimatePage;
import com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement.FundTransactionPage;
import com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement.GlobalFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundListPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundTransactionsPage;
import com.aurigo.masterworks.testframework.webUI.pages.fundManagement.ProjectFundingRulesPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.PlanningPage;
import com.aurigo.masterworks.testframework.webUI.testData.PayItem;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SharedSteps extends BasePage {

    public SharedSteps(WebDriver driver) {
        super(driver);
    }

    /**
     * Login to Masterworks and return true for a successful login
     *
     * @param username - username for login
     * @param password - password
     * @return true, if login is successful
     */
    @Step("Login into MasterWorks")
    public boolean login(String username, String password) {
        if (getPage(LandingPage.class).isLoggedIn() && getPage(LandingPage.class).getLoggedInUserName().equalsIgnoreCase(username)) {
            logger().info("Already logged in as " + getPage(LandingPage.class).getLoggedInUserName());
            return true;
        }
        if (getPage(LandingPage.class).isLoggedIn() && !getPage(LandingPage.class).getLoggedInUserName().equalsIgnoreCase(username)) {
            logOut();
        }
        return getPage(LoginPage.class).doLogin(username, password);
    }

    /**
     * @param username Username to login
     * @param password Password to login
     * @return boolean true if the user is logged in
     * Login to Autodesk and return true for a successful login
     */
    public boolean loginAutodesk(String username, String password) {
        if (!getPage(LoginPage.class).validateLoginFields()) {
            logOutAutodesk();
        }
        return getPage(LoginPage.class).doLogin(username, password);
    }

    /**
     * Method to perform logout from the application
     */
    public void logOutAutodesk() {
        logger().info("Logging out");
        waitHelper.waitForPageToLoad();
        getPage(Navigations.class).navigateToProjectDirectory();
        getPage(Navigations.class).logout();
    }

    /**
     * Method to perform logout from the application
     */
    @Step("Logout the MasterWorks")
    public void logOut() {
        logger().info("Logging out");
        waitHelper.waitForPageToLoad();
        getPage(LandingPage.class).logout();
    }

    /**
     * Method to perform logout SystemAdmin from the application
     */
    public void logoutSystemAdmin() {
        logger().info("Logging out");
        waitHelper.waitForPageToLoad();
        getPage(LandingPage.class).logoutSystemAdmin();
    }

    /**
     * Creates and Publishes a planned project
     *
     * @param projectData Project Data
     * @return Project details
     */
    public Project createAndPublishProject(Project projectData) {
        var projectDetails = getPage(PlanningPage.class).createProject(projectData);
        WorkFlowActions actions;
        String budgetEstimateName = "BE" + TestDataUtil.getTimeStamp();
        List<PayItem> listOfPayItems = new ArrayList<>();
        listOfPayItems.add(TestDataUtil.generateNewPayItem("Root", 100, 10));
        listOfPayItems.add(TestDataUtil.generateNewPayItem("Root", 50, 5));
        getPage(PlanningPage.class).openPlannedProject(projectDetails.name);
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).createBudgetAndEstimate(budgetEstimateName);
        getPage(BudgetEstimateItemsPage.class).createBudgetEstimateItems(listOfPayItems);
        getPage(BudgetEstimateItemsPage.class).performBudgetEstimateAutoForecast();
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Complete, WorkFlowStatus.Completed);
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Publish, WorkFlowStatus.Published);
        getPage(PlanningPage.class).publishProject(projectDetails.name);
        return projectDetails;
    }

    /**
     * Creates and Publishes a planned project
     *
     * @param projectData Project Data
     * @param userName username
     * @return Project details
     */
    public Project createAndPublishProject(Project projectData, String userName) {
        var projectDetails = getPage(PlanningPage.class).createProject(projectData);
        WorkFlowActions actions;
        String budgetEstimateName = String.format("%s - %s",userName, projectData.startDate);
        List<PayItem> listOfPayItems = new ArrayList<>();
        listOfPayItems.add(TestDataUtil.generateNewPayItem("Root", 100, 10));
        listOfPayItems.add(TestDataUtil.generateNewPayItem("Root", 50, 5));
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).createBudgetAndEstimate(budgetEstimateName);
        getPage(BudgetEstimateItemsPage.class).createBudgetEstimateItems(listOfPayItems);
        getPage(BudgetEstimateItemsPage.class).performBudgetEstimateAutoForecast();
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Complete, WorkFlowStatus.Completed);
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Publish, WorkFlowStatus.Published);
        getPage(PlanningPage.class).publishProject(projectDetails.name);
        return projectDetails;
    }

    /**
     * Creates and Publishes a planned project, with desired Budget Cost
     *
     * @param projectData Project Data
     * @param quantity    quantity of the item
     * @param unitPrice   unit price of the item
     * @return Project details
     */
    public Project createAndPublishProjectWithCustomItemCost(Project projectData, int quantity, int unitPrice) {
        var projectDetails = getPage(PlanningPage.class).createProject(projectData);
        String budgetEstimateName = "BE" + TestDataUtil.getTimeStamp();
        List<PayItem> listOfPayItems = new ArrayList<>();
        listOfPayItems.add(TestDataUtil.generateNewPayItem("Root", quantity, unitPrice));
        getPage(PlanningPage.class).openPlannedProject(projectDetails.name);
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).createBudgetAndEstimate(budgetEstimateName);
        getPage(BudgetEstimateItemsPage.class).createBudgetEstimateItems(listOfPayItems);
        getPage(BudgetEstimateItemsPage.class).performBudgetEstimateAutoForecast();
        getPage(BudgetEstimatePage.class).navigateTo();
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Complete, WorkFlowStatus.Completed);
        getPage(BudgetEstimatePage.class).changeBudgetEstimateForecastStatus(budgetEstimateName, WorkFlowActions.Publish, WorkFlowStatus.Published);
        getPage(PlanningPage.class).publishProject(projectDetails.name);
        return projectDetails;
    }

    /**
     * Method to add Fund Rule Pre-Condition
     *
     * @param projectData - project to be created
     * @return String - fundRule
     **/
    public String addFundRule(Project projectData) {
        String globalFundName = TestDataUtil.getRandomName();
        int globalFundAmount = TestDataUtil.getRandomNumber(100000, 9999999);
        getPage(GlobalFundListPage.class).navigateTo(true);
        getPage(GlobalFundListPage.class).createGlobalFundList(globalFundName);
        getPage(GlobalFundListPage.class).approveGlobalFund(globalFundName);
        getPage(FundTransactionPage.class).navigateTo();
        getPage(FundTransactionPage.class).createFundTransaction(globalFundName, FundTransactionType.Add, globalFundAmount);
        getPage(FundTransactionPage.class).approveFundTransaction(globalFundName);
        getPage(PlanningPage.class).createProject(projectData);
        getPage(ProjectFundListPage.class).navigateTo();
        getPage(ProjectFundListPage.class).createProjectFundList(globalFundName);
        getPage(ProjectFundListPage.class).approveProjectFund(globalFundName);
        getPage(ProjectFundTransactionsPage.class).navigateTo();
        int changeAmount = TestDataUtil.getRandomNumber(1000, 20000);
        getPage(ProjectFundTransactionsPage.class).createProjectFundTransaction(globalFundName, FundTransactionType.Add, changeAmount);
        getPage(ProjectFundTransactionsPage.class).approveProjectFundTransaction(globalFundName);
        getPage(ProjectFundingRulesPage.class).navigateTo();
        String fundRule = TestDataUtil.getRandomName();
        getPage(ProjectFundingRulesPage.class).createFundingRule(fundRule, globalFundName);
        getPage(ProjectFundingRulesPage.class).approveFundingRule(fundRule);
        getPage(ProjectFundingRulesPage.class).selectRecordInListPage(fundRule);
        getPage(ProjectFundingRulesPage.class).clickMarkAsDefault();
        return fundRule;
    }

    /**
     * Validate if Module is displayed
     *
     * @param moduleName Name of the module to check
     * @return True if module is present
     */
    public boolean isModuleDisplayedInLeftPlaneTree(String moduleName) {
        getPage(Navigation.class).switchFrameToDefault();
        boolean isModuleDisplayed = getPage(Navigation.class).isModuleDisplayed(moduleName);
        getPage(Navigation.class).switchFrameToContent();
        return isModuleDisplayed;
    }

    /***
     * Common method to perform contextual url and broken link validation
     * @param baseUrl Base URL.
     * @param expectedUrl expected contextual url
     * @return true if the validation is successful
     */
    public boolean validateContextualHelpUrlAndLinks(String baseUrl, String expectedUrl) {
        getPage(LandingPage.class).clickHelp();
        return getPage(Validations.class).validateContextualHelpUrlAndLinks(baseUrl, expectedUrl);

    }
}