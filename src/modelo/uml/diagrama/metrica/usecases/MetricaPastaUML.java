package modelo.uml.diagrama.metrica.usecases;

import arquivo.importacao.diagramas.LeitorDiagramaCasoDeUso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.usecases.PastaUML;
import org.xml.sax.SAXException;

/**
 * <p>Classe de Metrica <b>MetricaPastaUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre as <b>Pastas UML</b>.</p>
 * @author Leandro
 * @since  10/05/2017
 * @see    modelo.uml.diagrama.DiagramaCasoDeUso
 * @see    modelo.uml.diagrama.usecases.PastaUML
 */
public class MetricaPastaUML {
    private final DiagramaCasoDeUso diagramaCasoDeUso;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso UML.
     */
    public MetricaPastaUML(DiagramaCasoDeUso diagramaCasoDeUso) {
        this.diagramaCasoDeUso = diagramaCasoDeUso;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pastas UML do Diagrama de Caso de Uso.
     * @return Lista de Pastas UML de todo o Diagrama de Caso de Uso.
     */
    public List<PastaUML> getPastasDiagramaCasoDeUso() {
        List<PastaUML> pastas = new ArrayList<>();
                       pastas.add(this.diagramaCasoDeUso.getRaiz());
                       pastas.addAll(this.getPastasUML(this.diagramaCasoDeUso.getRaiz()));
        return pastas;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pastas UML de uma Pasta UML.
     * @param  pastaUML Pasta UML raiz.
     * @return Lista de Pastas UML encontradas.
     */
    public List<PastaUML> getPastasUML(PastaUML pastaUML) {
        List<PastaUML> pastasUML = pastaUML.getFilhos();
        for (int i = 0; i < pastaUML.getFilhos().size(); i++) {
            pastasUML.addAll(this.getPastasUML(pastaUML.getFilhos().get(i)));
        }
        return pastasUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pastas UML contido na Lista de Nomes.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Pastas UML encontradas.
     */
    public List<PastaUML> filterNomes(List<String> nomes) {
        if (nomes == null)
            return this.getPastasDiagramaCasoDeUso();
        if (nomes.isEmpty())
            return this.getPastasDiagramaCasoDeUso();
        if (nomes.get(0).equals("*"))
            return this.getPastasDiagramaCasoDeUso();
        if (nomes.contains("src"))
            return this.getPastasDiagramaCasoDeUso();
        return this.getPastasDiagramaCasoDeUso();
//        return this.diagramaCasoDeUso.getSubpacotesUML(nomes);
    }
    
    public static void main(String[] args) {
        String caminho = "C:\\Users\\Milena\\Documents\\Modelos UML\\Diagramas de Caso de Uso\\Atores\\Export_06.xmi";
        LeitorDiagramaCasoDeUso leitor = new LeitorDiagramaCasoDeUso(caminho);
        try {
            DiagramaCasoDeUso diagrama = leitor.getDiagramaCasoDeUso();
            MetricaPastaUML   metrica  = new MetricaPastaUML(diagrama);
            System.out.println(metrica.getPastasDiagramaCasoDeUso());
        }catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            System.out.println("ERRO");
        }
    }
}