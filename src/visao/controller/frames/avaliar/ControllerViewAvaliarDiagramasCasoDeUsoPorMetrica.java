package visao.controller.frames.avaliar;

import arquivo.importacao.diagramas.LeitorDiagramaCasoDeUso;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.avaliar.ViewAvaliarDiagramasCasoDeUsoPorMetrica;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;

/**
 * 
 * @author Leandro
 */
public class ControllerViewAvaliarDiagramasCasoDeUsoPorMetrica extends ControllerViewAvaliar {
    private final ViewAvaliarDiagramasCasoDeUsoPorMetrica viewAvaliarDiagramasCasoDeUsoPorMetrica;
    private LeitorDiagramaCasoDeUso leitorDiagramaCasoDeUso;
    private FunctAnalyzer functAnalyzer;
    
    public ControllerViewAvaliarDiagramasCasoDeUsoPorMetrica(ViewAvaliarDiagramasCasoDeUsoPorMetrica viewAvaliarDiagramasCasoDeUsoPorMetrica) {
        super(viewAvaliarDiagramasCasoDeUsoPorMetrica);
        this.viewAvaliarDiagramasCasoDeUsoPorMetrica = viewAvaliarDiagramasCasoDeUsoPorMetrica;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getButtonSearchMetrica().equals(event.getSource())) {
            new ViewPesquisaMetrica(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "DIAGRAMA DE CASO DE USO").setVisible(true);
        }
    }
    
    @Override
    protected boolean check() {
        if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso1() == null) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Selecione o Diagrama de Caso de Uso 1!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso2() == null) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Selecione o Diagrama de Caso de Uso 2!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getMetrica() == null) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Selecione a Metrica!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    protected void updateInfoPanel1(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewAvaliarDiagramasCasoDeUsoPorMetrica.setDiagramaCasoDeUso1(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void updateInfoPanel2(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewAvaliarDiagramasCasoDeUsoPorMetrica.setDiagramaCasoDeUso2(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void update() {
        this.updateInfoPanel1(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getTextFieldModeloUML1().getText());
        this.updateInfoPanel2(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getTextFieldModeloUML2().getText());
        this.updateResultado1();
        this.updateResultado2();
    }
    
    /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Classe 2.
     */
    private void updateResultado1() {
        if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso1() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso1());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getMetrica().getOperacao());
                this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getTextFieldResultado1().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Classe 1.
     */
    private void updateResultado2() {
        if (this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso2() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getDiagramaCasoDeUso2());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getMetrica().getOperacao());
                this.viewAvaliarDiagramasCasoDeUsoPorMetrica.getTextFieldResultado2().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasCasoDeUsoPorMetrica, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
}