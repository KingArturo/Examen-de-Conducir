
package com.mycompany.esamenautoescuela;

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
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class PreguntasPanel extends javax.swing.JPanel {

    private ArrayList<String[]> preguntas;
    private String pregunta[];
    private PreguntaPanel[] pre;
    private int preguntasRespondidas;
    private static int Cantidad_Preguntas = 10;
    private boolean Examen_Finalizado = false;
    private int aciertos;
    private ConexionDB db;

    public PreguntasPanel() {
        this.setLayout(new FlowLayout());
        JLabel a = new JLabel(); 
        a.setBounds(100, 50, 256, 256);
        URL imageResource = Main.class.getClassLoader().getResource("page-not-found.png");
        a.setIcon(new ImageIcon(imageResource));
        this.add(a);
        this.repaint();
    }
    
    public PreguntasPanel(ArrayList<String[]> preguntas, ConexionDB db, int cantPreguntas) {
        this.db = db;
        Cantidad_Preguntas = cantPreguntas;
        Collections.shuffle(preguntas);
        this.preguntas = preguntas;
        aciertos = 0;
        preguntasRespondidas = 0;
        initComponents();
        pregunta = cogerPregunta();
        formato();
        Thread t = new Thread(new Runnable() {
            public void run() {
                int seg = 0;
                int min = 5;
                while(min != 0 || seg != 0) {
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
                preguntasRespondidas=10;
            }
        });
        t.start();
    }
    
    private String[] cogerPregunta() {
        Random rn = new Random();
        int num = rn.nextInt(preguntas.size());
        return preguntas.get(preguntasRespondidas);
    }
    
    private void formato() {
        String[] a = pregunta;
        labelPregunta.setText(a[1]);
        URL imageResource = Main.class.getClassLoader().getResource(a[2]);
        labelImagen.setIcon(new ImageIcon(imageResource));
        pre = new PreguntaPanel[4];
        int cont = 0;
        ArrayList<String[]> respuestas = db.getRespuestas(Integer.parseInt(a[0]));
        while(cont < respuestas.size()) {
            String p[] = respuestas.get(cont);
            pre[cont] = new PreguntaPanel(p[1], Boolean.parseBoolean(p[2]));
            cont++;
        }
        respuestasAleatorias(pre);
    }
    
    /*
    * metodo que ordena las respuestas de manera aleatoria
    */
    private void respuestasAleatorias(PreguntaPanel[] pre) {
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
        jProgressBar1.setValue(preguntasRespondidas+1);
    }
    
    private void addrespuestasListener(PreguntaPanel pre) {
        pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(!Examen_Finalizado) {
                    if(pre.esCorrecta()) {
                        preguntaAcertada();
                    }
                    if(preguntasRespondidas < (Cantidad_Preguntas-1)) {
                      preguntasRespondidas++;
                      pregunta = cogerPregunta();
                      formato();
                      preguntaRespondida();
                    } else {
                        advertencia();
                    }
                }
            }
        });  
    }
    
    private void advertencia() {
        if(aciertos > (Cantidad_Preguntas*0.6)) {
            JOptionPane.showConfirmDialog(this, "Has probado\nacertado "+aciertos+" de "+Cantidad_Preguntas, "Bien", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showConfirmDialog(this, "Has suspendido\nacertado "+aciertos+" de "+Cantidad_Preguntas, "Bien", JOptionPane.WARNING_MESSAGE);
        }
        Examen_Finalizado = true;
        db.setRegistro((Cantidad_Preguntas-aciertos));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPregunta = new javax.swing.JLabel();
        scrollPaneRespuesta = new javax.swing.JScrollPane();
        panelRespuestas = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar(0,Cantidad_Preguntas);
        jScrollPane2 = new javax.swing.JScrollPane();
        labelImagen = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();

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

        jProgressBar1.setValue(1);
        jProgressBar1.setStringPainted(true);

        labelImagen.setPreferredSize(new java.awt.Dimension(256, 256));
        jScrollPane2.setViewportView(labelImagen);

        labelTiempo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTiempo.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelPregunta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPaneRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPregunta)
                    .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelPregunta;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JPanel panelRespuestas;
    private javax.swing.JScrollPane scrollPaneRespuesta;
    // End of variables declaration//GEN-END:variables
}
