package visao.frames.avaliar;

import javax.swing.JButton;
import javax.swing.JTextField;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;
import visao.controller.frames.avaliar.ControllerViewAvaliarDiagramasClasse;
import visao.frames.estruturais.ViewMenu;

/**
 *
 * @author Leandro
 */
public final class ViewAvaliarDiagramasClasse extends ViewAvaliar {
    private JTextField     textFieldOperacao1;
    private JButton        buttonAtualizar1;
    private JTextField     textFieldResultado1;
    private DiagramaClasse diagramaClasse1;
            
    private JTextField     textFieldOperacao2;
    private JButton        buttonAtualizar2;
    private JTextField     textFieldResultado2;
    private DiagramaClasse diagramaClasse2;
    
    private MetricaDiagramaClasse metricaDiagramaClasse;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAvaliarDiagramasClasse(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewAvaliarDiagramasClasse(this);
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
        this.addHeader("avaliar_diagramas_classe.jpg");
    }

    @Override
    public void addComponents() {
        this.createTextField();
        this.addSearch();
        this.addPanel();
        this.addTextFieldOperacoes();
        this.addTextFieldResultados();
    }
    
    /**
     * Metodo responsavel por adicionar os Campos de Busca do Arquivo.
     */
    private void addSearch() {
        this.addLinhas(1);
        
        this.addSearchTextField1();
        this.add(this.createLabel("     "));
        this.addSearchTextField2();
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar os Paineis de Comparacao na View.
     */
    private void addPanel() {
        this.addPanelDiagramaClasse1();
        this.add(this.createLabel("     "));
        this.addPanelDiagramaClasse2();
        
        this.addLinhas(1);
    }

    /**
     * Metodo responsavel por adicionar os Campos de Operacoes na View.
     */
    private void addTextFieldOperacoes() {
        this.textFieldOperacao1 = this.createTextField("", 23);
        this.textFieldOperacao2 = this.createTextField("", 23);
        
        this.buttonAtualizar1   = this.createButton("", "update.jpg");
        this.buttonAtualizar2   = this.createButton("", "update.jpg");
        
        this.add(this.createLabel("Operacao: "));
        this.add(this.textFieldOperacao1);
        this.add(this.buttonAtualizar1);
        this.add(this.createLabel("     "));
        this.add(this.createLabel("Operacao: "));
        this.add(this.textFieldOperacao2);
        this.add(this.buttonAtualizar2);
        
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por adicionar os Campos de Resultados na View.
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
    
    /**
     * Metodo responsavel por definir o Diagrama de Classe 1 da View.
     * @param diagramaClasse Diagrama de Classe 1 da View.
     */
    public void setDiagramaClasse1(DiagramaClasse diagramaClasse) {
        if (diagramaClasse != null) {
            this.diagramaClasse1 = diagramaClasse;
            this.setDadosPanel1();
        }
    }
    
    /**
     * Metodo responsavel por definir o Diagrama de Classe 2 da View.
     * @param diagramaClasse Diagrama de Classe 2 da View.
     */
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
    
    public void update1() {
        if (this.diagramaClasse1 != null) {
            String operacao1 = this.textFieldOperacao1.getText().trim();
        }
    }
    
    public DiagramaClasse getDiagramaClasse1() {
        return this.diagramaClasse1;
    }
    
    public JTextField getTextFieldOperacao1() {
        return this.textFieldOperacao1;
    }
    
    public JButton getButtonAtualizar1() {
        return this.buttonAtualizar1;
    }
    
    public JTextField getTextFieldResultado1() {
        return this.textFieldResultado1;
    }
    
    public DiagramaClasse getDiagramaClasse2() {
        return this.diagramaClasse2;
    }
    
    public JTextField getTextFieldOperacao2() {
        return this.textFieldOperacao2;
    }
    
    public JButton getButtonAtualizar2() {
        return this.buttonAtualizar2;
    }
    
    public JTextField getTextFieldResultado2() {
        return this.textFieldResultado2;
    }
}