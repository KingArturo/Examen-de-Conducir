package com.mycompany.esamenautoescuela;

import java.io.File;
import java.sql.*;


public class ConexionDB {
    
    private Connection connection;
    private Statement statement;
    private ResultSet datosResult;
    
    public ConexionDB(File dbFile) {
        String filePath = dbFile.getPath();
    }
    
    private void establecerConexion(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
            statement = connection.createStatement();
            ResultSet tablas = statement.executeQuery("SELECT name FROM sqlite_master "+
                "WHERE type='table';");
            String tabla = "";
            tablas.next();
            tabla = tablas.getString("name");
            
            datosResult = statement.executeQuery("SELECT * FROM "+tabla+";");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
