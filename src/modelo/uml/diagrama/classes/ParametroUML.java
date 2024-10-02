package modelo.uml.diagrama.classes;

import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>ParametroUML</b>.</p>
 * <p>Classe responsavel por representar um <b>Parametro UML</b> no Sistema.</p>
 * @author Leandro
 * @since  21/02/2017
 */
public class ParametroUML {
    private static final String TIPO_XMI = "uml:Parameter";
    private String id;
    private String nome;
    private String tipo;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento W3C.
     */
    public ParametroUML(Element element) {
        this.id   = element.getAttribute("xmi:id");
        this.nome = element.getAttribute("name");
        this.tipo = element.getAttribute("type");
    }

    /**
     * Metodo responsavel por retornar o Id do Parametro UML.
     * @return Id do Parametro UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Parametro UML.
     * @param id Id do Parametro UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Parametro UML.
     * @return Nome do Parametro UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Parametro UML.
     * @param nome Nome do Parametro UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar o Tipo do Parametro UML.
     * @return Tipo do Parametro UML.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo responsavel por definir o Tipo do Parametro UML.
     * @param tipo Tipo do Parametro UML.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Metodo responsavel por retornar os Dados do Parametro UML.
     * @return Dados do Parametro UML.
     */
    public String print() {
        return this.tipo + " " + this.nome;
    }
    
    @Override
    public String toString() {
        return this.print();
    }
}