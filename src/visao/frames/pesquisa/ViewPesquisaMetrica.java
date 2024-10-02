package visao.frames.pesquisa;

import visao.controller.frames.pesquisa.ControllerViewPesquisaMetrica;
import visao.frames.avaliar.ViewAvaliar;
import visao.frames.avaliar.medicao.ViewAvaliarMedicao;
import visao.frames.consulta.ViewConsultaMedicao;
import visao.frames.processar.ViewProcessar;

/**
 * <p>Classe de Visao <b>ViewPesquisaMetrica</b>.</p>
 * <p>Classe responsavel por definir a Interface de Pesquisa de Metricas.</p>
 * @author Leandro
 * @since  16/09/2016
 * @see    controller.view.pesquisa.ControllerViewPesquisaMetrica
 * @see    view.pesquisa.ViewPesquisa
 */
public final class ViewPesquisaMetrica extends ViewPesquisa {
    private final ViewAvaliar         viewAvaliar;
    private final ViewAvaliarMedicao  viewAvaliarMedicao;
    private final ViewConsultaMedicao viewConsultaMedicao;
    private final ViewProcessar       viewProcessar;
    private final String tipo;
    private static final String[] COLUNAS         = {"Id", "Nome", "Operacao"};
    private static final int[]    TAMANHO_COLUNAS = {25, 200, 100};

    /**
     * Metodo construtor padrao da Classe.
     * @param viewProcessar View Processar do Sistema.
     * @param tipo Tipo da Metrica.
     */
    public ViewPesquisaMetrica(ViewProcessar viewProcessar, String tipo) {
        super(viewProcessar);
        this.viewAvaliar         = null;
        this.viewAvaliarMedicao  = null;
        this.viewConsultaMedicao = null;
        this.viewProcessar       = viewProcessar;
        this.tipo                = tipo;
        this.controller          = new ControllerViewPesquisaMetrica(this);
        this.initComponents();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param viewAvaliar View Avaliar do Sistema.
     * @param tipo Tipo da Metrica.
     */
    public ViewPesquisaMetrica(ViewAvaliar viewAvaliar, String tipo) {
        super(viewAvaliar);
        this.viewAvaliar         = viewAvaliar;
        this.viewAvaliarMedicao  = null;
        this.viewConsultaMedicao = null;
        this.viewProcessar       = null;
        this.tipo                = tipo;
        this.controller          = new ControllerViewPesquisaMetrica(this);
        this.initComponents();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param viewAvaliarMedicao View Avaliar Medicao do Sistema.
     * @param tipo Tipo da Metrica.
     */
    public ViewPesquisaMetrica(ViewAvaliarMedicao viewAvaliarMedicao, String tipo) {
        super(viewAvaliarMedicao);
        this.viewAvaliar         = null;
        this.viewAvaliarMedicao  = viewAvaliarMedicao;
        this.viewConsultaMedicao = null;
        this.viewProcessar       = null;
        this.tipo                = tipo;
        this.controller          = new ControllerViewPesquisaMetrica(this);
        this.initComponents();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param viewConsultaMedicao View Consulta Medicao do Sistema.
     * @param tipo Tipo da Metrica.
     */
    public ViewPesquisaMetrica(ViewConsultaMedicao viewConsultaMedicao, String tipo) {
        super(viewConsultaMedicao);
        this.viewAvaliar         = null;
        this.viewAvaliarMedicao  = null;
        this.viewConsultaMedicao = viewConsultaMedicao;
        this.viewProcessar       = null;
        this.tipo                = tipo;
        this.controller          = new ControllerViewPesquisaMetrica(this);
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(550, 485);
        this.setTitle(SYSTEM + "Pesquisar Metrica");
        this.addHeader();
        this.addComponents();
        this.addButtons();
        this.clear();
    }
    
    @Override
    public void addHeader() {
        super.addHeader("pesquisa_metrica.jpg");
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Metrica: "));
        this.addCampos();
        this.addLinhas(1);
        this.addTable();
        this.setColumns(COLUNAS, TAMANHO_COLUNAS);
    }
    
    @Override
    public void clear() {
        this.textFieldPesquisa.setText("");
        this.textFieldPesquisa.requestFocus();
        ((ControllerViewPesquisaMetrica) this.controller).search();
    }
    
    /**
     * Metodo responsavel por retornar a View Avaliar.
     * @return View Avaliar.
     */
    public ViewAvaliar getViewAvaliar() {
        return this.viewAvaliar;
    }
    
    /**
     * Metodo responsavel por retornar a View Avaliar Medicao.
     * @return View Avaliar Medicao.
     */
    public ViewAvaliarMedicao getViewAvaliarMedicao() {
        return this.viewAvaliarMedicao;
    }
    
    /**
     * Metodo responsavel por retornar a View Consultar Medicao.
     * @return View Consultar Medicao.
     */
    public ViewConsultaMedicao getViewConsultaMedicao() {
        return this.viewConsultaMedicao;
    }
    
    /**
     * Metodo responsavel por retornar a View Processar.
     * @return View Processar.
     */
    public ViewProcessar getViewProcessar() {
        return this.viewProcessar;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo da Busca.
     * @return Tipo da Busca.
     */
    public String getTipo() {
        return this.tipo;
    }
}