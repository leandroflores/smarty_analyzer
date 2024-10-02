package arquivo.importacao.diagramas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import modelo.controller.uml.diagrama.classes.ControllerAtributoUML;
import modelo.controller.uml.diagrama.classes.ControllerClasseUML;
import modelo.controller.uml.diagrama.classes.ControllerParametroUML;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.AssociacaoUML;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.MetodoUML;
import modelo.uml.diagrama.classes.PacoteUML;
import modelo.uml.diagrama.classes.java.ObjetoJava;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Classe de Leitura <b>LeitorDiagramaClasse</b>.</p>
 * <p>Classe responsavel por ser o <b>Leitor do Documento XMI</b> para os Diagramas de Classe.</p>
 * <p>Classe realiza a leitura do <b>Arquivo XMI</b> e constroi o Diagrama.</p>
 * @author Leandro
 * @since  14/03/2017
 * @see    modelo.controller.uml.diagrama.classes.ControllerAtributoUML
 */
public class LeitorDiagramaClasse {
    private static final ControllerAtributoUML  CONTROLLER_ATRIBUTO_UML  = new ControllerAtributoUML();
    private static final ControllerClasseUML    CONTROLLER_CLASSE_UML    = new ControllerClasseUML();
    private static final ControllerParametroUML CONTROLLER_PARAMETRO_UML = new ControllerParametroUML();
    
    private final String  caminho;
    private final XPath   xPath;
    
    private File      arquivo;
    private Document  documento;
    private PacoteUML raiz;
    private NodeList  nodeList;
    private String    expressao;
    
    private DiagramaClasse diagramaDeClasse;
    
    private HashMap<String, ObjetoJava> java;
    private HashMap<String, String> estereotipos;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Documento.
     */
    public LeitorDiagramaClasse(String caminho) {
        this.caminho = caminho;
        this.xPath   = XPathFactory.newInstance().newXPath();
    }
    
    /**
     * Metodo responsavel por retornar o Diagrama de Classe UML.
     * @return Diagrama de Classe UML.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException 
     */
    public DiagramaClasse getDiagramaClasse() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.abrirArquivo();
        
        this.getEstereotipos();
        this.getClassesJavaLang();
        this.getClassesJavaUtil();
        this.getTiposPrimitivos();
        
