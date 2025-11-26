package com.aurigo.masterworks.testframework.webUI.pages.planning;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.*;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import com.aurigo.masterworks.testframework.webUI.testData.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static org.openqa.selenium.By.xpath;

public class ManageUsersInProjectListPage extends RibbonMenu {
    private By selectUserComboBoxID;
    private By existingProjectsGrid;
    private By addProjectsGrid;
    private By addProjectsButton;
    private By deleteProjectsButton;
    private By selectUsersComboBoxInput;
    private By addProjectsPicker;
    private By noMatchesMessageInSelectUser;
    private By userDropDownLoading;
    private By projectNameTextField;
    private By filterButtonId;
    private By equalToOption;
    private By existingProjectsMultiSelectCheckBox;
    private By nextButtonInExistingProjectsGrid;
    private By previousButtonInExistingProjectsGrid;
    private By isPrimaryCheckbox;
    private By existingProjectsGridID;
    private By savePendingForEdit;

    private String projectAddedToastMessageFormat = "Project/Role associations saved successfully for user %s.";
    private String projectInExistingProjectGridXpathTemplate = "//*[@id='ctl00_C1_grdProjectsWithRoles']//*[contains(text(),'%s')]";

    public ManageUsersInProjectListPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ManageUsersInProjectListPage.json");
        selectUserComboBoxID = locators.get("selectUserComboBoxID");
        existingProjectsGrid = locators.get("existingProjectsGrid");
        addProjectsGrid = locators.get("addProjectsGrid");
        addProjectsButton = locators.get("addProjectsButton");
        deleteProjectsButton = locators.get("deleteProjectsButton");
        selectUsersComboBoxInput = locators.get("selectUsersComboBoxInput");
        addProjectsPicker = locators.get("addProjectsPicker");
        noMatchesMessageInSelectUser = locators.get("noMatchesMessageInSelectUser");
        userDropDownLoading = locators.get("userDropDownLoading");
        projectNameTextField = locators.get("projectNameTextField");
        filterButtonId = locators.get("filterButtonId");
        equalToOption = locators.get("equalToOption");
        existingProjectsMultiSelectCheckBox = locators.get("existingProjectsMultiSelectCheckBox");
        nextButtonInExistingProjectsGrid = locators.get("nextButtonInExistingProjectsGrid");
        previousButtonInExistingProjectsGrid = locators.get("previousButtonInExistingProjectsGrid");
        isPrimaryCheckbox = locators.get("isPrimaryCheckbox");
        existingProjectsGridID = locators.get("existingProjectsGridID");
        savePendingForEdit = locators.get("savePendingForEdit");
    }

    /**
     * Navigate to Manage Users page on Porject list page
     */
    public void navigateToManageUsers(){
        clickRibbonIcon(RibbonIcons.ManageUsersSecurityRoles);
        waitHelper.waitForPageToLoad(selectUserComboBoxID);
    }

    /**
     * Switch user
     */
    public void switchUser(){
        clickRibbonIcon(RibbonIcons.SwitchUser);
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Select user in search box
     *
     * @param userName username
     */
    public void selectUserInSearchBox(String userName){
        waitHelper.waitForPageToLoad(selectUsersComboBoxInput);
        elementHelper.clearText(selectUsersComboBoxInput);
        elementHelper.doSendKeysUsingAction(selectUsersComboBoxInput, userName);
        waitHelper.waitForPageToLoad();
        waitHelper.waitUntilElementDisappears(userDropDownLoading);
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeysUsingAction(selectUsersComboBoxInput, Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate the presence of Project grids in page
     *
     * @param userName user name
     * @return validations of the above
     */
    public SoftAssert validateProjectsGridInPage(String userName){
        var softAssert = new SoftAssert();

        softAssert.assertFalse(getPage(Validations.class).verifyElementExists(addProjectsGrid), "Add Projects grid is absent before selecting user");
        softAssert.assertFalse(getPage(Validations.class).verifyElementExists(existingProjectsGrid), "Existing projects grid is absent before selecting user");

        selectUserInSearchBox(userName);

        softAssert.assertTrue(getPage(Validations.class).verifyElementExists(addProjectsGrid),"Add projects grid is present after selecting user");
        softAssert.assertTrue(getPage(Validations.class).verifyElementExists(existingProjectsGrid),"Existing projects grid is present after selecting uesr");

        return softAssert;
    }

    /**
     * Validate adding project to the user
     *
     * @param projectName project name
     * @param userName first name of the user to be added
     * @return true on validation of the above
     */
    public boolean validateAddingProject(String projectName, String userName) {
        waitHelper.waitForPageToLoad(addProjectsButton);
        elementHelper.doClick(addProjectsButton);
        waitHelper.waitForPageToLoad();
        getPage(Picker.class).singleSelectByText("Project Name",projectName,addProjectsPicker);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageTabHeaderToBeClickable();
        var expectedToastMsg = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
        var toastMsg = String.format(projectAddedToastMessageFormat,userName);
        return expectedToastMsg.equals(toastMsg);
    }

    /**
     * Checks whether Project is Existing projects grid
     *
     * @param project project name to be searched
     * @return true if project is present in the table
     */
    public boolean validateProjectInExistingProjectsGrid(String project) {
        var recordXpath = xpath(String.format(projectInExistingProjectGridXpathTemplate,project));
        return getPage(Validations.class).verifyElementExists(recordXpath);
    }

    /**
     * Validate if project is present in Existing Projects Grid
     * @param project - project to be checked
     * @return - true if project exists
     */
    public boolean isProjectPresentInExistingProjects(String project)
    {
        logger().info("Validate if project is present in Existing Projects Grid");
        waitHelper.waitForPageToLoad();
        elementHelper.doSendKeys(projectNameTextField,project);
        elementHelper.doClick(filterButtonId);
        waitHelper.waitUntilElementAppears(equalToOption);
        elementHelper.doClick(equalToOption);
        waitHelper.waitForPageToLoad();
        var recordXpath = xpath(String.format(projectInExistingProjectGridXpathTemplate,project));
        return getPage(Validations.class).verifyElementExists(recordXpath);

    }

    /**
     * Remove projects from existing projects grid
     *
     * @param removeAll true if all the projects are to be removed
     */
    public void removeProjectFromExistingProjectsGrid(boolean removeAll){
        if(removeAll){
            clickRibbonIcon(RibbonIcons.RemoveAllInsideDropDown);
        }else {
            getPage(GenericForm.class).singleClickOnRowCheckBox(existingProjectsGrid,1);
            getPage(Navigation.class).switchFrameToContent();
            clickRibbonIcon(RibbonIcons.RemoveInsideDropDown);
        }
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Back));
    }

    /**
     * Add multiple projects to the user
     *
     * @param numberOfProjects number of projects
     */
    public void addMultipleProjectsToTheUser(int numberOfProjects){
        waitHelper.waitForPageToLoad(addProjectsButton);
        elementHelper.doClick(addProjectsButton);
        waitHelper.waitForPageToLoad();
        getPage(Picker.class).multiSelectByCountOld(numberOfProjects,addProjectsPicker);
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Back));
    }

    /**
     * Validate adding multiple projects to the selected user
     *
     * @param numberOfProjectsAdded number of projects added
     * @return true on validation of the above
     */
    public boolean validateAddingMultipleProjects(int numberOfProjectsAdded){
        var checkBoxes = elementHelper.getElements(By.xpath("//table[contains(@id,'" + elementHelper.getLocatorAsString(existingProjectsGrid) +
                "')]/tbody/tr//input[contains(@id,'MultiSelectCheckBox')]"));
        return numberOfProjectsAdded==checkBoxes.size();
    }

    /**
     * Validate if the selection of project persists even after navigating to next page
     *
     * @return true on validation
     */
    public boolean validateSelectingProjectsAndNavigatingToNextPage(){
        waitHelper.waitForPageToLoad(existingProjectsGrid);
        elementHelper.doClick(existingProjectsMultiSelectCheckBox);
        elementHelper.scrollToView(nextButtonInExistingProjectsGrid);
        elementHelper.doClick(nextButtonInExistingProjectsGrid);
        waitHelper.waitForPageToLoad(existingProjectsGrid);
        elementHelper.scrollToView(previousButtonInExistingProjectsGrid);
        elementHelper.doClick(previousButtonInExistingProjectsGrid);
        waitHelper.waitForPageToLoad(existingProjectsGrid);
        return elementHelper.isCheckBoxSelected(existingProjectsMultiSelectCheckBox);
    }

    /**
     * Validate the multi select checkbox in Add Projects picker
     *
     * @return true on validation
     */
    public boolean validateMultiSelectCheckBoxInAddProjectsPicker(){
        waitHelper.waitForPageToLoad(addProjectsButton);
        elementHelper.doClick(addProjectsButton);
        waitHelper.waitForPageToLoad(addProjectsGrid);
        return getPage(Picker.class).validateMultiSelectCheckBox(false);
    }

    /**
     * Validate the isPrimary checkbox
     *
     * @param userName user name
     * @param project project
     * @return true on validation of the functioning of the checkbox
     */
    public SoftAssert validateIsPrimaryCheckBoxForTheUser(String userName, Project project){
        var softAssert = new SoftAssert();
        selectUserInSearchBox(userName);
        isProjectPresentInExistingProjects(project.name);
        var checkBox = elementHelper.getElement(isPrimaryCheckbox);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(checkBox);
        softAssert.assertTrue(getPage(Validations.class).verifyElementExists(savePendingForEdit),
                "Save pending for edit message should be present");

        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Back));

        selectUserInSearchBox(userName);
        isProjectPresentInExistingProjects(project.name);
        waitHelper.waitForPageToLoad();
        elementHelper.doClickCheckboxLabelByJS(isPrimaryCheckbox);
        softAssert.assertFalse(elementHelper.isCheckBoxSelected(isPrimaryCheckbox),
                "isPrimary Checkbox should be de selected");

        return softAssert;
    }
}