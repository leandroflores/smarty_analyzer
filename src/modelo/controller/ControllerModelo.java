package modelo.controller;

/**
 * <p>Classe de Controle <b>ControllerModel</b>.</p>
 * <p>Classe responsavel por definir as Principais Validacoes para as Classes Controladoras do Modelo.</p>
 * @author Leandro
 * @since  29/09/2016
 * @see    java.lang.String
 */
public class ControllerModelo {
    protected static final String CARACTERES_OBRIGATORIO = "\\D+";
    protected static final String CARACTERES_OPCIONAL    = "\\D*";
    protected static final String LETRAS_OBRIGATORIO     = "[A-Za-z _-]+";
    protected static final String LETRAS_OPCIONAL        = "[A-Za-z _-]*";
    protected static final String PALAVRA_OBRIGATORIO    = "\\w+";
    protected static final String PALAVRA_OPCIONAL       = "\\w*";
    protected static final String NUMERO_OBRIGATORIO     = "\\d+";
    protected static final String NUMERO_OPCIONAL        = "\\d*";
    protected static final String VALOR                  = "\\d+.\\d+";
    protected static final String NAO_VAZIO              = "\\S";
    protected static final String DOCUMENTO_CPF          = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
    protected static final String DOCUMENTO_CNPJ         = "\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}";
    
    /**
     * Metodo responsavel por retornar se uma String contem apenas Numeros.
     * @param  string String a ser Avaliada.
     * @param  empty Flag para indicar se a String pode ser Vazia (true - Aceita String Vazia).
     * @return String contem apenas Numeros.
     */
    public boolean checkNumeros(String string, boolean empty) {
        return (empty) ? this.check(NUMERO_OPCIONAL, string) : this.check(NUMERO_OBRIGATORIO, string);
    }
    
    /**
     * Metodo responsavel por retornar se uma String contem apenas Letras.
     * @param  string String a ser Avaliada.
     * @param  empty Flag para indicar se a String pode ser Vazia (true - Aceita String Vazia).
     * @return String contem apenas Letras.
     */
    public boolean checkLetras(String string, boolean empty) {
        return (empty) ? this.check(LETRAS_OPCIONAL, string) : this.check(LETRAS_OBRIGATORIO, string);
    }
    
    /**
     * Metodo responsavel por retornar se uma String corresponde a apenas uma Palavra.
     * @param  string String a ser Avaliada.
     * @param  empty Flag para indicar se a String pode ser Vazia (true - Aceita String Vazia).
     * @return String correspone a apenas uma Palavra.
     */
    public boolean checkPalavra(String string, boolean empty) {
        return (empty) ? this.check(PALAVRA_OPCIONAL, string) : this.check(PALAVRA_OBRIGATORIO, string);
    }
    
    /**
     * Metodo responsavel por retornar se uma String e nao-vazia.
     * @param  string String a ser avaliada.
     * @return String e nao-vazia.
     */
    public boolean noEmpty(String string) {
        return string.isEmpty() == false;
    }
    
    /**
     * Metodo responsavel por retornar se a String e aprovada pela Regex.
     * @param  regex Regex definida.
     * @param  string String a ser avaliada.
     * @return String e Aprovada pela Regex.
     */
    protected boolean check(String regex, String string) {
        return string.matches(regex);
    }
}