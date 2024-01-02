package br.com.itall.tool;

/**
 * Biblioteca de funções para tratamento de endereços <br>
 * Utilize: Endereco end = Endereco.get(); <br>
 *          				.setLogradouro("xxxxx")
 *          				.setNumero("xxxxx")
 *          				.setBairro("xxxxx")
 *          				.setCidade("xxxxxx")
 *          				.setEstado("XX")
 *          				.setPais("xxxxx")
 * Os campos não são obrigatórios, então é possível usar somente os dados que <br>
 * estão disponíveis no momento;          
 *          
 * @author MarcosVP
 * @since 01/01/2024
 * @version 1.01.0
 * 
 */
public class Endereco {
	
	/** Tamanho do campo Logradouro */
	public final static int LOGRADOURO_FIELD_LEN = 100;
	/** Tamanho mínimo permitido do campo Logradouro */
	public final static int LOGRADOURO_FIELD_LEN_MIN = 10;
	private String logradouro;
	
	/** Tamanho do campo Numero */
	public final static int NUMERO_FIELD_LEN = 12;
	/** Tamanho mínimo permitido do campo Logradouro */
	public final static int NUMERO_FIELD_LEN_MIN = 1;
	private String numero;
	
	/** Tamanho do campo Bairro */
	public final static int BAIRRO_FIELD_LEN = 100;
	/** Tamanho mínimo permitido do campo Bairro */
	public final static int BAIRRO_FIELD_LEN_MIN = 5;
	private String bairro;
	
	
	/** Tamanho do campo Cidade */
	public final static int CIDADE_FIELD_LEN = 100;
	/** Tamanho mínimo permitido do campo Cidade */
	public final static int CIDADE_FIELD_LEN_MIN = 2;
	private String cidade;
	
	/** Tamanho do campo Estado */
	public final static int ESTADO_FIELD_LEN = 2;
	/** Tamanho mínimo permitido do campo Estado */
	public final static int ESTADO_FIELD_LEN_MIN = 2;
	private String estado;
	
	private String pais;
	
	/**
	 * Construtor <b><i>Private</i></b><br>
	 * Todos os elementos são inicializados vazios. 
	 */
	private Endereco() {}

	
	/**Executa o construtor e instancia uma classe nova. <br>
	 * A atribuição das classes será por padrão builder utilizando os métodos set.	 
	 * @return Endereco
	 * */
	public static Endereco get() { return new Endereco(); }

	/** @return logradouro (String)  */
		public String getLogradouro() 				 { return logradouro;}
	/** @param logradouro (Strig)	 
	 * 	@return Endereco
	 * */
		public Endereco setLogradouro(String logradouro) { 
			this.logradouro = logradouro.toLowerCase(); 
			return this;	
		}
		
	/** @return numero (String)	     */
		public String getNumero() 			 		 { return numero;}
	/** @param numero (String) Porque o número pode conter letras	 
	 * 	@return Endereco
	 * */
		public Endereco setNumero(String numero) 	 {
			this.numero = (numero==null || numero.isEmpty() ? "S/N" : numero.toUpperCase());
			return this;
		}
		
	/** @return bairro (String)	     */
		public String getBairro() 					 { return bairro;}
	/** @param bairro (String)	 
	 * 	@return Endereco
	 * */
		public Endereco setBairro(String bairro)	 { 
			this.bairro = bairro.toUpperCase();
			return this;
		}
		
	/** @return the cidade 	        */
		public String getCidade() 					 { return cidade;						}
	/** @param cidade (String)	 
	 * 	@return Endereco
	 * */
		public Endereco setCidade(String cidade)	 { 
			this.cidade = cidade.toUpperCase();
			return this;
		}
		
	/** @return estado (String)	 */
		public String getEstado()					 { return estado;						}
	/** @param estado (String)	 
	 * 	@return Endereco
	 * */

		public Endereco setEstado(String estado)	 { 
			this.estado = estado.toUpperCase();
			return this;
		}
		
	/** @return pais (String) 	 */
		public String getPais() 					 { return pais;							}
	/** @param pais (String)
	 * 	@return Endereco
	 * */
		public Endereco setPais(String pais)		 { 
			this.pais = pais.toUpperCase();
			return this;
		}

	/**
	 * Ajuste no método padrão para retornar o endereço de modo descritivo
	 * @return String  
	 */
	@Override
	public String toString() {
		return getLogradouro().trim() 
		+ ", " + getNumero().trim() 
		+ ", " + getBairro().trim()
		+ ", " + getCidade().trim()
		+ " - " + getEstado(); 
	}

}