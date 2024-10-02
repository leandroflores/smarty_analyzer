package visao.frames.cadastro;

import java.awt.Color;
import javax.swing.JButton;
import visao.frames.ViewModal;
import visao.frames.Viewable;

/**
 * <p>Classe de Visao <b>ViewCadastro</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Cadastro</b>.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewCadastro extends ViewModal implements Viewable {

    /**
     * Metodo construtor padrao da Classe.
     * @param viewModal View Parent.
     */
    public ViewCadastro(ViewModal viewModal) {
        super(viewModal);
    }

    /**
     * Metodo responsavel por Inserir o Cabecalho na View Cadastro.
     * @param urlImage URL da Imagem de Cabecalho.
     */
    @Override
    protected void addHeader(String urlImage) {
        super.addHeader("cadastro/" + urlImage);
        this.labelMessage = this.createLabel("");
        this.add(this.labelMessage);
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por definir uma Mensagem no Cabecalho da View Cadastro.
     * @param message Mensagem a ser exibida.
     */
    public void setMessage(String message) {
        this.labelMessage.setText(message);
        this.labelMessage.setForeground(Color.BLACK);
    }

    /**
     * Metodo responsavel por definir uma Mensagem de Erro no Cabecalho da View Cadastro.
     * @param error Mensagem de Erro a ser exibida.
     */
    public void setError(String error) {
        this.labelMessage.setText(error);
        this.labelMessage.setForeground(Color.RED);
    }
    
    /**
     * Metodo responsavel por Limpar as Bordas dos Componentes da View Cadastro.
     */
    public abstract void clearBorder();
    
    /**
     * Metodo responsavel por Limpar os Componentes da View Cadastro.
     */
    public abstract void clear();

    
    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton(" Inserir ", "add.jpg");
        this.buttonAction2 = this.createButton("  Limpar ", "clear.jpg");
        this.buttonAction3 = this.createButton("  Voltar ", "back.jpg");
        
        this.add(this.buttonAction1);
        this.add(this.buttonAction2);
        this.add(this.buttonAction3);
    }
    
    /**
     * Metodo responsavel por retornar o Botao Inserir da View Cadastro.
     * @return Botao Inserir da View Cadastro.
     */
    public JButton getButtonInserir() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Limpar da View Cadastro.
     * @return Botao Limpar da View Cadastro.
     */
    public JButton getButtonClear() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Voltar da View Cadastro.
     * @return Botao Voltar da View Cadastro.
     */
    public JButton getButtonVoltar() {
        return this.buttonAction3;
    }
}