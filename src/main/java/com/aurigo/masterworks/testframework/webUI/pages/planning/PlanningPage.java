package com.aurigo.masterworks.testframework.webUI.pages.planning;

import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.CurrentResourcesGridColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ListPageFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.PlannedProjectListPageHeader;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowActions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowStatus;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import com.aurigo.masterworks.testframework.webUI.pages.projects.ProjectsPage;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import com.aventstack.extentreports.Status;
import com.google.common.base.Stopwatch;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlanningPage extends ListPage {
    public By projectDetailsTab;

    private By planningDashboardTab;
    private By plannedProjectsListTab;
    private By projectDashboardTab;
    private By selectUserComboBox;
    private By dateCreatedInputField;
    private By dateCreatedFilterButton;
    private By equalToFilter;
    private By createdDateTextBox;
    private By createdDateFilterOption;
    private By markProjectOffline;
    private By offlineProjectUsersTab;
    private By addOfflineProject;
    private By offlineProjectPicker;
    private By addOfflineProjectContainer;
    private By newFolderName;
    private By newFolderSave;
    private By clickUnifierSyncButton;
    private By projectFolder;

    private By projectValue;
    private By projectListTab;
    private By planningModule;

    private String projectNameHeader = "Project Name";
    private Integer firstRow = 0;
    private String nameDataFieldValue = "Name";


    public PlanningPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("PlanningPage.json");
        planningDashboardTab = locators.get("planningDashboardTab");
        projectDetailsTab = locators.get("projectDetailsTab");
        plannedProjectsListTab = locators.get("plannedProjectsListTab");
        projectDashboardTab = locators.get("projectDashboardTab");
        selectUserComboBox = locators.get("selectUserComboBox");
        dateCreatedInputField = locators.get("dateCreatedInputField");
        dateCreatedFilterButton = locators.get("dateCreatedFilterButton");
        equalToFilter = locators.get("equalToFilter");
        createdDateTextBox = locators.get("createdDateTextBox");
        createdDateFilterOption = locators.get("createdDateFilterOption");
        markProjectOffline = locators.get("markProjectOffline");
        offlineProjectUsersTab = locators.get("offlineProjectUsersTab");
        addOfflineProject = locators.get("addOfflineProject");
        offlineProjectPicker = locators.get("offlineProjectPicker");
        offlineProjectPicker = locators.get("addOfflineProjectContainer");
        newFolderName = locators.get("newFolderName");
        newFolderSave = locators.get("newFolderSave");
        projectValue = locators.get("projectValue");
        projectListTab=locators.get("projectListTab");
        planningModule = locators.get("planningModule");
        clickUnifierSyncButton = locators.get("clickUnifierSyncButton");
        projectFolder = locators.get("projectFolder");
    }

    /**
     * Method to navigate to Planning page
     */
    public void navigateTo() {
        navigation.navigateToModule("Planning", planningModule);
    }

    /**
     * Method to open Planned Projects page
     */
    public void openPlannedProjectsPage() {
        navigateTo();
        navigation.navigateToFormInLeftPaneTree("Planned Projects");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Function to open the planned project which is given in the parameter
     *
     * @param projectName project name
     */
    public void openPlannedProject(String projectName) {
        openPlannedProjectsPage();
        filterListPage("Project Name", projectName, ListPageFilterOptions.Contains);
        singleClickOnRowListPage(0);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Method to invite a user to a planned project
     *
     * @param firstName - First name of User who is to be invited
     * @param save      - Performed when save is needed
     */
    public void inviteUserToAPlannedProject(String firstName, boolean save) {
        waitHelper.waitForPageTabHeaderToBeClickable();
        if (elementHelper.doGetAttribute(projectDetailsTab, "aria-selected").equalsIgnoreCase("false")) {
            elementHelper.doClick(projectDetailsTab);
        }
        clickRibbonIcon(RibbonIcons.ManageUsers);
        waitHelper.waitForPageTabHeaderToBeClickable();
        getPage(ManageUsersInProjectPage.class).addUser(firstName);
        if (save) {
            clickRibbonIcon(RibbonIcons.Save);
            navigation.switchFrameToContent();
            waitHelper.waitForPageTabHeaderToBeClickable();
        }
    }

    public void clickUnifierSync(){
        navigation.switchFrameToDefault();
        elementHelper.doClick(projectFolder);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        clickRibbonIcon(RibbonIcons.Edit);
        elementHelper.doClick(clickUnifierSyncButton);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Creates a new project and returns the project details
     *
     * @param project project Details
     * @return Project
     */
    @Step("Creates a new project")
    public Project createProject(Project project) {
        logger().info("Creating a new project");
        navigateTo();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        clickRibbonIcon(RibbonIcons.New);
        Stopwatch stopwatch = Stopwatch.createStarted();
        var projectDetails = getPage(NewProjectPage.class).createProject(project);
        try {
            waitHelper.waitForPageToLoadWithCustomTimeout(120);
            waitHelper.waitForPageTabHeaderToBeClickable();
        } catch (Exception exception) {
            ExceptionHandler.log(exception);
            stopwatch.stop();
            logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
            throw exception;
        }
        stopwatch.stop();
        logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
        logger().info("Create new project finished");
        return projectDetails;
    }

    /**
     * Creates a new project with document properties and returns the project details
     *
     * @param projectData Project Data
     * @return Project object.
     */
    public Project createProjectWithDocumentProperties(Project projectData) {
        logger().log(Status.INFO, "Creating a new project with document properties attached");
        openPlannedProjectsPage();
        clickRibbonIcon(RibbonIcons.New);
        Stopwatch stopwatch = Stopwatch.createStarted();
        var projectDetails = getPage(NewProjectPage.class).createProjectWithDocStructure(projectData);
        try {
            waitHelper.waitForPageToLoadWithCustomTimeout(120);
            waitHelper.waitForPageTabHeaderToBeClickable();
        } catch (Exception exception) {
            ExceptionHandler.log(exception);
            stopwatch.stop();
            logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
            throw exception;
        }
        stopwatch.stop();
        logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
        logger().info("Create new project finished");
        return projectDetails;
    }

    /**
     * Create a project Document Folder Structure
     *
     * @param projectData Project Data
     * @return Project
     */
    public Project createProjectWithDocumentFolderStructure(Project projectData) {
        logger().log(Status.INFO, "Creating a new project with document folder structure");
        openPlannedProjectsPage();
        clickRibbonIcon(RibbonIcons.New);
        Stopwatch stopwatch = Stopwatch.createStarted();
        var projectDetails = getPage(NewProjectPage.class).createProjectWithDocFolderStructure(projectData);
        try {
            waitHelper.waitForPageToLoadWithCustomTimeout(120);
            waitHelper.waitForElementPresent(projectDetailsTab);
        } catch (Exception exception) {
            ExceptionHandler.log(exception);
            stopwatch.stop();
            logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
            throw exception;
        }
        stopwatch.stop();
        logger().info(String.format("Create Project - Time taken:%d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
        logger().info("Create new project finished");
        return projectDetails;
    }

    /**
     * Method to publish a project
     *
     * @param projectName name of project to be published
     * @return true if project is successfully published
     */
    public boolean publishProject(String projectName) {
        logger().info("Publishing the project");
        openPlannedProjectsPage();
        return getPage(WorkFlowHandler.class).workFlowActionProgression(projectName, "Project Name", WorkFlowActions.Approve, WorkFlowStatus.Published);
    }

    /**
     * Method to mark project offline
     *
     * @param projectName name of project to be published
     * @return true if project is successfully made offline
     */
    public boolean markOffline(String projectName) {
        logger().info("Marking Project Offline");
        getPage(ProjectsPage.class).navigateAndOpenProject(projectName);
        clickOnProjectDetailsTab();
        elementHelper.doClick(markProjectOffline);
        navigation.switchFrameToDefault();
        elementHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(offlineProjectUsersTab);

        waitHelper.waitForPageToLoad(addOfflineProject);
        elementHelper.doClick(addOfflineProject);
        waitHelper.waitForPageToLoad(offlineProjectPicker);
        var currentResourceName = getPage(Picker.class).getColumnData(addOfflineProjectContainer, nameDataFieldValue, 1);
        boolean obtainedOutput = getPage(Picker.class).singleSelectByText(CurrentResourcesGridColumns.FullName.getValue(), currentResourceName.get(0), offlineProjectPicker);
        getPage(GenericForm.class).clickSave();
        return obtainedOutput;
    }

    /**
     * @param projectDetails project details
     * @return boolean
     * Validates the existence of a project in the Project List page and returns true for successful validation
     */
    public boolean validateProjectInListPage(Project projectDetails) {
        filterListPage("Project Name", projectDetails.name, ListPageFilterOptions.Contains);
        if (getPage(Validations.class).isNoRecordsFoundDivDisplayed()) {
            logger().fail("Validation failed for the project " + projectDetails.name + " in Project List page");
            return false;
        } else {
            var filteredProjectName = getCellData(0, "Project Name");
            logger().pass("Validation successful for the project " + projectDetails.name + " in Project List page");
            return filteredProjectName.equals(projectDetails.name);
        }
    }


    /**
     * Method to Verify Status and Attributes Button in Planned Project Page
     *
     * @return true if the status and Attributes Button is Displayed
     */
    public boolean checkStatusAndAttributesButton() {
        openPlannedProjectsPage();
        var bool = validateRibbonIcon(RibbonIcons.Settings);
        elementHelper.doClick(plannedProjectsListTab);
        return bool;
    }

    /**
     * Creates a new project with Project Type and returns the project details
     *
     * @param projectData Project Data
     * @return Project
     */
    public Project createProjectWithProjectType(Project projectData) {
        logger().log(Status.INFO, "Creating a new project with Project Type");
        openPlannedProjectsPage();
        clickRibbonIcon(RibbonIcons.New);
        var projectDetails = getPage(NewProjectPage.class).createProjectWithProjectType(projectData);
        waitHelper.waitForElementPresent(projectDetailsTab);
        logger().info("Create new project finished");
        return projectDetails;
    }


    /**
     * Delete a project.
     *
     * @param projectName -   Name of the project to be deleted.
     */
    public void deleteProject(String projectName) {
        clearAllFilters();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        filterListPage("Project Name", projectName, ListPageFilterOptions.Contains);
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.Delete);
        getPage(AlertHandler.class).acceptAlert(true);

        waitHelper.waitForPageToLoad();
    }

    /**
     * Click on Project Details Tab
     */
    public void clickOnProjectDetailsTab() {
        elementHelper.doClick(projectDetailsTab);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
    }

    /**
     * Get Data from project details
     *
     * @param labelName - name of the column label
     * @return - the data expected
     */
    public String getProjectDetailsData(String labelName) {
        waitHelper.waitForPageToLoad();
        clickOnProjectDetailsTab();
        waitHelper.waitForPageToLoad();
        return elementHelper.doGetText(By.xpath(String.format("//td/span[contains(text(),'%s')]/../following-sibling::td//span", labelName)));
    }

    /**
     * Navigate to projectDetails Tab of a project
     *
     * @param projectName Project Name to navigate to
     */
    public void navigateToProjectDetails(String projectName) {
        openPlannedProject(projectName);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(projectDetailsTab);
        elementHelper.doClick(projectDetailsTab);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
    }



    /**
     * Click on edit button in projects Page
     */
    public void editProject() {
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Save));
    }


    /**
     * Selects the user in Projects Manage user page
     *
     * @param username-The user to be Selected
     */

    public void selectUserInProjectListPage(String username) {
        clickRibbonIcon(RibbonIcons.ManageUsersSecurityRoles);
        waitHelper.waitForPageToLoad(selectUserComboBox);
        elementHelper.doSendKeys(selectUserComboBox, username);
        elementHelper.doClickUsingActions(By.xpath("//div[contains(@id,'ComboBoxUserSelection_DropDown')]//li[contains(text(),'" + username + "')]"));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Checks Whether project is present in Project List Page
     *
     * @param projectName - Name of Project to be searched
     * @return true if project is present
     */
    public boolean isProjectPresent(String projectName) {
        filterListPage("Project Name", projectName, ListPageFilterOptions.Contains);
        return getNumberOfRowsInListPage() == 1;
    }

    /**
     * click on New in projects details page
     */
    public void clickOnNew() {
        navigation.switchFrameToContent();
        getPage(GenericForm.class).clickNew();

    }

    /**
     * click on Edit in projects details page
     *
     * @param projectName project Name to edit
     */
    public void clickOnEdit(String projectName) {
        navigation.switchFrameToContent();
        singleClickOnRowListPage(projectNameHeader, projectName);
        getPage(GenericForm.class).clickEdit();
    }

    /**
     * click on View in projects details page
     *
     * @param projectName project Name to view
     */
    public void clickOnView(String projectName) {
        navigation.switchFrameToContent();
        filterListPage(projectNameHeader, projectName, ListPageFilterOptions.Contains);
        singleClickOnRowListPage(firstRow);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
    }

    /**
     * click on Save in projects details page
     */
    public void clickOnSaveForToast() {
        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Method to invite a user to a contract
     *
     * @param firstName    - First name of User who is to be invited
     * @param contractName Name of the Contract to which user is invited
     */
    public void inviteUserToAContract(String firstName, String contractName) {
        waitHelper.waitForPageToLoad();
        if (elementHelper.doGetAttribute(projectDetailsTab, "aria-selected").equalsIgnoreCase("false")) {
            elementHelper.doClick(projectDetailsTab);
        }
        clickRibbonIcon(RibbonIcons.ManageUsers);
        waitHelper.waitForPageToLoad();
        getPage(ManageUsersInProjectPage.class).addUserToContract(firstName, contractName);
    }

    /**
     * Click ON Manage Users
     */
    public void clickOnManageUsers() {
        clickRibbonIcon(RibbonIcons.ManageUsersSecurityRoles);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Click ON Manage Users
     */
    public void clickOnManageUsersInProjectDetailsPage() {
        clickRibbonIcon(RibbonIcons.ManageUsers);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Existence of planned projects in left plane
     *
     * @return true if present
     */
    public boolean isPlannedProjectsPresent() {
        navigation.navigateToModule("Planning", planningDashboardTab);
        return navigation.verifyExistenceOfFormInLeftPaneTree("Planning", "Planned Projects");
    }

    /**
     * validate project type column poition
     *
     * @return true if Project Type comes after the owner column
     */
    public boolean validateProjectTypeColumnPositionComparedToOwnerColumn() {
        var indexOfProjectTypeColumn = getColNumOfListPageColHeader(PlannedProjectListPageHeader.ProjectType.getValue());
        var indexOfOwnerColumn = getColNumOfListPageColHeader(PlannedProjectListPageHeader.Owner.getValue());
        return indexOfProjectTypeColumn > indexOfOwnerColumn;
    }

    /**
     * Validate planned project when project name is passed
     *
     * @param projectName project name
     * @return true on validation of the above
     */
    public boolean validateProjectInListPage(String projectName) {
        filterListPage("Project Name", projectName, ListPageFilterOptions.Contains);
        return getNumberOfRowsInListPage() != 0;
    }

    /**
     * Validate functioning of WorkFlow History button in ribbon menu
     *
     * @return true if softAssert is performed on Workflow history popup successfully
     */
    public SoftAssert validateWorkFlowHistoryPopUp() {
        var softAssert = new SoftAssert();
        singleClickOnRowListPage(0);
        navigation.switchFrameToContent();
        getPage(WorkFlowHandler.class).openWorkflowHistory();

        waitHelper.waitForPageToLoad();
        softAssert.assertTrue(getPage(WorkFlowHandler.class).validateWorkFlowHistoryPopUp(), "Closing the History popup");
        getPage(WorkFlowHandler.class).closeWorkflowHistory();
        return softAssert;
    }


    /**
     * Select an automation project from planned project for any specified date
     *
     * @param projectCreationDate - Project Creation Date
     * @return - Project Name
     */
    public String getRandomProject(String projectCreationDate) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        clearAllFilters();
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), "Auto", ListPageFilterOptions.Contains);
        filterListPage(PlannedProjectListPageHeader.WorkflowStatus.getValue(), WorkFlowStatus.Draft.getValue(), ListPageFilterOptions.EqualTo);
        filterForCreatedDate(projectCreationDate, ListPageFilterOptions.EqualTo);
        return getColumnData(PlannedProjectListPageHeader.ProjectName.getValue()).get(0);
    }

    /**
     * Filter for create date
     *
     * @param date                  date to be provided
     * @param listPageFilterOptions filter option
     */
    public void filterForCreatedDate(String date, ListPageFilterOptions listPageFilterOptions) {
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeysUsingAction(createdDateTextBox, date);
        elementHelper.doClick(createdDateFilterOption);
        By filter = By.xpath(String.format("//span[text()='%s']", listPageFilterOptions.getValue()));
        waitHelper.waitForElementToBePresentAndClickable(filter);
        elementHelper.doClick(filter);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Navigate to Status and Attributes page
     */
    public void navigateToStatusAndAttributesPage() {
        clickRibbonIcon(RibbonIcons.Settings);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Cancel));
    }

    /**
     * Get required parameter of the project from list page
     *
     * @param columnName  column name
     * @param projectName project name
     * @return required String of the parameter
     */
    public String getRequiredParameterOfProject(PlannedProjectListPageHeader columnName, String projectName) {
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName, ListPageFilterOptions.Contains);
        return getColumnData(columnName.getValue()).get(0);
    }

    /**
     * To navigate to documents
     */
    public void navigateToDocuments() {
        waitHelper.waitForPageTabHeaderToBeClickable();
        navigation.navigateToFormInLeftPaneTree("Documents");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * To navigate to contracts
     */
    public void navigateToContracts() {
        waitHelper.waitForPageTabHeaderToBeClickable();
        navigation.navigateToFormInLeftPaneTree("Contracts");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Creates a new folder in documents form
     */
    public void createNewFolder() {
        getPage(RibbonMenu.class).clickRibbonIcon(RibbonIcons.NewFolderInDocumentsListPage);
        String folderName = TestDataUtil.getRandomName();
        elementHelper.doSendKeys(newFolderName, folderName);
        elementHelper.doClick(newFolderSave);
    }

    /**
     * verifies if 'Move' option is displayed in ribbon menu
     *
     * @return true if move option is displayed in the ribbon menu else return false
     */
    public boolean isMoveOptionDisplayed() {
        var moveOption = getPage(RibbonMenu.class).getRibbonIcon(RibbonIcons.Move);
        waitHelper.waitForElementPresent(moveOption);
        return elementHelper.isElementDisplayed(moveOption);
    }

    /**
     * Fetching project value details
     *
     * @return projectValue
     */
    public String getProjectValue() {
        waitHelper.waitForPageToLoad();
        clickOnProjectDetailsTab();
        waitHelper.waitForPageToLoad();
        return elementHelper.doGetAttribute(projectValue, "value");
    }

    /**
     * Validate planned project when project name is passed
     *
     * @param projectName project name
     * @return true on validation of the above
     */
    public boolean validateProjectNameInListPage(String projectName) {
        filterListPage("Project Name", projectName, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage() != 0;
    }
}