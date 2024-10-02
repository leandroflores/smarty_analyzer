package visao.frames.analisar;

import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso;
import visao.controller.frames.analisar.ControllerViewAnalisarDiagramaCasoDeUso;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewAnalisarDiagramaCasoDeUso</b>.</p>
 * <p>Classe responsavel por representar 
 * @author Leandro
 */
public final class ViewAnalisarDiagramaCasoDeUso extends ViewAnalisar {
    private DiagramaCasoDeUso diagramaCasoDeUso;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAnalisarDiagramaCasoDeUso(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAnalisarDiagramaCasoDeUso(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Analisar Diagrama de Caso de Uso");
        this.setSize(680, 520);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("analisar_diagrama_caso_de_uso.jpg");
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
     * Metodo responsavel por retornar o Diagrama de Caso de Uso UML.
     * @return Diagrama de Caso de Uso UML.
     */
    public DiagramaCasoDeUso getDiagramaCasoDeUso() {
        return this.diagramaCasoDeUso;
    }
    
    /**
     * Metodo responsavel por definir o Diagrama de Caso de Uso.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso UML.
     */
    public void setDiagramaCasoDeUso(DiagramaCasoDeUso diagramaCasoDeUso) {
        if (diagramaCasoDeUso != null) {
            this.diagramaCasoDeUso = diagramaCasoDeUso;
            this.setDadosPanel();
        }
    }
    
    /**
     * Metodo responsavel por atualizar as Informacoes do Diagrama de Classe.
     */
    public void setDadosPanel() {
        this.setPanelDiagramaCasoDeUso(new MetricaDiagramaCasoDeUso(this.diagramaCasoDeUso).getDados());
    }
}