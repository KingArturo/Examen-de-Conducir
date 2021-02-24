
package com.mycompany.arturo;

import com.sun.tools.javac.Main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase que crea un JPanel con una pregunta y las respuestas
 * asi como la imagen relacionada con la pregunta
 * @author Arturo
 */
public class PreguntasPanel extends javax.swing.JPanel {

    private ArrayList<String[]> preguntas;
    private ArrayList<String[]> infoRespuestas;
    private String pregunta[];
    private PreguntaButton[] pre;
    private int preguntasRespondidas;
    private static int Cantidad_Preguntas = 10;
    private boolean Examen_Finalizado = false;
    private int aciertos;
    private ConexionDB db;
    /*
    * Constructor que crea el panel sin preguntas
    */
    public PreguntasPanel() {
        this.setLayout(new FlowLayout());
        JLabel a = new JLabel(); 
        a.setBounds(100, 50, 256, 256);
        URL imageResource = Main.class.getClassLoader().getResource("logo.png");
        //a.setIcon(new ImageIcon(".\\.\\resources\\logo.png"));
        a.setIcon(new ImageIcon(imageResource));
        this.add(a);
        this.repaint();
    }
    /*
    * Constructor que crea el panel con la pregunta y sus posibles respuestas
    */
    public PreguntasPanel(ArrayList<String[]> preguntas, ConexionDB db, int cantPreguntas) {
        this.db = db;
        infoRespuestas = new ArrayList<>();
        Cantidad_Preguntas = cantPreguntas;
        Collections.shuffle(preguntas);
        this.preguntas = preguntas;
        aciertos = 0;
        preguntasRespondidas = 1;
        initComponents();
        setNumeroPreguntaLabel();
        pregunta = cogerPregunta();
        formato();
        temporizador();
    }
    // Crea el temporizador que determina el tiempo del examen
    private void temporizador() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int seg = 0;
                int min = 5;
                while((min != 0 || seg != 0) && !Examen_Finalizado) {
                    try {
                        String s = "";
                        if(seg==0) {
                            min--;
                        }
                        seg--;
                        if(seg<0) {
                            seg=59;
                        }
                        s=""+min+":";
                        if(seg<10) {
                            s +="0";
                        }
                        s += ""+seg;
                        labelTiempo.setText(s);
                        Thread.sleep(999);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PreguntasPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Examen_Finalizado=true;
                db.setRegistro((Cantidad_Preguntas-aciertos));
            }
        });
        t.start();
    }
    // Coge una pregunta del ArraList preguntas
    private String[] cogerPregunta() {
        return preguntas.get(preguntasRespondidas-1);
    }
    /*
    * Pone lad pregunta en el lable labelPregunta y crea las respuestas
    */
    private void formato() {
        String[] a = pregunta;
        labelPregunta.setText("<html>"+a[1]+"</html>");
        URL imageResource = Main.class.getClassLoader().getResource(a[2]);
        //labelImagen.setIcon(new ImageIcon(".\\.\\resources\\"+a[2]));
        labelImagen.setIcon(new ImageIcon(imageResource));
        pre = new PreguntaButton[4];
        int cont = 0;
        ArrayList<String[]> respuestas = db.getRespuestas(Integer.parseInt(a[0]));
        while(cont < respuestas.size()) {
            String p[] = respuestas.get(cont);
            pre[cont] = new PreguntaButton(p[1], Boolean.parseBoolean(p[2]));
            cont++;
        }
        respuestasAleatorias(pre);
    }
    
    /*
    * metodo que ordena las respuestas de manera aleatoria
    */
    private void respuestasAleatorias(PreguntaButton[] pre) {
        String num = "";
        panelRespuestas.removeAll();
        int cont = 0;
        while(cont < 4) {
            Random rn = new Random();
            int n= rn.nextInt(4);
            if(!num.contains(String.valueOf(n))) {
                panelRespuestas.add(pre[n]); 
                addrespuestasListener(pre[n]);
                num += String.valueOf(n);
                cont++;
            }
        }        
        panelRespuestas.repaint();
        scrollPaneRespuesta.repaint();
    }
    
    public void preguntaAcertada() {
        aciertos++;
    }
    
    public void preguntaRespondida() {
        ProgressBar.setValue(preguntasRespondidas);
    }
    
    private void setNumeroPreguntaLabel() {
        numeroPreguntaLabel.setText(String.valueOf(preguntasRespondidas)+" de "+Cantidad_Preguntas);
    }
    /*
    * Añade a los botones de las respuestas un listener que comprueba
    * se la pregunta pulsada es correcta o no
    */
    private void addrespuestasListener(PreguntaButton pre) {
        pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(!Examen_Finalizado) {
                    if(pre.esCorrecta()) {
                        preguntaAcertada();
                    }
                    String[] pregunt = {pregunta[1], pre.getPregunta(),String.valueOf(pre.esCorrecta())};
                    infoRespuestas.add(pregunt);
                    if(preguntasRespondidas < (Cantidad_Preguntas)) {
                      preguntasRespondidas++;
                      pregunta = cogerPregunta();
                      formato();
                      preguntaRespondida();
                    } else {
                        advertencia();
                    }
                    setNumeroPreguntaLabel();
                }
            }
        });  
    }
    /*
    * Muestra un dialogo que muestra si has aprobado o no y la cantidad de aciertos
    */
    private void advertencia() {
        Examen_Finalizado = true;
        if(aciertos > (Cantidad_Preguntas-4)) {
            ResultadosPanel result = new ResultadosPanel(infoRespuestas, "Aprobado");
            JOptionPane.showConfirmDialog(null,result,"Examen",JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE);
        } else {
            ResultadosPanel result = new ResultadosPanel(infoRespuestas, "Suspenso");
            JOptionPane.showConfirmDialog(null,result,"Examen",JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE);
        }
        db.setRegistro((Cantidad_Preguntas-aciertos));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPregunta = new javax.swing.JLabel();
        scrollPaneRespuesta = new javax.swing.JScrollPane();
        panelRespuestas = new javax.swing.JPanel();
        ProgressBar = new javax.swing.JProgressBar(0,Cantidad_Preguntas);
        jScrollPane2 = new javax.swing.JScrollPane();
        labelImagen = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        numeroPreguntaLabel = new javax.swing.JLabel();

        labelPregunta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelPregunta.setText("Pregunta");

        javax.swing.GroupLayout panelRespuestasLayout = new javax.swing.GroupLayout(panelRespuestas);
        panelRespuestas.setLayout(panelRespuestasLayout);
        panelRespuestasLayout.setHorizontalGroup(
            panelRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        panelRespuestasLayout.setVerticalGroup(
            panelRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        panelRespuestas.setLayout(new GridLayout(4,1));

        scrollPaneRespuesta.setViewportView(panelRespuestas);

        ProgressBar.setValue(1);
        ProgressBar.setStringPainted(true);

        labelImagen.setPreferredSize(new java.awt.Dimension(256, 256));
        jScrollPane2.setViewportView(labelImagen);

        labelTiempo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTiempo.setText("jLabel3");

        numeroPreguntaLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        numeroPreguntaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroPreguntaLabel.setText("PreguntaNum");
        numeroPreguntaLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(numeroPreguntaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPaneRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneRespuesta)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroPreguntaLabel)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelPregunta;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JLabel numeroPreguntaLabel;
    private javax.swing.JPanel panelRespuestas;
    private javax.swing.JScrollPane scrollPaneRespuesta;
    // End of variables declaration//GEN-END:variables
}
