package visao.frames.avaliar;

import javax.swing.JTextField;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso;
import visao.controller.frames.avaliar.ControllerViewAvaliarDiagramasCasoDeUsoPorMetrica;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewAvaliarDiagramasCasoDeUsoPorMetrica extends ViewAvaliar {
    private JTextField textFieldResultado1;
    private DiagramaCasoDeUso diagramaCasoDeUso1;
    private JTextField textFieldResultado2;
    private DiagramaCasoDeUso diagramaCasoDeUso2;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAvaliarDiagramasCasoDeUsoPorMetrica(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAvaliarDiagramasCasoDeUsoPorMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Avaliar Diagramas de Caso de Uso");
        this.setSize(1000, 535);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("avaliar_diagramas_caso_de_uso.jpg");
    }

    @Override
    public void addComponents() {
        this.createTextField();
        this.addSearch();
        this.addPanel();
        this.addTextFieldMetrica();
        this.addTextFieldResultados();
    }
    
    /**
     * Metodo responsavel por adicionar os Campos de Pesquisa.
     */
    private void addSearch() {
        this.addLinhas(1);
        
        this.addSearchTextField1();
        this.add(this.createLabel("     "));
        this.addSearchTextField2();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar os Paineis de comparacao.
     */
    private void addPanel() {
        this.addPanelDiagramaCasoDeUso1();
        this.add(this.createLabel("     "));
        this.addPanelDiagramaCasoDeUso2();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Text Field da Metrica.
     */
    private void addTextFieldMetrica() {
        this.addSearchMetrica();
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Text Field dos Resultados.
     */
    private void addTextFieldResultados() {
        this.textFieldResultado1 = this.createTextFieldNoEditable("", 27);
        this.textFieldResultado2 = this.createTextFieldNoEditable("", 27);
        
        this.add(this.createLabel("Resultado: "));
        this.add(this.textFieldResultado1);
        this.add(this.createLabel("      "));
        this.add(this.createLabel("Resultado: "));
        this.add(this.textFieldResultado2);
        this.add(this.createLabel("  "));
        
        this.addLinhas(1);
    }
    
    public void setDiagramaCasoDeUso1(DiagramaCasoDeUso diagramaCasoDeUso) {
        if (diagramaCasoDeUso != null) {
            this.diagramaCasoDeUso1 = diagramaCasoDeUso;
            this.setDadosPanel1();
        }
    }
    
    public void setDiagramaCasoDeUso2(DiagramaCasoDeUso diagramaCasoDeUso) {
        if (diagramaCasoDeUso != null) {
            this.diagramaCasoDeUso2 = diagramaCasoDeUso;
            this.setDadosPanel2();
        }
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     */
    public void setDadosPanel1() {
        if (this.diagramaCasoDeUso1 != null)
            this.setPanelDiagramaCasoDeUso1(new MetricaDiagramaCasoDeUso(this.diagramaCasoDeUso1).getDados());
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 2.
     */
    public void setDadosPanel2() {
        if (this.diagramaCasoDeUso2 != null)
            this.setPanelDiagramaCasoDeUso2(new MetricaDiagramaCasoDeUso(this.diagramaCasoDeUso2).getDados());
    }

    /**
     * Metodo responsavel por retornar o Text Field de Resultado 1.
     * @return Text Field de Resultado 1.
     */
    public JTextField getTextFieldResultado1() {
        return this.textFieldResultado1;
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Caso de Uso 1.
     * @return Diagrama de Caso de Uso 1.
     */
    public DiagramaCasoDeUso getDiagramaCasoDeUso1() {
        return this.diagramaCasoDeUso1;
    }

    /**
     * Metodo responsavel por retornar o Text Field de Resultado 2.
     * @return Text Field de Resultado 2.
     */
    public JTextField getTextFieldResultado2() {
        return this.textFieldResultado2;
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Caso de Uso 2.
     * @return Diagrama de Caso de Uso 2.
     */
    public DiagramaCasoDeUso getDiagramaCasoDeUso2() {
        return this.diagramaCasoDeUso2;
    }
}