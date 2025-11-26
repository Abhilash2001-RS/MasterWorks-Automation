package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.AccessibilityCategory;
import com.deque.axe.AXE;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class PdfHelper extends BasePage {
    private static final String lineSeparator = System.getProperty("line.separator");
    private static final boolean isAccessibilityTestingEnabled = (Strings.isNotNullAndNotEmpty(System.getProperty("accessibilityTest")) && System.getProperty("accessibilityTest").equalsIgnoreCase("true")) ||
            EnvironmentHelper.getPropertyValue("accessibilityTest").equalsIgnoreCase("true");
    //required to convert axe rule set into string remote/local
    private static Map<String, String> locationToFile = new HashMap<String, String>();
    //location location path for axe rule set js file
    private static String folderPathRuleSet = String.format("%s\\%s", System.getProperty("user.dir"), "src/test/resources/axeRuleset.js");
    //special font sizes
    Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 9, Font.NORMAL, new BaseColor(0, 0, 0));
    Font bf12 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
    Font bfURL = new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD, new BaseColor(0, 0, 0));
    private WebDriver driver;
    //path to the axe.min.js file
    private URL scriptUrl = PdfHelper.class.getResource("/axe.min.js");

    /**
     * Axe helper used to access the axe files
     *
     * @param driver
     */
    public PdfHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * To download file from AXE_RULESET_LOCATION(github) path and convert into string
     *
     * @param location Github location is passed
     * @return Axe rule set is returned
     */
    public static String axeRuleSetRemote(String location) {
        String ruleSetScript = locationToFile.get(location);
        if (ruleSetScript != null) {
            return ruleSetScript;
        }
        try {
            HttpGet httpGet = new HttpGet(location);
            CloseableHttpResponse response = new DefaultHttpClient().execute(httpGet);
            ruleSetScript = EntityUtils.toString(response.getEntity());
            response.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ruleSetScript;
    }

    /**
     * Convert axe rule set js file into String by keeping .js in local machine instead of github remote
     *
     * @return Axe rule set is returned
     */
    public static String axeRuleSetLocal() {
        StringBuilder ruleSetScript = new StringBuilder();
        try {
            InputStream readFile = new FileInputStream(folderPathRuleSet);
            BufferedReader buf = new BufferedReader(new InputStreamReader(readFile));
            String nextline = buf.readLine();
            while (nextline != null) {
                ruleSetScript.append(nextline).append("\n");
                nextline = buf.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleSetScript.toString();
    }

    /**
     * AxeInject Method is used to Inject the Axe Script url to the page
     *
     * @param axeTable - The Accessibility result table values of a particular page
     * @return The Accessibility result for the required category
     */
    public PdfPTable axeInject(PdfPTable axeTable) {
        if (isAccessibilityTestingEnabled) {
            try {
                AXE.inject(driver, scriptUrl, true);
                JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
                insertCell(axeTable, "URL : " + responseJSON.getString("url"), Element.ALIGN_LEFT, 4, bfURL, false);
                String rule = EnvironmentHelper.getPropertyValue("accessibilityCategory");
                String[] resultTypes = rule.split(",");
                if (resultTypes.length > 0) {
                    for (String type : resultTypes) {
                        report(responseJSON, type, axeTable);
                    }
                } else {
                    report(responseJSON, "violations", axeTable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger().info("Accessibility Run is not triggered");
        }
        return axeTable;
    }

    /**
     * Writes the axe result to the specified file
     *
     * @param fileName     -   Particular file where the result needs to be saved
     * @param fileContent- File content is the Json response output fetched
     */
    public void writeAxeResultsToFile(File fileName, String fileContent) {
        try {
            BufferedWriter writeReport = new BufferedWriter(new FileWriter(fileName, true));
            writeReport.write(fileContent);
            writeReport.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The value fetched from the Json response is being collectively stored in string builder
     *
     * @param appendResults Category results are appended
     * @return The Axe rule output will be fetched
     */
    public StringBuilder runAxeRuleSet(StringBuilder appendResults) {
        if (isAccessibilityTestingEnabled) {
            try {
                String axeRulesToExclude = EnvironmentHelper.getPropertyValue("excludeRuleId");
                String[] axeRuleArrayValue = axeRulesToExclude.split(",");
                StringBuilder axeRuleFormat = new StringBuilder();
                String prefix = "";
                for (String singleRuleId : axeRuleArrayValue) {
                    axeRuleFormat = axeRuleFormat.append(prefix);
                    prefix = ",";
                    axeRuleFormat = axeRuleFormat.append("'").append(singleRuleId).append("'").append(": { enabled: false }");
                }
                String axeRuleSetScript = axeRuleSetRemote(Constants.AXE_RULESET_LOCATION);
                Map aXeResponse = (Map) ((JavascriptExecutor) driver)
                        .executeAsyncScript(axeRuleSetScript
                                + "; sendResults = arguments[arguments.length - 1]; axe.run({rules: {" + axeRuleFormat + "}}, function (err, results) { sendResults(err || results); });");
                JSONObject responseJSON = new JSONObject(aXeResponse);
                appendResults = appendResults.append(AccessibilityCategory.Url + responseJSON.getString(AccessibilityCategory.JsonUrl.getValue()) + "\n");
                String rule = EnvironmentHelper.getPropertyValue("accessibilityCategory");
                String[] resultTypes = rule.split(",");
                if (resultTypes.length > 0) {
                    for (String type : resultTypes) {
                        appendResults = appendResults.append(type.toUpperCase() + " :\n" + AXE.report(responseJSON.getJSONArray(type.toLowerCase())) + "\n");
                    }
                } else {
                    appendResults = appendResults.append(AccessibilityCategory.Violations + AXE.report(responseJSON.getJSONArray(AccessibilityCategory.JsonViolations.getValue())) + "\n");
                }
                return appendResults;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger().info("Accessibility Run is not triggered");
        }
        return appendResults;
    }

    /**
     * The value fetched from the Json response is being collectively stored using runAxePDFTable method
     *
     * @param axeTable Created table format is being passed
     * @return Table with the category/type/url/severity is being returned
     */
    public PdfPTable runAxePDFTable(PdfPTable axeTable) {

        if (isAccessibilityTestingEnabled) {
            try {
                String axeRulesToExclude = EnvironmentHelper.getPropertyValue("excludeRuleId");
                if (axeRulesToExclude.isEmpty()) {
                    axeInject(axeTable);
                } else {
                    String[] axeRuleArrayValue = axeRulesToExclude.split(",");
                    StringBuilder axeRuleFormat = new StringBuilder();
                    String prefix = "";
                    for (String singleRuleId : axeRuleArrayValue) {
                        singleRuleId.trim();
                        axeRuleFormat = axeRuleFormat.append(prefix);
                        prefix = ",";
                        axeRuleFormat = axeRuleFormat.append("'").append(singleRuleId).append("'").append(": { enabled: false }");
                    }
                    String axeRuleSetScript = axeRuleSetLocal();
                    Map aXeResponse = (Map) ((JavascriptExecutor) driver)
                            .executeAsyncScript(axeRuleSetScript
                                    + "; sendResults = arguments[arguments.length - 1]; axe.run({rules: {" + axeRuleFormat + "}}, function (err, results) { sendResults(err || results); });");
                    JSONObject responseJSON = new JSONObject(aXeResponse);
                    insertCell(axeTable, String.format("%s: %s", AccessibilityCategory.Url, responseJSON.getString(AccessibilityCategory.JsonUrl.getValue())), Element.ALIGN_LEFT, 4, bfURL, false);
                    String rule = EnvironmentHelper.getPropertyValue("accessibilityCategory");
                    String[] resultTypes = rule.split(",");
                    if (resultTypes.length > 0) {
                        for (String type : resultTypes) {
                            report(responseJSON, type, axeTable);
                        }
                    } else {
                        report(responseJSON, "violations", axeTable);
                    }
                    return axeTable;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger().info("Accessibility Run is not triggered");
        }
        return axeTable;
    }

    /**
     * Creates the table with the specified alignment
     *
     * @return Created table
     */
    public PdfPTable createTableHeaders() {
        DecimalFormat df = new DecimalFormat("0.00");
        //specify column widths
        float[] columnWidths = {6f, 6f, 25f, 4f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "Category", Element.ALIGN_CENTER, 1, bf12, true);
        insertCell(table, "Rule Id", Element.ALIGN_CENTER, 1, bf12, true);
        insertCell(table, "Description", Element.ALIGN_CENTER, 1, bf12, true);
        insertCell(table, "Severity", Element.ALIGN_CENTER, 1, bf12, true);
        table.setHeaderRows(1);
        return table;
    }

    /**
     * Inserts the value into the cell
     *
     * @param table      PDF Table name
     * @param text       Text value to be entered
     * @param align      Alignment design
     * @param colspan    Column span value
     * @param font       Font design
     * @param backGround Background color
     */
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font, boolean backGround) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        if (backGround)
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    /**
     * Inserts the table cell with background color
     *
     * @param table   Table value
     * @param text    Text to be entered
     * @param align   Alignment value
     * @param colspan Column span value
     * @param font    Font design
     */
    private void insertCellWithBackGroud(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    /**
     * Writes axe results to the PDF generated
     *
     * @param fileName    Type is specified in the file name
     * @param fileContent The file content with the help url is specified
     */
    public void writeAxeResultsToPDF(File fileName, PdfPTable fileContent) {
        try {
            Document doc = new Document();
            PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            doc.open();
            //create a paragraph
            Paragraph paragraph = new Paragraph("Accessibility Reports:");
            paragraph.add(fileContent);
            doc.add(paragraph);
            doc.close();
            docWriter.close();
        } catch (Exception dex) {
            dex.printStackTrace();
        }
    }

    /**
     * report method fetches the required result and displays
     *
     * @param responseJSON Json array found
     * @param type         Violation/Incomplete/Inappropriate
     * @param table        PDF table values to be inserted
     */
    public void report(JSONObject responseJSON, String type, PdfPTable table) {
        JSONArray responseArray = responseJSON.getJSONArray(type.toLowerCase());
        if (responseArray.length() <= 0) {
            if (type.equalsIgnoreCase("INAPPLICABLE")) {
                if (accessibilityTestMap.containsKey(type.toUpperCase()))
                    accessibilityTestMap.replace(type.toUpperCase(), accessibilityTestMap.get(type.toUpperCase()) + responseArray.length());
                else
                    accessibilityTestMap.put(type.toUpperCase(), responseArray.length());
            } else {
                if (accessibilityTestMap.containsKey(type.toUpperCase())) {
                    accessibilityTestMap.replace(type.toUpperCase(), accessibilityTestMap.get(type.toUpperCase()) + responseArray.length());
                } else
                    accessibilityTestMap.put(type.toUpperCase(), responseArray.length());
            }
        } else {
            if (type.equalsIgnoreCase("INAPPLICABLE")) {
                if (accessibilityTestMap.containsKey(type.toUpperCase()))
                    accessibilityTestMap.replace(type.toUpperCase(), accessibilityTestMap.get(type.toUpperCase()) + responseArray.length());
                else
                    accessibilityTestMap.put(type.toUpperCase(), responseArray.length());
            }
            for (int i = 0; i < responseArray.length(); ++i) {
                StringBuilder sb = new StringBuilder();
                JSONObject jsonList = responseArray.getJSONObject(i);
                sb.append(lineSeparator).append(jsonList.getString("help"));
                if (jsonList.has("helpUrl")) {
                    String helpUrl = jsonList.getString("helpUrl");
                    sb.append(": ").append(helpUrl);
                }
                JSONArray nodes = jsonList.getJSONArray("nodes");
                if (!type.equalsIgnoreCase("INAPPLICABLE")) {
                    if (accessibilityTestMap.containsKey(type.toUpperCase())) {
                        accessibilityTestMap.replace(type.toUpperCase(), accessibilityTestMap.get(type.toUpperCase()) + nodes.length());
                    } else
                        accessibilityTestMap.put(type.toUpperCase(), nodes.length());
                }
                for (int j = 0; j < nodes.length(); ++j) {
                    JSONObject node = nodes.getJSONObject(j);
                    sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ").append(node.getJSONArray("target")).append(lineSeparator);
                    JSONArray all = node.getJSONArray("all");
                    JSONArray none = node.getJSONArray("none");

                    for (int k = 0; k < none.length(); ++k) {
                        all.put(none.getJSONObject(k));
                    }
                    appendFixes(sb, all, "Fix all of the following:");
                    appendFixes(sb, node.getJSONArray("any"), "Fix any of the following:");
                }
                insertCell(table, type.toUpperCase(), Element.ALIGN_LEFT, 1, bfBold12, false);
                insertCell(table, jsonList.getString("id"), Element.ALIGN_LEFT, 1, bfBold12, false);
                insertCell(table, sb.toString(), Element.ALIGN_LEFT, 1, bfBold12, false);
                if (type.equalsIgnoreCase("INAPPLICABLE"))
                    insertCell(table, "-", Element.ALIGN_CENTER, 1, bfBold12, false);
                else
                    insertCell(table, jsonList.getString("impact"), Element.ALIGN_LEFT, 1, bfBold12, false);
            }
        }
    }

    /**
     * @param sb      String builder value is passed
     * @param arr     Json array value is passed
     * @param heading Comment to be displayed
     */
    private void appendFixes(StringBuilder sb, JSONArray arr, String heading) {
        if (arr != null && arr.length() > 0) {
            sb.append("    ").append(heading).append(lineSeparator);

            for (int i = 0; i < arr.length(); ++i) {
                JSONObject fix = arr.getJSONObject(i);
                sb.append("      ").append(fix.get("message")).append(lineSeparator);
            }
            sb.append(lineSeparator);
        }
    }

    /**
     * getOrdinal used in converting the integer numbers into string ordinals
     *
     * @param number Required number is passed
     * @return converted string ordinal is returned
     */
    private String getOrdinal(int number) {
        String ordinal;
        int mod;
        for (ordinal = ""; number > 0; number = (number - mod) / 26) {
            mod = (number - 1) % 26;
            ordinal = (char) (mod + 97) + ordinal;
        }
        return ordinal;
    }
}
