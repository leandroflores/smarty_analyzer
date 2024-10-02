package modelo.uml.diagrama.usecases;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe de Modelo <b>PastaUML</b>.</p>
 * <p>Classe responsavel por representar uma <b>Pasta UML</b> no Sistema.</p>
 * <p>Usado exclusivamente para os <b>Diagramas de Casos de Uso</b>.</p>
 * @author Leandro
 * @since  21/02/2017
 */
public class PastaUML {
    private static final String TIPO_XMI = "uml:Package";
    private PastaUML pai;
    private String   id;
    private String   nome;
    private List<PastaUML> filhos;
    private List<AtorUML>  atores;
    private List<CasoDeUsoUML> casosDeUso;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param pai Pasta pai da Pasta UML.
     * @param nome Nome da Pasta UML.
     */
    public PastaUML(PastaUML pai, String nome) {
        this.pai        = pai;
        this.nome       = nome;
        this.filhos     = new ArrayList<>();
        this.atores     = new ArrayList<>();
        this.casosDeUso = new ArrayList<>();
    }

    /**
     * Metodo responsavel por retornar a Pasta Pai da Pasta UML.
     * @return Pasta Pai da Pasta UML.
     */
    public PastaUML getPai() {
        return this.pai;
    }

    /**
     * Metodo responsavel por definir a Pasta Pai da Pasta UML.
     * @param pai Pasta Pai da Pasta UML.
     */
    public void setPai(PastaUML pai) {
        this.pai = pai;
    }

    /**
     * Metodo responsavel por retornar o Id da Pasta UML.
     * @return Id da Pasta UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id da Pasta UML.
     * @param id Id da Pasta UML.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome da Pasta UML.
     * @return Nome da Pasta UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome da Pasta UML.
     * @param nome Nome da Pasta UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Lista de Filhos da Pasta UML.
     * @return Lista de Filhos da Pasta UML.
     */
    public List<PastaUML> getFilhos() {
        return this.filhos;
    }

    /**
     * Metodo responsavel por definir a Lista de Filhos da Pasta UML.
     * @param filhos Lista de Filhos da Pasta UML.
     */
    public void setFilhos(List<PastaUML> filhos) {
        this.filhos = filhos;
    }

    /**
     * Metodo responsavel por adicionar uma Pasta UML a Lista de Filhos da Pasta UML.
     * @param filho Pasta UML a ser adicionada.
     */
    public void addFilho(PastaUML filho) {
        if (filho != null)
            this.filhos.add(filho);
    }
    
    /**
     * Metodo responsavel por retornar uma Pasta UML pelo Nome.
     * @param  nome Nome da Pasta UML.
     * @return Pasta UML encontrada.
     */
    public PastaUML getFilho(String nome) {
        for (int i = 0; i < this.filhos.size(); i++) {
            if (this.filhos.get(i).getNome().toUpperCase().trim().equals(nome.toUpperCase().trim()))
                return this.filhos.get(i);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atores UML da Pasta UML.
     * @return Lista de Atores UML.
     */
    public List<AtorUML> getAtores() {
        return this.atores;
    }

    /**
     * Metodo responsavel por definir a Lista de Atores UML da Pasta UML.
     * @param atores Lista de Atores UML.
     */
    public void setAtores(List<AtorUML> atores) {
        this.atores = atores;
    }
    
    /**
     * Metodo responsavel por adicionar um Ator UML a Pasta UML.
     * @param ator Ator UML a ser adicionado.
     */
    public void addAtor(AtorUML ator) {
        if (ator != null)
            this.atores.add(ator);
    }
    
    /**
     * Metodo responsavel por retornar um Ator UML pelo Nome.
     * @param  nome Nome do Ator UML.
     * @return Ator UML encontrado.
     */
    public AtorUML getAtor(String nome) {
        for (int i = 0; i < this.atores.size(); i++) {
            if (this.atores.get(i).getNome().toUpperCase().trim().equals(nome.toUpperCase().trim()))
                return this.atores.get(i);
        }
        return null;
    }

    /**
     * Metodo responsavel por retornar a Lista de Casos de Uso UML da Pasta UML.
     * @return Lista de Casos de Uso UML da Pasta UML.
     */
    public List<CasoDeUsoUML> getCasosDeUso() {
        return this.casosDeUso;
    }

    /**
     * Metodo responsavel por definir a Lista de Casos de Uso UML da Pasta UML.
     * @param casosDeUso Lista de Casos de Uso UML.
     */
    public void setCasosDeUso(List<CasoDeUsoUML> casosDeUso) {
        this.casosDeUso = casosDeUso;
    }
    
    /**
     * Metodo responsavel por adicionar um Caso de Uso UML a Pasta UML.
     * @param casoDeUso Caso de Uso UML a ser adicionado.
     */
    public void addCasoDeUso(CasoDeUsoUML casoDeUso) {
        if (casoDeUso != null)
            this.casosDeUso.add(casoDeUso);
    }
    
    /**
     * Metodo responsavel por retornar um Caso de Uso UML pelo Nome.
     * @param  nome Nome do Caso de Uso UML.
     * @return Caso de Uso UML encontrado.
     */
    public CasoDeUsoUML getCasoDeUso(String nome) {
        for (int i = 0; i < this.casosDeUso.size(); i++) {
            if (this.casosDeUso.get(i).getNome().toUpperCase().trim().equals(nome.toUpperCase().trim()))
                return this.casosDeUso.get(i);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar o Caminho da Pasta UML.
     * @return Caminho da Pasta UML.
     */
    public String pasta() {
        if (this.pai == null)
            return this.nome;
//        if (this.pai.getPai() == null)
//            return this.nome;
        return this.pai.pasta() + "/" + this.nome;
    }
    
    @Override
    public String toString() {
        return this.pasta();
    }
}