package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum EnterpriseReportsList {

    BudgetVsActualsReport("Budget vs Actuals Report"),
    BudgetVsActualCost("Budget vs Actual Cost"),
    DelayedProjectsBasedOnSchedule("Delayed Projects Based on Schedule"),
    EnterpriseCostSheet("Enterprise Cost Sheet"),
    MobileSyncReport("Mobile Sync Report"),
    ProjectFundingOverviewReport("Project Funding Overview Report"),
    FundSummaryReport("Fund Summary Report"),
    ResourceCostSheetReport("Resource Cost Sheet Report"),
    ResourceDeficitReport("Resource Deficit Report"),
    ResourceUtilizationReport("Resource Utilization Report"),
    ResourceUtilizationSummaryReport("Resource Utilization Summary Report"),
    TitlesPendingAssignmentReport("Titles Pending Assignment Report");


    private String value;

    EnterpriseReportsList(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
