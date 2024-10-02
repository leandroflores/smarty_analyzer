package modelo.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Classe de Modelo <b>MetodoUML</b>.</p>
 * <p>Classe responsavel por representar um <b>Metodo UML</b> no Sistema.</p>
 * @author Leandro
 * @since  21/02/2017
 * @see    modelo.uml.diagrama.classes.ParametroUML
 */
public class MetodoUML {
    private static final String TIPO_XMI = "uml:Operation";
    private final ClasseUML    classeUML;
    private final InterfaceUML interfaceUML;
    private String  id;
    private String  nome;
    private String  visibilidade;
    private String  retorno;
    private boolean construtor;
    private boolean estatico;
    private boolean definitivo;
    private boolean abstrato;
    private List<ParametroUML> parametros;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param classeUML Classe UML.
     * @param element Elemento W3C do Documento.
     */
    public MetodoUML(ClasseUML classeUML, Element element) {
        this.classeUML    = classeUML;
        this.interfaceUML = null;
        this.id           = element.getAttribute("xmi:id");
        this.nome         = element.getAttribute("name");
        this.visibilidade = element.getAttribute("visibility");
        this.estatico     = element.getAttribute("isStatic").equals("true");
        this.definitivo   = element.getAttribute("isLeaf").equals("true");
        this.abstrato     = element.getAttribute("isAbstract").equals("true");
        this.parametros   = new ArrayList<>();
        this.setConstrutor();
        this.setRetorno(element);
        this.setOwner();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param interfaceUML Interface UML.
     * @param element Elemento W3C do Documento.
     */
    public MetodoUML(InterfaceUML interfaceUML, Element element) {
        this.classeUML    = null;
        this.interfaceUML = interfaceUML;
        this.id           = element.getAttribute("xmi:id");
        this.nome         = element.getAttribute("name");
        this.visibilidade = element.getAttribute("visibility");
        this.estatico     = element.getAttribute("isStatic").equals("true");
        this.definitivo   = false;
        this.abstrato     = true;
        this.parametros   = new ArrayList<>();
        this.setConstrutor();
        this.setRetorno(element);
        this.setOwner();
    }
    
     /**
     * Metodo responsavel por retornar o Id do Metodo UML.
     * @return Id do Metodo UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Metodo UML.
     * @param id Id do Metodo UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Metodo UML.
     * @return Nome do Metodo UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Metodo UML.
     * @param nome Nome do Metodo UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Visibilidade do Metodo UML.
     * @return Visibilidade do Metodo UML.
     */
    public String getVisibilidade() {
        return this.visibilidade;
    }

    /**
     * Metodo responsavel por definir a Visibilidade do Metodo UML.
     * @param visibilidade Visibilidade do Metodo UML.
     */
    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }
    
    /**
     * Metodo responsavel por retornar o Retorno do Metodo UML.
     * @return Retorno do Metodo UML.
     */
    public String getRetorno() {
        return this.retorno;
    }
    
    /**
     * Metodo responsavel por definir o Retorno do Metodo UML.
     * @param retorno Retorno do Metodo UML.
     */
    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    /**
     * Metodo responsavel por adicionar um Parametro ao Metodo UML.
     * @param parametro Parametro a ser adicionado.
     */
    public void addParametro(ParametroUML parametro) {
        if (!parametro.getNome().equals("")) {
            this.parametros.add(parametro);
        }
    }
    
    /**
     * Metodo responsavel por definir o Construtor do Metodo UML.
     */
    private void setConstrutor() {
        if (this.classeUML != null)
            this.construtor = this.nome.equals(this.classeUML.getNome());
        if (this.interfaceUML != null)
            this.construtor = this.nome.equals(this.interfaceUML.getNome());
    }
    
    /**
     * Metodo responsavel por definir o Retorno do Metodo UML.
     * @param element Elemento W3C.
     */
    private void setRetorno(Element element) {
        if (this.construtor)
            this.retorno = "";
        else
            this.retorno = this.findRetorno(element);
    }
    
    /**
     * Metodo responsavel por encontrar o Retorno do Metodo UML.
     * @param  element Elemento W3C.
     * @return Retorno do Metodo UML.
     */
    private String findRetorno(Element element) {
        NodeList elements = element.getElementsByTagName("ownedParameter");
        for (int i = 0; i < elements.getLength(); ++i) {
            Element current = (Element) elements.item(i);
            if (current.getAttribute("direction").equals("return")) {
                return current.getAttribute("type");
            }
        }
        return "";
    }

    /**
     * Metodo responsavel por definir o proprietario do Metodo UML.
     */
    private void setOwner() {
        if (this.classeUML != null)
            this.classeUML.addMetodo(this);
        if (this.interfaceUML != null)
            this.interfaceUML.addMetodo(this);
    }
    
