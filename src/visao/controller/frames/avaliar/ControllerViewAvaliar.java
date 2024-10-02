package visao.controller.frames.avaliar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.avaliar.ViewAvaliar;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public abstract class ControllerViewAvaliar extends ControllerViewModal {
    private final ViewAvaliar viewAvaliar;
    
    public ControllerViewAvaliar(ViewAvaliar viewAvaliar) {
        super(viewAvaliar);
        this.viewAvaliar = viewAvaliar;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewAvaliar.getButtonVoltar().equals(event.getSource())) {
            this.viewAvaliar.dispose();
        }else if ((this.viewAvaliar.getButtonSearchModeloUML1() != null) && (this.viewAvaliar.getButtonSearchModeloUML1().equals(event.getSource()))) {
            this.getArquivo1();
        }else if ((this.viewAvaliar.getButtonSearchModeloUML2() != null) && (this.viewAvaliar.getButtonSearchModeloUML2().equals(event.getSource()))) {
            this.getArquivo2();
        }else if (this.viewAvaliar.getButtonUpdate().equals(event.getSource())) {
            if (this.check()) {
                this.update();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.viewAvaliar.dispose();
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
            new ViewErro(this.viewAvaliar, "Selecione um Arquivo .xmi!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar o Arquivo XMI selecionado.
     */
    protected void getArquivo1() {
        int opcao = this.viewAvaliar.getFileChooser1().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewAvaliar.getFileChooser1().getSelectedFile().getAbsolutePath();
            if (this.checkArquivo(caminho)) {
                this.viewAvaliar.getTextFieldModeloUML1().setText(caminho);
                this.updateInfoPanel1(caminho);
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar as Informacoes do Panel 1.
     * @param caminho Caminho do Arquivo XMI.
     */
    protected abstract void updateInfoPanel1(String caminho);
    
    /**
     * Metodo responsavel por atualizar o Arquivo XMI selecionado.
     */
    protected void getArquivo2() {
        int opcao = this.viewAvaliar.getFileChooser2().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewAvaliar.getFileChooser2().getSelectedFile().getAbsolutePath();
            if (this.checkArquivo(caminho)) {
                this.viewAvaliar.getTextFieldModeloUML2().setText(caminho);
                this.updateInfoPanel2(caminho);
            }
        }
    }
    
    /**
     * Metodo responsavel por atualizar as Informacoes do Panel 2.
     * @param caminho Caminho do Arquivo XMI.
     */
    protected abstract void updateInfoPanel2(String caminho);
    
    /**
     * Metodo responsavel por checar os Dados.
     * @return Dados estao validos.
     */
    protected abstract boolean check();
    
    /**
     * Metodo responsavel por atualizar os Dados da View Avaliar.
     */
    protected abstract void update();
}