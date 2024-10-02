package modelo.uml.diagrama.metrica.classes;

import java.util.ArrayList;
import java.util.List;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.PacoteUML;

/**
 * <p>Classe de Metrica <b>MetricaClasseUML</b>.</p>
 * <p>Classe responsavel por extrair as Metricas sobre as <b>Classes UML</b>.</p>
 * @author Leandro
 * @since  22/03/2017
 * @see    modelo.uml.diagrama.DiagramaClasse
 * @see    modelo.uml.diagrama.classes.ClasseUML
 */
public class MetricaClasseUML {
    private final DiagramaClasse   diagramaClasse;
    private final MetricaPacoteUML metricaPacoteUML;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public MetricaClasseUML(DiagramaClasse diagramaClasse) {
        this.diagramaClasse   = diagramaClasse;
        this.metricaPacoteUML = new MetricaPacoteUML(this.diagramaClasse);
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Metrica de Classe UML.
     * @param  parametros Parametros para a Metrica.
     * @return Valor da Metrica.
     */
    public Double getValorMetrica(Object[] parametros) {
        return Double.parseDouble(Integer.toString(this.filter(parametros).size()));
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Classes UML filtradas pelos parametros.
     * @param  parametros Lista de Parametros.
     * @return Lista de Classes UML filtradas.
     */
    public List<ClasseUML> filter(Object[] parametros) {
        List<ClasseUML> classesUML = this.filterPacotes((List<String>) parametros[0]);
                        classesUML = this.filterNomes(classesUML, (List<String>) parametros[1]);
                        classesUML = this.filterEstereotipos(classesUML, (List<String>) parametros[2]);
                        classesUML = this.filterAbstrato(classesUML, (Boolean) parametros[3]);
                        classesUML = this.filterFolha(classesUML, (Boolean) parametros[4]);
        return classesUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Classes UML do Diagrama de Classes.
     * @return Lista de Classes UML de todo o Diagrama de Classes.
     */
    public List<ClasseUML> getClassesDiagramaClasse() {
        return this.getClassesUML(this.diagramaClasse.getRaiz());
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Classes UML pela Lista de Pacotes.
     * @param  pacotes Lista de Nomes.
     * @return Lista de Classes filtradas.
     */
    private List<ClasseUML> filterPacotes(List<String> pacotes) {
        if (pacotes == null)
            return this.getClassesDiagramaClasse();
        if (pacotes.isEmpty())
            return this.getClassesDiagramaClasse();
        List<ClasseUML> classesUML = new ArrayList<>();
        List<PacoteUML> pacotesUML = this.metricaPacoteUML.filterNomes(pacotes);
        for (int i = 0; i < pacotesUML.size(); i++) {
            List<ClasseUML> atual = this.getClassesUML(pacotesUML.get(i));
            for (int x = 0; x < atual.size(); x++) {
                if (classesUML.contains(atual.get(x)) == false)
                    classesUML.add(atual.get(x));
            }
        }
        return classesUML;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Classes UML de um Pacote UML.
     * @param  pacoteUML Pacote UML para busca.
     * @return Lista de Classes UML encontradas.
     */
    public List<ClasseUML> getClassesUML(PacoteUML pacoteUML) {
        List<ClasseUML> classesUML = pacoteUML.getClasses();
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            List<ClasseUML> subClassesUML = this.getClassesUML(pacoteUML.getSubPacotes().get(i));
            for (int x = 0; x < subClassesUML.size(); x++) {
                if (classesUML.contains(subClassesUML.get(x)) == false)
                    classesUML.add(subClassesUML.get(x));
            }
        }
        return classesUML;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Classes UML pela Lista de Nomes.
     * @param  classesUML Lista de Classes UML inicial.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Classes UML filtradas.
     */
    private List<ClasseUML> filterNomes(List<ClasseUML> classesUML, List<String> nomes) {
        List<ClasseUML> filtro = new ArrayList<>();
        if (nomes == null)
            return classesUML;
        if (nomes.isEmpty())
            return classesUML;
        if (nomes.get(0).equals("*"))
            return classesUML;
        for (int i = 0; i < classesUML.size(); i++) {
            if (nomes.contains(classesUML.get(i).getNome()))
                filtro.add(classesUML.get(i));
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Classes UML pela Lista de Estereotipos.
     * @param  classesUML Lista de Classes UML inicial.
     * @param  estereotipos Lista de Estereotipos.
     * @return Lista de Classes UML filtradas.
     */
    private List<ClasseUML> filterEstereotipos(List<ClasseUML> classesUML, List<String> estereotipos) {
        List<ClasseUML> filtro = new ArrayList<>();
        if (estereotipos == null)
            return classesUML;
        if (estereotipos.isEmpty())
            return classesUML;
        if (estereotipos.get(0).equals("*"))
            return classesUML;
        for (int i = 0; i < classesUML.size(); i++) {
            ClasseUML classeUML = classesUML.get(i);
            if (classeUML.getEstereotipos().isEmpty() == false) {
                for (int x = 0; x < classeUML.getEstereotipos().size(); x++) {
                    if (estereotipos.contains(classeUML.getEstereotipos().get(x))) {
                        if (filtro.contains(classeUML) == false)
                            filtro.add(classesUML.get(i));
                    }
                }
            }
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Classes UML filtrando pela flag Abstrato.
     * @param  classesUML Lista de Classes UML inicial.
     * @param  abstrato Flag Abstrato da Classe UML.
     * @return Lista de Classes UML filtradas.
     */
    private List<ClasseUML> filterAbstrato(List<ClasseUML> classesUML, Boolean abstrato) {
        List<ClasseUML> filtro = new ArrayList<>();
        if (abstrato == null)
            return classesUML;
        for (int i = 0; i < classesUML.size(); i++) {
            ClasseUML classeUML = classesUML.get(i);
            if ((abstrato) && (classeUML.isAbstrato()))
                filtro.add(classeUML);
            else if ((!abstrato) && (!classeUML.isAbstrato()))
                filtro.add(classeUML);
        }
        return filtro;
    }
    
    /**
     * Metodo responsavel por retornar uma Lista de Classes UML filtrando pela flag Abstrato.
     * @param classesUML Lista de Classes UML inicial.
     * @param folha Flag Folha da Classe UML.
     * @return Lista de Classes UML filtradas.
     */
    private List<ClasseUML> filterFolha(List<ClasseUML> classesUML, Boolean folha) {
        List<ClasseUML> filtro = new ArrayList<>();
        if (folha == null)
            return classesUML;
        for (int i = 0; i < classesUML.size(); i++) {
            ClasseUML classeUML = classesUML.get(i);
            if ((folha) && (classeUML.isFolha()))
                filtro.add(classeUML);
            else if ((!folha) && (!classeUML.isFolha()))
                filtro.add(classeUML);
        }
        return filtro;
    }
}