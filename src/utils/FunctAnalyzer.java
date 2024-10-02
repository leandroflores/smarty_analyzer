package utils;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import modelo.uml.diagrama.DiagramaCasoDeUso;
import modelo.uml.diagrama.DiagramaClasse;
import modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso;
import modelo.uml.diagrama.metrica.MetricaDiagramaClasse;

/**
 * <p>Classe de Funcao <b>FunctAnalyzer</b>.</p>
 * <p>Classe responsavel por definir as Funcoes para <b>Extracao de Metricas</b> dos Modelos.</p>
 * @author Leandro
 * @since  13/03/2017
 * @see    modelo.uml.diagrama.metrica.MetricaDiagramaCasoDeUso
 * @see    modelo.uml.diagrama.metrica.MetricaDiagramaClasse
 */
public class FunctAnalyzer {
    private final DiagramaClasse           diagramaClasse;
    private final DiagramaCasoDeUso        diagramaCasoDeUso;
    private final MetricaDiagramaClasse    metricaDiagramaClasse;
    private final MetricaDiagramaCasoDeUso metricaDiagramaCasoDeUso;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param diagramaClasse Diagrama de Classe UML.
     */
    public FunctAnalyzer(DiagramaClasse diagramaClasse) {
        this.diagramaClasse           = diagramaClasse;
        this.diagramaCasoDeUso        = null;
        this.metricaDiagramaClasse    = new MetricaDiagramaClasse(this.diagramaClasse);
        this.metricaDiagramaCasoDeUso = null;
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param diagramaCasoDeUso Diagrama de Caso de Uso UML.
     */
    public FunctAnalyzer(DiagramaCasoDeUso diagramaCasoDeUso) {
        this.diagramaClasse           = null;
        this.diagramaCasoDeUso        = diagramaCasoDeUso;
        this.metricaDiagramaClasse    = null;
        this.metricaDiagramaCasoDeUso = new MetricaDiagramaCasoDeUso(this.diagramaCasoDeUso);
    }
    
    /**
     * 
     * @param expressao
     * @return 
     */
    public String getExpressao(String expressao) {
        String toReturn = "";
        for (int i = 0; i < expressao.length(); i++) {
            if (this.checkCharacter(expressao.charAt(i))) {
                toReturn += expressao.charAt(i);
            }else {
                String valor = expressao.substring(i, expressao.indexOf(")", i) + 1);
                   toReturn += this.getValorClausula(valor);
                          i  = expressao.indexOf(")", i);
            }
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar a Palavra-Chave da Clausula.
     * @param  clausula Clausula da Operacao.
     * @return Palavra-Chave da Clausula.
     */
    private String getPalavraChave(String clausula) {
        if (!clausula.contains("("))
            return "";
        return clausula.substring(0, clausula.indexOf("("));
    }
    
    /**
     * Metodo responsavel por retornar o Filtro da Clausula.
     * @param  clausula Clausula da Operacao.
     * @return Filtro da Clausula.
     */
    private String getFiltro(String clausula) {
        if ((!clausula.contains("(")) || (!clausula.contains(")")))
            return "";
        if (clausula.indexOf("(") > clausula.indexOf(")"))
            return "";
        return clausula.substring(clausula.indexOf("(") + 1, clausula.indexOf(")"));
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Clausula.
     * @param  clausula Clausula da Operacao.
     * @return Valor da Clausula.
     */
    private Double getValorClausula(String clausula) {
        String palavraChave = this.getPalavraChave(clausula);
        String filtro       = this.getFiltro(clausula);
        if (this.metricaDiagramaClasse != null)
            return this.getValorClausulaDiagramaClasse(palavraChave, filtro);
        else if (this.metricaDiagramaCasoDeUso != null)
            return this.getValorClausulaDiagramaCasoDeUso(palavraChave, filtro);
        return null;
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Clausula do Diagrama de Classe.
     * @param  palavraChave Palavra-Chave da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Valor da Clausula.
     */
    private Double getValorClausulaDiagramaClasse(String palavraChave, String filtro) {
        if (palavraChave.toUpperCase().trim().equals("ATRIBUTO"))
            return this.metricaDiagramaClasse.getMetricaAtributoUML(this.getFiltrosAtributos(filtro));
        if (palavraChave.toUpperCase().trim().equals("ATTRIBUTE"))
            return this.metricaDiagramaClasse.getMetricaAtributoUML(this.getFiltrosAtributos(filtro));
        if (palavraChave.toUpperCase().trim().equals("METODO"))
            return this.metricaDiagramaClasse.getMetricaMetodoUML(this.getFiltrosMetodos(filtro));
        if (palavraChave.toUpperCase().trim().equals("METHOD"))
            return this.metricaDiagramaClasse.getMetricaMetodoUML(this.getFiltrosMetodos(filtro));
        if (palavraChave.toUpperCase().trim().equals("OPERACAO"))
            return this.metricaDiagramaClasse.getMetricaMetodoUML(this.getFiltrosMetodos(filtro));
        if (palavraChave.toUpperCase().trim().equals("OPERATION"))
            return this.metricaDiagramaClasse.getMetricaMetodoUML(this.getFiltrosMetodos(filtro));
        if (palavraChave.toUpperCase().trim().equals("CLASSE"))
            return this.metricaDiagramaClasse.getMetricaClasseUML(this.getFiltrosClasses(filtro));
        if (palavraChave.toUpperCase().trim().equals("CLASS"))
            return this.metricaDiagramaClasse.getMetricaClasseUML(this.getFiltrosClasses(filtro));
        if (palavraChave.toUpperCase().trim().equals("ESTEREOTIPO"))
            return this.metricaDiagramaClasse.getMetricaEstereotipoUML(this.getFiltrosEstereotipos(filtro));
        if (palavraChave.toUpperCase().trim().equals("STEREOTYPE"))
            return this.metricaDiagramaClasse.getMetricaEstereotipoUML(this.getFiltrosEstereotipos(filtro));
        if (palavraChave.toUpperCase().trim().equals("GENERALIZACAO"))
            return this.metricaDiagramaClasse.getMetricaGeneralizacaoUML(this.getFiltrosGeneralizacao(filtro));
        if (palavraChave.toUpperCase().trim().equals("HERANCA"))
            return this.metricaDiagramaClasse.getMetricaGeneralizacaoUML(this.getFiltrosGeneralizacao(filtro));
        if (palavraChave.toUpperCase().trim().equals("INTERFACE"))
            return this.metricaDiagramaClasse.getMetricaInterfaceUML(this.getFiltrosInterfaces(filtro));
        if (palavraChave.toUpperCase().trim().equals("REALIZACAO"))
            return this.metricaDiagramaClasse.getMetricaRealizacaoUML(this.getFiltrosRealizacao(filtro));
        return null;
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Clausula do Diagrama de Caso de Uso.
     * @param  palavraChave Palavra-Chave da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Valor da Clausula.
     */
    private Double getValorClausulaDiagramaCasoDeUso(String palavraChave, String filtro) {
        if (palavraChave.toUpperCase().trim().equals("ATOR"))
            return this.metricaDiagramaCasoDeUso.getMetricaAtorUML(this.getFiltrosAtor(filtro));
        if (palavraChave.toUpperCase().trim().equals("ACTOR"))
            return this.metricaDiagramaCasoDeUso.getMetricaAtorUML(this.getFiltrosAtor(filtro));
        if (palavraChave.toUpperCase().trim().equals("CASO_DE_USO"))
            return this.metricaDiagramaCasoDeUso.getMetricaCasoDeUsoUML(this.getFiltrosCasoDeUso(filtro));
        if (palavraChave.toUpperCase().trim().equals("USE_CASE"))
            return this.metricaDiagramaCasoDeUso.getMetricaCasoDeUsoUML(this.getFiltrosCasoDeUso(filtro));
        return null;
    }
    
    /**
     * Metodo responsavel por verificar se um Caractere e parte da Expressao.
     * @param  character Caractere a ser avaliado.
     * @return Caractere faz parte da Expressao.
     */
    public boolean checkCharacter(char character) {
        switch (character) {
            case '(':
            case ')':
            case '+':
            case '-':
            case '*':
            case '/':
            case ' ':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Filtros da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Filtros da Clausula.
     */
    private Object[] getFiltrosAtributos(String filtro) {
        Object[] filtros    = new Object[7];
                 filtros[1] = this.getPacotes(filtro);
                     filtro = this.clearPacotes(filtro);
                 filtros[2] = this.getNomes(filtro);
                    filtro  = this.clearNomes(filtro);
                 filtros[3] = this.getVisibilidade(filtro);
                 filtros[4] = this.getConstante(filtro);
                 filtros[5] = this.getEstatico(filtro);
                 filtros[6] = this.getTipos(filtro);
                 filtros[0] = this.getContexto(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Filtros da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Filtros da Clausula.
     */
    private Object[] getFiltrosMetodos(String filtro) {
        Object[] filtros    = new Object[8];
                 filtros[1] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[2] = this.getNomes(filtro);
                    filtro  = this.clearNomes(filtro);
                 filtros[3] = this.getVisibilidade(filtro);
                 filtros[4] = this.getAbstrato(filtro);
                 filtros[5] = this.getConstrutor(filtro);
                 filtros[6] = this.getConstante(filtro);
                 filtros[7] = this.getEstatico(filtro);
                 filtros[0] = this.getContexto(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros da Classe.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros da Classe.
     */
    private Object[] getFiltrosClasses(String filtro) {
        Object[] filtros    = new Object[5];
                 filtros[0] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[1] = this.getNomes(filtro);
                    filtro  = this.clearNomes(filtro);
                 filtros[2] = this.getEstereotipos(filtro);
                    filtro  = this.clearTipos(filtro);
                 filtros[3] = this.getAbstrato(filtro);
                 filtros[4] = this.getConstante(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros do Estereotipo.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros do Estereotipo.
     */
    private Object[] getFiltrosEstereotipos(String filtro) {
        Object[] filtros    = new Object[4];
                 filtros[1] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[2] = this.getNomes(filtro);
                    filtro  = this.clearNomes(filtro);
                 filtros[3] = this.getEstereotipos(filtro);
                    filtro  = this.clearTipos(filtro);
                 filtros[0] = this.getContexto(filtro);
        return filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros da Generalizacao.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros da Generalizacao.
     */
    private Object[] getFiltrosGeneralizacao(String filtro) {
        Object[] filtros    = new Object[2];
                 filtros[1] = this.getNomes(filtro);
                     filtro = this.clearNomes(filtro);
                 filtros[0] = this.getContexto(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros da Interface.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros da Interface.
     */
    private Object[] getFiltrosInterfaces(String filtro) {
        Object[] filtros    = new Object[3];
                 filtros[0] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[1] = this.getNomes(filtro);
                    filtro  = this.clearNomes(filtro);
                 filtros[2] = this.getEstereotipos(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros da Realizacao.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros da Realizacao.
     */
    private Object[] getFiltrosRealizacao(String filtro) {
        Object[] filtros    = new Object[1];
                 filtros[0] = this.getNomes(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros do Ator.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros do Ator.
     */
    private Object[] getFiltrosAtor(String filtro) {
        Object[] filtros    = new Object[2];
                 filtros[0] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[1] = this.getNomes(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar o Objeto com os filtros do Caso de Uso.
     * @param  filtro Filtro da Clausula.
     * @return Objeto com os filtros do Caso de Uso.
     */
    private Object[] getFiltrosCasoDeUso(String filtro) {
        Object[] filtros    = new Object[2];
                 filtros[0] = this.getPacotes(filtro);
                    filtro  = this.clearPacotes(filtro);
                 filtros[1] = this.getNomes(filtro);
        return   filtros;
    }
    
    /**
     * Metodo responsavel por retornar a Visibilidade do Filtro.
     * @param  filtro Filtro da Clausula.
     * @return Visibilidade do Filtro.
     */
    private String getVisibilidade(String filtro) {
        if (filtro.toUpperCase().contains("PUBLIC"))
            return "public";
        if (filtro.toUpperCase().contains("PUBLICO"))
            return "public";
        if (filtro.toUpperCase().contains("PUBLICA"))
            return "public";
        if (filtro.toUpperCase().contains("PROTECTED"))
            return "protected";
        if (filtro.toUpperCase().contains("PROTEGIDO"))
            return "protected";
        if (filtro.toUpperCase().contains("PROTEGIDA"))
            return "protected";
        if (filtro.toUpperCase().contains("DEFAULT"))
            return "default";
        if (filtro.toUpperCase().contains("PADRAO"))
            return "default";
        if (filtro.toUpperCase().contains("PACKAGE"))
            return "default";
        if (filtro.toUpperCase().contains("PRIVATE"))
            return "private";
        if (filtro.toUpperCase().contains("PRIVADO"))
            return "private";
        if (filtro.toUpperCase().contains("PRIVADA"))
            return "private";
        return "";
    }
    
    /**
     * Metodo responsavel por retornar a flag Constante do Filtro.
     * @param  filtro Filtro da Clausula.
     * @return Flag Constante do Filtro.
     */
    private Boolean getConstante(String filtro) {
        if (filtro.toUpperCase().contains("NO-FINAL"))
            return false;
        if (filtro.toUpperCase().contains("NAO-FINAL"))
            return false;
        if (filtro.toUpperCase().contains("NAO-FOLHA"))
            return false;
        if (filtro.toUpperCase().contains("NAO-CONSTANTE"))
            return false;
        if (filtro.toUpperCase().contains("FINAL"))
            return true;
        if (filtro.toUpperCase().contains("FOLHA"))
            return true;
        if (filtro.toUpperCase().contains("CONSTANTE"))
            return true;
        return null;
    }
    
    /**
     * Metodo responsavel por retornar a flag Estatico do Filtro.
     * @param  filtro Filtro da Clausula.
     * @return Flag Estatico do Filtro.
     */
    private Boolean getEstatico(String filtro) {
        if (filtro.toUpperCase().contains("NO-STATIC"))
            return false;
        if (filtro.toUpperCase().contains("NAO-STATICO"))
            return false;
        if (filtro.toUpperCase().contains("STATIC"))
            return true;
        if (filtro.toUpperCase().contains("ESTATICO"))
            return true;
        return null;
    }
    
    /**
     * Metodo responsavel por retornar a Flag Abstrato pelo Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Flag Abstrato do Filtro.
     */
    private Boolean getAbstrato(String filtro) {
        if (filtro.toUpperCase().contains("NO-ABSTRACT")) 
            return false;
        if (filtro.toUpperCase().contains("NAO-ABSTRATO"))
            return false;
        if (filtro.toUpperCase().contains("NAO-ABSTRATA"))
            return false;
        if (filtro.toUpperCase().contains("ABSTRACT"))
            return true;
        if (filtro.toUpperCase().contains("ABSTRATO"))
            return true;
        if (filtro.toUpperCase().contains("ABSTRATA"))
            return true;
        return null;
    }
    
    /**
     * Metodo responsavel por retornar a Flag Construtor pelo Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Flag Construtor do Filtro.
     */
    private Boolean getConstrutor(String filtro) {
        if (filtro.toUpperCase().contains("NO-CONSTRUCTOR"))
            return false;
        if (filtro.toUpperCase().contains("NAO-CONSTRUTOR"))
            return false;
        if (filtro.toUpperCase().contains("CONSTRUCTOR"))
            return true;
        if (filtro.toUpperCase().contains("CONSTRUTOR"))
            return true;
        return null;
    }
    
    /**
     * Metodo responsavel por retornar o Contexto pelo Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Contexto do Filtro da Clausula.
     */
    private Integer getContexto(String filtro) {
        if (filtro.toUpperCase().contains("CLASSE"))
            return 1;
        if (filtro.toUpperCase().contains("CLASS"))
            return 1;
        if (filtro.toUpperCase().contains("INTERFACE"))
            return 2;
        return 0;
    }
    
    /**
     * Metodo responsavel por limpar o conteudo do Nome do Tipo. 
     * @param  filtro Filtro da Clausula.
     * @return Filtro sem o conteudo do Tipo.
     */
    private String clearTipos(String filtro) {
        if (!filtro.contains("<") || (!filtro.contains(">")))
            return filtro;
        if (filtro.indexOf("<") > filtro.indexOf(">"))
            return filtro;
        return filtro.substring(0, filtro.indexOf("<")) + filtro.substring(filtro.indexOf(">") + 1);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Tipos do Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Tipos da Clausula.
     */
    private List<String> getTipos(String filtro) {
        if (!filtro.contains("<") || (!filtro.contains(">")))
            return new ArrayList<>();
        if (filtro.indexOf("<") > filtro.indexOf(">"))
            return new ArrayList<>();
        List<String> toReturn = new ArrayList<>();
        String[]     tipos    = filtro.substring(filtro.indexOf("<") + 1, filtro.indexOf(">")).split(",");
        for (String tipo : tipos) {
            toReturn.add(tipo.trim());
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Estereotipos do Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Estereotipos da Clausula.
     */
    private List<String> getEstereotipos(String filtro) {
        if (!filtro.contains("<") || (!filtro.contains(">")))
            return new ArrayList<>();
        if (filtro.indexOf("<") > filtro.indexOf(">"))
            return new ArrayList<>();
        List<String> toReturn     = new ArrayList<>();
        String[]     estereotipos = filtro.substring(filtro.indexOf("<") + 1, filtro.indexOf(">")).split(",");
        for (String estereotipo : estereotipos) {
            toReturn.add(estereotipo.trim());
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por limpar o conteudo do Nome do Filtro. 
     * @param  filtro Filtro da Clausula.
     * @return Filtro sem o conteudo do Nome.
     */
    private String clearNomes(String filtro) {
        if (!filtro.contains("[") || (!filtro.contains("]")))
            return filtro;
        if (filtro.indexOf("[") > filtro.indexOf("]"))
            return filtro;
        return filtro.substring(0, filtro.indexOf("[")) + filtro.substring(filtro.indexOf("]") + 1);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Nomes do Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Nomes da Clausula.
     */
    private List<String> getNomes(String filtro) {
        if (!filtro.contains("[") || (!filtro.contains("]")))
            return new ArrayList<>();
        if (filtro.indexOf("[") > filtro.indexOf("]"))
            return new ArrayList<>();
        List<String> toReturn = new ArrayList<>();
        String[]     nomes    = filtro.substring(filtro.indexOf("[") + 1, filtro.indexOf("]")).split(",");
        for (String nome : nomes) {
            toReturn.add(nome.trim());
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por limpar o conteudo do Pacote do Filtro. 
     * @param  filtro Filtro da Clausula.
     * @return Filtro sem o conteudo do Pacote.
     */
    private String clearPacotes(String filtro) {
        if (!filtro.contains("{") || (!filtro.contains("}")))
            return filtro;
        if (filtro.indexOf("{") > filtro.indexOf("}"))
            return filtro;
        return filtro.substring(0, filtro.indexOf("{")) + filtro.substring(filtro.indexOf("}") + 1);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pacotes do Filtro da Clausula.
     * @param  filtro Filtro da Clausula.
     * @return Lista de Pacotes da Clausula.
     */
    private List<String> getPacotes(String filtro) {
        if (!filtro.contains("{") || (!filtro.contains("}")))
            return new ArrayList<>();
        if (filtro.indexOf("{") > filtro.indexOf("}"))
            return new ArrayList<>();
        List<String> toReturn = new ArrayList<>();
        String[]     pacotes  = filtro.substring(filtro.indexOf("{") + 1, filtro.indexOf("}")).split(",");
        for (String pacote : pacotes) {
            toReturn.add(pacote.trim());
        }
        return toReturn;
    }
    
    /**
     * Metodo responsavel por retornar o Valor da Expressao Matematica.
     * @param  expressao Expressao da Operacao da Metrica.
     * @return Valor Numerico da Expressao.
     * @throws ScriptException Erro na Expressao.
     */
    public Double getValor(String expressao) throws ScriptException {
        return Double.parseDouble(new ScriptEngineManager().getEngineByName("JavaScript").eval(expressao).toString());
    }
    
    /**
     * 
     * @param expressao
     * @return
     * @throws ScriptException 
     */
    public Double getValorFinal(String expressao) throws ScriptException {
        return this.getValor(this.getExpressao(expressao));
    }
}