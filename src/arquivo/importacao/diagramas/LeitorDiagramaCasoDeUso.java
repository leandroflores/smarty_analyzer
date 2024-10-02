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
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.usecases.AtorUML;
import modelo.uml.diagrama.usecases.CasoDeUsoUML;
import modelo.uml.diagrama.usecases.PastaUML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Classe de Leitura <b>LeitorDiagramaCasoDeUso</b>.</p>
 * <p>Classe responsavel por ser o <b>Leitor do Documento XMI</b> para os Diagramas de Caso de Uso.</p>
 * <p>Classe realiza a leitura do <b>Arquivo XMI</b> e constroi o Diagrama de Caso de Uso.</p>
 * @author Leandro
 * @since  08/05/2017
 * @see    modelo.uml.diagrama.DiagramaCasoDeUso
 */
public class LeitorDiagramaCasoDeUso {
    private final String  caminho;
    private final XPath   xPath;
    
    private File      arquivo;
    private Document  documento;
    private PastaUML  raiz;
    private NodeList  nodeList;
    private String    expressao;
    
    private DiagramaCasoDeUso diagramaCasoDeUso;
    
    private HashMap<String, String> estereotipos;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Documento.
     */
    public LeitorDiagramaCasoDeUso(String caminho) {
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
    public DiagramaCasoDeUso getDiagramaCasoDeUso() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.abrirArquivo();
        
        this.getEstereotipos();
        
               this.diagramaCasoDeUso = new DiagramaCasoDeUso(this.createModeloUML());
//        System.out.println(this.estereotipos);
        return this.diagramaCasoDeUso;
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
        this.estereotipos = new HashMap<>();
    }
    
    /**
     * Metodo responsavel por adicionar os Estereotipos do Diagrama de Classes.
     * @throws XPathExpressionException 
     */
    private void getEstereotipos() throws XPathExpressionException {
        this.expressao = "/XMI/Model/ownedComment[@type='uml:Comment']";
        this.nodeList  = (NodeList) this.xPath.compile(this.expressao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            this.addEstereotipo((Element) this.nodeList.item(i));
        }
    }
    
