package visao.controller.frames.exportar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.exportar.ViewExportar;
import visao.frames.mensagem.ViewErro;

/**
 * <p>Classe de Controle <b>ControllerViewExportar</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewExportar.</p>
 * @author Leandro
 * @since  24/05/2017
 * @see    controller.visao.ControllerViewModal
 * @see    visao.frames.importar.ViewImportar
 */
public abstract class ControllerViewExportar extends ControllerViewModal {
    private final ViewExportar viewExportar;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewExportar View Exportar do Sistema.
     */
    public ControllerViewExportar(ViewExportar viewExportar) {
        super(viewExportar);
        this.viewExportar = viewExportar;
    }

    /**
     * Metodo responsavel pela Exportacao.
     */
    public abstract void exportacao();
    
    /**
     * Metodo responsavel por verificar se os parametros informados sao validos.
     * @return Parametros informados sao Validos.
     */
    public abstract boolean check();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewExportar.getButtonSearchDiretorio().equals(event.getSource())) {
            this.getDiretorio();
        }else if (this.viewExportar.getButtonExportar().equals(event.getSource())) {
            if (this.check()) {
                this.exportacao();
            }
        }else if (this.viewExportar.getButtonCancelar().equals(event.getSource())) {
            this.viewExportar.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F7:
                this.getDiretorio();
                break;
            case F5:
                if (this.check()) {
                    this.exportacao();
                }
                break;
            case F1:
                if (this.check()) {
                    this.exportacao();
                }
                break;
            case F2:
                this.viewExportar.dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Metodo responsavel por retornar se o arquivo e txt.
     * @return Arquivo e Txt.
     */
    protected boolean checkArquivo() {
        String caminho = this.viewExportar.getTextFieldArquivo().getText().trim();
        if (caminho.toLowerCase().endsWith(".txt") == false) {
            new ViewErro(this.viewExportar, "Digite um arquivo .txt!").setVisible(true);
            this.viewExportar.getTextFieldArquivo().requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar o Diretorio selecionado.
     */
    protected void getDiretorio() {
        int opcao = this.viewExportar.getDiretorioChooser().showSaveDialog(null);
        if (opcao != 1) {
            String caminho = this.viewExportar.getDiretorioChooser().getSelectedFile().getAbsolutePath();
            this.viewExportar.getTextFieldDiretorio().setText(caminho);
        }
    }
}