package visao.controller.frames.analisar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.analisar.ViewAnalisar;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public abstract class ControllerViewAnalisar extends ControllerViewModal {
    private final ViewAnalisar viewAnalisar;

    public ControllerViewAnalisar(ViewAnalisar viewAnalisar) {
        super(viewAnalisar);
        this.viewAnalisar = viewAnalisar;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewAnalisar.getButtonSearchModeloUML().equals(event.getSource())) {
            this.getArquivo();
        }else if (this.viewAnalisar.getButtonUpdate().equals(event.getSource())) {
            this.update();
        }else if (this.viewAnalisar.getButtonVoltar().equals(event.getSource())) {
            this.viewAnalisar.dispose();
        }else if (this.viewAnalisar.getButtonCancel().equals(event.getSource())) {
            this.viewAnalisar.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F6:
                this.getArquivo();
                break;
            case F5:
                this.update();
                break;
            case ENTER:
                this.update();
                break;
            case F1:
                this.viewAnalisar.dispose();
                break;
            case F2:
                this.viewAnalisar.dispose();
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
            new ViewErro(this.viewAnalisar, "Selecione um Arquivo .xmi!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar o Arquivo XMI selecionado.
     */
    protected void getArquivo() {
        int opcao = this.viewAnalisar.getFileChooser().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewAnalisar.getFileChooser().getSelectedFile().getAbsolutePath();
            if (this.checkArquivo(caminho)) {
                this.viewAnalisar.getTextFieldModeloUML().setText(caminho);
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
     * Metodo responsavel por atualizar os Dados da View Analisar.
     */
    protected abstract void update();
}