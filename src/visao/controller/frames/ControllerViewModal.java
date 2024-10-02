package visao.controller.frames;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import visao.controller.Controller;
import visao.frames.ViewModal;
import visao.frames.mensagem.ViewErro;

/**
 * <p>Classe de Controle <b>ControllerViewModal</b>.</p>
 * <p>Classe responsavel por ser a <b>Controladora de Eventos</b> das ViewsModais do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.Controller
 * @see    view.ViewModal
 */
public abstract class ControllerViewModal extends Controller {
    private final ViewModal viewModal;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewModal ViewModal do Controller.
     */
    public ControllerViewModal(ViewModal viewModal) {
        this.viewModal = viewModal;
    }
    
    /**
     * Metodo responsavel por Exibir a Mensagem de Erro.
     * @param  component Componente a ser focado.
     * @param  mensagem Mensagem a ser exibida.
     * @return false.
     */
    protected boolean erro(JComponent component, String mensagem) {
        new ViewErro(this.viewModal, mensagem).setVisible(true);
        component.requestFocus();
        component.setBorder(BorderFactory.createLineBorder(Color.RED));
        return false;
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (ESC == event.getKeyCode()) {
            this.viewModal.dispose();
        }
    }
}