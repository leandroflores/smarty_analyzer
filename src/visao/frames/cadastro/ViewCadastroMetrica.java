package visao.frames.cadastro;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelo.controller.entidades.ControllerMetrica;
import visao.controller.frames.cadastro.ControllerViewCadastroMetrica;
import visao.frames.consulta.ViewConsultaMetrica;

/**
 * <p>Classe de Visao <b>ViewCadastroMetrica</b>.</p>
 * <p>Classe responsavel por definir a Interface de <b>Cadastro de Metricas</b>.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    visao.controller.frames.cadastro.ControllerViewCadastroMetrica
 * @see    visao.frames.cadastro.ViewCadastro
 */
public final class ViewCadastroMetrica extends ViewCadastro {
    private final ViewConsultaMetrica viewConsultaMetrica;
    private JTextField textFieldNome;
    private JTextField textFieldRotulo;
    private JTextField textFieldDescricao;
    private JComboBox  comboBoxDiagramaAlvo;
    private JComboBox  comboBoxTipo;
    private JComboBox  comboBoxUnidadeMedida;
    private JTextField textFieldOperacao;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsultaMetrica View Parent.
     */
    public ViewCadastroMetrica(ViewConsultaMetrica viewConsultaMetrica) {
        super(viewConsultaMetrica);
        this.viewConsultaMetrica = viewConsultaMetrica;
        this.controller          = new ControllerViewCadastroMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Cadastro de Métrica");
        this.setSize(800, 475);
        this.addHeader();
        this.addComponents();
        this.addButtons();
        this.clear();
        this.clearBorder();
    }

    @Override
    public void addHeader() {
        super.addHeader("cadastro_metrica.jpg");
        this.setMessage("Os campos (*) são Obrigatorios");
    }

    @Override
    public void addComponents() {
        this.textFieldNome = this.createTextField("", 25);
        this.add(this.createLabel("               "));
        this.add(this.createLabel("Nome*: "));
        this.add(this.textFieldNome);
        
        this.textFieldRotulo = this.createTextField("", 12);
        this.add(this.createLabel("      "));
        this.add(this.createLabel("Rotulo*: "));
        this.add(this.textFieldRotulo);
        
        this.addLinhas(1);
        
        this.textFieldDescricao = this.createTextField("", 45);
        this.add(this.createLabel("       "));
        this.add(this.createLabel("Descricao*: "));
        this.add(this.textFieldDescricao);
        
        this.addLinhas(1);
        
        this.comboBoxDiagramaAlvo = this.createComboBox(ControllerMetrica.DIAGRAMAS);
        this.add(this.createLabel("Diagrama Alvo*: "));
        this.add(this.comboBoxDiagramaAlvo);
        
        this.comboBoxTipo         = this.createComboBox(ControllerMetrica.TIPOS);
        this.add(this.createLabel("Tipo*: "));
        this.add(this.comboBoxTipo);
        
        this.addLinhas(1);
        
        this.comboBoxUnidadeMedida = this.createComboBox(ControllerMetrica.UNIDADE_MEDIDA);
        this.comboBoxUnidadeMedida.setPreferredSize(new Dimension(200, 30));
        this.add(this.createLabel("           "));
        this.add(this.createLabel("Medida*: "));
        this.add(this.comboBoxUnidadeMedida);
        
        this.textFieldOperacao     = this.createTextField("", 20);
        this.add(this.createLabel("      "));
        this.add(this.createLabel("Operacao*: "));
        this.add(this.textFieldOperacao);
        
        this.addLinhas(1);
    }

    @Override
    public void clearBorder() {
        this.textFieldNome.setBorder(BORDA_CINZA);
        this.textFieldRotulo.setBorder(BORDA_CINZA);
        this.textFieldDescricao.setBorder(BORDA_CINZA);
        this.comboBoxDiagramaAlvo.setBorder(BORDA_CINZA);
        this.comboBoxTipo.setBorder(BORDA_CINZA);
        this.comboBoxUnidadeMedida.setBorder(BORDA_CINZA);
        this.textFieldOperacao.setBorder(BORDA_CINZA);
    }
    
    @Override
    public void clear() {
        this.textFieldNome.setText("");
        this.textFieldRotulo.setText("");
        this.textFieldDescricao.setText("");
        this.comboBoxDiagramaAlvo.setSelectedIndex(0);
        this.comboBoxTipo.setSelectedIndex(0);
        this.comboBoxUnidadeMedida.setSelectedIndex(0);
        this.textFieldOperacao.setText("");
        
        this.textFieldNome.requestFocus();
    }

    /**
     * Metodo responsavel por retornar a View de Consulta de Metricas.
     * @return View de Consulta de Metricas.
     */
    public ViewConsultaMetrica getViewConsultaMetrica() {
        return this.viewConsultaMetrica;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Nome da View Cadastro Metrica.
     * @return Text Field Nome da View Cadastro Metrica.
     */
    public JTextField getTextFieldNome() {
        return this.textFieldNome;
    }

    /**
     * Metodo responsavel por retornar o Text Field Rotulo da View Cadastro Metrica.
     * @return Text Field Rotulo da View Cadastro Metrica.
     */
    public JTextField getTextFieldRotulo() {
        return this.textFieldRotulo;
    }

    /**
     * Metodo responsavel por retornar o Text Field Descricao da View Cadastro Metrica.
     * @return Text Field Descricao da View Cadastro Metrica.
     */
    public JTextField getTextFieldDescricao() {
        return this.textFieldDescricao;
    }

    /**
     * Metodo responsavel por retornar o Combo Box do Diagrama Alvo da View Cadastro Metrica.
     * @return Combo Box do Diagrama Alvo da View Cadastro Metrica.
     */
    public JComboBox getComboBoxDiagramaAlvo() {
        return this.comboBoxDiagramaAlvo;
    }

    /**
     * Metodo responsavel por retornar o Combo Box Tipo da View Cadastro Metrica.
     * @return Combo Box Tipo da View Cadastro Metrica.
     */
    public JComboBox getComboBoxTipo() {
        return this.comboBoxTipo;
    }

    /**
     * Metodo responsavel por retornar o Combo Box Unidade de Medida da View Cadastro Metrica.
     * @return Combo Box Unidade de Medida da View Cadastro Metrica.
     */
    public JComboBox getComboBoxUnidadeMedida() {
        return this.comboBoxUnidadeMedida;
    }

    /**
     * Metodo responsavel por retornar o Text Field Operacao da View Cadastro Metrica.
     * @return Text Field Operacao da View Cadastro Metrica.
     */
    public JTextField getTextFieldOperacao() {
        return this.textFieldOperacao;
    }
}