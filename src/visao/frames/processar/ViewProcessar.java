package visao.frames.processar;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.entidades.Metrica;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewProcessar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Processar</b>.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewProcessar extends ViewModal implements Viewable {
    protected JTextField   textFieldModeloUML;
    private   JFileChooser fileChooser;
    
    // Dados do Panel:
    private JTextField textFieldDados1;
    private JTextField textFieldDados2;
    private JTextField textFieldDados3;
    
    private JTextField   textFieldMetrica;
    private Metrica      metrica;
    
    private JTextField   textFieldValorMetrica;
    private JButton      buttonRefresh;
    private JTextField   textFieldNomeMedicao;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewProcessar(ViewMenu viewMenu) {
        super(viewMenu);
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("processar/" + url);
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
               panel.setPreferredSize(new Dimension(600, 120));
               panel.add(this.createLabel("    Pacotes: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("    Classes: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Interfaces: "));
               panel.add(this.textFieldDados3);
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
    }
    
    /**
     * Metodo responsavel por Adicionar o Campo de Busca de Metricas do Sistema.
     */
    protected void addSearchMetrica() {
        this.textFieldMetrica = this.createTextField("", 25);
        this.textFieldMetrica.setEditable(false);
        
        this.buttonAction2 = this.createButton("", "search.jpg");
        this.buttonRefresh = this.createButton("", "update.jpg");
        
        this.add(this.createLabel("Metrica*: "));
        this.add(this.textFieldMetrica);
        this.add(this.buttonAction2);
        this.add(this.buttonRefresh);
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Rodape da View.
     */
    protected void addFooter() {
        this.add(this.createLabel("                    "));
        this.addSearchMetrica();
        
        this.textFieldValorMetrica = this.createTextFieldNoEditable("", 30);
        this.add(this.createLabel("Valor da Metrica: "));
        this.add(this.textFieldValorMetrica);
        this.add(this.createLabel("       "));
        
        this.addLinhas(1);
        
        this.textFieldNomeMedicao  = this.createTextField("", 30);
        this.add(this.createLabel("Nome da Medicao: "));
        this.add(this.textFieldNomeMedicao);
        this.add(this.createLabel("          "));
        
        this.addLinhas(1);
    }
    
    @Override
    public void addButtons() {
        this.buttonAction3 = this.createButton("  Gravar  ", "save.jpg");
        this.buttonAction4 = this.createButton(" Cancelar ", "exit.jpg");
       
        this.add(this.buttonAction3);
        this.add(this.buttonAction4);
    }
    
    /**
     * Metodo responsavel por retornar a Metrica da View Processar.
     * @return Metrica da View Processar.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }
    
    /**
     * Metodo responsavel por definir a Metrica da View Processar.
     * @param metrica Metrica a ser definida.
     */
    public void setMetrica(Metrica metrica) {
        if (metrica != null) {
            this.metrica = metrica;
            this.textFieldMetrica.setText(this.metrica.toString());
        }
    }
    
    /**
     * Metodo responsavel por retornar o TextField do Modelo UML da View Processar.
     * @return TextField do Modelo UML da View Processar.
     */
    public JTextField getTextFieldModeloUML() {
        return this.textFieldModeloUML;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser da View Processar.
     * @return File Chooser da View Processar.
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa do Modelo UML da View Processar.
     * @return Botao de Pesquisa do Modelo UML da View Processar.
     */
    public JButton getButtonSearchModeloUML() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Processar.
     * @return Botao de Pesquisa da Metrica da View Processar.
     */
    public JButton getButtonSearchMetrica() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Atualizar a View Processar.
     * @return Botao de Atualizar a Metrica da View Processar.
     */
    public JButton getButtonRefresh() {
        return this.buttonRefresh;
    }

    /**
     * Metodo responsavel por retornar o Text Field do Resultado da Medicao.
     * @return Text Field do Resultado da Medicao.
     */
    public JTextField getTextFieldValorMetrica() {
        return this.textFieldValorMetrica;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field do Nome da Medicao.
     * @return Text Field do Nome da Medicao.
     */
    public JTextField getTextFieldNomeMedicao() {
        return this.textFieldNomeMedicao;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Gravar da View Processar.
     * @return Botao de Gravar da View Processar.
     */
    public JButton getButtonGravar() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Cancelar da View Processar.
     * @return Botao de Cancelar da View Processar.
     */
    public JButton getButtonCancel() {
        return this.buttonAction4;
    }
}