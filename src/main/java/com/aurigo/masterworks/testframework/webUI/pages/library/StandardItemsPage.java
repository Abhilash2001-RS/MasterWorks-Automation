package com.aurigo.masterworks.testframework.webUI.pages.library;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ListPageFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericLibrary;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import com.aurigo.masterworks.testframework.webUI.testData.PayItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class StandardItemsPage extends GenericLibrary {

    private By tableNameTextField;
    private By measurementSystemComboBox;
    private By scheduleTab;
    private By scheduleTemplateName;
    private By components;
    private By componentsGridNameTextField;
    private By componentsGridNameDescriptionField;
    private By saveButton;
    private By componentDetailsRow;

    public StandardItemsPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("LibraryStandardItemsPage.json");
        tableNameTextField = locators.get("tableNameTextField");
        measurementSystemComboBox = locators.get("measurementSystemComboBox");
        scheduleTab = locators.get("scheduleTab");
        scheduleTemplateName = locators.get("scheduleTemplateName");
        components = locators.get("components");
        componentsGridNameTextField = locators.get("componentsGridNameTextField");
        componentsGridNameDescriptionField = locators.get("componentsGridNameDescriptionField");
        saveButton = locators.get("saveButton");
        componentDetailsRow = locators.get("componentDetailsRow");
    }

    /**
     * Method to navigate to Standard Items table in Library Page
     */
    public void navigateToStandardItemsPageLibrary() {
        navigateToLibraryModule();
        waitHelper.waitForPageToLoad();
        navigation.navigateToFormInLeftPaneTree("Standard Items Table");
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to navigate to Schedule template table in Library Page
     */
    public void navigateToScheduleTemplatePageLibrary() {
        navigateToLibraryModule();
        waitHelper.waitForPageToLoad();
        navigation.navigateToFormInLeftPaneTree("Schedule Template");
        waitHelper.waitForPageToLoad();
    }

    /**
     * fill standard Items Table and Measurement System to "IS System" (standard one)
     *
     * @param tableName Name of the table
     */
    public void createNewStandardItemsTable(String tableName) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(tableNameTextField);
        elementHelper.doSendKeys(tableNameTextField, tableName);
        elementHelper.selectComboBoxItemByText(measurementSystemComboBox, "IS System");
    }

    /**
     * Click on Standard Item ribbon icon
     *
     * @param tableName name of the table to select
     */
    public void clickOnStandardItemsInRibbonMenu(String tableName) {
        filterStandardItemsTable(tableName);
        clickRibbonIcon(RibbonIcons.LibraryStandardItems);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Filter and Select Record in Standard Items table
     *
     * @param tableName Table Name
     */
    public void filterStandardItemsTable(String tableName) {
        filterListPage("Standard Table Name", tableName, ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(0);
    }


    /**
     * Click on add Task schedule template Item ribbon icon
     *
     * @param tableName name of the table to select
     */
    public void addTaskScheduleTemplate(String tableName) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(scheduleTemplateName);
        elementHelper.doSendKeys(scheduleTemplateName, tableName);
        getPage(GenericForm.class).clickSave();
        filterListPage("Schedule Template Name", tableName, ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.ViewTaskDetails);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(scheduleTab);
        waitHelper.waitForPageToLoad(RibbonIcons.AddTask);
        clickRibbonIcon(RibbonIcons.AddTask);
        getPage(GenericForm.class).clickSave();
    }

    /**
     * Add Items in to the table
     *
     * @param payItems Name of Pay Items
     */
    public void addItemsInItemsListPage(List<PayItem> payItems) {
        getPage(ItemListPage.class).clickAddSingleItems();
        getPage(ItemListPage.class).addItemInStandardItemPage(payItems);
    }

    /**
     * Click on delete
     *
     * @param tableName Table Name
     */
    public void deleteStandardItem(String tableName) {
        filterListPage("Standard Table Name", tableName, ListPageFilterOptions.EqualTo);
        singleClickOnRowListPage(0);
        clickDelete();
        waitHelper.waitForAlertPresent();
        getPage(AlertHandler.class).acceptAlert(true);
    }

    /**
     * Click on Components
     */
    public void clickOnComponents() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(components);
        elementHelper.doClick(components);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select Component
     *
     * @param rowIdentifier row identifier
     */
    public void selectComponent(String rowIdentifier) {
        By row = By.xpath(String.format("//nobr[text()='%s']/../..", rowIdentifier));
        waitHelper.waitForPageToLoad(row);
        elementHelper.doClickUsingActions(row);
    }

    /**
     * Fill Component Details
     *
     * @param name        Name
     * @param description Description
     */
    public void fillComponentDetails(String name, String description) {
        waitHelper.waitForPageToLoad();
        elementHelper.getElement(componentsGridNameTextField).clear();
        elementHelper.getElement(componentsGridNameDescriptionField).clear();
        elementHelper.doSendKeysUsingAction(componentsGridNameTextField, name);
        elementHelper.doSendKeysUsingAction(componentsGridNameDescriptionField, description);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Save Component
     */
    public void clickSaveComponent() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(saveButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Edits the component
     *
     * @param newName        is the new name for component
     * @param newDescription is the new description
     */
    public void editComponent(String newName, String newDescription) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(componentDetailsRow);
        clickRibbonIcon(RibbonIcons.Edit);
        fillComponentDetails(newName, newDescription);
        clickSaveComponent();
    }
}
