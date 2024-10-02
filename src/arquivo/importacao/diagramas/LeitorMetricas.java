package arquivo.importacao.diagramas;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Classe de Leitura <b>LeitorMetricas</b>.</p>
 * <p>Classe responsavel por <b>Importar Metricas</b> de um Arquivo Texto.</p>
 * @author Leandro
 * @since  09/09/2016
 */
public class LeitorMetricas {
    private final String   path;
    private final XPath    xPath;
    private       File     file;
    private       Document document;
    private       NodeList nodeList;
    private       int      total;
    private static final DaoMetrica DAO_METRICA = new DaoMetrica();
    private static final String     EXPRESSION  = "metricas/metrica";
    private static final String[]   TAGS        = {"nome", "rotulo", "descricao", "diagrama-alvo", "tipo", "unidade-medida", "operacao"};

    
    /**
     * Metodo construtor padrao da Classe.
     * @param path Caminho do Arquivo.
     */
    public LeitorMetricas(String path) {
        this.path  = path;
        this.xPath = XPathFactory.newInstance().newXPath();
    }
    
    /**
     * Metodo responsavel por Abir o Arquivo para Leitura.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    private void openFile() throws ParserConfigurationException, SAXException, IOException {
        this.file     = new File(this.path);
        this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.file);
        this.document.getDocumentElement().normalize();
    }
    
    /**
     * Metodo responsavel por ler o conteudo do arquivo.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException 
     */
    public void read() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.openFile();
        this.nodeList = (NodeList) this.xPath.compile(EXPRESSION).evaluate(this.document, XPathConstants.NODESET);
        this.readMetricas();
    }
    
    /**
     * Metodo responsavel por ler as Metricas do arquivo.
     */
    private void readMetricas() {
        this.total = 0;
        if (this.nodeList != null) {
            for (int i = 0; i < this.nodeList.getLength(); ++i) {
                if (this.nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    this.insertMetrica((Element) this.nodeList.item(i));
                }
            }
        }
    }
    
    /**
     * Metodo responsavel por Inserir a Metrica lido do Arquivo no Banco de Dados.
     * @param element Elemento lido do Arquivo.
     */
    private void insertMetrica(Element element) {
        if (element != null) {
            Metrica metrica = new Metrica();
                    metrica.setId(DAO_METRICA.nextId());
                    metrica.setNome(element.getElementsByTagName(TAGS[0]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setRotulo(element.getElementsByTagName(TAGS[1]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setDescricao(element.getElementsByTagName(TAGS[2]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setDiagramaAlvo(element.getElementsByTagName(TAGS[3]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setTipo(element.getElementsByTagName(TAGS[4]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setUnidadeMedida(element.getElementsByTagName(TAGS[5]).item(0).getTextContent().toUpperCase().trim());
                    metrica.setOperacao(element.getElementsByTagName(TAGS[6]).item(0).getTextContent().toUpperCase().trim());
            DAO_METRICA.insert(metrica);
            this.total += 1;
        }
    }
    
    /**
     * Metodo responsavel por retornar o Total de Registros Inseridos.
     * @return Total de Registros Inseridos.
     */
    public int getTotal() {
        return this.total;
    }
}