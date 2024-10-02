package visao.frames.processar;

import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;
import visao.controller.frames.processar.ControllerViewProcessarMetricaDiagramaClasse;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewProcessarMetricaDiagramaClasse extends ViewProcessar {
    private DiagramaClasse diagramaClasse;
    
    public ViewProcessarMetricaDiagramaClasse(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewProcessarMetricaDiagramaClasse(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Processar Metrica sobre Diagrama de Classe");
        this.setSize(650, 575);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("metrica_diagrama_classe.jpg");
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Diagrama de Classe*: "));
        this.addSearchTextField();
        this.addPanel();
        this.addFooter();
    }
    
    /**
     * Metodo responsavel por adicinar o Painel de Informacoes.
     */
    private void addPanel() {
        this.addLinhas(1);
        this.addPanelDiagramaClasse();
        this.addLinhas(1);
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Classe.
     * @return Diagrama de Classe.
     */
    public DiagramaClasse getDiagramaClasse() {
        return this.diagramaClasse;
    }

    /**
     * Metodo responsavel por definir o Diagrama de Classe.
     * @param diagramaClasse Diagrama de Classe.
     */
    public void setDiagramaClasse(DiagramaClasse diagramaClasse) {
        if (diagramaClasse != null) {
            this.diagramaClasse = diagramaClasse;
            this.setDadosPanel();
        }
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel.
     */
    public void setDadosPanel() {
        if (this.diagramaClasse != null)
            this.setPanelDiagramaClasse(new MetricaDiagramaClasse(this.diagramaClasse).getDados());
    }
}