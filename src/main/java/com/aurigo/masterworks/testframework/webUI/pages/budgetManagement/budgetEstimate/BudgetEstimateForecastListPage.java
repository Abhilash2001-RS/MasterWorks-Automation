package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.DateTimeUtil;
import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.Navigation;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetEstimateForecastListPage extends ListPage {

    private By budgetEstimateForecastListTab;
    private By forecastDescriptionTextArea;
    private By selectForecastArrowButton;
    private By itemDetailsHeaderList;
    private By firstAmountDistributionInputBox;
    private By getAmountFromFirstInputBox;
    private By ganttViewTab;
    private By forecastsDetailsTab;
    private By forecastTab;

    private By headersInForcastDetailsPage;

    private By forecastSave;

    private String inputTextBoxFields = "//input[@type='text']";
    private String forecastOption = "//li[contains(text(),'%s')]";

    private String budgetEstimateForecastPageName = "Budget Management/Forecasts";

    public BudgetEstimateForecastListPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("BudgetEstimateForecastListPage.json");
        budgetEstimateForecastListTab = locators.get("budgetEstimateForecastListTab");
        forecastDescriptionTextArea = locators.get("forecastDescriptionTextArea");
        selectForecastArrowButton = locators.get("selectForecastArrowButton");
        itemDetailsHeaderList = locators.get("itemDetailsHeaderList");
        firstAmountDistributionInputBox = locators.get("firstAmountDistributionInputBox");
        getAmountFromFirstInputBox = locators.get("getAmountFromFirstInputBox");
        ganttViewTab = locators.get("ganttViewTab");
        forecastTab = locators.get("forecastTab");
        forecastsDetailsTab = locators.get("forecastsDetailsTab");
        headersInForcastDetailsPage = locators.get("headersInForcastDetailsPage");
        forecastSave = locators.get("forecastSave");
    }

    /**
     * This method changes the forecast status based on forecast ID
     *
     * @param forecastId                 - Forecast ID for which status needs to be changed
     * @param workFlowAction             - The workflow action to be performed on the Forecast ID
     * @param expectedStateAfterWFAction - Status change of the forecast status based on the forecast Id
     * @return true if the status is changed for the selected forecast
     */
    public boolean changeForecastStatus(String forecastId, WorkFlowActions workFlowAction, WorkFlowStatus expectedStateAfterWFAction) {
        logger().info("Changing the Forecast status of forecastId:" + forecastId + " to " + workFlowAction);
        var isWFActionSuccess = getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, forecastId, "Forecast ID", workFlowAction, expectedStateAfterWFAction);
        if (isWFActionSuccess) {
            logger().pass("Successfully changed the workflow status of forecastId:" + forecastId + " to " + expectedStateAfterWFAction);
            return true;
        } else {
            logger().fail("Failed to change the workflow status of forecastId:" + forecastId + " to " + expectedStateAfterWFAction);
            return false;
        }

    }

    /**
     * Get error on forecast
     *
     * @param forecastID           - the forecast ID
     * @param workFlowAction       - workflow action
     * @param expectedStatus       -  the status expected
     * @param verificationRequired - true if verification is required
     * @return - toast message displayed
     */
    public String getErrorOnForecast(String forecastID, WorkFlowActions workFlowAction, WorkFlowStatus expectedStatus, boolean verificationRequired) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(budgetEstimateForecastListTab);
        waitHelper.waitForElementToBePresentAndClickable(getRibbonIcon(RibbonIcons.NewDropDownInForecastAndBidLettingAndPayEstimatesAndCO));
        getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, forecastID, "Forecast ID", workFlowAction, expectedStatus, verificationRequired);
        waitHelper.waitForPageToLoad();
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
    }

    /**
     * Validate Forecast Status
     *
     * @param forecastIdOrDescription    - the ID of forecast or Description
     * @param expectedStateAfterWFAction - the Workflow Status Expected
     * @return - true if the expected workflow status matches the displayed one
     */
    public boolean verifyForecastStatus(String forecastIdOrDescription, WorkFlowStatus expectedStateAfterWFAction) {
        waitHelper.waitForPageToLoad();
        if (NumberUtils.isParsable(forecastIdOrDescription)) {
            return getColumnData(ForecastListPageColumnsOld.WorkflowStatus.getValue()).get(getRowNumberFromListPage(ForecastListPageColumnsOld.ForecastId.getValue(), forecastIdOrDescription))
                    .equalsIgnoreCase(expectedStateAfterWFAction.getValue());
        } else {
            return getColumnData(ForecastListPageColumnsOld.WorkflowStatus.getValue()).get(getRowNumberFromListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastIdOrDescription))
                    .equalsIgnoreCase(expectedStateAfterWFAction.getValue());
        }
    }

    /**
     * This method changes the forecast status based on forecast Description
     *
     * @param forecastDescription        - Forecast Description for which status needs to be changed
     * @param workFlowAction             - The workflow action to be performed on the Forecast Description
     * @param expectedStateAfterWFAction - Status change of the forecast status based on the forecast Description
     * @return true if the status is changed for the selected forecast
     */
    public boolean changeForecastStatusWithForecastDescription(String forecastDescription, WorkFlowActions workFlowAction, WorkFlowStatus expectedStateAfterWFAction) {
        logger().info("Changing the Forecast status of forecastDescription:" + forecastDescription + " to " + workFlowAction);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(budgetEstimateForecastListTab);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.NewDropDownInForecastAndBidLettingAndPayEstimatesAndCO));
        var isWFActionSuccess = getPage(WorkFlowHandler.class).workFlowActionProgression(true, false, forecastDescription, "Forecast Description", workFlowAction, expectedStateAfterWFAction);
        if (isWFActionSuccess) {
            logger().pass("Successfully changed the workflow status of forecastDescription:" + forecastDescription + " to " + expectedStateAfterWFAction);
            return true;
        } else {
            logger().fail("Failed to change the workflow status of forecastDescription:" + forecastDescription + " to " + expectedStateAfterWFAction);
            return false;
        }

    }

    /**
     * To edit a forecast
     *
     * @param oldForecastDescription - the old forecast description
     * @param newForecastDescription - the new forecast description
     * @return - true if forecast is updated
     */
    public boolean editAndValidateForecast(String oldForecastDescription, String newForecastDescription) {
        clearAllFilters();
        singleClickOnRowListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), oldForecastDescription);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitUntilElementAppears(forecastDescriptionTextArea);
        elementHelper.doSendKeys(forecastDescriptionTextArea, newForecastDescription);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        clearAllFilters();
        filterListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), newForecastDescription, ListPageFilterOptions.EqualTo);
        int getForecastRowNumber = getNumberOfRowsInListPage();
        return getForecastRowNumber != 0;
    }

    /**
     * Validate view functionality of Forecast list page
     *
     * @param forecastDescription - description of the forecast
     * @return - true if no input fields are present and save button is not displayed in the ribbon
     */
    public boolean viewAndValidateForecast(String forecastDescription) {
        clearAllFilters();
        singleClickOnRowListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        String saveButton = "Save";
        List<String> ribbonButtonList = getAllRibbonButtonsDisplayed();
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();
        return (!(ribbonButtonList.contains(saveButton)));
    }

    /**
     * Verify is the forecast is editable
     *
     * @param forecastDescription - the forecast to be tested
     * @return - true if forecast is editable
     */
    public boolean isForecastEditable(String forecastDescription) {
        clearAllFilters();
        singleClickOnRowListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        boolean saveButtonVisibility = elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.Save));
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();
        return saveButtonVisibility;
    }

    /**
     * Creating a new forecast
     *
     * @param forecastDescription - forecast description
     */
    public void createNewForecast(String forecastDescription) {
        clickRibbonIcon(RibbonIcons.NewForecast);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        elementHelper.doSendKeys(forecastDescriptionTextArea, forecastDescription);
        elementHelper.doClick(forecastSave);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Copy and Validate Existing forecast
     *
     * @param forecastDescription - description of the existing forecast
     * @return - true if forecast is copied
     */
    public boolean copyAndValidateExistingForecast(String forecastDescription) {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.CopyFromExisting);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(selectForecastArrowButton);
        elementHelper.doClick(selectForecastArrowButton);
        var forecastOptionToBeSelected = String.format(forecastOption, forecastDescription);
        waitHelper.waitUntilElementAppears(By.xpath(forecastOptionToBeSelected));
        elementHelper.doClick(By.xpath(forecastOptionToBeSelected));
        waitHelper.waitForPageToLoad(forecastDescriptionTextArea);
        elementHelper.doSendKeys(forecastDescriptionTextArea, forecastDescription);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        clearAllFilters();
        filterListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription, ListPageFilterOptions.EqualTo);
        int rowCount = getNumberOfRowsInListPage();
        return rowCount > 1;
    }

    /**
     * Delete a forecast and validate
     *
     * @param forecastDescription - forecast ti be deleted
     * @return - true if the forecast is deleted, false otherwise
     */
    public boolean deleteForecast(String forecastDescription) {
        navigation.refreshPage();
        clearAllFilters();
        singleClickOnRowListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription);
        clickRibbonIcon(RibbonIcons.Delete);
        return deleteARecord(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription);
    }

    /**
     * verify Item Details Page In Forecast
     *
     * @param payItemNo   - the pay item number
     * @param totalAmount - total amount of the pay item
     * @return - true if the pay items total amount displayed matches with the actual total amount
     */
    public boolean verifyItemDetailsPageInForecast(String payItemNo, String totalAmount) {
        elementHelper.waitForPageTabHeaderToBeClickable();
        getPage(BudgetEstimateItemsPage.class).performBudgetEstimateAutoForecast();
        singleClickOnRowListPage(0);
        String totalAmountDisplayed = getForecastAmountDetail(payItemNo,BudgetEstimateGanttViewColumnNames.AmountInDollar);
        return totalAmountDisplayed.equals(totalAmount);
    }

    /**
     * verify expected column headers with the displayed column headers
     *
     * @param expectedColumnNames - the column names expected
     * @return - true if the header name matches
     */
    public boolean verifyAvailableColumnsInItemDetails(List<String> expectedColumnNames) {
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        List<WebElement> headersDisplayedInItemDetailsElement = elementHelper.getElements(itemDetailsHeaderList);
        List<String> headersDisplayedInItemDetails = new ArrayList<>();
        for (int i = 0; i < headersDisplayedInItemDetailsElement.size(); i++) {
            elementHelper.scrollToView(headersDisplayedInItemDetailsElement.get(i));
            headersDisplayedInItemDetails.add(headersDisplayedInItemDetailsElement.get(i).getText());
        }

        return headersDisplayedInItemDetails.containsAll(expectedColumnNames);
    }

    /**
     * Distribute remaining amount if any in item details page
     *
     * @param itemName Name of the item
     * @return - true when amount gets distributed
     */
    public boolean distributeRemainingAmountInItemDetails(String itemName) {
        logger().info("Distribute remaining amount if any in item details page");
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.Edit);
        elementHelper.waitForPageTabHeaderToBeClickable();
        double remainingAmount = Double.parseDouble(getForecastAmountDetail(itemName, BudgetEstimateGanttViewColumnNames.RemainingAmountIn$).replaceAll(RegexStrings.comma.getValue(), ""));
        if (remainingAmount == 0.0) {
            return true;
        } else {
            var columns = elementHelper.getElements(By.xpath("//table[@class='ui-widget-header ui-frozen-head-table']//th/div"));
            int col;
            for (col = 1; col <= columns.size(); col++) {
                if (elementHelper.doGetText(columns.get(col)).contains(BudgetEstimateGanttViewColumnNames.Name.getValue())) {
                    break;
                }
            }
            int row;
            var elements = elementHelper.getElements(By.xpath(String.format("//div[@class='ui-widget-content ui-body-frozen-div']//tr/td[%d]//div[contains(@class,'text')]", col + 1)));
            for (row = 1; row <= elements.size(); row++) {
                if (elementHelper.doGetText(elements.get(row)).contains(itemName)) {
                    break;
                }
            }
            By newElement = By.xpath(String.format(elementHelper.getLocatorAsString(getAmountFromFirstInputBox), row + 1));
            elementHelper.scrollToView(newElement);
            var element = elementHelper.getElement(newElement);
            double firstAmount = Double.parseDouble(elementHelper.doGetText(element.findElement(By.xpath("./div"))));
            elementHelper.waitForElementClickable(element);
            elementHelper.doDoubleClick(element);
            elementHelper.doSendKeys(firstAmountDistributionInputBox, remainingAmount + firstAmount);
            clickRibbonIcon(RibbonIcons.Save);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
            remainingAmount = Double.parseDouble(getForecastAmountDetail(itemName, BudgetEstimateGanttViewColumnNames.RemainingAmountIn$).replaceAll(RegexStrings.comma.getValue(), ""));
            clickRibbonIcon(RibbonIcons.Save);
            getPage(AlertHandler.class).acceptAlert(true);
            return remainingAmount == 0.0;
        }
    }

    /**
     * Open forecast in edit state
     */
    public void openForecastInEditState(){
        singleClickOnRowListPage(0);
        getPage(GenericForm.class).clickEdit();
    }

    /**
     * Navigate to Schedule Gantt View page
     */
    public void navigateToGanttViewPage(){
        getPage(BudgetEstimateItemsPage.class).navigateToForecastTab();
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.ShowGantt);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate Excel Import
     *
     * @param fileName - name of the file
     * @param lineNumber - line number of the data
     * @param project - project data
     * @return - true if validation of import is successful, else false
     */
    public boolean validateExcelImport(String fileName, String lineNumber, Project project) {
        List<String> columnHeadersToBeUpdated = new ArrayList<>();
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.LineNumber.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.PayItemNo.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.Description.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.SubItemId.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.StartDate.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.EndDate.getValue());
        columnHeadersToBeUpdated.add(ScheduleGanttPageColumns.ProgressPercentage.getValue());

        var scheduleGanttImportData = TestDataUtil.generateScheduleGanttImportData(lineNumber, project);

        List<String> rowData = new ArrayList<>();
        rowData.add(scheduleGanttImportData.lineNumber);
        rowData.add(scheduleGanttImportData.payItemNumber);
        rowData.add(scheduleGanttImportData.description);
        rowData.add("");
        rowData.add(scheduleGanttImportData.startDate);
        rowData.add(scheduleGanttImportData.endDate);
        rowData.add(scheduleGanttImportData.progress);

        List<List<String>> addRowToImport = new ArrayList<>();
        addRowToImport.add(rowData);

        Map<List<String>, List<List<String>>> allHeaderAndData = new HashMap<>();
        allHeaderAndData.put(columnHeadersToBeUpdated, addRowToImport);

        String sheetName = "Schedule";
        ExcelUtil.writeExcelDataWithColumnHeader(fileName, allHeaderAndData, sheetName);
        return importFile(fileName);
    }

    /**
     * Get Total Amount
     * @param projectName Project NAme
     * @return total Amount
     */
    public String getAmountFromScheduleGantt(String projectName)
    {
        waitHelper.waitForPageToLoad();
        ArrayList<String> headerName = new ArrayList<>();
        elementHelper.getElements(headersInForcastDetailsPage).forEach(e->{elementHelper.scrollToView(e);
        headerName.add(e.getText().trim());});
        int index = headerName.indexOf("Amount in $");
        By amount = By.xpath(String.format("//div[@class='ui-widget-content ui-grid-body']/descendant::tr[1][contains(@class,'rq-grid-row')]/td[%d]", (index+1)));
        elementHelper.scrollToView(amount);
        waitHelper.waitForPageToLoad(amount);
        return elementHelper.doGetText(amount);
    }

    /**
     * Validating Gantt View Columns
     * @param columnNames - column names to be verified
     * @return true, if expected columns are displayed
     */
    public boolean validateBEGanttColumns(List<String> columnNames)
    {
        logger().info("Validating Gantt View Columns");
        String getTitle = "//th[contains(@title,'%s')]";
        boolean isColumnDisplayed = true;
        waitHelper.waitForPageToLoad();
        for (int i = 0; i < columnNames.size(); i++) {
            elementHelper.scrollToView(By.xpath(String.format(getTitle, columnNames.get(i))));
            isColumnDisplayed = elementHelper.isElementDisplayed(By.xpath(String.format(getTitle, columnNames.get(i))));
            if (!isColumnDisplayed) {
                isColumnDisplayed = false;
                break;
            }
        }
        return isColumnDisplayed;
    }

    /**
     * Validating Start Date and End Date
     * @param startDate - project start date
     * @param endDate - project end date
     * @return - true if start date and end date is available
     */
    public boolean validateStartAndEndDateInBEGantt(String startDate, String endDate)
    {
        logger().info("Validating Start Date and End Date");
        String dateLocator = "//*[text()='%s']";
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format(dateLocator, startDate))) &&
                elementHelper.isElementDisplayed(By.xpath(String.format(dateLocator, endDate)));
    }

    /**
     * Validate Pay Item In BE Gantt
     * @param payItemNo - pay item no
     * @return - true if pay item is displayed
     */
    public boolean validatePayItemInBEGantt(String payItemNo)
    {
        logger().info("Validate Pay Item In BE Gantt");
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format("//div[contains(text(),'%s')]", payItemNo)));
    }

    /**
     * Validate BE Total Amount In Gantt
     * @param projectName - project name
     * @param budgetEstimateTotalAmount - Budget Estimate Total Amount
     * @return - true if both the amounts matches
     */
    public boolean validateBETotalAmountInGantt(String projectName,String budgetEstimateTotalAmount)
    {
        logger().info("Validate BE Total Amount In Gantt");
        waitHelper.waitForPageToLoad();
        String totalAmount = getAmountFromScheduleGantt(projectName);
        return budgetEstimateTotalAmount.equals(totalAmount);
    }

    /**
     * Validate total calender days in gantt view
     * @param totalCalenderDays - total project duration in days
     * @return - true if calender days matches
     */
    public boolean validateTotalProjectDurationInBEGantt(String totalCalenderDays)
    {
        logger().info("Validate total calender days in gantt view");
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format("//td/div[text()='%s']", (totalCalenderDays + " days"))));
    }

    /**
     * Validate Show Critical Path
     * @return - true if Hide Critical Path is displayed
     */
    public boolean validateShowCriticalPathInBEGantt()
    {
        logger().info("Validate Show Critical Path");
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.ShowCriticalPath);
        waitHelper.waitForPageToLoad();
        return validateRibbonIcon(RibbonIcons.HideCriticalPath);
    }

    /**
     * Verify Save And Forecast Button In BE Gantt
     * @return - true if Save and Forecast button is displayed
     */
    public boolean verifySaveAndForecastButtonInBEGantt()
    {
        logger().info("Verify Save And Forecast Button In BE Gantt");
        return validateRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Validating print button
     * @return - true if print button is enabled
     */
    public boolean validatePrintButton()
    {
        logger().info("Validating print button");
        return validateRibbonIcon(RibbonIcons.Print);
    }

    /**
     * Navigate to list page
     */
    public void navigateTo(){
        logger().info("Navigating to Budget Estimate Forecast page");
        getPage(Navigation.class).navigateToFormInLeftPaneTree(budgetEstimateForecastPageName);
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Edit Gantt Details
     * @param fieldsValueMapping Fields to be edited
     * @param dateFormat Date format
     * @param editedFields Edited Values which need to be edited
     */
    public void editGanttDetails(Map<String, String> fieldsValueMapping, String dateFormat, Map<String, String> editedFields) {
        String fieldTemplate = "//div[contains(text(),'%s')]/../..//td[text()='%s']";
        String fieldTemplateTwo = "//div[contains(text(),'%s')]/../..//div[text()='%s']";
        String fieldTemplateThree = "//div[contains(text(),'%s')]/../..//td[text()='%s']/following-sibling::td//div[not(@style)]";
        String inputTemplate = "//div[contains(text(),'%s')]/../..//input";
        waitHelper.waitForPageToLoad();
        elementHelper.doDoubleClick(By.xpath(String.format(fieldTemplate, fieldsValueMapping.get("payItem"), fieldsValueMapping.get("project Start Date"))));
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem"), fieldsValueMapping.get("project Start Date")))).clear();
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem"), fieldsValueMapping.get("project Start Date")))).sendKeys(editedFields.get("project Start Date"));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(ganttViewTab);
        elementHelper.doDoubleClick(By.xpath(String.format(fieldTemplateTwo, fieldsValueMapping.get("payItem"), DateTimeUtil.dateGenerationFromToday(1, 0, 1, dateFormat))));
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem")))).clear();
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem")))).sendKeys(editedFields.get("project End Date"));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(ganttViewTab);
        elementHelper.doDoubleClick(By.xpath(String.format(fieldTemplate, fieldsValueMapping.get("payItem"), fieldsValueMapping.get("progress Percentage"))));
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem")))).sendKeys(editedFields.get("progress Percentage"));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(ganttViewTab);
        elementHelper.doDoubleClick(By.xpath(String.format(fieldTemplateThree, fieldsValueMapping.get("payItem"), editedFields.get("progress Percentage"))));
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem"), editedFields.get("progress Percentage")))).clear();
        elementHelper.getElement(By.xpath(String.format(inputTemplate, fieldsValueMapping.get("payItem"), editedFields.get("progress Percentage")))).sendKeys(editedFields.get("predecessor"));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(ganttViewTab);
    }


    /**
     * Edit Amount
     * @param payItem Pay Item Name
     * @param previousAmount Previous Amount Value
     * @param newAmountValue New Amount Value
     */
    public void editAmount(String payItem, String previousAmount, String newAmountValue) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        elementHelper.doDoubleClick(By.xpath(String.format("//div[text() = 'Linear']/../..//td[@canedit='true']//div[text()='%s']", previousAmount)));
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(By.xpath(String.format("//div[text()='Linear']/../..//input[not(@type='checkbox')]", payItem)), newAmountValue);
        elementHelper.doClick(By.xpath("//div[text()='Linear']"));
        navigation.switchFrameToContent();
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
    }

    /**
     * Get forecast page detail
     * @param rowIdentifier Row identifier
     * @param columnToFind  Column to get information from
     * @return Get forecast as a String
     */
    public String getForecastAmountDetail(String rowIdentifier, BudgetEstimateGanttViewColumnNames columnToFind) {
        waitHelper.waitForPageToLoad();
        var columns = elementHelper.getElements(By.xpath("//table[@class='ui-widget-header ui-frozen-head-table']//th/div"));
        int col;
        for (col = 1; col <= columns.size(); col++) {
            if (elementHelper.doGetText(columns.get(col)).contains(BudgetEstimateGanttViewColumnNames.Name.getValue())) {
                break;
            }
        }
        int row;
        var elements = elementHelper.getElements(By.xpath(String.format("//div[@class='ui-widget-content ui-body-frozen-div']//tr/td[%d]//div[contains(@class,'text')]", col + 1)));
        for (row = 1; row <= elements.size(); row++) {
            if (elementHelper.doGetText(elements.get(row)).contains(rowIdentifier)) {
                break;
            }
        }
        columns = elementHelper.getElements(By.xpath("//table[@class='ui-widget-header ui-grid-head-table']//th/div"));
        for (col = 1; col <= columns.size(); col++) {
            if (elementHelper.doGetText(columns.get(col)).contains(columnToFind.getValue())) {
                break;
            }
        }
        return elementHelper.doGetText(By.xpath(String.format("//div[@class='ui-widget-content ui-grid-body']//tr[%d]//td[%d]/div", row + 1, col + 1)));
    }

    /**
     * Navigate to Forecast Details Tab
     */
    public void navigateToForecastsDetailsTab(){
        waitHelper.waitForPageToLoad(forecastsDetailsTab);
        elementHelper.doClick(forecastsDetailsTab);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Edit Gantt View Date
     * @param date Date
     * @param newDate New Date
     */
    public void editGanttViewDate(String date, String newDate) {
        navigation.switchFrameToContent();
        By previousDate = By.xpath(String.format("//tr[not(contains(@style,'bold'))]//div[text()='%s']", date));
        elementHelper.doDoubleClick(previousDate);
        waitHelper.waitForPageToLoad();
        By newDateGantt = By.xpath(String.format("//a[text()='%s']", newDate));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(newDateGantt);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get Total Amount
     *
     * @return total Amount
     */
    public String getAmountFromScheduleGantt() {
        waitHelper.waitForPageToLoad();
        By amount = By.xpath("//div[contains(@title,'This amount represents inflated total from')]");
        waitHelper.waitForPageToLoad(amount);
        return elementHelper.doGetText(amount);
    }

    /**
     * Open the forecast in edit state
     *
     * @param forecastDescription forecast descriptions
     */
    public void openForecastInEditState(String forecastDescription){
        clearAllFilters();
        singleClickOnRowListPage(ForecastListPageColumnsOld.ForecastDescription.getValue(), forecastDescription);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
    }
}
