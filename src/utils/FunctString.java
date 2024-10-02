package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Classe de Funcao <b>FuncaoString</b>.</p>
 * <p>Classe responsavel pelas Operacoes envolvendo <b>Strings</b>.</p>
 * @author Leandro
 * @since  07/02/2015
 */
public class FunctString {
    
    /**
     * Metodo responsavel por retornar uma String contendo espacos com um determinado tamanho.
     * @param  size Tamanho esperado.
     * @return String contendo espaços.
     */
    public String getEspacos(int size) {
        String espacos = "";
        for (int i = 0; i < size; ++i) {
            espacos += " ";
        }
        return espacos;
    }
    
    /**
     * Metodo responsavel por retornar uma String alinhada a direita.
     * @param  string Cadeia de caracteres inicial.
     * @param  size   Tamanho desejado.
     * @return String alinhada a direita.
     */
    public String toRight(String string, int size) {
        if (string.length() > size) return string;
        String right  = this.getEspacos(size - string.length());
               right += string;
        return right;
    }
    
    /**
     * Metodo responsavel por retornar uma String alinhada a esquerda.
     * @param  string Cadeia de caracteres inicial.
     * @param  size   Tamanho desejado.
     * @return String alinhada a esquerda.
     */
    public String toLeft(String string, int size) {
        if (string.length() > size) return string;
        String left  = string;
               left += this.getEspacos(size - string.length());
        return left;
    }
    
    /**
     * Metodo responsavel por retornar uma String centralizada.
     * @param  string Cadeia de caracteres inicial.
     * @param  size   Tamanho desejado.
     * @return String centralizada.
     */
    public String toCenter(String string, int size) {
        if (string.length() > size) return string;
        String center  = this.getEspacos((size - string.length()) / 2);
               center += string;
               center += this.getEspacos((size - string.length()) / 2);
        return center;
    }
    
