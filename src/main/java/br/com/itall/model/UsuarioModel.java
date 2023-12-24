package br.com.itall.model;

import java.time.LocalDateTime;

/**
 * @apiNote Tabela "usuario"
 * @author MarcosVP
 *
 */
//@Entity
//@Table(name = "usuario", indexes = {
//		@Index(name = "usuario_nome_idx", columnList = "nome"),
//		@Index(name = "usuario_email_idx", columnList = "email")
//})
public class UsuarioModel {

	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public final static short FIELD_LEN_NOME = 80;
    //@Column(name = "nome", length = FIELD_LEN_NOME, nullable = false)
    private String nome;
    
    public final static int FIELD_LEN_EMAIL = 255;
    //@Column(name = "email", length = FIELD_LEN_EMAIL, nullable = false)
    private String email;
    
    public final static short FIELD_LEN_SENHA = 80;
    //@Column(name = "senha", length = FIELD_LEN_SENHA)
    private String senha;
    
    //@Column(name = "data_criacao")
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
