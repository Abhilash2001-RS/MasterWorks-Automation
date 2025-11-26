package com.aurigo.masterworks.testframework.webUI.pages.administration.functionalConfigurations;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ProgramCycleConfigurations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class PlanningManagement extends RibbonMenu {

    private By masterProgramUpdateProjectDatesRadioButton;
    private By programLevelText;
    private By programLevelDropDownId;
    private By forecastDistribution;
    private By updateProjectDates;
    private By fiscalStart;
    private By fiscalEnd;
    private By programDuration;
    private By programCycleConfigurationsGrid;
    private By programCycleConfigurationsGridData;
    private By addFiscalYearButton;
    private By fiscalYearName;
    private By fiscalYearStartMonthField;
    private By programYearDuration;
    private By saveButtonInPopUp;

    private String fiscalYearNameXpathFormat = "//td[contains(text(),'%s')]";

    public PlanningManagement(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("PlanningManagement.json");
        masterProgramUpdateProjectDatesRadioButton = locators.get("masterProgramUpdateProjectDatesRadioButton");
        programLevelDropDownId = locators.get("programLevelDropDownId");
        programLevelText = locators.get("programLevelText");
        forecastDistribution = locators.get("forecastDistribution");
        updateProjectDates = locators.get("updateProjectDates");
        fiscalStart = locators.get("fiscalStart");
        fiscalEnd = locators.get("fiscalEnd");
        programDuration = locators.get("programDuration");
        programCycleConfigurationsGrid = locators.get("programCycleConfigurationsGrid");
        programCycleConfigurationsGridData = locators.get("programCycleConfigurationsGridData");
        addFiscalYearButton = locators.get("addFiscalYearButton");
        fiscalYearName = locators.get("fiscalYearName");
        fiscalYearStartMonthField = locators.get("fiscalYearStartMonthField");
        programYearDuration = locators.get("programYearDuration");
        saveButtonInPopUp = locators.get("saveButtonInPopUp");
    }

    /**
     * Method to navigate to Application settings
     */
    public void navigateToPlanningManagement() {
        navigation.switchFrameToDefault();
        navigation.clickOnExpandAll();
        waitHelper.waitForPageToLoad();
        navigation.navigateToFormInLeftPaneTree("Functional Configurations/Planning Management");
        navigation.switchFrameToContent();
    }

    /**
     * Enable "update project dates for Master Program"
     */
    public void setUpdateProjectDatesForMasterProgram() {
        elementHelper.doClick(masterProgramUpdateProjectDatesRadioButton);
    }

    /**
     * Validate whether the program level field is available or not in the form
     * @return True if the text and the selection field is available
     */
    public boolean validationOfProgramLevelField()
    {
        waitHelper.waitForPageToLoad(programLevelText);
        return (elementHelper.isElementDisplayed(programLevelText) && elementHelper.isElementDisplayed(programLevelDropDownId));
    }

    /**
     * Get the values of the dropdown option in Program level field
     * @return List of the options under the drop down in string format
     */
    public List<String> getProgramLevelFieldOptions()
    {
        waitHelper.waitForPageToLoad();
        List<String> options = new ArrayList<>();
        var values = elementHelper.getComboBoxSelectOptions(programLevelDropDownId);
        values.forEach(x -> options.add(x.getText()));
        return options;
    }

    /**
     * Method to validate the available Dropdowns in the List
     *
     * @param expectedDropdownValues  - The Dropdown Values that must be present
     * @return true if the expectedValues are matched with the availableValues
     */
    public boolean validateDropdownsOptions(List<String> expectedDropdownValues) {
        return elementHelper.getSelectOptions(forecastDistribution).equals(expectedDropdownValues);
    }

    /**
     * Validate whether the Update Project dates on Approval  field is available or not in the form
     * @return True if the field is available
     */
    public boolean checkUpdateProjectDatesOnProgramApproval()
    {
        navigation.switchFrameToContent();
        return (elementHelper.isElementDisplayed(updateProjectDates));
    }

    /**
     * Validate whether the forecast distribution field is available or not in the form
     * @return True if the text and the selection field is available
     */
    public boolean validateOptionsUnderProgramCycleConfiguration(){
        navigation.switchFrameToContent();
        boolean fiscalStartIsPresent = elementHelper.isElementDisplayed(fiscalStart);
        boolean fiscalEndIsPresent = elementHelper.isElementDisplayed(fiscalEnd);
        boolean programDurationIsPresent = elementHelper.isElementDisplayed(programDuration);
        return fiscalStartIsPresent && fiscalEndIsPresent && programDurationIsPresent;
    }

    /**
     * Configure a build to Level-2 (MCON)
     * Caution!! - DO NOT USE this method anywhere, as this change cannot be reverted back
     */
    public void configureABuildToLevel2(){
        waitHelper.waitForElementPresent(programLevelDropDownId);
        var initialValue = elementHelper.doGetAttribute(programLevelDropDownId,"initialvalue");
        if(initialValue.equals("1")&&currentEnvironment.getType().equalsIgnoreCase("MCON")){
            elementHelper.selectComboBoxItemByText(programLevelDropDownId,"2-Level");
        }
    }

    /**
     * Save the changes done on the page
     */
    public void saveSettings(){
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }


    /**
     * Get Program cycle configurations.
     * @param columnName Column name for which the value to be picked.
     * @param configurationName Configuration Name under which the column value to be picked. If empty, then defaults to 1st row.
     * @return Program cycle configurations column value.
     */
    public String getProgramCycleConfigurations(ProgramCycleConfigurations columnName, String configurationName) {
        List<List<String>> configurations = getProgramCycleConfigurations();
        int colIndex = configurations.get(0).indexOf(columnName.getValue());
        if(Strings.isNotNullAndNotEmpty(configurationName)){
            var config = configurations.stream().filter(c -> c.contains(configurationName)).findFirst();
            return config.isPresent()? config.get().get(colIndex): Constants.EMPTY_STRING;
        }

        return configurations.get(1).get(colIndex);
    }

    /**
     * Get Program cycle configurations.
     * @return Program cycle configurations.
     */
    public List<List<String>> getProgramCycleConfigurations() {
        navigation.switchFrameToContent();
        waitHelper.waitForPageTabHeaderToBeClickable();
        String headerXpath = String.format("//div[@id='%s']//th[not(contains(@style, 'display:none;'))]", elementHelper.getLocatorAsString(programCycleConfigurationsGrid));
        List<List<String>> configurations = new ArrayList<>();
        List<String> headerCells = new ArrayList<>();
        elementHelper.getElements(By.xpath(headerXpath)).stream().
                filter(h -> Strings.isNotNullAndNotEmpty(h.getText())).forEach(h -> headerCells.add(h.getText()));
        configurations.add(headerCells);
        var rows = elementHelper.getElements(programCycleConfigurationsGridData);
        for (var row : rows) {
            List<String> data = new ArrayList<>();
            row.findElements(By.tagName("td")).stream().filter(td -> !td.getAttribute("style").contains("none")).
                    forEach(h -> data.add(h.getText()));
            configurations.add(data);
        }

        return configurations;
    }

    /**
     * Add required Fiscal year
     * @param name name of FY
     * @param startMonth start month
     * @param programDuration program duration
     */
    public void addFiscalYear(String name, String startMonth, String programDuration){
        waitHelper.waitForPageToLoad(addFiscalYearButton);
        var fiscalYearLocator = By.xpath(String.format(fiscalYearNameXpathFormat,name));
        if(!getPage(Validations.class).verifyElementExists(fiscalYearLocator)){
            elementHelper.doClick(addFiscalYearButton);
            waitHelper.waitForPageToLoad(fiscalYearName);
            elementHelper.doSendKeys(fiscalYearName,name);
            elementHelper.selectComboBoxItemByText(fiscalYearStartMonthField,startMonth);
            elementHelper.selectComboBoxItemByValue(programYearDuration,programDuration);
            elementHelper.doClick(saveButtonInPopUp);
            waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
        }
    }
}