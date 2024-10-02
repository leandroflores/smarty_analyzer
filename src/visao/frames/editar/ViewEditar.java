package visao.frames.editar;

import javax.swing.JButton;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.consulta.ViewConsulta;

/**
 * <p>Classe de Visao <b>ViewEditar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Edicao</b>.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewEditar extends ViewModal implements Viewable {
    private final ViewConsulta viewConsulta;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsulta View Consulta.
     */
    public ViewEditar(ViewConsulta viewConsulta) {
        super(viewConsulta);
        this.viewConsulta = viewConsulta;
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("editar/" + url);
    }
    
    /**
     * Metodo Abstrato responsavel por preencher os Campos.
     */
    public abstract void setDados();
    
    /**
     * Metodo responsavel por Limpar as Bordas dos Componentes da View Cadastro.
     */
    public abstract void clearBorder();
    
    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton("  Gravar  ", "save.jpg");
        this.buttonAction2 = this.createButton(" Cancelar ", "exit.jpg");
        
        this.add(this.buttonAction1);
        this.add(this.buttonAction2);
    }
    
    /**
     * Metodo responsavel por retornar o Botao Salvar da View Editar.
     * @return Botao Salvar da View Editar.
     */
    public JButton getButtonSave() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Cancelar da View Editar.
     * @return Botao Cancelar da View Editar.
     */
    public JButton getButtonCancel() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar a View Consulta da View Editar.
     * @return View Consulta da View Editar.
     */
    public ViewConsulta getViewConsulta() {
        return this.viewConsulta;
    }
}