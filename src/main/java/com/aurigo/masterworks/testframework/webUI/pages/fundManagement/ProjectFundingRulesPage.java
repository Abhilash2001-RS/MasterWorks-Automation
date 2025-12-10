package com.aurigo.masterworks.testframework.webUI.pages.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectFundingRulesPage extends WorkFlowHandler {

    private By fundRuleNameTextBox;
    private By addFundSourceName;
    private By editFundSourceName;
    private By pickerGrid;
    private By selectGlobalFund;
    private By percentageTextBox;
    private By saveDistributionDetails;
    private By viewRuleNameValue;
    private By isActiveCheckbox;
    private By fundRuleNotesTextBox;
    private By distributionGrid;
    private By addDistributionDetails;
    private By deleteDistributionDetails;
    private By editDistributionDetails;
    private By ruleRevisionID;
    private By fundRuleTextNotes;
    private By deleteFundSourceName;
    private By distributionGridHeader;
    private By gridClick;

    public ProjectFundingRulesPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ProjectFundingRulesPage.json");
        fundRuleNameTextBox = locators.get("fundRuleNameTextBox");
        addFundSourceName = locators.get("addFundSourceName");
        editFundSourceName = locators.get("editFundSourceName");
        pickerGrid = locators.get("pickerGrid");
        selectGlobalFund = locators.get("selectGlobalFund");
        percentageTextBox = locators.get("percentageTextBox");
        saveDistributionDetails = locators.get("saveDistributionDetails");
        viewRuleNameValue = locators.get("viewRuleNameValue");
        isActiveCheckbox = locators.get("isActiveCheckbox");
        fundRuleNotesTextBox = locators.get("fundRuleNotesTextBox");
        distributionGrid = locators.get("distributionGrid");
        addDistributionDetails = locators.get("addDistributionDetails");
        deleteDistributionDetails = locators.get("deleteDistributionDetails");
        editDistributionDetails = locators.get("editDistributionDetails");
        ruleRevisionID = locators.get("ruleRevisionID");
        fundRuleTextNotes = locators.get("fundRuleTextNotes");
        deleteFundSourceName = locators.get("deleteFundSourceName");
        distributionGridHeader = locators.get("distributionGridHeader");
        gridClick = locators.get("gridClick");
    }

    /**
     * Navigate to Project Funding Rules Page
     */
    public void navigateTo() {
        logger().info("Navigating to Project Funding Rules Page");
        navigation.navigateToFormInLeftPaneTree("Funding Rules");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
    }

    /**
     * Function to open a new form of Funding Rule
     */
    public void openNewForm() {
        logger().info("Opening a new form of Funding Rule");
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForElementClickable(fundRuleNameTextBox);
    }

    /**
     * Check if Copy button is available
     *
     * @return True if the copy button is enabled and displayed
     */
    public boolean validateCopyButton() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementEnabled(getRibbonIcon(RibbonIcons.Copy));
    }

    /**
     * Creating a new Funding Rule
     *
     * @param fundRuleName   - Name of the Funding Rule
     * @param globalFundName - Name of the Global Fund Created
     */
    public void createFundingRule(String fundRuleName, String globalFundName) {
        logger().info("Creating a new 'Funding Rule'");
        openNewForm();
        waitHelper.waitForElementClickable(fundRuleNameTextBox);
        elementHelper.doSendKeys(fundRuleNameTextBox, fundRuleName);
        elementHelper.doClick(addFundSourceName);
        getPage(Picker.class).singleSelectByText("Fund Source Name", globalFundName, pickerGrid);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(percentageTextBox);
        elementHelper.doSendKeysUsingAction(percentageTextBox, "100");
        elementHelper.doClick(gridClick);
        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Validate Fund Rule Creation
     *
     * @param fundRuleName - name of the newly created fund rule
     * @return - True if funding rule is created, false otherwise
     */
    public boolean validateFundingRuleCreation(String fundRuleName) {
        logger().info("Validating funding rule creation");
        waitHelper.waitForPageTabHeaderToBeClickable();
        return getRowNumberFromListPage("Rule Name", fundRuleName) != -1;
    }

    /**
     * Approving a newly Funding Rule
     *
     * @param fundRuleName - name of the newly created fund rule
     * @return - returns true when the Funding Rule is approved, false otherwise
     */
    public boolean approveFundingRule(String fundRuleName) {
        //getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, fundRuleName, "Rule Name", WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        return getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, fundRuleName, "Rule Name", WorkFlowActions.Approve, WorkFlowStatus.Approved);
    }

    /**
     * Method to get the fundRule Name
     *
     * @return the fund rule name
     */
    public String getFundRuleName() {
        return getCellData(0, ProjectFundingRulesPageColumns.RuleName.getValue());
    }

    /**
     * Function to verify that expected columns name are present in project funding rules page
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = ProjectFundingRulesPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param fundRule name of the record to select
     */
    public void selectRecordInListPage(String fundRule) {
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRule);
    }

    /**
     * Function to open Fund Rule in Edit mode
     *
     * @param fundRuleName name of the fund rule to open
     */
    public void openFundRuleInEditMode(String fundRuleName) {
        selectRecordInListPage(fundRuleName);
        clickRibbonIcon(RibbonIcons.Edit);
    }

    /**
     * Edit Funding Rule.
     *
     * @param fundRule            rule name of the fund rule to be edited
     * @param editedFundRuleValue value for editing
     */
    public void editFundingRule(String fundRule, String editedFundRuleValue) {
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRule);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(fundRuleNameTextBox, editedFundRuleValue);
        getPage(GenericForm.class).clickSave();
    }

    /**
     * function to verify fund rule view operation
     *
     * @param fundRuleName name of the fund rule
     * @return true if the fund rule view is verified successfully
     */
    public boolean verifyViewingFundRule(String fundRuleName) {
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRuleName);
        getPage(GenericForm.class).clickView();
        boolean fundRuleNameVerified = elementHelper.doGetText(viewRuleNameValue).equals(fundRuleName);
        getPage(GenericForm.class).clickCancel();
        return fundRuleNameVerified;
    }

    /**
     * Function to delete funding Rule
     *
     * @param fundRuleName name of the fund rule to be deleted
     */
    public void deleteFundingRule(String fundRuleName) {
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRuleName);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Function to verify that if fund rule record is present on the list page
     *
     * @param fundRuleName name of the fund rule to be validated
     * @return true if the record is present, else false
     */
    public boolean verifyIfFundingRulePresentOnListPage(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        clearAllFilters();
        filterListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRuleName, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage() == 1;
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
     * Function to enter rule name
     *
     * @param ruleName data to enter
     */
    public void enterRuleName(String ruleName) {
        elementHelper.doSendKeys(fundRuleNameTextBox, ruleName);
    }

    /**
     * Function to select fund name from Distribution Details table picker
     *
     * @param fundName name of the fund to be selected from picker
     */
    public void selectDistributionDetailsFromPicker(String fundName) {
        elementHelper.doClick(addFundSourceName);
        getPage(Picker.class).singleSelectByText("Fund Source Name", fundName, pickerGrid);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectGlobalFund);
    }

    /**
     * Function to edit percentage in distribution details
     *
     * @param percentageValue percentage value to enter
     */
    public void editPercentageInDistributionDetails(String percentageValue) {
        elementHelper.doClick(editFundSourceName);
        waitHelper.waitForElementPresent(percentageTextBox);
        elementHelper.doSendKeysUsingAction(percentageTextBox, percentageValue);
        elementHelper.doClick(saveDistributionDetails);
    }

    /**
     * Function to click on Save button
     */
    public void clickOnSave() {
        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Function to validate that expected toast message is displayed
     *
     * @param expectedMsg message which is expected to be displayed
     * @return true if expected and actual toast messages are same, else it returns false
     */
    public boolean validateToastMessage(String expectedMsg) {
        if (getPage(ToastUtil.class).validatePresenceOfToastMessage()) {

            String actualMsg = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
            return expectedMsg.contains(actualMsg);
        } else {
            return false;
        }
    }

    /**
     * Function to uncheck the Is Active checkbox if it is checked
     */
    public void markFundingRuleInactiveUsingCheckbox() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        if ((elementHelper.doGetAttribute(isActiveCheckbox, "checked")).equals("true")) {
            elementHelper.doClick(isActiveCheckbox);
        }
    }

    /**
     * Function to get cell data from a row
     *
     * @param rowIdentifier            value to uniquely identify a row
     * @param columnNameToSearchIn     name of the column to search the row identifier value
     * @param columnNameOfExpectedData column name from which the data is expected
     * @return data from the target cell
     */
    public String getCellData(String rowIdentifier, String columnNameToSearchIn, String columnNameOfExpectedData) {
        return getCellData(getRowNumberFromListPage(columnNameToSearchIn, rowIdentifier), columnNameOfExpectedData);
    }

    /**
     * Function to change the status of the fund rule to active, if it is inactive already
     * OR to inactive if it is active already
     *
     * @param fundingRuleName name of the fund rule record
     */
    public void changeStatus(String fundingRuleName) {
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundingRuleName);
        var status = getCellData(fundingRuleName, ProjectFundingRulesPageColumns.RuleName.getValue(), ProjectFundingRulesPageColumns.IsActive.getValue());

        if (status.equals("true")) {
            logger().info("Funding Rule is marked Active, hence changing the status to InActive");
        } else if (status.equals("false")) {
            logger().info("Funding Rule is marked InActive, hence changing the status to Active");
        }
        clickRibbonIcon(RibbonIcons.ChangeStatusAsActiveInactive);
    }

    /**
     * Function to click on Mark as Default button in the ribbon
     */
    public void clickMarkAsDefault() {
        clickRibbonIcon(RibbonIcons.MarkAsDefault);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Re-Draft a newly created funding rule record
     *
     * @param fundingRuleName - name of the fund rule record
     * @return - returns true when the funding rule is re-drafted, false otherwise
     */
    public boolean validateReDraftFundingRuleRecord(String fundingRuleName) {
        boolean submittedWorkFlowResult = workFlowActionProgression(fundingRuleName, ProjectFundingRulesPageColumns.RuleName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean reDraftWorkFlowResult = workFlowActionProgression(fundingRuleName, ProjectFundingRulesPageColumns.RuleName.getValue(), WorkFlowActions.ReDraft, WorkFlowStatus.Draft);
        return submittedWorkFlowResult && reDraftWorkFlowResult;
    }

    /**
     * Validate Excel Data Exported
     *
     * @param fileName - name of the file exported
     * @param node     - host node
     * @param ruleName - name of the rule to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String ruleName) {
        List<String> fundingRulesColumnHeaders = getListPageHeaders();
        List<String> fundingRulesRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int fundingRulesRowNumber = getRowNumberFromListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), ruleName);
        for (int i = 0; i < fundingRulesColumnHeaders.size(); i++) {
            fundingRulesRowValues.add(getCellData(fundingRulesRowNumber, fundingRulesColumnHeaders.get(i)));
            if (fundingRulesRowValues.get(i).equals("true")) {
                listPageColumnHeadersWithValues.put(fundingRulesColumnHeaders.get(i), "True");
            } else if (fundingRulesRowValues.get(i).equals("false")) {
                listPageColumnHeadersWithValues.put(fundingRulesColumnHeaders.get(i), "False");
            } else {
                listPageColumnHeadersWithValues.put(fundingRulesColumnHeaders.get(i), fundingRulesRowValues.get(i));
            }
        }
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, ProjectFundingRulesPageColumns.RuleName.getValue(), ruleName, null);
        return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
    }


    /**
     * View Audit Log and verify data.
     *
     * @return True if data verified, else false.
     */
//    public boolean viewAuditLog() {
//        getPage(AuditLogPage.class).clickView();
//        boolean viewVerified = getPage(AuditLogPage.class).verifyGridColumns(AuditLogGridColumns.getList());
//        getPage(GenericForm.class).clickBack();
//        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
//        return viewVerified;
//    }

    /**
     * Edit fund rule name
     *
     * @param newFundingRuleName new funding rule name
     */
    public void editFundingRule(String newFundingRuleName) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(fundRuleNameTextBox);
        elementHelper.doSendKeys(fundRuleNameTextBox, newFundingRuleName);
        getPage(GenericForm.class).clickSave();
    }

    /**
     * Validate funding rule name
     *
     * @param fundRuleName Fund rule name
     * @return true if validated successfully
     */
    public boolean validateFundingRule(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(viewRuleNameValue);
        boolean fundRuleNameVerified = elementHelper.doGetText(viewRuleNameValue).equals(fundRuleName);
        getPage(GenericForm.class).clickCancel();
        return fundRuleNameVerified;
    }

    /**
     * method to check if Funding Rule isHighlighted
     *
     * @param toCheckHighlighted string to be checked
     * @return true if highlighted
     */
    public boolean isFundingRuleHighlighted(String toCheckHighlighted) {
        navigation.switchFrameToDefault();
        String leftPlaneTreeTemplate = navigation.getXpathForLeftPaneTreeItem(toCheckHighlighted);
        By portfolio = By.xpath(leftPlaneTreeTemplate);
        String isBold = elementHelper.doGetAttribute(portfolio, "class");
        navigation.switchFrameToContent();
        return isBold.equals("bold-text");
    }

    /**
     * Function to get the list of data of name column of all records
     *
     * @param column ProjectFundingRulesPageColumns column name
     * @return list of data of name column of all records
     */
    public List<String> getDataForNameColumn(ProjectFundingRulesPageColumns column) {
        clearAllFilters();
        return getColumnData(column.getValue());
    }

    /**
     * Click on Copy button
     *
     * @param fundRuleName Name of the fund rule to copy
     */
    public void clickOnCopy(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(ProjectFundingRulesPageColumns.RuleName.getValue(), fundRuleName);
        clickRibbonIcon(RibbonIcons.Copy);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate Copy function fields
     *
     * @return Softassert
     */
    public SoftAssert validateCopyFields() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!getGridData(GridType.RadGrid, GlobalFundListPageColumns.FundSourceName.getValue(), distributionGrid, distributionGridHeader).isEmpty());
        softAssert.assertTrue(elementHelper.isElementDisplayed(fundRuleNameTextBox));
        softAssert.assertTrue(elementHelper.isElementDisplayed(fundRuleNotesTextBox));
        softAssert.assertTrue(elementHelper.isCheckBoxSelected(isActiveCheckbox));
        softAssert.assertTrue(elementHelper.isElementDisplayed(addDistributionDetails));
        softAssert.assertTrue(elementHelper.isElementDisplayed(editDistributionDetails));
        softAssert.assertTrue(elementHelper.isElementDisplayed(deleteDistributionDetails));
        return softAssert;
    }

    /**
     * Verify the auto populated fields for copy
     *
     * @param globalFundName Name of the global fund name
     * @return Softassert
     */
    public SoftAssert verifyAutoPopulatedCopyField(String globalFundName) {
        waitHelper.waitForPageToLoad();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(Strings.isEmpty(elementHelper.doGetText(fundRuleNameTextBox)));
        var rows = getGridData(GridType.RadGrid, GlobalFundListPageColumns.FundSourceName.getValue(), distributionGrid, distributionGridHeader);
        softAssert.assertTrue(!rows.isEmpty());
        boolean isGlobalPresent = rows.get(0).equals(globalFundName);
        softAssert.assertTrue(isGlobalPresent);
        return softAssert;
    }

    /**
     * Check the status of the checkbox
     *
     * @return true if the active checkbox is selected
     */
    public boolean validateCheckboxStatus() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isCheckBoxSelected(isActiveCheckbox);
    }

    /**
     * Enter fund rule name in the fund rule field
     *
     * @param fundName Name to enter
     */
    public void enterFundRuleName(String fundName) {
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(fundRuleNameTextBox, fundName);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Enter notes to add
     *
     * @param note Note to enter
     */
    public void enterNotes(String note) {
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(fundRuleNotesTextBox, note);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate notes exists
     *
     * @param notes Notes to validate
     * @return True if the notes match
     */
    public boolean validateNotes(String notes) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        return elementHelper.doGetText(fundRuleTextNotes).equals(notes);
    }

    /**
     * Validate ruleId is auto generated
     *
     * @return True if expected text is present
     */
    public boolean validateAutoGeneratedID() {
        waitHelper.waitForPageToLoad();
        return elementHelper.doGetText(ruleRevisionID).equals(Constants.AUTOGENERATED_TAG);
    }

    /**
     * Add global fund to fund rules
     *
     * @param globalFundName Name of global fund to add
     */
    public void addGlobalFund(String globalFundName) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(addFundSourceName);
        getPage(Picker.class).singleSelectByText("Fund Source Name", globalFundName, pickerGrid);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectGlobalFund);
        elementHelper.doClick(editFundSourceName);
        waitHelper.waitForElementPresent(percentageTextBox);
        elementHelper.doSendKeysUsingAction(percentageTextBox, "100");
        elementHelper.doClick(saveDistributionDetails);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Delete global fund to fund rules
     **/
    public void deleteGlobalFund() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectGlobalFund);
        elementHelper.doClick(deleteFundSourceName);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Submit fund rule
     *
     * @param fundRule        Name of the fund rule
     * @param workFlowActions Work flow action to select
     * @param workFlowStatus  Work flow status to be expected
     * @return true if submitted successfully
     */
    public boolean changeFundRuleStatus(String fundRule, WorkFlowActions workFlowActions, WorkFlowStatus workFlowStatus) {
        return getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, fundRule, "Rule Name", workFlowActions, workFlowStatus);
    }

    /**
     * Approve a record without waiting for validation
     *
     * @param fundRuleName Name of the fund rule to approve
     * @return Error message in toast
     */
    public String getToastMessageOnApproval(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        getPage(WorkFlowHandler.class).workflowActionWithoutVerifying(fundRuleName, ProjectFundingRulesPageColumns.RuleName.getValue(), WorkFlowActions.Approve);
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
    }

    //AutoDesk

    /**
     * Validate Global Option Toolbar under Single record selection List
     *
     * @return true if the fields are available
     */
    public boolean validateSingleRecordList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.View);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.WorkflowAction);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        ribbonMenuIcons.add(RibbonIcons.MarkAsDefault);
        ribbonMenuIcons.add(RibbonIcons.Copy);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

}
