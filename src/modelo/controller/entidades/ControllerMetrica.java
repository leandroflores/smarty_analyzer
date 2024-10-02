package modelo.controller.entidades;

import java.util.List;
import modelo.controller.ControllerModelo;
import modelo.dao.entidades.DaoMetrica;
import modelo.entidades.Metrica;

/**
 * <p>Classe de Controle <b>ControllerMetrica</b>.</p>
 * <p>Classe responsavel por ser a <b>Controladora</b> da Classe Metrica.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    model.entity.Metrica
 * @see    model.dao.entity.DaoMetrica
 */
public class ControllerMetrica extends ControllerModelo {
    //public  static final String[]   ESCOPO         = {"Selecione", "CODIGO"};
    public  static final String[]   TIPOS          = {"Selecione", "BINARY", "COLLECTIVE", "COUNTING", "DIRECT", "RATIO"};
    public  static final String[]   UNIDADE_MEDIDA = {"Selecione", "PACOTE", "CLASSE", "METODO", "ATRIBUTO", "OUTRO"};
    public  static final String[]   DIAGRAMAS      = {"Selecione", "DIAGRAMA DE CLASSE", "DIAGRAMA DE CASO DE USO"};
    public  static final String[]   TAGS           = {"nome", "rotulo", "descricao", "diagrama-alvo", "tipo", "unidade-medida", "operacao"};
    private static final DaoMetrica DAO_METRICA    = new DaoMetrica();
    
    /**
     * Metodo responsavel por Validar o Nome da Metrica.
     * @param  nome Nome da Metrica.
     * @return Nome e Valido para Metrica.
     */
    public boolean checkNome(String nome) {
        return this.checkLetras(nome, false);
    }
    
    /**
     * Metodo responsavel por retornar se o Nome e Valido para Cadastro/Edicao.
     * @param  nome Nome a ser avaliado.
     * @param  metrica Metrica sendo Editada (null caso Cadastro).
     * @return Nome valido para Cadastro/Edicao.
     */
    public boolean nomeAvailable(String nome, Metrica metrica) {
        String query = "e.nome LIKE '" + nome + "'";
        if (metrica != null) query += " AND e.id != " + metrica.getId();
        return DAO_METRICA.query(query).isEmpty();
    }
    
    /**
     * Metodo responsavel por Validar o Rotulo da Metrica.
     * @param  rotulo Rotulo da Metrica.
     * @return Rotulo e Valido para Metrica.
     */
    public boolean checkRotulo(String rotulo) {
        return this.checkPalavra(rotulo, false);
    }
    
    /**
     * Metodo responsavel por retornar se o Rotulo e Valido para Cadastro/Edicao.
     * @param  rotulo Rotulo a ser avaliado.
     * @param  metrica Metrica sendo Editada (null caso Cadastro).
     * @return Rotulo valido para Cadastro/Edicao.
     */
    public boolean rotuloAvailable(String rotulo, Metrica metrica) {
        String query = "e.rotulo LIKE '" + rotulo + "'";
        if (metrica != null) query += " AND e.id != " + metrica.getId();
        return DAO_METRICA.query(query).isEmpty();
    }
    
    /**
     * Metodo responsavel por Validar a Descricao da Metrica.
     * @param  descricao Descricao da Metrica.
     * @return Descricao e Valida para Metrica.
     */
    public boolean checkDescricao(String descricao) {
        return this.noEmpty(descricao);
    }
    
    /**
     * Metodo responsavel por Validar a Caracteristica da Metrica.
     * @param  caracteristica Caracteristica da Metrica.
     * @return Caracteristica e Valida para Metrica.
     */
    public boolean checkCaracteristica(String caracteristica) {
        return this.noEmpty(caracteristica);
    }
    
    /**
     * Metodo responsavel por Validar a Operacao da Metrica.
     * @param  operacao Caracteristica da Metrica.
     * @return Caracteristica e Valida para Metrica.
     */
    public boolean checkOperacao(String operacao) {
        return this.noEmpty(operacao);
    }
    
    /**
     * Metodo responsavel por retornar se a Operacao e Valida para Cadastro/Edicao.
     * @param  operacao Operacao a ser avaliada.
     * @param  metrica Metrica sendo Editada (null caso Cadastro).
     * @return Operacao valida para Cadastro/Edicao.
     */
    public boolean operacaoAvailable(String operacao, Metrica metrica) {
        String query = "e.operacao LIKE '" + operacao + "'";
        if (metrica != null) query += " AND e.id != " + metrica.getId();
        return DAO_METRICA.query(query).isEmpty();
    }
    
    /**
     * Metodo responsavel por retornar os Dados das Metricas para Pesquisa.
     * @param metricas Metricas a serem pesquisadas.
     * @return Dados das Metricas para Pesquisa.
     */
    public String[][] getCamposPesquisa(List<Metrica> metricas) {
        String[][] dados = new String[metricas.size()][3];
        for (int i = 0; i < dados.length; ++i) {
            dados[i] = metricas.get(i).getPesquisa();
        }
        return dados;
    }
    
    /**
     * Metodo responsavel por retornar os Dados das Metricas para Consulta.
     * @param metricas Metricas a serem buscadas.
     * @return Dados das Metricas para Consulta.
     */
    public String[][] getMetricas(List<Metrica> metricas) {
        String[][] dados = new String[metricas.size()][5];
        for (int i = 0; i < dados.length; ++i) {
            dados[i] = metricas.get(i).getDados();
        }
        return dados;
    }
}