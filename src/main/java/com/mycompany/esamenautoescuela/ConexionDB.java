package com.mycompany.esamenautoescuela;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionDB {
    
    private Connection connection;
    private Statement statement;
    private ResultSet datosResult;
    private ArrayList<String> preguntas;
    
    public ConexionDB(File dbFile) {
        String filePath = dbFile.getPath();
        establecerConexion(filePath);
    }
    
    private void establecerConexion(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
            statement = connection.createStatement();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String[]> getExamenes() {
        ArrayList<String[]> examenes = new ArrayList<>();
        try {
            ResultSet exam = statement.executeQuery("SELECT * FROM examenes");
            while(exam.next()) {
                String a[] = {String.valueOf(exam.getObject(1)),String.valueOf(exam.getObject(2))};
                examenes.add(a);
            }
            exam.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examenes;
    }
    
    public ArrayList<String[]> getPreguntas(int id) {
        ArrayList<String[]> preguntas = new ArrayList<>();
        try {
            ResultSet exam = statement.executeQuery("SELECT * FROM preguntas WHERE examen="+id+";");
            while(exam.next()) {
                String a[] = {String.valueOf(exam.getObject(1)),String.valueOf(exam.getObject(2)),String.valueOf(exam.getObject(3))};
                preguntas.add(a);
            }
            exam.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preguntas;
    }
    
    public ArrayList<String[]> getRespuestas(int id) {
        ArrayList<String[]> respuestas = new ArrayList<>();
        try {
            ResultSet exam = statement.executeQuery("SELECT * FROM respuestas WHERE pregunta="+id+";");
            while(exam.next()) {
                String a[] = {String.valueOf(exam.getObject(1)),String.valueOf(exam.getObject(2)),String.valueOf(exam.getObject(3))};
                respuestas.add(a);
            }
            exam.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestas;
    }
}
