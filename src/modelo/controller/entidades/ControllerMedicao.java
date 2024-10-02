package modelo.controller.entidades;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import modelo.dao.entidades.DaoMedicao;
import modelo.entidades.Medicao;

/**
 * <p>Classe de Controle <b>ControllerMedicao</b>.</p>
 * <p>Classe responsavel por ser a <b>Controladora</b> da Classe Medicao.</p>
 * @author Leandro
 * @since  28/09/2016
 * @see    model.entity.Medicao
 * @see    model.dao.entity.DaoMedicao
 */
public class ControllerMedicao {
    private static final DaoMedicao DAO_MEDICAO = new DaoMedicao();
    
    /**
     * Metodo responsavel por retornar os Dados das Medicoes para Consulta.
     * @param medicoes Medicoes a serem buscadas.
     * @return Dados das Medicoes para Consulta.
     */
    public String[][] getMedicoes(List<Medicao> medicoes) {
        String[][] dados = new String[medicoes.size()][6];
        for (int i = 0; i < dados.length; ++i) {
            dados[i] = medicoes.get(i).getDados();
        }
        return dados;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista com os Valores das Medicoes encontradas.
     * @param  condition Condicao da Clausula de Busca.
     * @return Lista com os Valores das Medicoes encontradas.
     */
    public Double[] getValores(String condition) {
        List<Medicao> medicoes = DAO_MEDICAO.query(condition);
        Double[] valores    = new Double[5];
                 valores[0] = Double.parseDouble(Integer.toString(medicoes.size())); // Tamanho
                 valores[1] = 0d; // Soma
                 valores[2] = 0d; // Media
                 valores[3] = Double.MIN_VALUE; // Maximo
                 valores[4] = Double.MAX_VALUE; // Minimo
        for (int i = 0; i < medicoes.size(); ++i) {
            valores[1] += medicoes.get(i).getValor();
            if (valores[3] < medicoes.get(i).getValor()) 
                valores[3] = medicoes.get(i).getValor();
            if (valores[4] > medicoes.get(i).getValor()) 
                valores[4] = medicoes.get(i).getValor();
        }
        if (valores[0] != 0) 
            valores[2] = (valores[1] / valores[0]);
        return valores;
    }
    
    /**
     * Metodo responsavel por retornar a Mediana de uma Lista de Valores.
     * @param  condition Condicao da Clausula.
     * @return Mediana de uma Lista de Valores.
     */
    public Double getMediana(String condition) {
        List<Double> valores = DAO_MEDICAO.getValores(condition);
        int index = valores.size();
        if (index > 0) {
            int mod   = index % 2;
            if (mod == 1)
                return valores.get(index / 2);
            return (valores.get((index / 2) - 1) + valores.get(index / 2)) / 2;
        }
        return 0d;
    }
    
    public String getModa(String condition) {
        List<Double> valores = DAO_MEDICAO.getValores(condition);
        HashMap map = new HashMap();
        Integer frequencia;
        Integer numeroAtual;
        Integer numeroMaior = 0;
        Double  moda        = null;
        String  toReturn    = "";
        boolean flag        = false;
        for (int count = 0; count < valores.size(); count++) {
            frequencia = (Integer) map.get(valores.get(count));
            if (frequencia == null) {
                map.put(valores.get(count), 1);
                if (moda == null)
                    moda = valores.get(count);
                if (flag == false)
                    toReturn += valores.get(count) + " ";
            }else {
                flag = true;
                map.put(valores.get(count), frequencia + 1);
                numeroAtual = frequencia + 1;
                if (numeroAtual > numeroMaior) {
                    numeroMaior = numeroAtual;
                    moda     = valores.get(count);
                    toReturn = moda + " ";
                }else if (Objects.equals(numeroAtual, numeroMaior)) {
                    toReturn += moda + " ";
                }
            }
        }
        toReturn += "(" + map.get(moda) + ")";
//        System.out.println(toReturn);
        return toReturn;
    }
}