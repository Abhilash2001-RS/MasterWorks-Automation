package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.testData.Program;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramDetailsViewPage extends RibbonMenu {

    private By programTitleText;
    private By programYearText;
    private By programCategoryText;
    private By projectGridDiv;
    private By numberOfProjects;
    private By projectsTable;
    private By projectForm;


    public ProgramDetailsViewPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ProgramDetailsViewPage.json");
        programTitleText = locators.get("programTitleText");
        programYearText = locators.get("programYearText");
        programCategoryText = locators.get("programCategoryText");
        projectGridDiv = locators.get("projectGridDiv");
        numberOfProjects = locators.get("numberOfProjects");
        projectsTable = locators.get("projectsTable");
        projectForm = locators.get("projectForm");
    }

    /**
     * Method to validate the view form contents of the program detail page
     *
     * @param program program data to validate the form
     * @return true if the validation is successful
     */
    public boolean validateProgramDetailsViewForm(Program program) {
        Map<By, String> textFields = new HashMap<>();
        textFields.put(programTitleText, program.title);
        textFields.put(programCategoryText, program.category);
        textFields.put(programYearText, program.year);
        return getPage(Validations.class).viewFormVerifyTextValues(textFields);
    }

    /**
     * Method to get Program Data in Program Details Page
     *
     * @return Map containing project details
     */
    public Map<String, String> getProgramData() {
        Map<String, String> fields = new HashMap<>();
        fields.put("programTitle", elementHelper.doGetText(programTitleText));
        fields.put("numberOfProjects", elementHelper.doGetText(numberOfProjects));
        fields.put("projectName", getPage(GenericForm.class).getCellValueRadGrid(projectsTable, 1, "Name"));
        return fields;
    }

    /**
     * Method to return the Project in Project Grid
     *
     * @return ProjectName
     */
    public String getFirstProject() {
        return getPage(GenericForm.class).getCellValueRadGrid(projectsTable, 1, "Name");
    }

    /**
     * Method to get the program Name
     *
     * @return Program Name
     */
    public String getProgramName() {
        return elementHelper.doGetText(programTitleText);
    }

    /**
     * Get the Project count in Program details page
     *
     * @return number of projects
     */
    public int getProjectCountInProgramDetailsPage() {
        waitHelper.waitForPageToLoad(numberOfProjects);
        return Integer.parseInt(elementHelper.doGetText(numberOfProjects));
    }

    /***
     * Method to check whether Project element is readonly/noneditable
     *
     * @return - true - if disabled (not editable)
     */
    public boolean isProjectValueNonEditable() {
        var element = elementHelper.getElement(projectForm);
        return (Strings.isNotNullAndNotEmpty(element.getAttribute("readonly")) && element.getAttribute("readonly").equals("true"));
    }

    /**
     * Validate accessing the planned project from the program details page
     *
     * @param projectName project name
     * @return true if the planned project is accessible
     */
    public boolean validateAccessingProject(String projectName, WebDriver driver) {
        var link = By.xpath(String.format("//a[contains(text(),'%s')]", projectName));
        if (getPage(Validations.class).verifyElementExists(link)) {
            waitHelper.waitForElementToBePresentAndClickable(link);
            elementHelper.doClick(link);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            waitHelper.waitForPageToLoad();
            var projectAccess = !getPage(ToastUtil.class).validatePresenceOfToastMessage();
            driver.close();
            driver.switchTo().window(tabs.get(0));
            return projectAccess;
        }
        return false;
    }

    /**
     * Access planned project added in the grid, from program details page
     *
     * @param projectName project name
     * @param driver      Webdriver driver
     */
    public void accessPlannedProjectFromProgramDetailsPage(String projectName, WebDriver driver) {
        var link = By.xpath(String.format("//a[contains(text(),'%s')]", projectName));
        waitHelper.waitForElementToBePresentAndClickable(link);
        elementHelper.doClick(link);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waitHelper.waitForPageToLoad();
    }
}
