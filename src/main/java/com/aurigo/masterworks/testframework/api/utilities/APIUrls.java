package com.aurigo.masterworks.testframework.api.utilities;

public class APIUrls {

    public static String LOGIN = "Login";
    public static String LOGIN_BODY = "\"{'username':'%s','password':'%s','c':'','role':''}\"";

    public static String LOGOUT = "Logout";

    public static String PROJECT_DETAILS = "ModuleV2/GetNewOrModifiedProjectDetails";

    public static String PROJECT_MODULES = "Module/GetProjectModules";
    public static String PROJECT_MODULES_PARAMS = "?moduleId=%s&projectId=%s&contractId=%s&modulesTemplate=%s";

    public static String GET_ESTIMATE_DOWNLOAD_SIZE = "ModuleV2/GetEstimatedDownloadSizeProjectList";

    public static String GET_ESTIMATED_DOWNLOAD_SIZE = "Module/GetEstimatedDownloadSizeProjectList";
    public static String GET_ESTIMATED_DOWNLOAD_SIZE_PARAMS = "?userName=%s&lastSyncedAt=%s&moduleSyncTime=&projectIds=%s";

    public static String GET_ESTIMATED_DOWNLOAD_SIZE_VALUE = "Module/GetEstimatedDownloadSize";

    public static String GET_LOCK_STATUS = "Contractor/GetLockStatus";
    public static String GET_LOCK_MESSAGE = "Contractor/GetLockMessage";
    public static String GET_LOCK_STATUS_PARAMS = "?parentID=%s";


    public static String GET_RESOURCE_GANTT = "ResourceGantt";
    public static String GET_RESOURCE_GANTT_PARAMS = "?ModuleId=%s&ParentId=%s&Module=%s";
    public static String GET_RESOURCE_GANTT_BODY_PARAMS = "{\"ParentID\": %s,\"ModuleID\": \"%s\",\"Module\": \"%s\",\"filterCond\": {\"Dept\": \"\",\"Title\": \"\",\"Users\": \"\"}}";

    public static String PROJECT_MODULESV2 = "ModuleV2";

    public static String PROJECT_MODULESV2_LIST = "ModuleV2/GetProjectModuleList";
    public static String PROJECT_MODULESV2_LIST_PARAMS = "?projectId=%s&lastSyncedAt=%s";

    public static String GET_FORM_TEMPLATE = "Module/GetFormTemplate";
    public static String GET_FORM_TEMPLATEV2 = "ModuleV2/GetFormTemplate";
    public static String GET_FORM_TEMPLATE_PARAMS = "?lastSyncedAt=&moduleId=%s";
    public static String GET_FORM_TEMPLATE_BODY_PARAMS = "={\"PID\":%s,\"ParentID\":%s,\"Context\":\"%s\"}";

    public static String GET_FILES_AND_FOLDER_LISTS = "DocumentV2/GetFilesAndFolders";
    public static String GET_FILES_AND_FOLDER_LISTS_PARAMS = "?projectID=%s&parentFolderID=%s&kendoParams&contractID=%s&parentModuleID=%s";

    public static String GET_IS_VALID_FILE_FOR_UPLOAD= "DocumentsUpload/IsValidFileForUpload";
    public static String GET_DOCUMENT_UPLOAD= "DocumentsUpload";
    public static String GET_DOCUMENT_UPLOAD_PARAMS = "?fileName=%s";

    public static String GET_UPDATE_DATA = "FormAttachment/UpdateData";
    public static String GET_UPDATE_BODY_PARAMS = "=[{\"PID\": %s,\"ParentID\": %s,\"ModuleID\": %s,\"ParentModuleID\":%s,\"InstanceID\":%s,\"DocumentList\":[{\"ModuleID\":%s,\"AttachmentControlID\":%s}]}]";

    public static String DELETE_ATTACHMENT = "Document/DeleteAttachment";
    public static String DELETE_ATTACHMENT_PARAMS = "?linkIds=%s";

    public static String GET_GANTTCHART = "GanttChart/Get";
    public static String GET_GANTTCHART_PARAMS = "?context=%s&ParentID=%s&QPID=%s";

    public static String POST_GANTTCHART = "GanttChart";
    public static String POST_GANTTCHART_PARAMS = "?context=%s&ParentID=%s&PID=%s&UID=%s&QPID=0&ParentModuleId=%s";
    public static String POST_GANTTCHART_BODY_PARAMS = "=[{\"ID\":%s,\"Name\":\"%s\",\"StartTime\":\"%s\",\"EndTime\":\"%s\",\"Effort\":\"00:00\",\"PreferredStartTime\":\"%s\",\"IndentLevel\":2,\"ProgressPercent\":0,\"IsAuto\":true,\"Tag\":{\"Type\":\"A\",\"ContainerID\":\"null\",\"ItemID\":1,\"SubItemID\":0,\"ActivityID\":1,\"Quantity\":1,\"ProjectPhaseID\":0},\"ParentID\":null,\"QPID\":0,\"ModuleID\":null,\"Year1\":0,\"Year2\":0,\"Year3\":0,\"Year4\":0,\"Year5\":0,\"Year6\":0,\"Year7\":0,\"Year8\":0,\"Year9\":0,\"Year10\":0,\"Year11\":0,\"Year12\":0,\"Year13\":0,\"Year14\":0,\"Year15\":0,\"Year16\":0,\"Year17\":0,\"Year18\":0,\"Year19\":0,\"Year20\":0,\"IsInflationEnabled\":true,\"AnnualInflationRate\":0,\"InflationEffectiveFrom\":null,\"UninflatedQuantity\":1200,\"Quantity\":1}]";

    public static String GET_WBSGANTT = "WBSGantt";
    public static String GET_WBSGANTT_PARAMS = "?context=%s&ID=%s&SnapshotID=%s&IsComparison=%s";

    public static String POST_WBSGANTT = "WBSGantt";
    public static String POST_WBSGANTT_PARAMS = "?context=%s&ID=%s";
    public static String POST_WBSGANTT_BODY_PARAMS = "{\"newAddedTasks\":\"[{\\\"HasAttachments\\\":0,\\\"WBSID\\\":\\\"1\\\",\\\"ID\\\":0,\\\"TaskName\\\":\\\"New Task \\\",\\\"StartTime\\\":\\\"%s\\\",\\\"EndTime\\\":\\\"%s\\\",\\\"Effort\\\":\\\"00:00\\\",\\\"Description\\\":\\\"Description of Task\\\",\\\"IndentLevel\\\":0,\\\"ProgressPercent\\\":0,\\\"SortOrder\\\":1,\\\"Weightage\\\":1}]\",\"removedTaskIds\":\"[]\",\"newUpdatedTasks\":\"[]\",\"listColumnUpdates\":\"[{\\\"ListInjectedColumns\\\":[]}]\"}";

    public static String GET_MODULE_DATA = "MobileModule/GetModules";
    public static String GET_MODULE_BODY_PARAMS="ClassAttribute=CUSTOM_MODULE&AdditionalInfo=&LastSyncedAt=&IdentifierKey=&ModuleId=FRMCHGD&CustomModuleFilter=&TemplateTypeList%5B%5D=FormTemplate&TemplateTypeList%5B%5D=ListTemplate";

    public static String GET_MY_TASKSV2 = "TasksV2/GetMyTasks";
    public static String GET_MY_TASK_LISTS = "TasksV2/GetMyTaskList";
    public static String POST_OBTAIN_TASKV2_RESULT = "TasksV2/ObtainTasksResult";
    public static String GET_MY_TASK_LISTS_PARAMS = "?jsonParameters=%s";

    public static String GET_TASKS = "Tasks";
    public static String GET_MY_TASKS = "Tasks/GetMyTasks";
    public static String POST_OBTAIN_TASK_RESULT = "Tasks/ObtainTasksResult";
    public static String GET_MY_TASKS_PARAMS = "?jsonParameters";

    public static String GET_DETAILS = "MicroService/GetDetails";

    public static String GET_FETCH_ALL_ROLES = "RolesAndPermissions/FetchAllRoles";

    public static String GET_ESTIMATED_DOWNLOAD_SIZE_POST = "Module/GetEstimatedDownloadSizePost";
    public static String GET_ESTIMATED_DOWNLOAD_SIZEV2_POST = "ModuleV2/GetEstimatedDownloadSizePost";
    public static String GET_ESTIMATED_DOWNLOAD_SIZE_POST_PARAMS = "?userName=%s&lastSynchedAt=%s&projectIds=%s";

    public static String GET_DOC_VISIBILITY = "Module/GetDocVisibility";
    public static String GET_DOC_VISIBILITY_PARAMS = "?projectID=%s";

    public static String GET_MODULE_COMPONENT = "ModuleComponent";

    public static String GET_GIS_SETTINGS = "GIS/GetGISSettings";
    public static String GET_GIS_SETTINGS_PARAMS = "?lastSyncedAt=";

    public static String GET_DOCUMENT_FOLDER_TREE = "Associatedocumentfolder/GetDocumentFolderTree";
    public static String GET_DOCUMENT_FOLDER_TREE_PARAMS = "?folderStructureId=%s";

    public static String GET_DATE_SYNC = "DateSync";
    public static String GET_SERVER_DATE_AS_TEXT = "DateSync/GetServerDateAsText";

    public static String GET_ESTIMATE = "GIS/GetEstimate";
    public static String GET_TOKEN = "GIS/GetToken";

    public static String GET_DATA_FOR_SELECTED_PROJECTS = "GIS/GetGISDataForSelectedProjects";
    public static String GET_DATA_FOR_SELECTED_PROJECTS_PARAMS = "?projectIDs=%s";

    public static String GET_GETLAYER = "GIS/GetLayer";
    public static String GET_LAYER_PARAMS = "?layerId=%s&IsExtentRequired=%s&outSR=%s";

    public static String GET_ADD_MULTIPLE = "AddMultiple?";
    public static String GET_ADD_MULTIPLE_PARAMS = "{\"sort\":null,\"filter\":null,\"additionalParameters\":\"A8840w9Cf9Kbu9K%2f9kmxyjzpKNOzuT20dn6rKx6dKPVr4yI6UZcmIiYUvJmFNWoSKhbZo1STi0vKO0RQwAxr6yN9dI036cpjjmN5VshcIHQLk91aUy6dAvi3pbkgReUJSJla4DfXNrc08A4Sa85%2b9GoAiT9rpGJKUjRYeN2JB9ztOCSYK%2fBtQKPH2ooXdid%2fzURGiGtDHGfkjTcIDNglY5%2bU4wO4fe0JDp6on86jcb66D4TVtj81AxGPZDLU9ROCkz7PVAKEpDCaH7DFfzLyPzQqwUa%2bn4G64usyKE5GriGt%2f58m10or0O4KdeVMI77Wi%2f%2bZX9DAK8s2mMVopuqpyr4okwBGSzxgrELTbm8pdghhepm39yN8ybg0i021CNiGKyHM3obMFADba7J04FQxvqTaX2xtLrERpyGBBX8CKXve2TlY26lK7GJbuNzHrV3kHRzhH88k0J%2bmHfyXF6DwHX3spfbB5m%2b1rvS%2f5BDLn4M%3d \",\"take\":20,\"skip\":0,\"page\":1,\"pageSize\":20}";
    public static String GET_DOC_PROPERTY = "AssociateDocumentFolder/GetDocumentPropertyListAndAssociatedDocumentProperty";
    public static String GET_DOC_PROPERTY_PARAMS = "?documentFolderId=%s";

    public static String POST_PROJECT_DETAILS_PROJECT_IDS = "ModuleV2/FetchProjectDetailsByProjectIds";
    public static String POST_PROJECT_DETAILS_PROJECT_BODY_PARAMS = "=[{\"projectIdList\":%s}]";

    public static String GET_PROJECTID_ITEMS = "GetProjectIDForItems/Get";
    public static String GET_PROJECTID_ITEMS_PARAMS = "?itemSelected=%s&containerSelected=%s&ModuleId=%s&instanceId=%s";

    public static String GET_CONTRACTS = "RenderService/GetContracts";
    public static String GET_CONTRACTS_PARAMS = "?projectID=%s";

