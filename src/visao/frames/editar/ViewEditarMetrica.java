package visao.frames.editar;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelo.controller.entidades.ControllerMetrica;
import modelo.entidades.Metrica;
import visao.controller.frames.editar.ControllerViewEditarMetrica;
import visao.frames.consulta.ViewConsultaMetrica;

/**
 * <p>Classe de Visao <b>ViewEditarMetrica</b>.</p>
 * <p>Classe responsavel por definir a Interface de Edicao de Metricas.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    view.editar.ViewEditar
 */
public final class ViewEditarMetrica extends ViewEditar {
    private final ViewConsultaMetrica viewConsultaMetrica;
    private final Metrica             metrica;
    private       JTextField          textFieldId;
    private       JTextField          textFieldNome;
    private       JTextField          textFieldRotulo;
    private       JTextField          textFieldDescricao;
    private       JComboBox           comboBoxDiagramaAlvo;
    private       JComboBox           comboBoxTipo;
    private       JComboBox           comboBoxUnidadeMedida;
    private       JTextField          textFieldOperacao;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsultaMetrica View Consulta de Metricas.
     * @param metrica Metrica a ser Editada.
     */
    public ViewEditarMetrica(ViewConsultaMetrica viewConsultaMetrica, Metrica metrica) {
        super(viewConsultaMetrica);
        this.viewConsultaMetrica = viewConsultaMetrica;
        this.metrica             = metrica;
        this.controller          = new ControllerViewEditarMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Editar Metrica");
        this.setSize(800, 500);
        this.addHeader();
        this.addComponents();
        this.addButtons();
        this.setDados();
    }

    @Override
    public void addHeader() {
        super.addHeader("editar_metrica.jpg");
    }

    @Override
    public void addComponents() {
        this.textFieldId = this.createTextField(Long.toString(this.metrica.getId()), 5);
        this.textFieldId.setEditable(false);
        this.add(this.createLabel("Codigo: "));
        this.add(this.textFieldId);
        
        this.addLinhas(1);
        
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
    public void setDados() {
        this.textFieldId.setText(Long.toString(this.metrica.getId()));
        this.textFieldNome.setText(this.metrica.getNome());
        this.textFieldRotulo.setText(this.metrica.getRotulo());
        this.textFieldDescricao.setText(this.metrica.getDescricao());
        this.comboBoxDiagramaAlvo.setSelectedItem(this.metrica.getDiagramaAlvo());
        this.comboBoxTipo.setSelectedItem(this.metrica.getTipo());
        this.comboBoxUnidadeMedida.setSelectedItem(this.metrica.getUnidadeMedida());
        this.textFieldOperacao.setText(this.metrica.getOperacao());
        
        this.textFieldNome.requestFocus();
    }
    
    @Override
    public void clearBorder() {
        this.textFieldId.setBorder(BORDA_CINZA);
        this.textFieldNome.setBorder(BORDA_CINZA);
        this.textFieldRotulo.setBorder(BORDA_CINZA);
        this.textFieldDescricao.setBorder(BORDA_CINZA);
        this.comboBoxDiagramaAlvo.setBorder(BORDA_CINZA);
        this.comboBoxTipo.setBorder(BORDA_CINZA);
        this.comboBoxUnidadeMedida.setBorder(BORDA_CINZA);
        this.textFieldOperacao.setBorder(BORDA_CINZA);
    }
    
    /**
     * Metodo responsavel por retornar a View de Consulta de Metricas.
     * @return View de Consulta de Metricas.
     */
    public ViewConsultaMetrica getViewConsultaMetrica() {
        return this.viewConsultaMetrica;
    }

    /**
     * Metodo responsavel por retornar a Metrica da View Editar Metrica.
     * @return Metrica da View Editar Metrica.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Nome da View Editar Metrica.
     * @return Text Field Nome da View Editar Metrica.
     */
    public JTextField getTextFieldNome() {
        return this.textFieldNome;
    }

    /**
     * Metodo responsavel por retornar o Text Field Rotulo da View Editar Metrica.
     * @return Text Field Rotulo da View Editar Metrica.
     */
    public JTextField getTextFieldRotulo() {
        return this.textFieldRotulo;
    }

    /**
     * Metodo responsavel por retornar o Text Field Descricao da View Editar Metrica.
     * @return Text Field Descricao da View Editar Metrica.
     */
    public JTextField getTextFieldDescricao() {
        return this.textFieldDescricao;
    }

    /**
     * Metodo responsavel por retornar o Combo Box do Diagrama Alvo da View Editar Metrica.
     * @return Combo Box do Diagrama Alvo Caracteristica da View Editar Metrica.
     */
    public JComboBox getComboBoxDiagramaAlvo() {
        return this.comboBoxDiagramaAlvo;
    }

    /**
     * Metodo responsavel por retornar o Combo Box do Tipo da View Editar Metrica.
     * @return Combo Box do Tipo da View Editar Metrica.
     */
    public JComboBox getComboBoxTipo() {
        return this.comboBoxTipo;
    }

    /**
     * Metodo responsavel por retornar o Combo Box Unidade de Medida da View Editar Metrica.
     * @return Combo Box Unidade de Medida da View Editar Metrica.
     */
    public JComboBox getComboBoxUnidadeMedida() {
        return this.comboBoxUnidadeMedida;
    }

    /**
     * Metodo responsavel por retornar o Text Field Operacao da View Editar Metrica.
     * @return Text Field Operacao da View Editar Metrica.
     */
    public JTextField getTextFieldOperacao() {
        return this.textFieldOperacao;
    }
}