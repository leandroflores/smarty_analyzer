package visao.controller.frames.consulta;

import visao.controller.frames.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.frames.consulta.ViewConsulta;

/**
 * <p>Classe de Controle <b>ControllerViewConsulta</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> das Views de Consulta do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    controller.view.ControllerViewModal
 * @see    view.consulta.ViewConsulta
 */
public abstract class ControllerViewConsulta extends ControllerViewModal {
    protected final ViewConsulta viewConsulta;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsulta View de Consulta do Sistema.
     */
    public ControllerViewConsulta(ViewConsulta viewConsulta) {
        super(viewConsulta);
        this.viewConsulta = viewConsulta;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewConsulta.getButtonSearch().equals(event.getSource())) {
            this.search();
        }else if (this.viewConsulta.getButtonNovo().equals(event.getSource())) {
            this.novo();
        }else if (this.viewConsulta.getButtonEdit().equals(event.getSource())) {
            this.edit();
        }else if (this.viewConsulta.getButtonRemove().equals(event.getSource())) {
            this.remove();
        }else if (this.viewConsulta.getButtonBack().equals(event.getSource())) {
            this.viewConsulta.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent oKeyEvent) {
        super.keyPressed(oKeyEvent);
        if ((F5      == oKeyEvent.getKeyCode())
         || (ENTER   == oKeyEvent.getKeyCode())) {
            this.search();
        }else if (F1 == oKeyEvent.getKeyCode()) {
            this.novo();
        }else if (F2 == oKeyEvent.getKeyCode()) {
            this.edit();
        }else if (F3 == oKeyEvent.getKeyCode()) {
            this.remove();
        }else if (F4 == oKeyEvent.getKeyCode()) {
            this.viewConsulta.dispose();
        }
    }

    
    /**
     * Metodo Abstrato responsavel por realizar uma Pesquisa.
     */
    public abstract void search();
    
    /**
     * Metodo Abstrato responsavel por Abrir a View de Cadastro.
     */
    public abstract void novo();
    
    /**
     * Metodo Abstrato responsavel por Abrir a View para Edicao.
     */
    public abstract void edit();
    
    /**
     * Metodo Abstrato responsavel por Abrir a View para Remocao.
     */
    public abstract void remove();
}