    public static String GET_TEMPLATES = "MobileModule/GetTemplates";
    public static String GET_TEMPLATES_PARAMS = "lastSyncedAt=";

    public static String GET_DOWNLOAD_STREAM = "Document/DownloadStream";
    public static String GET_DOWNLOAD_STREAM_PARAMS = "?docID=%s";

    public static String SYNC_DATA_DOWNLOAD = "MobilePackage/SyncDataDownload";
    public static String SYNC_DATA_UPLOAD = "MobilePackage/SyncDataUpload";
    public static String SYNC_DATA_DOWNLOAD_PARAMS = "={\"PackageIdentifierObj\":{\"ID\":%s,\"Name\":\"%s\",\"SyncKey\":\"%s\",\"Version\":%s},\"CustomData\":\"{}\"} ";

    public static String GET_DOWNLOAD = "Document/Download";
    public static String GET_DOWNLOAD_PARAMS = "?docID=%s";

    public static String GET_MAIN_MENUS = "MobileModule/GetMainMenus";

    public static String GET_APP_SETTING = "AppSetting";

    public static String GET_SYNC_PREREQUISITES = "module/GetSyncPrerequisites";

    public static String GET_DOCUMENT_DETAILS = "workflow/GetDocumentDetails";
    public static String GET_DOCUMENT_DETAILS_PARAMS = "?moduleId=%s&formInstanceid=%s&storageId=%s&jsonParameters=%s";

    public static String GET_UPLOAD_DOCUMENT_META_DATA = "DocumentV2/UploadDocumentMetadata";
    public static String GET_UPLOAD_DOCUMENT_META_DATA_PARAMS = "?parentModuleID=%s&folderId=%s&projectId=%s&contractID=%s&description=%s&docID=%s&workFlowID=%s&jsonReqParams=%s";

    public static String GET_ENVIRONMENTDETAILS = "Login/GetEnvironmentDetails";

    public static String GET_USER_DETAILS = "Security/GetUserDetails";
    public static String GET_ROLES = "Security/GetRoles";

    public static String GET_USERDETAILS = "UserDetails";
    public static String GET_LOG_USER_USAGE = "LogUserUsage";
    public static String GET_LOG_USER_USAGE_BODY = "{ \"UserID\": %s,\"UserName\": \"%s\",\"Url\": \"/Modules/USRMGMT/Login.aspx\",\"IP\": \"%s\",\"TimeDt\":\"\",\"Comment\": \"%s\",\"SessionID\": \"%s\",\"Module\": \"%s\", \"Role\": \"%s\", \"CompanyCode\": \"\",  \"BLTime\": null,  \"RenderTime\": null,  \"UserEmail\": \"\", \"LoginId\": \"%s\",  \"MobileSyncRequestGuid\": \"\" } ";

    public static String GET_LOG = "Log/Get";
    public static String GET_LOG_PARAMS = "/id=%s";

    public static String GET_REFRESH_TOKEN = "RefreshToken/Get";

    public static String GET_CUSTOM_SYNC = "CustomSync";

    public static String GET_DASHBOARD = "Dashboard/GetDashboards";
    public static String GET_DASHBOARD_PARAMS = "?jsonParameters=%s";

