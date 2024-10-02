package visao.controller.frames.avaliar.medicao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import visao.controller.frames.ControllerViewModal;
import visao.frames.avaliar.medicao.ViewAvaliarMedicao;
import visao.frames.mensagem.ViewErro;

/**
 *
 * @author Leandro
 */
public abstract class ControllerViewAvaliarMedicao extends ControllerViewModal {
    private final ViewAvaliarMedicao viewAvaliarMedicao;
    
    public ControllerViewAvaliarMedicao(ViewAvaliarMedicao viewAvaliarMedicao) {
        super(viewAvaliarMedicao);
        this.viewAvaliarMedicao = viewAvaliarMedicao;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewAvaliarMedicao.getButtonSearchMetrica().equals(event.getSource())) {
            this.searchMetrica();
        }else if (this.viewAvaliarMedicao.getButtonUpdate().equals(event.getSource())) {
            if (this.check()) {
                this.update();
            }
        }else if (this.viewAvaliarMedicao.getButtonRefresh().equals(event.getSource())) {
            if (this.check()) {
                this.update();
            }
        }else if (this.viewAvaliarMedicao.getButtonBack().equals(event.getSource())) {
            this.viewAvaliarMedicao.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F6:
                this.searchMetrica();
                break;
            case F5:
                if (this.check()) {
                    this.update();
                }
                break;
            case ENTER:
                if (this.check()) {
                    this.update();
                }
                break;
            case F1:
                if (this.check()) {
                    this.update();
                }
                break;
            case F2:
                this.viewAvaliarMedicao.dispose();
                break;
        }
    }
    
    /**
     * Metodo responsavel por pesquisar a Metrica da View Avaliar Medicao.
     */
    protected abstract void searchMetrica();
    
    /**
     * Metodo responsavel por retornar se os Dados estao corretos para Pesquisa.
     * @return Dados estao corretos para Pesquisa.
     */
    protected boolean check() {
        if ((this.viewAvaliarMedicao.getMetrica() == null)
         && (this.viewAvaliarMedicao.getTextFieldNome().getText().equals(""))) {
            new ViewErro(this.viewAvaliarMedicao, "Selecione uma Metrica ou Nome para Pesquisa!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por atualizar os Dados da View Avaliar.
     */
    protected abstract void update();
}