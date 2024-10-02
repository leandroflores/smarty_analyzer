package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;

/**
 * <p>Classe de Metrica <b>MetricaRealizacaoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre as <b>Realizacoes UML</b>.</p>
 * @author Leandro
 * @since  06/04/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 */
public class MetricaRealizacaoUML {
    private final DiagramaClasse      diagramaClasse;
    private final MetricaClasseUML    metricaClasseUML;
    private final MetricaInterfaceUML metricaInterfaceUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaRealizacaoUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse      = diagramaClasse;
        this.metricaClasseUML    = new MetricaClasseUML(this.diagramaClasse);
        this.metricaInterfaceUML = new MetricaInterfaceUML(this.diagramaClasse);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Realizacoes UML filtradas pelos parametros.
     * @param  parametros Lista de Parametros.
     * @return Lista de Realizacoes UML filtradas.
     */
    public Double getValorMetrica(Object[] parametros) {
        List<String> realizacoesUML = this.filterRealizacaoUML(parametros);
        return Double.parseDouble(Integer.toString(realizacoesUML.size()));
    }
    
    private List<String> filterRealizacaoUML(Object[] filtros) {
        List<String> realizacoesUML = this.filterNomeClasses((List<String>) filtros[0]);
        return realizacoesUML;
    }
    
    private List<String> filterNomeClasses(List<String> nomes) {
        List<String>    toReturn   = new ArrayList<>();
        List<ClasseUML> filtro     = this.filterClassesRealizacoes(this.metricaClasseUML.getClassesDiagramaClasse());
        List<ClasseUML> classesUML = this.filterClasses(filtro, nomes);
        for (int i = 0; i < classesUML.size(); i++) {
            toReturn.add(classesUML.get(i).getNome());
        }
        return toReturn;
    }
    
    /**
     * 
     * @param classesUML
     * @param nomes
     * @return 
     */
    private List<ClasseUML> filterClasses(List<ClasseUML> classesUML, List<String> nomes) {
        List<ClasseUML> filtro = new ArrayList<>();
        if (nomes == null)
            return classesUML;
        if (nomes.isEmpty())
            return classesUML;
        if (nomes.get(0).trim().equals("*"))
            return classesUML;
        for (int i = 0; i < classesUML.size(); i++) {
            List<InterfaceUML> interfacesUML = classesUML.get(i).getRealizacoes();
            for (int x = 0; x < interfacesUML.size(); x++) {
                String nomeInterface = interfacesUML.get(x).getNome();
                if ((nomes.contains(nomeInterface))
                 && (filtro.contains(classesUML.get(i)) == false)) {
                    filtro.add(classesUML.get(i));
                }
            }
        }
        return filtro;
    }
    
    private List<ClasseUML> filterClassesRealizacoes(List<ClasseUML> classesUML) {
        List<ClasseUML> toReturn = new ArrayList<>();
        for (int i = 0; i < classesUML.size(); i++) {
            if ((classesUML.get(i).getRealizacoes().isEmpty())
             && (toReturn.contains(classesUML.get(i)) == false)) {
                    toReturn.add(classesUML.get(i));
            }
        }
        return toReturn;
    }
}