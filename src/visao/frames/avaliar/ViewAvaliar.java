package visao.frames.avaliar;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.entidades.Metrica;
import visao.frames.ViewModal;
import visao.frames.Viewable;
import visao.frames.estruturais.ViewMenu;

/**
 * <p>Classe de Visao <b>ViewAvaliar</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Analise</b>.</p>
 * @author Leandro
 * @since  26/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewAvaliar extends ViewModal implements Viewable {
    protected JTextField   textFieldModeloUML1;
    private   JFileChooser fileChooser1;
    protected JTextField   textFieldModeloUML2;
    private   JFileChooser fileChooser2;

    protected JTextField textFieldMetrica;    
    protected Metrica    metrica;

    private   JButton      buttonUpdate;
    private   JButton      buttonBack;
    
    // Dados do Panel 1:
    protected JTextField textFieldDados1;
    protected JTextField textFieldDados2;
    protected JTextField textFieldDados3;
    protected JTextField textFieldDados4;
    protected JTextField textFieldDados5;
    
    // Dados do Panel 2:
    protected JTextField textFieldDados6;
    protected JTextField textFieldDados7;
    protected JTextField textFieldDados8;
    protected JTextField textFieldDados9;
    protected JTextField textFieldDados10;

    /**
     * Metodo construtor padrao da Classe.
     * @param viewMenu View Menu do Sistema.
     */
    public ViewAvaliar(ViewMenu viewMenu) {
        super(viewMenu);
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("avaliar/" + url);
    }

    /**
     * Metodo responsavel por adicionar o Campo de Busca do Modelo UML.
     */
    protected void addSearchTextField1() {
        this.textFieldModeloUML1 = this.createTextField("", 30);
        this.textFieldModeloUML1.setEditable(false);
        
        this.fileChooser1 = new JFileChooser();
        this.fileChooser1.setLocation(50, 50);
        this.fileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.buttonAction1 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldModeloUML1);
        this.add(this.buttonAction1);
    }
    
    /**
     * Metodo responsavel por adicionar o Campo de Busca do Modelo UML.
     */
    protected void addSearchTextField2() {
        this.textFieldModeloUML2 = this.createTextField("", 30);
        this.textFieldModeloUML2.setEditable(false);
        
        this.fileChooser2 = new JFileChooser();
        this.fileChooser2.setLocation(50, 50);
        this.fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.buttonAction2 = this.createButton("", "search.jpg");
        
        this.add(this.textFieldModeloUML2);
        this.add(this.buttonAction2);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 1 na View Avaliar.
     */
    protected void addPanelDiagramaClasse1() {
        JPanel panel = this.createPanel();
               panel.add(this.createLabel("    Pacotes: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("    Classes: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Interfaces: "));
               panel.add(this.textFieldDados3);
               panel.add(this.createLabel("   Metodos: "));
               panel.add(this.textFieldDados4);
               panel.add(this.createLabel("  Atributos: "));
               panel.add(this.textFieldDados5);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaClasse1(String[] dados) {
        this.textFieldDados1.setText(dados[0]);
        this.textFieldDados2.setText(dados[1]);
        this.textFieldDados3.setText(dados[2]);
        this.textFieldDados4.setText(dados[3]);
        this.textFieldDados5.setText(dados[4]);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 1 na View Avaliar.
     */
    protected void addPanelDiagramaCasoDeUso1() {
        JPanel panel = this.createPanel();
               panel.setPreferredSize(new Dimension(455, 115));
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Pastas: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Atores: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Casos de Uso: "));
               panel.add(this.textFieldDados3);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaCasoDeUso1(String[] dados) {
        this.textFieldDados1.setText(dados[0]);
        this.textFieldDados2.setText(dados[1]);
        this.textFieldDados3.setText(dados[2]);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 2 na View Avaliar.
     */
    protected void addPanelDiagramaClasse2() {
        JPanel panel = this.createPanel();
               panel.add(this.createLabel("    Pacotes: "));
               panel.add(this.textFieldDados6);
               panel.add(this.createLabel("    Classes: "));
               panel.add(this.textFieldDados7);
               panel.add(this.createLabel("Interfaces: "));
               panel.add(this.textFieldDados8);
               panel.add(this.createLabel("   Metodos: "));
               panel.add(this.textFieldDados9);
               panel.add(this.createLabel("  Atributos: "));
               panel.add(this.textFieldDados10);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 2.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaClasse2(String[] dados) {
        this.textFieldDados6.setText(dados[0]);
        this.textFieldDados7.setText(dados[1]);
        this.textFieldDados8.setText(dados[2]);
        this.textFieldDados9.setText(dados[3]);
        this.textFieldDados10.setText(dados[4]);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 2 na View Avaliar.
     */
    protected void addPanelDiagramaCasoDeUso2() {
        JPanel panel = this.createPanel();
               panel.setPreferredSize(new Dimension(455, 115));
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Pastas: "));
               panel.add(this.textFieldDados4);
               panel.add(this.createLabel("            "));
               panel.add(this.createLabel("Atores: "));
               panel.add(this.textFieldDados5);
               panel.add(this.createLabel("Casos de Uso: "));
               panel.add(this.textFieldDados6);
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por atualizar os Dados do Painel 1.
     * @param dados Dados a serem atualizados.
     */
    protected void setPanelDiagramaCasoDeUso2(String[] dados) {
        this.textFieldDados4.setText(dados[0]);
        this.textFieldDados5.setText(dados[1]);
        this.textFieldDados6.setText(dados[2]);
    }
    
    /**
     * Metodo responsavel por adicionar o JPanel 1 na View Avaliar.
     */
    protected void addPanelMedicoesDiagramaClasse() {
        JPanel panel = this.createPanel();
               panel.add(this.createLabel(" Total: "));
               panel.add(this.textFieldDados1);
               panel.add(this.createLabel(" Maior: "));
               panel.add(this.textFieldDados2);
               panel.add(this.createLabel("Menor: "));
               panel.add(this.textFieldDados3);
               panel.add(this.createLabel("Media: "));
               panel.add(this.textFieldDados4);
               panel.add(this.createLabel(" Moda: "));
               panel.add(this.textFieldDados5);
               this.setSize();
               panel.setPreferredSize(new Dimension(250, 185));
        this.add(panel);
    }
    
    protected void addPanelGrafico() {
        JPanel panel = this.createPanel();
               panel.setPreferredSize(new Dimension(525, 185));
        this.add(panel);
    }
    
    /**
     * Metodo responsavel por definir um novo tamanho para os Text Fields da Medicao.
     */
    private void setSize() {
        this.textFieldDados1.setColumns(10);
        this.textFieldDados2.setColumns(10);
        this.textFieldDados3.setColumns(10);
        this.textFieldDados4.setColumns(10);
        this.textFieldDados5.setColumns(10);
    }
    
    /**
     * Metodo responsavel por criar os Dados do Diagrama de Classe.
     */
    protected void createTextField() {
        this.textFieldDados1  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados2  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados3  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados4  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados5  = this.createTextFieldNoEditable("", 25);
        
        this.textFieldDados6  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados7  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados8  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados9  = this.createTextFieldNoEditable("", 25);
        this.textFieldDados10 = this.createTextFieldNoEditable("", 25);
    }
    
    /**
     * Metodo responsavel por Adicionar o Campo de Busca de Metricas do Sistema.
     */
    protected void addSearchMetrica() {
        this.textFieldMetrica = this.createTextField("", 60);
        this.textFieldMetrica.setEditable(false);
        
        this.buttonAction3 = this.createButton("", "search.jpg");
        
        this.add(this.createLabel("Metrica*: "));
        this.add(this.textFieldMetrica);
        this.add(this.buttonAction3);
    }
        
    @Override
    public void addButtons() {
        this.buttonUpdate = this.createButton(" Atualizar ", "update.jpg");
        this.buttonBack   = this.createButton("   Voltar  ", "back.jpg");
        
        this.add(this.buttonUpdate);
        this.add(this.buttonBack);
    }
    
    /**
     * Metodo responsavel por retornar a Metrica da View Avaliar.
     * @return Metrica da View Avaliar.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }
    
    /**
     * Metodo responsavel por retornar a Metrica da View Analisar.
     * @return Metrica da View Analisar.
     */
//    public Metrica getMetrica2() {
//        return this.metrica2;
//    }
    
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
     * Metodo responsavel por definir a Metrica da View Analisar.
     * @param metrica Metrica a ser definida.
     */
//    public void setMetrica2(Metrica metrica) {
//        if (metrica != null) {
//            this.metrica2 = metrica;
//            this.textFieldMetrica2.setText(this.metrica2.toString());
//        }
//    }
    
    /**
     * Metodo responsavel por retornar o TextField do Modelo UML da View Analisar.
     * @return TextField do Modelo UML da View Analisar.
     */
    public JTextField getTextFieldModeloUML1() {
        return this.textFieldModeloUML1;
    }

    /**
     * Metodo responsavel por retornar o TextField do Modelo UML da View Analisar.
     * @return TextField do Modelo UML da View Analisar.
     */
    public JTextField getTextFieldModeloUML2() {
        return this.textFieldModeloUML2;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser da View Analisar.
     * @return File Chooser da View Analisar.
     */
    public JFileChooser getFileChooser1() {
        return this.fileChooser1;
    }
    
    /**
     * Metodo responsavel por retornar o File Chooser da View Analisar.
     * @return File Chooser da View Analisar.
     */
    public JFileChooser getFileChooser2() {
        return this.fileChooser2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa do Modelo UML da View Analisar.
     * @return Botao de Pesquisa do Modelo UML da View Analisar.
     */
    public JButton getButtonSearchModeloUML1() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa do Modelo UML da View Analisar.
     * @return Botao de Pesquisa do Modelo UML da View Analisar.
     */
    public JButton getButtonSearchModeloUML2() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Analisar.
     * @return Botao de Pesquisa da Metrica da View Analisar.
     */
    public JButton getButtonSearchMetrica() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da Metrica da View Analisar.
     * @return Botao de Pesquisa da Metrica da View Analisar.
     */
//    public JButton getButtonSearchMetrica2() {
//        return this.buttonAction4;
//    }
    
    /**
     * Metodo responsavel por retornar o Botao Atualizar da View Avaliar.
     * @return Botao Atualizar da View Avaliar.
     */
    public JButton getButtonUpdate() {
        return this.buttonUpdate;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Voltar da View Analisar.
     * @return Botao Voltar da View Analisar.
     */
    public JButton getButtonVoltar() {
        return this.buttonBack;
    }
}