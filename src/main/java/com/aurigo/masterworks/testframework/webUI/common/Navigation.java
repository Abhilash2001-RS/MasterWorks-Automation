package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.WebDriverUtil;
import com.aurigo.masterworks.testframework.utilities.helper.ScreenshotHelper;
import com.aurigo.masterworks.testframework.utilities.models.environment.Build;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RegexStrings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Navigation extends BasePage {

    public By xpathTreeExpandAllBtn;
    private By formTreeSearchBar;
    public By clearSearchButton;
    private WebDriver driver;
    private String xpathForPageTabTemplate = ".//ul[@id='MenuTabsUL']//span[.='%s']";
    private String xpathForPageTabTemplateFacelift = ".//ul[@id='MenuTabsUL']//a[@title='%s']";
    private By idContentFrame;
    private By recentProjects;

    public Navigation(WebDriver driver) {
        super(driver);
        this.driver = driver;
        var locators = LocatorUtil.getLocators("Navigation.json");
        idContentFrame = locators.get("idContentFrame");
        xpathTreeExpandAllBtn = locators.get("xpathTreeExpandAllBtn");
        recentProjects = locators.get("recentProjects");
        formTreeSearchBar = locators.get("formTreeSearchBar");
        clearSearchButton = locators.get("clearSearchButton");
    }

    /**
     * Method to get folder items of a tree both foldered and non foldered forms
     *
     * @param parentProject Project name should be specified
     * @param folderType    Folders which to get selected
     * @return List of string containing the form names
     */
    public List<String> folderItemsInTree(String parentProject, String folderType) {
        List<String> items = new ArrayList<>();
        List<WebElement> eleList = null;
        var softAssert = new SoftAssert();
        switch (folderType) {
            case "getNonFolderItemsInTree":
                eleList = elementHelper.getElements(By.xpath(".//nobr[.='" + parentProject + "']/../../ul/li[contains(@class,'jstree-leaf')]"));
                break;
            case "getFolderItemsInTreeValues":
                eleList = elementHelper.getElements(By.xpath(".//nobr[.='" + parentProject + "']/../../ul/li[contains(@class,'jstree-open')]/ul/li"));
                break;
            case "getEnterpriseNonFolderValues":
                eleList = elementHelper.getElements(By.xpath(".//../../ul/li[contains(@class,'jstree-leaf')]"));
                break;
            case "getEnterpriseFolderValues":
                elementHelper.doClick(By.xpath(".//nobr[.='Notifications']/ancestor::ul/li/a/nobr[contains(text(),'Recent Projects')]/parent::a/../ins"));
                softAssert.assertTrue(elementHelper.isElementDisplayed(By.xpath(String.format("//*[@aria-expanded='false']/a/nobr[contains(text(),'Recent Projects')]"))));
                eleList = elementHelper.getElements(By.xpath(".//nobr[.='Notifications']/ancestor::ul/li[contains(@class,'jstree-open')]/child::ul/li"));
                break;
            default:
                break;
        }
        eleList.stream().filter(l -> Strings.isNotNullAndNotEmpty(l.findElement(By.tagName("nobr")).getText())).forEach(l -> items.add(l.findElement(By.tagName("nobr")).getText()));
        return items;
    }

    /**
     * Navigates in the left pane tree view.
     *
     * @param treePath String having tree path separated by /
     * @return true if reaches last step.
     */
    public boolean navigateToFormInLeftPaneTree(String treePath) {
        try {
            logger().info("Navigating to the tree item: " + treePath);
            clickOnFormInLeftPaneTree(treePath);
            waitHelper.waitForPageToLoad();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click on a form in the left pane tree view.
     *
     * @param treePath String having tree path separated by /
     */
    public void clickOnFormInLeftPaneTree(String treePath) {
        waitHelper.waitForPageToLoad();
        logger().info("Clicking on tree item: " + treePath);
        switchFrameToDefault();
        expandTreeList();
        var levels = treePath.split(RegexStrings.forwardSlash.getValue());
        var formToClick=levels[levels.length-1];
        elementHelper.doSendKeys(formTreeSearchBar,formToClick);
        var finalXpath = getXpathForLeftPaneTreeItem(treePath);
        var treeItemElement = elementHelper.getElement(By.xpath(finalXpath));
        elementHelper.scrollToView(treeItemElement);
        waitHelper.waitForElementClickable(treeItemElement);
        JavaScriptUtil.clickElementByJS(treeItemElement, driver);
    }

    /**
     * Method to check if element is present in tree
     *
     * @param treePath tree path to traverse
     * @return true if element is enabled in tree
     */
    public boolean folderIsPresentInTree(String treePath) {
        waitHelper.waitForPageToLoad();
        logger().info("Navigating to the tree item: " + treePath);
        switchFrameToDefault();
        expandTreeList();
        return elementHelper.isElementEnabled(By.xpath(getXpathForLeftPaneTreeItem(treePath)));
    }

    /**
     * Method to expand the tree list
     */
    public void expandTreeList() {
        switchFrameToDefault();
        waitHelper.waitForElementClickable(xpathTreeExpandAllBtn);
        elementHelper.doClick(xpathTreeExpandAllBtn);
    }

    /**
     * Method to get folder items of a tree folder
     *
     * @param folderName name of folder
     * @return List of string containing the item names
     */
    public List<String> getFolderItemsInTree(String folderName) {
        List<String> items = new ArrayList<>();
        var eleList = elementHelper.getElements(By.xpath(".//nobr[.='" + folderName + "']/../../ul/li"));
        eleList.forEach(l -> items.add(l.findElement(By.tagName("nobr")).getText()));

        return items;
    }

    /**
     * Method to get the xpath of a form in Left Pane tree
     *
     * @param treePath - Path of the form in the tree
     * @return xpath for the form given in the treePath
     */
    public String getXpathForLeftPaneTreeItem(String treePath) {
        var xpathOfTreeItem = new StringBuilder();
        var levels = treePath.split(RegexStrings.forwardSlash.getValue());
        xpathOfTreeItem.append("//div[@id='treeWrapper']//nobr[.='").append(levels[0]).append("']/..");
        if ((levels.length == 2) && (levels[0].equals(levels[1]))) {
            xpathOfTreeItem.append("/../..//*[not(@aria-level='1')]/*[.='").append(levels[0]).append("']/..");
        } else if (levels.length > 1) {
            for (int i = 1; i < levels.length; i++) {
                xpathOfTreeItem.append("/..//*[.='").append(levels[i]).append("']/..");
            }
        }
        return xpathOfTreeItem.toString();
    }

    /**
     * Switched to default content.
     *
     * @return Returns true if no exception occurred while switching to default.
     */
    public boolean switchFrameToDefault() {
        try {
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception e) {
            ExceptionHandler.logAndContinueWebDriverExceptions(e, "Exception occurred while switching to default frame");
            return false;
        }
    }

    /**
     * Switch to content frame if not already on it.
     *
     * @return Returns true if no issue occurred.
     */
    public boolean switchFrameToContent() {
        try {
            if (JavaScriptUtil.getCurrentFrame(driver).equals("contentFrame")) {
                return true;
            }
            waitHelper.waitForFrameAvailableAndSwitchToIt(idContentFrame);
            return true;
        } catch (Exception e) {
            ExceptionHandler.logAndContinueWebDriverExceptions(e, "Exception occurred while switching to content frame");
            return false;
        }
    }

    /**
     * Navigate to module from Landing page.
     *
     * @param moduleName             Module name
     * @param moduleDashboardLocator Locator for Module's Dashboard which appears after moving to module.
     * @return true if no issue occurred.
     */
    public boolean navigateToModule(String moduleName, By moduleDashboardLocator) {
        switchFrameToDefault();
        if (isModuleDisplayed(moduleName)) {
            navigateToModulePageByName(moduleName);
            waitHelper.waitForPageToLoad();
            switchFrameToContent();
            waitHelper.waitForPageTabHeaderToBeClickable();
            return true;
        }
        return false;
    }

    /**
     * Creates a new browser instance
     *
     * @param build - Build details.
     * @return WebDriver
     */
    public WebDriver createNewBrowserInstance(Build build) {
        logger().info("Creating a new browser instance");

        WebDriverUtil driverUtil = new WebDriverUtil();
        driverUtil.getDriver(build);

        return driverUtil.driver;
    }

    /**
     * Navigates to a module page using the page tabs in the menu bar
     *
     * @param pageName
     */
    public void navigateToModulePageByName(String pageName) {
        logger().info("Navigating to page: " + pageName);
        getPage(Navigation.class).switchFrameToDefault();
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(getPageElementFromName(pageName));
        elementHelper.doClick(getPageElementFromName(pageName));
        switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Method to check if module link exists in navigation.
     *
     * @param tabName -   Name of the module/link.
     * @return True if exists, else false.
     */
    public boolean isModuleDisplayed(String tabName) {
        var xpathForPageTab = String.format(xpathForPageTabTemplateFacelift, tabName);
        return elementHelper.isElementDisplayed(By.xpath(xpathForPageTab));
    }

    /**
     * Method to get the corresponding UI element of a tab in the menu bar
     *
     * @param pageName
     * @return UI element of a page tab in the menu bar
     */
    private WebElement getPageElementFromName(String pageName) {
        var xpathForPageTab = String.format(xpathForPageTabTemplateFacelift, pageName);
        return elementHelper.getElement(By.xpath(xpathForPageTab));
    }

    /**
     * Get Recent Projects list.
     *
     * @return -   Recent Projects list.
     */
    public List<String> getRecentProjectsList() {
        List<String> projects = new ArrayList<>();
        var eleList = elementHelper.getElements(recentProjects);
        eleList.forEach(l -> projects.add(elementHelper.doGetText(l)));
        return projects;
    }

    /**
     * Refresh page.
     */
    public void refreshPage() {
        logger().info("Refreshing the page");
        driver.navigate().refresh();
        switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Click on Expand All
     */
    public void clickOnExpandAll() {
        elementHelper.doClick(xpathTreeExpandAllBtn);
    }

    /**
     * Navigate back to Previous Page
     */
    public void navigateBackToPreviousPage() {
        //This method wil be updated after Bug for double click on browser back button is fixed
        logger().info("Navigating back to the previous page");
        driver.navigate().back();
        driver.navigate().back();
        waitHelper.waitForPageToLoad();
        switchFrameToContent();
    }

    /**
     * Verify if a form exists in the Left Pane Tree
     *
     * @param pageName - the landing page name
     * @param treePath - the form name
     * @return - true if the form page is displayed
     */
    public boolean verifyExistenceOfFormInLeftPaneTree(String pageName, String treePath) {
        navigateToModulePageByName(pageName);
        switchFrameToDefault();
        waitHelper.waitForElementClickable(xpathTreeExpandAllBtn);
        elementHelper.doClick(xpathTreeExpandAllBtn);
        var finalXpath = getXpathForLeftPaneTreeItem(treePath);
        return elementHelper.isElementDisplayed(By.xpath(finalXpath));
    }

    /**
     * Get current URL
     *
     * @return Url of the page
     */
    public String getURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Method to switch to a different window after clicking on a hyperLink
     */
    public void switchToDifferentWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }
    }

    /**
     * Method to switch back to Parent Window
     */
    public void switchToParentWindow() {
        Set<String> windows = driver.getWindowHandles();
        driver.switchTo().window(windows.iterator().next());
        switchFrameToContent();
    }

    /**
     * Method to get the xpath of a form in Left Pane tree
     *
     * @param treePath - Path of the form in the tree
     * @return xpath for the form given in the treePath
     */
    public String getXpathForLeftPaneTreeItemAutodesk(String treePath) {
        var xpathOfTreeItem = new StringBuilder();
        var levels = treePath.split(RegexStrings.forwardSlash.getValue());
        xpathOfTreeItem.append(".//a[.='").append(levels[0]).append("']/..");
        return xpathOfTreeItem.toString();
    }

    /**
     * Close the present tab and switch to previous tab (when there are 2 windows)
     *
     * @param driver WebDriver driver
     */
    public void closeCurrentTabAndSwitchToPreviousTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * Switch to a specific frame
     *
     * @param locator locator of the frame
     * @return true on succesful switching
     */
    public boolean switchToSpecificFrame(By locator) {
        try {
            waitHelper.waitForFrameAvailableAndSwitchToIt(locator);
            return true;
        } catch (Exception e) {
            ExceptionHandler.logAndContinueWebDriverExceptions(e, String.format("Exception occurred while switching to %s frame",
                    elementHelper.getLocatorAsString(locator)));
            return false;
        }
    }
}