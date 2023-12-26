package br.com.itall.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Biblioteca de funções para tratamento de e-mails <br>
 * Utilize: Email email = Email.get("email@teste.com.br") para instanciar um objeto já tratado. <br>
 * <b>email.isValid()</b> --&gt; boolean. se está válido ou não.<br>
 * <b>email.getDescription()</b> --> String. O próprio e-mail.<br>
 * <b>email.failures()</b> --&gt; List&lt;String&gt;. Lista de mensagens de tratamento.
 * @author MarcosVP
 * @since 24/12/2023
 * @version 1.01.0
 * 
 */
public class Email {
	
	private boolean isValid = true;
	private String description;
	private String user = "";
	private String domain = "";
	private String invalidChar = "";
	private List<String> failures = new ArrayList<String>();
	
	/**
	 * Construtor <b><i>Private</i></b><br>
	 * Armazena a conta do e-mail, separa seus componentes e verifica se já erros.<br>
	 * Veja os métodos deste classe para obter estes dados. 
	 * @param email (String) Conta do e-mail.
	 * @see #get(String)
	 */
	private Email(String email) {
		
		try {

			setDescription(email);

			if (email == null || email.isEmpty()) {
				
				failures.add(String.format("Não informado: %s",email == null?"<nulo>":"<vazio>"));
				
			} else {
				
				setInvalidChar(email);
				
				if (getInvalidChar().length() > 0) failures.add("Contém caracteres inválidos");
				
				int posArroba = email.indexOf("@");
				if (posArroba == -1) {
					failures.add("Não contém \"@\"");
				} else {
					if (posArroba == 0) {
						failures.add(String.format("Não contém o \"usuário\" do email: <?>%s", email));
					} else {
						setUser(email.substring(0,posArroba));
						if (posArroba == email.length()-1) {
							failures.add(String.format("Não contém o \"domínio\" do email: %s<?>", email));
						} else {
							setDomain(email.substring(posArroba+1));
						}
					}	
				}
			}
			
		} catch (Exception e) {
			failures.add(e.getMessage());
		}
		
		if (failures.size() > 0) setValid(false);
		
	}

	/**
	 * Executa o construtor e instancia uma classe nova.
	 * 
	 * @param email (String) Conta do e-mail.
	 * @return Email
	 */
	public static Email get(String email) {
		return new Email(email);
	}

    /** Retorna se o e-mail é válido 
     * @return boolean */
    public boolean isValid()						{ return isValid;				  }
    /** @param isValid (boolean) */
	public void setValid(boolean isValid)			{ this.isValid = isValid;		  }
	/** @return String Conta do e-mail */
	public String getDescription()					{ return description;			  }
	/** @param description (String) Conta do e-mail */
	public void setDescription(String description)	{ this.description = description; }
	/** @return (List&lt;String&gt;) Lista de erros contidos no e-mail */
	public List<String> getFailures()				{ return failures;				  }
	/** @param failures (List&lt;String&gt;) Lista de erros contidos no e-mail */
	public void setFailures(List<String> failures)	{ this.failures = failures;		  }
	/** @return (String) Usuário. Parte do e-mail que vem antes do @.*/
	public String getUser()							{ return user;					  }
	/** @param user (String) Usuário. Parte do e-mail que vem antes do @. */
	public void setUser(String user) 				{ this.user = user;				  }
	/** @return (String) Domínio. Parte do e-mail que vem depois do @. */
	public String getDomain()						{ return domain;				  }
	/** @param domain (String) Domínio. Parte do e-mail que vem depois do @. */
	public void setDomain(String domain)			{ this.domain = domain;			  }
	/** @return String Texto do tamanho da conta que demonstra as posições em que há erros. */
	public String getInvalidChar()					{ return invalidChar;			  }
	/** @param email String Texto do tamanho da conta que demonstra as posições em que há erros. */
	
	/**
	 * Trata a conta do e-mail e gera o texto de posições inválidas automaticamente <br>
	 * No exemplo abaixo a linha 1 é o e-mail e a linha 2 o texto gerado:<br>
	 * <p style="font-family: 'Lucida Console', Monaco, monospace;">
	 * 1) "márcos:vinicio,per@gmail com!"<br>
	 * 2) "&nbsp;^&nbsp;&nbsp;&nbsp;&nbsp;^&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;^&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;^&nbsp;&nbsp;&nbsp;^"<br></p>
 	 * 
	 * @param email String - Conta do e-mail. 
	 */
	public void setInvalidChar(String email)  { 
		StringBuilder sb = new StringBuilder();
		// abcdefgihjklmnopqrstuvwxyz.-@_0123456789
		for (String letra : email.split("")) {
			sb.append("abcdefgihjklmnopqrstuvwxyz.-@_0123456789".indexOf(letra) > -1?" ":"^"); 
		}
		this.invalidChar = sb.toString(); 
		if (this.invalidChar.trim().length() == 0) {
			this.invalidChar = "";
		}
	}
	
	/**
	 * Ajuste no método padrão para retornar a conta do e-mail quando for passada somente a classe como parâmetro.
	 * @return String Conta do e-mail. 
	 */
	@Override
	public String toString() {
		return getDescription();
	}

	/**
	 * Texto alternativo ao toString para exibir os dados gerados em logs.
	 * @return String
	 */
	public String toStringLog() {
		StringBuilder sb = new StringBuilder(String.format("Email    : \"%s\"\n",this.getDescription()));
		if (getInvalidChar().length() > 0 ) sb.append(String.format("Inválidos:  %s\n",this.getInvalidChar()));
		sb.append(String.format("Usuário  : %s\n",this.getUser()));
		sb.append(String.format("Domínio  : %s\n",this.getDomain()));
		sb.append(String.format("Válido   : %s\n",this.isValid()?"sim":"não"));
		sb.append(String.format("Mensagens: %d\n", this.failures.size() ));
		for (String s : this.failures) {
			sb.append(String.format(" - %s\n", s));
		}
		return sb.toString();
	}
	
	/**
	 * Método que gera uma String específica para mensagens em tela<br>
	 * Diferente do toString() este método traz apenas as informações de mensagens de<br>
	 * erros detectados durante a criação do objeto. 
	 * @return String
	 */
	public String toMessages() {
		StringBuilder sb = new StringBuilder();
		if (!this.isValid) {
			sb.append("E-mail inválido !!!\n");
			sb.append(String.format(" E-mail   : \"%s\"\n",this.getDescription()));
			if (getInvalidChar().length() > 0 ) sb.append(String.format(" Inválidos:  %s\n",this.getInvalidChar()));
			for (String s : this.failures) {
				sb.append(String.format(" - %s\n", s));
			}
		}
		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(Email.get("marcos@teste.com.br").toStringLog());
//		System.out.println(Email.get("m@arcosteste.com.br").toStringLog());
//		System.out.println(Email.get("marcosteste.com.b@r").toStringLog());
//		System.out.println(Email.get("@marcosteste.com.br").toStringLog());
//		System.out.println(Email.get("márcosteste.com.br@").toStringLog());
//		System.out.println(Email.get("marco @testê.com;br").toStringLog());
//		System.out.println(Email.get("marcos@teste.com.br"));
//	}
	
}

