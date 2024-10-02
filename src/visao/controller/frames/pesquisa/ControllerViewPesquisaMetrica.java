package visao.controller.frames.pesquisa;

import java.util.List;
import modelo.controller.entidades.ControllerMetrica;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;

/**
 * <p>Classe de Controle <b>ControllerViewPesquisaMetrica</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewPesquisaMetrica.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    controller.view.pesquisa.ControllerViewPesquisa
 * @see    view.pesquisa.ViewPesquisa
 */
public class ControllerViewPesquisaMetrica extends ControllerViewPesquisa {
    private final ViewPesquisaMetrica viewPesquisaMetrica;
    private final DaoMetrica          daoMetrica;
    private final ControllerMetrica   controllerMetrica;
    private       List<Metrica>       metricas;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewPesquisaMetrica View Pesquisa Metrica do Sistema.
     */
    public ControllerViewPesquisaMetrica(ViewPesquisaMetrica viewPesquisaMetrica) {
        super(viewPesquisaMetrica);
        this.viewPesquisaMetrica = viewPesquisaMetrica;
        this.daoMetrica          = new DaoMetrica();
        this.controllerMetrica   = new ControllerMetrica();
        this.metricas            = this.daoMetrica.select();
    }

    @Override
    public void search() {
        String metrica = this.getClausulaBusca();
        this.metricas  = this.daoMetrica.query(metrica);
        this.viewPesquisaMetrica.addRows(this.controllerMetrica.getCamposPesquisa(this.metricas));
    }

    /**
     * Metodo responsavel por retornar a Clausula de Busca.
     * @return Clausula de Busca.
     */
    private String getClausulaBusca() {
        String pesquisa = this.viewPesquisaMetrica.getTextFieldPesquisa().getText().trim();
        String query    = "(e.nome LIKE '%" + pesquisa + "%' OR ";
               query   += " e.rotulo LIKE '%" + pesquisa + "%' OR ";
               query   += " e.descricao LIKE '%" + pesquisa + "%')";
               query   += this.getClausulaTipo();
        return query;
    }
    
    private String getClausulaTipo() {
        String pesquisa = this.viewPesquisaMetrica.getTipo();
        String query    = "";
        if (pesquisa.equals("") == false)
            query = " AND e.diagramaAlvo = '" + pesquisa + "'";
        return query;
    }
    
    @Override
    public void select() {
        int index = this.viewPesquisaMetrica.getTable().getSelectedRow();
        int size  = this.metricas.size();
        if ((index >= 0) && (index < size)) {
            if (this.viewPesquisaMetrica.getViewAvaliar() != null) {
                this.viewPesquisaMetrica.getViewAvaliar().setMetrica(this.metricas.get(index));
            }else if (this.viewPesquisaMetrica.getViewAvaliarMedicao() != null) {
                this.viewPesquisaMetrica.getViewAvaliarMedicao().setMetrica(this.metricas.get(index));
            }else if (this.viewPesquisaMetrica.getViewConsultaMedicao() != null) {
                this.viewPesquisaMetrica.getViewConsultaMedicao().setMetrica(this.metricas.get(index));
            }else {
                this.viewPesquisaMetrica.getViewProcessar().setMetrica(this.metricas.get(index));
            }
            this.viewPesquisaMetrica.dispose();
        }else {
            new ViewErro(this.viewPesquisaMetrica, "Selecione uma Métrica!").setVisible(true);
            this.viewPesquisaMetrica.getTextFieldPesquisa().requestFocus();
        }
    }
}