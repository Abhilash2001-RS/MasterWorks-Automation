    package com.aurigo.masterworks.testframework.webUI.pages.planning;
    
    import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
    import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
    import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
    import com.aurigo.masterworks.testframework.webUI.common.Validations;
    import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
    import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
    import com.aurigo.masterworks.testframework.webUI.generic.GenericLibrary;
    import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
    import com.aurigo.masterworks.testframework.webUI.generic.Picker;
    import com.aurigo.masterworks.testframework.webUI.pages.planning.program.ProgramDetailsPage;
    import com.aurigo.masterworks.testframework.webUI.testData.Project;
    import org.openqa.selenium.By;
    import org.openqa.selenium.Keys;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.testng.asserts.SoftAssert;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public class ProjectDetailsPage extends ListPage {
    
        private WebDriver driver;
    
        private By projectNameInProjectDetails;
        private By projectCodeInProjectDetails;
        private By businessUnitInProjectDetails;
        private By startDateInProjectDetails;
        private By endDateInProjectDetails;
        private By projectEndDate;
        private By ownerNameInProjectDetails;
        private By programCategoryInProjectDetails;
        private By documentFolderStuctureDropDownInEdit;
        private By attributes;
        private By projectTypeLbl;
        private By projectNameTextBox;
        private By strategicGoalsGrid;
        private By strategicObjectivesGrid;
        private By strategicGoalsAddButton;
        private By strategicObjectivesAddButton;
        private By strategicGoalsPicker;
        private By strategicObjectivesPicker;
        private By strategicGoalTableId;
        private By strategicGoalGrid;
        private By strategicObjectivesTableId;
        private By strategicObjectiveGrid;
        private By projectTypeLabel;
        private By projectTypeData;
        private By projectType;
        private By enterpriseDashboardTab;
        private By projectNameRequiredWarning;
        private By projectCodeRequiredWarning;
        private By projectOwnerRequiredWarning;
        private By programCategoryRequiredWarning;
        private By businessUnitRequiredWarning;
        private By activeCheckBox;
        private By projectNameCopyTextField;
        private By projectCodeCopyTextField;
        private By estimateAllCheckBox;
        private By plannedProjectsListTab;
        private By selectAllInStrategicGoal;
        private By deleteButtonStrategicGoal;
        private By numberOfRowsInStrategicGoals;
        private By usersCheckBox;
        private By getProjectValue;
        private By copyModuleCheckbox;
        private By copyAttributeCheckbox;
        private By copyUserCheckbox;
        private By copyFundCheckbox;
        private By projectStartDate;
        private By projectNameTextArea;
        private By documentPropertiesText;
        private By documentFolderStructureDropdownText;
        private final By attachmentTable;
        private final By attachmentTableHeader;
        private final By downloadAttachmentInViewMode;
        private final By downloadAttachmentInEditMode;
        private final By expandAttachmentsButton;
        private final By calendarDaysValue;
    
        String programFamilyCategory = getPage(ProjectDetailsPage.class).getProgramFamilyCategory(getProjectName());

        private String valueAttribute = "value";
        private String strategicGoalHeader = "Strategic Goal";
        private String strategicObjectiveHeader = "Strategic Objective";
        private String errorMsg = "You do not have access to view the requested page.";
        private String strategicRecord = "//tr[@class='rgRow']//td[@title='%s']/..//td[contains(@class,'gridInlineEditing') and not(contains(@style,'display:none;'))]";
        private String strategicRecordPercentage = "//td[@title='%s']/..//input[@type='text']";
        private String selectRowInStrategicObjectives = "//div[contains(@id,'ctl00_ctl00_C1_ERP_CC_BODY_radgdStrategicObjectives')]//tr/td[contains(text(),'%s')]/../td[not(@style)][%s]";
        private String attachmentGridSelector = "//div[contains(@id,'_gridAttachment') ]//td[text()='%s' and not (contains(@style,'display:none;'))]";
    
        public ProjectDetailsPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
    
            var locators = LocatorUtil.getLocators("ProjectDetailsPage.json");
            projectNameInProjectDetails = locators.get("projectNameInProjectDetails");
            projectCodeInProjectDetails = locators.get("projectCodeInProjectDetails");
            businessUnitInProjectDetails = locators.get("businessUnitInProjectDetails");
            startDateInProjectDetails = locators.get("startDateInProjectDetails");
            endDateInProjectDetails = locators.get("endDateInProjectDetails");
            projectEndDate = locators.get("projectEndDate");
            ownerNameInProjectDetails = locators.get("ownerNameInProjectDetails");
            programCategoryInProjectDetails = locators.get("programCategoryInProjectDetails");
            documentFolderStuctureDropDownInEdit = locators.get("documentFolderStuctureDropDownInEdit");
            attributes = locators.get("attributes");
            projectTypeLbl = locators.get("projectTypeLbl");
            projectNameTextBox = locators.get("projectNameTextBox");
            strategicGoalsGrid = locators.get("strategicGoalsGrid");
            strategicObjectivesGrid = locators.get("strategicObjectivesGrid");
            strategicGoalsAddButton = locators.get("strategicGoalsAddButton");
            strategicObjectivesAddButton = locators.get("strategicObjectivesAddButton");
            strategicGoalsPicker = locators.get("strategicGoalsPicker");
            strategicObjectivesPicker = locators.get("strategicObjectivesPicker");
            strategicGoalTableId = locators.get("strategicGoalTableId");
            strategicGoalGrid = locators.get("strategicGoalGrid");
            strategicObjectivesTableId = locators.get("strategicObjectivesTableId");
            strategicObjectiveGrid = locators.get("strategicObjectiveGrid");
            projectTypeLabel = locators.get("projectTypeLabel");
            projectTypeData = locators.get("projectTypeData");
            projectType = locators.get("projectType");
            enterpriseDashboardTab = locators.get("enterpriseDashboardTab");
            projectNameRequiredWarning = locators.get("projectNameRequiredWarning");
            projectCodeRequiredWarning = locators.get("projectCodeRequiredWarning");
            projectOwnerRequiredWarning = locators.get("projectOwnerRequiredWarning");
            programCategoryRequiredWarning = locators.get("programCategoryRequiredWarning");
            businessUnitRequiredWarning = locators.get("businessUnitRequiredWarning");
            activeCheckBox = locators.get("activeCheckBox");
            projectNameCopyTextField = locators.get("projectNameCopyTextField");
            projectCodeCopyTextField = locators.get("projectCodeCopyTextField");
            estimateAllCheckBox = locators.get("estimateAllCheckBox");
            plannedProjectsListTab = locators.get("plannedProjectsListTab");
            selectAllInStrategicGoal = locators.get("selectAllInStrategicGoal");
            deleteButtonStrategicGoal = locators.get("deleteButtonStrategicGoal");
            numberOfRowsInStrategicGoals = locators.get("numberOfRowsInStrategicGoals");
            usersCheckBox = locators.get("usersCheckBox");
            getProjectValue = locators.get("getProjectValue");
            attachmentTable = locators.get("attachmentTable");
            attachmentTableHeader = locators.get("attachmentTableHeader");
            downloadAttachmentInViewMode = locators.get("downloadAttachmentInViewMode");
            downloadAttachmentInEditMode = locators.get("downloadAttachmentInEditMode");
            expandAttachmentsButton = locators.get("expandAttachmentsButton");
            copyModuleCheckbox = locators.get("copyModuleCheckbox");
            copyAttributeCheckbox = locators.get("copyAttributeCheckbox");
            copyUserCheckbox = locators.get("copyUserCheckbox");
            copyFundCheckbox = locators.get("copyFundCheckbox");
            projectStartDate = locators.get("projectStartDate");
            projectNameTextArea = locators.get("projectNameTextArea");
            documentFolderStructureDropdownText = locators.get("documentFolderStructureDropdownText");
            documentPropertiesText = locators.get("documentPropertiesText");
            calendarDaysValue = locators.get("calendarDaysValue");
        }
    
        /**
         * Validates the project parameters in the Project Details page
         *
         * @param projectDetails project data
         * @return SoftAssert collection of asserts for project details
         */
        public SoftAssert validateMandatoryFieldsAfterProjectCreation(Project projectDetails) {
            navigation.switchFrameToContent();
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Back));
            var softAssert = new SoftAssert();
            String projectNameFromUI = elementHelper.doGetText(projectNameInProjectDetails);
            softAssert.assertEquals(projectNameFromUI, projectDetails.name, "Project Name validation failed");
            softAssert.assertEquals(elementHelper.doGetText(projectCodeInProjectDetails), projectDetails.code, "Project Code validation failed");
            softAssert.assertEquals(elementHelper.doGetText(businessUnitInProjectDetails), projectDetails.businessUnit, "BusinessUnit validation failed");
            softAssert.assertEquals(elementHelper.doGetText(startDateInProjectDetails).replaceAll(RegexStrings.notDigit.getValue(), ""), projectDetails.startDate.replaceAll(RegexStrings.notDigit.getValue(), ""), "Project StartDate validation failed");
            softAssert.assertEquals(elementHelper.doGetText(endDateInProjectDetails).replaceAll(RegexStrings.notDigit.getValue(), ""), projectDetails.endDate.replaceAll(RegexStrings.notDigit.getValue(), ""), "Project EndDate validation failed");
            softAssert.assertEquals(elementHelper.doGetText(ownerNameInProjectDetails), projectDetails.owner, "Project Owner validation failed");
            navigation.switchFrameToContent();
            return softAssert;
        }
    
        /**
         * Method to Check Whether the Project Creation User is present in Manage Users Table
         *
         * @param username username
         * @return True if Username is Present in ManageUsers Table
         */
        public boolean verifyCreatedUserInManageUsersPage(String username) {
            clickRibbonIcon(RibbonIcons.ManageUsers);
            waitHelper.waitForPageToLoad();
            return getPage(ManageUsersInProjectPage.class).checkCreatedUserInListPage(username);
        }
    
        /**
         * Download the doc by clicking on the filename
         *
         * @param fileName   Name of the file to download
         * @param userName   Username of the user who added the document
         * @param inEditMode true if the download should be done in Edit Mode
         */
        public void downloadAttachment(String fileName, String userName, boolean inEditMode) {
            waitHelper.waitForPageTabHeaderToBeClickable();
            if (inEditMode) {
                if (!elementHelper.isElementDisplayed(downloadAttachmentInEditMode)) {
                    elementHelper.doClick(expandAttachmentsButton);
                }
                waitHelper.waitForPageToLoad(downloadAttachmentInEditMode);
                elementHelper.doClick(getPage(GenericForm.class).getRowFromRadGrid(attachmentTable, attachmentTableHeader, AttachmentColumn.DocumentName.getValue(), fileName)
                        .findElement(By.xpath("//td[text()='" + userName + "']")));
                elementHelper.doClick(downloadAttachmentInEditMode);
            } else {
                if (!elementHelper.isElementDisplayed(downloadAttachmentInViewMode)) {
                    elementHelper.doClick(expandAttachmentsButton);
                }
                waitHelper.waitForPageToLoad(downloadAttachmentInViewMode);
                elementHelper.doClick(By.xpath(String.format(attachmentGridSelector, userName)));
                elementHelper.doClick(downloadAttachmentInViewMode);
            }
            waitHelper.waitForPageToLoad();
        }
    
        /**
         * @param projectDetails project details
         * @return SoftAssert
         * Validates the project parameters in the Project Details page
         */
        public SoftAssert validateMandatoryFieldsAfterProjectCreationWithDocumentProperties(Project projectDetails) {
            var softAssert = new SoftAssert();
            String projectNameFromUI;
            projectNameFromUI = elementHelper.doGetText(projectNameInProjectDetails);
            softAssert.assertEquals(projectNameFromUI, projectDetails.name, "Project Name validation");
            softAssert.assertEquals(elementHelper.doGetText(projectCodeInProjectDetails), projectDetails.code, "Project Code validation");
            softAssert.assertEquals(elementHelper.doGetText(businessUnitInProjectDetails), projectDetails.businessUnit, "BusinessUnit validation");
            softAssert.assertEquals(elementHelper.doGetText(startDateInProjectDetails).replaceAll(RegexStrings.notDigit.getValue(), ""), projectDetails.startDate.replaceAll(RegexStrings.notDigit.getValue(), ""), "Project StartDate validation failed");
            softAssert.assertEquals(elementHelper.doGetText(endDateInProjectDetails).replaceAll(RegexStrings.notDigit.getValue(), ""), projectDetails.endDate.replaceAll(RegexStrings.notDigit.getValue(), ""), "Project EndDate validation failed");
            softAssert.assertEquals(elementHelper.doGetText(ownerNameInProjectDetails), projectDetails.owner, "Project Owner validation");
            //softAssert.assertEquals(elementHelper.doGetText(programCategoryInProjectDetails), projectDetails.programCategory, "ProgramCategory validation");
            softAssert.assertEquals(elementHelper.doGetText(documentFolderStructureDropdownText), projectDetails.documentFolderName, "Project Owner validation");
            softAssert.assertEquals(elementHelper.doGetText(documentPropertiesText), projectDetails.documentPropertyName, "ProgramCategory validation");
    
            return softAssert;
        }
    
        /**
         * Method to Verify Status and Attributes Button in Project Details Page
         *
         * @return true if the Status and Attributes Button is Displayed
         */
    
        public boolean verifyStatusAndAttributesButton() {
            singleClickOnRowListPage(0);
            clickRibbonIcon(RibbonIcons.View);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(getPage(PlanningPage.class).projectDetailsTab);
            elementHelper.doClick(getPage(PlanningPage.class).projectDetailsTab);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
            return elementHelper.isElementDisplayed(attributes);
    
        }
    
    
        /**
         * This validates whether Project Type drop down is present in new Project Page or not
         *
         * @return true if ProjectType Dropdown is present
         */
    
        public boolean isProjectTypeDropdownExists() {
            getPage(PlanningPage.class).openPlannedProjectsPage();
            return getPage(NewProjectPage.class).isProjectTypeDropdownDisplayed();
        }
    
        /**
         * Method to Navigate to Library and Create a Project with Required Project type From Library to Project Type
         *
         * @return Project Type as String
         */
    
        public String navigateAndCreateProjectWithProjectType(Project projectData) {
            getPage(GenericLibrary.class).navigateToPageInLibrary("Project Type");
            projectData.projectType = getCellData(1, "Project Type");
            getPage(PlanningPage.class).createProjectWithProjectType(projectData);
            return projectData.projectType;
        }
    
        /**
         * Method to Navigate to Library and Create a Project with Required Project type From Library to Project Type
         */
        public void createProjectWithProjectType(String projectData) {
            getPage(GenericLibrary.class).navigateToPageInLibrary("Project Type");
            getPage(GenericForm.class).clickNew();
            waitHelper.waitForPageToLoad(projectNameTextArea);
            elementHelper.doSendKeys(projectNameTextArea, projectData);
            getPage(GenericForm.class).clickSave();
        }
    
        /**
         * Create and Validation of Project Type in Project Details
         *
         * @param projectData Project Data
         * @return True
         */
        public boolean createAndValidateProjectTypeValue(Project projectData) {
            String projectTypeName = navigateAndCreateProjectWithProjectType(projectData);
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
            return elementHelper.doGetText(projectTypeLbl).equals(projectTypeName);
    
        }
    
        /**
         * Create and Validating ProjectType in Edit Mode
         *
         * @param projectData Project Data
         * @return True
         */
        public boolean createAndValidateProjectTypeInEditMode(Project projectData) {
            String projectTypeName = navigateAndCreateProjectWithProjectType(projectData);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementToBePresentAndClickable(getRibbonIcon(RibbonIcons.Edit));
            clickRibbonIcon(RibbonIcons.Edit);
            var softAssert = new SoftAssert();
            softAssert.assertEquals(elementHelper.getFirstSelectedOption(getPage(NewProjectPage.class).projectTypeDrpdwn), projectTypeName);
            elementHelper.selectComboBoxItemByIndex(getPage(NewProjectPage.class).projectTypeDrpdwn, 1);
            String newProjectType = elementHelper.getFirstSelectedOption(getPage(NewProjectPage.class).projectTypeDrpdwn);
            clickRibbonIcon(RibbonIcons.Save);
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
            return elementHelper.doGetText(projectTypeLbl).equals(newProjectType);
    
        }
    
        /**
         * To edit project Name
         *
         * @param projectNameEdit project Name which has to get changed
         */
        public void editProject(String projectNameEdit) {
            waitHelper.waitForPageToLoad(projectNameTextBox);
            elementHelper.doClick(projectNameTextBox);
            elementHelper.doSendKeys(projectNameTextBox, projectNameEdit);
            clickRibbonIcon(RibbonIcons.Save);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Edit));
        }
    
        /**
         * To update document folder structure for project
         *
         * @param documentFolderStructureName document folder structure Name which has to get changed
         */
        public void updateDocFolderStructureForProject(String documentFolderStructureName) {
            waitHelper.waitForPageToLoad();
            elementHelper.selectComboBoxItemByText(documentFolderStuctureDropDownInEdit, documentFolderStructureName);
            clickRibbonIcon(RibbonIcons.Save);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Edit));
        }
    
    
        /**
         * existence of strategic goals grid
         *
         * @return true if Grid is present
         */
        public boolean isStrategicGoalsGridPresent() {
            elementHelper.scrollToView(strategicGoalsGrid);
            return getPage(Validations.class).verifyElementExists(strategicGoalsGrid);
        }
    
        /**
         * existence of strategic objectives grid
         *
         * @return true if grid is present
         */
        public boolean isStrategicObjectivesGridPresent() {
            elementHelper.scrollToView(strategicObjectivesGrid);
            return getPage(Validations.class).verifyElementExists(strategicObjectivesGrid);
        }
    
        /**
         * adding data in strategic Goal grid
         *
         * @param rowNumber row number to be selected
         * @return Name of the Strategic Goal
         */
        public String addDataInStrategicGoals(Integer rowNumber) {
            waitHelper.waitForElementClickable(strategicGoalsAddButton);
            elementHelper.doClick(strategicGoalsAddButton);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(strategicGoalsPicker);
            String name = getPage(Picker.class).getDataByRowNoColumnName(rowNumber, "Strategic Goal", strategicGoalsPicker);
            getPage(Picker.class).singleSelectByRowNumber(rowNumber, strategicGoalsPicker);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Save));
            return name;
        }
    
        /**
         * adding data in strategic objectives grid
         *
         * @param rowNumber strategic objective to be added
         * @return Name of the Strategic Objective
         */
        public String addDataInStrategicObjectives(Integer rowNumber) {
            waitHelper.waitForElementClickable(strategicObjectivesAddButton);
            elementHelper.doClick(strategicObjectivesAddButton);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(strategicObjectivesPicker);
            String name = getPage(Picker.class).getDataByRowNoColumnName(rowNumber, "Strategic Objective", strategicObjectivesPicker);
            getPage(Picker.class).singleSelectByRowNumber(1, strategicObjectivesPicker);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Save));
            return name;
        }
    
        /**
         * Existence of record
         *
         * @param strategicGoal Strategic Goal
         * @param columnName    Column Name to search in
         * @return true if record exists
         */
        public boolean validateStrategicGoalGrid(String strategicGoal, String columnName) {
            return getPage(GenericForm.class).isRecordExistsInRadGrid(strategicGoalTableId, strategicGoalGrid, columnName, strategicGoal);
        }
    
        /**
         * Existence of record
         *
         * @param strategicObjective Strategic Objectives
         * @param columnName         Column Name to search in
         * @return true if record exists
         */
        public boolean validateStrategicObjectivesGrid(String strategicObjective, String columnName) {
            elementHelper.scrollToView(strategicObjectivesAddButton);
            waitHelper.waitForPageToLoad(strategicObjectivesAddButton);
            return getPage(GenericForm.class).isRecordExistsInRadGrid(strategicObjectivesTableId, strategicObjectiveGrid, columnName, strategicObjective);
        }
    
    
        /**
         * edit percentage in strategic goal percentage
         *
         * @param percentage    percentage value
         * @param rowIdentifier rowIdentifier
         */
        public void editPercentage(String percentage, String rowIdentifier) {
            navigation.switchFrameToContent();
            waitHelper.waitForPageTabHeaderToBeClickable();
            By rowTextField = By.xpath(String.format(strategicRecord, rowIdentifier));
            elementHelper.scrollToView(strategicGoalsAddButton);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(rowTextField);
            elementHelper.doClick(rowTextField);
            var input = elementHelper.getElement(By.xpath(String.format(strategicRecordPercentage, rowIdentifier)));
            String multiSelect = Keys.chord(Keys.CONTROL, Keys.chord("a"));
            input.sendKeys(multiSelect);
            elementHelper.doSendKeys(input, percentage);
            elementHelper.doClick(strategicGoalsGrid);
        }
    
        /**
         * edit percentage in strategic goal percentage
         *
         * @param percentage    percentage value
         * @param rowIdentifier rowIdentifier
         */
        public void editPercentageInStrategicObjectives(String percentage, String rowIdentifier) {
            By rowTextField = By.xpath(String.format(strategicRecord, rowIdentifier));
            waitHelper.waitForElementPresent(rowTextField);
            elementHelper.doClick(rowTextField);
            var input = elementHelper.getElement(By.xpath(String.format(strategicRecordPercentage, rowIdentifier)));
            String multiSelect = Keys.chord(Keys.CONTROL, Keys.chord("a"));
            input.sendKeys(multiSelect);
            elementHelper.doSendKeys(input, percentage);
            elementHelper.doClick(strategicObjectivesGrid);
        }
    
        /**
         * validate toast message
         *
         * @param message message to validate with
         * @return true if validation is true
         */
        public boolean validateToastMessage(String message) {
            waitHelper.waitForPageToLoad();
            String actToastMessage = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
            getPage(ToastUtil.class).waitAndCloseForSingleToast();
            return actToastMessage.equals(message);
        }
    
        /**
         * Function to click on Edit button
         */
        public void clickEdit() {
            getPage(GenericForm.class).clickEdit();
        }
    
        /**
         * Function to verify that Project Name is same as expected value
         *
         * @param expectedValue expected value for Project Name
         * @return true if expected value and project name are same, else returns false
         */
        public boolean compareProjectNameValue(String expectedValue) {
            String actualValue;
            actualValue = elementHelper.doGetText(projectNameInProjectDetails).trim();
            return actualValue.equals(expectedValue);
        }
    
        /**
         * Function to verify project details
         *
         * @param projectDetails Details of the project to be verified
         * @return true if the validation passes, else the function will fail at assertAll itself
         */
        public boolean verifyProjectDetails(Project projectDetails) {
            validateMandatoryFieldsAfterProjectCreation(projectDetails).assertAll();
            return true;
        }
    
        /**
         * Extract only the Project Type from drop down
         *
         * @return List of project type name
         */
        public List<String> getProjectTypeValues() {
            elementHelper.doClick(projectType);
            waitHelper.waitForPageToLoad();
            List<WebElement> projectTypeRecords = elementHelper.getElements(projectTypeData);
            List<String> records = new ArrayList<>();
            for (var rec : projectTypeRecords) {
                String value = elementHelper.doGetText(rec);
                records.add(value);
            }
            return records;
        }
    
        /**
         * Method to get calendar days in contract setup
         *
         * @return Calendar Days
         */
        public String getCalendarDays() {
            navigation.switchFrameToContent();
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(calendarDaysValue);
            return elementHelper.doGetText(calendarDaysValue);
        }
    
        /**
         * edit date in the project
         *
         * @param endDate value of the date
         */
        public void editEndDate(String endDate) {
            waitHelper.waitForElementPresent(projectEndDate);
            elementHelper.doSendKeysUsingAction(projectEndDate, endDate);
            elementHelper.doClick(projectTypeLabel);
            waitHelper.waitForPageToLoad();
        }
    
        /**
         * edit date in the project
         *
         * @param startDate value of the date
         */
        public void editStartDate(String startDate) {
            waitHelper.waitForElementPresent(projectStartDate);
            elementHelper.doSendKeysUsingAction(projectStartDate, startDate);
            elementHelper.doClick(projectTypeLabel);
            waitHelper.waitForPageToLoad();
        }
    
        /**
         * check if end date is refreshed
         *
         * @param endDate end date
         * @return true if element is refreshed is successfully
         */
        public boolean validateEndDate(String endDate) {
            boolean presenceOfElement = elementHelper.isElementDisplayed(projectEndDate);
            String value = elementHelper.doGetAttribute(projectEndDate, valueAttribute);
            boolean validationOfNewData = value.equals(endDate);
            return (presenceOfElement && validationOfNewData);
        }
    
        /**
         * method to open a set of projects
         *
         * @param projectRecords   list of projects to open
         * @param numberOfProjects number of projects to open
         * @return List of projects opened
         */
        public List<String> openListOfProjects(List<String> projectRecords, Integer numberOfProjects) {
            List<String> listOfProject = new ArrayList<>();
            int i = 0;
            while (i < numberOfProjects) {
                getPage(PlanningPage.class).openPlannedProject(projectRecords.get(i));
                listOfProject.add(projectRecords.get(i));
                i++;
            }
            return listOfProject;
        }
    
        /**
         * validation of the existence of projects opened in the recent projects tree
         *
         * @param projects list of projects to validate
         * @return true if they exist
         */
        public boolean validationOfRecentProjects(List<String> projects) {
            navigation.navigateToModule("Home", enterpriseDashboardTab);
            navigation.switchFrameToDefault();
            navigation.clickOnExpandAll();
            List<String> recentProjects = navigation.getRecentProjectsList();
            return projects.containsAll(recentProjects);
        }
    
        /**
         * method to list all locators of the warning
         *
         * @return list of locators
         */
        private List<By> warningLabels() {
            List<By> warningLabelProject = new ArrayList<>();
            warningLabelProject.add(projectNameRequiredWarning);
            warningLabelProject.add(projectCodeRequiredWarning);
            warningLabelProject.add(projectOwnerRequiredWarning);
            warningLabelProject.add(programCategoryRequiredWarning);
            warningLabelProject.add(businessUnitRequiredWarning);
            return warningLabelProject;
        }
    
        /**
         * method to validate save without filling mandatory fields
         *
         * @return true if all the warning fields are present else false
         */
        public boolean validateSaveWithoutMandatoryFilling() {
            return getPage(GenericForm.class).validateElementsExists(warningLabels());
        }
    
        /**
         * Click on mark inactive or active button
         *
         * @return String of the status of the checkbox
         */
        public String clickMarkActiveOrInactiveButton() {
            clickRibbonIcon(RibbonIcons.MarkActiveInactiveProjectDetails);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad(RibbonIcons.Back);
            return elementHelper.doGetAttribute(activeCheckBox, "checked");
        }
    
        /**
         * Click on Copy Button
         */
        public void clickOnCopyButton() {
            clickRibbonIcon(RibbonIcons.Copy);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementToBePresentAndClickable(getRibbonIcon(RibbonIcons.Save));
        }
    
        /**
         * Click on back button
         *
         * @return true if planned projects list exists
         */
        public boolean clickOnBackButton() {
            waitHelper.waitForElementToBePresentAndClickable(getRibbonIcon(RibbonIcons.Back));
            clickRibbonIcon(RibbonIcons.Back);
            waitHelper.waitForPageToLoad(plannedProjectsListTab);
            return getPage(Validations.class).verifyElementExists(plannedProjectsListTab);
        }
    
        /**
         * Fill copy Project details
         *
         * @param project Project details
         */
        public void fillProjectCopyDetails(Project project) {
            elementHelper.doSendKeys(projectNameCopyTextField, project.name);
            elementHelper.doSendKeys(projectCodeCopyTextField, project.code);
            if (!currentEnvironment.getType().equalsIgnoreCase("mcon")) {
                elementHelper.doClick(estimateAllCheckBox);
            } else {
                waitHelper.waitForElementPresent(usersCheckBox);
                elementHelper.doClick(usersCheckBox);
            }
        }
    
        /**
         * Click on save button of copy project
         */
        public void clickOnSaveOnCopyProject() {
            clickRibbonIcon(RibbonIcons.Save);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(plannedProjectsListTab);
        }
    
        /**
         * Click on save button
         */
        public void clickOnSave() {
            logger().info("Clicking on Save");
            waitHelper.waitForPageToLoad();
            getPage(GenericForm.class).clickSave();
        }
    
    
        public String getProgramFamilyCategory(String projectName) {
            filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName, ListPageFilterOptions.Contains);
            return getCellData(0, PlannedProjectListPageHeader.ProgramFamilyCategory.getValue());
        }

        /**
         * Click on row of strategic goal grid
         *
         * @param rowToIdentify row to identify
         * @return row WebElement
         */
        public WebElement selectCellInStrategicGoal(String rowToIdentify) {
            var row = selectRow(GridType.RadGrid, rowToIdentify);
            elementHelper.doClick(row);
            return row;
        }
    
    
        /**
         * Click on delete button on Strategic Goal
         */
        public void clickDeleteStrategicGoal() {
            elementHelper.doClick(deleteButtonStrategicGoal);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
        }
    
        /**
         * Get the number of rows in Strategic goals
         *
         * @return number of rows
         */
        public String getNumberOfRowsInStrategicGoals() {
            return Integer.toString(elementHelper.getElements(numberOfRowsInStrategicGoals).size());
        }
    
        /**
         * Method to click on ManageUserButton in Project Details Page
         */
        public void clickOnManageUser() {
            clickRibbonIcon(RibbonIcons.ManageUsers);
            waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.AddUser));
        }
    
        /**
         * Fetch project value
         *
         * @return - project value
         */
        public Double getProjectValue() {
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            elementHelper.doClick(getPage(PlanningPage.class).projectDetailsTab);
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Edit));
            String projectValueDisplayed = elementHelper.doGetAttribute(getProjectValue, "value");
            String regex = "[^0-9.]";
            projectValueDisplayed = projectValueDisplayed.replaceAll(regex, "");
            return Double.parseDouble(projectValueDisplayed);
        }
    
        /**
         * Method to return the project Name in Project Details Page
         *
         * @return Project Name
         */
        public String getProjectName() {
            return elementHelper.doGetText(projectNameInProjectDetails);
        }
    
        /**
         * Select all the checkbox
         */
        public void selectAllCheckboxInCopy() {
            waitHelper.waitForPageToLoad();
            elementHelper.doClickUsingActions(copyModuleCheckbox);
            elementHelper.doClickUsingActions(copyAttributeCheckbox);
            if (!elementHelper.isCheckBoxSelected(copyFundCheckbox)) {
                elementHelper.doClickUsingActions(copyUserCheckbox);
            }
            if (!elementHelper.isCheckBoxSelected(copyFundCheckbox)) {
                elementHelper.doClickUsingActions(copyFundCheckbox);
            }
        }
    }