    public static String GET_KEYWORD_DETAILS_DATA= "DataSource/GetKeywordDetailDataForInstanceIds";
    public static String GET_KEYWORD_DETAILS_DATA_PARAMS = "?keyword=%s";
    public static String GET_KEYWORD_DETAILS_DATA_BODY_PARAMS = "{\"ModuleID\":\"%s\",\"RequestParameters\":{\"PID\":%s,\"ParentID\":%s,\"PermissionModuleID\":\"%s\",\"Context\":\"RISKREG\",\"ParentModuleID\":\"\",\"LinkedModuleModuleID\":\"\"},\"CustomModuleFilter\":\"\",\"ControlName\":\"Identifier\",\"ContainerName\":\"OtherFields.Right\",\"LastSynchedDetails\":\"\",\"PageSize\":1000,\"IsEmbeddedForm\":false,\"ExpectedEncryptedIdList\":[\"OQA3ADYA\",\"MQAwADUANwA=\",\"MwA5ADUAMgA=\",\"NAAxADUA\",\"NwA1ADkA\",\"NwAyADYANwA=\",\"NwA1ADUAMwA=\",\"MwA4ADEA\",\"NwA1ADYAOAA=\",\"NAAxADEA\",\"NwA1ADUAMgA=\",\"NAAxADgAMwA=\",\"NAA0ADgA\",\"NAA0ADkA\",\"MQA=\",\"NAAyADkANwA=\",\"MwA5ADgANQA=\",\"MwA5ADgANgA=\",\"MwA5ADgANwA=\",\"MwA5ADgAOAA=\",\"MwA5ADgAOQA=\",\"OAA5ADIA\",\"NwA1ADYAOQA=\",\"NQAwADcA\",\"OAA5ADEA\",\"MgAzADEAMQA=\",\"MgA2ADAANwA=\",\"MgAzADEAMgA=\",\"NwA1ADEAOAA=\",\"MgAwADAAMgA=\",\"MgAzADEAMwA=\",\"NAAzADEAMAA=\",\"NAAzADEANQA=\",\"NwA0ADEAMAA=\",\"NgA5ADgAOAA=\",\"NwA1ADMAMgA=\",\"NwA1ADQAMwA=\",\"NwA1ADQANAA=\",\"NwA0ADQAOAA=\",\"NwA1ADQANQA=\",\"NwA1ADMAMQA=\",\"MgA3ADYAOQA=\",\"NwA1ADQAMgA=\",\"NwA1ADIAOQA=\",\"NQAzADcAMgA=\",\"NgA4ADUANQA=\",\"NwA1ADQAOAA=\",\"NQA3ADUA\",\"MwAxADcAMgA=\",\"MgAwADAAMQA=\",\"NQAyADAA\",\"MwA4ADEAOQA=\",\"NwAwADIAMgA=\",\"MwA4ADYANgA=\",\"MgA2ADYAMAA=\",\"MwA4ADMA\",\"MwA4ADQA\",\"NAAyADcAOQA=\",\"NwA1ADYAMQA=\",\"NgA2ADUAMwA=\",\"NgA2ADcAMQA=\",\"NgA2ADcAMgA=\",\"NgA2ADUAMAA=\",\"NgA2ADYAOQA=\",\"NgA2ADYANwA=\",\"NgA2ADUAMgA=\",\"NgA2ADUAMQA=\",\"NgA2ADcAMAA=\",\"NgA2ADQAOQA=\",\"NgA2ADQAOAA=\",\"NgA2ADYAOAA=\",\"NgA5ADcANgA=\",\"NgA5ADcANwA=\",\"NQA0ADAAMwA=\",\"NQA0ADAANAA=\",\"NQA0ADIAMwA=\",\"NQA0ADIANAA=\",\"NgA5ADcAOQA=\",\"NgA5ADgAMAA=\",\"NQA0ADMAMAA=\",\"NQA0ADMAMQA=\",\"NgA3ADIAOAA=\",\"NgA3ADIAOQA=\",\"NgA0ADMAOQA=\",\"NgA0ADQAMAA=\",\"NgA3ADMANwA=\",\"NgA3ADMAOAA=\",\"NgA0ADUAMgA=\",\"NgA0ADUAMwA=\",\"NgA3ADQANQA=\",\"NgA3ADQANgA=\",\"NgA3ADUAMwA=\",\"NgA3ADUANAA=\",\"NgA0ADgAMAA=\",\"NgA0ADgAMQA=\",\"NgA3ADYAMQA=\",\"NgA3ADYAMgA=\",\"NgA3ADYAOQA=\",\"NgA3ADcAMAA=\",\"NgA0ADgANwA=\",\"NgA0ADgAOAA=\",\"NgA3ADgAMQA=\",\"NgA3ADgAMgA=\",\"NgA4ADAAOAA=\",\"NgA4ADAAOQA=\",\"NgA4ADIAMAA=\",\"NgA4ADIAMQA=\",\"NgA1ADAAMAA=\",\"NgA1ADAAMQA=\",\"NgA4ADMAMAA=\",\"NgA4ADMAMQA=\",\"NgA1ADAAOQA=\",\"NgA1ADEAMAA=\",\"NgA4ADMAOAA=\",\"NgA4ADMAOQA=\",\"NgA1ADEAOAA=\",\"NgA1ADEAOQA=\",\"NgA4ADcANAA=\",\"NgA4ADcANQA=\",\"NgA4ADgAMQA=\",\"NgA4ADgAMgA=\",\"NgA1ADMANQA=\",\"NgA1ADMANgA=\",\"NgA1ADQANgA=\",\"NgA1ADQANwA=\",\"NgA4ADgANgA=\",\"NgA4ADgANwA=\",\"NgA1ADUANAA=\",\"NgA1ADUANQA=\",\"NgA4ADkAMQA=\",\"NgA4ADkAMgA=\",\"NgA1ADYAMwA=\",\"NgA1ADYANAA=\",\"NgA1ADgANQA=\",\"NgA1ADgANgA=\",\"NgA4ADkANwA=\",\"NgA4ADkAOAA=\",\"NgA2ADAAMwA=\",\"NgA2ADAANAA=\",\"NgA2ADEAMQA=\",\"NgA2ADEAMgA=\",\"NgA2ADMAMwA=\",\"NgA2ADMANAA=\",\"NgA5ADEAOAA=\",\"NgA5ADEAOQA=\",\"NgA5ADMAMAA=\",\"NgA5ADMAMQA=\",\"NgA5ADMANAA=\",\"NgA5ADMANQA=\",\"NgA5ADMAOQA=\",\"NgA5ADQAMAA=\",\"NgA5ADUAMgA=\",\"NgA5ADUAMwA=\",\"NQAzADYANgA=\",\"NQAzADYANwA=\",\"NQAzADcANwA=\",\"NQAzADcAOAA=\",\"NgA5ADUAOQA=\",\"NgA5ADYAMAA=\",\"NQAzADgANwA=\",\"NQAzADgAOAA=\",\"NgA5ADYANAA=\",\"NgA5ADYANQA=\",\"NQAzADkANQA=\",\"NQAzADkANgA=\",\"NgA5ADcAMQA=\",\"NgA5ADcAMgA=\",\"NQA0ADEAMAA=\",\"NgA2ADEAOQA=\",\"NgA1ADcAMAA=\",\"NgA0ADUAMQA=\",\"NgA0ADUANgA=\",\"NAAyADcANAA=\",\"NgA4ADEAMQA=\",\"NgA4ADIAMwA=\",\"NgA4ADIAOQA=\",\"NgA4ADMANwA=\",\"NgA4ADUANAA=\",\"NgA4ADcANgA=\",\"NgA4ADgANAA=\",\"NgA1ADkAMgA=\",\"NgA1ADkAOAA=\",\"NgA5ADIAMQA=\",\"NAAyADEAMgA=\",\"NgA5ADYAMQA=\",\"NgA5ADYANwA=\",\"NAAyADIANQA=\",\"NAAyADIAOQA=\",\"NgA5ADcAMAA=\",\"NAAyADQAMwA=\",\"NAAyADQANQA=\",\"NgA3ADEAMAA=\",\"NgA3ADEAMQA=\",\"NgA3ADEAMgA=\",\"NgA0ADYAMQA=\",\"MwAwADgAMwA=\",\"NgA0ADcAMAA=\",\"NgA0ADcAMQA=\",\"NgA0ADcAMgA=\",\"NgA0ADcAMwA=\",\"NgA0ADcANAA=\",\"NgA0ADcANQA=\",\"NgA0ADYAMgA=\",\"NgA0ADYAMwA=\",\"NgA0ADYANAA=\",\"NgA0ADYANQA=\",\"NgA0ADYANgA=\",\"NgA0ADYANwA=\",\"NgA0ADYAOAA=\",\"NgA0ADYAOQA=\",\"NgA2ADQAMgA=\",\"NgA2ADQAMwA=\",\"NgA2ADQANAA=\",\"NgA2ADQANQA=\",\"NgA2ADQANgA=\",\"NgA2ADQANwA=\",\"NwA0ADEANgA=\",\"NwAwADcANAA=\",\"NwAyADAANQA=\",\"NwAyADMAOAA=\",\"NwAyADUAOAA=\",\"NwAzADIANwA=\",\"NwAzADIAMwA=\",\"NwA0ADEANAA=\",\"NwAzADYANQA=\",\"NwA0ADAAMgA=\",\"NwAzADgANgA=\",\"NgA4ADUANwA=\",\"NgA4ADUAOAA=\",\"NwA0ADEAMgA=\",\"NwAwADAANQA=\",\"NwAyADkAMQA=\",\"NgA4ADQANwA=\",\"NwAwADAAOQA=\",\"NgA5ADkANgA=\",\"NwAzADEANwA=\",\"NwAzADQAOQA=\",\"NgA5ADkANwA=\",\"NwAyADEAOQA=\",\"NwAyADQANAA=\",\"NgA5ADkAOQA=\",\"NwAzADEAMAA=\",\"NwAyADQAMwA=\",\"NwAzADkAMAA=\",\"NwAxADcANgA=\",\"NwAyADEAMwA=\",\"NwAyADEAMQA=\",\"NgA5ADIANgA=\",\"NwAwADAAMAA=\",\"NwAxADkANQA=\",\"NwAzADUANwA=\",\"NwAxADcAOQA=\",\"NwAxADYAOQA=\",\"NwA0ADIANwA=\",\"NwAwADAAMQA=\",\"NwAwADMAOQA=\",\"NwAwADQAMQA=\",\"NwAzADcAMAA=\",\"NwAwADMAMQA=\",\"NwAwADIAMQA=\",\"NwAzADIAOQA=\",\"NwAxADgAOQA=\",\"NwAzADIAOAA=\",\"NwAzADAAOQA=\",\"NwAwADEANwA=\",\"NwAzADUAMAA=\",\"NwAzADQANAA=\",\"NwAzADcAMQA=\",\"NwAyADkAMgA=\",\"NwAxADYANwA=\",\"NwAwADMANwA=\",\"NwAzADEAOQA=\",\"NwAyADUANAA=\",\"NwA0ADMAMgA=\",\"NwAwADEANgA=\",\"NwA0ADAAOQA=\",\"NwAyADYAMQA=\",\"NwAzADUANAA=\",\"NwA0ADIAMgA=\",\"NgA4ADQAOAA=\",\"NgA5ADIAOAA=\",\"NwAyADcANgA=\",\"NwAzADQANwA=\",\"NwAzADYAOAA=\",\"NwA0ADAAMwA=\",\"NwAxADkANgA=\",\"NwAyADcANAA=\",\"NwAyADIAMQA=\",\"NwAyADkAOAA=\",\"NwAzADMAMwA=\",\"NwA0ADMANAA=\",\"NwAwADQAMAA=\",\"NwAxADgAMwA=\",\"NwAyADMANwA=\",\"NwAzADAANAA=\",\"NwAxADkAMAA=\",\"NwAxADgAMgA=\",\"NwAxADkAOAA=\",\"NwAzADAAMwA=\",\"NwAxADgAOAA=\",\"NwAyADgAOAA=\",\"NwAyADkANwA=\",\"NwA0ADEANQA=\",\"NwAzADIANAA=\",\"NgA3ADcANAA=\",\"NwAzADcANQA=\",\"NwAxADcAMwA=\",\"NwAwADAAMgA=\",\"NwAzADgAMQA=\",\"NwAzADgAOAA=\",\"NwAyADIAOQA=\",\"NgA3ADcANQA=\",\"NwAzADYAMQA=\",\"NwAyADEANgA=\",\"NwAzADgAMAA=\",\"NwAyADMAMAA=\",\"NwAyADIAMAA=\",\"NgA5ADQANAA=\",\"NwAxADkAMQA=\",\"NwAxADkANwA=\",\"NwAwADQANgA=\",\"NgA5ADIANwA=\",\"NwAyADkANQA=\",\"NwA0ADAANgA=\",\"NwA0ADAAOAA=\",\"NwAzADYAMAA=\",\"NwAwADAAOAA=\",\"NwAwADUANQA=\",\"NwAyADcANwA=\",\"NwAzADcAMgA=\",\"NwAyADgAMwA=\",\"NwAzADAANgA=\",\"NwAzADEANgA=\",\"NwA0ADIANAA=\",\"NwAzADAAOAA=\",\"NwAyADAANwA=\",\"NgA3ADEANwA=\",\"NgA5ADgAOQA=\",\"NgA5ADkANAA=\",\"NwAyADIANwA=\",\"NwA0ADAANwA=\",\"NwAwADIANQA=\",\"NgA5ADUAMAA=\",\"NwAyADMAMQA=\",\"NwAzADUANgA=\",\"NwAyADAAMQA=\",\"NwAwADUAMgA=\",\"NwAyADkANgA=\",\"NwAxADgAMAA=\",\"NwAwADcANQA=\",\"NgA5ADkAMQA=\",\"NwAxADcAOAA=\",\"NwAzADEAMQA=\",\"NwAzADMAOAA=\",\"NwAzADkANgA=\",\"NwAzADQAMwA=\",\"NwAzADcAOAA=\",\"NgA4ADYAMAA=\",\"NwAwADIANgA=\",\"NwAwADIANwA=\",\"NwAzADgAOQA=\",\"NwAyADIAMwA=\",\"NgA5ADQANwA=\",\"NwAyADMAMwA=\",\"NwAzADAAMQA=\",\"NwAyADMANAA=\",\"NwAyADgAOQA=\",\"NgA5ADkAMAA=\",\"NwAyADUAOQA=\",\"NwAzADAAMAA=\",\"NwAyADYAMgA=\",\"NwA0ADAAMQA=\",\"NwAyADEANwA=\",\"NwAyADIANQA=\",\"NwAzADIAMAA=\",\"NwAwADcAOAA=\",\"NgA4ADUAOQA=\",\"NwA0ADIANQA=\",\"NwA0ADIAMwA=\",\"NwAwADMAMgA=\",\"NwAxADcAMgA=\",\"NwAwADMAMAA=\",\"NwA0ADAAMAA=\",\"NwAwADQANwA=\",\"NgA3ADcANgA=\",\"NwAzADQAOAA=\",\"NwAzADQAMAA=\",\"NwAwADMANAA=\",\"NwAzADkAOAA=\",\"NwA0ADIAMQA=\",\"NwAyADEAMgA=\",\"NwAyADEANAA=\",\"NwAyADgAMQA=\",\"NwAzADcAMwA=\",\"NwAzADUANQA=\",\"NwAyADQAMAA=\",\"NwAzADkANQA=\",\"NwAxADgANAA=\",\"NwAzADEANQA=\",\"NwAzADgAMwA=\",\"NwAyADkAMwA=\",\"NwAwADAAMwA=\",\"NwAxADkAMgA=\",\"NwAzADcANAA=\",\"NwAwADUANAA=\",\"NwAyADUANgA=\",\"NwAyADgAMAA=\",\"NwAyADIAMgA=\",\"NwAyADMAOQA=\",\"NwAwADcANgA=\",\"NwAzADQAMQA=\",\"NwAxADYAOAA=\",\"NwAzADcAOQA=\",\"NwA0ADIAMAA=\",\"NwAzADMAMQA=\",\"NwAxADkANAA=\",\"NgA4ADQAMQA=\",\"NgA5ADkAMgA=\",\"NwAxADcANwA=\",\"NwAyADgANQA=\",\"NwAzADEAMwA=\",\"NwAwADMAOAA=\",\"NwAyADcAOQA=\",\"NwAyADYAMAA=\",\"NwAzADMANwA=\",\"NwAyADIANAA=\",\"NwAyADMANgA=\",\"NwAzADUAMwA=\",\"NwAzADYANAA=\",\"NwAzADYANgA=\",\"NwAyADkAOQA=\",\"NwAxADgANwA=\",\"NgA4ADQANgA=\",\"NwA0ADMAMQA=\",\"NwAzADUAMgA=\",\"NwAwADQAOAA=\",\"NwAyADgANAA=\",\"NwAzADYAMgA=\",\"NwAzADcANwA=\",\"NwAzADAANQA=\",\"NwAyADMANQA=\",\"NgA5ADIANQA=\",\"NwAyADkAMAA=\",\"NwAwADUANwA=\",\"NwAxADcANAA=\",\"NwAwADEANQA=\",\"NwAwADQAMwA=\",\"NwAzADUAOAA=\",\"NwA0ADAANQA=\",\"NwAyADcANQA=\",\"NwAxADgANQA=\",\"NwAyADUANQA=\",\"NgA4ADQAMwA=\",\"NwAwADcAOQA=\",\"NwAwADAANwA=\",\"NwAyADIAOAA=\",\"NwAzADUAOQA=\",\"NwAzADkANAA=\",\"NwA0ADMAMAA=\",\"NwA0ADAANAA=\",\"NgA4ADQANQA=\",\"NgA3ADEAOAA=\",\"NwAzADYAMwA=\",\"NwAyADAANgA=\",\"NwAzADEAOAA=\",\"NwAyADgANwA=\",\"NwAzADQANQA=\",\"NwA0ADEAMwA=\",\"NwAzADYAOQA=\",\"NgA3ADcAMwA=\",\"NwAzADgANwA=\",\"NwAzADcANgA=\",\"NwAwADQANQA=\",\"NgA4ADQANAA=\",\"NgA5ADQANQA=\",\"NwAzADQAMgA=\",\"NwAwADEAOAA=\",\"NwAzADgANAA=\",\"NwAwADEAOQA=\",\"NwAzADMANAA=\",\"NwAyADIANgA=\",\"NwAzADkAMgA=\",\"NwAxADgAMQA=\",\"NwAwADUAOAA=\",\"NwAwADUAMwA=\",\"NwAyADgANgA=\",\"NwAyADkANAA=\",\"NwAyADAANAA=\",\"NwAwADUAMQA=\",\"NwAzADkAOQA=\",\"NwAyADQAMgA=\",\"NwAwADIAMAA=\",\"NwAzADMAOQA=\",\"NgA5ADQAOQA=\",\"NwAzADkAMwA=\",\"NwAyADQAMQA=\",\"NwAzADIAMQA=\",\"NwAxADkAMwA=\",\"NgA5ADkAMwA=\",\"NwAzADYANwA=\",\"NwAzADQANgA=\",\"NwAyADAAMwA=\",\"NwAyADUANwA=\",\"NwAzADUAMQA=\",\"NwAzADIANgA=\",\"NwAzADAANwA=\",\"NwAyADEAMAA=\",\"NwAzADEAMgA=\",\"NwAzADMANgA=\",\"NwAzADgAMgA=\",\"NwAyADEAOAA=\",\"NwAwADAANgA=\",\"NwAzADkAMQA=\",\"NgA4ADQAMgA=\",\"NwAwADMANQA=\",\"NwAyADcAOAA=\",\"NwAzADkANwA=\",\"NwAzADMAMgA=\",\"NwAzADAAMgA=\",\"NgA5ADQANgA=\",\"NwA0ADEAMQA=\",\"NwAzADIAMgA=\",\"NwAwADUANgA=\",\"NwAyADgAMgA=\",\"NwAxADcAMAA=\",\"NwAzADIANQA=\",\"NwAwADAANAA=\",\"NgA5ADkANQA=\",\"NwA0ADMAMwA=\",\"NwAwADMAMwA=\",\"NwAzADgANQA=\",\"NwAwADUAMAA=\",\"NgA5ADQAOAA=\",\"NwAzADMANQA=\",\"NwAyADEANQA=\",\"NwAzADEANAA=\",\"NwAzADMAMAA=\",\"NwAxADgANgA=\",\"NwAyADMAMgA=\",\"NwA1ADQANwA=\",\"NwA2ADEAMQA=\",\"NwAyADYAOAA=\",\"NgA5ADQAMwA=\",\"NQA3ADQA\",\"NQA5ADcA\",\"NwA1ADUAMAA=\",\"NwA1ADYAMwA=\",\"NwA1ADUANAA=\",\"NwAyADAAMAA=\",\"MQAwADkANQA=\",\"MQAwADkANgA=\",\"MgAzADMAMgA=\",\"NQA2ADMA\",\"NQA0ADMANQA=\",\"MwAzADUANgA=\",\"MwA3ADEAMAA=\",\"MwA3ADIA\",\"NAAzADEAMwA=\",\"NwA0ADUAMQA=\",\"NQAzADUANgA=\",\"NwA1ADIAMwA=\",\"NwA1ADYANAA=\",\"NwA1ADYANQA=\",\"NwA0ADMANgA=\",\"NwA1ADcANAA=\",\"NgA0ADkANAA=\",\"MwA3ADUA\",\"NAA5ADcA\",\"NwAwADEAMQA=\",\"NwAwADEAMgA=\",\"NwAwADEAMwA=\",\"NwAwADEANAA=\",\"MQA4ADgAMAA=\",\"MQAzADQAOQA=\",\"MwA4ADIA\",\"NgA5ADIANAA=\",\"NgA5ADIAMwA=\",\"NgA5ADIAMgA=\",\"NgA5ADUANgA=\",\"MwA5ADYANgA=\",\"MwA1ADIAMwA=\",\"NQAzADgAMQA=\",\"MwA4ADcA\",\"NgA2ADUA\",\"MQAwADcA\",\"NAAzADAANAA=\",\"NgAwADIA\",\"OAA5ADMA\",\"NQAwADkA\",\"NAAwADQANQA=\",\"NQAxADAA\",\"MwA5ADYAMQA=\",\"MQA0ADkAMQA=\",\"NwA0ADkAMwA=\",\"NAAzADEANAA=\",\"MwA5ADEAOQA=\",\"NgA3ADQAMAA=\",\"NwA1ADMAOAA=\",\"MwA4ADcANgA=\",\"NwAyADYAMwA=\",\"NwA1ADMANQA=\",\"NwA1ADMANgA=\",\"MQAwADUAOAA=\",\"NAAzADMAMwA=\",\"NgA4ADkAMAA=\",\"NgA2ADMANgA=\",\"NAAxADIAMQA=\",\"NAAxADIAMgA=\",\"NwA1ADAAMQA=\",\"NwA1ADQANgA=\",\"NwA0ADkAOAA=\",\"NwA0ADUAMwA=\",\"NwA0ADUANAA=\",\"NwA0ADUANQA=\",\"NwA0ADUANgA=\",\"NwA0ADUANwA=\",\"NwA0ADUAOAA=\",\"NwA0ADUAOQA=\",\"NwA0ADYAMAA=\",\"NwA0ADYAMQA=\",\"NwA0ADYAMgA=\",\"NwA0ADYAMwA=\",\"NwA0ADYANAA=\",\"NwA0ADYANQA=\",\"NwA0ADYANgA=\",\"NwA0ADYANwA=\",\"NwA0ADYAOAA=\",\"NwA0ADYAOQA=\",\"NwA0ADcAMAA=\",\"NwA0ADcAMQA=\",\"NwA0ADcAMgA=\",\"NwA0ADcAMwA=\",\"NwA0ADcANAA=\",\"NwA0ADcANQA=\",\"NwA0ADcANgA=\",\"NwA0ADcANwA=\",\"NwA0ADcAOAA=\",\"NwA0ADcAOQA=\",\"NwA0ADgAMAA=\",\"NwA0ADgAMQA=\",\"NwA0ADgAMgA=\",\"NwA0ADgAMwA=\",\"NwA0ADgANAA=\",\"NwA0ADgANQA=\",\"NwA0ADgANgA=\",\"NwA0ADgANwA=\",\"NwA0ADgAOAA=\",\"NwA0ADgAOQA=\",\"NwA0ADkAMAA=\",\"NwA0ADkAMQA=\",\"NwA0ADkAMgA=\",\"MQA0ADMAOAA=\",\"NgA2ADQAMQA=\",\"MQA4ADgAMQA=\",\"MwA5ADIAMAA=\",\"NwA1ADUANwA=\",\"NAA3ADkA\",\"NAA4ADAA\",\"NAA4ADEA\",\"NAA4ADIA\",\"NgA2ADIANgA=\",\"NgA3ADgANQA=\",\"NgA4ADAAMwA=\",\"NwA1ADcAMgA=\",\"NwAwADQANAA=\",\"MwA4ADUA\",\"MgA2ADIANQA=\",\"MwA3ADYAMgA=\",\"MQA5ADkAOQA=\",\"NwAxADUAMAA=\",\"MwA5ADYAMAA=\",\"NgA5ADkAOAA=\",\"NgA1ADUAOAA=\",\"NwA0ADQAOQA=\",\"MQAwADUANgA=\",\"NAAwADMAOAA=\",\"NQA2ADYA\",\"NwA0ADQAMAA=\",\"MwA5ADMANQA=\",\"NwA1ADQAMAA=\",\"MwA3ADYA\",\"MwA3ADcA\",\"NwA1ADYANgA=\",\"MwA5ADUAMwA=\",\"NAAxADgANQA=\",\"NgA5ADgANAA=\",\"NwAzADYA\",\"NgA3ADIAMwA=\",\"NgA3ADIANAA=\",\"MwA5ADEAMQA=\",\"MwA5ADEAOAA=\",\"NAAxADgANAA=\",\"NgA0ADUANQA=\",\"NwAwADQAMgA=\",\"MwAzADkANAA=\",\"NwA0ADQANAA=\",\"MQAyADQAOAA=\",\"NgA2ADQA\",\"NAAxADUANQA=\",\"MQA1ADYANgA=\",\"MwA4ADUANgA=\",\"NwA1ADAANgA=\",\"NwA2ADEAMAA=\",\"NwA0ADkAOQA=\",\"MgA2ADYAMQA=\",\"MQA4ADcAOQA=\",\"MwA5ADYAMgA=\",\"MwA5ADEANgA=\",\"NAAxADMA\",\"NwA2ADAAMwA=\",\"OQA3ADcA\",\"NwA0ADUAMAA=\",\"NwAwADIANAA=\",\"NgA0ADkAMgA=\",\"MQA4ADgA\",\"NgA2ADMAOAA=\",\"MgA0ADEANQA=\",\"NQAzADkA\",\"NwA1ADUAMQA=\",\"MwAwADQAMgA=\",\"NwA1ADUANQA=\",\"NAAxADUANAA=\",\"NgA1ADQAMAA=\",\"NAAxADUAMwA=\",\"NgA1ADMAOQA=\",\"NwA1ADAAMwA=\",\"NwAwADEA\",\"NAAxADIA\",\"NwA4ADAA\",\"MwA5ADcANgA=\",\"NwA1ADcAMQA=\",\"NAA1ADQA\",\"MwA4ADUANAA=\",\"MwA1ADAAMQA=\",\"MwA1ADQAMQA=\",\"MQA0ADYANAA=\",\"MwA1ADQAMAA=\",\"MwAxADcA\",\"MwA2ADkA\",\"MwA3ADEA\",\"MQA4ADIAMwA=\",\"NwAwADIAMwA=\",\"NwAxADcAMQA=\",\"NwAwADcANwA=\",\"NwA1ADIAMQA=\",\"NwA0ADMAOQA=\",\"MwA1ADkAMAA=\",\"NAAwADUAMwA=\",\"NQAyADQA\",\"NAAxADkAMgA=\",\"NwAyADcAMQA=\",\"NAAxADkAMwA=\",\"NwAwADEAMAA=\",\"NAAxADIAMAA=\",\"NAAzADEANgA=\",\"MQAwADAAMQA=\",\"MQAyADEAMAA=\",\"NQAyADMA\",\"MQA5ADkAOAA=\",\"MwA5ADEANAA=\",\"MwA5ADEAMwA=\",\"MQAwADcANwA=\",\"NAAzADMANQA=\",\"NAAzADMANwA=\",\"NAAzADMAOQA=\",\"NAAzADQAMQA=\",\"NAAzADQAMwA=\",\"NAAzADQANQA=\",\"NAAzADQAOQA=\",\"NAAzADUAMQA=\",\"NQAzADUAMQA=\",\"NQAzADUAMwA=\",\"NAAyADMAMgA=\",\"NAAyADMANAA=\",\"NAAyADQAMAA=\",\"NAAyADQAMgA=\",\"NAAyADIANgA=\",\"NAAyADIAOAA=\",\"NAAyADcAMQA=\",\"NAAyADcAMwA=\",\"NAAyADAAOQA=\",\"NAAyADEAMQA=\",\"NAAyADEANQA=\",\"NAAyADEANwA=\",\"NAAyADIAMQA=\",\"NAAyADIAMwA=\",\"NAAyADQANgA=\",\"NAAyADQAOAA=\",\"NAAyADUAMgA=\",\"NAAyADUANAA=\",\"NAAyADUAOAA=\",\"NAAyADYAMAA=\",\"NAAyADYANAA=\",\"NAAyADYANgA=\",\"NQAzADcAMwA=\",\"NQAzADcANQA=\",\"NQAzADgAMwA=\",\"NQAzADgANQA=\",\"NQAzADUANwA=\",\"NQAzADUAOQA=\",\"NQAzADYAMgA=\",\"NQAzADYANAA=\",\"NQA0ADEAMQA=\",\"NQA0ADEAMgA=\",\"NQA0ADEAMwA=\",\"NQA0ADIAMQA=\",\"NQA0ADAAOAA=\",\"NQA0ADAAOQA=\",\"NQAzADkAOQA=\",\"NQA0ADAAMQA=\",\"NQAzADkAMQA=\",\"NQAzADkAMwA=\",\"NgA0ADMANQA=\",\"NgA0ADMANwA=\",\"NQA0ADIANgA=\",\"NQA0ADIAOAA=\",\"NgA0ADUANAA=\",\"NgA0ADQAOAA=\",\"NgA0ADUAMAA=\",\"NgA0ADcANgA=\",\"NgA0ADcANwA=\",\"NgA0ADcAOAA=\",\"NgA1ADMAMQA=\",\"NgA1ADMAMwA=\",\"NgA1ADIAMwA=\",\"NgA1ADIANQA=\",\"NgA1ADAANQA=\",\"NgA1ADAANwA=\",\"NgA1ADEANAA=\",\"NgA1ADEANgA=\",\"NgA0ADkANQA=\",\"NgA0ADkANgA=\",\"NgA0ADkAOAA=\",\"NgA0ADgAMwA=\",\"NgA0ADgANQA=\",\"NgA1ADQAMgA=\",\"NgA1ADQANAA=\",\"NgA1ADUAMAA=\",\"NgA1ADUAMgA=\",\"NgA1ADUAOQA=\",\"NgA1ADYAMQA=\",\"NgA1ADYAOAA=\",\"NgA1ADYAOQA=\",\"NgA1ADkAOQA=\",\"NgA2ADAAMQA=\",\"NgA1ADkAMwA=\",\"NgA1ADkANQA=\",\"NgA2ADAANwA=\",\"NgA2ADAAOQA=\",\"NgA1ADcAMQA=\",\"NgA1ADcAMgA=\",\"NgA1ADcANAA=\",\"NgA1ADcANgA=\",\"NgA1ADcANwA=\",\"NgA1ADgANAA=\",\"NgA1ADgAOQA=\",\"NgA1ADkAMQA=\",\"NgA2ADMAMgA=\",\"NgA2ADIAMAA=\",\"NgA2ADIAMQA=\",\"NgA2ADIAMwA=\",\"NgA2ADIANAA=\",\"NgA2ADIANQA=\",\"NgA2ADEANgA=\",\"NgA2ADEANwA=\",\"NgA2ADUANAA=\",\"NgA2ADUANgA=\",\"NgA3ADEAOQA=\",\"NgA3ADIAMQA=\",\"NgA3ADEAMwA=\",\"NgA3ADEANQA=\",\"NgA2ADcAMwA=\",\"NgA2ADcANQA=\",\"NgA2ADcANwA=\",\"NgA2ADcAOQA=\",\"NgA2ADgAMQA=\",\"NgA2ADgAMgA=\",\"NgA2ADgAMwA=\",\"NgA2ADgANQA=\",\"NgA2ADgANwA=\",\"NgA2ADgAOQA=\",\"NgA2ADkAMQA=\",\"NgA2ADkAMwA=\",\"NgA2ADkANQA=\",\"NgA2ADkANwA=\",\"NgA2ADkAOQA=\",\"NgA3ADAAMAA=\",\"NgA3ADAAMgA=\",\"NgA3ADIANQA=\",\"NgA3ADIANwA=\",\"NgA3ADMAMgA=\",\"NgA3ADMANAA=\",\"NgA3ADQAMQA=\",\"NgA3ADQAMwA=\",\"NgA3ADQAOQA=\",\"NgA3ADUAMQA=\",\"NgA3ADAANQA=\",\"NgA3ADAANwA=\",\"NgA3ADcANwA=\",\"NgA3ADcAOQA=\",\"NgA3ADYANQA=\",\"NgA3ADYANwA=\",\"NgA3ADUANwA=\",\"NgA3ADUAOQA=\",\"NgA4ADAANAA=\",\"NgA4ADAANgA=\",\"NgA4ADIANAA=\",\"NgA4ADIANgA=\",\"NgA4ADEANgA=\",\"NgA4ADEAOAA=\",\"NgA4ADkAMwA=\",\"NgA4ADkANQA=\",\"NgA5ADAAMgA=\",\"NgA5ADAANAA=\",\"NgA5ADEAMwA=\",\"NgA5ADEANAA=\",\"NgA5ADEANgA=\",\"NgA4ADQAOQA=\",\"NgA4ADUAMQA=\",\"NgA4ADMAMgA=\",\"NgA4ADMANAA=\",\"NgA4ADYAMQA=\",\"NgA4ADYAMwA=\",\"NgA4ADYANQA=\",\"NgA4ADYANwA=\",\"NgA4ADYAOQA=\",\"NgA4ADcAMQA=\",\"NgA4ADcANwA=\",\"NgA4ADcAOQA=\",\"NwA1ADMANAA=\",\"NgA0ADgAMgA=\",\"NwA1ADIANQA=\",\"NgA2ADMAOQA=\",\"MQAxADUAMwA=\",\"MwA3ADAA\",\"MQA1ADQANwA=\",\"NAAzADQAOAA=\",\"NwA1ADAAMgA=\",\"NwAyADcAMwA=\",\"NwA1ADAANQA=\",\"MwA3ADMA\",\"MQAwADkANwA=\",\"MQAxADEANgA=\",\"NQAyADIA\",\"NQA2ADUA\",\"MQAyADIAMAA=\",\"NAAzADMA\",\"MwAwADMAOQA=\",\"NgA1ADIAOQA=\",\"NwAwADcAMgA=\",\"MQA1ADgANgA=\",\"NwA0ADQAMwA=\",\"MQA1ADgANQA=\",\"NAAxADQA\",\"NQAxADIA\",\"MwA3AA==\",\"MwAwADMAOAA=\",\"MwA1ADEAMQA=\",\"MwA2ADEANQA=\",\"MQAxADUANAA=\",\"MQA5ADUAOQA=\",\"MQAyADAAOQA=\",\"MQA0ADYAOAA=\",\"MQA0ADYAOQA=\",\"MQA0ADcAMQA=\",\"MgA1ADEA\",\"NAAwADMA\",\"NAAzADIA\",\"NAA1ADAA\",\"NAA1ADEA\",\"NAA1ADIA\",\"NAA1ADMA\",\"MQA0ADMANQA=\",\"MQA0ADMANwA=\",\"MQA0ADQANAA=\",\"MQA0ADQANQA=\",\"NAAwADQA\",\"MQA0ADQANwA=\",\"MQA0ADQAOAA=\",\"MQA0ADUAOQA=\",\"MQA0ADYAMwA=\",\"MQA0ADYANQA=\",\"MQA0ADYANgA=\",\"MQA0ADYANwA=\",\"MQA0ADcAMAA=\",\"NAAwADUA\",\"NAAwADYA\",\"NAAwADcA\",\"NAAwADgA\",\"NAAwADkA\",\"NAAxADAA\",\"NAAxADYA\",\"NwA2ADAANAA=\",\"NwA2ADAANQA=\",\"MQA5ADcAOQA=\",\"NQAzADgAMgA=\",\"NgA1ADIAMgA=\",\"NQAyADYA\",\"MQA5ADMANwA=\",\"MwA1ADUAOQA=\"]}";

