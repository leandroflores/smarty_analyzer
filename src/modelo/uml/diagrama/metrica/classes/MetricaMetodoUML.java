package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.MetodoUML;

/**
 * <p>Classe de Metrica <b>MetricaMetodoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Metodos UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.MetodoUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaClasseUML
 * @see    modelo.uml.diagrama.metrica.classes.MetricaInterfaceUML
 */
public class MetricaMetodoUML {
    private final DiagramaClasse      diagramaClasse;
    private final MetricaClasseUML    metricaClasseUML;
    private final MetricaInterfaceUML metricaInterfaceUML;
    private final MetricaPacoteUML    metricaPacoteUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaMetodoUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse      = diagramaClasse;
        this.metricaClasseUML    = new MetricaClasseUML(this.diagramaClasse);
        this.metricaInterfaceUML = new MetricaInterfaceUML(this.diagramaClasse);
        this.metricaPacoteUML    = new MetricaPacoteUML(this.diagramaClasse);
    }
    
    /**
     * Metodo responsavel por retornar o Numero de Metodos UML filtrados pelos Parametros.
     * @param  parametros Lista de Parametros.
     * @return Numero de Metodos UML encontrados.
     */
    public Double getValorMetrica(Object[] parametros) {
        List<MetodoUML> metodosUML = this.filterMetodosUML(parametros);        
                        metodosUML = this.filterVisibilidade(metodosUML, parametros[3].toString());
                        metodosUML = this.filterAbstrato(metodosUML, (Boolean) parametros[4]);
                        metodosUML = this.filterConstrutor(metodosUML, (Boolean) parametros[5]);
                        metodosUML = this.filterDefinitivo(metodosUML, (Boolean) parametros[6]);
                        metodosUML = this.filterEstatico(metodosUML, (Boolean) parametros[7]);
        return Double.parseDouble(Integer.toString(metodosUML.size()));
    }
    
    public List<MetodoUML> filterMetodosUML(Object[] filtros) {
        Integer flag = (Integer) filtros[0];
        List<MetodoUML> metodos_1 = this.filterMetodosClassesUML(filtros);
        List<MetodoUML> metodos_2 = this.filterMetodosInterfacesUML(filtros);
        if (flag == 1)
            return metodos_1;
        else if (flag == 2)
            return metodos_2;
        
               metodos_1.addAll(metodos_2);
        return metodos_1;
    }
    
    public List<MetodoUML> filterMetodosClassesUML(Object[] filtros) {
        Object[] parametros    = new Object[5];
                 parametros[0] = filtros[1];
                 parametros[1] = filtros[2];
                 parametros[2] = null;
                 parametros[3] = null;
                 parametros[4] = null;
        List<ClasseUML> classesUML = this.metricaClasseUML.filter(parametros);
        List<MetodoUML> metodosUML = new ArrayList<>();
        for (int i = 0; i < classesUML.size(); i++) {
            metodosUML.addAll(classesUML.get(i).getMetodos());
        }
        return metodosUML;
    }
    
    public List<MetodoUML> filterMetodosInterfacesUML(Object[] filtros) {
        Object[] parametros    = new Object[3];
                 parametros[0] = filtros[1];
                 parametros[1] = filtros[2];
                 parametros[2] = null;
        List<InterfaceUML> interfacesUML = this.metricaInterfaceUML.filter(parametros);
        List<MetodoUML>    metodosUML    = new ArrayList<>();
        for (int i = 0; i < interfacesUML.size(); i++) {
            metodosUML.addAll(interfacesUML.get(i).getMetodos());
        }
        return metodosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Metodos UML do Diagrama de Classe.
     * @return Lista de Metodos UML do Diagrama de Classe.
     */
    public List<MetodoUML> getMetodosUML() {
        List<MetodoUML> metodosUML = this.getMetodosUMLClasse();
                        metodosUML.addAll(this.getMetodosUMLInterface());
        return metodosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Metodos UML das Classes do Diagrama de Classe.
     * @return Lista de Metodos UML das Classes do Diagrama de Classe.
     */
    public List<MetodoUML> getMetodosUMLClasse() {
        List<MetodoUML> metodosUML = new ArrayList<>();
        List<ClasseUML> classesUML = this.metricaClasseUML.getClassesDiagramaClasse();
        for (int i = 0; i < classesUML.size(); i++) 
            metodosUML.addAll(classesUML.get(i).getMetodos());
        return metodosUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Metodos UML das Interfaces do Diagrama de Classe.
     * @return Lista de Metodos UML das Interfaces do Diagrama de Classe.
     */
    public List<MetodoUML> getMetodosUMLInterface() {
        List<MetodoUML>    metodosUML    = new ArrayList<>();
        List<InterfaceUML> interfacesUML = this.metricaInterfaceUML.getInterfacesDiagramaClasse();
        for (int i = 0; i < interfacesUML.size(); i++)
            metodosUML.addAll(interfacesUML.get(i).getMetodos());
        return metodosUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela Visibilidade.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  visibilidade Visibilidade do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterVisibilidade(List<MetodoUML> metodosUML, String visibilidade) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (visibilidade.equals(""))
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if (metodosUML.get(i).getVisibilidade().toUpperCase().trim().equals(visibilidade.toUpperCase().trim()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pelo Retorno.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  retorno Tipo de Retorno do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterRetorno(List<MetodoUML> metodosUML, String retorno) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (retorno.equals(""))
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if (metodosUML.get(i).getRetorno().toUpperCase().trim().equals(retorno.toUpperCase().trim()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela flag Construtor.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  construtor Flag Construtor do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterConstrutor(List<MetodoUML> metodosUML, Boolean construtor) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (construtor == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((construtor) && (metodosUML.get(i).isConstrutor()))
                filtro.add(metodosUML.get(i));
            if ((!construtor) && (!metodosUML.get(i).isConstrutor()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela flag Estatico.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  estatico Flag Estatico do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterEstatico(List<MetodoUML> metodosUML, Boolean estatico) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (estatico == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((estatico) && (metodosUML.get(i).isEstatico()))
                filtro.add(metodosUML.get(i));
            if ((!estatico) && (!metodosUML.get(i).isEstatico()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela flag Definitivo.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  definitivo Flag Definitivo do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterDefinitivo(List<MetodoUML> metodosUML, Boolean definitivo) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (definitivo == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((definitivo) && (metodosUML.get(i).isDefinitivo()))
                filtro.add(metodosUML.get(i));
            if ((!definitivo) && (!metodosUML.get(i).isDefinitivo()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela flag Abstrato.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  abstrato Flag Abstrato do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterAbstrato(List<MetodoUML> metodosUML, Boolean abstrato) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (abstrato == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((abstrato) && (metodosUML.get(i).isAbstrato()))
                filtro.add(metodosUML.get(i));
            if ((!abstrato) && (!metodosUML.get(i).isAbstrato()))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela inicial set.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  set Flag Set do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterSet(List<MetodoUML> metodosUML, Boolean set) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (set == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((set) && (metodosUML.get(i).getNome().startsWith("set")))
                filtro.add(metodosUML.get(i));
            if ((!set) && (!metodosUML.get(i).getNome().startsWith("set")))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metodos UML filtrando pela inicial get e is.
     * @param  metodosUML Lista de Metodos UML inicial.
     * @param  get Flag Get do Metodo UML.
     * @return Lista de Metodos UML filtrados.
     */
    public List<MetodoUML> filterGet(List<MetodoUML> metodosUML, Boolean get) {
        List<MetodoUML> filtro = new ArrayList<>();
        if (get == null)
            return metodosUML;
        for (int i = 0; i < metodosUML.size(); i++) {
            if ((get)  && ((metodosUML.get(i).getNome().startsWith("get"))  || (metodosUML.get(i).getNome().startsWith("is"))))
                filtro.add(metodosUML.get(i));
            if ((!get) && ((!metodosUML.get(i).getNome().startsWith("get")) || (!metodosUML.get(i).getNome().startsWith("is"))))
                filtro.add(metodosUML.get(i));
        }
        return filtro;
    }
}