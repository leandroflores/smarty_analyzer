package visao.controller.frames.processar;

import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.io.IOException;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import modelo.dao.entidades.DaoMedicao;
import modelo.entidades.Medicao;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.mensagem.ViewErro;
import visao.frames.mensagem.ViewInformacao;
import visao.frames.processar.ViewProcessarMetricaDiagramaClasse;

/**
 *
 * @author Leandro
 */
public class ControllerViewProcessarMetricaDiagramaClasse extends ControllerViewProcessar {
    private final ViewProcessarMetricaDiagramaClasse viewProcessarMetricaDiagramaClasse;
    private final DaoMedicao daoMedicao;
    private LeitorDiagramaClasse leitorDiagramaClasse;
    private FunctAnalyzer functAnalyzer;

    public ControllerViewProcessarMetricaDiagramaClasse(ViewProcessarMetricaDiagramaClasse viewProcessarMetricaDiagramaClasse) {
        super(viewProcessarMetricaDiagramaClasse);
        this.viewProcessarMetricaDiagramaClasse = viewProcessarMetricaDiagramaClasse;
        this.daoMedicao = new DaoMedicao();
        this.tipo       = "DIAGRAMA DE CLASSE";
    }

    @Override
    public void save() {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(this.viewProcessarMetricaDiagramaClasse.getTextFieldModeloUML().getText());
        try {
            DiagramaClasse diagramaClasse = this.leitorDiagramaClasse.getDiagramaClasse();
            String[]       dadosDiagrama  = new MetricaDiagramaClasse(diagramaClasse).getDados();
            this.functAnalyzer            = new FunctAnalyzer(diagramaClasse);
            Medicao medicao    = new Medicao();
                    medicao.setId(this.daoMedicao.nextId());
                    medicao.setArquivo(this.viewProcessarMetricaDiagramaClasse.getTextFieldModeloUML().getText());
                    medicao.setDiagramaAlvo(this.tipo);
                    medicao.setMetrica(this.viewProcessarMetricaDiagramaClasse.getMetrica());
                    medicao.setNome(this.viewProcessarMetricaDiagramaClasse.getTextFieldNomeMedicao().getText().toUpperCase());
                    medicao.setOperacao(this.viewProcessarMetricaDiagramaClasse.getMetrica().getOperacao());
                    medicao.setValor(this.functAnalyzer.getValorFinal(this.viewProcessarMetricaDiagramaClasse.getMetrica().getOperacao()));
                    medicao.setValor1(Integer.parseInt(dadosDiagrama[1]));
                    medicao.setValor2(Integer.parseInt(dadosDiagrama[2]));
            this.daoMedicao.insert(medicao);
            new ViewInformacao(this.viewProcessarMetricaDiagramaClasse, "Medicao salva com Sucesso!").setVisible(true);
            this.viewProcessarMetricaDiagramaClasse.dispose();
        }catch (XPathExpressionException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro na Expressao XPath!").setVisible(true);
        }catch (ParserConfigurationException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro 2!").setVisible(true);
        }catch (SAXException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro 3!").setVisible(true);            
        }catch (IOException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro 4!").setVisible(true);
        }catch (ScriptException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro 5!").setVisible(true);
        }
    }

    @Override
    public boolean check() {
        if (this.viewProcessarMetricaDiagramaClasse.getDiagramaClasse() == null) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Selecione o Diagrama de Classes!").setVisible(true);
            return false;
        }else if (this.viewProcessarMetricaDiagramaClasse.getMetrica() == null) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Selecione a Metrica!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    protected void update() {
        if (this.viewProcessarMetricaDiagramaClasse.getTextFieldModeloUML().getText() != null)
            this.updateInfoPanel(this.viewProcessarMetricaDiagramaClasse.getTextFieldModeloUML().getText());
        
        if (this.viewProcessarMetricaDiagramaClasse.getMetrica() != null)
            this.updateResultado();
    }
    
    @Override
    protected void updateInfoPanel(String caminho) {
        this.leitorDiagramaClasse = new LeitorDiagramaClasse(caminho);
        try {
            this.viewProcessarMetricaDiagramaClasse.setDiagramaClasse(this.leitorDiagramaClasse.getDiagramaClasse());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }
    
     /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Classe.
     */
    private void updateResultado() {
        if (this.viewProcessarMetricaDiagramaClasse.getDiagramaClasse() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewProcessarMetricaDiagramaClasse.getDiagramaClasse());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewProcessarMetricaDiagramaClasse.getMetrica().getOperacao());
                this.viewProcessarMetricaDiagramaClasse.getTextFieldValorMetrica().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewProcessarMetricaDiagramaClasse, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
}