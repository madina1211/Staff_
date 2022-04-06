package model.controller;

import database.DatabaseConnection;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.*;
import java.sql.Connection;

import static constants.Constants.*;

public class ImageController {
/*
    private Connection connection;

    private void SaveFileToDatabase()
    {
        connection = new DatabaseConnection().getConnection();
        try
        {

            // путь к файлу для загрузки
            String filename = "E:\\IntelliG IDEA\\PROJECTS\\Staff\\src\\model\\images\\female.png";

            byte[] person_img = null;

            try
            {
                File image = new File(filename);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum=fis.read(buf))!=-1;) {
                    bos.write(buf,0, readNum);
                }

                person_img = bos.toByteArray();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }
    }
*/
}
