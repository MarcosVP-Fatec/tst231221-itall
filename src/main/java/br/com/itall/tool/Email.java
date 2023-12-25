package br.com.itall.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Biblioteca de funções para tratamento de e-mails <br>
 * Utilize: Email email = Email.get("email@teste.com.br") para instanciar um objeto já tratado. <br>
 * <b>email.isValid()</b> --> boolean. se está válido ou não.<br>
 * <b>email.getDescription()</b> --> String. O próprio e-mail.<br>
 * <b>email.failures()</b> --> List< String >. Lista de mensagens de tratamento.
 * @author MarcosVP
 * @since 24/12/2023
 * 
 */
public class Email {
	
	private boolean isValid = true;
	private String description;
	private String user = "";
	private String domain = "";
	private String invalidChar = "";
	private List<String> failures = new ArrayList<String>();
	
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

	public static Email get(String email) {
		return new Email(email);
	}

    //G&S
    public boolean isValid()						{ return isValid;				  }
	public void setValid(boolean isValid)			{ this.isValid = isValid;		  }
	public String getDescription()					{ return description;			  }
	public void setDescription(String description)	{ this.description = description; }
	public List<String> getFailures()				{ return failures;				  }
	public void setFailures(List<String> failures)	{ this.failures = failures;		  }
	public String getUser()							{ return user;					  }
	public void setUser(String user) 				{ this.user = user;				  }
	public String getDomain()						{ return domain;				  }
	public void setDomain(String domain)			{ this.domain = domain;			  }
	public String getInvalidChar()					{ return invalidChar;			  }
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
	
	public String toString() {
		return getDescription();
	}

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
	 * @apiNote Método que gera uma String para mensagens em tela<br>
	 * Diferente do toString() este método traz apenas as informações de mensagens.
	 * @return {String}
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

