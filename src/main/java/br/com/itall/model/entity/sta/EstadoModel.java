package br.com.itall.model.entity.sta;

import br.com.itall.model.GenericModel;

/**
 * Entidade da tabela "estados" <br>
 * Tipo ESTÁTICA
 * Registro dos estados do país <br>
 *  
 * @author MarcosVP
 * @since 06/01/2024
 * @version 1.01.0
 * 
 */
//TODO Finalizar quando fizer a tabela no banco.
public class EstadoModel extends GenericModel {

	private String uf;
	private String nome;
	
	/**
	 * Constructor 
	 * @param uf (String)
	 * @param nome (String)
	 */
	public EstadoModel(String uf, String nome) {
		setUf(uf.toUpperCase());
		setNome(nome);
	}
	/** @return uf (String) 	*/ public String getUf() 			{ return uf;		}
	/** @param uf (String) 		*/ public void setUf(String uf) 	{ this.uf = uf;		}
	/** @return nome (String) 	*/ public String getNome() 			{ return nome;		}
	/** @param nome (String) 	*/ public void setNome(String nome) { this.nome = nome;	}
	
}
