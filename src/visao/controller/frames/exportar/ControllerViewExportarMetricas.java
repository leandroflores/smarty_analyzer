package visao.controller.frames.exportar;

import arquivo.exportacao.diagramas.ExportarMetricas;
import java.io.IOException;
import modelo.dao.entidades.DaoMetrica;
import visao.frames.exportar.ViewExportarMetricas;
import visao.frames.mensagem.ViewErro;
import visao.frames.mensagem.ViewInformacao;

/**
 *
 * @author Leandro
 */
public class ControllerViewExportarMetricas extends ControllerViewExportar {
    private final ViewExportarMetricas viewExportarMetricas;
    private ExportarMetricas exportarMetricas;

    public ControllerViewExportarMetricas(ViewExportarMetricas viewExportarMetricas) {
        super(viewExportarMetricas);
        this.viewExportarMetricas = viewExportarMetricas;
    }

    @Override
    public void exportacao() {
        String diretorio = this.viewExportarMetricas.getTextFieldDiretorio().getText().trim();
        String arquivo   = this.viewExportarMetricas.getTextFieldArquivo().getText().trim();
        this.exportarMetricas = new ExportarMetricas(diretorio + "\\" + arquivo, new DaoMetrica().select());
        try {
            this.exportarMetricas.exportar();
            new ViewInformacao(this.viewExportarMetricas, "Metricas exportadas com Sucesso!").setVisible(true);
            this.viewExportarMetricas.dispose();
//        String arquivo   = this.viewExportarMetricas.getTextFieldArquivo().getText().trim();
//        String diretorio = this.viewExportarMetricas.getTextFieldDiretorio().getText().trim();
//        
//        this.leitorDiagramaClasse   = new LeitorDiagramaClasse(arquivo);
//        try {
//            this.leitorDiagramaClasse.createModeloUML();
//            this.exportarDiagramaClasse = new ExportarDiagramaClasse(diretorio, this.leitorDiagramaClasse.getDiagramaClasse());
//            this.exportarDiagramaClasse.exportar();
//            new ViewInformacao(this.viewExportarMetricas, "Diagrama exportado com Sucesso!").setVisible(true);
//        }catch (XPathExpressionException exception) {
//            new ViewErro(this.viewExportarMetricas, "Erro na Leitura do Modelo!").setVisible(true);
//        } catch (ParserConfigurationException | SAXException exception) {
//            new ViewErro(this.viewExportarMetricas, "Erro na Leitura do Arquivo!").setVisible(true);
//        } catch (IOException exception) {
//            new ViewErro(this.viewExportarMetricas, "Erro na Criacao do Arquivo!").setVisible(true);
//        }
        } catch (IOException exception) {
            new ViewErro(this.viewExportarMetricas, "Erro na Criacao do Arquivo!").setVisible(true);
        }
    }

    @Override
    public boolean check() {
        String diretorio = this.viewExportarMetricas.getTextFieldDiretorio().getText().trim();
        if (diretorio.equals("")) {
            new ViewErro(this.viewExportarMetricas, "Selecione um Diretorio!").setVisible(true);
            return false;
        }
        return this.checkArquivo();
    }
}