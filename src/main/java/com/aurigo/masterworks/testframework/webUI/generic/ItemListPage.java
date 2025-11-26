package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.helper.ScreenshotHelper;
import com.aurigo.masterworks.testframework.webUI.common.AddStandardItemsPage;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.pages.library.StandardItemsPage;
import com.aurigo.masterworks.testframework.webUI.testData.PayItem;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.util.Strings;

import java.io.File;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;

public class ItemListPage extends RibbonMenu {

    protected By totalAmount;
    protected By inflatedTotalAmount;
    protected By payItemNumberTxtBox;
    protected By itemDescriptionTextArea;
    protected By itemUnitComboBox;
    protected By itemUnitCostTextBox;
    protected By itemQuantityTextBox;
    private By defaultContainerInSelectContainer;
    private By addNewButton;
    private By showPayItemPickerBtn;
    private By addNewButtonWithModifyOption;
    private By itemContainerPickerButton;
    private By selectContainerTab;
    private By displayedElementsList;
    private By dataTable;
    private By headerTable;
    private By tableHeaderText;
    private By itemTable;
    private By expandAllButton;
    private By selectFirstItem;
    private By standardItemsPicker;
    private By bidderItemHeaders;
    private By accountingCodeTextBox;
    private By fundRuleField;
    protected By chooseFile;
    protected By uploadButton;
    private By saveButtonInImport;
    private By clearButtonInNewItemPage;
    private By subItemsLinkButton;
    private By subItemTableXpath;
    private By newContainerNameTextbox;
    private By addItem;
    private By fundRuleAssociationHistory;
    private By fundRuleAssociationHistoryCancelButton;
    private By itemHeader;

    private String contractorBidItemsTemplate = "//td/div[contains(text(),'%s')]/../following-sibling::td[@style='background-color:Yellow;']";
    private String contractorFLBidItemsTemplate = "//td/div[contains(text(),'%s')]/../following-sibling::td[@style='background-color:#F8E99B;']";
    private String input = "%s/input";
    private String inputTextBoxInItemDetailsForecast = "//table[@id='HeaderTableID']//td[%d]//input[@class='FilterBox']";
    private String filterButtonInItemDetailsForecast = "//table[@id='HeaderTableID']//td[%d]//input[@type='submit']";
    private String filterOptionsInItemDetailsForecast = "//ul[@id='FilterMenu_StandardItemNo']//span[contains(text(),'%s')]";
    private String filterOptionsInItemDetails = "//ul[@id='FilterMenu_Resources']//span[contains(text(),'%s')]";
    private String parentContainer = "//a[contains(text(),'%s')]";
    private String recordIdentifierFormat = "//*[text()='%s']";
    private String funRuleAssociatedXpath = "//a[text()='%s']";
    private String contextClickOption = ".//*[text()='Associate Fund - %s']";

    private WebDriver driver;

