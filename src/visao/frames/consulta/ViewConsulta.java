package visao.frames.consulta;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import visao.frames.View;
import visao.frames.ViewModal;
import visao.frames.Viewable;

/**
 * <p>Classe de Visao <b>ViewConsulta</b>.</p>
 * <p>Classe responsavel por definir um Modelo para as <b>Views de Consulta</b>.</p>
 * @author Leandro
 * @since  02/09/2016
 * @see    visao.frames.ViewModal
 * @see    visao.frames.Viewable
 */
public abstract class ViewConsulta extends ViewModal implements Viewable {
    protected JTextField textFieldSearch;
    protected JButton    buttonSearch;
    
    protected JTable            table;
    protected DefaultTableModel tableModel;
    protected JScrollPane       scrollPane;
    
    private   JTextField textFieldTotal;
    
    private static final short ALTURA  = 470;
    private static final short LARGURA = 455;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param viewParent View Parent.
     */
    public ViewConsulta(View viewParent) {
        super(viewParent);
        this.setSettings();
    }
    
    /**
     * Metodo responsavel por definir as Dimensoes da View de Consulta.
     */
    private void setSettings() {
        this.setSize(ALTURA, LARGURA);
    }

    /**
     * Metodo responsavel por adicionar o Campo de Pesquisa na View de Consulta.
     * @param colunas Numero de Colunas.
     */
    protected void addTextSearch(int colunas) {
        this.textFieldSearch = this.createTextField("", colunas);
        this.add(this.textFieldSearch);
    }
    
    /**
     * Metodo responsavel por adicionar o Botao de Pesquisa na View Consulta.
     */
    protected void addButtonSearch() {
        this.buttonSearch    = this.createButton("", "search.jpg");
        this.add(this.buttonSearch);
    }
    
    /**
     * Metodo responsavel por Inserir o Campo e Botao de Pesquisa na View de Consulta.
     */
    protected void addSearch() {
        this.addTextSearch(18);
        this.addButtonSearch();
    }

    @Override
    protected void addHeader(String url) {
        super.addHeader("consulta/" + url);
    }
    
    /**
     * Metodo responsavel por Inserir a Tabela na View de Consulta.
     */
    protected void addTable() {
        this.tableModel = new DefaultTableModel() {
           @Override
            public boolean isCellEditable(int iRowIndex, int iColIndex){   
                return false;
            }
        };
        this.table      = new JTable(this.tableModel);
        this.table.addKeyListener(this.controller);
        this.scrollPane = new JScrollPane(this.table);
        this.scrollPane.setPreferredSize(new Dimension(380, 150));
        this.add(this.scrollPane, new FlowLayout(FlowLayout.CENTER));
    }
    
    /**
     * Metodo responsavel por Inserir o Text Field do Total na View de Consulta.
     */
    protected void addTextFieldTotal() {
        this.textFieldTotal = this.createTextField("0", 3);
        this.textFieldTotal.setEditable(false);
        this.add(this.createLabel("Total: "));
        this.add(this.textFieldTotal);
    }
    
    /**
     * Metodo responsavel por definir as Colunas da Tabela da View de Consulta.
     * @param columns Titulo das Colunas da Tabela.
     * @param sizeColumns Tamanho das Colunas da Tabela.
     */
    protected void setColumns(String[] columns, int[] sizeColumns) {
        for (int i = 0; i < columns.length; ++i) {
            this.tableModel.addColumn(columns[i]);
            this.table.getColumnModel().getColumn(i).setPreferredWidth(sizeColumns[i]);
        }
    }

    /**
     * Metodo responsavel por adicionar Linhas a Tabela da View de Consulta.
     * @param rows Linhas a serem adicionadas.
     */
    public void addRows(String[][] rows) {
        this.clearTable();
        for (int i = 0; i < rows.length; ++i) {
            this.tableModel.addRow(rows[i]);
            this.table.setEditingRow(JTable.AUTO_RESIZE_NEXT_COLUMN);
            this.table.setEditingRow(0);
        }
    }
    
    /**
     * Metodo responsavel por Limpar a Tabela da View de Consulta.
     */
    public void clearTable() {
        while (this.tableModel.getRowCount() > 0) {
            this.tableModel.removeRow(0);
        }
        this.table.removeAll();
    }
    
    /**
     * Metodo responsavel por definir o Total de Registros da View de Consulta.
     * @param total Total de Registros.
     */
    public void setTotal(int total) {
        if (total > 0) {
            this.textFieldTotal.setText(Integer.toString(total));
        }else {
            this.textFieldTotal.setText("0");
        }
    }
    
    /**
     * Metodo responsavel por Limpar a View de Consulta.
     */
    public abstract void clear();
    
    /**
     * Metodo responsavel por Inserir os Botoes da View de Consulta.
     */
    @Override
    public void addButtons() {
        this.buttonAction1 = this.createButton(" Novo " , "add.jpg");
        this.buttonAction2 = this.createButton("Editar" , "edit.jpg");
        this.buttonAction3 = this.createButton("Remover", "exit.jpg");
        this.buttonAction4 = this.createButton("Voltar" , "back.jpg");
        
        this.add(this.buttonAction1);
        this.add(this.buttonAction2);
        this.add(this.buttonAction3);
        this.add(this.buttonAction4);
    }
    
    /**
     * Metodo responsavel por retornar o Text Field de Pesquisa da View de Consulta.
     * @return Text Field de Pesquisa.
     */
    public JTextField getTextFieldSearch() {
        return this.textFieldSearch;
    }
    
    /**
     * Metodo responsavel por retornar o Botao de Pesquisa da View de Consulta.
     * @return Botao de Pesquisa.
     */
    public JButton getButtonSearch() {
        return this.buttonSearch;
    }
    
    /**
     * Metodo responsavel por retornar o Table da View de Consulta.
     * @return Table da View de Consulta.
     */
    public JTable getTable() {
        return this.table;
    }
    
    /**
     * Metodo responsavel por retornar o Table Model da View de Consulta.
     * @return Table Model da View de Consulta.
     */
    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Novo da View de Consulta.
     * @return Botao Novo da View de Consulta.
     */
    public JButton getButtonNovo() {
        return this.buttonAction1;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Editar da View de Consulta.
     * @return Botao Editar da View de Consulta.
     */
    public JButton getButtonEdit() {
        return this.buttonAction2;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Remover da View de Consulta.
     * @return Botao Remover da View de Consulta.
     */
    public JButton getButtonRemove() {
        return this.buttonAction3;
    }
    
    /**
     * Metodo responsavel por retornar o Botao Voltar da View de Consulta.
     * @return Botao Voltar da View de Consulta.
     */
    public JButton getButtonBack() {
        return this.buttonAction4;
    }
}