package modelo.uml.diagrama.usecases;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>CasoDeUsoUML</b>.</p>
 * <p>Classe responsavel por representar um <b>Caso de Uso UML</b> no Sistema.</p>
 * @author Leandro
 * @since  07/03/2017
 */
public class CasoDeUsoUML {
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
    public CasoDeUsoUML(Element element, PastaUML pasta) {
        this.pasta        = pasta;
        this.id           = element.getAttribute("xmi:id");
        this.nome         = element.getAttribute("name");
        this.estereotipos = new ArrayList<>();
    }
    
    /**
     * Metodo responsavel por retornar a Pasta UML do Caso de Uso UML.
     * @return Pasta UML do Caso de Uso UML.
     */
    public PastaUML getPasta() {
        return this.pasta;
    }

    /**
     * Metodo responsavel por definir a Pasta UML do Caso de Uso UML.
     * @param pasta Pasta UML do Caso de Uso UML.
     */
    public void setPasta(PastaUML pasta) {
        this.pasta = pasta;
    }

    /**
     * Metodo responsavel por retornar o Id do Caso de Uso UML.
     * @return Id do Caso de Uso UML.
     */
    public String getId() {
        return this.id;
    }

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
     * Metodo responsavel por retornar o Nome do Caso de Uso UML.
     * @return Nome do Caso de Uso UML.
     */
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Lista de Estereotipos do Caso de Uso UML.
     * @return Lista de Estereotipos do Caso de Uso UML.
     */
    public List<String> getEstereotipos() {
        return this.estereotipos;
    }

    public void setEstereotipos(List<String> estereotipos) {
        this.estereotipos = estereotipos;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}