package modelo.uml.diagrama.metrica;

import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.usecases.MetricaAtorUML;
import modelo.uml.diagrama.metrica.usecases.MetricaCasoDeUsoUML;
import modelo.uml.diagrama.metrica.usecases.MetricaPastaUML;

/**
 * <p>Classe de Metrica <b>MetricaDiagramaCasoDeUso</b>.</p>
 * <p>Classe responsavel por extrair as Metricas do <b>Diagrama de Caso de Uso</b>.</p>
 * @author Leandro
 * @since  12/05/2017
 * @see    modelo.uml.diagrama.DiagramaCasoDeUso
 * @see    modelo.uml.diagrama.metrica.usecases.MetricaAtorUML
 * @see    modelo.uml.diagrama.metrica.usecases.MetricaCasoDeUsoUML
 * @see    modelo.uml.diagrama.metrica.usecases.MetricaPastaUML
 */
public class MetricaDiagramaCasoDeUso {
    private final MetricaAtorUML      metricaAtorUML;
    private final MetricaCasoDeUsoUML metricaCasoDeUsoUML;
    private final MetricaPastaUML     metricaPastaUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso UML.
     */
    public MetricaDiagramaCasoDeUso(DiagramaCasoDeUso diagramaCasoDeUso) {
        this.metricaAtorUML      = new MetricaAtorUML(diagramaCasoDeUso);
        this.metricaCasoDeUsoUML = new MetricaCasoDeUsoUML(diagramaCasoDeUso);
        this.metricaPastaUML     = new MetricaPastaUML(diagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Atores UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Atores UML.
     */
    public Double getMetricaAtorUML(Object[] parametros) {
        return this.metricaAtorUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Casos de Uso UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Casos de Uso UML.
     */
    public Double getMetricaCasoDeUsoUML(Object[] parametros) {
        return this.metricaCasoDeUsoUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar a Metrica das Pastas UML.
     * @return Metrica das Pastas UML.
     */
    public MetricaPastaUML getMetricaPastaUML() {
        return this.metricaPastaUML;
    }
    
    /**
     * Metodo responsavel por retornar um Vetor com os Dados do Diagrama de Caso de Uso.
     * @return Vetor com os Dados do Diagrama de Caso de Uso.
     */
    public String[] getDados() {
        String[] dados    = new String[3];
                 dados[0] = Integer.toString(this.metricaPastaUML.getPastasDiagramaCasoDeUso().size());
                 dados[1] = Integer.toString(this.metricaAtorUML.getAtoresDiagramaCasoDeUso().size());
                 dados[2] = Integer.toString(this.metricaCasoDeUsoUML.getCasosDeUsoDiagramaCasoDeUso().size());
        return   dados;
    }
}