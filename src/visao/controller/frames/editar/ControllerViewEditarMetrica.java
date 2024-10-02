package visao.controller.frames.editar;

import modelo.controller.entidades.ControllerMetrica;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;
import visao.frames.editar.ViewEditarMetrica;
import visao.frames.mensagem.ViewInformacao;

/**
 * <p>Classe de Controle <b>ControllerViewEditarMetrica</b>.</p>
 * <p>Classe responsavel por ser o <b>Controlador</b> da ViewEditarMetrica.</p>
 * @author Leandro
 * @since  12/09/2016
 * @see    visao.controller.frames.editar.ControllerViewEditar
 * @see    visao.frames.editar.ViewEditarMetrica
 */
public class ControllerViewEditarMetrica extends ControllerViewEditar {
    private final ViewEditarMetrica viewEditarMetrica;
    private static final ControllerMetrica CONTROLLER_METRICA = new ControllerMetrica();
    private static final DaoMetrica DAO_METRICA = new DaoMetrica();

    /**
     * Metodo construtor padrao da Classe.
     * @param viewEditarMetrica View Editar Metrica do Sistema.
     */
    public ControllerViewEditarMetrica(ViewEditarMetrica viewEditarMetrica) {
        super(viewEditarMetrica);
        this.viewEditarMetrica = viewEditarMetrica;
    }

    @Override
    public void edit() {
        this.viewEditarMetrica.clearBorder();
        if (this.checkDados()) {
            Metrica metrica = this.viewEditarMetrica.getMetrica();
                    metrica.setNome(this.viewEditarMetrica.getTextFieldNome().getText().trim().toUpperCase());
                    metrica.setRotulo(this.viewEditarMetrica.getTextFieldRotulo().getText().trim().toUpperCase());
                    metrica.setDescricao(this.viewEditarMetrica.getTextFieldDescricao().getText().trim().toUpperCase());
                    metrica.setDiagramaAlvo(this.viewEditarMetrica.getComboBoxDiagramaAlvo().getSelectedItem().toString().toUpperCase());
                    metrica.setTipo(this.viewEditarMetrica.getComboBoxTipo().getSelectedItem().toString());
                    metrica.setUnidadeMedida(this.viewEditarMetrica.getComboBoxUnidadeMedida().getSelectedItem().toString());
                    metrica.setOperacao(this.viewEditarMetrica.getTextFieldOperacao().getText().trim().toUpperCase());
            DAO_METRICA.update(metrica);
            new ViewInformacao(this.viewEditarMetrica, "Metrica Atualizada com Sucesso!").setVisible(true);
            this.viewEditarMetrica.getViewConsultaMetrica().clear();
            this.viewEditarMetrica.dispose();
        }
    }
    
    /**
     * Metodo responsavel por verificar se os Dados sao Validos para Edicao.
     * @return Dados sao validos para Edicao.
     */
    private boolean checkDados() {
        if (CONTROLLER_METRICA.checkNome(this.viewEditarMetrica.getTextFieldNome().getText().trim()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldNome(), "Nome Invalido!");
        }else if (CONTROLLER_METRICA.nomeAvailable(this.viewEditarMetrica.getTextFieldNome().getText().trim(), this.viewEditarMetrica.getMetrica()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldNome(), "Nome ja Cadastrado!");
        }else if (CONTROLLER_METRICA.checkRotulo(this.viewEditarMetrica.getTextFieldRotulo().getText().trim()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldRotulo(), "Rotulo Invalido!");
        }else if (CONTROLLER_METRICA.rotuloAvailable(this.viewEditarMetrica.getTextFieldRotulo().getText().trim(), this.viewEditarMetrica.getMetrica()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldRotulo(), "Rotulo ja Cadastrado!");
        }else if (CONTROLLER_METRICA.checkDescricao(this.viewEditarMetrica.getTextFieldDescricao().getText().trim()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldDescricao(), "Descricao Invalida!");
        }else if (this.viewEditarMetrica.getComboBoxDiagramaAlvo().getSelectedIndex() == 0) {
            return this.erro(this.viewEditarMetrica.getComboBoxDiagramaAlvo(), "Selecione um Diagrama Alvo!");
        }else if (this.viewEditarMetrica.getComboBoxTipo().getSelectedIndex() == 0) {
            return this.erro(this.viewEditarMetrica.getComboBoxTipo(), "Selecione um Tipo!");
        }else if (this.viewEditarMetrica.getComboBoxUnidadeMedida().getSelectedIndex() == 0) {
            return this.erro(this.viewEditarMetrica.getComboBoxUnidadeMedida(), "Selecione uma Unidade de Medida!");
        }else if (CONTROLLER_METRICA.checkOperacao(this.viewEditarMetrica.getTextFieldOperacao().getText().trim()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldOperacao(), "Operacao Invalida!");
        }else if (CONTROLLER_METRICA.operacaoAvailable(this.viewEditarMetrica.getTextFieldOperacao().getText().trim(), this.viewEditarMetrica.getMetrica()) == false) {
            return this.erro(this.viewEditarMetrica.getTextFieldOperacao(), "Operacao ja Cadastrada!");
        }
        return true;
    }
}