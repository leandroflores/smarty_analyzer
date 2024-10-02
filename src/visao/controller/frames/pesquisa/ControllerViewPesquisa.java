package visao.controller.frames.pesquisa;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.pesquisa.ViewPesquisa;

/**
 * <p>Classe de Controle <b>ControllerViewPesquisa</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewPesquisa.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    controller.visao.ControllerViewModal
 * @see    view.pesquisa.ViewPesquisa
 */
public abstract class ControllerViewPesquisa extends ControllerViewModal {
    private final ViewPesquisa viewPesquisa;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewPesquisa View Pesquisa do Sistema.
     */
    public ControllerViewPesquisa(ViewPesquisa viewPesquisa) {
        super(viewPesquisa);
        this.viewPesquisa = viewPesquisa;
    }

    /**
     * Metodo responsavel por realizar a Pesquisa e Atualizar a Tabela.
     */
    public abstract void search();
    
    /**
     * Metodo responsavel por Selecionar um Elemento da Tabela.
     */
    public abstract void select();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewPesquisa.getButtonPesquisa().equals(event.getSource())) {
            this.search();
        }else if (this.viewPesquisa.getButtonSelecionar().equals(event.getSource())) {
            this.select();
        }else if (this.viewPesquisa.getButtonVoltar().equals(event.getSource())) {
            this.viewPesquisa.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case ENTER:
                this.search();
                break;
            case F5:
                this.search();
                break;
            case F1:
                this.select();
                break;
            case F2:
                this.viewPesquisa.dispose();
                break;
            default:
                break;
        }
    }
}