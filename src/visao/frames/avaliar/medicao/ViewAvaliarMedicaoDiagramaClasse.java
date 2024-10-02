package visao.frames.avaliar.medicao;

import visao.controller.frames.avaliar.medicao.ControllerViewAvaliarMedicaoDiagramaClasse;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewAvaliarMedicaoDiagramaClasse extends ViewAvaliarMedicao {

    public ViewAvaliarMedicaoDiagramaClasse(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAvaliarMedicaoDiagramaClasse(this);
        this.tipo       = "DIAGRAMA DE CLASSE";
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Avaliar Medicoes sobre Diagrama de Classe");
        this.setSize(1000, 650);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("avaliar_medicao_diagrama_classe.jpg");
    }

    @Override
    public void addComponents() {
        this.createTextField();
        this.addSearch();
        this.addPanel();
    }
    
    private void addSearch() {
        this.addLinhas(1);
        this.addSearchMetrica();
        this.addLinhas(1);
        this.addFiltrosDiagramaClasse();
        this.addLinhas(1);
    }
    
    private void addPanel() {
        this.addPanelInformacao();
        this.addPanelGrafico();
        this.addLinhas(1);
    }
}