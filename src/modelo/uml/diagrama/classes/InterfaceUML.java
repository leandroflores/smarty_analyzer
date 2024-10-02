package modelo.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>InterfaceUML</b>.</p>
 * <p>Classe responsavel por representar uma <b>Interface UML</b> no Sistema.</p>
 * @author Leandro
 * @since  07/03/2017
 * @see    model.uml.Atributo
 * @see    model.uml.Metodo
 */
public class InterfaceUML {
    private static final String TIPO_XMI = "uml:Interface";
    private PacoteUML    pacote;
    private InterfaceUML superInterface;
    private String       idSuper;
    private String       id;
    private String       nome;
    private List<AtributoUML>   atributos;
    private List<MetodoUML>     metodos;
    private List<AssociacaoUML> associacoes;
    private List<String>        estereotipos;

    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento do Documento.
     * @param pacote Pacote UML.
     */
    public InterfaceUML(Element element, PacoteUML pacote) {
        this.pacote         = pacote;
        this.superInterface = null;
        this.idSuper        = "";
        this.id             = element.getAttribute("xmi:id");
        this.nome           = element.getAttribute("name");
        this.atributos      = new ArrayList<>();
        this.metodos        = new ArrayList<>();
        this.associacoes    = new ArrayList<>();
        this.estereotipos   = new ArrayList<>();
    }
    
    /**
     * Metodo responsavel por retornar o Pacote da Interface UML.
     * @return Pacote da Interface UML.
     */
    public PacoteUML getPacote() {
        return this.pacote;
    }

    /**
     * Metodo responsavel por definir o Pacote da Interface UML.
     * @param pacote Pacote da Interface UML.
     */
    public void setPacote(PacoteUML pacote) {
        this.pacote = pacote;
    }

    /**
     * Metodo responsavel por retornar a SuperInterface da Interface UML.
     * @return SuperInterface da Interface UML.
     */
    public InterfaceUML getSuperInterface() {
        return this.superInterface;
    }

    /**
     * Metodo responsavel por definir a SuperInterface da Interface UML.
     * @param superInterface Interface UML.
     */
    public void setSuperInterface(InterfaceUML superInterface) {
        this.superInterface = superInterface;
    }
    /**
     * Metodo responsavel por retornar o Id da Super da Interface UML.
     * @return Id da Super da Interface UML.
     */
    public String getIdSuper() {
        return this.idSuper;
    }

    /**
     * Metodo responsavel por definir o Id da Super da Interface UML.
     * @param idSuper Id da Super da Interface UML.
     */
    public void setIdSuper(String idSuper) {
        this.idSuper = idSuper;
    }
    
    /**
     * Metodo responsavel por retornar o Id da Interface UML.
     * @return Id da Interface UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Interface UML.
     * @param id Id da Interface UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome da Interface UML.
     * @return Nome da Interface UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome da Interface UML.
     * @param nome Nome da Interface UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Metodo responsavel por adicionar um Atributo a Interface UML.
     * @param atributo Atributo UML a ser adicionado.
     */
    public void addAtributo(AtributoUML atributo) {
        if (atributo != null)
            this.atributos.add(atributo);
    }
    
    /**
     * Metodo responsavel por Adicionar um Metodo a Interface UML.
     * @param metodo Metodo UML a ser adicionado.
     */
    public void addMetodo(MetodoUML metodo) {
        if (metodo != null)
            this.metodos.add(metodo);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atributos da Interface UML.
     * @return Lista de Atributos da Interface UML.
     */
    public List<AtributoUML> getAtributos() {
        return this.atributos;
    }
    
    /**
     * Metodo responsavel por definir a Lista de Atributos da Interface UML.
     * @param atributos Lista de Atributos da Interface UML.
     */
    public void setAtributos(List<AtributoUML> atributos) {
        this.atributos = atributos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Metodos da Interface UML.
     * @return Lista de Metodos da Interface UML.
     */
    public List<MetodoUML> getMetodos() {
        return this.metodos;
    }
    
    /**
     * Metodo responsavel por definir a Lista de Metodos UML da Interface UML.
     * @param metodos Lista de Metodos UML da Interface UML.
     */
    public void setMetodos(List<MetodoUML> metodos) {
        this.metodos = metodos;
    }

    /**
     * Metodo responsavel por retornar a Lista de Associacoes UML da Interface UML.
     * @return Lista de Associacoes UML da Interface UML.
     */
    public List<AssociacaoUML> getAssociacoes() {
        return this.associacoes;
    }

    /**
     * Metodo responsavel por definir a Lista de Associacoes UML da Interface UML.
     * @param associacoes Lista de Associacoes UML da Interface UML.
     */
    public void setAssociacoes(List<AssociacaoUML> associacoes) {
        this.associacoes = associacoes;
    }
    
    /**
     * Metodo responsavel por adicionar uma Associacao UML da Interface UML.
     * @param associacaoUML Associacao UML a ser adicionada.
     */
    public void addAssociacao(AssociacaoUML associacaoUML) {
        if (associacaoUML != null)
            this.associacoes.add(associacaoUML);
    }

    /**
     * Metodo responsavel por retornar a Lista de Estereotipos da Interface UML.
     * @return Lista de Estereotipos da Interface UML.
     */
    public List<String> getEstereotipos() {
        return this.estereotipos;
    }

    /**
     * Metodo responsavel por definir a Lista de Estereotipos da Interface UML.
     * @param estereotipos Lista de Estereotipos da Interface UML.
     */
    public void setEstereotipos(List<String> estereotipos) {
        this.estereotipos = estereotipos;
    }
    
    /**
     * Metodo responsavel por retornar o Pacote em uma String.
     * @return Pacote em uma String.
     */
    private String printPacote() {
        String print = this.pacote.pacote();
        if (print.equals("src"))
            return "";
        return "package " + print.substring(print.indexOf(".") + 1) + ";\n\n";
    }
    
    /**
     * Metodo responsavel por retornar a Heranca da Interface.
     * @return Heranca da Interface.
     */
    private String printHeranca() {
        if (this.idSuper.equals("") == false)
            return "extends " + this.idSuper + " ";
        return "";
    }
    
    /**
     * Metodo responsavel por retornar os Atributos em uma String.
     * @return Atributos em uma String.
     */
    private String printAtributos() {
        String print = "";
        for (int i = 0; i < this.atributos.size(); i++) {
            print += this.atributos.get(i).print() + "\n";
        }
        return print;
    }
    
    /**
     * Metodo responsavel por retornar os Metodos em uma String.
     * @return Metodos em uma String.
     */
    private String printMetodos() {
        String print = "";
        for (int i = 0; i < this.metodos.size(); i++) {
            print += "\n" + this.metodos.get(i).print();
        }
        return print;
    }
    
    /**
     * Metodo responsavel por retornar a Estrutura da Interface.
     * @return Estrutura da Interface.
     */
    public String print() {
        String print  = this.printPacote();
               print += "public ";
               print += "interface " + this.nome + " ";
               print += this.printHeranca();
               print += "{\n";
               print += this.printAtributos();
               print += this.printMetodos();
               print += "}";
        return print;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}