package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.TestDataUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.ToastUtil;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.Role;
import com.aurigo.masterworks.testframework.webUI.testData.DashBoard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DashBoardPage extends RibbonMenu {

    //Locators
    private By selectDashboard;
    private By selectDashboardDropDown;
    private By modeDashBoardDropDown;
    private By mode;
    private By newDashboard;
    private By editDashBoard;
    private By deleteDashBoard;
    private By setDefaultDashBoard;
    private By modeDropDownOptions;
    private By nameLabel;
    private By descriptionLabel;
    private By visibleToLabel;
    private By rolesLabel;
    private By layoutLabel;
    private By saveButton;
    private By saveAndDesignButton;
    private By cancelButton;
    private By visibleToMeButton;
    private By myDashBoardOptions;
    private By roleBasedDashBoardOptions;
    private By newDashBoardNameTextField;
    private By newDashBoardDescriptionTextField;
    private By roleTextBox;
    private By reportViewerCheckBox;
    private By addButtonWidgets;
    private By applyButtonWidgets;
    private By okButtonWidgets;
    private By editButtonWidgets;
    private By selectedReportViewer;
    private By deleteSelectedReportViewer;
    private By editorZoneText;
    private By webZoneDropDown;

    private WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        var locators = LocatorUtil.getLocators("DashBoardPage.json");
        selectDashboard = locators.get("selectDashboard");
        selectDashboardDropDown = locators.get("selectDashboardDropDown");
        modeDashBoardDropDown = locators.get("modeDashBoardDropDown");
        mode = locators.get("mode");
        newDashboard = locators.get("newDashboard");
        editDashBoard = locators.get("editDashBoard");
        deleteDashBoard = locators.get("deleteDashBoard");
        setDefaultDashBoard = locators.get("setDefaultDashBoard");
        modeDropDownOptions = locators.get("modeDropDownOptions");
        nameLabel = locators.get("nameLabel");
        descriptionLabel = locators.get("descriptionLabel");
        visibleToLabel = locators.get("visibleToLabel");
        rolesLabel = locators.get("rolesLabel");
        layoutLabel = locators.get("layoutLabel");
        saveButton = locators.get("saveButton");
        saveAndDesignButton = locators.get("saveAndDesignButton");
        cancelButton = locators.get("cancelButton");
        visibleToMeButton = locators.get("visibleToMeButton");
        myDashBoardOptions = locators.get("myDashBoardOptions");
        roleBasedDashBoardOptions = locators.get("roleBasedDashBoardOptions");
        newDashBoardNameTextField = locators.get("newDashBoardNameTextField");
        newDashBoardDescriptionTextField = locators.get("newDashBoardDescriptionTextField");
        reportViewerCheckBox = locators.get("reportViewerCheckBox");
        addButtonWidgets = locators.get("addButtonWidgets");
        applyButtonWidgets = locators.get("applyButtonWidgets");
        okButtonWidgets = locators.get("okButtonWidgets");
        editButtonWidgets = locators.get("editButtonWidgets");
        selectedReportViewer=locators.get("selectedReportViewer");
        deleteSelectedReportViewer=locators.get("deleteSelectedReportViewer");
        editorZoneText=locators.get("editorZoneText");
        webZoneDropDown=locators.get("webZoneDropDown");
        roleTextBox=locators.get("roleTextBox");
    }


    /**
     * method to verify if dashboard is present
     *
     * @return true if present
     */
    public boolean isDashBoardPresent(By dashboard) {
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        return getPage(Validations.class).verifyElementExists(dashboard);
    }

    /**
     * Method to add locator in a list
     *
     * @return list of locators
     */
    private List<By> iconsInDashBoard() {
        List<By> options = new ArrayList<>();
        options.add(deleteDashBoard);
        options.add(newDashboard);
        options.add(editDashBoard);

        return options;
    }

    /**
     * Get the drop down options
     *
     * @param dropDownLocator locator of the drop down
     * @return List of options in the drop down
     */
    public List<String> getDropDownOptions(By dropDownLocator) {
        List<String> options = new ArrayList<>();
        List<WebElement> optionsAvailable = elementHelper.getElements(dropDownLocator);
        int i = 0;
        for (var option : optionsAvailable) {
            String optionData = elementHelper.doGetText(option);
            options.add(optionData);
        }
        return options;
    }

    /**
     * Method to validate the dashboard Icons
     *
     * @return true if validated successfully
     */
    public boolean validateTheDashBoardIcons() {
        boolean iconsValidation = true;
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementPresent(modeDropDownOptions);
        elementHelper.doClick(modeDropDownOptions);
        List<String> modeOptions = getDropDownOptions(modeDropDownOptions);
        List<String> availableModeOptions = new ArrayList<>();
        availableModeOptions.add("View");
        availableModeOptions.add("Edit Widgets");
        availableModeOptions.add("Add Widgets");
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad();
        boolean dropDownValidation = getPage(Validations.class).verifyElementExists(selectDashboard) && getPage(Validations.class).verifyElementExists(mode);
        elementHelper.scrollToView(setDefaultDashBoard);
        for(int i=0;i<iconsInDashBoard().size();i++)
        {
            iconsValidation = iconsValidation && getPage(Validations.class).verifyElementExists(iconsInDashBoard().get(i));
            logger().info(iconsInDashBoard().get(i) + " " + getPage(Validations.class).verifyElementExists(iconsInDashBoard().get(i)));
        }
        logger().info("Icons Validation done successfully - " + iconsValidation);
        boolean modesValidation = modeOptions.equals(availableModeOptions);
        logger().info("Modes Validation done successfully - " + modesValidation);
        return iconsValidation && modesValidation && dropDownValidation;
    }

    /**
     * click on new dashboard
     */
    public void clickOnNew() {
        waitHelper.waitForPageToLoad(newDashboard);
        elementHelper.doClick(newDashboard);
    }

    /**
     * click on edit dashboard
     */
    public void clickOnEdit() {
        elementHelper.doClick(editDashBoard);
    }

    /**
     * click on new dashboard
     */
    public void clickOnDelete() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(deleteDashBoard);
        waitHelper.waitForAlertPresent();
        getPage(AlertHandler.class).acceptAlert(true);
        navigation.refreshPage();
    }

    /**
     * Method to click on cancel
     */
    public void clickOnCancel() {
        elementHelper.doClick(cancelButton);

    }

    /**
     * Click on Set Default
     */
    public void clickOnSetDefault() {
        elementHelper.doClick(setDefaultDashBoard);
    }

    /**
     * Method to add locator of the new dashboard in a list
     *
     * @return list of locators
     */
    private List<By> fieldsInNewDashBoard() {
        List<By> newDashboardLabelList = new ArrayList<>();
        newDashboardLabelList.add(nameLabel);
        newDashboardLabelList.add(descriptionLabel);
        newDashboardLabelList.add(visibleToLabel);
        newDashboardLabelList.add(rolesLabel);
        newDashboardLabelList.add(layoutLabel);
        newDashboardLabelList.add(saveButton);
        newDashboardLabelList.add(saveAndDesignButton);
        newDashboardLabelList.add(cancelButton);
        return newDashboardLabelList;
    }

    /**
     * Method to add locator of the new dashboard in a list
     *
     * @return list of locators
     */
    private List<By> fieldsInEditDashBoard() {
        List<By> newDashboardLabelList = new ArrayList<>();
        newDashboardLabelList.add(nameLabel);
        newDashboardLabelList.add(descriptionLabel);
        newDashboardLabelList.add(visibleToLabel);
        newDashboardLabelList.add(layoutLabel);
        newDashboardLabelList.add(saveButton);
        newDashboardLabelList.add(saveAndDesignButton);
        newDashboardLabelList.add(cancelButton);
        return newDashboardLabelList;
    }

    /**
     * Method to validate the new dashboard fields and buttons
     *
     * @return true if they exist
     */
    public boolean validateNewDashBoardFields() {

        waitHelper.waitForElementPresent(saveButton);
        return getPage(GenericForm.class).validateElementsExists(fieldsInNewDashBoard());
    }

    /**
     * Method to validate the new dashboard fields and buttons
     *
     * @return true if they exist
     */
    public boolean validateEditDashBoardFields() {

        waitHelper.waitForElementPresent(saveButton);
        return getPage(GenericForm.class).validateElementsExists(fieldsInEditDashBoard());
    }

    /**
     * Method to fill the dashboard details
     *
     * @return Dashboard details
     */
    public DashBoard fillInDashBoardDetails() {
        DashBoard dashBoard = new DashBoard();
        dashBoard.name = "DashBoard " + TestDataUtil.getRandomName();
        dashBoard.description = "DashBoard Description" + TestDataUtil.getRandomName();
        waitHelper.waitForElementToBePresentAndClickable(newDashBoardNameTextField);
        elementHelper.doSendKeys(newDashBoardNameTextField, dashBoard.name);
        waitHelper.waitForElementToBePresentAndClickable(newDashBoardDescriptionTextField);
        elementHelper.doSendKeys(newDashBoardDescriptionTextField, dashBoard.description);
        return dashBoard;
    }

    /**
     * Method to create New dashboard with Visibility Me
     *
     * @return dashboard Details
     */
    public DashBoard createNewDashBoard() {
        var dashBoard = fillInDashBoardDetails();
        waitHelper.waitForElementToBePresentAndClickable(visibleToMeButton);
        elementHelper.doClick(visibleToMeButton);
        elementHelper.doClick(saveButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(newDashboard);
        return dashBoard;
    }

    /**
     * Method to create New dashboard with Role based Visibility
     *
     * @return dashboard Details
     */
    public DashBoard createNewDashBoard(Role role) {
        var dashBoard = fillInDashBoardDetails();
        addRoleInDashBoard(role);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(saveButton);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(newDashboard);
        return dashBoard;
    }

    /**
     * DashBoard with save and design
     *
     * @return dashBoard details
     */
    public DashBoard createNewDashBoardWithSaveAndDesign() {
        var dashBoard = fillInDashBoardDetails();
        elementHelper.doClick(visibleToMeButton);
        elementHelper.doClick(saveAndDesignButton);
        waitHelper.waitForElementPresent(newDashboard);
        return dashBoard;
    }


    /**
     * Method to create New dashboard with Visibility Me
     *
     * @return dashboard Details
     */
    public DashBoard editDashBoard() {
        var dashBoard = fillInDashBoardDetails();
        elementHelper.doClick(saveButton);
        waitHelper.waitForElementPresent(newDashboard);
        return dashBoard;
    }

    /**
     * method to get options under dashBoard
     *
     * @param dashBoardType myDashboard or RoleBasedDashBoard
     * @return Option Data
     */
    public List<String> geOptionsUnderDashBoards(String dashBoardType) {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(selectDashboardDropDown);
        List<WebElement> dashBoardOptions = new ArrayList<>();
        if (dashBoardType == "myDashBoard") {
            dashBoardOptions = elementHelper.getElements(myDashBoardOptions);
        } else {
            dashBoardOptions = elementHelper.getElements(roleBasedDashBoardOptions);
        }

        List<String> myDashBoard = new ArrayList<>();
        for (var options : dashBoardOptions) {
            String optionData = elementHelper.doGetText(options);
            myDashBoard.add(optionData);
        }
        return myDashBoard;
    }

    /**
     * Method to add Dashboard name and save only
     *
     * @param dashBoard dashboard details
     */
    public void enterDashBoardNameOnly(DashBoard dashBoard) {
        waitHelper.waitForElementPresent(newDashBoardDescriptionTextField);
        elementHelper.doSendKeys(newDashBoardNameTextField, dashBoard.name);
        elementHelper.doClick(visibleToMeButton);
        elementHelper.doClick(saveButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Method to add Dashboard name for Role based Dashboard and save only
     *
     * @param dashBoard dashboard details
     * @param role      role
     */
    public void enterDashBoardNameOnlyRoleBased(DashBoard dashBoard, Role role) {
        waitHelper.waitForElementPresent(newDashBoardDescriptionTextField);
        elementHelper.doSendKeys(newDashBoardNameTextField, dashBoard.name);
        addRoleInDashBoard(role);
        elementHelper.doClick(saveButton);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Select Dashboard
     *
     * @param dashBoardName name of the dashboard
     */
    public void selectDashBoard(String dashBoardName) {
        waitHelper.waitForPageToLoad();
        elementHelper.selectComboBoxItemByText(selectDashboardDropDown, dashBoardName);
        waitHelper.waitForElementClickable(newDashboard);
    }

    /**
     * Select Dashboard
     *
     * @param mode mode of the dashboard
     */
    public void selectMode(String mode) {
        waitHelper.waitForPageToLoad(modeDashBoardDropDown);
        elementHelper.selectComboBoxItemByText(modeDashBoardDropDown, mode);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementClickable(newDashboard);
    }

    /**
     * get Selected DashBoard
     *
     * @return String of the name of the dashboard
     */
    public String getSelectedDashBoard() {
        waitHelper.waitForPageToLoad();
        String selectedDashboard = elementHelper.getFirstSelectedOption(selectDashboardDropDown);
        return selectedDashboard;
    }

    /**
     * get Selected DashBoard
     *
     * @return String of the mode  of the dashboard
     */
    public String getSelectedMode() {
        waitHelper.waitForPageToLoad();
        String selectedMode = elementHelper.getFirstSelectedOption(modeDashBoardDropDown);
        return selectedMode;
    }


    /**
     * Add Role in the new DashBoard page
     *
     * @param role Role to be added
     * @return Role added
     */
    public String addRoleInDashBoard(Role role) {
        elementHelper.doSendKeys(roleTextBox, role.getValue());
        By listOfRoles = By.xpath("//ul[@class='racList']/li[contains(@class,'racItem')]");
        waitHelper.waitForPageToLoadWithCustomTimeout(120);
        elementHelper.doDoubleClick(listOfRoles);
        return role.getValue();
    }

    /**
     * Method to add widgets in the dashBoard(Report Viewer)
     */
    public void addWidget() {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(reportViewerCheckBox);
        elementHelper.doClick(addButtonWidgets);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(newDashboard);
    }

    /**
     * Edit Widget (Report Viewer)
     */
    public void editWidget() {

        elementHelper.doClick(editButtonWidgets);
    }

    /**
     * Method to get Toast Message
     *
     * @return Toast message
     */
    public String getToastMessage() {
        String toastMessage = getPage(ToastUtil.class).waitAndGetMessageForSingleToast();
        getPage(ToastUtil.class).waitAndCloseForSingleToast();
        return toastMessage;
    }

    /**
     * Method to add widgets in the dashBoard with Report Viewer in specified web zone
     * @param webZone - WebZone to which report viewer must be added
     */
    public void addWidgetWithReportViewerInSpecifiedWebZone(String webZone) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(reportViewerCheckBox);
        elementHelper.selectComboBoxItemByText(webZoneDropDown,webZone);
        elementHelper.doClick(addButtonWidgets);
        waitHelper.waitForPageToLoad();
        waitHelper.waitForElementPresent(newDashboard);
    }

    /**
     * This Method checks if Report Viewer is present in the specified WebZone
     * @param webZone - WebZone to which report viewer must is added
     * @return true if Report Viewer is present
     */
    public boolean isReportViewerPresentInTheSpecifiedWebZone(String webZone){
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(By.xpath(String.format
                ("//td[text()='Web Part Zone %s']/ancestor::tr[contains(@id,'C1_DBControl_ZTR%s')]" +
                        "/descendant::span[contains(text(),'Report Viewer')]",webZone,webZone)));
    }

    /**
     * This Method is used to Edit the Report Viewer
     * @param reportViewerOptionToBeSelected - Report option to be selected from Editor zone
     */
    public void editReportViewer(String reportViewerOptionToBeSelected){
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(editButtonWidgets);
        waitHelper.waitForPageToLoad(editorZoneText);
        elementHelper.doClick(By.xpath(String.format
                ("//a[contains(@id,'ReportSelector_TreeViewt%s') and contains(@class,'DB')]",reportViewerOptionToBeSelected)));
        waitHelper.waitForPageToLoad();
        elementHelper.scrollToView(applyButtonWidgets);
        elementHelper.doClick(applyButtonWidgets);
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(okButtonWidgets);
        waitHelper.waitForPageToLoad();
    }

    /**
     * This Method checks if report viewer selected from Editor zone is present
     * @return true if report viewer is present
     */
    public boolean isSelectedReportViewerPresent() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(selectedReportViewer);
    }

    /**
     * This Method is used to delete the Report Viewer
     */
    public void deleteReportViewer(){
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(deleteSelectedReportViewer);
    }

}



