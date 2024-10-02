package visao.frames.processar;

import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso;
import visao.controller.frames.processar.ControllerViewProcessarMetricaDiagramaCasoDeUso;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewProcessarMetricaDiagramaCasoDeUso extends ViewProcessar {
    private DiagramaCasoDeUso diagramaCasoDeUso;

    public ViewProcessarMetricaDiagramaCasoDeUso(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewProcessarMetricaDiagramaCasoDeUso(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Processar Metrica sobre Diagrama de Caso de Uso");
        this.setSize(675, 575);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("metrica_diagrama_caso_de_uso.jpg");
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Diagrama de Caso de Uso*: "));
        this.addSearchTextField();
        this.addPanel();
        this.addFooter();
    }
    
    /**
     * Metodo responsavel por adicinar o Painel de Informacoes.
     */
    private void addPanel() {
        this.addLinhas(1);
        this.addPanelDiagramaCasoDeUso();
        this.addLinhas(1);
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Caso de Uso.
     * @return Diagrama de Caso de Uso.
     */
    public DiagramaCasoDeUso getDiagramaCasoDeUso() {
        return this.diagramaCasoDeUso;
    }

    /**
     * Metodo responsavel por definir o Diagrama de Caso de Uso.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso.
     */
    public void setDiagramaCasoDeUso(DiagramaCasoDeUso diagramaCasoDeUso) {
        if (diagramaCasoDeUso != null) {
            this.diagramaCasoDeUso = diagramaCasoDeUso;
            this.setDadosPanel();
        }
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel.
     */
    public void setDadosPanel() {
        if (this.diagramaCasoDeUso != null)
            this.setPanelDiagramaCasoDeUso(new MetricaDiagramaCasoDeUso(this.diagramaCasoDeUso).getDados());
    }
}