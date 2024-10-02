package visao.controller.frames.analisar;

import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.io.IOException;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.analisar.ViewAnalisarDiagramaClasse;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public class ControllerViewAnalisarDiagramaClasse extends ControllerViewAnalisar {
    private final ViewAnalisarDiagramaClasse viewAnalisarDiagramaClasse;
    private LeitorDiagramaClasse leitorDiagramaClasse;
    
    public ControllerViewAnalisarDiagramaClasse(ViewAnalisarDiagramaClasse viewAnalisar) {
        super(viewAnalisar);
        this.viewAnalisarDiagramaClasse = viewAnalisar;
    }
    
    @Override
    protected void update() {
        if (this.viewAnalisarDiagramaClasse.getDiagramaClasse() != null) {
            FunctAnalyzer functAnalyzer = new FunctAnalyzer(this.viewAnalisarDiagramaClasse.getDiagramaClasse());
            try {
                Double valor = functAnalyzer.getValorFinal(this.viewAnalisarDiagramaClasse.getTextFieldOperacao().getText());
                this.viewAnalisarDiagramaClasse.getTextFieldResultado().setText(Double.toString(valor));
            }catch (ScriptException exception) {
                new ViewErro(this.viewAnalisarDiagramaClasse, "Erro na Expressao da Operacao!").setVisible(true);
            }
        }
    }

    @Override
    protected void updateInfoPanel(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewAnalisarDiagramaClasse.setDiagramaClasse(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAnalisarDiagramaClasse, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }
}