    /**
     * Metodo responsavel por retornar a flag Construtor do Metodo UML.
     * @return Flag Construtor do Metodo UML.
     */
    public boolean isConstrutor() {
        return this.construtor;
    }

    /**
     * Metodo responsavel por definir a flag Construtor do Metodo UML.
     * @param construtor Flag Construtor do Metodo UML.
     */
    public void setConstrutor(boolean construtor) {
        this.construtor = construtor;
    }
    
    /**
     * Metodo responsavel por retornar a flag Estatico do Metodo UML.
     * @return Flag Estatico do Metodo UML.
     */
    public boolean isEstatico() {
        return this.estatico;
    }

    /**
     * Metodo responsavel por definir a flag Estatico do Metodo UML.
     * @param estatico Flag Estatico do Metodo UML.
     */
    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }
    
    /**
     * Metodo responsavel por retornar a flag Definitivo do Metodo UML.
     * @return Flag Definitivo do Metodo UML.
     */
    public boolean isDefinitivo() {
        return this.definitivo;
    }
    
    /**
     * Metodo responsavel por definir a flag Definitivo do Metodo UML.
     * @param definitivo Flag Definitivo do Metodo UML.
     */
    public void setDefinitivo(boolean definitivo) {
        this.definitivo = definitivo;
    }

    /**
     * Metodo responsavel por definir a flag Abstrato do Metodo UML.
     * @param abstrato Flag Abstrato do Metodo UML.
     */
    public void setAbstrato(boolean abstrato) {
        this.abstrato = abstrato;
    }
    
    /**
     * Metodo responsavel por retornar a flag Abstrato do Metodo UML.
     * @return Flag Abstrato do Metodo UML.
     */
    public boolean isAbstrato() {
        return this.abstrato;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros do Metodo UML.
     * @return Lista de Parametros do Metodo UML.
     */
    public List<ParametroUML> getParametros() {
        return this.parametros;
    }
    
    /**
     * Metodo responsavel por definir uma Lista de Parametros do Metodo UML.
     * @param parametros Lista de Parametros do Metodo UML.
     */
    public void setParametros(List<ParametroUML> parametros) {
        this.parametros = parametros;
    }
    
    public List<String> getPacotes() {
        List<String> toReturn = new ArrayList<>();
//        for (int i = 0; i < this.parametros.size(); i++) {
//            //String pacote = this.parametros.get(i).
//        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar uma String com os Parametros de um Metodo UML.
     * @return String com os Parametros de um Metodo UML.
     */
    private String printParametros() {
        if (this.parametros.isEmpty())   return "()";
        if (this.parametros.size() == 1) return "(" + this.parametros.get(0).print() + ")";
        String toReturn = "(";
        for (int i = 0; i < this.parametros.size() - 1; ++i) {
               toReturn += this.parametros.get(i).print() + ", ";
        }
               toReturn += this.parametros.get(this.parametros.size() - 1).print() + ")";
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar os Dados para Impressao de um Metodo UML.
     * @return Dados para Impressao de um Metodo UML.
     */
    public String print() {
        String metodo  = "    ";
               metodo += this.visibilidade;
               metodo += (this.estatico)   ? " static"   : "";
               metodo += (this.abstrato)   ? " abstract" : "";
               metodo += (this.definitivo) ? " final"    : "";
               metodo += " " + this.retorno;
               metodo += " " + this.nome;
               metodo += this.printParametros();
               metodo += (this.abstrato)   ? ";\n" : " {" + this.printRetorno();
        return metodo;
    }
    
    /**
     * Metodo responsavel por retornar o Retorno do Metodo UML.
     * @return Retorno do Metodo UML.
     */
    private String printRetorno() {
        if (this.construtor)
            return "}\n";
        if (this.retorno.equals("void"))
            return "\n    }\n";
        if (this.retorno.equals("int"))
            return "\n        return 0;\n    }\n";
        if (this.retorno.equals("Integer"))
            return "\n    return 0;\n    }\n";
        if (this.retorno.equals("Long"))
            return "    return 0L;\n    }\n";
        if (this.retorno.equals("long"))
            return "    return 0L;\n    }\n";
        if (this.retorno.equals("String"))
            return "    return \"\";\n    }\n";
        if (this.retorno.equals("char"))
            return "    return '';\n    }\n";
        if (this.retorno.equals("boolean"))
            return "    return true;\n    }\n";
        return "\n    return null;\n    }\n";
    }
    
    @Override
    public boolean equals(Object object) {
        if ((object instanceof MetodoUML) == false) 
            return false;
        return this.id.equals(((MetodoUML) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.print();
    }
}