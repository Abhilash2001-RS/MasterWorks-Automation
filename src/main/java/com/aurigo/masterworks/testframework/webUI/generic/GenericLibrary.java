package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.GridType;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ListPageFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RegexStrings;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.aventstack.extentreports.Status.INFO;

/**
 * Generic Page for Library Tests.
 * Functions to Create, View, Edit, Delete. Create Duplicate, Verify Required fields.
 */
public class GenericLibrary extends ListPage {

    private By libraryDashboardTab;
    private By unitsGridHead;
    private By unitsGridBody;

    private String editAppendPrefix = "Edit";
    private String systemName = "System name";
    private String unit = "Unit";


    public GenericLibrary(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("GenericLibrary.json");
        libraryDashboardTab = locators.get("libraryDashboardTab");
        unitsGridHead = locators.get("unitsGridHead");
        unitsGridBody = locators.get("unitsGridBody");
    }

    /**
     * Enters text to text field's.
     *
     * @param fields A Map of locator and the text to be sent.
     */
    public void enterTextValues(Map<By, String> fields) {
        logger().info("Entering Text Field Values.");
        navigation.switchFrameToContent();
        fields.forEach((k, v) -> elementHelper.doSendKeys(k, v));
    }

    /**
     * Select's Items from drop down field's.
     *
     * @param fields A Map of locator and the item's text to be selected.
     */
    public void selectDropDownItemsUsingText(Map<By, String> fields) {
        logger().info("Selecting Drop Down Values.");
        navigation.switchFrameToContent();
        fields.forEach((k, v) -> {
            elementHelper.selectComboBoxItemByText(k, v);
            waitHelper.waitForPageToLoad();
        });
    }

    /**
     * Verify text values of the text box in edit form.
     *
     * @param fields A Map of locator and the text to be verified.
     */
    private boolean editFormVerifyTextValues(Map<By, String> fields) {
        AtomicBoolean textValuesEqual = new AtomicBoolean(true);
        logger().log(INFO, "Editing Text Field Values in edit form");
        navigation.switchFrameToContent();
        fields.forEach((k, v) -> {
            if (!v.equals(elementHelper.doGetAttribute(k, "Value")) && !(v.equals(elementHelper.doGetText(k)))) {
                logger().fail("Expected value " + v + " is not equal to " + " value " + elementHelper.doGetText(k) + ".");
                textValuesEqual.set(false);
            }
        });
        return textValuesEqual.get();
    }

    /**
     * Verify text values of the text box in view form.
     *
     * @param fields A Map of locator and the text to be verified.
     * @return True if all field validations are as expected, else false.
     */
    public boolean viewFormVerifyTextValues(Map<By, String> fields) {
        AtomicBoolean textValuesEqual = new AtomicBoolean(true);
        logger().log(INFO, "Verifying Text Values in view form");
        navigation.switchFrameToContent();
        fields.forEach((k, v) -> {
            if (!v.equals(elementHelper.doGetText(By.id(k.toString().replace("By.id: ", "") + "_Text")))) {
                logger().fail("Expected value " + v + " is not equal to " + " value " + elementHelper.doGetText(k) + ".");
                textValuesEqual.set(false);
                logger().fail("Validation failed for <b>" + k.toString() + "</b> field against the value <b>" + v + "</b>");
            } else {
                logger().pass("Validation passed for <b>" + k.toString() + "</b> field against the value <b>" + v + "</b>");
            }
        });
        return textValuesEqual.get();
    }

