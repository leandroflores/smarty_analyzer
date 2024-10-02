package visao.controller.frames.estruturais;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import visao.controller.frames.ControllerView;
import visao.frames.analisar.ViewAnalisarDiagramaCasoDeUso;
import visao.frames.analisar.ViewAnalisarDiagramaClasse;
import visao.frames.avaliar.ViewAvaliarDiagramasCasoDeUsoPorMetrica;
import visao.frames.avaliar.ViewAvaliarDiagramasCasoDeUso;
import visao.frames.avaliar.ViewAvaliarDiagramasClasse;
import visao.frames.avaliar.ViewAvaliarDiagramasClassePorMetrica;
import visao.frames.avaliar.medicao.ViewAvaliarMedicaoDiagramaCasoDeUso;
import visao.frames.avaliar.medicao.ViewAvaliarMedicaoDiagramaClasse;
import visao.frames.consulta.ViewConsultaMedicao;
import visao.frames.consulta.ViewConsultaMetrica;
import visao.frames.estruturais.ViewMenu;
import visao.frames.exportar.ViewExportarMetricas;
import visao.frames.importar.ViewImportarMetricas;
import visao.frames.mensagem.ViewErro;
import visao.frames.processar.ViewProcessarMetricaDiagramaCasoDeUso;
import visao.frames.processar.ViewProcessarMetricaDiagramaClasse;
import visao.frames.sistema.ViewSistemaSair;
import visao.frames.sistema.ViewSistemaSobre;

/**
 * <p>Classe de Controle <b>ControllerViewMenu</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewMenu do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerView
 * @see    view.estrutural.ViewMenu
 */
public class ControllerViewMenu extends ControllerView {
    private final ViewMenu viewMenu;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View de Menu do Sistema.
     */
    public ControllerViewMenu(ViewMenu viewMenu) {
        super(viewMenu);
        this.viewMenu = viewMenu;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewMenu.getMenuItemCadastroMetrica().equals(event.getSource())) {
            new ViewConsultaMetrica(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemCadastroMedicao().equals(event.getSource())) {
            new ViewConsultaMedicao(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemProcessarDiagramaClasse().equals(event.getSource())) {
            new ViewProcessarMetricaDiagramaClasse(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemProcessarDiagramaCasoDeUso().equals(event.getSource())) {
            new ViewProcessarMetricaDiagramaCasoDeUso(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAnalisarDiagramaClasse().equals(event.getSource())) {
            new ViewAnalisarDiagramaClasse(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAnalisarDiagramaCasoDeUso().equals(event.getSource())) {
            new ViewAnalisarDiagramaCasoDeUso(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarDiagramasClasse().equals(event.getSource())) {
            new ViewAvaliarDiagramasClasse(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarDiagramasCasoDeUso().equals(event.getSource())) {
            new ViewAvaliarDiagramasCasoDeUso(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarDiagramasClassePorMetrica().equals(event.getSource())) {
            new ViewAvaliarDiagramasClassePorMetrica(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarDiagramasCasoDeUsoPorMetrica().equals(event.getSource())) {
            new ViewAvaliarDiagramasCasoDeUsoPorMetrica(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarMedicoesDiagramasClasse().equals(event.getSource())) {
            new ViewAvaliarMedicaoDiagramaClasse(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemAvaliarMedicoesDiagramasCasoDeUso().equals(event.getSource())) {
            new ViewAvaliarMedicaoDiagramaCasoDeUso(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemImportarMetricas().equals(event.getSource())) {
            new ViewImportarMetricas(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemExportarMetricas().equals(event.getSource())) {
            new ViewExportarMetricas(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemExportarCodigoFonte().equals(event.getSource())) {
//            new ViewExportarModelo(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemSistemaSobre().equals(event.getSource())) {
            new ViewSistemaSobre(this.viewMenu).setVisible(true);
        }else if (this.viewMenu.getMenuItemSistemaSite().equals(event.getSource())) {
            try {
                File   file = new File("site/index.html");
                String url  = file.getAbsolutePath();
                       url  = url.replaceAll("\\\\", "/");
                if (file.exists())
                    Desktop.getDesktop().browse(new URL("file:///" + url).toURI());                
                else
                    new ViewErro(this.viewMenu, "Pagina nao encontrada!").setVisible(true);
            } catch (URISyntaxException | IOException oException) {
                new ViewErro(this.viewMenu, "Erro ao Abrir Navegador!").setVisible(true);
            }
        }else if (this.viewMenu.getMenuItemSistemaSair().equals(event.getSource())) {
            new ViewSistemaSair(this.viewMenu).setVisible(true);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (ESC == event.getKeyCode()) {
            new ViewSistemaSair(this.viewMenu).setVisible(true);
        }
    }
}