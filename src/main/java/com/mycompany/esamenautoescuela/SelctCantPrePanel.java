package com.mycompany.esamenautoescuela;

import java.net.URL;
import javax.swing.JSlider;
import com.sun.tools.javac.Main;
import javax.swing.ImageIcon;
/**
 *
 * @author Arturo
 */
public class SelctCantPrePanel extends javax.swing.JPanel {

    /**
     * Creates new form SelctCantPrePanel
     */
    public SelctCantPrePanel() {
        initComponents();
    }

    public int getValue() {
        return jSlider1.getValue();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider(JSlider.HORIZONTAL, 5, 10, 10);
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Selecciona la cantidad de Preguntas");

        jSlider1.setMaximum(10);
        jSlider1.setMinimum(5);
        jSlider1.setPaintTicks(true);
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSlider1.setPaintLabels(true);

        jLabel2.setPreferredSize(new java.awt.Dimension(128, 128));
        URL imageResource = Main.class.getClassLoader().getResource("informacion.png");
        jLabel2.setIcon(new ImageIcon(imageResource));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
