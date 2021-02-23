package com.mycompany.arturo;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de las conexiones con la base de datos
 * @author Arturo
 */
public class ConexionDB {
    
    private Connection connection;
    private Statement statement;
    private int examenActual;
    
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
    
    /*
    * Devuelve un ArratList de arrays de String con los examenes
    */
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
    
    /*
    * Devuelve un ArratList de arrays de String con las preguntas de un examen
    */
    public ArrayList<String[]> getPreguntas(int id) {
        ArrayList<String[]> preguntas = new ArrayList<>();
        examenActual=id;
        try {
            ResultSet exam = statement.executeQuery("SELECT * FROM preguntas WHERE examen="+id+";");
            while(exam.next()) {
                String a[] = {String.valueOf(exam.getObject(1)),String.valueOf(exam.getObject(2)),String.valueOf(exam.getObject(3)),String.valueOf(exam.getObject(4))};
                preguntas.add(a);
            }
            exam.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preguntas;
    }
    /*
    * Devuelve un ArratList de arrays de String con las respuestas de una pregunta
    */
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
    /*
    * Inserta en la tabla registro los aciertos de un examen
    */
    public void setRegistro(int aciertos) {
        try {
            statement.execute("INSERT INTO registro VALUES("+aciertos+","+examenActual+");");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    * Inserte en la tabla preguntas un pregunta
    */
    public boolean setPregunta(String pregunta, String imagen, int examen) {
        boolean insertado = false;
        try {
            ResultSet examenes = statement.executeQuery("SELECT * FROM preguntas ORDER BY id DESC");
            int num = 1;
            if(examenes.next()) {
                num = examenes.getInt(1);
            }
            int insert = statement.executeUpdate("INSERT INTO preguntas VALUES ("+(num+1)+", '"+pregunta+"', '"+imagen+"', "+examen+");");
            if(insert > 0) {
                insertado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insertado;
    }
    /*
    * Inserta en la tabla respuestas una respuesta
    */
    public void setRespuesta(String respuesta, String correcta) {
        try {
            ResultSet examenes = statement.executeQuery("SELECT * FROM preguntas ORDER BY id DESC");
            int num = 1;
            if(examenes.next()) {
                num = examenes.getInt(1);
            }
            examenes = statement.executeQuery("SELECT * FROM respuestas ORDER BY id DESC");
            int idRespuesta = 1;
            if(examenes.next()) {
                idRespuesta = examenes.getInt(1)+1;
            }
            int insert = statement.executeUpdate("INSERT INTO respuestas VALUES ("+idRespuesta+",'"+respuesta+"','"+correcta+"',"+num+");");

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    * Devuelve un ArratList de arrays de String con los examenes hecho y si estan o no aprobados
    */
    public ArrayList<String[]> getRegistros() {
        ArrayList<String[]> registros = new ArrayList<>();
        try {
            ResultSet exam = statement.executeQuery("SELECT id FROM examenes;");
            ArrayList<Integer> a = new ArrayList<>();
            while(exam.next()) {
                a.add(exam.getInt(1));
            }
            exam.close();
            for(int i : a) {
                exam = statement.executeQuery("SELECT aciertos, nombre "
                        + "FROM registro, examenes WHERE examenes.id=examen AND examen="+i+";");
                String[] re = new String[3];
                String[] es = new String[3];
                String[] sus = new String[3];
                int cont = 0, apr=0,suspen=0;
                while(exam.next()) {
                    cont++;
                    re[0] = String.valueOf(cont);
                    re[1] = String.valueOf("Total");
                    re[2] = String.valueOf(exam.getObject(2));
                    if(exam.getInt(1) < 4) {
                        apr++;
                        es[0] = String.valueOf(apr);
                        es[1] = "Aprobado";
                        es[2] = String.valueOf(exam.getObject(2));
                    } else {
                        suspen++;
                        sus[0] = String.valueOf(suspen);
                        sus[1] = "Suspenso";
                        sus[2] = String.valueOf(exam.getObject(2));
                    }
                }
                if(re[0]!=null) {
                registros.add(re);
                }
                if(es[0]!=null) {
                    registros.add(es);
                }
                if(sus[0]!=null) {
                    registros.add(sus);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return registros;
    }
}
