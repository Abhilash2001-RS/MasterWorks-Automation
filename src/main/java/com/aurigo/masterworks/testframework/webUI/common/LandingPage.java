package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.ScreenshotHelper;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class LandingPage extends RibbonMenu {

    private WebDriver driver;
    private By enterpriseDashboardTab;
    private By usernameLink;
    private By logoutLink;
    private By viewProfileLink;
    private By changePasswordLink;
    private By helpLink;
    private By aboutLink;
    private By aboutPopupContainer;
    private By aboutPopupLogo;
    private By aboutPopupBuildVersion;
    private By aboutPopupCloseButton;


    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        var locators = LocatorUtil.getLocators("LandingPage.json");
        enterpriseDashboardTab = locators.get("enterpriseDashboardTab");
        usernameLink = locators.get("usernameLink");
        logoutLink = locators.get("logoutLink");
        viewProfileLink = locators.get("viewProfileLink");
        changePasswordLink = locators.get("changePasswordLink");
        helpLink = locators.get("helpLink");
        aboutLink = locators.get("aboutLink");
        aboutPopupContainer = locators.get("aboutPopupContainer");
        aboutPopupLogo = locators.get("aboutPopupLogo");
        aboutPopupBuildVersion = locators.get("aboutPopupBuildVersion");
        aboutPopupCloseButton = locators.get("aboutPopupCloseButton");

    }


    public void navigateTo() {
        logger().info("Navigating to Home page");
        navigation.navigateToModule("Home", enterpriseDashboardTab);
    }

    /**
     * Method to validate whether the opening of the 'About' popup
     *
     * @param buildVersion build version for validation
     * @return true if the popup is opened and validation is successfully
     */
    public SoftAssert validateAboutPopup(String buildVersion) {
        logger().info("Validating About Popup");
        logger().info("Clicking on About");
        var softAssert = new SoftAssert();
        navigation.switchFrameToDefault();
        waitHelper.waitForElementClickable(usernameLink);
        elementHelper.doClick(usernameLink);
        waitHelper.waitForElementClickable(viewProfileLink);
        waitHelper.waitForPageToLoad(aboutLink);
        elementHelper.scrollToView(aboutLink);
        elementHelper.doClick(aboutLink);
        waitHelper.waitForPageToLoad();
        getPage(ScreenshotHelper.class).takeFullScreenshot("Current Screen");
        navigation.switchFrameToDefault();
        softAssert.assertTrue(elementHelper.isElementDisplayed(aboutPopupContainer));
        softAssert.assertTrue(elementHelper.isElementDisplayed(aboutPopupLogo));
        var buildVersionFromUi = elementHelper.waitAndGetText(aboutPopupBuildVersion);
        logger().info("Build version from ui= " + buildVersionFromUi + " Expected build version= " + buildVersion);
        softAssert.assertEquals(buildVersionFromUi, buildVersion, "Validating build version in the popup");
        getPage(ScreenshotHelper.class).takeElementScreenshot(aboutPopupContainer, "About Popup");
        softAssert.assertTrue(elementHelper.isElementDisplayed(aboutPopupCloseButton) && elementHelper.isElementEnabled(aboutPopupCloseButton), "Validating close button");
        logger().info("Closing the popup");
        elementHelper.doClick(aboutPopupCloseButton);
        softAssert.assertFalse(elementHelper.isElementDisplayed(aboutPopupContainer), "Validating closing of popup");
        getPage(ScreenshotHelper.class).takeFullScreenshot("Current Screen");
        return softAssert;
    }

    /**
     * Method to perform logout from the application
     */
    public void logout() {
        navigation.switchFrameToDefault();
        if (!driver.getCurrentUrl().toLowerCase().contains("login")) {
            waitHelper.waitForPageToLoad(usernameLink);
            elementHelper.doClickNoScroll(usernameLink);
            waitHelper.waitForPageToLoad(logoutLink);
            elementHelper.doClickNoScroll(logoutLink);
        } else {
            logger().info("Already at the login page!");
        }
    }

    /**
     * Logout SystemAdmin from the application
     */
    public void logoutSystemAdmin() {
        navigation.switchFrameToDefault();
        if (!driver.getCurrentUrl().toLowerCase().contains("login")) {
            waitHelper.waitForElementClickable(usernameLink);
            elementHelper.doClick(usernameLink);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementClickable(helpLink);
            waitHelper.waitForElementToBePresentAndClickable(logoutLink);
            elementHelper.scrollToView(logoutLink);
            elementHelper.doClick(logoutLink);
            waitHelper.waitForPageToLoad();
        } else {
            logger().fail("Already at the login page!");
        }
    }

    /**
     * Todo: Make efficient login flow
     * Method to fetch the first name of the current user from UI
     *
     * @return first name of the current user
     */
    public String getLoggedInUserName() {
        navigation.switchFrameToDefault();
        String loggedInUsername;
        waitHelper.waitForElementClickable(usernameLink);
        loggedInUsername = elementHelper.doGetText(usernameLink);
        return loggedInUsername;
    }

    /**
     * Method to check whether a user has already logged in
     *
     * @return true if a user is logged in
     */
    public boolean isLoggedIn() {
        navigation.switchFrameToDefault();
        return elementHelper.isElementDisplayed(usernameLink);
    }

    /**
     * Method to check whether the Landing page is loaded or not
     *
     * @return true if the landing page is loaded and visible
     */
    public boolean isPageLoaded() {
        navigation.switchFrameToContent();
        return elementHelper.isElementDisplayed(enterpriseDashboardTab);
    }

    /**
     * Select View Profile
     */
    public void selectViewProfile() {
        navigation.switchFrameToDefault();
        waitHelper.waitForPageToLoad(usernameLink);
        elementHelper.doClick(usernameLink);
        waitHelper.waitForElementToBePresentAndClickable(viewProfileLink);
        elementHelper.doClick(viewProfileLink);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
    }

    /***
     * Clicks Change Password
     */
    public void clickChangePassword() {
        navigation.switchFrameToDefault();
        waitHelper.waitForPageToLoad(usernameLink);
        elementHelper.doClick(usernameLink);
        waitHelper.waitForPageToLoad(changePasswordLink);
        elementHelper.doClick(changePasswordLink);
        navigation.switchFrameToContent();
        elementHelper.moveToElement(elementHelper.getElement(getRibbonIcon(RibbonIcons.Save)));
    }

    /**
     * Click Help link under User details menu.
     */
    public void clickHelp() {
        navigation.switchFrameToDefault();
        elementHelper.doClick(usernameLink);
        waitHelper.waitForPageToLoad(helpLink);
        elementHelper.doClick(helpLink);
    }
}