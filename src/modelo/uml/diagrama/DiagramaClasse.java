package modelo.uml.diagrama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.uml.diagrama.classes.AssociacaoUML;
import modelo.uml.diagrama.classes.AtributoUML;
import modelo.uml.diagrama.classes.ClasseUML;
import modelo.uml.diagrama.classes.InterfaceUML;
import modelo.uml.diagrama.classes.MetodoUML;
import modelo.uml.diagrama.classes.PacoteUML;
import modelo.uml.diagrama.classes.ParametroUML;
import modelo.uml.diagrama.classes.java.ObjetoJava;

/**
 * <p>Classe de Modelo <b>DiagramaClasse</b>.</p>
 * <p>Classe responsavel por representar o <b>Diagrama de Classe</b> no Sistema.</p>
 * @author Leandro
 * @since  14/03/2017
 * @see    modelo.uml.diagrama.classes.AssociacaoUML
 * @see    modelo.uml.diagrama.classes.AtributoUML
 * @see    modelo.uml.diagrama.classes.ClasseUML
 * @see    modelo.uml.diagrama.classes.InterfaceUML
 * @see    modelo.uml.diagrama.classes.MetodoUML
 * @see    modelo.uml.diagrama.classes.PacoteUML
 * @see    modelo.uml.diagrama.classes.ParametroUML
 */
public class DiagramaClasse {
    private PacoteUML raiz;
    private HashMap<String, String> pacotes;
    private HashMap<String, ObjetoJava> objetosJava;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param raiz Pacote UML Raiz do Diagrama de Classe.
     */
    public DiagramaClasse(PacoteUML raiz) {
        this.raiz = raiz;
    }

    /**
     * Metodo responsavel por retornar o Pacote UML Raiz do Diagrama de Classe.
     * @return Pacote UML Raiz do Diagrama de Classe.
     */
    public PacoteUML getRaiz() {
        return this.raiz;
    }

    /**
     * Metodo responsavel por definir o Pacote UML Raiz do Digrama de Classe.
     * @param raiz Pacote UML Raiz do Digrama de Classe.
     */
    public void setRaiz(PacoteUML raiz) {
        this.raiz = raiz;
    }
    
    /**
     * Metodo responsavel por definir a Hash dos Pacotes.
     * @param pacotes Pacotes.
     */
    public void setPacotes(HashMap<String, String> pacotes) {
        this.pacotes = pacotes;
    }
    
    /**
     * Metodo responsavel por retornar o Pacote do Objeto.
     * @param  objeto Objeto do Modelo UML.
     * @return Pacote do Objeto.
     */
    public String getPacote(String objeto) {
        if (this.pacotes == null)
            return "";
        if (this.pacotes.get(objeto) == null)
            return "";
        return this.pacotes.get(objeto);
    }
    
