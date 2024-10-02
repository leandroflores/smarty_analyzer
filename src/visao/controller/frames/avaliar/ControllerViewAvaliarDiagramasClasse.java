package visao.controller.frames.avaliar;

import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.avaliar.ViewAvaliarDiagramasClasse;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public class ControllerViewAvaliarDiagramasClasse extends ControllerViewAvaliar {
    private final ViewAvaliarDiagramasClasse viewAvaliarDiagramasClasse;
    private LeitorDiagramaClasse leitorDiagramaClasse;
    private FunctAnalyzer functAnalyzer;
    
    public ControllerViewAvaliarDiagramasClasse(ViewAvaliarDiagramasClasse viewAvaliarDiagramasClasse) {
        super(viewAvaliarDiagramasClasse);
        this.viewAvaliarDiagramasClasse = viewAvaliarDiagramasClasse;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.viewAvaliarDiagramasClasse.getButtonAtualizar1().equals(event.getSource())) {
            this.getOperacao1();
        }else if (this.viewAvaliarDiagramasClasse.getButtonAtualizar2().equals(event.getSource())) {
            this.getOperacao2();
        }
    }
    
    /**
     * Metodo responsavel por atualizar o resultado da Operacao 1.
     */
    private void getOperacao1() {
        if (this.viewAvaliarDiagramasClasse.getDiagramaClasse1() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasClasse.getDiagramaClasse1());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasClasse.getTextFieldOperacao1().getText());
                this.viewAvaliarDiagramasClasse.getTextFieldResultado1().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasClasse, "Erro na Expressao da Operacao 1!").setVisible(true);
                this.viewAvaliarDiagramasClasse.getTextFieldOperacao1().requestFocus();
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar o resultado da Operacao 2.
     */
    private void getOperacao2() {
        if (this.viewAvaliarDiagramasClasse.getDiagramaClasse2() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewAvaliarDiagramasClasse.getDiagramaClasse2());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewAvaliarDiagramasClasse.getTextFieldOperacao2().getText());
                this.viewAvaliarDiagramasClasse.getTextFieldResultado2().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewAvaliarDiagramasClasse, "Erro na Expressao da Operacao 2!").setVisible(true);
                this.viewAvaliarDiagramasClasse.getTextFieldOperacao2().requestFocus();
            }
        }
    }

    @Override
    protected void updateInfoPanel1(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewAvaliarDiagramasClasse.setDiagramaClasse1(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected void updateInfoPanel2(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewAvaliarDiagramasClasse.setDiagramaClasse2(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }

    @Override
    protected boolean check() {
        if (this.viewAvaliarDiagramasClasse.getDiagramaClasse1() == null) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Selecione o Diagrama de Classe 1!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasClasse.getDiagramaClasse2() == null) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Selecione o Diagrama de Classe 2!").setVisible(true);
            return false;
        }else if (this.viewAvaliarDiagramasClasse.getTextFieldOperacao1().getText().trim().equals("")) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Digite a Operacao 1!").setVisible(true);
            this.viewAvaliarDiagramasClasse.getTextFieldOperacao1().requestFocus();
            return false;
        }else if (this.viewAvaliarDiagramasClasse.getTextFieldOperacao2().getText().trim().equals("")) {
            new ViewErro(this.viewAvaliarDiagramasClasse, "Digite a Operacao 2!").setVisible(true);
            this.viewAvaliarDiagramasClasse.getTextFieldOperacao2().requestFocus();
            return false;
        }
        return true;
    }

    @Override
    protected void update() {
        this.updateInfoPanel1(this.viewAvaliarDiagramasClasse.getTextFieldModeloUML1().getText());
        this.updateInfoPanel2(this.viewAvaliarDiagramasClasse.getTextFieldModeloUML2().getText());
        this.getOperacao1();
        this.getOperacao2();
    }
}