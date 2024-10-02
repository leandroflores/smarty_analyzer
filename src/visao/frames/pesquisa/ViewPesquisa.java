package visao.frames.pesquisa;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import visao.frames.ViewModal;
import visao.frames.Viewable;

/**
 * <p>Classe de Visao <b>ViewPesquisa</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Pesquisa</b>.</p>
 * @author Leandro
 * @since  16/09/2016
 * @see    view.ViewModal
 * @see    view.Viewable
 */
public abstract class ViewPesquisa extends ViewModal implements Viewable {
    protected JTextField textFieldPesquisa;
    
    private JTable            table;
    private DefaultTableModel tableModel;
    private TableColumnModel  columnModel;
    private JScrollPane       scrollPane;
    
    private int[] sizeColumns;
    

    /**
     * Metodo construtor padrao da Classe.
     * @param viewModal View Modal do Sistema.
     */
    public ViewPesquisa(ViewModal viewModal) {
        super(viewModal);
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("pesquisa/" + url);
    }
    
    /**
     * Metodo responsavel por Adicionar os Campos de Pesquisa a View Pesquisa.
     */
    protected void addCampos() {
        this.textFieldPesquisa = this.createTextField("", 25);
        this.buttonAction1     = this.createButton("", "update.jpg");
        this.add(this.textFieldPesquisa);
        this.add(this.buttonAction1);
        this.addLinhas(1);
    }

    /**
     * Metodo responsaval por Adicionar a Tabela a View Pesquisa.
     */
    protected void addTable() {
        this.tableModel  = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int iRowIndex, int iColIndex){   
                return false;   
            }
        };
        this.table       = new JTable(this.tableModel);
        this.table.addKeyListener(this.controller);
        this.columnModel = this.table.getColumnModel();
        this.scrollPane  = new JScrollPane(this.table);
        this.scrollPane.setPreferredSize(new Dimension(475, 175));
        
        this.add(this.scrollPane, new FlowLayout(FlowLayout.CENTER));
        this.addLinhas(1);
    }
    
    /**
     * Metodo responsavel por definir o Titulo das Colunas da View Pesquisa.
     * @param columns Colunas da View Pesquisa.
     * @param size Tamanho das Colunas.
     */
    protected void setColumns(String[] columns, int[] size) {
        for (int i = 0; i < columns.length; ++i) {
            this.tableModel.addColumn(columns[i]);
            this.table.getColumn(columns[i]).setPreferredWidth(size[i]);
        }
    }
    
    /**
     * Metodo responsavel por Adicionar os Valores da View Pesquisa.
     * @param rows Matriz com os Valores da View Pesquisa.
     */
    public void addRows(String[][] rows) {
        this.clearTable();
        for (int i = 0; i < rows.length; ++i) {
            this.tableModel.addRow(rows[i]);
            this.table.setEditingColumn(i);
        }
    }
    
    /**
     * Metodo responsavel por definir o Tamanho das Colunas da Tabela.
     */
    private void resizeColumn() {
        for (int i = 0; i < this.sizeColumns.length; ++i) {
            this.table.getColumnModel().getColumn(i).setPreferredWidth(this.sizeColumns[i]);
        }
    }
    
    /**
     * Metodo responsavel por Limpar os Valores da Tabela.
     */
    public void clearTable() {
        while (this.tableModel.getRowCount() > 0) {
            this.tableModel.removeRow(0);
        }
        this.table.removeAll();
    }
    
    public abstract void clear();

    @Override
    public void addButtons() {
        this.buttonAction2 = this.createButton("Selecionar", "select.jpg");
        this.buttonAction3 = this.createButton("  Voltar  ", "back.jpg");
        
        this.add(this.buttonAction2);
        this.add(this.buttonAction3);
    }
    
    /**
     * Metodo responsavel por definir o Tamanho das Colunas da Tabela.
     * @param sizeColumns Tamanho das Colunas da Tabela.
     */
    public void setColumnSize(int[] sizeColumns) {
        this.sizeColumns = sizeColumns;
    }
    
    /**
     * Metodo responsavel por retornar o Text Field da Pesquisa da View Pesquisa.
     * @return Text Field da Pesquisa da View Pesquisa.
     */
    public JTextField getTextFieldPesquisa() {
        return this.textFieldPesquisa;
    }

    /**
     * Metodo responsavel por retornar o Botao Pesquisa da View Pesquisa.
     * @return Botao Pesquisa da View Pesquisa.
     */
    public JButton getButtonPesquisa() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar a Tabela da View Pesquisa.
     * @return Tabela da View Pesquisa.
     */
    public JTable getTable() {
        return this.table;
    }

    /**
     * Metodo responsavel por retornar o Modelo da Tabela da View Pesquisa.
     * @return Modelo da Tabela da View Pesquisa.
     */
    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }

    /**
     * Metodo responsavel por retornar o Scroll Pane da View Pesquisa.
     * @return Scroll Pane da View Pesquisa.
     */
    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    /**
     * Metodo responsavel por retornar o Botao Selecionar da View Pesquisa.
     * @return Botao Selecionar da View Pesquisa.
     */
    public JButton getButtonSelecionar() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Voltar da View Pesquisa.
     * @return Botao Voltar da View Pesquisa.
     */
    public JButton getButtonVoltar() {
        return this.buttonAction3;
    }
}