package com.aurigo.masterworks.testframework.webUI.pages.planning;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ManageUsersInProjectPage extends RibbonMenu {

    private By projectUsersTable;
    private By projectUsersRadGrid;
    private By allUserTable;
    private By allUserTableId;
    private By addUsersButton;
    private By addProjectButton;
    private By projectPickerXpath;
    private By comboUsers;
    private By expandButtonMcon;
    private By noUserInTheSystemMessage;
    private By addUsersMultiSelectCheckbox;
    private By manageUsersGridTableID;
    private By nextPageButtonInGrid;
    private By previousPageButtonInGrid;
    private By paginationLoading;
    private By selectAllRecordsButton;


    private String usernameRow = "./ancestor::tr[contains(@id,'C1_grdProjectUsers')]//td[text()='%s']";
    private String projectNameCheckBox = "//td[text()='%s']//parent::tr/following-sibling::tr[@role='presentation' and not(contains(@style,'display:none'))]//td[text()='%s']/../td/input[(@type='checkbox')and (not(contains(@id,'Primary')))]";

    public ManageUsersInProjectPage(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("ManageUsersInProjectPage.json");
        projectUsersTable = locators.get("projectUsersTable");
        projectUsersRadGrid = locators.get("projectUsersRadGrid");
        allUserTable = locators.get("allUserTable");
        allUserTableId = locators.get("allUserTableId");
        addUsersButton = locators.get("addUsersButton");
        addProjectButton = locators.get("addProjectButton");
        projectPickerXpath = locators.get("projectPickerXpath");
        comboUsers = locators.get("comboUsers");
        expandButtonMcon = locators.get("expandButtonMcon");
        noUserInTheSystemMessage = locators.get("noUserInTheSystemMessage");
        addUsersMultiSelectCheckbox = locators.get("addUsersMultiSelectCheckbox");
        manageUsersGridTableID = locators.get("manageUsersGridTableID");
        nextPageButtonInGrid = locators.get("nextPageButtonInGrid");
        previousPageButtonInGrid = locators.get("previousPageButtonInGrid");
        paginationLoading = locators.get("paginationLoading");
        selectAllRecordsButton = locators.get("selectAllRecordsButton");
    }

    /**
     * Function to add user in project
     *
     * @param firstName first name of the user to be added
     */
    public void addUser(String firstName) {
        clickRibbonIcon(RibbonIcons.AddUser);
        waitHelper.waitForPageToLoad();
        getPage(GenericFormProposed.class).filterGrid(GridType.RadGrid, allUserTable, ManageUsersInProject.FirstName.getValue(), firstName, ListPageFilterOptions.Contains);
        waitHelper.waitForPageToLoad();
        getPage(GenericForm.class).singleClickOnRowCheckBox(allUserTableId, 1);
        elementHelper.doClick(addUsersButton);
        waitHelper.waitForPageToLoad();
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
    }

    /**
     * Checks whether user is present manage User Page
     *
     * @param firstName-First name of the user to be searched
     * @return true if user is present in the table
     */

    public boolean isUserPresent(String firstName) {
        try {
            waitHelper.waitForPageTabHeaderToBeClickable();
            getPage(GenericFormProposed.class).filterGrid(GridType.RadGrid, projectUsersRadGrid, ManageUsersInProject.FirstName.getValue(), firstName, ListPageFilterOptions.Contains);
            return getCellDataFromRadGrid(projectUsersTable, ManageUsersInProject.FirstName.getValue(), 1).equals(firstName);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Function to check if the user is present in Manage Users
     *
     * @param firstName first name of the user to be verified
     * @return true if user is present in the table
     */
    public boolean verifyIfUserIsPresentInManageUsersTable(String firstName) {
        return getPage(GenericForm.class).isRecordExistsInRadGrid(projectUsersTable, projectUsersTable, ManageUsersInProject.FirstName.getValue(), firstName);
    }

    /**
     * Function to get Project Roles of a user
     *
     * @param rowNumber row number of the user whose Project Roles are required
     * @return list of project roles
     */
    private String getProjectRolesForUser(int rowNumber) {
        return getPage(GenericForm.class).getCellDataFromRadGrid(projectUsersTable, "Project Role", rowNumber);
    }

    /**
     * Function to verify Project Roles in invited users table
     *
     * @param rowNumber number of the row of user for which project roles has to be verified
     * @return true if the verification is successful
     */
    public boolean verifyProjectRoles(int rowNumber) {
        String rolesInTable = getProjectRolesForUser(rowNumber);
        WebElement rowByNumber = elementHelper.getElement(By.xpath(String.format(".//*[@id='ctl00_C1_grdProjectUsers_ctl00']/tbody/tr[%s]", rowNumber)));
        elementHelper.doDoubleClick(rowByNumber);
        WebElement roleSpan = rowByNumber.findElement(comboUsers);
        String rolesExpected = elementHelper.doGetInnerHtml(roleSpan);
        return rolesInTable.equals(rolesExpected);
    }

    /**
     * Method to Check Whether the Project Creation User is present in Manage Users Table
     *
     * @param username username
     * @return true if the Project Creation User is present else false
     */
    public boolean checkCreatedUserInListPage(String username) {
        String firstName = getCellDataFromRadGrid(projectUsersTable, ManageUsersInProject.FirstName.getValue(), 1);
        return firstName.equalsIgnoreCase(username);
    }


    /**
     * Method to get CellData From Radix Grid Table
     *
     * @param tableid
     * @param columnName
     * @param rowNum
     * @return
     */
    //This method will be moved to Generic Form Page after Vishal Merges that Page
    public String getCellDataFromRadGrid(By tableid, String columnName, int rowNum) {
        int i, columnNum = 0;
        var tablename = elementHelper.getElement(tableid);
        List<WebElement> tableheaders = tablename.findElements(By.tagName("th"));
        for (i = 0; i < tableheaders.size(); i++) {
            if (tableheaders.get(i).getText().equals(columnName)) {
                columnNum = i + 1;
                break;
            }
        }
        String cellData = elementHelper.getElement(By.xpath("//table[@id='" + elementHelper.getLocatorAsString(tableid) +
                "']/tbody/tr[" + rowNum + "]/td[" + columnNum + "]")).getText();
        return cellData;
    }

    /**
     * This method adds Project to a User
     *
     * @param username     - Username of the user to be added
     * @param projectName- Name of Project to be added
     */

    public void addProjectToAUser(String username, String projectName) {
        elementHelper.doClick(addProjectButton);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).singleSelectByText("Project Name", projectName, projectPickerXpath, PickerFilterOptions.Contains);
        clickRibbonIcon(RibbonIcons.Save);
        getPage(ToastUtil.class).waitForToastDisappears();
        waitHelper.waitForPageTabHeaderToBeClickable();
    }

    /**
     * Add user to the project and Contract in MCON
     *
     * @param firstName    Name of the person who created the project and Contract
     * @param contractName Name of the Contract on which the User is to be added
     */
    public void addUserToContract(String firstName, String contractName) {
        List<WebElement> rows = elementHelper.getElements(expandButtonMcon);
        if (rows.size() > 1) {
            for (WebElement row : rows) {
                if (!row.findElements(By.xpath(String.format(usernameRow, firstName))).isEmpty()) {
                    elementHelper.doClick(row);
                    break;
                }
            }
        } else {
            elementHelper.doClick(rows.get(0));
        }
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(By.xpath(String.format(projectNameCheckBox, firstName, contractName)));
        clickRibbonIcon(RibbonIcons.Save);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate user in add users grid
     *
     * @param firstName first name of the user
     * @return true on validation of the above
     */
    public boolean validateUserInAddUserGrid(String firstName) {
        clickRibbonIcon(RibbonIcons.AddUser);
        waitHelper.waitForPageToLoad();
        getPage(GenericFormProposed.class).filterGrid(GridType.RadGrid, allUserTable, ManageUsersInProject.FirstName.getValue(), firstName, ListPageFilterOptions.Contains);
        waitHelper.waitForPageToLoad();
        return !getPage(Validations.class).verifyElementExists(noUserInTheSystemMessage);
    }

    /**
     * Add all the users displayed in add user grid
     */
    public void addAllUsersDisplayedInAddUsers() {
        navigation.refreshPage();
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.AddUser);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(addUsersMultiSelectCheckbox);
        elementHelper.doClick(addUsersButton);
        waitHelper.waitForPageToLoad();
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
    }

    /**
     * Select first row in current users grid
     */
    public void selectFirstRowInGrid() {
        getPage(GenericForm.class).singleClickOnRowCheckBox(manageUsersGridTableID, 1);
    }

    /**
     * Navigate to next page in Current users grid
     */
    public void navigateToNextPageInGrid() {
        waitHelper.waitForPageToLoad(nextPageButtonInGrid);
        elementHelper.doClick(nextPageButtonInGrid);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Navigate to previous page in Current users grid
     */
    public void navigateToPreviousPageInGrid() {
        waitHelper.waitForPageToLoad(previousPageButtonInGrid);
        elementHelper.doClick(previousPageButtonInGrid);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate if the checkbox of the first row of current user grid is selected
     *
     * @return true on validation the above
     */
    public boolean validateIfTheGridCheckboxOfFirstRowIsSelected() {
        var xpath = getPage(GenericForm.class).getXpathOfMultiSelectCheckBoxFromTableId(manageUsersGridTableID, 1);
        return elementHelper.getElement(xpath).isSelected();
    }

    /**
     * Remove user from current user grid
     */
    public void removeUser() {
        clickRibbonIcon(RibbonIcons.RemoveUser);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
    }

    /**
     * Select all records in add user grid
     */
    public void selectAllRecords() {
        waitHelper.waitForPageToLoad();
        clickRibbonIcon(RibbonIcons.AddUser);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(selectAllRecordsButton);
        waitHelper.waitForPageToLoad();
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
    }

    /**
     * Validate users in add users grid
     *
     * @return true on validation
     */
    public boolean validatePickerInAddUserGrid() {
        waitHelper.waitForPageToLoad();
        return getPage(Validations.class).verifyElementExists(noUserInTheSystemMessage);
    }
}
