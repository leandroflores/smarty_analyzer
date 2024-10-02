package modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * <p>Classe de Acesso ao <b>Banco de Dados</b>.</p>
 * <p>Classe responsavel pelas operacoes envolvendo <b>Persistencia</b> no Sistema.</p>
 * @author Leandro
 * @param  <Entidade> Classe de Entidade.
 * @since  15/12/2016
 * @see    javax.persistence.EntityManager
 * @see    javax.persistence.Persistence
 */
public class Dao<Entidade> {
    protected static EntityManager acesso = Persistence.createEntityManagerFactory("UP").createEntityManager();
    private final Class classe;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param classe Classe de Entidade.
     */
    public Dao(Class classe) {
        this.classe = classe;
    }
    
    /**
     * Metodo responsavel por <b>Inserir</b> um Objeto no Banco de Dados.
     * @param  objeto Objeto a ser inserido.
     * @return Objeto foi inserido.
     */
    public boolean insert(Entidade objeto) {
        if (objeto != null) {
            acesso.getTransaction().begin();
                acesso.persist(objeto);
            acesso.getTransaction().commit();
            return true;
        }
        return false;
    }
    
    /**
     * Metodo responsavel por <b>Atualizar</b> um Objeto no Banco de Dados.
     * @param  objeto Objeto a ser Atualizado.
     * @return Objeto foi Atualizado.
     */
    public boolean update(Entidade objeto) {
        if (objeto != null) {
            acesso.getTransaction().begin();
                acesso.merge(objeto);
            acesso.getTransaction().commit();
            return true;
        }
        return false;
    }
    
    /**
     * Metodo responsavel por <b>Remover</b> um Objeto do Banco de Dados.
     * @param  id Id do Objeto.
     * @return Objeto foi removido.
     */
    public boolean delete(Object id) {
        Entidade objeto = this.get(id);
        if (objeto != null) {
            acesso.getTransaction().begin();
                acesso.remove(objeto);
            acesso.getTransaction().commit();
            return true;
        }
        return false;
    }
    
    /**
     * Metodo responsavel por <b>Buscar</b> um Objeto pelo Id.
     * @param  id Id do Objeto.
     * @return Objeto encontrado.
     */
    public Entidade get(Object id) {
        return (Entidade) acesso.find(this.classe, id);
    }
    
    /**
     * Metodo responsavel por retornar o <b>Proximo Id</b> disponivel para a Entidade.
     * @return Proximo Id disponivel.
     */
    public Long nextId() {
        String query = "SELECT MAX(e.id) + 1 FROM " + this.classe.getSimpleName() + " e";
        if (acesso.createQuery(query).getSingleResult() != null) {
            return (Long) acesso.createQuery(query).getSingleResult();
        }else {
            return 1L;
        }
    }
    
    /**
     * Metodo responsavel por retornar a <b>Lista</b> dos Objetos no Banco de Dados.
     * @return Lista de Objetos no Banco de Dados.
     */
    public List<Entidade> select() {
        String query = "SELECT e FROM " + this.classe.getSimpleName() + " e";
        return acesso.createQuery(query).getResultList();
    }
    
    /**
     * Metodo responsavel por retornar uma <b>Lista</b> de Objetos pela Condicao.
     * @param  condition Condicao do WHERE.
     * @return Lista de Objetos encontrados.
     */
    public List<Entidade> query(String condition) {
        String query = "SELECT e FROM " + this.classe.getSimpleName() + " e WHERE " + condition;
        return (List) acesso.createQuery(query).getResultList();
    }
}