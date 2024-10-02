package modelo.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import modelo.controller.entidades.ControllerMetrica;
import utils.FunctDate;

/**
 * <p>Classe de Persistencia <b>Metrica</b>.</p>
 * <p>Classe responsavel por representar uma <b>Metrica SMM</b> no Sistema.</p>
 * <p>Classe representada no Banco de Dados com o nome <b>metrica</b>.</p>
 * @author Leandro
 * @since  06/12/2016
 * @see    java.io.Serializable
 */
@Entity
@Table (name = "metrica")
public class Metrica implements Serializable {
    @Id
    @Column (name = "id")
    private Long id;
    @Column (name = "nome")
    private String nome;
    @Column (name = "rotulo")
    private String rotulo;
    @Column (name = "descricao")
    private String descricao;
    @Column (name = "diagrama_alvo")
    private String diagramaAlvo;
    @Column (name = "tipo_metrica")
    private String tipo;
    @Column (name = "unidade_medida")
    private String unidadeMedida;
    @Column (name = "operacao")
    private String operacao;
    @Column (name = "ativa")
    private boolean ativa;
    @Column (name = "data_cadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    @Column (name = "hora_cadastro")
    private Time horaCadastro;

    /**
     * Metodo construtor padrao da Classe.
     */
    public Metrica() {
        this.id           = null;
        this.ativa        = true;
        this.dataCadastro = new FunctDate().getCurrentDate();
        this.horaCadastro = new FunctDate().getCurrentTime();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param nome Nome da Metrica.
     * @param rotulo Rotulo da Metrica.
     * @param descricao Descricao da Metrica.
     * @param diagramaAlvo Diagrama Alvo da Metrica.
     * @param tipo Tipo da Metrica.
     * @param unidadeMedida Unidade de Medida da Metrica.
     * @param operacao Operacao da Metrica.
     */
    public Metrica(String nome, String rotulo, String descricao, String diagramaAlvo, String tipo, String unidadeMedida, String operacao) {
        this();
        this.nome           = nome;
        this.rotulo         = rotulo;
        this.descricao      = descricao;
        this.diagramaAlvo   = diagramaAlvo;
        this.tipo           = tipo;
        this.unidadeMedida  = unidadeMedida;
        this.operacao       = operacao;
    }
    
    /**
     * Metodo responsavel por retornar o Id da Metrica.
     * @return Id da Metrica.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Metrica.
     * @param id Id da Metrica.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
        /**
     * Metodo responsavel por retornar o Nome da Metrica.
     * @return Nome da Metrica.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome da Metrica.
     * @param nome Nome da Metrica.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Metodo responsavel por retornar o Rotulo da Metrica.
     * @return Rotulo da Metrica.
     */
    public String getRotulo() {
        return this.rotulo;
    }
    
    /**
     * Metodo responsavel por definir o Rotulo da Metrica.
     * @param rotulo Rotulo da Metrica.
     */
    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    /**
     * Metodo responsavel por retornar a Descricao da Metrica.
     * @return Descricao da Metrica.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Metodo responsavel por definir a Descricao da Metrica.
     * @param descricao Descricao da Metrica.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Metodo responsavel por retornar o Diagrama Alvo da Metrica.
     * @return Diagrama Alvo da Metrica.
     */
    public String getDiagramaAlvo() {
        return this.diagramaAlvo;
    }

    /**
     * Metodo responsavel por definir o Diagrama Alvo da Metrica.
     * @param diagramaAlvo Diagrama Alvo da Metrica.
     */
    public void setDiagramaAlvo(String diagramaAlvo) {
        this.diagramaAlvo = diagramaAlvo;
    }

    /**
     * Metodo responsavel por retornar o Tipo da Metrica.
     * @return Tipo da Metrica.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo responsavel por definir o Tipo da Metrica.
     * @param tipo Tipo da Metrica.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo responsavel por retornar a Unidade de Medida da Metrica.
     * @return Unidade de Medida da Metrica.
     */
    public String getUnidadeMedida() {
        return this.unidadeMedida;
    }

    /**
     * Metodo responsavel por definir a Unidade de Medida da Metrica.
     * @param unidadeMedida Unidade de Medida da Metrica.
     */
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    /**
     * Metodo responsavel por retornar a Operacao da Metrica.
     * @return Operacao da Metrica.
     */
    public String getOperacao() {
        return this.operacao;
    }

    /**
     * Metodo responsavel por definir a Operacao da Metrica.
     * @param operacao Operacao da Metrica.
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    
    /**
     * Metodo responsavel por retornar a flag Ativa da Metrica.
     * @return Flag Ativa da Metrica.
     */
    public boolean isAtiva() {
        return this.ativa;
    }
    
    /**
     * Metodo responsavel por definir a flag Ativa da Metrica.
     * @param ativa Flag Ativa da Metrica.
     */
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    /**
     * Metodo responsavel por retornar a Data de Cadastro da Metrica.
     * @return Data de Cadastro da Metrica.
     */
    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    /**
     * Metodo responsavel por retornar a Hora de Cadastro da Metrica.
     * @return Hora de Cadastro da Metrica.
     */
    public Time getHoraCadastro() {
        return this.horaCadastro;
    }

    /**
     * Metodo responsavel por retornar os Dados da Consulta de uma Metrica.
     * @return Dados da Consulta de uma Metrica.
     */
    public String[] getDados() {
        String[] dados    = new String[5];
                 dados[0] = Long.toString(this.id);
                 dados[1] = this.nome.toUpperCase().trim();
                 dados[2] = this.descricao.toUpperCase().trim();
                 dados[3] = this.tipo.toUpperCase().trim();
                 dados[4] = this.operacao.toUpperCase().trim();
        return   dados;
    }
    
    /**
     * Metodo responsavel por retornar os Dados da Pesquisa de uma Metrica.
     * @return Dados da Pesquisa de uma Metrica.
     */
    public String[] getPesquisa() {
        String[] dados    = new String[3];
                 dados[0] = Long.toString(this.id);
                 dados[1] = this.nome.toUpperCase().trim();
                 dados[2] = this.operacao.toUpperCase().trim();
        return   dados;
    }
    
    /**
     * Metodo responsavel por retornar uma String com os Dados de Exportacao da Metrica.
     * @return Dados de Exportacao da Metrica.
     */
    public String print() {
        String dados  = "    <metrica>\n";
               dados += "        <" + ControllerMetrica.TAGS[0] + ">" + this.nome           + "</" + ControllerMetrica.TAGS[0] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[1] + ">" + this.rotulo         + "</" + ControllerMetrica.TAGS[1] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[2] + ">" + this.descricao      + "</" + ControllerMetrica.TAGS[2] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[3] + ">" + this.diagramaAlvo   + "</" + ControllerMetrica.TAGS[3] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[4] + ">" + this.tipo           + "</" + ControllerMetrica.TAGS[4] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[5] + ">" + this.unidadeMedida  + "</" + ControllerMetrica.TAGS[5] + ">\n";
               dados += "        <" + ControllerMetrica.TAGS[6] + ">" + this.operacao       + "</" + ControllerMetrica.TAGS[6] + ">\n";
               dados += "    </metrica>";
        return dados;
    }
    
    @Override
    public int hashCode() {
        int    hash  = 0;
               hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if ((object instanceof Metrica) == false) return false;
        return Objects.equals(this.id, ((Metrica) object).getId());
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}