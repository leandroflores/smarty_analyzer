package visao.frames.estruturais;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import utils.FunctFrame;
import visao.controller.frames.estruturais.ControllerViewMenu;
import visao.frames.View;

/**
 * <p>Classe de Visao <b>ViewMenu</b>.</p>
 * <p>Classe responsavel por representar o <b>Menu</b> da Aplicacao.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    view.View
 */
public class ViewMenu extends View {
    private JPanel    panel;
    
    private JMenuBar  menuBar;
    
    private JMenu     menuCadastro;
    private JMenu     menuProcessar;
    private JMenu     menuAnalisar;
    private JMenu     menuAvaliar;
    private JMenu     menuImportar;
    private JMenu     menuExportar;
    private JMenu     menuSistema;
    
    // Cadastro:
    private JMenuItem menuItemCadastroMetrica;
    private JMenuItem menuItemCadastroMedicao;
    
    // Processar:
    private JMenuItem menuItemProcessarDiagramaClasse;
    private JMenuItem menuItemProcessarDiagramaCasoDeUso;

    // Avaliar:
    private JMenuItem menuItemAvaliarDiagramasClasse;
    private JMenuItem menuItemAvaliarDiagramasCasoDeUso;
    private JMenuItem menuItemAvaliarDiagramasClassePorMetrica;
    private JMenuItem menuItemAvaliarDiagramasCasoDeUsoPorMetrica;
    private JMenuItem menuItemAvaliarMedicoesDiagramaClasse;
    private JMenuItem menuItemAvaliarMedicoesDiagramaCasoDeUso;
    
    // Importar:
    private JMenuItem menuItemImportarMetricas;
    
    // Exportar:
    private JMenuItem menuItemExportarMetricas;
    private JMenuItem menuItemExportarCodigoFonte;
    
    // Analisar:
    private JMenuItem menuItemAnalisarDiagramaClasse;
    private JMenuItem menuItemAnalisarDiagramaCasoDeUso;
    
    // Sistema:
    private JMenuItem menuItemSistemaSobre;
    private JMenuItem menuItemSistemaSite;
    private JMenuItem menuItemSistemaSair;
    
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public ViewMenu() {
        super();
        this.controller = new ControllerViewMenu(this);
        this.addKeyListener(this.controller);
        this.initComponents();
    }
    
    /**
     * Metodo responsavel por Inicializar os Componentes da View.
     */
    private void initComponents() {
        this.setTitle("SMartyAnalyzer - Menu Principal");
        this.noMove();
        this.addMenu();
        this.addLogo();
    }
    
    /**
     * Metodo responsavel por Adicionar o Menu na View.
     */
    private void addMenu() {
        this.panel   = new JPanel();
        this.menuBar = new JMenuBar();
        
        this.createMenuCadastro();
        this.createMenuProcessar();
        this.createMenuAnalisar();
        this.createMenuAvaliar();
        this.createMenuImportar();
        this.createMenuExportar();
        this.createMenuSistema();
        
        this.menuBar.add(this.menuCadastro);
        this.menuBar.add(this.menuProcessar);
        this.menuBar.add(this.menuAnalisar);
        this.menuBar.add(this.menuAvaliar);
        this.menuBar.add(this.menuImportar);
        this.menuBar.add(this.menuExportar);
        this.menuBar.add(this.menuSistema);
        
        this.setJMenuBar(this.menuBar);
        this.getContentPane().add(this.panel);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Cadastro.
     */
    private void createMenuCadastro() {
        this.menuCadastro = this.createMenu("Cadastro");
        
        this.menuItemCadastroMetrica = this.createMenuItem("Metrica", "cadastro/metrica.jpg");
        this.menuItemCadastroMetrica.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
        
        this.menuItemCadastroMedicao = this.createMenuItem("Medicao", "cadastro/medicao.jpg");
        this.menuItemCadastroMedicao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));