    public static String FETCH_DATA = "DataSource/FetchData";
    public static String FETCH_DATA_BODY_PARAMS = "{\"ModuleID\":\"%s\",\"RequestParameters\":{\"PID\":%s,\"ParentID\":%s,\"PermissionModuleID\":\"%s\",\"Context\":\"%s\",\"ParentModuleID\":\"\"},\"CustomModuleFilter\":\"\",\"ControlName\":\"DDL_CBLDataSource\",\"ContainerName\":\"DDL_CBL\",\"LastSynchedDetails\":\"\",\"PageSize\":1000,\"IsEmbeddedForm\":true}";

    public static String GET_DOCUMENTS_LINKED_RECORD_COUNT = "Document/GetDocumentsLinkedRecordCount";
    public static String GET_DOCUMENTS_LINKED_RECORD_COUNT_PARAMS = "?docIds=%s&folderID=%s";

    public static String ALL_INSTANCES = "Module/GetAllInstanceDetails";
    public static String ALL_INSTANCES_PARAMS = "?moduleId=%s&pid=%s&parentId=%s&lastSyncedAt=%s&getCountOnly=%s&excludeFinalStage=%s&jsonParameters=%s";

    public static String EMBEDDED_INSTANCE = "Module/GetMultipleInstanceDetailsOfEmbeddedControl";
    public static String EMBEDDED_INSTANCE_PARAMS = "?moduleId=%s";

