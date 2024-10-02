package visao.frames.exportar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewExportar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Exportacao</b>.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    visao.frames.ViewModal
 * @see    visao.frames.Viewable
 */
public abstract class ViewExportar extends ViewModal implements Viewable {
    private JTextField   textFieldModelo;
    private JFileChooser fileChooser;
    
    private JTextField   textFieldDiretorio;
    private JFileChooser diretorioChooser;
    protected JTextField   textFieldArquivo;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewExportar(ViewMenu viewMenu) {
        super(viewMenu);
    }
    
    @Override
    protected void addHeader(String url) {
        super.addHeader("exportar/" + url);
    }
    
    /**
     * Metodo responsavel por adicionar o Campo de Busca do arquivo txt.
     */
    protected void addSearchModelo() {
        this.textFieldModelo = this.createTextField("", 30);
        this.textFieldModelo.setEditable(false);
        
        this.fileChooser = new JFileChooser();
        this.fileChooser.setLocation(50, 50);
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.buttonAction1 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldArquivo);
        this.add(this.buttonAction1);
    }
    
    /**
     * Metodo responsavel por adicionar o Diretorio de Exportacao.
     */
    protected void addBuscaDiretorio() {
        this.textFieldDiretorio = this.createTextField("", 30);
        this.textFieldDiretorio.setEditable(false);
        
        this.diretorioChooser = new JFileChooser();
        this.diretorioChooser.setLocation(50, 50);
        this.diretorioChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        this.buttonAction2 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldDiretorio);
        this.add(this.buttonAction2);
    }
    
    /**
     * Metodo responsavel por adicionar o Text Field do Arquivo.
     */
    protected void addTextFieldArquivo() {
        this.textFieldArquivo = this.createTextField("", 30);
        this.add(this.textFieldArquivo);
    }
    
    @Override
    public void addButtons() {
        this.buttonAction3 = this.createButton(" Exportar ", "export.jpg");
        this.buttonAction4 = this.createButton(" Cancelar ", "exit.jpg");
       
        this.add(this.buttonAction3);
        this.add(this.buttonAction4);
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Arquivo.
     * @return Text Field Arquivo.
     */
    public JTextField getTextFieldArquivo() {
        return this.textFieldArquivo;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Modelo.
     * @return Text Field Modelo.
     */
    public JTextField getTextFieldModelo() {
        return this.textFieldModelo;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser.
     * @return File Chooser.
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Busca do Arquivo.
     * @return Botao de Busca do Arquivo.
     */
    public JButton getButtonSearchArquivo() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Diretorio.
     * @return Text Field Diretorio.
     */
    public JTextField getTextFieldDiretorio() {
        return this.textFieldDiretorio;
    }
    
    /**
     * Metodo responsavel por retornar o Diretorio Chooser.
     * @return Diretorio Chooser.
     */
    public JFileChooser getDiretorioChooser() {
        return this.diretorioChooser;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Busca do Diretorio.
     * @return Botao de Busca do Diretorio.
     */
    public JButton getButtonSearchDiretorio() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Exportacao.
     * @return Botao de Exportacao.
     */
    public JButton getButtonExportar() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Cancelar.
     * @return Botao Cancelar.
     */
    public JButton getButtonCancelar() {
        return this.buttonAction4;
    }
}