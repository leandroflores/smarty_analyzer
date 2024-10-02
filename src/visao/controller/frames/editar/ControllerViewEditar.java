package visao.controller.frames.editar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.editar.ViewEditar;

/**
 * <p>Classe de Controle <b>ControllerViewEditar</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewEditar.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.visao.ControllerViewModal
 * @see    view.editar.ViewEditar
 */
public abstract class ControllerViewEditar extends ControllerViewModal {
    private final ViewEditar viewEditar;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewEditar View de Editar do Sistema.
     */
    public ControllerViewEditar(ViewEditar viewEditar) {
        super(viewEditar);
        this.viewEditar = viewEditar;
    }
    
    /**
     * Metodo abstrato responsavel por realizar a Edicao.
     */
    public abstract void edit();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewEditar.getButtonSave().equals(event.getSource())) {
            this.edit();
        }else if (this.viewEditar.getButtonCancel().equals(event.getSource())) {
            this.viewEditar.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode()) {
            this.edit();
        }else if (F2 == event.getKeyCode()) {
            this.viewEditar.dispose();
        }
    }
}