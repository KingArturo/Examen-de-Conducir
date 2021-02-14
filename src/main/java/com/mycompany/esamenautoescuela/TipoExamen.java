package com.mycompany.esamenautoescuela;

public class TipoExamen extends javax.swing.JButton {
    
    private String nombre;
    private int id;
    
    public TipoExamen() {
        initComponents();
    }
    
    public TipoExamen(int id,String nombre) {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
