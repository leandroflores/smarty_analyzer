package modelo.controller.uml.diagrama.classes;

import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.MetodoUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Controle <b>ControllerMetodoUML</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da Classe de Modelo <b>Metodo UML</b>.</p>
 * @author Leandro
 * @since  14/03/2017
 * @see    modelo.controller.uml.diagrama.classes.ControllerParametroUML
 * @see    modelo.uml.diagrama.classes.MetodoUML
 */
public class ControllerMetodoUML {
    private static final ControllerParametroUML CONTROLLER_PARAMETRO_UML = new ControllerParametroUML();
    
    /**
     * Metodo responsavel por retornar um novo Metodo UML de uma Classe UML.
     * @param  classeUML Classe UML.
     * @param  element Elemento W3C do Documento.
     * @return Novo Metodo UML.
     */
    public MetodoUML create(ClasseUML classeUML, Element element) {
        if (element != null)
            return new MetodoUML(classeUML, element);
        return null;
    }
    
    /**
     * Metodo responsavel por retornar um novo Metodo UML de uma Interface UML.
     * @param  interfaceUML Interface UML.
     * @param  element Elemento W3C do Documento.
     * @return Novo Metodo UML.
     */
    public MetodoUML create(InterfaceUML interfaceUML, Element element) {
        if (element != null)
            return new MetodoUML(interfaceUML, element);
        return null;
    }
    
    /**
     * Metodo responsavel por adicionar os Metodos UML a uma Classe UML.
     * @param classeUML Classe UML.
     * @param element Elemento W3C do Documento.
     */
    public void addMetodosUML(ClasseUML classeUML, Element element) {
        NodeList elementos = element.getElementsByTagName("ownedOperation");
        for (int i = 0; i < elementos.getLength(); i++) {
            MetodoUML metodoUML = this.create(classeUML, element);
                CONTROLLER_PARAMETRO_UML.addParametros(metodoUML, element);
            classeUML.addMetodo(metodoUML);
        }
    }
    
    /**
     * Metodo responsavel por adicionar as Interfaces UML a uma Classe UML.
     * @param interfaceUML Interface UML.
     * @param element Elemento W3C do Documento.
     */
    public void addMetodosUML(InterfaceUML interfaceUML, Element element) {
        NodeList elementos = element.getElementsByTagName("ownedOperation");
        for (int i = 0; i < elementos.getLength(); i++) {
            MetodoUML metodoUML = this.create(interfaceUML, element);
                CONTROLLER_PARAMETRO_UML.addParametros(metodoUML, element);
            interfaceUML.addMetodo(metodoUML);
        }
    }
}