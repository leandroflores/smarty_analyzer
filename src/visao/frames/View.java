package visao.frames;

import visao.controller.Controller;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import utils.FunctFrame;
import utils.FunctString;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * <p>Classe de Visao <b>View</b>.</p> 
 * <p>Classe de Modelo para as <b>Interfaces</b> do Sistema.</p>
 * @author Leandro
 * @since  06/10/2015
 * @see    controller.Controller
 * @see    javax.swing.JFrame
 */
public abstract class View extends JFrame {
    protected              Controller controller;
    private   static final String     ESTILO  = "Arial";
    private   static final byte       TAMANHO = 15;
    private   static final byte       NEGRITO = Font.BOLD;
    private   static final byte       PADRAO  = Font.LAYOUT_LEFT_TO_RIGHT;
    private   static final byte       LARGURA = 40;
    private   static final byte       ALTURA  = 30;
    
    /**
     * Metodo contrutor padrao da Classe.
     */
    public View() {
        super();
        this.setSettings();
    }
    
    /**
     * Metodo responsavel por definir as propriedades padrao para a View.
     */
    private void setSettings() {
        this.setSize(new Dimension(this.getEixoX(), this.getEixoY()));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocation(5, 5);
        this.setIconImage(new ImageIcon(getClass().getResource("/visao/imagens/icone.jpg")).getImage());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * Metodo responsavel por retornar um Tamanho de Largura Padrao para a View.
     * @return Largura do Frame.
     */
    private int getEixoX() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 10;
    }
    
    /**
     * Metodo responsavel por retornar um Tamanho de Altura Padrao para a View.
     * @return Altura do Frame.
     */
    private int getEixoY() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;
    }
    
    /**
     * Metodo responsavel por adicionar linhas em uma Frame.
     * @param linhas Numero de Linhas a serem adicionadas.
     */
    protected void addLinhas(int linhas) {
        for (int i = 0; i < linhas; ++i) {
            this.add(new JLabel(new FunctString().getEspacos(1000)));
        }
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JTextField</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  message Mensagem do Rotulo.
     * @return Novo JLabel.
     */
    protected JLabel createLabel(String message) {
        JLabel jLabel = new JLabel(message);
               jLabel.addKeyListener(this.controller);
               jLabel.setFont(new Font(ESTILO, NEGRITO, TAMANHO));
        return jLabel;
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
     * <p>Metodo responsavel por instanciar e retornar um <b>JTextField</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @return JPasswordField.
     */
    protected JPasswordField createPasswordField() {
        JPasswordField jPasswordField = new JPasswordField(15);
                       jPasswordField.addActionListener(this.controller);
                       jPasswordField.addKeyListener(this.controller);
                       jPasswordField.setPreferredSize(new Dimension(LARGURA, ALTURA));
                       jPasswordField.setFont(new Font(ESTILO, PADRAO, TAMANHO));
        return         jPasswordField;
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JButton</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  message  Mensagem a ser exibida no Botão.
     * @param  urlImage Imagem a ser exibida no Botão.
     * @return Novo JButton
     */
    protected JButton createButton(String message, String urlImage) {
        JButton jButton = new JButton(new FunctFrame().createImage("buttons/" + urlImage));
                jButton.setText(message);
                jButton.addActionListener(this.controller);
                jButton.addKeyListener(this.controller);
                jButton.setPreferredSize(new Dimension(130, 30));
                jButton.setFont(new Font(ESTILO, PADRAO, TAMANHO));
        return  jButton;
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JMenu</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * <p>Por padrao, deixa o Titulo em Arial Negrito e tamanho 15.</p>
     * @param  title Titulo a ser exibido no Item do Menu.
     * @return Novo JMenu.
     */
    protected JMenu createMenu(String title) {
        JMenu  jMenu = new JMenu(title);
               jMenu.addActionListener(this.controller);
               jMenu.setFont(new Font(ESTILO, NEGRITO, TAMANHO));
        return jMenu;
    }
    
    /**
     * <p>Metodo responsavel por instanciar e retornar um <b>JMenuItem</b>.</p>
     * <p>Adiciona um listener da biblioteca como padrao.</p>
     * @param  title    Titulo a ser exibido no Item do Menu.
     * @param  urlImage Imagem a ser exibida no Item do Menu.
     * @return Novo JMenuItem.
     */
    protected JMenuItem createMenuItem(String title, String urlImage) {
        JMenuItem jMenuItem = new JMenuItem(title, new FunctFrame().createImage("icones/" + urlImage));
                  jMenuItem.addActionListener(this.controller);
                  jMenuItem.addKeyListener(this.controller);
                  jMenuItem.setFont(new Font(ESTILO, NEGRITO, TAMANHO));
        return    jMenuItem;
    }
    
    /**
     * Metodo responsavel por impossibilitar mover a View.
     */
    protected void noMove() {
        this.addComponentListener(
            new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    setEnabled(false);
                    setEnabled(true);
                }
            }
        );
    }
}