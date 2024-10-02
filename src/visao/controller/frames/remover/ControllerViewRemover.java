package visao.controller.frames.remover;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.remover.ViewRemover;

/**
 * <p>Classe de Controle <b>ControllerViewRemover</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> das Views de Remocao do Sistema.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.view.ControllerViewModal
 */
public abstract class ControllerViewRemover extends ControllerViewModal {
    private final ViewRemover viewRemover;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewRemover View Remover.
     */
    public ControllerViewRemover(ViewRemover viewRemover) {
        super(viewRemover);
        this.viewRemover = viewRemover;
    }

    /**
     * Metodo Abstrato responsavel por realizar a Remocao.
     */
    public abstract void remove();
    
    @Override
    public void actionPerformed(ActionEvent event) {
       if (this.viewRemover.getButtonSim().equals(event.getSource()) == true) {
            this.remove();
        }else if (this.viewRemover.getButtonNao().equals(event.getSource()) == true) {
            this.viewRemover.dispose();
        } 
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode()) {
            this.remove();
        }else if (F2 == event.getKeyCode()) {
            this.viewRemover.dispose();
        }
    }
}