package com.mycompany.arturo;

import java.awt.Font;

/**
 * Clase que crea un JButton con la informacion de una respuesta de la DB
 * @author Arturo
 */
public class PreguntaButton extends javax.swing.JButton {

    private boolean  esCorrecta;

    public PreguntaButton() {
        initComponents();
    }
    
    public PreguntaButton(String pregunta, boolean correcta) {
        esCorrecta = correcta;
        initComponents();
        Font f = new Font("Tahoma", 1, 24);
        this.setFont(f);
        this.setText(pregunta);
    }
    
    public String getPregunta() {
        return this.getText();
    }
    
    public boolean esCorrecta() {
        return esCorrecta;
    }
    
    public void setEsCorrecta(boolean correcta) {
        esCorrecta = correcta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
