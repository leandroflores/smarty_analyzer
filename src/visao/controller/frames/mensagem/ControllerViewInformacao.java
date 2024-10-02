package visao.controller.frames.mensagem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.mensagem.ViewInformacao;

/**
 * <p>Classe de Controle <b>ControllerViewInformacao</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewInformacao.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.mensagem.ViewInformacao
 */
public class ControllerViewInformacao extends ControllerViewModal {
    private final ViewInformacao viewInformacao;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewInformacao View Informacao do Controller.
     */
    public ControllerViewInformacao(ViewInformacao viewInformacao) {
        super(viewInformacao);
        this.viewInformacao = viewInformacao;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewInformacao.getButtonOk().equals(event.getSource())) {
            this.viewInformacao.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode()) {
            this.viewInformacao.dispose();
        }
    }
}