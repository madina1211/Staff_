package constants;

public interface Constants {

    String DATABASE_NAME = "Staff_exmaple";
    String DATABASE_USER = "sa";
    String DATABASE_PASSWORD = "20021112M_m";
    String URL = "jdbc:sqlserver://DESKTOP-GOSO5J2\\MSSQLSERVER01;databaseName=" + DATABASE_NAME + ";user=" + DATABASE_USER + ";password=" + DATABASE_PASSWORD;
    String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String SUCCESSFUL_ADDING_WORKER = "Data has been added successfully!";
    String UNSUCCESSFUL_ADDING_WORKER = "Error! Data hasn't been added!";
    String SUCCESSFUL_UPDATE_WORKER = "Data has been edited successfully!";
    String UNSUCCESSFUL_UPDATE_WORKER = "Error! Data hasn't been edited!";
    String SUCCESSFUL_DELETE_WORKER = "Data has been deleted successfully!";
    String UNSUCCESSFUL_DELETE_WORKER = "Error! Data hasn't been deleted!";
    String QUERY_FOURTH = "SELECT FIO, DATEDIFF(YEAR, hiringDate,GETDATE()) as workExperience\n" +
            "FROM worker \n" +
            "Where firingDate IS NULL AND DATEDIFF(YEAR, hiringDate,GETDATE()) BETWEEN 3 AND 20;\n";
    String REPORT_FIRST = "SELECT * FROM scheduleTest";
    String REPORT_SECOND = "SELECT * FROM results4thGroup";
    String PROCEDURE_FIRST = "EXECUTE Vacancy ?";
    String PROCEDURE_MIN_SALARY = "EXECUTE MinSalary ?";
    String VIEW_DEPARTMENT_LIST = "SELECT * FROM departmentList";
    String VIEW_EDUCATION_LIST = "SELECT * FROM educationList";
    String PROCEDURE_SECOND = "EXECUTE ListOfWorkers ?, ?";
    String PROCEDURE_FOURTH = "EXECUTE ageAbiturent ?";
    String FILE_PATH_TO = "src\\report\\";
    String EXCEL_FILE_DIRECTORY = "E:\\IntelliG IDEA\\PROJECTS\\Staff\\src\\report";
    String TITLE_SAVE_AS = "Save As";
    String SUCCESSFUL_EXPORT_DATA = "Report's data has been successfully exported!";
    String UNSUCCESSFUL_EXPORT_DATA = "Erorr! Report's data hasn't been successfully exported!";
    String REGEX_FILENAME = "^.*\\.(csv|xlsx|xls|xlsm)$";
    String CSV_FORMAT = ".csv";
    String SELECT_ALL_FROM_WORKER = "SELECT * FROM worker";
    String INSERT_INTO_WORKER = "INSERT INTO worker (FIO, address, phone, birthDate, nationalityID, sex, \n" +
            "educationID, dependentNumber, UIN, pensionFundID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    String DELETE_DATA_FROM_WORKER = "DELETE FROM worker WHERE workerID = ";
    String UPDATE_DATA_WORKER = "UPDATE worker\n" +
            "SET FIO = ? , address = ?, phone = ?, birthDate = ?, nationalityID = ?, sex = ?, \n" +
            "educationID = ?, dependentNumber = ?, salary = ?, UIN = ?, \n" +
            "pensionFundID = ?, positionID = ?, departmentID = ?\n" +
            "WHERE workerID = ";
    String SELECT_ALL_FROM_STAFFING_TABLE = "SELECT * FROM staffTable";
    String SELECT_ALL_FROM_ORDER = "SELECT * FROM orders";
    String INSERT_INTO_ORDER = "INSERT INTO orders (dateOrder, workerID, orderTypeID) " +
            "VALUES (?, ?, ?)";
    String SUCCESSFUL_ADDING_ORDER = "Order has been added successfully!";
    String UNSUCCESSFUL_ADDING_ORDER = "Error! Order hasn't been added!";
}
