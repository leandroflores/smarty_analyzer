package visao.frames;

import visao.controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import utils.FunctFrame;
import utils.FunctString;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * <p>Classe de Visao <b>ViewModal</b>.</p> 
 * <p>Classe de Modelo para as <b>Interfaces Modais</b> do Sistema.</p>
 * @author Leandro
 * @since  06/10/2015
 * @see    controller.Controller
 * @see    javax.swing.JDialog
 */
public abstract class ViewModal extends JDialog {
    protected JLabel     labelHeader;
    protected JLabel     labelMessage;
    
    protected JButton    buttonAction1;
    protected JButton    buttonAction2;
    protected JButton    buttonAction3;
    protected JButton    buttonAction4;
    
    protected Controller controller;
    
    private   static final String ESTILO  = "Arial";
    private   static final int    TAMANHO = 15;
    private   static final byte   NEGRITO = Font.BOLD;
    private   static final byte   PADRAO  = Font.LAYOUT_LEFT_TO_RIGHT;
    private   static final byte   CENTRO  = Font.CENTER_BASELINE;
    private   static final byte   LARGURA = 40;
    private   static final byte   ALTURA  = 30;
    protected static final String SYSTEM = "SMartyAnalyzer - ";
    protected static final Border BORDA_CINZA = BorderFactory.createLineBorder(Color.GRAY);

    
    /**
     * Metodo construtor que recebe uma View Parent como parametro.
     * @param view View Parent.
     */
    public ViewModal(View view) {
        super(view, "", true);
        this.setSettings();
    }
    
    /**
     * Metodo construtor que recebe uma View Parent como parametro.
     * @param viewModal View Parent.
     */
    public ViewModal(ViewModal viewModal) {
        super(viewModal, "", true);
        this.setSettings();
    }
    
    /**
     * Metodo responsavel por definir propriedades padrao para a ViewModal.
     */
    private void setSettings() {
        this.setSize(220, 120);
        this.setIconImage(new ImageIcon(getClass().getResource("/visao/imagens/icone.jpg")).getImage());
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * Metodo responsavel por Adicionar o Cabecalho a View Modal.
     * @param url Url da Imagem de Cabecalho.
     */
    protected void addHeader(String url) {
        this.addLinhas(1);
        this.labelHeader = new FunctFrame().getLabelImage(url);
        this.add(this.labelHeader);
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar linhas em uma <b>ViewModal</b>.
     * @param linhas Número de Linhas a serem adicionadas em uma ViewModal.
     */
    protected void addLinhas(int linhas) {
        for (int i = 0; i < linhas; ++i) {
            this.add(this.getLinha());
        }
    }
    
    /**
     * Metodo responsavel por retornar um Label com uma nova Linha.
     * @return Label com uma nova Linha.
     */
    protected JLabel getLinha() {
        return new JLabel(new FunctString().getEspacos(1000));
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JLabel</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  message Mensagem do Rotulo.
     * @return Novo JLabel.
     */
    protected JLabel createLabel(String message) {
        JLabel label = new JLabel(message);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ESTILO, NEGRITO, TAMANHO));
        return label;
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JLabel</b> de Titulo.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  message Mensagem do Rotulo.
     * @return Novo JLabel de Titulo.
     */
    protected JLabel createTitulo(String message) {
        JLabel label = new JLabel(message);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ESTILO, NEGRITO, TAMANHO + 5));
        return label;
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JTextField</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  message  Mensagem a ser exibida no JTextField.
     * @param  size     Tamanho a ser exibida no JTextField.
     * @return Novo JTextField.
     */
    protected JTextField createTextField(String message, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(message);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(LARGURA, ALTURA));
                   textField.setFont(new Font(ESTILO, PADRAO, TAMANHO));
        return     textField;
    }
    
    /**
     * Metodo responsavel por criar um JTextField nao Editavel.
     * @param  message  Mensagem a ser exibida no JTextField.
     * @param  size     Tamanho a ser exibida no JTextField.
     * @return Novo JTextField nao Editavel.
     */
    protected JTextField createTextFieldNoEditable(String message, int size) {
        JTextField textField = this.createTextField(message, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Metodo responsavel por criar um <b>JButton</b> com imagem e mensagem.
     * @param  message  Mensagem a ser exibida.
     * @param  urlImage URL da Imagem a ser exibida.
     * @return Novo JButton.
     */
    protected JButton createButton(String message, String urlImage) {
        JButton button = new JButton(new FunctFrame().createImage("buttons/" + urlImage));
                button.setText(message);
                button.addActionListener(this.controller);
                button.addKeyListener(this.controller);
                button.setFont(new Font(ESTILO, CENTRO, TAMANHO));
        return  button;
    }
    
    /**
     * Metodo responsavel por criar os radio-botoes padroes para a ViewModal.
     * @param  message Mensagem exibida no Radio Botao.
     * @return Novo JRadioButton.
     */
    public JRadioButton createRadioButton(String message) {
        JRadioButton radioButton = new JRadioButton(message);
                     radioButton.addActionListener(this.controller);
                     radioButton.addKeyListener(this.controller);
                     radioButton.setFont(new Font(ESTILO, PADRAO, TAMANHO));
        return       radioButton;
    }
    
    /**
     * Metodo responsavel por criar os Combos padroes para a ViewModal.
     * @param  values Valores do Combo Box.
     * @return Novo JComboBox.
     */
    public JComboBox createComboBox(String[] values) {
        JComboBox comboBox = new JComboBox(values);
                  comboBox.addActionListener(this.controller);
                  comboBox.addKeyListener(this.controller);
                  comboBox.setFont(new Font(ESTILO, PADRAO, TAMANHO));
                  comboBox.setPreferredSize(new Dimension(265, 30));
        return    comboBox;
    }
    
    /**
     * Metodo responsavel por definir Cinza como a Cor da Borda de um Componente.
     * @param component Componente a ser alterado.
     */
    protected void clearBorder(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
    
    /**
     * Metodo responsavel por retornar um novo JPanel.
     * @return Novo JPanel.
     */
    protected JPanel createPanel() {
        JPanel panel = new JPanel();
               panel.setPreferredSize(new Dimension(455, 185));
               panel.setVisible(true);
               panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }
    
    /**
     * Metodo responsavel por criar a JTextArea padrao para a ViewModal.
     * @param  width  Largura da JTextArea.
     * @param  height Altura da JTextArea.
     * @return Nova JTextArea.
     */
    public JTextArea createTextArea(int width, int height) {
        JTextArea textArea = new JTextArea(width, height);
                  textArea.addKeyListener(this.controller);
                  textArea.setFont(new Font(ESTILO, PADRAO, TAMANHO));
        return    textArea;
    }
    
    /**
     * Metodo responsavel por definir um Tamanho para a View e a deixar Relativa a seu Parent.
     * @param y Eixo Y.
     * @param x Eixo X.
     */
    @Override
    public void setSize(int y, int x) {
        super.setSize(y, x);
        this.setLocationRelativeTo(this.getParent());
    }
}