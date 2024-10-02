package visao.controller.frames.mensagem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.mensagem.ViewErro;

/**
 * <p>Classe de Controle <b>ControllerViewErro</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewErro.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.mensagem.ViewErro
 */
public class ControllerViewErro extends ControllerViewModal {
    private final ViewErro viewErro;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewErro View Erro do Controller.
     */
    public ControllerViewErro(ViewErro viewErro) {
        super(viewErro);
        this.viewErro = viewErro;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewErro.getButtonOk().equals(event.getSource())) {
            this.viewErro.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode()) {
            this.viewErro.dispose();
        }
    }
}