    /**
     * Metodo responsavel por retornar o numero de vezes que um char aparece em uma String.
     * @param  string    String a ser percorrida.
     * @param  character Caracter a ser contado.
     * @return Quantidade de Char na String.
     */
    public int countChar(String string, char character) {
        int count = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == character) {
                count += 1;
            }
        }
        return count;
    }
    
    /**
     * Metodo responsavel por retornar uma String ordenada inversamente.
     * @param  string String a ser invertida.
     * @return String invertida.
     */
    public String reverse(String string) {
        String reverse = "";
        for (int i = string.length() - 1; i >= 0; --i) {
            reverse += string.charAt(i);
        }
        return reverse;
    }
    
    /**
     * Metodo responsavel por retornar uma String com um determinado tamanho contendo o char.
     * @param  character Caracter a ser Escrito.
     * @param  size      Tamanho da String.
     * @return String com um tamanho determinado com um caracter.
     */
    public String getString(char character, int size) {
        String string = "";
        for (int i = 0; i < size; ++i) {
            string += character;
        }
        return string;
    }
    
    /**
     * Metodo responsavel por criar a mascara de CPF em uma String.
     * @param  string String com os numeros.
     * @return String formatada em CPF.
     */
    public String getFormatoCPF(String string) {
        if (string.trim().length() != 11) return string;
        String cpf  = string.substring(0, 3);
               cpf += ".";
               cpf += string.substring(3, 6);
               cpf += ".";
               cpf += string.substring(6, 9);
               cpf += "-";
               cpf += string.substring(9);
        return cpf;
    }
    
    /**
     * Metodo responsavel por retornar uma String com o primeiro char maiusculo.
     * @param  string String a ser processada.
     * @return String com o Primeiro caracter maiusculo.
     */
    public String initUpperCase(String string) {
        if (string.length() == 0) return "";
        if (string.length() == 1) return string.toUpperCase();
        String initUpper  = string.substring(0, 1).toUpperCase();
               initUpper += string.substring(1).toLowerCase();
        return initUpper;
    }
    
    /**
    * Metodo responsavel por retornar uma String apenas com os numeros da String 
    * passada como parametro.
    * @param  string String a ser analisada.
    * @return String apenas com os numeros.
    */
    public String getNumeros(String string) {
        String numeros   = "0123456789";
        String newString = "";
        for (int i = 0; i < string.length(); ++i) {
            if (numeros.contains(Character.toString(string.charAt(i))) == true) {
                newString += string.charAt(i);
            }
        }
        return newString;
    }
    
    /**
     * Metodo responsavel por retornar as iniciais maiusculas de uma String.
     * @param  string String a ser processada.
     * @return String com as Iniciais maiusculas.
     */
    public String getInitUpperCase(String string) {
        String   initUpper = "";
        String[] words     = string.split(" ");
        for (int i = 0; i < words.length; ++i) {
            initUpper += this.initUpperCase(words[i]) + " ";
        }
        return initUpper.trim();
    }
    
    
    /**
     * Metodo responsavel por remover os caracteres especiais de uma String.
     * @param  string String a ser processada.
     * @return String sem caracteres especiais.
     */
    public String removeCharEspeciais(String string) {
        String newString = "";
        for (int i = 0; i < string.length(); ++i) {
            newString += this.replaceChar(string.charAt(i));
        }
        return newString;
    }
    
    /**
     * Metodo responsavel por trocar um char especial pelo seu respectivo nao especial.
     * @param  character Caracter a ser avaliado.
     * @return Caracter correspondente nao especial.
     */
    public char replaceChar(char character) {
        switch(character) {
            case 'á':
            case 'à':
            case 'â':
            case 'ã':
            case 'ä':
                return 'a';
            case 'ç':
                return 'c';
            case 'é':
            case 'è':
            case 'ê':
            case 'ë':
                return 'e';
            case 'í':
            case 'ì':
            case 'î':
            case 'ï':
                return 'i';
            case 'ó':
            case 'ò':
            case 'ô':
            case 'õ':
            case 'ö':
                return 'o';
            case 'ú':
            case 'ù':
            case 'û':
            case 'ü':
                return 'u';
            case 'Á':
            case 'À':
            case 'Â':
            case 'Ã':
            case 'Ä':
                return 'A';
            case 'Ç':
                return 'C';
            case 'É':
            case 'È':
            case 'Ê':
            case 'Ë':
                return 'E';
            case 'Í':
            case 'Ì':
            case 'Î':
            case 'Ï':
                return 'I';
            case 'Ó':
            case 'Ò':
            case 'Ô':
            case 'Õ':
            case 'Ö':
                return 'O';
            case 'Ú':
            case 'Ù':
            case 'Û':
            case 'Ü':
                return 'U';
            default:
                return character;
        }
    }
    
    /**
     * Metodo responsavel por checar se uma mascara e valida em uma String.
     * @param  string String a ser verificada.
     * @param  mask   Mascara definida.
     * @return String respeita a Mascara.
     */
    public boolean checkMask(String string, String mask) {
        for (int i = 0; i < string.length(); ++i) {
            if (mask.contains(Character.toString(string.charAt(i))) == false) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo responsavel por retornar um Vetor com as informacoes de uma Linha.
     * @param  string String a ser verificada.
     * @param  token  Delimitador das Informacoes.
     * @return Vetor com as Informacoes.
     */
    public String[] getDados(String string, char token) {
        int      size  = this.countChar(string, token) + 1;
        int      index = 0;
        String[] dados = new String[size];
        String   dado  = "";
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) != token) {
                dado += string.charAt(i);
            }else {
                dados[index] = dado.toUpperCase().trim();
                      index += 1;
                      dado   = "";
            }
        }
                dados[index] = dado;
        return  dados;
    }
    
    /**
     * Metodo responsavel por retornar uma String criptografada, utilizando o Algoritmo MD5.
     * @param  string String a ser processada.
     * @return String criptografada.
     */
    public String md5(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                          messageDigest.update(string.getBytes(), 0, string.length());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException exception) {
            return null;
        }
    }
}