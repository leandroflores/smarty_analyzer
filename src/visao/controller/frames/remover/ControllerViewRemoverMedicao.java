package visao.controller.frames.remover;

import modelo.dao.entidades.DaoMedicao;
import visao.frames.mensagem.ViewInformacao;
import visao.frames.remover.ViewRemoverMedicao;

/**
 * <p>Classe de Controle <b>ControllerViewRemoverMedicao</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewRemoverMedicao.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.visao.remover.ControllerViewRemover
 */
public class ControllerViewRemoverMedicao extends ControllerViewRemover {
    private        final ViewRemoverMedicao viewRemoverMedicao;
    private static final DaoMedicao         DAO_MEDICAO = new DaoMedicao();

    /**
     * Metodo construtor padrao da Classe.
     * @param viewRemoverMedicao View Remover Medicao.
     */
    public ControllerViewRemoverMedicao(ViewRemoverMedicao viewRemoverMedicao) {
        super(viewRemoverMedicao);
        this.viewRemoverMedicao = viewRemoverMedicao;
    }

    @Override
    public void remove() {
        DAO_MEDICAO.delete(this.viewRemoverMedicao.getMedicao().getId());
        new ViewInformacao(this.viewRemoverMedicao, "Medicao Removida com Sucesso!").setVisible(true);
        this.viewRemoverMedicao.getViewConsulta().clear();
        this.viewRemoverMedicao.dispose();
    }
}