    /**
     * Method to click on specified System name in Measurement system
     *
     * @param textToSearch Text to search in column search field
     */
    public void clickOnSystem(String textToSearch) {
        filterListPage(systemName, textToSearch, ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(0);
        clickView();
    }

    /**
     * Method to get the list of all the units in the library measurement system
     *
     * @return list of units
     */
    public List<String> getUnitsListFromMeasurementSys() {
        return getGridData(GridType.RadGrid, unit, unitsGridBody, unitsGridHead);
    }

    /**
     * Edit the library form, change its name text field and verify edited name.
     *
     * @param fields                  Text field locators.
     * @param labelsAndAsterisk       Text field names and required asterisk sign locators.
     * @param identifyingColumnName   Column name to identify the list row in list page.
     * @param expectedValueBeforeEdit Value of the row before it is edited.
     */
    public boolean editLibraryFormAndVerify(HashMap<By, String> fields, List<By> labelsAndAsterisk, String identifyingColumnName, String expectedValueBeforeEdit) {
        logger().log(INFO, "Editing Library");
        validateRowInListPage(identifyingColumnName, expectedValueBeforeEdit);
        clickFirstRow();
        clickEdit();
        validateElementsExists(labelsAndAsterisk);
        boolean textValueVerified = editFormVerifyTextValues(fields);
        fields.forEach((k, v) -> elementHelper.doSendKeys(k, editAppendPrefix + v));
        clickSave();
        String returnedValue = validateRowInListPage(identifyingColumnName, "Edit" + expectedValueBeforeEdit);
        return returnedValue.equals(editAppendPrefix + expectedValueBeforeEdit) && textValueVerified;
    }

    /**
     * Delete Library from list page and Append Edit to given name.
     *
     * @param identifyingColumnName   Column name to identify the list row in list page.
     * @param expectedValueBeforeEdit Value of the row before it is edited.
     * @return returns true if search after deleting shows zero rows, else returns false.
     */
    public boolean deleteLibraryFromListPageAfterEdit(String identifyingColumnName, String expectedValueBeforeEdit) {
        logger().log(INFO, "Deleting Library");
        navigation.switchFrameToContent();
        validateRowInListPage(identifyingColumnName, editAppendPrefix + expectedValueBeforeEdit);
        clickFirstRow();
        clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        return filterToVerifyElementDoesNotExists(identifyingColumnName, editAppendPrefix + expectedValueBeforeEdit);
    }

    /**
     * Delete Library from list page.
     *
     * @param identifyingColumnName Column name to identify the list row in list page.
     * @param searchString          Value of the row before it is edited.
     * @return returns true if search after deleting shows zero rows, else returns false.
     */
    public boolean deleteRecordFromListPage(String identifyingColumnName, String searchString) {
        logger().log(INFO, "Deleting Library");
        navigation.switchFrameToContent();
        validateRowInListPage(identifyingColumnName, searchString);
        clickFirstRow();
        clickDelete();
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        return filterToVerifyElementDoesNotExists(identifyingColumnName, searchString);
    }

    /**
     * Navigate to library module from landing page.
     */
    public void navigateToLibraryModule() {
        navigation.navigateToModule("Library", libraryDashboardTab);
    }

    /**
     * Validates Vendor Sub category on List page
     *
     * @param columnName   name of the column
     * @param listCellName Name to validate.
     * @return True if exists else false.
     */
    public boolean validateOnListPage(String columnName, String listCellName) {
        var rowNum = getRowNumberFromListPage(columnName, listCellName);
        return rowNum > -1;
    }

    /**
     * Verify the warning Required text when trying to save without entering required values.
     *
     * @param requiredText Required labels locator's
     */
    public boolean verifyRequiredWarningLabels(List<By> requiredText) {
        logger().log(INFO, "Verifying Required fields.");
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        boolean validated = validateElementsExists(requiredText);
        clickCancel();
        return validated;
    }

    /**
     * Verify given text labels with it's locators are verified.
     *
     * @param labels Locators of elements to check if exists.
     */
    public boolean validateElementsExists(List<By> labels) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(labels.get(0));
        getPage(Validations.class).verifyElementsExists(labels);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Verify given text labels with it's locators are verified.
     *
     * @param labels Locators of Mandatory Elements to check if exists.
     * @return true if exists
     */
    public boolean validateMandatoryFields(List<By> labels) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(labels.get(0));
        getPage(Validations.class).isAsteriskSignPresentOnLabel(labels).assertAll();
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Checks if List Page contains specified cell value in the search column.
     *
     * @param columnName  column which has to be searched
     * @param searchValue the text to be searched in the specified column
     * @return Returns true if searched field doesn't exists else false.
     */
    public boolean validateElementDoesNotExistsInListPage(String columnName, String searchValue) {
        return filterToVerifyElementDoesNotExists(columnName, searchValue);
    }

    /**
     * Click New and wait for Cancel button in new page to appear.
     */
    public void clickNew() {
        logger().log(INFO, "Clicking New");
        clickRibbonIcon(RibbonIcons.New);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click Delete.
     */
    public void clickDelete() {
        logger().log(INFO, "Clicking Delete");
        clickRibbonIcon(RibbonIcons.Delete);
    }

    /**
     * Click Save and wait for New button in list page to appear.
     */
    public void clickSave() {
        logger().log(INFO, "Clicking Save");
        clickRibbonIcon(RibbonIcons.Save);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click Cancel and wait for New button in list page to appear.
     */
    public void clickCancel() {
        logger().log(INFO, "Clicking Cancel");
        clickRibbonIcon(RibbonIcons.Cancel);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click Edit and wait for Cancel button in edit page to appear.
     */
    public void clickEdit() {
        logger().log(INFO, "Clicking Edit");
        clickRibbonIcon(RibbonIcons.Edit);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click View and wait for Cancel button in view page to appear.
     */
    public void clickView() {
        logger().log(INFO, "Clicking View");
        clickRibbonIcon(RibbonIcons.View);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click first row in the list page.
     */
    public void clickFirstRow() {
        logger().log(INFO, "Clicking First Row");
        navigation.switchFrameToContent();
        singleClickOnRowListPage(0);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate if row exists in the list page.
     *
     * @param columnName   Column name to identify the list row in list page.
     * @param searchString Cell value to search row.
     * @return Returns cell value of the column searched in after search results positively.
     */
    public String validateRowInListPage(String columnName, String searchString) {
        logger().log(INFO, "Validating Row in List Page.");
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
        filterListPage(columnName, searchString, ListPageFilterOptions.EqualTo);
        return getCellData(0, columnName);
    }

    /**
     * Validate if row exists in the list page.
     *
     * @param columnName            Column name to identify the list row in list page.
     * @param searchString          Cell value to search row.
     * @param listPageFilterOptions Filter Options
     * @return Returns cell value of the column searched in after search results positively.
     */
    public String validateRowInListPage(String columnName, String searchString, ListPageFilterOptions listPageFilterOptions) {
        logger().log(INFO, "Validating Row in List Page.");
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.New));
        filterListPage(columnName, searchString, listPageFilterOptions);
        return getCellData(0, columnName);
    }


    /**
     * Get Generic Xpath for the asterisk sign from the label's xpath
     *
     * @param labelXpath Label's xpath
     * @return Asterisk sign By xpath locator
     */
    public By getRequiredAsteriskXpath(By labelXpath) {
        String prefixXpath = elementHelper.getLocatorAsString(labelXpath);
        return By.xpath(prefixXpath + "/following-sibling::span[@class = 'mandatory-indicator']");
    }

    /**
     * Get generic requires label Xpath using Label's Xpath
     *
     * @param labelXpath Label's Xpath
     * @return Required warning xpath locator.
     */
    public By getRequiredWarningXpath(By labelXpath) {
        String prefixXpath = elementHelper.getLocatorAsString(labelXpath);
        return By.xpath(prefixXpath + "/../following-sibling::td/span[contains(text(),'Required!')]");
    }

    /**
     * Click Save for creating duplicate library and verifies Toast Error.
     *
     * @param expectedToastMessage Expected Toast message to verify.
     * @return True, if Toast message verified else false.
     */
    public boolean clickSaveAndVerifyToastMessageAsExpected(String expectedToastMessage) {
        logger().log(INFO, "Verifying Error Message on creating duplicate library.");
        clickRibbonIcon(RibbonIcons.Save);
        expectedToastMessage = expectedToastMessage.replaceAll(RegexStrings.notASpace.getValue(), "");
        boolean toastMessageAsExpected = getPage(ToastUtil.class).waitAndGetMessageForSingleToast().replaceAll(RegexStrings.notASpace.getValue(), "").equals(expectedToastMessage);
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
        clickCancel();
        return toastMessageAsExpected;
    }

    /**
     * Method to Navigate to Library Module and respective Page in That Module
     *
     * @param pageName Name of the page to be navigated to.
     */

    public void navigateToPageInLibrary(String pageName) {
        navigateToLibraryModule();
        navigation.switchFrameToDefault();
        navigateToLibraryForm(pageName);
    }

    /**
     * Navigate to a particular page in Library module
     *
     * @param pageName name of the page to be navigated
     */
    public void navigateToLibraryForm(String pageName) {
        navigation.navigateToFormInLeftPaneTree(pageName);
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Method to get column data
     *
     * @param columnName column Name
     * @return list of column Data
     */
    public List<String> getColumnDataInLibrary(String columnName) {
        return getColumnData(columnName);
    }

    /**
     * Get Record Identifier Data
     *
     * @param recordName Record
     * @return Record Identifier
     */
    public String getRecordIdentifierData(String recordName) {
        String recordIdentifierXpathCheck = "//td[@title='%s']/following-sibling::td[@align ='right' and @style='display:none;']";
        return elementHelper.doGetAttribute(By.xpath(String.format(recordIdentifierXpathCheck, recordName)), "title");

    }

}