    public static String CREATE_UPDATE_INSTANCE = "Module/CreateOrUpdateInstance";
    public static String CREATE_UPDATE_INSTANCE_PARAMS = "?moduleId=%s&jsonReqParams=%s";

    public static String LINKED_INSTANCE = "LinkedInstance";

    public static String DELETE_INSTANCE = "Module/DeleteInstance";
    public static String DELETE_INSTANCE_PARAMS ="?moduleId=%s&ids=%d";

    public static String CLEAR_CACHE = "APPSetting/ClearCache";

    public static String DELETE_PROJECT ="DeleteProject";
    public static String DELETE_PROJECT_BODY_PARAMS = "=[{'ID':'%s','ParentID':'%s','ProjectID':'%s','ProjectName':'%s','ProjectCode':'%s'}]";

    public static String CHECK_IF_MODIFIED_ITEM ="WorkOrderAssosciations/CheckIfModifiedItem";
    public static String CHECK_FOR_SAME_PROJECT ="WorkOrderAssosciations/CheckForSameProject";
    public static String CHECK_FOR_SAME_PROJECT_PARAMS ="?moduleID=%s&workOrderID=%s&itemIds=%s&containerIds=%s";

    public static String INSTANCE = "Module/GetInstanceDetails";
    public static String INSTANCE_PARAMS = "?moduleId=%s&Id=%s&jsonParameters=%s&lastSyncedAt=%s";

    public static String LIST_PAGE_DATA = "GenericXMLListPageV2/GetListPageData";
    public static String LIST_PAGE_DATA_PARAMS = "{\"PageSize\":%d,\"LastSyncedAt\":\"%s\",\"RequestParams\":{\"pid\":%d,\"parentId\":%d,\"context\":\"%s\"}}";

    public static String LIST_PAGE_DATA_V1 = "GenericXMLListPage";

    public static String WF_DETAILS = "Workflow/WorkflowDetails";
    public static String WF_DETAILS_PARAMS = "?moduleId=%s&jsonParameters=";

    public static String GET_WORKFLOW_HELP = "WorkflowData/GetWorkflowHelp";
    public static String GET_WORKFLOW_HELP_PARAMS = "?workflowGuid=%s&workflowFileId=1";

    public static String GET_WORKFLOW_HISTORY = "workflow/GetWorkflowHistory";
    public static String GET_WORKFLOW_HISTORY_PARAMS = "?moduleId=%s&forminstanceid=%s&workflowInstanceGuid=%s&jsonParameters=";

    public static String GET_WORKFLOW = "Workflow";
    public static String GET_WORKFLOW_PARAMS = "?moduleId=%s&forminstanceid=%s&workflowInstanceGuid=%s&jsonParameters=";

    public static String WF_PERFORM_ACTION = "Workflow/PerformAction";
    public static String WF_PERFORM_ACTION_PARAMS = "?moduleId=%s&actionId=%s&actionName=%s&formInstanceId=%s";
    public static String WF_PERFORM_ACTION_BODY = "{\"pid\":%s,\"parentid\":%s,\"parentmodule\":\"%s\",\"notes\":\"%s\"}";

    public static String WORKFLOWV2_PERFORM_ACTION = "WorkflowV2/PerformAction";
    public static String WORKFLOWV2_PERFORM_ACTION_PARAMS = "?moduleId=%s&actionId=%s&actionName=%s&formInstanceId=%s";
    public static String WORKFLOWV2_PERFORM_ACTION_BODY = "{\"pid\":%s,\"parentid\":%s,\"parentmodule\":\"%s\",\"notes\":\"%s\"}";

    public static String ATTACHMENT_DOCUMENT = "Document/AttachDocuments";
    public static String ATTACHMENT_DOCUMENT_PARAMS = "?moduleID=%s&projectId=%d&contractID=%d&instanceId=%d";

    public static String DOCUMENT_GET_ALL_FOLDERS_RECURSIVELY = "Document/GetAllFoldersRecursively";
    public static String DOCUMENT_GET_ALL_FOLDERS_RECURSIVELY_PARAMS = "?projectId=%s";

    public static String DOCUMENTV2_GET_ALL_FOLDERS_RECURSIVELY = "DocumentV2/GetAllFoldersRecursively";

    public static String GET_ALL_FOLDERS = "DocumentV2/GetAllFolders";
    public static String GET_ALL_FOLDERS_PARAMS = "?projectId=%s&moduleId=%s&parentFolderID=%s&contractID=%s&parentModuleID=%s";

    public static String DOCUMENT_GET_FOLDERS_FOR_PROJECT = "DocumentV2/GetFoldersForProject";
    public static String DOCUMENT_GET_FOLDERS_FOR_PROJECT_PARAMS = "?projectId=%s&parentInstanceId=%s&parentModuleId=%s&lastSyncedFolderId=%s&pageSize=100";

    public static String DOCUMENT_GET_DOCUMENTS_FOR_FOLDER = "Document/GetDocumentsForFolder";
    public static String DOCUMENT_GET_DOCUMENTS_FOR_FOLDER_PARAMS = "?projectId=%s&folderId=%d&lastSyncedAt=%s&pageSize=%d";

    public static String DOCUMENT_GET_DOCUMENTS_FOR_FOLDER_V2 = "DocumentV2/GetDocumentsForFolder";
    public static String DOCUMENT_GET_DOCUMENTS_FOR_FOLDER_PARAMS_V2 = "?projectId=%s&parentId=%s&folderId=%d&lastSyncedAt=%s&pageSize=%d&parentModuleId=%s";

    public static String GET_DOCUMENT_FOR_V2 = "DocumentV2";
    public static String GET_DOCUMENT_PARAMS_FOR_V2 = "?projectID=%s&parentFolderID=%s&kendoParams=null&contractID=%s&parentModuleID=%s";

    public static String UPLOAD = "DocumentV2/Upload";
    public static String UPLOAD_PARAMS = "?folderId=%s&projectId=%s&description=%s&contractID=%s&parentModuleID=%s";

    public static String UPLOAD_DOCUMENT = "DocumentV2/UploadDocument";
    public static String UPLOAD_DOCUMENT_PARAMS = "?parentModuleID=%s&fileId=%s&projectId=%s&contractID=%s&folderId=%d&description=%s";

