package br.com.itall.tool;

/**
 * Biblioteca de funções para tratamento de textos
 * 
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 */
public class Texto {
	
	/**
	 * Método específico para DEGUBS que executa um System.out.println(texto)<br>
	 * Utilizado para gerar logs e depois ser rastreável para limpeza.
	 * Este método não é para ficar permanente na aplicação.
	 * @param texto (String) Mensagem que será impressa no console do servidor.
	 */
	public static void CONSOLE_LOG(Object texto) { System.out.println(texto); } //Esta declaração é válida
	
	/**
	 * Gera um log no console do servidor com data e hora local<br>
	 * Utilizado para colocar logs intencionais de rastreio.
	 * Este comando inibe quaisquer exceptions para poder imprimir o StackTrace sem interromper neste momento.
	 * @param texto (String) Mensagem que será impressa no console do servidor.
	 */
	public static void logConsole(Object texto) { 
		try {
			System.out.println(Data.dateMask("[yy-MM-dd hh:mm:ss,SSS] ") + texto);
		} catch (Exception e) {}
	}
	
	/**
	 * Gera um log no console referente a SQL
	 * @param sql (String) Sql que será armazenado
	 */
	public static void logSQL(Object sql) { 
		try {
			System.out.println(Data.dateMask("[yy-MM-dd hh:mm:ss,SSS] ") + sql);
		} catch (Exception e) {}
	}
	
}
