package com.mycompany.arturo;

import java.net.URL;
import javax.swing.JSlider;
import com.sun.tools.javac.Main;
import javax.swing.ImageIcon;
/**
 * Clase que crea un JPanel que contiene unSlider para seleccionar 
 * la cantidad de preguntas de los examenes
 * @author Arturo
 */
public class SelctCantPrePanel extends javax.swing.JPanel {

    public SelctCantPrePanel() {
        initComponents();
    }

    public int getValue() {
        return cantPreSlider.getValue();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cantPreLabel = new javax.swing.JLabel();
        cantPreSlider = new javax.swing.JSlider(JSlider.HORIZONTAL, 5, 10, 10);
        imageLabel = new javax.swing.JLabel();

        cantPreLabel.setText("Selecciona la cantidad de Preguntas");

        cantPreSlider.setMaximum(10);
        cantPreSlider.setMinimum(5);
        cantPreSlider.setPaintTicks(true);
        cantPreSlider.setMajorTickSpacing(1);
        cantPreSlider.setMinorTickSpacing(1);
        cantPreSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cantPreSlider.setPaintLabels(true);

        imageLabel.setPreferredSize(new java.awt.Dimension(64, 64));
        URL imageResource = Main.class.getClassLoader().getResource("informacion.png");
        imageLabel.setIcon(new ImageIcon(imageResource));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cantPreLabel)
                    .addComponent(cantPreSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cantPreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cantPreSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cantPreLabel;
    private javax.swing.JSlider cantPreSlider;
    private javax.swing.JLabel imageLabel;
    // End of variables declaration//GEN-END:variables
}
