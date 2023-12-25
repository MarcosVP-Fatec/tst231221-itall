package br.com.itall.model;

import java.time.LocalDateTime;

import br.com.itall.tool.Email;

/**
 * Entidade da tabela "usuario"
 * @author MarcosVP
 * @since 24/12/2023
 *
 */
public class UsuarioModel {

    private Long id;
    
    public final static short FIELD_LEN_NOME = 80;
    private String nome;
    
    public final static int FIELD_LEN_EMAIL = 255;
    private Email email;
    
    public final static short FIELD_LEN_SENHA = 80;
    public final static int FIELD_LEN_MIN_SENHA = 3;
    private String senha;
    
    private LocalDateTime dataCriacao;

    //CONSTRUCTORS
    public UsuarioModel() {}
    public UsuarioModel(Long id, String nome, String email, String senha, LocalDateTime dataCriacao) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setDataCriacao(dataCriacao==null?LocalDateTime.now():dataCriacao);
	}

	//G&S
	public Long getId() {
		return id;
	}
	public UsuarioModel setId(Long id) {
		this.id = id;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = Email.get(email);
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
