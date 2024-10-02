package visao.frames.importar;

import visao.controller.frames.importar.ControllerViewImportarMetricas;
import visao.frames.estruturais.ViewMenu;

/**
 * 
 * @author Leandro
 */
public final class ViewImportarMetricas extends ViewImportar {

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewImportarMetricas(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewImportarMetricas(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Importar Metricas");
        this.setSize(500, 270);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("import_metricas.jpg");
    }

    @Override
    public void addComponents() {
        this.addSearchTextField();
        this.addLinhas(1);
    }
}