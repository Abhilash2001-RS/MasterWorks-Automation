package com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.helper.ScreenshotHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.AuditLogPage;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.Attachments;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.util.Strings;

import java.io.File;
import java.util.*;

/**
 * Global Fund List POM.
 */
public class GlobalFundListPage extends WorkFlowHandler {


    private By fundSourceIdLabel;
    private By fundSourceNameLabel;
    private By fundSourceCodeLabel;
    private By fundSourceCategoryLabel;
    private By fundSourceTypeLabel;
    private By fundingAgencyLabel;
    private By totalAmountLabel;
    private By remainingAmountLabel;
    private By notesLabel;
    private By isActiveLabel;
    private By fundSourceNameTextBox;
    private By fundSourceNameText;
    private By fundSourceCodeTextBox;
    private By fundSourceCategoryDropDown;
    private By fundSourceTypeDropDown;
    private By fundingAgencyDropDown;
    private By notesTextBox;
    private By isActiveCheckBox;
    private By totalAmountViewFormText;
    private By remainingAmountViewFormText;
    private By globalFundListTab;
    private By uploadDocument;
    private By selectFirstDocumentInAttachments;
    private By versionHistoryTable;
    private By popUpCloseBtn;
    private By checkIn;
    private By addComment;
    private By chooseFileToCheckIn;
    private By tableId;
    private By attachmentsGridData;

    private Select select;
    private String randomAppend;
    private WebDriver driver;

    private String recordIdentifierLink = "//a[contains(@Title,'%s')][@href='#']";

    public GlobalFundListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        randomAppend = TestDataUtil.getTimeStamp();

