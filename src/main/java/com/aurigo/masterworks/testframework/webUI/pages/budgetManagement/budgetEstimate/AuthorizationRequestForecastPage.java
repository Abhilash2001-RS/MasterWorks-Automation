package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationRequestForecastPage extends GenericFormProposed
{

    private By authorizationReqForecastHeader;
    private By fiscalYearLabel;
    private By fiscalYearDropdown;
    private By gridLocator;
    private By remainingAmount;

    private By gridHeader;
    private By itemRows;
    private By saveAuthRequestForecast;
    private By phaseItem;
    private WebDriver driver;

    public AuthorizationRequestForecastPage(WebDriver driver){
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("AuthorizationRequestForecastPage.json");
        authorizationReqForecastHeader = locators.get("authorizationReqForecastHeader");
        fiscalYearLabel = locators.get("fiscalYearLabel");
        fiscalYearDropdown = locators.get("fiscalYearDropdown");
        gridLocator = locators.get("gridLocator");
        gridHeader = locators.get("gridHeader");
        itemRows = locators.get("itemRows");
        saveAuthRequestForecast = locators.get("saveAuthRequestForecast");
        remainingAmount = locators.get("remainingAmount");
        phaseItem = locators.get("phaseItem");
    }

    public String validateFiscalYearSelection(){
        elementHelper.selectComboBoxItemByIndex(elementHelper.getElement(fiscalYearDropdown), 1);
        return elementHelper.getFirstSelectedOption(fiscalYearDropdown);
    }

    public void navigateTo(){
        elementHelper.doClick(authorizationReqForecastHeader);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageTabHeaderToBeClickable();
        validateFiscalYearSelection();
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

    public void enterData() throws InterruptedException {
        List<Integer> actualColumnIndexes = getColumnIndex("Actual");
        //gets the Index of the First Actual Column
        int actualColumn = actualColumnIndexes.get(0);
        List<Integer> remainingAmountColumn = getColumnIndex("Remaining");
        List<WebElement> rows = elementHelper.getElements(itemRows);

        for (WebElement row : rows)
        {
            //Extract the Remaining Amount
            String remainingAmountCell = elementHelper.doGetText(row.findElement(By.xpath(String.format("./td[not(contains(@style,'display:none')) and not(contains(@style,'display: none;'))]%s",  remainingAmountColumn))));
            System.out.println(remainingAmountCell);
            Double remainingAmount = Double.parseDouble(remainingAmountCell.replace(",",""));
            System.out.println(remainingAmount);

            var actualCell = row.findElement(By.xpath(String.format("./td[not(contains(@style,'display:none')) and not(contains(@style,'width: 100px; text-align: right; display: none;'))][%s]",actualColumn)));
            var displayDiv = actualCell.findElement(By.xpath("./div"));
            elementHelper.doClick(displayDiv);
            var editor = actualCell.findElement(By.xpath(".//input | .//textarea"));
            editor.clear();
            elementHelper.doSendKeys(editor, String.format("%.2f", remainingAmount));
            elementHelper.doClick(row.findElement(By.xpath(String.format("./td[not(contains(@style,'display:none')) and not(contains(@style,'width: 100px; text-align: right; display: none;'))][%s]",actualColumn-1))));
        }
        saveAuthRequestForecast();
    }

    public void saveAuthRequestForecast(){
        elementHelper.doClick(saveAuthRequestForecast);
        waitHelper.waitForPageToLoad();
    }


}
