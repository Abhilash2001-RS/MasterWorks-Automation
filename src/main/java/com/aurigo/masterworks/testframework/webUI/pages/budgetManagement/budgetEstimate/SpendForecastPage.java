package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SpendForecastPage extends GenericFormProposed {

    private By spendForecastHeader;
    private By spendForecastGridHeader;
    private By itemRows;
    private By saveSpendForecast;

    public SpendForecastPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("SpendForecastPage.json");
        spendForecastHeader = locators.get("spendForecastHeader");
        itemRows = locators.get("itemRows");
        spendForecastGridHeader = locators.get("spendForecastGridHeader");
        saveSpendForecast = locators.get("saveSpendForecast");
    }

    public void navigateTo(){
        elementHelper.doClick(spendForecastHeader);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageTabHeaderToBeClickable();
        getPage(AuthorizationRequestForecastPage.class).validateFiscalYearSelection();
    }

    public void enterData(){
        List<WebElement> items = getGridWebElements(itemRows);

        for (var item : items) {
            var remainingAmountFromItems = elementHelper.doGetText(item.findElement(By.xpath("./td[not (contains(@style,'display:none')) and not(contains(@style,'display: none'))][5]")));
            Double remainingAmount = Double.parseDouble(remainingAmountFromItems.replace(",",""));

            var cell = item.findElement(By.xpath("./td[not (contains(@style,'display:none')) and not(contains(@style,'display: none'))][7]"));
            var displayDiv = cell.findElement(By.xpath("./div"));
            elementHelper.doClick(displayDiv);

            var editor = cell.findElement(By.xpath(".//input | .//textarea"));
            editor.clear();
            elementHelper.doSendKeys(editor, String.format("%.2f", remainingAmount));
            elementHelper.doSendKeys(Keys.TAB);
        }
        saveSpendForecast();
        navigation.navigateToFormInLeftPaneTree("Budget Estimates");
    }

    public void saveSpendForecast(){
        elementHelper.doClick(saveSpendForecast);
        waitHelper.waitForPageToLoad();
    }
}