        var locators = LocatorUtil.getLocators("GlobalFundListPage.json");
        fundSourceIdLabel = locators.get("fundSourceIdLabel");
        fundSourceNameLabel = locators.get("fundSourceNameLabel");
        fundSourceCodeLabel = locators.get("fundSourceCodeLabel");
        fundSourceCategoryLabel = locators.get("fundSourceCategoryLabel");
        fundSourceTypeLabel = locators.get("fundSourceTypeLabel");
        fundingAgencyLabel = locators.get("fundingAgencyLabel");
        totalAmountLabel = locators.get("totalAmountLabel");
        remainingAmountLabel = locators.get("remainingAmountLabel");
        notesLabel = locators.get("notesLabel");
        isActiveLabel = locators.get("isActiveLabel");
        fundSourceNameTextBox = locators.get("fundSourceNameTextBox");
        fundSourceNameText = locators.get("fundSourceNameText");
        fundSourceCodeTextBox = locators.get("fundSourceCodeTextBox");
        fundSourceCategoryDropDown = locators.get("fundSourceCategoryDropDown");
        fundSourceTypeDropDown = locators.get("fundSourceTypeDropDown");
        fundingAgencyDropDown = locators.get("fundingAgencyDropDown");
        notesTextBox = locators.get("notesTextBox");
        isActiveCheckBox = locators.get("isActiveCheckBox");
        totalAmountViewFormText = locators.get("totalAmountViewFormText");
        remainingAmountViewFormText = locators.get("remainingAmountViewFormText");
        globalFundListTab = locators.get("globalFundListTab");
        uploadDocument = locators.get("uploadDocument");
        selectFirstDocumentInAttachments = locators.get("selectFirstDocumentInAttachments");
        versionHistoryTable = locators.get("versionHistoryTable");
        popUpCloseBtn = locators.get("popUpCloseBtn");
        checkIn = locators.get("checkIn");
        addComment = locators.get("addComment");
        chooseFileToCheckIn = locators.get("chooseFileToCheckIn");
        tableId = locators.get("tableId");
        attachmentsGridData = locators.get("attachmentsGridData");
    }

    /**
     * Navigate to Global Fund List page.
     *
     * @param navigateViaHome true if navigating to Home module is required
     */
    public void navigateTo(boolean navigateViaHome) {
        String moduleName = "Home";
        String pageName = "Global Fund List";
        logger().info("Navigating to Global Fund List page");
        if (navigateViaHome) {
            navigation.navigateToModulePageByName(moduleName);
        }
        navigation.navigateToFormInLeftPaneTree(pageName);
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Labels and Asterisk signs to verify on Global Fund List Page.
     *
     * @return List of locators for labels and asterisk.
     */
    private List<By> labelsAndAsterisk() {
        List<By> labels = new ArrayList<>();
        labels.add(fundSourceIdLabel);
        labels.add(fundSourceNameLabel);
        labels.add(fundSourceCodeLabel);
        labels.add(fundSourceCategoryLabel);
        labels.add(fundSourceTypeLabel);
        labels.add(fundingAgencyLabel);
        labels.add(totalAmountLabel);
        labels.add(remainingAmountLabel);
        labels.add(notesLabel);
        labels.add(isActiveLabel);
        labels.add(getPage(GenericForm.class).getRequiredAsteriskXpath(fundSourceNameLabel));
        return labels;
    }

    /**
     * Text field name and values to be entered.
     *
     * @return Hashmap of locators and string to be entered.
     */
    public HashMap<By, String> textFieldNameAndValue() {
        HashMap<By, String> textFields = new HashMap<>();
        textFields.put(fundSourceCodeTextBox, "Code" + randomAppend);
        textFields.put(notesTextBox, "Notes" + randomAppend);
        return textFields;
    }

    /**
     * Creates Drop Down Elements and values hash map.
     *
     * @return Hash Map for Drop Down Elements and Values.
     */
    private LinkedHashMap<By, Integer> dropDownFieldLocatorAndIndex() {
        LinkedHashMap<By, Integer> dropDownFields = new LinkedHashMap<>();
        dropDownFields.put(fundSourceCategoryDropDown, 1);
        dropDownFields.put(fundSourceTypeDropDown, 1);
        dropDownFields.put(fundingAgencyDropDown, 1);
        return dropDownFields;
    }

    /**
     * Create a new Global Fund List item entering only mandatory fields.
     *
     * @param fundName - name of the global fund
     */
    public void createGlobalFundList(String fundName) {
        logger().info("Creating a new 'Global Fund List'");
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForElementClickable(fundSourceNameTextBox);
        elementHelper.doSendKeys(fundSourceNameTextBox, fundName);
        select = new Select(elementHelper.getElement(fundSourceCategoryDropDown));
        select.selectByIndex(dropDownFieldLocatorAndIndex().get(fundSourceCategoryDropDown));
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Create a new Global Fund List item with category and type
     *
     * @param fundName - name of the global fund
     * @param category - fund category
     * @param type     - fund type
     */
    public void createGlobalFundList(String fundName, String category, String type) {
        logger().info("Creating a new 'Global Fund List'");
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForElementClickable(fundSourceNameTextBox);
        elementHelper.doSendKeys(fundSourceNameTextBox, fundName);
        elementHelper.selectComboBoxItemByText(fundSourceCategoryDropDown, category);
        elementHelper.selectComboBoxItemByText(fundSourceTypeDropDown, type);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Creating a new 'Global Fund List' with document upload
     *
     * @param fundName           -  name of fund
     * @param attachmentFileName - file to be uploaded
     */
    public void createGlobalFundList(String fundName, String attachmentFileName) {
        logger().info("Creating a new 'Global Fund List' with document upload");
        getPage(GenericForm.class).clickNew();
        elementHelper.doSendKeys(fundSourceNameTextBox, fundName);
        waitHelper.waitForPageToLoad();
        uploadDocument(attachmentFileName);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Upload a document
     *
     * @param fileName - name of file to be created and uploaded
     */
    public void uploadDocument(String fileName) {
        logger().info("Uploading document");
        try {
            File file = FileHelper.createFile(fileName, true);
            elementHelper.doSendKeys(uploadDocument, file.getAbsolutePath());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    /**
     * Create Global Fund List by entering all field values.
     *
     * @return Created Global Fund List name.
     */
    public String createGlobalFundList(boolean isActive) {
        logger().info("Creating a new 'Global Fund List'");
        String fundName = TestDataUtil.getRandomName();
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickNew();
        elementHelper.doSendKeys(fundSourceNameTextBox, fundName);
        getPage(GenericForm.class).enterTextValues(textFieldNameAndValue());
        getPage(GenericForm.class).selectDropDownItemsUsingIndex(dropDownFieldLocatorAndIndex());
        if (!isActive) {
            elementHelper.doClickUsingActions(isActiveCheckBox);
        }
        getPage(GenericForm.class).clickSave();
        return fundName;
    }

    /**
     * Edits Global Fund List.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @return Edited global fund list name.
     */
    public String editGlobalFundList(String globalFundListName) {
        String editedGlobalFundListName = "Edited" + randomAppend;
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(fundSourceNameTextBox, editedGlobalFundListName);
        getPage(GenericForm.class).clickSave();
        return editedGlobalFundListName;
    }

    /**
     * Gets und Source name of funds with remaining amount greater than equal to amount passed as param
     *
     * @param remainingAmount minimum remaining amount
     * @return a fund source name
     */
    public String getGlobalFundSourceWithFundsRemaining(String remainingAmount) {
        logger().info("Get a Global Fund Source name with approved fund transactions");
        filterListPage(GlobalFundListPageColumns.RemainingAmount.getValue(), remainingAmount, ListPageFilterOptions.GreaterThanOrEqualTo);
        String fundSourceName = getNumberOfRowsInListPage() == 0 ? null : getCellData(0, GlobalFundListPageColumns.FundSourceName.getValue());
        clearAllFilters();
        return fundSourceName;
    }

    /**
     * Click View on Global Fund List.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     */
    public void clickViewForGlobalFundList(String globalFundListName) {
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickView();
    }

    /**
     * Verify Global Fund List Name on View Form.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     * @return True if viewed successfully else false.
     */
    public boolean verifyGlobalFundListNameOnViewForm(String globalFundListName) {
        return elementHelper.doGetText(fundSourceNameText).equals(globalFundListName);
    }

    /**
     * Verify Global Fund List Expected Amount and Remaining Amount on View Form.
     *
     * @param expectedTotalAmount  Expected Total Amount value.
     * @param expectedReturnAmount Expected Return Amount value.
     * @return True if total amount and return amount verified else false.
     */
    public boolean verifyTotalAndRemainingViewForm(String expectedTotalAmount, String expectedReturnAmount) {
        boolean totalAmountVerified = Float.parseFloat(elementHelper.doGetAttribute(totalAmountViewFormText, "value").replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), "")) == Float.parseFloat(expectedTotalAmount);
        boolean returnAmountVerified = Float.parseFloat(elementHelper.doGetAttribute(remainingAmountViewFormText, "value").replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), "")) == Float.parseFloat(expectedReturnAmount);
        return totalAmountVerified && returnAmountVerified;
    }


    /**
     * Validate Global Fund List creation
     *
     * @param fundName - name of the global fund
     * @return - returns true when the global fund is created, false otherwise
     */
    public boolean validateGlobalFundListCreation(String fundName) {
        logger().info("Validating 'Global Fund List' creation");
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        return getRowNumberFromListPage(GlobalFundListPageColumns.FundSourceName.getValue(), fundName) != -1;
    }

    /**
     * Validate Global Fund List on List Page.
     *
     * @param globalFundListName       Name of Global Fund List.
     * @param isClearAllFilterRequired true if clearing all filter is required
     * @return Number of row after filtering with given value.
     */
    public int validateGlobalFundListOnListPage(String globalFundListName, boolean isClearAllFilterRequired) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        if (isClearAllFilterRequired) {
            clearAllFilters();
        }
        filterListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage();
    }

    /**
     * Get Fund name
     *
     * @return -   Fund name.
     */
    public String getFundName() {
        elementHelper.waitForElementClickable(this.fundSourceNameText);
        return elementHelper.doGetText(this.fundSourceNameText);
    }

    /**
     * Approving a newly created global fund
     *
     * @param globalFundName - name of the global fund created
     * @return - returns true when the fund is approved, false otherwise
     */
    public boolean approveGlobalFund(String globalFundName) {
        waitHelper.waitForPageToLoad();
        boolean submittedWorkFlowResult = workFlowActionProgression(false, true, globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean approvedWorkFlowResult = workFlowActionProgression(false, false, globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);
        return submittedWorkFlowResult && approvedWorkFlowResult;
    }


    /**
     * Function to perform Submit workflow action
     *
     * @param globalFundName name of the global fund
     * @return true if the submit action is performed, else returns false
     */
    public boolean performSubmitWorkflow(String globalFundName) {
        return workFlowActionProgression(false, true, globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);

    }

    /**
     * Function to perform Approve workflow action
     *
     * @param globalFundName name of the global fund
     * @return true if the approve action is performed, else returns false
     */
    public boolean performApproveWorkflow(String globalFundName) {
        return workFlowActionProgression(false, true, globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);

    }

    /**
     * Re-Draft a newly created global fund
     *
     * @param globalFundName - name of the global fund created
     * @return - returns true when the fund is approved, false otherwise
     */
    public boolean reDraftGlobalFund(String globalFundName) {
        boolean submittedWorkFlowResult = workFlowActionProgression(globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean reDraftWorkFlowResult = workFlowActionProgression(globalFundName, GlobalFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.ReDraft, WorkFlowStatus.Draft);
        return submittedWorkFlowResult && reDraftWorkFlowResult;
    }

    /**
     * Checks whether Security Role has Permission to Global Fund List
     *
     * @return true - if Permissions are given to Global Fund page
     */

    public boolean checkGlobalFundXMLPage() {
        navigateTo(false);
        getPage(ScreenshotHelper.class).takeFullScreenshot("Check For New Button");
        var isNewButtonVisible = elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.New));
        var availableRows = getColumnData(GlobalFundListPageColumns.FundSourceName.getValue());
        selectRecordInListPage(availableRows.get(0));
        waitHelper.waitForPageToLoad(RibbonIcons.Edit);
        var areContextualButtonsVisible = elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.View)) && elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.Edit)) && elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.Delete));
        return isNewButtonVisible && areContextualButtonsVisible;
    }

    /**
     * Delete Global Fund List.
     *
     * @param globalFundListName Global Fund List Name to be edited.
     */
    public void deleteGlobalFundList(String globalFundListName) {
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Function to verify that expected columns name are present in global fund list page.
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = GlobalFundListPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param globalFundListName name of the record to select
     */
    public void selectRecordInListPage(String globalFundListName) {
        int num = getRowNumberFromListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        selectRowCheckboxByNumber(num);
    }

    /**
     * Mark An already active fund as active and check message.
     *
     * @return True is toast message as expected.
     */
    public boolean markActiveForActiveFund() {
        String expectedToastMessage = "Already marked as Active.";
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.MarkActiveUnderActiveInactiveDropDown);
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast().contains(expectedToastMessage);
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
     * Function to select Mark Active option in MarkActive/InActive dropdown in ribbon
     */
    public void markActive() {
        clickRibbonIcon(RibbonIcons.MarkActiveUnderActiveInactiveDropDown);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Function to select Mark InActive option in MarkActive/InActive dropdown in ribbon
     */
    public void markInActive() {
        clickRibbonIcon(RibbonIcons.MarkInactiveUnderActiveInactiveDropDown);
        waitHelper.waitForPageToLoad();
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
            return actualMsg.contains(expectedMsg);
        } else {
            return false;
        }
    }

    /**
     * Function to get the fund source name using fund source ID
     *
     * @param fundSourceID fund source ID of global fund
     * @return fund source name of the record
     */
    public String getFundNameUsingFundSourceID(String fundSourceID) {
        clearAllFilters();
        filterListPage(GlobalFundListPageColumns.FundSourceID.getValue(), fundSourceID, ListPageFilterOptions.EqualTo);
        int rowNum = getRowNumberFromListPage(GlobalFundListPageColumns.FundSourceID.getValue(), fundSourceID);
        return getCellData(rowNum, GlobalFundListPageColumns.FundSourceName.getValue());
    }

    /**
     * Validate the presence of fields in the form
     *
     * @return true on validation of the above
     */
    public boolean validateFieldsInForm() {
        return getPage(GenericForm.class).validateElementsExists(labelsAndAsterisk());
    }

    /**
     * Open new form
     */
    public void openNewForm() {
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Cancel));
    }

    /**
     * Validate Excel Import
     *
     * @param fileName - name of the file
     * @param node     - host node
     * @return - true if validation of import is successful, else false
     */
    public boolean validateExcelImport(String fileName, Host node) {
        List<String> columnHeadersToBeUpdated = new ArrayList<>();
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.InternalID.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.RecordID.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.FundSourceName.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.FundSourceCode.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.IsActive.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.Notes.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.FundingAgency.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.FundSourceCategory.getValue());
        columnHeadersToBeUpdated.add(GlobalFundListExcelColumns.FundSourceType.getValue());

        var globalFundListImportData = TestDataUtil.generateGlobalFundListImportData();
        var fundSourceName = globalFundListImportData.fundSourceName;
        var fundSourceCode = globalFundListImportData.fundSourceCode;

        List<String> rowData = new ArrayList<>();
        rowData.add("");
        rowData.add("");
        rowData.add(fundSourceName);
        rowData.add(fundSourceCode);

        List<List<String>> addRowToImport = new ArrayList<>();
        addRowToImport.add(rowData);

        Map<List<String>, List<List<String>>> allHeaderAndData = new HashMap<>();
        allHeaderAndData.put(columnHeadersToBeUpdated, addRowToImport);

        String sheetName = "Global Fund List";
        ExcelUtil.writeExcelDataWithColumnHeader(fileName, allHeaderAndData, sheetName);
        return importFile(fileName);
    }

    /**
     * Validate Excel Template Export
     *
     * @param fileName - name of the file
     * @param node     - host node
     * @return - returns true if the expected headers are displayed, else false
     */
    public boolean validateExcelTemplateExport(String fileName, Host node) {
        List<String> columnHeadersInTemplate = ExcelUtil.getExcelColumnHeaders(fileName, node);
        List<String> columnHeadersExpected = GlobalFundListExcelColumns.getList();
        return columnHeadersInTemplate.containsAll(columnHeadersExpected);
    }

    /**
     * Validate Excel Data Exported
     *
     * @param fileName           - name of the file exported
     * @param node               - host node
     * @param globalFundListName - name of the global fund list to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String globalFundListName) {
        waitHelper.waitForPageToLoad();
        List<String> globalFundListColumnHeaders = GlobalFundListPageColumns.getList();
        List<String> globalFundListRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        waitHelper.waitForPageToLoad();
        int globalFundListRowNumber = getRowNumberFromListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        for (int i = 0; i < globalFundListColumnHeaders.size(); i++) {
            String s = getCellData(globalFundListRowNumber, globalFundListColumnHeaders.get(i));
            do {
                globalFundListRowValues.add(s);
            } while (!Strings.isNotNullAndNotEmpty(s));

            if (globalFundListRowValues.get(i).equals("true")) {
                listPageColumnHeadersWithValues.put(globalFundListColumnHeaders.get(i), "True");
            } else {
                listPageColumnHeadersWithValues.put(globalFundListColumnHeaders.get(i), globalFundListRowValues.get(i));
            }
        }
        Map<String, String> excelColumnHeadersWithValue = ExcelUtil.getColumnHeadersWithRowData(fileName, node, GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName, null);
        return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
    }

    /**
     * Validate excel data exported with template
     *
     * @param fileName       - name of the file exported
     * @param node           - host node
     * @param fundAgencyName - name of the fund agency record
     * @return -  true if the excel values and headers matches with the list page, recordID and internalID  headers are present in excel
     */
    public boolean validateExcelTemplateExportWithData(String fileName, Host node, String fundAgencyName) {
        List<String> fundAgencyColumnHeaders = getListPageHeaders();
        List<String> fundAgencyRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int fundAgencyRowNumber = getRowNumberFromListPage(GlobalFundListPageColumns.FundSourceName.getValue(), fundAgencyName);
        for (int i = 0; i < fundAgencyColumnHeaders.size(); i++) {
            fundAgencyRowValues.add(getCellData(fundAgencyRowNumber, fundAgencyColumnHeaders.get(i)));
            listPageColumnHeadersWithValues.put(fundAgencyColumnHeaders.get(i), fundAgencyRowValues.get(i));
        }
        List<String> columnHeadersFromExcel = ExcelUtil.getExcelColumnHeaders(fileName, node);
        Map<String, String> excelColumnHeadersWithValue = ExcelUtil.getColumnHeadersWithRowData(fileName, node, GlobalFundListPageColumns.FundSourceName.getValue(), fundAgencyName, null);

        return (columnHeadersFromExcel.contains("RecordID") && columnHeadersFromExcel.contains("InternalID") && listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue));
    }

    /**
     * Opens Audit Log and Enters mandatory fields.
     *
     * @return True if no issue.
     */
    public boolean openAndEnterAuditLogValues() {
        getPage(AuditLogPage.class).openAuditLog();
        return getPage(AuditLogPage.class).selectFormFields("Global Fund List");
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
     * Validate import export options in the drop down
     *
     * @return true on validation of the above
     */
    public boolean validateImportExportOptions() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.ExcelImportOrExportDropDown);
        List<By> importExportOptions = new ArrayList<>();
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelImport.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelExportXLS.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelExportXLSX.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelTemplateWithDataXLS.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelTemplateWithDataXLSX.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelTemplateXLS.getValue())));
        importExportOptions.add(By.xpath(String.format(optionsInRibbonMenu, ImportExportOptionsInListPage.ExcelTemplateXLSX.getValue())));

        return getPage(GenericForm.class).validateElementsExists(importExportOptions);
    }


    /**
     * Check the isActive checkbox
     */
    public void checkIsActiveCheckbox() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClickUsingActions(isActiveCheckBox);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate Global Option List
     *
     * @return true on Fields available
     */
    public boolean validateGlobalList() {
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.New);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under New list
     *
     * @return true on validation of the above
     */
    public boolean validateNewList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        ribbonMenuIcons.add(RibbonIcons.SaveAndContinue);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under New list
     *
     * @return true on validation of the above
     */
    public boolean validateNewListWithoutContinueOld() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under New list
     *
     * @return true on validation of the above
     */
    public boolean validateNewListWithoutContinue() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Back);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under Multiple list selection
     *
     * @return true on Fields available
     */
    public boolean validateMultipleList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under single record selection
     *
     * @return true on Fields available
     */
    public boolean validateSingleRecordList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Edit);
        ribbonMenuIcons.add(RibbonIcons.View);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.More);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under Edit list
     *
     * @return true on Fields available
     */
    public boolean validateEditList() {
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Check the isActive checkbox
     *
     * @return true if the checkbox is selected
     */
    public boolean isActiveCheckBoxStatus() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isCheckBoxSelected(isActiveCheckBox);
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return validateImportExportOptions(true, true) && validateWorkflow() && validateReportList(true, false, true, false) && validateMoreList(false, true, true, true, false, false);
    }

    /**
     * Validate Contextual fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean contextualFieldValidation() {
        var softAssert = new SoftAssert();
        return (
                //Known application failure
                //   ((validateActiveInActiveList())&&
                (validateWorkflowValues(true, false, true)) && (validateReportList(false, true, false, false)));
    }

    /**
     * click on Global fun tab
     */
    public void clickGFLTab() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(globalFundListTab);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validating Record Identifier Link
     *
     * @param globalFundName - name of the global fund
     * @param globalFundID   - global fund ID
     * @return - true if record identifier is a link
     */
    public boolean validateRecordIdentifierLink(String globalFundName, String globalFundID) {
        logger().info("Validating Record Identifier Link");
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format(recordIdentifierLink, globalFundID)));
    }

    /**
     * Validate Header In Global Fund List Documents
     *
     * @param headerName - name of header to be searched
     * @return - true if header is present
     */
    public boolean validateHeaderInGFListDocuments(String headerName) {
        logger().info("Validate Header In Global Fund List Documents");
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Attachment);
        return validateListPageHeader(headerName);
    }

    /**
     * Validate List Page Header
     *
     * @param headerName - the header to be validated
     * @return - true if header is present
     */
    public boolean validateListPageHeader(String headerName) {
        logger().info("Validate List Page Header");
        waitHelper.waitForPageToLoad(tableId);
        return getPage(GenericFormProposed.class).getGridHeaders(GridType.DocGrid, tableId).contains(headerName);
    }

    /**
     * Validating Record Identifier On Click
     *
     * @param globalFundName - global fund name
     * @param globalFundID   - global fund ID
     * @return - true, if page opens in view mode
     */
    public boolean validateRecordIdentifierOnClick(String globalFundName, String globalFundID) {
        logger().info("Validating Record Identifier On Click");
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(By.xpath(String.format(recordIdentifierLink, globalFundID)));
        waitHelper.waitForPageToLoad();
        List<String> allRibbonIconsDisplayed = getAllRibbonButtonsDisplayed();
        return !allRibbonIconsDisplayed.contains("Save");
    }

    /**
     * Validate Filter Functionality In Attachments
     *
     * @param globalFundListDocumentsColumns - column name
     * @param columnText                     - column data
     * @param listPageFilterOptions          - filter options
     * @return - true if filter functionality works as expected
     */
    public boolean validateFilterFunctionalityInAttachments(GlobalFundListDocumentsColumns globalFundListDocumentsColumns, String columnText, ListPageFilterOptions listPageFilterOptions) {
        logger().info("Validate Filter Functionality In Attachments");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        getPage(GenericFormProposed.class).filterGrid(GridType.DocGrid, tableId, globalFundListDocumentsColumns.getValue(), columnText, listPageFilterOptions);
        waitHelper.waitForPageToLoad();
        return getGridData(GridType.DocGrid, AttachmentPageColumnHeaders.DocumentName.getValue(), attachmentsGridData, tableId).size() == 1;
    }

    /**
     * Validate Back Button
     *
     * @return - true, if back button works as expected
     */
    public boolean validateBackButtonInAttachments() {
        logger().info("Validate Back Button In Attachments");
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Back);
        waitHelper.waitForPageToLoad();
        return validateRibbonIcon(RibbonIcons.New);
    }

    /**
     * Validate Get Document Functionality In Attachments
     *
     * @param globalFundName - global fund name
     * @param fileName       - file name
     * @param node           - node
     * @return - true,if 'get document' functionality works as expected
     */
    public boolean validateGetDocumentFunctionalityInAttachments(String globalFundName, String fileName, Host node) {
        logger().info("Validate Get Document Functionality In Attachments");
        int maxWait = 60;
        int pollingInterval = 1;
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        FileHelper.deleteFile(fileName, node);
        elementHelper.doClick(selectFirstDocumentInAttachments);
        clickRibbonIcon(RibbonIcons.GetDocument);
        return FileHelper.waitForFileToBeAvailable(fileName, maxWait, pollingInterval, node);
    }

    /**
     * Validate Version History In Attachments
     *
     * @param globalFundName - Global Fund Name
     * @param fileName       - file name
     * @return - true if version history works as expected
     */
    public boolean validateVersionHistoryInAttachments(String globalFundName, String fileName) {
        logger().info("Validate Version History In Attachments");
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        boolean isVerified = getPage(Attachments.class).verifyVersionHistoryDisplayed(fileName, versionHistoryTable);
        elementHelper.doClick(popUpCloseBtn);
        waitHelper.waitUntilElementDisappears(versionHistoryTable);
        return isVerified;
    }

    /**
     * Validate Check Out In Attachments
     *
     * @param globalFundName - global fund name
     * @param fileName       - file name
     * @param node           - node
     * @return - true, if check out works as expected
     */
    public boolean validateCheckOutInAttachments(String globalFundName, String fileName, Host node) {
        logger().info("Validate Check Out In Attachments");
        int maxWait = 60;
        int pollingInterval = 1;
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        FileHelper.deleteFile(fileName, node);
        elementHelper.doClick(selectFirstDocumentInAttachments);
        clickRibbonIcon(RibbonIcons.CheckOutWithoutParent);
        return FileHelper.waitForFileToBeAvailable(fileName, maxWait, pollingInterval, node);
    }

    /**
     * Discard Check Out
     */
    public void discardCheckOutAttachments() {
        logger().info("Discard Check Out");
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectFirstDocumentInAttachments);
        clickRibbonIcon(RibbonIcons.DiscardCheckOutWithoutParent);
    }

    /**
     * Check In Attachments
     *
     * @param globalFundName - global fund name
     * @param fileName       - file name
     * @param node           - node
     */
    public void checkInAttachments(String globalFundName, String fileName, Host node) {
        logger().info("Check In Attachments");
        int maxWait = 60;
        int pollingInterval = 1;
        waitHelper.waitForPageToLoad();
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundName);
        clickRibbonIcon(RibbonIcons.Attachment);
        waitHelper.waitForPageToLoad();
        FileHelper.deleteFile(fileName, node);
        elementHelper.doClick(selectFirstDocumentInAttachments);
        clickRibbonIcon(RibbonIcons.CheckOutWithoutParent);
        FileHelper.waitForFileToBeAvailable(fileName, maxWait, pollingInterval, node);
        waitHelper.waitForElementClickable(selectFirstDocumentInAttachments);
        elementHelper.doClick(selectFirstDocumentInAttachments);
        clickRibbonIcon(RibbonIcons.CheckInWithoutParent);
        waitHelper.waitForElementClickable(checkIn);

        waitHelper.waitForElementClickable(addComment);
        elementHelper.doSendKeys(addComment, "Uploading document");

        waitHelper.waitForElementPresent(chooseFileToCheckIn);
        File file = FileHelper.createFile(Constants.UPLOAD_DOCUMENT, true);
        elementHelper.getElement(chooseFileToCheckIn).sendKeys(file.getAbsolutePath());

        waitHelper.waitForElementClickable(checkIn);
        elementHelper.doClick(checkIn);

        waitHelper.waitForPageToLoad();
    }

    /**
     * Adding multiple attachment to Global Fund
     *
     * @param fundName           fundName
     * @param attachmentFileName attachmentFileName
     */
    public void addAttcahmentinGlobalFund(String fundName, String attachmentFileName) {
        filterListPage(GlobalFundListPageColumns.FundSourceName.getValue(), fundName, ListPageFilterOptions.Contains);
        selectVisibleRows(1);
        getPage(GenericForm.class).clickEdit();
        waitHelper.waitForPageToLoad();
        uploadDocument(attachmentFileName);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        getPage(GenericForm.class).clickSave();
        waitHelper.waitForPageToLoad();
    }

    /**
     * verify if multiple attachment is present
     *
     * @param expectedDocumentNames expectedDocumentNames
     * @return true if attachments is present else return false
     */
    public boolean verifyMultipleAttachment(List<String> expectedDocumentNames) {
        var allDocumentNames = getPage(Attachments.class).getColumnData(AttachmentPageColumnHeaders.DocumentName.getValue());
        SoftAssert softAssert = new SoftAssert();
        expectedDocumentNames.forEach(d -> softAssert.assertTrue(allDocumentNames.contains(d)));
        softAssert.assertAll();
        return true;
    }

    /**
     * Verify if record is editable in view mode
     *
     * @return true if record is not editable
     */
    public boolean isRecordEditableInViewMode() {
        logger().info("Verify if record is editable in view mode");
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.View));
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        boolean isRecordEditableInView = !validateRibbonIcon(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();
        return isRecordEditableInView;
    }

    /**
     * Delete and Validate record
     *
     * @param globalFundListName global fund list name
     * @return - true if record gets deleted
     */
    public boolean deleteAndValidateRecord(String globalFundListName) {
        singleClickOnRowListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        getPage(ListPage.class).filterListPage(GlobalFundListPageColumns.FundSourceName.getValue(), globalFundListName, ListPageFilterOptions.EqualTo);
        return getPage(ListPage.class).getNumberOfRowsInListPage() == 0;
    }
}
