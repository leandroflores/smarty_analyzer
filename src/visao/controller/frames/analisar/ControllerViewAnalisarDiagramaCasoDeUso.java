package visao.controller.frames.analisar;

import arquivo.importacao.diagramas.LeitorDiagramaCasoDeUso;
import java.io.IOException;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.analisar.ViewAnalisarDiagramaCasoDeUso;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public class ControllerViewAnalisarDiagramaCasoDeUso extends ControllerViewAnalisar {
    private final ViewAnalisarDiagramaCasoDeUso viewAnalisarDiagramaCasoDeUso;
    private LeitorDiagramaCasoDeUso leitorDiagramaCasoDeUso;
    
    public ControllerViewAnalisarDiagramaCasoDeUso(ViewAnalisarDiagramaCasoDeUso viewAnalisar) {
        super(viewAnalisar);
        this.viewAnalisarDiagramaCasoDeUso = viewAnalisar;
    }
    
    @Override
    protected void update() {
        if (this.viewAnalisarDiagramaCasoDeUso.getDiagramaCasoDeUso() != null) {
            FunctAnalyzer functAnalyzer = new FunctAnalyzer(this.viewAnalisarDiagramaCasoDeUso.getDiagramaCasoDeUso());
            try {
                Double valor = functAnalyzer.getValorFinal(this.viewAnalisarDiagramaCasoDeUso.getTextFieldOperacao().getText());
                this.viewAnalisarDiagramaCasoDeUso.getTextFieldResultado().setText(Double.toString(valor));
            }catch (ScriptException exception) {
                new ViewErro(this.viewAnalisarDiagramaCasoDeUso, "Erro na Expressao da Operacao!").setVisible(true);
            }
        }
    }

    @Override
    protected void updateInfoPanel(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewAnalisarDiagramaCasoDeUso.setDiagramaCasoDeUso(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAnalisarDiagramaCasoDeUso, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }
}