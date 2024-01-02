package br.com.itall.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Biblioteca de funções para tratamento de datas
 * 
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 * 
 */
public class Data {

    private Data() { }

    /**
     * Função que transforma uma String "99/99/9999" em data
     * 
     * @author MarcosVP
     * @param sData String "dd/mm/yyyy"
     * @return LocalDate
     */
    public static LocalDate sToDate(String sData) {
    	
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
	 * Converte: <b>String</b> "99/99/9999" => <b>LocalDateTime</b>
     * @author MarcosVP
     * @param sData "dd/mm/yyyy hh:MM:ss"
     * @return LocalDateTime
     * 
     */
    public static LocalDateTime convertStringToLocalDateTime(String sData) {
    	
    	try {
    		return LocalDateTime.of(Integer.parseInt(sData.substring( 6,10))
    				               ,Integer.parseInt(sData.substring( 3, 5))
    				               ,Integer.parseInt(sData.substring( 0, 2))
    				               ,Integer.parseInt(sData.substring(11,13))
    				               ,Integer.parseInt(sData.substring(14,16))
    				               ,Integer.parseInt(sData.substring(17,19))
    				               );
		} catch (Exception e) {
			try { e.printStackTrace(); } catch (Exception e2) {}
		}
		return null;
    }
    
    /**
	 * Converte: <b>String</b> "99/99/9999" => <b>LocalDate</b>
     * @param sData "dd/mm/yyyy hh:MM:ss"
     * @return LocalDateTime
     * @author MarcosVP
     * @since 02/01/2024
     */
    public static LocalDate convertStringToLocalDate(String sData) {
    	
    	try {
    		return LocalDate.of(Integer.parseInt(sData.substring( 6,10))
    				           ,Integer.parseInt(sData.substring( 3, 5))
    				           ,Integer.parseInt(sData.substring( 0, 2))
    				           );
		} catch (Exception e) {
			try { e.printStackTrace(); } catch (Exception e2) {}
		}
		return null;
    }

    /**
	 * Converte: <b>java.time.LocalDateTime</b> => <b>java.time.Instant</b><br>
     * @author MarcosVP
     * @param data (LocalDateTime)
     * @return Long (java.time.Instant.toEpochMilli())
     */
	@SuppressWarnings("deprecation")
	public static Long convertLocalDateTimeToInstant(LocalDateTime data) {
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

    	return segundos;
    }

	/**
     * Converte: <b>java.time.LocalDateTime</b> => <b>java.sql.Date</b><br>
     * Utilizado no tratamento de datas do <b>PreparedStatement</b>
     * @author MarcosVP
	 * @param data (LocalDateTime)
     * @return java.sql.Date
	 */
    public static java.sql.Date convertLocalDateTimeToDateSql(LocalDateTime data) {
    	return new java.sql.Date(convertLocalDateTimeToInstant(data));
    }
    
    /** METHOD OVERLOADING <br>
     * Executa o método principal usando a data atual e <br>
     * e considerando uma máscara diferente da padrão.
     *  
     * @param mask (String) Default "dd.MM.yy HH:mm:ss"
     * @return String (Conforme máscara utilizada)
     * @see #dateMask(Date, String) 
     */
    public static String dateMask(String mask) { return dateMask(new Date(),mask); }
    
    /** METHOD OVERLOADING <br>
     * Executa o método principal usando a data atual e <br>
     * passando uma máscara fixa "dd.MM.yy HH:mm:ss"
     *  
     * @return String (Conforme máscara utilizada)
     * @see #dateMask(Date)
     * @see #dateMask(Date, String) 
     */
    public static String dateMask() { return dateMask(new Date(),"dd.MM.yy HH:mm:ss"); }
    
    /** METHOD OVERLOADING <br>
     * Executa o método principal passando uma máscara fixa "dd.MM.yy HH:mm:ss" 
     * @param data java.time.LocalDate 
     * @return String (Conforme máscara utilizada)
     * @see #dateMask(Date, String) 
     */
    public static String dateMask(Date data) { return dateMask(data,"dd.MM.yy HH:mm:ss"); }
    
    /**
     * Gera uma data na máscara informada
     * 
     * @param data java.time.LocalDate 
     * @param mask (String) Default "dd.MM.yy HH:mm:ss"
     * @return String (Conforme máscara utilizada)
     * @author MarcosVP
     * @since 25/12/2023
     */
    public static String dateMask(Date data, String mask) {
    	return new SimpleDateFormat(mask).format(data);
    }
    
    /**
     * Converte <b>java.util.Date</b> => <b>LocalDateTime</b>  
     * @param data (java.util.Date)
     * @return LocalDateTime
     */
    @SuppressWarnings("deprecation")
	public static LocalDateTime convertDateToLocalDateTime( Date data ) {
    	
    	int horas, minutos, segundos;
    	try { horas = data.getHours();} finally {}
    	try { minutos = data.getMinutes();} finally {}
    	try { segundos = data.getSeconds();} finally {}
    	
    	return LocalDateTime.of(data.getYear()+1900
    			               ,data.getMonth()+1
    			               ,data.getDate()
    			               ,horas
    			               ,minutos
    			               ,segundos);
    }
    
    /**
     * Converte <b>java.util.Date</b> => <b>LocalDate</b>  
     * @param data (java.util.Date)
     * @return LocalDate
     */
    @SuppressWarnings("deprecation")
	public static LocalDate convertDateToLocalDate( Date data ) {
    	return LocalDate.of(data.getYear()+1900
    			           ,data.getMonth()+1
    			           ,data.getDate());
    }

    /**
     * Converte <b>LocalDateTime</b> => <b>java.util.Date</b> <br>
     * Faz a conversão do fuso horário antes.
     *  
     * @param localDT (LocalDateTime)
     * @return java.util.Date
     */
    public static Date convertLocalDateTimeToDate(LocalDateTime localDT) {
			return Date.from(localDT.atZone(ZoneId.systemDefault()).toInstant());
    }
    
}

