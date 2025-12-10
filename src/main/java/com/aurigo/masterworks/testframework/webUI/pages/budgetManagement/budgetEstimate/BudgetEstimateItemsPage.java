package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BudgetEstimateItemColumnsList;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BudgetEstimateItemsExcelColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ForecastListPageColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ItemMenuBar;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields.ActionsOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields.NewOptions;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import com.aurigo.masterworks.testframework.webUI.testData.PayItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BudgetEstimateItemsPage extends ItemListPage {

    private By budgetEstimateItemsTab;
    private By budgetItemsTotal;
    private By inflationRule;
    private By annualInflationRate;
    private By effectiveFrom;
    private By newButtonInBudgetItemsPage;
    private By newButtonFromDropdown;
    private By selectFirstBudgetItem;
    private By inflationRulePicker;
    private By selectButton;
    private By annualInflationRateTextBox;
    private By budgetItemCheckbox;
    private By dragBudgetItemSourceFrom;
    private By dragBudgetItemDestinationTo;
    private By containerNameTextBox;
    private By pickerPathForInflationRule;
    private By getPayItemNo;
    private By getPayItemAmount;
    private By inputTextBoxFields;
    private By itemDetailsTableDivID;
    private By addWorkProjectsPicker;
    private By excelImportErrorLogButton;
    private By excelUploadErrorMessage;
    private By subItemsLinkButton;
    private By subItemsPageTitle;
    private By selectContainerInNewItemsPage;
    private By getContainerFieldValue;
    private By effectiveFromDateBEItem;
    private By inflationRate;
    private By effectiveFromInformationIcon;
    private By applyInflationRulePicker;
    private By applyInflationRuleButton;
    private By inflationPicker;
    private By saveInflation;
    private By checkAllItems;
    private By fundRuleField;
    public By selectFirstItem;
    private By payItemNumberTxtBox;
    private By itemDescriptionTextArea;
    private By itemUnitComboBox;
    private By itemQuantityTextBox;
    private By itemUnitCostTextBox;
    private By parentContainerLabel;
    private By selectResourceCategory;
    private By resourcePickerButton;
    private By resourcePicker;
    private By resourcePickerId;
    private By inflationHighlightSubItemInListPage;
    private By forecastHeader;

    private WebDriver driver;

    private String displayedLabels = ".//span[contains(text(),'%s')]";
    private String displayedLabels1 = ".//td[contains(text(),'%s')]";
    private String mandatoryLabel = ".//span[contains(text(),'%s')]/following-sibling::span[contains(text(),'*')]";
    private String searchContainerInFlatList = ".//*[contains(text(),'%s')]";

    public BudgetEstimateItemsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        var locators = LocatorUtil.getLocators("BudgetEstimateItemsPage.json");
        budgetEstimateItemsTab = locators.get("budgetEstimateItemsTab");
        budgetItemsTotal = locators.get("budgetItemsTotal");
        inflationRule = locators.get("inflationRule");
        annualInflationRate = locators.get("annualInflationRate");
        effectiveFrom = locators.get("effectiveFrom");
        newButtonInBudgetItemsPage = locators.get("newButtonInBudgetItemsPage");
        newButtonFromDropdown = locators.get("newButtonFromDropdown");
        selectFirstBudgetItem = locators.get("selectFirstBudgetItem");
        inflationRulePicker = locators.get("inflationRulePicker");
        selectButton = locators.get("selectButton");
        annualInflationRateTextBox = locators.get("annualInflationRateTextBox");
        budgetItemCheckbox = locators.get("budgetItemCheckbox");
        dragBudgetItemSourceFrom = locators.get("dragBudgetItemSourceFrom");
        dragBudgetItemDestinationTo = locators.get("dragBudgetItemDestinationTo");
        containerNameTextBox = locators.get("containerNameTextBox");
        pickerPathForInflationRule = locators.get("pickerPathForInflationRule");
        getPayItemNo = locators.get("getPayItemNo");
        getPayItemAmount = locators.get("getPayItemAmount");
        inputTextBoxFields = locators.get("inputTextBoxFields");
        itemDetailsTableDivID = locators.get("itemDetailsTableDivID");
        addWorkProjectsPicker = locators.get("addWorkProjectsPicker");
        excelImportErrorLogButton=locators.get("excelImportErrorLogButton");
        excelUploadErrorMessage=locators.get("excelUploadErrorMessage");
        subItemsLinkButton = locators.get("subItemsLinkButton");
        subItemsPageTitle = locators.get("subItemsPageTitle");
        selectContainerInNewItemsPage = locators.get("selectContainerInNewItemsPage");
        getContainerFieldValue = locators.get("getContainerFieldValue");
        effectiveFromDateBEItem = locators.get("effectiveFromDateBEItem");
        inflationRate = locators.get("inflationRate");
        effectiveFromInformationIcon = locators.get("effectiveFromInformationIcon");
        applyInflationRulePicker = locators.get("applyInflationRulePicker");
        applyInflationRuleButton = locators.get("applyInflationRuleButton");
        inflationPicker = locators.get("inflationPicker");
        saveInflation = locators.get("saveInflation");
        checkAllItems = locators.get("checkAllItems");
        fundRuleField = locators.get("fundRuleField");
        selectFirstItem = locators.get("selectFirstItem");
        payItemNumberTxtBox = locators.get("payItemNumberTxtBox");
        itemDescriptionTextArea = locators.get("itemDescriptionTextArea");
        itemUnitComboBox = locators.get("itemUnitComboBox");
        itemQuantityTextBox = locators.get("itemQuantityTextBox");
        itemUnitCostTextBox = locators.get("itemUnitCostTextBox");
        parentContainerLabel = locators.get("parentContainerLabel");
        selectResourceCategory = locators.get("selectResourceCategory");
        resourcePickerButton = locators.get("resourcePickerButton");
        resourcePicker = locators.get("resourcePicker");
        resourcePickerId = locators.get("resourcePickerId");
        inflationHighlightSubItemInListPage = locators.get("inflationHighlightSubItemInListPage");
        forecastHeader = locators.get("forecastHeader");
    }

    /**
     * Navigate to Budget Forecast items tab
     */
    public void navigateToForecastTab() {
        logger().info("Clicking on Budget Estimate Forecast Tab");
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(forecastHeader);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Clicking on New Item
     */
    public void clickNew()
    {
        waitHelper.waitForPageToLoad();
        click(ItemMenuBar.NewItem);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to create new Budget Estimate Items
     *
     * @param payItems list of item data
     * @return true if all the items are created
     */
    public boolean createBudgetEstimateItems(List<PayItem> payItems) {
        logger().info("Creating " + payItems.size() + " BE Items");
        var currentItemRowCount = getItemRowCount();
        var finalCount = currentItemRowCount + payItems.size();
        waitHelper.waitForPageToLoad();
        clickAddItemButton();
        addItem(payItems);
        waitHelper.waitForPageTabHeaderToBeClickable();
        expandItemList();
        return getItemRowCount() == finalCount;
    }

    /**
     * Click on Add Item button in Budget Item list page
     */
    public void clickAddItemButton(){
        navigation.switchFrameToContent();
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
    }

    /**
     * Updating Container of an Item
     * @param payItemNumber - the pay item number whose container is to be updated
     * @param newContainerName - the container under which the item will be placed
     * @return - true if container is not empty
     */
    public boolean updateContainerOfAnItem(String payItemNumber, String newContainerName)
    {
        logger().info("Updating Container of an Item");
        waitHelper.waitForPageToLoad();
        //Select Item from Item List Page
        clickCheckboxInExpandedList(payItemNumber, "Name");
        //Click on Edit button:the Ribbon
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        waitHelper.waitForElementClickable(selectContainerInNewItemsPage);
        elementHelper.doClick(selectContainerInNewItemsPage);
        waitHelper.waitForPageToLoad();
        String containerLocator = "//a[text()='%s']";
        waitHelper.waitForElementClickable(By.xpath(String.format(containerLocator,newContainerName)));
        elementHelper.doClick(By.xpath(String.format(containerLocator,newContainerName)));
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        return !isContainerEmpty(newContainerName);

    }

    /**
     * To check if sub item link is present
     * @return - true if sub item link is present
     */
    public boolean isSubItemLinkPresent()
    {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(subItemsLinkButton);
    }

    /**
     * Validate Sub Items link functionality on new items page
     * @param payItems - pay item mandatory values
     * @return - true if landing page is Sub Items page
     */
    public boolean validateSubItemsFeatureInItemsPage(List<PayItem> payItems)
    {
        logger().info("validate Sub Items Feature In Items Page");
        var currentItemRowCount = getItemRowCount();
        var finalCount = currentItemRowCount + payItems.size();
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        addMandatoryFieldsInItem(payItems);
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(subItemsLinkButton);
        elementHelper.doClick(subItemsLinkButton);
        boolean popUpDisplayed = getPage(AlertHandler.class).isAlertPresent(true);
        if(popUpDisplayed)
        {
            getPage(AlertHandler.class).acceptAlert(true);
        }
        waitHelper.waitForPageToLoad();
        waitHelper.waitUntilElementAppears(getRibbonIcon(RibbonIcons.New));
        return elementHelper.isElementDisplayed(subItemsPageTitle);
    }

    /**
     * Validate clear functionality in budget estimate items list page
     *
     * @return - true if clear functionality works
     */
    public boolean validateClearFunctionalityInNewItemsPage() {
        click(ItemMenuBar.NewItem);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        return validateClearFunctionality();
    }

    /**
     * Method to create new Budget Estimate Items from standard items
     *
     * @param noOfItems - The number of budget items to be created
     * @param container - The container in which budget items needs to be created
     * @return true if all the items are created
     */
    public boolean createBudgetEstimateItemsFromStandardItems(int noOfItems, String container) {
        logger().info("Creating " + noOfItems + " BE Items, Container name:" + container);
        expandItemList();
        var currentItemRowCount = getItemRowCount();
        var finalCount = currentItemRowCount + noOfItems;
        addStandardItems(container, noOfItems);
        expandItemList();
        return getItemRowCount() == finalCount;
    }

    /**
     * Gets total revenue displayed for Budget Items
     *
     * @return - returns the total revenue
     */
    public double getTotalRevenueFromBudgetItems() {
        waitHelper.waitForPageToLoad();
        String totalRevenueExtract = elementHelper.getElement(budgetItemsTotal).getText();
        String[] allRevenue = totalRevenueExtract.split("\\$");
        String regex = "[^0-9.]";
        allRevenue[1] = allRevenue[1].replaceAll(regex, "");
        return Double.parseDouble(allRevenue[1]);
    }

    /**
     * Verify Inflation related columns availability in Budget Items New and Edit page
     *
     * @return - returns true if the inflation columns are displayed in New and Edit page, false otherwise
     */
    public boolean checkInflationColumnsInNewAndEdit() {

        //Check in new budget estimate items
        elementHelper.doClick(newButtonInBudgetItemsPage);
        elementHelper.doClick(newButtonFromDropdown);
        waitHelper.waitForPageToLoad();
        boolean isInflationDisplayedInNew = (elementHelper.isElementDisplayed(inflationRule) && elementHelper.isElementDisplayed(annualInflationRate) && elementHelper.isElementDisplayed(effectiveFrom));
        clickRibbonIcon(RibbonIcons.Cancel);

        //Check in edit budget estimate items
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectFirstBudgetItem);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        boolean isInflationDisplayedInEdit = (elementHelper.isElementDisplayed(inflationRule) && elementHelper.isElementDisplayed(annualInflationRate) && elementHelper.isElementDisplayed(effectiveFrom));
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();

        return (isInflationDisplayedInNew && isInflationDisplayedInEdit);
    }

    /**
     * Edit and Validate Budget Estimate Item
     * @param payItemNumber - pay item number to be edited
     * @param newItemNumber - new item number to be updated
     * @return - true if the budget estimate item is updated
     */
    public boolean editAndValidateBudgetEstimateItems(String payItemNumber,String newItemNumber)
    {
        logger().info("Editing and Validating Budget Estimate Items");
        waitHelper.waitForPageToLoad();
        editPayItemNumber(payItemNumber,newItemNumber);
        waitHelper.waitForPageToLoad();
        return isItemPresentInList(newItemNumber,BudgetEstimateItemColumnsList.Name.getValue());
    }

    /**
     * verify Inflation Rate Field When Inflation Rule Is Selected
     *
     * @param inflationRuleName - name of the inflation rule
     * @return - returns true when annual inflation rate text box is not enabled, false otherwise
     */
    public boolean verifyInflationRateFieldWhenInflationRuleIsSelected(String inflationRuleName) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(budgetItemCheckbox);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(inflationRulePicker);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        getPage(Picker.class).singleSelectByText("Name", inflationRuleName, pickerPathForInflationRule);
        waitHelper.waitForPageToLoad();
        boolean inflationRateBoxVerification = elementHelper.getElement(annualInflationRateTextBox) != null && elementHelper.isElementDisplayed(annualInflationRateTextBox);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();

        return inflationRateBoxVerification;
    }

    /**
     * verify Inflation Rate Field When Inflation Rule Is Not Selected
     *
     * @return - returns true when annual inflation rate text box is not enabled and value displayed is 0.00, false otherwise
     */
    public boolean verifyInflationRateFieldWhenInflationRuleIsNotSelected() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(budgetItemCheckbox);
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        String inflationValueDisplayed = elementHelper.doGetAttribute(annualInflationRateTextBox,"value");
        boolean inflationRateBoxVerification = inflationValueDisplayed.equals("0.00") && elementHelper.isElementDisplayed(annualInflationRateTextBox);
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();

        return inflationRateBoxVerification;
    }

    /**
     * Verify the column headers in Budget Estimate Item List Page
     *
     * @param columnHeaders - column header name to be searched
     * @return - returns true if the header is visible, false otherwise
     */
    public boolean verifyColumnHeadersInItemListPage(String[] columnHeaders) {
        waitHelper.waitForPageToLoad();
        int columnHeaderAvailability = 1;
        for (String headers : columnHeaders) {
            columnHeaderAvailability = getColumnNumberUsingHeaderName(headers);
            if (columnHeaderAvailability == -1) {
                columnHeaderAvailability = 0;
                break;
            }
            waitHelper.waitForPageToLoad();
        }
        return (columnHeaderAvailability != 0);
    }

    /**
     * deleting a budget estimate item
     *
     * @return - true if deleted, false otherwise
     */
    public boolean deleteBudgetItem() {
        Double totalBeforeItemDelete = getTotalRevenueFromBudgetItems();
        deleteMultipleItems(1);
        Double totalAfterItemDelete = getTotalRevenueFromBudgetItems();
        return (!totalBeforeItemDelete.equals(totalAfterItemDelete));
    }

    /**
     * Verify necessary Budget Estimate Item labels in new form page
     *
     * @param labelsToCompare - the labels to be compared
     * @return - softAssert
     */
    public SoftAssert verifyBudgetEstimateItemLabelsInNewPage(List<String> labelsToCompare) {
        clickAddItemButton();
        waitHelper.waitForPageToLoad();
        var softAssert = new SoftAssert();
        for (int i = 0; i < labelsToCompare.size(); i++) {
            String labelsDisplayed = null;
            labelsDisplayed = String.format(displayedLabels, labelsToCompare.get(i));
            softAssert.assertTrue(elementHelper.isElementDisplayed(By.xpath(labelsDisplayed)));
        }
        return softAssert;
    }

    /**
     * Verify Mandatory Budget Estimate Item labels in new form page
     *
     * @param mandatoryLabels - the mandatory labels to be checked
     * @return - softAssert
     */
    public SoftAssert verifyMandatoryBudgetEstimateItemLabelsInNewPage(List<String> mandatoryLabels) {
        clickAddItemButton();
        waitHelper.waitForPageToLoad();
        var softAssert = new SoftAssert();
        for (int i = 0; i < mandatoryLabels.size(); i++) {
            String mandatoryLabelsDisplayed = String.format(mandatoryLabel, mandatoryLabels.get(i));
            softAssert.assertTrue(elementHelper.isElementDisplayed(By.xpath(mandatoryLabelsDisplayed)));
        }
        return softAssert;
    }

    /**
     * Reorder Budget Estimate Items
     *
     * @return - true if items are reordered
     */
    public boolean reOrderBudgetEstimateItems() {
        waitHelper.waitForPageToLoad();
        String firstItem = getItemData(0, "Name");
        clickRibbonIcon(RibbonIcons.ReOrder);
        waitHelper.waitForPageToLoad();
        waitHelper.waitUntilElementAppears(dragBudgetItemSourceFrom);
        waitHelper.waitUntilElementAppears(dragBudgetItemDestinationTo);
        elementHelper.dragAndDrop(dragBudgetItemSourceFrom, dragBudgetItemDestinationTo);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.ItemSave);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();
        String firstItemPostShuffle = getItemData(0, "Name");
        return !(firstItem.equalsIgnoreCase(firstItemPostShuffle));
    }

    /**
     * Verify if Refresh Line Number button works fine
     *
     * @return - true if it works as expected
     */
    public boolean verifyRefreshLineNumberInBudgetEstimateItems() {
        clickRibbonIcon(RibbonIcons.RefreshLineNumber);
        waitHelper.waitForPageToLoad();
        return validateRibbonIcon(RibbonIcons.RefreshLineNumber);
    }

    /**
     * Function to click on Upload in Excel Import
     *
     */
    public void clickUploadInExcelImport() {
        waitHelper.waitForElementClickable(uploadButton);
        elementHelper.doClick(uploadButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * method to click on Error log in Excel Import
     */
    public void clickOnErrorLogInExcelImport() {
        waitHelper.waitForPageToLoad(excelImportErrorLogButton);
        elementHelper.doClick(excelImportErrorLogButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * method to check if the error text is present
     *
     * @return  If excel upload error message is displayed, else false.
     */
    public boolean checkIfUploadErrorMessageDisplayed() {
        waitHelper.waitForPageToLoad(excelUploadErrorMessage);
        return elementHelper.isElementDisplayed(excelUploadErrorMessage);
    }

    /**
     * Create a new container
     *
     * @return - container name
     */
    public String createContainer() {
        waitHelper.waitForPageToLoad();
        String containerName = "Container" + TestDataUtil.getRandomNumber(1000000, 9999999);
        logger().info("Creating Container : " + containerName);
        clickRibbonIcon(RibbonIcons.BudgetItemAddContainer);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(containerNameTextBox, containerName);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageTabHeaderToBeClickable();
        return containerName;
    }

    /**
     * Create a new container
     * @param containerName  Name of the container.
     *
     * @return - container name
     */
    public String createContainerOld(String containerName) {
        clickRibbonIcon(RibbonIcons.BudgetItemAddContainer);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(containerNameTextBox, containerName);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        waitHelper.waitUntilElementAppears(getRibbonIcon(RibbonIcons.NewDropDown));
        return containerName;
    }

    /**
     * Validating Container displayed if selected in New Item page
     * @param containerName - Parent Container Name
     * @return - true if container name is displayed post selection
     */
    public boolean validateContainerInNewItemsPage(String containerName)
    {
        logger().info("Validating Container displayed if selected in New Item page");
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        waitHelper.waitForElementClickable(selectContainerInNewItemsPage);
        elementHelper.doClick(selectContainerInNewItemsPage);
        waitHelper.waitForPageToLoad();
        String containerLocator = "//a[text()='%s']";
        waitHelper.waitForElementClickable(By.xpath(String.format(containerLocator,containerName)));
        elementHelper.doClick(By.xpath(String.format(containerLocator,containerName)));
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(getContainerFieldValue);
        String containerNameDisplayedWithParent = elementHelper.doGetAttribute(getContainerFieldValue,"value");
        String[] containerNameDisplayed = containerNameDisplayedWithParent.split("/");
        clickRibbonIcon(RibbonIcons.Cancel);
        return(containerName.equals(containerNameDisplayed[containerNameDisplayed.length-1]));
    }

    /**
     * Verify Flat list ribbon functionality
     *
     * @return - true if container details is not present
     */
    public boolean verifyFlatList() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        String containerName = createContainer();
        clickRibbonIcon(RibbonIcons.FlatList);
        waitHelper.waitForPageToLoad();
        return !(elementHelper.isElementDisplayed(By.xpath(String.format(searchContainerInFlatList, containerName))));
    }

    /**
     * Create Container Inside Container
     * @param parentContainerName - parent container name
     * @return - Container Name
     */
    public String createContainerInsideContainer(String parentContainerName)
    {
        waitHelper.waitForPageToLoad();
        String containerName = "Container" + TestDataUtil.getRandomNumber(1000000, 9999999);
        logger().info("Creating Container : " + containerName);
        click(ItemMenuBar.NewContainer);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(containerNameTextBox, containerName);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(By.xpath(String.format("//a[text()='%s']",parentContainerName)));
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        waitHelper.waitUntilElementAppears(getRibbonIcon(RibbonIcons.NewDropDown));
        return containerName;
    }

    /**
     * Validate New On Selection Of Multiple Container
     * @param containerName1 - container Name 1
     * @param containerName2 - container Name 2
     * @return - true if return button is not displayed
     */
    public boolean validateNewOnSelectionOfMultipleContainer(String containerName1, String containerName2)
    {
        logger().info("Validate New On Selection Of Multiple Container");
        clickCheckboxInExpandedList(containerName1, "Name");
        clickCheckboxInExpandedList(containerName2, "Name");

        return !validateRibbonIcon(RibbonIcons.NewItem);
    }

    /**
     * Associate Fund Rule to budget estimate item
     *
     * @param itemName               - item name
     * @param fundRuleToBeAssociated - fund rule to be associated
     * @return - true if fund rule is associated
     */
    public boolean associateFundRuleToBudgetEstimateItem(String itemName, String fundRuleToBeAssociated) {
        waitHelper.waitForPageToLoad();
        clickCheckboxInExpandedList(itemName, "Name");
        if (fundRuleToBeAssociated != null) {
            clickRibbonIcon(RibbonIcons.FundRuleAssociationInListPage);
            var fundRuleXpath = By.xpath(String.format("//span[text()='%s'][@class='rrbText']", fundRuleToBeAssociated));
            elementHelper.doClickUsingActions(fundRuleXpath);
            waitHelper.waitForAjaxToComplete();
        }
        waitHelper.waitForPageToLoad();
        return isItemPresentInList(fundRuleToBeAssociated, "Fund Rule");
    }

    public boolean associateFundRuleToBudgetEstimateItem(String fundRuleToBeAssociated) {
        elementHelper.doClick(elementHelper.getElement(By.cssSelector("#chk_checkAllItem")));
        waitHelper.waitForPageTabHeaderToBeClickable();
        if (fundRuleToBeAssociated != null) {
            clickRibbonIcon(RibbonIcons.FundRuleAssociationInListPage);
            var fundRuleXpath = By.xpath(String.format("//li[@class='rrbItem']//a[@title='%s' and not(contains(@title,'None'))]", fundRuleToBeAssociated));
            elementHelper.doClickUsingActions(fundRuleXpath);
            waitHelper.waitForAjaxToComplete();
        }
        waitHelper.waitForPageToLoad();
        return isItemPresentInList(fundRuleToBeAssociated, "Fund Rule");
    }
    /**
     * Get Pay Item Number and Total Amount
     *
     * @param rowNumber - the pay item row number
     * @return - pay item number and total amount displayed
     */
    public List<String> getPayItemNumberAndTotalAmount(int rowNumber) {
        List<String> payItemNumberAndAmount = new ArrayList<>();
        waitHelper.waitForPageToLoad();
        clickCheckboxByRowNumber(rowNumber);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        payItemNumberAndAmount.add(elementHelper.doGetAttribute(getPayItemNo,"value"));
        payItemNumberAndAmount.add(elementHelper.doGetAttribute(getPayItemAmount,"value"));

        return payItemNumberAndAmount;
    }

    /**
     * Validate view functionality
     *
     * @return - true if the view functionality works as intended
     */
    public boolean validateViewFunctionality() {
        logger().info("View and validate budget estimate type");
        String saveButton = "Save";
        clickCheckboxByRowNumber(0);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForPageToLoad();
        return (!elementHelper.isElementDisplayed(inputTextBoxFields));
    }

    /**
     * Validate Excel Import
     *
     * @param fileName - name of the file
     * @param node     - host node
     * @return - true if import is successful
     */
    public boolean validateExcelImport(String fileName, Host node) {

        // Excel Headers

        List<String> columnHeadersToBeUploaded = new ArrayList<>();
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InternalID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Type.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.IndentLevel.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.LineNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Name.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Description.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Notes.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.PayItemNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.UnitPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.FundRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Quantity.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Unit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.EffectiveFrom.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InflationRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AnnualInflationRate.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AccountingCode.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.TitleID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceTitle.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceType.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceFixedPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceVariablePriceInDollarsPerUnit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.NumberOfResources.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceQuantityInUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceAmountInDollars.getValue());

        var budgetEstimateTypeImportData = TestDataUtil.generateBudgetEstimateItemsImportData();

        // Excel Data

        List<String> columnRecord = new ArrayList<>();
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.type);
        columnRecord.add(budgetEstimateTypeImportData.indentLevel);
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.itemDescription);
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.payItemNo);
        columnRecord.add(budgetEstimateTypeImportData.unitPriceInDollars);
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.quantity);
        columnRecord.add(budgetEstimateTypeImportData.unit);
        IntStream.range(13, 25).forEach($ -> {columnRecord.add(Constants.EMPTY_STRING);});

        List<List<String>> addColumnDataToBeWritten = new ArrayList<>();
        addColumnDataToBeWritten.add(columnRecord);

        Map<List<String>, List<List<String>>> allHeaderAndData = new HashMap<>();
        allHeaderAndData.put(columnHeadersToBeUploaded, addColumnDataToBeWritten);

        String sheetName = "Item";
        ExcelUtil.writeExcelDataWithColumnHeader(fileName, allHeaderAndData, sheetName);
        return importFile(fileName);
    }

    /**
     * Validate Excel Import With few of the data Missing/Mismatch
     *
     * @param fileName - name of the file
     * @param node     - host node
     * @return - true if import is unsuccessful
     */
    public boolean validateExcelImportWithOutProperData(String fileName, Host node) {

        // Excel Headers

        List<String> columnHeadersToBeUploaded = new ArrayList<>();
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InternalID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Type.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.IndentLevel.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.LineNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Name.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Description.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Notes.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.PayItemNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.UnitPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.FundRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Quantity.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Unit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.EffectiveFrom.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InflationRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AnnualInflationRate.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AccountingCode.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.TitleID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceTitle.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceType.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceFixedPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceVariablePriceInDollarsPerUnit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.NumberOfResources.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceQuantityInUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceAmountInDollars.getValue());

        var budgetEstimateTypeImportData = TestDataUtil.generateBudgetEstimateItemsImportData();

        // Excel Data

        List<String> columnRecord = new ArrayList<>();
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.type);
        columnRecord.add(budgetEstimateTypeImportData.indentLevel);
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.itemDescription);
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.payItemNo);
        columnRecord.add(budgetEstimateTypeImportData.unitPriceInDollars);
        columnRecord.add("");
        columnRecord.add(budgetEstimateTypeImportData.quantity);
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");
        columnRecord.add("");

        List<List<String>> addColumnDataToBeWritten = new ArrayList<>();
        addColumnDataToBeWritten.add(columnRecord);

        Map<List<String>, List<List<String>>> allHeaderAndData = new HashMap<>();
        allHeaderAndData.put(columnHeadersToBeUploaded, addColumnDataToBeWritten);

        String sheetName = "Item";
        ExcelUtil.writeExcelDataWithColumnHeader(fileName, allHeaderAndData, sheetName);
        waitHelper.waitForPageToLoad();
        return importFile(fileName);
    }

    /**
     * Validate Excel Template Export
     *
     * @param fileName - name of the file
     * @param node     - host node
     * @return - returns true if the expected headers are displayed
     */
    public boolean validateExcelTemplateExport(String fileName, Host node) {
        List<String> columnHeadersInTemplate = ExcelUtil.getExcelColumnHeaders(fileName, node, "Item");
        columnHeadersInTemplate.add(BudgetEstimateItemColumnsList.AmountIn$.getValue());
        columnHeadersInTemplate.add(BudgetEstimateItemColumnsList.InflatedAmountIn$.getValue());
        columnHeadersInTemplate.add(BudgetEstimateItemColumnsList.LineNumber.getValue());
        columnHeadersInTemplate.remove(BudgetEstimateItemsExcelColumns.LineNo.getValue());

        List<String> columnHeadersExpected = new ArrayList<>();
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Type.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.IndentLevel.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Description.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Name.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Notes.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.PayItemNo.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Unit.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.FundRule.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.Quantity.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.AccountingCode.getValue());
        columnHeadersExpected.add(BudgetEstimateItemsExcelColumns.ResourceAmountInDollars.getValue());

        columnHeadersInTemplate.forEach(s -> logger().info(String.format("Template Headers List : ",s)));
        columnHeadersExpected.forEach(s -> logger().info(String.format("Expected Headers List : ",s)));

        return columnHeadersInTemplate.containsAll(columnHeadersExpected);
    }

    /**
     * Validate Excel Data Exported
     *
     * @param fileName               - name of the file exported
     * @param node                   - host node
     * @param payItemNoToBeEvaluated - the pay item number to be verified
     * @return - true, if the Excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String payItemNoToBeEvaluated) {
        waitHelper.waitForPageToLoad();
        List<String> budgetEstimateItemsColumnHeaders = itemListPageHeaders();
        budgetEstimateItemsColumnHeaders.remove("Open the calendar popup.");
        List<String> budgetEstimateRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int budgetEstimatedItemsRowNumber = 0;
        for (int i = 0; i < budgetEstimateItemsColumnHeaders.size(); i++) {
            budgetEstimateRowValues.add(getItemData(budgetEstimatedItemsRowNumber, budgetEstimateItemsColumnHeaders.get(i)));
            listPageColumnHeadersWithValues.put(budgetEstimateItemsColumnHeaders.get(i), budgetEstimateRowValues.get(i));
        }
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.FundRule.getValue(),"");
        double inflationValue = Double.parseDouble(listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.AnnualInflationRate.getValue()));
        String payItemNoValue = listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.Name.getValue());
        listPageColumnHeadersWithValues.put(BudgetEstimateItemsExcelColumns.PayItemNo.getValue(), payItemNoValue);
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.Name.getValue(),"");
        DecimalFormat df = new DecimalFormat("0.0000");
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.AnnualInflationRate.getValue(), df.format(inflationValue));
        listPageColumnHeadersWithValues.put(BudgetEstimateItemsExcelColumns.Notes.getValue(), "");
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node,BudgetEstimateItemsExcelColumns.PayItemNo.getValue(), payItemNoValue,"Item");
        excelColumnHeadersWithValue.put(BudgetEstimateItemColumnsList.AmountIn$.getValue(), listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.AmountIn$.getValue()));
        excelColumnHeadersWithValue.put(BudgetEstimateItemColumnsList.InflatedAmountIn$.getValue(), listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.InflatedAmountIn$.getValue()));
        excelColumnHeadersWithValue.remove(BudgetEstimateItemsExcelColumns.LineNo.getValue());
        excelColumnHeadersWithValue.put(BudgetEstimateItemColumnsList.LineNumber.getValue(), listPageColumnHeadersWithValues.get("Line Number"));

        listPageColumnHeadersWithValues.entrySet().forEach(e -> logger().info(String.format("List Page Column Header = ",e.getKey()," : Value = ",e.getValue())));
        excelColumnHeadersWithValue.entrySet().forEach(e -> logger().info(String.format("Excel Column Header = ",e.getKey()," : Value = ",e.getValue())));

        return excelColumnHeadersWithValue.entrySet().containsAll(listPageColumnHeadersWithValues.entrySet());
    }

    /**
     * Validate excel data exported with template
     *
     * @param fileName               - name of the file exported
     * @param node                   - host node
     * @param payItemNoToBeEvaluated - the pay item number to be verified
     * @return -  true if the Excel values and headers matches with the list page and internalID header is present in excel
     */
    public boolean validateExcelTemplateExportWithData(String fileName, Host node, String payItemNoToBeEvaluated) {
        logger().info("Validating excel template with data");
        waitHelper.waitForPageToLoad();
        List<String> budgetEstimateItemsColumnHeaders = itemListPageHeaders();
        budgetEstimateItemsColumnHeaders.remove("Open the calendar popup.");
        List<String> budgetEstimateRowValues = new ArrayList<>();
        Map<String, String> listPageColumnHeadersWithValues = new HashMap<>();
        int budgetEstimatedItemsRowNumber = 0;
        for (int i = 0; i < budgetEstimateItemsColumnHeaders.size(); i++) {
            budgetEstimateRowValues.add(getItemData(budgetEstimatedItemsRowNumber, budgetEstimateItemsColumnHeaders.get(i)));
            listPageColumnHeadersWithValues.put(budgetEstimateItemsColumnHeaders.get(i), budgetEstimateRowValues.get(i));
        }
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.FundRule.getValue(),"");
        double inflationValue = Double.parseDouble(listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.AnnualInflationRate.getValue()));
        String payItemNoValue = listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.Name.getValue());
        listPageColumnHeadersWithValues.put(BudgetEstimateItemsExcelColumns.PayItemNo.getValue(),payItemNoValue);
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.Name.getValue(),"");
        DecimalFormat df = new DecimalFormat("0.0000");
        listPageColumnHeadersWithValues.replace(BudgetEstimateItemColumnsList.AnnualInflationRate.getValue(), df.format(inflationValue));
        listPageColumnHeadersWithValues.put(BudgetEstimateItemsExcelColumns.Notes.getValue(),"");
        Map<String, String> excelColumnHeadersWithValue =
                ExcelUtil.getColumnHeadersWithRowData(fileName, node,BudgetEstimateItemsExcelColumns.PayItemNo.getValue(), payItemNoValue,"Item");
        excelColumnHeadersWithValue.put(BudgetEstimateItemColumnsList.AmountIn$.getValue(), listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.AmountIn$.getValue()));
        excelColumnHeadersWithValue.put(BudgetEstimateItemColumnsList.InflatedAmountIn$.getValue(), listPageColumnHeadersWithValues.get(BudgetEstimateItemColumnsList.InflatedAmountIn$.getValue()));
        excelColumnHeadersWithValue.remove(BudgetEstimateItemsExcelColumns.LineNo);
        excelColumnHeadersWithValue.put("Line Number",listPageColumnHeadersWithValues.get("Line Number"));

        listPageColumnHeadersWithValues.entrySet().forEach(e -> logger().info(String.format("List Page Column Header = ",e.getKey()," : Value = ",e.getValue())));
        excelColumnHeadersWithValue.entrySet().forEach(e -> logger().info(String.format("Excel Column Header = ",e.getKey()," : Value = ",e.getValue())));

        List columnHeadersFromExcel = ExcelUtil.getExcelColumnHeaders(fileName, node, "Item");

        return columnHeadersFromExcel.contains("Internal ID") &&
                excelColumnHeadersWithValue.entrySet().containsAll(listPageColumnHeadersWithValues.entrySet());
    }

    /**
     * Validate filter functionality
     *
     * @param columnName  - name of the column
     * @param columnValue - value to be filtered
     * @return - true if the result is filtered
     */
    public boolean validateFilterFunctionality(String columnName, String columnValue) {
        waitHelper.waitForPageToLoad();
        var names = getPage(ItemListPage.class).getColumnData(columnName);
        return names.contains(columnValue);
    }

    /**
     * Adding a sub item to an existing item
     *
     * @param payItemNo          - the item in which the sub item is to be added
     * @param subItemDescription - description of the sub item
     * @return - true if sub item is added, false otherwise
     */
    public boolean addSubItem(String payItemNo, String subItemDescription) {
        waitHelper.waitForPageToLoad();
        //Select Item from Item List Page
        clickCheckboxInExpandedList(payItemNo, "Name");

        //Click on Edit button:the Ribbon
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        addSubItemToAnItem(1, subItemDescription);
        waitHelper.waitForPageToLoad();
        expandItemList();
        return isItemPresentInList(subItemDescription, "Name");
    }

    /**
     * Edit a sub item
     *
     * @param payItemNo  - Pay Item No
     * @param quantity           - quantity to be updated
     * @param unitPrice          - unit price to be updated
     * @param validate           - is validation required
     * @return - true if sub item is updated
     */
    public boolean editSubItem(String payItemNo, double quantity, double unitPrice, boolean validate) {
        waitHelper.waitForPageToLoad();
        //Select Item from Item List Page
        clickCheckboxInExpandedList(payItemNo, "Name");
        waitHelper.waitForPageToLoad();
        //Click on Edit button:the Ribbon
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        return updateQtyInSubItemAndValidate(quantity, unitPrice, validate);
    }

    /**
     * Validate item in list page
     *
     * @param rowIdentifier rowIdentifier of the item
     * @param columnName    column name to be searched in
     * @return true on validation of the above
     */
    public boolean validateItemInListPage(String rowIdentifier, String columnName) {
        return isItemPresentInList(rowIdentifier, columnName);
    }

    /**
     * Get the Work project that can be associated with Budget Estimate
     *
     * @return Work Project in the picker
     */
    public String getListOfWorkProjectsWhichAreAvailableForAssociation(){
        clickRibbonIcon(RibbonIcons.AddWPs);
        waitHelper.waitForElementPresent(addWorkProjectsPicker);
        return getPage(Picker.class).getDataByRowNoColumnName(1,BudgetEstimateItemColumnsList.Name.getValue(), addWorkProjectsPicker);
    }

    /**
     * Verify Effective date is enabled or not on inflation rule selection
     * @param inflationRuleName - Inflation Rule Name
     * @return - true if effective date is enabled post inflation rule selection
     */
    public boolean verifyEffectiveDateEnabled(String inflationRuleName)
    {
        logger().info("Verify Effective date is enabled or not on inflation rule selection in new page");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        elementHelper.doClick(newButtonInBudgetItemsPage);
        elementHelper.doClick(newButtonFromDropdown);
        waitHelper.waitForPageToLoad();
        if((elementHelper.isElementDisplayed(inflationRule) && elementHelper.isElementDisplayed(annualInflationRate) && elementHelper.isElementDisplayed(effectiveFrom)))
        {
            waitHelper.waitForPageToLoad();
            elementHelper.doClick(inflationRulePicker);
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            getPage(Picker.class).singleSelectByText("Name", inflationRuleName, pickerPathForInflationRule);
            waitHelper.waitForPageToLoad();
            boolean isEffectiveDateEnabled =  elementHelper.doGetAttribute(effectiveFromDateBEItem,"disabled")==null;
            clickRibbonIcon(RibbonIcons.Cancel);
            return isEffectiveDateEnabled;
        }
        else
        {
            logger().info("Inflation Columns are not displayed");
            return false;
        }
    }

    /**
     * Verify Effective Date And Annual Inflation Rate Enabled if Inflation Rule is selected
     * @param inflationRuleName - inflation rule name
     * @return - true if Annual Inflation Rate and Effective date is enabled
     */
    public boolean verifyEffectiveDateAndAnnualInflationRateEnabled(String inflationRuleName)
    {
        logger().info("Verify Effective date is enabled or not on inflation rule selection in new page");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        elementHelper.doClick(newButtonInBudgetItemsPage);
        elementHelper.doClick(newButtonFromDropdown);
        waitHelper.waitForPageToLoad();
        if((elementHelper.isElementDisplayed(inflationRule) && elementHelper.isElementDisplayed(annualInflationRate) && elementHelper.isElementDisplayed(effectiveFrom)))
        {
            waitHelper.waitForPageToLoad();
            elementHelper.doClick(inflationRulePicker);
            waitHelper.waitForPageToLoad();
            navigation.switchFrameToContent();
            getPage(Picker.class).singleSelectByText("Name", inflationRuleName, pickerPathForInflationRule);
            waitHelper.waitForPageToLoad();
            if(elementHelper.doGetAttribute(inflationRate,"disabled")==null)
            {
                boolean isEffectiveDateEnabled =  elementHelper.doGetAttribute(effectiveFromDateBEItem,"disabled")==null;
                clickRibbonIcon(RibbonIcons.Cancel);
                return isEffectiveDateEnabled;
            }
            else
            {
                clickRibbonIcon(RibbonIcons.Cancel);
                return false;
            }
        }
        else
        {
            logger().info("Inflation Columns are not displayed");
            clickRibbonIcon(RibbonIcons.Cancel);
            return false;
        }
    }

    /**
     * Validate Information Icon Hover Text
     * @return - true if the text displayed is accurate
     */
    public boolean validateInformationIcon()
    {
        logger().info("Validate Information Icon Hover Text");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        elementHelper.doClick(newButtonInBudgetItemsPage);
        elementHelper.doClick(newButtonFromDropdown);
        waitHelper.waitForPageToLoad();
        String displayedInfo = elementHelper.doGetAttribute(effectiveFromInformationIcon,"title");
        String expectedInfo = "Inflation rate will be applied on\r\nthe item's cost if the Start Date\r\nof the item lies on or after the\r\nEffective From Date.";
        clickRibbonIcon(RibbonIcons.Cancel);
        return displayedInfo.equals(expectedInfo);
    }

    /**
     * Select all the rows in Budget Estimate
     */
    public void selectAllRowsInBudgetEstimate()
    {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(checkAllItems);
    }

    /**
     * Assoicate Inflation Rule
     * @param inflationRule Name of the inflation Rule
     */
    public void associateInflationRule(String inflationRule)
    {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.AssociateInflationRule);
        waitHelper.waitForPageToLoad(applyInflationRulePicker);
        elementHelper.doClick(applyInflationRuleButton);
        waitHelper.waitForPageToLoad(inflationPicker);
        getPage(Picker.class).singleSelectByText("Name",inflationRule,inflationPicker);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(saveInflation);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select fund rule to associate with the record
     * @param fundRuleName Name of the fund rule
     */
    public void selectFundRule(String fundRuleName)
    {
        clickRibbonIcon(RibbonIcons.FundRuleAssociationInListPage);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        By locator = By.xpath(String.format("//a[@id]//span[text()='%s']",fundRuleName));
        elementHelper.doClick(locator);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate that fund rule is selected
     * @param fundRuleName Name of the fund rule to check
     * @return True if fund rule name matches
     */
    public boolean validateFundRuleEntered(String fundRuleName)
    {
        waitHelper.waitForPageToLoad();
        WebElement element = elementHelper.getElement(fundRuleField).findElement(By.xpath("./option[@selected='selected']"));
        return fundRuleName.equalsIgnoreCase(elementHelper.doGetText(element));
    }

    //AutoDesk

    /**
     * Validate Toolbar Global Option List
     *
     * @return true on validation fields displayed
     */
    public boolean validateGlobalList() {
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.NewDropDown);
        ribbonMenuIcons.add(RibbonIcons.ExcelImportOrExportDropDown);
        ribbonMenuIcons.add(RibbonIcons.Back);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar New Option List
     *
     * @return true on validation fields displayed
     */
    public boolean validateNewList() {
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        ribbonMenuIcons.add(RibbonIcons.SaveAndContinue);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under Edit list
     *
     * @return true on validation of the above
     */
    public boolean validateEditList() {
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        ribbonMenuIcons.add(RibbonIcons.SaveAndContinue);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar New Container List
     *
     * @return true on validation fields displayed
     */
    public boolean validateNewContainerList() {
        clickRibbonIcon(RibbonIcons.NewContainer);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar in Single record select
     *
     * @return true on Fields available
     */
    public boolean validateSingleRecordList() {
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Edit);
        ribbonMenuIcons.add(RibbonIcons.View);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar in Mutiple record select
     *
     * @return true on Fields available
     */
    public boolean validateMultipleRecordList() {
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.Actions);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under Action dropdown
     * @param globalGroups Validate the globalgroups action items
     * @param copyItems Validate the copyItems action items
     *
     * @return true on Fields available
     */
    public boolean validateActionValues(boolean globalGroups, boolean copyItems) {
        clickRibbonIcon(RibbonIcons.Actions);
        List<By> importExportOptions = new ArrayList<>();
        if (globalGroups)
            importExportOptions.add(By.xpath(String.format(textValidationInRibbonMenu, ActionsOptions.GlobalGroups.getValue())));
        if (copyItems)
            importExportOptions.add(By.xpath(String.format(textValidationInRibbonMenu, ActionsOptions.CopyItems.getValue())));

        return getPage(GenericForm.class).validateElementsExists(importExportOptions);
    }

    /**
     * Validate Toolbar under New dropdown
     *
     * @return true on Fields available
     */
    public boolean validateNewValues() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitUntilElementAppears(newButtonInBudgetItemsPage);
        waitHelper.waitForElementClickable(newButtonInBudgetItemsPage);
        elementHelper.doClick(newButtonInBudgetItemsPage);
        List<By> importExportOptions = new ArrayList<>();
        importExportOptions.add(By.xpath(String.format(newOptions, NewOptions.New.getValue())));
        importExportOptions.add(By.xpath(String.format(newOptions, NewOptions.NewContainer.getValue())));
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        return getPage(GenericForm.class).validateElementsExists(importExportOptions);
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return ((getPage(ListPage.class).validateImportExportOptions(true, true)) &&
                (getPage(ListPage.class).validateMoreList(false, false, true, false, true, false)) &&
                (validateNewValues()));
    }

    /**
     * This function will click on the first record checkbox
     */
    public void selectFirstBudgetItem() {
        elementHelper.doClick(budgetItemCheckbox);
    }

    /**
     * This function will click on the checkbox of the given row of the list page
     *
     * @return true if the element exists
     */
    public boolean selectReorderItem() {
        clickRibbonIcon(RibbonIcons.ReOrder);
        elementHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Save);
        ribbonMenuIcons.add(RibbonIcons.Back);
        ribbonMenuIcons.add(RibbonIcons.CollapseAll);
        ribbonMenuIcons.add(RibbonIcons.ExpandAll);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Editable Fields
     * @param payItemNo Pay Item No
     * @return - SoftAssert
     */
    public SoftAssert validateEditableFields(String payItemNo)
    {
        logger().info("Validate Editable Fields");
        waitHelper.waitForPageToLoad();
        clickCheckboxInExpandedList(payItemNo, BudgetEstimateItemColumnsList.Name.getValue());
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        var softAssert = new SoftAssert();
        softAssert.assertTrue(elementHelper.isElementDisplayed(payItemNumberTxtBox),"Pay Item Number is un editable");
        softAssert.assertTrue(elementHelper.isElementDisplayed(itemDescriptionTextArea),"Item Description Number is un editable");
        softAssert.assertTrue(elementHelper.isElementDisplayed(itemUnitComboBox),"Item Unit is un editable");
        softAssert.assertTrue(elementHelper.isElementDisplayed(itemQuantityTextBox),"Item Quantity is un editable");
        softAssert.assertTrue(elementHelper.isElementDisplayed(itemUnitCostTextBox),"Item Unit Cost is un editable");
        return softAssert;
    }

    /**
     * Adding Resource to an item
     * @param payItemNo - the item in which resource is to be added
     * @return - resource name
     */
    public String addResource(String payItemNo)
    {
        logger().info("Adding Resource to an item");
        clickCheckboxInExpandedList(payItemNo, "Name");
        clickRibbonIcon(RibbonIcons.Edit);
        waitHelper.waitForPageToLoad();
        elementHelper.selectComboBoxItemByIndex(selectResourceCategory,1);
        elementHelper.doClick(resourcePickerButton);
        waitHelper.waitForPageToLoad(resourcePicker);
        String name = getPage(Picker.class).getColumnData(resourcePickerId,"Description",1).get(0);
        getPage(Picker.class).singleSelectByRowNumber(1,resourcePicker);
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.Save);
        return name;
    }

    /**
     * Validate Inflation Highlight For Sub Item
     * @return - true if sub item inflation amount is highlighted in blue
     */
    public boolean validateInflationHighlightForSubItem()
    {
        logger().info("Validate Inflation Highlight For Sub Item");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        expandItemList();
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(inflationHighlightSubItemInListPage);
    }

    /**
     * Enter item quantity
     * @param quantity Quantity to enter for item
     */
    public void enterItemQuantity(String quantity)
    {
        waitHelper.waitForElementClickable(itemQuantityTextBox);
        elementHelper.doSendKeys(itemQuantityTextBox,quantity);
    }

    public boolean validateExcelImportWithNegativeValues(String fileName, Host node) {

        // Excel Headers

        List<String> columnHeadersToBeUploaded = new ArrayList<>();
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InternalID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Type.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.IndentLevel.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.LineNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Name.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Description.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Notes.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.PayItemNo.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.UnitPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.FundRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Quantity.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.Unit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.EffectiveFrom.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.InflationRule.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AnnualInflationRate.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.AccountingCode.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.TitleID.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceTitle.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceType.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceFixedPriceInDollars.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceVariablePriceInDollarsPerUnit.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.NumberOfResources.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceQuantityInUnits.getValue());
        columnHeadersToBeUploaded.add(BudgetEstimateItemsExcelColumns.ResourceAmountInDollars.getValue());

        // Excel Data

        List<String> columnRecord = new ArrayList<>();
        IntStream.range(0, 4).forEach($ -> {columnRecord.add(Constants.EMPTY_STRING);});
        columnRecord.add("Automation");
        IntStream.range(6, 10).forEach($ -> {columnRecord.add(Constants.EMPTY_STRING);});
        columnRecord.add("CR");
        IntStream.range(12, 24).forEach($ -> {columnRecord.add(Constants.EMPTY_STRING);});


        List<List<String>> addColumnDataToBeWritten = new ArrayList<>();
        addColumnDataToBeWritten.add(columnRecord);

        Map<List<String>, List<List<String>>> allHeaderAndData = new HashMap<>();
        allHeaderAndData.put(columnHeadersToBeUploaded, addColumnDataToBeWritten);

        String sheetName = "Item";
        ExcelUtil.writeExcelDataWithColumnHeader(fileName, allHeaderAndData, sheetName);
        return importFile(fileName);
    }

    /**
     * Navigating to the Forecast page, performs the auto budget forecast
     */
    public void performBudgetEstimateAutoForecast() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        elementHelper.doClick(forecastHeader);
        waitHelper.waitForPageTabHeaderToBeClickable();
        if (getPage(ToastUtil.class).validatePresenceOfToastMessage()) {
            getPage(ToastUtil.class).waitAndCloseForSingleToast();
        }
    }

    /**
     * Get forecast column value in Budget estimate forecast page
     * @param rowIdentifier row name
     * @param columnToFilter column name
     * @param columnToFetch column to fetch
     * @return column value
     */
    public String getForecastColumnCpValue(String rowIdentifier, ForecastListPageColumns columnToFilter, ForecastListPageColumns columnToFetch) {
        waitHelper.waitForPageToLoad();
        getPage(ListPage.class).singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.AllForecastsBudgetEstimate);
        waitHelper.waitForPageToLoad();
        var rowNum = getPage(ListPage.class).getRowNumberFromListPage(columnToFilter.getValue(), rowIdentifier);
        return getPage(ListPage.class).getCellData(rowNum, columnToFetch.getValue());
    }
}