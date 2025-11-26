package com.aurigo.masterworks.testframework.webUI.pages.planning;

import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.helper.CustomFieldsHelper;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.AdministrationForms;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.pages.administration.functionalConfigurations.PlanningManagement;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewProjectPage extends RibbonMenu {

    public By projectTypeDrpdwn;
    private WebDriver driver;
    private File file;

    private By projectNameTextArea;
    private By projectCodeTextBox;
    private By ownerTextBox;
    private By endDateInput;
    private By descriptionTextBox;
    private By businessUnitComboBox;
    private By documentFolderStructureDropdown;
    private By documentProperties;
    private By expandAttachments;
    private By uploadDocument;
    private By projectEndDate;
    private By projectStartDate;
    private By projectValueTextBox;
    private By statusDropDown;
    private By dateCreated;
    private By activeCheckbox;
    private By calendarDays;
    private By projectScoreField;
    private By projectNameLabel;
    private By projectCodeLabel;
    private By ownerLabel;
    private By businessUnitLabel;
    private By attributesTab;
    private By statusAndAttributesTable;
    private By statusAndAttributesHeader;
    private By statusAndAttributesGridData;
    private By programDurationInAdministration;
    private By businessUnitTable;
    private By businessUnitSpans;
    private By programYearDropDown;
    private By calendarDropDown;
    private By attributesGrid;
    private By attributesAddButton;
    private By attributesEditButton;
    private By attributesDeleteButton;
    private By attributesExpandButton;
    private By attachmentsDeleteBtn;
    private By attachmentsExpandLabel;
    private By attributeRows;
    private By attributeDropDown;
    private By valueFieldInAttribute;
    private By attributesOkButton;
    private By attributesCancelButton;
    private By isActiveCheckboxInListPage;
    private By modulesGridLabel;
    private By modulesGridExpandButton;
    private By moduleNameColumnInModulesGrid;
    private By webColumnInModulesGrid;
    private By offlineClientColumnInModulesGrid;
    private By modulesGridCheckAllCheckbox;
    private By copyProjectNameField;
    private By copyProjectCodeField;
    private By statusGridID;
    private By masterAttributeGridID;
    private By statusGridAddButton;
    private By statusGridDeleteButton;
    private By statusGridEditButton;
    private By masterAttributeAddButton;
    private By masterAttributeDeleteButton;
    private By masterAttributeEditButton;
    private By statusSaveButton;
    private By statusCloseButton;
    private By statusNameField;
    private By statusRows;
    private By masterAttributeNameField;
    private By masterAttributeRows;
    private By masterAttributeSaveButton;
    private By masterAttributeCloseButton;
    private By attachmentTable;
    private By attachmentTableHeader;
    private By defaultFiscalYearLabel;
    private By defaultFiscalYearSelect;

    String deleteDocumentList = "//*[@class='attachmentCtrlRowClass'][contains(text(),'%s')]";
    String statusRowTemplate = "//table[@id='%s']//td[%s]";
    String statusOrAttributeNameTemplate = "//td[contains(text(),'%s')]";

    public NewProjectPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("NewProjectPage.json");
        projectNameTextArea = locators.get("projectNameTextArea");
        projectCodeTextBox = locators.get("projectCodeTextBox");
        ownerTextBox = locators.get("ownerTextBox");
        endDateInput = locators.get("endDateInput");
        descriptionTextBox = locators.get("descriptionTextBox");
        businessUnitComboBox = locators.get("businessUnitComboBox");
        documentFolderStructureDropdown = locators.get("documentFolderStructureDropdown");
        documentProperties = locators.get("documentProperties");
        expandAttachments = locators.get("expandAttachments");
        uploadDocument = locators.get("uploadDocument");
        projectEndDate = locators.get("projectEndDate");
        projectStartDate = locators.get("projectStartDate");
        projectTypeDrpdwn = locators.get("projectTypeDrpdwn");
        projectValueTextBox = locators.get("projectValueTextBox");
        statusDropDown = locators.get("statusDropDown");
        dateCreated = locators.get("dateCreated");
        activeCheckbox = locators.get("activeCheckbox");
        calendarDays = locators.get("calendarDays");
        projectScoreField = locators.get("projectScoreField");
        projectNameLabel = locators.get("projectNameLabel");
        projectCodeLabel = locators.get("projectCodeLabel");
        ownerLabel = locators.get("ownerLabel");
        businessUnitLabel = locators.get("businessUnitLabel");
        attributesTab = locators.get("attributesTab");
        statusAndAttributesTable = locators.get("statusAndAttributesTable");
        programDurationInAdministration = locators.get("programDurationInAdministration");
        businessUnitTable = locators.get("businessUnitTable");
        businessUnitSpans = locators.get("businessUnitSpans");
        programYearDropDown = locators.get("programYearDropDown");
        calendarDropDown = locators.get("calendarDropDown");
        attributesGrid = locators.get("attributesGrid");
        attributesAddButton = locators.get("attributesAddButton");
        attributesEditButton = locators.get("attributesEditButton");
        attributesDeleteButton = locators.get("attributesDeleteButton");
        attributesExpandButton = locators.get("attributesExpandButton");
        attachmentsDeleteBtn = locators.get("attachmentsDeleteBtn");
        attachmentsExpandLabel = locators.get("attachmentsExpandLabel");
        attributeRows = locators.get("attributeRows");
        attributeDropDown = locators.get("attributeDropDown");
        valueFieldInAttribute = locators.get("valueFieldInAttribute");
        attributesOkButton = locators.get("attributesOkButton");
        attributesCancelButton = locators.get("attributesCancelButton");
        isActiveCheckboxInListPage = locators.get("isActiveCheckboxInListPage");
        modulesGridLabel = locators.get("modulesGridLabel");
        modulesGridExpandButton = locators.get("modulesGridExpandButton");
        moduleNameColumnInModulesGrid = locators.get("moduleNameColumnInModulesGrid");
        webColumnInModulesGrid = locators.get("webColumnInModulesGrid");
        offlineClientColumnInModulesGrid = locators.get("offlineClientColumnInModulesGrid");
        modulesGridCheckAllCheckbox = locators.get("modulesGridCheckAllCheckbox");
        copyProjectNameField = locators.get("copyProjectNameField");
        copyProjectCodeField = locators.get("copyProjectCodeField");
        statusGridID = locators.get("statusGridID");
        masterAttributeGridID = locators.get("masterAttributeGridID");
        statusGridAddButton = locators.get("statusGridAddButton");
        statusGridDeleteButton = locators.get("statusGridDeleteButton");
        statusGridEditButton = locators.get("statusGridEditButton");
        masterAttributeAddButton = locators.get("masterAttributeAddButton");
        masterAttributeDeleteButton = locators.get("masterAttributeDeleteButton");
        masterAttributeEditButton = locators.get("masterAttributeEditButton");
        statusSaveButton = locators.get("statusSaveButton");
        statusCloseButton = locators.get("statusCloseButton");
        statusNameField = locators.get("statusNameField");
        statusRows = locators.get("statusRows");
        masterAttributeNameField = locators.get("masterAttributeNameField");
        masterAttributeRows = locators.get("masterAttributeRows");
        masterAttributeSaveButton = locators.get("masterAttributeSaveButton");
        masterAttributeCloseButton = locators.get("masterAttributeCloseButton");
        attachmentTable = locators.get("attachmentTable");
        attachmentTableHeader = locators.get("attachmentTableHeader");
        defaultFiscalYearLabel = locators.get("defaultFiscalYearLabel");
        defaultFiscalYearSelect = locators.get("defaultFiscalYearSelect");
        statusAndAttributesHeader = locators.get("statusAndAttributesHeader");
        statusAndAttributesGridData = locators.get("statusAndAttributesGridData");

    }

    /**
     * To get the list of locators of fields
     *
     * @return list of locators
     */
    private List<By> getListOfFieldXpaths() {
        var list = new ArrayList<By>();
        list.add(projectNameTextArea);
        list.add(projectCodeTextBox);
        list.add(ownerTextBox);
        list.add(statusDropDown);
        list.add(businessUnitComboBox);
        list.add(dateCreated);
        list.add(activeCheckbox);
        list.add(projectStartDate);
        list.add(projectEndDate);
        list.add(calendarDays);
        list.add(descriptionTextBox);
        list.add(projectValueTextBox);
        list.add(projectScoreField);

        return list;
    }

    /**
     * Method to get the Xpaths of required labels
     *
     * @return list of xpaths for Required! labels
     */
    private List<By> mandatoryFieldsRequiredLabels() {
        ArrayList<By> labels = new ArrayList<>();
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(businessUnitLabel));
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(ownerLabel));
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(projectCodeLabel));
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(projectNameLabel));

        return labels;
    }

    /**
     * To get list of locators of attributes grid
     *
     * @return list of locators
     */
    private List<By> attributeGridButtons() {
        ArrayList<By> buttons = new ArrayList<>();
        buttons.add(attributesAddButton);
        buttons.add(attributesEditButton);
        buttons.add(attributesDeleteButton);

        return buttons;
    }

    /**
     * To get list of locators of modules grid
     *
     * @return list of locators
     */
    private List<By> modulesGridLocators() {
        ArrayList<By> locators = new ArrayList<>();
        locators.add(modulesGridCheckAllCheckbox);
        locators.add(moduleNameColumnInModulesGrid);
        locators.add(webColumnInModulesGrid);
        locators.add(offlineClientColumnInModulesGrid);

        return locators;
    }

    /**
     * To get list of locators of Status and attributes page
     *
     * @return list of locators
     */
    private List<By> statusAndAttributesPageButtons() {
        ArrayList<By> locators = new ArrayList<>();
        locators.add(statusGridAddButton);
        locators.add(statusGridDeleteButton);
        locators.add(statusGridEditButton);
        locators.add(masterAttributeDeleteButton);
        locators.add(masterAttributeAddButton);
        locators.add(masterAttributeEditButton);

        return locators;
    }

    /**
     * Inserting dat for project fields
     *
     * @param projectData - the project data to be inserted
     */
    public void insertProjectData(Project projectData) {
        logger().info("Project Name: " + projectData.name);
        waitHelper.waitForPageToLoad(projectNameTextArea);
        elementHelper.doSendKeys(projectNameTextArea, projectData.name);
        logger().info("Project Code: " + projectData.code);
        elementHelper.doSendKeys(projectCodeTextBox, projectData.code);
        elementHelper.doSendKeys(ownerTextBox, projectData.owner);
        JavaScriptUtil.sendKeysUsingJS(driver, projectEndDate, projectData.endDate);
        elementHelper.doClick(projectNameTextArea);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        elementHelper.doClick(endDateInput);
        elementHelper.doSendKeys(descriptionTextBox, projectData.description);
        getPage(CustomFieldsHelper.class).SelectBusinessUnitForProject(businessUnitComboBox, projectData.businessUnit);
    }

    /**
     * Creates a new project and returns the project details
     *
     * @param projectData Project Details
     * @return Project
     */
    public Project createProject(Project projectData) {
        insertProjectData(projectData);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        return projectData;
    }

    /**
     * Method to create a Project with DocFolder Structure
     *
     * @param projectData Project Data
     * @return Project
     */
    public Project createProjectWithDocStructure(Project projectData) {
        insertProjectData(projectData);
        elementHelper.selectComboBoxItemByText(documentFolderStructureDropdown, projectData.documentFolderName);
        elementHelper.selectComboBoxItemByText(documentProperties, projectData.documentPropertyName);
        clickRibbonIcon(RibbonIcons.Save);
        return projectData;
    }

    /**
     * Method to create a Project with Doc Folder Structure
     *
     * @param projectData Project Data
     * @return Project
     */
    public Project createProjectWithDocFolderStructure(Project projectData) {
        insertProjectData(projectData);
        elementHelper.selectComboBoxItemByText(documentFolderStructureDropdown, projectData.documentFolderName);
        clickRibbonIcon(RibbonIcons.Save);
        return projectData;

    }

    /**
     * Create a Project with Project Type
     *
     * @param projectData Project Data
     * @return Project
     */
    public Project createProjectWithProjectType(Project projectData) {
        insertProjectData(projectData);
        elementHelper.selectComboBoxItemByText(projectTypeDrpdwn, projectData.projectType);
        clickRibbonIcon(RibbonIcons.Save);
        return projectData;
    }

    /**
     * Method verifies the Project Type DropDown
     *
     * @return true
     */
    public boolean isProjectTypeDropdownDisplayed() {
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        return elementHelper.isElementDisplayed(projectTypeDrpdwn);
    }

    /**
     * Method to add document
     *
     * @param fileName                name of the file
     * @param waitForToastToDisappear if true, method will wait for toast to disappear before next step
     * @param randomData              randomData to add
     */
    private void uploadDocument(String fileName, boolean waitForToastToDisappear, String randomData) {
        try {
            file = FileHelper.createFileWithRandomData(fileName, randomData);
            elementHelper.doSendKeys(uploadDocument, file.getAbsolutePath());
            waitHelper.waitForPageToLoad();
            if (waitForToastToDisappear) {
                getPage(ToastUtil.class).waitForToastDisappears();
            }
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    /**
     * Method to add document
     *
     * @param randomData              random data
     * @param waitForToastToDisappear if true, method will wait for toast to disappear before next step
     * @param fileNames               name of the file(s)
     */
    public void uploadDocuments(String randomData, boolean waitForToastToDisappear, String... fileNames) {
        navigation.switchFrameToContent();
        elementHelper.scrollToView(attachmentsExpandLabel);
        waitHelper.waitForPageToLoad(attachmentsExpandLabel);
        if (!elementHelper.isElementDisplayed(attachmentsDeleteBtn)) {
            elementHelper.doClick(expandAttachments);
        }
        waitHelper.waitForPageTabHeaderToBeClickable();
        elementHelper.scrollToView(uploadDocument);
        for (String fileName : fileNames) {
            uploadDocument(fileName, waitForToastToDisappear, randomData);
        }
    }

    /**
     * Get toast message
     *
     * @return toast message
     */
    public String getToastMessage() {
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
    }

    /**
     * Validate the fields in the form
     *
     * @return true on validation of the above
     */
    public boolean validateFieldsInForm() {
        return getPage(GenericForm.class).validateElementsExists(getListOfFieldXpaths());
    }

    /**
     * Validate the mandatory fields of the form
     *
     * @return true on validation of the above
     */
    public boolean validateMandatoryRequiredLabels() {
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForElementPresent(projectNameLabel);
        return getPage(GenericForm.class).validateElementsExists(mandatoryFieldsRequiredLabels());
    }

    /**
     * Get the values of status from the pop up
     *
     * @return List of status options
     */
    public List<String> getStatusValuesFromTable() {
        List<String> data = new ArrayList<>();
        clickRibbonIcon(RibbonIcons.Settings);
        waitHelper.waitForPageToLoad(statusAndAttributesTable);
        Integer columnIndex = getPage(GenericFormProposed.class).getColumnIndex(NewProject.Status.getValue(), statusAndAttributesHeader, GridType.DocGrid);
        var rowsXpath = By.xpath(String.format(statusRowTemplate, elementHelper.getLocatorAsString(statusAndAttributesGridData), columnIndex));
        var cells = elementHelper.getElements(rowsXpath);
        cells.forEach(a -> data.add(a.getText()));
        return data;
    }

    /**
     * Get the list of drop down values from library
     *
     * @param pageName                    page name
     * @param columnName                  column name
     * @param isNavigateToLibraryRequired true if navigation to library is required
     * @param isActive                    true if only the active records have to be fetched
     * @param isPublished                 true if published records have to be fetched
     * @return list of options
     */
    public List<String> getDropDownValuesFromLibrary(String pageName, String columnName, boolean isNavigateToLibraryRequired,
                                                     boolean isActive, boolean isPublished) {
        if (isNavigateToLibraryRequired) {
            navigation.navigateToModulePageByName("Library");
        }
        navigation.navigateToFormInLeftPaneTree(pageName);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        if (isActive) {
            getPage(ListPage.class).filterCheckBoxColumn(CalendarColumns.IsActive.getValue(), true, ListPageCheckBoxFilterOptions.EqualTo.getValue());
        }
        if (isPublished) {
            getPage(ListPage.class).filterCheckBoxColumn(WorkFlowStatus.Published.getValue(), true, ListPageCheckBoxFilterOptions.EqualTo.getValue());
        }
        return getPage(ListPage.class).getColumnData(columnName);
    }

    /**
     * Validate the drop down in the New planned project form
     *
     * @param listFromLibrary list of options from library
     * @param dropDownID      drop down ID
     * @return true on validation of the above
     */
    public boolean validateDropDown(List<String> listFromLibrary, By dropDownID) {
        elementHelper.waitForElementClickable(dropDownID);
        var dropdownOptions = elementHelper.getComboBoxSelectOptions(dropDownID);
        var optionsText = new ArrayList<String>();
        dropdownOptions.forEach(a -> {
            if (!a.getText().equals("Select One")) {
                optionsText.add(a.getText());
            }
        });
        return optionsText.containsAll(listFromLibrary);
    }

    /**
     * Validate the required drop down from the available list
     *
     * @param listFromLibrary list of options from library
     * @param dropDownName    name of the drop down
     * @return true on validating the required drop down
     */
    public boolean validateDropDown(List<String> listFromLibrary, String dropDownName) {
        dropDownName = dropDownName.toLowerCase();
        boolean validated = false;
        switch (dropDownName) {
            case "status":
                validated = validateDropDown(listFromLibrary, statusDropDown);
                break;
            case "calendar":
                validated = validateDropDown(listFromLibrary, calendarDropDown);
                break;
            case "document properties":
                validated = validateDropDown(listFromLibrary, documentProperties);
                break;
            case "document folder structure":
                validated = validateDropDown(listFromLibrary, documentFolderStructureDropdown);
                break;
            default:
                logger().info("Please enter a valid drop down name");
        }
        return validated;
    }

    /**
     * To get the Program Year duration allowed in administration
     *
     * @return the value of the Program Year Duration
     */
    public String getProgramYearValueInPlanningManagement() {
        logger().info("Fetching the program year value in planning management");
        navigation.navigateToModulePageByName("Administration");
        navigation.navigateToFormInLeftPaneTree("Planning Management");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        return getPage(PlanningManagement.class).getProgramCycleConfigurations(ProgramCycleConfigurations.ProgramDuration, Constants.EMPTY_STRING);
    }

    /**
     * Navigate to Business units in administration
     */
    public void navigateToBusinessUnitsInAdministration() {
        logger().info("Navigating to Business Units in administration");
        navigation.navigateToFormInLeftPaneTree(AdministrationForms.BusinessUnit.getValue());
    }

    /**
     * To get the list of Business Units present in Administration
     *
     * @return List of Business Units
     */
    public List<String> getListOfBusinessUnitsFromListPage() {
        logger().info("Fetching the list of Business Units");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        return getPage(ListPage.class).getColumnDataFromAllPages("Business Unit", false);
    }

    /**
     * Validate the Business Unit Drop down
     *
     * @param businessUnitOptionsFromAdministration business units from administration
     * @return true on validation of the above
     */
    public boolean validateBusinessUnitsDropDown(List<String> businessUnitOptionsFromAdministration) {
        logger().info("Validate Business unit drop down");
        waitHelper.waitForPageToLoad(businessUnitComboBox);
        elementHelper.doClick(businessUnitComboBox);
        waitHelper.waitForElementPresent(businessUnitTable);
        var businessUnitValuesInDropDown = new ArrayList<>();
        List<WebElement> businessUnitOptions;
        businessUnitOptions = elementHelper.getElements(businessUnitSpans);
        for (int a = 0; a < 5; a++) {
            if (businessUnitOptions.size() == 0) {
                businessUnitOptions = elementHelper.getElements(businessUnitSpans);
            } else break;
        }
        waitHelper.waitForPageToLoad();
        businessUnitOptions.forEach(a -> businessUnitValuesInDropDown.add(a.getText()));
        var list = businessUnitValuesInDropDown.subList(0, 4);
        return businessUnitOptionsFromAdministration.containsAll(list);
    }

    /**
     * Validate the Program year drop down
     *
     * @param programDurationInAdministration program year duration from administration
     * @return true on validation of the above
     */
    public boolean validateProgramYearDropDown(String programDurationInAdministration, String timeZone, String dateFormat) {
        return getPage(GenericForm.class).validateProgramYearDropDown(programDurationInAdministration, timeZone, dateFormat, programYearDropDown);
    }

    /**
     * Validate attribute grid
     *
     * @return true on validation of the above
     */
    public SoftAssert validateAttributesGrid() {
        var softAssert = new SoftAssert();
        elementHelper.scrollToView(attributesExpandButton);
        elementHelper.doClick(attributesExpandButton);
        waitHelper.waitForElementPresent(attributesGrid);
        softAssert.assertTrue(getPage(GenericFormProposed.class).getGridHeaders(GridType.RadGrid, attributesGrid).containsAll(AttributesGridColumn.getList()), "Attributes Grid Column validation");
        softAssert.assertTrue(getPage(GenericForm.class).validateElementsExists(attributeGridButtons()), "Attributes grid buttons validation");
        return softAssert;
    }

    /**
     * Add attributes in project details page
     *
     * @return name of the added attribute
     */
    public String addAttribute() {
        getPage(GenericForm.class).clickEdit();
        waitHelper.waitForPageToLoad(attributesExpandButton);
        elementHelper.doClick(attributesExpandButton);
        waitHelper.waitForElementClickable(attributesAddButton);
        elementHelper.doClick(attributesAddButton);
        waitHelper.waitForElementClickable(attributeDropDown);
        var defaultAttribute = elementHelper.getFirstSelectedOption(attributeDropDown);
        waitHelper.waitForPageToLoad(attributesOkButton);
        elementHelper.doClick(attributesOkButton);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Edit));
        return defaultAttribute;
    }

    /**
     * Edit the added attribute
     *
     * @param attributeName name of the attribute to be edited
     * @return edited name
     */
    public String editAttribute(String attributeName) {
        getPage(GenericForm.class).clickEdit();
        waitHelper.waitForPageToLoad(attributesExpandButton);
        elementHelper.doClick(attributesExpandButton);
        waitHelper.waitForElementClickable(attributesAddButton);
        var attributeRow = By.xpath(String.format(statusOrAttributeNameTemplate, attributeName));
        elementHelper.doDoubleClick(attributeRow);
        elementHelper.doClick(attributesEditButton);
        elementHelper.scrollToView(valueFieldInAttribute);
        waitHelper.waitForElementClickable(valueFieldInAttribute);
        var attributeValue = "- edited";
        elementHelper.doSendKeys(valueFieldInAttribute, attributeValue);
        elementHelper.doClick(attributesOkButton);
        waitHelper.waitForPageTabHeaderToBeClickable();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Edit));
        return attributeValue;
    }

    /**
     * Delete the mentioned attribute
     *
     * @param attributeName attribute name to be deleted
     */
    public void deleteAttribute(String attributeName) {
        getPage(GenericForm.class).clickEdit();
        waitHelper.waitForPageToLoad(attributesExpandButton);
        elementHelper.doClick(attributesExpandButton);
        waitHelper.waitForElementClickable(attributesAddButton);
        var attributeRow = By.xpath(String.format(statusOrAttributeNameTemplate, attributeName));
        elementHelper.scrollToView(attributeRow);
        elementHelper.doDoubleClick(attributeRow);
        elementHelper.scrollToView(attachmentsExpandLabel);
        waitHelper.waitForPageToLoad(attributesDeleteButton);
        elementHelper.doClick(attributesDeleteButton);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForElementClickable(attributesAddButton);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Edit));
    }

    /**
     * validate the presence of attribute
     *
     * @param attributeName name of the attribute
     * @return true on validation of the above
     */
    public boolean validateAttribute(String attributeName) {
        waitHelper.waitForPageToLoad(attributesExpandButton);
        elementHelper.doClick(attributesExpandButton);
        waitHelper.waitForElementPresent(attributesGrid);
        var attributeRow = By.xpath(String.format(statusOrAttributeNameTemplate, attributeName));
        return getPage(Validations.class).verifyElementExists(attributeRow);
    }

    /**
     * Make a project active or inactive
     *
     * @param projectName project name
     */
    public void makeProjectActiveOrInactive(String projectName) {
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        getPage(ListPage.class).singleClickOnRowListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName);
        clickRibbonIcon(RibbonIcons.MarkActiveAndInactiveInProjectsOrUserAccountsListPage);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
    }

    /**
     * Validate modules grid locators
     *
     * @return true on validation of the above
     */
    public boolean validateModulesGrid() {
        getPage(GenericForm.class).clickEdit();
        elementHelper.scrollToView(modulesGridExpandButton);
        elementHelper.doClick(modulesGridExpandButton);
        elementHelper.scrollToView(modulesGridCheckAllCheckbox);
        return getPage(GenericForm.class).validateElementsExists(modulesGridLocators());
    }

    /**
     * Copy Planned project
     *
     * @param projectName name of the project
     * @return updated project name for copied project
     */
    public String copyCreatedPlannedProject(Project projectName) {
        getPage(ListPage.class).filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName.name, ListPageFilterOptions.Contains);
        getPage(ListPage.class).singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.Copy);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad(copyProjectNameField);
        var copiedPrjName = TestDataUtil.getRandomName();
        var copiedPrjCode = projectName.code + "-copy";
        elementHelper.doSendKeys(copyProjectNameField, copiedPrjName);
        elementHelper.doSendKeys(copyProjectCodeField, copiedPrjCode);
        clickRibbonIcon(RibbonIcons.Save);
        return copiedPrjName;
    }

    /**
     * Validate status checkbox of planned project
     *
     * @return true on validation of the above
     */
    public boolean ValidateStatusCheckboxOfPlannedProject() {
        waitHelper.waitForElementPresent(isActiveCheckboxInListPage);
        var checkedAttribute = elementHelper.doGetAttribute(isActiveCheckboxInListPage, "checked");
        return checkedAttribute != null;
    }

    /**
     * Validate the locators and columns of Status and Attributes page
     *
     * @return true on validation of the above
     */
    public SoftAssert validateStatusAndAttributesPage() {
        var softAssert = new SoftAssert();
        clickRibbonIcon(RibbonIcons.Settings);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Cancel));

        softAssert.assertTrue(getPage(GenericFormProposed.class).getGridHeaders(GridType.RadGrid, statusGridID).containsAll(StatusColumnList.getList()), "Validate status columns");

        softAssert.assertTrue(getPage(GenericFormProposed.class).getGridHeaders(GridType.RadGrid, masterAttributeGridID).containsAll(MasterAttributeColumnList.getList()), "Validate master attribute columns");

        softAssert.assertTrue(getPage(GenericForm.class).validateElementsExists(statusAndAttributesPageButtons()), "Validate buttons");
        return softAssert;
    }

    /**
     * Add status in status and attributes page
     *
     * @param statusName status name
     */
    public void addStatusInStatusAndAttributesPage(String statusName) {
        waitHelper.waitForElementClickable(statusGridAddButton);
        elementHelper.doClick(statusGridAddButton);
        elementHelper.doSendKeys(statusNameField, statusName);
        elementHelper.doClick(statusSaveButton);
        waitHelper.waitForPageToLoad(statusGridAddButton);
    }

    /**
     * Edit the status in status and attributes page
     *
     * @param statusName status name
     * @return edited name
     */
    public String editStatusInStatusGrid(String statusName) {
        waitHelper.waitForElementClickable(statusGridEditButton);
        var statusRow = By.xpath(String.format(statusOrAttributeNameTemplate, statusName));
        elementHelper.doDoubleClick(statusRow);
        elementHelper.doClick(statusGridEditButton);
        var editedStatusName = statusName + " -edited";
        elementHelper.scrollToView(statusNameField);
        elementHelper.doSendKeys(statusNameField, editedStatusName);
        elementHelper.doClick(statusSaveButton);
        waitHelper.waitForPageToLoad(statusGridEditButton);
        return editedStatusName;
    }

    /**
     * Delete the status from status and attributes page
     *
     * @param statusName name of the status to be deleted
     */
    public void deleteStatusInStatusGrid(String statusName) {
        waitHelper.waitForElementClickable(statusGridDeleteButton);
        var statusRow = By.xpath(String.format(statusOrAttributeNameTemplate, statusName));
        elementHelper.doDoubleClick(statusRow);
        elementHelper.doClick(statusGridDeleteButton);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad(statusGridAddButton);
    }

    /**
     * Add master attribute in status and attributes page
     *
     * @param masterAttributeName master attribute name
     */
    public void addMasterAttributeInStatusAndAttributesPage(String masterAttributeName) {
        waitHelper.waitForElementClickable(masterAttributeAddButton);
        elementHelper.doClick(masterAttributeAddButton);
        elementHelper.doSendKeys(masterAttributeNameField, masterAttributeName);
        waitHelper.waitForElementClickable(masterAttributeSaveButton);
        elementHelper.doClick(masterAttributeSaveButton);
        waitHelper.waitForPageToLoad(masterAttributeAddButton);
    }

    /**
     * Edit the master attribute in status and attributes page
     *
     * @param masterAttributeName master attribute name
     * @return edited name
     */
    public String editMasterAttributeInStatusGrid(String masterAttributeName) {
        waitHelper.waitForElementClickable(masterAttributeEditButton);
        var MasterAttributeRow = By.xpath(String.format(statusOrAttributeNameTemplate, masterAttributeName));
        elementHelper.doDoubleClick(MasterAttributeRow);
        elementHelper.doClick(masterAttributeEditButton);
        var editedMasterAttributeName = masterAttributeName + " -edited";
        waitHelper.waitForPageToLoad(masterAttributeNameField);
        elementHelper.scrollToView(masterAttributeNameField);
        elementHelper.doSendKeys(masterAttributeNameField, editedMasterAttributeName);
        elementHelper.doClick(masterAttributeSaveButton);
        waitHelper.waitForPageToLoad(masterAttributeEditButton);
        return editedMasterAttributeName;
    }

    /**
     * Delete the master attribute
     *
     * @param masterAttributeName name of the master attribute
     */
    public void deleteMasterAttributeInStatusGrid(String masterAttributeName) {
        waitHelper.waitForElementClickable(masterAttributeDeleteButton);
        var MasterAttributeRow = By.xpath(String.format(statusOrAttributeNameTemplate, masterAttributeName));
        elementHelper.doDoubleClick(MasterAttributeRow);
        elementHelper.doClick(masterAttributeDeleteButton);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad(masterAttributeAddButton);
    }

    /**
     * Validate the status and attributes
     *
     * @param parameter parameter to be validated
     * @return true on validation of above
     */
    public boolean validateStatusOrAttributesInStatusAndAttributesPage(String parameter) {
        var name = By.xpath(String.format(statusOrAttributeNameTemplate, parameter));
        return getPage(Validations.class).verifyElementExists(name);
    }

    /**
     * Validate navigating back to list page
     *
     * @return true on validation of above
     */
    public boolean validateNavigatingBackToListPage() {
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        getPage(GenericForm.class).clickCancel();
        return validateRibbonIcon(RibbonIcons.New);
    }

    /**
     * Validate creation of project
     *
     * @param project project details
     * @return false if duplicate project creation is allowed
     */
    public boolean validateCreationOfProject(Project project) {
        insertProjectData(project);
        clickRibbonIcon(RibbonIcons.Save);
        return !getPage(ToastUtil.class).validatePresenceOfToastMessage();
    }

    /**
     * Create a project with project Value Field
     *
     * @param projectValue project Value
     * @param projectData  Project Data
     * @return true if saved successfully
     */
    public boolean createProjectWithProjectValue(String projectValue, Project projectData) {
        insertProjectData(projectData);
        elementHelper.waitAndSetText(projectValueTextBox, projectValue);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Delete the attachment by clicking on the filename
     *
     * @param fileName Name of the file to delete
     * @param userName Username of the user who added the document
     * @return true if the attachment is deleted
     */
    public boolean deleteAttachment(String fileName, String userName) {
        waitHelper.waitForPageToLoad();
        if (!elementHelper.isElementDisplayed(attachmentsDeleteBtn)) {
            elementHelper.doClick(expandAttachments);
        }
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(getPage(GenericForm.class).getRowFromRadGrid(attachmentTable, attachmentTableHeader, AttachmentColumn.DocumentName.getValue(), fileName).findElement(By.xpath("//td[text()='" + userName + "']")));
        elementHelper.doClick(attachmentsDeleteBtn);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        boolean obtainedOutput = getPage(Validations.class).verifyElementExists(By.xpath(String.format(deleteDocumentList, fileName)));
        getPage(GenericForm.class).clickSave(false);
        return obtainedOutput;
    }

    /**
     * Edits Project name and owner name
     *
     * @param ProjectName     is the name of the project to be edited
     * @param EditProjectName is the edited new name of the project
     * @param ProjectOwner    is the new edited owner of the project
     */
    public void editProject(String ProjectName, String EditProjectName, String ProjectOwner) {
        getPage(ListPage.class).singleClickOnRowListPage(PlannedProjectListPageHeader.ProjectName.getValue(), ProjectName);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(projectNameTextArea, EditProjectName);
        elementHelper.doSendKeys(ownerTextBox, ProjectOwner);
        getPage(GenericForm.class).clickSave();
    }

}