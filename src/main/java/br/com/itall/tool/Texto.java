package br.com.itall.tool;

/**
 * @apiNote Biblioteca de funções para tratamento de textos
 * @author MarcosVP
 * @version 1.01.0
 */
public class Texto {
	
	/**
	 * @apiNote Executa um System.out.print(texto) <br>
	 * Utilizado para gerar logs e depois ser rastreável para limpeza
	 * @param texto
	 */
	public static void PRINT(Object texto) { PRINT(texto,false); }
	public static void PRINT(Object texto, boolean lNoBreakLine) { 
		if (lNoBreakLine) System.out.print(texto);
		else System.out.println(texto); 
	}

	/**
	 * @apiNote Executa um System.out.println(texto) <br>
	 * Utilizado para gerar logs e depois ser rastreável para limpeza
	 * @param texto
	 */
	public static void CONSOLE_LOG(Object texto) { System.out.println(texto); }
	
	/**
	 * Gera um log no console do servidor (Informa antes o horário local) <br>
	 * Utilizado para colocar logs intencionais de rastreio.
	 * @author MarcosVP
	 * @since 25/12/2023
	 * @param texto
	 */
	public static void logConsole(Object texto) { System.out.println(Data.dateMask("[yy-MM-dd hh:mm:ss,SSS] ") + texto); }
	
	
}
