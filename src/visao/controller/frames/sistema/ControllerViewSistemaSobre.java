package visao.controller.frames.sistema;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.sistema.ViewSistemaSobre;

/**
 * <p>Classe de Controle <b>ControllerViewSistemaSobre</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewSistemaSobre.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.sistema.ViewSistemaSobre
 */
public class ControllerViewSistemaSobre extends ControllerViewModal {
    private final ViewSistemaSobre viewSistemaSobre;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewSistemaSobre View Sobre do Sistema.
     */
    public ControllerViewSistemaSobre(ViewSistemaSobre viewSistemaSobre) {
        super(viewSistemaSobre);
        this.viewSistemaSobre = viewSistemaSobre;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewSistemaSobre.getButtonOk().equals(event.getSource())) {
            this.viewSistemaSobre.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode()) {
            if (this.viewSistemaSobre.getFocusOwner().equals(this.viewSistemaSobre.getButtonOk())) {
                this.viewSistemaSobre.dispose();
            }
        }
    }
}