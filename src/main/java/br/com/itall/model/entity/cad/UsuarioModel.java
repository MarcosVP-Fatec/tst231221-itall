package br.com.itall.model.entity.cad;

import java.time.LocalDateTime;

import br.com.itall.tool.Email;

/**
 * Entidade da tabela "usuario" <br>
 * Tipo CADASTRO
 * Registro dos usuários do sistema <br>
 * O e-mail não poderá se repetir por se tratar do Login único.
 *  
 * @author MarcosVP
 * @since 24/12/2023
 * @version 1.01.0
 *
 */
public class UsuarioModel {

    private Long id;
    
    /**
     * Tamanho do atributo "nome" (NOME) = 80 
     */
    public final static short NOME_FIELD_LEN = 80;
    private String nome;
    
    /** Tamanho do atributo "email" (EMAIL) = 255 */
    public final static int EMAIL_FIELD_LEN = 255;
    private Email email;
    
    /** Tamanho do atributo "senha" (SENHA) = 80 */
    public final static short SENHA_FIELD_LEN = 80;
    /** Tamanho mínimo do atributo "senha" (SENHA) = 3 */
    public final static int SENHA_FIELD_LEN_MIN = 3;
    private String senha;
    
    private LocalDateTime dataCriacao;

    /** Básico */
    public UsuarioModel() {}
    /**
     * Parametrizado
     * @param id Identificador único
     * @param nome Nome completo do usuário
     * @param email E-mail do usuário (Login do Sistema) 
     * @param senha Senha de acesso ao sistema
     * @param dataCriacao Data em que este registro foi criado
     */
    public UsuarioModel(Long id, String nome, String email, String senha, LocalDateTime dataCriacao) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setDataCriacao(dataCriacao==null?LocalDateTime.now():dataCriacao);
	}

    /** @return id (Long) */
	public Long getId() {
		return id;
	}
    /** @param id (Long)
     *  @return UsuarioModel */
	public UsuarioModel setId(Long id) {
		this.id = id;
		return this;
	}
    /** @return nome (String) */
	public String getNome() {
		return nome;
	}
	/** @param nome (String) */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/** @return email (Email) 
	 *  @see Email	 */
	public Email getEmail() {
		return email;
	}
	/**
	 * Set do Email <br>
	 * Armazena um objeto da classe Email gerado a partir do parâmetro 
	 * 
	 * @param email (String)
	 * @see Email
	 */
	public void setEmail(String email) {
		this.email = Email.get(email);
	}
    /** @return senha (String) */
	public String getSenha() {
		return senha;
	}
    /** @param senha (String) */
	public void setSenha(String senha) {
		this.senha = senha;
	}
    /** @return dataCriacao (LocalDateTime) */
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
    /** @param dataCriacao (LocalDateTime) */
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
