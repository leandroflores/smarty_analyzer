package utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <p>Classe de Funcao <b>FunctFrame</b>.</p>
 * <p>Classe responsavel pelas Operacoes envolvendo <b>Frames</b> e <b>Imagens</b>.</p>
 * @author Leandro
 * @since  13/10/2015
 * @see    javax.swing.ImageIcon
 */
public class FunctFrame {
    private static final String DIRETORIO = "/visao/imagens/";
    
    /**
     * <p>Metodo responsavel por retornar uma ImageIcon pela sua url.</p>
     * <p>Utiliza como padrao o diretorio <b>src/images/</b>.</p>
     * @param  urlImage URL da Imagem a ser instanciada. 
     * @return ImageIcon criada.
     */
    public ImageIcon createImage(String urlImage) {
         return new ImageIcon(getClass().getResource(DIRETORIO + urlImage));
    }
    
    /**
     * Metodo responsavel por retornar um JLabel com uma Imagem.
     * @param  urlImage URL da Imagem.
     * @return JLabel com uma Imagem.
     */
    public JLabel getLabelImage(String urlImage) {
        return new JLabel((Icon) this.createImage(urlImage));
    }
}