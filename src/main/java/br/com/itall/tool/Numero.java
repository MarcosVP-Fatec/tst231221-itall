package br.com.itall.tool;

/**
 * Biblioteca de funções para tratamento de números
 * 
 * @author MarcosVP
 * @since 05/01/2024
 * @version 1.01.0
 */
public class Numero {
	
	/**
	 * Converte: <b>int</b> => <b>String</b> preenchido com espaços à esquerda.<br>
	 * 
	 * Se o tamanho do texto gerado pelo número for maior que o tamanho desejado<br>
	 * este não será truncado.   
	 * 
	 * @param valor (int) Valor que será tratado
	 * @param tamanho (int) Tamanho desejado para o texto resultante
	 * @return (String) "   123".
	 */
	public static String padL( int valor, int tamanho ) {
		return Texto.padL(Integer.toString(valor), tamanho, ' ');
	}

	/**
	 * Converte: <b>int</b> => <b>String</b> preenchido com zeros à esquerda.<br>
	 * 
	 * Se o tamanho do texto gerado pelo número for maior que o tamanho desejado<br>
	 * este não será truncado.   
	 * 
	 * @param valor (int) Valor que será tratado
	 * @param tamanho (int) Tamanho desejado para o texto resultante
	 * @return (String) "000123".
	 */
	public static String zeroEsq( int valor, int tamanho ) {
		return Texto.padL(Integer.toString(valor), tamanho, '0');
	}
	
}
