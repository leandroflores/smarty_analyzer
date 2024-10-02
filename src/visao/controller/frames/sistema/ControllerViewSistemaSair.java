package visao.controller.frames.sistema;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.sistema.ViewSistemaSair;

/**
 * <p>Classe de Controle <b>ControllerViewSistemaSair</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewSistemaSair.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.sistema.ViewSistemaSair
 */
public class ControllerViewSistemaSair extends ControllerViewModal {
    private final ViewSistemaSair viewSistemaSair;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewSistemaSair View Sair do Sistema.
     */
    public ControllerViewSistemaSair(ViewSistemaSair viewSistemaSair) {
        super(viewSistemaSair);
        this.viewSistemaSair = viewSistemaSair;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewSistemaSair.getButtonYes().equals(event.getSource())) {
            this.viewSistemaSair.dispose();
            this.viewSistemaSair.getViewMenu().dispose();
        }else if (this.viewSistemaSair.getButtonNo().equals(event.getSource())) {
            this.viewSistemaSair.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode()) {
            if (this.viewSistemaSair.getFocusOwner().equals(this.viewSistemaSair.getButtonYes())) {
                this.viewSistemaSair.dispose();
                this.viewSistemaSair.getViewMenu().dispose();
            }else if (this.viewSistemaSair.getFocusOwner().equals(this.viewSistemaSair.getButtonNo())) {
                this.viewSistemaSair.dispose();
            }
        }
    }
}