package com.aurigo.masterworks.testframework.webUI.pages.enterpriseWideFeatures.fundManagement;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PlanningFundPage extends WorkFlowHandler {


    private By titleLabel;
    private By programYearLabel;
    private By descriptionLabel;
    private By planningFundTitleTextBox;
    private By programYearDropDown;
    private By descriptionTextBox;
    private By planningNameText;
    private By distributionDetailsTableRow;
    private By distributionTableGridCells;
    private By forecastTab;
    private By addButton;
    private By deleteButton;
    private By pickerGrid;
    private By elementToClick;
    private By enterTextElement;

    private final By defaultFiscalYearSelect;

    public String nonSeptFiscalYear = "Not September";

    public PlanningFundPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("PlanningFundPage.json");
        titleLabel = locators.get("titleLabel");
        programYearLabel = locators.get("programYearLabel");
        descriptionLabel = locators.get("descriptionLabel");
        planningFundTitleTextBox = locators.get("planningFundTitleTextBox");
        programYearDropDown = locators.get("programYearDropDown");
        descriptionTextBox = locators.get("descriptionTextBox");
        planningNameText = locators.get("planningNameText");
        distributionDetailsTableRow = locators.get("distributionDetailsTableRow");
        distributionTableGridCells = locators.get("distributionTableGridCells");
        forecastTab = locators.get("forecastTab");
        addButton = locators.get("addButton");
        deleteButton = locators.get("deleteButton");
        pickerGrid = locators.get("pickerGrid");
        defaultFiscalYearSelect = locators.get("defaultFiscalYearSelectInPlanningFund");
        elementToClick = locators.get("elementToClick");
        enterTextElement = locators.get("enterTextElement");
    }

    /**
     * Navigate to Planning Fund page.
     */
    public void navigateTo() {
        logger().info("Navigating to Planning Fund");
        navigation.navigateToModulePageByName("Planning");
        navigation.navigateToFormInLeftPaneTree("Planning Fund");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.New);
    }

    /**
     * Creating a new Planning Fund
     *
     * @param planningFundTitle - add title of the planning fund
     * @param programYear       - the program year
     * @param globalFundName    - the newly created global fund
     */
    public void createPlanningFund(String planningFundTitle, String programYear, String globalFundName) {
        logger().info("Creating a new Planning Fund: " + planningFundTitle);
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickNew();
        waitHelper.waitForElementClickable(planningFundTitleTextBox);
        elementHelper.doSendKeys(planningFundTitleTextBox, planningFundTitle);
        elementHelper.selectComboBoxItemByText(programYearDropDown, programYear);
        getPage(GenericFormProposed.class).selectFiscalYear(defaultFiscalYearSelect);
        pickFund(globalFundName);
    }

    /**
     * Picks Fund from picker.
     *
     * @param globalFundName Global Fund name to pick.
     */
    public void pickFund(String globalFundName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        elementHelper.scrollToView(addButton);
        elementHelper.doClick(addButton);
        waitHelper.waitForPageToLoad(pickerGrid);
        getPage(Picker.class).singleSelectByText("Fund Source Name", globalFundName, pickerGrid);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click Save wrapper.
     */
    public void clickSaveButton() {
        getPage(GenericForm.class).clickSave();
    }

    /**
     * Click Back button from Forecast tab.
     */
    public void clickBackButton() {
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Edits Global Fund List.
     *
     * @param planningFundTitle Planning Fund List Name to be edited.
     * @return Edited global fund list title.
     */
    public String editPlanningFund(String planningFundTitle) {
        String editedPlanningFundListName = "Edited" + planningFundTitle;
        singleClickOnRowListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle);
        getPage(GenericForm.class).clickEdit();
        elementHelper.doSendKeys(planningFundTitleTextBox, editedPlanningFundListName);
        getPage(GenericForm.class).clickSave();
        return editedPlanningFundListName;
    }

    /**
     * View Global Fund List.
     *
     * @param planningFundTitle Planning Fund List Name to be viewed.
     * @return True if viewed successfully else false.
     */
    public boolean viewPlanningFundList(String planningFundTitle) {
        singleClickOnRowListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle);
        getPage(GenericForm.class).clickView();
        boolean globalFundListNameVerified = elementHelper.doGetText(planningNameText).equals(planningFundTitle);
        getPage(GenericForm.class).clickCancel();
        return globalFundListNameVerified;
    }

    /**
     * Delete Fund Transaction.
     *
     * @param planningFundTitle Planning Fund List Name to be deleted.
     */
    public void deletePlanningFund(String planningFundTitle) {
        singleClickOnRowListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle);
        getPage(GenericForm.class).clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validating creation of planning fund
     *
     * @param planningFundTitle - title of the newly created planning fund
     * @return - returns true if the Planning Fund is created, false otherwise
     */
    public boolean validatingPlanningFundCreation(String planningFundTitle) {
        logger().info("Validating creation of 'Planning Fund'");
        return getRowNumberFromListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle) != -1;
    }

    /**
     * Validate Planning Fund on List Page.     
     *
     * @param planningFundTitle Name of Global Fund List.
     * @return Number of row after filtering with given value.
     */
    public int validatePlanningFundOnListPage(String planningFundTitle) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        filterListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage();
    }


    /**
     * Approving a newly created Planning Fund.
     *
     * @param planningFundTitle - title of the newly created planning fund
     * @return - returns true when the Planning Fund is approved, false otherwise
     */
    public boolean approvePlanningFund(String planningFundTitle) {
        return getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, planningFundTitle, PlanningFundListPageColumns.Title.getValue(), WorkFlowActions.Approve, WorkFlowStatus.Approved);
    }

    /**
     * Fill Distribution details grid cells values.
     *
     * @param valuesToEnter List of values to enter.
     */
    public void fillDistributionGridCellsForSingleRow(List<String> valuesToEnter) {
        var distributionGridCells = elementHelper.getElements(distributionTableGridCells);
        for (int i = 1; i < distributionGridCells.size(); i++) {
            elementHelper.doDoubleClick(distributionGridCells.get(i));
            elementHelper.doSendKeysUsingAction(valuesToEnter.get(i - 1));
        }
    }

    /**
     * Goes to Forecast tab.
     */
    public void goToForecastTab() {
        waitHelper.waitForElementClickable(forecastTab);
        elementHelper.doClick(forecastTab);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Verify Forecast values in the first row of list.
     *
     * @param valuesAndColumnsToVerify Map of column names and values to verify.
     * @return True if all values verified.
     */
    public boolean verifyItemForecastDataForFirstRow(HashMap<String, Integer> valuesAndColumnsToVerify) {
        AtomicReference<AtomicBoolean> allVerified = new AtomicReference<>(new AtomicBoolean(true));
        valuesAndColumnsToVerify.forEach((x, y) -> {
            if (!(getPage(ItemListPage.class).getItemData(0, x).replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), "").contains(Integer.toString(y)))) {
                allVerified.set(new AtomicBoolean(false));
            }
        });
        return allVerified.get().get();
    }

    /**
     * Delete Fund from Distribution Details row.
     *
     * @param fundName Name of the fund
     * @return True if row deleted.
     */
    public boolean deleteDistributionDetailsRow(String fundName) {
        String toastExpectedMessage = "Before saving please add funds.";
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(elementHelper.getElement(distributionDetailsTableRow).findElement(By.xpath(String.format("//td[text()='%s']", fundName))));
        elementHelper.doClick(deleteButton);
        getPage(AlertHandler.class).acceptAlert(true);
        clickRibbonIcon(RibbonIcons.Save);
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
        waitHelper.waitUntilElementDisappears(distributionDetailsTableRow);
        elementHelper.doClick(forecastTab);
        getPage(AlertHandler.class).acceptAlert(true);
        return getPage(ToastUtil.class).waitAndGetMessageForSingleToast().equals(toastExpectedMessage);
    }

    /**
     * Click Forecast Next Button.
     */
    public void clickForecastNextButton() {
        clickRibbonIcon(RibbonIcons.Next);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click Forecast Previous Button.
     */
    public void clickForecastPreviousButton() {
        clickRibbonIcon(RibbonIcons.Previous);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate if Forecast page table header exists
     *
     * @param headerName Name of the header value to verify.
     * @return True if no issue else false.
     */
    public boolean validateForecastHeaderExists(String headerName) {
        String headerFormat = "//div[@class = 'headerDiv']/a[contains(text() , '%s')]";
        By headerLocator = By.xpath(String.format(headerFormat, headerName));
        waitHelper.waitForElementPresent(headerLocator);
        return true;
    }

    /**
     * Function to verify that expected columns name are present in planning fund page.
     *
     * @return true if the validation passes, else returns false
     */
    public boolean validateColumnsList() {
        List<String> expectedColumns = PlanningFundListPageColumns.getList();
        return validateListPageCaptions(expectedColumns);
    }

    /**
     * Function to select the record in list page
     *
     * @param planningFundTitle title of the record to select
     */
    public void selectRecordInListPage(String planningFundTitle) {
        singleClickOnRowListPage(PlanningFundListPageColumns.Title.getValue(), planningFundTitle);
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
     * @param title    - title of the planning fund to be verified
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String title) {
        boolean flag = false;
        if (FileHelper.waitForFileToBeAvailable(fileName, 20, 4, node)) {
            flag = true;
            List<String> planningFundColumnHeaders = getListPageHeaders();
            List<String> planningFundRowValues = new ArrayList<>();
            Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
            int planningFundRowNumber = getRowNumberFromListPage(PlanningFundListPageColumns.Title.getValue(), title);
            for (int i = 0; i < planningFundColumnHeaders.size(); i++) {
                String s = getCellData(planningFundRowNumber, planningFundColumnHeaders.get(i));
                planningFundRowValues.add(s);
                listPageColumnHeadersWithValues.put(planningFundColumnHeaders.get(i), planningFundRowValues.get(i));
            }
            Map<String, String> excelColumnHeadersWithValue = ExcelUtil.getColumnHeadersWithRowData(fileName, node, PlanningFundListPageColumns.Title.getValue(), title, null);
            return listPageColumnHeadersWithValues.equals(excelColumnHeadersWithValue);
        } else {
            logger().info("Planning Fund Excel Export failed");
            return flag;
        }
    }

    //AutoDesk

    /**
     * Validate Toolbar changes in Global Option List
     *
     * @return true if the fields are available
     */
    public boolean validateGlobalList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.New);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.More);
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
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar on selection single record view
     *
     * @return true if the fields are available
     */
    public boolean validateSingleRecordList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Edit);
        ribbonMenuIcons.add(RibbonIcons.View);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return validateImportExportOptions(true, false) &&
                validateWorkflow() &&
                validateMoreList(false, false, true, false, false, false);
    }

    /**
     * Set amount to the 1t distribution year
     *
     * @param amount Amount to enter
     */
    public void setDistributionAmount(String amount) {
        waitHelper.waitForPageToLoad(elementToClick);
        elementHelper.doClick(elementToClick);
        waitHelper.waitForElementClickable(enterTextElement);
        elementHelper.doSendKeys(enterTextElement, amount);
    }


    /**
     * This method is used to edit Program year
     *
     * @param programYear    programYear
     * @param globalFundName globalFundName
     */
    public void editPlanningFundProgramYear(String programYear, String globalFundName) {
        waitHelper.waitForPageToLoad(programYearDropDown);
        elementHelper.selectComboBoxItemByTextWithoutValidation(programYearDropDown, programYear);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        pickFund(globalFundName);
    }

    /**
     * This method is used to edit Facial year
     *
     * @param globalFundName globalFundName
     */
    public void editPlanningFundFacialYear(String globalFundName) {
        waitHelper.waitForPageToLoad(defaultFiscalYearSelect);
        elementHelper.selectComboBoxItemByTextWithoutValidation(defaultFiscalYearSelect, nonSeptFiscalYear);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        pickFund(globalFundName);
    }

    /**
     * Deletes distribution details row and saves the record when there are more than 1 distribution details
     *
     * @param fundName is the fund name to be deleted
     */
    public void deleteDistributionDetailsRowAndSave(String fundName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(elementHelper.getElement(distributionDetailsTableRow).findElement(By.xpath(String.format("//td[text()='%s']", fundName))));
        elementHelper.doClick(deleteButton);
        getPage(AlertHandler.class).acceptAlert(true);
    }

}
