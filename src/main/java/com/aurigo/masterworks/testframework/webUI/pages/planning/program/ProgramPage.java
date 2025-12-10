package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.CustomFieldsHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.*;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.PlanningPage;
import com.aurigo.masterworks.testframework.webUI.testData.Program;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.util.Strings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProgramPage extends ListPage {

    private By programForecastDetailsTab;
    private By scheduleGanttViewTab;
    private By headerCellsXpath;
    private By contentRowsXpath;
    private By taskNameCell;
    private By textContentCell;
    private By getProjectCostValue;
    private By programDynamicGridIdInProgram;
    private By projectDynamicGridIdInProgram;
    private By programCountInForm;
    private By programCount;
    private By programGridCancel;
    private By fieldSelection;
    private By workFlowStateTemplate;
    private By programYearDropDown;
    private By remainingFundInForecast;
    private By attachmentTable;
    private By attachmentTableHeader;
    private By deleteAttachment;

    private String programForecastItemExpandButtonTemplate;
    private String projectTemplateInForecastDetailsPageTemplate;
    private String listPageProgamCategoryTemplate;


    public ProgramPage(WebDriver driver) {
        super(driver);
        programForecastItemExpandButtonTemplate = "//a[contains(text(),'%s')]/../..//div[@id='arrow']";
        projectTemplateInForecastDetailsPageTemplate = "//a[contains(text(),'%s')]";
        listPageProgamCategoryTemplate = "//div[contains(text(),'Program Category : ')]//strong[text()='%s']";

        var locators = LocatorUtil.getLocators("ProgramPage.json");
        programForecastDetailsTab = locators.get("programForecastDetailsTab");
        scheduleGanttViewTab = locators.get("scheduleGanttViewTab");
        headerCellsXpath = locators.get("headerCellsXpath");
        contentRowsXpath = locators.get("contentRowsXpath");
        taskNameCell = locators.get("taskNameCell");
        textContentCell = locators.get("textContentCell");
        getProjectCostValue = locators.get("getProjectCostValue");
        programDynamicGridIdInProgram = locators.get("programDynamicGridIdInProgram");
        projectDynamicGridIdInProgram = locators.get("projectDynamicGridIdInProgram");
        programCountInForm = locators.get("programCountInForm");
        programCount = locators.get("programCount");
        programGridCancel = locators.get("programGridCancel");
        fieldSelection = locators.get("fieldSelection");
        workFlowStateTemplate = locators.get("workFlowStateTemplate");
        programYearDropDown = locators.get("programYearDropDown");
        remainingFundInForecast = locators.get("remainingFundInForecast");
        attachmentTable = locators.get("attachmentTable");
        attachmentTableHeader = locators.get("attachmentTableHeader");
        deleteAttachment = locators.get("deleteAttachment");
    }

    /**
     * Navigates to the program form in planning module
     */
    public void navigateTo() {
        getPage(PlanningPage.class).navigateTo();
        navigateToListPage();
    }

    /**
     * Navigate to Program List Page
     */
    public void navigateToListPage() {
        navigation.navigateToFormInLeftPaneTree("Program");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Method to view a program detail form
     *
     * @param programTitle title of the program to be viewed
     */
    public void selectProgramInListPage(String programTitle) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        int rownum;
        rownum = getRowNumberFromListPage(ProgramListPageHeader.Name.getValue(), programTitle);
        singleClickOnRowListPage(rownum);
        waitHelper.waitForPageToLoad(RibbonIcons.View);
    }

    /**
     * Method to filter a program in list page
     *
     * @param programTitle title of the program to be viewed
     */
//    public void filterProgramInListPage(String programTitle) {
//        clearAllFilters();
//        filterListPage(ProgramListPageHeader.Name.getValue(), programTitle, ListPageFilterOptions.Contains);
//        waitHelper.waitForPageToLoad(RibbonIcons.New);
//    }

    /**
     * Method to filter a program in list page
     *
     * @param programCategory title of the program to be viewed
     */
    public void filterProgramInListPage(String programCategory) {
        clearAllFilters();
        filterListPage(ProgramListPageHeader.Name.getValue(), programCategory, ListPageFilterOptions.Contains);
        waitHelper.waitForPageToLoad(RibbonIcons.New);
    }

    /**
     * Method to create a new program
     *
     * @param publishedProjectsToAdd list of published projects to be added to the program
     * @return program
     */
    public Program createNewProgram(List<String> publishedProjectsToAdd) {
        logger().info("Creating new Program");
        navigateTo();
        clickRibbonIcon(RibbonIcons.New);
        return getPage(ProgramDetailsPage.class).createNewProgram(publishedProjectsToAdd, false);
    }

    /**
     * Method to create a new program
     *
     * @return program
     */
    public Program createNewProgram() {
        logger().info("Creating new Program");
        clickRibbonIcon(RibbonIcons.New);
        return getPage(ProgramDetailsPage.class).createNewProgram();
    }

    /**
     * Create a new program without Program Budget
     *
     * @param projects      list of projects to be added
     * @param isContingency true if the program is contingency, else false
     * @return program data
     */
    public Program createAProgramWOBudget(List<String> projects, boolean isContingency) {
        getPage(GenericForm.class).clickNew();
        Program program = getPage(ProgramDetailsPage.class).addProgramData(isContingency);
        if (!isContingency) {
            getPage(ProgramDetailsPage.class).addPlannedProjects(projects);
        }
        getPage(ProgramDetailsPage.class).clearProgramBudget();
        getPage(GenericForm.class).clickSave();
        return program;
    }

    /**
     * Method to create a new program with given number of projects.
     *
     * @param numberOfProjectToAdd number of projects to be added to the program
     * @return program
     */
    public Program createNewProgram(int numberOfProjectToAdd) {
        logger().info("Creating new Program");
        navigateTo();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        clickRibbonIcon(RibbonIcons.New);
        return getPage(ProgramDetailsPage.class).createNewProgram(numberOfProjectToAdd);
    }

    /**
     * Validate if a program is present in the list page
     *
     * @param programName - program to be searched
     * @return - true if the program is present
     */
    public boolean validatePresenceOfProgram(String programName) {
        logger().info("Validate if a program is present in the list page");
        filterListPage(ProgramListPageHeader.Name.getValue(), programName, ListPageFilterOptions.Contains);
        return getNumberOfRowsInListPage() != 0;
    }

    /**
     * Method to create a new program with given number of projects.
     *
     * @param numberOfProjectToAdd number of projects to be added to the program
     * @param projectState         State of project(s) to be selected
     * @return program
     */
    public Program createNewProgram(int numberOfProjectToAdd, String projectState) {
        logger().info("Creating new Program");
        navigateTo();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        clickRibbonIcon(RibbonIcons.New);
        return getPage(ProgramDetailsPage.class).createNewProgram(numberOfProjectToAdd, projectState);
    }

    /**
     * Method to validate the view form contents of the program detail page
     *
     * @param program    program data to validate the form
     * @param columnName Name of the column to search in
     * @return true if the validation is successful
     */
    public boolean validateProgramDetailsViewForm(Program program, ProgramListPageHeader columnName) {
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        filterListPage(columnName.getValue(), program.title, ListPageFilterOptions.Contains);
        int rowNum = getRowNumberFromListPage(columnName.getValue(), program.title);
        singleClickOnRowListPage(rowNum);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        var result = getPage(ProgramDetailsViewPage.class).validateProgramDetailsViewForm(program);
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        return result;
    }

    /**
     * Method to change the WF status of a program
     *
     * @param programTitle   name of the program
     * @param workflowAction workflow action to be performed
     * @param expectedStatus expected status after performing workflow action
     * @return true if the wf action is performed successfully
     */
    public boolean changeProgramWorkFlowStatus(String programTitle, WorkFlowActions workflowAction, WorkFlowStatus expectedStatus) {
        navigateTo();
        logger().info("Changing the WF status of program '" + programTitle + "' to " + workflowAction);
        return getPage(WorkFlowHandler.class).workFlowActionProgression(true, true, programTitle, ProgramListPageHeader.Name.getValue(), workflowAction, expectedStatus);
    }

    /**
     * Method to change the WF status of a program
     *
     * @param isNavigationRequired true if navigation is required
     * @param programTitle         name of the program
     * @param workflowAction       workflow action to be performed
     * @param expectedStatus       expected status after performing workflow action
     * @return true if the wf action is performed successfully
     */
    public boolean changeProgramWorkFlowStatus(boolean isNavigationRequired, String programTitle, WorkFlowActions workflowAction, WorkFlowStatus expectedStatus) {
        if (isNavigationRequired) {
            navigateTo();
        }
        filterListPage(ProgramListPageHeader.Name.getValue(), programTitle, ListPageFilterOptions.Contains);
        logger().info("Changing the WF status of program '" + programTitle + "' to " + workflowAction);
        return getPage(WorkFlowHandler.class).workFlowActionProgression(1, workflowAction, expectedStatus);
    }

    /**
     * Validates the status of program Workflow
     *
     * @param programTitle  - title of the program
     * @param expectedState - the state of the new budget estimate after the workflow action is performed
     * @param columnName    - Header name.
     * @return - true for a successful validation of workflow status
     */
    public boolean validateProgramWFStatus(String programTitle, String expectedState, ProgramListPageHeader columnName) {
        navigation.refreshPage();
        int rowNum = getRowNumberFromListPage(columnName.getValue(), programTitle);
        return getCellData(rowNum, "Workflow Status").equals(expectedState);
    }

    /**
     * Edit program description
     *
     * @param programTitle     Title of the program
     * @param programTitleEdit Title to change
     */
    public void editProgram(String programTitle, String programTitleEdit) {
        openEditProgram(programTitle);
        getPage(ProgramDetailsPage.class).editProgram(programTitleEdit);
    }

    /**
     * Program Grid Cancel
     */
    public void programGridCancel() {
        logger().info("Click cancel on Program grid");
        elementHelper.waitForElementClickable(programGridCancel);
        elementHelper.doClick(programGridCancel);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Open Program Edit page
     *
     * @param programTitle Title of the program
     */
    public void openEditProgram(String programTitle) {
        filterListPage(ProgramListPageHeader.Name.getValue(), programTitle, ListPageFilterOptions.Contains);
        singleClickOnRowListPage(1);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
    }

    /**
     * Method to download Document
     */
    public void downloadDocument() {
        logger().info("Click field selection to download the document");
        elementHelper.doClick(fieldSelection);
        elementHelper.doClick(getPage(GenericForm.class).downloadDocument);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Delete program
     *
     * @return true on successful deletion of program
     */
    public boolean deleteProgram() {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Delete);
        waitHelper.waitForAlertPresent();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageTabHeaderToBeClickable();
        if (getPage(ToastUtil.class).validatePresenceOfToastMessage()) {
            return false;
        } else {
            waitHelper.waitForPageToLoad(RibbonIcons.New);
            return true;
        }
    }

    /**
     * Click on Forecast Details Tab.
     */
    public void clickOnForecastDetailsTab() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        elementHelper.doClick(programForecastDetailsTab);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to fetch the column headers and its corresponding index values
     *
     * @return a map of column headers and its corresponding index values
     */
    public HashMap<String, Integer> getScheduleGanttPageColumnMap() {
        waitHelper.waitForPageTabHeaderToBeClickable();
        var headerCells = elementHelper.getElements(headerCellsXpath);
        HashMap<String, Integer> columnMap = new HashMap<>();
        for (int columnIndex = 0; columnIndex < headerCells.size(); columnIndex++) {
            var headerContent = headerCells.get(columnIndex).findElement(By.className("headerContent"));
            if (Strings.isNotNullAndNotEmpty(elementHelper.doGetText(headerContent))) {
                columnMap.put(elementHelper.doGetText(headerContent), columnIndex);
            }
        }
        return columnMap;
    }

    /**
     * Get toast message upon saving
     *
     * @return toast message
     */
    public String getToastMsgOnSaving() {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
    }

    /**
     * saving the program schedule-gantt
     */
    public void saveProgramSchedule() {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Project Cost displayed in program page
     *
     * @return - project cost displayed
     */
    public Double getTotalProjectCostFromProgram() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(getProjectCostValue);
        String projectCostDisplayed = elementHelper.doGetAttribute(getProjectCostValue, "value");
        String regex = "[^0-9.]";
        projectCostDisplayed = projectCostDisplayed.replaceAll(regex, "");
        return Double.parseDouble(projectCostDisplayed);
    }


    /**
     * Validate amount in program forecast page
     *
     * @param amount    calculated amount
     * @param parameter parameter value to identify row
     * @return true on validation of the above
     */
    public boolean validateAmountInForecastDetailsPage(Double amount, String parameter) {
        var listOfData = getPage(ProgramDetailsPage.class).getAllItemListData(ProgramForecastListPageColumns.AmountIn$.getValue());
        var rowNumber = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.ProjectStatus, parameter);
        var amountValue = listOfData.get(rowNumber - 1).get(0);
        var prjAmt = Math.round(Double.parseDouble(amountValue.split("\\.")[0].replace(",", "")));
        return amount == prjAmt;
    }

    /**
     * Validate updating forecast
     *
     * @param programName program name
     * @return true on validation of the above
     */
    public boolean validateUpdatingForecast(String programName) {
        waitHelper.waitForPageToLoad(RibbonIcons.UpdateForecast);
        var expandButton = By.xpath(String.format(programForecastItemExpandButtonTemplate, programName));
        waitHelper.waitForPageToLoad(expandButton);
        clickRibbonIcon(RibbonIcons.UpdateForecast);

        var toastMessage = getPage(ToastUtil.class).waitAndGetMessageForSingleToast(false);
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
        waitHelper.waitForPageTabHeaderToBeClickable();
        return toastMessage.equals(Constants.PROGRAM_FORECAST_PAGE_TOAST_MESSAGE);
    }

    /**
     * Validate expanding or contracting folders
     *
     * @param project     Project name
     * @param programName Program Name
     * @return true on validation of the above
     */
    public boolean validateExpandingOrContractingFoldersInProgramForecastPage(String programName, Project project) {
        var button = By.xpath(String.format(programForecastItemExpandButtonTemplate, programName));
        var projectFormat = String.format("%s - %s", project.code, project.name);
        var projectElementOnExpanding = By.xpath(String.format(projectTemplateInForecastDetailsPageTemplate, projectFormat));
        waitHelper.waitForPageToLoad(button);
        elementHelper.doClick(button);
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(projectElementOnExpanding);
    }

    /**
     * Validate expanding all folders
     *
     * @param parameter  row parameter
     * @param columnName column name of the parameter
     * @return true on validation of the above
     */
    public boolean validateExpandingAllFolders(String parameter, ProgramForecastListPageColumns columnName) {
        getPage(ItemListPage.class).expandItemList();
        var columnData = getPage(ItemListPage.class).getColumnData(columnName.getValue());
        return columnData.contains(parameter);
    }

    /**
     * Collapse all folders
     */
    public void collapseAllFolders() {
        getPage(ItemListPage.class).collapseAll();
    }

    /**
     * Validate Program Title
     *
     * @param programTitle Title to be validated against
     * @return true if validated successfully
     */
    public boolean validateProgramTitle(String programTitle) {
        return getPage(ProgramDetailsViewPage.class).getProgramName().equals(programTitle);
    }

    /**
     * Validate Program grid is available for level 2
     *
     * @return True if the grid is available
     */
    public boolean validateAvailabilityOfProgramGrid() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(programDynamicGridIdInProgram);
    }

    /**
     * Validate Project grid is available for level 1 and 2
     *
     * @return True if the grid is available
     */
    public boolean validateAvailabilityOfProjectGrid() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(projectDynamicGridIdInProgram);
    }


    /**
     * Method to fetch the task details from the list page
     *
     * @param programName    - Program Name
     * @param project        - Project details' to be fetched
     * @param columnsToFetch - list of column names to be  fetched
     * @return Map of column names and its values
     */
    public HashMap<String, String> getTaskDetails(String programName, Project project, List<String> columnsToFetch) {
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        singleClickOnRowListPage(getRowNumberFromListPage(ProgramListPageHeader.Name.getValue(), programName));
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        waitHelper.waitForElementClickable(programForecastDetailsTab);
        elementHelper.doClick(programForecastDetailsTab);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        logger().info("Getting task details for '" + project.name + "'");
        logger().info("Columns to fetch :" + columnsToFetch.toString());
        var columnMap = getScheduleGanttPageColumnMap();
        var contentRows = elementHelper.getElements(contentRowsXpath);

        var parameter = String.format("%s - %s", project.code, project.name);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name, parameter);

        if (rowIndex > contentRows.size()) {
            logger().fail("No rows found with the task name '" + project + "'");
            return new HashMap<>();
        }
        var row = contentRows.get(rowIndex - 1);

        HashMap<String, String> cellValueMap = new HashMap<>();

        for (var columnName : columnsToFetch) {
            if (columnMap.containsKey(columnName)) {
                var cellIndex = columnMap.get(columnName);
                var cells = row.findElements(By.tagName("td"));
                var cellToFetch = cells.get(cellIndex);
                if (!cellToFetch.findElements(By.tagName("div")).isEmpty()) {
                    var textElement = cellToFetch.findElement(textContentCell);
                    cellValueMap.put(columnName, elementHelper.doGetText(textElement));
                } else {
                    cellValueMap.put(columnName, elementHelper.doGetText(cellToFetch));
                }
            } else {
                logger().fail("Column '" + columnName + "' not found on page");
            }
        }
        return cellValueMap;
    }


    /**
     * Method to change the project start date in Forecast details page in Program
     *
     * @param project          - project details
     * @param updatedStartDate - updated date
     * @param saveChanges      - true if save is required
     * @param validate         - true if validation is required
     * @return String value of the Toast message for validation
     */
    public String changeProjectStartDateInProgramForecast(Project project, LocalDate updatedStartDate, boolean saveChanges, boolean validate) {
        var columnMap = getScheduleGanttPageColumnMap();
        var contentRows = elementHelper.getElements(contentRowsXpath);

        var parameter = String.format("%s - %s", project.code, project.name);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name, parameter);

        var row = contentRows.get(rowIndex - 1);
        if (columnMap.containsKey("Start Date")) {
            var cellIndex = columnMap.get("Start Date");
            var cells = row.findElements(By.tagName("td"));
            var startDateCell = cells.get(cellIndex);
            startDateCell.click();
            //clicking again to bring up the date picker
            startDateCell.click();
            getPage(CustomFieldsHelper.class).scheduleGanttPageDatePickerSelectDate(updatedStartDate);

        } else {
            logger().fail("Column 'Start Date' not found on page");
        }
        if (saveChanges) {
            waitHelper.waitForPageToLoad();
            clickRibbonIcon(RibbonIcons.Save);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
        }
        if (validate) {
            return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
        } else return null;
    }

    /**
     * Get program count linked in master program
     *
     * @return number of programs
     */
    public Integer getProgramCountInForm() {
        waitHelper.waitForPageToLoad(programCountInForm);
        elementHelper.scrollToView(programCountInForm);
        return Integer.parseInt(elementHelper.doGetText(programCountInForm));
    }

    //AutoDesk

    /**
     * Validate the Edit list Toolbar changes
     *
     * @return true on validation of available fields
     */
    public boolean validateEditList() {

        List<RibbonIcons> locators = new ArrayList<>();
        locators.add(RibbonIcons.Cancel);
        locators.add(RibbonIcons.Workflow);

        return validateRibbonIcons(locators);
    }

    /**
     * Validate single record selection toolbar values
     *
     * @return true on validation of available fields
     */
    public boolean validateSingleRecordList() {

        List<RibbonIcons> locators = new ArrayList<>();
        locators.add(RibbonIcons.Edit);
        locators.add(RibbonIcons.View);
        locators.add(RibbonIcons.Delete);
        locators.add(RibbonIcons.More);
        locators.add(RibbonIcons.Workflow);
        return validateRibbonIcons(locators);
    }


    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return validateImportExportOptions(true, false) &&
                validateWorkflow() &&
                validateMoreList(false, true, true, false, false, false);
    }

    /**
     * Validate Contextual fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean contextualFieldValidation() {
        return ((validateWorkflowValues(true, false, true)) &&
                (validateMoreList(false, true, false, true, false, false)));
    }

    /**
     * Validate the set of program if its exists or not
     *
     * @param programName Name of the program
     * @return True if all exists
     */
    public boolean validateIfSetOfProgramExists(List<String> programName) {
        boolean programValidation = true;
        waitHelper.waitForPageToLoad();
        for (String s : programName) {
            filterProgramInListPage(s);
            waitHelper.waitForPageToLoad();
            programValidation = programValidation && getColumnData("Name").contains(s);
            waitHelper.waitForPageToLoad();
            logger().info(s + " Exists - " + programValidation);
        }
        return programValidation;
    }

    /**
     * Get year data of Program Forecasts
     *
     * @param year Staring year
     * @return Year Data
     */
    public List<String> addAllYearData(Integer year) {
        List<String> yearData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            yearData.add(Integer.toString(year + i) + "-" + Integer.toString((year + i + 1) % 1000));
        }
        return yearData;
    }

    /***
     * Method to check whether Program element is readonly/noneditable
     *
     * @return - true - if disabled (not editable)
     */
    public boolean isProgramValueNonEditable() {
        var element = elementHelper.getElement(programCount);
        return (Strings.isNotNullAndNotEmpty(element.getAttribute("readonly")) && element.getAttribute("readonly").equals("true"));
    }

    /**
     * Validate presence of the header for grouping programs wrt program category
     *
     * @param category category name
     * @return true on validation of the above
     */
    public boolean validateGroupingByProgramCategory(String category) {
        var header = By.xpath(String.format(listPageProgamCategoryTemplate, category));
        return getPage(Validations.class).verifyElementExists(header);
    }

    /**
     * Validate if the record is in the given work flow state
     *
     * @param parameter  parameter of the record to be filtered
     * @param columnName corresponding enum value
     * @param wfStatus   Workflow Status
     * @return true on if the record is in its final work flow state
     */
    public boolean validateIfProgramIsInTheGivenWfState(String parameter, Enum<?> columnName, WorkFlowStatus wfStatus) {
        singleClickOnRowListPage(String.valueOf(columnName), parameter);
        getPage(WorkFlowHandler.class).openWorkflowHistory();
        var wfStatusElement = By.xpath(String.format(elementHelper.getLocatorAsString(workFlowStateTemplate), wfStatus.getValue()));
        return getPage(Validations.class).verifyElementExists(wfStatusElement);
    }

    /**
     * Edit the program year of the record
     *
     * @param programYear program year to be entered
     */
    public void editProgramYear(String programYear) {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        waitHelper.waitForElementClickable(programYearDropDown);
        elementHelper.selectComboBoxItemByText(programYearDropDown, programYear);
        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Is lock symbol present next to project name
     *
     * @return True if image is present
     */
    public boolean isLockImagePresent(String projectName) {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format("//a[contains(text(),'%s')]/..//img[contains(@src,'lock')]", projectName)));
    }

    /**
     * Select what view option to click on
     *
     * @param viewOption BasicViewEnum option from the list
     */
    public void clickBasicViewButtons(BasicViewEnum viewOption) {
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        switch (viewOption) {
            case BasicView:
                clickRibbonIcon(RibbonIcons.ForecastBasicView);
                break;
            case FundCategoryPhase:
                clickRibbonIcon(RibbonIcons.ForecastFundSourcePhaseView);
                break;
            case FundSourceProject:
                clickRibbonIcon(RibbonIcons.ForecastFundSourceProjectView);
                break;
            case FundCategoryProject:
                clickRibbonIcon(RibbonIcons.ForecastFundCategoryProjectView);
                break;
            case FundSourcePhase:
                clickRibbonIcon(RibbonIcons.ForecastFundCategoryPhaseView);
                break;
        }
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
    }

    /**
     * Validate view page details
     *
     * @return True if view page is opened
     */
    public boolean validateIfViewPageIsOpen() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(remainingFundInForecast);
    }

    /**
     * Validate Excel Data Exported
     *
     * @param fileName    - name of the file exported
     * @param node        - host node
     * @param projectName - the project name to check
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String projectName) {
        waitHelper.waitForPageToLoad();
        getPage(ItemListPage.class).expandItemList();
        List<String> columnHeaders = getPage(ItemListPage.class).itemListPageHeaders();
        List<String> rowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int rowNumber = getPage(ItemListPage.class).getRowNumberOfItem(projectName, ProgramListPageHeader.Name.getValue());
        for (int i = 0; i < columnHeaders.size(); i++) {
            rowValues.add(getPage(ItemListPage.class).getItemData(rowNumber, columnHeaders.get(i)));
            listPageColumnHeadersWithValues.put(columnHeaders.get(i), rowValues.get(i));
        }
        listPageColumnHeadersWithValues.keySet().remove(ProgramListPageHeader.Description.getValue());

        Map<String, String> excelColumnHeadersWithValue = ExcelUtil.getColumnHeadersWithRowData(fileName, node, ProgramListPageHeader.Name.getValue(), projectName, null);

        return (excelColumnHeadersWithValue.keySet().containsAll(listPageColumnHeadersWithValues.keySet()));
    }

    /**
     * Click monthlyView ribbon button
     */
    public void clickMonthlyView() {
        clickRibbonIcon(RibbonIcons.MonthlyView);
    }

    /**
     * This method is used to select the attachment
     *
     * @param rowIdentifier attachment name
     */
    public void selectAttachmentRow(String rowIdentifier) {
        elementHelper.scrollToView(attachmentTable);
        WebElement selectRow = getPage(GenericFormProposed.class).selectRow(GridType.RadGrid, rowIdentifier);
        elementHelper.scrollToView(selectRow);
        selectRow.findElement(By.xpath("./td[not(contains(@style,'display:none;'))]")).click();

    }

    /**
     * This method is used to delete the file and verify the file is deleted
     *
     * @param fileName file name to delete
     * @return true if file deleted else return false
     */
    public boolean deleteFileAndVerify(String fileName) {
        waitHelper.waitForElementClickable(deleteAttachment);
        elementHelper.doClick(deleteAttachment);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        boolean isFileDeleted = getPage(Validations.class).verifyElementExists(By.xpath(String.format("//td[text()='%s']", fileName)));
        getPage(RibbonMenu.class).clickRibbonIcon(RibbonIcons.Save);
        return !isFileDeleted;
    }
}

