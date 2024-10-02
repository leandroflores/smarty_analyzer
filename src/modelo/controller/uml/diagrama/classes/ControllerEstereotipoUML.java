package modelo.controller.uml.diagrama.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class ControllerEstereotipoUML {
    
    public List<String> getEstereotipos(String comentario) {
        List<String> estereotipos = new ArrayList<>();
        return estereotipos;
    }
    
    private String replace(String comentario) {
        if (!comentario.equals("")) {
            comentario = comentario.replaceAll("&lt;", "<");
            while (comentario.contains("<<"))
                comentario = comentario.replaceAll("<<", "<");
            
            comentario = comentario.replaceAll("&gt;", ">");
            while (comentario.contains(">>"))
                comentario = comentario.replaceAll(">>", ">");
        }
        comentario = comentario.replaceAll(",", "><");
        return comentario;
    }
    
    
    
    
}