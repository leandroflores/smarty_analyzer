package modelo.controller.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.classes.AtributoUML;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Controle <b>ControllerAtributoUML</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da Classe de Modelo <b>Atributo UML</b>.</p>
 * @author Leandro
 * @since  07/03/2017
 * @see    modelo.uml.diagrama.classes.AtributoUML
 */
public class ControllerAtributoUML {
    
    /**
     * Metodo responsavel por retornar um novo Atributo UML.
     * @param  element Elemento do Documento.
     * @return Novo Atributo UML.
     */
    public AtributoUML create(Element element) {
        if (element != null)
            return new AtributoUML(element);
        return null;
    }
    
    /**
     * Metodo responsavel por criar uma Lista de Atributos pelo Node List.
     * Metodo ignora associacoes e atributos sem nome.
     * @param  list Lista de Elementos do Documento.
     * @return Lista de Atributos UML.
     */
    public List<AtributoUML> getAtributosUML(NodeList list) {
        List<AtributoUML> atributosUML = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Element atributoUML = (Element) list.item(i);
            if ((atributoUML.getAttribute("name").equals("") == false)
             && (atributoUML.getAttribute("association").equals(""))) {
                atributosUML.add(this.create(atributoUML));
            }
        }
        return atributosUML;
    }
    
    /**
     * Metodo responsavel por adicionar os Atributos UML em uma Classe UML.
     * @param classeUML Classe UML.
     * @param element Elemento W3C do Documento.
     */
    public void addAtributosUML(ClasseUML classeUML, Element element) {
        NodeList elements = element.getElementsByTagName("ownedAttribute");
        for (int i = 0; i < elements.getLength(); i++) 
            classeUML.addAtributo(this.create((Element) elements.item(i)));
    }
    
    /**
     * Metodo responsavel por adicionar os Atributos UML em uma Interface UML.
     * @param interfaceUML Interface UML.
     * @param element Elemento W3C do Documento.
     */
    public void addAtributosUML(InterfaceUML interfaceUML, Element element) {
        NodeList elements = element.getElementsByTagName("ownedAttribute");
        for (int i = 0; i < elements.getLength(); i++)
            interfaceUML.addAtributo(this.create((Element) elements.item(i)));
    }
}