package modelo.dao.entidades;

import java.util.List;
import modelo.dao.Dao;
import modelo.entidades.Metrica;

/**
 * <p>Classe de Acesso ao Banco de Dados <b>DaoMetrica</b>.</p>
 * <p>Classe responsavel pela Operacoes envolvendo o Banco de Dados e a Classe de Entidade Metrica.</p>
 * @author Leandro
 * @since  05/09/2016
 * @see    model.dao.Dao
 * @see    model.entity.Metrica
 */
public class DaoMetrica extends Dao<Metrica> {
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public DaoMetrica() {
        super(Metrica.class);
    }
    
    @Override
    public boolean delete(Object id) {
        Metrica metrica = this.get(id);
        if (metrica != null) {
            metrica.setAtiva(false);
            this.update(metrica);
            return true;
        }
        return false;
    }
    
    @Override
    public List select() {
        String query = "SELECT e FROM Metrica e WHERE e.ativa=true";
        return acesso.createQuery(query).getResultList();
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Metricas buscando pelo Nome, Rotulo, Descricao ou Operacao.
     * @param  search Palavra-Chave da Busca.
     * @return Lista de Metricas encontradas.
     */
    public List search(String search) {
        if (search.trim().equals("")) return this.select();
        String condition = "e.nome LIKE '%"      + search + "%' OR " +
                           "e.rotulo LIKE '%"    + search + "%' OR " +
                           "e.descricao LIKE '%" + search + "%' OR " +
                           "e.operacao LIKE '%"  + search + "%'";
        return super.query(condition);
    }
    
    @Override
    public List<Metrica> query(String condition) {
        String query = "SELECT e FROM Metrica e WHERE " + condition + " AND e.ativa=true";
        return (List) acesso.createQuery(query).getResultList();
    }
}