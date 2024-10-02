package modelo.uml.diagrama.usecases;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>AtorUML</b>.</p>
 * <p>Classe responsavel por representar um <b>Ator UML</b> no Sistema.</p>
 * @author Leandro
 * @since  07/03/2017
 */
public class AtorUML {
    private static final String TIPO_XMI = "uml:Class";
    private PastaUML pasta;
    private String   id;
    private String   idSuper;
    private String   nome;
    private List<String> estereotipos;

    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento W3C do Documento.
     * @param pasta Pasta UML pai.
     */
    public AtorUML(Element element, PastaUML pasta) {
        this.pasta        = pasta;
        this.id           = element.getAttribute("xmi:id");
        this.nome         = element.getAttribute("name");
        this.estereotipos = new ArrayList<>();
    }
    
    /**
     * Metodo responsavel por retornar a Pasta do Ator UML.
     * @return Pasta do Ator UML.
     */
    public PastaUML getPasta() {
        return this.pasta;
    }

    /**
     * Metodo responsavel por definir a Pasta do Ator UML.
     * @param pasta Pasta do Ator UML.
     */
    public void setPasta(PastaUML pasta) {
        this.pasta = pasta;
    }
    
    /**
     * Metodo responsavel por retornar o Id do Ator UML.
     * @return Id do Ator UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Ator UML.
     * @param id Id do Ator UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getIdSuper() {
        return this.idSuper;
    }

    public void setIdSuper(String idSuper) {
        this.idSuper = idSuper;
    }
    
    /**
     * Metodo responsavel por retornar o Nome do Ator UML.
     * @return Nome do Ator UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Ator UML.
     * @param nome Nome do Ator UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Lista de Estereotipos do Ator UML.
     * @return Lista de Estereotipos do Ator UML.
     */
    public List<String> getEstereotipos() {
        return this.estereotipos;
    }

    /**
     * Metodo responsavel por definir a Lista de Estereotipos do Ator UML.
     * @param estereotipos Lista de Estereotipos do Ator UML.
     */
    public void setEstereotipos(List<String> estereotipos) {
        this.estereotipos = estereotipos;
    }
    
    @Override
    public String toString() {
        return this.pasta.pasta() + "/" + this.nome + "[Ator]";
    }
}