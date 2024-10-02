package visao.controller.frames.cadastro;

import modelo.controller.entidades.ControllerMetrica;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;
import visao.frames.cadastro.ViewCadastroMetrica;
import visao.frames.mensagem.ViewInformacao;

/**
 * <p>Classe de Controle <b>ControllerViewCadastroMetrica</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewCadastroMetrica.</p>
 * @author Leandro
 * @since  06/09/2016
 * @see    modelo.controller.entidades.ControllerMetrica
 * @see    modelo.dao.entidades.DaoMetrica
 * @see    visao.controller.frames.cadastro.ControllerViewCadastro
 * @see    visao.frames.cadastro.ViewCadastroMetrica
 */
public class ControllerViewCadastroMetrica extends ControllerViewCadastro {
    private final ViewCadastroMetrica viewCadastroMetrica;
    private static final ControllerMetrica CONTROLLER_METRICA = new ControllerMetrica();
    private static final DaoMetrica DAO_METRICA = new DaoMetrica();

    /**
     * Metodo construtor padrao da Classe.
     * @param viewCadastroMetrica View Cadastro de Metrica do Sistema.
     */
    public ControllerViewCadastroMetrica(ViewCadastroMetrica viewCadastroMetrica) {
        super(viewCadastroMetrica);
        this.viewCadastroMetrica = viewCadastroMetrica;
    }

    @Override
    public void cadastrar() {
        this.viewCadastroMetrica.clearBorder();
        if (this.checkDados()) {
            Metrica metrica = new Metrica(this.viewCadastroMetrica.getTextFieldNome().getText().trim().toUpperCase(),
                                          this.viewCadastroMetrica.getTextFieldRotulo().getText().trim().toUpperCase(),
                                          this.viewCadastroMetrica.getTextFieldDescricao().getText().trim().toUpperCase(),
                                          this.viewCadastroMetrica.getComboBoxDiagramaAlvo().getSelectedItem().toString().toUpperCase(),
                                          this.viewCadastroMetrica.getComboBoxTipo().getSelectedItem().toString(),
                                          this.viewCadastroMetrica.getComboBoxUnidadeMedida().getSelectedItem().toString(),
                                          this.viewCadastroMetrica.getTextFieldOperacao().getText().trim().toUpperCase());
            metrica.setId(DAO_METRICA.nextId());
            DAO_METRICA.insert(metrica);
            new ViewInformacao(this.viewCadastroMetrica, "Metrica Inserida com Sucesso!").setVisible(true);
            this.viewCadastroMetrica.getViewConsultaMetrica().clear();
            this.viewCadastroMetrica.dispose();
        }
    }
    
    /**
     * Metodo responsavel por verificar se os Dados sao Validos para Insercao.
     * @return Dados sao validos.
     */
    private boolean checkDados() {
        if (CONTROLLER_METRICA.noEmpty(this.viewCadastroMetrica.getTextFieldNome().getText().trim()) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldNome(), "Nome Invalido!");
        }else if (CONTROLLER_METRICA.nomeAvailable(this.viewCadastroMetrica.getTextFieldNome().getText().trim(), null) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldNome(), "Nome ja Cadastrado!");
        }else if (CONTROLLER_METRICA.checkRotulo(this.viewCadastroMetrica.getTextFieldRotulo().getText().trim()) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldRotulo(), "Rotulo Invalido!");
        }else if (CONTROLLER_METRICA.rotuloAvailable(this.viewCadastroMetrica.getTextFieldRotulo().getText().trim(), null) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldRotulo(), "Rotulo ja Cadastrado!");
        }else if (CONTROLLER_METRICA.checkDescricao(this.viewCadastroMetrica.getTextFieldDescricao().getText().trim()) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldDescricao(), "Descricao Invalida!");
        }else if (this.viewCadastroMetrica.getComboBoxDiagramaAlvo().getSelectedIndex() == 0) {
            return this.erro(this.viewCadastroMetrica.getComboBoxDiagramaAlvo(), "Selecione um Diagrama Alvo!");
        }else if (this.viewCadastroMetrica.getComboBoxTipo().getSelectedIndex() == 0) {
            return this.erro(this.viewCadastroMetrica.getComboBoxTipo(), "Selecione um Tipo!");
        }else if (this.viewCadastroMetrica.getComboBoxUnidadeMedida().getSelectedIndex() == 0) {
            return this.erro(this.viewCadastroMetrica.getComboBoxUnidadeMedida(), "Selecione uma Unidade de Medida!");
        }else if (CONTROLLER_METRICA.checkOperacao(this.viewCadastroMetrica.getTextFieldOperacao().getText().trim()) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldOperacao(), "Operacao Invalida!");
        }else if (CONTROLLER_METRICA.operacaoAvailable(this.viewCadastroMetrica.getTextFieldOperacao().getText().trim(), null) == false) {
            return this.erro(this.viewCadastroMetrica.getTextFieldOperacao(), "Operacao ja Cadastrada!");
        }
        return true;
    }
}