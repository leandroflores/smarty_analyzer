package modelo.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import utils.FunctDate;

/**
 * <p>Classe de Modelo <b>Medicao</b>.</p>
 * <p>Classe responsavel por definir a Entidade <b>Medicao</b>.</p>
 * @author Leandro
 * @since  27/09/2016
 * @see    java.io.Serializable
 * @see    model.entity.Metrica
 */
@Entity
@Table (name = "medicao")
public class Medicao implements Serializable {
    @Id
    @Column (name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn (name = "metrica_id")
    private Metrica metrica;
    @Column (name = "arquivo")
    private String  arquivo;
    @Column (name = "diagrama_alvo")
    private String  diagramaAlvo;
    @Column (name = "nome")
    private String  nome;
    @Column (name = "operacao_aplicada")
    private String  operacao;
    @Column (name = "valor_1")
    private Integer valor1;
    @Column (name = "valor_2")
    private Integer valor2;
    @Column (name = "valor_metrica")
    private Double  valor;
    @Column (name = "data_cadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    @Column (name = "hora_cadastro")
    private Time horaCadastro;

    /**
     * Metodo construtor padrao da Classe.
     */
    public Medicao() {
        this.id           = null;
        this.dataCadastro = new FunctDate().getCurrentDate();
        this.horaCadastro = new FunctDate().getCurrentTime();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param metrica Metrica aplicada na Medicao.
     * @param arquivo Arquivo do Modelo UML da Medicao.
     * @param diagramaAlvo Diagrama Alvo da Medicao.
     * @param nome Nome do Modelo UML da Medicao.
     * @param operacao Operacao Aplicada na Medicao.
     */
    public Medicao(Metrica metrica, String arquivo, String diagramaAlvo, String nome, String operacao) {
        this();
        this.metrica      = metrica;
        this.arquivo      = arquivo;
        this.diagramaAlvo = diagramaAlvo;
        this.nome         = nome;
        this.operacao     = operacao;
    }
    
    /**
     * Metodo responsavel por retornar o Id da Medicao.
     * @return Id da Medicao.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Medicao.
     * @param id Id da Medicao.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar a Metrica da Medicao.
     * @return Metrica da Medicao.
     */
    public Metrica getMetrica() {
        return this.metrica;
    }

    /**
     * Metodo responsavel por definir a Metrica da Medicao.
     * @param metrica Metrica da Medicao.
     */
    public void setMetrica(Metrica metrica) {
        this.metrica = metrica;
    }

    /**
     * Metodo responsavel por retornar o Arquivo do Modelo UML da Medicao.
     * @return Arquivo do Modelo UML da Medicao.
     */
    public String getArquivo() {
        return this.arquivo;
    }

    /**
     * Metodo responsavel por definir o Arquivo do Modelo UML da Medicao.
     * @param arquivo Arquivo do Modelo UML da Medicao.
     */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    /**
     * Metodo responsavel por retornar o Diagrama Alvo da Medicao.
     * @return Diagrama Alvo da Medicao.
     */
    public String getDiagramaAlvo() {
        return this.diagramaAlvo;
    }

    /**
     * Metodo responsavel por definir o Diagrama Alvo da Medicao.
     * @param diagramaAlvo Diagrama Alvo da Medicao.
     */
    public void setDiagramaAlvo(String diagramaAlvo) {
        this.diagramaAlvo = diagramaAlvo;
    }

    /**
     * Metodo responsavel por retornar o Nome do Modelo UML da Medicao.
     * @return Nome do Modelo UML da Medicao.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Modelo UML da Medicao.
     * @param nome Nome do Modelo UML da Medicao.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Operacao Aplicada sobre o Modelo UML da Medicao.
     * @return Operacao Aplicada sobre o Modelo UML da Medicao.
     */
    public String getOperacao() {
        return this.operacao;
    }

    /**
     * Metodo responsavel por definir a Operacao Aplicada sobre o Modelo UML da Medicao.
     * @param operacao Operacao Aplicada sobre o Modelo UML da Medicao.
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * Metodo responsavel por retornar o Valor 1 da Medicao.
     * @return Valor 1 da Medicao.
     */
    public Integer getValor1() {
        return this.valor1;
    }

    /**
     * Metodo responsavel por definir o Valor 1 da Medicao.
     * @param valor1 Valor 1 da Medicao.
     */
    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }

    /**
     * Metodo responsavel por retornar o Valor 2 da Medicao.
     * @return Valor 2 da Medicao.
     */
    public Integer getValor2() {
        return this.valor2;
    }

    /**
     * Metodo responsavel por definir o Valor 2 da Medicao.
     * @param valor2 Valor 2 da Medicao.
     */
    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    /**
     * Metodo responsavel por retornar o Valor da Medicao.
     * @return Valor da Medicao.
     */
    public Double getValor() {
        return this.valor;
    }

    /**
     * Metodo responsavel por definir o Valor da Medicao.
     * @param valor Valor da Medicao.
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Metodo responsavel por retornar a Data de Cadastro da Medicao.
     * @return Data de Cadastro da Medicao.
     */
    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    /**
     * Metodo responsavel por retornar a Hora de Cadastro da Medicao.
     * @return Hora de Cadastro da Medicao.
     */
    public Time getHoraCadastro() {
        return this.horaCadastro;
    }
    
    /**
     * Metodo responsavel por retornar os Dados da Consulta de uma Medicao.
     * @return Dados da Consulta de uma Medicao.
     */
    public String[] getDados() {
        String[] dados    = new String[6];
                 dados[0] = Long.toString(this.id);
                 dados[1] = this.getDataFormatada();
                 dados[2] = this.horaCadastro.toString().trim();
                 dados[3] = this.nome.toUpperCase().trim();
                 dados[4] = this.metrica.getNome().toUpperCase().trim();
                 dados[5] = this.getValorFormatado();
        return   dados;
    }
    
    /**
     * Metodo responsavel por retornar a Data formatada.
     * @return Data formatada.
     */
    private String getDataFormatada() {
        if (this.dataCadastro != null)
            return new SimpleDateFormat("dd/MM/yyyy").format(this.dataCadastro);
        return "";
    }
    
    /**
     * Metodo responsavel por retornar o Valor formatado.
     * @return Valor formatado.
     */
    private String getValorFormatado() {
        if (this.valor != null)
            return Double.toString(this.valor);
        return "";
    }
    
    /**
     * Metodo responsavel por retornar uma String com os Dados de Exportacao da Medicao.
     * @return Dados de Exportacao da Medicao.
     */
    public String print() {
        String dados  = "<medicao>\n";
               dados += "    <metrica>"   + this.metrica.getNome() + "</metrica>\n";
               dados += "    <operacao>"  + this.operacao          + "</operacao>\n";
               dados += "    <valor>"     + this.valor             + "</valor>\n";
//               dados += "    <classes>"   + this.classes           + "</classes>\n";
//               dados += "    <metodos>"   + this.metodos           + "</metodos>\n";
//               dados += "    <atributos>" + this.metodos           + "</atributos>\n";
               dados += "</medicao>\n";
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
        if ((object instanceof Medicao) == false) return false;
        return this.id.equals(((Medicao) object).getId());
    }

    @Override
    public String toString() {
        return this.id + " - " + this.metrica.getRotulo() + " = " + this.valor;
    }
}