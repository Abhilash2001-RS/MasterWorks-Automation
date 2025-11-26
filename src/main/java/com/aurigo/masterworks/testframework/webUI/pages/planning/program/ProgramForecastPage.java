package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.CustomFieldsHelper;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ProgramForecastListPageColumns;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.constants.enums.WorkFlowStatus;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ItemListPage;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ProgramForecastPage extends ItemListPage {

    private By programForecastDetailsTab;
    private By programForecastTableId;
    private By programGanttTable;
    private By forecastDataTable;
    private By contentRowsXpath;
    private By taskNameCell;
    private By calendarPickerXpath;
    private By textContentCell;
    private By gantTableRows;
    private By gantTableSection;
    private By frozenDataTableProgramForecast;
    private By displayedTextXpath;
    private By includedNameColumn;

    private WebDriver driver;

    public ProgramForecastPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("ProgramForecastPage.json");

        programForecastDetailsTab = locators.get("programForecastDetailsTab");
        programForecastTableId = locators.get("programForecastTableId");
        programGanttTable = locators.get("programGanttTable");
        forecastDataTable = locators.get("forecastDataTable");
        contentRowsXpath = locators.get("contentRowsXpath");
        taskNameCell = locators.get("taskNameCell");
        calendarPickerXpath = locators.get("calendarPickerXpath");
        textContentCell = locators.get("textContentCell");
        gantTableRows = locators.get("gantTableRows");
        gantTableSection = locators.get("gantTableSection");
        frozenDataTableProgramForecast = locators.get("frozenDataTableProgramForecast");
        displayedTextXpath = locators.get("displayedTextXpath");
        includedNameColumn = locators.get("includedNameColumn");
    }

    /**
     * Navigate To Forecast Details
     */
    public void navigateTo() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        elementHelper.doClick(programForecastDetailsTab);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Validate navigating to Forecast Details page
     *
     * @return true on successfully navigating
     */
    public boolean validateNavigationToPage() {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementClickable(programForecastDetailsTab);
        elementHelper.doClick(programForecastDetailsTab);
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(programForecastTableId);
    }

    /**
     * Update Forecast in program
     *
     * @return - true if updated
     */
    public boolean updateForecast() {
        waitHelper.waitForPageToLoad();
        if (elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.UpdateForecast))) {
            clickRibbonIcon(RibbonIcons.UpdateForecast);
            String toastMessageDisplayed = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
            clickRibbonIcon(RibbonIcons.Back);
            waitHelper.waitForPageToLoad();
            return !toastMessageDisplayed.isEmpty();
        } else {
            clickRibbonIcon(RibbonIcons.Back);
            waitHelper.waitForPageToLoad();
            return false;
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
        if(rowNumber==0){
            return elementHelper.getElements(By.xpath(String.format(
                    "//div[@id = '%s']//tbody//tr[not(contains(@style,'display:none')) and not(contains(@style,'display: none;'))]",
                    elementHelper.getLocatorAsString(tableId))));
        }
        else{
            return elementHelper.getElements(By.xpath(String.format(
                    "//div[@id = '%s']//tbody//tr[%d]//td[not(contains(@style,'display:none')) and not(contains(@style,'display: none;'))]",
                    elementHelper.getLocatorAsString(tableId),rowNumber)));
        }
    }

    /**
     * Method to Get Row Number of specified Record in Forecast details page
     *
     * @param columnName - The column where the text should be present
     * @param searchText - the Text to be Searched
     * @return RowNumber
     */
    public int getRowNumberInProgramForecastPage(ProgramForecastListPageColumns columnName, String searchText) {
        var rows = getRowValuesInForecastPage(programForecastTableId,0);
        int rowNum = 0;
        int colNum = getPage(ProgramDetailsPage.class).getColumnNumberUsingHeaderName(columnName.getValue());
        for (int i = 1; i <= rows.size(); i++) {
            if (getValueFromForecastPage(programForecastTableId, i, colNum).equalsIgnoreCase(searchText)) {
                rowNum = i;
                break;
            }
        }
        return rowNum;
    }

    /**
     * Get column data in forecast details page
     * [Data which is not null or empty is returned]
     *
     * @param columnName column name
     * @return List of data
     */
    public List<String> getColumnDataFromForecastDetailsPage(String columnName){
        var listOfData = getPage(ProgramDetailsPage.class).getAllItemListData(columnName);
        var columnData = new ArrayList<String>();
        for(var data:listOfData){
            if(!data.contains(Constants.EMPTY_STRING)){
                columnData.add(data.get(0));
            }
        }
        return columnData;
    }

    /**
     * Get presence of value in Forecast Page
     *
     * @param columnName - The column where the text should be present
     * @param searchText - the Text to be Searched
     * @return - true if found/false if not found
     */
    public boolean valuePresentInForecastPage(ProgramForecastListPageColumns columnName, String searchText) {
        int rowNum = getRowNumberInProgramForecastPage(columnName, searchText);
        return rowNum != 0;
    }

    /**
     * Verify previous and next period button in Program Monthly View
     *
     * @return - true if the functionality is working as expected
     */
    public boolean verifyPreviousAndNextPeriodInItemProgramMonthlyView() {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.MonthlyView);
        waitHelper.waitForPageTabHeaderToBeClickable();
        boolean verifyNextPeriodButton = elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.Next));
        boolean verifyPreviousPeriodButton = false;
        if (verifyNextPeriodButton) {
            clickRibbonIcon(RibbonIcons.Next);
            waitHelper.waitForPageToLoad();
            verifyPreviousPeriodButton = elementHelper.isElementDisplayed(getRibbonIcon(RibbonIcons.Previous));
        }
        return (verifyNextPeriodButton && verifyPreviousPeriodButton);
    }

    /**
     * Click SaveAndAccept with Alert
     */
    public void clickSaveAndAcceptWithAlert() {
        logger().info("Clicking SaveAndAccept");
        clickRibbonIcon(RibbonIcons.Save);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate the presence of update forecast icon
     * @return true if the update forecast button is available
     */
    public boolean validateThePresenceOfUpdateForecastOption(){
        waitHelper.waitForPageToLoad(programForecastDetailsTab);
        return getPage(Validations.class).verifyElementExists(getRibbonIcon(RibbonIcons.UpdateForecast));
    }

    /**
     * Validate presence of ribbon icons
     *
     * @return true on validation of the above
     */
    public boolean validatePresenceOfRibbonIcons(){
        var icons = new ArrayList<RibbonIcons>();
        icons.add(RibbonIcons.Save);
        icons.add(RibbonIcons.Cancel);
        icons.add(RibbonIcons.UpdateForecast);
        icons.add(RibbonIcons.Snapshot);
        icons.add(RibbonIcons.More);

        return validateRibbonIcons(icons);
    }

    /**
     * Validate presence of program gantt grid
     *
     * @return true on validation of the above
     */
    public boolean validatePresenceOfProgramGantGrid(){
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(programGanttTable);
    }

    /**
     * Validate Project forecast update
     *
     * @param updatedDate updated date
     * @return true on validation of the above
     */
    public boolean validateProjectForecastUpdate(String updatedDate){
        var columnName = ProgramForecastListPageColumns.IsRevisionAvailable.getValue();

        var listOfData = getPage(ProgramDetailsPage.class).getAllItemListData(columnName);
        var forecastUpdateColumnData = new ArrayList<>();
        for(var data:listOfData){
            if(!data.contains(Constants.EMPTY_STRING)){
                forecastUpdateColumnData.add(data.get(0));
            }
        }
        if(forecastUpdateColumnData.isEmpty()){
            return false;
        }
        var forecastUpdateStatus = forecastUpdateColumnData.get(0).toString().split(" ")[1].trim();
        var forecastUpdateDate = forecastUpdateColumnData.get(0).toString().split(" ")[0].trim();
        return forecastUpdateStatus.contains("FID-")&&forecastUpdateDate.contains(updatedDate);
    }

    /**
     * Validate project status in the Project Forecast page
     *
     * @param expectedStatus expected project status
     * @param parameter project name
     * @return true on validation of the above
     */
    public boolean validateProjectStatus(WorkFlowStatus expectedStatus, String parameter){
        var statusList = getPage(ProgramDetailsPage.class).getAllItemListData(ProgramForecastListPageColumns.ProjectStatus.getValue());
        var rowNumber = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,parameter);
        var projectStatus = statusList.get(rowNumber-1).get(0);
        return projectStatus.equals(expectedStatus.getValue());
    }

    /**
     * Validate presence of Program Forecast page columns
     *
     * @return true on validation of the above
     */
    public boolean validatePresenceOfListPageColumns(){
        var columnList = ProgramForecastListPageColumns.getList();
        var presenceOfColumn = false;
        for (var a :columnList) {
            var columnIndex = getPage(ProgramDetailsPage.class).getColumnNumberUsingHeaderName(a);
            if(columnIndex==-1){
                presenceOfColumn = false;
                break;
            }
            else presenceOfColumn=true;
        }
        return presenceOfColumn;
    }

    /**
     * Retrieve the selected Gantt View
     *
     * @return the name of the view being selected
     */
    public String retrieveSelectedGanttView(){
        clickRibbonIcon(RibbonIcons.More);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.GanttViews));
        var element = elementHelper.getElement(getRibbonIcon(RibbonIcons.GanttViews));
        var textXpath = element.findElement(displayedTextXpath);
        return textXpath.getText();
    }

    /**
     * Verify if the forecast is distributed yearly
     *
     * @param programYear program year
     * @return true if the forecast distribution is done yearly
     */
    public Boolean verifyYearlyDistributedForecasts(String programYear){
        navigation.switchFrameToContent();
        int secondYear = Integer.parseInt(programYear.replace("20", ""))+1;
        String firstColumn = programYear + "-" + secondYear;
        var columnXpathTemp = "//div[contains(text(),' %s')]";
        var element = By.xpath(String.format(columnXpathTemp,firstColumn));
        JavaScriptUtil.scrollIntoView(elementHelper.getElement(element),driver);
        return getPage(Validations.class).verifyElementExists(element);
    }

    /**
     * Get list of elements under a required column
     *
     * @param frozenDataTable true if the table to fetch data is frozen, false otherwise
     * @param column column name
     * @return list of web elements
     */
    public List<WebElement> getColumnElements(boolean frozenDataTable, ProgramForecastListPageColumns column){
        By tableId = frozenDataTable ? frozenDataTableProgramForecast : forecastDataTable;
        var columnIndex = getPage(ProgramDetailsPage.class).getColumnNumberUsingHeaderName(column.getValue());
        return elementHelper.getElement(tableId).findElements(By.xpath(
                ".//tr/td[" + (columnIndex + 1) + "]"));
    }

    /**
     * Validate included Column
     *
     * @return true, if the checkboxes are checked by default
     */
    public boolean validateIncludedColumn(){
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        var checkBoxes = elementHelper.getElements(By.xpath("//input[@checked]"));
        return !checkBoxes.isEmpty();
    }

    /**
     * Verify if program start date is editable
     *
     * @param project project details
     * @param program program name
     * @return true if the program start date is editable
     */
    public boolean verifyIfProgramStartDateIsEditable(Project project, String program){
        var columnMap = getPage(ProgramPage.class).getScheduleGanttPageColumnMap();
        var contentRows = elementHelper.getElements(contentRowsXpath);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,program);

        var row = contentRows.get(rowIndex-1);
        var cellIndex = columnMap.get("Start Date");
        var cells = row.findElements(By.tagName("td"));
        var startDateCell = cells.get(cellIndex);
        startDateCell.click();
        startDateCell.click();

        return getPage(Validations.class).verifyElementExists(calendarPickerXpath);
    }

    /**
     * Navigate to Forecast SnapShot page
     */
    public void navigateToSnapshotPage(){
        clickRibbonIcon(RibbonIcons.Snapshot);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get Planning Fund cost added in Program
     *
     * @param fundFormat Fund template in Forecast page
     * @return string value of the cost
     */
    public String getPlanningFundCostAddedInProgram(String fundFormat){
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Cancel));
        var amounts = getPage(ProgramDetailsPage.class).getAllItemListData(ProgramForecastListPageColumns.AmountIn$.getValue());
        waitHelper.waitForPageToLoad(RibbonIcons.Cancel);
        var rowIndex = getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,fundFormat);
        return amounts.get(rowIndex-1).get(0);
    }

    /**
     * Validate the presence of Gantt view bars
     *
     * @param identifier row identifier in forecast page
     * @return true on validation of the above
     */
    public boolean validatePresenceOfGanttTableRow(String identifier){
        var rowIndex = getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,identifier);
        var rows = elementHelper.getElements(gantTableRows);
        var requiredRow = rows.get(rowIndex);
        return requiredRow!=null;
    }

    /**
     * Show Gantt View in Program Forecast page
     */
    public void showGanttView(){
        clickRibbonIcon(RibbonIcons.ShowGantt);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Get Planning Fund dates
     *
     * @param fundFormat fund template in Forecast page
     * @param isStartDate true if start date is required
     * @param dateFormat string value of the date format
     * @return Local date
     */
    public LocalDate getPlanningFundDate(String fundFormat, boolean isStartDate, String dateFormat){
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Cancel));
        List<List<String>> dates = null;
        if(isStartDate){
            dates = getPage(ProgramDetailsPage.class).getAllItemListData(ProgramForecastListPageColumns.StartDate.getValue());
        }
        else {
            dates = getPage(ProgramDetailsPage.class).getAllItemListData(ProgramForecastListPageColumns.EndDate.getValue());
        }
        var rowIndex = getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,fundFormat);
        var dateString = dates.get(rowIndex-1).get(0);
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Navigate to Forecast views
     * @param icon icon of the required view to be selected
     */
    public void navigateToForecastView(RibbonIcons icon){
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Cancel));
        clickRibbonIcon(icon);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate the included Checkbox in Program Forecast page
     *
     * @param project project details
     * @return true if the Checkbox is editable (enabled)
     */
    public boolean validateIncludedCheckbox(Project project){
        var checkboxes = getPage(ProgramForecastPage.class).getColumnElements(false,ProgramForecastListPageColumns.Included);
        var prjParam = String.format("%s - %s",project.code, project.name);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,prjParam);
        var checkbox = checkboxes.get(rowIndex-1);
        return checkbox.isEnabled();
    }

    /**
     * Method to change the project End date in Forecast details page in Program
     *
     * @param project          - project details
     * @param updatedEndDate   - updated date
     * @param saveChanges      - true if save is required
     * @param validate         - true if validation is required
     * @return String value of the Toast message for validation
     */
    public String changeProjectEndDateInProgramForecast(Project project, LocalDate updatedEndDate, boolean saveChanges, boolean validate) {
        var columnMap = getPage(ProgramPage.class).getScheduleGanttPageColumnMap();
        var contentRows = elementHelper.getElements(contentRowsXpath);

        var parameter = String.format("%s - %s",project.code, project.name);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,parameter);

        var row = contentRows.get(rowIndex-1);
        if (columnMap.containsKey("End Date")) {
            var cellIndex = columnMap.get("End Date");
            var cells = row.findElements(By.tagName("td"));
            var startDateCell = cells.get(cellIndex);
            startDateCell.click();
            //clicking again to bring up the date picker
            startDateCell.click();
            getPage(CustomFieldsHelper.class).scheduleGanttPageDatePickerSelectDate(updatedEndDate);

        } else {
            logger().fail("Column 'End Date' not found on page");
        }
        if(saveChanges){
            waitHelper.waitForPageToLoad();
            clickRibbonIcon(RibbonIcons.Save);
            getPage(AlertHandler.class).acceptAlert(true);
            waitHelper.waitForPageToLoad();
        }
        if(validate){
            return getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
        }else return null;
    }

    /**
     * Uncheck project checkbox in Program Forecast listpage
     *
     * @param project project to be unchecked
     */
    public void uncheckIncludedCheckboxForProject(Project project){
        var checkboxes = getPage(ProgramForecastPage.class).getColumnElements(true,ProgramForecastListPageColumns.Included);
        var prjParam = String.format("%s - %s",project.code, project.name);
        var rowIndex = getPage(ProgramForecastPage.class).getRowNumberInProgramForecastPage(ProgramForecastListPageColumns.Name,prjParam);
        var checkbox = checkboxes.get(rowIndex-1);
        elementHelper.doClick(checkbox);
    }

    /**
     * Check if Show Gantt button or Hid Gantt button is visible
     * @param isShowGantt True if checking for Show Gantt button
     * @return True if expected button is present
     */
    public boolean isGanttButtonDisplayed(boolean isShowGantt)
    {
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.Cancel));
        boolean isTrue = false;
        if(isShowGantt)
        {
            isTrue =  elementHelper.isElementEnabled(getRibbonIcon(RibbonIcons.ShowGantt));
            clickRibbonIcon(RibbonIcons.ShowGantt);
            waitHelper.waitForElementPresent(gantTableSection);
        }
        else {
            isTrue = elementHelper.isElementEnabled(getRibbonIcon(RibbonIcons.HideGantt));
            clickRibbonIcon(RibbonIcons.HideGantt);
            waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.ShowGantt));
        }
        return isTrue;
    }

    /**
     * Get program years distribution column values
     *
     * @param programYear program year
     * @param programDuration program year duration
     * @param fiscalYear selected Fiscal year
     * @return List of column values for program year distribution
     */
    public List<String> getProgramYearsDistribution(String programYear, Integer programDuration, String fiscalYear){
        navigation.switchFrameToContent();
        var columns = new ArrayList<String>();
        if(fiscalYear.equals(getPage(GenericFormProposed.class).standardFiscalYear)){
            int currentYear = Integer.parseInt(programYear.replace("20", ""));
            int finalYear = (currentYear+programDuration);
            while (currentYear < finalYear){
                String columnValue = String.format("20%d-%d",currentYear,(currentYear+1));
                columns.add(columnValue);
                currentYear+=1;
            }
        }
        else{
            int currentYear = Integer.parseInt(programYear);
            int finalYear = (currentYear+programDuration);
            while (currentYear < finalYear){
                String columnValue = String.valueOf(currentYear);
                columns.add(columnValue);
                currentYear+=1;
            }
        }
        return columns;
    }

    /**
     * Get number of days between 2 specified days
     *
     * @param startDate start date
     * @param endDate end date
     * @return number of days between start and end date
     */
    public Long getNumberOfDaysInARange(LocalDate startDate, LocalDate endDate){
        return ChronoUnit.DAYS.between(startDate,endDate);
    }

    /**
     * Get number of days overlapping between 2 ranges of date
     *
     * @param rangeOneStartDate range one start date
     * @param rangeOneEndDate range one end date
     * @param rangeTwoStartDate range two start date
     * @param rangeTwoEndDate range one end date
     * @return number of days overlapping
     */
    public Long getNumberOfOverlappingDaysInTwoDifferentRanges(String rangeOneStartDate, String rangeOneEndDate,
                                                               String rangeTwoStartDate, String rangeTwoEndDate, String dateFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        var startDate1 = LocalDate.parse(rangeOneStartDate,formatter);
        var endDate1 = LocalDate.parse(rangeOneEndDate,formatter);
        var startDate2 = LocalDate.parse(rangeTwoStartDate,formatter);
        var endDate2 = LocalDate.parse(rangeTwoEndDate,formatter);

        var firstEndDate= (endDate1.isBefore(endDate2))? endDate1:endDate2;
        var laterStartDate= (startDate1.isAfter(startDate2))? startDate1:startDate2;

        var days = getNumberOfDaysInARange(laterStartDate,firstEndDate);
        return (days>0)? days + 1 :0;
    }

    /**
     * Get current fiscal year, based on current date
     *
     * @param currentDate current date
     * @return string value of selected fiscal year
     */
    public String getCurrentFiscalYear(LocalDate currentDate){
        var start = LocalDate.of(currentDate.getYear(), Month.SEPTEMBER, 22);
        var end = LocalDate.of(currentDate.getYear(), Month.OCTOBER, 1);
        return (currentDate.isAfter(start)&&currentDate.isBefore(end))?
                getPage(GenericFormProposed.class).nonSeptFiscalYear:getPage(GenericFormProposed.class).standardFiscalYear;
    }

    /**
     * Get the list of fiscal start dates based on the selected fiscal year
     *
     * @param currentDate current date
     * @param programDuration program year duration
     * @param fiscalYear fiscal year
     * @param dateFormat date format
     * @return list of fiscal start and end dates
     */
    public HashMap<String, List<String>> getListOfFiscalStartDates(LocalDate currentDate, Integer programDuration, String fiscalYear, String dateFormat){
        HashMap<String, List<String>> fiscalDates = new HashMap<>();
        List<String> fiscalStartDates = new ArrayList<>();
        List<String> fiscalEndDates = new ArrayList<>();

        var currentYear = currentDate.getYear();
        if(fiscalYear.equals(getPage(GenericFormProposed.class).standardFiscalYear)){
            for(int i=0;i<programDuration;i++){
                fiscalStartDates.add(LocalDate.of(currentYear, Month.OCTOBER, 1).format(DateTimeFormatter.ofPattern(dateFormat)));
                currentYear+=1;
                fiscalEndDates.add(LocalDate.of(currentYear, Month.SEPTEMBER, 30).format(DateTimeFormatter.ofPattern(dateFormat)));
            }
        }
        else{
            for(int i=0;i<programDuration;i++){
                fiscalStartDates.add(LocalDate.of(currentYear, Month.JANUARY, 1).format(DateTimeFormatter.ofPattern(dateFormat)));
                currentYear+=1;
                fiscalEndDates.add(LocalDate.of(currentYear, Month.DECEMBER, 31).format(DateTimeFormatter.ofPattern(dateFormat)));
            }
        }
        fiscalDates.put("fiscalStartDates",fiscalStartDates);
        fiscalDates.put("fiscalEndDates",fiscalEndDates);

        return fiscalDates;
    }

    /**
     * Get the number of days for cost distribution evaluation
     *
     * @param fiscalYear fiscal year
     * @param programYearDuration program year duration
     * @param projectStart project start date
     * @param projectEnd project end date
     * @param dateFormat date format
     * @return List of number of days
     */
    public List<Long> getNumberOfDaysForCostDistribution(String fiscalYear, Integer programYearDuration, String projectStart, String projectEnd, String dateFormat){
        var costDistributionDays = new ArrayList<Long>();

        var fiscalDates = getListOfFiscalStartDates(LocalDate.parse(projectStart,DateTimeFormatter.ofPattern(dateFormat)),programYearDuration,fiscalYear,dateFormat);
        var fiscalStartDates = fiscalDates.get("fiscalStartDates");
        var fiscalEndDates = fiscalDates.get("fiscalEndDates");

        //Yearly distribution
        logger().info("Calculating number of days for yearly distribution");
        for(int i=0;i<programYearDuration;i++){
            costDistributionDays.add(getNumberOfOverlappingDaysInTwoDifferentRanges(projectStart,projectEnd,
                    fiscalStartDates.get(i),fiscalEndDates.get(i),dateFormat));
        }

        //Before amount days
        logger().info("Calculating number of days for Before amount calculation");
        var firstFiscalStartDate = LocalDate.parse(fiscalStartDates.get(0),DateTimeFormatter.ofPattern(dateFormat));
        var projectStartDate = LocalDate.parse(projectStart,DateTimeFormatter.ofPattern(dateFormat));
        var beforeDays = (projectStartDate.isBefore(firstFiscalStartDate))?
                getNumberOfDaysInARange(projectStartDate,firstFiscalStartDate):0;

        costDistributionDays.add(beforeDays);

        //After amount days
        logger().info("Calculating number of days for After amount calculation");
        var lastFiscalEndDate = LocalDate.parse(fiscalEndDates.get(programYearDuration-1),DateTimeFormatter.ofPattern(dateFormat));
        var projectEndDate = LocalDate.parse(projectEnd,DateTimeFormatter.ofPattern(dateFormat));
        var afterDays = lastFiscalEndDate.isBefore(projectEndDate)?
                getNumberOfDaysInARange(lastFiscalEndDate,projectEndDate)+1:0;

        costDistributionDays.add(afterDays);
        return costDistributionDays;
    }

    /**
     * Evaluate the distributed costs
     *
     * @param amount total amount in program
     * @param projectDays number of project days
     * @param costDistributionDays list of cost distribution days
     * @return list of string value of costs, for validation
     */
    public List<String> evaluateAndGetDistributedCosts(Long amount, Long projectDays, List<Long> costDistributionDays){
        float costPerDay = ((float)amount)/projectDays;
        ArrayList<String> costs = new ArrayList<>();
        for (Long costDistributionDay : costDistributionDays) {
            costs.add(String.valueOf(((float)costDistributionDay)*costPerDay));
        }
        return costs;
    }

    /**
     * Validate the program cost distribution
     *
     * @param costs costs value
     * @param programYear program year
     * @param programDuration program year duration
     * @param fiscalYear fiscal year
     * @param parameterColumnName parameter column name, to locate the record
     * @param parameter record identifier
     * @return true on successful cost validation
     */
    public boolean validateProgramCostDistribution(List<String> costs, String programYear, Integer programDuration,
                                                   String fiscalYear, ProgramForecastListPageColumns parameterColumnName, String parameter){
        var columns = getProgramYearsDistribution(programYear,programDuration,fiscalYear);
        columns.add(ProgramForecastListPageColumns.Before.getValue());
        columns.add(ProgramForecastListPageColumns.After.getValue());
        var columnData = new ArrayList<String>();
        var costsData = new ArrayList<String>();

        elementHelper.scrollToView(includedNameColumn);
        var records = getColumnDataFromForecastDetailsPage(ProgramForecastListPageColumns.Name.getValue());
        if(!records.contains(parameter)){
            return false;
        }
        else{
            elementHelper.scrollToView(includedNameColumn);
            var rowNumber = getRowNumberInProgramForecastPage(parameterColumnName,parameter);
            for (String column : columns) {
                columnData.add(getColumnDataFromForecastDetailsPage(column).get(rowNumber-1).split("\\.")[0].replace(",",""));
            }
            for(String cost : costs){
                costsData.add(cost.split("\\.")[0]);
            }
            return columnData.equals(costsData);
        }
    }

    /**
     * Get list of costs from Program Forecast page
     *
     * @param parameterColumnName parameter column name
     * @param parameterName parameter name
     * @param programYear program year
     * @param programDuration program year duration
     * @param fiscalYear string value of the fiscal year
     * @return List of costs displayed
     */
    public List<String> getListOfCosts(ProgramForecastListPageColumns parameterColumnName, String parameterName, String programYear, Integer programDuration,
                                       String fiscalYear){
        var columns = getProgramYearsDistribution(programYear,programDuration,fiscalYear);
        columns.add(ProgramForecastListPageColumns.Before.getValue());
        columns.add(ProgramForecastListPageColumns.After.getValue());
        var columnData = new ArrayList<String>();

        var rowNumber = getRowNumberInProgramForecastPage(parameterColumnName,parameterName);
        for (String column : columns) {
            columnData.add(getColumnDataFromForecastDetailsPage(column).get(rowNumber-1).split("\\.")[0].replace(",",""));
        }
        return columnData;
    }

    /**
     * Validate Program Cost distribution with Inflation
     *
     * @param expectedCosts expected costs
     * @param actualCosts actual costs
     * @return true if the expected and actual costs are same
     */
    public boolean validateCostDistributionWithInflationInBudget(List<String> expectedCosts, List<String> actualCosts){
        ArrayList<Integer> actualCostsValue = new ArrayList<>();
        ArrayList<Integer> expectedCostsValue = new ArrayList<>();

        for(var cost : actualCosts){
            actualCostsValue.add(Integer.parseInt(cost));
        }

        for(var cost : expectedCosts){
            expectedCostsValue.add(Integer.parseInt(cost.split("\\.")[0]));
        }

        var n = 0;
        var result = false;
        while (n<expectedCostsValue.size()){
            result=expectedCostsValue.get(n)<=actualCostsValue.get(n);
            n++;
        }
        return result;
    }
}
