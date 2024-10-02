package arquivo.exportacao.diagramas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import modelo.entidades.Metrica;

/**
 * <p>Classe de Exportacao <b>ExportarMetrica</b>.</p>
 * <p>Classe responsavel por <b>Exportar</b> os dados de uma <b>Metrica</b>.</p>
 * @author Leandro
 * @since  21/03/2017
 * @see    modelo.entidades.Metrica
 */
public class ExportarMetricas {
    private final String        caminho;
    private final List<Metrica> metricas;
    private       FileWriter    fileWriter;
    private       PrintWriter   printWriter;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param caminho Caminho do Arquivo de Exportacao.
     * @param metricas Classe UML.
     */
    public ExportarMetricas(String caminho, List<Metrica> metricas) {
        this.caminho  = caminho;
        this.metricas = metricas;
    }
    
    /**
     * Metodo responsavel por realizar a Exportacao da Classe.
     * @throws IOException Erro na Manipulacao do Arquivo.
     */
    public void exportar() throws IOException {
        this.criarArquivo();
        this.exportarMetricas();
        this.fecharArquivo();
    }
    
    /**
     * Metodo responsavel por criar o Arquivo de Exportacao.
     * @throws IOException Erro na Criacao do Arquivo.
     */
    private void criarArquivo() throws IOException {
        this.fileWriter  = new FileWriter(this.caminho);
        this.printWriter = new PrintWriter(this.fileWriter);
    }
    
    /**
     * Metodo responsavel por exportar os Dados das Metricas.
     */
    private void exportarMetricas() {
        if (this.metricas.isEmpty() == false) {
            this.printWriter.println("<metricas>");
            for (int i = 0; i < this.metricas.size(); i++) {
                this.printWriter.println(this.metricas.get(i).print());
            }
            this.printWriter.println("</metricas>");
        }
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