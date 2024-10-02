package modelo.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe de Modelo <b>PacoteUML</b>.</p>
 * <p>Classe responsavel por representar um <b>Pacote UML</b> no Sistema.</p>
 * <p>Usado exclusivamente para os <b>Diagramas de Classe</b>.</p>
 * @author Leandro
 * @since  21/02/2017
 */
public class PacoteUML {
    private static final String TIPO_XMI = "uml:Package";
    private PacoteUML pai;
    private String    id;
    private String    nome;
    private List<ClasseUML> classes;
    private List<InterfaceUML> interfaces;
    private List<PacoteUML> subPacotes;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param pai Pacote pai do Pacote.
     * @param nome Nome do Pacote.
     */
    public PacoteUML(PacoteUML pai, String nome) {
        this.pai        = pai;
        this.nome       = nome;
        this.classes    = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.subPacotes = new ArrayList<>();
    }

    /**
     * Metodo responsavel por retornar o Pacote Pai do Pacote.
     * @return Pacote Pai do Pacote.
     */
    public PacoteUML getPai() {
        return this.pai;
    }

    /**
     * Metodo responsavel por definir o Pacote Pai do Pacote.
     * @param pai Pacote Pai do Pacote.
     */
    public void setPai(PacoteUML pai) {
        this.pai = pai;
    }

    /**
     * Metodo responsavel por retornar o Id do Pacote.
     * @return Id do Pacote.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Metodo responsavel por definir o Id do Pacote.
     * @param id Id do Pacote.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Pacote.
     * @return Nome do Pacote.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Pacote.
     * @param nome Nome do Pacote.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Lista de Classes UML do Pacote UML.
     * @return Lista de Classes UML do Pacote UML.
     */
    public List<ClasseUML> getClasses() {
        return this.classes;
    }

    /**
     * Metodo responsavel por definir a Lista de Classes UML do Pacote UML.
     * @param classes Lista de Classes UML.
     */
    public void setClasses(List<ClasseUML> classes) {
        this.classes = classes;
    }
    
    /**
     * Metodo responsavel por adicionar uma Classe UML no Pacote UML.
     * @param classeUML Classe UML.
     */
    public void addClasseUML(ClasseUML classeUML) {
        if (classeUML != null)
            this.classes.add(classeUML);
    }
    
    /**
     * Metodo responsavel por retornar uma Classe UML pelo Nome.
     * @param  nome Nome da Classe UML.
     * @return Classe UML encontrada.
     */
    public ClasseUML getClasseUML(String nome) {
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i).getNome().equals(nome))
                return this.classes.get(i);
        }
        return null;
    }

    /**
     * Metodo responsavel por retornar a Lista de Interfaces UML do Pacote UML.
     * @return Lista de Interfaces UML do Pacote UML.
     */
    public List<InterfaceUML> getInterfaces() {
        return this.interfaces;
    }

    /**
     * Metodo responsavel por definir a Lista de Interfaces UML do Pacote UML.
     * @param interfaces Lista de Interfaces UML do Pacote UML.
     */
    public void setInterfaces(List<InterfaceUML> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * Metodo responsavel por adicionar a Interface UML ao Pacote UML.
     * @param interfaceUML Interface UML.
     */
    public void addInterfaceUML(InterfaceUML interfaceUML) {
        if (interfaceUML != null)
            this.interfaces.add(interfaceUML);
    }
    
    /**
     * Metodo responsavel por retornar uma Interface UML pelo Nome.
     * @param  nome Nome da Interface UML.
     * @return Interface UML encontrada.
     */
    public InterfaceUML getInterfaceUML(String nome) {
        for (int i = 0; i < this.interfaces.size(); i++) {
            if (this.interfaces.get(i).getNome().equals(nome))
                return this.interfaces.get(i);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar um SubPacote UML pelo Nome.
     * @param  nome Nome do SubPacote UML.
     * @return Pacote UML encontrado.
     */
    public PacoteUML getSubpacoteUML(String nome) {
        for (int i = 0; i < this.subPacotes.size(); i++) {
            if (this.subPacotes.get(i).getNome().equals(nome))
                return this.subPacotes.get(i);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retonar a Lista de Subpacotes de um Pacote UML.
     * @return Lista de Subpacotes do Pacote UML.
     */
    public List<PacoteUML> getSubPacotes() {
        return this.subPacotes;
    }

    /**
     * Metodo responsavel por definir a Lista de Subpacotes do Pacote UML.
     * @param subPacotes Lista de Subpacotes do Pacote UML.
     */
    public void setSubPacotes(List<PacoteUML> subPacotes) {
        this.subPacotes = subPacotes;
    }
    
    /**
     * Metodo responsavel por adicionar um Subpacote UML no Pacote UML.
     * @param pacoteUML Pacote UML.
     */
    public void addSubpacoteUML(PacoteUML pacoteUML) {
        if (pacoteUML != null)
            this.subPacotes.add(pacoteUML);
    }
    
    /**
     * Metodo responsavel por retornar o Caminho do Pacote.
     * @return Caminho do Pacote.
     */
    public String pacote() {
        if (this.pai == null) 
            return this.nome;
        if (this.pai.getPai() == null) 
            return this.pai.getNome() + "." + this.nome;
        return this.pai.pacote() + "." + this.nome;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}