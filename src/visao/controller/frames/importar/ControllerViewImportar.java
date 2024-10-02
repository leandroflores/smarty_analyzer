package visao.controller.frames.importar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.importar.ViewImportar;
import visao.frames.mensagem.ViewErro;

/**
 * <p>Classe de Controle <b>ControllerViewImportar</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewImportar.</p>
 * @author Leandro
 * @since  24/05/2017
 * @see    controller.visao.ControllerViewModal
 * @see    visao.frames.importar.ViewImportar
 */
public abstract class ControllerViewImportar extends ControllerViewModal {
    private final ViewImportar viewImportar;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewImportar View Importar do Sistema.
     */
    public ControllerViewImportar(ViewImportar viewImportar) {
        super(viewImportar);
        this.viewImportar = viewImportar;
    }

    /**
     * Metodo responsavel pela Importacao.
     */
    public abstract void importacao();
    
    /**
     * Metodo responsavel por verificar se os parametros informados sao validos.
     * @return Parametros informados sao Validos.
     */
    public boolean check() {
        String caminho = this.viewImportar.getTextFieldArquivo().getText().trim();
        if (caminho.endsWith(".txt") == false) {
            new ViewErro(this.viewImportar, "Selecione um Arquivo .txt!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewImportar.getButtonSearch().equals(event.getSource())) {
            this.getArquivo();
        }else if (this.viewImportar.getButtonImportar().equals(event.getSource())) {
            if (this.check()) {
                this.importacao();
            }
        }else if (this.viewImportar.getButtonCancelar().equals(event.getSource())) {
            this.viewImportar.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F7:
                this.getArquivo();
                break;
            case F5:
                if (this.check()) {
                    this.importacao();
                }
                break;
            case F1:
                if (this.check()) {
                    this.importacao();
                }
                break;
            case F2:
                this.viewImportar.dispose();
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
        if (caminho.toLowerCase().endsWith(".txt") == false) {
            new ViewErro(this.viewImportar, "Selecione um Arquivo .txt!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar o Arquivo XMI selecionado.
     */
    protected void getArquivo() {
        int opcao = this.viewImportar.getFileChooser().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewImportar.getFileChooser().getSelectedFile().getAbsolutePath();
            this.viewImportar.getTextFieldArquivo().setText(caminho);
        }
    }
}