package visao.controller.frames.avaliar.medicao;

import java.util.List;
import modelo.controller.entidades.ControllerMedicao;
import modelo.dao.entidades.DaoMedicao;
import visao.frames.avaliar.medicao.ViewAvaliarMedicaoDiagramaClasse;
import visao.frames.mensagem.ViewErro;
import visao.frames.pesquisa.ViewPesquisaMetrica;

/**
 *
 * @author Leandro
 */
public class ControllerViewAvaliarMedicaoDiagramaClasse extends ControllerViewAvaliarMedicao {
    private final ViewAvaliarMedicaoDiagramaClasse viewAvaliarMedicaoDiagramaClasse;
    private static final ControllerMedicao CONTROLLER_MEDICAO = new ControllerMedicao();
    private static final DaoMedicao DAO_MEDICAO = new DaoMedicao();

    public ControllerViewAvaliarMedicaoDiagramaClasse(ViewAvaliarMedicaoDiagramaClasse viewAvaliarMedicaoDiagramaClasse) {
        super(viewAvaliarMedicaoDiagramaClasse);
        this.viewAvaliarMedicaoDiagramaClasse = viewAvaliarMedicaoDiagramaClasse;
    }

    @Override
    protected void searchMetrica() {
        new ViewPesquisaMetrica(this.viewAvaliarMedicaoDiagramaClasse, "DIAGRAMA DE CLASSE").setVisible(true);
    }

    @Override
    protected void update() {
        Double[]     valores = CONTROLLER_MEDICAO.getValores(this.viewAvaliarMedicaoDiagramaClasse.getClausula());
        List<Double> dados   = DAO_MEDICAO.getValores(this.viewAvaliarMedicaoDiagramaClasse.getClausula());
        Double       mediana = CONTROLLER_MEDICAO.getMediana(this.viewAvaliarMedicaoDiagramaClasse.getClausula());
        String       moda    = CONTROLLER_MEDICAO.getModa(this.viewAvaliarMedicaoDiagramaClasse.getClausula());
        if (dados.isEmpty()) {
            new ViewErro(this.viewAvaliarMedicaoDiagramaClasse, "Nenhuma Medicao encontrada!").setVisible(true);
            this.viewAvaliarMedicaoDiagramaClasse.clearPanelInformacoes();
        }else {
            this.viewAvaliarMedicaoDiagramaClasse.setPanelInformacoes(valores, moda, mediana);
            this.viewAvaliarMedicaoDiagramaClasse.setPanelGrafico(dados);
        }
    }
}