package com.aurigo.masterworks.testframework.webUI.pages.documentMangement;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocumentFolderCreationPage extends RibbonMenu {
    private By folderNameLocator;
    private By inheritParentPermissionCheckbox;
    private By saveFolderButton;

    public DocumentFolderCreationPage(WebDriver driver) {
        super(driver);
        var locators = LocatorUtil.getLocators("DocumentFolderCreationPage.json");
        folderNameLocator = locators.get("folderNameLocator");
        inheritParentPermissionCheckbox = locators.get("inheritParentPermissionCheckbox");
        saveFolderButton = locators.get("saveFolderButton");
    }

    /**
     * Creates a new folder in the parent folder specified
     *
     * @param parentFolderName      - Parent folder under which the new folder is to be created
     * @param newFolderName         - The new folder created
     * @param inheritParentProperty - true if property is to be inherited, false otherwise
     * @return - returns true when the folder gets created,false otherwise
     */
    public boolean createFolderInDocuments(String parentFolderName, String newFolderName, boolean inheritParentProperty) {
        logger().info("Creating a new folder");
        navigation.navigateToFormInLeftPaneTree(parentFolderName);
        waitHelper.waitForPageToLoad();
        navigation.switchFrameToContent();
        waitHelper.waitForElementClickable(getRibbonIcon(RibbonIcons.New));
        if (validateRibbonIcon(RibbonIcons.NewFolderInDocumentsListPage)) {
            clickRibbonIcon(RibbonIcons.NewFolderInDocumentsListPage);
            waitHelper.waitForPageToLoad();
            waitHelper.waitForElementPresent(folderNameLocator);
            elementHelper.doSendKeysUsingAction(folderNameLocator, newFolderName);
            elementHelper.waitForElementPresent(inheritParentPermissionCheckbox);
            if (!inheritParentProperty) {
                elementHelper.doClick(inheritParentPermissionCheckbox);
            }
            waitHelper.waitForElementClickable(saveFolderButton);
            elementHelper.doClick(saveFolderButton);
            waitHelper.waitForPageToLoad();
            return navigation.navigateToFormInLeftPaneTree(newFolderName);
        } else {
            logger().fail("New Folder cannot be created as create new folder icon is not displayed in the ribbon");
            return false;
        }
    }


    /**
     * Method to edit Folder Name
     *
     * @param folderName - existing folder name
     * @param newFolderName - edited name
     * @return - returns true when the folder is renamed
     */
    public boolean editFolderName(String folderName,String newFolderName) {
        logger().info(String.format("Editing folder '%s'",folderName));
        navigation.navigateToFormInLeftPaneTree(folderName);
        clickRibbonIcon(RibbonIcons.EditFolder);
        waitHelper.waitForElementPresent(folderNameLocator);
        elementHelper.clearText(folderNameLocator);
        elementHelper.doSendKeysUsingAction(folderNameLocator, newFolderName);
        waitHelper.waitForElementClickable(saveFolderButton);
        elementHelper.doClick(saveFolderButton);
        waitHelper.waitForPageToLoad(getRibbonIcon(RibbonIcons.New));
        return getPage(Validations.class).isFormAvailableInLeftPaneTree(newFolderName);
    }
}
