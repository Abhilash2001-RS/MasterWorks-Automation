package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BudgetEstimateDetailsPage extends RibbonMenu {

    private By budgetEstimateNameTxtBox;
    private By budgetEstimateTypeComboBox;
    private By measurementSystemComboBox;
    private By budgetEstimateDetailsTab;
    private By documentTitle;

    public BudgetEstimateDetailsPage(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("BudgetEstimateDetailsPage.json");
        budgetEstimateNameTxtBox = locators.get("budgetEstimateNameTxtBox");
        budgetEstimateTypeComboBox = locators.get("budgetEstimateTypeComboBox");
        measurementSystemComboBox = locators.get("measurementSystemComboBox");
        budgetEstimateDetailsTab = locators.get("budgetEstimateDetailsTab");
        documentTitle = locators.get("documentTitle");
    }

    /**
     * Navigate to the details tab
     */
    public void navigateTo()
    {
        logger().info("Clicking on Budget Estimate Details Tab");
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(budgetEstimateDetailsTab);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Creating a new Budget Estimate
     *
     * @return - true if Budget Estimate is Created
     */
    public boolean createBudgetEstimate(String budgetEstimateName) {
        waitHelper.waitForPageTabHeaderToBeClickable();
        waitHelper.waitForElementClickable(budgetEstimateTypeComboBox);
        waitHelper.waitForElementClickable(budgetEstimateNameTxtBox);
        elementHelper.doSendKeys(budgetEstimateNameTxtBox, budgetEstimateName);
        elementHelper.selectComboBoxItemByIndex(budgetEstimateTypeComboBox, 1);
        waitHelper.waitForElementClickable(measurementSystemComboBox);
        elementHelper.selectComboBoxItemByText(measurementSystemComboBox, "IS System");
        return true;
    }

    /**
     * Create Budget Estimate With Document Upload
     * @param budgetEstimateName - name of Budget Estimate
     * @param documentToBeUploaded - File to be uploaded
     * @return - true if file is uploaded
     */
    public boolean createBudgetEstimateWithDocument(String budgetEstimateName, File documentToBeUploaded) {
        logger().info("Creating BE:" + budgetEstimateName);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        elementHelper.doSendKeys(budgetEstimateNameTxtBox, budgetEstimateName);
        elementHelper.selectComboBoxItemByIndex(budgetEstimateTypeComboBox, 1);
        elementHelper.selectComboBoxItemByText(measurementSystemComboBox, "IS System");
        elementHelper.doSendKeys(getPage(GenericForm.class).uploadDocument, documentToBeUploaded.getAbsolutePath());
        waitHelper.waitForPageToLoadWithCustomTimeout(30);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * @param budgestEstimateName Method to edit a new Budget Estimate
     */
    public void editBudgetEstimate(String budgestEstimateName) {
        logger().info("Editing BE:" + budgestEstimateName);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(budgetEstimateDetailsTab);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        elementHelper.doSendKeys(budgetEstimateNameTxtBox, budgestEstimateName);
        logger().info("Changing BE type combobox selected item");
        elementHelper.selectComboBoxItemByIndex(budgetEstimateTypeComboBox, 1);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Edit Budget Estimate Attachment title
     *
     * @param title Method to edit a new Budget Estimate
     */
    public void editBudgetEstimateAttachmentTitle(String title) {
        logger().info("Editing BE: Attachment Title " + title);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(budgetEstimateDetailsTab);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Save));
        elementHelper.doSendKeys(documentTitle, title);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    //AutoDesk

    /**
     * Validate Global Option List
     *
     * @return true on validation fields displayed
     */
    public boolean validateGlobalList() {
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.New);
        ribbonMenuIcons.add(RibbonIcons.ExcelImportOrExportDropDown);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        ribbonMenuIcons.add(RibbonIcons.BudgetEstimateForecast);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Single record selection from List
     *
     * @return true on validation fields displayed
     */
    public boolean validateSingleRecordList() {
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.New);
        ribbonMenuIcons.add(RibbonIcons.Edit);
        ribbonMenuIcons.add(RibbonIcons.Delete);
        ribbonMenuIcons.add(RibbonIcons.ExcelImportOrExportDropDown);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.Reports);
        ribbonMenuIcons.add(RibbonIcons.BudgetEstimateForecast);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }
    /**
     * Validate Edit list
     *
     * @return true if the fields are available
     */
    public boolean validateEditList() {
        List<RibbonIcons> ribbonMenuIcons=new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.Cancel);
        ribbonMenuIcons.add(RibbonIcons.Workflow);
        ribbonMenuIcons.add(RibbonIcons.SaveAndContinue);
        return validateRibbonIcons(ribbonMenuIcons);
    }
}
