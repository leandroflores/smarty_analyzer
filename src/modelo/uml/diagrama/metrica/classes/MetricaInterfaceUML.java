package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.PacoteUML;

/**
 * <p>Classe de Metrica <b>MetricaInterfaceUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre as <b>Interfaces UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 */
public class MetricaInterfaceUML {
    private final DiagramaClasse   diagramaClasse;
    private final MetricaPacoteUML metricaPacoteUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaInterfaceUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse   = diagramaClasse;
        this.metricaPacoteUML = new MetricaPacoteUML(this.diagramaClasse);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica de Interface UML.
     * @param  parametros Parametros para a Metrica.
     * @return Valor da Metrica.
     */
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.filter(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces UML filtradas pelos parametros.
     * @param  parametros Lista de Parametros para a Metrica.
     * @return Lista de Interfaces UML filtradas.
     */
    public List<InterfaceUML> filter(Object[] parametros) {
        List<InterfaceUML> interfacesUML = this.filterPacotes((List<String>) parametros[0]);
                           interfacesUML = this.filterNomes(interfacesUML, (List<String>) parametros[1]);
                           interfacesUML = this.filterEstereotipos(interfacesUML, (List<String>) parametros[2]);
        return interfacesUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces UML do Diagrama de Classe.
     * @return Lista de Interfaces UML de todo o Diagrama de Classe.
     */
    public List<InterfaceUML> getInterfacesDiagramaClasse() {
        return this.getInterfacesUML(this.diagramaClasse.getRaiz());
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces UML pela Lista de Pacotes.
     * @param  pacotes Lista de Nomes dos Pacotes.
     * @return Lista de Interfaces UML filtradas.
     */
    private List<InterfaceUML> filterPacotes(List<String> pacotes) {
        if (pacotes == null)
            return this.getInterfacesDiagramaClasse();
        if (pacotes.isEmpty())
            return this.getInterfacesDiagramaClasse();
        List<InterfaceUML> interfacesUML = new ArrayList<>();
        List<PacoteUML>    pacotesUML    = this.metricaPacoteUML.filterNomes(pacotes);
        for (int i = 0; i < pacotesUML.size(); i++) {
            List<InterfaceUML> atual = this.getInterfacesUML(pacotesUML.get(i));
            for (int x = 0; x < atual.size(); x++) {
                if (interfacesUML.contains(atual.get(x)) == false)
                    interfacesUML.add(atual.get(x));
            }
        }
        return interfacesUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces UML de um Pacote UML.
     * @param  pacoteUML Pacote UML raiz.
     * @return Lista de Interfaces UML encontradas.
     */
    public List<InterfaceUML> getInterfacesUML(PacoteUML pacoteUML) {
        List<InterfaceUML> interfacesUML = pacoteUML.getInterfaces();
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            List<InterfaceUML> interfaces = this.getInterfacesUML(pacoteUML.getSubPacotes().get(i));
            for (int x = 0; x < interfaces.size(); x++) {
                if (interfacesUML.contains(interfaces.get(x)) == false)
                    interfacesUML.add(interfaces.get(x));
            }
        }
        return interfacesUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Interfaces UML pela Lista de Nomes.
     * @param  interfacesUML Lista de Interfaces UML inicial.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Interfaces UML filtradas.
     */
    private List<InterfaceUML> filterNomes(List<InterfaceUML> interfacesUML, List<String> nomes) {
        List<InterfaceUML> filtro = new ArrayList<>();
        if (nomes == null)
            return interfacesUML;
        if (nomes.isEmpty())
            return interfacesUML;
        if (nomes.get(0).equals("*"))
            return interfacesUML;
        for (int i = 0; i < interfacesUML.size(); i++) {
            if (nomes.contains(interfacesUML.get(i).getNome()))
                filtro.add(interfacesUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Interfaces UML pela Lista de Estereotipos.
     * @param  interfacesUML Lista de Classes UML inicial.
     * @param  estereotipos Lista de Estereotipos.
     * @return Lista de Classes UML filtradas.
     */
    private List<InterfaceUML> filterEstereotipos(List<InterfaceUML> interfacesUML, List<String> estereotipos) {
        List<InterfaceUML> filtro = new ArrayList<>();
        if (estereotipos == null)
            return interfacesUML;
        if (estereotipos.isEmpty())
            return interfacesUML;
        if (estereotipos.get(0).equals("*"))
            return interfacesUML;
        for (int i = 0; i < interfacesUML.size(); i++) {
            for (int x = 0; x < interfacesUML.get(i).getEstereotipos().size(); x++) {
                if (estereotipos.contains(interfacesUML.get(i).getEstereotipos().get(x))) {
                    if (filtro.contains(interfacesUML.get(i)) == false) {
                        filtro.add(interfacesUML.get(i));
                    }
                }
            }
        }
        return filtro;
    }
}