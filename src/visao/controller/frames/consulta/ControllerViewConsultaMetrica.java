package visao.controller.frames.consulta;

import java.util.List;
import modelo.controller.entidades.ControllerMetrica;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;
import visao.frames.cadastro.ViewCadastroMetrica;
import visao.frames.consulta.ViewConsultaMetrica;
import visao.frames.editar.ViewEditarMetrica;
import visao.frames.mensagem.ViewErro;
import visao.frames.remover.ViewRemoverMetrica;

/**
 * <p>Classe de Controle <b>ControllerViewConsultaMetrica</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewConsultaMetrica.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.view.consulta.ControllerViewConsulta
 * @see    view.consulta.ViewConsultaMetrica
 */
public class ControllerViewConsultaMetrica extends ControllerViewConsulta {
    private final ViewConsultaMetrica viewConsultaMetrica;
    private final ControllerMetrica   controllerMetrica;
    private final DaoMetrica          daoMetrica;
    private       List<Metrica>       metricas;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsultaMetrica View de Consulta de Metricas do Sistema.
     */
    public ControllerViewConsultaMetrica(ViewConsultaMetrica viewConsultaMetrica) {
        super(viewConsultaMetrica);
        this.viewConsultaMetrica = viewConsultaMetrica;
        this.controllerMetrica   = new ControllerMetrica();
        this.daoMetrica          = new DaoMetrica();
        this.metricas            = this.daoMetrica.select();
    }

    @Override
    public void search() {
        String search = this.getClausulaBusca();
        this.metricas = this.daoMetrica.query(search);
        this.viewConsultaMetrica.addRows(this.controllerMetrica.getMetricas(this.metricas));
        this.viewConsultaMetrica.setTotal(this.metricas.size());
    }
    
    /**
     * Metodo responsavel por retornar a Clausula de Busca.
     * @return Clausula de Busca.
     */
    private String getClausulaBusca() {
        String pesquisa = this.viewConsultaMetrica.getTextFieldSearch().getText();
        String query    = "(e.nome LIKE '%" + pesquisa + "%' OR ";
               query   += " e.rotulo LIKE '%" + pesquisa + "%' OR ";
               query   += " e.descricao LIKE '%" + pesquisa + "%')";
               query   += this.getClausulaDiagramaAlvo();
               query   += this.getClausulaTipo();
        return query;
    }
    
    /**
     * Metodo responsavel por retornar a Clausula de Pesquisa do Diagrama Alvo.
     * @return Clausula de Pesquisa do Diagrama Alvo.
     */
    private String getClausulaDiagramaAlvo() {
        String pesquisa = this.viewConsultaMetrica.getComboBoxDiagramaAlvo().getSelectedItem().toString();
        String query    = "";
        if (pesquisa.equals("Selecione") == false)
            query = " AND e.diagramaAlvo = '" + pesquisa + "'";
        return query;
    }

    /**
     * Metodo responsavel por retornar a Clausula de Pesquisa do Tipo.
     * @return Clausula de Pesquisa do Tipo.
     */
    private String getClausulaTipo() {
        String pesquisa = this.viewConsultaMetrica.getComboBoxTipo().getSelectedItem().toString();
        String query    = "";
        if (pesquisa.equals("Selecione") == false)
            query = " AND e.tipo = '" + pesquisa + "'";
        return query;
    }
    
    @Override
    public void novo() {
        new ViewCadastroMetrica(this.viewConsultaMetrica).setVisible(true);
    }

    @Override
    public void edit() {
        int index = this.viewConsultaMetrica.getTable().getSelectedRow();
        int total = this.metricas.size();
        if ((index >= 0) && (index < total)) {
            new ViewEditarMetrica(this.viewConsultaMetrica, this.metricas.get(index)).setVisible(true);
        }else {
            new ViewErro(this.viewConsultaMetrica, "Selecione uma Métrica!").setVisible(true);
            this.viewConsultaMetrica.getTextFieldSearch().requestFocus();
        }
    }

    @Override
    public void remove() {
        int index = this.viewConsultaMetrica.getTable().getSelectedRow();
        int total = this.metricas.size();
        if ((index >= 0) && (index < total)) {
            new ViewRemoverMetrica(this.viewConsultaMetrica, this.metricas.get(index)).setVisible(true);
        }else {
            new ViewErro(this.viewConsultaMetrica, "Selecione uma Métrica!").setVisible(true);
            this.viewConsultaMetrica.getTextFieldSearch().requestFocus();
        }
    }
}