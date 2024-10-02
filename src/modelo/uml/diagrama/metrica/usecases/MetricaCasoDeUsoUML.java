package modelo.uml.diagrama.metrica.usecases;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.usecases.CasoDeUsoUML;
import modelo.uml.diagrama.usecases.PastaUML;

/**
 * <p>Classe de Metrica <b>MetricaCasoDeUsoUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Casos de Uso UML</b>.</p>
 * @author Leandro
 * @since  12/05/2017
 * @see    modelo.uml.diagrama.DiagramaCasoDeUso
 * @see    modelo.uml.diagrama.usecases.CasoDeUsoUML
 */
public class MetricaCasoDeUsoUML {
    private final DiagramaCasoDeUso diagramaCasoDeUso;
    private final MetricaPastaUML   metricaPastaUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso.
     */
    public MetricaCasoDeUsoUML(DiagramaCasoDeUso diagramaCasoDeUso) {
        this.diagramaCasoDeUso = diagramaCasoDeUso;
        this.metricaPastaUML   = new MetricaPastaUML(this.diagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica de Caso de Uso UML.
     * @param  parametros Parametros para a Metrica.
     * @return Valor da Metrica.
     */
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.filter(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Casos de Uso UML filtrados pelos parametros.
     * @param  parametros Lista de Parametros.
     * @return Lista de Casos de Uso UML filtrados.
     */
    public List<CasoDeUsoUML> filter(Object[] parametros) {
        List<CasoDeUsoUML> casosDeUsoUML = this.filterPastas((List<String>) parametros[0]);
                           casosDeUsoUML = this.filterNomes(casosDeUsoUML, (List<String>) parametros[1]);
//                        atoresUML = this.filterEstereotipos(atoresUML, (List<String>) parametros[2]);
        return casosDeUsoUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Casos de Uso UML do Diagrama de Caso de Uso.
     * @return Lista de Casos de Uso UML de todo o Diagrama de Caso de Uso.
     */
    public List<CasoDeUsoUML> getCasosDeUsoDiagramaCasoDeUso() {
        return this.getCasosDeUsoUML(this.diagramaCasoDeUso.getRaiz());
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Casos de Uso UML pela Lista de Pastas.
     * @param  pastas Lista de Nomes.
     * @return Lista de Casos de Uso filtrados.
     */
    private List<CasoDeUsoUML> filterPastas(List<String> pastas) {
        if (pastas == null)
            return this.getCasosDeUsoDiagramaCasoDeUso();
        if (pastas.isEmpty())
            return this.getCasosDeUsoDiagramaCasoDeUso();
        List<CasoDeUsoUML>  casosDeUsoUML = new ArrayList<>();
        List<PastaUML>      pastasUML     = this.metricaPastaUML.filterNomes(pastas);
        for (int i = 0; i < pastasUML.size(); i++) {
            List<CasoDeUsoUML> atual = this.getCasosDeUsoUML(pastasUML.get(i));
            for (int x = 0; x < atual.size(); x++) {
                if (casosDeUsoUML.contains(atual.get(x)) == false)
                    casosDeUsoUML.add(atual.get(x));
            }
        }
        return casosDeUsoUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Casos de Uso UML pela Lista de Nomes.
     * @param  casosDeUsoUML Lista de Casos de Uso UML inicial.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Casos de Uso UML filtrados.
     */
    private List<CasoDeUsoUML> filterNomes(List<CasoDeUsoUML> casosDeUsoUML, List<String> nomes) {
        List<CasoDeUsoUML> filtro = new ArrayList<>();
        if (nomes == null)
            return casosDeUsoUML;
        if (nomes.isEmpty())
            return casosDeUsoUML;
        if (nomes.get(0).equals("*"))
            return casosDeUsoUML;
        for (int i = 0; i < casosDeUsoUML.size(); i++) {
            if (nomes.contains(casosDeUsoUML.get(i).getNome()))
                filtro.add(casosDeUsoUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Casos de Uso UML de uma Pasta UML.
     * @param  pastaUML Pasta UML para busca.
     * @return Lista de Casos de Uso UML encontradas.
     */
    public List<CasoDeUsoUML> getCasosDeUsoUML(PastaUML pastaUML) {
        List<CasoDeUsoUML> casosDeUsoUML = pastaUML.getCasosDeUso();
        for (int i = 0; i < pastaUML.getFilhos().size(); i++) {
            List<CasoDeUsoUML> subCasosDeUsoUML = this.getCasosDeUsoUML(pastaUML.getFilhos().get(i));
            for (int x = 0; x < subCasosDeUsoUML.size(); x++) {
                if (casosDeUsoUML.contains(subCasosDeUsoUML.get(x)) == false)
                    casosDeUsoUML.add(subCasosDeUsoUML.get(x));
            }
        }
        return casosDeUsoUML;
    }
}