package visao.frames.analisar;

import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;
import visao.controller.frames.analisar.ControllerViewAnalisarDiagramaClasse;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewAnalisarDiagramaClasse</b>.</p>
 * <p>Classe responsavel por representar 
 * @author Leandro
 */
public final class ViewAnalisarDiagramaClasse extends ViewAnalisar {
    private DiagramaClasse diagramaClasse;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAnalisarDiagramaClasse(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAnalisarDiagramaClasse(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Analisar Diagrama de Classe");
        this.setSize(650, 580);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("analisar_diagrama_classes.jpg");
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
     * Metodo responsavel por retornar o Diagrama de Classe UML.
     * @return Diagrama de Classe UML.
     */
    public DiagramaClasse getDiagramaClasse() {
        return this.diagramaClasse;
    }
    
    /**
     * Metodo responsavel por definir o Diagrama de Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public void setDiagramaClasse(DiagramaClasse diagramaClasse) {
        if (diagramaClasse != null) {
            this.diagramaClasse = diagramaClasse;
            this.setDadosPanel();
        }
    }
    
    /**
     * Metodo responsavel por atualizar as Informacoes do Diagrama de Classe.
     */
    public void setDadosPanel() {
        this.setPanelDiagramaClasse(new MetricaDiagramaClasse(this.diagramaClasse).getDados());
    }
}