    public static String GET_ESTIMATED_DOWNLOAD_DATA_V1 = "Document/GetEstimatedDownloadData";
    public static String GET_ESTIMATED_DOWNLOAD_DATA = "DocumentV2/GetEstimatedDownloadData";
    public static String GET_ESTIMATED_DOWNLOAD_DATA_PARAMS = "?userName=%s";
    public static String GET_ESTIMATED_DOWNLOAD_DATA_BODY_PARAMS = "=[{\"PID\":%s,\"ParentInstanceID\":%s,\"ParentModuleID\":\"%s\",\"DeletedFolderIDs\":\"\",\"OfflineFolderIDs\":\"\",\"OnlineFolderIDs\":\"\",\"DeletedDocumentIDs\":\"\",\"DocumentIDsMarkedOnline\":\"\",\"DocumentIDsMarkedOffline\":\"\",\"DownloadedDocuments\":[{\"DocId\":%s,\"LinkID\":%s,\"VersionNumber\":1}],\"DownloadedAnnotations\":[]}]";


    public static String CREATE_UPDATE_DELETE_DOCUMENT = "DocumentV2/CreateUpdateDeleteDocumentDetails";
    public static String CREATE_UPDATE_DELETE_FOLDER_V2 = "DocumentV2/CreateUpdateDeleteFolderDetails";
    public static String CREATE_UPDATE_DELETE_FOLDER = "Document/CreateUpdateDeleteFolderDetails";

    public static String GET_ANNOTATION = "Annotation";
    public static String GET_ANNOTATION_FOR_DOCIDS = "Annotation/GetAnnotationsForDocIds";
    public static String GET_ANNOTATION_FOR_DOCIDS_PARAMS ="?commaSeparatedDocIds=%s";
    public static String GET_ANNOTATION_PARAMS = "?commaSeparatedAnnotationInstanceIds=%s";
    public static String CREATE_ANNOTATION = "Annotation/Post";
    public static String DELETE_ANNOTATION = "Annotation/Delete";

    public static String GET_ANNOTATION_FOR_DOCUMENT_LIST = "Annotation/GetAnnotationIdsForDocumentList";
    public static String GET_ANNOTATION_FOR_DOCUMENT_LIST_BODY_PARAMS ="=[{\"DocId\":%s,\"LinkId\":%s,\"VersionNo\":1,\"Annotations\":[]}]";

    public static String GET_ANNOTATIONS = "Page/GetAnnotations";
    public static String GET_ANNOTATIONS_BODY_PARAM = "{\"documentId\":\"%s\",\"pageNumber\":0,\"createEmpty\":true,\"userData\":null}";

    public static String LOAD_FROM_URI = "Factory/LoadFromUri";
    public static String LOAD_FROM_URI_BODY_PARAM = "{\"uri\":\"\",\"resolution\":0,\"options\":{\"ltldlltldl$lpxel_0x4ef_\":[0,7,8,2,4,5,1,6,3],\"documentId\":\"%s\",\"resolution\":0,\"name\":null,\"password\":null,\"annotationsUri\":\"\",\"loadEmbeddedAnnotations\":false,\"renderAnnotations\":false,\"loadAttachmentsMode\":0,\"maximumImagePixelSize\":4096,\"firstPageNumber\":0,\"lastPageNumber\":0,\"loadMode\":0,\"redactionOptions\":null,\"timeoutMilliseconds\":0,\"annotationid\":\"0\",\"versionNo\":\"1\"},\"userData\":null}";

    public static String GET_IMAGE = "Page/GetImage";
    public static String GET_IMAGE_PARAM = "?documentId=%s&pageNumber=1&userData=null";

    public static String VALID_INSTANCE_IDS= "DataSource/ValidInstanceIDs";
    public static String VALID_INSTANCE_IDS_PARAM = "{\"ModuleID\":\"XF00161\",\"RequestParameters\":{\"PID\":%s,\"ParentID\":%s,\"PermissionModuleID\":\"%s\",\"Context\":\"XF00157\",\"ParentModuleID\":\"\"},\"CustomModuleFilter\":\"\",\"ControlName\":\"DDL_RBLDataSource\",\"ContainerName\":\"DDL_RBL\",\"LastSynchedDetails\":null,\"PageSize\":0,\"IsEmbeddedForm\":true}";

    public static String GET_ACTIONS = "Workflow/GetActions";
    public static String GET_ACTIONS_PARAM ="?moduleId=%s&forminstanceid=%s&jsonParameters=%s";

    public static String GET_EMBEDDED_FORM_TEMPLATE = "Module/GetEmbeddedFormTemplate";
    public static String GET_EMBEDDED_FORM_TEMPLATE_PARAMS ="?moduleId=%s";
    public static String GET_EMBEDDED_FORM_TEMPLATE_BODY_PARAMS ="= {\"context\":'%s',\"PID\":%s,\"ParentID\":%s,\"CustomModuleFilter\":%s}";

    public static String GET_EMBEDDED_FORM_MODULE_DETAILS = "Module/GetEmbeddedFormModuleDetails";
    public static String GET_EMBEDDED_FORM_MODULE_DETAILS_PARAMS ="?moduleId=%s&moduleId=%s";
    public static String GET_EMBEDDED_FORM_MODULE_DETAILS_BODY_PARAMS ="={\"PID\":%s,\"ParentID\":%s,\"PermissionModuleID\":\"%s\",\"Context\":\"%s\",\"ParentModuleID\":\"\"}";

    public static String V3_UPLOAD_DOCUMENT = "DocumentV3/UploadDocument";
    public static String V3_UPLOAD_DOCUMENT_PARAMS = "?parentModuleID=%s&fileId=%s&projectId=%s&contractID=%s&folderId=%d&workflowGuid=%s&description=%s";

    public static String GET_SECURITY = "Security";
    public static String GET_SECURITY_PARAMS = "?roleName=%s";

    public static String GET_TERMINOLOGY = "Terminology/Get";

    public static String GET_RENDER_SERVICE = "RenderService/GetProjectDocuments";
    public static String GET_RENDER_SERVICE_PARAMS = "?instanceId=%s&moduleId=%s&projectId=%s";

    public static String POST_RENDER_SERVICE = "RenderService";

    public static String GET_PROJECTS = "RenderService/GetProjects";

    public static String GET_DOC_FOLDER_STRUCTURE_LIST = "AssociateDocumentFolder/GetDocumentFolderStructureList";

    public static String GET_ALL_FORM_PERMISSIONS_BY_ROLE = "RolesAndPermissions/FetchAllFormPermissionsByRole";
    public static String GET_ALL_FORM_PERMISSIONS_BY_ROLE_PARAMS = "?roleId=%s";

    public static String SET_FORM_PERMISSIONS_BY_ROLE = "RolesAndPermissions/SaveFormPermissionsByRole";
    public static String SET_FORM_PERMISSIONS_BY_ROLE_PARAMS = "?roleId=%s";

    public static String GET_ALL_ROLE_PERMISSIONS_BY_FORM = "RolesAndPermissions/FetchAllRolePermissionsByForm";
    public static String GET_ALL_ROLE_PERMISSIONS_BY_FORM_PARAMS = "?moduleId=%s";

    public static String SET_ROLE_PERMISSIONS_BY_FORM = "RolesAndPermissions/SaveRolePermissionsByForm";
    public static String SET_ROLE_PERMISSIONS_BY_FORM_PARAMS = "?moduleId=%s";

    public static String GET_ALL_REPORT_PERMISSIONS_BY_FORM = "RolesAndPermissions/FetchAllReportPermissionsByForm";
    public static String GET_ALL_REPORT_PERMISSIONS_BY_FORM_PARAMS = "?moduleId=%s&moduleText=%s";

    public static String SET_REPORT_PERMISSIONS_BY_FORM = "RolesAndPermissions/SaveReportPermissionsByForm";
    public static String SET_REPORT_PERMISSIONS_BY_FORM_PARAMS = "?moduleId=%s";

    public static String GET_ALL_REPORT_PERMISSIONS_BY_ROLE = "RolesAndPermissions/FetchAllReportPermissionsByRole";
    public static String GET_ALL_REPORT_PERMISSIONS_BY_ROLE_PARAMS = "?roleId=%s";

    public static String SET_REPORT_PERMISSIONS_BY_ROLE = "RolesAndPermissions/SaveReportPermissionsByRole";
    public static String SET_REPORT_PERMISSIONS_BY_ROLE_PARAMS = "?roleId=%s";

    public static String IS_USER_IN_ROLES = "WorkflowResource/IsUserInRoles";
    public static String IS_USER_IN_ROLES_PARAMS = "?pid=%s&uid=%s&role=%s";

    public static String COMMON = "Common";
    public static String GET_SERVER_GUID_AS_TEXT = "Common/GetServerGuidAsText";

    public static String GET_TOOL_TIP = "GetToolTip";
    public static String GET_TOOL_TIP_BODY = "FormID=%s&ControlName=%s&PID=%s&ParentID=%s&XmlType=%s";

    public static String TEMPLATE_BY_MODULE = "ModuleV2/GetProjectTemplateByModule";
    public static String TEMPLATE_BY_MODULE_PARAMS = "?projectId=%s&parentId=%s&moduleId=%s&lastSyncedAt=%s";

    public static String TEMP_FILE_UPLOAD = "Document/UploadTemp";
    public static String TEMP_FILE_UPLOAD_PARAMS = "?tempFolder=%s";

    public static String TEMPLATE_BY_MODULEV3 = "ModuleV3/GetProjectTemplateByModule";
    public static String TEMPLATE_BY_MODULEV3_PARAMS = "?projectId=%s&parentId=%s&moduleId=%s&linkedModuleModuleID=%s";

    public static String GET_DOCUMENT_METADATA = "Document/GetDocumentMetadata";
    public static String GET_DOCUMENT_METADATA_PARAMS = "?moduleId=%s&projectId=%s&docID=%s&jsonReqParams=%s";

    public static String GET_DELETE_DOCUMENT = "Document/DeleteDocument";
    public static String GET_DELETE_DOCUMENT_PARAMS = "?linkId=%s&pid=%s&parentId=%s";

    public static String GET_EMBEDDED_DATA_SOURCE_DETAILS = "Module/GetEmbeddedDataSourceDetails";
    public static String GET_EMBEDDED_DATA_SOURCE_DETAILS_PARAMS = "?ModuleID=%s&pageSize=%s&lastSycnedRowNumber=%s";

    public static String GET_ESTIMATOR_DATA_DETAILS = "Estimator/Get?";
    public static String GET_ESTIMATOR_DATA_DETAILS_PARAMS = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"BidItemsPickerName\\\",\\\"ColumnName\\\":\\\"ItemID\\\",\\\"Filter\\\":\\\"ItemID not in ('')\\\"}\",\"additionalParameters\":\"98o0vZvQL9Y7VIHs7iwgGkaF4jF44zoKSPGjOruFQBpPsXwDlEZvcSGNmp0E3TwMfcgY0XRQoWNkb4cRAUz1OQ== \",\"take\":20,\"skip\":20,\"page\":2,\"pageSize\":20}";

    public static String GET_SPATIAL_REFERENCE_FOR_MAP_SERVE_DETAILS = "GIS/GetSpatialReferenceforMapServer";
    public static String GET_SPATIAL_REFERENCE_FOR_MAP_SERVE_URL= "?url=https://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer";

    public static String GET_READ_LAYER_DETAILS = "GIS/GetReadLayer";
    public static String GET_READ_LAYER_PARAMS= "?layerId=%s";

    public static String DOWNLOAD_PACKAGE = "MobilePackage/DownloadPackage";
    public static String DOWNLOAD_PACKAGE_PARAMS= "?id=%s&syncKey=%s&name=%s";

    public static String GET_EDITABLE_LAYER_DATA = "GIS/GetEditableLayerData";
    public static String GET_EDITABLE_LAYER_DATA_PARAMS= "?layerId=%s&context=%s&pid=%s&parentID=%s&instanceID=%s&controlName=%s&outSR=";

    public static String REBUILD_CACHE= "MobileSettings/RebuildCache";
    public static String REBUILD_CACHE_PARAMS= "MobileLastUserCount=NULL";

    public static String SCHEMA_GET = "Schema/Get";

