package com.mycompany.esamenautoescuela;

import com.sun.tools.javac.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JRootPane;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.DefaultCategoryDataset;


public class TestFrame extends javax.swing.JFrame {

    private ConexionDB db;
    private ArrayList<String[]> examenes;
    private PreguntasPanel preguntasPanel1;

    public TestFrame() {
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
    }
    
    public TestFrame(ConexionDB db) {
        this.db = db;
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
        preguntasPanel1 = new PreguntasPanel();
        jScrollPane1.setViewportView(preguntasPanel1);
        examenes = db.getExamenes();
        jPanel1.setLayout(new GridLayout(examenes.size(),1));
        for(String[] a : examenes) {
            TipoExamen b = new TipoExamen(Integer.parseInt(a[0]),a[1]);
            addButtonListener(b);
            jPanel1.add(b);
        }
        jPanel1.repaint();
        addMenuListener();
    }
            
         
    @Override
    public Image getIconImage() {
        URL imageResource = Main.class.getClassLoader().getResource("prueba.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    
    private void crearPreguntas(int id) {    
        preguntasPanel1 = new PreguntasPanel(db.getPreguntas(id),db);
        preguntasPanel1.repaint();
        preguntasPanel1.setVisible(true);
        jScrollPane1.setViewportView(preguntasPanel1);
        jScrollPane1.repaint();
        jScrollPane1.revalidate();
        this.repaint();
        this.revalidate();
    }
    
    private void addButtonListener(TipoExamen button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                crearPreguntas(button.getId());
            }
        });  
    }
    
    private void addMenuListener() {
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                showGrafica();
            }
        });  
    }
    
    private void showGrafica() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ArrayList<String[]> registros = db.getRegistros();
        for(String[] registro : registros) {
            datos.addValue(Integer.parseInt(registro[0]), registro[1], registro[2]);        
        }
        CategoryAxis categoryAxis = new CategoryAxis("Examenes");
        ValueAxis valueAxis = new NumberAxis("Realizados");
        BarRenderer renderer = new BarRenderer() {

            @Override
            public Paint getItemPaint(int row, int column) {
                switch(row) {
                    case 0:
                        return new Color(0,162,255);
                    case 1:
                        return new Color(0,255,124);
                    case 2:
                        return new Color(255,50,0);
                    default:
                        return Color.BLACK;
                }
            }
        };
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        StandardCategoryToolTipGenerator generator =
          new StandardCategoryToolTipGenerator("{1}, {2}", NumberFormat.getInstance());
        renderer.setDefaultToolTipGenerator(generator);
        CategoryPlot plot = new CategoryPlot(datos, categoryAxis, valueAxis, renderer);
        LegendItemCollection chartLegend = new LegendItemCollection();
        Shape shape = new Rectangle(10, 10);
        chartLegend.add(new LegendItem("Total", null, null, null, shape, new Color(0,162,255)));
        chartLegend.add(new LegendItem("Aprobado", null, null, null, shape, new Color(0,255,124)));
        chartLegend.add(new LegendItem("Suspenso", null, null, null, shape, new Color(255,50,0)));
        
        JFreeChart chart = new JFreeChart("Cantidad de Examnes", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ((CategoryPlot)chart.getPlot()).setFixedLegendItems(chartLegend);
        
        ChartPanel panel = new ChartPanel(chart);
        panel.setSize(jScrollPane1.getWidth(), jScrollPane1.getHeight());
        panel.setPreferredSize(new Dimension((jScrollPane1.getWidth()-10), (jScrollPane1.getHeight()-10)));
        panel.setVisible(true);
        jScrollPane1.setViewportView(panel);
        jScrollPane1.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        empezazLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        empezazLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        empezazLabel.setText("Autoescuela A&A");
        empezazLabel.setToolTipText("");
        empezazLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        empezazLabel.setFocusable(false);
        empezazLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        jMenu1.setText("Informes");

        jMenuItem1.setText("Examenes");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(empezazLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(empezazLabel)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empezazLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
