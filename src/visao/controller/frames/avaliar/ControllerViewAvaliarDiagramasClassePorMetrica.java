package visao.controller.frames.avaliar;

import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.avaliar.ViewAvaliarDiagramasClassePorMetrica;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;

/**
 * 
 * @author Leandro
 */
public class ControllerViewAvaliarDiagramasClassePorMetrica extends ControllerViewAvaliar {
    private final ViewAvaliarDiagramasClassePorMetrica viewAvaliarDiagramasClassePorMetrica;
    private LeitorDiagramaClasse leitorDiagramaClasse;
    private FunctAnalyzer functAnalyzer;
    
    public ControllerViewAvaliarDiagramasClassePorMetrica(ViewAvaliarDiagramasClassePorMetrica viewAvaliarDiagramasClassePorMetrica) {
        super(viewAvaliarDiagramasClassePorMetrica);
        this.viewAvaliarDiagramasClassePorMetrica = viewAvaliarDiagramasClassePorMetrica;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.viewAvaliarDiagramasClassePorMetrica.getButtonSearchMetrica().equals(event.getSource())) {
            new ViewPesquisaMetrica(this.viewAvaliarDiagramasClassePorMetrica, "DIAGRAMA DE CLASSE").setVisible(true);
        }
    }
    
    @Override
    protected boolean check() {
        if (this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse1() == null) {
            new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Selecione o Diagrama de Classes 1!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse2() == null) {
            new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Selecione o Diagrama de Classes 2!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasClassePorMetrica.getMetrica() == null) {
            new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Selecione a Metrica!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    protected void updateInfoPanel1(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewAvaliarDiagramasClassePorMetrica.setDiagramaClasse1(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void updateInfoPanel2(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewAvaliarDiagramasClassePorMetrica.setDiagramaClasse2(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void update() {
        this.updateInfoPanel1(this.viewAvaliarDiagramasClassePorMetrica.getTextFieldModeloUML1().getText());
        this.updateInfoPanel2(this.viewAvaliarDiagramasClassePorMetrica.getTextFieldModeloUML2().getText());
        this.updateResultado1();
        this.updateResultado2();
    }
    
    /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Classe 2.
     */
    private void updateResultado1() {
        if (this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse1() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse1());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasClassePorMetrica.getMetrica().getOperacao());
                this.viewAvaliarDiagramasClassePorMetrica.getTextFieldResultado1().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Classe 1.
     */
    private void updateResultado2() {
        if (this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse2() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasClassePorMetrica.getDiagramaClasse2());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasClassePorMetrica.getMetrica().getOperacao());
                this.viewAvaliarDiagramasClassePorMetrica.getTextFieldResultado2().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasClassePorMetrica, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
}