package modelo.uml.diagrama.metrica;

import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.classes.MetricaAtributoUML;
import modelo.uml.diagrama.metrica.classes.MetricaClasseUML;
import modelo.uml.diagrama.metrica.classes.MetricaEstereotipoUML;
import modelo.uml.diagrama.metrica.classes.MetricaGeneralizacaoUML;
import modelo.uml.diagrama.metrica.classes.MetricaInterfaceUML;
import modelo.uml.diagrama.metrica.classes.MetricaMetodoUML;
import modelo.uml.diagrama.metrica.classes.MetricaPacoteUML;
import modelo.uml.diagrama.metrica.classes.MetricaParametroUML;
import modelo.uml.diagrama.metrica.classes.MetricaRealizacaoUML;

/**
 * <p>Classe de Metrica <b>MetricaDiagramaClasse</b>.</p>
 * <p>Classe responsavel por extrair as Metricas do <b>Diagrama de Classe</b>.</p>
 * @author Leandro
 * @since  23/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.metrica.classes.MetricaAtributoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaClasseUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaEstereotipoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaInterfaceUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaGeneralizacaoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaMetodoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaPacoteUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaParametroUML
 */
public class MetricaDiagramaClasse {
    private final MetricaAtributoUML      metricaAtributoUML;
    private final MetricaClasseUML        metricaClasseUML;
    private final MetricaEstereotipoUML   metricaEstereotipoUML;
    private final MetricaGeneralizacaoUML metricaGeneralizacaoUML;
    private final MetricaInterfaceUML     metricaInterfaceUML;
    private final MetricaMetodoUML        metricaMetodoUML;
    private final MetricaPacoteUML        metricaPacoteUML;
    private final MetricaParametroUML     metricaParametroUML;
    private final MetricaRealizacaoUML    metricaRealizacaoUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaDiagramaClasse(DiagramaClasse diagramaClasse) {
        this.metricaAtributoUML      = new MetricaAtributoUML(diagramaClasse);
        this.metricaClasseUML        = new MetricaClasseUML(diagramaClasse);
        this.metricaEstereotipoUML   = new MetricaEstereotipoUML(diagramaClasse);
        this.metricaGeneralizacaoUML = new MetricaGeneralizacaoUML(diagramaClasse);
        this.metricaInterfaceUML     = new MetricaInterfaceUML(diagramaClasse);
        this.metricaMetodoUML        = new MetricaMetodoUML(diagramaClasse);
        this.metricaPacoteUML        = new MetricaPacoteUML(diagramaClasse);
        this.metricaParametroUML     = new MetricaParametroUML(diagramaClasse);
        this.metricaRealizacaoUML    = new MetricaRealizacaoUML(diagramaClasse);
    }

    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Atributos UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Atributos UML.
     */
    public Double getMetricaAtributoUML(Object[] parametros) {
        return this.metricaAtributoUML.getValorMetrica(parametros);
    }

    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre as Classes UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre as Classes UML.
     */
    public Double getMetricaClasseUML(Object[] parametros) {
        return this.metricaClasseUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Estereotipos UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Estereotipos UML.
     */
    public Double getMetricaEstereotipoUML(Object[] parametros) {
        return this.metricaEstereotipoUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre as Generalizacoes UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre as Generalizacoes UML.
     */
    public Double getMetricaGeneralizacaoUML(Object[] parametros) {
        return this.metricaGeneralizacaoUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre as Interfaces UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre as Interfaces UML.
     */
    public Double getMetricaInterfaceUML(Object[] parametros) {
        return this.metricaInterfaceUML.getValorMetrica(parametros);
    }

    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Metodos UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Metodos UML.
     */
    public Double getMetricaMetodoUML(Object[] parametros) {
        return this.metricaMetodoUML.getValorMetrica(parametros);
    }

    /**
     * Metodo responsavel por retornar a Metrica sobre os Pacotes UML.
     * @return Metrica sobre os Pacotes UML.
     */
    public MetricaPacoteUML getMetricaPacoteUML() {
        return this.metricaPacoteUML;
    }

    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre os Parametros UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre os Pararametros UML.
     */
    public Double getMetricaParametroUML(Object[] parametros) {
        return this.metricaParametroUML.getMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica sobre as Realizacoes UML.
     * @param  parametros Lista de Parametros para o filtro.
     * @return Valor da Metrica sobre as Realizacoes UML.
     */
    public Double getMetricaRealizacaoUML(Object[] parametros) {
        return this.metricaRealizacaoUML.getValorMetrica(parametros);
    }
    
    /**
     * Metodo responsavel por retornar um Vetor com os Dados do Diagrama de Classe.
     * @return Vetor com os Dados do Diagrama de Classe.
     */
    public String[] getDados() {
        String[] dados    = new String[5];
                 dados[0] = Integer.toString(this.metricaPacoteUML.getPacotesDiagramaClasse().size());
                 dados[1] = Integer.toString(this.metricaClasseUML.getClassesDiagramaClasse().size());
                 dados[2] = Integer.toString(this.metricaInterfaceUML.getInterfacesDiagramaClasse().size());
                 dados[3] = Integer.toString(this.metricaMetodoUML.getMetodosUML().size());
                 dados[4] = Integer.toString(this.metricaAtributoUML.getAtributosUML().size());
        return   dados;
    }
}