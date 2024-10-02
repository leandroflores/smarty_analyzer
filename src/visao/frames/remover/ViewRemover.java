package visao.frames.remover;

import javax.swing.JButton;
import javax.swing.JLabel;
import utils.FunctFrame;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.consulta.ViewConsulta;

/**
 * <p>Classe de Visao <b>ViewRemover</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Remocao</b>.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewRemover extends ViewModal implements Viewable {
    private final ViewConsulta viewConsulta;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsulta View de Consulta.
     */
    public ViewRemover(ViewConsulta viewConsulta) {
        super(viewConsulta);
        this.viewConsulta = viewConsulta;
        this.setSettings();
    }
    
    /**
     * Metodo responsavel por definir um Tamanho Padrao.
     */
    private void setSettings() {
        this.setSize(450, 150);
    }

    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(new FunctFrame().getLabelImage("buttons/erro.jpg"));
    }

    /**
     * Metodo responsavel por adicionar a Mensagem a ser apresenta na View Remover.
     * @param message Mensagem a ser apresenta na View Remover.
     */
    protected void addComponents(String message) {
        this.add(new JLabel("Confirma exclusão: " +  message + "?"));
        this.addLinhas(1);
    }

    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton("  Sim  ", "yes.jpg");
        this.buttonAction2 = this.createButton("  Nao  ", "no.jpg");
        
        this.add(this.buttonAction1);
        this.add(this.buttonAction2);
    }
    
    /**
     * Metodo responsavel por retornar o Botao Sim da View Remover.
     * @return Botao Sim da View Remover.
     */
    public JButton getButtonSim() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Nao da View Remover.
     * @return Botao Nao da View Remover.
     */
    public JButton getButtonNao() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar a View Consulta da View Remover.
     * @return View Consulta da View Remover.
     */
    public ViewConsulta getViewConsulta() {
        return this.viewConsulta;
    }
}