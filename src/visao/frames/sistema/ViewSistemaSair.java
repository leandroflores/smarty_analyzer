package visao.frames.sistema;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import visao.controller.frames.sistema.ControllerViewSistemaSair;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewSistemaSair</b>.</p>
 * <p>Classe responsavel por definir a Interface de Saida do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public final class ViewSistemaSair extends ViewModal implements Viewable {
    private final ViewMenu viewMenu;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View de Menu do Controller.
     */
    public ViewSistemaSair(ViewMenu viewMenu) {
        super(viewMenu);
        this.viewMenu   = viewMenu;
        this.controller = new ControllerViewSistemaSair(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Sair");
        this.setSize(300, 225);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(new JLabel(new ImageIcon(getClass().getResource("/visao/imagens/sistema/sair.jpg"))));
        this.addLinhas(1);
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Deseja realmente Sair do Sistema?"));
        this.addLinhas(1);
    }

    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton("  Sim  ", "yes.jpg");
        this.buttonAction2 = this.createButton("  Não  ", "no.jpg");
        
        this.add(this.buttonAction1);
        this.add(new JLabel("  "));
        this.add(this.buttonAction2);
    }
    
    /**
     * Metodo responsavel por retornar o Botao Sim da View Sair.
     * @return Botao Sim da View Sair.
     */
    public JButton getButtonYes() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Nao da View Sair.
     * @return Botao Nao da View Sair.
     */
    public JButton getButtonNo() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar a View Menu da View Sair.
     * @return View Menu da View Sair.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}