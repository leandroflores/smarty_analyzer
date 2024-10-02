package visao.frames.avaliar;

import javax.swing.JTextField;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;
import visao.controller.frames.avaliar.ControllerViewAvaliarDiagramasClassePorMetrica;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewAvaliarDiagramasClassePorMetrica extends ViewAvaliar {
    private JTextField textFieldResultado1;
    private DiagramaClasse diagramaClasse1;
    private JTextField textFieldResultado2;
    private DiagramaClasse diagramaClasse2;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAvaliarDiagramasClassePorMetrica(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAvaliarDiagramasClassePorMetrica(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(SYSTEM + "Avaliar Diagramas de Classe");
        this.setSize(1000, 605);
        this.addHeader();
        this.addComponents();
        this.addButtons();
    }

    @Override
    public void addHeader() {
        super.addHeader("avaliar_diagramas_classe.jpg");
    }

    @Override
    public void addComponents() {
        this.createTextField();
        this.addSearch();
        this.addPanel();
        this.addTextFieldMetrica();
        this.addTextFieldResultados();
    }
    
    /**
     * Metodo responsavel por adicionar os Campos de Pesquisa.
     */
    private void addSearch() {
        this.addLinhas(1);
        
        this.addSearchTextField1();
        this.add(this.createLabel("     "));
        this.addSearchTextField2();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar os Paineis de comparacao.
     */
    private void addPanel() {
        this.addPanelDiagramaClasse1();
        this.add(this.createLabel("     "));
        this.addPanelDiagramaClasse2();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Text Field da Metrica.
     */
    private void addTextFieldMetrica() {
        this.addSearchMetrica();
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar o Text Field dos Resultados.
     */
    private void addTextFieldResultados() {
        this.textFieldResultado1 = this.createTextFieldNoEditable("", 27);
        this.textFieldResultado2 = this.createTextFieldNoEditable("", 27);
        
        this.add(this.createLabel("Resultado: "));
        this.add(this.textFieldResultado1);
        this.add(this.createLabel("      "));
        this.add(this.createLabel("Resultado: "));
        this.add(this.textFieldResultado2);
        this.add(this.createLabel("  "));
        
        this.addLinhas(1);
    }
    
    public void setDiagramaClasse1(DiagramaClasse diagramaClasse) {
        if (diagramaClasse != null) {
            this.diagramaClasse1 = diagramaClasse;
            this.setDadosPanel1();
        }
    }
    
    public void setDiagramaClasse2(DiagramaClasse diagramaClasse) {
        if (diagramaClasse != null) {
            this.diagramaClasse2 = diagramaClasse;
            this.setDadosPanel2();
        }
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     */
    public void setDadosPanel1() {
        if (this.diagramaClasse1 != null)
            this.setPanelDiagramaClasse1(new MetricaDiagramaClasse(this.diagramaClasse1).getDados());
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 2.
     */
    public void setDadosPanel2() {
        if (this.diagramaClasse2 != null)
            this.setPanelDiagramaClasse2(new MetricaDiagramaClasse(this.diagramaClasse2).getDados());
    }

    /**
     * Metodo responsavel por retornar o Text Field de Resultado 1.
     * @return Text Field de Resultado 1.
     */
    public JTextField getTextFieldResultado1() {
        return this.textFieldResultado1;
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Classe 1.
     * @return Diagrama de Classe 1.
     */
    public DiagramaClasse getDiagramaClasse1() {
        return this.diagramaClasse1;
    }

    /**
     * Metodo responsavel por retornar o Text Field de Resultado 2.
     * @return Text Field de Resultado 2.
     */
    public JTextField getTextFieldResultado2() {
        return this.textFieldResultado2;
    }

    /**
     * Metodo responsavel por retornar o Diagrama de Classe 2.
     * @return Diagrama de Classe 2.
     */
    public DiagramaClasse getDiagramaClasse2() {
        return this.diagramaClasse2;
    }
}