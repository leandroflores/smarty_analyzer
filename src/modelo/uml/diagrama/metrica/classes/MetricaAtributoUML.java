package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.AtributoUML;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;

/**
 * <p>Classe de Metrica <b>MetricaAtributoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Atributos UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.AtributoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaClasseUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaInterfaceUML
 */
public class MetricaAtributoUML {
    private final DiagramaClasse      diagramaClasse;
    private final MetricaClasseUML    metricaClasseUML;
    private final MetricaInterfaceUML metricaInterfaceUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaAtributoUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse      = diagramaClasse;
        this.metricaClasseUML    = new MetricaClasseUML(diagramaClasse);
        this.metricaInterfaceUML = new MetricaInterfaceUML(diagramaClasse);
    }
    
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.getMetrica(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar o Numero de Atributos UML filtrados pelos Parametros.
     * @param  parametros Lista de Parametros.
     * @return Numero de Atributos UML encontrados.
     */
    public List<AtributoUML> getMetrica(Object[] parametros) {
        List<AtributoUML> atributosUML = this.filterAtributosUML(parametros);
                          atributosUML = this.filterVisibilidade(atributosUML, parametros[3].toString());
                          atributosUML = this.filterConstante(atributosUML, (Boolean) parametros[4]);
                          atributosUML = this.filterEstatico(atributosUML, (Boolean) parametros[5]);
                          atributosUML = this.filterTipos(atributosUML, (List<String>) parametros[6]);
        return atributosUML;
    }
    
    public List<AtributoUML> filterAtributosUML(Object[] filtros) {
        Integer flag = (Integer) filtros[0];
        List<AtributoUML> atributos_1 = this.filterAtributosClassesUML(filtros);
        List<AtributoUML> atributos_2 = this.filterAtributosInterfacesUML(filtros);
        if (flag == 1)
            return atributos_1;
        else if (flag == 2)
            return atributos_2;
        
               atributos_1.addAll(atributos_2);
        return atributos_1;
    }
    
    public List<AtributoUML> filterAtributosClassesUML(Object[] filtros) {
        Object[] parametros    = new Object[5];
                 parametros[0] = filtros[1];
                 parametros[1] = filtros[2];
                 parametros[2] = null;
                 parametros[3] = null;
                 parametros[4] = null;
        List<ClasseUML>   classesUML   = this.metricaClasseUML.filter(parametros);
        List<AtributoUML> atributosUML = new ArrayList<>();
        for (int i = 0; i < classesUML.size(); i++) {
            atributosUML.addAll(classesUML.get(i).getAtributos());
        }
        return atributosUML;
    }
    
    public List<AtributoUML> filterAtributosInterfacesUML(Object[] filtros) {
        Object[] parametros    = new Object[3];
                 parametros[0] = filtros[1];
                 parametros[1] = filtros[2];
                 parametros[2] = null;
        List<InterfaceUML> interfacesUML = this.metricaInterfaceUML.filter(parametros);
        List<AtributoUML>  atributosUML  = new ArrayList<>();
        for (int i = 0; i < interfacesUML.size(); i++) {
            atributosUML.addAll(interfacesUML.get(i).getAtributos());
        }
        return atributosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista dos Atributos UML do Diagrama de Classe.
     * @return Lista dos Atributos UML do Diagrama de Classe.
     */
    public List<AtributoUML> getAtributosUML() {
        List<AtributoUML> atributosUML = this.getAtributosUMLClasse();
                          atributosUML.addAll(this.getAtributosUMLInterface());
        return atributosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atributos UML das Classes do Diagrama de Classe.
     * @return Lista de Atributos UML das Classes do Diagrama de Classe.
     */
    public List<AtributoUML> getAtributosUMLClasse() {
        List<AtributoUML> atributosUML = new ArrayList<>();
        List<ClasseUML>   classesUML   = this.metricaClasseUML.getClassesDiagramaClasse();
        for (int i = 0; i < classesUML.size(); i++)
            atributosUML.addAll(classesUML.get(i).getAtributos());
        return atributosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atributos UML das Interfaces do Diagrama de Classe.
     * @return Lista de Atributos UML das Interfaces do Diagrama de Classe.
     */
    public List<AtributoUML> getAtributosUMLInterface() {
        List<AtributoUML>  atributosUML  = new ArrayList<>();
        List<InterfaceUML> interfacesUML = this.metricaInterfaceUML.getInterfacesDiagramaClasse();
        for (int i = 0; i < interfacesUML.size(); i++)
            atributosUML.addAll(interfacesUML.get(i).getAtributos());
        return atributosUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Atributos UML filtrando pela Lista de Tipos.
     * @param  atributosUML Lista de Atributos UML inicial.
     * @param  tipos Listas de Tipos do Atributo UML.
     * @return Lista de Atributos UML filtrados.
     */
    public List<AtributoUML> filterTipos(List<AtributoUML> atributosUML, List<String> tipos) {
        List<AtributoUML> filter = new ArrayList<>();
        if (tipos.isEmpty()) 
            return atributosUML;
        for (int i = 0; i < atributosUML.size(); i++) {
            if (tipos.contains(atributosUML.get(i).getTipo()))
                filter.add(atributosUML.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Atributos UML filtrando pela Visibilidade.
     * @param  atributosUML Lista de Atributos UML inicial.
     * @param  visibilidade Visibilidade a ser filtrada.
     * @return Lista de Atributos UML filtrados.
     */
    public List<AtributoUML> filterVisibilidade(List<AtributoUML> atributosUML, String visibilidade) {
        List<AtributoUML> filtro = new ArrayList<>();
        if (visibilidade.trim().equals("")) 
            return atributosUML;
        for (int i = 0; i < atributosUML.size(); i++) {
            if (atributosUML.get(i).getVisibilidade().toUpperCase().trim().equals(visibilidade.toUpperCase().trim()))
                filtro.add(atributosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Atributos UML filtrando pela flag Estatico.
     * @param  atributosUML Lista de Atributos UML inicial.
     * @param  estatico Flag Estatico a ser filtrada.
     * @return Lista de Atributos UML filtrados.
     */
    public List<AtributoUML> filterEstatico(List<AtributoUML> atributosUML, Boolean estatico) {
        List<AtributoUML> filtro = new ArrayList<>();
        if (estatico == null) 
            return atributosUML;
        for (int i = 0; i < atributosUML.size(); i++) {
            AtributoUML atributoUML = atributosUML.get(i);
            if ((estatico)  && (atributoUML.isEstatico()))
                filtro.add(atributosUML.get(i));
            if ((!estatico) && (!atributoUML.isEstatico()))
                filtro.add(atributosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Atributos UML filtrando pela flag Constante.
     * @param  atributosUML Lista de Atributos UML inicial.
     * @param  constante Flag Constante a ser filtrada.
     * @return Lista de Atributos UML filtrados.
     */
    public List<AtributoUML> filterConstante(List<AtributoUML> atributosUML, Boolean constante) {
        List<AtributoUML> filtro = new ArrayList<>();
        if (constante == null) 
            return atributosUML;
        for (int i = 0; i < atributosUML.size(); i++) {
            AtributoUML atributoUML = atributosUML.get(i);
            if ((constante)  && (atributoUML.isEstatico()))
                filtro.add(atributosUML.get(i));
            if ((!constante) && (!atributoUML.isEstatico()))
                filtro.add(atributosUML.get(i));
        }
        return filtro;
    }
}