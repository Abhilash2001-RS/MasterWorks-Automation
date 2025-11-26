package com.aurigo.masterworks.testframework.webUI.pages.projects;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProjectsPage extends ListPage {

    private By projectListTab;
    private By projectDashboardTab;
    private By projectDetailsTab;
    private By unifierSyncButton;

    public ProjectsPage(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("ProjectsPage.json");
        projectListTab = locators.get("projectListTab");
        projectDashboardTab = locators.get("projectDashboardTab");
        projectDetailsTab = locators.get("projectDetailsTab");
        unifierSyncButton = locators.get("unifierSyncButton");
    }

    /**
     * Method to navigate to Projects page
     */
    public void navigateTo() {
        navigation.navigateToModule("Projects", projectListTab);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Navigate to Location
     */
    public void navigateToLocation()
    {
        logger().info("Navigate to Location");
        navigation.navigateToFormInLeftPaneTree("Location");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
    }



    /**
     * Method to validate page load of project page
     *
     * @return True if the project page is loaded successfully
     */
    public boolean isPageLoaded() {
        navigation.switchFrameToContent();
        return elementHelper.isElementDisplayed(this.projectListTab);
    }

    /**
     * Function to open the project which is given in the parameter
     *
     * @param projectName project name
     */
    public void navigateAndOpenProject(String projectName) {
        navigateTo();
        openProject(projectName);
    }

    /**
     * Function to open the project which is given in the parameter
     *
     * @param projectName project name
     */
    public void openProject(String projectName){
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(),projectName,ListPageFilterOptions.Contains);
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Method to get the project code from given project name
     *
     * @param projectName Name of the project to select
     * @return String value of the project code
     */
    public String getProjectCode(String projectName) {
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName, ListPageFilterOptions.Contains);
        return getCellData(0, PlannedProjectListPageHeader.ProjectCode.getValue());
    }

    /**
     * Method to Verify Status and Attributes Button in Project Page
     *
     * @return true if the Status and Attributes Button is Displayed
     */

    public boolean checkStatusAndAttributesButton() {
        navigateTo();
        return validateRibbonIcon(RibbonIcons.Settings);
    }


    /**
     * Validates the WF status of a project
     *
     * @param projectName   - project Name
     * @param expectedState - expected state of the project
     * @return - true for a successful validation of workflow status
     */
    public boolean validateProjectWFStatus(String projectName, String expectedState) {
        navigateTo();
        waitHelper.waitForPageTabHeaderToBeClickable();
        var rowNum=getRowNumberFromListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName);
        return getCellData(rowNum, "Workflow Status").equals(expectedState);
    }

    /**
     * Get Project Names from the List of Project
     *
     * @return List of project Names
     */
    public List<String> getProjectName() {
        return getColumnData(PlannedProjectListPageHeader.ProjectName.getValue());
    }

    /**
     * Validate if a project is present in the list page
     * @param projectName - project to be searched
     * @return - true if the project is present
     */
    public boolean validatePresenceOfProject(String projectName)
    {
        logger().info("Validate if a project is present in the list page");
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName, ListPageFilterOptions.Contains);
        return getNumberOfRowsInListPage()!=0;
    }

    /**
     * Delete a project.
     *
     * @param projectName -   Name of the project to be deleted.
     */
    public void deleteProject(String projectName) {
        clearAllFilters();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Workflow));
        singleClickOnRowListPage(PlannedProjectListPageHeader.ProjectName.getValue(),projectName);
        clickRibbonIcon(RibbonIcons.Delete);
        getPage(AlertHandler.class).acceptAlert(true);

        waitHelper.waitForPageToLoad();
    }

    /**
     * Click ProjectDetails tab
     *
     */
    public void clickProjectDetailsTab()
    {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(projectDetailsTab);
        waitHelper.waitForPageToLoad();
    }

    public void clickUnifierSync(String projectName){
        navigateTo();
        openProject(projectName);
        elementHelper.doClick(unifierSyncButton);
    }


    /**
     * Click status and attribute
     */
    public void clickStatusAndAttribute()
    {
        clickRibbonIcon(RibbonIcons.Settings);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click manageUser
     */
    public void clickManageUser()
    {
        clickRibbonIcon(RibbonIcons.ManageUsersSecurityRoles);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Change project status
     * @param projName Name of the project to change status
     * @param workFlowActions Action to do
     * @param workFlowStatus Status it has to change to
     * @return True if expected status is executed to
     */
    public boolean changeProjectStatus(String projName, WorkFlowActions workFlowActions, WorkFlowStatus workFlowStatus)
    {
        waitHelper.waitForPageToLoad();
        logger().info(String.format("Changing state of the project to %s",workFlowActions.getValue()));
        return getPage(WorkFlowHandler.class).workFlowActionProgression(projName, "Project Name", workFlowActions, workFlowStatus);
    }
}
