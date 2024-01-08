package br.com.itall.tool.exception;

/**
 * Exceção de validações dos CRUDs do sistema.
 * 
 * @author MarcosVP
 * @since 08/01/2024
 * @version 1.01.0 
 */
public class CrudException extends RuntimeException {
	private static final long serialVersionUID = -7169519580229527295L;

	/**
	 * @param msg (String) Mensagem que será exibida por esta exceção.
	 */
	public CrudException(String msg) {
		super(msg);
	}
	
}