    public ItemListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("ItemListPage.json");
        defaultContainerInSelectContainer = locators.get("defaultContainerInSelectContainer");
        addNewButton = locators.get("addNewButton");
        showPayItemPickerBtn = locators.get("showPayItemPickerBtn");
        addNewButtonWithModifyOption = locators.get("addNewButtonWithModifyOption");
        itemContainerPickerButton = locators.get("itemContainerPickerButton");
        payItemNumberTxtBox = locators.get("payItemNumberTxtBox");
        itemDescriptionTextArea = locators.get("itemDescriptionTextArea");
        itemUnitComboBox = locators.get("itemUnitComboBox");
        itemQuantityTextBox = locators.get("itemQuantityTextBox");
        itemUnitCostTextBox = locators.get("itemUnitCostTextBox");
        selectContainerTab = locators.get("selectContainerTab");
        dataTable = locators.get("dataTable");
        headerTable = locators.get("headerTable");
        tableHeaderText = locators.get("tableHeaderText");
        expandAllButton = locators.get("expandAllButton");
        selectFirstItem = locators.get("selectFirstItem");
        standardItemsPicker = locators.get("standardItemsPicker");
        bidderItemHeaders = locators.get("bidderItemHeaders");
        totalAmount = locators.get("totalAmount");
        displayedElementsList = locators.get("displayedElementsList");
        itemTable = locators.get("itemTable");
        accountingCodeTextBox = locators.get("accountingCodeTextBox");
        inflatedTotalAmount = locators.get("inflatedTotalAmount");
        chooseFile = locators.get("chooseFile");
        uploadButton = locators.get("uploadButton");
        saveButtonInImport = locators.get("saveButtonInImport");
        clearButtonInNewItemPage = locators.get("clearButtonInNewItemPage");
        subItemsLinkButton = locators.get("subItemsLinkButton");
        subItemTableXpath = locators.get("subItemTableXpath");
        newContainerNameTextbox = locators.get("newContainerNameTextbox");
        addItem = locators.get("addItem");
        fundRuleField = locators.get("fundRuleField");
        fundRuleAssociationHistory = locators.get("fundRuleAssociationHistory");
        fundRuleAssociationHistoryCancelButton = locators.get("fundRuleAssociationHistoryCancelButton");
        itemHeader = locators.get("itemRowHeader");
    }

    /**
     * Checks if given item is present.
     *
     * @param labelName String name of item.
     * @return True if present else false.
     */
    public boolean isElementPresentInItemsPage(String labelName) {
        for (var itr : elementHelper.getElements(displayedElementsList)) {
            if (itr.getText().contains(labelName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add standard items to the items list
     *
     * @param container container of the items to be selected
     * @param noOfItems no of items to be selected
     * @return List of names of selected standard items.
     */
    public ArrayList<String> addStandardItemsOld(String container, int noOfItems) {
        clickAddMultipleItemsOld();
        return getPage(AddStandardItemsPage.class).addStandardItemsOld(container, noOfItems, "StandardItems");
    }

    /**
     * Method to add standard items to the items list
     *
     * @param container container of the items to be selected
     * @param noOfItems no of items to be selected
     */
    public ArrayList<String> addStandardItems(String container, int noOfItems) {
        clickRibbonIcon(RibbonIcons.BudgetItemAddMultiple);
        return getPage(AddStandardItemsPage.class).addStandardItems(container, noOfItems, "StandardItems");
    }

    /**
     * Method to add standard items in Budget Estimate Revision
     *
     * @param container container of the items to be selected
     * @param noOfItems no of items to be selected
     * @return - standard item details
     */
    public ArrayList<String> addStandardItemsInBER(String container, int noOfItems) {
        clickAddMultipleItemsInBER();
        return getPage(AddStandardItemsPage.class).addStandardItemsOld(container, noOfItems, "StandardItems");
    }

    /**
     * Method to add standard items to the items list
     *
     * @param container      container of the items to be selected
     * @param startRowNumber row number to start with
     * @param noOfItems      no of items to be selected
     * @return List of names of selected standard items.
     */
    public ArrayList<String> addStandardItemsOld(String container, int startRowNumber, int noOfItems) {
        clickAddMultipleItemsOld();
        return getPage(AddStandardItemsPage.class).addStandardItemsOld(container, startRowNumber, noOfItems, "StandardItems");
    }

    /**
     * Click add
     */
    public void clickAddMultipleItemsOld() {
        click(ItemMenuBar.AddMultiple);

    }

    /**
     * Click add multiple in Budget Estimate Revision
     */
    public void clickAddMultipleItemsInBER() {
        clickRibbonIcon(RibbonIcons.NewContainerBudgetRevision);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click add
     */
    public void clickAddSingleItems() {
        waitHelper.waitForPageToLoad();
        click(ItemMenuBar.NewItem);
    }

    /**
     * Click add container
     */
    public void clickAddContainer() {
        waitHelper.waitForPageToLoad();
        click(ItemMenuBar.NewContainer);
    }

    /**
     * Method for modifying existing Budget Estimate Items
     *
     * @param payItemNumber    item number of the item to be modified
     * @param modifiedQuantity modified quantity value
     */
    protected void modifyExistingItem(String payItemNumber, float modifiedQuantity) {
        clickRibbonIcon(RibbonIcons.ModifyExistingItems);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.doClick(showPayItemPickerBtn);
        getPage(Picker.class).singleSelectByText("Pay Item No.", payItemNumber, standardItemsPicker);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.doSendKeys(itemQuantityTextBox, Float.toString(modifiedQuantity));
        elementHelper.doClick(accountingCodeTextBox);
        elementHelper.doClick(itemQuantityTextBox);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method for modifying existing Budget Estimate Items
     *
     * @param payItemNumber item number of the item to be modified
     * @param fundRule      - fund rule to be updated
     */
    protected void modifyExistingItem(String payItemNumber, String fundRule) {
        clickRibbonIcon(RibbonIcons.ModifyExistingItems);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.doClick(showPayItemPickerBtn);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByText("Pay Item No.", payItemNumber, standardItemsPicker);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.selectComboBoxItemByText(fundRuleField, fundRule);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method for viewing item details inside modify existing item
     *
     * @param payItemNumber item number of the item to be modified
     */
    protected void modifyExistingItemWithoutEdit(String payItemNumber) {
        clickRibbonIcon(RibbonIcons.ModifyExistingItems);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.doClick(showPayItemPickerBtn);
        getPage(Picker.class).singleSelectByText("Pay Item No.", payItemNumber, standardItemsPicker);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
    }

    /**
     * Method to click on New item,Add Multiple or other options available in Items List Page
     *
     * @param option Menu bar item.
     */
    protected void click(ItemMenuBar option) {
        switch (option) {
            case NewItem:
                clickRibbonIcon(RibbonIcons.NewItem);
                break;

            case AddMultiple:
                clickRibbonIcon(RibbonIcons.AddMultiple);
                break;

            case NewContainer:
                clickRibbonIcon(RibbonIcons.NewContainer);
                break;

            default:
                logger().fail("Invalid Option.");
                break;
        }
    }


    /**
     * Method to Edit Pay Item Number of an Item
     *
     * @param itemNumber    of which Pay Item is to be Edited
     * @param newItemNumber Value with which Pay Item Number is to be Updated. Default is "Item"+TimeStamp
     * @return Edited PayItem Number
     */
    public String editPayItemNumber(String itemNumber, String newItemNumber) {
        logger().info("Editing pay item number " + itemNumber);
        //Select Item from Item List Page
        clickCheckboxInExpandedList(itemNumber, "Name");

        //Click on Edit button:the Ribbon
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        //Edit PayItem Number
        if (newItemNumber.equalsIgnoreCase("Item")) {
            newItemNumber = newItemNumber + "_" + TestDataUtil.getTimeStamp();
        }
        if (isElementPresentInItemsPage("Pay Item No.")) {
            waitHelper.waitForPageToLoad(payItemNumberTxtBox);
            elementHelper.getElement(payItemNumberTxtBox).clear();
            elementHelper.doSendKeysUsingAction(this.payItemNumberTxtBox, newItemNumber);
        }
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();

        return newItemNumber;
    }


    /**
     * Function to Add a new Item
     *
     * @param payItems items to be added
     */
    public void addItem(List<PayItem> payItems) {
        for (var i = 0; i < payItems.size(); i++) {
            var item = payItems.get(i);
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            Wait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"))))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            fluentWait.until(ExpectedConditions.elementToBeClickable(getRibbonIcon(RibbonIcons.SaveAndContinue)));
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.SaveAndContinue));
            if (Strings.isNotNullAndNotEmpty(item.container)) {
                waitHelper.waitForElementClickable(itemContainerPickerButton);
                elementHelper.doClick(itemContainerPickerButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementPresent(selectContainerTab);
                String containerElementTemplate = ".//div[@id='C1_ucContainerControl_TreeContainer']/*//a[.='%s']";
                var element = By.xpath(String.format(containerElementTemplate, item.container));
                waitHelper.waitForElementToBePresentAndClickable(element);
                elementHelper.doClick(element);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
            }
            logger().info("Adding new BE Item Container:" + item.container);
            logger().info("Item no.: " + item.payItemNumber);
            elementHelper.scrollToView(payItemNumberTxtBox);
            elementHelper.doSendKeys(payItemNumberTxtBox, item.payItemNumber);
            elementHelper.doSendKeys(itemDescriptionTextArea, item.description);
            elementHelper.selectComboBoxItemByIndex(itemUnitComboBox, 1);
            waitHelper.waitForPageToLoad();
            elementHelper.doSendKeysUsingAction(itemQuantityTextBox, item.quantity);
            elementHelper.doSendKeysUsingAction(itemUnitCostTextBox, item.unitPrice);
            elementHelper.doClick(itemQuantityTextBox);
            if (i == payItems.size() - 1) {
                clickRibbonIcon(RibbonIcons.Save);
                waitHelper.waitForPageToLoad();
                if (getPage(ToastUtil.class).validatePresenceOfToastMessage() & item.quantity < 0) {
                    break;
                }
                waitHelper.waitForPageToLoad();
                return;
            }
            clickRibbonIcon(RibbonIcons.SaveAndContinue);
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * Add Mandatory field data for an item without saving it
     *
     * @param payItemData - the mandatory data to be fed
     */
    public void addMandatoryFieldsInItem(List<PayItem> payItemData) {
        var item = payItemData.get(0);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.fluentWait(getRibbonIcon(RibbonIcons.SaveAndContinue));
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.SaveAndContinue));
        if (Strings.isNotNullAndNotEmpty(item.container)) {
            waitHelper.waitForElementClickable(itemContainerPickerButton);
            elementHelper.doClick(itemContainerPickerButton);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(selectContainerTab);
            String containerElementTemplate = ".//div[@id='C1_ucContainerControl_TreeContainer']/*//a[.='%s']";
            var element = By.xpath(String.format(containerElementTemplate, item.container));
            waitHelper.waitForElementToBePresentAndClickable(element);
            elementHelper.doClick(element);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        }
        logger().info("Adding new BE Item Container:" + item.container);
        logger().info("Item no.: " + item.payItemNumber);
        elementHelper.scrollToView(payItemNumberTxtBox);
        elementHelper.doSendKeys(payItemNumberTxtBox, item.payItemNumber);
        elementHelper.doSendKeys(itemDescriptionTextArea, item.description);
        elementHelper.selectComboBoxItemByIndex(itemUnitComboBox, 1);
        elementHelper.doSendKeysUsingAction(itemQuantityTextBox, item.quantity);
        elementHelper.doSendKeysUsingAction(itemUnitCostTextBox, item.unitPrice);
        elementHelper.doClick(itemQuantityTextBox);
    }

    /**
     * Validate clear functionality
     *
     * @return - true, if clear functionality works fine
     */
    public boolean validateClearFunctionality() {
        logger().info("Validating Clear Functionality");
        var item = "PayItem" + TestDataUtil.getRandomNumber(1000000, 9999999);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.fluentWait(getRibbonIcon(RibbonIcons.SaveAndContinue));
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.SaveAndContinue));
        logger().info("Item no.: " + item);
        elementHelper.scrollToView(payItemNumberTxtBox);
        elementHelper.doSendKeys(payItemNumberTxtBox, item);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(clearButtonInNewItemPage);
        elementHelper.doClick(clearButtonInNewItemPage);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageToLoad();
        return elementHelper.doGetAttribute(payItemNumberTxtBox, "value").isEmpty();
    }

    /**
     * Function to Add a new Item
     *
     * @param payItems items to be added
     */
    public void addItemInStandardItemPage(List<PayItem> payItems) {
        for (var i = 0; i < payItems.size(); i++) {
            var item = payItems.get(i);
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            Wait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"))))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            fluentWait.until(ExpectedConditions.elementToBeClickable(getRibbonIcon(RibbonIcons.SaveAndContinue)));
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.SaveAndContinue));
            if (Strings.isNotNullAndNotEmpty(item.container)) {
                waitHelper.waitForElementClickable(itemContainerPickerButton);
                elementHelper.doClick(itemContainerPickerButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementPresent(selectContainerTab);
                String containerElementTemplate = ".//div[@id='C1_ucContainerControl_TreeContainer']/*//a[.='%s']";
                elementHelper.doClick(By.xpath(String.format(containerElementTemplate, item.container)));
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
            }
            logger().info("Adding new BE Item Container:" + item.container);
            logger().info("Item no.: " + item.payItemNumber);
            elementHelper.scrollToView(payItemNumberTxtBox);
            elementHelper.doSendKeys(payItemNumberTxtBox, item.payItemNumber);
            elementHelper.doSendKeys(itemDescriptionTextArea, item.description);
            elementHelper.selectComboBoxItemByIndex(itemUnitComboBox, 1);
            if (i == payItems.size() - 1) {
                clickRibbonIcon(RibbonIcons.Save);
                waitHelper.waitForPageToLoad();
                if (getPage(ToastUtil.class).validatePresenceOfToastMessage() & item.quantity < 0) {
                    break;
                }
                waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.NewDropDown));
                return;
            }
            clickRibbonIcon(RibbonIcons.SaveAndContinue);
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * Function to Add a new BER Item
     *
     * @param payItems items to be added
     */
    public void addBERItem(List<PayItem> payItems) {
        for (var i = 0; i < payItems.size(); i++) {
            var item = payItems.get(i);
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            Wait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"))))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.SaveAndContinue));
            if (Strings.isNotNullAndNotEmpty(item.container)) {
                waitHelper.waitForElementClickable(itemContainerPickerButton);
                elementHelper.doClick(itemContainerPickerButton);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementPresent(selectContainerTab);
                String containerElementTemplate = ".//div[@id='C1_ucContainerControl_TreeContainer']/*//a[.='%s']";
                var element = By.xpath(String.format(containerElementTemplate, item.container));
                waitHelper.waitForElementToBePresentAndClickable(element);
                elementHelper.doClick(element);
                waitHelper.waitForPageToLoad();
                waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
            }
            logger().info("Adding new BE Item Container:" + item.container);
            logger().info("Item no.: " + item.payItemNumber);
            elementHelper.scrollToView(payItemNumberTxtBox);
            elementHelper.doSendKeys(payItemNumberTxtBox, item.payItemNumber);
            elementHelper.doSendKeys(itemDescriptionTextArea, item.description);
            elementHelper.selectComboBoxItemByIndex(itemUnitComboBox, 1);
            waitHelper.waitForPageToLoad();
            elementHelper.doSendKeys(itemQuantityTextBox, item.quantity);
            elementHelper.doSendKeys(itemUnitCostTextBox, item.unitPrice);
            elementHelper.doClick(itemQuantityTextBox);
            if (i == payItems.size() - 1) {
                clickRibbonIcon(RibbonIcons.Save);
                waitHelper.waitForPageToLoad();
                if (getPage(ToastUtil.class).validatePresenceOfToastMessage() & item.quantity < 0) {
                    break;
                }
                waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.AddNewWithModifyOption));
                return;
            }
            clickRibbonIcon(RibbonIcons.SaveAndContinue);
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * Method to Delete An item
     *
     * @param itemNumber item to be Deleted
     */
    public void deleteAnItem(String itemNumber) {
        //Select Item from Item List Page
        clickCheckboxInExpandedList(itemNumber, "Name");

        //Click on Delete button:the Ribbon
        clickRibbonIcon(RibbonIcons.Delete);

        //Handle Alert
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Delete more than one item from Item List Page
     *
     * @param numberOfItems - number of items to be deleted
     */
    public void deleteMultipleItems(int numberOfItems) {
        while (numberOfItems > 0) {
            elementHelper.doClick(selectFirstItem);
            //Click on Delete button:the Ribbon
            clickRibbonIcon(RibbonIcons.Delete);
            //Handle Alert
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
            numberOfItems--;
        }
    }

    /**
     * Function to get item list page headers
     *
     * @return list of item list page headers
     */
    public List<String> itemListPageHeaders() {
        navigation.switchFrameToContent();
        List<WebElement> headerColumns = elementHelper.getElement(headerTable).findElements(By.tagName("a"));
        List<String> headerList = new ArrayList<>();
        String headerName;
        for (var headerColumn : headerColumns) {
            elementHelper.scrollToView(headerColumn);
            headerName = headerColumn.getText();
            if (Strings.isNotNullAndNotEmpty(headerName)) {
                headerList.add(headerName);
            }
        }
        return headerList;
    }

    /**
     * Function to Expand the Item List (Same as ExpandItemList Method with changed logic)
     */
    public void expandItemList() {
        logger().info("Expanding Item List");
        waitHelper.waitForPageTabHeaderToBeClickable();
        List<WebElement> elementsToExpand = elementHelper.getElements(By.xpath(".//*[@id='dataTable']//tr[@class='hrRow']"));
        if (elementsToExpand.size() == 0) {
            elementsToExpand = elementHelper.getElements(By.xpath(".//*[@id='dataTable']//tr[@class='hrRow'][1]"));
        }

        //Getting the Status of the Expand Button in the List Page
        if (elementsToExpand.size() > 0) {
            String isExpandedStatus = elementsToExpand.get(0).getAttribute("isexpanded");

            if (isExpandedStatus.equalsIgnoreCase("false")) {
                List<WebElement> cellsToExpand = elementsToExpand.get(0).findElements(By.tagName("td"));
                elementHelper.doContextClick(cellsToExpand.get(1));
                elementHelper.doClick(expandAllButton);
                waitHelper.waitForPageTabHeaderToBeClickable();
            }
        }
    }

    /**
     * Expand item at a particular row
     *
     * @param row Row of the item.
     */
    public void expandItemList(int row) {
        List<WebElement> elementsToExpand = elementHelper.getElement(dataTable).findElements(By.className("hrRow"));

        //Getting the Status of the Expand Button:the List Page
        if (elementsToExpand.size() > 0) {
            String isExpandedStatus = elementsToExpand.get(row).getAttribute("isexpanded");

            if (isExpandedStatus.equalsIgnoreCase("false")) {
                List<WebElement> cellsToExpand = elementsToExpand.get(row).findElements(By.tagName("td"));
                elementHelper.doContextClick(cellsToExpand.get(1));
                elementHelper.doClick(expandAllButton);
                waitHelper.waitForPageToLoad();
            }
        }
    }

    /**
     * Function to Save Settings by Right Clicking on the Item List Page
     */
    public void saveSettings() {
        clickRibbonIcon(RibbonIcons.ItemListSave);
    }

    /**
     * Function to Collapse All the Expanded items
     */
    public void collapseAll() {
        List<WebElement> elementsToExpand = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        List<WebElement> cellsToExpand = elementsToExpand.get(0).findElements(By.tagName("td"));
        elementHelper.doContextClick(cellsToExpand.get(1));

        elementHelper.doClick(
                elementHelper.getElement(By.className("buttonsplitmenu"))
                        .findElement(By.xpath(".//*[text()='Collapse All']")));
    }

    /**
     * Function to get the column data of all rows
     *
     * @param columnHeaderName Header name of the column.
     * @return List of data string
     */
    public List<String> getColumnData(String columnHeaderName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        List<String> dataList = new ArrayList<>();
        expandItemList();
        //Getting all the row elements of the table.
        int targetColumnNumber = getColumnNumberUsingHeaderName(columnHeaderName);
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(By.xpath(String.format("//a[contains(text(),'%s')]", columnHeaderName)));
        waitHelper.waitForPageToLoad();
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        String cellData = null;
        if (itemListPageRows.size() > 0) {
            for (var row : itemListPageRows) {
                if (!row.getText().isEmpty())
                    cellData = row.findElements(By.tagName("td")).get(targetColumnNumber).getText().trim();
                logger().info("Row data " + cellData + "\n");
                if (!cellData.isEmpty())
                    dataList.add(cellData);
            }
            return dataList;
        } else {
            logger().info("No Record is present in the List page.");
        }
        return null;
    }

    /**
     * Function to get the column data of all rows in collapsed state
     *
     * @param columnHeaderName Header name of the column.
     * @return List of data string
     */
    public List<String> getCollapsedColumnData(String columnHeaderName) {
        navigation.switchFrameToContent();
        List<String> dataList = new ArrayList<>();
        collapseAll();
        //Getting all the row elements of the table.
        int targetColumnNumber = getColumnNumberUsingHeaderName(columnHeaderName);
        waitHelper.waitForPageToLoad();
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        String cellData = null;
        if (itemListPageRows.size() > 0) {
            for (var row : itemListPageRows) {
                if (!row.getText().isEmpty())
                    cellData = row.findElements(By.tagName("td")).get(targetColumnNumber).getText().trim();
                if (!cellData.isEmpty())
                    dataList.add(cellData);
            }

            return dataList;
        } else {
            logger().info("No Record is present in the List page.");
        }
        return null;
    }


    /**
     * Function to get the Column Number for the Item.
     *
     * @param headerName Header of the Column Number to search in.
     * @return column number.
     */
    public int getColumnNumberUsingHeaderName(String headerName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        List<WebElement> tableDatas = elementHelper.getElement(headerTable).findElements(By.xpath("//td[@class = 'hgHeader']"));
        //Starting i from 1 since 0th index is checkbox which doesn't contains text.
        for (int i = 1; i < tableDatas.size(); i++) {
            WebElement headerTextElement = tableDatas.get(i).findElement(By.xpath(".//div"));
            String headerText = elementHelper.doGetText(headerTextElement);
            if (headerText.equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function to check if column header is present
     *
     * @param columnHeaderNameToSearchIn Header of the Column Number to search in
     * @return True if column header present else false.
     */
    public boolean isColumnHeaderPresent(String columnHeaderNameToSearchIn) {
        navigation.switchFrameToContent();
        List<WebElement> headerColumnNames = elementHelper.getElement(headerTable).findElements(By.tagName("tr"));
        List<WebElement> headerColumnCount = headerColumnNames.get(0).findElements(By.tagName("td"));

        int headerColumnCounter = 1;
        boolean flag = false;
        for (; headerColumnCounter < headerColumnCount.size(); headerColumnCounter++) {
            WebElement headerColumnName = headerColumnCount.get(headerColumnCounter);
            String columnheader = headerColumnName.getText();
            if (columnheader.trim().equalsIgnoreCase(columnHeaderNameToSearchIn)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Function to Validate if the Item is present in the List
     *
     * @param itemIdentifier             Item that needs to be searched
     * @param columnHeaderNameToSearchIn Column to search for:the item
     * @return True if item present else false.
     */
    public boolean isItemPresentInList(String itemIdentifier, String columnHeaderNameToSearchIn) {
        expandItemList();
        navigation.switchFrameToContent();
        boolean found = false;
        int targetHeaderColumnNumber = getColumnNumberUsingHeaderName(columnHeaderNameToSearchIn);
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (var row : itemListPageRows) {
            List<WebElement> itemRowData = row.findElements(By.tagName("td"));
            String targetData = itemRowData.get(targetHeaderColumnNumber).getText();
            itemIdentifier = itemIdentifier.trim();
            if (targetData.trim().equalsIgnoreCase(itemIdentifier)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Function to get the column data of all rows
     *
     * @param columnHeaderName Header name of the column.
     * @return List of data string
     */
    protected List<WebElement> getColumnDataAsWebElementList(String columnHeaderName) {
        navigation.switchFrameToContent();
        List<WebElement> dataList = new ArrayList<>();
        expandItemList();
        //Getting all the row elements of the table.
        int targetColumnNumber = getColumnNumberUsingHeaderName(columnHeaderName);
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));

        WebElement cellData = null;
        if (itemListPageRows.size() > 0) {
            for (var row : itemListPageRows) {
                if (!row.getText().isEmpty()) {
                    cellData = row.findElements(By.tagName("td")).get(targetColumnNumber);
                    dataList.add(cellData);
                }
            }
            return dataList;
        } else {
            logger().info("No Record is present in the List page.");
        }
        return null;
    }

    /**
     * Function to check whether items are present in the item list or not
     *
     * @return True if empty else false.
     */
    public boolean isItemListEmpty() {
        navigation.switchFrameToContent();
        return elementHelper.getElement(dataTable).findElements(By.tagName("tr")).size() <= 0;
    }

    /**
     * Function to Click on the Checkbox next to the Item name
     *
     * @param itemIdentifier             Item Name
     * @param columnHeaderNameToSearchIn Column to search in
     */
    public void clickCheckbox(String itemIdentifier, String columnHeaderNameToSearchIn) {
        navigation.switchFrameToContent();
        expandItemList();
        int targetHeaderColumnNumber = getColumnNumberUsingHeaderName(columnHeaderNameToSearchIn);
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (var row : itemListPageRows) {
            List<WebElement> itemRowData = row.findElements(By.tagName("td"));
            String targetData = itemRowData.get(targetHeaderColumnNumber).getText();
            if (targetData.trim().equalsIgnoreCase(itemIdentifier.trim())) {
                elementHelper.doClick(itemRowData.get(targetHeaderColumnNumber - 1).findElement(By.tagName("input")));
                break;
            }
        }
        navigation.switchFrameToContent();
    }

    /**
     * Click checkbox for given row number.
     *
     * @param rowNumber Row number for checkbox to click.
     */
    public void clickCheckboxByRowNumber(int rowNumber) {
        navigation.switchFrameToContent();
        expandItemList();
        WebElement ItemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> ItemListPageRows = ItemListPageDataTable.findElements(By.tagName("tr"));
        elementHelper.doClick(ItemListPageRows.get(rowNumber).findElement(By.tagName("input")));
    }

    /**
     * Method to Select All Rows Present:the Item List Page
     */
    public void selectAllRows() {
        navigation.switchFrameToContent();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        waitHelper.waitForPageToLoad();
        for (var row : itemListPageRows) {
            elementHelper.doClick(row.findElement(By.tagName("input")));
        }
    }

    /**
     * Select all the item rows
     */
    public void selectAllItemRows() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        expandItemList();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        int colNum = getColumnNumberUsingHeaderName(ItemListPageHeaders.Description.getValue());
        for (var row : itemListPageRows) {
            if (!row.findElement(By.xpath(".//td[" + colNum + "]")).getText().isEmpty()) {
                elementHelper.doClick(row.findElement(By.tagName("input")));
            }
        }
    }

    /**
     * Get number of items in item list page
     *
     * @return The number of items
     */
    public Integer getNumberOfItems() {
        int numberOfRows = 0;
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        expandItemList();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        int colNum = getColumnNumberUsingHeaderName(ItemListPageHeaders.Description.getValue());
        for (var row : itemListPageRows) {
            if (!row.findElement(By.xpath(".//td[" + colNum + "]")).getText().isEmpty()) {
                numberOfRows = numberOfRows + 1;
            }
        }
        return numberOfRows;
    }

    /**
     * Get item names from list page
     *
     * @return List of all the item names in page
     */
    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        expandItemList();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        int nameCol = getColumnNumberUsingHeaderName(ItemListPageHeaders.Name.getValue()) + 1;
        int colNum = getColumnNumberUsingHeaderName(ItemListPageHeaders.Description.getValue());
        for (var row : itemListPageRows) {
            if (!row.findElement(By.xpath(".//td[" + colNum + "]")).getText().isEmpty()) {
                names.add(row.findElement(By.xpath(".//td[" + nameCol + "]")).getText());
            }
        }
        return names;
    }

    /**
     * Method to Select A Record Present:the Item List Page
     *
     * @param searchText Text to search
     */
    public void selectARecord(String searchText) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageCells = itemListPageDataTable.findElements(By.tagName("td"));
        for (var row : itemListPageCells) {
            if (row.getText().equals(searchText)) {
                elementHelper.doClick(row);
                return;
            }
        }
    }

    /**
     * Deselect Record
     *
     * @param recordName Name of the record
     */
    public void deselectARecord(String recordName) {
        waitHelper.waitForPageToLoad();
        By checkBox = By.xpath(String.format("//div[@title='%s']/../preceding-sibling::td//input", recordName));
        if (elementHelper.isCheckBoxSelected(checkBox)) {
            elementHelper.doClick(checkBox);
        }
    }

    /**
     * Method to Select A Record Present:the Item List Page
     *
     * @param searchText     Text to search
     * @param columnToSearch Column name to search/filter in
     */
    public void selectCheckBoxOfRowByDataAndColumn(String searchText, String columnToSearch) {
        navigation.switchFrameToContent();
        var columnData = getColumnDataAsWebElementList(columnToSearch);
        for (var data : columnData) {
            if (data.getText().equals(searchText)) {
                elementHelper.doClick(data.findElement(By.xpath("./ancestor::tr[contains(@class,'Row')]//input[@type='checkbox']")));
                return;
            }
        }
    }

    /**
     * Deletes all item.
     *
     * @return True if all item is deleted else false.
     */
    public boolean deleteAllItems() {
        boolean isDeleted = false;
        boolean isFormPageEmpty;
        logger().info("Deleting all records");
        isFormPageEmpty = isItemListEmpty();
        if (!isFormPageEmpty) {
            selectAllItemRows();
            //delete
            clickRibbonIcon(RibbonIcons.Delete);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
            if (getNumberOfItems() == 0) {
                isDeleted = true;
            }
        }
        logger().info("All the records are deleted successfully");
        return isDeleted;
    }

    /**
     * Function to Click on the Checkbox next to the Item name
     *
     * @param itemIdentifier             Item Name
     * @param columnHeaderNameToSearchIn Column to search in
     */
    public void clickCheckboxInExpandedList(String itemIdentifier, String columnHeaderNameToSearchIn) {
        navigation.switchFrameToContent();
        int targetHeaderColumnNumber = getColumnNumberUsingHeaderName(columnHeaderNameToSearchIn);
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        for (var row : itemListPageRows) {
            List<WebElement> itemRowData = row.findElements(By.tagName("td"));
            String targetData = itemRowData.get(targetHeaderColumnNumber).getText();
            if (targetData.trim().equalsIgnoreCase(itemIdentifier)) {
                WebElement checkBox = itemRowData.get(targetHeaderColumnNumber - 1).findElement(By.tagName("input"));
                checkBox.click();
                break;
            }
        }
    }

    /**
     * Function to get the Row number of a specified item:the Items List
     *
     * @param itemIdentifier             Item that needs to be searched
     * @param columnHeaderNameToSearchIn Column in which the item needs to be searched
     * @return item row.
     */
    public int getRowNumberOfItem(String itemIdentifier, String columnHeaderNameToSearchIn) {
        navigation.switchFrameToContent();
        int itemRow = 0;
        int targetHeaderColumnNumber = getColumnNumberUsingHeaderName(columnHeaderNameToSearchIn);
        expandItemList();
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (int rowCounter = 0; rowCounter < itemListPageRows.size(); rowCounter++) {
            List<WebElement> itemRowData = itemListPageRows.get(rowCounter).findElements(By.tagName("td"));
            String targetData = itemRowData.get(targetHeaderColumnNumber).getText();
            if (targetData.trim().equalsIgnoreCase(itemIdentifier)) {
                itemRow = rowCounter;
                break;
            }
        }
        return itemRow;
    }

    /**
     * Returns True if Container doesn't contain any item. Else it returns false
     *
     * @param containerName Name of the container
     * @return True if no issue else false.
     */
    public boolean isContainerEmpty(String containerName) {
        boolean isEmpty = false;
        navigation.switchFrameToContent();
        int itemRow = getRowNumberOfItem(containerName, "Name");
        List<WebElement> rows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        String srcText = rows.get(itemRow).findElement(By.xpath(".//*[text() = '" + containerName + "']/ancestor::div[1]//img[1]")).getAttribute("src");

        if (srcText.contains("Expand_disabled")) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Function to get all the required item field values
     *
     * @param columnNames Can pass multiple column names
     * @return List page data.
     */
    public List<List<String>> getAllItemListData(String... columnNames) {
        navigation.switchFrameToContent();
        List<List<String>> listPageData = null;
        expandItemList();
        //Getting all the row elements of the table.
        List<WebElement> rows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (var row : rows) {
            List<String> data = null;
            //Adding all column data of a row to a data list
            for (String Column : columnNames) {
                data.add(row.findElement(By.xpath(".//td[" + getColumnNumberUsingHeaderName(Column) + "]")).getText().trim());
            }
            listPageData.add(data);
        }
        return listPageData;
    }

    /**
     * Function to get all data present on a itemList page with their corresponding column names
     *
     * @param includeContainers whether to include containers or not
     * @return List of data.
     */
    public List<Dictionary<String, String>> getItemDataWithHeaders(boolean includeContainers) {
        navigation.switchFrameToContent();
        List<Dictionary<String, String>> listPageData = null;
        List<String> columnNames = itemListPageHeaders();
        expandItemList();
        //Getting all the row elements of the table.
        List<WebElement> itemListPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (var row : itemListPageRows) {
            Dictionary<String, String> data = null;
            //Adding all column and column data of a row to a data dictionary
            for (String column : columnNames) {
                data.put(column, row.findElement(By.xpath(".//td[" + (getColumnNumberUsingHeaderName(column) + 1) + "]")).getText().trim());
            }
            // Add data dictionary to a listPageData
            if (includeContainers) {
                listPageData.add(data);
            } else {
                if (row.findElements(By.xpath(".//td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]//a")).size() == 0) {
                    listPageData.add(data);
                }
            }
        }
        return listPageData;
    }

    /**
     * Function to get container name
     *
     * @param itemName Can pass multiple column names
     * @return Container name.
     */
    public String getContainerName(String itemName) {
        String container = null;
        navigation.switchFrameToContent();

        expandItemList();
        //Getting all the row elements of the table.
        List<WebElement> rows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (int rowInt = 1; rowInt <= rows.size(); ) {
            int nextRowInt = rowInt;
            if ((driver.findElements(By.xpath("//tr[" + rowInt + "]/td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]//b/a")).size() != 0)) {
                container = driver.findElement(By.xpath("//tr[" + rowInt + "]/td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]//b/a")).getText().trim();
                if (container != "Default" && (driver.findElements(By.xpath("//tr[" + (rowInt + 1) + "]/td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]//b/a")).size() != 0)) {
                    if (driver.findElement(By.xpath("//tr[" + (rowInt + 1) + "]/td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]//b/a")).getText().equals("Default")) {
                        nextRowInt += 1;
                    }
                }
            }
            if ((driver.findElement(By.xpath("//tr[" + rowInt + "]/td[" + (getColumnNumberUsingHeaderName("Name") + 1) + "]")).getText().trim()).equals(itemName) && container != null) {
                return container;
            }
            nextRowInt++;
            rowInt = nextRowInt;
        }
        return container;
    }

    /**
     * Function to get all the projects under the program folder.
     *
     * @param programName Name of program.
     * @param columnNames Column names.
     * @return list page data.
     */
    public List<List<String>> getProjectsUnderProgramFolder(String programName, String... columnNames) {
        int progRowNum = -1;
        int numberOfProjects = -1;
        // Reading all the names and Data
        List<List<String>> listPageName = getAllItemListData("Name");
        List<List<String>> listPageData = getAllItemListData(columnNames);
        for (int i = 0; i < listPageName.size(); i++) {
            if (listPageName.get(i).get(0).equals(programName)) {
                progRowNum = i;
                break;
            }
        }
        //If program is absent then we are returning null
        if (progRowNum == -1) {
            //Utilities.Reports.LogFail("The program Name is not present on the LIst Page");
            return null;
        }

        // Noe getting number of projects
        String projectsFolder = listPageName.get(progRowNum + 1).get(0);
        String number = projectsFolder.substring(9, 1);
        numberOfProjects = Integer.parseInt(number);

        //Now removing everything except for the Projects
        for (int itr = 0; itr <= (progRowNum + 2); itr++) {
            listPageData = Collections.singletonList(listPageData.remove(itr));
        }
        int temp = listPageData.size();
        if (listPageData.size() > numberOfProjects) {
            for (int itr = numberOfProjects; itr <= (temp - numberOfProjects); itr++) {
                listPageData = Collections.singletonList(listPageData.remove(itr));
            }
        }
        return listPageData;
    }

    /**
     * Function to get data of all the containers
     *
     * @param columnHeaderName Name  of the column whose data is needed
     * @return List of container list data.
     */
    public List<String> getAllContainerListData(String columnHeaderName) {
        navigation.switchFrameToContent();
        expandItemList();
        var rowControl = elementHelper.getElements(By.xpath(".//*[@id='dataTable']/tbody/tr//a/../../../.."));
        List<String> list = new ArrayList<>();
        for (var row : rowControl) {
            list.add(row.findElement(By.xpath(".//td[" + (getColumnNumberUsingHeaderName(columnHeaderName) + 1) + "]")).getText().trim());
        }
        return list;
    }

    /**
     * Function to get the Details of the Item:the items list
     *
     * @param ItemRowNumber              Item Number:the Row
     * @param ColumnHeaderOfRequiredData Column Header:the List Page
     * @return Cell data.
     */
    public String getItemData(int ItemRowNumber, String ColumnHeaderOfRequiredData) {
        navigation.switchFrameToContent();
        List<WebElement> listPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        int TargetColumnNum = getColumnNumberUsingHeaderName(ColumnHeaderOfRequiredData);
        String cellData = listPageRows.get(ItemRowNumber).findElements(By.tagName("td")).get(TargetColumnNum).getText();
        return cellData;
    }

    /**
     * Function to Edit a Column Data
     *
     * @param headerName      column header name.
     * @param rowIdentifier   row cell value to identify row.
     * @param textToBeEntered text to be entered in the row.
     * @return True if no error occurs.
     */
    public boolean editItemData(String headerName, String rowIdentifier, String textToBeEntered) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageTabHeaderToBeClickable();
        By cellLocator = By.xpath("//div[text()='" + rowIdentifier + "']/../following-sibling::td");
        int columnNumber = getColumnNumberUsingHeaderName(headerName);
        List<WebElement> rowCells = elementHelper.getElements(cellLocator);

        var requiredCell = rowCells.get(columnNumber - 2);

        WebElement inputField = null;
        for (int i = 0; i < 3; i++) {
            waitHelper.waitForElementPresent(requiredCell);
            elementHelper.doDoubleClick(requiredCell);
            waitHelper.waitForPageToLoad();
            try {
                inputField = requiredCell.findElement(By.tagName("input"));
            } catch (Exception e) {
                continue;
            }
            if (inputField != null) {
                elementHelper.doDoubleClick(inputField);
                elementHelper.doSendKeysUsingAction(textToBeEntered);
                elementHelper.doSendKeys(Keys.TAB);
                break;
            }
        }

        saveSettings();
        getPage(ToastUtil.class).waitForToastDisappears();
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Get Pay item name.
     *
     * @param column1 First column.
     * @param column2 Second column.
     * @return Pay item name.
     */
    public String getPayItemName(String column1, String column2) {
        navigation.switchFrameToContent();
        int rowData = 0;
        int targetheaderColumnNumber1 = getColumnNumberUsingHeaderName(column1);
        int targetheaderColumnNumber2 = getColumnNumberUsingHeaderName(column2);
        List<WebElement> rows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        for (int rowCounter = 0; rowCounter < rows.size(); rowCounter++) {
            List<WebElement> itemRowData = rows.get(rowCounter).findElements(By.tagName("td"));
            String lineNumber = (itemRowData.get(targetheaderColumnNumber2).getText()).trim();
            if (!lineNumber.equals("")) {
                rowData = rowCounter;
                break;
            }
        }
        List<WebElement> itemRowData2 = rows.get(rowData).findElements(By.tagName("td"));
        return (itemRowData2.get(targetheaderColumnNumber1).getText()).trim();
    }

    /**
     * Function to Right Click on Item
     *
     * @param columnIdentifier     Column Identifier
     * @param columnNameToSearchIn Name of the column to search in
     */
    public void rightClickOnItemRow(String columnIdentifier, String columnNameToSearchIn) {
        navigation.switchFrameToContent();
        int itemRowNumber = getRowNumberOfItem(columnIdentifier, columnNameToSearchIn);
        List<WebElement> listPageRows = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        elementHelper.doContextClick(listPageRows.get(itemRowNumber)
                .findElements(By.tagName("td")).get(getColumnNumberUsingHeaderName(columnNameToSearchIn)));
    }

    /**
     * Method to get item row count in item list page
     *
     * @return item row count
     */
    public int getItemRowCount() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.xpath(".//tr[contains(@class,'hrRow itemRow')]"));
        return itemListPageRows.size();
    }

    /**
     * Gets Total Amount in the item list.
     *
     * @return Total amount value.
     */
    public float getTotalAmount() {
        return getAmount(totalAmount);
    }

    /**
     * Gets Inflated Total Amount in the item list.
     *
     * @return Inflated Total amount value.
     */
    public float getInflatedTotalAmount() {
        return getAmount(inflatedTotalAmount);
    }

    /**
     * Method to fetch the amount of an item
     *
     * @param rowIdentifier               row identifier string
     * @param columnHeaderOfROwIdentifier column name of the row identifier
     * @return amount value of the item
     */
    public float getItemAmount(String rowIdentifier, ContractItemPageColumn columnHeaderOfROwIdentifier) {
        var amount = getItemData(rowIdentifier, columnHeaderOfROwIdentifier.getValue(), "Amount in $");
        return Float.parseFloat(amount.replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""));
    }

    /**
     * Function to get ColumnNumber for Header specified in Bidder Items Page
     *
     * @param headerName - Whose Column Number is required
     * @return - Column Number
     */
    public int getColumnNumberForBidderItems(String headerName) {
        List<WebElement> headerTextElements = elementHelper.getElements(bidderItemHeaders);
        for (int i = 0; i < headerTextElements.size(); i++) {
            String text = headerTextElements.get(i).getText();
            if (text.equals(headerName)) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * Double Click cell
     *
     * @param textToBeEntered text to be entered
     * @param cellLocator     cell locator of the cell to be entered
     */
    public void editPrice(String textToBeEntered, By cellLocator) {
        WebElement cell = elementHelper.getElement(cellLocator);
        elementHelper.doDoubleClick(cell);
        elementHelper.doSendKeysUsingAction(textToBeEntered);
    }

    /**
     * Function to Edit a Bidder item Data
     *
     * @param headerName      Key item Header for which Data needs to be edited
     * @param rowIdentifier   ItemName whose data is to be Edited
     * @param textToBeEntered The text to be entered
     */
    public void editBidderItemData(String headerName, String rowIdentifier, String textToBeEntered) {
        By cellLocator = By.xpath("//td/*[contains(text(),'" + rowIdentifier + "')]/../following-sibling::td[contains(@style,'#F8E99B')]");
        editPrice(textToBeEntered, cellLocator);
        elementHelper.doSendKeys(Keys.TAB);
    }

    /**
     * Function to Edit a Bidder item Data
     *
     * @param rowIdentifier   ItemName whose data is to be Edited
     * @param textToBeEntered The text to be entered
     */

    public void editBidderItemUnitPrice(String rowIdentifier, String textToBeEntered) {
        By cellLocator = By.xpath(String.format(contractorFLBidItemsTemplate, rowIdentifier));
        editPrice(textToBeEntered, cellLocator);
    }

    /**
     * This Method returns the cell data from Bidder Item Table
     *
     * @param rowIdentifier - Item Name
     * @param headerName    - ColumnHeader whose Value is needed
     * @return The cellData as String
     */

    public String getCellDataForBidderItemsTable(String rowIdentifier, String headerName) {
        waitHelper.waitForPageToLoad();
        By rowLocator = By.xpath("//td/div[contains(text(),'" + rowIdentifier + "')]/../parent::tr/td/div");
        int columnNumber = getColumnNumberForBidderItems(headerName);
        return elementHelper.getElements(rowLocator).get(columnNumber).getText();

    }

    /**
     * Get the required data of a Sub Item
     *
     * @param rowIdentifier               row identifier string
     * @param columnOfSpecifiedIdentifier column name of the specified row identifier
     * @param ColumnHeaderOfRequiredData  Column Header in the List Page
     * @return item data.
     */
    public String getItemData(String rowIdentifier, String columnOfSpecifiedIdentifier, String ColumnHeaderOfRequiredData) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        int itemRow = getRowNumberOfItem(rowIdentifier, columnOfSpecifiedIdentifier);
        int dataColumn = getColumnNumberUsingHeaderName(ColumnHeaderOfRequiredData);
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> itemListPageRows = itemListPageDataTable.findElements(By.tagName("tr"));
        String itemData = elementHelper.doGetText(itemListPageRows.get(itemRow).findElements(By.tagName("td")).get(dataColumn));
        if (itemData.isEmpty()) {
            List<WebElement> elements = itemListPageRows.get(itemRow).findElements(By.tagName("td")).get(dataColumn + 1).findElements(By.xpath(".//input"));
            for (var itr : elements) {
                if (itr.isDisplayed()) {
                    return itr.getText();
                }
            }
        }
        return itemData;
    }

    /**
     * Function to get ColumnNumber for specified header in a table
     *
     * @param headerName column header name
     * @param tableXpath xpath of the table
     * @return - Column Number
     */
    protected int getColumnNumberInTable(String headerName, By tableXpath) {
        logger().info(String.format("Get the column number of the column - %s", headerName));
        var headerXpath = By.xpath(String.format("%s/thead/tr/th", elementHelper.getLocatorAsString(tableXpath)));
        List<WebElement> headerTextElements = elementHelper.getElements(headerXpath);
        for (int i = 0; i < headerTextElements.size(); i++) {
            String text = headerTextElements.get(i).getText();
            if (text.equals(headerName)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Get xpath of the required element under the specified column in a table
     *
     * @param rowNumber  row number of the element
     * @param headerName column header name
     * @param tableXpath xpath of the table
     * @return xpath of the element
     */
    protected By getXpathOfElementInTable(int rowNumber, String headerName, By tableXpath) {
        logger().info(String.format("Get xpath of the element in row - %s and under column - %s", rowNumber, headerName));
        int columnNumber = getColumnNumberInTable(headerName, tableXpath);
        var xpathString = String.format("%s/tbody/tr[%s]/td[%s]", elementHelper.getLocatorAsString(tableXpath), rowNumber, columnNumber);
        return By.xpath(xpathString);
    }

    /**
     * Get Total amount of desired element passed.
     *
     * @param locator Element locator.
     * @return Floating value of total amount.
     */
    private float getAmount(By locator) {
        waitHelper.waitForPageTabHeaderToBeClickable();
        waitHelper.waitForElementPresent(locator);
        String text = elementHelper.doGetText(locator).trim();
        getPage(ScreenshotHelper.class).takeElementScreenshot(locator, "Total Amount");
        return Float.parseFloat(text.replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""));
    }

    /**
     * Import a file
     *
     * @param fileName - file to be uploaded
     * @return - true if error log button is not displayed
     */
    public boolean importFile(String fileName) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(chooseFile);
        File file = FileHelper.getFile(fileName, null);
        waitHelper.waitForPageToLoad();
        elementHelper.getElement(chooseFile).sendKeys(file.getAbsolutePath());
        waitHelper.waitForPageToLoad(uploadButton);
        elementHelper.doClick(uploadButton);
        waitHelper.waitForPageToLoad();
        if (elementHelper.isElementEnabled(saveButtonInImport)) {
            waitHelper.waitForPageToLoad(saveButtonInImport);
            elementHelper.doClick(saveButtonInImport);
            waitHelper.waitForPageToLoad();
        }
        return !validateRibbonIcon(RibbonIcons.ExcelErrorLog);
    }

    /**
     * Validate adding a Sub Item to an Item
     *
     * @param indexOfSubItemPerItem index of the Sub Item created
     * @param subItemDescription    description of the Sub Item
     * @return true on validation of the above
     */
    public boolean addSubItemToAnItem(int indexOfSubItemPerItem, String subItemDescription) {
        logger().info("Adding Sub item to an item");
        elementHelper.scrollToView(subItemsLinkButton);
        elementHelper.doClick(subItemsLinkButton);
        clickRibbonIcon(RibbonIcons.New);
        var descriptionXpath = getXpathOfElementInTable(indexOfSubItemPerItem, SubItemTableColumns.Description.getValue(), subItemTableXpath);
        var descriptionField = By.xpath(elementHelper.getLocatorAsString(descriptionXpath) + "/input");
        elementHelper.doDoubleClick(descriptionXpath);
        waitHelper.waitForElementPresent(descriptionField);
        elementHelper.doSendKeys(descriptionField, subItemDescription);
        saveEditingSubItemAndNavigateToItemPage();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Update the quantity of sub item and validate the total amount of Sub Items displayed
     *
     * @param quantity  quantity of the Sub Item
     * @param unitPrice unit price of the item
     * @param validate  - is validation required
     * @return true on validation of the above
     */
    public boolean updateQtyInSubItemAndValidate(double quantity, double unitPrice, boolean validate) {
        var subItemValue = (unitPrice * quantity);
        var qtyString = String.valueOf(quantity);
        elementHelper.doClick(subItemsLinkButton);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
        var qtyField = getXpathOfElementInTable(1, ContractItemPageColumn.Quantity.getValue(), subItemTableXpath);
        var qtyInputField = By.xpath(String.format(elementHelper.getLocatorAsString(qtyField), input));
        elementHelper.doDoubleClick(qtyField);
        waitHelper.waitForElementPresent(qtyInputField);
        elementHelper.doSendKeysUsingAction(qtyInputField, qtyString);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
        if (validate) {
            var totalField = getXpathOfElementInTable(1, ContractItemPageColumn.AmountIn$.getValue(), subItemTableXpath);
            var totalValueEntered = Double.parseDouble((elementHelper.getElement(totalField).getText()).replaceAll(RegexStrings.notDigitOrDot.getValue(), ""));
            return subItemValue == totalValueEntered;
        } else {
            return true;
        }
    }

    /**
     * Save editing the Sub Item and navigate back to Item page
     */
    public void saveEditingSubItemAndNavigateToItemPage() {
        clickRibbonIcon(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Back);
    }

    /**
     * Filter Item Details Page
     *
     * @param columnName   - name of the column
     * @param searchText   - the text to be searched
     * @param options      - filter option
     * @param itemsDetails If true, use filter option.
     */
    public void filterItemDetailsInForecast(String columnName, String searchText, ListPageFilterOptions options, boolean itemsDetails) {
        String clearAllFilter;
        String filterButton;
        String inputBox;
        String filterOptions;
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        List<String> headersList = itemListPageHeaders();
        int rowCount = 2;
        for (String s : headersList) {
            if (columnName.equals(s)) {
                break;
            } else {
                rowCount++;
            }
        }
        filterButton = String.format(filterButtonInItemDetailsForecast, rowCount);
        inputBox = String.format(inputTextBoxInItemDetailsForecast, rowCount);
        if (itemsDetails) {
            clearAllFilter = String.format(filterOptionsInItemDetails, ListPageFilterOptions.ClearAllFilter.getValue());
            filterOptions = String.format(filterOptionsInItemDetails, options.getValue());
        } else {
            clearAllFilter = String.format(filterOptionsInItemDetailsForecast, ListPageFilterOptions.ClearAllFilter.getValue());
            filterOptions = String.format(filterOptionsInItemDetailsForecast, options.getValue());
        }
        // Clearing all filter
        waitHelper.waitForElementPresent(By.xpath(filterButton));
        elementHelper.doClick(By.xpath(filterButton));
        waitHelper.waitForElementClickable(By.xpath(clearAllFilter));
        elementHelper.doClick(By.xpath(clearAllFilter));
        waitHelper.waitForPageToLoad();

        // Filter Text
        waitHelper.waitForElementPresent(By.xpath(inputBox));
        elementHelper.doSendKeys(By.xpath(inputBox), searchText);

        waitHelper.waitForElementPresent(By.xpath(filterButton));
        elementHelper.doClick(By.xpath(filterButton));

        waitHelper.waitForElementPresent(By.xpath(filterOptions));
        elementHelper.doClick(By.xpath(filterOptions));

        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to create a Container with Parent container Name
     *
     * @param containerName       - Name of Container to create
     * @param parentContainerName - Parent container name
     */
    public void createNewContainer(String containerName, String parentContainerName) {
        waitHelper.waitForPageToLoad();
        click(ItemMenuBar.NewContainer);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(newContainerNameTextbox, containerName);
        elementHelper.doClick(By.xpath(String.format(parentContainer, parentContainerName)));
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to create a Container with Parent container Name
     *
     * @param containerName       - Name of Container to create
     * @param parentContainerName - Parent container name
     */
    public void createNewContainerRev(String containerName, String parentContainerName) {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.AddNewWithModifyOption);
        waitHelper.waitForElementPresent(getRibbonIcon(RibbonIcons.NewContainer));
        elementHelper.doClickUsingActions(getRibbonIcon(RibbonIcons.NewContainer));
        waitHelper.waitForPageToLoad(newContainerNameTextbox);
        elementHelper.doSendKeys(newContainerNameTextbox, containerName);
        elementHelper.doClick(By.xpath(String.format(parentContainer, parentContainerName)));
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Edit Container Name
     *
     * @param newContainerName new container name
     */
    public void editContainerName(String newContainerName) {
        elementHelper.doSendKeys(newContainerNameTextbox, newContainerName);
    }

    /**
     * Method to check expanded button status
     *
     * @return True if expanded, else false.
     */
    public boolean checkExpandedButtonState() {
        String isExpandedStatus = null;
        List<WebElement> elementsToExpand = elementHelper.getElements(By.xpath(".//*[@id='dataTable']//tr[@class='hrRow']"));
        if (elementsToExpand.size() == 0) {
            elementsToExpand = elementHelper.getElements(By.xpath(".//*[@id='dataTable']//tr[@class='hrRow'][1]"));
        }

        //Getting the Status of the Expand Button in the List Page
        if (elementsToExpand.size() > 0) {
            isExpandedStatus = elementsToExpand.get(0).getAttribute("isexpanded");
        }
        return isExpandedStatus.equals("true");
    }

    /**
     * Validate record in list page
     *
     * @param parameter parameter to search
     * @return true on validation of the above
     */
    public boolean validateRecordInListPage(String parameter) {
        var xpath = By.xpath(String.format(recordIdentifierFormat, parameter));
        var elements = elementHelper.getElements(xpath);
        if (elements == null || elements.isEmpty()) {
            return false;
        } else {
            return elements.size() == 1;
        }
    }

    /**
     * Click on add new item in Budget revision items page
     */
    public void clickAddSingleItemsInBER() {
        clickRibbonIcon(RibbonIcons.AddNewWithModifyOption);
        waitHelper.waitForPageToLoad(addItem);
        elementHelper.doClick(addItem);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to view a PayItem
     *
     * @param payItem - The Payitem to be viewed
     */
    public void viewItem(String payItem) {
        clickCheckbox(payItem, "Name");
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Validate fund rule is available in Budget estimate items
     *
     * @param fundRuleName Name of the fund rule to check
     * @return True if the fund rule is available
     */
    public boolean validateFundRuleAvailabilityInItemListPage(String fundRuleName) {
        clickRibbonIcon(RibbonIcons.FundRuleAssociationInListPage);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        By locator = By.xpath(String.format("//a[@id]//span[contains(text(),'%s')]", fundRuleName));
        return elementHelper.isElementDisplayed(locator);
    }

    /**
     * Validate fund rule is available
     *
     * @param fundRuleName Name of the fund rule to verify
     * @return True if the fund rule is available
     */
    public boolean verifyFundRuleInDetailsPage(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        return elementHelper.getComboBoxSelectOptions(fundRuleField).stream().anyMatch(x -> x.getText().equals(fundRuleName));
    }

    /**
     * Validate the fund rule changes to hyperlink
     *
     * @param fundRuleName Name of the fund rule to check
     * @return Return true if fund rule is hyper linked
     */
    public boolean verifyFundHyperLink(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        By locator = By.xpath(String.format(funRuleAssociatedXpath, fundRuleName));
        return !elementHelper.doGetAttribute(locator, "href").isEmpty();
    }

    /**
     * Click on fund rule hyper link
     *
     * @param fundRuleName Name of the fund rule
     */
    public void clickOnFundRuleHyperlink(String fundRuleName) {
        waitHelper.waitForPageToLoad();
        By locator = By.xpath(String.format(funRuleAssociatedXpath, fundRuleName));
        elementHelper.doClick(locator);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate association pop up available with fund rule history
     *
     * @param fundRuleName Name of the fund rule
     * @return True if fund rule history is available
     */
    public boolean validateAssociationHistoryPopUp(String fundRuleName) {
        waitHelper.waitForPageToLoad(fundRuleAssociationHistory);
        boolean isAvailable = elementHelper.getElement(fundRuleAssociationHistory).findElements(By.xpath(String.format(".//*[text()='%s']", fundRuleName))).size() > 0;
        elementHelper.doClick(fundRuleAssociationHistoryCancelButton);
        waitHelper.waitForPageToLoad();
        return isAvailable;
    }

    /**
     * Validate association of columns in history in fund rule hyper link
     *
     * @return True if all the columns are present
     */
    public boolean validateAssociationHistoryColumns() {
        waitHelper.waitForPageToLoad();
        List<String> headers = new ArrayList<>();
        elementHelper.getElement(fundRuleAssociationHistory).findElements(By.xpath(".//th")).stream().forEach(x -> headers.add(x.getText()));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(fundRuleAssociationHistoryCancelButton);
        waitHelper.waitForPageToLoad();
        return headers.containsAll(ItemAssociationHistoryColumns.getList());
    }

    /**
     * Associate fund rule by right-clicking
     *
     * @param itemName     Name of the item
     * @param fundRuleName Name of the fund to select
     */
    public void associateFundByContextClicking(String itemName, String fundRuleName) {
        waitHelper.waitForPageToLoad();
        expandItemList();
        List<WebElement> elementsToExpand = elementHelper.getElement(dataTable).findElements(By.tagName("tr"));
        List<WebElement> cellToExpand = new ArrayList<>();
        if (elementsToExpand.get(0).findElements(By.xpath(".//a[@title='Default']")).size() > 0) {
            cellToExpand = elementsToExpand.get(1).findElements(By.xpath(String.format(".//div[@title='%s']", itemName)));
        } else {
            cellToExpand = elementsToExpand.get(0).findElements(By.xpath(String.format(".//div[@title='%s']", itemName)));
        }
        elementHelper.doContextClick(cellToExpand.get(0));
        elementHelper.doClick(By.xpath(String.format(contextClickOption, fundRuleName)));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click On Copy
     */
    public void clickOnCopy() {
        navigation.switchFrameToContent();
        clickRibbonIcon(RibbonIcons.CopyItemsUnderAction);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Click On Paste
     *
     * @param alert to select alert or not
     */
    public void clickOnPaste(boolean alert) {
        clickRibbonIcon(RibbonIcons.PasteItemsUnderAction);
        if (alert) {
            getPage(AlertHandler.class).acceptAlert(true);
        }
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select Container
     *
     * @param containerName Name of the container
     * @param expandList    true to expand The item list
     */
    public void selectContainer(String containerName, boolean expandList) {
        if (expandList) {
            expandItemList();
        }
        By container = By.xpath(String.format("//a[text()='%s']/../../../..//td//input", containerName));
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(container);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Edit Item Description
     *
     * @param newDescription New Description
     */
    public void editItemDescription(String newDescription) {
        waitHelper.waitForPageToLoad();
        elementHelper.getElement(itemDescriptionTextArea).clear();
        elementHelper.doSendKeysUsingAction(itemDescriptionTextArea, newDescription);
    }

    /**
     * Get number of rows in item list page
     *
     * @return The number of rows
     */
    public int getTotalRowCount() {
        navigation.switchFrameToContent();
        WebElement itemListPageDataTable = elementHelper.getElement(dataTable);
        List<WebElement> pageRows = itemListPageDataTable.findElements(By.xpath(".//tr"));
        return pageRows.size();
    }

    /**
     * Method for deleting existing Budget Estimate revision Items
     *
     * @param payItemNumber item number of the item to be modified
     */
    protected void deleteExistingItem(String payItemNumber) {
        clickRibbonIcon(RibbonIcons.DeleteExistingItem);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        elementHelper.doClick(showPayItemPickerBtn);
        getPage(Picker.class).singleSelectByText("Pay Item No.", payItemNumber, standardItemsPicker);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(showPayItemPickerBtn);
        clickRibbonIcon(RibbonIcons.Delete);
        waitHelper.waitForPageToLoad();
    }

    /**
     * This method help to edit the Item cell using header,identifier
     *
     * @param headerName    the field which need to edit
     * @param rowIdentifier row cell value to identify row
     * @param text          to send the text
     */
    public void editCellItemData(String headerName, String rowIdentifier, String text) {
        waitHelper.waitForPageTabHeaderToBeClickable();
        By cellLocator = By.xpath("//div[text()='" + rowIdentifier + "']/../following-sibling::td");
        int columnNumber = getColumnNumberUsingHeaderName(headerName);
        List<WebElement> rowCells = elementHelper.getElements(cellLocator);
        var requiredCell = rowCells.get(columnNumber - 2);
        waitHelper.waitForElementPresent(requiredCell);
        elementHelper.doDoubleClick(requiredCell);
        waitHelper.waitForPageToLoad();
        WebElement inputField = requiredCell.findElement(By.tagName("input"));
        if (inputField != null) {
            elementHelper.doDoubleClick(inputField);
            inputField.clear();
            inputField.sendKeys(text);
            elementHelper.doSendKeys(Keys.TAB);
        }

        saveSettings();
        getPage(ToastUtil.class).waitForToastDisappears();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method is used to get total amount using quantity and unit price
     *
     * @param quantity         quantity
     * @param unitPrice        unit price
     * @param decimalFormatter decimal formatter
     * @return total amount with decimal format
     */
    public double getTotalAmount(String quantity, String unitPrice, String decimalFormatter) {
        double quantityValue = quantity == null ? 0.0 : Double.parseDouble(quantity.replace(",", ""));
        double unitPriceValueValue = unitPrice == null ? 0.0 : Double.parseDouble(unitPrice.replace(",", ""));
        DecimalFormat dfFormatter = new DecimalFormat(decimalFormatter);
        double expectedTotalAmount = quantityValue * unitPriceValueValue;
        return Double.parseDouble(dfFormatter.format(expectedTotalAmount));
    }

    /**
     * Creates a new standard item with specified pay item number for a standard item table
     *
     * @param standardItemsTableName is the name of the standard item table
     * @param payItemNumber          is the required pay item number for new standard item
     */
    public void newStandardItem(String standardItemsTableName, Double payItemNumber) {
        getPage(StandardItemsPage.class).clickOnStandardItemsInRibbonMenu(standardItemsTableName);
        clickRibbonIcon(RibbonIcons.NewItem);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(payItemNumberTxtBox, payItemNumber);
        elementHelper.doSendKeys(itemDescriptionTextArea, "Created via automation");
        elementHelper.selectComboBoxItemByIndex(itemUnitComboBox, 1);
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).clickSave();
        waitHelper.waitForPageToLoad();
    }
}
