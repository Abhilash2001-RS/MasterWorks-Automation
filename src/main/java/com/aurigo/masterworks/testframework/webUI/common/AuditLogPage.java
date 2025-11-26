package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditLogPage extends WorkFlowHandler {


    private By fromDatePicker;
    private By formFieldsDropDown;
    private By toDatePicker;
    private By viewButton;
    private By excelExportButton;
    private By auditLogGrid;
    private By auditLogGridHeaders;
    private By auditLogGridData;
    private By formFieldsPlus;
    private By actionDropDownSelect;
    private By actionDropDownOptions;
    private By auditLogHeaders;
    private By actionByDropDownSelect;
    private By auditDataGrid;
    private By actionByLabelInDetailsPage;
    private By noRecordsFoundDisplayed;
    private By checkAllFormFields;
    private By byDurationComboBox;
    private By byDurationComboBoxDropDown;
    private By monthComboBox;
    private By monthComboBoxDropDown;
    private By yearComboBox;
    private By yearComboBoxDropDown;
    private By quarterComboBox;
    private By quarterComboBoxDropDown;
    private By quarterYearComboBox;
    private By quarterYearComboBoxDropDown;
    private By formFieldsText;
    private By actionDropDownArrow;
    private By auditLogTabName;
    private By generateReportButton;
    private By auditLogHeaderName;
    private By auditLogDataValue;

    private final String ACTION_TEMPLATE = "//label[text()='%s']/..//input[@type='checkbox']";

    private WebDriver driver;

    public AuditLogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;


        var locators = LocatorUtil.getLocators("AuditLogPage.json");

        fromDatePicker = locators.get("fromDatePicker");
        formFieldsDropDown = locators.get("formFieldsDropDown");
        toDatePicker = locators.get("toDatePicker");
        viewButton = locators.get("viewButton");
        excelExportButton = locators.get("excelExportButton");
        auditLogGrid = locators.get("auditLogGrid");
        auditLogGridHeaders = locators.get("auditLogGridHeaders");
        auditLogGridData = locators.get("auditLogGridData");
        formFieldsPlus = locators.get("formFieldsPlus");
        actionDropDownSelect = locators.get("actionDropDownSelect");
        actionDropDownOptions = locators.get("actionDropDownOptions");
        auditLogHeaders = locators.get("auditLogHeaders");
        actionByDropDownSelect = locators.get("actionByDropDownSelect");
        auditDataGrid = locators.get("auditDataGrid");
        actionByLabelInDetailsPage = locators.get("actionByLabelInDetailsPage");
        noRecordsFoundDisplayed = locators.get("noRecordsFoundDisplayed");
        checkAllFormFields = locators.get("checkAllFormFields");
        byDurationComboBox = locators.get("byDurationComboBox");
        byDurationComboBoxDropDown = locators.get("byDurationComboBoxDropDown");
        monthComboBox = locators.get("monthComboBox");
        monthComboBoxDropDown = locators.get("monthComboBoxDropDown");
        yearComboBox = locators.get("yearComboBox");
        yearComboBoxDropDown = locators.get("yearComboBoxDropDown");
        quarterComboBox = locators.get("quarterComboBox");
        quarterComboBoxDropDown = locators.get("quarterComboBoxDropDown");
        quarterYearComboBox = locators.get("quarterYearComboBox");
        quarterYearComboBoxDropDown = locators.get("quarterYearComboBoxDropDown");
        formFieldsText = locators.get("formFieldsText");
        actionDropDownArrow = locators.get("actionDropDownArrow");
        auditLogTabName = locators.get("auditLogTabName");
        generateReportButton = locators.get("generateReportButton");
        auditLogHeaderName = locators.get("auditLogHeaderName");
        auditLogDataValue = locators.get("auditLogDataValue");
    }

    /**
     * Select Form Field checkbox in audit log page.
     *
     * @param formFieldName Name of the form field to be selected.
     * @return True if no issues.
     */
    public boolean selectFormFields(String formFieldName) {
        waitHelper.waitForPageToLoad(formFieldsDropDown);
        String formFieldLocatorFormat = "//span[text()='%s']/../preceding-sibling::span[contains(@class,'checked')]";
        elementHelper.doClick(formFieldsDropDown);
        waitHelper.waitForPageToLoad(formFieldsPlus);
        elementHelper.doClickUsingActions(formFieldsPlus);
        waitHelper.waitForPageToLoad(By.xpath(String.format(formFieldLocatorFormat, formFieldName)));
        elementHelper.doClick(By.xpath(String.format(formFieldLocatorFormat, formFieldName)));
        elementHelper.doClick(formFieldsDropDown);
        return true;
    }

    /**
     * Check All Form Fields
     */
    public void checkAllFormFields() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(formFieldsDropDown);
        waitHelper.waitForPageToLoad(formFieldsPlus);
        elementHelper.doClick(checkAllFormFields);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(formFieldsDropDown);
    }

    /**
     * Select Form Field checkbox in audit log page.
     *
     * @param formFieldName   Name of the form field to be selected.
     * @param parentFormField Parent of the form field
     */
    public void selectFormFields(AuditLogFormFields formFieldName, AuditLogFormFields parentFormField) {
        waitHelper.waitForPageToLoad(formFieldsText);
        String formFieldLocatorFormat = "//span[contains(text(),'%s')]/../../..//span[text()='%s']/../preceding-sibling::span[contains(@class,'checked')]";
        elementHelper.doClick(formFieldsText);
        elementHelper.scrollToView(formFieldsDropDown);
        waitHelper.waitForElementClickable(formFieldsDropDown);
        elementHelper.doClick(formFieldsDropDown);
        By parentFormFieldLocator = By.xpath(String.format("//span[text()='%s']/../..//span[@class='rtPlus']", parentFormField.getValue()));
        waitHelper.waitForPageToLoad(parentFormFieldLocator);
        elementHelper.doClickUsingActions(parentFormFieldLocator);
        waitHelper.waitForPageToLoad(By.xpath(String.format(formFieldLocatorFormat, parentFormField.getValue(), formFieldName.getValue())));
        elementHelper.doClick(By.xpath(String.format(formFieldLocatorFormat, parentFormField.getValue(), formFieldName.getValue())));
        elementHelper.doClick(By.xpath(String.format("//span[text()='%s']/../..//span[@class='rtMinus']", parentFormField.getValue())));
        elementHelper.doClick(formFieldsText);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click View in audit log page.
     *
     * @return True if no issue, else false.
     */
    public boolean clickView() {
        waitHelper.waitForPageToLoad(viewButton);
        elementHelper.doClick(viewButton);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Click Excel Export button in audit log page.
     */
    public void clickExcelExportButton() {
        elementHelper.doClick(excelExportButton);
        waitHelper.waitForPageToLoadWithCustomTimeout(180);
    }

    /**
     * Verifies grid column names in audit log page.
     *
     * @param expectedColumnNames List of column names.
     * @return True if no issue.
     */
    public boolean verifyGridColumns(List<String> expectedColumnNames) {
        getPage(GenericForm.class).getRadGridColumnMap(auditLogGrid).keySet().containsAll(expectedColumnNames);
        return true;
    }

    /**
     * Opens Audit Log from ribbon.
     *
     * @return true if no issue.
     */
    public boolean openAuditLog() {
        clickRibbonIcon(RibbonIcons.AuditLog);
        waitHelper.waitForPageToLoad(RibbonIcons.Back);
        navigation.refreshPage();
        return true;
    }

    /**
     * Validate the presence of back button and Excel export button
     *
     * @return true on validation of the above
     */
    public boolean validateBackButtonAndExcelExportTab() {
        return !elementHelper.isElementDisplayed(excelExportButton) && validateRibbonIcon(RibbonIcons.Back);
    }

    /**
     * Fill Date in Audit Log
     *
     * @param fromDate From date
     * @param toDate   To date
     */
    public void fillDate(String fromDate, String toDate) {
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeysUsingAction(toDatePicker, toDate);
        elementHelper.doSendKeysUsingAction(fromDatePicker, fromDate);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get Audit Log data
     *
     * @param columnName Column Name
     * @return Audit Log Data
     */
    public List<String> getAuditLogData(String columnName) {
        waitHelper.waitForPageToLoad(auditDataGrid);
        waitHelper.waitForPageToLoad(auditLogGridHeaders);
        var data = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, columnName, auditLogGridData, auditLogGridHeaders);
        waitHelper.waitForPageTabHeaderToBeClickable();
        return data;
    }


    /**
     * Get Audit Log Column Headers
     *
     * @return Audit log headers
     */
    public List<String> getAuditLogColumnHeaders() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        var headerElements = elementHelper.getElements(auditLogHeaders);
        List<String> headers = new ArrayList<>();
        for (WebElement headerElement : headerElements) {
            String data = elementHelper.doGetText(headerElement);
            if (!data.isEmpty()) {
                headers.add(data);
            }
        }
        return headers;
    }


    /**
     * Select Action in Audit Log
     *
     * @param actions Name of the action
     */
    public void selectAuditLogAction(AuditLogActions actions) {
        waitHelper.waitForPageToLoad(actionDropDownSelect);
        elementHelper.doClick(actionDropDownSelect);
        waitHelper.waitForElementPresent(actionDropDownOptions);
        waitHelper.waitForPageToLoad();
        By checkAll = By.xpath(String.format(ACTION_TEMPLATE, AuditLogActions.CheckAll.getValue()));
        JavaScriptUtil.clickElementByJS(elementHelper.getElement(checkAll), driver);
        waitHelper.waitForPageToLoad();
        JavaScriptUtil.clickElementByJS(elementHelper.getElement(By.xpath(String.format(ACTION_TEMPLATE, actions.getValue()))), driver);
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(actionDropDownArrow);
    }

    /**
     * is All Form Field Selected
     *
     * @param formsFields List of form Fields
     * @return true if all forms fields are selected
     */
    public boolean isAllFormFieldsSelected(List<String> formsFields) {
        waitHelper.waitForPageToLoad(actionDropDownSelect);
        elementHelper.doClick(actionDropDownSelect);
        waitHelper.waitForElementPresent(actionDropDownOptions);
        boolean isSelected = false;
        for (String formsField : formsFields) {
            By field = By.xpath(String.format(ACTION_TEMPLATE, formsField));
            elementHelper.scrollToView(field);
            if (elementHelper.isCheckBoxSelected(field)) {
                isSelected = true;
            } else {
                isSelected = false;
                break;
            }

        }
        return isSelected;
    }

    /**
     * Filter Results in Audit Log
     *
     * @param columnTextBoxField Name of the column (Use Enum of Audit Column Name Text Box)
     * @param value              Value to Filter
     * @param filterOptions      Filter Options
     */
    public void filterResultsInAuditLog(AuditLogGridTextBoxFields columnTextBoxField, String value, ListPageFilterOptions filterOptions) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();

        By filterTextBox = By.xpath(String.format("//input[contains(@id,'FilterTextBox_%s')]", columnTextBoxField.getValue()));
        By filterButton = By.xpath(String.format("//button[contains(@id,'Filter_%s')]", columnTextBoxField.getValue()));
        By filterOption = By.xpath(String.format("//span[text()='%s']", filterOptions.getValue()));

        elementHelper.doSendKeysUsingAction(filterTextBox, value);
        waitHelper.waitForPageToLoad(filterButton);
        elementHelper.doClickUsingActions(filterButton);
        waitHelper.waitForPageToLoad(filterOption);
        elementHelper.doClickUsingActions(filterOption);
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(auditDataGrid);
        waitHelper.waitForPageToLoad();

    }

    /**
     * Select Actions By In Audit Log
     *
     * @param userName Name of the user
     */
    public void selectActionByDropDown(String userName) {
        waitHelper.waitForPageToLoad(actionByDropDownSelect);
        elementHelper.doClick(actionByDropDownSelect);
        waitHelper.waitForPageToLoad();
        String actionsTemplate = "//label[text()='%s']/..//input[@type='checkbox']";
        waitHelper.waitForPageToLoad();
        By checkAll = By.xpath(String.format(actionsTemplate, AuditLogActions.CheckAll.getValue()));
        JavaScriptUtil.clickElementByJS(elementHelper.getElement(checkAll), driver);
        waitHelper.waitForPageToLoad();
        JavaScriptUtil.clickElementByJS(elementHelper.getElement(By.xpath(String.format(actionsTemplate, userName))), driver);
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(actionByLabelInDetailsPage);
    }

    /**
     * validation of Audit Log Grid
     *
     * @return true if present
     */
    public boolean isGridPresent() {
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(auditDataGrid);
    }

    /**
     * Validate the Tab
     *
     * @param formTabName Name of the form tab to validate
     * @return True if the tab is present else false
     */
    public boolean validateTab(String formTabName) {
        return getPage(Validations.class).verifyElementExists(By.xpath(String.format("//a[text()='%s']", formTabName)));
    }

    /**
     * Is Audit Log Enabled
     *
     * @return true if enabled
     */
    public boolean isAuditLogEnabled() {
        return validateRibbonIcon(RibbonIcons.AuditLog);
    }

    /**
     * Check if No records are displayed in The Audit Log Grid
     *
     * @return true if no records is displayed
     */
    public boolean isNoRecordsFoundDisplayed() {
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(noRecordsFoundDisplayed);
    }

    /**
     * Validate Excel Export
     *
     * @param rowIdentifier Row Identifier
     * @param fileName      File Name
     * @param columnName    Column Name
     * @param node          Node
     * @param downloadPath  download Path
     * @return True if excel data and grid Data is validated Successfully
     */
    public boolean validateExcelExport(String rowIdentifier, String fileName, String columnName, Host node, String downloadPath) {
        waitHelper.waitForPageToLoad();
        FileHelper.waitForFileToBeAvailable(fileName, 100, 10, node, downloadPath);
        boolean isFileExists = FileHelper.isFileExists(fileName, node, downloadPath);
        logger().info("File Exists - " + isFileExists);
        logger().info("Validation Excel Export Scenario");

        Map<String, String> auditLogData = getPage(GenericFormProposed.class).getRowData(GridType.RadGrid, columnName, auditLogGridData, auditLogGridHeaders, rowIdentifier);
        if (!auditLogData.isEmpty()) {
            logger().pass("Retrieved Audit Log Data");
        } else {
            logger().fail("Retrieved Audit Log Data Failed");
        }
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, columnName, rowIdentifier, null, downloadPath);

        if (!excelColumnHeadersWithValue.isEmpty()) {
            logger().pass("Retrieved Audit Log Data From Excel Successfully");
        } else {
            logger().fail("Retrieved Audit Log Data Failed from Excel");
        }
        return auditLogData.equals(excelColumnHeadersWithValue);
    }

    /**
     * Click On Duration
     *
     * @param auditLogDurationType Audit Log Duration Type
     */
    public void selectDurationBy(AuditLogDurationType auditLogDurationType) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(byDurationComboBox);
        waitHelper.waitForPageToLoad(byDurationComboBoxDropDown);
        var type = By.xpath(String.format("//li[contains(text(),'%s')]", auditLogDurationType.getValue()));
        elementHelper.doClick(type);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select Month For By Month
     *
     * @param month Month
     */
    public void selectMonthForByMonth(String month) {

        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(monthComboBox);
        elementHelper.doClickUsingActions(monthComboBox);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(monthComboBoxDropDown);
        elementHelper.doClickUsingActions(By.xpath(String.format("//li[contains(text(),'%s')]", month)));
    }

    /**
     * Select Year for By Month
     *
     * @param year Year
     */
    public void selectYearForByMonth(String year) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(yearComboBox);
        waitHelper.waitForElementPresent(yearComboBoxDropDown);
        elementHelper.doClickUsingActions(By.xpath(String.format("//li[contains(text(),'%s')]", year)));
    }

    /**
     * Select Quarter
     *
     * @param quarter Quarter
     */
    public void selectQuarter(String quarter) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(quarterComboBox);
        waitHelper.waitForPageToLoad(quarterComboBoxDropDown);
        elementHelper.doClickUsingActions(By.xpath(String.format("//li[contains(text(),'%s')]", quarter)));
    }

    /**
     * Select Year For By Quarter
     *
     * @param year Year
     */
    public void selectYearForByQuarter(String year) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(quarterYearComboBox);
        waitHelper.waitForElementPresent(quarterYearComboBoxDropDown);
        elementHelper.doClickUsingActions(By.xpath(String.format("//li[contains(text(),'%s')]", year)));
    }

    /**
     * Validate Parent Child Categorization
     *
     * @param parentChildData parent Child data Formation
     * @return true if validated successfully
     */
    public boolean validateParentChildFormFields(Map<String, List<String>> parentChildData) {
        navigation.switchFrameToContent();
        String formFieldLocatorFormat = "//span[contains(text(),'%s')]/../../..//span[contains(text(), '%s')]/../preceding-sibling::span[contains(@class,'checked')]";
        boolean childCheck = true;
        boolean parentCheck = true;
        for (int i = parentChildData.size() - 1; i >= 0; i--) {
            waitHelper.waitForPageToLoad();
            var parent = parentChildData.keySet().toArray()[i];
            By parentFormFieldLocator = By.xpath(String.format("//span[contains(text(),'%s')]/../..//span[@class='rtPlus']", parentChildData.keySet().toArray()[i]));
            Integer childSize = parentChildData.get(parent).size();
            Integer j = 0;
            waitHelper.waitForPageToLoad(formFieldsDropDown);
            elementHelper.doClickUsingActions(formFieldsDropDown);
            waitHelper.waitForPageToLoad(parentFormFieldLocator);
            elementHelper.doClickUsingActions(parentFormFieldLocator);
            waitHelper.waitForPageToLoad();
            while (childSize != 0) {
                if (childCheck) {
                    childCheck = elementHelper.isElementDisplayed(By.xpath(String.format(formFieldLocatorFormat, parentChildData.keySet().toArray()[i],
                            parentChildData.get(parent).get(j))));
                    logger().info("Parent - " + parentChildData.keySet().toArray()[i] + " Child - " + parentChildData.get(parent).get(j) + " -" + childCheck);
                } else {
                    childCheck = false;
                    logger().info("Parent Child Wrong in Form Fields");
                    break;
                }
                j++;
                childSize--;
            }
            navigation.refreshPage();
            waitHelper.waitForPageToLoad();
            parentCheck = parentCheck && childCheck;
        }
        return parentCheck;
    }

    /**
     * Verifying AuditLog Page Name
     *
     * @return true AuditLogTabName displayed else return false
     */
    public boolean verifyAuditLogPage() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(auditLogTabName);

    }

    /**
     * Click Generate Report button in audit log page.
     */
    public void clickGenerateReportButton() {
        waitHelper.waitForElementClickable(generateReportButton);
        elementHelper.doClick(generateReportButton);
        waitHelper.waitForPageToLoadWithCustomTimeout(300);
    }

    /**
     * This method is used to verify excel with empty data and it will compare Page data along with excel data
     *
     * @param rowIdentifier row name to identify the row
     * @param fileName      Excel filename to validate
     * @param columnName    column name to identify the column
     * @param node          currently running node
     * @param downloadPath  downloaded file path
     * @param sheetName     sheetName
     * @return true if values matched else return false
     */
    public boolean validateExcelExportForEmptyData(String rowIdentifier, String fileName, String columnName, Host node, String downloadPath, String sheetName) {
        waitHelper.waitForPageToLoad();
        boolean isFileExists = FileHelper.isFileExists(fileName, node, downloadPath);
        logger().info("File Exists - " + isFileExists);
        logger().info("Validation Excel Export Scenario");

        Map<String, String> auditLogData = mapAuditLogHeaderAndData();
        if (!auditLogData.isEmpty()) {
            logger().pass("Retrieved Audit Log Data");
        } else {
            logger().fail("Retrieved Audit Log Data Failed");
        }

        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, columnName, rowIdentifier, sheetName, downloadPath);

        if (!excelColumnHeadersWithValue.isEmpty()) {
            logger().pass("Retrieved Audit Log Data From Excel Successfully");
        } else {
            logger().fail("Retrieved Audit Log Data Failed from Excel");
        }

        return auditLogData.equals(excelColumnHeadersWithValue);
    }

    /**
     * this method is used to map audit log header and data
     *
     * @return audit log header and data
     */
    public Map<String, String> mapAuditLogHeaderAndData() {
        List<WebElement> headerElements = elementHelper.getElements(auditLogHeaderName);
        List<WebElement> gridDataElements = elementHelper.getElements(auditLogDataValue);
        int minSize = Math.min(headerElements.size(), gridDataElements.size());
        Map<String, String> mapAuditLogData = new HashMap<>();
        for (int i = 0; i < minSize; i++) {
            mapAuditLogData.put(headerElements.get(i).getText().trim(), gridDataElements.get(i).getText().trim());
        }
        return mapAuditLogData;
    }

    /**
     * Generates audit log for settings page for update action
     */
    public void generateSettingsAuditLogforUpdate() {
        clickRibbonIcon(RibbonIcons.Settings);
        clickRibbonIcon(RibbonIcons.AuditLog);
        selectAuditLogAction(AuditLogActions.Update);
        checkAllFormFields();
        clickGenerateReportButton();
    }

    /**
     * This method is used to open audit log
     *
     * @param actions    -AuditLogActions to be selected
     * @param userName   -userName to be selected
     * @param formFields -AuditLogFormFields to be selected
     */
    public void openAuditLogAndViewData(AuditLogActions actions, String userName, AuditLogFormFields formFields) {
        openAuditLog();
        selectAuditLogAction(actions);
        selectActionByDropDown(userName);
        selectFormFields(formFields.getValue());
        clickView();
    }

}
