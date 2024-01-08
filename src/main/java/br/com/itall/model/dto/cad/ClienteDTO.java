package br.com.itall.model.dto.cad;

import java.time.LocalDate;
import java.util.Date;

import br.com.itall.model.entity.cad.ClienteModel;
import br.com.itall.tool.Data;

/**
* DTO de Clientes
* Os parâmetros foram criados de acordo com o tratamento do JSP
* 
* @author MarcosVP
* @since 02/01/2024
* @version 1.01.0
* 
*/
public class ClienteDTO {

	private Long id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private Date dataNascimento;
    private String nacionalidade;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;
    
    /**
     * Construtor Básico
     */
    public ClienteDTO() {}

    /**
	 * Construtor - Lista de Clientes
	 * @param id (Long)
	 * @param nome (String)
	 * @param sobrenome (String)
	 * @param email (String)
	 * @param cidade (String)
	 * @param estado (String)
	 * @param telefone (String)
	 */
	public ClienteDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, String email, String cidade, String estado, String telefone) {
		setId(id);
		setNome(nome);
		setSobrenome(sobrenome);
		setDataNascimento(dataNascimento);
		setEmail(email);
		setCidade(cidade);
		setEstado(estado);
		setTelefone(telefone);
	}
	
    /**
     * Construtor pelo Model
     * @param model (ClienteModel)
     */
    public ClienteDTO(ClienteModel model) {
    	setId(model.getId());
    	setNome(model.getNome());
    	setSobrenome(model.getSobrenome());
    	setSexo(model.getSexo());
    	setDataNascimento(model.getDataNascimento());
    	setNacionalidade(model.getNacionalidade());
    	setEmail(model.getEmail().getDescription());
    	setEndereco(model.getEndereco());
    	setCidade(model.getCidade());
    	setEstado(model.getEstado());
    	setTelefone(model.getTelefone());    	
	}

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

	/** @param nome (String) */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** @return sobrenome (String) */
	public String getSobrenome() {
		return sobrenome;
	}

	/** @param sobrenome (String) */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/** @return sexo (String) */
	public String getSexo() {
		return sexo;
	}

	/** @param sexo (String) */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/** @return dataNascimento (Date) */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/** @param dataNascimento (Date) */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/** @param dataNascimento (LocalDate) */
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = Data.convertLocalDateToDate(dataNascimento);
	}

	/** @return nacionalidade (String) */
	public String getNacionalidade() {
		return nacionalidade;
	}

	/** @param nacionalidade (String) */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/** @return email (String) */
	public String getEmail() {
		return email;
	}

	/** @param email (String) */
	public void setEmail(String email) {
		this.email = email;
	}

	/** @return endereco (String) */
	public String getEndereco() {
		return endereco;
	}

	/** @param endereco (String) */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** @return cidade (String) */
	public String getCidade() {
		return cidade;
	}

	/** @param cidade (String) */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** @return estado (String) */
	public String getEstado() {
		return estado;
	}

	/** @param estado (String) */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** @return telefone (String) */
	public String getTelefone() {
		return telefone;
	}

	/** @param telefone (String) */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    
    
}
