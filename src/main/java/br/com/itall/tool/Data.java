package br.com.itall.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @apiNote Biblioteca de funções para tratamento de datas
 * @author MarcosVP
 */
public class Data {

    private Data() { }

    /**
     * @author MarcosVP
     * @apiNote toDate( String 99/99/9999 ) = Função que transforma uma String em data
     * @param sData -> "dd/mm/yyyy"
     * @return LocalDate
     */
    public static LocalDate toDate(String sData) {
    	
    	try {
    		return LocalDate.of(Integer.parseInt(sData.substring(6,10))
    				           ,Integer.parseInt(sData.substring(3, 5))
    				           ,Integer.parseInt(sData.substring(0, 2)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * @author MarcosVP
     * @apiNote toDateTime( String 99/99/9999 ) = Função que transforma uma String em data
     * @param sData -> "dd/mm/yyyy hh:MM:ss"
     * @return LocalDateTime
     */
    public static LocalDateTime toDateTime(String sData) {
    	
    	try {
    		return LocalDateTime.of(Integer.parseInt(sData.substring( 6,10))
    				               ,Integer.parseInt(sData.substring( 3, 5))
    				               ,Integer.parseInt(sData.substring( 0, 2))
    				               ,Integer.parseInt(sData.substring(11,13))
    				               ,Integer.parseInt(sData.substring(14,16))
    				               ,Integer.parseInt(sData.substring(17,19))
    				               );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * @author MarcosVP
     * @apiNote Converte: <b>java.time.LocalDateTime</b> => <b>java.time.Instant</b><br>
     * @return java.time.Instant.toEpochMilli()
     */
	@SuppressWarnings("deprecation")
	public static Long toInstant(LocalDateTime data) {
    	//Se não existir fração em segundos faz um ajuste devido a um bug encontrado.
    	Long segundos = (new java.util.Date(data.getYear()-1900
		                                   ,data.getMonthValue()-1
		                                   ,data.getDayOfMonth()
		                                   ,data.getHour()
		                                   ,data.getMinute()
		                                   ,data.getSecond())).toInstant().toEpochMilli();
    	if (segundos % 84000 == 0) {
    		Long segundosDia = (long) ( (data.getHour()*60*60) + (data.getMinute()*60) + data.getSecond() );
    		if (segundosDia < 84000) {
    			segundos += segundosDia;
    		}
    	}

    	Texto.CONSOLE_LOG("--------");    	
Texto.CONSOLE_LOG(data);    	
Texto.CONSOLE_LOG(segundos);
    	
    	return segundos;
    }

    /**
     * @author MarcosVP
     * @apiNote Converte: <b>java.time.LocalDateTime</b> => <b>java.sql.Date</b><br>
     * Utilizado no tratamento de datas do <b>PreparedStatement</b> 
     * @return java.sql.Date
     */
    public static java.sql.Date toDateSql(LocalDateTime data) {
    	return new java.sql.Date(toInstant(data));
    }
    
    public static String dateMask(String mask) { return dateMask(new Date(),mask); }
    public static String dateMask() { return dateMask(new Date(),"dd.MM.yy HH:mm:ss"); }
    public static String dateMask(Date d) { return dateMask(d,"dd.MM.yy HH:mm:ss"); }
    /**
     * Gera uma data na máscara informada
     * @param d
     * @param mask (Default "dd.MM.yy HH:mm:ss")
     * @return String
     * @author MarcosVP
     * @since 25/12/2023
     */
    public static String dateMask(Date d, String mask) {
    	return new SimpleDateFormat(mask).format(d);
    }
    
}

