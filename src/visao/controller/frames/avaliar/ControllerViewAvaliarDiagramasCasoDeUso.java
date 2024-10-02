package visao.controller.frames.avaliar;

import arquivo.importacao.diagramas.LeitorDiagramaCasoDeUso;
import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.avaliar.ViewAvaliarDiagramasCasoDeUso;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public class ControllerViewAvaliarDiagramasCasoDeUso extends ControllerViewAvaliar {
    private final ViewAvaliarDiagramasCasoDeUso viewAvaliarDiagramasCasoDeUso;
    private LeitorDiagramaCasoDeUso leitorDiagramaCasoDeUso;
    private FunctAnalyzer functAnalyzer;
    
    public ControllerViewAvaliarDiagramasCasoDeUso(ViewAvaliarDiagramasCasoDeUso viewAvaliarDiagramasCasoDeUso) {
        super(viewAvaliarDiagramasCasoDeUso);
        this.viewAvaliarDiagramasCasoDeUso = viewAvaliarDiagramasCasoDeUso;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.viewAvaliarDiagramasCasoDeUso.getButtonAtualizar1().equals(event.getSource())) {
            this.getOperacao1();
        }else if (this.viewAvaliarDiagramasCasoDeUso.getButtonAtualizar2().equals(event.getSource())) {
            this.getOperacao2();
        }
    }
    
    /**
     * Metodo responsavel por atualizar o resultado da Operacao 1.
     */
    private void getOperacao1() {
        if (this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso1() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso1());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao1().getText());
                this.viewAvaliarDiagramasCasoDeUso.getTextFieldResultado1().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Erro na Expressao da Operacao 1!").setVisible(true);
                this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao1().requestFocus();
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar o resultado da Operacao 2.
     */
    private void getOperacao2() {
        if (this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso2() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso2());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao2().getText());
                this.viewAvaliarDiagramasCasoDeUso.getTextFieldResultado2().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Erro na Expressao da Operacao 2!").setVisible(true);
                this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao2().requestFocus();
            }
        }
    }

    @Override
    protected void updateInfoPanel1(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewAvaliarDiagramasCasoDeUso.setDiagramaCasoDeUso1(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void updateInfoPanel2(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewAvaliarDiagramasCasoDeUso.setDiagramaCasoDeUso2(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected boolean check() {
        if (this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso1() == null) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Selecione o Diagrama de Classe 1!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasCasoDeUso.getDiagramaCasoDeUso2() == null) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Selecione o Diagrama de Classe 2!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao1().getText().trim().equals("")) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Digite a Operacao 1!").setVisible(true);
            this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao1().requestFocus();
            return false;
        }else if (this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao2().getText().trim().equals("")) {
            new ViewErro(this.viewAvaliarDiagramasCasoDeUso, "Digite a Operacao 2!").setVisible(true);
            this.viewAvaliarDiagramasCasoDeUso.getTextFieldOperacao2().requestFocus();
            return false;
        }
        return true;
    }

    @Override
    protected void update() {
        this.updateInfoPanel1(this.viewAvaliarDiagramasCasoDeUso.getTextFieldModeloUML1().getText());
        this.updateInfoPanel2(this.viewAvaliarDiagramasCasoDeUso.getTextFieldModeloUML2().getText());
        this.getOperacao1();
        this.getOperacao2();
    }
}