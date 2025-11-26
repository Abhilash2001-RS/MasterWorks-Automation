package com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.AuditLogPage;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FundTransactionPage extends WorkFlowHandler {

    private By transactionIdLabel;
    private By fundSourceNameLabel;
    private By fundSourceCodeLabel;
    private By fundSourceCategoryLabel;
    private By fundSourceTypeLabel;
    private By transactionTypeLabel;
    private By transactionDateLabel;
    private By availableAmountInFundSourceLabel;
    private By amountLabel;
    private By notesLabel;
    private By fundSourceNameDropDown;
    private By amount;
    private By addFundTransaction;
    private By returnFundTransaction;
    private By fundSourceNameText;
    private By amountText;
    private By transactionDate;

    private WebDriver driver;

    public FundTransactionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("FundTransactionPage.json");
        transactionIdLabel = locators.get("transactionIdLabel");
        fundSourceNameLabel = locators.get("fundSourceNameLabel");
        fundSourceCodeLabel = locators.get("fundSourceCodeLabel");
        fundSourceCategoryLabel = locators.get("fundSourceCategoryLabel");
        fundSourceTypeLabel = locators.get("fundSourceTypeLabel");
        transactionTypeLabel = locators.get("transactionTypeLabel");
        transactionDateLabel = locators.get("transactionDateLabel");
        availableAmountInFundSourceLabel = locators.get("availableAmountInFundSourceLabel");
        amountLabel = locators.get("amountLabel");
        notesLabel = locators.get("notesLabel");
        fundSourceNameDropDown = locators.get("fundSourceNameDropDown");
        amount = locators.get("amount");
        addFundTransaction = locators.get("addFundTransaction");
        returnFundTransaction = locators.get("returnFundTransaction");
        fundSourceNameText = locators.get("fundSourceNameText");
        amountText = locators.get("amountText");
        transactionDate = locators.get("transactionDate");
    }

    /**
     * Labels and Asterisk signs to verify on Fund Transaction Page.
     *
     * @return List of locators for labels and asterisk.
     */
    private List<By> labelsAndAsterisk() {
        List<By> labels = new ArrayList<>();
        labels.add(transactionIdLabel);
        labels.add(fundSourceNameLabel);
        labels.add(fundSourceCodeLabel);
        labels.add(fundSourceCategoryLabel);
        labels.add(fundSourceTypeLabel);
        labels.add(transactionTypeLabel);
        labels.add(transactionDateLabel);
        labels.add(availableAmountInFundSourceLabel);
        labels.add(notesLabel);
        labels.add(amountLabel);
        labels.add(getPage(GenericForm.class).getRequiredAsteriskXpath(fundSourceNameLabel));
        return labels;
    }

    /**
     * List of required label
     *
     * @return true if labels are verified
     */
    public boolean verifyRequiredLabels() {
        List<By> labels = new ArrayList<>();
        getPage(GenericForm.class).clickNew();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(fundSourceNameLabel));
        labels.add(getPage(GenericForm.class).getRequiredWarningXpath(amountLabel));
        boolean values = getPage(GenericForm.class).validateElementsExists(labels);
        getPage(GenericForm.class).clickCancel();
        return values;
    }

    /**
     * Navigate to Fund Transaction page.
     */
    public void navigateTo() {
        logger().info("Navigating to Fund Transaction");
        navigation.navigateToFormInLeftPaneTree("Fund Transaction");
        navigation.switchFrameToContent();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
    }

    /**
     * Creating a new Fund Transaction
     *
     * @param fundSourceName  - name of the newly found source created
     * @param transactionType - the type of transaction
     * @param amountInDollars - The amount to be entered
     */
    public void createFundTransaction(String fundSourceName, FundTransactionType transactionType, int amountInDollars) {
        logger().info("Creating a new 'Fund Transaction'");
        getPage(GenericForm.class).clickNew();
        getPage(GenericForm.class).validateElementsExists(labelsAndAsterisk());
        waitHelper.waitForPageToLoad(fundSourceNameDropDown);
        elementHelper.selectComboBoxItemByText(fundSourceNameDropDown, fundSourceName);
        waitHelper.waitForPageTabHeaderToBeClickable();
        switch (transactionType) {
            case Return: {
                waitHelper.waitForElementClickable(returnFundTransaction);
                elementHelper.doClickUsingActions(returnFundTransaction);
                break;
            }
            case Add: {
                waitHelper.waitForElementClickable(addFundTransaction);
                elementHelper.doClickUsingActions(addFundTransaction);
                break;
            }
        }
        elementHelper.doSendKeys(amount, Integer.toString(amountInDollars));
        elementHelper.doClick(fundSourceNameDropDown);
        elementHelper.doClick(amount);
        getPage(GenericForm.class).clickSave();
    }


    /**
     * Add Amount
     *
     * @param amountToDollars Amount in Dollars
     */
    public void addAmount(String amountToDollars) {
        elementHelper.doSendKeys(amount, amountToDollars);
    }

    /**
     * Verify if only positive amount could be added to global fund transction.
     *
     * @return True if verified.
     */
    public boolean verifyOnlyPositiveAmountCanBeAdded() {
        logger().info("Creating a new 'Fund Transaction'");
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForPageToLoad();
        //Any negative value would suffice.
        elementHelper.doSendKeys(amount, "-100");
        elementHelper.doClick(amountLabel);
        elementHelper.isElementDisplayed(By.xpath("//span[contains(@style,'display: inline') and contains(text(),'Must be between 0 and 1000000000000000.')]"));
        getPage(GenericForm.class).clickCancel();
        return true;
    }

    /**
     * Check if Global Fund exists in the drop down.
     *
     * @param fundSourceName Fund Source name.
     * @return True is exists else false.
     */
    public boolean checkIfGlobalFundListExistsInDropDown(String fundSourceName) {
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickNew();
        boolean fundSourceExists = elementHelper.getComboBoxSelectOptions(fundSourceNameDropDown).contains(fundSourceName);
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickCancel();
        return fundSourceExists;
    }

    /**
     * Validate Fund Transaction on List Page.
     *
     * @param fundSourceName  - name of the newly found source created
     * @param transactionType - the type of transaction
     * @return true if the record is found in list page
     */
    public boolean validateFundTransactionOnListPage(String fundSourceName, FundTransactionType transactionType) {
        clearAllFilters();
        filterListPage(FundTransactionPageColumns.FundName.getValue(), fundSourceName, ListPageFilterOptions.Contains);
        var rowNum = getRowNumberFromListPage(FundTransactionPageColumns.FundName.getValue(), fundSourceName);
        var transactionTypeColumnData = getColumnData(FundTransactionPageColumns.TransactionType.getValue());
        if (transactionTypeColumnData.isEmpty()) {
            return false;
        } else {
            return transactionTypeColumnData.get(rowNum).equals(transactionType.name());
        }
    }

    /**
     * Edits Fund Transaction.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @param transactionType    Fund Transaction Type.
     * @return Edited global fund list name.
     */
    public String editFundTransaction(String globalFundListName, FundTransactionType transactionType) {
        String editedAmount = String.valueOf(TestDataUtil.getRandomNumber(100000, 900000));
        clearAllFilters();
        filterListPage(FundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(FundTransactionPageColumns.FundName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(amount, editedAmount);
        getPage(GenericForm.class).clickSave();
        return editedAmount;
    }

    /**
     * View Global Fund List.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @param transactionType    Fund Transaction Type.
     * @param amountToVerify     Amount to verify on view page.
     * @return True if viewed successfully else false.
     */
    public boolean viewFundTransaction(String globalFundListName, FundTransactionType transactionType, String amountToVerify) {
        filterListPage(FundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(FundTransactionPageColumns.FundName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickView();
        boolean fundListNameVerified = elementHelper.doGetText(fundSourceNameText).equals(globalFundListName);
        boolean fundAmountVerified = Float.parseFloat(elementHelper.doGetAttribute(amountText, "value")
                .replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""))
                == Float.parseFloat(amountToVerify);
        getPage(GenericForm.class).clickCancel();
        return fundListNameVerified && fundAmountVerified;
    }

    /**
     * Delete Fund Transaction.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @param transactionType    Fund Transaction Type.
     */
    public void deleteFundTransaction(String globalFundListName, FundTransactionType transactionType) {
        filterListPage(FundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(FundTransactionPageColumns.FundName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Approve fund transaction
     *
     * @param globalFundName Name of the global Fund
     * @return - returns true when the fund transaction is approved, false otherwise
     */
    public boolean approveFundTransaction(String globalFundName) {
        boolean submitWorkFlowResult = workFlowActionProgression(false, true, globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean approveWorkFlowResult = workFlowActionProgression(false, true, globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);
        return submitWorkFlowResult && approveWorkFlowResult;
    }

    /**
     * Approve fund transaction without filtering
     *
     * @param globalFundName Name of the global Fund
     * @return - returns true when the fund transaction is approved, false otherwise
     */
    public boolean approveFundTransactionWithoutFiltering(String globalFundName) {
        boolean submitWorkFlowResult = workFlowActionProgression(false, false, globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean approveWorkFlowResult = workFlowActionProgression(false, false, globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);
        return submitWorkFlowResult && approveWorkFlowResult;
    }

    /**
     * Re-Draft fund transaction
     *
     * @param globalFundName Name of the global Fund
     * @return - returns true when the fund transaction is Re-Draft, false otherwise
     */
    public boolean reDraftFundTransaction(String globalFundName) {
        boolean submitWorkFlowResult = workFlowActionProgression(globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean reDraftWorkFlowResult = workFlowActionProgression(globalFundName, FundTransactionPageColumns.FundName.getValue(), WorkFlowActions.ReDraft, WorkFlowStatus.Draft);
        return submitWorkFlowResult && reDraftWorkFlowResult;
    }

    /**
     * Function to verify that expected columns name are present in fund transaction page.
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = FundTransactionPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param globalFundName name of the record to select
     */
    public void selectRecordInListPage(String globalFundName) {
        singleClickOnRowListPage(FundTransactionPageColumns.FundName.getValue(), globalFundName);
    }

    /**
     * Function to get the fund name of the record using the Transaction ID
     *
     * @param transactionId to filter the record
     * @return fund name of the filtered record
     */
    public String getFundNameUsingTransactionID(String transactionId) {
        clearAllFilters();
        filterListPage(FundTransactionPageColumns.TransactionID.getValue(), transactionId, ListPageFilterOptions.EqualTo);
        int rowNum = getRowNumberFromListPage(FundTransactionPageColumns.TransactionID.getValue(), transactionId);
        return getCellData(rowNum, FundTransactionPageColumns.FundName.getValue());
    }

    /**
     * Function to verify that workflow history is correctly displaying the statuses
     *
     * @param expectedStatuses statuses which should be present in workflow history table
     * @return true if the verification is successful, else returns false
     */
    public boolean verifyWorkflowHistory(List<String> expectedStatuses) {
        openWorkflowHistory();
        waitHelper.waitForPageToLoad();
        return validateWorkflowHistory(expectedStatuses);
    }


    /**
     * Validate Excel Data Exported
     *
     * @param fileName - name of the file exported
     * @param node     - host node
     * @param fundName - name of the fund to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String fundName) {
        waitHelper.waitForPageToLoad();
        List<String> fundTransactionColumnHeaders = getListPageHeaders();
        List<String> fundTransactionRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        waitHelper.waitForPageToLoad();
        int fundTransactionRowNumber = getRowNumberFromListPage(FundTransactionPageColumns.FundName.getValue(), fundName);
        for (int i = 0; i < fundTransactionColumnHeaders.size(); i++) {
            String s = getCellData(fundTransactionRowNumber, fundTransactionColumnHeaders.get(i));
            fundTransactionRowValues.add(s);
            listPageColumnHeadersWithValues.put(fundTransactionColumnHeaders.get(i), fundTransactionRowValues.get(i));
        }
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, FundTransactionPageColumns.FundName.getValue(), fundName, null);
        return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
    }

    /**
     * Opens Audit Log and Enters mandatory fields.
     *
     * @return True if no issue.
     */
    public boolean openAndEnterAuditLogValues() {
        return getPage(AuditLogPage.class).selectFormFields("Fund Transaction");
    }

    /**
     * View Audit Log and verify data.
     *
     * @return True if data verified, else false.
     */
    public boolean viewAuditLog() {
        getPage(AuditLogPage.class).clickView();
        navigation.switchFrameToContent();
        boolean viewVerified = getPage(AuditLogPage.class).verifyGridColumns(AuditLogGridColumns.getList());
        getPage(GenericForm.class).clickBack();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
        return viewVerified;
    }

    /**
     * Validate if global fund is available in fund transaction page
     *
     * @param globalFundName Name of the fund to check
     * @return true if global fund is available
     */
    public boolean isGlobalFundAvailable(String globalFundName) {
        waitHelper.waitForPageToLoad();
        List<WebElement> values = elementHelper.getComboBoxSelectOptions(fundSourceNameDropDown);
        return values.stream().anyMatch(x -> x.getText().equals(globalFundName));
    }

    /**
     * Validate if global fund is available in fund transaction page
     *
     * @param globalFundName Name of the fund to check
     * @return amount in $ value
     */
    public String getAmountInDollarValueFromListPage(String globalFundName) {
        waitHelper.waitForPageToLoad();
        int row = getRowNumberFromListPage(FundTransactionPageColumns.FundName.getValue(), globalFundName);
        return getCellData(row, FundTransactionPageColumns.Amount.getValue()).split("\\.")[0].replace(RegexStrings.comma.getValue(), Constants.EMPTY_STRING);
    }

    //AutoDesk

    /**
     * Validate Single Record selection Toolbar
     *
     * @return true on validation fields displayed
     */
    public boolean validateSingleRecordList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Edit);
        ribbonMenuIcons.add(RibbonIcons.View);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.WorkflowAction);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Contextual fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean contextualFieldValidation() {
        return ((validateWorkflowValues(true, false, true)) &&
                (validateReportList(false, true, false, false)));
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return validateImportExportOptions(true, false) &&
                validateWorkflow() &&
                validateMoreList(false, true, true, true, false, false);
    }

    /**
     * Fill Transaction Date
     *
     * @param date Date
     */
    public void fillTransactionDate(String date) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(transactionDate);
        elementHelper.doSendKeys(transactionDate, date);
    }

    /**
     * Change Transaction Type
     *
     * @param transactionType Type of transaction
     */
    public void changeTransactionType(FundTransactionType transactionType) {
        switch (transactionType) {
            case Return: {
                waitHelper.waitForElementClickable(returnFundTransaction);
                elementHelper.doClickUsingActions(returnFundTransaction);
                break;
            }
            case Add: {
                waitHelper.waitForElementClickable(addFundTransaction);
                elementHelper.doClickUsingActions(addFundTransaction);
                break;
            }
        }
    }
}
