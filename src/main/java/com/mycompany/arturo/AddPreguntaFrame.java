package com.mycompany.arturo;

import com.sun.tools.javac.Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que crea un JFrame que permite insertar una pregunta 
 * con sus respectivas respuestas a la base de datos
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
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setResizable(false);
        initComponents();
        addButton.setEnabled(false);
        setExamenesComboBox();
        addAnadirButtonListener();
        addFotoButtonListener();
        addTextAreaFocusListener();
        addTextFieldFocusListener(correctaTextField);
        addTextFieldFocusListener(respuesta2TextField);
        addTextFieldFocusListener(respuesta3TextField);
        addTextFieldFocusListener(respuesta4TextField);
    }
    
    @Override
    public Image getIconImage() {
        URL imageResource = Main.class.getClassLoader().getResource("prueba.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    /*
    * Rellena el ComboBox con los examenes disponibles
    */
    private void setExamenesComboBox() {
        for(String[] a : db.getExamenes()) {
            examenesComboBox.addItem(a[0]+"-"+a[1]);
        }
    }
    /*
    * Limpia los campos del formulario
    */
    private void clean() {
        examenesComboBox.setSelectedIndex(0);
        preguntaTextArea.setText(null);
        correctaTextField.setText("Correcta");
        respuesta2TextField.setText("False1");
        respuesta3TextField.setText("False2");
        respuesta4TextField.setText("False3");
    }
    /*
    * Coprueba que los campos de formulario contienen informacio
    */
    private void comprobar() {
        if(!preguntaTextArea.getText().isBlank() && !correctaTextField.getText().isBlank()
                && !respuesta2TextField.getText().isBlank() && !respuesta3TextField.getText().isBlank()
                && !respuesta4TextField.getText().isBlank()) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
    }
    /*
    * Metodo que añade un listener al boton addButton
    * que al ser pulsado añade la pregunta a la base de datos
    */
    private void addAnadirButtonListener() {
        addButton.addActionListener(new ActionListener() {
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
    /*
    * Focus listener para el TextArea
    */
    private void addTextAreaFocusListener() {
        preguntaTextArea.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }
            public void focusLost(FocusEvent e) {
                comprobar();
            }
        });
    }
    /*
    * Focus listener para los TextField
    */
    private void addTextFieldFocusListener(JTextField textField) {
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }
            public void focusLost(FocusEvent e) {
                comprobar();
            }
        });
    }
    /*
    * Cuando pulsas el boton fotoButon se abre un explorador de archivos
    * para que puedas coger una imagen para la pregunta
    * una vez seleccionada la imagen se copia a la carpeta resources
    */
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
                    File padre = new File("resources");
                    //File destino = new File(padre.getAbsolutePath(),fc.getSelectedFile().getName());
                    File destino = new File("src/main/resources",fc.getSelectedFile().getName());
                    try {
                        Files.copy(Paths.get(fc.getSelectedFile().getAbsolutePath())
                                , Paths.get(destino.getAbsolutePath())
                                , StandardCopyOption.REPLACE_EXISTING);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        preguntaTextArea.setText(ex.toString());
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
        addButton = new javax.swing.JButton();
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

        addButton.setText("Añadir");

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
                        .addComponent(addButton))
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
                .addComponent(addButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel preguntaLabel;
    private javax.swing.JTextArea preguntaTextArea;
    private javax.swing.JTextField respuesta2TextField;
    private javax.swing.JTextField respuesta3TextField;
    private javax.swing.JTextField respuesta4TextField;
    // End of variables declaration//GEN-END:variables
}
