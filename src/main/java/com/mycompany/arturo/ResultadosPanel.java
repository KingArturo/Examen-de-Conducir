package com.mycompany.arturo;

import com.sun.tools.javac.Main;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arturo
 */
public class ResultadosPanel extends javax.swing.JPanel {
    
    private ArrayList<String[]> infoPreguntas;
    private String aprobado;

    /**
     * Creates new form ResultadosPanel
     */
    public ResultadosPanel(ArrayList<String[]> info, String aprobado) {
        infoPreguntas = info;
        this.aprobado = aprobado;
        initComponents();
        aprobadoLabel.setText("El examen esta: "+this.aprobado);
        rellenarJTree();
        aprobadoLabelImage();
    }
    
    private void aprobadoLabelImage() {
        if(aprobado.equals("Aprobado")) {
            URL imageResource = Main.class.getClassLoader().getResource("aprobadoExam.png");
            aprobadoLabel.setIcon(new ImageIcon(imageResource));
        } else {
            URL imageResource = Main.class.getClassLoader().getResource("puntuacion.png");
            aprobadoLabel.setIcon(new ImageIcon(imageResource));
        }
    }
    
    private void rellenarJTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Preguntas");  
        for(String[] pre : infoPreguntas) {
            DefaultMutableTreeNode v = new DefaultMutableTreeNode(pre[0]); 
            root.add(v);     
        }
        jTree1 = new JTree(root);
        jScrollPane1.setViewportView(jTree1);
        addTreeListener();
    }
    
    private void addTreeListener() {
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                String pregunta = evt.getNewLeadSelectionPath().getLastPathComponent().toString();
                if(!pregunta.contains("Preguntas")) {
                    for(String[] pre : infoPreguntas) {
                        if(pregunta.equals(pre[0])) {
                            preguntaLabel.setText("Pregunta: "+pre[0]);
                            respuestaLabel.setText("Respuesta: "+pre[1]);
                            if(pre[2].equals("true")) {
                                correctaLabel.setText("La respuesta es: Correcta");
                            } else {
                                correctaLabel.setText("La respuesta es: Incorrecta");
                            }
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        preguntaLabel = new javax.swing.JLabel();
        respuestaLabel = new javax.swing.JLabel();
        correctaLabel = new javax.swing.JLabel();
        aprobadoLabel = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        preguntaLabel.setText("Pregunta");

        respuestaLabel.setText("Respuesta");

        correctaLabel.setText("Correcta");

        aprobadoLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        aprobadoLabel.setText("Examen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(respuestaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addComponent(preguntaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(correctaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aprobadoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aprobadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preguntaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(respuestaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correctaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aprobadoLabel;
    private javax.swing.JLabel correctaLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel preguntaLabel;
    private javax.swing.JLabel respuestaLabel;
    // End of variables declaration//GEN-END:variables
}
