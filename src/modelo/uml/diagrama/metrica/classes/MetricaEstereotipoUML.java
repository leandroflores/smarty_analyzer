package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;

/**
 * <p>Classe de Metrica <b>MetricaEstereotipoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Estereotipos UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.ClasseUML
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 */
public class MetricaEstereotipoUML {
    private final DiagramaClasse      diagramaClasse;
    private final MetricaClasseUML    metricaClasseUML;
    private final MetricaInterfaceUML metricaInterfaceUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaEstereotipoUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse      = diagramaClasse;
        this.metricaClasseUML    = new MetricaClasseUML(this.diagramaClasse);
        this.metricaInterfaceUML = new MetricaInterfaceUML(this.diagramaClasse);
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(0));
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(0).getEstereotipos());
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(1));
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(1).getEstereotipos());
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(2));
//        System.out.println(this.diagramaClasse.getRaiz().getClasses().get(2).getEstereotipos());
    }
    
    /**
     * 
     * @param parametros
     * @return 
     */
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.filter(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Objetos filtrados pelos parametros.
     * @param  parametros Lista de Parametros.
     * @return Lista de Objetos filtrados.
     */
    public List<String> filter(Object[] parametros) {
        List<String> objetos = new ArrayList<>();
        Integer      flag    = (Integer) parametros[0];
        if (flag != 2) {
            List<ClasseUML> classesUML = this.filterClassesUML(parametros);
            for (int i = 0; i < classesUML.size(); i++) {
                String chave = classesUML.get(i).getPacote().pacote() + "." + classesUML.get(i).getNome();
                if (objetos.contains(chave) == false)
                    objetos.add(chave);
            }
        }
        
        if (flag != 1) {
            List<InterfaceUML> interfacesUML = this.filterInterfacesUML(parametros);
            for (int i = 0; i < interfacesUML.size(); i++) {
                String chave = interfacesUML.get(i).getPacote().pacote() + "." + interfacesUML.get(i).getNome();
                if (objetos.contains(chave) == false)
                    objetos.add(chave);
            }
        }
        System.out.println(objetos);
        return objetos;
    }
    
    public List<ClasseUML> filterClassesUML(Object[] parametros) {
        Object[] filtroClasses    = new Object[5];
                 filtroClasses[0] = parametros[1];
                 filtroClasses[1] = parametros[2];
                 filtroClasses[2] = parametros[3];
                 filtroClasses[3] = null;
                 filtroClasses[4] = null;
        return this.metricaClasseUML.filter(filtroClasses);
    }
    
    public List<InterfaceUML> filterInterfacesUML(Object[] parametros) {
        Object[] filtroInterfaces    = new Object[3];
                 filtroInterfaces[0] = parametros[1];
                 filtroInterfaces[1] = parametros[2];
                 filtroInterfaces[2] = parametros[3];
        return this.metricaInterfaceUML.filter(filtroInterfaces);
    }
}