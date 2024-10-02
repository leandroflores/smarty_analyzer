package modelo.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>ClasseUML</b>.</p>
 * <p>Classe responsavel por representar uma <b>Classe UML</b> do Sistema.</p>
 * @author Leandro
 * @since  21/02/2017
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 * @see    modelo.uml.diagrama.classes.PacoteUML
 * @see    modelo.uml.diagrama.classes.EstereotipoUML
 */
public class ClasseUML {
    public static final String TIPO_XMI = "uml:Class";
    private PacoteUML pacote;
    private ClasseUML superClasse;
    private String    idSuperClasse;
    private String    id;
    private String    nome;
    private boolean   abstrato;
    private boolean   folha;
    private List<AtributoUML>   atributos;
    private List<MetodoUML>     metodos;
    private List<String>        interfaces;
    private List<AssociacaoUML> associacoes;
    private List<InterfaceUML>  realizacoes;
    private List<String>        estereotipos;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param element Elemento W3C do Documento.
     * @param pacoteUML Pacote UML pai.
     */
    public ClasseUML(Element element, PacoteUML pacoteUML) {
        this.pacote        = pacoteUML;
        this.superClasse   = null;
        this.idSuperClasse = "";
        this.id            = element.getAttribute("xmi:id");
        this.nome          = element.getAttribute("name");
        this.abstrato      = element.getAttribute("isAbstract").equals("true");
        this.folha         = element.getAttribute("isLeaf").equals("true");
        this.atributos     = new ArrayList<>();
        this.associacoes   = new ArrayList<>();
        this.realizacoes   = new ArrayList<>();
        this.metodos       = new ArrayList<>();
        this.interfaces    = new ArrayList<>();
        this.estereotipos  = new ArrayList<>();
    }

    /**
     * Metodo responsavel por retornar o Pacote da Classe UML.
     * @return Pacote da Classe UML.
     */
    public PacoteUML getPacote() {
        return this.pacote;
    }

    /**
     * Metodo responsavel por definir o Pacote da Classe UML.
     * @param pacote Pacote da Classe UML.
     */
    public void setPacote(PacoteUML pacote) {
        this.pacote = pacote;
    }

    /**
     * Metodo responsavel por retornar a Super Classe da Classe UML.
     * @return Super Classe da Classe UML.
     */
    public ClasseUML getSuperClasse() {
        return this.superClasse;
    }

