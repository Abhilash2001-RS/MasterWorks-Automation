package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.webUI.constants.enums.ExternalIDP;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RegexStrings;
import com.aurigo.masterworks.testframework.webUI.constants.enums.Role;
import com.aurigo.masterworks.testframework.webUI.constants.enums.TimeZoneList;
import com.aurigo.masterworks.testframework.webUI.testData.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.util.Strings;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestDataUtil {

    private static Random random = new Random();

    /**
     * Get UUID.
     *
     * @return UUID.
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get Random number.
     *
     * @param min Min value.
     * @param max Max Value.
     * @return Random number between Min and Max.
     */
    public static Integer getRandomNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Get Random Double.
     *
     * @param min Min value.
     * @param max Max Value.
     * @return Random double between Min and Max.
     */
    public static Double getRandomDouble(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }

    /**
     * Get Decimal value representation
     *
     * @param numberOfZeros The number of zeros required after the decimal and before the value
     * @param value         The value to be made into a decimal form
     * @return String with the required value.
     */
    public static String formatIntToDecimal(int numberOfZeros, int value) {
        StringBuilder decimal = new StringBuilder();
        decimal.append("0.");
        String padded = String.format("%0" + (numberOfZeros + 1) + "d", value);
        decimal.append(padded);
        return decimal.toString();
    }

    /**
     * Get Random name.
     *
     * @return Random name.
     */
    public static String getRandomName() {
        return RandomStringUtils.randomAlphabetic(10).toUpperCase();
    }

    /**
     * Get Random name.
     *
     * @param stringLength -    Length of the string to be generated.
     * @return Random name.
     */
    public static String getRandomName(Integer stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength).toUpperCase();
    }

    /**
     * Method to reduce a string to only numeric characters and the character '.'
     *
     * @param wholeString the string to be reduced
     * @return a subset of string containing only numeric characters and the character '.'
     */
    public static String onlyNumericCharactersAndDots(String wholeString) {
        return (wholeString.replaceAll(RegexStrings.notAlphabetOrDotOneOrMore.getValue(), ""));
    }

    /**
     * Method to reduce a string to only alphabetic characters
     *
     * @param wholeString the string to be reduced
     * @return a subset of string containing only alphabetic character
     */
    public static String onlyAlphabeticalCharactersAndSpecialCharacters(String wholeString) {
        return (wholeString.replaceAll(RegexStrings.digitsOneOrMore.getValue(), ""));
    }

    /**
     * Try parsing string value to Integer.
     *
     * @param value -   String value to be parsed.
     * @return True if parse is successful, else false.
     */
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to generate program data for new program creation
     *
     * @return program data
     */
    public static Program generateNewProgramData() {
        var program = new Program();
        LocalDate today = LocalDate.now();
        program.title = "AutoPGM" + generateUniqueAlphanumericCode();
        program.description = program.title + " Description";
        program.year = Integer.toString(today.getYear());
        program.category = "Central";
        program.budget = "99999.99";
        return program;
    }

    /**
     * Method to generate RFI data
     *
     * @return RFI data
     */
    public static RequestForInformation getNewRFIData() {
        var rfiData = new RequestForInformation();
        rfiData.subject = String.format("AutoSubject%s", generateUniqueAlphanumericCode());
        rfiData.questionsOrClarificationsRequired = String.format("This is a dummy question for %s", rfiData.subject);
        return rfiData;
    }

    /**
     * Method to generate pay item data
     *
     * @param container container to be selected
     * @param quantity  quantity of the item
     * @param unitPrice unit price of the item
     * @return pay item data
     */
    public static PayItem generateNewPayItem(String container, double quantity, double unitPrice) {
        var item = new PayItem();
        item.container = container;
        item.payItemNumber = "PI" + generateUniqueAlphanumericCode();
        item.description = item.payItemNumber + " description";
        item.quantity = quantity;
        item.unitPrice = unitPrice;
        item.unit = "AC";
        item.amount = quantity * unitPrice;
        return item;
    }

    /**
     * Generating Dynamic Grid Filter and Sorting Data
     *
     * @return - data generated
     */
    public static DynamicGridFilterAndSorting generateDynamicGridFilterAndSortingData() {
        var dynamicGridData = new DynamicGridFilterAndSorting();
        dynamicGridData.column0 = TestDataUtil.getRandomName(10);
        dynamicGridData.column1 = TestDataUtil.getRandomName(10);
        dynamicGridData.column2 = DateTimeUtil.getCurrentDate("MM-dd-yyyy");
        dynamicGridData.column4 = DateTimeUtil.getCurrentDate("MM-dd-yyyy HH:mm:ss");
        return dynamicGridData;
    }

    /**
     * Method to generate pay item data without a container
     *
     * @param quantity  quantity of the item
     * @param unitPrice unit price of the item
     * @return pay item data
     */
    public static PayItem generateNewPayItem(double quantity, double unitPrice) {
        var item = new PayItem();
        item.payItemNumber = "PI" + generateUniqueAlphanumericCode();
        item.description = item.payItemNumber + " description";
        item.quantity = quantity;
        item.unitPrice = unitPrice;
        item.unit = "AC";
        item.amount = quantity * unitPrice;
        return item;
    }

    /**
     * Method to generate master program data
     *
     * @return master program data
     */
    public static MasterProgram generateNewMasterProgramData() {
        var masterProgram = new MasterProgram();
        LocalDate today = LocalDate.now();
        masterProgram.title = "AutoMPGM" + generateUniqueAlphanumericCode();
        masterProgram.description = masterProgram.title + " Description";
        masterProgram.programYear = Integer.toString(today.getYear());
        return masterProgram;
    }

    /**
     * @return Project; Creates a new project data for project creation
     */
    public static Project generateNewProjectData(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();

        Project newProject;
        newProject = new Project();
        newProject.code = generateUniqueAlphanumericCode();
        newProject.name = "Auto" + newProject.code;
        newProject.owner = "Automation Team";
        newProject.description = "Created through automation script";
        newProject.startDate = DateTimeUtil.getDateString(dateFormat, today);
        newProject.endDate = DateTimeUtil.getDateString(dateFormat, nextYear);
        newProject.programCategory = "Central";
        newProject.businessUnit = "HeadOffice";

        return newProject;
    }

    /**
     * Method to generate Data for Template Project for Scratch build setup
     *
     * @param dateFormat - Date format from DB
     * @return Project; Creates a new project data for Template project creation
     */
    public static Project generateNewProjectTemplateData(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();

        Project newProject;
        newProject = new Project();
        newProject.code = "TemplateProject";
        newProject.name = "TemplateProject";
        newProject.owner = "Automation Team";
        newProject.description = "Created through automation script";
        newProject.startDate = DateTimeUtil.getDateString(dateFormat, today);
        newProject.endDate = DateTimeUtil.getDateString(dateFormat, nextYear);
        newProject.programCategory = "Central";
        newProject.businessUnit = "HeadOffice";

        return newProject;
    }


    /**
     * Creates a new project data for project creation for API Testdata
     *
     * @param randomName boolean key to create random project name
     * @return Project Created project is returned
     */
    public static Project generateNewProjectDataAPI(String dateFormat, boolean randomName) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();
        Project newProject;
        newProject = new Project();
        newProject.code = generateUniqueAlphanumericCode();
        if (randomName) {
            newProject.name = "Auto" + newProject.code;
            newProject.owner = "apiuser";
        } else {
            newProject.name = "API PROJECT (DONOT DELETE)";
            newProject.owner = "API";
        }
        newProject.description = "Created through automation script";
        newProject.startDate = DateTimeUtil.getDateString(dateFormat, today);
        newProject.endDate = DateTimeUtil.getDateString(dateFormat, nextYear);
        newProject.programCategory = "Central";
        newProject.businessUnit = "HeadOffice";
        return newProject;
    }

    /**
     * @param dateFormat          DAte format
     * @param numberOfYears       number of years for end date
     * @param timeZoneAppSettings Time zone Settings
     * @return Project; Creates a new project data for project creation
     */
    public static Project generateNewProjectData(String dateFormat, Integer numberOfYears, String timeZoneAppSettings) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeZone = timeZoneAppSettings.replaceAll(" ", "");
        String todayDate = DateTimeUtil.getDateBasedOnTimeZone(TimeZoneList.valueOf(timeZone), dateFormat);
        String nextYear = DateTimeUtil.getDateBasedOnTimeZone(TimeZoneList.valueOf(timeZone), "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(nextYear, formatter);
        dateTime = dateTime.plusYears(numberOfYears);
        String tenYearsAfterString = dateTime.format(formatter);
        Date nextYearDate = new Date();
        try {
            nextYearDate = format.parse(tenYearsAfterString);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        Project newProject;
        newProject = new Project();
        newProject.code = generateUniqueAlphanumericCode();
        newProject.name = "Auto" + newProject.code;
        newProject.owner = "Automation Team";
        newProject.description = "Created through automation script";
        newProject.startDate = todayDate;
        newProject.endDate = DateTimeUtil.getDateString(dateFormat, nextYearDate);
        newProject.programCategory = "Central";
        newProject.businessUnit = "HeadOffice";

        return newProject;
    }

    /**
     * @return User; Creates data for new user creation
     */
    public static User generateNewUserData(String userName) {
        var userData = new User();
        var uniqueCode = generateUniqueAlphanumericCode();
        if (Strings.isNotNullAndNotEmpty(userName)) {
            userData.username = userName;
            userData.firstName = userName;
        } else {
            userData.username = "autoUser" + uniqueCode;
            userData.firstName = "autoUser" + uniqueCode;
        }
        userData.password = "Aurigo@1234567";
        userData.businessUnit = "HeadOffice";
        userData.email = userData.username + "@email.com";
        userData.lastName = ""; //Intentionally passing empty string
        userData.roles = new String[]{"User", "Administrator"};
        userData.userType = "Internal";
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();
        userData.accountExpiryDate = DateTimeUtil.getDateString("yyyy-MM-dd HH:mm:ss.SSS", nextYear);
        userData.registrationDate = DateTimeUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss.SSS");
        return userData;
    }

    /**
     * @return Need; Creates data for new Need creation
     */
    public static Need generateNewNeedData() {
        var today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.MONTH, 1);
        int year = cal.get(Calendar.YEAR);
        var need = new Need();
        need.title = "Need" + getTimeStamp();
        need.address = "Sample Address";
        need.comments = "Comments";
        need.estimatedCost = "10.00";
        need.businessUnit = "HeadOffice";
        need.programYear = Integer.toString(year);
        need.expectedCompletionMonth = DateTimeUtil.getDateString("MMMM", cal.getTime());
        need.expectedCompletionYear = Integer.toString(year + 1);
        need.problemStatementAndJustification = "Test";
        return need;

    }

    /**
     * Generating data for Resource Titles Items for import
     *
     * @return Resource titles items data
     */
    public static ResourceTitles generateResourceTitlesImportData() {
        ResourceTitles resourceTitles = new ResourceTitles();
        resourceTitles.title = "Testing";
        resourceTitles.recordID = TestDataUtil.getRandomNumber(0, 20).toString();
        resourceTitles.resourceType = "Contractor";
        resourceTitles.unit = "DAY";
        resourceTitles.fixedPriceIn$ = "10";
        resourceTitles.directPriceIn$ = "20";
        resourceTitles.inDirectPriceIn$ = "30";
        resourceTitles.workingHoursPerDay = "5";
        return resourceTitles;
    }

    /**
     * Generating data for Purchase order for import
     *
     * @param project project data
     * @return Purchase order data
     */
    public static PurchaseOrder generatePurchaseOrderData(Project project) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.purchaseOrderDate = project.startDate;
        purchaseOrder.startDate = project.startDate;
        purchaseOrder.endDate = project.endDate;
        purchaseOrder.poNo = Integer.toString(TestDataUtil.getRandomNumber(100, 1000));

        return purchaseOrder;
    }

    /**
     * Generating data for Pay Item Items for import
     *
     * @return Pay item items data
     */
    public static PayItemExcelRecord generatePayItemImportData() {
        PayItemExcelRecord payItemExcelRecord = new PayItemExcelRecord();
        payItemExcelRecord.name = "PayItem-" + TestDataUtil.getRandomName();
        payItemExcelRecord.description = payItemExcelRecord.name + "- Description";
        payItemExcelRecord.measurementSystem = "IS System";
        payItemExcelRecord.unit = "AC";
        payItemExcelRecord.quantity = "10.0";
        payItemExcelRecord.rateIn$ = "100.00";

        return payItemExcelRecord;
    }

    /**
     * Generating data for Current Resource Titles Items for import
     *
     * @return Current Resource titles items data
     */
    public static CurrentResourceTitles generateCurrentResourceTitlesImportData() {
        CurrentResourceTitles currentResourceTitles = new CurrentResourceTitles();
        currentResourceTitles.resourceId = "testResourceID";
        currentResourceTitles.recordID = TestDataUtil.getRandomNumber(0, 20).toString();
        currentResourceTitles.fullName = "Automation";
        currentResourceTitles.fixedPriceIn$ = "10";
        currentResourceTitles.directPriceIn$ = "20";
        currentResourceTitles.inDirectPriceIn$ = "30";
        currentResourceTitles.workingHoursPerDay = "7";
        return currentResourceTitles;
    }

    /**
     * To generate DPR data
     *
     * @return DPR
     */
    public static DailyProgressReport generateDPRData() {
        var dailyProgressReport = new DailyProgressReport();
        dailyProgressReport.location = "Bangalore";
        dailyProgressReport.lowTemp = "10";
        dailyProgressReport.highTemp = "20";
        return dailyProgressReport;
    }

    /**
     * To generate Subscription data
     *
     * @return Subscription data
     */
    public static Subscribe generateSubscribeData(String dateFormat) {
        var subscribe = new Subscribe();
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        subscribe.subject = "Subscribing to Report";
        subscribe.startDate = new SimpleDateFormat(dateFormat).format(today);
        subscribe.endDate = new SimpleDateFormat(dateFormat).format(tomorrow);
        subscribe.startTime = "00";
        return subscribe;
    }

    /**
     * Method to create the details for a new portfolio
     *
     * @return Creates a new portfolio and returns the portfolio details
     */
    public static Portfolio generateNewPortfolioData() {

        Portfolio newPortfolio;
        newPortfolio = new Portfolio();
        newPortfolio.code = "Portfolio" + TestDataUtil.generateUniqueAlphanumericCode();
        newPortfolio.description = TestDataUtil.getRandomName();
        int budget = TestDataUtil.getRandomNumber(10000, 999999);
        newPortfolio.budget = Integer.toString(budget);
        return newPortfolio;
    }

    /**
     * Method to generate a unique alpha numeric code
     *
     * @return A unique alphanumeric code
     */
    public static String generateUniqueAlphanumericCode() {
        return UUID.randomUUID().toString().replace(RegexStrings.hyphen.getValue(), "").substring(0, 12);
    }

    /**
     * Function returns random number from 0 to 99
     *
     * @return number from 0 to 99
     */
    public static int getMaxTwoDigitRandomNumber() {
        return random.nextInt(100);
    }

    /**
     * Method to get the current time stamp
     *
     * @return current time stamp
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * @param i - to select the number of digits for a random number
     * @return - returns the generated random number
     */
    public static String getRandomNumberCustomised(double i) {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, i));
        return String.valueOf(randomNumber);
    }

    /**
     * Generates new inflation rule data
     *
     * @param dateFormat - date format
     * @return inflationRule; Creates a new Inflation Rule Data
     */
    public static InflationRule generateNewInflationRuleData(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        InflationRule inflationRule;
        inflationRule = new InflationRule();
        inflationRule.inflationRuleID = "IF" + getRandomNumber(1000000, 9999999);
        inflationRule.description = "Creating new Inflation Rule " + inflationRule.inflationRuleID;
        inflationRule.annualInflationRate = "10.55";
        inflationRule.effectiveFromDate = DateTimeUtil.getDateString(dateFormat, today);
        return inflationRule;
    }

    /**
     * Generating data for Budget Estimate Type for import
     *
     * @return budget estimate type data
     */
    public static BudgetEstimateTypeImport generateBudgetEstimateTypeImportData() {
        BudgetEstimateTypeImport budgetEstimateTypeImport = new BudgetEstimateTypeImport();
        budgetEstimateTypeImport.budgetEstimateType = "ImportBET" + getRandomNumber(1000000, 9999999);
        budgetEstimateTypeImport.description = budgetEstimateTypeImport.budgetEstimateType;
        return budgetEstimateTypeImport;
    }

    /**
     * Generating data for Budget Estimate Items for import
     *
     * @return budget estimate items data
     */
    public static BudgetEstimateItemsImport generateBudgetEstimateItemsImportData() {
        BudgetEstimateItemsImport budgetEstimateItemsImport = new BudgetEstimateItemsImport();
        String payItemNoGenerated = "PayItemNo" + getRandomNumber(1000000, 9999999);
        budgetEstimateItemsImport.type = "Item";
        budgetEstimateItemsImport.itemDescription = payItemNoGenerated;
        budgetEstimateItemsImport.payItemNo = payItemNoGenerated;
        budgetEstimateItemsImport.unitPriceInDollars = "100";
        budgetEstimateItemsImport.unit = "LS";
        budgetEstimateItemsImport.indentLevel = "0";
        budgetEstimateItemsImport.quantity = "10";
        return budgetEstimateItemsImport;
    }

    /**
     * Generating data for Fund Agency for import
     *
     * @return fund agency data
     */
    public static FundAgencyImport generateFundAgencyImportData() {
        FundAgencyImport fundAgencyImport = new FundAgencyImport();
        fundAgencyImport.name = "ImportFundAgency" + getRandomNumber(1000000, 9999999);
        fundAgencyImport.description = fundAgencyImport.name;
        return fundAgencyImport;
    }

    /**
     * Generating data for Fund Categories for import
     *
     * @return fund categories data
     */
    public static FundCategoriesImport generateFundCategoriesImportData() {
        FundCategoriesImport fundCategoriesImport = new FundCategoriesImport();
        fundCategoriesImport.name = "ImportFundCategories" + getRandomNumber(1000000, 9999999);
        fundCategoriesImport.description = fundCategoriesImport.name;
        return fundCategoriesImport;
    }

    /**
     * Generating data for Fund Type for import
     *
     * @return fund type data
     */
    public static FundTypeImport generateFundTypeImportData() {
        FundTypeImport fundTypeImport = new FundTypeImport();
        fundTypeImport.name = "ImportFundCategories" + getRandomNumber(1000000, 9999999);
        fundTypeImport.description = fundTypeImport.name;
        return fundTypeImport;
    }

    /**
     * Generating data for Global Fund List for import
     *
     * @return Global Fund List data
     */
    public static GlobalFundListImport generateGlobalFundListImportData() {
        GlobalFundListImport globalFundListImport = new GlobalFundListImport();
        globalFundListImport.fundSourceName = "ImportGlobalFundList" + getRandomNumber(1000000, 9999999);
        globalFundListImport.fundSourceCode = globalFundListImport.fundSourceName;
        return globalFundListImport;
    }

    /**
     * Generating data for Schedule Gantt for import
     *
     * @param lineNumber line number
     * @param project    project data
     * @return Schedule Gantt data
     */
    public static ScheduleGanttImport generateScheduleGanttImportData(String lineNumber, Project project) {
        ScheduleGanttImport scheduleGanttImport = new ScheduleGanttImport();
        scheduleGanttImport.lineNumber = lineNumber;
        scheduleGanttImport.payItemNumber = TestDataUtil.getRandomName();
        scheduleGanttImport.description = "Import " + getRandomNumber(1000000, 9999999);
        scheduleGanttImport.startDate = project.startDate;
        scheduleGanttImport.endDate = project.endDate;
        scheduleGanttImport.progress = "0.0000";
        return scheduleGanttImport;
    }

    /**
     * Generating data for Bid Items for import
     * The pay item number will have the format "Line Number"+Random number, to avoid duplication
     *
     * @param lineNumber   line number of the record being imported
     * @param quantity     quantity of bid item to be imported
     * @param unitPriceIn$ unit price in $
     * @return Bid Items data
     */
    public static BidItemsImport generateBidItemsImportData(String lineNumber, String quantity, String unitPriceIn$) {
        BidItemsImport bidItemsImport = new BidItemsImport();
        bidItemsImport.type = "Item";
        bidItemsImport.indentLevel = "0";
        bidItemsImport.lineNumber = lineNumber;
        bidItemsImport.description = getRandomName();
        bidItemsImport.payItemNumber = "PayItem " + getRandomName();
        bidItemsImport.quantity = quantity;
        bidItemsImport.group = "Default";
        bidItemsImport.unit = "AC";
        bidItemsImport.alternateGroup = "Base Bid";
        bidItemsImport.unitPriceIn$ = unitPriceIn$;
        return bidItemsImport;
    }

    /**
     * Generating data for Contract Items for import
     * The pay item number will have the format "Line Number"+Random number, to avoid duplication
     *
     * @param lineNumber   line number of the record being imported
     * @param quantity     quantity of bid item to be imported
     * @param unitPriceIn$ unit price in $
     * @return Contract Items data
     */
    public static ContractItemsImport generateContractItemsImportData(String lineNumber, String quantity, String unitPriceIn$) {
        ContractItemsImport contractItemsImport = new ContractItemsImport();
        contractItemsImport.type = "Item";
        contractItemsImport.indentLevel = "0";
        contractItemsImport.lineNumber = lineNumber;
        contractItemsImport.description = getRandomName();
        contractItemsImport.payItemNumber = "PayItem " + getRandomName();
        contractItemsImport.quantity = quantity;
        contractItemsImport.group = "Default";
        contractItemsImport.unit = "AC";
        contractItemsImport.unitPriceIn$ = unitPriceIn$;
        contractItemsImport.isNonContract = "N";
        contractItemsImport.isComplete = "N";
        return contractItemsImport;
    }

    /**
     * User data for excel import for AD Users
     *
     * @param dateFormat date format
     * @return User data
     */
    public static UserDataImport generateUserDataImportADUser(String dateFormat) {
        UserDataImport userDataImport = new UserDataImport();
        userDataImport.internalID = "";
        userDataImport.userName = getRandomName() + "@" + generateUniqueAlphanumericCode() + ".com";
        userDataImport.firstName = "Automation";
        userDataImport.email = getRandomName() + "@" + generateUniqueAlphanumericCode() + ".com";
        userDataImport.isActive = "Yes";
        userDataImport.accountExpiryDate = DateTimeUtil.dateGenerationFromToday(0, 0, 6, dateFormat);
        userDataImport.roles = Role.Administrator.getValue();
        userDataImport.businessUnit = "HeadOffice";
        userDataImport.isADUser = "Yes";
        return userDataImport;
    }

    /**
     * User data for excel import
     *
     * @param dateFormat date format
     * @return User data
     */
    public static UserDataImport generateUserDataImport(String dateFormat) {
        UserDataImport userDataImport = new UserDataImport();
        userDataImport.internalID = "";
        userDataImport.userName = "Auto" + generateUniqueAlphanumericCode();
        userDataImport.firstName = userDataImport.userName;
        userDataImport.email = userDataImport.userName + "@" + generateUniqueAlphanumericCode() + ".com";
        userDataImport.isActive = "Yes";
        userDataImport.accountExpiryDate = DateTimeUtil.dateGenerationFromToday(0, 0, 6, dateFormat);
        userDataImport.roles = Role.Administrator.getValue();
        userDataImport.businessUnit = "HeadOffice";
        userDataImport.type = "Internal";
        userDataImport.isADUser = "No";
        userDataImport.sendEmail = "No";
        return userDataImport;
    }

    /**
     * External IDP configuration data
     *
     * @param externalIDP External IDP name
     * @return External IDP configuration data
     */
    public static ExternalIDPConfiguration generateExternalDataIDPConfigurationData(String externalIDP) {
        ExternalIDPConfiguration externalIDPConfiguration = new ExternalIDPConfiguration();
        if (externalIDP.contains(ExternalIDP.Azure.getValue())) {
            externalIDPConfiguration.name = "AZURE-ADFS";
            externalIDPConfiguration.IDPIdentifier = "https://sts.windows.net/1cb57d25-6f25-4e2a-9e2d-895176ed40c6/";
            externalIDPConfiguration.protocol = "SAML 2.0";
            externalIDPConfiguration.signOnBinding = "Http Post";
            externalIDPConfiguration.logOutBinding = "Http Redirect";
            externalIDPConfiguration.signOnUrl = "https://login.microsoftonline.com/1cb57d25-6f25-4e2a-9e2d-895176ed40c6/saml2";
            externalIDPConfiguration.logOutUrl = "https://login.microsoftonline.com/1cb57d25-6f25-4e2a-9e2d-895176ed40c6/saml2";
            externalIDPConfiguration.metaDataAddress = "https://login.microsoftonline.com/1cb57d25-6f25-4e2a-9e2d-895176ed40c6/federationmetadata/2007-06/federationmetadata.xml?appid=d77c87f0-ddfb-4d5a-9178-ad518bba6892";
            return externalIDPConfiguration;
        } else if (externalIDP.contains(ExternalIDP.ADFS3.getValue())) {
            externalIDPConfiguration.name = "ADFS3";
            externalIDPConfiguration.IDPIdentifier = "https://adfs3.aurigo.net/adfs/services/trust";
            externalIDPConfiguration.protocol = "ADFS (WS - Federation)";
            externalIDPConfiguration.signOnBinding = "Http Redirect";
            externalIDPConfiguration.logOutBinding = "Http Redirect";
            externalIDPConfiguration.signOnUrl = "https://adfs3.aurigo.net/adfs/ls";
            externalIDPConfiguration.logOutUrl = "https://adfs3.aurigo.net/adfs/ls";
            externalIDPConfiguration.metaDataAddress = "https://adfs3.aurigo.net/federationmetadata/2007-06/federationmetadata.xml";
            return externalIDPConfiguration;

        } else if (externalIDP.contains(ExternalIDP.ADFS4.getValue())) {
            externalIDPConfiguration.name = "ADFS4";
            externalIDPConfiguration.IDPIdentifier = "https://adfs4.aurigo.net/adfs/services/trust";
            externalIDPConfiguration.protocol = "ADFS (WS - Federation)";
            externalIDPConfiguration.signOnBinding = "Http Redirect";
            externalIDPConfiguration.logOutBinding = "Http Redirect";
            externalIDPConfiguration.signOnUrl = "https://adfs4.aurigo.net/adfs/ls";
            externalIDPConfiguration.logOutUrl = "https://adfs4.aurigo.net/adfs/ls";
            externalIDPConfiguration.metaDataAddress = "https://adfs4.aurigo.net/federationmetadata/2007-06/federationmetadata.xml";
            return externalIDPConfiguration;
        } else
            return externalIDPConfiguration;
    }

    /***
     * Generates test data for WorkSize page
     *
     *
     * @return new WorkSize data
     */
    public static WorkSize getWorkSizeData() {
        WorkSize workSize = new WorkSize();
        workSize.name = String.format("WorkSize%s", TestDataUtil.generateUniqueAlphanumericCode());
        workSize.description = "WS Description";
        workSize.minimumWorkValue = 1;
        workSize.maximumWorkValue = 100000;
        workSize.isActive = true;
        return workSize;
    }

    /**
     * Method to generate key contact data
     *
     * @return a new key contact test data
     */
    public static KeyContact generateKeyContact() {
        var keyContact = new KeyContact();
        keyContact.firstName = String.format("First%s", generateUniqueAlphanumericCode());
        keyContact.lastName = String.format("Last%s", generateUniqueAlphanumericCode());
        keyContact.title = "Supreme Leader President-Prime Minister Admiral General";
        keyContact.emailId = String.format("%s@email.com", keyContact.firstName);
        keyContact.address = "105/23 1st Road, 2nd Block, 3rd Street";
        keyContact.zipcode = "560034";
        keyContact.city = "Bengaluru";
        keyContact.state = "Karnataka";
        return keyContact;
    }

    /**
     * Get all months in a list
     *
     * @return - listpage
     */
    public static List<String> getAllMonths() {
        List<String> allMonths = Arrays.asList(new DateFormatSymbols().getMonths());

        return allMonths;
    }

    /**
     * Generates Vendor data for additional fields
     *
     * @return vendorDetails
     */
    public static Vendor generateVendorDetails() {
        Vendor vendorDetails = new Vendor();
        vendorDetails.address1 = TestDataUtil.getRandomName();
        vendorDetails.address2 = TestDataUtil.getRandomName();
        vendorDetails.address3 = TestDataUtil.getRandomName();
        vendorDetails.city = TestDataUtil.getRandomName();
        vendorDetails.state = TestDataUtil.getRandomName();
        vendorDetails.phoneNumber = "1111111111";
        vendorDetails.zipCode = String.valueOf(TestDataUtil.getRandomNumber(1, 100000));
        vendorDetails.email = TestDataUtil.getRandomName() + "@a.com";
        vendorDetails.faxNumber = String.valueOf(TestDataUtil.getRandomNumber(1, 100000));
        return vendorDetails;
    }
}
