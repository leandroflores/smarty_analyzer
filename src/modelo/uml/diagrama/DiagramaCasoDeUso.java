package modelo.uml.diagrama;

import modelo.uml.diagrama.usecases.PastaUML;

/**
 * <p>Classe de Modelo <b>DiagramaCasoDeUso</b>.</p>
 * <p>Classe responsavel por representar o <b>Diagrama de Caso de Uso</b> no Sistema.</p>
 * @author Leandro
 * @since  14/03/2017
 * @see    modelo.uml.diagrama.usecases.PastaUML
 */
public class DiagramaCasoDeUso {
    private PastaUML raiz;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param raiz Pasta UML Raiz do Diagrama de Caso de Uso.
     */
    public DiagramaCasoDeUso(PastaUML raiz) {
        this.raiz = raiz;
    }

    /**
     * Metodo responsavel por retornar a Pasta UML Raiz do Diagrama de Caso de Uso.
     * @return Pasta UML Raiz do Diagrama de Caso de Uso.
     */
    public PastaUML getRaiz() {
        return this.raiz;
    }

    /**
     * Metodo responsavel por definir a Pasta UML Raiz do Diagrama de Caso de Uso.
     * @param raiz Pasta UML Raiz do Diagrama de Caso de Uso.
     */
    public void setRaiz(PastaUML raiz) {
        this.raiz = raiz;
    }
}