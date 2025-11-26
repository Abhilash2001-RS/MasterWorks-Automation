package com.aurigo.masterworks.testframework.webUI.pages.budgetManagement.budgetEstimate;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BudgetForecastPageColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.pages.planning.program.ProgramDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BudgetEstimateForecastDetailsPage extends ListPage {

    private By forecastsDetailsTab;
    private By frozenDataTableBudgetForecast;
    private By forecastDataTable;
    private By forecastCPHeader;
    private By budgetForecastTableId;
    private By distributionChart;
    private By maximizeChart;
    private By distributionChartWindow;
    private By distributionChartWindowCloseButton;

    public BudgetEstimateForecastDetailsPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("BudgetEstimateForecastDetailsPage.json");
        forecastsDetailsTab = locators.get("forecastsDetailsTab");
        frozenDataTableBudgetForecast = locators.get("frozenDataTableBudgetForecast");
        forecastDataTable = locators.get("forecastDataTable");
        forecastCPHeader = locators.get("forecastCPHeader");
        budgetForecastTableId = locators.get("budgetForecastTableId");
        distributionChart = locators.get("distributionChart");
        maximizeChart = locators.get("maximizeChart");
        distributionChartWindow = locators.get("distributionChartWindow");
        distributionChartWindowCloseButton = locators.get("distributionChartWindowCloseButton");
    }

    /**
     * Navigate to Forecast Details Tab
     */
    public void navigateToForecastsDetailsTab() {
        waitHelper.waitForPageToLoad(forecastsDetailsTab);
        elementHelper.doClick(forecastsDetailsTab);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Function to get the Column Number for the Item.
     *
     * @param headerName Header of the Column Number to search in.
     * @return column number.
     */
    public int getColumnNumberUsingHeaderName(String headerName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        List<WebElement> tableDatas = elementHelper.getElement(forecastCPHeader).findElements(By.xpath(".//th/div"));

        for (int i = 0; i < tableDatas.size(); i++) {
            WebElement headerTextElement = tableDatas.get(i);
            String headerText = elementHelper.doGetText(headerTextElement);
            if (headerText.equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get list of elements under a required column
     *
     * @param frozenDataTable true if the table to fetch data is frozen, false otherwise
     * @param column          column name
     * @return list of web elements
     */
    public List<WebElement> getColumnElements(boolean frozenDataTable, BudgetForecastPageColumns column) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        By tableId = frozenDataTable ? frozenDataTableBudgetForecast : forecastDataTable;
        var columnIndex = getColumnNumberUsingHeaderName(column.getValue());
        return elementHelper.getElement(tableId).findElements(By.xpath(
                ".//tr/td[" + (columnIndex + 1) + "]"));
    }

    /**
     * Method to retrieve the web elements of the select checkboxes in the Budget Forecast Details page
     *
     * @return list of web elements
     */
    public List<WebElement> getListOfSelectCheckboxes() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        var elements = getColumnElements(true, BudgetForecastPageColumns.Select);
        List<WebElement> checkboxes = new ArrayList<>();
        var checkboxXpath = By.xpath(".//input[@type ='checkbox']");
        elements.forEach(a -> checkboxes.add(a.findElement(checkboxXpath)));
        return checkboxes;
    }

    /**
     * Get row cells of a Forecast page
     * Pass rowNumber as '0' and fetch row values of all rows
     *
     * @param tableId   Locator of the table containing "thead" and "tbody"
     * @param rowNumber the row number of cells [rowNumber = 0, if all row values are required]
     * @return List of cell web elements.
     */
    public List<WebElement> getRowValuesInForecastPage(By tableId, int rowNumber) {
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(tableId);
        elementHelper.scrollToView(tableId);
        if (rowNumber == 0) {
            return elementHelper.getElements(By.xpath(String.format(
                    "//div[@id = '%s']//tbody//tr[not(contains(@style,'display:none')) and not(contains(@style,'display: none;'))]",
                    elementHelper.getLocatorAsString(tableId))));
        } else {
            return elementHelper.getElements(By.xpath(String.format(
                    "//div[@id = '%s']//tbody//tr[%d]//td[not(contains(@style,'display:none')) and not(contains(@style,'display: none;'))]",
                    elementHelper.getLocatorAsString(tableId), rowNumber)));
        }
    }

    /**
     * Get value of cell in Forecast Page
     *
     * @param tableId    Locator of the table containing "thead" and "tbody"
     * @param rowNumber  Row number of cell.
     * @param cellNumber Cell number.
     * @return Cell value.
     */
    public String getValueFromForecastPage(By tableId, int rowNumber, int cellNumber) {
        return getRowValuesInForecastPage(tableId, rowNumber)
                .get(cellNumber).getText();
    }

    /**
     * Method to Get Row Number of specified Record in Forecast details page
     *
     * @param columnName - The column where the text should be present
     * @param searchText - the Text to be Searched
     * @return RowNumber
     */
    public int getRowNumberInBudgetForecastPage(BudgetForecastPageColumns columnName, String searchText) {
        var rows = getRowValuesInForecastPage(budgetForecastTableId, 0);
        int rowNum = 0;
        int colNum = getColumnNumberUsingHeaderName(columnName.getValue());
        for (int i = 1; i <= rows.size(); i++) {
            if (getValueFromForecastPage(budgetForecastTableId, i, colNum).equalsIgnoreCase(searchText)) {
                rowNum = i;
                break;
            }
        }
        return rowNum;
    }

    /**
     * Select required record in BE forecast list page
     *
     * @param parameter record name to be selected
     */
    public void selectRequiredRecord(String parameter) {
        var rowNumber = getRowNumberInBudgetForecastPage(BudgetForecastPageColumns.Name, parameter);
        var checkboxes = getListOfSelectCheckboxes();
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(checkboxes.get(rowNumber - 1));
    }

    /**
     * Validate selection of records in Forecast list page
     *
     * @param listOfParameters list of parameters
     * @return true on validation of the above
     */
    public boolean validateSelectionOfRecords(List<String> listOfParameters) {
        AtomicBoolean result = new AtomicBoolean(false);
        var checkboxes = getListOfSelectCheckboxes();
        listOfParameters.forEach(a -> result.set(checkboxes.get(getRowNumberInBudgetForecastPage(BudgetForecastPageColumns.Name, a) - 1).isSelected()));
        return result.get();
    }

    /**
     * Validate presence of Curve,Select Columns and Ribbon icons (Select Curve, Preview)
     *
     * @return true on validation of the presence of Curve,Select columns
     */
    public SoftAssert validateColumnsAndRibbonIcons() {
        var softAssert = new SoftAssert();
        var selectColumnIndex = getColumnNumberUsingHeaderName(BudgetForecastPageColumns.Select.getValue());
        softAssert.assertTrue(selectColumnIndex != -1, "Select column is present in Forecast list page");

        var curveColumnIndex = getColumnNumberUsingHeaderName(BudgetForecastPageColumns.Curve.getValue());
        softAssert.assertTrue(curveColumnIndex != -1, "Curve column is present in Forecast list page");

        softAssert.assertTrue(validateRibbonIcon(RibbonIcons.SelectCurve) && validateRibbonIcon(RibbonIcons.CurvePreview),
                "Select curve & preview buttons are present in Ribbon of Budget forecast list page");

        return softAssert;
    }

    /**
     * Validate presence of distribution curve in Ribbon menu
     *
     * @param curveName curve name
     * @return true on validation of presence of curve
     */
    public boolean validateDistributionCurveInRibbon(String curveName) {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.SelectCurve);
        var template = "//span[contains(text(),'%s')]";
        var curveXpath = By.xpath(String.format(template, curveName));
        return getPage(Validations.class).verifyElementExists(curveXpath);
    }

    /**
     * Get column data in forecast details page
     * [Data which is not null or empty is returned]
     *
     * @param columnName column name
     * @return List of data
     */
    public List<String> getColumnDataFromForecastDetailsPage(BudgetForecastPageColumns columnName) {
        var listOfData = getPage(ProgramDetailsPage.class).getAllItemListData(columnName.getValue());
        var columnData = new ArrayList<String>();
        for (var data : listOfData) {
            if (!data.contains(Constants.EMPTY_STRING)) {
                columnData.add(data.get(0));
            }
        }
        return columnData;
    }

    /**
     * Select curve variant in Ribbon menu
     *
     * @param curveName curve name to be selected
     */
    public void selectCurveVariantInRibbonMenu(String curveName) {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.SelectCurve);
        var template = "//span[contains(text(),'%s')]";
        var curveXpath = By.xpath(String.format(template, curveName));
        elementHelper.scrollToView(curveXpath);
        elementHelper.doClick(curveXpath);
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
    }

    /**
     * Validate curve name column, on variant selection
     *
     * @param curveName curve name
     * @param parameter parameter name
     * @return true on presence of expected curve name
     */
    public boolean validateCurveNameColumn(String curveName, List<String> parameter) {
        while (validateSelectionOfRecords(parameter)) {
            waitHelper.waitForPageToLoad();
        }
        var columnValue = getColumnDataFromForecastDetailsPage(BudgetForecastPageColumns.Curve);
        return columnValue.contains(curveName);
    }

    /**
     * Verify the curve distribution window
     *
     * @return true on verifying the curve distribution chart
     */
    public boolean verifyCurveDistributionPreviewWindow() {
        clickRibbonIcon(RibbonIcons.CurvePreview);
        if (getPage(ToastUtil.class).validatePresenceOfToastMessage()) {
            getPage(ToastUtil.class).waitForToastDisappears();
            return false;
        } else {
            waitHelper.waitForPageToLoad(distributionChart);
            waitHelper.waitForPageToLoad(maximizeChart);
            elementHelper.doClick(maximizeChart);
            waitHelper.waitForPageToLoad(distributionChartWindow);
            var result = getPage(Validations.class).verifyElementExists(distributionChartWindowCloseButton);
            elementHelper.doClick(distributionChartWindowCloseButton);
            return result;
        }
    }

    /**
     * Save and forecast the changes in Forecast details page
     */
    public void saveAndForecastChanges() {
        waitHelper.waitForPageToLoad(RibbonIcons.Save);
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
        getPage(ToastUtil.class).waitForToastDisappears();
        waitHelper.waitForPageToLoad();
    }

}