               this.diagramaDeClasse = new DiagramaClasse(this.createModeloUML());
               this.diagramaDeClasse.updateTipos(this.java);
        return this.diagramaDeClasse;
    }
    
    /**
     * Metodo responsavel por abrir o Arquivo XMI do Modelo UML.
     * @throws ParserConfigurationException Erro no Parser.
     * @throws SAXException Eror no SAX.
     * @throws IOException Erro na Abertura do Arquivo.
     */
    private void abrirArquivo() throws ParserConfigurationException, SAXException, IOException {
        this.arquivo      = new File(this.caminho);
        this.documento    = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.arquivo);
        this.documento.getDocumentElement().normalize();
        this.java         = new HashMap();
        this.estereotipos = new HashMap<>();
    }
    
    /**
     * Metodo responsavel por adicionar os Estereotipos do Diagrama de Classes.
     * @throws XPathExpressionException 
     */
    private void getEstereotipos() throws XPathExpressionException {
        this.expressao = "/XMI/Model/ownedComment[@type='uml:Comment']";
        this.nodeList  = (NodeList) this.xPath.compile(this.expressao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++)
            this.addEstereotipo((Element) this.nodeList.item(i));
    }
    
    /**
     * Metodo responsavel por adicionar os Estereotipo na Lista de Esteretipo.
     * @param elemento Elemento W3C do Documento.
     */
    private void addEstereotipo(Element elemento) throws XPathExpressionException {
        String   objeto      = elemento.getAttribute("annotatedElement").trim();
        String   id          = elemento.getAttribute("xmi:id").trim();
        String   filtro      = "/XMI/Model/ownedComment[@type='uml:Comment' and @id='" + id + "']/body/text()";
        String   estereotipo = (String) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.STRING);
        String   valor       = this.estereotipos.get(objeto);
        if (valor != null) {
            this.getEstereotipos(estereotipo);
            valor += estereotipo;
            this.estereotipos.put(objeto, valor);
        }else {
            this.estereotipos.put(objeto, estereotipo);
        }
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Estereotipos.
     * @param estereotipos String com os Estereotipos.
     * @return Lista de Estereotipos.
     */
    private List<String> getEstereotipos(String estereotipos) {
        List<String> lista = new ArrayList<>();
             String  valor = estereotipos.replaceAll("&lt;&lt;&lt;", "<").replaceAll("&lt;&lt;", "<").replaceAll("&lt;", "<");
                     valor = valor.replaceAll("&gt;&gt;&gt;", ">").replaceAll("&gt;&gt;", ">").replaceAll("&gt;", ">");
        if ((valor.contains("<")) && (valor.contains(">"))) {
            String[] valores = valor.split(">");
            for (String estereotipo : valores) {
                if ((!estereotipo.trim().equals("")) && (!lista.contains(estereotipo)))
                    lista.add(estereotipo.replace("<", "").trim());
            }
        }
        return lista;
    }
    
    /**
     * Metodo responsavel por adicionar as Classes Java no Hash.
     * @param pacote Pacote da Classe Java.
     * @param element Elemento W3C do Documento.
     */
    private void addClassesJava(String pacote, Element element) {
        this.nodeList = element.getElementsByTagName("packagedElement");
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element elemento = (Element) this.nodeList.item(i);
            if    ((elemento.getAttribute("xmi:type").equals("uml:Class"))
                || (elemento.getAttribute("xmi:type").equals("uml:Interface")))
                this.java.put(elemento.getAttribute("xmi:id"), new ObjetoJava(pacote, elemento.getAttribute("name")));
        }
    }
    
    /**
     * Metodo responsavel por adicionar as Classes Java Lang na Hash.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void getClassesJavaLang() throws XPathExpressionException {
        this.expressao = "/XMI/Model/packagedElement[@name='java' and @type='uml:Package']/packagedElement[@name='lang' and @type='uml:Package']";
        this.nodeList  = (NodeList) this.xPath.compile(this.expressao).evaluate(this.documento, XPathConstants.NODESET);
        if (this.nodeList.getLength() == 1)
            this.addClassesJava("java.lang", (Element) this.nodeList.item(0));
    }
    
    /**
     * Metodo responsavel por adicionar as Classes Java Util na Hash.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void getClassesJavaUtil() throws XPathExpressionException {
        this.expressao = "/XMI/Model/packagedElement[@name='java' and @type='uml:Package']/packagedElement[@name='util' and @type='uml:Package']";
        this.nodeList  = (NodeList) this.xPath.compile(this.expressao).evaluate(this.documento, XPathConstants.NODESET);
        if (this.nodeList.getLength() == 1)
            this.addClassesJava("java.util", (Element) this.nodeList.item(0));
    }
    
    /**
     * Metodo responsavel por adicionar os Tipos Primitivos no Hash.
     * @param element Elemento W3C do Documento.
     */
    private void addTiposPrimitivos(Element element) {
        this.nodeList = element.getElementsByTagName("packagedElement");
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element elemento = (Element) this.nodeList.item(i);
            if (elemento.getAttribute("xmi:type").equals("uml:PrimitiveType"))
                this.java.put(elemento.getAttribute("xmi:id"), new ObjetoJava("", elemento.getAttribute("name")));
        }
    }
    
    /**
     * Metodo responsavel por adicionar os Tipos Primitivos do Java no Hash.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void getTiposPrimitivos() throws XPathExpressionException {
        this.expressao = "/XMI/Model/packagedElement[@name='PrimitiveTypes' and @type='uml:Package']";
        this.nodeList  = (NodeList) this.xPath.compile(this.expressao).evaluate(this.documento, XPathConstants.NODESET);
        if (this.nodeList.getLength() == 1)
            this.addTiposPrimitivos((Element) this.nodeList.item(0));
    }
    
    /**
     * Metodo responsavel por criar o Modelo UML.
     * @return Diagrama de Classe UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    public PacoteUML createModeloUML() throws XPathExpressionException {
        this.raiz       = new PacoteUML(null, "src");
        this.expressao  = "/XMI/Model";
             
        // Classes:
        String condicao = "/packagedElement[@type='uml:Class']";
        this.nodeList   = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element   elemento  = (Element) this.nodeList.item(i);
            ClasseUML classeUML = this.createClasseUML(elemento, this.raiz);
            this.raiz.addClasseUML(classeUML);
            this.java.put(elemento.getAttribute("xmi:id"), new ObjetoJava("", classeUML.getNome()));
        }
        
        // Interfaces:
             condicao = "/packagedElement[@type='uml:Interface']";
        this.nodeList = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element      elemento     = (Element) this.nodeList.item(i);
            InterfaceUML interfaceUML = this.createInterfaceUML(elemento, this.raiz);
            this.raiz.addInterfaceUML(interfaceUML);
            this.java.put(elemento.getAttribute("xmi:id"), new ObjetoJava("", interfaceUML.getNome()));
        }
        
        // Pacotes:
             condicao = "/packagedElement[@type='uml:Package' and @name!='java' and @name!='PrimitiveTypes']";
        this.nodeList = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element   elemento  = (Element) this.nodeList.item(i);
            this.raiz.addSubpacoteUML(this.createPacoteUML(elemento, this.raiz));
        }
        
        return this.raiz;
    }
    
    /**
     * Metodo responsavel por retornar um novo Pacote UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pai Pacote UML pai.
     * @return Novo Pacote UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private PacoteUML createPacoteUML(Element elemento, PacoteUML pai) throws XPathExpressionException {
        PacoteUML pacoteUML = new PacoteUML(pai, elemento.getAttribute("name"));
        String    condicao  = this.expressao + "//packagedElement[@type='uml:Package' and @name='" + elemento.getAttribute("name") + "']";
        
        // Classes:
        String   filtro = condicao + "/packagedElement[@type='uml:Class']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element   atual     = (Element) list.item(i);
            ClasseUML classeUML = this.createClasseUML(atual, pacoteUML);
            pacoteUML.addClasseUML(classeUML);
            this.java.put(atual.getAttribute("xmi:id"), new ObjetoJava(pacoteUML.pacote(), classeUML.getNome()));
        }
        
        // Interfaces:
        filtro = condicao + "/packagedElement[@type='uml:Interface']";
        list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element      atual        = (Element) list.item(i);
            InterfaceUML interfaceUML = this.createInterfaceUML(atual, pacoteUML);
            pacoteUML.addInterfaceUML(interfaceUML);
            this.java.put(atual.getAttribute("xmi:id"), new ObjetoJava(pacoteUML.pacote(), interfaceUML.getNome()));
        }
        
        // Pacotes:
        filtro = condicao + "/packagedElement[@type='uml:Package']";
        list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element   atual        = (Element) list.item(i);
            PacoteUML subPacoteUML = this.createPacoteUML(atual, pacoteUML);
            pacoteUML.addSubpacoteUML(subPacoteUML);
        } 
        
        return pacoteUML;
    }
    
    /**
     * Metodo responsavel por retornar uma nova Classe UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pacoteUML Pacote UML.
     * @return Nova Classe UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private ClasseUML createClasseUML(Element elemento, PacoteUML pacoteUML) throws XPathExpressionException {
        ClasseUML classeUML = new ClasseUML(elemento, pacoteUML);
        String    condicao  = "//packagedElement[@type='uml:Class' and @name='" + elemento.getAttribute("name") + "']";
            this.addEstereotipos(classeUML);
            this.addSuperClasse(classeUML, condicao);
            this.addRealizacoes(classeUML, elemento);
            this.addAtributos(classeUML, condicao);
            this.addAssociacoes(classeUML, condicao);
            this.addMetodos(classeUML, condicao);
        return classeUML;
    }
    
    /**
     * Metodo responsavel por retornar uma nova Interface UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pacoteUML Pacote UML.
     * @return Nova Interface UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private InterfaceUML createInterfaceUML(Element elemento, PacoteUML pacoteUML) throws XPathExpressionException {
        InterfaceUML interfaceUML = new InterfaceUML(elemento, pacoteUML);
        String    condicao  = "//packagedElement[@type='uml:Interface' and @name='" + elemento.getAttribute("name") + "']";
            this.addEstereotipos(interfaceUML);
            this.addSuperInterface(interfaceUML, condicao);
            this.addAtributos(interfaceUML, condicao);
            this.addAssociacoes(interfaceUML, condicao);
            this.addMetodos(interfaceUML, condicao);
        return interfaceUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces implementadas por uma Classe UML.
     * @param  classeUML Classe UML.
     * @param  element Elemento W3C do Documento.
     * @return Lista de Interfaces implementadas.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addRealizacoes(ClasseUML classeUML, Element element) throws XPathExpressionException {
        if (element.getAttribute("clientDependency").equals("")) return;
        List<String>  interfaces    = new ArrayList<>();
             String   valor         = element.getAttribute("clientDependency");
             String[] idsInterfaces = valor.trim().split(" ");
        for (String id : idsInterfaces) {
            String   condition = "//packagedElement[@type='uml:Realization' and @id='";
            NodeList list      = (NodeList) this.xPath.compile(this.expressao + condition + id.trim() + "']").evaluate(this.documento, XPathConstants.NODESET);
            if (list.getLength() == 1) {
                Element realizacao = (Element) list.item(0);
                interfaces.add(realizacao.getAttribute("supplier").trim());
            }
        }
        classeUML.setInterfaces(interfaces);
    }
    
    /**
     * Metodo responsavel por adicionar a Lista de Estereotipos da Classe UML.
     * @param classeUML Classe UML.
     */
    private void addEstereotipos(ClasseUML classeUML) {
        String valor = this.estereotipos.get(classeUML.getId());
        if (valor != null)
            classeUML.setEstereotipos(this.getEstereotipos(valor));
    }
    
    /**
     * Metodo responsavel por adicionar a Lista de Estereotipos da Interface UML.
     * @param interfaceUML Interface UML.
     */
    private void addEstereotipos(InterfaceUML interfaceUML) {
        String valor = this.estereotipos.get(interfaceUML.getId());
        if (valor != null)
           interfaceUML.setEstereotipos(this.getEstereotipos(valor));
    }
    
    /**
     * Metodo responsavel por adicionar o Id da Super Classe em uma Classe UML.
     * @param  classeUML Classe UML.
     * @param  condicao Condicao da Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addSuperClasse(ClasseUML classeUML, String condicao) throws XPathExpressionException {
        String   filtro  = this.expressao + condicao + "/generalization";
        String   idSuper = "";
        NodeList list    = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() > 0) {
            Element elemento = (Element) list.item(0);
                    idSuper  = elemento.getAttribute("general");
        }
        classeUML.setIdSuperClasse(idSuper);
    }
    
    /**
     * Metodo responsavel por adicionar o Id da Super Interface em uma Interface UML.
     * @param interfaceUML Interface UML.
     * @param condicao Condicao da Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addSuperInterface(InterfaceUML interfaceUML, String condicao) throws XPathExpressionException {
        String   filtro  = this.expressao + condicao + "/generalization";
        String   idSuper = "";
        NodeList list    = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() > 0) {
            Element elemento = (Element) list.item(0);
                    idSuper  = elemento.getAttribute("general");
        }
        interfaceUML.setIdSuper(idSuper);
    }
    
    /**
     * Metodo responsavel por adicionar os Atributos UML de uma Classe UML.
     * @param classeUML Classe UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAtributos(ClasseUML classeUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        classeUML.setAtributos(CONTROLLER_ATRIBUTO_UML.getAtributosUML(list));
    }
    
    /**
     * Metodo responsavel por adicionar os Atributos UML de uma Classe UML.
     * @param interfaceUML Interface UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAtributos(InterfaceUML interfaceUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        interfaceUML.setAtributos(CONTROLLER_ATRIBUTO_UML.getAtributosUML(list));
    }
    
    /**
     * Metodo responsavel por adicionar as Associacoes UML de uma Classe UML.
     * @param classeUML Classe UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAssociacoes(ClasseUML classeUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property'";
        NodeList list   = (NodeList) this.xPath.compile(filtro + " and @association!='']").evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element       elemento      = (Element) list.item(i);
            AssociacaoUML associacaoUML = new AssociacaoUML(elemento);
                          associacaoUML.setMinimo(this.getMinimo(associacaoUML.getId(), filtro));
                          associacaoUML.setMaximo(this.getMaximo(associacaoUML.getId(), filtro));
            classeUML.addAssociacao(associacaoUML);
        }
    }
    
    /**
     * Metodo responsavel por adicionar as Associacoes UML de uma Interface UML.
     * @param interfaceUML Interface UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAssociacoes(InterfaceUML interfaceUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property'";
        NodeList list   = (NodeList) this.xPath.compile(filtro + " and @association!='']").evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element       elemento      = (Element) list.item(i);
            AssociacaoUML associacaoUML = new AssociacaoUML(elemento);
                          associacaoUML.setMinimo(this.getMinimo(associacaoUML.getId(), filtro));
                          associacaoUML.setMaximo(this.getMaximo(associacaoUML.getId(), filtro));
            interfaceUML.addAssociacao(associacaoUML);
        }
    }
    
    /**
     * Metodo responsavel por retornar o Valor Minimo de uma Associacao UML.
     * @param  id Id da Associacao UML.
     * @param  condicao Condicao de Busca.
     * @return Valor Minimo da Associacao UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private int getMinimo(String id, String condicao) throws XPathExpressionException {
        String   filtro = condicao + " and @id='" + id + "']/lowerValue";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() == 1) {
            Element elemento = (Element) list.item(0);
            if (elemento.getAttribute("value") != null) {
                if (elemento.getAttribute("value").equals("*")
                || (elemento.getAttribute("value").equals("")))
                    return Integer.MAX_VALUE;
                else
                    return Integer.parseInt(elemento.getAttribute("value").trim());
            }
        }
        return 1;
    }
    
    /**
     * Metodo responsavel por retornar o Valor Maximo de uma Associacao UML.
     * @param  id Id da Associacao UML.
     * @param  condicao Condicao de Busca.
     * @return Valor Maximo da Associacao UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private int getMaximo(String id, String condicao) throws XPathExpressionException {
        String   filtro = condicao + " and @id='" + id + "']/upperValue";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() == 1) {
            Element elemento = (Element) list.item(0);
            if (elemento.getAttribute("value") != null) {
                if (elemento.getAttribute("value").equals("*")
                || (elemento.getAttribute("value").equals("")))
                    return Integer.MAX_VALUE;
                else
                    return Integer.parseInt(elemento.getAttribute("value").trim());
            }
        }
        return 1;
    }
    
    /**
     * Metodo responsavel por adicionar os Metodos UML da Classe UML.
     * @param  classeUML Classe UML.
     * @param  condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addMetodos(ClasseUML classeUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedOperation[@type='uml:Operation']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element   elemento  = (Element) list.item(i);
            MetodoUML metodoUML = new MetodoUML(classeUML, elemento);
            String    where     = this.expressao + condicao + "/ownedOperation[@type='uml:Operation' and @name='" + metodoUML.getNome() + "']";
            this.addParametros(metodoUML, where);
        }
    }
    
    /**
     * Metodo responsavel por adicionar os Metodos UML da Interface UML.
     * @param interfaceUML Interface UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addMetodos(InterfaceUML interfaceUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedOperation[@type='uml:Operation']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element   elemento  = (Element) list.item(i);
            MetodoUML metodoUML = new MetodoUML(interfaceUML, elemento);
            String    where     = this.expressao + condicao + "/ownedOperation[@type='uml:Operation' and @name='" + metodoUML.getNome() + "']";
            this.addParametros(metodoUML, where);
        }
    }
    
    /**
     * Metodo responsavel por adicionar os Parametros UML ao Metodo UML.
     * @param metodoUML Metodo UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addParametros(MetodoUML metodoUML, String condicao) throws XPathExpressionException {
        String   filtro = condicao + "/ownedParameter[@type='uml:Parameter']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        metodoUML.setRetorno(CONTROLLER_PARAMETRO_UML.getRetorno(list));
        metodoUML.setParametros(CONTROLLER_PARAMETRO_UML.getParametros(list));
    }
}