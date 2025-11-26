package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.constants.enums.PickerFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import java.util.ArrayList;

public class AddStandardItemsPage extends RibbonMenu {


    private By standardItemListDropDown;
    private By itemPickerDiv;
    private By ignoreContainerStandardItemsCheckbox;
    private By selectButtonSingleItem;
    private By singleItemStandardItemPicker;
    private By standardItemSelectionForSingleItem;
    private By containerText;
    private By itemName;
    private By clearItem;
    private By standardItemsDropDownSave;

    private By standardItemsDropDown;

    private By multipleItemPickerDiv;

    private By multipleItemPickerRows;

    public AddStandardItemsPage(WebDriver driver)
    {
        super(driver);
        var locators = LocatorUtil.getLocators("AddStandardItemsPage.json");
        standardItemListDropDown = locators.get("standardItemListDropDown");
        itemPickerDiv = locators.get("itemPickerDiv");
        ignoreContainerStandardItemsCheckbox = locators.get("ignoreContainerStandardItemsCheckbox");
        selectButtonSingleItem = locators.get("selectButtonSingleItem");
        singleItemStandardItemPicker = locators.get("singleItemStandardItemPicker");
        standardItemSelectionForSingleItem = locators.get("standardItemSelectionForSingleItem");
        containerText= locators.get("containerText");
        itemName = locators.get("itemName");
        clearItem = locators.get("clearItem");
        standardItemsDropDown = locators.get("standardItemsDropDown");
        standardItemsDropDownSave = locators.get("standardItemsDropDownSave");
        multipleItemPickerDiv = locators.get("multipleItemPickerDiv");
        multipleItemPickerRows = locators.get("multipleItemPickerRows");
    }

    /**
     * Method to add standard items to the items list.
     *
     * @param container         container of the items to be selected.
     * @param noOfItems         no of items to be selected.
     * @param standardItemTable Name of the standard Item Table
     * @return List of names of selected standard items.
     */
    public ArrayList<String> addStandardItemsOld(String container, int noOfItems, String standardItemTable) {
        selectStandardItemsInDropdownOld(standardItemTable);
        ArrayList<String> selectRowsItemNumber = new ArrayList<>();
        clickRibbonIcon(RibbonIcons.Add);
        if (Strings.isNotNullAndNotEmpty(container)) {
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(itemPickerDiv);
            getPage(Picker.class).filterPickerColumn(itemPickerDiv, "Container", container, PickerFilterOptions.Contains);
        }
        waitHelper.waitForPageToLoad();
        for (int i = 1; i <= noOfItems; i++) {
            selectRowsItemNumber.add(getPage(Picker.class).getDataByRowNoColumnName(i, "Pay Item No.", itemPickerDiv));
        }
        getPage(Picker.class).multiSelectByCountOld(noOfItems, itemPickerDiv);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageTabHeaderToBeClickable();
        return selectRowsItemNumber;
    }

    /**
     * Method to add standard items to the items list.
     *
     * @param container         container of the items to be selected.
     * @param noOfItems         no of items to be selected.
     * @param standardItemTable Name of the standard Item Table
     * @return List of names of selected standard items.
     */
    public ArrayList<String> addStandardItems(String container, int noOfItems, String standardItemTable) {
        selectStandardItemsInDropdown(standardItemTable);
        ArrayList<String> selectRowsItemNumber = new ArrayList<>();
        if (Strings.isNotNullAndNotEmpty(container)) {
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(multipleItemPickerDiv);
            getPage(Picker.class).filterPickerColumn(multipleItemPickerDiv, "Container", container, PickerFilterOptions.Contains);
        }
        waitHelper.waitForPageToLoad();
        for (int i = 1; i <= noOfItems; i++) {
            selectRowsItemNumber.add(getPage(Picker.class).getDataByRowNoColumnName(i, "Name/Pay Item No", multipleItemPickerDiv));
        }
        getPage(Picker.class).multiSelectByCount(noOfItems, multipleItemPickerDiv);
        waitHelper.waitForPageTabHeaderToBeClickable();
        return selectRowsItemNumber;
    }

