package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;

/**
 * <p>Classe de Metrica <b>MetricaGeneralizacaoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre as <b>Generalizacoes UML</b>.</p>
 * @author Leandro
 * @since  06/04/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 */
public class MetricaGeneralizacaoUML {
    private final DiagramaClasse      diagramaClasse;
    private final MetricaClasseUML    metricaClasseUML;
    private final MetricaInterfaceUML metricaInterfaceUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaGeneralizacaoUML(DiagramaClasse diagramaClasse) {
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
        List<String> generalizacoesUML = this.filterGeneralizacaoUML(parametros);
        return Double.parseDouble(Integer.toString(generalizacoesUML.size()));
    }
    
    private List<String> filterGeneralizacaoUML(Object[] filtros) {
        Integer      flag          = (Integer) filtros[0];
        List<String> classesUML    = this.filterNomeClasses((List<String>) filtros[1]);
        List<String> interfacesUML = this.filterNomeInterfaces((List<String>) filtros[1]);
        
        if (flag == 1)
            return classesUML;
        if (flag == 2)
            return interfacesUML;
        
               classesUML.addAll(interfacesUML);
        return classesUML;
    }
    
    private List<String> filterNomeClasses(List<String> nomes) {
        List<String>    toReturn   = new ArrayList<>();
        List<ClasseUML> filtro     = this.filterSubClasses(this.metricaClasseUML.getClassesDiagramaClasse());
        List<ClasseUML> classesUML = this.filterClasses(filtro, nomes);
        for (int i = 0; i < classesUML.size(); i++) {
            toReturn.add(classesUML.get(i).getNome());
        }
        return toReturn;
    }
    
    private List<String> filterNomeInterfaces(List<String> nomes) {
        List<String>       toReturn      = new ArrayList<>();
        List<InterfaceUML> filtro        = this.filterSubInterfaces(this.metricaInterfaceUML.getInterfacesDiagramaClasse());
        List<InterfaceUML> interfacesUML = this.filterInterfaces(filtro, nomes);
        for (int i = 0; i < interfacesUML.size(); i++) {
            toReturn.add(interfacesUML.get(i).getNome());
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
            if ((nomes.contains(classesUML.get(i).getSuperClasse().getNome()))
             && (classesUML.get(i).getSuperClasse() != null))
                filtro.add(classesUML.get(i));
        }
        return filtro;
    }
    
    private List<ClasseUML> filterSubClasses(List<ClasseUML> classesUML) {
        List<ClasseUML> toReturn = new ArrayList<>();
        for (int i = 0; i < classesUML.size(); i++) {
            if ((classesUML.get(i).getSuperClasse() != null)
             && (toReturn.contains(classesUML.get(i)) == false)) {
                    toReturn.add(classesUML.get(i));
            }
        }
        return toReturn;
    }
    
    private List<InterfaceUML> filterSubInterfaces(List<InterfaceUML> interfacesUML) {
        List<InterfaceUML> toReturn = new ArrayList<>();
        for (int i = 0; i < interfacesUML.size(); i++) {
            if ((interfacesUML.get(i).getSuperInterface() != null)
             && (toReturn.contains(interfacesUML.get(i)) == false)) {
                    toReturn.add(interfacesUML.get(i));
            }
        }
        return toReturn;
    }
    
    private List<ClasseUML> filterSuperClasses(List<ClasseUML> classesUML) {
        List<ClasseUML> toReturn = new ArrayList<>();
        for (int i = 0; i < classesUML.size(); i++) {
            ClasseUML superClasseUML = classesUML.get(i).getSuperClasse();
            if ((superClasseUML != null)
             && (toReturn.contains(superClasseUML) == false)) {
                toReturn.add(classesUML.get(i));
            }
        }
        return toReturn;
    }
    
    /**
     * 
     * @param interfacesUML
     * @param nomes
     * @return 
     */
    private List<InterfaceUML> filterInterfaces(List<InterfaceUML> interfacesUML, List<String> nomes) {
        List<InterfaceUML> filtro = new ArrayList<>();
        if (nomes == null)
            return interfacesUML;
        if (nomes.isEmpty())
            return interfacesUML;
        if (nomes.get(0).trim().equals("*"))
            return interfacesUML;
        for (int i = 0; i < interfacesUML.size(); i++) {
            if ((nomes.contains(interfacesUML.get(i).getSuperInterface().getNome()))
             && (interfacesUML.get(i).getSuperInterface() != null))
                filtro.add(interfacesUML.get(i));
        }
        return filtro;
    }
}