        this.menuCadastro.add(this.menuItemCadastroMetrica);
        this.menuCadastro.add(this.menuItemCadastroMedicao);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Processar.
     */
    private void createMenuProcessar() {
        this.menuProcessar = this.createMenu("Processar");
        
        this.menuItemProcessarDiagramaClasse    = this.createMenuItem("Metrica sobre Diagrama de Classe",      "processar/modelo_classes.jpg");
        this.menuItemProcessarDiagramaCasoDeUso = this.createMenuItem("Metrica sobre Diagrama de Caso de Uso", "processar/modelo_caso_de_uso.jpg");
        
        this.menuProcessar.add(this.menuItemProcessarDiagramaClasse);
        this.menuProcessar.add(this.menuItemProcessarDiagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Analisar.
     */
    private void createMenuAnalisar() {
        this.menuAnalisar = this.createMenu("Analisar");
        
        this.menuItemAnalisarDiagramaClasse    = this.createMenuItem("Diagrama de Classe",      "analisar/modelo_classe.jpg");
        this.menuItemAnalisarDiagramaCasoDeUso = this.createMenuItem("Diagrama de Caso de Uso", "analisar/modelo_caso_de_uso.jpg");
        
        this.menuAnalisar.add(this.menuItemAnalisarDiagramaClasse);
        this.menuAnalisar.add(this.menuItemAnalisarDiagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Avaliar.
     */
    private void createMenuAvaliar() {
        this.menuAvaliar = this.createMenu("Avaliar");
        
        this.menuItemAvaliarDiagramasClasse    = this.createMenuItem("Diagramas de Classe",      "avaliar/diagramas_classe.jpg");
        this.menuItemAvaliarDiagramasCasoDeUso = this.createMenuItem("Diagramas de Caso de Uso", "avaliar/diagramas_caso_de_uso.jpg");
        
        this.menuItemAvaliarDiagramasClassePorMetrica    = this.createMenuItem("Diagramas de Classe por Metrica",      "avaliar/diagramas_classe.jpg");
        this.menuItemAvaliarDiagramasCasoDeUsoPorMetrica = this.createMenuItem("Diagramas de Caso de Uso por Metrica", "avaliar/diagramas_caso_de_uso.jpg");
        
        this.menuItemAvaliarMedicoesDiagramaClasse    = this.createMenuItem("Medicoes sobre Diagrama de Classe", "avaliar/metricas.jpg");
        this.menuItemAvaliarMedicoesDiagramaCasoDeUso = this.createMenuItem("Medicoes sobre Diagrama de Caso de Uso", "avaliar/metricas.jpg");
        
        this.menuAvaliar.add(this.menuItemAvaliarDiagramasClasse);
        this.menuAvaliar.add(this.menuItemAvaliarDiagramasCasoDeUso);
        this.menuAvaliar.addSeparator();
        this.menuAvaliar.add(this.menuItemAvaliarDiagramasClassePorMetrica);
        this.menuAvaliar.add(this.menuItemAvaliarDiagramasCasoDeUsoPorMetrica);
        this.menuAvaliar.addSeparator();
        this.menuAvaliar.add(this.menuItemAvaliarMedicoesDiagramaClasse);
        this.menuAvaliar.add(this.menuItemAvaliarMedicoesDiagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Importar.
     */
    private void createMenuImportar() {
        this.menuImportar = this.createMenu("Importar");
        
        this.menuItemImportarMetricas = this.createMenuItem("Metricas", "importar/metricas.jpg");
        this.menuItemImportarMetricas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
        
        this.menuImportar.add(this.menuItemImportarMetricas);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Importar.
     */
    private void createMenuExportar() {
        this.menuExportar = this.createMenu("Exportar");
        
        this.menuItemExportarMetricas    = this.createMenuItem("Metricas",     "exportar/metricas.jpg");
        this.menuItemExportarMetricas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        
        this.menuItemExportarCodigoFonte = this.createMenuItem("Codigo-Fonte", "exportar/codigo_fonte.jpg");
        this.menuItemExportarCodigoFonte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_MASK));
        
        this.menuExportar.add(this.menuItemExportarMetricas);
        this.menuExportar.addSeparator();
        this.menuExportar.add(this.menuItemExportarCodigoFonte);
    }
    
    /**
     * Metodo responsavel por Criar o Menu Sistema.
     */
    private void createMenuSistema() {
        this.menuSistema = this.createMenu("Sistema");
        
        this.menuItemSistemaSobre = this.createMenuItem("Sobre", "sistema/sobre.jpg");
        this.menuItemSistemaSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, InputEvent.ALT_MASK));
        this.menuItemSistemaSite  = this.createMenuItem("Site" , "sistema/link.jpg");
        this.menuItemSistemaSite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.ALT_MASK));
        this.menuItemSistemaSair  = this.createMenuItem("Sair" , "sistema/sair.jpg");
        this.menuItemSistemaSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        
        this.menuSistema.add(this.menuItemSistemaSobre);
        this.menuSistema.add(this.menuItemSistemaSite);
        this.menuSistema.add(this.menuItemSistemaSair);
    }
    
    /**
     * Metodo responsavel por Adicionar o Logo a View.
     */
    private void addLogo() {
        this.addLinhas(8);
        this.add(new JLabel(new FunctFrame().createImage("logo.jpg")));
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Cadastro de Metrica.
     * @return Menu Item de Cadastro de Metrica.
     */
    public JMenuItem getMenuItemCadastroMetrica() {
        return this.menuItemCadastroMetrica;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Cadastro da Medicao.
     * @return Menu Item de Cadastro da Medicao.
     */
    public JMenuItem getMenuItemCadastroMedicao() {
        return this.menuItemCadastroMedicao;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Processar Metrica sobre o Diagrama de Classe.
     * @return Menu Item de Processar Metrica sobre o Diagrama de Classe.
     */
    public JMenuItem getMenuItemProcessarDiagramaClasse() {
        return this.menuItemProcessarDiagramaClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Processar Metrica sobre o Diagrama de Caso de Uso.
     * @return Menu Item de Processar Metrica sobre o Diagrama de Caso de Uso.
     */
    public JMenuItem getMenuItemProcessarDiagramaCasoDeUso() {
        return this.menuItemProcessarDiagramaCasoDeUso;
    }
        
    /**
     * Metodo responsavel por retornar o Menu Item de Analise do Diagrama de Classe.
     * @return Menu Item de Analise do Diagrama de Classe.
     */
    public JMenuItem getMenuItemAnalisarDiagramaClasse() {
        return this.menuItemAnalisarDiagramaClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Analise do Diagrama de Caso de Uso.
     * @return Menu Item de Analise do Diagrama de Caso de Uso.
     */
    public JMenuItem getMenuItemAnalisarDiagramaCasoDeUso() {
        return this.menuItemAnalisarDiagramaCasoDeUso;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Diagramas de Classe.
     * @return Menu Item de Avaliar Diagramas de Classe.
     */
    public JMenuItem getMenuItemAvaliarDiagramasClasse() {
        return this.menuItemAvaliarDiagramasClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Diagramas de Caso de Uso.
     * @return Menu Item de Avaliar Diagramas de Caso de Uso.
     */
    public JMenuItem getMenuItemAvaliarDiagramasCasoDeUso() {
        return this.menuItemAvaliarDiagramasCasoDeUso;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Diagramas de Classe por Metrica.
     * @return Menu Item de Avaliar Diagramas de Classe por Metrica.
     */
    public JMenuItem getMenuItemAvaliarDiagramasClassePorMetrica() {
        return this.menuItemAvaliarDiagramasClassePorMetrica;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Diagramas de Caso de Uso por Metrica.
     * @return Menu Item de Avaliar Diagramas de Caso de Uso por Metrica.
     */
    public JMenuItem getMenuItemAvaliarDiagramasCasoDeUsoPorMetrica() {
        return this.menuItemAvaliarDiagramasCasoDeUsoPorMetrica;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Medicoes do Diagrama de Classe.
     * @return Menu Item de Avaliar Medicoes do Diagrama de Classe.
     */
    public JMenuItem getMenuItemAvaliarMedicoesDiagramasClasse() {
        return this.menuItemAvaliarMedicoesDiagramaClasse;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Avaliar Medicoes do Diagrama de Caso de Uso.
     * @return Menu Item de Avaliar Medicoes do Diagrama de Caso de Uso.
     */
    public JMenuItem getMenuItemAvaliarMedicoesDiagramasCasoDeUso() {
        return this.menuItemAvaliarMedicoesDiagramaCasoDeUso;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Importacao de Metricas.
     * @return Menu Item de Importacao de Metricas.
     */
    public JMenuItem getMenuItemImportarMetricas() {
        return this.menuItemImportarMetricas;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Exportacao de Metricas.
     * @return Menu Item de Exportacao de Metricas.
     */
    public JMenuItem getMenuItemExportarMetricas() {
        return this.menuItemExportarMetricas;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Exportacao do Codigo-Fonte.
     * @return Menu Item de Exportacao do Codigo-Fonte.
     */
    public JMenuItem getMenuItemExportarCodigoFonte() {
        return this.menuItemExportarCodigoFonte;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Sobre.
     * @return Menu Item do Sistema Sobre.
     */
    public JMenuItem getMenuItemSistemaSobre() {
        return this.menuItemSistemaSobre;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Site.
     * @return Menu Item do Sistema Site.
     */
    public JMenuItem getMenuItemSistemaSite() {
        return this.menuItemSistemaSite;
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Sair.
     * @return Menu Item do Sistema Sair.
     */
    public JMenuItem getMenuItemSistemaSair() {
        return this.menuItemSistemaSair;
    }
    
    public static void main(String[] args) {
        new ViewMenu().setVisible(true);
    }
}