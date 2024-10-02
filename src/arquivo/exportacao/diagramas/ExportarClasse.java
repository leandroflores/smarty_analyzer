package arquivo.exportacao.diagramas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modelo.uml.diagrama.classes.ClasseUML;

/**
 * <p>Classe de Exportacao <b>ExportarClasse</b>.</p>
 * <p>Classe responsavel por <b>Exportar</b> os dados de uma <b>Classe</b>.</p>
 * @author Leandro
 * @since  21/03/2017
 * @see    modelo.uml.diagrama.classes.ClasseUML
 */
public class ExportarClasse {
    private final String      caminho;
    private final ClasseUML   classeUML;
    private       FileWriter  fileWriter;
    private       PrintWriter printWriter;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Arquivo de Exportacao.
     * @param classeUML Classe UML.
     */
    public ExportarClasse(String caminho, ClasseUML classeUML) {
        this.caminho   = caminho;
        this.classeUML = classeUML;
    }
    
    /**
     * Metodo responsavel por realizar a Exportacao da Classe.
     * @throws IOException Erro na Manipulacao do Arquivo.
     */
    public void exportar() throws IOException {
        this.criarArquivo();
        this.exportarClasse();
        this.fecharArquivo();
    }
    
    /**
     * Metodo responsavel por criar o Arquivo de Exportacao.
     * @throws IOException Erro na Criacao do Arquivo.
     */
    private void criarArquivo() throws IOException {
        this.fileWriter  = new FileWriter(this.caminho + "\\" + this.classeUML.getNome() + ".java");
        this.printWriter = new PrintWriter(this.fileWriter);
    }
    
    /**
     * Metodo responsavel por exportar os Dados da Classe.
     */
    private void exportarClasse() {
        this.printWriter.println(this.classeUML.print());
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