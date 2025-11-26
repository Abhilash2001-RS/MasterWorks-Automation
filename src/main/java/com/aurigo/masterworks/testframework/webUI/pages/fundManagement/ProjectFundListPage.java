package com.aurigo.masterworks.testframework.webUI.pages.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.AuditLogPage;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProjectFundListPage extends WorkFlowHandler {

    private By projectFundIdLabel;
    private By fundSourceNameLabel;
    private By fundSourceCodeLabel;
    private By fundSourceCategoryLabel;
    private By fundSourceTypeLabel;
    private By availableAmountLabel;
    private By remainingAmountLabel;
    private By notesLabel;
    private By fundSourceNameDropDown;
    private By notesTextBox;
    private By notesText;
    private By reportPagePrintButton;

    public ProjectFundListPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ProjectFundListPage.json");
        projectFundIdLabel = locators.get("projectFundIdLabel");
        fundSourceNameLabel = locators.get("fundSourceNameLabel");
        fundSourceCodeLabel = locators.get("fundSourceCodeLabel");
        fundSourceCategoryLabel = locators.get("fundSourceCategoryLabel");
        fundSourceTypeLabel = locators.get("fundSourceTypeLabel");
        availableAmountLabel = locators.get("availableAmountLabel");
        remainingAmountLabel = locators.get("remainingAmountLabel");
        notesLabel = locators.get("notesLabel");
        fundSourceNameDropDown = locators.get("fundSourceNameDropDown");
        notesTextBox = locators.get("notesTextBox");
        notesText = locators.get("notesText");
        reportPagePrintButton = locators.get("reportPagePrintButton");
    }

    /**
     * Navigate to Project Fund List page.
     */
    public void navigateTo() {
        logger().info("Navigating to Project Fund List page");
        navigation.navigateToFormInLeftPaneTree("Project Fund List");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
    }

    /**
     * Labels and Asterisk signs to verify on Project Fund List Page.
     *
     * @return List of locators for labels and asterisk.
     */
    private List<By> labelsAndAsterisk() {
        List<By> labels = new ArrayList<>();
        labels.add(fundSourceCategoryLabel);
        labels.add(fundSourceCodeLabel);
        labels.add(fundSourceNameLabel);
        labels.add(fundSourceTypeLabel);
        labels.add(projectFundIdLabel);
        labels.add(availableAmountLabel);
        labels.add(remainingAmountLabel);
        labels.add(notesLabel);
        labels.add(getPage(GenericForm.class).getRequiredAsteriskXpath(fundSourceNameLabel));
        return labels;
    }

    /**
     * Create a new Project Fund List item.
     *
     * @param fundSourceName -   - Global Fund source name
     */
    public void createProjectFundList(String fundSourceName) {
        logger().info("Creating a new 'Project Fund List'");
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForElementClickable(fundSourceNameDropDown);
        getPage(GenericForm.class).validateElementsExists(labelsAndAsterisk());
        waitHelper.waitForElementClickable(fundSourceNameDropDown);
        elementHelper.selectComboBoxItemByText(fundSourceNameDropDown, fundSourceName);
        clickRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Create a new Project Fund List item.
     *
     * @param index number to get selected from dropdown
     */
    public void createProjectFundList(int index) {
        logger().info("Creating a new 'Project Fund List'");
        clickRibbonIcon(RibbonIcons.New);
        getPage(GenericForm.class).validateElementsExists(labelsAndAsterisk());
        elementHelper.selectComboBoxItemByIndex(fundSourceNameDropDown, index);
        clickRibbonIcon(RibbonIcons.Save);
    }


    /**
     * Validating creation of Project Fund List
     *
     * @param fundSourceName - Global Fund source name
     * @return -  true when Project Fund List is created, false otherwise
     */
    public boolean validateProjectFundListCreation(String fundSourceName) {
        return getRowNumberFromListPage("Fund Source Name", fundSourceName) != -1;
    }

    /**
     * Method to validate Changes in Project List page amount
     *
     * @param fundSourceName             Name of the fund rule considered
     * @param fundDeclared               fund Declared in Project Transaction
     * @param fundAllocatedInExpenseForm fund Allocated In Expense Form
     * @param purchaseOrderAllocation    fund declared while creating purchase order with fund rule
     * @param directExpensesAmount       The direct expense amount added under a purchase order
     * @return Soft-assert soft assert
     */
    public SoftAssert validateProjectFundListChanges(String fundSourceName, int fundDeclared, int fundAllocatedInExpenseForm, int purchaseOrderAllocation, int... directExpensesAmount) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
        var softAssert = new SoftAssert();
        int rowNum = getRowNumberFromListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
        String[] firstPart = getCellData(rowNum, ProjectFundListPageColumn.EncumberedAmountIn$.getValue()).split("\\.");
        softAssert.assertEquals(getIntegerCellValue(firstPart), fundDeclared, "Allocated Fund validation");
        int totalAllocation = fundAllocatedInExpenseForm + purchaseOrderAllocation;
        firstPart = getCellData(rowNum, ProjectFundListPageColumn.AllocatedAmountIn$.getValue()).split("\\.");
        softAssert.assertEquals(getIntegerCellValue(firstPart), totalAllocation, "Allocated Fund validation");
        if (directExpensesAmount.length > 0) {
            firstPart = getCellData(rowNum, ProjectFundListPageColumn.ConsumedAmountIn$.getValue()).split("\\.");
            softAssert.assertEquals(getIntegerCellValue(firstPart), fundAllocatedInExpenseForm + directExpensesAmount[0],
                    "Consumed Fund validation");
            firstPart = getCellData(rowNum, ProjectFundListPageColumn.RemainingAmountIn$.getValue()).split("\\.");
            softAssert.assertEquals(getIntegerCellValue(firstPart), purchaseOrderAllocation - directExpensesAmount[0],
                    "Remaining Fund validation");
        } else {
            firstPart = getCellData(rowNum, ProjectFundListPageColumn.ConsumedAmountIn$.getValue()).split("\\.");
            softAssert.assertEquals(getIntegerCellValue(firstPart), fundAllocatedInExpenseForm,
                    "Consumed Fund validation");
            firstPart = getCellData(rowNum, ProjectFundListPageColumn.RemainingAmountIn$.getValue()).split("\\.");
            softAssert.assertEquals(getIntegerCellValue(firstPart), purchaseOrderAllocation,
                    "Remaining Fund validation");
        }
        return softAssert;
    }

    /**
     * Approving a newly created project fund
     *
     * @param fundSourceName - Global Fund source name
     * @return - returns true when the fund is approved, false otherwise
     */
    public boolean approveProjectFund(String fundSourceName) {
        //getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, fundSourceName, "Fund Source Name", WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        return getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, fundSourceName, "Fund Source Name", WorkFlowActions.Approve, WorkFlowStatus.Approved);
    }

    /**
     * Edits Project Fund List.
     *
     * @param fundSourceName Project Fund List Name selector to be edited.
     * @return Edited global fund list title.
     */
    public String editProjectFund(String fundSourceName) {
        String editedProjectFundListName = "Edited " + fundSourceName;
        singleClickOnRowListPage(ProjectFundListPageColumns.FundSourceName.getValue(), fundSourceName);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad(notesTextBox);
        elementHelper.doSendKeys(notesTextBox, editedProjectFundListName);
        clickRibbonIcon(RibbonIcons.Save);
        return editedProjectFundListName;
    }

    /**
     * View Project Fund List.
     *
     * @param fundSourceName Project Fund List Name selector to be viewed.
     * @return Note text
     */
    public String viewProjectFundList(String fundSourceName) {
        singleClickOnRowListPage(ProjectFundListPageColumns.FundSourceName.getValue(), fundSourceName);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad(RibbonIcons.Edit);
        String projectFundListNotesVerified = elementHelper.doGetText(notesText);
        clickRibbonIcon(RibbonIcons.Cancel);
        return projectFundListNotesVerified;
    }

    /**
     * Delete Project Fund List.
     *
     * @param fundSourceName Project Fund List Name selector to be deleted.
     */
    public void deleteProjectFund(String fundSourceName) {
        singleClickOnRowListPage(ProjectFundListPageColumns.FundSourceName.getValue(), fundSourceName);
        clickRibbonIcon(RibbonIcons.Delete);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate Project Fund on List Page.
     *
     * @param fundSourceName Name of Global Fund List.
     * @return Number of row after filtering with given value.
     */
    public int validateProjectFundOnListPage(String fundSourceName) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        filterListPage(ProjectFundListPageColumns.FundSourceName.getValue(), fundSourceName, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage();
    }

    /**
     * Re-Draft fund transaction
     *
     * @param globalFundName Name of the global Fund
     * @return - returns true when the fund transaction is Re-Draft, false otherwise
     */
    public boolean reDraftFundTransaction(String globalFundName) {
        boolean submitWorkFlowResult = workFlowActionProgression(globalFundName, ProjectFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.Submit, WorkFlowStatus.Submitted);
        boolean reDraftWorkFlowResult = workFlowActionProgression(globalFundName, ProjectFundListPageColumns.FundSourceName.getValue(), WorkFlowActions.ReDraft, WorkFlowStatus.Draft);
        return submitWorkFlowResult && reDraftWorkFlowResult;
    }

    /**
     * Method to get Encumbered Amount from the form page
     *
     * @return Amount in string form
     */
    public String getEncumberedAmount() {
        waitHelper.waitForPageToLoad();
        return (getCellData(0, ProjectFundListPageColumn.EncumberedAmountIn$.getValue()));
    }

    /**
     * Function to get the encumbered amount of the fund list record
     *
     * @param fundSourceName to identify the record
     * @return value of Encumbered Amount in String format
     */
    public String getEncumberedAmount(String fundSourceName) {
        waitHelper.waitForPageToLoad();
        int rowNumber = getRowNumberFromListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
        return (getCellData(rowNumber, ProjectFundListPageColumn.EncumberedAmountIn$.getValue()));
    }

    /**
     * Function to get the UnAllocated amount of the fund list record
     *
     * @param fundSourceName to identify the record
     * @return value of UnAllocated Amount in String format
     */
    public String getUnAllocatedAmount(String fundSourceName) {
        waitHelper.waitForPageToLoad();
        int rowNumber = getRowNumberFromListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
        return (getCellData(rowNumber, ProjectFundListPageColumn.UnAllocatedAmountIn$.getValue()));
    }

    /**
     * Function to validate that Encumbered Amount and UnAllocated Amount are correct for the fund list record
     *
     * @param fundSourceName            to identify the record
     * @param expectedEncumberedAmount  expected Encumbered Amount
     * @param expectedUnAllocatedAmount expected UnAllocated Amount
     * @return true if the validation is successful
     */
    public boolean validateEncumberedAndAllocatedAmount(String fundSourceName, float expectedEncumberedAmount, float expectedUnAllocatedAmount) {
        int rowNumber = getRowNumberFromListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
        float actualEncumberedAmount = Float.parseFloat(getCellData(rowNumber, ProjectFundListPageColumn.EncumberedAmountIn$.getValue()).replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""));
        float actualUnAllocatedAmount = Float.parseFloat(getCellData(rowNumber, ProjectFundListPageColumn.UnAllocatedAmountIn$.getValue()).replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""));
        return actualEncumberedAmount == expectedEncumberedAmount && actualUnAllocatedAmount == expectedUnAllocatedAmount;
    }

    /**
     * Function to verify that expected columns name are present in project fund list page
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = ProjectFundListPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param fundSourceName name of the record to select
     */
    public void selectRecordInListPage(String fundSourceName) {
        singleClickOnRowListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
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
     * @param fileName       - name of the file exported
     * @param node           - host node
     * @param fundSourceName - name of the fund Source Name to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String fundSourceName) {
        List<String> projectFundListColumnHeaders = getListPageHeaders();
        List<String> projectFundListRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int projectFundListRowNumber = getRowNumberFromListPage(ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName);
        for (int i = 0; i < projectFundListColumnHeaders.size(); i++) {
            String s = getCellData(projectFundListRowNumber, projectFundListColumnHeaders.get(i));
            projectFundListRowValues.add(s);
            listPageColumnHeadersWithValues.put(projectFundListColumnHeaders.get(i), projectFundListRowValues.get(i));
        }
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node, ProjectFundListPageColumn.FundSourceName.getValue(), fundSourceName, null);
        return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
    }

    /**
     * Opens Audit Log and Enters mandatory fields.
     *
     * @return True if no issue.
     */
    public boolean openAndEnterAuditLogValues() {
        getPage(AuditLogPage.class).openAuditLog();
        return getPage(AuditLogPage.class).selectFormFields("Project Fund List");
    }

    /**
     * View Audit Log and verify data.
     *
     * @return True if data verified, else false.
     */
    public boolean viewAuditLog() {
        getPage(AuditLogPage.class).clickView();
        boolean viewVerified = getPage(AuditLogPage.class).verifyGridColumns(AuditLogGridColumns.getList());
        clickRibbonIcon(RibbonIcons.Back);
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
        return viewVerified;
    }

    /**
     * Validate Reports Fields
     *
     * @return true if validated Successfully
     */
    public boolean validateReportFields() {
        List<String> fields = new ArrayList<>();
        fields.add("Encumbered Amount($):");
        fields.add("Allocated Amount($):");
        fields.add("UnAllocated Amount($):");
        fields.add("Consumed Amount($):");
        fields.add("Remaining Amount($):");
        List<By> fieldsValidation = new ArrayList<>();
        for (String field : fields) {
            fieldsValidation.add(By.xpath(String.format("//span[text()='%s']", field)));
        }
        return getPage(GenericForm.class).validateElementsExists(fieldsValidation);
    }

    /**
     * Get Report Details
     *
     * @return Report Details
     */
    public Map<String, String> reportsDetails() {
        Map<String, String> reports = new HashMap<>();
        List<String> fields = new ArrayList<>();
        fields.add("Encumbered Amount($):");
        fields.add("Allocated Amount($):");
        fields.add("UnAllocated Amount($):");
        fields.add("Consumed Amount($):");
        fields.add("Remaining Amount($):");
        for (String field : fields) {
            reports.put(field, elementHelper.doGetText(By.xpath(String.format("//span[text()='%s']/../../../../..//span[contains(text(),'.')]", field))));
        }
        return reports;
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
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return validateImportExportOptions(true, false) &&
                validateMoreList(false, true, true, true, false, false);
    }

    /**
     * This method used convert String cell value to integer
     *
     * @param values array of string for conversion
     * @return converted value in integer format
     */
    private int getIntegerCellValue(String[] values) {
        if (values.length > 0) {
            return (Integer.parseInt(values[0].replace(",", "")));
        }
        return 0;
    }

    /**
     * Method to get all the comboBox options
     *
     * @return List of all options
     */
    public List<String> getComboOptions() {
        waitHelper.waitForPageToLoad();
        return elementHelper.getSelectOptions(fundSourceNameDropDown);
    }

    /**
     * Method to click on report button in MasterContract and select the option
     *
     * @param option Name of option to select in
     */
    public void clickReport(String option) {
        clickRibbonIcon(RibbonIcons.Reports);
        By optionXpath;
        optionXpath = By.xpath("//span[(@class='rrbText') and (contains(text(),'" + option + "'))]");
        elementHelper.doClick(optionXpath);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate presence of print button
     *
     * @return True if the button is present
     */
    public boolean validatePrintButtonIsPresent() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementEnabled(reportPagePrintButton);
    }
}