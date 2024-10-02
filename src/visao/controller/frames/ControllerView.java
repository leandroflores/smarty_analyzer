package visao.controller.frames;

import visao.controller.Controller;
import java.awt.event.KeyEvent;
import visao.frames.View;

/**
 * <p>Classe de Controle <b>ControllerView</b>.</p>
 * <p>Classe responsavel por ser a <b>Controladora de Eventos</b> das Views do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.Controller
 * @see    view.View
 */
public abstract class ControllerView extends Controller {
    private final View view;

    /**
     * Metodo construtor padrao da Classe.
     * @param view View do Controller.
     */
    public ControllerView(View view) {
        this.view = view;
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (ESC == event.getKeyCode()) {
            this.view.dispose();
        }
    }
}