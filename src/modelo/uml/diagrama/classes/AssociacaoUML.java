package modelo.uml.diagrama.classes;

import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>AssociacaoUML</b>.</p>
 * <p>Classe responsavel por representar uma <b>Associacao UML</b> no Sistema.</p>
 * @author Leandro
 * @since  15/03/2017
 * @see    org.w3c.dom.Element
 */
public class AssociacaoUML {
    public static final String TIPO_XMI = "uml:Association";
    private String id;
    private String associacao;
    private String nome;
    private String tipo;
    private String visibilidade;
    private int    minimo;
    private int    maximo;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento W3C do Documento.
     */
    public AssociacaoUML(Element element) {
        this.id           = element.getAttribute("xmi:id");
        this.associacao   = element.getAttribute("association");
        this.nome         = element.getAttribute("name");
        this.tipo         = element.getAttribute("aggregation");
        this.visibilidade = element.getAttribute("visibility");
        this.minimo       = 1;
        this.maximo       = 1;
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param element Elemento W3C do Documento.
     * @param minimo Valor Minimo da Associacao UML.
     * @param maximo Valor Maximo da Associacao UML.
     */
    public AssociacaoUML(Element element, int minimo, int maximo) {
        this.id           = element.getAttribute("xmi:id");
        this.associacao   = element.getAttribute("association");
        this.nome         = element.getAttribute("name");
        this.tipo         = element.getAttribute("type");
        this.visibilidade = element.getAttribute("visibility");
        this.minimo       = minimo;
        this.maximo       = maximo;
    }

    /**
     * Metodo responsavel por retornar o Id da Associacao UML.
     * @return Id da Associacao UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Associacao UML.
     * @param id Id da Associacao UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Codigo da Associacao UML.
     * @return Codigo da Associacao UML.
     */
    public String getAssociacao() {
        return this.associacao;
    }

    /**
     * Metodo responsavel por definir o Codigo da Associacao UML.
     * @param associacao Codigo da Associacao UML.
     */
    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    /**
     * Metodo responsavel por retornar o Nome da Associacao UML.
     * @return Nome da Associacao UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome da Associacao UML.
     * @param nome Nome da Associacao UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar o Tipo da Associacao UML.
     * @return Tipo da Associacao UML.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo responsavel por definir o Tipo da Associacao UML.
     * @param tipo Tipo da Associacao UML.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Metodo responsavel por retornar a Visibilidade da Associacao UML.
     * @return Visibilidade da Associacao UML.
     */
    public String getVisibilidade() {
        return this.visibilidade;
    }

    /**
     * Metodo responsavel por definir a Visibilidade da Associacao UML.
     * @param visibilidade Visibilidade da Associacao UML.
     */
    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }

    /**
     * Metodo responsavel por retornar o Valor Minimo da Associacao UML.
     * @return Valor Minimo da Associacao UML.
     */
    public int getMinimo() {
        return this.minimo;
    }

    /**
     * Metodo responsavel por definir o Valor Minimo da Associacao UML.
     * @param minimo Valor Minimo da Associacao UML.
     */
    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    /**
     * Metodo responsavel por retornar o Valor Maximo da Associacao UML.
     * @return Valor Maximo da Associacao UML.
     */
    public int getMaximo() {
        return this.maximo;
    }

    /**
     * Metodo responsavel por definir o Valor Maximo da Associacao UML.
     * @param maximo Valor Maximo da Associacao UML.
     */
    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }
    
    /**
     * Metodo responsavel por retornar os Dados da Associacao UML.
     * @return Dados da Associacao UML.
     */
    public String print() {
        String toReturn  = "    ";
               toReturn += this.visibilidade + " ";
               toReturn += this.getCardinalidade() + " ";
               toReturn += this.getNomeVariavel()  + ";";
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar o Nome da Variavel da Associacao UML.
     * @return Nome da Variavel da Associacao UML.
     */
    private String getNomeVariavel() {
        if (this.nome.trim().equals(""))
            return this.tipo.substring(0, 1).toLowerCase() + this.tipo.substring(1);
        return this.nome;
    }
    
    /**
     * Metodo responsavel por retornar a Cardinalidade da Associacao UML.
     * @return Cardinalidade da Associacao UML.
     */
    private String getCardinalidade() {
        if (this.maximo == 1)
            return this.tipo;
        return "List<" + this.tipo + ">";
    }
    
    @Override
    public String toString() {
        return this.print();
    }
}