package modelo.uml.diagrama.classes.java;

/**
 * <p>Classe de Modelo <b>ObjetoJava</b>.</p>
 * <p>Classe responsavel por representar um <b>Objeto Java</b> no Sistema.</p>
 * @author Leandro
 * @since  15/03/2017
 */
public class ObjetoJava {
    private final String pacote;
    private final String nome;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param pacote Pacote do Objeto Java.
     * @param nome Nome do Objeto Java.
     */
    public ObjetoJava(String pacote, String nome) {
        this.pacote = pacote;
        this.nome   = nome;
    }
    
    /**
     * Metodo responsavel por retornar o Pacote do Objeto Java.
     * @return Pacote do Objeto Java.
     */
    public String getPacote() {
        return this.pacote;
    }
    
    /**
     * Metodo responsavel por retornar o Nome do Objeto Java.
     * @return Nome do Objeto Java.
     */
    public String getNome() {
        return this.nome;
    }
    
    @Override
    public String toString() {
        if (this.pacote.equals(""))
            return this.nome;
        return this.pacote + "." + this.nome;
    }
}