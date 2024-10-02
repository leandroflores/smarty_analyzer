package visao.frames.sistema;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import visao.controller.frames.sistema.ControllerViewSistemaSobre;
import visao.frames.View;
import visao.frames.ViewModal;
import visao.frames.Viewable;

/**
 * <p>Classe de Visao <b>ViewSistemaSobre</b>.</p>
 * <p>Classe responsavel por definir a Interface com <b>Informacoes</b> do Sistema.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public final class ViewSistemaSobre extends ViewModal implements Viewable {

    /**
     * Metodo construtor padrao da Classe.
     * @param view View Parent.
     */
    public ViewSistemaSobre(View view) {
        super(view);
        this.controller = new ControllerViewSistemaSobre(this);
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Sobre");
        this.setSize(320, 350);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }
    
    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(new JLabel(new ImageIcon(getClass().getResource("/visao/imagens/sistema/sobre.jpg"))));
        this.addLinhas(1);
    }
    
    @Override
    public void addComponents() {
        this.add(this.createLabel("Universidade Estadual de Maringa"));
        this.addLinhas(1);
        this.add(this.createLabel("Departamento de Informatica"));
        this.addLinhas(1);
        this.add(this.createLabel("SMartyAnalyzer"));
        this.addLinhas(1);
        this.add(this.createLabel("Sistema de Aplicacao de Metricas"));
        this.addLinhas(1);
    }

    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton("   Ok   ", "ok.jpg");
        
        this.add(this.buttonAction1);
    }
 
    /**
     * Metodo responsavel por retornar o Botao Ok da View Sobre.
     * @return Botao Ok da View Sobre.
     */
    public JButton getButtonOk() {
        return this.buttonAction1;
    }
}