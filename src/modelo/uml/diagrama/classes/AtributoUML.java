package modelo.uml.diagrama.classes;

import org.w3c.dom.Element;

public class AtributoUML {
    public static final String TIPO_XMI = "uml:Property";
    private String  id;
    private String  nome;
    private String  tipo;
    private String  visibilidade;
    private boolean estatico;
    private boolean constante;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento W3C.
     */
    public AtributoUML(Element element) {
        this.id           = element.getAttribute("xmi:id");
        this.nome         = element.getAttribute("name");
        this.tipo         = element.getAttribute("type");
        this.visibilidade = element.getAttribute("visibility");
        this.estatico     = element.getAttribute("isStatic").trim().equals("true");
        this.constante    = element.getAttribute("readOnly").trim().equals("true");
    }

    /**
     * Metodo responsavel por retornar o Id do Atributo UML.
     * @return Id do Atributo UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Atributo UML.
     * @param id Id do Atributo UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Atributo UML.
     * @return Nome do Atributo UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Atributo UML.
     * @param nome Nome do Atributo UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar o Tipo do Atributo UML.
     * @return Tipo do Atributo UML.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo responsavel por definir o Tipo do Atributo UML.
     * @param tipo Tipo do Atributo UML.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo responsavel por retornar a Visibilidade do Atributo UML.
     * @return Visibilidade do Atributo UML.
     */
    public String getVisibilidade() {
        return this.visibilidade;
    }

    /**
     * Metodo responsavel por definir a Visibilidade do Atributo UML.
     * @param visibilidade Visibilidade do Atributo UML.
     */
    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }

    /**
     * Metodo responsavel por retornar a flag Estatico do Atributo UML.
     * @return Flag Estatico do Atributo UML.
     */
    public boolean isEstatico() {
        return this.estatico;
    }

    /**
     * Metodo responsavel por definir a flag Estatico do Atributo UML.
     * @param estatico Flag Estatico do Atributo UML.
     */
    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }

    /**
     * Metodo responsavel por retornar a flag Constante do Atributo UML.
     * @return Flag Constante do Atributo UML.
     */
    public boolean isConstante() {
        return this.constante;
    }

    /**
     * Metodo responsavel por definir a flag Constante do Atributo UML.
     * @param constante Flag Constante do Atributo UML.
     */
    public void setConstante(boolean constante) {
        this.constante = constante;
    }
    
    /**
     * Metodo responsavel por retornar os Dados do Atributo UML.
     * @return Dados do Atributo UML.
     */
    public String print() {
        String atributo  = "    ";
               atributo += this.printVisibilidade();
               atributo += (this.estatico)  ? " static" : "";
               atributo += (this.constante) ? " final"  : "";
               atributo += " " + this.printTipo();
               atributo += " " + this.nome + ";";
        return atributo;
    }
    
    /**
     * Metodo responsavel por retornar a Visibilidade do Atributo UML.
     * @return Visibilidade do Atributo UML.
     */
    private String printVisibilidade() {
        if (this.visibilidade.equals("package"))
            return "";
        return this.visibilidade;
    }
    
    /**
     * Metodo responsavel por retornar a Importacao do Atributo UML.
     * @return Importacao do Atributo UML.
     */
    public String printImport() {
        if (this.tipo.contains(".")) {
            return this.tipo;
        }
        return "";
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Atributo UML.
     * @return Tipo do Atributo UML.
     */
    private String printTipo() {
        if (this.tipo.contains("."))
            return this.tipo.substring(this.tipo.lastIndexOf(".") + 1);
        return this.tipo;
    }
    
    @Override
    public String toString() {
        return this.nome.trim();
    }
}