
package com.mycompany.esamenautoescuela;

import com.sun.tools.javac.Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ElegirPreguntasFrame extends javax.swing.JFrame {

    private JEditorPane editorPane1;
    private File preguntasfile;
    private ConexionDB conexion;
    
    public ElegirPreguntasFrame() {
         preguntasfile = null;
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
         initComponents();
         addElegirButtonListener();
         addEmpezarButtonListener();
    }
    
        
    @Override
    public Image getIconImage() {
        URL imageResource = Main.class.getClassLoader().getResource("prueba.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    
    
    /*
    *   Metodo que abre un file chooser para elegir una base de datos con preguntas
    */
    private void addElegirButtonListener() {
         elegirButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {     
                           editorPane1 = new JEditorPane(); 
                           FileNameExtensionFilter filter = new FileNameExtensionFilter("DataBase", "db");
                           JFileChooser fc = new JFileChooser();
                           fc.setFileFilter(filter);
                           int seleccion = fc.showOpenDialog(editorPane1);
                           if (seleccion == JFileChooser.APPROVE_OPTION){
                               preguntasfile = fc.getSelectedFile();
                               conexion = new ConexionDB(preguntasfile);
                           }
                  }
        });  
    }
    
    private void addEmpezarButtonListener() {
        startButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {     
                      if(preguntasfile != null) {
                        TestFrame test = new TestFrame(conexion);
                        test.setVisible(true);
                        closeFrame();
                      } else {
                          advertencia();
                      }

                  }
        });  
    }
    
    private void advertencia() {
        JOptionPane.showConfirmDialog(this, "No has cogido las preguntas", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    private void closeFrame() {
        this.dispose();
    }


    /*@SuppressWarnings("unchecked");*/
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        elegirButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setResizable(false);

        elegirButton.setText("Elegir Preguntas");
        URL imageResource = Main.class.getClassLoader().getResource("mas.png");
        elegirButton.setIcon(new ImageIcon(imageResource));

        // Texto en el centro y debajo del icono
        elegirButton.setHorizontalTextPosition( SwingConstants.LEFT );
        elegirButton.setHorizontalTextPosition( SwingConstants.RIGHT );

        startButton.setText("Empezar");
        imageResource = Main.class.getClassLoader().getResource("play.png");
        startButton.setIcon(new ImageIcon(imageResource));

        // Texto en el centro y debajo del icono
        startButton.setHorizontalTextPosition( SwingConstants.LEFT );
        startButton.setHorizontalTextPosition( SwingConstants.RIGHT );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(elegirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(elegirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterContrastIJTheme());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ElegirPreguntasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ElegirPreguntasFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton elegirButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
