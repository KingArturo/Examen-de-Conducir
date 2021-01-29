package com.mycompany.esamenautoescuela;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;


public class ConexionDB {
    
    private Connection connection;
    private Statement statement;
    private ResultSet datosResult;
    private ArrayList<String> preguntas;
    
    public ConexionDB(File dbFile) {
        String filePath = dbFile.getPath();
        preguntas = new ArrayList<>();
        establecerConexion(filePath);
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
            
            while(datosResult.next()) {
                preguntas.add(String.valueOf(datosResult.getObject(1))+"-"+String.valueOf(datosResult.getObject(2)));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getPreguntas() {
        return preguntas;
    }
}
