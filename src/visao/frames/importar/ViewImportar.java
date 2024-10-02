package visao.frames.importar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewImportar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Importacao</b>.</p>
 * @author Leandro
 * @since  24/05/2017
 * @see    visao.frames.ViewModal
 * @see    visao.frames.Viewable
 */
public abstract class ViewImportar extends ViewModal implements Viewable {
    private JTextField   textFieldArquivo;
    private JFileChooser fileChooser;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewImportar(ViewMenu viewMenu) {
        super(viewMenu);
    }
    
    @Override
    protected void addHeader(String url) {
        super.addHeader("importar/" + url);
    }
    
    /**
     * Metodo responsavel por adicionar o Campo de Busca do arquivo txt.
     */
    protected void addSearchTextField() {
        this.textFieldArquivo = this.createTextField("", 30);
        this.textFieldArquivo.setEditable(false);
        
        this.fileChooser = new JFileChooser();
        this.fileChooser.setLocation(50, 50);
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.buttonAction1 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldArquivo);
        this.add(this.buttonAction1);
    }
    
    @Override
    public void addButtons() {
        this.buttonAction2 = this.createButton(" Importar ", "import.jpg");
        this.buttonAction3 = this.createButton(" Cancelar ", "exit.jpg");
       
        this.add(this.buttonAction2);
        this.add(this.buttonAction3);
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Arquivo.
     * @return Text Field Arquivo.
     */
    public JTextField getTextFieldArquivo() {
        return this.textFieldArquivo;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser.
     * @return File Chooser.
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Busca.
     * @return Botao de Busca.
     */
    public JButton getButtonSearch() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Importacao.
     * @return Botao de Importacao.
     */
    public JButton getButtonImportar() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Cancelar.
     * @return Botao Cancelar.
     */
    public JButton getButtonCancelar() {
        return this.buttonAction3;
    }
}