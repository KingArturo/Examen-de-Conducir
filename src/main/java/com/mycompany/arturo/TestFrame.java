package com.mycompany.arturo;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
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
    private int cantPreguntas;
    private boolean theme;

    public TestFrame() {
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        theme = true;
        initComponents();
        addListenerTheme();
    }
    
    public TestFrame(ConexionDB db) {
        this.db = db;
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        theme = true;
        cantPreguntas = 10;
        initComponents();
        addBotonCantPreguntasListener();
        addListenerTheme();
        preguntasPanel1 = new PreguntasPanel();
        scrollPanePreguntas.setViewportView(preguntasPanel1);
        examenes = db.getExamenes();
        panelImagen.setLayout(new GridLayout(examenes.size(),1));
        for(String[] a : examenes) {
            TipoExamen b = new TipoExamen(Integer.parseInt(a[0]),a[1]);
            addButtonListener(b);
            panelImagen.add(b);
        }
        panelImagen.repaint();
        addMenuListener();
    }
            
         
    @Override
    public Image getIconImage() {
        URL imageResource = Main.class.getClassLoader().getResource("prueba.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    
    private void crearPreguntas(int id) {    
        preguntasPanel1 = new PreguntasPanel(db.getPreguntas(id),db, cantPreguntas);
        preguntasPanel1.repaint();
        preguntasPanel1.setVisible(true);
        scrollPanePreguntas.setViewportView(preguntasPanel1);
        scrollPanePreguntas.repaint();
        scrollPanePreguntas.revalidate();
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
    
    /*
     *	Añade un listener al boton theme y cuando es pulsado
     *	Cambia de tema 
     */
    private void addListenerTheme() {
        buttonTema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theme = !theme;
                if(theme) {
                        tamaClaro(buttonTema);
               } else {
                       temaOscuro(buttonTema);
               }
            }
        });
    }
    
    private void temaOscuro(JButton button) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicContrastIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
                URL imagebut3 = Main.class.getClassLoader().getResource("sunny.png");
                button.setIcon(new ImageIcon(imagebut3));
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    private void tamaClaro(JButton button) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterContrastIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
                URL imagebut3 = Main.class.getClassLoader().getResource("half-moon.png");
                button.setIcon(new ImageIcon(imagebut3));
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addMenuListener() {
        menuItemGrafica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                showGrafica();
            }
        });  
    }
    
    private void addBotonCantPreguntasListener() {
        CantPreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                mostrarDialogoCantPreguntas();
            }
        });  
    }
    
    private void mostrarDialogoCantPreguntas() {
        SelctCantPrePanel pn = new SelctCantPrePanel();
        JOptionPane.showConfirmDialog(null,
                        pn,
                        "Elegir ",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE);
        cantPreguntas = pn.getValue();
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
        panel.setSize(scrollPanePreguntas.getWidth(), scrollPanePreguntas.getHeight());
        panel.setPreferredSize(new Dimension((scrollPanePreguntas.getWidth()-10), (scrollPanePreguntas.getHeight()-10)));
        panel.setVisible(true);
        scrollPanePreguntas.setViewportView(panel);
        scrollPanePreguntas.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombre = new javax.swing.JLabel();
        scrollPanePreguntas = new javax.swing.JScrollPane();
        scrollPaneImagen = new javax.swing.JScrollPane();
        panelImagen = new javax.swing.JPanel();
        buttonTema = new javax.swing.JButton();
        CantPreButton = new javax.swing.JButton();
        menuBarExamen = new javax.swing.JMenuBar();
        menuInforme = new javax.swing.JMenu();
        menuItemGrafica = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1027, 512));

        labelNombre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelNombre.setText("Autoescuela A&A");
        labelNombre.setToolTipText("");
        labelNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labelNombre.setFocusable(false);
        labelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        scrollPaneImagen.setViewportView(panelImagen);

        URL imagebut3 = Main.class.getClassLoader().getResource("half-moon.png");
        buttonTema.setIcon(new ImageIcon(imagebut3));
        buttonTema.putClientProperty("JButton.buttonType", "roundRect");
        buttonTema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        CantPreButton.setText("Numero Preguntas");
        CantPreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menuInforme.setText("Informes");

        menuItemGrafica.setText("Examenes");
        URL imageResource = Main.class.getClassLoader().getResource("barra-grafica.png");
        menuItemGrafica.setIcon(new ImageIcon(imageResource));
        menuInforme.add(menuItemGrafica);

        menuBarExamen.add(menuInforme);

        setJMenuBar(menuBarExamen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonTema))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CantPreButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneImagen, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPanePreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTema)
                .addGap(1, 1, 1)
                .addComponent(labelNombre)
                .addGap(3, 3, 3)
                .addComponent(CantPreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanePreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton CantPreButton;
    private javax.swing.JButton buttonTema;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JMenuBar menuBarExamen;
    private javax.swing.JMenu menuInforme;
    private javax.swing.JMenuItem menuItemGrafica;
    private javax.swing.JPanel panelImagen;
    private javax.swing.JScrollPane scrollPaneImagen;
    private javax.swing.JScrollPane scrollPanePreguntas;
    // End of variables declaration//GEN-END:variables
}
