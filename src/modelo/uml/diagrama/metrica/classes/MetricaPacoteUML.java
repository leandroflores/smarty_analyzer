package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.PacoteUML;

/**
 * <p>Classe de Metrica <b>MetricaPacoteUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre os <b>Pacotes UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.PacoteUML
 */
public class MetricaPacoteUML {
    private final DiagramaClasse diagramaClasse;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaPacoteUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse = diagramaClasse;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pacotes UML do Diagrama de Classe.
     * @return Lista de Pacotes UML de todo o Diagrama de Classe.
     */
    public List<PacoteUML> getPacotesDiagramaClasse() {
        List<PacoteUML> pacotes = new ArrayList<>();
                        pacotes.add(this.diagramaClasse.getRaiz());
                        pacotes.addAll(this.getPacotesUML(this.diagramaClasse.getRaiz()));
        return pacotes;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pacotes UML de um Pacote UML.
     * @param  pacoteUML Pacote UML raiz.
     * @return Lista de Pacotes UML encontrados.
     */
    public List<PacoteUML> getPacotesUML(PacoteUML pacoteUML) {
        List<PacoteUML> pacotesUML = pacoteUML.getSubPacotes();
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            pacotesUML.addAll(this.getPacotesUML(pacoteUML.getSubPacotes().get(i)));
        }
        return pacotesUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pacotes UML contido na Lista de Nomes.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Pacotes UML encontrados.
     */
    public List<PacoteUML> filterNomes(List<String> nomes) {
        if (nomes == null)
            return this.getPacotesDiagramaClasse();
        if (nomes.isEmpty())
            return this.getPacotesDiagramaClasse();
        if (nomes.get(0).equals("*"))
            return this.getPacotesDiagramaClasse();
        if (nomes.contains("src"))
            return this.getPacotesDiagramaClasse();
        return this.diagramaClasse.getSubpacotesUML(nomes);
    }
}