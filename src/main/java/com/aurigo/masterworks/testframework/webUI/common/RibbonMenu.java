package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.models.ColumnNumberAndName;
import com.aurigo.masterworks.testframework.utilities.models.Locator;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.enums.DocumentFolderStructureColumn;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ImportExportOptionsInListPage;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RibbonMenu extends BasePage {

    private static HashMap<String, Locator> locatorHashMap;
    private static final String moreOptionsParentTemplate = ".//*[@id='%s' and (@parentmenu='lnkMoreOptions') and not(contains(@class,'hideMenuItem'))]";
    private static final String activeChildrenTemplate = ".//a[(@parentmenu='%s') and not(contains(@class,'hideMenuItem'))]";
    private final WebDriver driver;

    /**
     * Ribbon Menu Button Locators
     */
    protected String optionsInRibbonMenu = "//span[text() = '%s']";
    protected String textValidationInRibbonMenu = "//a[@aria-label='%s']";
    protected String newOptions = "//li//a[@aria-label='%s']";

    protected Navigation navigation;

    public RibbonMenu(WebDriver driver) {
        super(driver);
        this.driver = driver;
        navigation = GetInstance(Navigation.class, driver);
        locatorHashMap = LocatorUtil.getRibbonLocators();
    }

    /**
     * Method to get ribbon icon locator
     *
     * @param icon - ribbon icon
     * @return ribbon menu locator with grouping details
     */
    public By getRibbonIcon(RibbonIcons icon) {
        return locatorHashMap.get(icon.getValue()).identifier;
    }

    /**
     * Function to click button in Ribbon Menu.
     *
     * @param icon Locator of button.
     */
    public void clickRibbonIcon(RibbonIcons icon) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        clickIcon(icon.getValue());
    }

    /**
     * Method to click on the parent and child ribbon icons recursively. Handles icon grouping and More dropdown
     *
     * @param icon - Ribbon icon
     */
    private void clickIcon(String icon) {
        var hoverParent = false;
        var locator = locatorHashMap.get(icon);
        Locator iconLocator = new Locator();
        iconLocator.identifier = locator.identifier;
        iconLocator.name = locator.name;
        iconLocator.parent = locator.parent;
        if (iconLocator.parent != null) {
            clickIcon(iconLocator.parent.name);
        }
        var id = elementHelper.getLocatorAsString(iconLocator.identifier);
        if (id.startsWith("MainToolBar_")) {
            id = id.substring(id.indexOf("_") + 1);
            iconLocator.identifier = By.xpath(String.format(".//*[contains(@id,'%s')]", id));
        } else {
            iconLocator.identifier = By.xpath(String.format(".//*[@id='%s' and (not(contains(@class,'rrbExpanded')))]", id));
        }
        var template = String.format(moreOptionsParentTemplate, id);
        if (!driver.findElements(By.xpath(template)).isEmpty()) {
            var activeChildren = String.format(activeChildrenTemplate, id);
            hoverParent = !driver.findElements(By.xpath(activeChildren)).isEmpty();
            clickRibbonIcon(RibbonIcons.More);
        }
        waitHelper.waitForElementPresent(iconLocator.identifier);
        elementHelper.scrollToView(iconLocator.identifier);
        if (hoverParent) {
            logger().info(String.format("Hovering over %s icon", icon));
            elementHelper.moveToElement(elementHelper.getElementNoWait(iconLocator.identifier));
        } else {
            waitHelper.waitForElementClickable(iconLocator.identifier);
            logger().info(String.format("Clicking on %s icon", icon));
            elementHelper.doClick(iconLocator.identifier);
        }
    }

    /**
     * Method to validate the presence of Ribbon Icon. Handles icon grouping and More dropdown
     *
     * @param icon - String value of Ribbon icon
     * @return true if the validation is successful
     */
    protected boolean validateIcon(String icon) {
        boolean hoverParent = false;
        var locator = locatorHashMap.get(icon);
        Locator iconLocator = new Locator();
        iconLocator.identifier = locator.identifier;
        iconLocator.name = locator.name;
        iconLocator.parent = locator.parent;
        if (iconLocator.parent != null) {
            if (!validateIcon(iconLocator.parent.name)) {
                logger().info(String.format("Parent element %s is missing from view!", iconLocator.parent.name));
                return false;
            }
            clickIcon(iconLocator.parent.name);
            waitHelper.waitForPageToLoad();
        }
        String id = elementHelper.getLocatorAsString(iconLocator.identifier);
        id = id.startsWith("MainToolBar_") ? id.substring(id.indexOf("_") + 1) : id;
        iconLocator.identifier = By.xpath(String.format(".//*[contains(@id,'%s')]", id));
        var template = String.format(moreOptionsParentTemplate, id);
        var isUnderMore = !driver.findElements(By.xpath(template)).isEmpty();
        if (isUnderMore) {
            var activeChildren = String.format(activeChildrenTemplate, id);
            hoverParent = !driver.findElements(By.xpath(activeChildren)).isEmpty();
            clickIcon(RibbonIcons.More.getValue());
            waitHelper.waitForPageToLoad();
        }
        if (hoverParent) {
            elementHelper.moveToElement(elementHelper.getElementNoWait(iconLocator.identifier));
        }
        var result = elementHelper.isElementDisplayed(iconLocator.identifier);
        if (isUnderMore) {
            clickIcon(RibbonIcons.More.getValue());
        } else if (iconLocator.parent != null) {
            locator = locatorHashMap.get(iconLocator.parent.name);
            Locator iconToClick = new Locator();
            iconToClick.identifier = locator.identifier;
            iconToClick.name = locator.name;
            iconToClick.parent = locator.parent;
            id = elementHelper.getLocatorAsString(iconToClick.identifier);
            id = id.startsWith("MainToolBar_") ? id.substring(id.indexOf("_") + 1) : id;
            iconToClick.identifier = By.xpath(String.format(".//*[contains(@id,'%s')]", id));
            template = String.format(moreOptionsParentTemplate, id);
            isUnderMore = !driver.findElements(By.xpath(template)).isEmpty();
            if (isUnderMore) {
                clickIcon(RibbonIcons.More.getValue());
            } else {
                clickIcon(iconLocator.parent.name);
            }

        }
        return result;
    }

    /**
     * Method to validate the presence of Ribbon Icon.
     *
     * @param icon - Ribbon icon
     * @return true if the validation is successful
     */
    public boolean validateRibbonIcon(RibbonIcons icon) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        return validateIcon(icon.getValue());
    }

    /**
     * Validating a list of ribbon icons
     *
     * @param iconList - List of ribbon icons to validate
     * @return true, if validation is successful
     */
    public boolean validateRibbonIcons(List<RibbonIcons> iconList) {
        logger().info("Validating ribbon icons");
        SoftAssert iconAssert = new SoftAssert();
        try {
            for (var icon : iconList) {
                iconAssert.assertTrue(validateRibbonIcon(icon), String.format("Validating icon '%S'", icon.getValue()));
            }
            iconAssert.assertAll();
            return true;
        } catch (AssertionError error) {
            logger().info(String.format("Assertion failed, \n%s", error.getMessage()));
            return false;
        }
    }

    /**
     * Selects Check In, Check Out and Discards Check Out for any document
     *
     * @param option - select check in/ check out and discard check out option from ribbon
     */
    public void checkInAndCheckOut(String option) {

        if (option.equalsIgnoreCase("Check In")) {
            clickRibbonIcon(RibbonIcons.CheckIn);
        } else if (option.equalsIgnoreCase("Check Out")) {
            clickRibbonIcon(RibbonIcons.CheckOut);
        } else if (option.equalsIgnoreCase("Discard Check Out")) {
            clickRibbonIcon(RibbonIcons.DiscardCheckOut);
        } else {
            logger().fail("Invalid Check In/Out Option");
        }
    }

    /**
     * Generic Function to search in Filter for a specific Column
     *
     * @param elementID            , This is the parent element Id for the Filter Header
     * @param columnNameToSearchIn ,Column name in the list page where the filter needs to be applied on.
     * @return Column number and its name
     */
    public ColumnNumberAndName filter(String elementID, String columnNameToSearchIn) {
        navigation.switchFrameToContent();

        var table = elementHelper.getElement(By.id(elementID));
        var headerTR = table.findElement(By.xpath(".//*[text()='" + columnNameToSearchIn + "']/ancestor::tr"));
        var tHs = headerTR.findElements(By.cssSelector("th"));
        int columnNumber = 0;
        for (var element : tHs) {
            elementHelper.scrollToView(element);
            String text = element.getText();
            if (text.equals(columnNameToSearchIn)) {
                break;
            }
            columnNumber++;
        }
        ColumnNumberAndName columnGridObj = new ColumnNumberAndName();
        columnGridObj.ColumnNumber = columnNumber;
        columnGridObj.ColumnName = (List<WebElement>) headerTR;
        return columnGridObj;
    }

    /**
     * Get all buttons displayed in the ribbon
     *
     * @return - all buttons displayed in the ribbon
     */
    public List<String> getAllRibbonButtonsDisplayed() {
        logger().info("Get all buttons displayed in the ribbon");
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        List<WebElement> ribbonButtons = new ArrayList<>(elementHelper.getElements(By.xpath("//span[@class='rrbText']")));
        List<String> ribbonButtonsDisplayed = new ArrayList<>();
        for (WebElement ribbonButton : ribbonButtons) {
            ribbonButtonsDisplayed.add(ribbonButton.getText());
        }
        return ribbonButtonsDisplayed;
    }

    /**
     * Navigate to Excel Import and Export option in the ribbon
     *
     * @param option - the Import/Export option to be selected
     */
    public void selectImportExportInListPage(ImportExportOptionsInListPage option) {
        navigation.switchFrameToContent();
        var locator = locatorHashMap.get(RibbonIcons.ExcelImportOrExportDropDown.getValue());
        Locator excelImportExportDropdown = new Locator();
        excelImportExportDropdown.identifier = locator.identifier;
        excelImportExportDropdown.name = locator.name;
        excelImportExportDropdown.parent = locator.parent;
        String id = elementHelper.getLocatorAsString(excelImportExportDropdown.identifier);
        id = id.startsWith("MainToolBar_") ? id.substring(id.indexOf("_") + 1) : id;
        excelImportExportDropdown.identifier = By.xpath(String.format(".//*[contains(@id,'%s')]", id));
        var template = String.format(moreOptionsParentTemplate, id);
        if (!driver.findElements(By.xpath(template)).isEmpty()) {
            clickRibbonIcon(RibbonIcons.More);
            elementHelper.moveToElement(elementHelper.getElement(excelImportExportDropdown.identifier));
        } else {
            clickRibbonIcon(RibbonIcons.ExcelImportOrExportDropDown);
        }
        var optionToBeSelected = By.xpath(String.format(optionsInRibbonMenu, option.getValue()));
        waitHelper.waitForElementClickable(optionToBeSelected);
        elementHelper.doClick(optionToBeSelected);
        waitHelper.waitForLoadingSpinnerDisappear();
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Click on WF action button
     */
    public void clickWorkFlowButton() {
        var locator = locatorHashMap.get(RibbonIcons.WorkflowAction.getValue());
        Locator workFlowActionLocator = new Locator();
        workFlowActionLocator.identifier = locator.identifier;
        workFlowActionLocator.name = locator.name;
        workFlowActionLocator.parent = locator.parent;
        String id = elementHelper.getLocatorAsString(workFlowActionLocator.identifier);
        id = id.startsWith("MainToolBar_") ? id.substring(id.indexOf("_") + 1) : id;
        workFlowActionLocator.identifier = By.xpath(String.format(".//*[contains(@id,'%s')]", id));
        var template = String.format(moreOptionsParentTemplate, id);
        if (!driver.findElements(By.xpath(template)).isEmpty()) {
            clickRibbonIcon(RibbonIcons.More);
            elementHelper.moveToElement(elementHelper.getElement(workFlowActionLocator.identifier));
        } else {
            clickRibbonIcon(RibbonIcons.WorkflowAction);
        }
    }

    /**
     * Method to verify a excelImport Option is displayed or not
     *
     * @param option - the option to be verified
     * @return true if the option is displayed
     */
    public boolean isDisplayedExportImportOption(ImportExportOptionsInListPage option) {
        clickRibbonIcon(RibbonIcons.ExcelImportOrExportDropDown);
        var optionToBeSelected = By.xpath(String.format(optionsInRibbonMenu, option.getValue()));
        return elementHelper.isElementDisplayed(optionToBeSelected);
    }

    /**
     * Validating Ribbon Report
     *
     * @param ribbonReports - ribbon reports
     * @return - true if the ribbon report is present
     */
    public boolean validateRibbonReportIcon(RibbonReports ribbonReports) {
        logger().info("Validating Ribbon Report");
        clickRibbonIcon(RibbonIcons.Reports);
        return elementHelper.isElementDisplayed(By.xpath("//*[text() = '" + ribbonReports.getValue() + "']/../..//a[@parentmenu]"));
    }

    /**
     * Click on Ribbon Reports
     *
     * @param ribbonReports - report name
     */
    public void clickRibbonReports(RibbonReports ribbonReports) {
        logger().info("Click on Ribbon Reports");
        clickRibbonIcon(RibbonIcons.Reports);
        elementHelper.doClickUsingActions(By.xpath("//*[text() = '" + ribbonReports.getValue() + "']/../..//a[@parentmenu]"));
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
    }

}