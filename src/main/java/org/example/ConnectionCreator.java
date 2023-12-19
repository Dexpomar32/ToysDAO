package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Properties PROPERTIES = new Properties();
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    static {
        try {
            PROPERTIES.load(new FileReader(("src/main/resources/application.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DATABASE_URL = (String) PROPERTIES.get("db.url");
        DATABASE_USER = (String) PROPERTIES.get("db.user");
        DATABASE_PASSWORD = (String) PROPERTIES.get("db.password");
    }

    private ConnectionCreator() {}

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}