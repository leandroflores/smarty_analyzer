package arquivo.exportacao.diagramas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modelo.uml.diagrama.classes.InterfaceUML;

/**
 * <p>Classe de Exportacao <b>ExportarInterface</b>.</p>
 * <p>Classe responsavel por <b>Exportar</b> os dados de uma <b>Interface</b>.</p>
 * @author Leandro
 * @since  21/03/2017
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 */
public class ExportarInterface {
    private final String       caminho;
    private final InterfaceUML interfaceUML;
    private       FileWriter   fileWriter;
    private       PrintWriter  printWriter;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Arquivo de Exportacao.
     * @param interfaceUML Interface UML.
     */
    public ExportarInterface(String caminho, InterfaceUML interfaceUML) {
        this.caminho      = caminho;
        this.interfaceUML = interfaceUML;
    }
    
    /**
     * Metodo responsavel por realizar a Exportacao da Interface.
     * @throws IOException Erro na Manipulacao do Arquivo.
     */
    public void exportar() throws IOException {
        this.criarArquivo();
        this.exportarInterface();
        this.fecharArquivo();
    }
    
    /**
     * Metodo responsavel por criar o Arquivo de Exportacao.
     * @throws IOException Erro na Criacao do Arquivo.
     */
    private void criarArquivo() throws IOException {
        this.fileWriter  = new FileWriter(this.caminho + "\\" + this.interfaceUML.getNome() + ".java");
        this.printWriter = new PrintWriter(this.fileWriter);
    }
    
    /**
     * Metodo responsavel por exportar os Dados da Interface.
     */
    private void exportarInterface() {
        this.printWriter.println(this.interfaceUML.print());
    }
    
    /**
     * Metodo responsavel por fechar o Arquivo de Exportacao.
     * @throws IOException Erro no Fechamento do Arquivo.
     */
    private void fecharArquivo() throws IOException {
        this.printWriter.close();
        this.fileWriter.close();
    }
}