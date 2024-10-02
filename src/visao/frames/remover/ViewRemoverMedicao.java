package visao.frames.remover;

import modelo.entidades.Medicao;
import visao.controller.frames.remover.ControllerViewRemoverMedicao;
import visao.frames.consulta.ViewConsultaMedicao;
import visao.frames.consulta.ViewConsultaMetrica;

/**
 * <p>Classe de Visao <b>ViewRemoverMedicao</b>.</p>
 * <p>Classe responsavel por definir uma Interface para a Remocao de Medicao.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    visao.remover.ViewRemover
 */
public final class ViewRemoverMedicao extends ViewRemover {
    private final Medicao medicao;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsulta View Consulta de Medicoes.
     * @param medicao Medicao a ser removida.
     */
    public ViewRemoverMedicao(ViewConsultaMedicao viewConsulta, Medicao medicao) {
        super(viewConsulta);
        this.medicao    = medicao;
        this.controller = new ControllerViewRemoverMedicao(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Remover Medicao");
        this.setSize(550, 150);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.medicao.toString());
    }
    
    /**
     * Metodo responsavel por retornar a Medicao da View Remover Medicao.
     * @return Medicao da View Remover Medicao.
     */
    public Medicao getMedicao() {
        return this.medicao;
    }
}