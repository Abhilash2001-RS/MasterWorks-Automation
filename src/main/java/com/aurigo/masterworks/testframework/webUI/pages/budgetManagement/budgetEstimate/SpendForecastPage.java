package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SpendForecastPage extends GenericFormProposed {

    private By spendForecastHeader;
    private By spendForecastGridHeader;
    private By itemRows;
    private By saveSpendForecast;
    private By gridHeader;
    private WebDriver driver;

    public SpendForecastPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("SpendForecastPage.json");
        spendForecastHeader = locators.get("spendForecastHeader");
        itemRows = locators.get("itemRows");
        spendForecastGridHeader = locators.get("spendForecastGridHeader");
        saveSpendForecast = locators.get("saveSpendForecast");
        gridHeader = locators.get("gridHeader");
    }

    public void navigateTo(){
        elementHelper.doClick(spendForecastHeader);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageTabHeaderToBeClickable();
        getPage(AuthorizationRequestForecastPage.class).validateFiscalYearSelection();
    }

    public void enterData() {
        List<WebElement> items = getGridWebElements(itemRows);
        var remainingAmountColumn = getColumnIndex("Remaining");
        var priorFiscalYear = getColumnIndex("Prior");
        for (var item : items) {
            var remainingAmountFromItems = elementHelper.doGetText(item.findElement(By.xpath(String.format( "./td[not (contains(@style,'display:none')) and not(contains(@style,'display: none'))]%s", remainingAmountColumn))));
            Double remainingAmount = Double.parseDouble(remainingAmountFromItems.replace(",",""));

            var cell = item.findElement(By.xpath("./td[not (contains(@style,'display:none')) and not(contains(@style,'display: none'))][7]"));
            var priorYearColumn= item.findElement(By.xpath(String.format("./td[not (contains(@style,'display:none')) and not(contains(@style,'display: none'))]%s", priorFiscalYear)));
            var displayDiv = cell.findElement(By.xpath("./div"));
            elementHelper.doClick(displayDiv);
            var editor = cell.findElement(By.xpath(".//input | .//textarea"));
            editor.clear();
            elementHelper.doSendKeys(editor, String.format("%.2f", remainingAmount));
            elementHelper.doClick(priorYearColumn);
        }
        saveSpendForecast();
        navigation.navigateToFormInLeftPaneTree("Budget Estimates");
    }

    public List<Integer> getColumnIndex(String columnName){
        List<WebElement> gridHeaders =  getGridWebElements(gridHeader);
        List<Integer> columnNumber = new ArrayList<>();
        for (int i = 0; i < gridHeaders.size(); i++) {
            if (elementHelper.doGetText(gridHeaders.get(i)).contains(columnName)) {
                columnNumber.add(i+1);
            }
        }
        return columnNumber;
    }


    public void saveSpendForecast(){
        elementHelper.doClick(saveSpendForecast);
        waitHelper.waitForPageToLoad();
    }
}
