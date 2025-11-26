package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.webUI.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomFieldsHelper extends BasePage {


    private By businessUnitSpansForProjectPage = By.id("ctl00_C1_PROJECT_BusinessUnitID_Id_EmbeddedTree");
    private By businessUnitSpansForUserPage = By.id("ctl00_C1_radddlBusinessUnitTree_EmbeddedTree");
    private By businessUnitSpansForParentBUDropdown = By.id("ctl00_C1_BUSUNIT_ParentID_Id_EmbeddedTree");
    private By multiLineSelectedValueSpans = By.className("MultiSelectDropdown-selected-value");
    private By datePickerDiv = By.id("ui-datepicker-div");
    private By datePickerMonthDropDown = By.className("ui-datepicker-month");
    private By datePickerYearDropDown = By.className("ui-datepicker-year");

    private String dateCellTemplate = ".//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[.='%s']/..";

    public CustomFieldsHelper(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to select the businessUnit for project page
     *
     * @param businessUnitSelector
     * @param businessUnit
     */
    public void SelectBusinessUnitForProject(By businessUnitSelector, String businessUnit) {
        selectBusinessUnit(businessUnitSelector, businessUnit, businessUnitSpansForProjectPage);
    }

    /**
     * Method to select the parent businessUnit in BU page
     *
     * @param businessUnitSelector
     * @param businessUnit
     */
    public void selectParentBusinessUnitInBUPage(By businessUnitSelector, String businessUnit) {
        selectBusinessUnit(businessUnitSelector, businessUnit, businessUnitSpansForParentBUDropdown);
    }

    /**
     * Method to select an item from the Multi line dropdown selector
     *
     * @param dropDownElement   dropdown locator
     * @param dropDownItem      locator of the items inside the dropdown
     * @param itemCountToSelect count of items to be selected
     * @return list of text from the selected items
     */
    public List<String> multiLineDropdownSelect(By dropDownElement, By dropDownItem, int itemCountToSelect) {
        if (itemCountToSelect == 0) {
            logger().info("Not selecting any items");
            return null;
        }
        var alreadySelectedItems=elementHelper.getElement(dropDownElement).findElements(By.xpath(".//ul[contains(@id,'_taglist')]//li"));
        if(alreadySelectedItems.size()>0 && alreadySelectedItems.size() == itemCountToSelect){
            logger().info("Items already selected!");
            return null;
        }
        elementHelper.doClick(dropDownElement);
        waitHelper.waitForElementClickable(dropDownItem);
        if (!elementHelper.isElementDisplayed(dropDownItem)) {
            logger().info("Multi line dropdown content is empty!");
            return null;
        }

        var items = elementHelper.getElements(dropDownItem);
        List<String> itemText = new ArrayList<>();
        if (items.size() > itemCountToSelect) {
            for (int i = 0; i < itemCountToSelect; i++) {
                elementHelper.doClick(dropDownElement);
                waitHelper.waitForElementToBePresentAndClickable(dropDownItem);
                itemText.add(elementHelper.doGetText(items.get(i)));
                elementHelper.doClick(items.get(i));
                waitHelper.waitForPageToLoad();
            }
            var selectedSpans = elementHelper.getElement(dropDownElement).findElements(multiLineSelectedValueSpans);
            if (selectedSpans.size() == 0) {
                multiLineDropdownSelect(dropDownElement, dropDownItem, itemCountToSelect);
            }
            return itemText;
        } else if (items.size() == 0) {
            return null;
        }
        elementHelper.doClick(dropDownItem);
        itemText.add(elementHelper.doGetText(dropDownItem));
        return itemText;
    }

    /**
     * Method to select the businessUnit for Needs page
     *
     * @param businessUnitSelector
     * @param businessUnit
     */
    public void SelectBusinessUnitForNeeds(By businessUnitSelector, String businessUnit) {
        elementHelper.scrollToView(businessUnitSelector);
        elementHelper.doClick(businessUnitSelector);
        var element = elementHelper.getElement(By.xpath("//div[@id='ctl00_C1_ENTPNDS_BusinessUnit_Id_EmbeddedTree']/*//span[.='" + businessUnit + "']"));
        elementHelper.scrollToView(element);
        waitHelper.waitForElementClickable(element);
        element.click();
        elementHelper.doClick(businessUnitSelector);
    }

    /**
     * Selects the BU based on the bu-span element
     *
     * @param businessUnitSelector
     * @param businessUnit
     * @param businessUnitSpan
     */
    private void selectBusinessUnit(By businessUnitSelector, String businessUnit, By businessUnitSpan) {
        waitHelper.waitForElementClickable(businessUnitSelector);
        elementHelper.scrollToView(businessUnitSelector);
        elementHelper.doClick(businessUnitSelector);
        elementHelper.scrollToView(businessUnitSpan);
        waitHelper.waitForElementClickable(businessUnitSpan);
        var businessUnitSpanElement = elementHelper.getElement(businessUnitSpan);
        waitHelper.waitForElementClickable(businessUnitSpanElement);
        var element = businessUnitSpanElement.findElement(By.xpath(".//span[text()='" + businessUnit + "']"));
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(element);
        element.click();
    }

    /**
     * Method to select the businessUnit for User page
     *
     * @param businessUnitSelector - BU dropdown locator
     * @param businessUnit - BU to be selected
     */
    public void SelectBusinessUnitForUser(By businessUnitSelector, String businessUnit) {
        elementHelper.scrollToView(businessUnitSelector);
        elementHelper.doClick(businessUnitSelector);
        waitHelper.waitForPageToLoad();
        var element = elementHelper.getElement(businessUnitSpansForUserPage).findElement(By.xpath("//label/span[text()='" + businessUnit + "']"));
        waitHelper.waitForElementClickable(element);
        elementHelper.scrollToView(element);
        waitHelper.waitForElementClickable(element);
        var buElement = element.findElement(By.xpath("../input"));
        elementHelper.scrollToView(buElement);
        buElement.click();
        elementHelper.doClick(businessUnitSelector);
    }

    /**
     * Method to select the businessUnits for User page
     *
     * @param businessUnitSelector - BU dropdown locator
     * @param businessUnits - BUs to be selected
     */
    public void selectBusinessUnitsForUser(By businessUnitSelector, List<String> businessUnits) {
        elementHelper.scrollToView(businessUnitSelector);
        elementHelper.doClick(businessUnitSelector);
        waitHelper.waitForPageToLoad();
        for(var businessUnit:businessUnits) {
            var element = elementHelper.getElement(businessUnitSpansForUserPage).findElement(By.xpath("//label/span[text()='" + businessUnit + "']"));
            waitHelper.waitForElementClickable(element);
            elementHelper.scrollToView(element);
            waitHelper.waitForElementClickable(element);
            var buElement = element.findElement(By.xpath("../input"));
            elementHelper.scrollToView(buElement);
            buElement.click();
        }
        elementHelper.doClick(businessUnitSelector);
    }

    /**
     * Date picker in Schedule-Gantt Pages
     *
     * @param dateToBeSelected - date to be selected in the picker
     */
    public void scheduleGanttPageDatePickerSelectDate(LocalDate dateToBeSelected) {
        var month = dateToBeSelected.getMonth().getValue() - 1;
        var year = dateToBeSelected.getYear();
        var day = dateToBeSelected.getDayOfMonth();
        waitHelper.waitForElementPresent(datePickerDiv);
        elementHelper.selectComboBoxItemByIndex(datePickerMonthDropDown, month);
        elementHelper.selectComboBoxItemByText(datePickerYearDropDown, Integer.toString(year));
        elementHelper.doClick(By.xpath(String.format(dateCellTemplate, day)));
    }
}