    public static String GET_KEYWORD_VALID_INSTANCE = "DataSource/GetKeywordValidInstanceIds";
    public static String GET_KEYWORD_VALID_INSTANCE_PARAMS = "?keyword=%s";
    public static String GET_KEYWORD_VALID_INSTANCE_BODY_PARAMS = "{\"IsSuccess\":\"true\",\"ErrorMsg\": \"\",\"ValidIdList\": [],\"ModifiedIdList\": [],\"ModifiedIdListEncrypted\": [], \"LastSyncedDetails\":\"\" }";

    public static String PROJECT = "Project";
    public static String PROJECT_PARAMS = "?jsonVersionInformation=%s";
    public static String PROJECT_PICKER = "ProjectPicker/Get";

    public static String DOCUMENT_METADATA_GET_WORKFLOW = "DocumentMetadata/GetMetaDataWorkFlows";
    public static String DOCUMENT_METADATA_GET_WORKFLOW_PARAMS = "?lastSyncedAt=%s";

    public static String DOCUMENT_METADATA_GET_INSTANCE = "DocumentMetadata/GetDocumentsMetadataInstances";
    public static String DOCUMENT_METADATA_GET_INSTANCE_PARAMS = "?moduleId=%s&pid=%s&parentId=%s&lastSyncedAt&jsonReqParams=";

    public static String DOCUMENT_METADATA_GET_WF_DETAILS = "DocumentMetadata/GetDocumentWorkflowDetails";
    public static String DOCUMENT_METADATA_GET_WF_DETAILS_PARAMS = "?documentIds=%s&lastSyncedAt&jsonReqParams=";

    public static String DOCUMENT_METADATA_GET_MODULE_TEMPLATE_FOR_DOCUMENT = "DocumentMetadata/GetModuleMetadataTemplateForDocument";
    public static String DOCUMENT_METADATA_GET_MODULE_TEMPLATE_FOR_DOCUMENT_PARAMS = "?projectId=%s&parentId=%s&moduleId=%s&lastSyncedAt";

    public static String DOCUMENTS= "Document";
    public static String DOCUMENT_DETAILS_PARAMS = "?projectID=%s&parentFolderID=%s&kendoParams=null";

    public static String LINKED_ATTACHMENT= "LinkedAttachment";
    public static String LINKED_ATTACHMENT_BODY_PARAMS = "{\"InstanceId\":%s,\"ModuleID\":\"%s\",\"ParentID\":%s,\"PID\":%s,\"RequestParameters\":{\"PID\":\"%s\",\"ParentID\":\"%s\",\"ModuleID\":\"%s\"},\"Attachments\":[{\"ControlName\":\"\",\"Description\":\"\",\"DocId\":%s,\"DocName\":\"%s\"}]}";

    public static String PORTFOLIO_MANAGEMENT= "PortfolioManagement/Get?";
    public static String PORTFOLIO_MANAGEMENT_PARAMS = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"PortfolioPicker\\\",\\\"ColumnName\\\":\\\"SubPortfolioId\\\",\\\"Filter\\\":\\\"\\\"}\",\"additionalParameters\":\"9SphVTvlBhZk1nE88ic9dUZHsF0TzWDujwoBaMF9U6NAr12FKhGH2qTSAniWITsAt6UIUrprdbeUEvBzpVKwpe5isA7GdH1cGsXlBUcOLtgE2B2CnMcwI6UBsXG7npqM55DXL/e/5XPCH1banyIa3n4jUmVfCVrYrGb/CovvLpw= \",\"take\":20,\"skip\":0,\"page\":1,\"pageSize\":20}";

    public static String GET_PROGRAM_PROJECT = "ProgramProject/Get?";
    public static String GET_PROGRAM_PROJECT_PARAM = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"ProjectPicker\\\",\\\"ColumnName\\\":\\\"ProjectID\\\",\\\"Filter\\\":\\\"FilterStartProgramType%3D'0'SelectionProgramBudget%3D'0'SelectionProjectValue%3D'0'SelectionIncludeOngoing%3D'true'ProgramCategory%3D'5'FilterEnd\\\"}\",\"additionalParameters\":\"9SphVTvlBhZk1nE88ic9dcPjAsrKS6gDiCgZpC%2fXsO5eqDm4ef5wcgGNZbiGTgde%2bh3hBHUY%2btRbzABH2X8%2fZiC25UU%2fMCGCA0M2Fa5j%2bX8QA0ihXCticNoDYJ7JpFZgUt0dxPyeF5rEjdCNnqjQhtDblbIqE7UmoFXcGdB3v6FpkxusHEVf6Wgk7A9%2fKu9Z%20\",\"take\":4,\"skip\":0,\"page\":1,\"pageSize\":15}";

    public static String SAVE_DOCUMENT_ASSOCIATION = "AssociateDocumentFolder/SaveDocumentAssociation?";
    public static String SAVE_DOCUMENT_ASSOCIATION_BODY_PARAM = "{\"valid\":true,\"documentPropertyId\":\"%s\",\"folderStructureId\":\"1\",\"documentFolderIds\":[\"%s\"],\"documentFolderIdsForDissociation\":[]}";

    public static String SYNC_LOG = "SyncLog/Post";
    public static String SYNC_LOG_PARAM = "?userName=%s&ip=&deviceType=&deviceName=&os=&logData=1&VersionNumber=1&mobileSyncRequestGuid=1";

    public static String GET_WORKING_DAYS = "Project/GetWorkingDays";
    public static String GET_WORKING_DAYS_PARAM = "?projectID=%s&startDateAsStr=%s&endDateAsStr=%s";

    public static String GET_PROJECT_LIST = "Project/GetProjectsList";
    public static String GET_PROJECT_LIST_PARAM = "?projects=%s&programs=%s&masterPrograms=%s&portfolios=%s&getInactiveProjects=%s";

    public static String GET_DOCUMENT_DELETE = "Document/DeleteFolder";
    public static String GET_DOCUMENT_DELETE_PARAM = "?folderId=%s";

    public static String GET_AUTH_TOKEN = "microservice/GetAuthToken";

    public static String DOCUMENTS_UPLOAD_METADATA_EXCEL = "DocumentsUpload/UploadMetaDataExcel";
    public static String DOCUMENTS_UPLOAD_METADATA_EXCEL_PARAM = "?PID=%s&IID=%s&MID=%s&FID=%s";

    public static String DOCUMENTS_IS_VALID_FILE = "DocumentsUpload/IsValidFileForUpload";
    public static String DOCUMENTS_IS_VALID_FILE_PARAM = "?fileName=%s";

    public static String DOCUMENTS_UPDATE_FILE_DETAILS = "DocumentsUpload/UpdateFileDetails";
    public static String DOCUMENTS_UPDATE_FILE_DETAILS_PARAM = "?PID=%s&IID=%s&MID=%s&FID=%s&UID=%s&UName=%s";
    public static String DOCUMENTS_UPDATE_FILE_DETAILS_BODY = "{\"StorageProviderId\":2,\"ActualFileName\":\"%s\",\"UploadedFileName\":\"%s.txt\",\"FileSize\":37,\"FileDetailJSON\":\"\\\"{\\\\\\\"FileSizeInBytes\\\\\\\":37,\\\\\\\"BucketName\\\\\\\":\\\\\\\"docmgmt-s3-upload-test\\\\\\\",\\\\\\\"VersionId\\\\\\\":\\\\\\\"SWuc8S52Wm5jJzectlIcgtotQzX1Ee9y\\\\\\\",\\\\\\\"ETag\\\\\\\":\\\\\\\"%s\\\\\\\",\\\\\\\"RelativePath\\\\\\\":\\\\\\\"mconqa/%s\\\\\\\",\\\\\\\"IsPartsUpload\\\\\\\":false,\\\\\\\"TotalPartCount\\\\\\\":1,\\\\\\\"UploadedPartCount\\\\\\\":1,\\\\\\\"ActivityGUID\\\\\\\":null}\\\"\",\"GeoTagJSON\":\"null\",\"Description\":\"%s\"}";;

    public static String POST_DOCUMENT_UPLOAD = "Document/Upload";
    public static String POST_DOCUMENT_UPLOAD_PARAM = "?folderId=%s&projectId=%s&description=%s";

    public static String INVITED_PROJECT_LIST = "InvitedProjectList";
    public static String INVITED_PROJECT_LIST_PARAM = "?inlinecount=%s&selectedValues";

    public static String CONTRACT_LIST = "ContractList";
    public static String CONTRACT_LIST_PARAM = "?PID=%s&inlinecount=%s&selectedValues";

    public static String DISCUSSION_CONTROL_GRID = "DiscussionControlGrid";
    public static String DISCUSSION_CONTROL_GRID_PARAM = "?ModuleId=%s&ControlName=%s&InstanceId=%s&PID=%s&ParentID=%s&inlinecount=%s";

    public static String DROPDOWN_VALUES = "DropdownValues";
    public static String DROPDOWN_VALUES_PARAM = "?ModuleID=%s&CN=%s&PID=%s&ParentID=%s&InstanceID=%s&ParentModuleID=%s&Context=%s&RenderAsLabel=%s&inlinecount=%s&selectedValues";

    public static String EXTERNALCOMPANY_VALUES = "ExternalCompany";
    public static String EXTERNALCOMPANY_VALUES_PARAM = "?inlinecount=%s&selectedValues";

    public static String GENERIC_CLIENT_DYNAMIC_GRID = "GenericClientDynamicGrid";
    public static String CONTRACTOR_LIST = "ContractorList";
    public static String GENERIC_CLIENT_DYNAMIC_GRID_PARAM = "?ModuleId=%s&ControlName=%s&InstanceId=%s&PID=%s&ParentID=%s&inlinecount=%s";

    public static String MOM_AGENDA= "MOMAgenda";
    public static String LINK_TO_GRID = "LinkToGrid";
    public static String MODULE_LIST = "ModuleList";
    public static String RETURN_20KB_FILE = "Module/Return20KBFile";
    public static String PING_LIST = "Ping";
    public static String PING_LIST_PARAM = "?r=1";
    public static String MODULE_LIST_PARAM = "?PID=%s&inlinecount=%s&selectedValues";

    public static String POST_MOBILE_MODULE_TEMPLATE_MODULE = "MobileModule/GetTemplateByModule";
    public static String POST_MOBILE_MODULE_TEMPLATE_MODULE_BODY = "ClassAttribute=%s&AdditionalInfo=&LastSyncedAt=&IdentifierKey=&ModuleId=%s&CustomModuleFilter=&TemplateTypeList[]=FormTemplate&TemplateTypeList[]=ListTemplate";

    public static String GET_FOLDER_FOR_PROJECT = "Document/GetFoldersForProject";
    public static String GET_FOLDER_FOR_PROJECT_PARAM = "?projectId=%s&lastSyncedFolderId=%s&pageSize=%s";

    public static String POST_DOCUMENT_UPLOAD_DOCUMENT = "Document/UploadDocument";
    public static String POST_DOCUMENT_UPLOAD_DOCUMENT_PARAM = "?fileId=%s&projectId=%s&folderId=%s";

    public static String GET_IS_CHECKLIST_COMPLETED = "Checklist/IsCheckListCompleted";
    public static String GET_IS_CHECKLIST_COMPLETED_PARAM = "?moduleId=USRMGMT&checklistId=%s&formInstanceId=%s";

    public static String POST_RENDER_DOCUMENT_TYPE = "RenderService/GetDocumentTypes";

    public static String POST_GENERIC_XML_PICKER = "GenericXMLPicker/Post";
    public static String GET_GENERIC_XML_PICKER = "GenericXMLPicker/Get?";
    public static String GENERIC_XML_PICKER_BODY = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"ItemPicker\\\",\\\"ColumnName\\\":\\\"ItemID\\\",\\\"Filter\\\":\\\"\\\"}\",\"additionalParameters\":\"gP2KyXC0qQtvJCTeY5Hk7GsrUw07qKBPgOFuzXwS7fDZXZcq2ZjyhHhhfyVdsDQvQfdNJU6NzLV3R84jBO5vdKiZNbCqtrno7aHO6yIxBDSaV7GXATMB00S5ao2njJxleNEvysrdgeZPSAxdP/TdbkKxezQiZQbMvLBW6eE70dbE2jnpKpmCA9o5uSqfS9cekrD11mRa504h9aiq1XZvcUbEzC1O8xZvHq2qE/aQaUA= \",\"take\":40,\"skip\":0,\"page\":1,\"pageSize\":40}";

