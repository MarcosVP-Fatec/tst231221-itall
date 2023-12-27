package br.com.itall.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Classe para geração de Critografia de dados
 * 
 * @author MarcosVP
 * @since 27/12/2023
 * @version 1.01.0
 */
public class Criptografia {

	/**
	 * Função que gera uma Hash no padrão MD5 (32 bytes)
	 * 
	 * @param senha (String)
	 * @return String (32 bytes)
	 * @throws NoSuchAlgorithmException Tratamento de erros no cálculo da Hash
	 */
	public static String pwToMD5(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes());
	    byte[] by = md.digest();	    
		return new String(Hex.encodeHex(by));
	}
	
}
