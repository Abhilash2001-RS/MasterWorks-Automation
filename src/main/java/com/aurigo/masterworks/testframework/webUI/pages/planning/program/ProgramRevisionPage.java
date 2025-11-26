package com.aurigo.masterworks.testframework.webUI.pages.planning.program;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.Navigation;
import com.aurigo.masterworks.testframework.webUI.common.WorkFlowHandler;
import com.aurigo.masterworks.testframework.webUI.constants.enums.*;
import com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields.WorkflowOptions;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import com.aurigo.masterworks.testframework.webUI.generic.GenericFormProposed;
import com.aurigo.masterworks.testframework.webUI.generic.ListPage;
import com.aurigo.masterworks.testframework.webUI.generic.Picker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ProgramRevisionPage extends ListPage {

    private By selectProgramPickerButton;
    private By programPickerContainer;
    private By addProjectPickerButton;
    private By deleteProjectPickerButton;
    private By addProjectPickerContainer;
    private By addProjectPickerId;
    private By programPickerId;
    private By addPlannedProjectGridData;
    private By addPlannedProjectGridHeaderId;
    private By deletePlannedProjectsBtnInAddProjectGrid;
    private By addPlannedProjectsGridId;
    private By addButtonInRemovePlannedProjectGrid;
    private By removePlannedProjectPickerContainer;
    private By removePlannedProjectPickerId;

    private String programCategoryDataFieldValue = "ProgramCategory";

    public ProgramRevisionPage(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("ProgramRevisionPage.json");
        selectProgramPickerButton = locators.get("selectProgramPickerButton");
        programPickerContainer = locators.get("programPickerContainer");
        addProjectPickerButton = locators.get("addProjectPickerButton");
        deleteProjectPickerButton = locators.get("deleteProjectPickerButton");
        addProjectPickerContainer = locators.get("addProjectPickerContainer");
        addProjectPickerId = locators.get("addProjectPickerId");
        programPickerId = locators.get("programPickerId");
        addPlannedProjectGridData = locators.get("addPlannedProjectGridData");
        addPlannedProjectGridHeaderId = locators.get("addPlannedProjectGridHeaderId");
        deletePlannedProjectsBtnInAddProjectGrid = locators.get("deletePlannedProjectsBtnInAddProjectGrid");
        addPlannedProjectsGridId = locators.get("addPlannedProjectsGridId");
        addButtonInRemovePlannedProjectGrid = locators.get("addButtonInRemovePlannedProjectGrid");
        removePlannedProjectPickerContainer = locators.get("removePlannedProjectPickerContainer");
        removePlannedProjectPickerId = locators.get("removePlannedProjectPickerId");

    }

    /**
     * Navigate to Program Revision page
     */
    public void navigateToProgramRevisionPage(){
        getPage(Navigation.class).navigateToFormInLeftPaneTree(Form.ReviseProgram.getValue());
    }

    /**
     * Add the program which needs to be revised
     *
     * @param programName program name
     */
    public void addAProgram(String programName){
        waitHelper.waitForPageToLoad(selectProgramPickerButton);
        elementHelper.doClick(selectProgramPickerButton);
        waitHelper.waitForElementPresent(programPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        getPage(Picker.class).singleSelectByText(ProgramRevisionPageColumns.Title.getValue(), programName, programPickerContainer,PickerFilterOptions.Contains);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Add a random project to the program revision
     *
     * @return project name of the project added
     */
    public String addARandomProject(){
        waitHelper.waitForPageToLoad(addProjectPickerButton);
        elementHelper.doClick(addProjectPickerButton);
        waitHelper.waitForPageToLoad(addProjectPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        var projectAdded = getPage(Picker.class).getColumnData(addProjectPickerId,ProgramRevisionPageColumns.Title.getValue(),1);
        getPage(Picker.class).filterPickerColumn(addProjectPickerContainer,
                ProgramProjectPickerColumns.Name.getValue(), "Auto", PickerFilterOptions.Contains);
        getPage(Picker.class).singleSelectByRowNumber(1,addProjectPickerContainer);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
        return projectAdded.get(0);
    }

    /**
     * Create new Program Revision
     *
     * @param programName program name
     * @return project name of the project added
     */
    public String createNewRevision(String programName){
        addAProgram(programName);
        var projectAdded = addARandomProject();
        getPage(GenericForm.class).clickSave();
        return projectAdded;
    }

    /**
     * Open Program Revision in Edit state
     *
     * @param revisionId program name
     */
    public void openProgramRevisionInEditState(int revisionId){
        singleClickOnRowListPage(ProgramRevisionPageColumns.RevisionID.getValue(),String.valueOf(revisionId));
        getPage(GenericForm.class).clickEdit();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Cancel));
    }

    /**
     * Validate Program picker
     *
     * @param programName program name to be validated
     * @return true on finding the program in the program picker
     */
    public boolean validateProgramPicker(String programName){
        waitHelper.waitForPageToLoad(selectProgramPickerButton);
        elementHelper.doClick(selectProgramPickerButton);
        waitHelper.waitForPageToLoad(programPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();
        getPage(Picker.class).filterPickerColumn(programPickerContainer,ProgramRevisionPageColumns.Title.getValue(),programName,PickerFilterOptions.Contains);
        var value = getPage(Picker.class).getColumnData(programPickerId,ProgramRevisionPageColumns.Title.getValue(),1);
        getPage(Picker.class).singleSelectByRowNumber(1,programPickerContainer);
        return programName.equals(value.get(0));
    }

    /**
     * Validate the Planned Project picker
     *
     * @param projectName project name to be validated
     * @return true on finding the project in the picker
     */
    public boolean validatePlannedProjectPicker(String projectName){
        waitHelper.waitForPageToLoad(addProjectPickerButton);
        elementHelper.doClick(addProjectPickerButton);
        waitHelper.waitForLoadingSpinnerDisappear();
        waitHelper.waitForPageToLoad(addProjectPickerContainer);

        getPage(Picker.class).filterPickerColumn(addProjectPickerContainer, "Name",projectName,PickerFilterOptions.Contains);
        var value = getPage(Picker.class).getColumnData(addProjectPickerId, ProgramRevisionPageColumns.Title.getValue(),1);
        if(value.isEmpty()){
            return false;
        }
        else {
            return projectName.equals(value.get(0));
        }
    }

    /**
     * Validate planned projects picker data with corresponding program category
     *
     * @param category program category selected
     * @return true on validation of the above
     */
    public boolean validatePlannedProjectsPickerWithCorrespondingProgramCategory(String category){
        waitHelper.waitForPageToLoad(addProjectPickerButton);
        elementHelper.doClick(addProjectPickerButton);
        waitHelper.waitForPageToLoad(addProjectPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        var projectCategoryInPicker = getPage(Picker.class).getColumnData(addProjectPickerId,programCategoryDataFieldValue,1);
        return projectCategoryInPicker.get(0).equals(category);
    }

    /**
     * Click cancel on add projects picker
     */
    public void clickCancelOnAddProjectsPicker(){
        getPage(Picker.class).cancelPicker(addProjectPickerContainer);
    }


    /**
     * Click cancel on Remove Planned Project picker
     */
    public void clickCancelOnRemovePlannedProjectPicker(){
        getPage(Picker.class).cancelPicker(removePlannedProjectPickerContainer);
    }

    /**
     * Method to change the WF status of a program revision
     *
     * @param revisionId   Revision ID
     * @param workflowAction workflow action to be performed
     * @param expectedStatus expected status after performing workflow action
     * @return true if the wf action is performed successfully
     */
    public boolean changeProgramWorkFlowStatus(int revisionId, WorkFlowActions workflowAction, WorkFlowStatus expectedStatus) {
        logger().info("Changing the WF status of program Revision'" + revisionId + "' to " + workflowAction);
        filterListPage(ProgramRevisionPageColumns.RevisionID.getValue(),String.valueOf(revisionId),ListPageFilterOptions.EqualTo);
        return getPage(WorkFlowHandler.class).workFlowActionProgression(false, false, String.valueOf(revisionId),
                ProgramRevisionPageColumns.RevisionID.getValue(), workflowAction, expectedStatus);
    }

    /**
     * Validate Editing Submitted Program Revision
     *
     * @return true on validation of the above
     */
    public boolean validateEditingSubmittedProgramRevision(){
        waitHelper.waitForPageToLoad(selectProgramPickerButton);
        return validateRibbonIcon(RibbonIcons.Save);
    }

    /**
     * Delete a record from Planned Projects grid
     *
     * @param projectName project name
     */
    public void deleteARecordFromPlannedProjectsGrid(String projectName){
        elementHelper.scrollToView(selectProgramPickerButton);
        waitHelper.waitForPageToLoad();
        int rowNum = getPage(GenericForm.class).getRowIndexRadGrid(addPlannedProjectGridData, addPlannedProjectGridHeaderId, ProgramListPageHeader.Name.getValue(), projectName)+1;
        elementHelper.doClick(getPage(GenericForm.class).getRowCellsRadGrid(addPlannedProjectGridData, rowNum).get(1));
        elementHelper.doClick(deletePlannedProjectsBtnInAddProjectGrid);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * Validate if the project grid has all the projects that are be added
     *
     * @param projectNameList Names of the projects added
     * @return True if all the projects are added
     */
    public boolean validateProjectsInAddProjectGrid(List<String> projectNameList) {
        waitHelper.waitForPageToLoad();
        List<String> values = getPage(GenericFormProposed.class).getGridData(GridType.RadGrid,ProgramListPageHeader.Name.getValue(),addPlannedProjectGridData,addPlannedProjectGridHeaderId);
        if(values.isEmpty()){
            return false;
        }
        else{
            return values.containsAll(projectNameList);
        }
    }

    /**
     * Validate Remove planned projects picker data with corresponding program category
     *
     * @param category program category selected
     * @return true on validation of the above
     */
    public boolean validateRemovePlannedProjectsPickerWithCorrespondingProgramCategory(String category){
        waitHelper.waitForPageToLoad(addButtonInRemovePlannedProjectGrid);
        elementHelper.doClick(addButtonInRemovePlannedProjectGrid);
        waitHelper.waitForPageToLoad(removePlannedProjectPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        var projectCategoryInPicker = getPage(Picker.class).getColumnData(removePlannedProjectPickerId,programCategoryDataFieldValue,1);
        return projectCategoryInPicker.get(0).equals(category);
    }

    /**
     * Add a Planned Project to add projects grid
     *
     * @param projectName project name
     */
    public void addAPlannedProjectToAddProjectsGrid(String projectName){
        waitHelper.waitForPageToLoad(addProjectPickerButton);
        elementHelper.doClick(addProjectPickerButton);
        waitHelper.waitForElementPresent(addProjectPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        getPage(Picker.class).filterPickerColumn(addProjectPickerContainer, PlannedProjectGridColumnHeaders.ProjectName.getValue(),projectName,PickerFilterOptions.Contains);
        getPage(Picker.class).singleSelectByRowNumber(1, addProjectPickerContainer);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Add a Planned Project to Remove projects grid
     *
     * @param projectName project name
     */
    public void addAPlannedProjectToRemoveProjectsGrid(String projectName){
        waitHelper.waitForPageToLoad(addButtonInRemovePlannedProjectGrid);
        elementHelper.doClick(addButtonInRemovePlannedProjectGrid);
        waitHelper.waitForElementPresent(removePlannedProjectPickerContainer);
        waitHelper.waitForLoadingSpinnerDisappear();

        getPage(Picker.class).filterPickerColumn(removePlannedProjectPickerContainer, PlannedProjectGridColumnHeaders.ProjectName.getValue(),projectName,PickerFilterOptions.Contains);
        getPage(Picker.class).singleSelectByRowNumber(1, removePlannedProjectPickerContainer);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.Save));
    }

    /**
     * Get Revision ID of the Program Revision
     *
     * @param programName name of the Program revised
     * @return Revision Id
     */
    public int getRevisionIdOfFirstProgramRevisionRecord(String programName){
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        clearAllFilters();
        filterListPage(ProgramRevisionPageColumns.ProgramName.getValue(),programName,ListPageFilterOptions.Contains);
        return Integer.parseInt(getColumnData(ProgramRevisionPageColumns.RevisionID.getValue()).get(0));
    }

    /**
     * Create New Revision either by adding or removing a project
     *
     * @param programName program name
     * @param projectName project name
     * @param addProject true if the project is to be added
     */
    public void createNewRevision(String programName,String projectName,boolean addProject){
        getPage(GenericForm.class).clickNew();
        addAProgram(programName);
        if(addProject){
            addAPlannedProjectToAddProjectsGrid(projectName);
        }else {
            addAPlannedProjectToRemoveProjectsGrid(projectName);
        }
        getPage(GenericForm.class).clickSave();
    }
    //Autodesk

    /**
     * Validate single record selection toolbar values
     *
     * @return true on validation of available fields
     */
    public boolean validateReviseSingleRecordList() {

        List<By> locators = new ArrayList<>();
        locators.add(getRibbonIcon(RibbonIcons.Edit));
        locators.add(getRibbonIcon(RibbonIcons.View));
        locators.add(getRibbonIcon(RibbonIcons.Delete));
        locators.add(getRibbonIcon(RibbonIcons.Workflow));
        return getPage(GenericForm.class).validateElementsExists(locators);
    }
    /**
     * Validate Toolbar under Single record selection workflow
     *
     * @param history     To validate History workflow value
     * @param workflow    To validate Workflow value
     * @return true on Fields available
     */
    public boolean validateWorkflowValue(boolean history, boolean workflow) {
        clickRibbonIcon(RibbonIcons.Workflow);
        List<By> workflowValues = new ArrayList<>();
        if (history)
            workflowValues.add(By.xpath(String.format(textValidationInRibbonMenu, WorkflowOptions.History.getValue())));
        if (workflow)
            workflowValues.add(By.xpath(String.format(textValidationInRibbonMenu, WorkflowOptions.WorkflowUsers.getValue())));
        return getPage(GenericForm.class).validateElementsExists(workflowValues);
    }
    /**
     * Validate Contextual fields displayed
     *
     * @return true if the field values are displayed
     */
    public boolean contextualFieldValidation() {
        return ((validateWorkflowValue(true,  true)) &&
                (validateMoreList(false, true, false, false,false,false)));
    }
}
