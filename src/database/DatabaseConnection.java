package database;

import java.sql.Connection;
import java.sql.DriverManager;

import static constants.Constants.*;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){

        try {
            Class.forName(CLASS_NAME);
            databaseLink = DriverManager.getConnection(URL);
            //System.out.println("db connected");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

}