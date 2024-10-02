package visao.controller.frames.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.cadastro.ViewCadastro;

/**
 * <p>Classe de Controle <b>ControllerViewCadastro</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> das Views de Cadastro do Sistema.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.cadastro.ViewCadastro
 */
public abstract class ControllerViewCadastro extends ControllerViewModal {
    private final ViewCadastro viewCadastro;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewCadastro View de Cadastro do Sistema.
     */
    public ControllerViewCadastro(ViewCadastro viewCadastro) {
        super(viewCadastro);
        this.viewCadastro = viewCadastro;
    }

    /**
     * Metodo Abstrato responsavel por realizar o Cadastro.
     */
    public abstract void cadastrar();

    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewCadastro.getButtonInserir().equals(event.getSource())) {
            this.cadastrar();
        }else if (this.viewCadastro.getButtonClear().equals(event.getSource())) {
            this.viewCadastro.clear();
        }else if (this.viewCadastro.getButtonVoltar().equals(event.getSource())) {
            this.viewCadastro.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.cadastrar();
                break;
            case F2:
                this.viewCadastro.clear();
                break;
            case F3:
                this.viewCadastro.dispose();
                break;
            default:
                break;
        }
    }
}