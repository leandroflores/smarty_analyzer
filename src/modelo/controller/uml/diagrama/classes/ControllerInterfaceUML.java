package modelo.controller.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.PacoteUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Controle <b>ControllerInterfaceUML</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da Classe de Modelo <b>Interface UML</b>.</p>
 * @author Leandro
 * @since  15/03/2017
 * @see    modelo.controller.uml.diagrama.classes.ControllerAtributoUML
 * @see    modelo.controller.uml.diagrama.classes.ControllerMetodoUML
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 */
public class ControllerInterfaceUML {
    private static final ControllerAtributoUML CONTROLLER_ATRIBUTO_UML = new ControllerAtributoUML();
    private static final ControllerMetodoUML   CONTROLLER_METODO_UML   = new ControllerMetodoUML();
    
    /**
     * Metodo responsavel por retornar uma nova Interface UML.
     * @param  element Elemento W3C do Documento.
     * @param  pai Pacote UML pai.
     * @return Nova Interface UML.
     */
    public InterfaceUML create(Element element, PacoteUML pai) {
        if (element != null) {
            InterfaceUML interfaceUML = new InterfaceUML(element, pai);
                CONTROLLER_ATRIBUTO_UML.addAtributosUML(interfaceUML, element);
                CONTROLLER_METODO_UML.addMetodosUML(interfaceUML, element);
            return interfaceUML;
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Interfaces UML da Lista de Elementos W3C.
     * @param  pai Pacote UML pai.
     * @param  list Lista de Elementos W3C.
     * @return Lista de Interfaces UML.
     */
    public List<InterfaceUML> getInterfaces(PacoteUML pai, NodeList list) {
        List<InterfaceUML> interfaces = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            interfaces.add(this.create((Element) list.item(i), pai));
        }
        return interfaces;
    }
}