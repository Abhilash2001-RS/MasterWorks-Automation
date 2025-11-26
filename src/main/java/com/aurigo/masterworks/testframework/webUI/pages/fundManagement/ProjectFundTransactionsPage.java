package com.aurigo.masterworks.testframework.webUI.pages.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.AuditLogPage;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectFundTransactionsPage extends WorkFlowHandler {

    private By transactionIdLabel;
    private By fundSourceNameLabel;
    private By remainingAmount;
    private By transactionTypeLabel;
    private By transactionDateLabel;
    private By availableAmountInFundSourceLabel;
    private By amountLabel;
    private By notesLabel;
    private By fundSourceNameDropDown;
    private By amountTextBox;
    private By addFundTransaction;
    private By returnFundTransaction;
    private By transferFundTransaction;
    private By fundSourceNameText;
    private By amountText;
    private By transferProject;
    private By availableAmountText;
    private By remainingAmountText;
    private By transactionDateInput;

    public ProjectFundTransactionsPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ProjectFundTransactionsPage.json");
        transactionIdLabel = locators.get("transactionIdLabel");
        fundSourceNameLabel = locators.get("fundSourceNameLabel");
        remainingAmount = locators.get("remainingAmount");
        transactionTypeLabel = locators.get("transactionTypeLabel");
        transactionDateLabel = locators.get("transactionDateLabel");
        availableAmountInFundSourceLabel = locators.get("availableAmountInFundSourceLabel");
        amountLabel = locators.get("amountLabel");
        notesLabel = locators.get("notesLabel");
        fundSourceNameDropDown = locators.get("fundSourceNameDropDown");
        amountTextBox = locators.get("amountTextBox");
        addFundTransaction = locators.get("addFundTransaction");
        returnFundTransaction = locators.get("returnFundTransaction");
        transferFundTransaction = locators.get("transferFundTransaction");
        fundSourceNameText = locators.get("fundSourceNameText");
        amountText = locators.get("amountText");
        transferProject = locators.get("transferProject");
        availableAmountText = locators.get("availableAmountText");
        remainingAmountText = locators.get("remainingAmountText");
        transactionDateInput = locators.get("transactionDateInput");
    }

    /**
     * Labels and Asterisk signs to verify on Project Fund Transaction Page.
     *
     * @return List of locators for labels and asterisk.
     */
    private List<By> labelsAndAsterisk() {
        List<By> labels = new ArrayList<>();
        labels.add(transactionIdLabel);
        labels.add(fundSourceNameLabel);
        labels.add(remainingAmount);
        labels.add(transactionTypeLabel);
        labels.add(transactionDateLabel);
        labels.add(availableAmountInFundSourceLabel);
        labels.add(notesLabel);
        labels.add(amountLabel);
        labels.add(getPage(GenericForm.class).getRequiredAsteriskXpath(fundSourceNameLabel));
        labels.add(getPage(GenericForm.class).getRequiredAsteriskXpath(amountLabel));
        return labels;
    }

    /**
     * Navigate to Project Fund Transaction page.
     */
    public void navigateTo() {
        logger().info("Navigating to Project Fund Transaction page");
        navigation.navigateToFormInLeftPaneTree("Project Fund Transaction");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
    }

    /**
     * Verify Error Message on adding negative value for amount.
     *
     * @return true if no issue.
     */
    public boolean verifyAmountMustBePositive() {
        logger().info("Creating a new 'Project Fund Transaction'");
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickNew();
        //Any Negative value would suffice.
        elementHelper.doSendKeys(amountTextBox, "-100");
        elementHelper.doClick(amountLabel);
        elementHelper.isElementDisplayed(By.xpath("//span[contains(@style,'display: inline') and contains(text(),'Must be greater than 0.')]"));
        getPage(GenericForm.class).clickCancel();
        return true;
    }

    /**
     * Create a new Project Fund Transaction item.
     *
     * @param fundSourceName  -   - Global Fund source name
     * @param transactionType - type of transaction
     * @param amountInDollars - total amount in dollars
     */
    public void createProjectFundTransaction(String fundSourceName, FundTransactionType transactionType, int amountInDollars) {
        logger().info("Creating a new 'Project Fund Transaction'");
        getPage(GenericForm.class).clickNew();
        getPage(GenericForm.class).validateElementsExists(labelsAndAsterisk());
        switch (transactionType) {
            case Return: {
                waitHelper.waitForElementPresent(returnFundTransaction);
                elementHelper.doClickUsingActions(returnFundTransaction);
                break;
            }
            case Add: {
                waitHelper.waitForElementPresent(addFundTransaction);
                elementHelper.doClickUsingActions(addFundTransaction);
                break;
            }
            case Transfer: {
                waitHelper.waitForElementPresent(transferFundTransaction);
                elementHelper.doClickUsingActions(transferFundTransaction);
                break;
            }
        }
        waitHelper.waitForElementClickable(fundSourceNameDropDown);
        elementHelper.selectComboBoxItemByText(fundSourceNameDropDown, fundSourceName);
        waitHelper.waitForElementClickable(amountTextBox);
        if (transactionType == FundTransactionType.Transfer) {
            waitHelper.waitForElementPresent(transferProject);
            elementHelper.selectComboBoxItemByIndex(transferProject, 1);
        }
        waitHelper.waitForElementClickable(amountTextBox);
        elementHelper.doSendKeys(amountTextBox, Integer.toString(amountInDollars));
        elementHelper.doClick(fundSourceNameDropDown);
        elementHelper.doClick(amountTextBox);
        getPage(GenericForm.class).clickSave();
    }

    /**
     * Validate Fund Transaction Creation
     *
     * @param fundSourceName  - Global fund source name
     * @param transactionType - type of transaction
     * @return - returns true when project fund transaction is created, false otherwise
     */
    public boolean validateProjectFundTransactionCreation(String fundSourceName, String transactionType) {
        logger().info("Validating creation of 'Project Fund Transaction'");
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
        var rowNum = getRowNumberFromListPage(ProjectFundTransactionPageColumns.FundName.getValue(), fundSourceName);
        var transactionTypeColumnData = getColumnData(ProjectFundTransactionPageColumns.TransactionType.getValue());
        return transactionTypeColumnData.get(rowNum).equals(transactionType);
    }

    /**
     * Approving a newly created project fund Transaction
     *
     * @param fundSourceName - Global fund source name
     * @return - returns true when the fund transaction is approved, false otherwise
     */
    public boolean approveProjectFundTransaction(String fundSourceName) {
        workFlowActionProgression(false, false, fundSourceName, ProjectFundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        return workFlowActionProgression(false, false, fundSourceName, ProjectFundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);
    }


    /**
     * Validate Project Fund Transaction on List Page.
     *
     * @param fundSourceName  - name of the newly found source created
     * @param transactionType - the type of transaction
     * @return Number of row after filtering with given value.
     */
    public int validateFundTransactionOnListPage(String fundSourceName, FundTransactionType transactionType) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        clearAllFilters();
        filterListPage(ProjectFundTransactionPageColumns.FundName.getValue(), fundSourceName, ListPageFilterOptions.EqualTo);
        filterListPage(ProjectFundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage();
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
        filterListPage(ProjectFundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(ProjectFundTransactionPageColumns.FundName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(amountTextBox, editedAmount);
        getPage(GenericForm.class).clickSave();
        return editedAmount;
    }

    /**
     * View Project Fund Transaction.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @param transactionType    Fund Transaction Type.
     * @param amountToVerify     Amount to verify on view page.
     * @return True if viewed successfully else false.
     */
    public boolean viewFundTransaction(String globalFundListName, FundTransactionType transactionType, String amountToVerify) {
        filterListPage(ProjectFundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(ProjectFundTransactionPageColumns.FundName.getValue(), globalFundListName);
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
        filterListPage(ProjectFundTransactionPageColumns.TransactionType.getValue(), transactionType.name(), ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(ProjectFundTransactionPageColumns.FundName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Re-Draft fund transaction
     *
     * @param globalFundName Name of the global Fund
     * @return - returns true when the fund transaction is Re-Draft, false otherwise
     */
    public boolean reDraftProjectFundTransaction(String globalFundName) {
        boolean submitWorkFlowResult = workFlowActionProgression(globalFundName, ProjectFundTransactionPageColumns.FundName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean reDraftWorkFlowResult = workFlowActionProgression(globalFundName, ProjectFundTransactionPageColumns.FundName.getValue(), WorkFlowActions.ReDraft, WorkFlowStatus.Draft);
        return submitWorkFlowResult && reDraftWorkFlowResult;
    }

    /**
     * Function to verify that expected columns name are present in project fund transaction page
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = ProjectFundTransactionPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param fundSourceName name of the record to select
     */
    public void selectRecordInListPage(String fundSourceName) {
        singleClickOnRowListPage(ProjectFundTransactionPageColumns.FundName.getValue(), fundSourceName);
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
     * @param fundName - name of the fund Name to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String fundName) {
        List<String> projectFundTransactionColumnHeaders = getListPageHeaders();
        List<String> projectFundTransactionRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int projectFundTransactionRowNumber = getRowNumberFromListPage(ProjectFundTransactionPageColumns.FundName.getValue(), fundName);
        for (int i = 0; i < projectFundTransactionColumnHeaders.size(); i++) {
            String s = getCellData(projectFundTransactionRowNumber, projectFundTransactionColumnHeaders.get(i));
            projectFundTransactionRowValues.add(s);
            listPageColumnHeadersWithValues.put(projectFundTransactionColumnHeaders.get(i), projectFundTransactionRowValues.get(i));
        }
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, ProjectFundTransactionPageColumns.FundName.getValue(), fundName, null);
        return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
    }

    /**
     * Opens Audit Log and Enters mandatory fields.
     *
     * @return True if no issue.
     */
    public boolean openAndEnterAuditLogValues() {
        getPage(AuditLogPage.class).openAuditLog();
        return getPage(AuditLogPage.class).selectFormFields("Project Fund Transaction");
    }

    /**
     * View Audit Log and verify data.
     *
     * @return True if data verified, else false.
     */
    public boolean viewAuditLog() {
        getPage(AuditLogPage.class).clickView();
        boolean viewVerified = getPage(AuditLogPage.class).verifyGridColumns(AuditLogGridColumns.getList());
        getPage(GenericForm.class).clickBack();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
        return viewVerified;
    }

    /**
     * Function to get the list of data of name column of all records
     *
     * @param column ProjectFundTransactionPageColumns column enum
     * @return list of data of name column of all records
     */
    public List<String> getDataForNameColumn(ProjectFundTransactionPageColumns column) {
        clearAllFilters();
        return getColumnData(column.getValue());
    }

    /**
     * Enter fund source value in field
     *
     * @param fundName Name of fund to add
     */
    public void enterFundSourceName(String fundName) {
        waitHelper.waitForElementClickable(fundSourceNameDropDown);
        elementHelper.selectComboBoxItemByText(fundSourceNameDropDown, fundName);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get the amount value from field
     *
     * @param isAvailable True if Available amount, false if remaining amount is required
     * @return String value of the amount
     */
    public String getAmountValue(boolean isAvailable) {
        if (isAvailable) {
            return elementHelper.doGetAttribute(availableAmountText, "value");
        } else {
            return elementHelper.doGetAttribute(remainingAmountText, "value");
        }
    }

    /**
     * Validate project is present in selection box
     *
     * @param projectName Project to check if present
     * @return True if project is present
     */
    public boolean validateProjectNamePresentForTransfer(Project projectName) {
        List<String> m = new ArrayList<>();
        elementHelper.getComboBoxSelectOptions(transferProject).forEach(x -> m.add(x.getText()));
        return m.contains(String.format("%s-%s", projectName.code, projectName.name));
    }

    /**
     * Select fund transfer tyoe
     *
     * @param transactionType Type of transaction
     */
    public void selectTransferType(FundTransactionType transactionType) {
        switch (transactionType) {
            case Return: {
                waitHelper.waitForElementPresent(returnFundTransaction);
                elementHelper.doClickUsingActions(returnFundTransaction);
                break;
            }
            case Add: {
                waitHelper.waitForElementPresent(addFundTransaction);
                elementHelper.doClickUsingActions(addFundTransaction);
                break;
            }
            case Transfer: {
                waitHelper.waitForElementPresent(transferFundTransaction);
                elementHelper.doClickUsingActions(transferFundTransaction);
                break;
            }
        }
    }

    /**
     * Enter the date of transactions
     *
     * @param date Date to enter
     */
    public void enterTransactionDate(String date) {
        waitHelper.waitForElementPresent(transactionDateInput);
        elementHelper.doSendKeys(transactionDateInput, date);
    }

    /**
     * Edits Fund Transaction.
     *
     * @param amount          Amount to enter
     * @param transactionType Fund Transaction Type.
     */
    public void enterFundTransferAmount(String amount, FundTransactionType transactionType) {
        waitHelper.waitForPageToLoad();
        switch (transactionType) {
            case Return: {
                waitHelper.waitForElementPresent(returnFundTransaction);
                elementHelper.doClickUsingActions(returnFundTransaction);
                break;
            }
            case Add: {
                waitHelper.waitForElementPresent(addFundTransaction);
                elementHelper.doClickUsingActions(addFundTransaction);
                break;
            }
            case Transfer: {
                waitHelper.waitForElementPresent(transferFundTransaction);
                elementHelper.doClickUsingActions(transferFundTransaction);
                break;
            }
        }
        elementHelper.doSendKeys(amountTextBox, amount);
        getPage(GenericForm.class).clickSave();
    }
}