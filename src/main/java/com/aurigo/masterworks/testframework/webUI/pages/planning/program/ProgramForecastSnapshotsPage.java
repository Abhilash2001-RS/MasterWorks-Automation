package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.ExcelUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ListPageFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ProgramForecastSnapshotListPageColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ProgramListPageHeader;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramForecastSnapshotsPage extends ListPage {

    private By snpshtDscrptnPopUp;
    private By snpshtDscrptnPopUpTitle;
    private By snpshtDscrptnOkBtn;
    private By snpshtDscrptnTxtBox;
    private By snapShotHeaderColumns;
    private By snapShotBody;
    private String descriptionColumnName;

    private WebDriver driver;

    public ProgramForecastSnapshotsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        descriptionColumnName = "Snapshot Description";

        var locators = LocatorUtil.getLocators("ProgramForecastSnapshotsPage.json");
        snpshtDscrptnPopUp = locators.get("snpshtDscrptnPopUp");
        snpshtDscrptnPopUpTitle = locators.get("snpshtDscrptnPopUpTitle");
        snpshtDscrptnOkBtn = locators.get("snpshtDscrptnOkBtn");
        snpshtDscrptnTxtBox = locators.get("snpshtDscrptnTxtBox");
        snapShotHeaderColumns = locators.get("snapShotHeaderColumns");
        snapShotBody = locators.get("snapShotBody");
    }

    /**
     * Function to click on Forecast Snapshot button in ribbon
     */
    public void clickOnForecastSnapshot() {
        waitHelper.waitForPageTabHeaderToBeClickable();
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        clickRibbonIcon(RibbonIcons.Snapshot);
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
    }

    /**
     * Function to click on New button
     */
    public void clickNewButton() {
        navigation.switchFrameToContent();
        clickRibbonIcon(RibbonIcons.New);
        waitHelper.waitForElementPresent(snpshtDscrptnPopUp);
    }

    /**
     * Function to validate the title of the snapshot pop up window is "Snapshot Description"
     *
     * @return true if Title is Snapshot Description, else returns false
     */
    public boolean validateSnapshotPopUpTitle() {
        String expectedTitle = "Snapshot Description";
        var actualTitle = elementHelper.doGetText(snpshtDscrptnPopUpTitle);
        return expectedTitle.equals(actualTitle);
    }

    /**
     * Function to open snapshot description pop up
     */
    public void openSnapshotDescriptionPopUp() {
        clickOnForecastSnapshot();
        clickNewButton();
    }

    /**
     * Function to validate blank forecast snapshot
     *
     * @return true if user can't save the blank forecast snapshot
     * and if the error message is correct, else false is returned
     */
    public boolean validateBlankForecastSnapshot() {
        String expectedErrorMsg = "Please enter Snapshot Description to proceed.";
        elementHelper.doClick(snpshtDscrptnOkBtn);
        String actualErrorMsg = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
        return expectedErrorMsg.equals(actualErrorMsg);
    }

    /**
     * Function to validate that 150 characters are allowed in Snapshot description
     *
     * @return true if validation passes, else returns false
     */
    public boolean validateAcceptableNumberOfCharacters() {
        int arrayLength = 150;
        char character = '*';
        char[] array = new char[arrayLength];
        int pos = 0;
        while (pos < arrayLength) {
            array[pos] = character;
            pos++;
        }
        String value = new String(array);
        elementHelper.doSendKeys(snpshtDscrptnTxtBox, value);
        elementHelper.doClick(snpshtDscrptnOkBtn);
        filterListPage(ProgramForecastSnapshotListPageColumns.SnapshotDescription.getValue(), value, ListPageFilterOptions.EqualTo);
        return getNumberOfRowsInListPage() > 0;
    }

    /**
     * Create manual forecast snapshot
     *
     * @param description description
     * @return true on successful creation of record
     */
    public boolean createManualForecastSnapshot(String description){
        waitHelper.waitForPageToLoad(snpshtDscrptnTxtBox);
        elementHelper.doSendKeys(snpshtDscrptnTxtBox,description);
        waitHelper.waitForElementPresent(snpshtDscrptnOkBtn);
        elementHelper.doClick(snpshtDscrptnOkBtn);
        waitHelper.waitForPageToLoad();
        var toastMsg = getPage(ToastUtil.class).getToastMessage();
        return toastMsg.equals(Constants.FORECAST_SNAPSHOT_TOAST_MESSAGE);
    }

    /**
     * Delete the forecast snapshot
     *
     * @param description description
     */
    public void deleteManualForecastSnapshot(String description){
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        singleClickOnRowListPage(descriptionColumnName,description);
        getPage(GenericForm.class).clickDelete();
    }

    /**
     * Validate forecast snapshot on list page
     *
     * @param description description
     * @return true if the record is found
     */
    public boolean validateSnapshotRecordInListPage(String description){
        filterListPage(descriptionColumnName,description,ListPageFilterOptions.Contains);
        return description.equals(getColumnData(descriptionColumnName).get(0));
    }

    /**
     * Click cancel on list page
     */
    public void clickCancel(){
        clickRibbonIcon(RibbonIcons.Cancel);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate Excel Data Exported
     *
     * @param fileName                - name of the file exported
     * @param node                    - host node
     * @param projectName             - the project name to check
     * @return - true if the excel values and headers matches with the list page
     */
    public boolean validateExcelDataExport(String fileName, Host node, String projectName) {
        waitHelper.waitForPageToLoad();
        List<String> columnHeaders = new ArrayList<>();
        elementHelper.getElement(snapShotHeaderColumns).findElements(By.xpath(".//th")).forEach(x->columnHeaders.add(elementHelper.doGetText(x).contains("-")?elementHelper.doGetText(x).split("-")[0]:elementHelper.doGetText(x)));
        Map<String, String> excelColumnHeadersWithValue = ExcelUtil.getColumnHeadersWithRowData(fileName, node, ProgramListPageHeader.Name.getValue(), projectName,null);
        List<String> newExcelHeaders = new ArrayList<>();
        excelColumnHeadersWithValue.keySet().forEach(x->newExcelHeaders.add(x.contains("-")?x.split("-")[0]:x));
        return (columnHeaders.containsAll(newExcelHeaders));
    }
}

