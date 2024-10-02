package visao.controller.frames.consulta;

import java.awt.event.ActionEvent;
import java.util.List;
import modelo.controller.entidades.ControllerMedicao;
import modelo.dao.entidades.DaoMedicao;
import modelo.entidades.Medicao;
import visao.frames.consulta.ViewConsultaMedicao;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;
import visao.frames.remover.ViewRemoverMedicao;

/**
 * <p>Classe de Controle <b>ControllerViewConsultaMedicao</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewConsultaMedicao.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    controller.view.consulta.ControllerViewConsulta
 * @see    view.consulta.ViewConsultaMedicao
 */
public class ControllerViewConsultaMedicao extends ControllerViewConsulta {
    private final ViewConsultaMedicao viewConsultaMedicao;
    private final ControllerMedicao   controllerMedicao;
    private final DaoMedicao          daoMedicao;
    private       List<Medicao>       medicoes;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewConsultaMedicao View de Consulta de Medicoes do Sistema.
     */
    public ControllerViewConsultaMedicao(ViewConsultaMedicao viewConsultaMedicao) {
        super(viewConsultaMedicao);
        this.viewConsultaMedicao = viewConsultaMedicao;
        this.controllerMedicao   = new ControllerMedicao();
        this.daoMedicao          = new DaoMedicao();
        this.medicoes            = this.daoMedicao.select();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.viewConsultaMedicao.getButtonSearchMetrica().equals(event.getSource())) {
            new ViewPesquisaMetrica(this.viewConsultaMedicao, "").setVisible(true);
        }
    }

    @Override
    public void search() {
        String search = this.getClausulaBusca();
        this.medicoes = this.daoMedicao.query(search);
        this.viewConsultaMedicao.addRows(this.controllerMedicao.getMedicoes(this.medicoes));
        this.viewConsultaMedicao.setTotal(this.medicoes.size());
    }
    
    /**
     * Metodo responsavel por retornar a Clausula de Busca.
     * @return Clausula de Busca.
     */
    private String getClausulaBusca() {
        String pesquisa = this.viewConsultaMedicao.getTextFieldSearch().getText();
//        String query    = "(e.nome LIKE '%" + pesquisa + "%' OR ";
//               query   += " e.rotulo LIKE '%" + pesquisa + "%' OR ";
//               query   += " e.descricao LIKE '%" + pesquisa + "%')";
        String query    = " e.id > 0";
               query   += this.getClausulaDiagramaAlvo();
        return query;
    }
    
    /**
     * Metodo responsavel por retornar a Clausula de Pesquisa do Diagrama Alvo.
     * @return Clausula de Pesquisa do Diagrama Alvo.
     */
    private String getClausulaDiagramaAlvo() {
        String pesquisa = this.viewConsultaMedicao.getComboBoxDiagramaAlvo().getSelectedItem().toString();
        String query    = "";
        if (pesquisa.equals("Selecione") == false)
            query = " AND e.diagramaAlvo = '" + pesquisa + "'";
        return query;
    }
    
    @Override
    public void novo() {}

    @Override
    public void edit() {
        int index = this.viewConsultaMedicao.getTable().getSelectedRow();
        int total = this.medicoes.size();
        if ((index >= 0) && (index < total)) {
//            new ViewEditarMetrica(this.viewConsultaMedicao, this.medicoes.get(index)).setVisible(true);
        }else {
            new ViewErro(this.viewConsultaMedicao, "Selecione uma Métrica!").setVisible(true);
            this.viewConsultaMedicao.getTextFieldSearch().requestFocus();
        }
    }

    @Override
    public void remove() {
        int index = this.viewConsultaMedicao.getTable().getSelectedRow();
        int total = this.medicoes.size();
        if ((index >= 0) && (index < total)) {
            new ViewRemoverMedicao(this.viewConsultaMedicao, this.medicoes.get(index)).setVisible(true);
        }else {
            new ViewErro(this.viewConsultaMedicao, "Selecione uma Métrica!").setVisible(true);
            this.viewConsultaMedicao.getTextFieldSearch().requestFocus();
        }
    }
}