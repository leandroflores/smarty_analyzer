package arquivo.exportacao.diagramas;

import arquivo.importacao.diagramas.LeitorDiagramaClasse;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.PacoteUML;
import org.xml.sax.SAXException;

/**
 * <p>Classe de Exportacao <b>ExportarDiagramaClasse</b>.</p>
 * <p>Classe responsavel por <b>Exportar</b> os dados de um <b>Diagrama de Classe</b>.</p>
 * @author Leandro
 * @since  21/03/2017
 * @see    arquivo.exportacao.diagramas.ExportarClasse
 * @see    arquivo.exportacao.diagramas.ExportarInterface
 * @see    modelo.uml.diagrama.DiagramaClasse
 */
public class ExportarDiagramaClasse {
    private final String           caminho;
    private final DiagramaClasse diagramaDeClasse;
    private File file;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Arquivo de Exportacao.
     * @param diagramaDeClasse Diagrama de Classe a ser exportado.
     */
    public ExportarDiagramaClasse(String caminho, DiagramaClasse diagramaDeClasse) {
        this.caminho          = caminho;
        this.diagramaDeClasse = diagramaDeClasse;
    }
    
    /**
     * Metodo responsavel por exportar o Diagrama de Classe.
     * @throws IOException Erro na Manipulacao dos Arquivos.
     */
    public void exportar() throws IOException {
        this.criarDiretorioRaiz();
        this.exportarPacote(diagramaDeClasse.getRaiz());
    }
    
    /**
     * Metodo responsavel por criar o Diretor Raiz do Diagrama de Classe.
     * @throws IOException Erro na Criacao do Diretorio.
     */
    private void criarDiretorioRaiz() throws IOException {
        this.file = new File(this.caminho + "\\src");
        this.file.mkdir();
    }
    
    /**
     * Metodo responsavel por exportar os dados do Pacote UML.
     * @param  pacoteUML Pacote UML.
     * @throws IOException Erro na Criacao do Pacote UML.
     */
    private void exportarPacote(PacoteUML pacoteUML) throws IOException {
        String novoCaminho = this.caminho + pacoteUML.pacote().replace(".", "\\");
        
        this.file = new File(novoCaminho);
        this.file.mkdir();
        
        // Pacotes:
        for (PacoteUML pacote : pacoteUML.getSubPacotes())
            this.exportarPacote(pacote);
        
        // Classes:
        for (ClasseUML classeUML : pacoteUML.getClasses())
            new ExportarClasse(novoCaminho, classeUML).exportar();
        
        // Interfaces:
        for (InterfaceUML interfaceUML : pacoteUML.getInterfaces())
            new ExportarInterface(novoCaminho, interfaceUML).exportar();
    }
    
    /**
     * Metodo principal da Aplicacao.
     * @param args
     * @throws XPathExpressionException
     * @throws IOException 
     * @throws javax.xml.parsers.ParserConfigurationException 
     * @throws org.xml.sax.SAXException 
     */
    public static void main(String[] args) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        LeitorDiagramaClasse leitor           = new LeitorDiagramaClasse("src/astah/exemplos/classes/atributos/Exemplo_4.xmi");
//        DiagramaClasse       diagramaClasse   = leitor.getDiagramaClasse();
//        System.out.println(diagramaClasse.getRaiz().getSubPacotes().get(1).getInterfaces().get(0).getAtributos());
        
//        MetricaPacoteUML     metricaPacoteUML = new MetricaPacoteUML(diagramaClasse);
//        MetricaClasseUML     metricaClasseUML = new MetricaClasseUML(diagramaClasse);
//        
        ExportarDiagramaClasse exportar = new ExportarDiagramaClasse("C:\\Users\\Milena\\Desktop\\", leitor.getDiagramaClasse());
                               exportar.exportar();
    }
}