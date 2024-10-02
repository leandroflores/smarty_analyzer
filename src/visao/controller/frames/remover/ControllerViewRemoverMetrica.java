package visao.controller.frames.remover;

import modelo.dao.entidades.DaoMedicao;
import modelo.dao.entidades.DaoMetrica;
import visao.frames.mensagem.ViewInformacao;
import visao.frames.remover.ViewRemoverMetrica;

/**
 * <p>Classe de Controle <b>ControllerViewRemoverMetrica</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewRemoverMetrica.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.visao.remover.ControllerViewRemover
 */
public class ControllerViewRemoverMetrica extends ControllerViewRemover {
    private        final ViewRemoverMetrica viewRemoverMetrica;
    private static final DaoMetrica         DAO_METRICA = new DaoMetrica();
    private static final DaoMedicao         DAO_MEDICAO = new DaoMedicao();

    /**
     * Metodo construtor padrao da Classe.
     * @param viewRemoverMetrica View Remover Metrica.
     */
    public ControllerViewRemoverMetrica(ViewRemoverMetrica viewRemoverMetrica) {
        super(viewRemoverMetrica);
        this.viewRemoverMetrica = viewRemoverMetrica;
    }

    @Override
    public void remove() {
        DAO_MEDICAO.remove(this.viewRemoverMetrica.getMetrica());
        DAO_METRICA.delete(this.viewRemoverMetrica.getMetrica().getId());
        new ViewInformacao(this.viewRemoverMetrica, "Métrica Removida com Sucesso!").setVisible(true);
        this.viewRemoverMetrica.getViewConsulta().clear();
        this.viewRemoverMetrica.dispose();
    }
}