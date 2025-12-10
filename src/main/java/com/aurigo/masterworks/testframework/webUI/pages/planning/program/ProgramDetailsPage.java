package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields.WorkflowOptions;
import com.aurigo.masterworks.testframework.webUI.generic.*;
import com.aurigo.masterworks.testframework.webUI.pages.planning.ProjectDetailsPage;
import com.aurigo.masterworks.testframework.webUI.testData.Program;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.util.Strings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramDetailsPage extends RibbonMenu {
    private final By programTitleTxtBox;
    private final By programYearDropDown;
    private final By programCategoryDropDown;
    private final By programDescriptionTxtArea;
    private final By programBudgetTxtBox;
    private final By addPlannedProjectsBtn;
    private final By plannedProjectPicker;
    private final By projectCostField;
    private final By projectInflatedCostField;
    private final By projectsGrid;
    private final By projectsGridData;
    private final By programsGridData;
    private final By plannedProjectPickerId;
    private final By projectDynamicGridIdInProgram;
    private final By deletePlannedProjectsBtn;
    private final By projectGridTableId;
    private final By projectGridHeaderId;
    private final By totalProgramCost;
    private final By programForecastDetailsTab;
    private final By programDetailsTab;
    private final By fundSourceProgramSelectButton;
    private final By fundPicker;
    private final By fundPickerId;
    private final By forecastCPHeader;
    private final By dataTableProgramForecast;
    private final By frozenDataTableProgramForecast;
    private final By programDetailsShowSummaryButton;
    private final By contingencyProgramTypeLabel;
    private final By addProgramsButton;
    private final By programPicker;
    private final By minCostField;
    private final By maxCostField;
    private final By retrieveProjectsButton;
    private final By programNameLabel;
    private final By programYearLabel;
    private final By minCostEnteredValue;
    private final By maxCostEnteredValue;
    private final By regularProgramTypeLabel;
    private final By totalProgramCostViewMode;
    private final By programGridHeader;
    private final By programBudgetGridHeader;
    private final By programBudgetGrid;
    private final By pickerHeader;
    private final By deleteProgramsBtn;
    private final By programCode;
    private final By selectedProgramType;
    private final By programTypes;
    private final By attachmentsGrid;
    private final By attachmentsGridData;
    private final By downloadAttachment;
    private final By uploadDocument;
    private final By attachmentsGridHeader;
    private final By programPickerId;
    private final By programsGrid;
    private final By programBudgetGridData;
    private final By remainingBudget;
    private final By clearProgramBudgetGrid;
    private final By defaultFiscalYearLabel;
    private final By defaultFiscalYearSelect;
    private final By attachmentGridHeaderDetails;
    private final By plannedProjectGirdTotal;
    private final By fiscalYearSelected;


    private String programCategoryDataFieldValue = "ProgramCategory";
    private String name = "Name";
    private String fundSource = "Fund Source";

    private List<String> projectStatus = new ArrayList<String>();

    private WebDriver driver;

    public ProgramDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        projectStatus.add("Published");
        projectStatus.add("Pre-Award");

        var locators = LocatorUtil.getLocators("ProgramDetailsPage.json");
        programTitleTxtBox = locators.get("programTitleTxtBox");
        programYearDropDown = locators.get("programYearDropDown");
        programCategoryDropDown = locators.get("programCategoryDropDown");
        programDescriptionTxtArea = locators.get("programDescriptionTxtArea");
        programBudgetTxtBox = locators.get("programBudgetTxtBox");
        addPlannedProjectsBtn = locators.get("addPlannedProjectsBtn");
        plannedProjectPicker = locators.get("plannedProjectPicker");
        projectCostField = locators.get("projectCostField");
        projectsGrid = locators.get("projectsGrid");
        projectsGridData = locators.get("projectsGridData");
        programsGridData = locators.get("programsGridData");
        programsGrid = locators.get("programsGrid");
        projectInflatedCostField = locators.get("projectInflatedCostField");
        plannedProjectPickerId = locators.get("plannedProjectPickerId");
        projectDynamicGridIdInProgram = locators.get("projectDynamicGridIdInProgram");
        deletePlannedProjectsBtn = locators.get("deletePlannedProjectsBtn");
        deleteProgramsBtn = locators.get("deleteProgramsBtn");
        projectGridTableId = locators.get("projectGridTableId");
        projectGridHeaderId = locators.get("projectGridHeaderId");
        totalProgramCost = locators.get("totalProgramCost");
        programForecastDetailsTab = locators.get("programForecastDetailsTab");
        fundSourceProgramSelectButton = locators.get("fundSourceProgramSelectButton");
        fundPicker = locators.get("fundPicker");
        fundPickerId = locators.get("fundPickerId");
        forecastCPHeader = locators.get("forecastCPHeader");
        dataTableProgramForecast = locators.get("dataTableProgramForecast");
        programDetailsShowSummaryButton = locators.get("programDetailsShowSummaryButton");
        contingencyProgramTypeLabel = locators.get("contingencyProgramTypeLabel");
        addProgramsButton = locators.get("addProgramsButton");
        programPicker = locators.get("programPicker");
        minCostField = locators.get("minCostField");
        maxCostField = locators.get("maxCostField");
        retrieveProjectsButton = locators.get("retrieveProjectsButton");
        programNameLabel = locators.get("programNameLabel");
        programYearLabel = locators.get("programYearLabel");
        minCostEnteredValue = locators.get("minCostEnteredValue");
        maxCostEnteredValue = locators.get("maxCostEnteredValue");
        regularProgramTypeLabel = locators.get("regularProgramTypeLabel");
        totalProgramCostViewMode = locators.get("totalProgramCostViewMode");
        programGridHeader = locators.get("programGridHeader");
        programBudgetGridHeader = locators.get("programBudgetGridHeader");
        programBudgetGrid = locators.get("programBudgetGrid");
        pickerHeader = locators.get("pickerHeader");
        programCode = locators.get("programCode");
        selectedProgramType = locators.get("programType");
        programTypes = locators.get("programTypes");
        attachmentsGrid = locators.get("attachmentsGrid");
        downloadAttachment = locators.get("downloadAttachment");
        uploadDocument = locators.get("uploadDocument");
        attachmentsGridHeader = locators.get("attachmentsGridHeader");
        attachmentsGridData = locators.get("attachmentsGridData");
        programPickerId = locators.get("programPickerId");
        defaultFiscalYearLabel = locators.get("defaultFiscalYearLabel");
        defaultFiscalYearSelect = locators.get("defaultFiscalYearSelectInProgram");
        programBudgetGridData = locators.get("programBudgetGridData");
        remainingBudget = locators.get("remainingBudget");
        clearProgramBudgetGrid = locators.get("clearProgramBudgetGrid");
        attachmentGridHeaderDetails = locators.get("attachmentGridHeaderDetails");
        frozenDataTableProgramForecast = locators.get("frozenDataTableProgramForecast");
        plannedProjectGirdTotal = locators.get("plannedProjectGirdTotal");
        programDetailsTab = locators.get("programDetailsTab");
        fiscalYearSelected = locators.get("fiscalYearSelected");
    }

    /**
     * Validating presence of required labels
     *
     * @return True if all labels in list are mandatory
     */
    public boolean validateMandatoryFields() {
        List<By> labels = new ArrayList<>();
        labels.add(getPage(GenericLibrary.class).getRequiredAsteriskXpath(programNameLabel));
        labels.add(getPage(GenericLibrary.class).getRequiredAsteriskXpath(programYearLabel));
        return getPage(GenericForm.class).verifyRequiredWarningLabels(labels);
    }

    /**
     * Get list of locators for cost fields
     *
     * @return list of locators
     */
    private List<By> getCostFields() {
        var list = new ArrayList<By>();
        list.add(projectCostField);
        list.add(minCostField);
        list.add(maxCostField);

        return list;
    }

    /**
     * Method to create a new program
     *
     * @param publishedProjectsToAdd list of published projects to be added to the program
     * @param isContingencyProgram   true if the Program Should be Contingency Program
     * @return program data
     */
    public Program createNewProgram(List<String> publishedProjectsToAdd, boolean isContingencyProgram) {
        var program = addProgramData(isContingencyProgram);
        logger().info("Planned projects to add : " + StringUtils.join(publishedProjectsToAdd, ","));
        if (!isContingencyProgram) {
            addPlannedProjects(publishedProjectsToAdd);
        }
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Method to create a new program with number of projects.
     *
     * @param numberOfProjects Number of projects to be added to the program.
     * @return program data
     */
    public Program createNewProgram(int numberOfProjects) {
        var program = addProgramData(false);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        if (getPage(Picker.class).getListRows(plannedProjectPicker).size() < numberOfProjects) {
            logger().info("Created number of project is less than required " + numberOfProjects);
            clickRibbonIcon(RibbonIcons.Cancel);
            return null;
        }
        getPage(Picker.class).filterPickerColumn(plannedProjectPicker,
                ProgramProjectPickerColumns.Name.getValue(), "Auto", PickerFilterOptions.Contains);
        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, ProgramProjectPickerColumns.ProjectStatus.getValue(),
                WorkFlowStatus.Published.getValue(), PickerFilterOptions.Contains);
        getPage(Picker.class).multiSelectByCountOld(numberOfProjects, plannedProjectPicker);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(fundSourceProgramSelectButton);
        waitHelper.waitForPageToLoad(fundPicker);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByRowNumber(1, fundPicker);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Create Program Without Project
     *
     * @return - program data
     */
    public Program createNewProgram() {
        var program = addProgramData(false);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Method to create a new program with number of projects.
     *
     * @param numberOfProjects Number of projects to be added to the program.
     * @param projectState     State of project(s) to be selected
     * @return program data
     */
    public Program createNewProgram(int numberOfProjects, String projectState) {
        var program = addProgramData(false);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        if (getPage(Picker.class).getListRows(plannedProjectPicker).size() < numberOfProjects) {
            logger().info("Created number of project is less than required " + numberOfProjects);
            clickRibbonIcon(RibbonIcons.Cancel);
            return null;
        }

        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, ProgramListPageHeader.Name.getValue(), "Auto", PickerFilterOptions.StartsWith);
        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, "Project Status", projectState, PickerFilterOptions.IsEqualTo);
        getPage(Picker.class).multiSelectByCountOld(numberOfProjects, plannedProjectPicker);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Method to add planned projects in the Projects grid.
     *
     * @param numberOfProjects Number of projects to be added to the projects.
     */
    public void addPlannedProjects(int numberOfProjects) {
        logger().info("Adding a planned project to the Project grid.");
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();

        if (getPage(Picker.class).getListRows(plannedProjectPicker).size() < numberOfProjects) {
            logger().info("Created number of projects is less than required " + numberOfProjects);
            clickRibbonIcon(RibbonIcons.Cancel);
            return;
        }

        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, ProgramListPageHeader.Name.getValue(), "Auto", PickerFilterOptions.StartsWith);
        getPage(Picker.class).multiSelectByCountOld(numberOfProjects, plannedProjectPicker);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Add Program data to new program.
     *
     * @param isContingencyProgram true if it is a Contingency Program
     * @return Program data
     */
    public Program addProgramData(boolean isContingencyProgram) {
        var program = TestDataUtil.generateNewProgramData();
        addProgramData(program, isContingencyProgram);

        return program;
    }

    /**
     * Add Program data to new program.
     *
     * @param program              Program details
     * @param isContingencyProgram true if it is a Contingency Program
     */
    public void addProgramData(Program program, boolean isContingencyProgram) {
        String projectName = getPage(ProjectDetailsPage.class).getProjectName();
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        if (isContingencyProgram) {
            navigation.switchFrameToContent();
            logger().info("Program Type : Contingency");
            JavaScriptUtil.clickElementByJS(elementHelper.getElement(contingencyProgramTypeLabel), driver);
        }
        logger().info("Program Title : " + program.title);
        waitHelper.waitForElementClickable(programTitleTxtBox);
        elementHelper.doSendKeys(programTitleTxtBox, program.title);
        elementHelper.selectComboBoxItemByText(defaultFiscalYearSelect, "State");
        logger().info("Program Year : " + program.year);
        waitHelper.waitForElementClickable(programYearDropDown);
        elementHelper.selectComboBoxItemByText(programYearDropDown, program.year);
        logger().info("Program Category : " + program.category);
        waitHelper.waitForElementClickable(programCategoryDropDown);
        elementHelper.selectComboBoxItemByText(programCategoryDropDown, program.category);
        elementHelper.doSendKeys(programDescriptionTxtArea, program.description);
        JavaScriptUtil.sendKeysUsingJSWithID(driver, elementHelper.getLocatorAsString(programBudgetTxtBox), program.budget);
        getPage(GenericFormProposed.class).selectFiscalYear(defaultFiscalYearSelect);
    }

    /**
     * Create a program without category
     *
     * @param program Program details
     */
    public void createProgramWOCategory(Program program) {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);

        logger().info("Program Title : " + program.title);
        elementHelper.doSendKeys(programTitleTxtBox, program.title);
        logger().info("Program Year : " + program.year);
        elementHelper.selectComboBoxItemByText(programYearDropDown, program.year);
        elementHelper.doSendKeys(programDescriptionTxtArea, program.description);
        JavaScriptUtil.sendKeysUsingJSWithID(driver, elementHelper.getLocatorAsString(programBudgetTxtBox), program.budget);
        getPage(GenericFormProposed.class).selectFiscalYear(defaultFiscalYearSelect);

        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Edit program.
     *
     * @param programTitle title of the program
     */
    public void editProgram(String programTitle) {
        elementHelper.doSendKeys(programTitleTxtBox, programTitle);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.View));
    }

    /**
     * Validation of Toast message Retrieve click without adding Project
     *
     * @param costParameters title of the program
     * @return True if the toast message is available
     */
    public boolean retrieveInput(String[] costParameters) {
        waitHelper.waitForPageToLoad(minCostField);
        elementHelper.doSendKeys(minCostField, costParameters[0]);
        waitHelper.waitForPageToLoad(maxCostField);
        elementHelper.doSendKeys(maxCostField, costParameters[1]);
        elementHelper.doClick(retrieveProjectsButton);
        elementHelper.waitForPageToLoad();
        if (getPage(ToastUtil.class).validatePresenceOfToastMessage()) {
            boolean toast = getPage(ToastUtil.class).waitAndGetMessageForSingleToast().equals(Constants.RETRIEVE_MESSAGE);
            getPage(ToastUtil.class).waitAndCloseForSingleToast();
            elementHelper.waitForPageToLoad();
            return toast;
        } else return false;
    }

    /**
     * Validate Program Ribbon option
     *
     * @return true on Fields available
     */
    public SoftAssert validateProgramRibbon() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(validateRibbonIcon(RibbonIcons.Cancel), "Validates Cancel Button availability");
        softAssert.assertTrue(validateRibbonIcon(RibbonIcons.Save), "Validates Save Button availability");
        clickRibbonIcon(RibbonIcons.Workflow);
        softAssert.assertTrue(getPage(Validations.class).verifyElementExists(By.xpath(String.format(textValidationInRibbonMenu, WorkflowOptions.BallInCourt.getValue()))), "Validates Ball in court Button availability");
        clickRibbonIcon(RibbonIcons.WorkflowAction);
        softAssert.assertTrue(getPage(Validations.class).verifyElementExists(By.xpath(String.format(textValidationInRibbonMenu, WorkflowOptions.Submit.getValue()))), "Validates Submit Button availability");
        return softAssert;
    }

    /**
     * Validate program cost field
     *
     * @return true on validation of the above
     */
    public boolean validateProgramCostFieldAsPerProjectCosts() {
        waitHelper.waitForElementPresent(addPlannedProjectsBtn);
        var costData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectCostGridColumnName.getValue(), projectsGridData, projectGridHeaderId);
        List<Long> costDataValue = new ArrayList<>();
        for (String costDatum : costData) {
            var value = Math.round(Double.parseDouble(costDatum.replaceAll(",", "")));
            costDataValue.add(value);
        }
        Long totalCost = 0L;
        for (Long value : costDataValue) {
            totalCost += value;
        }
        elementHelper.scrollToView(projectCostField);
        var value = elementHelper.doGetAttribute(projectCostField, "value").replaceAll(",", "");
        var costCalculated = Math.round(Double.parseDouble(value));
        return totalCost == (costCalculated);
    }

    /**
     * Get list of Cost and inflated costs for the specified project from Project Picker
     *
     * @param project Project
     * @return list of costs
     */
    public List<Long> getProjectCostsInPicker(Project project) {
        waitHelper.waitForPageToLoad(programCategoryDropDown);
        elementHelper.selectComboBoxItemByText(programCategoryDropDown, project.programCategory);
        elementHelper.scrollToView(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForElementPresent(plannedProjectPicker);
        waitHelper.waitForLoadingSpinnerDisappear();

        var listOfCosts = new ArrayList<Long>();

        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, ProgramListPageHeader.Name.getValue(), project.name, PickerFilterOptions.Contains);
        var cost = getPage(Picker.class).getColumnData(plannedProjectPickerId, ProgramCostColumns.projectCostPickerColumnValue.getValue(), 1);
        var value = Math.round(Double.parseDouble(cost.get(0).replaceAll(",", "").split("\\.")[0]));
        listOfCosts.add(value);
        logger().info(String.format("Cost of %s in the picker is - %s", project.name, value));

        var inflatedCost = getPage(Picker.class).getColumnData(plannedProjectPickerId, ProgramCostColumns.projectInflatedCostPickerColumnValue.getValue(), 1);
        var InflatedCostValue = Math.round(Double.parseDouble(inflatedCost.get(0).replaceAll(",", "").split("\\.")[0]));
        listOfCosts.add(InflatedCostValue);
        logger().info(String.format("Inflated Cost of %s in the picker is - %s", project.name, InflatedCostValue));

        navigation.refreshPage();
        return listOfCosts;
    }

    /**
     * Get the list of Cost and inflated costs of the project added in the Project Grid
     *
     * @return list of costs
     */
    public List<Long> getListOfCostsFromProjectGrid() {
        waitHelper.waitForElementPresent(addPlannedProjectsBtn);
        var costData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectCostGridColumnName.getValue(), projectsGridData, projectGridHeaderId);
        var inflatedCostData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectInflatedCostGridColumnName.getValue(), projectsGridData, projectGridHeaderId);

        var value1 = Math.round(Double.parseDouble(costData.get(0).replaceAll(",", "").split("\\.")[0]));
        var value2 = Math.round(Double.parseDouble(inflatedCostData.get(0).replaceAll(",", "").split("\\.")[0]));

        var listOfCosts = new ArrayList<Long>();
        listOfCosts.add(value1);
        listOfCosts.add(value2);
        return listOfCosts;
    }

    /**
     * Get list of Cost and Inflated Cost of the Program after adding the planned Project
     *
     * @return list of Costs
     */
    public List<Long> getListOfCostsFromProgramDetailsPage() {
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(projectCostField);
        var cost = elementHelper.doGetAttribute(projectCostField, "value").replaceAll(",", "").split("\\.")[0];
        var costValue = Math.round(Double.parseDouble(cost));

        var listOfCosts = new ArrayList<Long>();
        listOfCosts.add(costValue);

        return listOfCosts;
    }

    /**
     * Validate if the project grid has all the projects that should be added
     *
     * @param projectNameList Names of the project that had to be added
     * @return True if all the projects are added
     */
    public boolean validateProjectsAddedToTheGrid(List<String> projectNameList) {
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        List<String> values = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramListPageHeader.Name.getValue(), projectsGridData, projectGridHeaderId);
        return values.containsAll(projectNameList);
    }

    /**
     * Delete a project from projects grid
     *
     * @param projectName Name of the project to select
     */
    public void deleteProjectFromPlannedProjectGrid(String projectName) {
        logger().info("Deleting project from project grid:" + projectName);
        elementHelper.scrollToView(programTitleTxtBox);
        waitHelper.waitForPageToLoad();
        int rowNum = 1;
        if (Strings.isNotNullAndNotEmpty(projectName)) {
            rowNum = getPage(GenericForm.class).getRowIndexRadGrid(projectsGridData, projectGridHeaderId, ProgramListPageHeader.Name.getValue(), projectName);
        }
        elementHelper.doClick(getPage(GenericForm.class).getRowCellsRadGrid(projectsGridData, rowNum).get(1));
        elementHelper.doClick(deletePlannedProjectsBtn);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Delete a program from program grid.
     *
     * @param programName Name of the program to delete
     */
    public void deleteProgram(String programName) {
        logger().info("Deleting program from program grid:" + programName);
        elementHelper.scrollToView(programsGridData);
        int rowNum = 1;
        if (Strings.isNotNullAndNotEmpty(programName)) {
            rowNum = getPage(GenericForm.class).getRowIndexRadGrid(programsGridData, programGridHeader, ProgramListPageHeader.Name.getValue(), programName);
        }
        elementHelper.doClick(getPage(GenericForm.class).getRowCellsRadGrid(programsGridData, rowNum).get(1));
        elementHelper.doClick(deleteProgramsBtn);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get count of records from grid
     *
     * @param gridName grid name
     * @return count of records
     */
    public int getGridCount(ProgramGrids gridName) {
        List<String> content = null;
        int count = 0;
        switch (gridName) {
            case Projects: {
                content = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, name, projectsGridData, projectGridHeaderId);
                break;
            }
            case Programs: {
                content = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, name, programsGridData, programGridHeader);
                break;
            }
            case ProgramBudget: {
                content = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, fundSource, programBudgetGridData, programBudgetGridHeader);
                break;
            }
        }
        if (!content.contains("No records to display.")) {
            count = content.size();
        }
        return count;
    }

    /**
     * get total cost from program page
     *
     * @param programName - name
     * @return - total cost
     */
    public double getTotalCostOfProgramName(String programName) {
        getPage(ListPage.class).filterListPage(ProgramListPageHeader.Name.getValue(), programName, ListPageFilterOptions.EqualTo);
        getPage(ListPage.class).singleClickOnRowListPage(1);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();

        String projectCostDisplayed = elementHelper.doGetAttribute(totalProgramCost, "value");
        String regex = "[^0-9.]";
        projectCostDisplayed = projectCostDisplayed.replaceAll(regex, "");
        return Double.parseDouble(projectCostDisplayed);
    }

    /**
     * get total cost from program page
     *
     * @param programName - name
     * @return - total cost
     */
    public String getTotalCostOfProgram(String programName) {
        getPage(ListPage.class).filterListPage(ProgramListPageHeader.Name.getValue(), programName, ListPageFilterOptions.EqualTo);
        getPage(ListPage.class).singleClickOnRowListPage(1);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        return elementHelper.doGetAttribute(totalProgramCostViewMode, "value");

    }

    /**
     * Get total cost from program page
     *
     * @return - total cost
     */
    public String getTotalCost() {
        return elementHelper.doGetAttribute(totalProgramCostViewMode, "value");
    }

    /**
     * validate item details page in Program
     *
     * @param programName          - program name
     * @param projectCostDisplayed - project cost displayed
     * @return - true if the validation is successful
     */
    public boolean validateProgramItemDetailsPage(double projectCostDisplayed, String programName) {
        getPage(ListPage.class).filterListPage(ProgramListPageHeader.Name.getValue(), programName, ListPageFilterOptions.EqualTo);
        getPage(ListPage.class).singleClickOnRowListPage(1);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();

        clickOnForecastDetailsTab();

        List<String> itemDetailsPageHeaders = itemListPageHeaders();

        List<List<String>> amountsDisplayed = getAllItemListData("Amount in $");
        List<String> totalAmountDisplayedList = amountsDisplayed.get(3);
        String totalAmountDisplayed = totalAmountDisplayedList.get(0);
        String regex = "[^0-9.]";
        totalAmountDisplayed = totalAmountDisplayed.replaceAll(regex, "");
        double amountInDouble = Double.parseDouble(totalAmountDisplayed);

        return (itemDetailsPageHeaders.contains("Before") && itemDetailsPageHeaders.contains("After") && projectCostDisplayed == amountInDouble);
    }

    /**
     * Click on Forecast Details Tab.
     */
    protected void clickOnForecastDetailsTab() {
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(programForecastDetailsTab);
        elementHelper.doClick(programForecastDetailsTab);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Snapshot));
    }

    /**
     * Click on Program Details Tab.
     */
    public void clickOnProgramDetailsTab() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(programDetailsTab);
        elementHelper.doClick(programDetailsTab);
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
    }

    /**
     * Method to view a program detail form
     *
     * @param programTitle title of the program to be viewed
     */
    public void viewProgram(String programTitle) {
        getPage(ProgramPage.class).navigateTo();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
        getPage(ListPage.class).filterListPage(ProgramListPageHeader.Name.getValue(), programTitle, ListPageFilterOptions.EqualTo);
        getPage(ListPage.class).singleClickOnRowListPage(1);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Function to get item list page headers
     *
     * @return list of item list page headers
     */
    public List<String> itemListPageHeaders() {
        navigation.switchFrameToContent();
        List<WebElement> headerColumns = elementHelper.getElement(forecastCPHeader).findElements(By.xpath(".//th/div"));
        List<String> headerList = new ArrayList<>();
        String headerName;
        for (var headerColumn : headerColumns) {
            elementHelper.scrollToView(headerColumn);
            headerName = headerColumn.getText();
            if (Strings.isNotNullAndNotEmpty(headerName)) {
                headerList.add(headerName);
            }
        }
        return headerList;
    }

    /**
     * validate item details page in Program
     *
     * @param projectCostDisplayed - the project cost displayed
     * @return - true if the validation is successful
     */
    public boolean validateProgramItemDetailsPage(double projectCostDisplayed) {
        waitHelper.waitForPageToLoad();
        List<String> itemDetailsPageHeaders = itemListPageHeaders();

        List<List<String>> amountsDisplayed = getAllItemListData("Amount in $");
        List<String> totalAmountDisplayedList = amountsDisplayed.get(3);
        String totalAmountDisplayed = totalAmountDisplayedList.get(0);
        String regex = "[^0-9.]";
        totalAmountDisplayed = totalAmountDisplayed.replaceAll(regex, "");
        double amountInDouble = Double.parseDouble(totalAmountDisplayed);

        return (itemDetailsPageHeaders.contains("Before") && itemDetailsPageHeaders.contains("After") && projectCostDisplayed == amountInDouble);
    }

    /**
     * Function to get all the required item field values
     *
     * @param columnNames Can pass multiple column names
     * @return List page data.
     */
    public List<List<String>> getAllItemListData(String... columnNames) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        List<List<String>> listPageData = new ArrayList<>();
        var columnIndex = getColumnNumberUsingHeaderName(columnNames[0]);

        //Getting all the row elements of the table.
        if (columnIndex <= 1) {
            //Getting all the row elements of the table.
            List<WebElement> rows = elementHelper.getElement(frozenDataTableProgramForecast).findElements(By.xpath(".//tr"));
            for (var row : rows) {
                List<String> data = new ArrayList<>();
                //Adding all column data of a row to a data list
                for (String Column : columnNames) {
                    data.add(row.findElement(By.xpath(".//td[" + (columnIndex + 1) + "]")).getText().trim());
                }
                listPageData.add(data);
            }
        } else {
            List<WebElement> rows = elementHelper.getElement(dataTableProgramForecast).findElements(By.xpath(".//tr"));
            for (var row : rows) {
                List<String> data = new ArrayList<>();
                //Adding all column data of a row to a data list
                for (String Column : columnNames) {
                    data.add(row.findElement(By.xpath(".//td[" + (columnIndex - 1) + "]")).getText().trim());
                }
                listPageData.add(data);
            }
        }
        return listPageData;
    }

    /**
     * Function to get the Column Number for the Item.
     *
     * @param headerName Header of the Column Number to search in.
     * @return column number.
     */
    public int getColumnNumberUsingHeaderName(String headerName) {
        navigation.switchFrameToContent();
        List<WebElement> tableDatas = elementHelper.getElement(forecastCPHeader).findElements(By.xpath(".//th/div"));

        for (int i = 0; i < tableDatas.size(); i++) {
            WebElement headerTextElement = tableDatas.get(i);
            String headerText = elementHelper.doGetText(headerTextElement);
            if (headerText.equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Validate the presence of Show Summary button in Details page
     *
     * @return true on validation of the above
     */
    public boolean validatePresenceOfShowSummaryButton() {
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(programDetailsShowSummaryButton);
    }

    /**
     * Validate Retrieving Projects with the required criteria or Minimum cost, maximum cost or Both
     *
     * @param costVariant    Cost variant (minimum/maximum/both)
     * @param costParameters Cost Parameters (String of minimum and maximum costs)
     * @return true on retrieving the required list of projects
     */
    public boolean validateRetrievingProjectsUsingCosts(String costVariant, String[] costParameters) {
        var option = costVariant.toLowerCase();
        boolean validation = false;
        switch (option) {
            case "minimum": {
                logger().info("Retrieving the projects for minimum cost criterion");
                waitHelper.waitForPageToLoad(minCostField);
                elementHelper.doSendKeys(minCostField, costParameters[0]);
                elementHelper.doClick(retrieveProjectsButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForPageTabHeaderToBeClickable();
                waitHelper.waitForElementPresent(projectsGrid);
                waitHelper.waitForPageToLoad(retrieveProjectsButton);
                var costData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectCostGridColumnName.getValue(), projectsGridData, projectsGrid, 10);
                var costDataValue = Math.round(Double.parseDouble(costData.get(0).replaceAll(",", "").split("\\.")[0]));
                var enteredValue = Math.round(Double.parseDouble(costParameters[0]));
                if (costDataValue >= enteredValue) {
                    validation = true;
                }
                navigation.refreshPage();
                waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
            }
            case "maximum": {
                logger().info("Retrieving the projects for maximum cost criterion");
                waitHelper.waitForPageToLoad(maxCostField);
                elementHelper.doSendKeys(maxCostField, costParameters[1]);
                elementHelper.doClick(retrieveProjectsButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForPageTabHeaderToBeClickable();
                waitHelper.waitForElementPresent(projectsGrid);
                waitHelper.waitForPageToLoad(retrieveProjectsButton);
                var costData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectCostGridColumnName.getValue(), projectsGridData, projectsGrid, 10);
                var costDataValue = Math.round(Double.parseDouble(costData.get(0).replaceAll(",", "").split("\\.")[0]));
                var enteredValue = Math.round(Double.parseDouble(costParameters[1]));
                if (costDataValue >= enteredValue) {
                    validation = true;
                }
                navigation.refreshPage();
                waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
            }
            case "both": {
                logger().info("Retrieving the projects for both minimum and maximum cost criteria");
                waitHelper.waitForPageToLoad(minCostField);
                elementHelper.doSendKeys(minCostField, costParameters[0]);
                waitHelper.waitForPageToLoad(maxCostField);
                elementHelper.doSendKeys(maxCostField, costParameters[1]);
                elementHelper.doClick(retrieveProjectsButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForPageTabHeaderToBeClickable();
                waitHelper.waitForElementPresent(projectsGrid);
                waitHelper.waitForPageToLoad(retrieveProjectsButton);
                var costData = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, ProgramCostColumns.projectCostGridColumnName.getValue(), projectsGridData, projectsGrid, 10);
                var costDataValue = Math.round(Double.parseDouble(costData.get(0).replaceAll(",", "").split("\\.")[0]));
                var enteredMinValue = Math.round(Double.parseDouble(costParameters[0]));
                var enteredMaxValue = Math.round(Double.parseDouble(costParameters[1]));
                if (costDataValue >= enteredMinValue & costDataValue <= enteredMaxValue) {
                    validation = true;
                }
                navigation.refreshPage();
                waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
            }
            default: {
                logger().info("Entered cost variant is not valid");
            }
        }
        return validation;
    }

    /**
     * Method to create a new program with number of projects.
     *
     * @param numberOfPrograms Number of programs to be added to the program.
     * @param programName      Name of the program to add
     * @return program data
     */
    public Program createNewProgramWithPrograms(int numberOfPrograms, String... programName) {

        var program = addProgramData(false);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();

        if (getPage(Picker.class).getListRows(programPicker).size() < numberOfPrograms) {
            logger().info("Created number of programs is less than required " + numberOfPrograms);
            clickRibbonIcon(RibbonIcons.Cancel);
            return null;
        }
        if (programName.length > 0) {
            getPage(Picker.class).singleSelectByText(ProgramPickerColumns.Name.getValue(), programName[0], programPicker, PickerFilterOptions.Contains);
            elementHelper.doClick(fundSourceProgramSelectButton);
            waitHelper.waitForPageToLoad(fundPicker);
            getPage(Picker.class).singleSelectByRowNumber(1, fundPicker);
        } else {
            getPage(Picker.class).multiSelectByCountOld(numberOfPrograms, programPicker);
        }
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Add new program
     *
     * @param rowNumber Based on Row Number
     */
    public void addNewProgram(int rowNumber) {
        logger().info("Adding New Program");
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByRowNumber(rowNumber, programPicker);
    }


    /**
     * Add published project to a program
     *
     * @param publishedProjectsToAdd List of projects to add
     */
    public void addPlannedProjects(List<String> publishedProjectsToAdd, String... planningFundName) {
        waitHelper.waitForPageTabHeaderToBeClickable();
        for (var projectName : publishedProjectsToAdd) {
            logger().info("Adding project '" + projectName + "' to program");
            elementHelper.doClick(addPlannedProjectsBtn);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(plannedProjectPicker);
            waitHelper.waitForLoadingSpinnerDisappear();
            getPage(Picker.class).singleSelectByText(ProgramListPageHeader.Name.getValue(), projectName, plannedProjectPicker);
            elementHelper.doClick(fundSourceProgramSelectButton);
            waitHelper.waitForPageToLoad(fundPicker);
            if (planningFundName.length > 0) {
                getPage(Picker.class).singleSelectByText(PlanningFundListPageColumns.Title.getValue(), planningFundName[0], fundPicker);
            } else {
                getPage(Picker.class).singleSelectByRowNumber(1, fundPicker);
            }
            waitHelper.waitForPageTabHeaderToBeClickable();
        }
    }

    /**
     * Add Programs for validation
     *
     * @param numberOfPrograms number of programs
     * @return Program details
     */
    public Program addPrograms(int numberOfPrograms, String projectName) {
        Program programDetails = addProgramData(false);
        if (numberOfPrograms > 0) {
            elementHelper.doClick(addProgramsButton);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForLoadingSpinnerDisappear();
            getPage(Picker.class).multiSelectByCountOld(numberOfPrograms, programPicker);
        }

        return programDetails;
    }

    /**
     * Validate the multi select checkbox in Add Program picker
     *
     * @return true on validation
     */
    public boolean validateMultiSelectCheckBoxInAddProgramPicker() {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad(programPicker);
        return getPage(Picker.class).validateMultiSelectCheckBox(true);
    }

    /**
     * Validate grid column title
     *
     * @param tableId   Table id value
     * @param tableName List value for Table name value
     * @return True if all the columns are present
     */
    private boolean validateGridHeader(By tableId, List<String> tableName) {
        waitHelper.waitForPageToLoad();
        List<String> headers = new ArrayList<>();
        elementHelper.getElement(tableId).findElements(By.xpath(".//th")).stream().distinct().filter(x -> Strings.isNotNullAndNotEmpty(elementHelper.doGetText(x))).forEach(x -> headers.add(x.getText()));
        waitHelper.waitForPageToLoad();
        return tableName.containsAll(headers);
    }

    /**
     * Validate grid column title
     *
     * @param tableName List value for Table name value
     * @return True if all the columns are present
     */
    public boolean validatePickerHeader(List<String> tableName) {
        waitHelper.waitForPageToLoad();
        List<String> headers = new ArrayList<>();
        elementHelper.getElement(pickerHeader).findElements(By.xpath(".//th")).stream().distinct().filter(x -> Strings.isNotNullAndNotEmpty(elementHelper.doGetText(x))).forEach(x -> headers.add(x.getText()));
        waitHelper.waitForPageToLoad();
        return tableName.containsAll(headers);
    }

    /**
     * validates Grid Header Columns
     *
     * @return True if all the columns are present
     */
    public SoftAssert validateGridHeaderColumns() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(validateGridHeader(programGridHeader, ProgramGridColumnHeaders.getList()),
                "Expected program grid headers are present");
        softAssert.assertTrue(validateGridHeader(projectGridHeaderId, PlannedProjectGridColumnHeaders.getList()),
                "Expected projects grid headers are present");
        softAssert.assertTrue(validateGridHeader(programBudgetGridHeader, ProgramBudgetListPageColumns.getList()),
                "Expected Program Budget grid headers are present");
        return softAssert;
    }

    /**
     * validates Picker Header Columns
     *
     * @param type Type to validate in switch case
     * @return True if all the columns are present
     */
    public SoftAssert validatePickerHeaderColumns(List<String> type) {
        SoftAssert softAssert = new SoftAssert();
        for (var value : type) {
            switch (value) {
                case "program": {
                    elementHelper.doClick(addProgramsButton);
                    waitHelper.waitForPageToLoad(programPicker);
                    waitHelper.waitForLoadingSpinnerDisappear();
                    softAssert.assertTrue(validatePickerHeader(ProgramPickerColumns.getList()), "Validates Program picker Header availability");
                    getPage(ProgramPage.class).programGridCancel();
                    break;
                }
                case "project": {
                    elementHelper.doClick(addPlannedProjectsBtn);
                    waitHelper.waitForPageToLoad(plannedProjectPicker);
                    waitHelper.waitForLoadingSpinnerDisappear();
                    softAssert.assertTrue(validatePickerHeader(ProgramProjectPickerColumns.getList()), "Validates Project picker Header availability");
                    clickCancelOnAddProjectsPicker();
                    break;
                }
            }
        }
        return softAssert;
    }

    /**
     * Add Budget to a program
     */
    public void addProgramBudget() {
        elementHelper.doClick(fundSourceProgramSelectButton);
        waitHelper.waitForPageToLoad(fundPicker);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByRowNumber(1, fundPicker);
    }

    /**
     * Validate the Program year drop down
     *
     * @param programDurationInAdministration program year duration from administration
     * @param timeZone                        time zone
     * @param dateFormat                      date format string
     * @return true on validation of the above
     */
    public boolean validateProgramYearDropDown(String programDurationInAdministration, String timeZone, String dateFormat) {
        return getPage(GenericForm.class).validateProgramYearDropDown(programDurationInAdministration, timeZone, dateFormat, programYearDropDown);
    }

    /**
     * Validate the program category drop down values
     *
     * @param libraryOptions program category library options
     * @return true on validation of the above
     */
    public boolean validateProgramCategoryDropDown(List<String> libraryOptions) {
        logger().info("Validating program category drop down");
        waitHelper.waitForPageToLoad(programCategoryDropDown);
        var programCategoryValuesInDropDown = elementHelper.getComboBoxSelectOptions(programCategoryDropDown);
        var textValuesOfCategories = new ArrayList<>();
        programCategoryValuesInDropDown.forEach(a -> textValuesOfCategories.add(a.getText()));
        return textValuesOfCategories.containsAll(libraryOptions);
    }

    /**
     * Validate if the Program cost field is editable
     *
     * @return true on validation of the above
     */
    public boolean validateProgramCostField() {
        waitHelper.waitForPageToLoad(projectCostField);
        return getPage(Validations.class).isElementUneditable(projectCostField);
    }

    /**
     * Validate the numeric cost fields
     *
     * @return softassert of the following validations
     */
    public SoftAssert validateNumericCostFields() {
        var softAssert = new SoftAssert();
        waitHelper.waitForPageToLoad(minCostField);
        var text = TestDataUtil.getRandomName();
        elementHelper.doSendKeys(minCostField, text);
        elementHelper.doSendKeys(maxCostField, text);

        var minEnteredValue = elementHelper.doGetAttribute(minCostEnteredValue, "value");
        var maxEnteredValue = elementHelper.doGetAttribute(maxCostEnteredValue, "value");
        softAssert.assertFalse(minEnteredValue.equals(text), "Min Cost field Does not accepts text");
        softAssert.assertFalse(maxEnteredValue.equals(text), "Max Cost field Does not accepts text");

        var number = TestDataUtil.getRandomNumber(1, 100);
        elementHelper.doSendKeys(minCostField, number.toString());
        elementHelper.doSendKeys(maxCostField, number.toString());

        minEnteredValue = elementHelper.doGetAttribute(minCostEnteredValue, "value").split("\\.")[0];
        elementHelper.doClick(minCostField);
        maxEnteredValue = elementHelper.doGetAttribute(maxCostEnteredValue, "value").split("\\.")[0];
        softAssert.assertTrue(minEnteredValue.equals(number.toString()), "Min Cost field accepts numeric data");
        softAssert.assertTrue(maxEnteredValue.equals(number.toString()), "Max Cost field accepts numeric data");

        return softAssert;
    }

    /**
     * Validate the contingency and regular program type fields
     *
     * @return true on validation of the presence of cost fields for Regular Program Type
     */
    public SoftAssert validateContingencyAndRegularProgramFields() {
        var softAssert = new SoftAssert();
        elementHelper.scrollToView(contingencyProgramTypeLabel);
        elementHelper.doClick(contingencyProgramTypeLabel);
        waitHelper.waitForPageTabHeaderToBeClickable();
        softAssert.assertFalse(getPage(GenericForm.class).validateElementsExists(getCostFields()), "Cost fields should be absent for Contingency type");

        waitHelper.waitForPageToLoad(regularProgramTypeLabel);
        elementHelper.doClick(regularProgramTypeLabel);
        waitHelper.waitForPageTabHeaderToBeClickable();
        softAssert.assertTrue(getPage(GenericForm.class).validateElementsExists(getCostFields()), "Cost fields should be present for Regular type");

        return softAssert;
    }

    /**
     * Validate Editing Submitted Program
     *
     * @return true on validation of the above
     */
    public boolean validateEditingSubmittedProgram() {
        waitHelper.waitForPageToLoad(programTitleTxtBox);
        return validateRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Validate Program type radio button in edit mode
     *
     * @return true on validation of the above
     */
    public boolean validateProgramTypeRadioButtonInEditMode() {
        waitHelper.waitForPageTabHeaderToBeClickable();
        return getPage(Validations.class).verifyElementExists(regularProgramTypeLabel);
    }

    /**
     * Get Parameter of the project from Projects Grid
     *
     * @param columnName corresponding column name of the parameter
     * @return Project parameter required
     */
    public List<String> getParameterValueOfProjectFromProjectsGrid(PlannedProjectGridColumnHeaders columnName) {
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, columnName.getValue(), projectsGridData, projectGridHeaderId);
    }

    /**
     * Validate project in add projects picker
     *
     * @param projectName project name
     * @return true on validation of the above
     */
    public boolean validateProjectInAddProjectsPicker(String projectName) {
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad(plannedProjectPicker);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, ProgramForecastListPageColumns.Name.getValue(), projectName, PickerFilterOptions.Contains);
        var projectFiltered = getPage(Picker.class).getColumnData(plannedProjectPickerId, ProgramListPageHeader.Title.getValue(), 1);
        if (projectFiltered.size() == 0) {
            return false;
        } else {
            return projectName.equals(projectFiltered.get(0));
        }
    }

    /**
     * Click cancel on add projects picker
     */
    public void clickCancelOnAddProjectsPicker() {
        getPage(Picker.class).cancelPicker(plannedProjectPicker);
    }

    /**
     * Validate editing program category
     *
     * @param category new program category
     * @return false if the editing of program category results in an alert
     */
    public boolean validateEditingProgramCategory(String category) {
        waitHelper.waitForPageToLoad(programCategoryDropDown);
        elementHelper.selectComboBoxItemByTextWithoutWait(programCategoryDropDown, category);
        if (getPage(AlertHandler.class).isAlertPresent(true)) {
            getPage(AlertHandler.class).acceptAlert(false);
            return false;
        }
        return true;
    }

    /**
     * Validate planned projects picker data with corresponding program category
     *
     * @param category program category selected
     * @return true on validation of the above
     */
    public boolean validateProjectsPickerWithCorrespondingProgramCategory(String category) {
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad(plannedProjectPicker);
        waitHelper.waitForLoadingSpinnerDisappear();
        var projectCategoryInPicker = getPage(Picker.class).getColumnData(plannedProjectPickerId, programCategoryDataFieldValue, 1);
        return projectCategoryInPicker.get(0).equals(category);
    }

    /**
     * Validate program budget picker with approved planning fund
     *
     * @param fundName approved planning fund name
     * @return true on validation of the above
     */
    public boolean validateProgramBudgetPicker(String fundName) {
        waitHelper.waitForElementClickable(fundSourceProgramSelectButton);
        elementHelper.doClick(fundSourceProgramSelectButton);
        waitHelper.waitForPageToLoad(fundPicker);
        waitHelper.waitForLoadingSpinnerDisappear();

        getPage(Picker.class).filterPickerColumn(fundPicker, ProgramRevisionPageColumns.Title.getValue(), fundName, PickerFilterOptions.Contains);
        var value = getPage(Picker.class).getColumnData(fundPickerId, ProgramRevisionPageColumns.Title.getValue(), 1);
        return fundName.equals(value.get(0));
    }

    /**
     * Validate Adding a Program which is already a part of Submitted Program
     *
     * @param program     program details
     * @param projectName project name
     * @return false if the project is not available to be added
     */
    public boolean validateCreatingProgramWithProjectWhichIsPartOfASubmittedProgram(Program program, String projectName) {
        waitHelper.waitForPageToLoad(programCategoryDropDown);
        elementHelper.selectComboBoxItemByText(programCategoryDropDown, program.category);
        return validateProjectInAddProjectsPicker(projectName);
    }

    /**
     * Validate the presence of Project status column, and number of projects in Grid
     *
     * @param numberOfProjects number of projects added
     * @return soft assert of the validations
     */
    public SoftAssert validatePlannedProjectsAddedAndProjectStatusColumn(int numberOfProjects) {
        var softAssert = new SoftAssert();
        var projectsInGrid = getParameterValueOfProjectFromProjectsGrid(PlannedProjectGridColumnHeaders.ProjectStatus);
        softAssert.assertTrue(projectsInGrid.size() == numberOfProjects, "Added planned projects are present in the grid");

        softAssert.assertTrue(projectStatus.contains(projectsInGrid.get(0)), "Status of the Added planned project is " + projectStatus.get(0) + "or" + projectStatus.get(1));
        return softAssert;
    }

    /**
     * Method to create a new program with a specific program
     *
     * @param programName program name
     * @return program data
     */
    public Program createNewProgramWithSpecificProgram(String programName) {
        var program = addProgramData(false);
        addRequiredProgram(programName);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Verify if Projects grid and add button are displayed.
     *
     * @return True if displayed, else false.
     */
    public boolean isProjectsGridDisplayed() {
        return elementHelper.isElementDisplayed(projectsGrid) && elementHelper.isElementDisplayed(addPlannedProjectsBtn);
    }

    /**
     * Verify if Programs grid and add button are displayed.
     *
     * @return True if displayed, else false.
     */
    public boolean isProgramsGridDisplayed() {
        return elementHelper.isElementDisplayed(programBudgetGridHeader) && elementHelper.isElementDisplayed(addProgramsButton);
    }

    /**
     * Get selected program type option (after save).
     *
     * @return Selected program type.
     */
    public String getProgramType() {
        return elementHelper.doGetText(selectedProgramType);
    }

    /**
     * Get Program code.
     *
     * @return Program code.
     */
    public String getProgramCode() {
        return elementHelper.doGetText(programCode);
    }

    /**
     * Select Program type radio button.
     *
     * @param type Program type
     */
    public void selectProgramType(ProgramType type) {
        var labels = elementHelper.getElement(programTypes).findElements(By.tagName("label"));
        var label = labels.stream().filter(l -> elementHelper.doGetText(l).equals(type.getValue())).findFirst();
        label.ifPresent(webElement -> elementHelper.doClick(webElement));
    }

    /**
     * Get Selected program type (before save).
     *
     * @return Selected program type
     */
    public String getSelectedProgramType() {
        var inputs = elementHelper.getElement(programTypes).findElements(By.tagName("input"));
        var input = inputs.stream().filter(i -> elementHelper.isCheckBoxSelected(i)).findFirst();
        if (input.isPresent()) {
            // Get the label and return the text.
            var label = input.get().findElement(By.xpath("../label"));
            return elementHelper.doGetText(label);
        }

        return Constants.EMPTY_STRING;
    }

    /**
     * Select Program year.
     *
     * @param year Program year.
     */
    public void selectProgramYear(String year) {
        elementHelper.selectComboBoxItemByText(programYearDropDown, year);
    }

    /**
     * Get Program year.
     *
     * @return Program year.
     */
    public String getProgramYear() {
        return elementHelper.getFirstSelectedOption(programYearDropDown);
    }

    /**
     * Select Program category.
     *
     * @param category Program category.
     */
    public void selectProgramCategory(String category) {
        elementHelper.selectComboBoxItemByText(programCategoryDropDown, category);
    }

    /**
     * Get Program category.
     *
     * @return Program category.
     */
    public String getProgramCategory() {
        return elementHelper.getFirstSelectedOption(programCategoryDropDown);
    }

    /**
     * Validate attachment grid headers.
     *
     * @return True if expected headers exists, else false.
     */
    public boolean validateAttachmentGridHeaders() {
        elementHelper.scrollToView(attachmentsGrid);
        List<String> actualHeaders = getPage(GenericFormProposed.class).getGridHeaders(GridType.RadGrid, attachmentGridHeaderDetails);
        return actualHeaders.containsAll(AttachmentColumn.getList());
    }

    /**
     * Upload a document in attachments grid.
     *
     * @param fileName - Name of the file.
     */
    public void uploadDocument(String fileName) {
        navigation.switchFrameToContent();
        elementHelper.scrollToView(attachmentsGrid);
        File file = FileHelper.createFile(fileName, true);
        if (file != null) {
            elementHelper.doSendKeys(uploadDocument, file.getAbsolutePath());
            waitHelper.waitForPageToLoad();
            getPage(ToastUtil.class).waitAndCloseForSingleToast();
        }
    }

    /**
     * Check if attachment exists in the attachments grid.
     *
     * @param fileName File to be checked.
     * @return True if exists, else false.
     */
    public boolean isAttachmentExists(String fileName) {
        elementHelper.scrollToView(attachmentsGrid);
        return getPage(GenericForm.class).isRecordExistsInRadGrid(attachmentsGrid, attachmentsGridHeader, AttachmentColumn.DocumentName.getValue(), fileName);
    }

    /**
     * Download specific attachment
     *
     * @param fileName Name of the file to download.
     * @param userName User name of the file uploaded.
     */
    public void downloadAttachment(String fileName, String userName) {
        elementHelper.scrollToView(attachmentsGrid);
        var row = getPage(GenericForm.class).getRowFromRadGrid(attachmentsGridData, attachmentsGridHeader, AttachmentColumn.DocumentName.getValue(), fileName);
        var userCell = row.findElement(By.xpath("//td[text()='" + userName + "']"));
        elementHelper.doClick(userCell);
        elementHelper.doClick(downloadAttachment);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select Planned project based on the filter criteria.
     *
     * @param columnName  Column Name
     * @param columnValue Expected column value.
     * @param filter      Filter to be applied.
     * @return Selected column value.
     */
    public String selectPlannedProject(PlannedProjectGridColumnHeaders columnName, String columnValue, PickerFilterOptions filter) {
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, columnName.getValue(), columnValue, filter);
        getPage(Picker.class).singleSelectByRowNumber(1, plannedProjectPicker);

        return getPage(GenericForm.class).getCellValueRadGrid(projectsGrid, 1, columnName.getValue());
    }

    /**
     * Select Planned program based on the filter criteria.
     *
     * @param columnName  Column Name
     * @param columnValue Expected column value.
     * @param filter      Filter to be applied.
     * @param header      Get the cell value of this header.
     * @return Selected column value.
     */
    public String selectRandomProgramAndGetRequiredParam(ProgramPickerColumns columnName, String columnValue, PickerFilterOptions filter, ProgramGridColumnHeaders header) {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        getPage(Picker.class).filterPickerColumn(programPicker, columnName.getValue(), columnValue, filter);
        getPage(Picker.class).singleSelectByRowNumber(1, programPicker);

        return getPage(GenericForm.class).getCellValueRadGrid(programsGrid, 1, header.getValue());
    }

    /**
     * Select Program budget based on the filter criteria.
     *
     * @param columnName  Column Name
     * @param columnValue Expected column value.
     * @param filter      Filter to be applied.
     * @param header      Get the cell value of this header.
     * @return Selected column value.
     */
    public String selectProgramBudget(PlanningFundListPageColumns columnName, String columnValue, PickerFilterOptions filter, ProgramBudgetListPageColumns header) {
        elementHelper.doClick(fundSourceProgramSelectButton);
        waitHelper.waitForPageToLoad(fundPicker);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).filterPickerColumn(fundPicker, columnName.getValue(), columnValue, filter);
        getPage(Picker.class).singleSelectByRowNumber(1, fundPicker);

        return getPage(GenericForm.class).getCellValueRadGrid(programBudgetGrid, 1, header.getValue());
    }

    /**
     * Get Program Budget value.
     *
     * @return Program Budget value.
     */
    public String getProgramBudget() {
        return elementHelper.doGetAttribute(programBudgetTxtBox, "value");
    }

    /**
     * Get Program Remaining Budget value.
     *
     * @return Program Remaining Budget value.
     */
    public String getProgramRemainingBudget() {
        return elementHelper.doGetAttribute(remainingBudget, "value");
    }

    /**
     * Click Picker Cancel button
     *
     * @param grid Picker grid
     */
    public void cancelPicker(ProgramGrids grid) {
        String cancelXpath = "//div[@id='%s']//input[contains(@type,'submit') and @value='Cancel']";
        switch (grid) {
            case Programs:
                elementHelper.doClick(By.xpath(String.format(cancelXpath, elementHelper.getLocatorAsString(programPickerId))));
                break;
            case Projects:
                elementHelper.doClick(By.xpath(String.format(cancelXpath, elementHelper.getLocatorAsString(plannedProjectPickerId))));
                break;
            case ProgramBudget:
                elementHelper.doClick(By.xpath(String.format(cancelXpath, elementHelper.getLocatorAsString(fundPickerId))));
                break;
        }
        waitHelper.waitForPageToLoad();
    }

    /**
     * Add required program to the program details page
     *
     * @param programName program name
     */
    public void addRequiredProgram(String programName) {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByText(ProgramListPageHeader.Name.getValue(), programName, programPicker);
    }

    /**
     * Validate program budget grid if it is editable.
     *
     * @return true on validation of the above
     */
    public boolean validateIfProgramBudgetGridIsEditable() {
        var inputElements = elementHelper.getElement(programBudgetGridData).findElements(By.tagName("input"));
        return inputElements.size() > 0;
    }

    /**
     * Click Clear button under Program budget grid.
     */
    public void clearProgramBudget() {
        elementHelper.doClick(clearProgramBudgetGrid);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get list of program year drop down options
     *
     * @return list of string
     */
    public List<String> getListOfProgramYearOptions() {
        var programYearValuesInDropDown = elementHelper.getComboBoxSelectOptions(programYearDropDown);
        var programYearValuesString = new ArrayList<String>();
        programYearValuesInDropDown.forEach(a -> programYearValuesString.add(a.getText()));
        return programYearValuesString;
    }

    /**
     * Validate program year column in program picker
     *
     * @param programYearOptions program year options
     * @return true on validation of the above
     */
    public boolean validateProgramYearInProgramPicker(List<String> programYearOptions) {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad(programPicker);
        var listFromPicker = getPage(Picker.class).getColumnData(programPickerId, "ProgramYear", 1);
        return programYearOptions.containsAll(listFromPicker);
    }

    /**
     * Get Parameter of the program from Programs Grid
     *
     * @param columnName corresponding column name of the parameter
     * @return program parameter required
     */
    public List<String> getParameterValueFromProgramsGrid(ProgramGridColumnHeaders columnName) {
        waitHelper.waitForElementPresent(addProgramsButton);
        return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, columnName.getValue(), programsGridData, programGridHeader);
    }

    /**
     * Validate the presence of grid data after adding records
     *
     * @param recordType type of record (Project / Program)
     * @return true on validation of the above
     */
    public boolean validatePresenceOfGridData(String recordType) {
        var recordFound = false;
        switch (recordType.toLowerCase()) {
            case "project": {
                var name = getParameterValueOfProjectFromProjectsGrid(PlannedProjectGridColumnHeaders.ProjectName);
                var code = getParameterValueOfProjectFromProjectsGrid(PlannedProjectGridColumnHeaders.ProjectCode);
                recordFound = (name.size() & code.size()) != 0;
                break;
            }
            case "program": {
                var name = getParameterValueFromProgramsGrid(ProgramGridColumnHeaders.ProgramName);
                var code = getParameterValueFromProgramsGrid(ProgramGridColumnHeaders.ProgramCode);
                recordFound = (name.size() & code.size()) != 0;
                break;
            }
            default:
                logger().info("Enter proper record type (Project / Program)");
        }
        return recordFound;
    }

    /**
     * Select Contingency Program type
     */
    public void selectContingencyProgramType() {
        navigation.switchFrameToContent();
        logger().info("Program Type : Contingency");
        JavaScriptUtil.clickElementByJS(elementHelper.getElement(contingencyProgramTypeLabel), driver);
    }

    /**
     * Validate add programs button for a Contingency Program
     *
     * @return true if the add programs button is clickable
     */
    public boolean validateAddProgramsButtonForContingencyProgram() {
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(addProgramsButton);
        return elementHelper.getElement(addProgramsButton).isEnabled();
    }

    /**
     * Validate adding projects from the picker without selecting program category
     *
     * @return true if project picker has records to select
     */
    public boolean validateAddingProjectsWithoutSelectingProgramCategory() {
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad(plannedProjectPicker);
        var columnData = getPage(Picker.class).getColumnData(plannedProjectPickerId, PlannedProjectGridColumnHeaders.ProjectName.getValue(), 1);
        return columnData != null;
    }

    /**
     * Get the number of programs in add program picker
     *
     * @return number of programs
     */
    public int getNumberOfProgramsInAddProgramPicker() {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad(programPicker);
        var num = getPage(Picker.class).getRowSizeInPickerGrid(programPicker);
        cancelPicker(ProgramGrids.Programs);
        return num;
    }

    /**
     * Get locators of the fields
     *
     * @return list of locators
     */
    private List<By> getAllFieldsInProgramPage() {
        var list = new ArrayList<By>();
        list.add(programTitleTxtBox);
        list.add(programCategoryDropDown);
        list.add(programYearDropDown);
        list.add(defaultFiscalYearSelect);
        list.add(minCostField);
        list.add(maxCostField);
        list.add(retrieveProjectsButton);
        list.add(regularProgramTypeLabel);
        list.add(contingencyProgramTypeLabel);
        list.add(projectsGrid);
        list.add(programsGrid);
        list.add(programBudgetGrid);
        list.add(addPlannedProjectsBtn);
        list.add(addProgramsButton);
        list.add(fundSourceProgramSelectButton);

        return list;
    }

    /**
     * Vaildate the alert message while retrieving projects in Project grid
     *
     * @param expAlertMsg expected alert message
     * @return true on validation of the above
     */
    public boolean validateAlertWhileRetrievingProjects(String expAlertMsg) {
        waitHelper.waitForElementPresent(retrieveProjectsButton);
        elementHelper.doClick(retrieveProjectsButton);
        waitHelper.waitForPageToLoad(retrieveProjectsButton);
        elementHelper.doClick(retrieveProjectsButton);
        var alertMsg = getPage(AlertHandler.class).getAlertMessage();
        return alertMsg.equals(expAlertMsg);
    }

    /**
     * Get column value from Project Picker
     *
     * @param column column value
     * @return list of required parameter
     */
    public List<String> getColumnValueFromProjectsPicker(PlannedProjectGridColumnHeaders column) {
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad(plannedProjectPicker);
        waitHelper.waitForLoadingSpinnerDisappear();

        return getPage(Picker.class).getAllDataUnderColumnHeader(column.getValue(), plannedProjectPicker);
    }

    /**
     * Get Project grid total cost
     *
     * @return long value of the total project cost
     */
    public long getProjectGridTotal() {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        elementHelper.scrollToView(addPlannedProjectsBtn);
        var cells = elementHelper.getElements(plannedProjectGirdTotal);
        var column = getPage(GenericFormProposed.class).getColumnIndex(PlannedProjectGridColumnHeaders.RevisedBudget.getValue(),
                projectsGrid, GridType.RadGrid);
        var totalCost = cells.get(column).getText();
        return Math.round(Double.parseDouble(totalCost.replaceAll(",", "").split("\\.")[0]));
    }

    /**
     * Get the selected Fiscal year
     *
     * @return string value of the selected Fiscal year
     */
    public String getTheSelectedFiscalYear() {
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Cancel));
        waitHelper.waitForElementPresent(fiscalYearSelected);
        return elementHelper.doGetText(fiscalYearSelected);
    }

    /**
     * Method to create a new program with a specific program budget
     *
     * @param numberOfProjects Number of projects to be added to the program.
     * @param programBudget    program budget to be added
     * @return program data
     */
    public Program createNewProgramWithSpecificProgramBudget(int numberOfProjects, String programBudget) {
        var program = addProgramData(false);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        if (getPage(Picker.class).getListRows(plannedProjectPicker).size() < numberOfProjects) {
            logger().info("Created number of project is less than required " + numberOfProjects);
            clickRibbonIcon(RibbonIcons.Cancel);
            return null;
        }
        getPage(Picker.class).multiSelectByCountOld(numberOfProjects, plannedProjectPicker);
        waitHelper.waitForPageToLoad();
        selectProgramBudget(PlanningFundListPageColumns.Title, programBudget, PickerFilterOptions.Contains,
                ProgramBudgetListPageColumns.TotalFunds);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        logger().info("New program creation complete");
        return program;
    }

    /**
     * Select Program budget from picker
     *
     * @param columnValue Expected column value.
     */
    public void selectProgramBudget(String columnValue) {
        selectProgramBudget(PlanningFundListPageColumns.Title, columnValue, PickerFilterOptions.Contains, ProgramBudgetListPageColumns.TotalFunds);
    }

    /**
     * Validate presence of Actual cost columns in Program and Projects grid
     *
     * @return true on validation of the above
     */
    public boolean validatePresenceOfActualCostColumns() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(projectsGrid);
        boolean presence = false;
        var planningGrid = getPage(GenericFormProposed.class).getColumnIndex(PlannedProjectGridColumnHeaders.ActualCost.getValue(),
                projectsGrid, GridType.RadGrid);
        var programGrid = getPage(GenericFormProposed.class).getColumnIndex(PlannedProjectGridColumnHeaders.ActualCost.getValue(),
                programsGrid, GridType.RadGrid);
        if (planningGrid != -1 && programGrid != -1) {
            presence = true;
        }
        return presence;
    }

    /**
     * Fetch the parameter of corresponding record in program picker
     *
     * @param parameter parameter to be searched
     * @param column    column to be fetched
     * @return return column data after filtering
     */
    public String getParameterValueFromProgramsPicker(String parameter, ProgramCostColumns column) {
        waitHelper.waitForPageToLoad(addProgramsButton);
        elementHelper.doClick(addProgramsButton);
        waitHelper.waitForPageToLoad(programPicker);
        getPage(Picker.class).filterPickerColumn(programPicker, ProgramPickerColumns.Name.getValue(), parameter, PickerFilterOptions.Contains);
        var columnData = getPage(Picker.class).getColumnData(programPickerId, column.getValue(), 1);
        return columnData.size() > 0 ? columnData.get(0) : "";
    }

    /**
     * Get Grid Details from the Program
     *
     * @param gridName Type of Grid (Projects , Program , ProgramFundSource)
     * @return Grid Details
     */
    public List<String> getGridDetails(ProgramGrids gridName) {
        switch (gridName) {
            case Projects:
                return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, name, projectsGridData, projectGridHeaderId, 10);

            case Programs:
                return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, name, programsGridData, programGridHeader, 10);

            case ProgramBudget:
                return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, fundSource, programBudgetGridData, programBudgetGridHeader, 10);

            default:
                return null;
        }

    }

    /**
     * Validate the project picker for Closed and Draft state projects
     *
     * @return soft assert of the above validation (Absence of Closed and Draft state projects)
     */
    public SoftAssert validateProjectPickerWithClosedAndDraftRecords() {
        var softAssert = new SoftAssert();
        waitHelper.waitForPageToLoad(addPlannedProjectsBtn);
        elementHelper.doClick(addPlannedProjectsBtn);
        waitHelper.waitForPageToLoad(plannedProjectPicker);
        waitHelper.waitForLoadingSpinnerDisappear();

        getPage(Picker.class).filterPickerColumn(plannedProjectPicker, PlannedProjectGridColumnHeaders.ProjectStatus.getValue(),
                WorkFlowStatus.Closed.getValue(), PickerFilterOptions.Contains);

        var columnData = getPage(Picker.class).getColumnData(plannedProjectPickerId, PlannedProjectGridColumnHeaders.ProjectStatus.getValue(), 1);
        var cellValue = columnData.size() > 0 ? columnData.get(0) : "";
        softAssert.assertTrue(Objects.equals(cellValue, ""), "Projects in Closed state are not listed in the planned projects picker");

        getPage(Picker.class).clearFilterAndFilterPickerColumn(plannedProjectPicker, PlannedProjectGridColumnHeaders.ProjectStatus.getValue(),
                WorkFlowStatus.Draft.getValue(), PickerFilterOptions.Contains);

        columnData = getPage(Picker.class).getColumnData(plannedProjectPickerId, PlannedProjectGridColumnHeaders.ProjectStatus.getValue(), 1);
        cellValue = columnData.size() > 0 ? columnData.get(0) : "";
        softAssert.assertTrue(Objects.equals(cellValue, ""), "Projects in Draft state are not listed in the planned projects picker");
        return softAssert;
    }

    /**
     * Method is used to validate the program details page field value
     *
     * @return true if all element exist else return false
     */
    public boolean verifyAllFieldInProgramPage() {
        return getPage(GenericForm.class).validateElementsExists(getPage(ProgramDetailsPage.class).getAllFieldsInProgramPage());
    }
}
