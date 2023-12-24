package br.com.itall.tool;

import java.text.ParseException;
import java.time.LocalDate;

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
     * @throws ParseException
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
    
}

