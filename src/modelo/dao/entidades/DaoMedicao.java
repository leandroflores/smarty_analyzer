package modelo.dao.entidades;

import java.util.List;
import modelo.dao.Dao;
import modelo.entidades.Medicao;
import modelo.entidades.Metrica;

/**
 * <p>Classe de Acesso ao Banco de Dados <b>DaoMedicao</b>.</p>
 * <p>Classe responsavel pela Operacoes envolvendo o Banco de Dados e a Classe de Entidade Medicao.</p>
 * @author Leandro
 * @since  27/09/2016
 * @see    model.dao.Dao
 * @see    model.entity.Medicao
 */
public class DaoMedicao extends Dao<Medicao> {
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public DaoMedicao() {
        super(Medicao.class);
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Medicoes por uma Metrica.
     * @param  metrica Metrica a ser Buscada.
     * @return Lista de Medicoes encontradas.
     */
    public List<Medicao> find(Metrica metrica) {
        return super.query("e.metrica.id = " + metrica.getId());
    }
    
    /**
     * Metodo responsavel por remover as Medicoes de uma Metrica.
     * @param metrica Metrica da Medicao.
     */
    public void remove(Metrica metrica) {
        List<Medicao> medicoes = this.find(metrica);
        acesso.getTransaction().begin();
        for (int i = 0; i < medicoes.size(); ++i) {
            this.delete(medicoes.get(i));
        }
        acesso.getTransaction().commit();
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Valores da Medicao.
     * @param  condition Condicao da Clausula.
     * @return Lista de Valores da Medicao.
     */
    public List<Double> getValores(String condition) {
        String query = "SELECT e.valor FROM Medicao e WHERE " + condition + " ORDER BY e.valor";
        return acesso.createQuery(query).getResultList();
    }
}