package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RegexStrings;
import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelUtil {


    /**
     * Read excel content.
     *
     * @param fileName -   File name.
     * @param node node
     * @param pathToTheFile - Path to the file.
     * @return -   Excel content.
     */
    public static Map<Integer, List<String>> readExcel(String fileName, Host node, String pathToTheFile) {
        Map<Integer, List<String>> data = new HashMap<>();
        Sheet sheet = getSheetFromWorkbook(fileName, node, null, pathToTheFile);
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i)
                                .add(cell.getRichStringCellValue()
                                        .getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i)
                                    .add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i)
                                    .add((int) cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i)
                                .add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i)
                                .add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(i)
                                .add(" ");
                }
            }
            i++;
        }

        return data;
    }

    /**
     * Read excel content.
     *
     * @param fileName -   File name.
     * @param node node
     * @param sheetNumber - Sheet Number
     * @return -   Excel content.
     */
    public static Map<Integer, List<String>> readExcelWithSheetName(String fileName, Host node,Integer sheetNumber) {
        Map<Integer, List<String>> data = new HashMap<>();
        try {
            File file = FileHelper.getFile(fileName, node);
            FileInputStream fileStream = new FileInputStream(file);
            Workbook workbook = null;

            if (file.getName().contains(".xlsx"))
                workbook = new XSSFWorkbook(fileStream);
            else if (file.getName().contains(".xls"))
                workbook = new HSSFWorkbook(fileStream);
            Sheet sheet = workbook.getSheetAt((sheetNumber-1));
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(i)
                                    .add(cell.getRichStringCellValue()
                                            .getString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i)
                                        .add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i)
                                        .add((int) cell.getNumericCellValue() + "");
                            }
                            break;
                        case BOOLEAN:
                            data.get(i)
                                    .add(cell.getBooleanCellValue() + "");
                            break;
                        case FORMULA:
                            data.get(i)
                                    .add(cell.getCellFormula() + "");
                            break;
                        default:
                            data.get(i)
                                    .add(" ");
                    }
                }
                i++;
            }
            if (workbook != null) {
                workbook.close();
            }
            fileStream.close();
        } catch (IOException io) {
            ExceptionHandler.log(io);
        }

        return data;
    }

    /**
     * Read Column Headers from a given excel
     *
     * @param fileName - file whose column headers need to be fetched
     * @param node node
     * @return - the list of headers
     */
    public static List<String> getExcelColumnHeaders(String fileName, Host node) {
        return getExcelColumnHeaders(fileName, node, null);
    }

    /**
     * Read Column Headers from a given excel
     *
     * @param fileName - file whose column headers need to be fetched
     * @param sheetName sheet name
     * @param node node
     * @return - the list of headers
     */
    public static List<String> getExcelColumnHeaders(String fileName, Host node, String sheetName) {
        List<String> excelHeaders = new ArrayList<>();

        Sheet sheet = getSheetFromWorkbook(fileName, node, sheetName);
        if(sheet != null) {
            Row row = sheet.getRow(0);
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                excelHeaders.add(cell.getStringCellValue());
            }
        }

        return excelHeaders;
    }

    /**
     * Get Column headers with Row Data using Download Path
     * @param fileName File name
     * @param node node
     * @param columnName Column Name
     * @param columnValue Column Value
     * @param sheetName Sheet Name
     * @param downloadPath download Path
     * @return Data of headers with data
     */
    public static Map<String, String> getColumnHeadersWithRowData(String fileName, Host node, String columnName, String columnValue,String sheetName,String downloadPath)
    {
        Map<String, String> columnHeaderWithData = new HashMap<>();
        Sheet sheet = null;

        sheet = getSheetFromWorkbook(fileName, node, sheetName,downloadPath);
        Row headerRow = sheet.getRow(0);
        int rowNumber = 0;
        int columnNumber = 0;
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Cell> headerCellIterator = headerRow.cellIterator();
        while (headerCellIterator.hasNext()) {
            Cell cell = headerCellIterator.next();
            String columnHeaderValue = dataFormatter.formatCellValue(cell);
            columnHeaderValue = columnHeaderValue.replaceAll(RegexStrings.colon.getValue(), "");
            columnHeaderValue = columnHeaderValue.trim();
            if (columnHeaderValue.equalsIgnoreCase(columnName)) {
                columnNumber = cell.getColumnIndex();
                break;
            }
        }
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            headerRow = sheet.getRow(rowIndex);
            if (headerRow != null) {
                Cell cell = headerRow.getCell(columnNumber);
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (cellValue.equalsIgnoreCase(columnValue)) {
                        rowNumber = cell.getRowIndex();
                        break;
                    }
                }
            }
        }
        headerRow = sheet.getRow(0);
        Row dataRow = sheet.getRow(rowNumber);
        headerCellIterator = headerRow.cellIterator();
        Iterator<Cell> dataCellIterator = dataRow.cellIterator();
        while (headerCellIterator.hasNext() && dataCellIterator.hasNext()) {
            Cell headerCell = headerCellIterator.next();
            Cell dataCell = dataCellIterator.next();
            String cellHeaderValue = headerCell.getStringCellValue();
            if (!cellHeaderValue.equalsIgnoreCase("InternalID") && !cellHeaderValue.equalsIgnoreCase("RecordID")) {
                String columnHeaderToBeAdded = dataFormatter.formatCellValue(headerCell).replaceAll(":", "");
                columnHeaderToBeAdded = columnHeaderToBeAdded.trim();
                columnHeaderWithData.put(columnHeaderToBeAdded, dataFormatter.formatCellValue(dataCell));
            }
        }

        return columnHeaderWithData;
    }

    /**
     * fetch column header with a specific rows data
     *
     * @param fileName    - name of the file
     * @param node node
     * @param columnName  - the name of the column for which the value is to be searched
     * @param columnValue - the value to be searched
     * @param sheetName - sheetName
     * @return - all column headers with a specific rows value
     */
    public static Map<String, String> getColumnHeadersWithRowData(String fileName, Host node, String columnName, String columnValue,String sheetName) {
        return getColumnHeadersWithRowData(fileName,node,columnName,columnValue,sheetName,null);
    }

    /**
     * Write data in to excel along with headers.
     * Make sure the column headers and values are kept in the same sequence
     *
     * @param fileName         -   Name of the file to be created.
     * @param allHeaderAndData -   Excel header and list of data
     * @param sheetName        - Name of the sheet. Note - sheet name must be the list page name
     */
    public static void writeExcelDataWithColumnHeader(String fileName, Map<List<String>, List<List<String>>> allHeaderAndData, String sheetName) {
        try {
            XSSFWorkbook workbook = null;
            File file = new File(Constants.DEFAULT_DOWNLOAD_PATH + "/" + fileName);
            if (!file.exists()) {
                workbook = new XSSFWorkbook();
            } else {
                workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            }
            XSSFSheet sheet = null;
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            //XSSFSheet sheet = workbook.createSheet(sheetName);
            int i = 0;
            Cell headerCell;
            Cell dataCell;
            for (Map.Entry<List<String>, List<List<String>>> entry : allHeaderAndData.entrySet()) {
                Row headerRow = sheet.createRow(i);
                int headerCellID = 0;
                for (String headerList : entry.getKey()) {
                    headerCell = headerRow.createCell(headerCellID++);
                    headerCell.setCellValue(headerList);
                }
                i++;
                for (List<String> allDataList : entry.getValue()) {
                    Row dataRow = sheet.createRow(i);
                    int dataCellID = 0;
                    for (String listData : allDataList) {
                        dataCell = dataRow.createCell(dataCellID++);
                        if (!listData.equals("")) {
                            dataCell.setCellValue(listData);
                        }
                    }
                    i++;
                }
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write data in to excel along with headers.
     * Make sure the column headers and values are kept in the same sequence
     *
     * @param fileName         -   Name of the file to be created.
     * @param allHeaderAndData -   Excel header and list of data
     * @param sheetName        - Name of the sheet. Note - sheet name must be the list page name
     */
    public static void writeExcelDataWithColumnHeader(String fileName, Map<List<String>, List<List<String>>> allHeaderAndData, String sheetName,boolean check) {
        try {
            XSSFWorkbook workbook = null;
            File file = new File(Constants.DEFAULT_DOWNLOAD_PATH + "/" + fileName);
            if (!file.exists()) {
                workbook = new XSSFWorkbook();
            } else {
                workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            }
            XSSFSheet sheet = null;
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            //XSSFSheet sheet = workbook.createSheet(sheetName);
            int i = 0;
            Cell headerCell;
            Cell dataCell;
            for (Map.Entry<List<String>, List<List<String>>> entry : allHeaderAndData.entrySet()) {
                Row headerRow = sheet.createRow(i);
                int headerCellID = 0;
                for (String headerList : entry.getKey()) {
                    headerCell = headerRow.createCell(headerCellID++);
                    headerCell.setCellValue(headerList);
                }
                i++;
                for (List<String> allDataList : entry.getValue()) {
                    Row dataRow = sheet.createRow(i);
                    int dataCellID = 0;
                    for (String listData : allDataList) {
                        dataCell = dataRow.createCell(dataCellID++);
                        if(listData.contains(".0")) {
                            Double data = Double.parseDouble(listData);
                            dataCell.setCellValue(data);
                        }
                        else if (!listData.equals("")) {
                            dataCell.setCellValue(listData);
                        }
                    }
                    i++;
                }
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get values from an exported Excel sheet based on column header
     *
     * @param sheetName  - The sheet name of the Excel sheet whose column value is returned
     * @param columnName - Name of the column Header
     * @param fileName   - Name of Exported file
     * @param node       - Node where the TC is Executing
     * @return column based on column Header
     */
    public static String getExcelDataForHeaderValue(String sheetName, String columnName, String fileName, Host node) {
        try {
            XSSFWorkbook workbook = null;
            File file = FileHelper.getFile(fileName, node);
            workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row firstRow = sheet.getRow(0);
            int requiredColumnNumber = 0;
            String cellValue = null;
            for (Cell cell : firstRow) {
                if (cell.getStringCellValue().equals(columnName)) {
                    requiredColumnNumber = cell.getColumnIndex();
                    break;
                }
            }
            for (Row row : sheet) {
                Cell c = row.getCell(requiredColumnNumber);
                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue().equals(columnName)|| c.getStringCellValue().equals("")) {
                    continue;
                } else {
                    cellValue = c.getStringCellValue();
                }
            }
            return cellValue;
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * Method to get Excel Column Headers
     * @param node - node
     * @param fileName - Excel File Name
     * @param sheetName - Sheet for which column Headers is to be returned
     * @return list of column Headers
     */
    public static List<String> getExcelColumnHeaders(Host node,String fileName,String sheetName){
        List<String> columnHeaders = new ArrayList<>();
        try{
            XSSFWorkbook workbook ;
            File file = FileHelper.getFile(fileName, node);
            workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row firstRow = sheet.getRow(0);
            for(Cell cell : firstRow){
                columnHeaders.add(cell.getStringCellValue());
            }
            workbook.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return columnHeaders;
    }

    /**
     * Fetch column data form the excel.
     *
     * @param fileName    - Name of the file.
     * @param columnName  - Column name for which the value is to be fetched.
     * @param sheetName   - Sheet name
     * @param node node
     * @return            - Column data
     */
    public static <E> List<String> getColumnData(String fileName, Host node, E columnName, String sheetName) {
        String enumValue = getEnumValue(columnName);
        List <String> columnHeaderWithData = new ArrayList<>();
        Sheet sheet = getSheetFromWorkbook(fileName, node, sheetName);
        Row row = sheet.getRow(0);
        int columnNumber = 0;
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String columnHeaderValue = dataFormatter.formatCellValue(cell);
            columnHeaderValue = columnHeaderValue.replaceAll(RegexStrings.colon.getValue(), "");
            columnHeaderValue = columnHeaderValue.trim();
            if (columnHeaderValue.equalsIgnoreCase(enumValue)) {
                columnNumber = cell.getColumnIndex();
                break;
            }
        }
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(columnNumber);
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    columnHeaderWithData.add(cellValue);
                }
            }
        }
        return columnHeaderWithData;
    }

    /**
     * Update cell value in excel file.
     * @param fileName      -   Name of the file.
     * @param sheetName     -   Sheet name.
     * @param node          -   Node
     * @param columnName    -   Excel column name.
     * @param columnValue   -   Actual column value.
     * @param textToUpdate  -   Value to be updated too.
     * @param <E>           -   Enum Type.
     */
    public static <E> void updateCellValue(String fileName, String sheetName, Host node, E columnName, String columnValue, String textToUpdate) {
        try {
            String enumValue = getEnumValue(columnName);
            File file = FileHelper.getFile(fileName, node);
            FileInputStream fileStream = new FileInputStream(file);
            Workbook workbook = null;
            Sheet sheet = null;
            if (file.getName().contains(".xlsx"))
                workbook = new XSSFWorkbook(fileStream);
            else if (file.getName().contains(".xls"))
                workbook = new HSSFWorkbook(fileStream);

            sheet = sheetName != null ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);

            Row row = sheet.getRow(0);
            int rowNumber = 0;
            int columnNumber = 0;
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String columnHeaderValue = dataFormatter.formatCellValue(cell);
                columnHeaderValue = columnHeaderValue.replaceAll(RegexStrings.colon.getValue(), "");
                columnHeaderValue = columnHeaderValue.trim();
                if (columnHeaderValue.equalsIgnoreCase(enumValue)) {
                    columnNumber = cell.getColumnIndex();
                    break;
                }
            }
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell cell = row.getCell(columnNumber);
                    if (cell != null) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        if (cellValue.equalsIgnoreCase(columnValue)) {
                            cell.setCellValue(textToUpdate);
                            break;
                        }
                    }
                }
            }

            fileStream.close();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            outFile.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get Enum value
     *
     * @param enumObj -   Enum object.
     * @param <E>     -   Corresponding enum.
     * @return -   Enum value.
     */
    private static <E> String getEnumValue(E enumObj) {
        Method methodCall = null;
        try {
            Class cls = enumObj.getClass();
            methodCall = cls.getDeclaredMethod("getValue");

            return methodCall.invoke(enumObj).toString();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return Strings.nullToEmpty(null);
    }

    /**
     * Get sheet from workbook
     * @param fileName  -   Excel file name.
     * @param node      -   Node.
     * @param sheetName -   Name of the sheet, else pass null.
     * @param pathToTheFile -   Path to the file.
     * @return          -   Sheet object.
     */
    private static Sheet getSheetFromWorkbook(String fileName, Host node, String sheetName, String pathToTheFile) {
        Sheet sheet = null;
        File file;
        try {
            if(Strings.isNullOrEmpty(pathToTheFile)){
                file = FileHelper.getFile(fileName, node);
            }
            else {
                file = FileHelper.getFile(fileName, node, pathToTheFile);
            }

            FileInputStream fileStream = new FileInputStream(file);
            Workbook workbook = null;
            if (file.getName().contains(".xlsx"))
                workbook = new XSSFWorkbook(fileStream);
            else if (file.getName().contains(".xls"))
                workbook = new HSSFWorkbook(fileStream);

            if (workbook != null) {
                sheet = sheetName != null ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);
            }

            if (workbook != null) {
                workbook.close();
            }
            fileStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sheet;
    }

    /**
     * Get sheet from workbook
     * @param fileName  -   Excel file name.
     * @param node      -   Node.
     * @param sheetName -   Name of the sheet, else pass null.
     * @return          -   Sheet object.
     */
    public static Sheet getSheetFromWorkbook(String fileName, Host node, String sheetName) {
        return getSheetFromWorkbook(fileName, node, sheetName, null);
    }
}
