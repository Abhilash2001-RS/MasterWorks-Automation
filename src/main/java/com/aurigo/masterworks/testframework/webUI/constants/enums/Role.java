package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum Role {

    Administrator("Administrator"),
    User("User"),
    ProjectInitiator("Project Initiator"),
    ProjectManager("Project Manager"),
    ProjectReviewBoard("Project Review Board"),
    ProgramManager("Program Manager"),
    PortfolioManager("Portfolio Manager"),
    ConstructionManager("Construction Manager"),
    FieldInspector("Field Inspector"),
    Contractor("Contractor"),
    ProjectCoordinator("Project Coordinator"),
    BusinessAdmin("Business Admin"),
    BidReviewer("Bid Reviewer"),
    Bidder("Bidder"),
    FinanceAdministrator("Finance Administrator"),
    FinanceManager("Finance Manager"),
    FinanceCoordinator("Finance Coordinator"),
    FundManager("Fund Manager"),
    OnlyView("Only View"),
    OnlyEdit("Only Edit"),
    OnlyDelete("Only Delete"),
    OnlyNew("Only New"),
    MCWOUser("MCWO User"),
    Automation("Automation");


    private String value;

    Role(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the Enum item selected
     *
     * @return Role Name
     */
    public String getValue() {
        return this.value;
    }
}
