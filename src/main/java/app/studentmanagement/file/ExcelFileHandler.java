package app.studentmanagement.file;
import java.io.*;
import java.util.HashSet;

import app.studentmanagement.model.Data;
import app.studentmanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileHandler implements FileHandler{
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<String> groups = FXCollections.observableArrayList();
    private ObservableList<String> tableHeader = FXCollections.observableArrayList();
    private ObservableList<String[]> tableData = FXCollections.observableArrayList();
    @Override
    public void importData(String filePath) {
        students.clear();
        groups.clear();
        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String firstName = readCell(row.getCell(0));
                String lastName = readCell(row.getCell(1));

                String group = readCell(row.getCell(2));
                HashSet<String> attendanceDates = new HashSet<>();
                for (int i = 3; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        String value = readCell(cell);
                        attendanceDates.add(value);
                    }
                }
                students.add(new Student(firstName, lastName, group, attendanceDates));
            }

            // Close the workbook and file streams
            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readCell(Cell cell) {
        String value = switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
        return value;
    }

    @Override
    public void exportData(String filePath) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

            int rowNum = 0;
            Row row = spreadsheet.createRow(rowNum++);
            int colNum = 0;
            for (String head : tableHeader) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(head);
            }

            for (String[] rowData : tableData) {
                row = spreadsheet.createRow(rowNum++);
                colNum = 0;
                for (String data : rowData) {
                    Cell cell = row.createCell(colNum++);
                    cell.setCellValue(data);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData(String filePath) {
        students = Data.getInstance().getStudents();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

            // Write data to cells
            int rowNum = 0;
            for (Student s : students) {
                Row row = spreadsheet.createRow(rowNum++);
                int colNum = 0;

                Cell cell = row.createCell(colNum++);
                cell.setCellValue(s.getFirstName());
                cell = row.createCell(colNum++);
                cell.setCellValue(s.getLastName());
                cell = row.createCell(colNum++);
                cell.setCellValue(s.getGroup());

                for (String date : s.getAttendanceDates()) {
                    cell = row.createCell(colNum++);
                    cell.setCellValue(date);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public ObservableList<String> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<String> groups) {
        this.groups = groups;
    }

    public ObservableList<String> getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(ObservableList<String> tableHeader) {
        this.tableHeader = tableHeader;
    }

    public ObservableList<String[]> getTableData() {
        return tableData;
    }

    public void setTableData(ObservableList<String[]> tableData) {
        this.tableData = tableData;
    }

}
