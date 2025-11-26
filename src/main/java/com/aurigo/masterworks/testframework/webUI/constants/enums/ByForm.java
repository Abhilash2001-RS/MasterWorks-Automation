package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ByForm {
    AdHocReports("Ad-hoc Reports"),
    Administration("Administration"),
    FormBuilder("Form Builder"),
    FunctionalConfiguration("Functional Configurations"),
    BudgetManagement("Budget Management"),
    ContractManagement("Contract Management"),
    IssueLogColorConfiguration("Issue Log Color Configuration"),
    MobileApplicationSettings("Mobile Application Settings"),
    PlanningManagement("Planning Management"),
    ResourceManagement("Resource Management"),
    SystemConfigurations("System Configurations"),
    ReportManagement("Report Management"),
    ReportConfigurations("Report Configurations"),
    ReportSubscriptions("Report Subscriptions"),
    UserManagement("User Management"),
    PendingRegistrations("Pending Registrations"),
    Permissions("Permissions"),
    GIS("GIS"),
    SecurityRoles("Security Roles"),
    UserAccounts("User Accounts"),
    WorkFlowManagement("WorkFlow Management"),
    CheckListForLand("Checklist For Land"),
    DocumentManagement("Document Management"),
    EnterpriseManagement("Enterprise Management"),
    EnterpriseScheduleGantt("Enterprise Resource Gantt"),
    FavoriteManagement("Favorite Management"),
    FundManagement("Fund Management"),
    FundTransaction("Fund Transaction"),
    GlobalFundList("Global Fund List"),
    PlanningFund("Planning Fund"),
    Needs("Needs"),
    Notification("Notifications"),
    Inbox("Inbox"),
    Portfolio("Portfolio"),
    VendorManagement("Vendor Management"),
    StatementOfQualification("Statement of Qualification"),
    VendorScoring("Vendor Scoring"),
    ReportGallery("Report Gallery"),
    LandManagement("Land Management"),
    Forecast("Forecast"),
    LandMgmt("Land Management"),
    LandScoring("Land Scoring"),
    ParcelDetails("Parcel Details"),
    LibraryManagement("Library Management"),
    PlanningMgmt("Planning Management"),
    Configuration("Configuration"),
    LandNormalizationFactor("Land Normalization Factor"),
    ProgramForecastSnapshots("Program Forecast Snapshots"),
    Program("Program"),
    ReviseProgram("Revise Program"),
    WorkProjects("Work Projects"),
    ReportGalleryPlanning("Report Gallery"),
    ProjectManagement("Project Management"),
    BidEstimateInformation("Bid Estimate Information"),
    BidEstimate("Bid Estimate"),
    BidItems("Bid Items"),
    BidLetting("Bid Letting"),
    BidManagement("Bid Management"),
    BidMgmt("Bid Management"),
    SubmittalForEstimate("Submittal For Estimate"),
    ReportGalleryBid("Report Gallery"),
    BudgetManagementProject("Budget Management"),
    BudgetEstimateRevision("Budget Estimate Revision"),
    BudgetEstimates("Budget Estimates"),
    CurrentBudget("Current Budget"),
    Forecasts("Forecasts"),
    ReportGalleryBudget("Report Gallery"),
    Collaboration("Collaboration"),
    MinutesOfMeeting("Minutes Of Meeting"),
    RequestForInformation("Request for Information"),
    CommitmentsAndExpenses("Commitments & Expenses"),
    ExpenseForm("Expenses"),
    PurchaseOrderRevision("Purchase Order Revision"),
    PurchaseOrder("Purchase Order"),
    ContractManagementProject("Contract Management"),
    BillingsAndPayment("Billings & Payments"),
    MaterialsOnHand("Materials On Hand"),
    PayEstimates("Pay Estimates"),
    ChangeManagement("Change Management"),
    ContractChangeOrder("Contract Change Order"),
    PotentialChangeOrder("Potential Change Order"),
    RequestForInformationChange("Request for Information"),
    RequestForProposal("Request For Proposal"),
    CollaborationProject("Collaboration"),
    SubmittalPackage("Submittal Package"),
    Submittals("Submittals"),
    Transmittals("Transmittals"),
    Configurations("Configurations"),
    ContractCalendar("Contract Calendar"),
    Contractors("Contractors"),
    KeyContacts("Key Contacts"),
    Settings("Settings"),
    ContractItems("Contract Items"),
    Contracts("Contracts"),
    ForecastsContracts("Forecasts"),
    IssueTracking("Issue Tracking"),
    PunchList("Punch List"),
    ProgressTracking("Progress Tracking"),
    DailyProgressReport("Daily Progress Report"),
    RequestForSubletWork("Request For Sublet Work"),
    PunchListProgressTracking("Punch List"),
    SubmittalForContract("Submittal For Contract"),
    VendorMgmt("Vendor Management"),
    ConsolidatedVendorEvaluation("Consolidated Vendor Evaluation"),
    VendorEvaluation("Vendor Evaluation"),
    ReportGalleryVendorMgmt("Report Gallery"),
    FundMgmt("Fund Management"),
    FundingRules("Funding Rules"),
    ProjectFundList("Project Fund List"),
    ProjectFundTransaction("Project Fund Transaction"),
    ProjectCalendar("Project Calendar"),
    ProjectNeeds("Project Needs"),
    ProjectPhases("Project Phases"),
    ProjectScoring("Project Scoring"),
    Projects("Projects"),
    TaskResourceGantt("Task Resource Gantt"),
    RiskandIssues("Risks & Issues"),
    IssueLog("Issue Log"),
    RiskRegister("Risk Register"),
    ScheduleSnapshot("Schedule Snapshot"),
    Schedule("Schedule"),
    ReportGalleryProject("Report Gallery");
    private String value;

     ByForm(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkFlowStatus items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ByForm.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
