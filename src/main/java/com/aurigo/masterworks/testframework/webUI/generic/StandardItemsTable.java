package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate.BudgetEstimateItemsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class StandardItemsTable extends ListPage {


    private final By standardItemsTitle;

    public StandardItemsTable(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("StandardItemsTable.json");
        standardItemsTitle = locators.get("standardItemsTitle");
    }

    /**
     * Validate Toolbar Global Option List
     *
     * @return true on validation fields displayed
     */
    public boolean validateGlobalList() {
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.New);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Toolbar under New list
     *
     * @return true on validation of the above
     */
    public boolean validateNewList() {
        List<By> locators = new ArrayList<>();
        locators.add(getRibbonIcon(RibbonIcons.New));
        locators.add(getRibbonIcon(RibbonIcons.Back));
        return getPage(GenericForm.class).validateElementsExists(locators);
    }

    /**
     * Validate Toolbar in Single record select
     *
     * @return true on Fields available
     */
    public boolean validateSingleRecordList() {
        List<By> locators = new ArrayList<>();
        locators.add(getRibbonIcon(RibbonIcons.Edit));
        locators.add(getRibbonIcon(RibbonIcons.View));
        locators.add(getRibbonIcon(RibbonIcons.Delete));
        locators.add(getRibbonIcon(RibbonIcons.LibraryStandardItems));
        return getPage(GenericForm.class).validateElementsExists(locators);
    }

    /**
     * Validate Navigate to Items Page
     *
     * @return true on Fields available
     */
    public boolean navigateToItems() {
        clickRibbonIcon(RibbonIcons.LibraryStandardItems);
        elementHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(standardItemsTitle);
    }

    /**
     * Validate Toolbar Standard Items Global Option List
     *
     * @return true on validation fields displayed
     */
    public boolean validateStandardItemsGlobalList() {
        navigation.switchFrameToContent();
        List<RibbonIcons> ribbonMenuIcons = new ArrayList<>();
        ribbonMenuIcons.add(RibbonIcons.NewDropDown);
        ribbonMenuIcons.add(RibbonIcons.ExcelImportOrExportDropDown);
        ribbonMenuIcons.add(RibbonIcons.RefreshLineNumber);
        ribbonMenuIcons.add(RibbonIcons.ReOrder);
        ribbonMenuIcons.add(RibbonIcons.More);
        return validateRibbonIcons(ribbonMenuIcons);
    }

    /**
     * Validate Global fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean globalFieldValidation() {
        return ((getPage(ListPage.class).validateImportExportOptions(true, true)) &&
                (getPage(BudgetEstimateItemsPage.class).validateNewValues()) &&
                (getPage(ListPage.class).validateMoreList(false, false, true, false, true, false)));
    }
}