package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ProgramGanttViewPageColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.GanttViewTable;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramGanttViewPage extends RibbonMenu {


    private By ganttFilterPopUp;
    private By applyFilterButtonGanttFilter;
    private By programForecastDetailsColumnHeaders;

    private WebDriver driver;


    public ProgramGanttViewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("ProgramGanttViewPage.json");

        ganttFilterPopUp = locators.get("ganttFilterPopUp");
        applyFilterButtonGanttFilter = locators.get("applyFilterButtonGanttFilter");
        programForecastDetailsColumnHeaders = locators.get("programForecastDetailsColumnHeaders");
    }

    /**
     * Clicks Gantt Filter Button.
     */
    public void clickGanttFilterButton() {
        clickRibbonIcon(RibbonIcons.FilterGantt);
        waitHelper.waitForElementPresent(ganttFilterPopUp);
    }

    /**
     * Click Apply filter button.
     */
    public void clickApplyFilterButton() {
        elementHelper.doClick(applyFilterButtonGanttFilter);
        waitHelper.waitUntilElementDisappears(ganttFilterPopUp);
    }

    /**
     * Get gantt view column data.
     *
     * @param columnName Column Name to get data from.
     * @return List of column data.
     */
    public List<String> getGanttViewColumnData(ProgramGanttViewPageColumns columnName) {
        return getPage(GanttViewTable.class).getAllDataUnderColumnHeader(columnName.getValue());
    }

    /**
     * Remove Gantt view filter.
     *
     * @return True if no issue else false.
     */
    public boolean removeFilter() {
        clickRibbonIcon(RibbonIcons.GanttViewRemoveFilter);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Get Forecasts Year Details
     *
     * @param programTitle Name of the program
     * @return Forecasts Year Details
     */
    public Map<String, String> getForecastsYearDetails(String programTitle) {
        waitHelper.waitForPageToLoad();
        List<WebElement> getColumnHeaders = elementHelper.getElements(programForecastDetailsColumnHeaders);
        waitHelper.waitForPageToLoad();
        Map<String, String> forecastsAmount = new HashMap<>();
        Map<String, Integer> headerDetails = new HashMap<String, Integer>();
        List<String> headerName = new ArrayList<>();
        int nameIndex = 0;
        for (int i = 0; i < getColumnHeaders.size(); i++) {
            String columnHeader = elementHelper.doGetText(getColumnHeaders.get(i));
            if (columnHeader.equals("Name")) {
                nameIndex = i + 1;
            }
            headerName.add(columnHeader);
            headerDetails.put(columnHeader, i);
        }
        List<String> allColumData = new ArrayList<>();
        elementHelper.getElements(By.xpath(String.format("//table[@class='ui-frozen-body-table ui-selectable ui-grid-body-table']/descendant::tr/td[%s]", nameIndex))).
                forEach(
                        e -> allColumData.add(e.getText()));
        int row = allColumData.indexOf(programTitle);
        List<String> headerInfo = new ArrayList<>();
        headerInfo.addAll(headerName);
        headerInfo.remove("Included");
        headerInfo.remove("Name");
        for (int i = 0; i < headerInfo.size(); i++) {
            String headerColumnName = headerInfo.get(i);
            Integer columnNumber = headerDetails.get(headerColumnName);
            By xpathTemplate = By.xpath(String.format("//div[@id='gantt_container_grid']/descendant::div[@class='ui-widget-content ui-grid-body']/descendant::tr[%s]/td[%s]", row + 1, columnNumber - 1));
            elementHelper.scrollToView(xpathTemplate);
            waitHelper.waitForPageToLoad(xpathTemplate);
            String data = elementHelper.doGetText(xpathTemplate);
            logger().info(headerInfo + " - " + data);
            if (data.isEmpty()) {
                forecastsAmount.put(headerInfo.get(i), "N.A");
            } else {
                forecastsAmount.put(headerInfo.get(i), data);
            }
        }
        return forecastsAmount;
    }

    /**
     * Get Forecasts Details Amount
     *
     * @param programTitle Name of the program
     * @return Get Forecasts Month Details
     */
    public Map<String, String> getForecastsMonthDetails(String programTitle) {
        clickRibbonIcon(RibbonIcons.MonthlyView);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        var details = elementHelper.getElements(By.xpath(String.format
                ("//a[text()='%s']/../../../..//td[contains(@style,'right')]", programTitle)));
        var columnHeader = getPage(ItemListPage.class).itemListPageHeaders();
        columnHeader.remove("Name");
        columnHeader.remove("Description");
        columnHeader.remove("Project Status");
        Map<String, String> monthlyForecastsData = new HashMap<>();
        monthlyForecastsData.put("Name", programTitle);
        monthlyForecastsData.put("Description", programTitle + " Description");
        for (int i = 0; i < columnHeader.size(); i++) {
            String data = elementHelper.doGetText(details.get(i));
            if (!data.isEmpty()) {
                monthlyForecastsData.put(columnHeader.get(i), data);
            }

        }
        return monthlyForecastsData;
    }
}