    /**
     * Method to add standard items to the items list.
     *
     * @param container         container of the items to be selected.
     * @param noOfItems         no of items to be selected.
     * @param startRowNumber    row number to start with
     * @param standardItemTable standard Item table
     * @return List of names of selected standard items.
     */
    public ArrayList<String> addStandardItemsOld(String container, int startRowNumber, int noOfItems, String standardItemTable) {
        selectStandardItemsInDropdownOld(standardItemTable);
        ArrayList<String> selectRowsItemNumber = new ArrayList<>();
        clickRibbonIcon(RibbonIcons.Add);
        if (Strings.isNotNullAndNotEmpty(container)) {
            getPage(Picker.class).filterPickerColumn(itemPickerDiv, "Container", container, PickerFilterOptions.Contains);
        }
        waitHelper.waitForPageToLoad();
        for (int i = startRowNumber; i < noOfItems + startRowNumber; i++) {
            selectRowsItemNumber.add(getPage(Picker.class).getDataByRowNoColumnName(i, "Pay Item No.", itemPickerDiv));
            getPage(Picker.class).clickRow(i, itemPickerDiv);
        }
        getPage(Picker.class).clickSelect(itemPickerDiv);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(RibbonIcons.NewDropDown);
        return selectRowsItemNumber;
    }

    /**
     * Selects the Standard Items option in the item dropdown
     *
     * @param standardItemTableName Name of the standard Table name
     */
    public void selectStandardItemsInDropdownOld(String standardItemTableName) {
        waitHelper.waitForPageToLoad(standardItemListDropDown);
        elementHelper.selectComboBoxItemByText(standardItemListDropDown, standardItemTableName);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
    }

    /**
     * Selects the Standard Items option in the item dropdown
     *
     * @param standardItemTableName Name of the standard Table name
     */
    public void selectStandardItemsInDropdown(String standardItemTableName) {
        waitHelper.waitForPageToLoad(standardItemsDropDown);
        elementHelper.selectComboBoxItemByText(standardItemsDropDown, standardItemTableName);
        elementHelper.scrollToView(standardItemsDropDownSave);
    }


    /**
     * This method is used to validate container Value in Items' Picker
     * @param standardItemTableName - Standard Item Table to be verified
     * @param containerName - Name of the container to be verified
     * @return True if the Container Name exists
     */
    public boolean validateContainerInItemPicker(String standardItemTableName,String containerName){
        selectStandardItemsInDropdownOld(standardItemTableName);
        clickRibbonIcon(RibbonIcons.Add);
        return getPage(Picker.class).filterPickerColumn(itemPickerDiv, "Container", containerName, PickerFilterOptions.Contains);
    }

    /**
     * Method to click on ignore container check box
     */
    public void clickOnIgnoreContainer()
    {
        waitHelper.waitForPageToLoad();
        elementHelper.doClickCheckboxLabelByJS(ignoreContainerStandardItemsCheckbox);
    }

    /**
     * Click on Standard Item for Single Item
     * @param rowNumber Row number to be selected
     * @return Name of the standard Item
     */
    public String clickOnStandardItemsForSingleItem(Integer rowNumber)
    {
        waitHelper.waitForPageToLoad();
        elementHelper.selectComboBoxItemByText(standardItemSelectionForSingleItem,"StandardItems");
        waitHelper.waitForPageToLoad(selectButtonSingleItem);
        elementHelper.doClick(selectButtonSingleItem);
        waitHelper.waitForPageToLoad(singleItemStandardItemPicker);
        getPage(Picker.class).singleSelectByRowNumber(rowNumber,singleItemStandardItemPicker);
        waitHelper.waitForPageToLoad(selectButtonSingleItem);
        return elementHelper.doGetAttribute(itemName,"value");
    }

    /**
     * Click on Standard Item for Single Item
     * @param rowNumber Row number to be selected
     * @param standardItemsTable Name of the standardItems table
     * @return Name of the standard Item
     */
    public String clickOnStandardItemsForSingleItem(Integer rowNumber,String standardItemsTable)
    {
        waitHelper.waitForPageToLoad();
        elementHelper.selectComboBoxItemByText(standardItemSelectionForSingleItem,standardItemsTable);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectButtonSingleItem);
        waitHelper.waitForPageToLoad(singleItemStandardItemPicker);
        getPage(Picker.class).singleSelectByRowNumber(rowNumber,singleItemStandardItemPicker);
        waitHelper.waitForPageToLoad(selectButtonSingleItem);
        return elementHelper.doGetAttribute(itemName,"value");
    }

    /**
     * Get container element
     * @return Container Element
     */
    public String getContainerElement()
    {
        waitHelper.waitForPageToLoad(containerText);
        return elementHelper.doGetAttribute(containerText,"value");
    }

    /**
     * Validate Clear Pay Item
     * @param item Name of the item
     * @return True if validated Successfully
     */
    public boolean validateClearPayItem(String item)
    {
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeysUsingAction(itemName,item);
        elementHelper.doClick(clearItem);
        waitHelper.waitForPageToLoad(itemName);
        return elementHelper.doGetAttribute(itemName,"value").isEmpty();
    }

}