    public static String POST_ADD_CURRENT_FILTER = "ListFilterManager/AddCurrentFilter";
    public static String POST_ADD_CURRENT_FILTER_BODY = "=%7B%22FilterName%22%3A%22%s%22%2C%22IsDefaultFilter%22%3Afalse%2C%22ReplaceCode%22%3A%22%22%2C%22IsReplace%22%3Afalse%2C%22StateString_FilterData%22%3A%22PAA%2FAHgAbQBsACAAdgBlAHIAcwBpAG8AbgA9ACIAMQAuADAAIgAgAGUAbgBjAG8AZABpAG4AZwA9ACIAdQB0AGYALQAxADYAIgA%2FAD4APABGAGkAbAB0AGUAcgBNAGEAbgBhAGcAZQByAEEAZABhAHAAdABlAHIATwBiAGoAZQBjAHQAIAB4AG0AbABuAHMAOgB4AHMAZAA9ACIAaAB0AHQAcAA6AC8ALwB3AHcAdwAuAHcAMwAuAG8AcgBnAC8AMgAwADAAMQAvAFgATQBMAFMAYwBoAGUAbQBhACIAIAB4AG0AbABuAHMAOgB4AHMAaQA9ACIAaAB0AHQAcAA6AC8ALwB3AHcAdwAuAHcAMwAuAG8AcgBnAC8AMgAwADAAMQAvAFgATQBMAFMAYwBoAGUAbQBhAC0AaQBuAHMAdABhAG4AYwBlACIAPgA8AFUAcwBlAHIASQBkAD4ANQA1ADwALwBVAHMAZQByAEkAZAA%2BADwATQBvAGQAdQBsAGUASQBEAD4AUABSAE8ASgBFAEMAVAA8AC8ATQBvAGQAdQBsAGUASQBEAD4APABJAGQAZQBuAHQAaQBmAGkAZQByAFAAYQByAGEAbQA%2BAFAAUAA8AC8ASQBkAGUAbgB0AGkAZgBpAGUAcgBQAGEAcgBhAG0APgA8AEMAbwBkAGUAPgAwADwALwBDAG8AZABlAD4APABTAG8AcgB0AE8AcgBkAGUAcgAgAC8APgA8AEYAaQBsAHQAZQByAFAAYQByAGEAbQBTAHQAcgBpAG4AZwA%2BACgAMQA9ADEAIAAgAEEATgBEACAAKABbAE8AdwBuAGUAcgBdACAATABJAEsARQAgACcAJQBhAHAAaQAlACcAKQApADwALwBGAGkAbAB0AGUAcgBQAGEAcgBhAG0AUwB0AHIAaQBuAGcAPgA8AEcAcgBpAGQARgBpAGwAdABlAHIARQB4AHAAcgBlAHMAcwBpAG8AbgBMAGkAcwB0AD4APABHAHIAaQBkAEYAaQBsAHQAZQByAEUAeABwAHIAZQBzAHMAaQBvAG4APgA8AEMAbwBsAHUAbQBuAE4AYQBtAGUAPgBPAHcAbgBlAHIAPAAvAEMAbwBsAHUAbQBuAE4AYQBtAGUAPgA8AEYAdQBuAGMAdABpAG8AbgA%2BAEMAbwBuAHQAYQBpAG4AcwA8AC8ARgB1AG4AYwB0AGkAbwBuAD4APABWAGEAbAB1AGUAIAB4AHMAaQA6AHQAeQBwAGUAPQAiAHgAcwBkADoAcwB0AHIAaQBuAGcAIgA%2BAGEAcABpADwALwBWAGEAbAB1AGUAPgA8AC8ARwByAGkAZABGAGkAbAB0AGUAcgBFAHgAcAByAGUAcwBzAGkAbwBuAD4APAAvAEcAcgBpAGQARgBpAGwAdABlAHIARQB4AHAAcgBlAHMAcwBpAG8AbgBMAGkAcwB0AD4APABHAHIAaQBkAEcAcgBvAHUAcABCAHkARQB4AHAAcgBlAHMAcwBpAG8AbgBMAGkAcwB0ACAALwA%2BADwALwBGAGkAbAB0AGUAcgBNAGEAbgBhAGcAZQByAEEAZABhAHAAdABlAHIATwBiAGoAZQBjAHQAPgA%3D%22%7D";

    public static String GET_LITE_DATA_LIST= "ListFilterManager/GetLiteDataList";
    public static String GET_LITE_DATA_LIST_PARAM = "?fltrUserId=NQA1AA==&fltrModuleId=PROJECT&fltrIdParam=PP";

    public static String GET_SAVE_LITE_DATA_ITEM ="ListFilterManager/SaveLiteDataItem";
    public static String GET_SAVE_LITE_DATA_ITEM_PARAMS = "?fltrUserId=NQA1AA==&fltrModuleId=PROJECT&fltrIdParam=PP&fltrFilterName=%s&fltrIsDefault=false&fltrCode=%s";

    public static String Get_DELETE_DATA_ITEM = "ListFilterManager/DeleteDataItem";
    public static String Get_DELETE_DATA_ITEM_PARAMS="?fltrUserId=NQA1AA==&fltrModuleId=PROJECT&fltrIdParam=PP&fltrCode=%s";

    public static String Get_WORK_DONE = "WorkDone";
    public static String Get_WORK_DONE_PARAMS = "?{%22sort%22:null,%22filter%22:null,%22additionalInfo%22:,%22additionalParameters%22:%22xOZRDHgGMLFUi6KuLFTpuKTZYG0BQG3Nl6Opnao6s%2bACn7f6NKb2M1onAKzlwqghPRpgVjIfxDE6nNej8fD5bRS965LBelxPFCOVv4C70fBfoqwClyXnOACE7zmF35r2%20%22,%22take%22:20,%22skip%22:0,%22page%22:1,%22pageSize%22:20}";

    public static String PROJECTLIST_GET = "ProjectList/Get";
    public static String PROJECTLIST_GET_BODY = "{ \"sort\":null, \"filter\":null, \"additionalInfo\":\"{\\\"PickerName\\\":\\\"ObjectivesPickerName\\\",\\\"ColumnName\\\":\\\"ParentObjectiveID\\\",\\\"Filter\\\":\\\"\\\"}\", \"additionalParameters\":\"9SphVTvlBhZk1nE88ic9ddvQNin1I78H7Y1zTQHqL0E7cfnDEhgmamFuvTad0QmI\" ,\"take\":5, \"skip\":0, \"page\":1, \"pageSize\":5 }";

    public static String LINK_TO_PICKER = "LinkToPicker";
    public static String LINK_TO_PICKER_BODY = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"MOMLinkToGrid_Picker\\\",\\\"ColumnName\\\":\\\"\\\",\\\"Filter\\\":\\\"\\\",\\\"MultiColumnFilterData\\\":\\\"\\\",\\\"SearchParams\\\":\\\"{\\\\\\\"PID\\\\\\\" : \\\\\\\"141\\\\\\\",\\\\\\\"ParentId\\\\\\\" : \\\\\\\"\\\\\\\",\\\\\\\"ModuleId\\\\\\\" : \\\\\\\"Items_ESTMATE\\\\\\\"}\\\"}\",\"additionalParameters\":\"G8mJ2G8mEleVTjrQn0G2g%2baDxfr1hOwrTDhJumCPgROFNALk1qmEjtRvxwFhfmvt5%2bDBBlwLN2fKgx74TN8kBCva6tyXtQoWHfQuZ1U690liVNXlycE%2fu7mxCFtznMEs%2fAciexplhDFcV%2fG52yqMwsPyr16yFbcZV7ZZ5gKpthY%3d \",\"take\":15,\"skip\":0,\"page\":1,\"pageSize\":15}";

    public static String GET_PACKAGE_ESTIMATE = "MobilePackage/GetPackageEstimate";
    public static String GET_PACKAGE_ESTIMATE_BODY = "=%5B1%5D";

    public static String RETURN_1KB_FILE = "Module/Return1KBFile";
    public static String RETURN_40KB_FILE = "Module/Return40KBFile";

    public static String DOWNLOAD_LAYER = "GIS/DownloadLayer";
    public static String DOWNLOAD_LAYER_PARAM = "?layerName=%s";

    public static String ALL_USERS_PICKER_GET = "AllUsersPicker/Get";
    public static String ALL_USERS_PICKER_BODY = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"{\\\"PickerName\\\":\\\"PickerUsers\\\",\\\"ColumnName\\\":\\\"UserId\\\",\\\"Filter\\\":\\\"\\\",\\\"MultiColumnFilterData\\\":\\\"\\\"}\",\"additionalParameters\":\"gP2KyXC0qQtvJCTeY5Hk7Crw7KYyf4abvw/VvLkIMwyH8LOIpuHM171glsp32UHGc5efWjlcTz/0KizwIsX55hCWyT3vT9AiDaR2G+5NaqiK6Yu1+WNqHQfPJHY00zlIwAuymQYkljWTtwgmQQeuRw== \",\"take\":40,\"skip\":0,\"page\":1,\"pageSize\":40}";

    public static String INFLATION_RULE_PICKER_GET = "InflationRulePicker?";
    public static String INFLATION_RULE_PICKER_BODY = "{\"sort\":null,\"filter\":null,\"additionalParameters\":\"gP2KyXC0qQtvJCTeY5Hk7MLeOatYSy46Ow2ue7mn0UJOw0ktKG8heBQJHpT1Vff0K5Re0D2P%2bHIE7%2btkmhiuxg%3d%3d%20\",\"take\":40,\"skip\":0,\"page\":1,\"pageSize\":40}";

    public static String ADJUSTMENTS_PICKER_GET = "Adjustments/Get?";
    public static String ADJUSTMENTS_PICKER_QUERY = "{\"sort\": null,\"filter\": null,\"additionalInfo\": \"\",\"additionalParameters\": \"\",\"take\": 100,\"skip\": 0,\"page\": 1,\"pageSize\": 100}";

    public static String PROGRAM_PROJECT_GET = "ProgramProject/Get?";
    public static String PROGRAM_PROJECT_QUERY = "{\"sort\":null,\"filter\":null,\"additionalInfo\":\"\",\"additionalParameters\":\"\",\"take\":5000,\"skip\":0,\"page\":1,\"pageSize\":100}";

    public static String CONSOLIDATION_INFORMATION_GET = "Document/GetConsolidationInformation";
    public static String CONSOLIDATION_INFORMATION_GET_PARAMS = "?annotationID=%s";

    public static class DocService {

        public static String UPLOAD_SINGLE_DOCUMENT = "storage/UploadSingleDocument";
        public static String GET_DOCUMENT = "storage/GetDocument";
        public static String DELETE_DOCUMENT = "storage/DeleteDocument";
    }

    public static class ConfigPlus {
        public static String CULTURE_PARAM = "?Culture=en-US";
        public static String RESOURCE_PARAM = "?Culture=en-US&ResourceType=1";
        public static String KEY_PARAM = "?Culture=en-US&ResourceType=1&key=%s";

        public static String GET_MASTER_RESOURCE = "MasterResourceAPI/GetMasterResource";
        public static String GET_MASTER_RESOURCES = "MasterResourceAPI/GetMasterResources";
        public static String GET_ALL_MASTER_RESOURCE = "MasterResourceAPI/GetAllMasterResources";

        public static String CREATE_RESOURCE = "ResourceAPI/CreateResource";
        public static String DELETE_RESOURCE = "ResourceAPI/DeleteResource";
        public static String GET_RESOURCE = "ResourceAPI/GetResource";
        public static String MODIFY_RESOURCE = "ResourceAPI/ModifyResource";
        public static String CREATE_RESOURCES = "ResourceAPI/CreateResources";
        public static String GET_RESOURCES = "ResourceAPI/GetResources";
        public static String GET_ALL_RESOURCES = "ResourceAPI/GetAllResources";
        public static String DELETE_RESOURCES = "ResourceAPI/DeleteResources";
    }
}
