package visao.frames.avaliar.medicao;

import visao.controller.frames.avaliar.medicao.ControllerViewAvaliarMedicaoDiagramaCasoDeUso;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewAvaliarMedicaoDiagramaCasoDeUso extends ViewAvaliarMedicao {

    public ViewAvaliarMedicaoDiagramaCasoDeUso(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAvaliarMedicaoDiagramaCasoDeUso(this);
        this.tipo       = "DIAGRAMA DE CASO DE USO";
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Avaliar Medicoes sobre Diagrama de Caso de Uso");
        this.setSize(1000, 650);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("avaliar_medicao_diagrama_caso_de_uso.jpg");
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
        this.addFiltrosDiagramaCasoDeUso();
        this.addLinhas(1);
    }
    
    private void addPanel() {
        this.addPanelInformacao();
        this.addPanelGrafico();
        this.addLinhas(1);
    }
}