    /**
     * Metodo responsavel por adicionar os Estereotipo na Lista de Esteretipo.
     * @param elemento Elemento W3C do Documento.
     */
    private void addEstereotipo(Element elemento) throws XPathExpressionException {
        String   objeto      = elemento.getAttribute("annotatedElement").trim();
        String[] idsobjeto   = objeto.split(" ");
        String   id          = elemento.getAttribute("xmi:id").trim();
        String   filtro      = "/XMI/Model/ownedComment[@type='uml:Comment' and @id='" + id + "']/body/text()";
        String   estereotipo = (String) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.STRING);
                 estereotipo = estereotipo.replaceAll(",", ">><<");
        String   valor       = this.estereotipos.get(objeto);
        if (valor != null) {
            valor += estereotipo;
            this.addEstereotipo(idsobjeto, valor);
        }else {
            this.addEstereotipo(idsobjeto, estereotipo);
        }
    }
    
    /**
     * Metodo responsavel por adicionar os Estereotipos de uma Lista de Ids.
     * @param ids Lista de Ids.
     * @param valor Valor do Estereotipo.
     */
    private void addEstereotipo(String[] ids, String valor) {
        for (String id : ids)
            this.estereotipos.put(id.trim(), valor);
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
                     valor = valor.replaceAll(",", "><");
        
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
     * Metodo responsavel por criar o Modelo UML.
     * @return Diagrama de Classe UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    public PastaUML createModeloUML() throws XPathExpressionException {
        this.raiz       = new PastaUML(null, "src");
        this.expressao  = "/XMI/Model";
             
        // Atores:
        String condicao = "/packagedElement[@type='uml:Class']";
        this.nodeList   = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element      elemento   = (Element) this.nodeList.item(i);
            String       idElemento = elemento.getAttribute("xmi:id");
            String       valor      = (this.estereotipos.get(idElemento) != null) ?  this.estereotipos.get(idElemento) : "";
            List<String> list       = this.getEstereotipos(valor);
            if (list.contains("actor") || list.contains("ator")) {
                AtorUML atorUML = this.createAtorUML(elemento, this.raiz);
                        atorUML.setEstereotipos(list);
                this.raiz.addAtor(atorUML);
            }
        }
        
        // Casos de Uso:
             condicao = "/packagedElement[@type='uml:Class']";
        this.nodeList = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element      elemento   = (Element) this.nodeList.item(i);
            String       idElemento = elemento.getAttribute("xmi:id");
            String       valor      = (this.estereotipos.get(idElemento) != null) ?  this.estereotipos.get(idElemento) : "";
            List<String> list       = this.getEstereotipos(valor);
            if (!list.contains("actor") && !list.contains("ator")) {
                CasoDeUsoUML casoDeUsoUML = this.createCasoDeUsoUML(elemento, this.raiz);
                             casoDeUsoUML.setEstereotipos(list);
                this.raiz.addCasoDeUso(casoDeUsoUML);
            }
        }
        
        // Pacotes:
             condicao = "/packagedElement[@type='uml:Package' and @name!='java' and @name!='PrimitiveTypes']";
        this.nodeList = (NodeList) this.xPath.compile(this.expressao + condicao).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element   elemento  = (Element) this.nodeList.item(i);
            this.raiz.addFilho(this.createPastaUML(elemento, this.raiz));
        }
        
        return this.raiz;
    }
    
    /**
     * Metodo responsavel por retornar uma nova Pasta UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pai Pasta UML pai.
     * @return Nova Pasta UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private PastaUML createPastaUML(Element elemento, PastaUML pai) throws XPathExpressionException {
        PastaUML pastaUML = new PastaUML(pai, elemento.getAttribute("name"));
        String   condicao = this.expressao + "//packagedElement[@type='uml:Package' and @name='" + elemento.getAttribute("name") + "']";
        
        // Atores:
        String   filtro = condicao + "/packagedElement[@type='uml:Class']";
        NodeList list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element      atual            = (Element) list.item(i);
            String       idElemento       = atual.getAttribute("xmi:id");
            String       valor            = (this.estereotipos.get(idElemento) != null) ?  this.estereotipos.get(idElemento) : "";
            List<String> listEstereotipos = this.getEstereotipos(valor);
            if (listEstereotipos.contains("actor") || listEstereotipos.contains("ator")) {
                AtorUML atorUML = this.createAtorUML(elemento, pastaUML);
                        atorUML.setEstereotipos(listEstereotipos);
                pastaUML.addAtor(atorUML);
            }
        }
        
        // Casos de Uso:
        filtro = condicao + "/packagedElement[@type='uml:Class']";
        list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element      atual            = (Element) list.item(i);
            String       idElemento       = elemento.getAttribute("xmi:id");
            String       valor            = (this.estereotipos.get(idElemento) != null) ?  this.estereotipos.get(idElemento) : "";
            List<String> listEstereotipos = this.getEstereotipos(valor);
            if (!listEstereotipos.contains("actor") && !listEstereotipos.contains("ator")) {
                CasoDeUsoUML casoDeUsoUML = this.createCasoDeUsoUML(elemento, pastaUML);
                             casoDeUsoUML.setEstereotipos(listEstereotipos);
                pastaUML.addCasoDeUso(casoDeUsoUML);
            }
        }
        
        // Pastas:
        filtro = condicao + "/packagedElement[@type='uml:Package']";
        list   = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element  atual       = (Element) list.item(i);
            PastaUML subPastaUML = this.createPastaUML(atual, pastaUML);
            pastaUML.addFilho(subPastaUML);
        }
        
        return pastaUML;
    }
    
    /**
     * Metodo responsavel por retornar um novo Ator UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pastaUML Pasta UML.
     * @return Novo Ator UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private AtorUML createAtorUML(Element elemento, PastaUML pastaUML) throws XPathExpressionException {
        AtorUML atorUML  = new AtorUML(elemento, pastaUML);
        String  condicao = "//packagedElement[@type='uml:Class' and @name='" + elemento.getAttribute("name") + "']";
//            this.addEstereotipos(atorUML);
            this.addSuperClasse(atorUML, condicao);
//            this.addRealizacoes(atorUML, elemento);
//            this.addAtributos(atorUML, condicao);
//            this.addAssociacoes(atorUML, condicao);
//            this.addMetodos(atorUML, condicao);
        return atorUML;
    }
    
    /**
     * Metodo responsavel por retornar um novo Caso de Uso UML.
     * @param  elemento Elemento W3C do Documento.
     * @param  pastaUML Pasta UML.
     * @return Novo Caso de Uso UML.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private CasoDeUsoUML createCasoDeUsoUML(Element elemento, PastaUML pastaUML) throws XPathExpressionException {
        CasoDeUsoUML casoDeUsoUML = new CasoDeUsoUML(elemento, pastaUML);
        String    condicao  = "//packagedElement[@type='uml:Interface' and @name='" + elemento.getAttribute("name") + "']";
//            this.addEstereotipos(casoDeUsoUML);
            this.addSuperClasse(casoDeUsoUML, condicao);
//            this.addAtributos(casoDeUsoUML, condicao);
//            this.addAssociacoes(casoDeUsoUML, condicao);
//            this.addMetodos(casoDeUsoUML, condicao);
        return casoDeUsoUML;
    }
        
    /**
     * Metodo responsavel por adicionar o Id da Super Classe de um Ator UML.
     * @param  atorUML Ator UML.
     * @param  condicao Condicao da Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addSuperClasse(AtorUML atorUML, String condicao) throws XPathExpressionException {
        String   filtro  = this.expressao + condicao + "/generalization";
        String   idSuper = "";
        NodeList list    = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() > 0) {
            Element elemento = (Element) list.item(0);
                    idSuper  = elemento.getAttribute("general");
        }
        atorUML.setIdSuper(idSuper);
    }
    
    /**
     * Metodo responsavel por adicionar o Id da Super Classe em um Caso de Uso UML.
     * @param casoDeUsoUML Caso de Uso UML.
     * @param condicao Condicao da Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addSuperClasse(CasoDeUsoUML casoDeUsoUML, String condicao) throws XPathExpressionException {
        String   filtro  = this.expressao + condicao + "/generalization";
        String   idSuper = "";
        NodeList list    = (NodeList) this.xPath.compile(filtro).evaluate(this.documento, XPathConstants.NODESET);
        if (list.getLength() > 0) {
            Element elemento = (Element) list.item(0);
                    idSuper  = elemento.getAttribute("general");
        }
        casoDeUsoUML.setIdSuper(idSuper);
    }
    
    /**
     * Metodo responsavel por adicionar as Associacoes UML de um Ator UML.
     * @param atorUML Ator UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAssociacoes(AtorUML atorUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property'";
        NodeList list   = (NodeList) this.xPath.compile(filtro + " and @association!='']").evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element       elemento      = (Element) list.item(i);
//            AssociacaoUML associacaoUML = new AssociacaoUML(elemento);
//                          associacaoUML.setMinimo(this.getMinimo(associacaoUML.getId(), filtro));
//                          associacaoUML.setMaximo(this.getMaximo(associacaoUML.getId(), filtro));
//            atorUML.addAssociacao(associacaoUML);
        }
    }
    
    /**
     * Metodo responsavel por adicionar as Associacoes UML de um Caso de Uso UML.
     * @param casoDeUsoUML Caso de Uso UML.
     * @param condicao Condicao de Busca.
     * @throws XPathExpressionException Erro na Expressao XPath.
     */
    private void addAssociacoes(CasoDeUsoUML casoDeUsoUML, String condicao) throws XPathExpressionException {
        String   filtro = this.expressao + condicao + "/ownedAttribute[@type='uml:Property'";
        NodeList list   = (NodeList) this.xPath.compile(filtro + " and @association!='']").evaluate(this.documento, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Element       elemento      = (Element) list.item(i);
//            AssociacaoUML associacaoUML = new AssociacaoUML(elemento);
//                          associacaoUML.setMinimo(this.getMinimo(associacaoUML.getId(), filtro));
//                          associacaoUML.setMaximo(this.getMaximo(associacaoUML.getId(), filtro));
//            casoDeUsoUML.addAssociacao(associacaoUML);
        }
    }
}