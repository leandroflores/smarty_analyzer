package visao.frames.exportar;

import visao.controller.frames.exportar.ControllerViewExportarMetricas;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewExportarMetricas extends ViewExportar {

    public ViewExportarMetricas(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewExportarMetricas(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Exportar Metricas");
        this.setSize(570, 320);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("export_metricas.jpg");
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Diretorio*: "));
        this.addBuscaDiretorio();
        this.addLinhas(1);
        
        this.add(this.createLabel("    Nome*: "));
        this.addTextFieldArquivo();
        this.add(this.createLabel("           "));
        this.textFieldArquivo.setText("Metricas.txt");
        this.addLinhas(1);
    }
}