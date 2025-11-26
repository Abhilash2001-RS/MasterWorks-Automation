package com.aurigo.masterworks.testframework.webUI.generic;

import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.common.AlertHandler;
import com.aurigo.masterworks.testframework.webUI.common.Validations;
import com.aurigo.masterworks.testframework.webUI.constants.enums.AttachmentColumn;
import com.aurigo.masterworks.testframework.webUI.constants.enums.GridType;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Attachments extends ListPage {


    private By tableId;
    private By pickerId;
    private By attachementDataGridId;
    private By headerId;
    private By attachmentResultGridDataId;
    private By attachmentResultGridHeaderId;
    private By documentInDocumentViewer;
    private By attachmentsGridData;
    private By attachmentsDeleteBtn;
    private By expandAttachments;

    private By globalFundListDocuments;

    public Attachments(WebDriver driver) {
        super(driver);

        var locators = LocatorUtil.getLocators("Attachments.json");
        tableId = locators.get("tableId");
        pickerId = locators.get("pickerId");
        attachementDataGridId = locators.get("attachementDataGridId");
        headerId = locators.get("headerId");
        attachmentResultGridDataId = locators.get("attachmentResultGridDataId");
        attachmentResultGridHeaderId = locators.get("attachmentResultGridHeaderId");
        documentInDocumentViewer = locators.get("documentInDocumentViewer");
        attachmentsDeleteBtn = locators.get("attachmentsDeleteBtn");
        expandAttachments = locators.get("expandAttachments");
        attachmentsGridData = locators.get("attachmentsGridData");
        globalFundListDocuments = locators.get("globalFundListDocuments");

    }

    /**
     * Method to validate attachment
     *
     * @param fileName          name of file added
     * @param isDocumentPresent To check for document present or deleted
     * @return softassert
     */
    public SoftAssert validateAttachmentByName(String fileName, boolean isDocumentPresent) {
        var softAssert = new SoftAssert();
        navigation.switchFrameToContent();
        waitHelper.waitForPageToLoad(lastPageBtn);
        elementHelper.doClick(lastPageBtn);
        waitHelper.waitForPageToLoad();
        List<String> values = getPage(GenericFormProposed.class).getGridData(GridType.DocGrid, AttachmentColumn.DocumentName.getValue(), attachementDataGridId, attachementDataGridId);
        if (isDocumentPresent) {
            softAssert.assertTrue(values.contains(fileName), "Assert verification at " + fileName + " verification");
        } else {
            softAssert.assertFalse(values.contains(fileName), "Assert verification at " + fileName + " verification");
        }
        clickRibbonIcon(RibbonIcons.Back);
        waitHelper.waitForPageToLoad();
        return softAssert;
    }

    /**
     * Method to verify version history
     *
     * @param versionTable Table id of the doc grid
     * @param fileName     name of file added
     * @return true if element exists
     */
    public boolean verifyVersionHistoryDisplayed(String fileName, By versionTable) {
        selectDocRow(fileName);
        clickRibbonIcon(RibbonIcons.VersionHistory);
        elementHelper.waitForElementPresent(pickerId);
        return (!getPage(GenericForm.class).getRowsFromDocGridForVersion(versionTable).isEmpty()) && (getPage(GenericForm.class).getRowsFromDocGrid(tableId) != null);
    }

    /**
     * Select doc specified
     *
     * @param name Name of the doc to select
     */
    public void selectDocInAttachment(String name) {
        waitHelper.waitForPageToLoad();
        elementHelper.doClick(lastPageBtn);
        waitHelper.waitForPageToLoad();
        var row = getPage(GenericForm.class).getRowFromRadGrid(attachmentsGridData, attachmentResultGridHeaderId, AttachmentColumn.DocumentName.getValue(), name);
        elementHelper.doClick(row);
    }

    /**
     * Validate that document is opened in view mode
     *
     * @return True if Doc is opened in view mode
     */
    public boolean isDocOpened() {
        waitHelper.waitForPageToLoad();
        waitHelper.waitForPageToLoad(RibbonIcons.Back);
        return elementHelper.isElementDisplayed(documentInDocumentViewer);
    }

    /**
     * Validate attachment is downloaded
     *
     * @param fileName Name of the file to check
     * @param node     Host node
     * @return True if attachment is downloaded
     */
    public boolean validateAttachmentIsDownloaded(String fileName, Host node) {
        logger().info("Validating the download");
        int maxWait = 60;
        int pollingInterval = 1;
        waitHelper.waitForPageToLoad();
        FileHelper.deleteFile(fileName, node);
        clickRibbonIcon(RibbonIcons.GetDocument);
        return FileHelper.waitForFileToBeAvailable(fileName, maxWait, pollingInterval, node);
    }

    /**
     * Used to select the row using file name
     *
     * @param fileName name of the file
     */
    public void selectDocRow(String fileName) {
        String locator = String.format("//td[text()='%s']/parent::tr[starts-with(@id,'ctl00_C1_docgrid')]", fileName);
        elementHelper.doClick(By.xpath(locator));
    }

    /**
     * This method used to delete the specific attachment by clicking on the file name
     *
     * @param fileName name of the file which we need to click
     * @return true if the attachment is deleted
     */
    public boolean deleteAttachByFileName(String fileName) {
        final String deleteDocumentList = "//*[@class='attachmentCtrlRowClass'][contains(text(),'%s')]";
        selectAttachment(fileName);
        waitHelper.waitForPageToLoad();
        clickDeleteAttachmentButton();
        waitHelper.waitForPageToLoad();
        boolean obtainedOutput = getPage(Validations.class).verifyElementExists(By.xpath(String.format(deleteDocumentList, fileName)));
        getPage(GenericForm.class).clickSave(false);
        return obtainedOutput;
    }

    /**
     * This method is used to click the delete button in attachment
     */
    public void clickDeleteAttachmentButton() {
        waitHelper.waitForElementClickable(attachmentsDeleteBtn);
        elementHelper.doClick(attachmentsDeleteBtn);
        getPage(AlertHandler.class).acceptAlert(true);
        waitHelper.waitForPageToLoad();
    }

    /**
     * This method used to select the document in attachment grid
     *
     * @param fileName name of the file which we need to click
     */
    public void selectAttachment(String fileName) {
        waitHelper.waitForPageToLoad();
        if (!elementHelper.isElementDisplayed(attachmentsDeleteBtn)) {
            elementHelper.doClick(expandAttachments);
        }
        WebElement webElement = getPage(GenericFormProposed.class).selectRow(GridType.RadGrid, fileName);
        elementHelper.scrollToView(webElement);
        elementHelper.doClick(webElement.findElement(By.xpath("./td[not(contains(@style,'display:none;'))]")));
        waitHelper.waitForPageToLoad();
    }

    /**
     * Verifying Display of grid in attachment page
     *
     * @return true if grid is present else return false
     */
    public boolean isAttachmentGridIsDisplayed() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(attachementDataGridId);
    }

    /**
     * Fetching Column data in attachment page
     *
     * @param columnName columnName
     * @return list of column data
     */
    public List<String> getColumnData(String columnName) {
        waitHelper.waitForPageToLoad();
        return getPage(GenericFormProposed.class).getGridData(GridType.RadGrid, columnName, attachementDataGridId, attachementDataGridId);
    }

    /**
     * Verifying Global Fund List Document Page name
     *
     * @return true if global fund list document page is displayed else return false
     */
    public boolean verifyGlobalFundListDocumentPage() {
        waitHelper.waitForPageToLoad();
        return elementHelper.isElementDisplayed(globalFundListDocuments);
    }
}
