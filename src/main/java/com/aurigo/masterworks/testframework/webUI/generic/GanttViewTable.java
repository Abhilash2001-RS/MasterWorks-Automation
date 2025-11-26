package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GanttViewTable extends RibbonMenu {


    private By ganttViewListRows;

    public GanttViewTable(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("GanttViewTable.json");
        ganttViewListRows = locators.get("ganttViewListRows");

    }

    /**
     * Function to get a column number of a list page column header
     *
     * @param columnHeaderName Header of the Column in the List page
     * @return Column number of header.
     */
    public int getColumnNumberOfGanttTable(String columnHeaderName) {
        int headerNum = 0;
        List<WebElement> headers = elementHelper.getElements(By.xpath(".//thead//th"));
        for (var i = 1; i <= headers.size(); i++) {
            var temp = elementHelper.getElement(By.xpath(".//thead//th[" + i + "]"));
            elementHelper.scrollToView(temp);
            if (temp.getText().equals(columnHeaderName)) {
                headerNum = headers.indexOf(temp) + 1;
                break;
            }
        }
        return headerNum;
    }

    /**
     * Function to get all the required item field values
     *
     * @param columnName field column names, Can pass multiple column names
     * @return List of column data.
     */
    public List<String> getAllDataUnderColumnHeader(String columnName) {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        ArrayList<String> listPageData = new ArrayList<>();
        //Get Column Header Number
        int headerNumber = getColumnNumberOfGanttTable(columnName);
        List<WebElement> ganttviewRows = elementHelper.getElements(ganttViewListRows);
        if (!ganttviewRows.isEmpty()) {
            for (var row : ganttviewRows) {
                //Adding all column data of a row to a data list
                String dataValue = row.findElement(By.xpath("td[" + headerNumber + "]")).getText();
                if (!dataValue.isEmpty())
                    listPageData.add(dataValue);
            }
        }
        return listPageData;
    }
}
