package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.common.Navigation;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.PickerFilterOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Picker extends BasePage {
    private final String filterOperatorContentXpathTemplate = ".//ul[contains(@class,'k-list')]//li[.='%s']";


    private final By scrollBarHandle;
    private final By filterDropDownList;
    private final By filterOperatorSpan;
    private final By cancelPicker;
    private final By filterButton;
    private final By pickerClearFilterButton;
    private final By multiSelectCheckboxInPicker;

    private final By standardItemsDropDownSave;

    private final By standardItemsDropDownSelect;

    private final By itemPostingPickerSelect;

    private final By bidManagementPickerSelect;

    private final By expensesPickerSelect;

    public Picker(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("Picker.json");
        scrollBarHandle = locators.get("scrollBarHandle");
        filterDropDownList = locators.get("filterDropDownList");
        filterOperatorSpan = locators.get("filterOperatorSpan");
        cancelPicker = locators.get("cancelPicker");
        filterButton = locators.get("filterButton");
        pickerClearFilterButton = locators.get("pickerClearFilterButton");
        multiSelectCheckboxInPicker = locators.get("multiSelectCheckboxInPicker");
        standardItemsDropDownSave = locators.get("standardItemsDropDownSave");
        standardItemsDropDownSelect = locators.get("standardItemsDropDownSelect");
        itemPostingPickerSelect = locators.get("itemPostingPickerSelect");
        bidManagementPickerSelect = locators.get("bidManagementPickerSelect");
        expensesPickerSelect = locators.get("expensesPickerSelect");
    }

    /**
     * Verify given text labels with it's locators are verified.
     *
     * @param labels Locators of elements to check if exists.
     * @return True if no issue, else false.
     */
    public boolean validateElementsExists(List<By> labels) {
        waitHelper.waitForElementPresent(labels.get(0));
        getPage(Validations.class).verifyElementsExists(labels);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Function to verify if the list of header names passed are present:Picker header
     *
     * @param headerNameList list of header names
     * @return true, if all the names passed:list are present in Picker header. Else it returns false
     */
    public boolean verifyPickerHeadersArePresent(List<String> headerNameList) {
        List<By> locators = new ArrayList<>();
        for (String headerName : headerNameList) {
            locators.add(By.xpath(".//a[text()='" + headerName + "']"));
        }
        return validateElementsExists(locators);
    }

    /**
     * Gets Item Labels using table locators.
     *
     * @param tableLocator Locator of the table.
     * @return List of Item Labels.
     */
    private List<WebElement> getPickerItemLabels(By tableLocator) {
        try {
            WebElement table = elementHelper.getElement(tableLocator);
            return table.findElements(By.tagName("td"));
        } catch (NullPointerException e) {
            logger().info("Exception occurred while fetching elements of table - " + elementHelper.getLocatorAsString(tableLocator));
            return new ArrayList<>();
        }
    }

    /**
     * Click Filter Button in Picker Table using columnName
     *
     * @param columnName  Column to filter on
     * @param pickerXpath picker locator
     * @return Returns true of not issue occured, else false.
     */
    public boolean clickFilterButton(String columnName, By pickerXpath) {
        waitHelper.waitForElementClickable(pickerXpath);
        var locator = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//a[.='" + columnName + "']/../a[contains(@class,'k-grid-filter')]/span");

        WebElement filterElement = elementHelper.getElement(locator);
        elementHelper.scrollToView(filterElement);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(filterElement);
        elementHelper.doClick(locator);
        if (!elementHelper.isElementDisplayed(filterDropDownList)) {
            waitHelper.waitForPageToLoad();
            elementHelper.doClick(locator);
        }
        waitHelper.waitForPageToLoad(filterDropDownList);
        return elementHelper.isElementDisplayed(filterDropDownList);
    }

    /**
     * Gets Filtered Row.
     *
     * @param tableLocator  Locator of the table.
     * @param valueToFilter Value to filter on
     * @return Returns filtered row WebElement
     */
    public WebElement getFilteredRow(By tableLocator, String valueToFilter) {
        return getPickerItemLabels(tableLocator).stream()
                .filter(labels -> labels.getText()
                        .equals(valueToFilter)).findAny().get();
    }


    /**
     * Function to Filter and select a Row in the Picker Table
     *
     * @param locatorOfTable Table Main Control ID
     * @param columnName     Column Name to filter
     * @param valueToFilter  Value that needs to be searched
     * @param pickerXpath    picker locator
     * @return True if no issue occurred else false;
     */
    public boolean selectRow(By locatorOfTable, String columnName, String valueToFilter, By pickerXpath) {
        clickFilterButton(columnName, pickerXpath);
        inputValueInFilterField(valueToFilter);
        getFilteredRow(locatorOfTable, valueToFilter).click();
        elementHelper.doClick(
                By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//input[@value = 'Select']")
        );
        return true;
    }

    /**
     * function to check if an element is present in the picker
     *
     * @param locatorOfTable Table Main Control ID
     * @param columnName     Column Name to filter
     * @param valueToFilter  Value that needs to be searched
     * @param pickerXpath    picker locator
     * @return True if no issue occurred else false;
     */
    public boolean isElementPresent(By locatorOfTable, String columnName, String valueToFilter, By pickerXpath) {
        waitHelper.waitForElementClickable(pickerXpath);
        clickFilterButton(columnName, pickerXpath);
        inputValueInFilterField(valueToFilter);
        waitHelper.waitForLoadingSpinnerDisappear();
        return getFilteredRow(locatorOfTable, valueToFilter).isDisplayed();
    }

    /**
     * Function to Filter the Data in the Table
     *
     * @param valueToFilter Value using which we need to filter
     * @return True if no issue, else false.
     */
    public boolean inputValueInFilterField(String valueToFilter) {
        WebElement filterDropDown = elementHelper.getElements(filterDropDownList).stream().filter(WebElement::isDisplayed).findFirst().get();
        elementHelper.waitForPageToLoad();
        try {
            elementHelper.doSendKeys(filterDropDown.findElements(By.tagName("input")).get(0), valueToFilter);
            elementHelper.doClick(filterDropDown.findElement(By.xpath("//button[contains(text(),'Filter')]")));
            return true;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while fetching elements of drop down - " + elementHelper.getLocatorAsString(filterDropDownList));
            return false;
        }
    }

    /**
     * Function to Filter the Data in the Table
     *
     * @param picker        Picker locator
     * @param columnName    Name of the column to be filtered
     * @param valueToFilter Value using which we need to filter
     * @param filterOption  Filter type
     * @return True if filtering is successful, else false.
     */
    public boolean filterPickerColumn(By picker, String columnName, String valueToFilter, PickerFilterOptions filterOption) {
        logger().info("Filtering the picker");
        waitHelper.waitForLoadingSpinnerDisappear();
        clickFilterButton(columnName, picker);
        WebElement filterDropDown = elementHelper.getElements(filterDropDownList).stream().filter(WebElement::isDisplayed).findFirst().get();
        if (filterDropDown != null) {
            var filterOperator = filterDropDown.findElement(filterOperatorSpan);

            waitHelper.waitForPageToLoad(filterButton);
            waitHelper.waitForElementClickable(filterOperator);
            elementHelper.doClick(filterOperator);
            waitHelper.waitForPageToLoad(picker);
            var filterOptionElement = filterDropDown.findElement(By.xpath(String.format(filterOperatorContentXpathTemplate, filterOption.getValue())));
            waitHelper.waitForLoadingSpinnerDisappear();
            waitHelper.waitForElementClickable(filterOptionElement);
            elementHelper.scrollToView(filterOptionElement);
            filterOptionElement.click();
            waitHelper.waitForPageToLoad();
            elementHelper.doSendKeys(filterDropDown.findElements(By.tagName("input")).get(0), valueToFilter);
            waitHelper.waitForPageToLoad(filterButton);
            elementHelper.doClick(filterButton);
            waitHelper.waitForLoadingSpinnerDisappear();
            return true;
        } else {
            logger().info("Exception occurred while fetching elements of drop down - " + elementHelper.getLocatorAsString(filterDropDownList));
            return false;
        }
    }

    /**
     * Function set a Date:Date Picker popup
     *
     * @param date            date to be selected.
     * @param dropDownLocator Locator for the Dropdown.
     * @param monthLocator    Locator for Month Dropdown.
     * @param yearLocator     Locator for Year Dropdown.
     * @param dayLocator      Locator for Day Dropdown.
     * @return True if no issue, else false.
     */
    public boolean setValue(LocalDate date, By dropDownLocator, By monthLocator, By yearLocator, By dayLocator) {
        waitHelper.waitForElementPresent(dropDownLocator);
        elementHelper.doClick(dropDownLocator);

        waitHelper.waitForElementPresent(monthLocator);
        elementHelper.selectComboBoxItemByIndex(monthLocator, date.getMonthValue() - 1);
        waitHelper.waitForElementPresent(yearLocator);
        elementHelper.selectComboBoxItemByIndex(yearLocator, date.getYear());
        waitHelper.waitForElementPresent(dayLocator);

        try {
            List<WebElement> dayList = elementHelper.getElement(dayLocator).findElements(By.tagName("td"));
            for (var day : dayList) {
                if (day.getText().equals(date.getDayOfMonth())) {
                    day.click();
                    break;
                }
            }
            return true;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'td' elements of - " + elementHelper.getLocatorAsString(dayLocator));
            return false;
        }
    }

    /**
     * Function to set a Date in Date Picker popup
     *
     * @param date            Date to be set:MM/dd/yyyyformat
     * @param inputBox        input textbox of the date
     * @param dropDownLocator Locator for Dropdown which is beside the input textbox
     * @param monthLocator    Locator for Month Dropdown in the Popup
     * @param yearLocator     Locator for Year Dropdown in the Popup
     * @param dayLocator      Locator for Day Dropdown in the Popup ie the entire calender box.
     * @return True if no issue, else false.
     */
    public boolean setValueUsingList(LocalDate date, By inputBox, By dropDownLocator, By monthLocator, By yearLocator, By dayLocator) {
        waitHelper.waitForElementPresent(dropDownLocator);
        elementHelper.doClick(dropDownLocator);

        waitHelper.waitForElementPresent(monthLocator);
        elementHelper.selectComboBoxItemByIndex(monthLocator, date.getMonthValue() - 1);
        waitHelper.waitForElementPresent(yearLocator);
        elementHelper.selectComboBoxItemByIndex(yearLocator, date.getYear());
        waitHelper.waitForElementPresent(dayLocator);

        try {
            List<WebElement> dayList = elementHelper.getElement(dayLocator).findElements(By.xpath(".//*[text()='" + date.getDayOfMonth() + "']"));
            elementHelper.doClick(dayList.get(0));
            if (date.toString().equals(elementHelper.doGetAttribute(inputBox, "value"))) {
                elementHelper.doClick(dropDownLocator);
                elementHelper.selectComboBoxItemByIndex(monthLocator, date.getMonthValue());
                elementHelper.selectComboBoxItemByIndex(yearLocator, date.getYear());
                elementHelper.doClick(dayList.get(1));
            }
            return true;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'td' elements of - " + elementHelper.getLocatorAsString(dayLocator));
            return false;
        }
    }

    /**
     * Set Date in Calendar
     *
     * @param date          Date to be set:MM/dd/yyyyformat
     * @param inputBox      input textbox of the date
     * @param calendar      Calendar locator
     * @param calendarTitle Calendar Title Locator.
     * @param dayLocator    Day Locator.
     * @return True if no issue, else false.
     */
    public boolean setDateInCalendar(LocalDate date, By inputBox, By calendar, By calendarTitle, By dayLocator) {
        elementHelper.doClick(calendar);
        elementHelper.doClick(calendarTitle);
        elementHelper.doClick(By.id("rcMView_" + date.getMonthValue()));
        elementHelper.doClick(By.id("rcMView_" + date.getYear()));
        elementHelper.doClick(By.id("rcMView_OK"));

        try {
            List<WebElement> dayList = elementHelper.getElement(dayLocator).findElements(By.xpath(".//*[text()='" + date.getDayOfMonth() + "']"));
            elementHelper.doClick(dayList.get(0));
            if (elementHelper.isElementDisplayed(By.id("ctl00_C1_radbidOpnTime_calendar"))) {
                elementHelper.doClick(dayList.get(0));
            }
            if (date.toString().equals(elementHelper.doGetAttribute(inputBox, "value"))) {
                elementHelper.doClick(calendar);
                elementHelper.doClick(calendarTitle);
                elementHelper.doClick(By.id("rcMView_" + date.getMonthValue()));
                elementHelper.doClick(By.id("rcMView_" + date.getYear()));
                elementHelper.doClick(By.id("rcMView_OK"));
                elementHelper.doClick(dayList.get(0));
            }
            return true;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'td' elements of - " + elementHelper.getLocatorAsString(dayLocator));
            return false;
        }
    }

    /**
     * Set Time
     *
     * @param time       Time to be selected.
     * @param timePicker Locator of time picker.
     * @return True if no issue, else false.
     */
    public boolean setTime(String time, By timePicker) {
        elementHelper.doClick(timePicker);
        elementHelper.doClick(By.xpath(".//a[text()='" + time + "']"));
        return true;
    }

    /**
     * Function to get the picker Column Index using its Text.
     *
     * @param header      Header of the Column
     * @param pickerXpath picker locator
     * @return picker column number
     */
    private int getPickerHeaderColumnIndex(String header, By pickerXpath) {
        By pickerHeaderTable = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//*[contains(@class,'k-grid-header-wrap')]");
        List<WebElement> pickerHeaderColumns = elementHelper.getElement(pickerHeaderTable).findElements(By.tagName("tr")).get(0).findElements(By.tagName("th"));
        try {
            for (int pickerCounter = 1; pickerCounter < pickerHeaderColumns.size(); pickerCounter++) {
                String pickerColumnHeader = pickerHeaderColumns.get(pickerCounter).findElements(By.tagName("a")).stream().filter(c -> c.getAttribute("class").equals("k-link")).findFirst().get().getAttribute("text");
                if (pickerColumnHeader.equalsIgnoreCase(header)) {
                    return pickerCounter;
                }
            }
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'tr' elements of - " + elementHelper.getLocatorAsString(pickerHeaderTable));
        }
        return -1;
    }

    /**
     * Method to get all cell values from column.
     *
     * @param columnHeaderName Column header name.
     * @param pickerXpath      picker locator
     * @return List of all cell values in the column.
     */
    public List<String> getAllDataUnderColumnHeader(String columnHeaderName, By pickerXpath) {
        List<String> dataList = new ArrayList<>();
        waitHelper.waitForElementPresent(pickerXpath);
        int columnHeaderNumber = getPickerHeaderColumnIndex(columnHeaderName, pickerXpath);
        List<WebElement> rows = getListRows(pickerXpath);
        try {
            for (int i=1;i<=rows.size();i++) {
                String pickerNameLocator = String.format("%s//*[@class = 'k-selectable']//tr[%s]/td", elementHelper.getLocatorAsString(pickerXpath), i);
                List<WebElement> webElements = elementHelper.getElements(By.xpath(pickerNameLocator));
                String value = webElements.get(columnHeaderNumber).getText().trim();
                if (!value.isEmpty() && !dataList.contains(value)) {
                    dataList.add(value);
                }
            }
            return dataList;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding rows of picker - " + elementHelper.getLocatorAsString(pickerXpath));
            return new ArrayList<>();
        }
    }

    /**
     * Function to Get the row number in list page of the picker item
     *
     * @param pickerItem         Item to be picked.
     * @param pickerColumnHeader Column Header to search for the item.
     * @param pickerXpath        picker locator
     * @return Row number.
     */
    public int getRowNumberOfPickerItem(String pickerColumnHeader, String pickerItem, By pickerXpath) {
        int pickerColumnNumber = getPickerHeaderColumnIndex(pickerColumnHeader, pickerXpath);
        if (pickerColumnNumber == -1) {
            return -1;
        }
        try {
            for (int pickerRowCounter = 0; pickerRowCounter < getRowSizeInPickerGrid(pickerXpath); pickerRowCounter++) {
                List<WebElement> PickerItemRowData = getListRows(pickerXpath).get(pickerRowCounter).findElements(By.tagName("td"));
                String PickerData = PickerItemRowData.get(pickerColumnNumber).getText();
                if (PickerData.trim().equalsIgnoreCase(pickerItem.trim())) {
                    return pickerRowCounter + 1;
                }
                elementHelper.scrollToView(PickerItemRowData.get(pickerColumnNumber));
            }
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding rows of picker - " + elementHelper.getLocatorAsString(pickerXpath));
        }
        return -1;
    }


    /**
     * Function to Select a Row by Text
     *
     * @param pickerItem         Item to be picked.
     * @param pickerColumnHeader Column Header to search for the item.
     * @param pickerXpath        picker locator
     * @return True if no issue occurred, else false
     */
    public boolean singleSelectByText(String pickerColumnHeader, String pickerItem, By pickerXpath) {
        logger().info("Selecting the item '" + pickerItem + "' under the column '" + pickerColumnHeader + "' in picker");
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForElementClickable(pickerXpath);
        if (filterPickerColumn(pickerXpath, pickerColumnHeader, pickerItem, PickerFilterOptions.IsEqualTo)) {
            logger().info("Successfully filtered the item from the picker");
            singleSelectByRowNumber(1, pickerXpath);
            return true;
        }
        return false;
    }

    /**
     * Function to Select a Row by Text
     *
     * @param pickerItem          Item to be picked.
     * @param pickerColumnHeader  Column Header to search for the item.
     * @param pickerXpath         picker locator
     * @param pickerFilterOptions filter option for the picker
     * @return True if no issue occurred, else false
     */
    public boolean singleSelectByText(String pickerColumnHeader, String pickerItem, By pickerXpath, PickerFilterOptions pickerFilterOptions) {
        logger().info("Selecting the item '" + pickerItem + "' under the column '" + pickerColumnHeader + "' in picker");
        waitHelper.waitForLoadingSpinnerDisappear();
        if (filterPickerColumn(pickerXpath, pickerColumnHeader, pickerItem, pickerFilterOptions)) {
            logger().info("Successfully filtered the item from the picker");
            singleSelectByRowNumber(1, pickerXpath);
        }
        return true;
    }

    /**
     * Function to count number of rows in the picker grid
     *
     * @param pickerXpath picker locator
     * @return row size.
     */
    public int getRowSizeInPickerGrid(By pickerXpath) {
        return getListRows(pickerXpath).size();
    }

    /**
     * Function to Select a Row in Picker by an Index
     *
     * @param rowNum      Row Number to Select
     * @param pickerXpath picker locator
     * @return True if no issue occurred, else false.
     */
    public boolean singleSelectByRowNumber(int rowNum, By pickerXpath) {
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForPageToLoad(pickerXpath);
        logger().info("Selecting row number " + rowNum + " in picker");
        By selectButton = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//input[(@value = 'Select') and not(contains(@style,'display: none'))]");
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForPageToLoad(selectButton);
        waitHelper.waitForElementPresent(pickerXpath);
        clickRow(rowNum, pickerXpath);
        elementHelper.doClick(selectButton);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Function to get Data by passing Row No. and Column Name
     *
     * @param rowNum      Row Number to select the Data(To get data of first Row, "1" should be passed for RowNum parameter)
     * @param columnName  Column Name to Search In
     * @param pickerXpath picker locator
     * @return Cell data.
     */
    public String getDataByRowNoColumnName(int rowNum, String columnName, By pickerXpath) {
        waitHelper.waitForLoadingSpinnerDisappear();
        return getListRows(pickerXpath).get(rowNum - 1)
                .findElement(By.xpath(".//td["
                        + (getPickerHeaderColumnIndex(columnName, pickerXpath) + 1) + "]"))
                .getText();
    }

    /**
     * Method to click on the select button inside a picker
     *
     * @param pickerXpath Xpath of the picker
     */
    public void clickSelect(By pickerXpath) {
        logger().info("Clicking on select button");
        waitHelper.waitForElementPresent(pickerXpath);
        By selectButton = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//input[(@value = 'Select') and not(contains(@style,'display: none'))]");
        elementHelper.waitForElementClickable(selectButton);
        elementHelper.doClick(selectButton);
    }

    /**
     * Function to select Rows in a list if they are multi selectable
     *
     * @param numberOfDataToSelect Number of Rows to Select
     * @param pickerXpath          picker locator
     * @return true if no issue, else false.
     */
    public boolean multiSelectByCount(int numberOfDataToSelect, By pickerXpath) {
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForPageToLoad(pickerXpath);
        try {
            for (int number = 1; number <= numberOfDataToSelect; number++) {
                getListRows(pickerXpath).get(number - 1)
                        .findElements(By.tagName("td"))
                        .get(0).click();
            }
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'td' elements of picker - " + elementHelper.getLocatorAsString(pickerXpath));
        }
        elementHelper.doClick(standardItemsDropDownSave);
        return true;
    }
    /**
     * Function to select Rows in a list if they are multi selectable
     *
     * @param numberOfDataToSelect Number of Rows to Select
     * @param pickerXpath          picker locator
     * @return true if no issue, else false.
     */
    public boolean multiSelectByCountOld(int numberOfDataToSelect, By pickerXpath) {
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForPageToLoad(pickerXpath);
        try {
            for (int number = 1; number <= numberOfDataToSelect; number++) {
                getListRows(pickerXpath).get(number - 1)
                        .findElements(By.tagName("td"))
                        .get(0).click();
            }
        } catch (NullPointerException e) {
            logger().info("Exception occurred while finding 'td' elements of picker - " + elementHelper.getLocatorAsString(pickerXpath));
        }
        By selectButton = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//input[@value = 'Select']");
        elementHelper.doClick(
                elementHelper.getElements(selectButton).stream().reduce((first, last) -> last).get());
        return true;
    }

    /**
     * Function to Select a Row:Picker by an Index
     *
     * @param rowNum      Row Number to Select
     * @param pickerXpath picker locator
     */

    public void clickRow(int rowNum, By pickerXpath) {
        getPage(Navigation.class).switchFrameToContent();
        waitHelper.waitForPageToLoad(pickerXpath);
        WebElement row = getListRows(pickerXpath).get(rowNum - 1);
        waitHelper.waitForElementClickable(row);
        elementHelper.doClick(row);
    }

    /**
     * Get Picker List Rows.
     *
     * @param pickerXpath picker locator
     * @return List of rows.
     */
    public List<WebElement> getListRows(By pickerXpath) {
        getPage(Navigation.class).switchFrameToContent();
        By pickerListRows = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//*[@class = 'k-selectable']//tr");
        return elementHelper.getElements(pickerListRows);
    }

    /**
     * Picker Scroll Down.
     *
     * @return true if scrolled down, else false.
     */
    public boolean scrollDown() {
        int maxHeight = (int) Double.parseDouble(
                elementHelper.getElement(scrollBarHandle).findElement(By.tagName("div")).getCssValue("height").replace("px", "").trim()
        );
        String offsetHeight = elementHelper.getElement(scrollBarHandle).findElement(By.xpath("../div")).getAttribute("clientHeight").replace("px", "").trim();
        int offset = (int) Double.parseDouble(offsetHeight);
        // this causes a gradual drag of the scroll bar
        if (maxHeight > offset) {
            elementHelper.moveToElementWithOffset(scrollBarHandle, offset);
            return true;
        }
        return false;
    }

    /**
     * Getting column data (At most 20 records only)
     *
     * @param pickerID        picker ID  (only ID should be passed)
     * @param dataFieldName   dataField Name to get data from
     * @param numberOfRecords number of records to be fetched
     * @return column Data
     */
    public List<String> getColumnData(By pickerID, String dataFieldName, Integer numberOfRecords) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForLoadingSpinnerDisappear();
        String xpathPicker = elementHelper.getLocatorAsString(pickerID);
        List<String> dataList = new ArrayList<>();
        By xpath = By.xpath("//div[contains(@id,'" + xpathPicker + "')and contains(@style,'display:block')]" + "//td[@data-field ='" + dataFieldName + "']");
        List<WebElement> rows = elementHelper.getElements(xpath);
        waitHelper.waitForPageToLoad();
        int i = 0;
        for (var Row : rows) {
            if ((i % 10 == 0) && i != 0) {
                elementHelper.scrollToView(Row);
            }
            String value = elementHelper.doGetText(rows.get(i));

            if (!value.isEmpty() && !dataList.contains(value) && i < numberOfRecords) {
                dataList.add(value);
                i++;
            }
        }
        return dataList;
    }

    /**
     * Method to sort the column in ascending order
     *
     * @param picker     picker
     * @param columnName column Name that can be sorted
     */
    public void ascendingOrderSort(By picker, By columnName) {
        waitHelper.waitForElementPresent(picker);
        elementHelper.doClick(columnName);
        waitHelper.waitForLoadingSpinnerDisappear();
    }

    /**
     * Method to sort the column in descending order
     *
     * @param picker     picker
     * @param columnName column Name that can be sorted
     */
    public void descendingOrderSort(By picker, By columnName) {
        waitHelper.waitForElementPresent(picker);
        elementHelper.doClick(columnName);
        waitHelper.waitForLoadingSpinnerDisappear();
        elementHelper.doClick(columnName);
        waitHelper.waitForLoadingSpinnerDisappear();
    }

    /**
     * Method to cancel the picker
     */
    public void cancelPicker() {
        waitHelper.waitForElementClickable(cancelPicker);
        elementHelper.doClick(cancelPicker);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to get picker Headers
     *
     * @param pickerXpath - Picker xpath
     * @return list of Picker column Headers
     */
    public List<String> getPickerColumnHeaders(By pickerXpath) {
        List<String> columnNames = new ArrayList<>();
        By pickerHeaderTable = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//*[contains(@class,'k-grid-header-wrap')]");
        try {
            List<WebElement> pickerHeaderColumns = elementHelper.getElement(pickerHeaderTable).findElements(By.tagName("tr")).get(0).findElements(By.tagName("th"));
            for (int pickerHeaderCount = 1; pickerHeaderCount < pickerHeaderColumns.size(); pickerHeaderCount++) {
                columnNames.add(pickerHeaderColumns.get(pickerHeaderCount).findElements(By.tagName("a")).stream().filter(c -> c.getAttribute("class").equals("k-link")).findFirst().get().getAttribute("text"));
            }
            return columnNames;
        } catch (NullPointerException e) {
            logger().info("Exception occurred while fetching elements of table - " + elementHelper.getLocatorAsString(pickerHeaderTable));
            return new ArrayList<>();
        }
    }

    /**
     * Method to Cancel the Picker
     *
     * @param pickerXpath - the picker to be cancelled
     */
    public void cancelPicker(By pickerXpath) {
        By pickerCancelButton = By.xpath(elementHelper.getLocatorAsString(pickerXpath) + "//input[@type='submit' and @value='Cancel']");
        elementHelper.doClick(pickerCancelButton);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Validate multiselect checkbox in picker
     *
     * @param skipCancel To skip cancel option
     * @return true on validation
     */
    public boolean validateMultiSelectCheckBox(boolean skipCancel) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(multiSelectCheckboxInPicker);
        var isSelected = elementHelper.isCheckBoxSelected(multiSelectCheckboxInPicker);
        elementHelper.doClick(multiSelectCheckboxInPicker);
        var isDeSelected = !elementHelper.isCheckBoxSelected(multiSelectCheckboxInPicker);
        if (!skipCancel)
            cancelPicker();
        return isSelected && isDeSelected;
    }

    /**
     * Method to clear picker column filter
     *
     * @param picker        picker xpath
     * @param columnName    column name to filter
     * @param valueToFilter value to be filtered
     * @param filterOption  picker filter option
     */
    public void clearFilterAndFilterPickerColumn(By picker, String columnName, String valueToFilter, PickerFilterOptions filterOption) {
        logger().info("Clearing the picker filter");
        waitHelper.waitForLoadingSpinnerDisappear();
        clickFilterButton(columnName, picker);
        try {
            WebElement filterDropDown = elementHelper.getElements(filterDropDownList).stream().filter(WebElement::isDisplayed).findFirst().get();
            var filterOperator = filterDropDown.findElement(filterOperatorSpan);
            waitHelper.waitForPageToLoad(pickerClearFilterButton);
            elementHelper.doClick(pickerClearFilterButton);
            waitHelper.waitForPageToLoad(picker);
            waitHelper.waitForLoadingSpinnerDisappear();

            logger().info("Filtering the picker");
            waitHelper.waitForLoadingSpinnerDisappear();
            clickFilterButton(columnName, picker);
            filterDropDown = elementHelper.getElements(filterDropDownList).stream().filter(WebElement::isDisplayed).findFirst().get();
            filterOperator = filterDropDown.findElement(filterOperatorSpan);

            waitHelper.waitForPageToLoad(filterButton);
            waitHelper.waitForElementClickable(filterOperator);
            elementHelper.doClick(filterOperator);
            waitHelper.waitForPageToLoad(picker);

            var filterOptionElement = filterDropDown.findElement(By.xpath(String.format(filterOperatorContentXpathTemplate, filterOption.getValue())));
            waitHelper.waitForElementClickable(filterOptionElement);
            elementHelper.scrollToView(filterOptionElement);
            waitHelper.waitForLoadingSpinnerDisappear();
            filterOptionElement.click();
            waitHelper.waitForPageToLoad();
            elementHelper.doSendKeys(filterDropDown.findElements(By.tagName("input")).get(0), valueToFilter);
        } catch (NullPointerException e) {
            logger().info("Exception while fetching input fields in picker filter");
        }
        waitHelper.waitForPageToLoad(filterButton);
        elementHelper.doClick(filterButton);
        waitHelper.waitForPageToLoad();
    }

}
