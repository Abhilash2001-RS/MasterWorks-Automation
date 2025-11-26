package com.aurigo.masterworks.testframework.webUI.pages.autodesk;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.Navigation;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.ListPageFilterOptions;
import com.aurigo.masterworks.testframework.webUI.constants.enums.PlannedProjectListPageHeader;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.CapitalForms;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.FinancialForms;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.ReportGalleryForms;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Navigations extends ListPage {


    private By aurigoLogo;
    private By capitalPlanningDashboard;
    private By projectDashboardTab;
    public By newDashboard;
    public By budgetChangesTitle;
    public By programCostsChartTitle;
    public By cashFlowTitle;
    public By plannedProjectsDashboard;
    public By leftPanelList;
    public By expandAll;
    public By collapseAll;
    public By libraryDashboardTab;
    public By administrationTab;
    public By reportGalleryTab;
    public By reportGalleryReportList;
    public By revisionProgramTab;
    public By arrowButton;
    public By kebabOption;
    public By getKebabButtonsList;
    public By selectedRecordsCount;
    public By logoutLink;
    public By listViewButtonOptions;
    public By listViewKebabButtonOptions;
    public By dropdownValues;
    public By administrationDashboard;
    public By libraryDashboard;
    public By executionProjectDashboard;
    public By workFlowParentMenu;
    public By parentNewMenu;
    public By parentImportExportMenu;
    public By parentMoreMenu;
    public By reportsParentMenu;
    public By DoWorkFlowParentMenu;
    public By activeInactiveParentMenu;

    private WebDriver driver;

    private String navigationTab = "//a[contains(text(),'%s')]";
    private String listViewDropDownOptions = "//*[not(contains(@class,'hideMenuItem')) and (@parentmenu='%s')]/span[@class='rrbText']";
    private String listViewKebabDropDownOptions = "//li[contains(@title,'%s')]//ul//li//span";

    public Navigations(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("AutoDeskProjectDirectory.json");
        aurigoLogo = locators.get("aurigoLogo");
        capitalPlanningDashboard = locators.get("capitalPlanningDashboard");
        newDashboard = locators.get("newDashboard");
        cashFlowTitle = locators.get("cashFlowTitle");
        budgetChangesTitle = locators.get("budgetChangesTitle");
        programCostsChartTitle = locators.get("programCostsChartTitle");
        projectDashboardTab = locators.get("projectDashboardTab");
        leftPanelList = locators.get("leftPanelList");
        plannedProjectsDashboard = locators.get("plannedProjectsDashboard");
        expandAll = locators.get("expandAll");
        collapseAll = locators.get("collapseAll");
        libraryDashboardTab = locators.get("libraryDashboardTab");
        administrationTab = locators.get("administrationTab");
        reportGalleryTab = locators.get("reportGalleryTab");
        reportGalleryReportList = locators.get("reportGalleryReportList");
        revisionProgramTab = locators.get("revisionProgramTab");
        arrowButton = locators.get("arrowButton");
        kebabOption = locators.get("kebabOption");
        getKebabButtonsList = locators.get("getKebabButtonsList");
        selectedRecordsCount = locators.get("selectedRecordsCount");
        logoutLink = locators.get("logoutLink");
        listViewButtonOptions = locators.get("listViewButtonOptions");
        listViewKebabButtonOptions = locators.get("listViewKebabButtonOptions");
        dropdownValues = locators.get("dropdownValues");
        administrationDashboard = locators.get("administrationDashboard");
        libraryDashboard = locators.get("libraryDashboard");
        executionProjectDashboard = locators.get("executionProjectDashboard");
        workFlowParentMenu = locators.get("workFlowParentMenu");
        parentNewMenu = locators.get("parentNewMenu");
        parentImportExportMenu = locators.get("parentImportExportMenu");
        reportsParentMenu = locators.get("reportsParentMenu");
        DoWorkFlowParentMenu = locators.get("DoWorkFlowParentMenu");
        activeInactiveParentMenu = locators.get("activeInactiveParentMenu");
        parentMoreMenu = locators.get("parentMoreMenu");
    }

    /**
     * Function to select all the rows present in a page of a List Page
     * @return True if element is enabled, else false.
     */
    public boolean selectMultipleRowsInListPage() {
        return elementHelper.isElementEnabled(selectedRecordsCount);
    }

    /**
     * Method to navigate to ACC Project Directory
     */
    public void navigateToProjectDirectory() {
        navigation.switchFrameToDefault();
        elementHelper.waitForElementPresent(aurigoLogo);
        elementHelper.doClick(aurigoLogo);
        switchBetweenWindows();
    }

    /**
     * Method to perform logout from the application
     */
    public void logout() {
        navigation.switchFrameToDefault();
        waitHelper.waitForPageToLoad(logoutLink);
        elementHelper.scrollToView(logoutLink);
        elementHelper.doClick(logoutLink);
    }

    /**
     * Method to perform Switch between the windows
     */
    public void switchBetweenWindows() {
        Set<String> windowHandle = driver.getWindowHandles();
        Iterator<String> itr = windowHandle.iterator();
        String parentWindow = itr.next();
        if (itr.hasNext()) {
            String childWindow = itr.next();
            if (!parentWindow.equalsIgnoreCase(childWindow))
                driver.close();
            driver.switchTo().window(childWindow);
        }
    }

    /**
     * Method to navigate to Capital Planning Dashboard
     */
    public void setCapitalPlanningDashboard() {
        elementHelper.waitForElementPresent(capitalPlanningDashboard);
        elementHelper.doClick(capitalPlanningDashboard);
        switchBetweenWindows();
        elementHelper.waitForElementPresent(aurigoLogo);
    }

    /**
     * Method to navigate to Financial Dashboard
     */
    public void setFinancialDashboard() {
        elementHelper.waitForElementPresent(plannedProjectsDashboard);
        elementHelper.doClick(plannedProjectsDashboard);
        switchBetweenWindows();
        elementHelper.waitForElementPresent(aurigoLogo);
    }

    /**
     * Method to navigate to Administration Dashboard
     */
    public void setAdministrationDashboard() {
        elementHelper.waitForElementPresent(administrationDashboard);
        elementHelper.doClick(administrationDashboard);
        switchBetweenWindows();
        elementHelper.waitForElementPresent(aurigoLogo);
    }

    /**
     * Method to navigate to Library Dashboard
     */
    public void setLibraryDashboard() {
        elementHelper.waitForElementPresent(libraryDashboard);
        elementHelper.doClick(libraryDashboard);
        switchBetweenWindows();
        elementHelper.waitForElementPresent(aurigoLogo);
    }

    /**
     * Method to navigate to Execution Project Dashboard
     */
    public void setExecutionProjectDashboard() {
        elementHelper.waitForElementPresent(executionProjectDashboard);
        elementHelper.doClick(executionProjectDashboard);
        switchBetweenWindows();
        elementHelper.waitForElementPresent(aurigoLogo);
    }

    /**
     * Method to validate First level Navigation
     *
     * @param tabName Tab name to get switched
     */
    public void firstLevelNavigation(Enum tabName) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(By.xpath(String.format(navigationTab, getEnumValue(tabName))));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to Navigate to the module
     *
     * @param moduleName module name to get selected
     */
    public void navigateUsingModule(String moduleName) {
        navigation.switchFrameToDefault();
        navigateToFormInLeftPaneTree(moduleName);
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to open Planned Project
     *
     * @param projectName Project name to get opened
     */
    public void openPlannedProject(String projectName) {
        logger().info("Navigating to Planned Project page");
        filterListPage(PlannedProjectListPageHeader.ProjectName.getValue(), projectName, ListPageFilterOptions.Contains);
        singleClickOnRowListPage(0);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        singleClickOnRowListPage(0);
        clickRibbonIcon(RibbonIcons.View);
        waitHelper.waitForElementPresent(projectDashboardTab);
    }

    /**
     * Navigate to module from landing page.
     * @param moduleName Name of the module.
     *
     * @return True if the element is displayed
     */
    public boolean navigateTo(String moduleName) {
        getPage(Navigation.class).switchFrameToDefault();
        var finalXpath = getPage(Navigation.class).getXpathForLeftPaneTreeItemAutodesk(moduleName);
        return elementHelper.isElementDisplayed(By.xpath(finalXpath));
    }

    /**
     * Navigate to List view
     *
     * @param moduleName Module name to get selected
     */
    public void navigateToListPage(Enum moduleName) {

        navigation.switchFrameToDefault();
        navigation.clickOnExpandAll();
        waitHelper.waitForPageToLoad();
        navigation.navigateToFormInLeftPaneTree(getEnumValue(moduleName));
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
    }

    /**
     * Navigates in the left pane tree view.
     *
     * @param treePath String having tree path separated by /
     * @return true if reaches last step.
     */
    public boolean navigateToFormInLeftPaneTree(String treePath) {
        logger().info("Navigating to the tree item: " + treePath);
        clickOnFormInLeftPaneTree(treePath);
        waitHelper.waitForPageToLoad();
        return true;
    }

    /**
     * Click on a form in the left pane tree view.
     *
     * @param treePath String having tree path separated by /
     */
    public void clickOnFormInLeftPaneTree(String treePath) {
        waitHelper.waitForPageToLoad();
        logger().info("Clicking on tree item: " + treePath);
        getPage(Navigation.class).switchFrameToDefault();
        var finalXpath = getPage(Navigation.class).getXpathForLeftPaneTreeItemAutodesk(treePath);
        var treeItemElement = elementHelper.getElement(By.xpath(finalXpath));
        elementHelper.scrollToView(treeItemElement);
        waitHelper.waitForElementClickable(treeItemElement);
        elementHelper.doClickUsingActions(treeItemElement);
    }

    /**
     * Check the Kebab list options availability
     *
     * @param kebabSelect Value to select the kebab button
     * @return True if the kebab button is available
     */
    public boolean kebabOptionsAvailability(boolean kebabSelect) {
        getPage(Navigation.class).switchFrameToContent();
        waitHelper.waitForPageToLoad();
        boolean obtainedResult = elementHelper.isElementEnabled(kebabOption);
        if (kebabSelect) {
            elementHelper.doClick(kebabOption);
        }
        return obtainedResult;
    }

    /**
     * Validate the Titles present under Planning Dashboard
     *
     * @return true on validation of the Titles
     */
    public boolean validatePlanningDashboardTitles() {

        waitHelper.waitForElementClickable(newDashboard);
        return getPage(Validations.class).verifyElementExists(cashFlowTitle) &&
                getPage(Validations.class).verifyElementExists(budgetChangesTitle) &&
                getPage(Validations.class).verifyElementExists(programCostsChartTitle);
    }

    /**
     * Validates the Financial modules in the left panel
     *
     * @return - True if all the required values are available
     */
    public boolean validateFinancialColumnsInLeftPanel() {
        navigation.switchFrameToDefault();
        List<String> allColumnHeadersDisplayed = financialProductFormValidation(leftPanelList);
        List<String> listPageColumnsExpected = FinancialForms.getList();
        return allColumnHeadersDisplayed.containsAll(listPageColumnsExpected);
    }

    /**
     * Validates the Capital modules in the left panel
     *
     * @return - True if all the required values are available
     */
    public boolean validateCapitalColumnsInLeftPanel() {
        navigation.switchFrameToDefault();
        List<String> allColumnHeadersDisplayed = financialProductFormValidation(leftPanelList);
        List<String> listPageColumnsExpected = CapitalForms.getList();
        return allColumnHeadersDisplayed.containsAll(listPageColumnsExpected);
    }

    /**
     * Validates the Report modules in the left panel
     *
     * @return - True if all the required values are available
     */
    public boolean validateReportGallery() {
        navigation.switchFrameToDefault();
        List<String> allColumnHeadersDisplayed = financialProductFormValidation(reportGalleryReportList);
        List<String> listPageColumnsExpected = ReportGalleryForms.getList();
        return allColumnHeadersDisplayed.containsAll(listPageColumnsExpected);
    }

    /**
     * Method to fetch the modules in the financial panel
     *
     * @param xpathFormat Xpath format to get searched
     * @return - List of Fields available under Financial Projects
     */
    public List<String> financialProductFormValidation(By xpathFormat) {
        List<String> stages = new ArrayList<>();
        var expandedFolders = elementHelper.getElements(xpathFormat);
        waitHelper.waitForPageToLoad();
        expandedFolders.stream().filter(h -> !h.getText().isEmpty()).forEach(h -> stages.add(h.getText()));
        return stages;
    }



    /**
     * Validate Global Option List
     *
     * @return true on validation of the above
     */
    public boolean validateKebabList() {
        var element = elementHelper.getElements(listViewButtonOptions);
        var elementKebab = elementHelper.getElements(listViewKebabButtonOptions);
        return element.equals(elementKebab);
    }

    /**
     * Validate Global Option List
     *
     * @return true on validation of the above
     */
    public boolean validateWorkflowKebabList() {
        var element = elementHelper.getElements(listViewButtonOptions);
        var elementKebab = elementHelper.getElements(listViewKebabButtonOptions);
        return element.equals(elementKebab);
    }

    /**
     * Validate Global Option List
     *
     * @return true on validation of the above
     */
    public boolean validateWorkflowDropDownList() {
        var element = elementHelper.getElements(listViewButtonOptions);
        var elementKebab = elementHelper.getElements(listViewKebabButtonOptions);
        return element.equals(elementKebab);
    }

    /**
     * Method to Validate the Folder dropdown Options and Validates the Kebab dropdown with the contextual values
     *
     * @return True if the fields are available
     */
    public SoftAssert folderDropDownValuesValidation() {
        List<WebElement> eleList = null;
        var softAssert = new SoftAssert();
        List<WebElement> contextualList = elementHelper.getElements(dropdownValues);
        for (var value : contextualList) {

            String fieldValue = value.getText().trim();
            if (!fieldValue.isEmpty()) {
                List<WebElement> eleKebabList = elementHelper.getElements(By.xpath(String.format(listViewKebabDropDownOptions, fieldValue)));

                switch (fieldValue) {
                    case "New":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(parentNewMenu))));
                        break;
                    case "Workflow":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(workFlowParentMenu))));
                        break;
                    case "Reports":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(reportsParentMenu))));
                        break;
                    case "More":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(parentMoreMenu))));
                        break;
                    case "Mark Active/Inactive":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(activeInactiveParentMenu))));
                        break;
                    case "Select Actions":
                        eleList = elementHelper.getElements(By.xpath(String.format(listViewDropDownOptions, elementHelper.getLocatorAsString(DoWorkFlowParentMenu))));
                        break;
                    default:
                        break;
                }
                softAssert.assertTrue(eleList.contains(eleKebabList));
                eleList.clear();
            }
        }
        return softAssert;
    }

    /**
     * Validate Empty list view on Record selection
     *
     * @return true on Fields available
     */
    public boolean emptyListView() {
        return elementHelper.isElementDisplayed(listViewButtonOptions);
    }
}
