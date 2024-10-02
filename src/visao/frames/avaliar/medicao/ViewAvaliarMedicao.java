package visao.frames.avaliar.medicao;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.entidades.Metrica;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;
import visao.grafico.boxplot.GraficoBoxPlot;

/**
 * <p>Classe de Visao <b>ViewAvaliarMedicao</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Avaliacao de Medicao</b>.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewAvaliarMedicao extends ViewModal implements Viewable {
    private final static String[] OPCOES = {"TODAS", "ENTRE 0 E 5", "ENTRE 5 e 10", "ENTRE 10 e 25", "ENTRE 25 e 50", "MAIS QUE 50"};
    protected String tipo;
    
    protected JTextField textFieldMetrica;
    protected Metrica    metrica;
    
    private JComboBox  comboBoxInfo1;
    private JComboBox  comboBoxInfo2;
    private JTextField textFieldNome;
    
    // Dados do Panel 1:
    protected JTextField textFieldDados1;
    protected JTextField textFieldDados2;
    protected JTextField textFieldDados3;
    protected JTextField textFieldDados4;
    protected JTextField textFieldDados5;
    protected JTextField textFieldDados6;
    protected JTextField textFieldDados7;

    protected JPanel panelGrafico;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAvaliarMedicao(ViewMenu viewMenu) {
        super(viewMenu);
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("avaliar/" + url);
    }
    
    /**
     * Metodo responsavel por Adicionar o Campo de Busca de Metricas do Sistema.
     */
    protected void addSearchMetrica() {
        this.textFieldMetrica = this.createTextField("", 60);
        this.textFieldMetrica.setEditable(false);
        
        this.buttonAction1 = this.createButton("", "search.jpg");
        
        this.add(this.createLabel("Metrica*: "));
        this.add(this.textFieldMetrica);
        this.add(this.buttonAction1);
    }
    
    protected void addFiltrosDiagramaClasse() {
        this.comboBoxInfo1    = this.createComboBox(OPCOES);
        this.comboBoxInfo1.setPreferredSize(new Dimension(175, 30));
        this.add(this.createLabel("   "));
        this.add(this.createLabel("Classes: "));
        this.add(this.comboBoxInfo1);
        
        this.comboBoxInfo2 = this.createComboBox(OPCOES);
        this.comboBoxInfo2.setPreferredSize(new Dimension(175, 30));
        this.add(this.createLabel("Interfaces: "));
        this.add(this.comboBoxInfo2);
        
        this.textFieldNome      = this.createTextField("", 20);
        this.add(this.createLabel("   "));
        this.add(this.createLabel("Nome*: "));
        this.add(this.textFieldNome);
        
        this.buttonAction2      = this.createButton("", "update.jpg");
        this.add(this.buttonAction2);
        this.add(this.createLabel("   "));
    }
    
    protected void addFiltrosDiagramaCasoDeUso() {
        this.comboBoxInfo1    = this.createComboBox(OPCOES);
        this.comboBoxInfo1.setPreferredSize(new Dimension(175, 30));
        this.add(this.createLabel("       "));
        this.add(this.createLabel("Atores: "));
        this.add(this.comboBoxInfo1);
        
        this.comboBoxInfo2 = this.createComboBox(OPCOES);
        this.comboBoxInfo2.setPreferredSize(new Dimension(175, 30));
        this.add(this.createLabel("Casos de Uso: "));
        this.add(this.comboBoxInfo2);
        
        this.textFieldNome      = this.createTextField("", 19);
        this.add(this.createLabel("Nome*: "));
        this.add(this.textFieldNome);
        
        this.buttonAction2      = this.createButton("", "update.jpg");
        this.add(this.buttonAction2);
        this.add(this.createLabel("   "));
    }
    
    /**
     * Metodo responsavel por retornar a Clausula de Busca.
     * @return Clausula de Busca.
     */
    public String getClausula() {
        String clausula  = "e.diagramaAlvo = '" + this.tipo + "'";
               clausula += this.getClausulaMetrica();
               clausula += this.getClausulaComboBox();
               clausula += this.getClausulaNome();
        return clausula;
    }
    
    /**
     * Metodo responsavel por retornar a Clausula da Metrica.
     * @return Clausula da Metrica.
     */
    private String getClausulaMetrica() {
        if (this.metrica != null)
            return " AND e.metrica.id = " + this.metrica.getId();
        return "";
    }
    
    /**
     * Metodo responsavel por retornar a Clausula do Combo Box.
     * @return Clausula do Combo Box.
     */
    private String getClausulaComboBox() {
        String clausula  = "";
               clausula += this.getClausulaComboBox("e.valor1", this.comboBoxInfo1);
               clausula += this.getClausulaComboBox("e.valor2", this.comboBoxInfo2);
        return clausula;
    }
    
    /**
     * Metodo responsavel por retornar a Clausula do Combo Box.
     * @param  campo Campo do Hibernate.
     * @param  comboBox Combo Box.
     * @return Clausula do Combo Box.
     */
    private String getClausulaComboBox(String campo, JComboBox comboBox) {
        if (comboBox.getSelectedIndex() == 1)
            return " AND " + campo + " > 0 AND " + campo  + " <= 5";
        if (comboBox.getSelectedIndex() == 2)
            return " AND " + campo + " > 5 AND " + campo  + " <= 10";
        if (comboBox.getSelectedIndex() == 3)
            return " AND " + campo + " > 10 AND " + campo + " <= 25";
        if (comboBox.getSelectedIndex() == 4)
            return " AND " + campo + " > 25 AND " + campo + " <= 50";
        if (comboBox.getSelectedIndex() == 5)
            return " AND " + campo + " > 50";
        return "";
    }
    
    /**
     * Metodo responsavel por retornar a Clausula Nome.
     * @return Clausula Nome.
     */
    private String getClausulaNome() {
        String nome = this.textFieldNome.getText().trim().toUpperCase();
        if (nome.equals("") == false)
            return " AND e.nome = '" + nome + "'";
        return "";
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 1 na View Avaliar.
     */
    protected void addPanelInformacao() {
        JPanel panel = this.createPanel();
               panel.add(this.getLinha());
               panel.add(this.createLabel("Quantidade: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("          "));
               panel.add(this.createLabel("Soma: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("          "));
               panel.add(this.createLabel("Maior: "));
               panel.add(this.textFieldDados3);
               panel.add(this.createLabel("         "));
               panel.add(this.createLabel("Menor: "));
               panel.add(this.textFieldDados4);
               panel.add(this.createLabel("          "));
               panel.add(this.createLabel("Media: "));
               panel.add(this.textFieldDados5);
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Moda: "));
               panel.add(this.textFieldDados6);
               panel.add(this.createLabel("       "));
               panel.add(this.createLabel("Mediana: "));
               panel.add(this.textFieldDados7);
               panel.setPreferredSize(new Dimension(325, 290));
        this.add(panel);
    }
    
    /**
     * 
     */
    protected void addPanelGrafico() {
        this.panelGrafico = this.createPanel();
        this.panelGrafico.setPreferredSize(new Dimension(375, 290));
        this.add(this.panelGrafico);
    }
    
    /**
     * Metodo responsavel por limpar os Dados do Painel 1.
     */
    public void clearPanelInformacoes() {
        this.textFieldDados1.setText("");
        this.textFieldDados2.setText("");
        this.textFieldDados3.setText("");
        this.textFieldDados4.setText("");
        this.textFieldDados5.setText("");
        this.textFieldDados6.setText("");
        this.textFieldDados7.setText("");
        
        this.panelGrafico.removeAll();
        this.panelGrafico.revalidate();
        this.panelGrafico.repaint();
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     * @param dados Dados a serem atualizados.
     * @param mediana Mediana dos Valores.
     * @param moda Moda dos Valores.
     */
    public void setPanelInformacoes(Double[] dados, String moda, Double mediana) {
        this.textFieldDados1.setText(this.getValor(dados[0])); // Tamanho
        this.textFieldDados2.setText(this.getValor(dados[1])); // Soma
        this.textFieldDados3.setText(this.getValor(dados[3])); // Media
        this.textFieldDados4.setText(this.getValor(dados[4])); // Maximo
        this.textFieldDados5.setText(this.getValor(dados[2])); // Minimo
        this.textFieldDados6.setText(this.getString(moda));
        this.textFieldDados7.setText(this.getValor(mediana)); 
    }
    
    public void setPanelGrafico(List<Double> valores) {
        this.panelGrafico.removeAll();
        this.panelGrafico.repaint();

        this.panelGrafico.add(new GraficoBoxPlot(valores).getChart());
        this.panelGrafico.validate();
        this.panelGrafico.repaint();
    }
    
    private String getString(String string) {
        if (string.length() > 15)
            return string.substring(0, 15);
        return string;
    }
    
    private String getValor(Double valor) {
        String toReturn = Double.toString(valor);
        if (toReturn.length() > 15)
            return toReturn.substring(0, 15);
        return toReturn;
    }
    
    /**
     * Metodo responsavel por criar os Dados do Diagrama de Classe.
     */
    protected void createTextField() {
        this.textFieldDados1 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados2 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados3 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados4 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados5 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados6 = this.createTextFieldNoEditable("", 15);
        this.textFieldDados7 = this.createTextFieldNoEditable("", 15);
    }
        
    @Override
    public void addButtons() {
        this.buttonAction3 = this.createButton(" Atualizar ", "update.jpg");
        this.buttonAction4 = this.createButton("   Voltar  ", "back.jpg");
        
        this.add(this.buttonAction3);
        this.add(this.buttonAction4);
    }
    
    /**
     * Metodo responsavel por retornar a Metrica da View Avaliar.
     * @return Metrica da View Avaliar.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }
    
    /**
     * Metodo responsavel por definir a Metrica da View Avaliar.
     * @param metrica Metrica a ser definida.
     */
    public void setMetrica(Metrica metrica) {
        if (metrica != null) {
            this.metrica = metrica;
            this.textFieldMetrica.setText(this.metrica.toString());
        }
    }
        
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Avaliar Medicao.
     * @return Botao de Pesquisa da Metrica da View Avaliar Medicao.
     */
    public JButton getButtonSearchMetrica() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Combo Box 1 da View Avaliar Medicao.
     * @return Combo Box 1 da View Avaliar Medicao.
     */
    public JComboBox getComboBoxInfo1() {
        return this.comboBoxInfo1;
    }
    
    /**
     * Metodo responsavel por retornar o Combo Box 2 da View Avaliar Medicao.
     * @return Combo Box 2 da View Avaliar Medicao.
     */
    public JComboBox getComboBoxInfo2() {
        return this.comboBoxInfo2;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field Nome da View Avaliar Medicao.
     * @return Text Field Nome da View Avaliar Medicao.
     */
    public JTextField getTextFieldNome() {
        return this.textFieldNome;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa do Modelo UML da View Analisar.
     * @return Botao de Pesquisa do Modelo UML da View Analisar.
     */
    public JButton getButtonUpdate() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Analisar.
     * @return Botao de Pesquisa da Metrica da View Analisar.
     */
    public JButton getButtonRefresh() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Analisar.
     * @return Botao de Pesquisa da Metrica da View Analisar.
     */
    public JButton getButtonBack() {
        return this.buttonAction4;
    }
}