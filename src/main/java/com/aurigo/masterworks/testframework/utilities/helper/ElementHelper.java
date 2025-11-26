package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElementHelper extends  WaitHelper{

    private WebDriver driver;
    private Actions actions;
    private static final Logger logger = LogManager.getLogger(ElementHelper.class);

    public ElementHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
    }

    /**
     * Gets Element using By locator.
     *
     * @param locator Locator of element to fetch.
     * @return Returns WebElement
     */
    public WebElement getElement(By locator) {
        waitForElementPresent(locator);
        WebElement element;
        element = driver.findElement(locator);
        var flash = EnvironmentHelper.getPropertyValue("elementFlash");
        if (flash.equalsIgnoreCase("yes")) {
            JavaScriptUtil.flash(element, driver);
        }
        return element;
    }

    /**
     * Gets Element using By locator.
     *
     * @param locator Locator of element to fetch.
     * @return Returns WebElement
     */
    public WebElement getElementNoWait(By locator) {
        WebElement element;
        element = driver.findElement(locator);
        var flash = EnvironmentHelper.getPropertyValue("elementFlash");
        if (flash.equalsIgnoreCase("yes")) {
            JavaScriptUtil.flash(element, driver);
        }
        return element;
    }

    /**
     * Gets List of Element
     *
     * @param locator Locators of element to fetch.
     * @return Returns list of WebElement.
     */
    public List<WebElement> getElements(By locator) {
        List<WebElement> elements;
        elements = driver.findElements(locator);
        return elements;
    }

    /**
     * Clicks WebElement using locator.
     *
     * @param locator By locator of the element to click.
     */
    public void doClick(By locator) {
        try {
            var element = getElement(locator);
            if (!isElementDisplayed(locator)) {
                scrollToView(element);
            }
            element.click();
        } catch (Exception e) {
            logger().info("Locator not found : " + locator);
            logger().info("Some exception occurred while clicking on webelement " + e);
            throw e;
        }
    }

    /**
     * Clicks WebElement using locator without scrolling to element.
     *
     * @param locator By locator of the element to click.
     */
    public void doClickNoScroll(By locator) {
        try {
            var element = getElement(locator);
            element.click();
        } catch (Exception e) {
            logger().info("Locator not found : " + locator);
            logger().info("Some exception occurred while clicking on web element " + e);
            throw e;
        }
    }

    /**
     * Clicks WebElement using locator.
     *
     * @param locator By locator of the element to click.
     */
    public void doClickWithRetry(By locator) {
        int maximumRetryAttempts = 3;//Should come from config.properties
        boolean actionCompleted;
        do {
            --maximumRetryAttempts;
            try {
                var element = getElement(locator);
                scrollToView(element);
                element.click();
                actionCompleted = true;
            } catch (Exception e) {
                actionCompleted = false;
                logger().info("Locator not found : " + locator);
                logger().info("Some exception occurred while clicking on webelement " + e);
                if (maximumRetryAttempts <= 0)
                    throw e;
            }
        }
        while (actionCompleted);
    }

    /**
     * Submits WebElement using locator.
     *
     * @param locator By locator of the element to be submitted
     */
    public void doSubmit(By locator) {
        try {
            var element = getElement(locator);
            scrollToView(element);
            element.submit();
        } catch (Exception e) {
            logger().info("Locator not found : " + locator);
            logger().info("Some exception occurred while clicking on web element " + e);
            throw e;
        }
    }

    /**
     * Clicks Label following a checkbox.
     *
     * @param checkboxLocator By locator of the element to click.
     */
    public void doClickCheckboxLabelByJS(By checkboxLocator) {
        try {
            var element = getElement(checkboxLocator).findElement(By.xpath("./following-sibling::label"));
            scrollToView(element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click(true);", element);
        } catch (Exception e) {
            logger().info("Locator not found : " + checkboxLocator);
            logger().info("Some exception occurred while clicking on webelement " + e);
            throw e;
        }
    }

    /**
     * Is element displayed (checks Label following an element (for checkbox)).
     *
     * @param locator By locator of the element to check.
     * @return True if displayed, else false.
     */
    public boolean isElementDisplayedByLabel(By locator) {
        try {
            var element = getElement(locator).findElement(By.xpath("./following-sibling::label"));
            scrollToView(element);
            return element.isDisplayed();
        } catch (Exception e) {
            logger().info("Locator not found : " + locator);
            logger().info("Some exception occurred while checking displayed " + e);
            throw e;
        }
    }

    /**
     * Clicks WebElement.
     *
     * @param element Webelement.
     */
    public void doClick(WebElement element) {
        try {
            scrollToView(element);
            element.click();
        } catch (Exception e) {
            logger().info("Some exception occurred while clicking on webelement " + e);
            throw e;
        }
    }

    /**
     * Clicks WebElement using Actions.
     *
     * @param locator By locator of the element to click.
     */
    public void doClickUsingActions(By locator) {
        try {
            var element = getElement(locator);
            scrollToView(element);
            actions.click(element).perform();
        } catch (Exception e) {
            logger().info("Locator not found : " + locator);
            logger().info("Some exception occurred while clicking on webelement " + e);
            throw e;
        }
    }

    /**
     * Clicks WebElement using Actions.
     *
     * @param element Element to click.
     */
    public void doClickUsingActions(WebElement element) {
        try {
            scrollToView(element);
            actions.click(element).perform();
        } catch (Exception e) {
            logger().info("Element not found.");
            logger().info("Some exception occurred while clicking on webelement " + e);
            throw e;
        }
    }

    /**
     * Double clicks Webelement using locator.
     *
     * @param locator By locator of the element to double click.
     */
    public void doDoubleClick(By locator) {
        var element = getElement(locator);
        scrollToView(element);
        actions.doubleClick(element).perform();
    }

    /**
     * Double click Webelement.
     *
     * @param element Webelement to be clicked.
     */
    public void doDoubleClick(WebElement element) {
        scrollToView(element);
        actions.doubleClick(element).perform();
    }


    /**
     * Function to get inner HTML text of the element
     *
     * @param webElement web element whose inner HTML is required
     * @return inner HTML of the web element
     */
    public String doGetInnerHtml(WebElement webElement) {
        scrollToView(webElement);
        return webElement.getAttribute("innerHTML").trim();
    }

    /**
     * Click and hold using locator.
     *
     * @param locator Locator of the element.
     */
    public void doClickAndHold(By locator) {
        var element = getElement(locator);
        scrollToView(element);
        element.click();
        actions.clickAndHold(element).perform();
        if (getElement(locator) != null)
            actions.moveToElement(element).release().build().perform();
        else
            actions.release().build().perform();

        waitForPageToLoad();
    }

    /**
     * Context clicks Webelement.
     *
     * @param element element to be context clicked.
     */
    public void doContextClick(WebElement element) {
        scrollToView(element);
        actions.contextClick(element).perform();
    }

    /**
     * Context clicks Webelement using locator.
     *
     * @param locator By locator of the element to double click.
     */
    public void doContextClick(By locator) {
        var element = getElement(locator);
        scrollToView(element);
        actions.contextClick(element).perform();
    }

    /**
     * Send Keys on the WebElement using locator.
     *
     * @param locator Webelement locator.
     * @param value   Value to be sent.
     */
    public void doSendKeys(By locator, String value) {
        var element = getElement(locator);
        scrollToView(element);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement using locator for Double DataType
     *
     * @param locator Webelement locator.
     * @param value   Value to be sent.
     */
    public void doSendKeys(By locator, Double value) {
        var element = getElement(locator);
        scrollToView(element);
        element.clear();
        element.sendKeys(value.toString());
    }

    /**
     * Performing keyboard key presses
     *
     * @param keyToPress key to be pressed
     */
    public void doSendKeys(Keys keyToPress) {
        actions.sendKeys(keyToPress).perform();
    }

    /**
     * Send Keys on the WebElement using locator.
     *
     * @param locator Webelement locator.
     * @param value   Value to be sent.
     */
    public void appendText(By locator, String value) {
        var element = getElement(locator);
        scrollToView(element);
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement using locator.
     *
     * @param locator Webelement locator.
     * @param value   Value to be sent.
     */
    public void appendText(By locator, Keys value) {
        var element = getElement(locator);
        scrollToView(element);
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement using locator.
     *
     * @param element -  Webelement locator.
     * @param value   Value to be sent.
     */
    public void appendText(WebElement element, String value) {
        scrollToView(element);
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement.
     *
     * @param element Webelement.
     * @param value   Value to be sent.
     */
    public void doSendKeys(WebElement element, String value) {
        scrollToView(element);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement using locator.
     *
     * @param locator Webelement locator.
     * @param value   Value to be sent.
     */
    public void doSendKeys(By locator, Keys value) {
        var element = getElement(locator);
        scrollToView(element);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Send Keys on the WebElement using actions.
     *
     * @param value Value to be sent.
     */
    public void doSendKeysUsingAction(String value) {
        actions.sendKeys(value).perform();
    }

    /**
     * Send value using Actions
     *
     * @param locator - element locator where value is to be entered
     * @param value   - the value in string to be entered
     */
    public void doSendKeysUsingAction(By locator, String value) {
        doClickUsingActions(locator);
        actions.sendKeys(value).perform();
    }


    /**
     * Send value using Actions
     *
     * @param element - element locator where value is to be entered
     * @param value   - the value in string to be entered
     */
    public void doSendKeysUsingAction(WebElement element, String value) {
        actions.doubleClick(element).perform();
        actions.sendKeys(value).perform();
    }


    /**
     * Send value using Actions
     *
     * @param locator - element locator where value is to be entered
     * @param value   - the value in double to be entered
     */
    public void doSendKeysUsingAction(By locator, Double value) {
        doClickUsingActions(locator);
        actions.sendKeys(value.toString()).perform();
    }

    /**
     * Selects combo box item by test using locator of element.
     *
     * @param locator Locators of the element.
     * @param value   Value of the drop down.
     */
    public String selectComboBoxItemByText(By locator, String value) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        comboBoxContent.selectByVisibleText(value);
        waitForPageToLoad();
        return getFirstSelectedOption(locator);
    }

    /**
     * Selects combo box item by test using locator of element, without wait
     *
     * @param locator Locators of the element.
     * @param value   Value of the drop down.
     */
    public void selectComboBoxItemByTextWithoutWait(By locator, String value) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        comboBoxContent.selectByVisibleText(value);
    }

    /**
     * Selects combo box item by test using locator of element.
     *
     * @param locator Locators of the element.
     * @param value   Value of the drop down.
     */
    public void selectComboBoxItemByValue(By locator, String value) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        comboBoxContent.selectByValue(value);
        waitForPageToLoad();
    }

    /**
     * Selects combo box item by test using locator of element.
     *
     * @param locator  Locators of the element.
     * @param moduleID Value of the drop down.
     */
    public String selectComboBoxItemByModule(By locator, String moduleID) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        var optionToSelect = comboBoxContent.getOptions().stream().filter(o -> o.getAttribute("value").equals(moduleID)).findFirst().orElse(null);
        if (optionToSelect != null) {
            optionToSelect.click();
        }
        waitForPageToLoad();
        return getFirstSelectedOption(locator);
    }

    /**
     * The first selected option in select tag / the currently selected option in a normal select.
     *
     * @param locator Locators of the element
     * @return return the selected option
     */
    public String getFirstSelectedOption(By locator) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        return comboBoxContent.getFirstSelectedOption().getText();
    }

    /**
     * The first selected option in select tag / the currently selected option in a normal select.
     *
     * @param element element
     * @return return the selected option
     */
    public String getFirstSelectedOption(WebElement element) {
        Select comboBoxContent = new Select(element);
        scrollToView(element);
        return comboBoxContent.getFirstSelectedOption().getText();
    }

    /**
     * The select options in a normal select
     *
     * @param locator Locators of the elements
     * @return returns the select options in a normal select
     */
    public List<WebElement> getComboBoxSelectOptions(By locator) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        return comboBoxContent.getOptions();
    }

    /**
     * The select options in a normal select
     *
     * @param locator Locators of the elements
     * @return returns the select options in a normal select
     */
    public List<String> getSelectOptions(By locator) {
        List<WebElement> dropdownOptions = getComboBoxSelectOptions(locator);
        List<String> options = new ArrayList<>();
        for (int i = 0; i < dropdownOptions.size(); i++) {
            String value = doGetText(dropdownOptions.get(i));
            logger().info("Option - " + value);
            if (!value.isEmpty()) {
                options.add(value);
            }
        }
        return options;
    }

    /**
     * Selects combo box item by index using locator of element.
     *
     * @param locator Locators of the element.
     * @param index   Value of the drop down.
     * @return Value at index
     */
    public String selectComboBoxItemByIndex(By locator, int index) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        comboBoxContent.selectByIndex(index);
        return getFirstSelectedOption(locator);
    }

    /**
     * Selects combo box item by index using element.
     *
     * @param element element.
     * @param index   Value of the drop down.
     * @return Value at index
     */
    public String selectComboBoxItemByIndex(WebElement element, int index) {
        Select comboBoxContent = new Select(element);
        scrollToView(element);
        comboBoxContent.selectByIndex(index);
        return getFirstSelectedOption(element);
    }

    /**
     * Do Get Text for element.
     *
     * @param locator Locator of element.
     * @return Returns text value.
     */
    public String doGetText(By locator) {
        var element = getElement(locator);
        scrollToView(element);
        return element.getText().trim();
    }

    /**
     * Selects combo box item by text using locator of element.
     *
     * @param locator Locators of the element.
     * @param value   Value of the drop down.
     */
    public void selectComboBoxItemByTextWithoutReturningSelectedOption(By locator, String value) {
        Select comboBoxContent = new Select(getElement(locator));
        scrollToView(getElement(locator));
        comboBoxContent.selectByVisibleText(value);
        waitForPageToLoad();
    }

    /**
     * Do Get Text for element.
     *
     * @param webElement Locator of element.
     * @return Returns text value.
     */
    public String doGetText(WebElement webElement) {
        scrollToView(webElement);
        return webElement.getText().trim();
    }


    /**
     * Get Attribute of Element.
     *
     * @param locator       Locator of element.
     * @param attributeName Name of the element.
     * @return Returns attribute string value.
     */
    public String doGetAttribute(By locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    /**
     * Check if element is displayed
     *
     * @param locator Locator of element to be checked.
     * @return Returns true if element is displayed else false.
     */
    public boolean isElementDisplayed(By locator) {
        try {
            var element = driver.findElement(locator);
            boolean isDisplayed = element.isDisplayed();
            logger().info("Is Element " + getLocatorAsString(locator) + isDisplayed);
            return isDisplayed;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method to check if an attribute is present in a locator or not
     *
     * @param locator   Locator to check
     * @param attribute Name of the attribute to check if present
     * @return True if attribute present
     */
    public boolean isAttributePresent(By locator, String attribute) {
        return doGetAttribute(locator, attribute) != null;
    }

    /**
     * Check if element is enabled
     *
     * @param locator Locator of element to be checked.
     * @return Returns true if element is enabled else false.
     */
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (ElementNotInteractableException e) {
            return false;
        }
    }

    /**
     * Get Element By Index
     *
     * @param locator Locator of the element.
     * @param index   Index for element to get.
     * @return Returns element in the given index.
     */
    public WebElement getElementByIndex(By locator, int index) {
        waitForElementPresent(locator);

        List<WebElement> element = null;
        try {
            element = driver.findElements(locator);
        } catch (Exception e) {
            logger().info("Some exception occurred while creating webelement " + locator);
        }
        return element.get(index);
    }

    /**
     * Scroll Element to view.
     *
     * @param element Webelement to scroll to.
     */
    public void scrollToView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        }
        catch (Exception e){
            logger.info("Unable to scroll to view");
        }
    }

    /**
     * Clearing text from a text box
     *
     * @param locator text box locator
     */
    public void clearText(By locator) {
        getElement(locator).clear();
    }

    /**
     * Scroll Element to view.
     *
     * @param locator By locator for element to scroll to.
     */
    public void scrollToView(By locator) {
        scrollToView(getElement(locator));
    }

    /**
     * Move to Element with offset
     *
     * @param locator Locator of element to scroll.
     * @param offset  Offset to scroll.
     */
    public void moveToElementWithOffset(By locator, int offset) {
        actions.moveToElement(getElement(locator)).clickAndHold().moveByOffset(0, offset).release().perform();
    }

    /**
     * Move to element using Action.
     *
     * @param element -   Desired element.
     */
    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    /**
     * Get Child Element using parent element.
     *
     * @param childElement child element.
     * @return parent element.
     */
    public WebElement getParentElement(WebElement childElement) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", childElement);
    }

    /**
     * Method to return a locator as String. This method can be modified based on requirements
     *
     * @param locator
     * @return string value of the locator
     */

    public String getLocatorAsString(By locator) {
        return locator.toString().substring(locator.toString().indexOf(":") + 1).trim();
    }

    /**
     * Verify if a checkbox is checked
     *
     * @param locator - The locator of the checkbox
     * @return -true if the checkbox is selected, false otherwise
     */
    public boolean isCheckBoxSelected(By locator) {
        return isCheckBoxSelected(driver.findElement(locator));
    }

    /**
     * Verify if a checkbox is checked
     *
     * @param element -  Web element to be checked
     * @return - true if the checkbox is selected, false otherwise
     */
    public boolean isCheckBoxSelected(WebElement element) {
        try {
            boolean checkedStatus = element.isSelected();
            return checkedStatus;
        } catch (ElementNotInteractableException e) {
            logger().info("Checkbox cannot be checked");
            return false;
        }
    }

    /**
     * Drag and drop the element using action.
     *
     * @param destination -   Destination to which the source needs to be dragged.
     * @param source      -   Element which needs to be dragged.
     */
    public void dragAndDrop(By source, By destination) {
        actions.dragAndDrop(getElement(source), getElement(destination)).perform();
    }

    /**
     * Method to check whether an element is Disabled or Not
     *
     * @param element -  By locator element to be checked if disabled
     * @return true if disabled
     */
    public boolean isElementDisabled(By element) {
        return doGetAttribute(element, "class").contains("rrbDisabled");
    }

    /**
     * Method to check whether an element is Disabled or Not
     *
     * @param element -  By locator element to be checked if disabled
     * @return true if disabled
     */

    public boolean isElementDisabledNonRibbon(By element) {
        String value = doGetAttribute(element, "disabled");
        return value.equals("disabled")||value.equals("true");
    }

    /**
     * Scroll to element in the Unit price search Results Grid
     *
     * @param element Element to be scrolled till
     */
    public void scrollToViewInGrid(WebElement element) {
        Coordinates coordinate = ((Locatable) element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
    }

    /**
     * Method to get the Title
     *
     * @return - Title as String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * The method help to set the text in the text-field if the element is visible
     *
     * @param locator -field locator
     * @param value   -to set the value
     */
    public void waitAndSetText(By locator, String value, long... time) {
        WebElement element;
        if (time.length > 0) {
            element = explicitWait(time[0]).until(ExpectedConditions.visibilityOf(getElement(locator)));
        } else {
            element = explicitWait().until(ExpectedConditions.visibilityOf(getElement(locator)));
        }
        element.clear();
        element.sendKeys(value);
    }

    /**
     * The method help to get the list of text
     *
     * @param locator -field locator
     */
    public List<String> getListOfText(By locator) {
        List<WebElement> allElements = getElements(locator);
        List<String> allText = new ArrayList<>();
        for (WebElement webElement : allElements) {
            allText.add(webElement.getText());
        }
        return allText;
    }

    /**
     * This method help to get the text if the element visible
     *
     * @param locator to get the text of the element
     * @return text of the locator
     */
    public String waitAndGetText(By locator) {
        return explicitWait().until(ExpectedConditions.visibilityOf(getElement(locator))).getText();
    }

    /**
     * Used to get the current window
     *
     * @return current opened window handle
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Used to get list of opened windows
     *
     * @return set of opened windows handles
     */
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Scroll Element to view.
     *
     * @param element Webelement to scroll to.
     */
    public void scrollToElementView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /**
     * Select combobox item by text without validation (while editing already existing value)
     *
     * @param locator        locator
     * @param optionToSelect optionToSelect
     */
    public void selectComboBoxItemByTextWithoutValidation(By locator, String optionToSelect) {
        new Select(getElement(locator)).selectByVisibleText(optionToSelect);
    }

    /**
     * This method is used to select and drag the element
     * @param source        - source locator
     * @param destination   - destination locator
     */
    public void clickAndHoldAndDragTheElement(By source,By destination){
        actions.clickAndHold(getElement(source)).moveToElement(getElement(destination)).release(getElement(destination)).build().perform();
    }
}
