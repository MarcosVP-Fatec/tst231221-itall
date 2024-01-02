package br.com.itall.model.entity.cad;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.com.itall.tool.Email;
import br.com.itall.tool.Texto;

/**
 * Entidade da tabela "clientes" <br>
 * Tipo CADASTRO
 * Registro dos clientes do sistema <br>
 * O e-mail não poderá se repetir.
 *  
 * @author MarcosVP
 * @since 01/01/2024
 * @version 1.01.0
 *
 */
public class ClienteModel {

	/** Atributos para serem utilizados na automatização do Update */
	private final List<String> attribs = Arrays.asList("nome,sobrenome,sexo,dataNascimento,nacionalidade,email,endereco,cidade,estado,telefone".split(",")); 
	/** Atributos para serem utilizados na automatização do Update */
	private final List<String> fields = Arrays.asList("nome,sobrenome,sexo,data_nascimento,nacionalidade,email,endereco,cidade,estado,telefone".split(",")); 
	
	private Long id;
    
    /** Tamanho do atributo "nome" (NOME) = 80 */
    public final static short NOME_FIELD_LEN = 80;
    private String nome;
    
    /** Tamanho do atributo "sobrenome" (SOBRENOME) = 80 */
    public final static short SOBRENOME_FIELD_LEN = 80;
    private String sobrenome;

    /** Tamanho do atributo "sexo" (SEXO) = 1 */
    public final static short SEXO_FIELD_LEN = 1;
    private String sexo;
    
    private LocalDate dataNascimento;
    
    private String nacionalidade;
    
    private Email email;
    
    private String endereco;
    
    private String cidade;
    
    private String estado;
    
    private String telefone;
    
    /** @return id (Long) */
	public Long getId() {
		return id;
	}
    /** @param id (Long) */
	public void setId(Long id) {
		this.id = id;
	}
    /** @return nome (String) */
	public String getNome() {
		return nome;
	}
	/**
	 * Set do nome<br>
	 * Independente da forma como é transmitodo sempre é gravado em caixa alta. 
	 * 
	 * @param nome (String) 
	 * 
	 * */
	public void setNome(String nome) {
		this.nome = Texto.tiraEspacosDuplos(nome.trim().toUpperCase());
	}
	
	/** @return email (Email) 
	 *  @see Email	 */
	public Email getEmail() {
		return email;
	}
	/**
	 * Set do Email <br>
	 * Armazena um objeto da classe Email gerado a partir do parâmetro <br>
	 * Independente da forma como os e-mails são transmitidos sempre são gravados em caixa baixa.
	 * 
	 * @param email (String)
	 * @see Email
	 */
	public void setEmail(String email) {
		this.email = Email.get(email);
	}
	
	/** @return sobrenome (String) */
	public String getSobrenome() {
		return sobrenome;
	}
	/**
	 * @param sobrenome (String)
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = Texto.tiraEspacosDuplos(sobrenome.trim().toUpperCase());
	}
	
	/** @return sexo (String) */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo (String)
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo.toUpperCase();
	}
	
	/** @return dataNascimento (LocalDate) */
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento (LocalDate)
	 */
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	/** @return nacionalidade (String) */
	public String getNacionalidade() {
		return nacionalidade;
	}
	/**
	 * @param nacionalidade (String)
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = Texto.tiraEspacosDuplos(nacionalidade.trim().toUpperCase());
	}
	
	/** @return endereco (String) */
	public String getEndereco() {
		return endereco;
	}
	
	/**
	 * @param endereco (String)
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * @return cidade (String)
	 */
	public String getCidade() {
		return cidade;
	}
	/**
	 * @param cidade (String)
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	/**
	 * @return estado (String)
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param estado (String)
	 */
	public void setEstado(String estado) {
		this.estado = estado.trim().toUpperCase();
	}
	
	/**
	 * @return telefone (String)
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone.trim();
	}

	/**
	 * @return List&lt;String&gt; de nomes dos campos (Exceto o id)
	 */
	public List<String> getFields() {
		return fields;
	}
	/**
	 * @return List&lt;String&gt; de nomes de atributos (Exceto o id)
	 */
	public List<String> getAttribs() {
		return attribs;
	}
	
}
