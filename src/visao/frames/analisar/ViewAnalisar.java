package visao.frames.analisar;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewAnalisar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Analise</b>.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewAnalisar extends ViewModal implements Viewable {
    protected JTextField   textFieldModeloUML;
    private   JFileChooser fileChooser;
    
    // Dados do Panel:
    private JTextField textFieldDados1;
    private JTextField textFieldDados2;
    private JTextField textFieldDados3;
    private JTextField textFieldDados4;
    private JTextField textFieldDados5;
    
    // Rodape:
    private JTextField textFieldOperacao;
    private JTextField textFieldResultado;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAnalisar(ViewMenu viewMenu) {
        super(viewMenu);
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("analisar/" + url);
    }

    /**
     * Metodo responsavel por adicionar o Campo de Busca do Modelo UML.
     */
    protected void addSearchTextField() {
        this.textFieldModeloUML = this.createTextField("", 30);
        this.textFieldModeloUML.setEditable(false);
        
        this.fileChooser = new JFileChooser();
        this.fileChooser.setLocation(50, 50);
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.buttonAction1 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldModeloUML);
        this.add(this.buttonAction1);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel na View Processar.
     */
    protected void addPanelDiagramaClasse() {
        this.initTextFields();
        JPanel panel = this.createPanel();
               panel.setPreferredSize(new Dimension(570, 185));
               panel.add(this.createLabel("    Pacotes: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("    Classes: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Interfaces: "));
               panel.add(this.textFieldDados3);
               panel.add(this.createLabel("   Metodos: "));
               panel.add(this.textFieldDados4);
               panel.add(this.createLabel("  Atributos: "));
               panel.add(this.textFieldDados5);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaClasse(String[] dados) {
        this.textFieldDados1.setText(dados[0]);
        this.textFieldDados2.setText(dados[1]);
        this.textFieldDados3.setText(dados[2]);
        this.textFieldDados4.setText(dados[3]);
        this.textFieldDados5.setText(dados[4]);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel na View Processar.
     */
    protected void addPanelDiagramaCasoDeUso() {
        this.initTextFields();
        JPanel panel = this.createPanel();
               panel.setPreferredSize(new Dimension(600, 120));
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Pastas: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Atores: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Casos de Uso: "));
               panel.add(this.textFieldDados3);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaCasoDeUso(String[] dados) {
        this.textFieldDados1.setText(dados[0]);
        this.textFieldDados2.setText(dados[1]);
        this.textFieldDados3.setText(dados[2]);
    }
    
    /**
     * Metodo responsavel por inicializar os Text Fields.
     */
    private void initTextFields() {
        this.textFieldDados1  = this.createTextFieldNoEditable("", 35);
        this.textFieldDados2  = this.createTextFieldNoEditable("", 35);
        this.textFieldDados3  = this.createTextFieldNoEditable("", 35);
        this.textFieldDados4  = this.createTextFieldNoEditable("", 35);
        this.textFieldDados5  = this.createTextFieldNoEditable("", 35);
    }

    /**
     * Metodo responsavel por adicionar o Rodape da View Analisar.
     */
    protected void addFooter() {
        this.addTextFieldOperacao();
        this.addTextFieldResultado();
    }
    
    /**
     * Metodo responsavel por Adicionar o Campo de Operacao da View Analisar.
     */
    protected void addTextFieldOperacao() {
        this.textFieldOperacao = this.createTextField("", 25);
        
        this.buttonAction2 = this.createButton("", "update.jpg");
        
        this.add(this.createLabel("Operacao*: "));
        this.add(this.textFieldOperacao);
        this.add(this.buttonAction2);
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por Adicionar o Campo de Resultado da View Analisar.
     */
    protected void addTextFieldResultado() {
        this.textFieldResultado = this.createTextFieldNoEditable("", 30);
        this.add(this.createLabel("  Resultado: "));
        this.add(this.textFieldResultado);
        
        this.addLinhas(1);
    }
    
    @Override
    public void addButtons() {
        this.buttonAction3 = this.createButton("  Voltar   ", "back.jpg");
        this.buttonAction4 = this.createButton(" Cancelar  ", "exit.jpg");
       
        this.add(this.buttonAction3);
        this.add(this.buttonAction4);
    }
    
    /**
     * Metodo responsavel por retornar o TextField do Modelo UML da View Analisar.
     * @return TextField do Modelo UML da View Analisar.
     */
    public JTextField getTextFieldModeloUML() {
        return this.textFieldModeloUML;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser da View Analisar.
     * @return File Chooser da View Analisar.
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa do Modelo UML da View Analisar.
     * @return Botao de Pesquisa do Modelo UML da View Analisar.
     */
    public JButton getButtonSearchModeloUML() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Atualizar da View Analisar.
     * @return Botao Atualizar da View Analisar.
     */
    public JButton getButtonUpdate() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field da Operacao.
     * @return Text Field da Operacao.
     */
    public JTextField getTextFieldOperacao() {
        return this.textFieldOperacao;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field do Resultado.
     * @return Text Field do Resultado.
     */
    public JTextField getTextFieldResultado() {
        return this.textFieldResultado;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Voltar da View Analisar.
     * @return Botao Voltar da View Analisar.
     */
    public JButton getButtonVoltar() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Cancelar da View Analisar.
     * @return Botao Cancelar da View Analisar.
     */
    public JButton getButtonCancel() {
        return this.buttonAction4;
    }
}