package visao.controller.frames.importar;

import arquivo.importacao.diagramas.LeitorMetricas;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import visao.frames.importar.ViewImportarMetricas;
import visao.frames.mensagem.ViewErro;
import visao.frames.mensagem.ViewInformacao;

/**
 *
 * @author Leandro
 */
public class ControllerViewImportarMetricas extends ControllerViewImportar {
    private final ViewImportarMetricas viewImportarMetricas;
    private LeitorMetricas leitorMetricas;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewImportarMetricas View Importar Metricas do Sistema.
     */
    public ControllerViewImportarMetricas(ViewImportarMetricas viewImportarMetricas) {
        super(viewImportarMetricas);
        this.viewImportarMetricas = viewImportarMetricas;
    }

    @Override
    public void importacao() {
        this.leitorMetricas = new LeitorMetricas(this.viewImportarMetricas.getTextFieldArquivo().getText().trim());
        try {
            this.leitorMetricas.read();
            new ViewInformacao(this.viewImportarMetricas, this.leitorMetricas.getTotal() + " Metricas inseridas com Sucesso!").setVisible(true);
        }catch (IOException exception) {
            new ViewErro(this.viewImportarMetricas, "Erro na Abertura do Arquivo!").setVisible(true);
        }catch (ParserConfigurationException | SAXException | XPathExpressionException ex) {
            new ViewErro(this.viewImportarMetricas, "Erro na Importacao das Metricas!").setVisible(true);
        }
    }
}