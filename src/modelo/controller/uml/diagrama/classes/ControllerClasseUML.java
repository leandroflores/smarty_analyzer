package modelo.controller.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.PacoteUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Controle <b>ControllerClasseUML</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da Classe de Modelo <b>Classe UML</b>.</p>
 * @author Leandro
 * @since  15/03/2017
 * @see    modelo.controller.uml.diagrama.classes.ControllerAtributoUML
 * @see    modelo.controller.uml.diagrama.classes.ControllerMetodoUML
 * @see    modelo.uml.diagrama.classes.ClasseUML
 */
public class ControllerClasseUML {
    private static final ControllerAtributoUML CONTROLLER_ATRIBUTO_UML = new ControllerAtributoUML();
    private static final ControllerMetodoUML   CONTROLLER_METODO_UML   = new ControllerMetodoUML();
    
    /**
     * Metodo responsavel por retornar uma nova Classe UML.
     * @param  element Elemento W3C do Documento.
     * @param  pai Pacote UML pai.
     * @return Nova Classe UML.
     */
    public ClasseUML create(Element element, PacoteUML pai) {
        if (element != null) {
            ClasseUML classeUML = new ClasseUML(element, pai);
                CONTROLLER_ATRIBUTO_UML.addAtributosUML(classeUML, element);
                CONTROLLER_METODO_UML.addMetodosUML(classeUML, element);
            return classeUML;
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Classes UML da Lista de Elementos W3C.
     * @param  pai Pacote UML pai.
     * @param  list Lista de Elementos W3C.
     * @return Lista de Classes UML.
     */
    public List<ClasseUML> getClasses(PacoteUML pai, NodeList list) {
        List<ClasseUML> classes = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            classes.add(this.create((Element) list.item(i), pai));
        }
        return classes;
    }
}