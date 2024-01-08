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
	 * Gera um log no console do servidor com data e hora local precedido por [ERRO]<br>
	 * @see #logConsole(Object)
	 * Utilizado para colocar logs intencionais de rastreio.
	 * @param texto (String) Mensagem que será impressa no console do servidor.
	 */
	public static void logConsoleErro(Object texto) { logConsole("[ERRO] " + texto.toString()); }

	/**
	 * Gera um log no console referente a SQL que foi executado.
	 * @param sql (String) Sql que será armazenado
	 */
	public static void logSQL(Object sql) { 
		try {
			System.out.println(Data.dateMask("[yy-MM-dd hh:mm:ss,SSS] ") + "[QUERY] " + sql);
		} catch (Exception e) {}
	}
	
	/**
	 * Tira espaços duplos de dentro de um texto
	 * @param texto (String)
	 * @return String
	 */
	public static String tiraEspacosDuplos(String texto) {
		while (texto.contains("  ")) texto = texto.replace("  "," ");
		return texto;
	}
	
	/**
	 * Função que coloca a primeira letra de uma palavra em maiúsculo.<br>
	 * 
	 * @param palavra (String)
	 * @return String Palavra
	 */
	public static String capFirst(String palavra) {
		if (palavra == null || palavra.isEmpty()) return palavra;
        return palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
	}
	
	
	/**
	 * Trata um <b>texto</b> preenchendo com espaços à esquerda<br>
	 * @see #padL(String, int, Character)
	 * @param texto (String) Texto que será tratado.
	 * @param tamanho (int) Tamanho desejado para o texto resultante
	 * @return (String) "   texto".
	 */
	public static String padL( String texto , int tamanho) { return padL( texto, tamanho, ' '); }

	/**
	 * Trata um <b>texto</b> preenchendo com determinado caractere à esquerda<br>
	 * - Se o tamanho solicitado for menor que o <b>texto</b> de parâmetro<br>
	 *   não faz tratamento retornando o próprio <b>texto</b>.
	 * - Não retira espaços do texto, portanto tem que tratar isso pela sua chamada.
	 * - Se o caractere de preenchimento não for informado será considerado um espaço.
	 * 
	 * @param texto (String) Texto que será tratado.
	 * @param tamanho (int) Tanaho desejado para o texto resultante
	 * @param letra (Character) &#91;Optional&#93; Letra que será inserida. Default ' '.
	 * @return (String) "   texto".
	 */
	public static String padL( String texto , int tamanho, Character letra ) {
		if (texto==null || texto.length() >= tamanho) return texto;
		return String.format("%" + (tamanho-texto.length()) + "s", "").replace(' ', letra==null?' ':letra) + texto;
	}
	
	/**
	 * Trata um <b>texto</b> preenchendo com espaços à direita<br>
	 * @see #padR(String, int, Character)
	 * @param texto (String) Texto que será tratado.
	 * @param tamanho (int) Tamanho desejado para o texto resultante
	 * @return (String) "texto   ".
	 */
	public static String padR( String texto , int tamanho) { return padL( texto, tamanho, ' '); }

	/**
	 * Trata um <b>texto</b> preenchendo com determinado caractere à direita<br>
	 * - Se o tamanho solicitado for menor que o <b>texto</b> de parâmetro<br>
	 *   não faz tratamento retornando o próprio <b>texto</b>.
	 * - Não retira espaços do texto, portanto tem que tratar isso pela sua chamada.
	 * - Se o caractere de preenchimento não for informado será considerado um espaço.
	 * 
	 * @param texto (String) Texto que será tratado.
	 * @param tamanho (int) Tanaho desejado para o texto resultante
	 * @param letra (Character) &#91;Optional&#93; Letra que será inserida. Default ' '.
	 * @return (String) "   texto".
	 */
	public static String padR( String texto , int tamanho, Character letra ) {
		if (texto==null || texto.length() >= tamanho) return texto;
		return texto + String.format("%" + (tamanho-texto.length()) + "s", "").replace(' ', letra==null?' ':letra);
	}
	
}
