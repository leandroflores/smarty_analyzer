package visao.controller.frames.processar;

import arquivo.importacao.diagramas.LeitorDiagramaCasoDeUso;
import java.io.IOException;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import modelo.dao.entidades.DaoMedicao;
import modelo.entidades.Medicao;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso;
import org.xml.sax.SAXException;
import utils.FunctAnalyzer;
import visao.frames.mensagem.ViewErro;
import visao.frames.mensagem.ViewInformacao;
import visao.frames.processar.ViewProcessarMetricaDiagramaCasoDeUso;

/**
 *
 * @author Leandro
 */
public class ControllerViewProcessarMetricaDiagramaCasoDeUso extends ControllerViewProcessar {
    private final ViewProcessarMetricaDiagramaCasoDeUso viewProcessarMetricaDiagramaCasoDeUso;
    private final DaoMedicao daoMedicao;
    private LeitorDiagramaCasoDeUso leitorDiagramaCasoDeUso;
    private FunctAnalyzer functAnalyzer;

    public ControllerViewProcessarMetricaDiagramaCasoDeUso(ViewProcessarMetricaDiagramaCasoDeUso viewProcessarMetricaDiagramaCasoDeUso) {
        super(viewProcessarMetricaDiagramaCasoDeUso);
        this.viewProcessarMetricaDiagramaCasoDeUso = viewProcessarMetricaDiagramaCasoDeUso;
        this.daoMedicao = new DaoMedicao();
        this.tipo       = "DIAGRAMA DE CASO DE USO";
    }

    @Override
    public void save() {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldModeloUML().getText());
        try {
            DiagramaCasoDeUso diagramaCasoDeUso = this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso();
            String[] dadosDiagrama = new MetricaDiagramaCasoDeUso(diagramaCasoDeUso).getDados();
            this.functAnalyzer     = new FunctAnalyzer(diagramaCasoDeUso);
            Medicao medicao        = new Medicao();
                    medicao.setId(this.daoMedicao.nextId());
                    medicao.setArquivo(this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldModeloUML().getText());
                    medicao.setDiagramaAlvo(this.tipo);
                    medicao.setMetrica(this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica());
                    medicao.setNome(this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldNomeMedicao().getText().toUpperCase());
                    medicao.setOperacao(this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica().getOperacao());
                    medicao.setValor(this.functAnalyzer.getValorFinal(this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica().getOperacao()));
                    medicao.setValor1(Integer.parseInt(dadosDiagrama[1]));
                    medicao.setValor2(Integer.parseInt(dadosDiagrama[2]));
            this.daoMedicao.insert(medicao);
            new ViewInformacao(this.viewProcessarMetricaDiagramaCasoDeUso, "Medicao salva com Sucesso!").setVisible(true);
            this.viewProcessarMetricaDiagramaCasoDeUso.dispose();
        }catch (XPathExpressionException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro na Expressao XPath!").setVisible(true);
        }catch (ParserConfigurationException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro 2!").setVisible(true);
        }catch (SAXException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro 3!").setVisible(true);            
        }catch (IOException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro 4!").setVisible(true);
        }catch (ScriptException ex) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro 5!").setVisible(true);
        }
    }

    @Override
    public boolean check() {
        if (this.viewProcessarMetricaDiagramaCasoDeUso.getDiagramaCasoDeUso() == null) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Selecione o Diagrama de Caso de Uso!").setVisible(true);
            return false;
        }else if (this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica() == null) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Selecione a Metrica!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    protected void update() {
        if (this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldModeloUML().getText() != null)
            this.updateInfoPanel(this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldModeloUML().getText());
        
        if (this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica() != null)
            this.updateResultado();
    }
    
    @Override
    protected void updateInfoPanel(String caminho) {
        this.leitorDiagramaCasoDeUso = new LeitorDiagramaCasoDeUso(caminho);
        try {
            this.viewProcessarMetricaDiagramaCasoDeUso.setDiagramaCasoDeUso(this.leitorDiagramaCasoDeUso.getDiagramaCasoDeUso());
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro na Leitura do Arquivo XMI!").setVisible(true);
        }
    }
    
     /**
     * Metodo responsavel por atualizar o Resultado do Diagrama de Caso de Uso.
     */
    private void updateResultado() {
        if (this.viewProcessarMetricaDiagramaCasoDeUso.getDiagramaCasoDeUso() != null) {
            this.functAnalyzer = new FunctAnalyzer(this.viewProcessarMetricaDiagramaCasoDeUso.getDiagramaCasoDeUso());
            try {
                Double valor = this.functAnalyzer.getValorFinal(this.viewProcessarMetricaDiagramaCasoDeUso.getMetrica().getOperacao());
                this.viewProcessarMetricaDiagramaCasoDeUso.getTextFieldValorMetrica().setText(Double.toString(valor));
            }catch (Exception exception) {
                new ViewErro(this.viewProcessarMetricaDiagramaCasoDeUso, "Erro na Expressao da Metrica!").setVisible(true);
            }
        }
    }
}