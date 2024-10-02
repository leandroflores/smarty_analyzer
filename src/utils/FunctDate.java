package utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Classe de Funcao <b>FunctDate</b>.</p>
 * <p>Classe responsavel pelas Operacoes envolvendo <b>Datas</b> e <b>Times</b>.</p>
 * @author Leandro
 * @since  17/03/2015
 * @see    java.util.Date
 * @see    java.sql.Time
 */
public class FunctDate {
    private static final String[] MONTH_NAMES = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", 
                                                 "MAIO", "JUNHO", "JULHO", "AGOSTO", 
                                                 "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};
    private static final String[] DAY_NAMES   = {"DOMINGO", "SEGUNDA-FEIRA", "TERÇA-FEIRA", "QUARTA-FEIRA",
                                                 "QUINTA-FEIRA", "SEXTA-FEIRA", "SÁBADO"};
    
    /**
     * Metodo responsavel por retornar a Data Atual do Sistema.
     * @return Data Atual.
     */
    public Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * Metodo responsavel por retornar a Hora Atual do Sistema.
     * @return Hora Atual.
     */
    public Time getCurrentTime() {
        return new Time(this.getCurrentDate().getTime());
    }
    
    /**
     * Metodo responsavel por criar um objeto Date no formato "dd/MM/yyyy".
     * @param  date Data em formato "dd/MM/yyyy".
     * @return Data criada.
     */
    public Date createDate(String date) {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
        } catch (ParseException exception) {
            return null;
        }
    }
    
    /**
     * Metodo responsavel por criar um objeto Date no formato "dd/MM/yy"
     * @param  date Data em formato "dd/MM/yy".
     * @return Data criada.
     */
    public Date createDate2(String date) {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yy").parse(date).getTime());
        } catch (ParseException exception) {
            return null;
        }
    }
    
    /**
     * Metodo responsavel por criar um objeto Time com os parametros Horas, Minutos e Segundos.
     * @param  hours   Hora (0 - 23)
     * @param  minutes Minuto (0 - 59)
     * @param  seconds Segundo (0 - 59)
     * @return Time criado.
     */
    public Time createTime(int hours, int minutes, int seconds) {
        return new Time(hours, minutes, seconds);
    }
    
    /**
     * Metodo responsavel por retornar uma String com a data no formato "dd/MM/yyyy".
     * @param  date Data a ser formatada.
     * @return String com a Data no formato "dd/MM/yyyy".
     */
    public String getFormattedDate(Date date) {
        return (date != null) ? new SimpleDateFormat("dd/MM/yyyy").format(date) : "";
    }
    
    /**
     * Metodo responsavel por retornar uma String com a data no formato "MMyyyy".
     * @param  date Data a ser formatada.
     * @return String com o Semestre da Data no formato "MMyyyy".
     */
    public String getSemestreDate(Date date) {
        return (date != null) ? new SimpleDateFormat("MMyyyy").format(date) : "";
    }
    
    /**
     * Metodo responsavel por retornar uma String com o ano da Data no formato "yyyy".
     * @param  date Data a ser formatada.
     * @return String com o Ano da Data.
     */
    public String getYear(Date date) {
        return (date != null) ? new SimpleDateFormat("yyyy").format(date) : "";
    }
    
    /**
     * Metodo responsavel por retornar uma String com a data no formato "dd/MM/yy".
     * @param  date  Data a ser formatada.
     * @return String com a Data formatada em "dd/MM/yy".
     */
    public String getFormattedDate2(Date date) {
        return new SimpleDateFormat("dd/MM/yy").format(date);
    }
    
    /**
     * Metodo responsavel por retornar a data por extenso.
     * @param  date Data a ser escrita por extenso.
     * @return String com a Data em Extenso.
     */
    public String getDateName(Date date) {
        if (date == null) return "";
        String sDate  = this.getDayName(date.getDay()) + ", ";
               sDate += date.getDate() + " DE ";
               sDate += this.getMonthName(date.getMonth()) + " DE ";
               sDate += date.getYear();
        return sDate;
    }
    
    /**
     * Metodo responsavel por retornar o nome do dia por extenso.
     * @param  day Dia da Semana (0 - 6)
     * @return String com o Nome do Dia da Semana.
     */
    public String getDayName(int day) {
        if ((day >= 0) && (day <= 6)) {
            return DAY_NAMES[day];
        }
        return "";
    }
    
    /**
     * Metodo responsavel por retornar o nome por extenso do mes.
     * @param  month Mes do Ano (0 - 11)
     * @return String com o Nome do Mes.
     */
    public String getMonthName(int month) {
        if ((month >= 0) && (month <= 11)) {
           return MONTH_NAMES[month]; 
        }
        return "";
    }
    
    /**
     * Metodo responsavel por retornar uma String com a Data e Hora Atual.
     * @return Data e Hora Atual.
     */
    public String getInfo() {
        return this.getFormattedDate(new Date()).replace("/", "-") 
             + "-"
             + this.getCurrentTime().toString().replace(":", "-");
    }
    
    /**
     * Metodo responsavel por retornar uma String com a Data e Hora Atual.
     * @return Data e Hora Atual.
     */
    public String getInfo2() {
        return this.getFormattedDate(new Date()).replace("/", "-") 
             + "-"
             + this.getCurrentTime().toString();
    }
}