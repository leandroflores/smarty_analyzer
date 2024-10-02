package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.MetodoUML;
import modelo.uml.diagrama.classes.ParametroUML;

/**
 * <p>Classe de Metrica <b>MetricaParametroUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Parametros UML</b>.</p>
 * @author Leandro
 * @since  23/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.ParametroUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaMetodoUML
 */
public class MetricaParametroUML {
    private final DiagramaClasse   diagramaClasse;
    private final MetricaMetodoUML metricaMetodoUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaParametroUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse   = diagramaClasse;
        this.metricaMetodoUML = new MetricaMetodoUML(diagramaClasse);
    }
    
    /**
     * Metodo responsavel por retornar o Numero de Parametros UML filtrados pelos Parametros.
     * @param  parametros Lista de Parametros.
     * @return Numero de Parametros UML encontrados.
     */
    public Double getMetrica(Object[] parametros) {
        List<ParametroUML> parametrosUML = this.getParametrosUML();
                           parametrosUML = this.filterTipo(parametrosUML, (List<String>) parametros[0]);
        return Double.parseDouble(Integer.toString(parametrosUML.size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros UML do Diagrama de Classe.
     * @return Lista de Parametros UML do Diagrama de Classe.
     */
    public List<ParametroUML> getParametrosUML() {
        List<ParametroUML> parametrosUML = this.getParametrosUMLClasse();
                           parametrosUML.addAll(this.getParametrosUMLInterface());
        return parametrosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros UML das Classes do Diagrama de Classe.
     * @return Lista de Parametros UML das Classes do Diagrama de Classe.
     */
    public List<ParametroUML> getParametrosUMLClasse() {
        List<ParametroUML> parametrosUML = new ArrayList<>();
        List<MetodoUML>    metodosUML    = this.metricaMetodoUML.getMetodosUMLClasse();
        for (int i = 0; i < metodosUML.size(); i++)
            parametrosUML.addAll(metodosUML.get(i).getParametros());
        return parametrosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros UML das Interfaces do Diagrama de Classe.
     * @return Lista de Parametros UML das Interfaces do Diagrama de Classe.
     */
    public List<ParametroUML> getParametrosUMLInterface() {
        List<ParametroUML> parametrosUML = new ArrayList<>();
        List<MetodoUML>    metodosUML    = this.metricaMetodoUML.getMetodosUMLInterface();
        for (int i = 0; i < metodosUML.size(); i++)
            parametrosUML.addAll(metodosUML.get(i).getParametros());
        return parametrosUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela Lista de Tipos.
     * @param  parametrosUML Lista de Parametros UML inicial.
     * @param  tipos Lista de Tipos para busca.
     * @return Lista de Parametros UML filtrados.
     */
    public List<ParametroUML> filterTipo(List<ParametroUML> parametrosUML, List<String> tipos) {
        List<ParametroUML> filtro = new ArrayList<>();
        if (tipos == null)
            return parametrosUML;
        if (tipos.isEmpty())
            return parametrosUML;
        if (tipos.get(0).equals("*"))
            return parametrosUML;
        for (int i = 0; i < parametrosUML.size(); i++) {
            if (tipos.contains(parametrosUML.get(i).getTipo()))
                filtro.add(parametrosUML.get(i));
        }
        return filtro;
    }
}