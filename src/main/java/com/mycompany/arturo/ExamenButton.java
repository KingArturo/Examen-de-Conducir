package com.mycompany.arturo;
/**
 * Clase que crea un boton para el examen
 * @author Arturo
 */
public class ExamenButton extends javax.swing.JButton {
    
    private String nombre;
    private int id;
    
    public ExamenButton() {
        initComponents();
    }
    /*
    * Constructor que crea el boton con la info del examen
    */
    public ExamenButton(int id,String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.setText(nombre);
        initComponents();
    }
    
    public int getId() {
        return id;
    }
    
    public String nombre() {
        return nombre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
