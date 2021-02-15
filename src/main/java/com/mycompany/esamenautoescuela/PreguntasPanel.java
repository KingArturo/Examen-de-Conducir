
package com.mycompany.esamenautoescuela;

import com.sun.tools.javac.Main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
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
    private int preguntasRespondidas;
    private static final int Cantidad_Preguntas = 10;
    private static final int tiempo = 5;
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
    
    public PreguntasPanel(ArrayList<String[]> preguntas, ConexionDB db) {
        this.db = db;
        this.preguntas = preguntas;
        aciertos = 0;
        preguntasRespondidas = 0;
        initComponents();
        jButton3.putClientProperty("JComponent.outline", "error");
        pregunta = cogerPregunta();
        formato();
        addButtonListener();
        Thread t = new Thread(new Runnable() {
            public void run() {
                int seg = 0;
                int min = 5;
                while(min != 0) {
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
                        jLabel3.setText(s);
                        Thread.sleep(999);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PreguntasPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();
    }
    
    private String[] cogerPregunta() {
        Random rn = new Random();
        int num = rn.nextInt(preguntas.size());
        System.out.println(preguntasRespondidas);
        return preguntas.get(preguntasRespondidas);
    }
    
    private void formato() {
        String[] a = pregunta;
        jLabel2.setText(a[1]);
        URL imageResource = Main.class.getClassLoader().getResource(a[2]);
        jLabel1.setIcon(new ImageIcon(imageResource));
        PreguntaPanel[] pre = new PreguntaPanel[4];
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
        jPanel1.removeAll();
        int cont = 0;
        while(cont < 4) {
            Random rn = new Random();
            int n= rn.nextInt(4);
            if(!num.contains(String.valueOf(n))) {
                jPanel1.add(pre[n]); 
                addrespuestasListener(pre[n]);
                num += String.valueOf(n);
                cont++;
            }
        }        
        jPanel1.repaint();
        jScrollPane1.repaint();
    }
    
    public void preguntaAcertada() {
        aciertos++;
    }
    
    public void preguntaRespondida() {
        jProgressBar1.setValue(preguntasRespondidas+1);
    }

    private void addButtonListener() {
        jButton3.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {     
                      pregunta = cogerPregunta();
                      formato();
                  }
        });  
    }
    
    private void addrespuestasListener(PreguntaPanel pre) {
        pre.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) { 
                      if(preguntasRespondidas < (Cantidad_Preguntas-1)) {
                        if(pre.esCorrecta()) {
                            preguntaAcertada();
                        }
                        preguntasRespondidas++;
                        pregunta = cogerPregunta();
                        formato();
                        preguntaRespondida();
                      } else {
                          advertencia();
                      }
                  }
        });  
    }
    
    private void advertencia() {
        JOptionPane.showConfirmDialog(this, "Has acertado "+aciertos+" de "+Cantidad_Preguntas, "Bien", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar(0,10);
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Pregunta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new GridLayout(4,1));

        jScrollPane1.setViewportView(jPanel1);

        jButton3.setText("Recargar");

        jProgressBar1.setValue(1);
        jProgressBar1.setStringPainted(true);

        jLabel1.setPreferredSize(new java.awt.Dimension(256, 256));
        jScrollPane2.setViewportView(jLabel1);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");

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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
