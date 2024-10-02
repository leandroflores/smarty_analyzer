package visao.frames;

/**
 * <p>Interface <b>Viewable</b>.</p>
 * <p>Interface responsavel por definir as principais operacoes para as <b>Views</b> do Projeto.</p>
 * @author Leandro
 * @since  06/10/2015
 */
public interface Viewable {
    
    /**
     * Metodo responsavel por carregar os elementos da View.
     */
    public void initComponents();
     
    /**
     * Metodo responsavel por adicionar o Cabecalho de uma View.
     */
    public void addHeader();
    
    /**
     * Metodo responsavel por adicionar os Componentes de uma View.
     */
    public void addComponents();
    
    /**
     * Metodo responsavel por adicionar os Botoes de uma View.
     */
    public void addButtons();
}