    /**
     * Metodo responsavel por definir a Super Classe da Classe UML.
     * @param superClasse Super Classe da Classe UML.
     */
    public void setSuperClasse(ClasseUML superClasse) {
        this.superClasse = superClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Id da Super Classe da Classe UML.
     * @return Id da Super Classe da Classe UML.
     */
    public String getIdSuperClasse() {
        return this.idSuperClasse;
    }

    /**
     * Metodo responsavel por definir o Id da Super Classe da Classe UML.
     * @param idSuperClasse Id da Super Classe da Classe UML.
     */
    public void setIdSuperClasse(String idSuperClasse) {
        this.idSuperClasse = idSuperClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Id da Classe UML.
     * @return Id da Classe UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Classe.
     * @param id Id da Classe.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome da Classe UML.
     * @return Nome da Classe UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome da Classe UML.
     * @param nome Nome da Classe UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a flag Abstrato da Classe UML.
     * @return Flag Abstrato da Classe UML.
     */
    public boolean isAbstrato() {
        return this.abstrato;
    }

    /**
     * Metodo responsavel por definir a flag Abstrato da Classe UML.
     * @param abstrato Flag Abstrato da Classe UML.
     */
    public void setAbstrato(boolean abstrato) {
        this.abstrato = abstrato;
    }

    /**
     * Metodo responsavel por retornar a flag Folha da Classe UML.
     * @return Flag Folha da Classe UML.
     */
    public boolean isFolha() {
        return this.folha;
    }

    /**
     * Metodo responsavel por definir a flag Folha da Classe UML.
     * @param folha Flag Folha da Classe UML.
     */
    public void setFolha(boolean folha) {
        this.folha = folha;
    }
    
    /**
     * Metodo responsavel por adicionar um Atributo UML a Classe UML.
     * @param atributoUML Atributo UML a ser adicionado.
     */
    public void addAtributo(AtributoUML atributoUML) {
        if (!atributoUML.getNome().equals(""))
            this.atributos.add(atributoUML);
    }
    
    /**
     * Metodo responsavel por adicionar um Metodo UML a Classe UML.
     * @param metodoUML Metodo UML a ser adicionado.
     */
    public void addMetodo(MetodoUML metodoUML) {
        if (!metodoUML.getNome().equals("")) {
            this.metodos.add(metodoUML);
        }
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atributos da Classe UML.
     * @return Lista de Atributos da Classe UML.
     */
    public List<AtributoUML> getAtributos() {
        return this.atributos;
    }
    
    /**
     * Metodo responsavel por definir a Lista de Atributos da Classe UML.
     * @param atributos Lista de Atributos da Classe UML.
     */
    public void setAtributos(List<AtributoUML> atributos) {
        this.atributos = atributos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Metodos da Classe UML.
     * @return Lista de Metodos da Classe UML.
     */
    public List<MetodoUML> getMetodos() {
        return this.metodos;
    }
    
    /**
     * Metodo responsavel por definir a Lista de Metodos da Classe UML.
     * @param metodos Lista de Metodos da Classe UML.
     */
    public void setMetodos(List<MetodoUML> metodos) {
        this.metodos = metodos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces implementadas pela Classe UML.
     * @return Lista de Interfaces implementadas pela Classe UML.
     */
    public List<String> getInterfaces() {
        return this.interfaces;
    }
    
    /**
     * Metodo responsavel por definir a Lista de Interfaces implementadas pela Classe UML.
     * @param interfaces Lista de Interfaces implementadas pela Classe UML.
     */
    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * Metodo responsavel por retornar a Lista de Associacoes UML da Classe UML.
     * @return Lista de Associacoes UML da Classe UML.
     */
    public List<AssociacaoUML> getAssociacoes() {
        return this.associacoes;
    }

    /**
     * Metodo responsavel por definir a Lista de Associacoes UML da Classe UML.
     * @param associacoes Lista de Associacoes UML da Classe UML.
     */
    public void setAssociacoes(List<AssociacaoUML> associacoes) {
        this.associacoes = associacoes;
    }
    
    /*
     * Metodo responsavel por adicionar uma Associacao UML a Classe UML.
     * @param associacao Associacao UML a ser adicionada.
     */
    public void addAssociacao(AssociacaoUML associacao) {
        if (associacao != null)
            this.associacoes.add(associacao);
    }

    /**
     * Metodo responsavel por retornar a Lista de Associacoes UML da Classe UML.
     * @return Lista de Associacoes UML da Classe UML.
     */
    public List<InterfaceUML> getRealizacoes() {
        return this.realizacoes;
    }

    /**
     * Metodo responsavel por definir a Lista de Realizacoes UML da Classe UML.
     * @param realizacoes Lista de Realizacoes UML da Classe UML.
     */
    public void setRealizacoes(List<InterfaceUML> realizacoes) {
        this.realizacoes = realizacoes;
    }
    
    /**
     * Metodo responsavel por adicionar uma Realizacao UML a Classe UML.
     * @param interfaceUML Interface UML a ser adicionada.
     */
    public void addRealizacao(InterfaceUML interfaceUML) {
        if (interfaceUML != null)
            this.realizacoes.add(interfaceUML);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Estereotipos da Classe UML.
     * @return Lista de Estereotipos da Classe UML.
     */
    public List<String> getEstereotipos() {
        return this.estereotipos;
    }

    /**
     * Metodo responsavel por definir a Lista de Estereotipos da Classe UML.
     * @param estereotipos Lista de Estereotipos da Classe UML.
     */
    public void setEstereotipos(List<String> estereotipos) {
        this.estereotipos = estereotipos;
    }
        
    /**
     * Metodo responsavel por retornar a String para Impressao da Classe UML.
     * @return String para Impressao da Classe UML.
     */
    public String print() {
        String print  = this.printPacote();
               print += this.printImport();
               print += this.printJavaDoc();
               print += "public ";
               print += (this.abstrato) ? "abstract " : "";
               print += "class " + this.nome + " ";
               print += this.printSuperClasse();
               print += this.printInterfaces();
               print += "{\n";
               print += this.printAtributos();
               print += this.printAssociacoes();
               print += this.printMetodos();
               print += "}";
        return print;
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
     * Metodo responsavel por retornar as Importacao em uma String.
     * @return Importacoes em uma String.
     */
    private String printImport() {
        List<String> imports  = this.getImportacoes();
             String  toReturn = "";
        for (int i = 0; i < imports.size(); i++) {
            if (imports.get(i).equals("") == false)
               toReturn += "import " + imports.get(i) + ";\n";
        }
               toReturn += "\n";
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar o Java Doc da Classe UML.
     * @return Java Doc da Classe UML.
     */
    private String printJavaDoc() {
        String toReturn  = "/**\n";
               toReturn += " * <p>Classe <b>" + this.nome + "</b>.</p>\n";
               toReturn += " * @author SMartyAnalyzer\n";
               toReturn += " * @since  21/02/2017\n";
               toReturn += " */\n";
        return toReturn;
    }
    
    public static void main(String[] args) {
        String nome = "testeA";
        System.out.println(ClasseUML.printClasseHibernate(nome));
    }
    
    private static String printClasseHibernate(String classe) {
        String toReturn  = "@Entity\n";
               toReturn += "@Table (name = \"";
               toReturn += getFormatoBanco(classe);
               toReturn += "\")\n";
        return toReturn;
    }
    
    private static String printAtributoHibernate(String atributo) {
        String toReturn  = "@Column (name = \"";
               toReturn += getFormatoBanco(atributo);
               toReturn += "\")\n";
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar a Entidade em formato de Banco de Dados.
     * @param  entidade Entidade a ser mapeada.
     * @return Entidade em forma de Banco de Dados.
     */
    private static String getFormatoBanco(String entidade) {
        String toReturn = "";
        for (int i = 0; i < entidade.length(); i++) {
            if (Character.isUpperCase(entidade.charAt(i))) {
                if (i > 0) 
                    toReturn += "_";
                toReturn += Character.toString(entidade.charAt(i)).toLowerCase();
            }else {
                toReturn += entidade.charAt(i);
            }
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Importacoes da Classe UML.
     * @return Lista de Importacoes da Classe UML.
     */
    private List<String> getImportacoes() {
        List<String> importacoes = new ArrayList<>();
            this.addImportSuperClasse(importacoes);
            this.addImportRealizacoes(importacoes);
            this.addImportAtributos(importacoes);
        Collections.sort(importacoes);
        return importacoes;
    }
    
    /**
     * Metodo responsavel por retornar o formato de importacao.
     * @param  importacao Caminho da Importacao.
     * @return Formato correto da importacao.
     */
    private String setImport(String importacao) {
        if (importacao.equals(""))
            return "";
        if (importacao.contains(".") == false)
            return "";
        if (importacao.startsWith("src."))
            return importacao.substring(importacao.indexOf(".") + 1);
        return importacao;
    }
    
    /**
     * Metodo responsavel por adicionar a importacao da Super Classe.
     * @param importacoes Lista de Importacoes Iniciais.
     */
    private void addImportSuperClasse(List<String> importacoes) {
        if (this.superClasse != null) {
            String importacao = this.setImport(this.superClasse.getPacote().pacote() + "." + this.superClasse.getNome());
            if (importacoes.contains(importacao) == false)
                importacoes.add(importacao);
        }
    }
    
    /**
     * Metodo responsavel por adicionar as importacoes das Realizacoes.
     * @param importacoes Lista de Importacoes iniciais.
     */
    private void addImportRealizacoes(List<String> importacoes) {
        for (int i = 0; i < this.realizacoes.size(); i++) {
            String importacao = this.setImport(this.realizacoes.get(i).getPacote().pacote() + "." + this.realizacoes.get(i).getNome());
            if (importacoes.contains(importacao) == false)
                importacoes.add(importacao);
        }
    }
    
    /**
     * Metodo responsavel por adicionar as importacoes dos Atributos.
     * @param importacoes Lista de Importacoes iniciais.
     */
    private void addImportAtributos(List<String> importacoes) {
        for (int i = 0; i < this.atributos.size(); i++) {
            String importacao = this.setImport(this.atributos.get(i).printImport());
            if (importacoes.contains(importacao) == false)
                importacoes.add(importacao);
        }
    }
    
    /**
     * Metodo responsavel por retornar a SuperClasse da Classe.
     * @return SuperClasse da Classe.
     */
    private String printSuperClasse() {
        if (this.superClasse != null)
            return "extends " + this.superClasse + " ";
        return "";
    }
    
    /**
     * Metodo responsavel por retornar as Interfaces implementadas pela Classe.
     * @return Interfaces implementadas pela Classe.
     */
    private String printInterfaces() {
        if (this.interfaces.isEmpty())   return "";
        if (this.interfaces.size() == 1) return "implements " + this.interfaces.get(0) + " ";
        String print = "implements ";
        for (int i = 0; i < this.interfaces.size() - 1; i++) {
            print += this.interfaces.get(i) + ", ";
        }
               print += this.interfaces.get(this.interfaces.size() - 1) + " ";
        return print;
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
     * Metodo responsavel por retornar as Associacoes em uma String.
     * @return Associacoes em uma String.
     */
    private String printAssociacoes() {
        String print = "";
        //for (int i = 0; i < this.associacoes.size(); i++) {
        //    print += this.associacoes.get(i).print() + "\n";
        //}
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
    
    @Override
    public boolean equals(Object object) {
        if ((object instanceof ClasseUML) == false) 
            return false;
        return this.id.equals(((ClasseUML) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 7;
               hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}