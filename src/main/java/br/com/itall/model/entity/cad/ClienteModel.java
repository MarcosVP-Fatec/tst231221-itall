package br.com.itall.model.entity.cad;

import java.time.LocalDate;

import br.com.itall.annotation.CampoBD;
import br.com.itall.annotation.TabelaBD;
import br.com.itall.model.GenericModel;
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
@TabelaBD(name = "clientes")
public class ClienteModel extends GenericModel {

	@CampoBD(is_id = true)
	private Long id;
    
    @CampoBD(field_len = 80)
    private String nome;
    
    @CampoBD(field_len = 80)
    private String sobrenome;

    @CampoBD(field_len = 1)
    private String sexo;
    
    @CampoBD(field_name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @CampoBD(field_len = 20)
    private String nacionalidade;
    
    @CampoBD(field_name = "email", field_len = 255)
    private Email email;
    
    @CampoBD(field_len = 255)
    private String endereco;
    
    @CampoBD(field_len = 100)
    private String cidade;
    
    @CampoBD(field_len = 2)
    private String estado;
    
    @CampoBD(field_len = 20)
    private String telefone;
    
    /** Básico */
    public ClienteModel() {}
    
    /**
     * Construtor Parametrizado
	 * @param id (Long)
	 * @param nome (String)
	 * @param sobrenome (String)
	 * @param sexo (String) F,M
	 * @param dataNascimento (LocalDate)
	 * @param nacionalidade (String)
	 * @param email (String)
	 * @param endereco (String)
	 * @param cidade (String)
	 * @param estado (String)
	 * @param telefone (String)
	 */
	public ClienteModel(Long id, String nome, String sobrenome, String sexo, LocalDate dataNascimento,
			String nacionalidade, String email, String endereco, String cidade, String estado, String telefone) {
		setId(id);
		setNome(nome);
		setSobrenome(sobrenome);
		setSexo(sexo);
		setDataNascimento(dataNascimento);
		setNacionalidade(nacionalidade);
		setEmail(email);
		setEndereco(endereco);
		setCidade(cidade);
		setEstado(estado);
		setTelefone(telefone);
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
		this.nacionalidade = Texto.tiraEspacosDuplos(nacionalidade.trim());
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
		this.estado = estado == null ? estado : estado.trim().toUpperCase();
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
	
}
