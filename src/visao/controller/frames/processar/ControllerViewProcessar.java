package visao.controller.frames.processar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;
import visao.frames.processar.ViewProcessar;

/**
 * <p>Classe de Controle <b>ControllerViewProcessar</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewProcessar.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    controller.visao.ControllerViewModal
 * @see    view.processar.ViewProcessar
 */
public abstract class ControllerViewProcessar extends ControllerViewModal {
    private final ViewProcessar viewProcessar;
    protected String tipo = "";

    /**
     * Metodo construtor padrao da Classe.
     * @param viewProcessar View Processar do Sistema.
     */
    public ControllerViewProcessar(ViewProcessar viewProcessar) {
        super(viewProcessar);
        this.viewProcessar = viewProcessar;
    }

    /**
     * Metodo responsavel por Gravar a Medicao.
     */
    public abstract void save();
    
    /**
     * Metodo responsavel por Verificar se os Parametros informados sao Validos.
     * @return Parametros informados sao Validos.
     */
    public abstract boolean check();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewProcessar.getButtonSearchModeloUML().equals(event.getSource())) {
            this.getArquivo();
        }else if (this.viewProcessar.getButtonSearchMetrica().equals(event.getSource())) {
            new ViewPesquisaMetrica(this.viewProcessar, this.tipo).setVisible(true);
        }else if (this.viewProcessar.getButtonRefresh().equals(event.getSource())) {
            if (this.check()) {
                this.update();
            }
        }else if (this.viewProcessar.getButtonGravar().equals(event.getSource())) {
            if (this.check()) {
                this.save();
            }
        }else if (this.viewProcessar.getButtonCancel().equals(event.getSource())) {
            this.viewProcessar.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F7:
                this.getArquivo();
                break;
            case F6:
                new ViewPesquisaMetrica(this.viewProcessar, this.tipo).setVisible(true);
            case F5:
                if (this.check()) {
                    this.update();
                }
                break;
            case F1:
                if (this.check()) {
                    this.save();
                }
                break;
            case F2:
                this.viewProcessar.dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Metodo responsavel por retornar se o arquivo e XMI.
     * @param  caminho Caminho do Arquivo.
     * @return Arquivo e XMI.
     */
    protected boolean checkArquivo(String caminho) {
        if (caminho.toLowerCase().endsWith(".xmi") == false) {
            new ViewErro(this.viewProcessar, "Selecione um Arquivo .xmi!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar o Arquivo XMI selecionado.
     */
    protected void getArquivo() {
        int opcao = this.viewProcessar.getFileChooser().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewProcessar.getFileChooser().getSelectedFile().getAbsolutePath();
            if (this.checkArquivo(caminho)) {
                this.viewProcessar.getTextFieldModeloUML().setText(caminho);
                this.updateInfoPanel(caminho);
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar as Informacoes do Panel.
     * @param caminho Caminho do Arquivo XMI.
     */
    protected abstract void updateInfoPanel(String caminho);
    
    /**
     * Metodo responsavel por atualizar os Dados da View Processar.
     */
    protected abstract void update();
}