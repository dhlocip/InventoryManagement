/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.Inventory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sa
 */
public class InventoryModifier extends JDBCConnect {

    // get list inventory
    public ObservableList<Inventory> getListInventory() throws SQLException {
        ObservableList<Inventory> oList = FXCollections.observableArrayList();
        String sql = "select * from vCurrentQuantity";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new Inventory(result.getString("categoryId"), result.getString("productId"),
                    result.getString("productName"), result.getInt("quantity"),
                    result.getString("mfgDate"), result.getString("expDate")));
        }
        return oList;
    }

    // get list inventory after search
    public ObservableList<Inventory> getListInventoryAfterSearch(String categoryId) throws SQLException {
        ObservableList<Inventory> oList = FXCollections.observableArrayList();
        String sql = "select * from vCurrentQuantity where categoryId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, categoryId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new Inventory(result.getString("categoryId"), result.getString("productId"),
                    result.getString("productName"), result.getInt("quantity"),
                    result.getString("mfgDate"), result.getString("expDate")));
        }
        return oList;
    }

    public ObservableList<XYChart.Data<String, Number>> getBarChartByDate() throws SQLException {
        ObservableList<XYChart.Data<String, Number>> dList = FXCollections.observableArrayList();
        String sql = "select * from vInventoryBarChart";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            dList.add(new XYChart.Data((result.getString("productName")), result.getDouble("quantity")));
        }
        return dList;
    }

    public ObservableList<XYChart.Data<String, Number>> getBarChartByCategory(String categoryId) throws SQLException {
        ObservableList<XYChart.Data<String, Number>> dList = FXCollections.observableArrayList();
        String sql = "select * from vInventoryBarChart where categoryId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, categoryId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            dList.add(new XYChart.Data((result.getString("productName")), result.getDouble("quantity")));
        }
        return dList;
    }

    public boolean exportInventoryByDate(String userId) throws SQLException, FileNotFoundException, IOException {
        String sql = "select * from vCurrentQuantity";
        PreparedStatement statement = connect().prepareStatement(sql);
        statement.execute();
        ResultSet result = statement.getResultSet();

        // get current day
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime localDate = LocalDateTime.now();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(currentDate.format(localDate));

        XSSFFont fontTitle = workbook.createFont();
        fontTitle.setBold(true);
        fontTitle.setFontHeight(10);

        XSSFFont fontRow1 = workbook.createFont();
        fontRow1.setFontHeight(10);
        fontRow1.setBold(true);

        XSSFFont fontMain = workbook.createFont();
        fontMain.setBold(true);
        fontMain.setFontHeight(14);
        fontMain.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle styleTitle = workbook.createCellStyle();
        styleTitle.setFont(fontTitle);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleRow2 = workbook.createCellStyle();
        styleRow2.setBorderLeft(BorderStyle.THIN);
        styleRow2.setBorderRight(BorderStyle.THIN);
        styleRow2.setBorderTop(BorderStyle.THIN);
        styleRow2.setBorderBottom(BorderStyle.THIN);

        XSSFCellStyle styleRow1 = workbook.createCellStyle();
        styleRow1.setFont(fontRow1);
        styleRow1.setBorderLeft(BorderStyle.THIN);
        styleRow1.setBorderRight(BorderStyle.THIN);
        styleRow1.setBorderTop(BorderStyle.THIN);
        styleRow1.setBorderBottom(BorderStyle.THIN);
        styleRow1.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleMain = workbook.createCellStyle();
        styleMain.setFont(fontMain);
        styleMain.setAlignment(HorizontalAlignment.CENTER);
        styleMain.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("Inventory by date");
        cell.setCellStyle(styleMain);

        row = spreadsheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Phone: 0948 00 0949");
        cell.setCellStyle(styleTitle);

        //MEARGING CELLS 
        //this statement for merging cells
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        1, //first row (0-based)
                        1, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        3, //first row (0-based)
                        3, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        5, //first row (0-based)
                        5, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        6, //first row (0-based)
                        6, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        7, //first row (0-based)
                        7, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        row = spreadsheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("From: SIMS");

        row = spreadsheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("Date: " + currentDate.format(localDate));

        row = spreadsheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("Inventory Manager No: " + userId);

//        XSSFRow row = spreadsheet.createRow(1);
//        XSSFCell cell;
        row = spreadsheet.createRow(9);
        cell = row.createCell(1);
        cell.setCellValue("CATEGORY ID");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(2);
        cell.setCellValue("PRODUCT NAME");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(3);
        cell.setCellValue("PRODUCT ID");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(4);
        cell.setCellValue("QUANTITY");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(5);
        cell.setCellValue("MFG DATE");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(6);
        cell.setCellValue("EXP DATE");
        cell.setCellStyle(styleRow1);

        int i = 10;

        while (result.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(result.getString("categoryId"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(2);
            cell.setCellValue(result.getString("productName"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(3);
            cell.setCellValue(result.getString("productId"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(4);
            cell.setCellValue(result.getInt("quantity"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(5);
            cell.setCellValue(result.getString("mfgDate"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(6);
            cell.setCellValue(result.getString("expDate"));
            cell.setCellStyle(styleRow2);

            i++;
        }

        FileOutputStream out = new FileOutputStream(new File("/home/sa/Desktop/InventoryByDate.xlsx"));
        workbook.write(out);
        out.close();
        return true;
    }
    
    public boolean exportInventoryByCategoryId(String userId, String categoryId) throws SQLException, FileNotFoundException, IOException {
        String sql = "select * from vCurrentQuantity where categoryId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, categoryId);
        preS.execute();
        ResultSet result = preS.getResultSet();

        // get current day
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime localDate = LocalDateTime.now();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(currentDate.format(localDate));

        XSSFFont fontTitle = workbook.createFont();
        fontTitle.setBold(true);
        fontTitle.setFontHeight(10);

        XSSFFont fontRow1 = workbook.createFont();
        fontRow1.setFontHeight(10);
        fontRow1.setBold(true);

        XSSFFont fontMain = workbook.createFont();
        fontMain.setBold(true);
        fontMain.setFontHeight(14);
        fontMain.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle styleTitle = workbook.createCellStyle();
        styleTitle.setFont(fontTitle);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleRow2 = workbook.createCellStyle();
        styleRow2.setBorderLeft(BorderStyle.THIN);
        styleRow2.setBorderRight(BorderStyle.THIN);
        styleRow2.setBorderTop(BorderStyle.THIN);
        styleRow2.setBorderBottom(BorderStyle.THIN);

        XSSFCellStyle styleRow1 = workbook.createCellStyle();
        styleRow1.setFont(fontRow1);
        styleRow1.setBorderLeft(BorderStyle.THIN);
        styleRow1.setBorderRight(BorderStyle.THIN);
        styleRow1.setBorderTop(BorderStyle.THIN);
        styleRow1.setBorderBottom(BorderStyle.THIN);
        styleRow1.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleMain = workbook.createCellStyle();
        styleMain.setFont(fontMain);
        styleMain.setAlignment(HorizontalAlignment.CENTER);
        styleMain.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("Inventory by category");
        cell.setCellStyle(styleMain);

        row = spreadsheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Phone: 0948 00 0949");
        cell.setCellStyle(styleTitle);

        //MEARGING CELLS 
        //this statement for merging cells
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        1, //first row (0-based)
                        1, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        3, //first row (0-based)
                        3, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        5, //first row (0-based)
                        5, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        6, //first row (0-based)
                        6, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        7, //first row (0-based)
                        7, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );

        row = spreadsheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("From: SIMS");

        row = spreadsheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("Date: " + currentDate.format(localDate));

        row = spreadsheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("Inventory Manager No: " + userId);

//        XSSFRow row = spreadsheet.createRow(1);
//        XSSFCell cell;
        row = spreadsheet.createRow(9);
        cell = row.createCell(1);
        cell.setCellValue("CATEGORY ID");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(2);
        cell.setCellValue("PRODUCT NAME");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(3);
        cell.setCellValue("PRODUCT ID");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(4);
        cell.setCellValue("QUANTITY");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(5);
        cell.setCellValue("MFG DATE");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(6);
        cell.setCellValue("EXP DATE");
        cell.setCellStyle(styleRow1);

        int i = 10;

        while (result.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(result.getString("categoryId"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(2);
            cell.setCellValue(result.getString("productName"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(3);
            cell.setCellValue(result.getString("productId"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(4);
            cell.setCellValue(result.getInt("quantity"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(5);
            cell.setCellValue(result.getString("mfgDate"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(6);
            cell.setCellValue(result.getString("expDate"));
            cell.setCellStyle(styleRow2);

            i++;
        }

        FileOutputStream out = new FileOutputStream(new File("/home/sa/Desktop/InventoryByCategory.xlsx"));
        workbook.write(out);
        out.close();
        return true;
    }
}
