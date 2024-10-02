package visao.frames.consulta;

import java.awt.Dimension;
import javax.swing.JComboBox;
import modelo.controller.entidades.ControllerMetrica;
import visao.controller.frames.consulta.ControllerViewConsultaMetrica;
import visao.frames.View;

/**
 * <p>Classe de Visao <b>ViewConsultaMetrica</b>.</p>
 * <p>Classe responsavel por definir a Interface de Consulta de Metricas do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.consulta.ViewConsulta
 */
public final class ViewConsultaMetrica extends ViewConsulta {
    private JComboBox comboBoxDiagramaAlvo;
    private JComboBox comboBoxTipo;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewParent View Parent.
     */
    public ViewConsultaMetrica(View viewParent) {
        super(viewParent);
        this.controller = new ControllerViewConsultaMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Consulta de Métricas");
        this.setSize(925, 555);
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
        this.comboBoxDiagramaAlvo = this.createComboBox(ControllerMetrica.DIAGRAMAS);
        this.comboBoxDiagramaAlvo.setPreferredSize(new Dimension(210, 30));
        this.comboBoxTipo         = this.createComboBox(ControllerMetrica.TIPOS);
        this.comboBoxTipo.setPreferredSize(new Dimension(200, 30));
        
        this.add(this.createLabel("Metrica: "));
        this.addTextSearch(20);
        this.add(this.comboBoxDiagramaAlvo);
        this.add(this.comboBoxTipo);
        this.addButtonSearch();
        
        this.addLinhas(1);
        
        this.addTable();
        String[] columns     = {"Id", "Nome", "Descricao", "Escopo", "Operacao"};
        int[]    sizeColumns = {10, 100, 100, 100, 100};
        this.setColumns(columns, sizeColumns);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.scrollPane.setPreferredSize(new Dimension(850, 200));
        
        this.addLinhas(1);
        
        this.addTextFieldTotal();
        
        this.addLinhas(1);
    }
    
    @Override
    public void clear() {
        ((ControllerViewConsultaMetrica) this.controller).search();
    }
    
    /**
     * Metodo responsavel por retornar o Combo Box do Diagrama Alvo.
     * @return Combo Box do Diagrama Alvo.
     */
    public JComboBox getComboBoxDiagramaAlvo() {
        return this.comboBoxDiagramaAlvo;
    }
    
    /**
     * Metodo responsavel por retornar o Combo Box do Tipo.
     * @return Combo Box do Tipo.
     */
    public JComboBox getComboBoxTipo() {
        return this.comboBoxTipo;
    }
}