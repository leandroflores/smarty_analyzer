package visao.controller.frames.avaliar.medicao;

import java.util.List;
import modelo.controller.entidades.ControllerMedicao;
import modelo.dao.entidades.DaoMedicao;
import visao.frames.avaliar.medicao.ViewAvaliarMedicaoDiagramaCasoDeUso;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;

/**
 *
 * @author Leandro
 */
public class ControllerViewAvaliarMedicaoDiagramaCasoDeUso extends ControllerViewAvaliarMedicao {
    private final ViewAvaliarMedicaoDiagramaCasoDeUso viewAvaliarMedicaoDiagramaCasoDeUso;
    private static final ControllerMedicao CONTROLLER_MEDICAO = new ControllerMedicao();
    private static final DaoMedicao DAO_MEDICAO = new DaoMedicao();

    public ControllerViewAvaliarMedicaoDiagramaCasoDeUso(ViewAvaliarMedicaoDiagramaCasoDeUso viewAvaliarMedicaoDiagramaCasoDeUso) {
        super(viewAvaliarMedicaoDiagramaCasoDeUso);
        this.viewAvaliarMedicaoDiagramaCasoDeUso = viewAvaliarMedicaoDiagramaCasoDeUso;
    }

    @Override
    protected void searchMetrica() {
        new ViewPesquisaMetrica(this.viewAvaliarMedicaoDiagramaCasoDeUso, "DIAGRAMA DE CASO DE USO").setVisible(true);
    }

    @Override
    protected void update() {
        Double[]     valores = CONTROLLER_MEDICAO.getValores(this.viewAvaliarMedicaoDiagramaCasoDeUso.getClausula());
        List<Double> dados   = DAO_MEDICAO.getValores(this.viewAvaliarMedicaoDiagramaCasoDeUso.getClausula());
        Double       mediana = CONTROLLER_MEDICAO.getMediana(this.viewAvaliarMedicaoDiagramaCasoDeUso.getClausula());
        String       moda    = CONTROLLER_MEDICAO.getModa(this.viewAvaliarMedicaoDiagramaCasoDeUso.getClausula());
        if (dados.isEmpty()) {
            new ViewErro(this.viewAvaliarMedicaoDiagramaCasoDeUso, "Nenhuma Medicao encontrada!").setVisible(true);
            this.viewAvaliarMedicaoDiagramaCasoDeUso.clearPanelInformacoes();
        }else {
            this.viewAvaliarMedicaoDiagramaCasoDeUso.setPanelInformacoes(valores, moda, mediana);
            this.viewAvaliarMedicaoDiagramaCasoDeUso.setPanelGrafico(dados);
        }
    }
}