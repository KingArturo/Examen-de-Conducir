/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arturo;

import com.sun.tools.javac.Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Arturo
 */
public class AddPreguntaFrame extends javax.swing.JFrame {

    private ConexionDB db;
    
    /**
     * Creates new form AddPreguntaFrame
     */
    public AddPreguntaFrame() {
        initComponents();
    }
    
    
    public AddPreguntaFrame(ConexionDB db) {
        this.db = db;
        initComponents();
        setExamenesComboBox();
        addAñadirButtonListener();
        addFotoButtonListener();
    }
    
    @Override
    public Image getIconImage() {
        URL imageResource = Main.class.getClassLoader().getResource("prueba.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    
    private void setExamenesComboBox() {
        for(String[] a : db.getExamenes()) {
            examenesComboBox.addItem(a[0]+"-"+a[1]);
        }
    }
    
    private void clean() {
        examenesComboBox.setSelectedIndex(0);
        preguntaTextArea.setText(null);
        correctaTextField.setText("Correcta");
        respuesta2TextField.setText("False1");
        respuesta3TextField.setText("False2");
        respuesta4TextField.setText("False3");
    }
    
    private void addAñadirButtonListener() {
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(examenesComboBox.getSelectedItem()).split("-")[0];
                boolean insertado = db.setPregunta(preguntaTextArea.getText(), imageLabel.getText(), Integer.parseInt(id));
                db.setRespuesta(correctaTextField.getText(),"true");
                db.setRespuesta(respuesta2TextField.getText(),"false");
                db.setRespuesta(respuesta3TextField.getText(),"false");
                db.setRespuesta(respuesta4TextField.getText(),"false");
                clean();
            }
        });  
    }
    
    private void addFotoButtonListener() {
        fotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JEditorPane editorPane1 = new JEditorPane(); 
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "png","jpg");
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(filter);
                int seleccion = fc.showOpenDialog(editorPane1);
                if (seleccion == JFileChooser.APPROVE_OPTION){
                    imageLabel.setText(fc.getSelectedFile().getName());
                    File destino = new File("src/main/resources",fc.getSelectedFile().getName());
                    System.out.println(destino.getAbsolutePath());
                    try {
                        Files.copy(Paths.get(fc.getSelectedFile().getAbsolutePath())
                                , Paths.get(destino.getAbsolutePath())
                                , StandardCopyOption.REPLACE_EXISTING);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }                    
                    Image img = new ImageIcon(fc.getSelectedFile().getAbsolutePath()).getImage();
                    Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(newimg); 
                    imageLabel.setIcon(imageIcon);
                }
            }
        });  
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        examenesComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        preguntaTextArea = new javax.swing.JTextArea();
        examenLabel = new javax.swing.JLabel();
        preguntaLabel = new javax.swing.JLabel();
        correctaTextField = new javax.swing.JTextField();
        respuesta2TextField = new javax.swing.JTextField();
        respuesta3TextField = new javax.swing.JTextField();
        respuesta4TextField = new javax.swing.JTextField();
        correctaLabel = new javax.swing.JLabel();
        false1Label = new javax.swing.JLabel();
        false2Label = new javax.swing.JLabel();
        false3Label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        imagenLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        fotoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        preguntaTextArea.setColumns(20);
        preguntaTextArea.setRows(5);
        jScrollPane1.setViewportView(preguntaTextArea);

        examenLabel.setText("Examen");

        preguntaLabel.setText("Pregunta");

        correctaTextField.setText("Correcta");

        respuesta2TextField.setText("False1");

        respuesta3TextField.setText("False2");

        respuesta4TextField.setText("False3");

        correctaLabel.setText("Respuesta 1(Correcta)");

        false1Label.setText("Respuesta 2");

        false2Label.setText("Respuesta 3");

        false3Label.setText("Respuesta 4");

        jButton1.setText("Añadir");

        imagenLabel.setText("Imagen");

        imageLabel.setText("no-photo.png");

        fotoButton.setText("Foto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(examenLabel)
                            .addComponent(preguntaLabel)
                            .addComponent(correctaLabel)
                            .addComponent(false1Label)
                            .addComponent(false2Label)
                            .addComponent(false3Label))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(respuesta2TextField)
                                    .addComponent(correctaTextField)
                                    .addComponent(respuesta3TextField)
                                    .addComponent(respuesta4TextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fotoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(examenesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagenLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(examenesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examenLabel))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preguntaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correctaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(respuesta2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(false1Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(respuesta3TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(false2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(respuesta4TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(false3Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imagenLabel)
                        .addComponent(imageLabel))
                    .addComponent(fotoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddPreguntaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPreguntaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPreguntaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPreguntaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPreguntaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel correctaLabel;
    private javax.swing.JTextField correctaTextField;
    private javax.swing.JLabel examenLabel;
    private javax.swing.JComboBox<String> examenesComboBox;
    private javax.swing.JLabel false1Label;
    private javax.swing.JLabel false2Label;
    private javax.swing.JLabel false3Label;
    private javax.swing.JButton fotoButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel preguntaLabel;
    private javax.swing.JTextArea preguntaTextArea;
    private javax.swing.JTextField respuesta2TextField;
    private javax.swing.JTextField respuesta3TextField;
    private javax.swing.JTextField respuesta4TextField;
    // End of variables declaration//GEN-END:variables
}