    /**
     * Metodo responsavel por retornar um Pacote UML pelo seu Caminho.
     * @param  pacoteUML Pacote UML Raiz.
     * @param  caminho Caminho do Pacote UML.
     * @return Pacote UML encontrado.
     */
    public PacoteUML getPacoteUML(PacoteUML pacoteUML, String caminho) {
        if (pacoteUML.pacote().equals(caminho))
            return pacoteUML;
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            return this.getPacoteUML(pacoteUML.getSubPacotes().get(i), caminho);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar uma Classe UML pelo Nome.
     * @param  pacoteUML Pacote UML Raiz.
     * @param  nome Nome da Classe UML.
     * @return Classe UML encontrada.
     */
    public ClasseUML getClasseUML(PacoteUML pacoteUML, String nome) {
        if (pacoteUML.getClasseUML(nome) != null)
            return pacoteUML.getClasseUML(nome);
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            return this.getClasseUML(pacoteUML.getSubPacotes().get(i), nome);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar uma Interface UML pelo Nome.
     * @param  pacoteUML Pacote UML Raiz.
     * @param  nome Nome da Interface UML.
     * @return Interface UML encontrada.
     */
    public InterfaceUML getInterfaceUML(PacoteUML pacoteUML, String nome) {
        if (pacoteUML.getInterfaceUML(nome) != null)
            return pacoteUML.getInterfaceUML(nome);
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            return this.getInterfaceUML(pacoteUML.getSubPacotes().get(i), nome);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar um SubPacote UML pelo Nome.
     * @param  pacoteUML Pacote UML Raiz.
     * @param  nome Nome do SubPacote UML.
     * @return SubPacote UML encontrado.
     */
    public PacoteUML getSubpacoteUML(PacoteUML pacoteUML, String nome) {
        if (pacoteUML.getSubpacoteUML(nome) != null)
            return pacoteUML.getSubpacoteUML(nome);
        for (int i = 0; i < pacoteUML.getSubPacotes().size(); i++) {
            return this.getSubpacoteUML(pacoteUML.getSubPacotes().get(i), nome);
        }
        return null;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pacotes UML pela Lista de Nomes.
     * @param  nomes Lista de Nomes para busca.
     * @return Lista de Pacotes UML encontrados.
     */
    public List<PacoteUML> getSubpacotesUML(List<String> nomes) {
        List<PacoteUML> subPacotesUML = new ArrayList<>();
        for (int i = 0; i < nomes.size(); i++) {
            PacoteUML pacoteUML = this.getSubpacoteUML(this.raiz, nomes.get(i));
            if ((pacoteUML != null) && (subPacotesUML.contains(pacoteUML) == false))
                subPacotesUML.add(pacoteUML);
        }
        return subPacotesUML;
    }
    
    /**
     * Metodo responsavel por definir os Tipos do Modelo UML.
     * @param objetos Hash dos Objetos Java.
     */
    public void updateTipos(HashMap<String, ObjetoJava> objetos) {
        this.objetosJava = objetos;
        this.setTipos(this.raiz);
    }
    
    /**
     * Metodo responsavel por definir os Tipos do Pacote UML.
     * @param pacoteUML Pacote UML.
     */
    private void setTipos(PacoteUML pacoteUML) {
        
        // Classes:
        List<ClasseUML> classesUML = pacoteUML.getClasses();
        for (int i = 0; i < classesUML.size(); i++) {
            this.setSuperClasse(classesUML.get(i));
            this.setRealizacoesUML(classesUML.get(i));
            this.setAtributosUML(classesUML.get(i));
            this.setAssociacoesUML(classesUML.get(i));
            this.setMetodosUML(classesUML.get(i));
        }
        
        // Interfaces:
        List<InterfaceUML> interfacesUML = pacoteUML.getInterfaces();
        for (int i = 0; i < interfacesUML.size(); i++) {
            this.setSuperInterface(interfacesUML.get(i));
            this.setAtributosUML(interfacesUML.get(i));
            this.setAssociacoesUML(interfacesUML.get(i));
            this.setMetodosUML(interfacesUML.get(i));
        }
        
        // Pacotes:
        List<PacoteUML> pacotesUML = pacoteUML.getSubPacotes();
        for (int i = 0; i < pacotesUML.size(); i++) {
            this.setTipos(pacotesUML.get(i));
        }
    }
    
    /**
     * Metodo responsavel por definir o Tipo da Super Classe da Classe UML.
     * @param classeUML Classe UML.
     */
    private void setSuperClasse(ClasseUML classeUML) {
        ObjetoJava objeto = this.objetosJava.get(classeUML.getIdSuperClasse());
        if (objeto != null) {
            classeUML.setIdSuperClasse(objeto.getPacote() + "." + objeto.getNome());
            classeUML.setSuperClasse(this.getClasseUML(this.raiz, objeto.getNome()));
        }
    }
    
    /**
     * Metodo responsavel por definir o Tipo da Super Interface da Interface UML.
     * @param interfaceUML Interface UML.
     */
    private void setSuperInterface(InterfaceUML interfaceUML) {
        ObjetoJava objeto = this.objetosJava.get(interfaceUML.getIdSuper());
        if (objeto != null) {
            interfaceUML.setIdSuper(objeto.getNome());
            interfaceUML.setSuperInterface(this.getInterfaceUML(this.raiz, objeto.getNome()));
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos das Realizacoes UML da Interface UML.
     * @param classeUML Classe UML.
     */
    private void setRealizacoesUML(ClasseUML classeUML) {
        for (int i = 0; i < classeUML.getInterfaces().size(); i++) {
            String     id     = classeUML.getInterfaces().get(i);
            ObjetoJava objeto = this.objetosJava.get(id);
            if (objeto != null) {
                classeUML.getInterfaces().set(i, objeto.getNome());
                classeUML.addRealizacao(this.getInterfaceUML(this.raiz, objeto.getNome()));
            }
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos dos Atributos UML da Classe UML.
     * @param classeUML Classe UML.
     */
    private void setAtributosUML(ClasseUML classeUML) {
        for (int i = 0; i < classeUML.getAtributos().size(); i++) {
            AtributoUML atributoUML = classeUML.getAtributos().get(i);
            ObjetoJava  objetoJava  = this.objetosJava.get(atributoUML.getTipo());
            if (objetoJava != null)
                atributoUML.setTipo(objetoJava.toString());
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos dos Atributos UML da Interface UML.
     * @param interfaceUML Interface UML.
     */
    private void setAtributosUML(InterfaceUML interfaceUML) {
        for (int i = 0; i < interfaceUML.getAtributos().size(); i++) {
            AtributoUML atributoUML = interfaceUML.getAtributos().get(i);
            ObjetoJava  objetoJava  = this.objetosJava.get(atributoUML.getTipo());
            if (objetoJava != null)
                atributoUML.setTipo(objetoJava.getNome());
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos das Associacoes UML da Classe UML.
     * @param classeUML Classe UML.
     */
    private void setAssociacoesUML(ClasseUML classeUML) {
        for (int i = 0; i < classeUML.getAssociacoes().size(); i++) {
            AssociacaoUML associacaoUML = classeUML.getAssociacoes().get(i);
            ObjetoJava    objetoJava    = this.objetosJava.get(associacaoUML.getTipo());
            if (objetoJava != null)
                associacaoUML.setTipo(objetoJava.getNome());
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos das Associacoes UML da Interface UML.
     * @param interfaceUML Interface UML.
     */
    private void setAssociacoesUML(InterfaceUML interfaceUML) {
        for (int i = 0; i < interfaceUML.getAssociacoes().size(); i++) {
            AssociacaoUML associacaoUML = interfaceUML.getAssociacoes().get(i);
            ObjetoJava    objetoJava    = this.objetosJava.get(associacaoUML.getTipo());
            if (objetoJava != null)
                associacaoUML.setTipo(objetoJava.getNome());
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos dos Metodos UML da Classe UML.
     * @param classeUML Classe UML.
     */
    private void setMetodosUML(ClasseUML classeUML) {
        for (int i = 0; i < classeUML.getMetodos().size(); i++) {
            this.setRetornoUML(classeUML.getMetodos().get(i));
            this.setParametrosUML(classeUML.getMetodos().get(i));
        }
    }
    
    /**
     * Metodo responsavel por definir os Tipos dos Metodos UML da Interface UML.
     * @param interfaceUML Interface UML.
     */
    private void setMetodosUML(InterfaceUML interfaceUML) {
        for (int i = 0; i < interfaceUML.getMetodos().size(); i++) {
            this.setRetornoUML(interfaceUML.getMetodos().get(i));
            this.setParametrosUML(interfaceUML.getMetodos().get(i));
        }
    }
    
    /**
     * Metodo responsavel por definir o Tipo de Retorno do Metodo UML.
     * @param metodoUML Metodo UML.
     */
    private void setRetornoUML(MetodoUML metodoUML) {
        ObjetoJava objeto = this.objetosJava.get(metodoUML.getRetorno());
        if (objeto != null)
            metodoUML.setRetorno(objeto.getNome());
    }
    
    /**
     * Metodo responsavel por definir os Tipos dos Parametros UML do Metodo UML.
     * @param metodoUML Metodo UML.
     */
    private void setParametrosUML(MetodoUML metodoUML) {
        for (int i = 0; i < metodoUML.getParametros().size(); i++) {
            ParametroUML parametroUML = metodoUML.getParametros().get(i);
            ObjetoJava   objetoJava   = this.objetosJava.get(parametroUML.getTipo());
            if (objetoJava != null)
                parametroUML.setTipo(objetoJava.getNome());
        }
    }
}