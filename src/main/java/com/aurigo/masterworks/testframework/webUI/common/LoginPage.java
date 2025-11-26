package com.aurigo.masterworks.testframework.webUI.common;

import com.aurigo.masterworks.testframework.utilities.DriverManager;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {


    private By emailId;
    private By passwordField;
    private By loginButton;
    private By errorMessage;
    private By buildVersion;
    private By logOutAIS;
    private By yesButtonForAISLogOut;
    private By noButtonForAISLogOut;
    private By pickAnAccount;
    private By externalSignIn;
    private By azureEnterCodePage;
    private By signInOtherWay;
    private By cancelButton;
    private By useAnotherAccount;
    private By emailTextField;
    private By passwordTextField;
    private By nextButton;
    private By adfsLoginUserNameField;
    private By adfsLoginPasswordField;
    private By adfsLoginSignInButton;
    private By staySignedInPage;
    private By errorLogin;
    private By errorAdfsLogin;
    private By errorMasterworksLogin;
    private By errorAdfs4Login;
    private By verifyButton;
    private By newUserLnk;
    private By loginError;
    private By loginErrorAfterMaxPasswordAttempts;
    private By unsuccessfulLoginAttemptsErrorMessage;
    private By redirectedPageName;

    private String accountNameTemplate = "//div[@data-test-id = '%s']/div";
    private String accountSelectionTemplate = "//input[@type = 'submit' and  @value = '%s']";
    //constructor of page class:
    public LoginPage(WebDriver driver)
    {
        super(driver);
        var locators = LocatorUtil.getLocators("LoginPage.json");
        emailId = locators.get("emailId");
        passwordField = locators.get("password");
        loginButton = locators.get("loginButton");
        errorMessage = locators.get("errorMessage");
        buildVersion = locators.get("BuildVersion");
        logOutAIS = locators.get("logOutAIS");
        yesButtonForAISLogOut = locators.get("yesButtonForAISLogOut");
        noButtonForAISLogOut = locators.get("noButtonForAISLogOut");
        pickAnAccount = locators.get("pickAnAccount");
        externalSignIn = locators.get("externalSignIn");
        azureEnterCodePage = locators.get("azureEnterCodePage");
        signInOtherWay = locators.get("signInOtherWay");
        cancelButton = locators.get("cancelButton");
        useAnotherAccount = locators.get("useAnotherAccount");
        emailTextField = locators.get("emailTextField");
        passwordTextField = locators.get("passwordTextField");
        nextButton = locators.get("nextButton");
        adfsLoginUserNameField = locators.get("adfsLoginUserNameField");
        adfsLoginPasswordField = locators.get("adfsLoginPasswordField");
        adfsLoginSignInButton = locators.get("adfsLoginSignInButton");
        staySignedInPage = locators.get("staySignedInPage");
        errorLogin = locators.get("errorLogin");
        errorAdfsLogin = locators.get("errorAdfsLogin");
        errorMasterworksLogin = locators.get("errorMasterworksLogin");
        errorAdfs4Login = locators.get("errorAdfs4Login");
        verifyButton = locators.get("verifyButton");
        newUserLnk = locators.get("newUserLnk");
        loginError = locators.get("loginError");
        loginErrorAfterMaxPasswordAttempts = locators.get("loginErrorAfterMaxPasswordAttempts");
        unsuccessfulLoginAttemptsErrorMessage = locators.get("unsuccessfulLoginAttemptsErrorMessage");
        redirectedPageName = locators.get("redirectedPageName");
    }


    /**
     * @param username - username
     * @param password - password
     * @return true if login is successful
     * Logs in to Masterworks and returns true after a successful login
     */
    public boolean doLogin(String username, String password) {
        boolean isLoaded;
        Stopwatch stopwatch = Stopwatch.createStarted();
        try{
            login(username, password);
            waitHelper.waitForPageToLoad();
        }
        catch (Exception exception){
            stopwatch.stop();
            logger().fail(String.format("Page load failed after : %d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
            throw exception;
        }
        isLoaded = getPage(LandingPage.class).isPageLoaded();
        stopwatch.stop();
        logger().pass(String.format("Time taken for login : %d seconds", stopwatch.elapsed(TimeUnit.SECONDS)));
        return isLoaded;
    }

    /***
     * Method to click on New User Link located in LoginPage
     */
    public void doClickNewUserLink() {
        logger().info("Clicking on NewUser Link");
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(newUserLnk);
        elementHelper.doClick(newUserLnk);
    }

    /**
     * This method is used to get error message from Login Page
     *
     * @return Error Message
     */

    public String getErrorMessage() {
        return elementHelper.getElement(errorMessage).getText();
    }

    /**
     * This method is used to get  Build Version
     *
     * @return Build Version
     */
    public String getBuildVersion() {
        return elementHelper.getElement(buildVersion).getText();
    }

    /**
     * Method for Login Function(used inside dologin method)
     *
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        logger().info("Logging in as " + username);
        waitHelper.waitForElementPresent(emailId);
        elementHelper.doSendKeys(emailId, username);
        waitHelper.waitForElementPresent(passwordField);
        elementHelper.doSendKeys(passwordField, password);
        elementHelper.doClick(loginButton);
    }

    /**
     * Validate the login field
     *
     * @return true if username and passwords are present
     */
    public boolean validateLoginFields() {
        boolean userName = getPage(Validations.class).verifyElementExists(emailId);
        boolean passwordCheck = getPage(Validations.class).verifyElementExists(passwordField);
        return (userName && passwordCheck);
    }

    /**
     * Verify default data of login fields
     *
     * @return true if username and passwords are not populated by default
     */
    public boolean verifyLoginDetails() {
        var email = elementHelper.doGetText(emailId);
        var pwd = elementHelper.doGetText(passwordField);
        return (email.length() == 0) && (pwd.length() == 0);
    }

    /**
     * Validate the login field
     *
     * @return true if username and passwords are present
     */
    public boolean validateMixedLoginFields() {
        boolean userName = getPage(Validations.class).verifyElementExists(emailId);
        boolean passwordCheck = getPage(Validations.class).verifyElementExists(passwordField);
        boolean loginButtonCheck = getPage(Validations.class).verifyElementExists(loginButton);
        boolean adfsSignInCheck = getPage(Validations.class).verifyElementExists(externalSignIn);
        return (userName && passwordCheck && loginButtonCheck && adfsSignInCheck);
    }

    /**
     * Is log out present for AIS
     *
     * @return true if present
     */
    public boolean isLogOutFromAISPresent() {
        return getPage(Validations.class).verifyElementExists(logOutAIS);
    }

    /**
     * Log out from AIS
     *
     * @param logOutOption yes to log out and NO to not log out
     */
    public void logOutFromAIS(boolean logOutOption) {
        if (logOutOption) {
            elementHelper.doClick(yesButtonForAISLogOut);
            waitHelper.waitForPageToLoad();
        } else {
            elementHelper.doClick(noButtonForAISLogOut);
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * is pick an account page present
     *
     * @return true if present
     */
    public boolean isPickAnAccountPresent() {
        return getPage(Validations.class).verifyElementExists(pickAnAccount);
    }

    /**
     * Select account
     *
     * @param accountName name of the account to select
     */
    public void selectAccount(String accountName) {
        if (!accountName.isEmpty()) {
            elementHelper.doClick(By.xpath(String.format(accountSelectionTemplate, accountName)));
            waitHelper.waitForPageToLoad();
        } else {
            elementHelper.doClick(By.xpath(String.format(accountSelectionTemplate, "Select another account")));
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * Click on external Sign in
     */
    public void clickOnExternalLogin() {
        waitHelper.waitForElementToBePresentAndClickable(externalSignIn);
        elementHelper.doClick(externalSignIn);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Azure logging
     *
     * @param emailID  email ID credentials
     * @param password password ID credentials
     */
    private void azureLogging(String emailID, String password) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(azureEnterCodePage);
        waitHelper.waitForElementToBePresentAndClickable(verifyButton);
        waitHelper.waitForElementToBePresentAndClickable(signInOtherWay);
        elementHelper.doClick(signInOtherWay);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(cancelButton);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(useAnotherAccount);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementToBePresentAndClickable(emailTextField);
        elementHelper.doSendKeys(emailTextField, emailID);
        elementHelper.doClick(nextButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(passwordTextField);
        elementHelper.doSendKeys(passwordTextField, password);
        waitHelper.waitForElementToBePresentAndClickable(nextButton);
        elementHelper.doClick(nextButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Azure login
     *
     * @param emailID  email ID credentials
     * @param password password ID credentials
     */
    public void azureLogin(String emailID, String password) {

        azureLogging(emailID, password);
        if (elementHelper.isElementDisplayed(staySignedInPage)) {
            elementHelper.doClick(nextButton);
            waitHelper.waitForPageToLoad();
        }
    }

    /**
     * Azure External IDP Login
     *
     * @param emailID  email ID of the user
     * @param password password of the user
     */
    public void azureErrorLogin(String emailID, String password) {
        azureLogging(emailID, password);
    }

    /**
     * Azure log Out
     *
     * @param emailId email id
     */
    public void azureLogOut(String emailId) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(By.xpath(String.format(accountNameTemplate, emailId)));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method for adfs login
     *
     * @param emailID  email id of the user
     * @param password password of the user
     */
    public void adfsLogin(String emailID, String password) {
        elementHelper.doSendKeys(adfsLoginUserNameField, emailID);
        elementHelper.doSendKeys(adfsLoginPasswordField, password);
        elementHelper.doClick(adfsLoginSignInButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Error login
     *
     * @return equal if error login text is equal
     */
    public boolean getErrorLogin() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(errorLogin);
        return elementHelper.doGetText(errorLogin).equals(Constants.AZURE_LOGIN_ERROR);
    }

    /**
     * Error login
     *
     * @return equal if error login text is equal
     */
    public boolean getErrorADFSLogin() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(errorAdfsLogin);
        return elementHelper.doGetText(errorAdfsLogin).equals(Constants.ADFS_LOGIN_ERROR);
    }

    /**
     * Error login
     *
     * @return equal if error login text is equal
     */
    public boolean getErrorADFS4Login() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(errorAdfs4Login);
        return elementHelper.doGetText(errorAdfs4Login).equals(Constants.ADFS_LOGIN_ERROR);
    }

    /**
     * Error login
     *
     * @return equal if error login text is equal
     */
    public boolean getErrorMasterworksLogin() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(errorMasterworksLogin);
        return elementHelper.doGetText(errorMasterworksLogin).equals("Invalid User Name or Password. 3 attempts remaining.");
    }

    /***
     * Method Validates Error Message On login page
     *
     * @param errorMessage Expected Error Message
     * @return - true - if error matches expected error
     */
    public boolean validateErrorMessageOnLogin(String errorMessage) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(loginError);
        return elementHelper.doGetText(loginError).equals(errorMessage);
    }

    /***
     * Method to get error message on exceeding Max Password Attempts
     *
     * @return true/false - based on the validation
     */
    public boolean getMaxPasswordAttemptErrorMsg() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(loginErrorAfterMaxPasswordAttempts);
    }

    /***
     * Method to get error message on unsuccessfull Password Attempt
     *
     * @param attempts Attempts remaining
     * @return true/false - based on the validation
     */
    public boolean getUnsuccessfullAttemptErrorMsg(int attempts) {
        String unsuccessfulAttemptsMsg = String.format("Error: Incorrect User Name or Password. %s attempts remaining", attempts);
        String unsuccessfulAttemptMsg = String.format("Error: Incorrect User Name or Password. %s attempt remaining", attempts);
        waitHelper.waitForPageToLoad();
        String actualErrorMessage = elementHelper.doGetText(unsuccessfulLoginAttemptsErrorMessage);
        return attempts>1 ? actualErrorMessage.contains(unsuccessfulAttemptsMsg) : actualErrorMessage.contains(unsuccessfulAttemptMsg);
    }

    /**
     * Validation of the Password field for Masking
     *
     * @return true if the validation is successfull
     */
    public boolean validatePasswordField() {
        String attributeValue1 = elementHelper.doGetAttribute(passwordField, "type");
        return attributeValue1.contains("password");
    }

    /**
     * This method is used to navigate to different url
     *
     * @param url navigation URL
     */
    public void navigateRandomURL(String url) {
        logger().info("Navigate to--->"+url);
        DriverManager.getDriver().get(url);
        waitHelper.waitForElementPresent(passwordField);
    }

    /**
     * This method is used to get the name of redirected page
     *
     * @return redirected page name
     */
    public String verifyRedirectedPage() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(redirectedPageName);
        return elementHelper.waitAndGetText(redirectedPageName);
    }
}
