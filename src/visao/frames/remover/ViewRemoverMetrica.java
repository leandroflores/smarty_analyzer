package visao.frames.remover;

import modelo.entidades.Metrica;
import visao.controller.frames.remover.ControllerViewRemoverMetrica;
import visao.frames.consulta.ViewConsultaMetrica;

/**
 * <p>Classe de Visao <b>ViewRemoverMetrica</b>.</p>
 * <p>Classe responsavel por definir uma Interface para a Remocao de Metrica.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    visao.remover.ViewRemover
 */
public final class ViewRemoverMetrica extends ViewRemover {
    private final Metrica metrica;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsulta View Consulta de Metricas.
     * @param metrica Metrica a ser removida.
     */
    public ViewRemoverMetrica(ViewConsultaMetrica viewConsulta, Metrica metrica) {
        super(viewConsulta);
        this.metrica    = metrica;
        this.controller = new ControllerViewRemoverMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Remover Métrica");
        this.setSize(550, 150);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.metrica.toString() + " e todas suas Medicoes");
    }
    
    /**
     * Metodo responsavel por retornar a Metrica da View Remover Metrica.
     * @return Metrica da View Remover Metrica.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }
}