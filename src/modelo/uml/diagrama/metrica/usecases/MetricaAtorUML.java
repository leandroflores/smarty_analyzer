package modelo.uml.diagrama.metrica.usecases;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.usecases.AtorUML;
import modelo.uml.diagrama.usecases.PastaUML;

/**
 * <p>Classe de Metrica <b>MetricaAtorUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Atores UML</b>.</p>
 * @author Leandro
 * @since  12/05/2017
 * @see    modelo.uml.diagrama.DiagramaCasoDeUso
 * @see    modelo.uml.diagrama.usecases.AtorUML
 */
public class MetricaAtorUML {
    private final DiagramaCasoDeUso diagramaCasoDeUso;
    private final MetricaPastaUML   metricaPastaUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso.
     */
    public MetricaAtorUML(DiagramaCasoDeUso diagramaCasoDeUso) {
        this.diagramaCasoDeUso = diagramaCasoDeUso;
        this.metricaPastaUML   = new MetricaPastaUML(this.diagramaCasoDeUso);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica de Ator UML.
     * @param  parametros Parametros para a Metrica.
     * @return Valor da Metrica.
     */
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.filter(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atores UML filtrados pelos parametros.
     * @param  parametros Lista de Parametros.
     * @return Lista de Atores UML filtrados.
     */
    public List<AtorUML> filter(Object[] parametros) {
        List<AtorUML> atoresUML = this.filterPastas((List<String>) parametros[0]);
                      atoresUML = this.filterNomes(atoresUML, (List<String>) parametros[1]);
//                        atoresUML = this.filterEstereotipos(atoresUML, (List<String>) parametros[2]);
        return atoresUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atores UML do Diagrama de Caso de Uso.
     * @return Lista de Atores UML de todo o Diagrama de Caso de Uso.
     */
    public List<AtorUML> getAtoresDiagramaCasoDeUso() {
        return this.getAtoresUML(this.diagramaCasoDeUso.getRaiz());
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atores UML pela Lista de Pastas.
     * @param  pastas Lista de Nomes.
     * @return Lista de Atores filtrados.
     */
    private List<AtorUML> filterPastas(List<String> pastas) {
        if (pastas == null)
            return this.getAtoresDiagramaCasoDeUso();
        if (pastas.isEmpty())
            return this.getAtoresDiagramaCasoDeUso();
        List<AtorUML>  atoresUML = new ArrayList<>();
        List<PastaUML> pastasUML = this.metricaPastaUML.filterNomes(pastas);
        for (int i = 0; i < pastasUML.size(); i++) {
            List<AtorUML> atual = this.getAtoresUML(pastasUML.get(i));
            for (int x = 0; x < atual.size(); x++) {
                if (atoresUML.contains(atual.get(x)) == false)
                    atoresUML.add(atual.get(x));
            }
        }
        return atoresUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Atores UML pela Lista de Nomes.
     * @param  atoresUML Lista de Atores UML inicial.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Atores UML filtrados.
     */
    private List<AtorUML> filterNomes(List<AtorUML> atoresUML, List<String> nomes) {
        List<AtorUML> filtro = new ArrayList<>();
        if (nomes == null)
            return atoresUML;
        if (nomes.isEmpty())
            return atoresUML;
        if (nomes.get(0).equals("*"))
            return atoresUML;
        for (int i = 0; i < atoresUML.size(); i++) {
            if (nomes.contains(atoresUML.get(i).getNome()))
                filtro.add(atoresUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Atores UML de uma Pasta UML.
     * @param  pastaUML Pasta UML para busca.
     * @return Lista de Atores UML encontradas.
     */
    public List<AtorUML> getAtoresUML(PastaUML pastaUML) {
        List<AtorUML> atoresUML = pastaUML.getAtores();
        for (int i = 0; i < pastaUML.getFilhos().size(); i++) {
            List<AtorUML> subAtoresUML = this.getAtoresUML(pastaUML.getFilhos().get(i));
            for (int x = 0; x < subAtoresUML.size(); x++) {
                if (atoresUML.contains(subAtoresUML.get(x)) == false)
                    atoresUML.add(subAtoresUML.get(x));
            }
        }
        return atoresUML;
    }
}