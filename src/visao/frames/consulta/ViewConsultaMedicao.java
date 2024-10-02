package visao.frames.consulta;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelo.controller.entidades.ControllerMetrica;
import modelo.entidades.Metrica;
import visao.controller.frames.consulta.ControllerViewConsultaMedicao;
import visao.frames.View;

/**
 * <p>Classe de Visao <b>ViewConsultaMedicao</b>.</p>
 * <p>Classe responsavel por definir a Interface de Consulta de Medicoes do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.consulta.ViewConsulta
 */
public final class ViewConsultaMedicao extends ViewConsulta {
    private JTextField textFieldMetrica;
    private JButton    buttonSearchMetrica;
    private JComboBox  comboBoxDiagramaAlvo;
    
    private Metrica    metrica;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewParent View Parent.
     */
    public ViewConsultaMedicao(View viewParent) {
        super(viewParent);
        this.controller = new ControllerViewConsultaMedicao(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Consulta de Medicoes");
        this.setSize(950, 600);
        this.addHeader();
        this.addComponents();
        this.addButtons();
        this.clear();
    }

    @Override
    public void addHeader() {
        super.addHeader("consulta_metrica.jpg");
    }

    @Override
    public void addComponents() {
        this.addSearchMetrica();
        this.addSearch();
        
        this.addLinhas(1);
        
        this.addTable();
        String[] columns     = {"Id", "Data", "Hora", "Nome", "Metrica", "Valor"};
        int[]    sizeColumns = {10, 20, 20, 100, 100, 100};
        this.setColumns(columns, sizeColumns);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(250);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(30);
        this.scrollPane.setPreferredSize(new Dimension(875, 200));
        
        this.addLinhas(1);
        
        this.addTextFieldTotal();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Campo de Busca de Metricas do Sistema.
     */
    private void addSearchMetrica() {
        this.textFieldMetrica    = this.createTextField("", 55);
        this.textFieldMetrica.setEditable(false);
        this.buttonSearchMetrica = this.createButton("", "search.jpg");
        
        this.add(this.createLabel("Metrica: "));
        this.add(this.textFieldMetrica);
        this.add(this.buttonSearchMetrica);
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Campo de Filtro de Medicoes do Sistema.
     */
    @Override
    protected void addSearch() {
        this.comboBoxDiagramaAlvo = this.createComboBox(ControllerMetrica.DIAGRAMAS);
        this.comboBoxDiagramaAlvo.setPreferredSize(new Dimension(230, 30));
        
        this.add(this.createLabel("Medicao: "));
        this.addTextSearch(25);
        this.add(this.createLabel("       "));
        this.add(this.createLabel("Diagrama Alvo: "));
        this.add(this.comboBoxDiagramaAlvo);
        this.addButtonSearch();
    }
    
    @Override
    protected void addButtonSearch() {
        this.buttonSearch    = this.createButton("", "update.jpg");
        this.add(this.buttonSearch);
    }
    
    @Override
    public void clear() {
        ((ControllerViewConsultaMedicao) this.controller).search();
    }
    
    /**
     * Metodo responsavel por retornar o Combo Box do Diagrama Alvo.
     * @return Combo Box do Diagrama Alvo.
     */
    public JComboBox getComboBoxDiagramaAlvo() {
        return this.comboBoxDiagramaAlvo;
    }
    
    /**
     * Metodo responsavel por retornar o Button de Busca de Metrica.
     * @return Button de Busca de Metrica.
     */
    public JButton getButtonSearchMetrica() {
        return this.buttonSearchMetrica;
    }
    
    public void setMetrica(Metrica metrica) {
        if (metrica != null) {
            this.metrica = metrica;
            this.textFieldMetrica.setText(this.metrica.toString());
        }
    }
    
    public Metrica getMetrica() {
        return this.metrica;
    }
}