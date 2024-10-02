package visao.frames.mensagem;

import javax.swing.JButton;
import javax.swing.JLabel;
import utils.FunctFrame;
import visao.controller.frames.mensagem.ControllerViewInformacao;
import visao.frames.View;
import visao.frames.ViewModal;
import visao.frames.Viewable;

/**
 * <p>Classe de Visao <b>ViewMensagem</b>.</p>
 * <p>Classe responsavel por definir a Interface de Mensagem do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public final class ViewInformacao extends ViewModal implements Viewable {
    private final String mensagem;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param view View Parent.
     * @param mensagem Mensagem de Erro.
     */
    public ViewInformacao(View view, String mensagem) {
        super(view);
        this.mensagem   = mensagem;
        this.controller = new ControllerViewInformacao(this);
        this.initComponents();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param viewModal View Parent.
     * @param mensagem Mensagem de Erro.
     */
    public ViewInformacao(ViewModal viewModal, String mensagem) {
        super(viewModal);
        this.mensagem   = mensagem;
        this.controller = new ControllerViewInformacao(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Informacao");
        this.setSize(450, 150);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(new JLabel(new FunctFrame().createImage("mensagem/info.jpg")));
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel(this.mensagem));
    }

    @Override
    public void addButtons() {
        this.addLinhas(1);
        this.buttonAction1 = this.createButton("   Ok   ", "ok.jpg");
        this.add(this.buttonAction1);
    }
    
    /**
     * Metodo responsavel por retornar o Botao Ok da View Erro.
     * @return Botao Ok da View Erro.
     */
    public JButton getButtonOk() {
        return this.buttonAction1;
    }
}