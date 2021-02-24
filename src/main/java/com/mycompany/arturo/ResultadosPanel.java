package com.mycompany.arturo;

import com.sun.tools.javac.Main;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Clase que muestra un Frame con la informacion de las preguntas respondidas
 * en un examen
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
        setPregunta(0);
        aprobadoLabel.setText("El examen esta: "+this.aprobado);
        addListListener();
        aprobadoLabelImage();
    }
    /*
    * Rellena los textField con la informacion de una de las preguntas de ArrayList
    */
    private void setPregunta(int pos) {
        String pregunta = infoPreguntas.get(pos)[0];
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
    /*
    * Inserta una imgen en el aprobadoLabel que varia si has aprobado o no el examen
    */
    private void aprobadoLabelImage() {
        if(aprobado.equals("Aprobado")) {
            URL imageResource = Main.class.getClassLoader().getResource("aprobadoExam.png");
            aprobadoLabel.setIcon(new ImageIcon(imageResource));
        } else {
            URL imageResource = Main.class.getClassLoader().getResource("puntuacion.png");
            aprobadoLabel.setIcon(new ImageIcon(imageResource));
        }
    }
    /*
    * Añade un listener al slider que detecta cuando se efectua un cambio y 
    * coge el valor actual del slider para mostrar la info de una pregunt
    */
    private void addListListener() {
        preguntasSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int value = preguntasSlider.getValue();
                setPregunta(value-1);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preguntaLabel = new javax.swing.JLabel();
        respuestaLabel = new javax.swing.JLabel();
        correctaLabel = new javax.swing.JLabel();
        aprobadoLabel = new javax.swing.JLabel();
        preguntasSlider = new javax.swing.JSlider(JSlider.HORIZONTAL, 1, infoPreguntas.size(), 1);

        preguntaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        preguntaLabel.setText("Pregunta");

        respuestaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        respuestaLabel.setText("Respuesta");

        correctaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        correctaLabel.setText("Correcta");

        aprobadoLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        aprobadoLabel.setText("Examen");

        preguntasSlider.setToolTipText("Selecciona la pregunta que quieres ver");
        preguntasSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(correctaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(respuestaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                            .addComponent(preguntaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(preguntasSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addComponent(aprobadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aprobadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preguntaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respuestaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correctaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preguntasSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        preguntasSlider.setMaximum(infoPreguntas.size());

        preguntasSlider.setMinimum(1);

        preguntasSlider.setPaintTicks(true);
        preguntasSlider.setMajorTickSpacing(1);
        preguntasSlider.setMinorTickSpacing(1);
        preguntasSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        preguntasSlider.setPaintLabels(true);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aprobadoLabel;
    private javax.swing.JLabel correctaLabel;
    private javax.swing.JLabel preguntaLabel;
    private javax.swing.JSlider preguntasSlider;
    private javax.swing.JLabel respuestaLabel;
    // End of variables declaration//GEN-END:variables
}
