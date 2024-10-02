package modelo.controller.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.classes.MetodoUML;
import modelo.uml.diagrama.classes.ParametroUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Controle <b>ControllerParametroUML</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da Classe de Modelo <b>Parametro</b>.
 * @author Leandro
 * @since  14/03/2017
 * @see    modelo.uml.diagrama.classes.MetodoUML
 * @see    modelo.uml.diagrama.classes.ParametroUML
 */
public class ControllerParametroUML {
    public static final String PARAMETRO = "/ownedParameter[@type='uml:Parameter']";
    
    /**
     * Metodo responsavel por retornar um novo Parametro UML.
     * @param  element Elemento W3C do Documento.
     * @return Novo Parametro UML.
     */
    public ParametroUML create(Element element) {
        if (element != null)
            return new ParametroUML(element);
        return null;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo de Retorno de um Parametro UML.
     * @param  list NodeList com os Elementos.
     * @return Tipo de Retorno.
     */
    public String getRetorno(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Element parametro = (Element) list.item(i);
            if (parametro.getAttribute("direction").equals("return")) {
                return parametro.getAttribute("type");
            }
        }
        return "";
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Parametros UML de uma NodeList.
     * @param  list NodeList com os Elementos.
     * @return Lista de Parametros UML.
     */
    public List<ParametroUML> getParametros(NodeList list) {
        List<ParametroUML> parametros = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Element parametro = (Element) list.item(i);
            if (parametro.getAttribute("direction").equals("")) {
                parametros.add(this.create(parametro));
            }
        }
        return parametros;
    }
    
    /**
     * Metodo responsavel por adicionar os Parametros UML de um Metodo UML.
     * @param metodo Metodo UML.
     * @param element Elemento W3C do Documento.
     */
    public void addParametros(MetodoUML metodo, Element element) {
        NodeList elements = element.getElementsByTagName("ownedParameter");
        for (int i = 0; i < elements.getLength(); i++) {
            Element current = (Element) elements.item(i);
            if (current.getAttribute("direction").equals(""))
                metodo.addParametro(this.create(current));
        }
    }
}