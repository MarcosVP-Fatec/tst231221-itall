package br.com.itall.model.entity.cad;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import br.com.itall.tool.Email;

/**
 * Entidade da tabela "usuarios" <br>
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

	/** Atributos para serem utilizados na automatização do Update */
	private final List<String> attribs = Arrays.asList("nome,email,senha".split(",")); //,dataCriacao
	/** Atributos para serem utilizados na automatização do Update */
	private final List<String> fields = Arrays.asList("nome,email,senha".split(",")); //,data_criacao
	
	private Long id;
    
    /** Tamanho do atributo "nome" (NOME) = 80 */
    public final static short NOME_FIELD_LEN = 80;
    private String nome;
    
    private Email email;
    
    /** Tamanho do atributo "senha" (SENHA) = 80 */
    public final static short SENHA_FIELD_LEN = 80;
    /** Tamanho mínimo do atributo "senha" (SENHA) = 3 */
    public final static int SENHA_FIELD_LEN_MIN = 3;
    private String senha;
    
    private LocalDateTime dataCriacao;
    
    /**
     * Atributo transiente utilizado para alterar a senha.<br>
     * Não é gravado no banco de dados
     */
    private Boolean isMudarSenha;

    /** Básico */
    public UsuarioModel() {}
    /**
     * Parametrizado
     * @param id Identificador único
     * @param nome Nome completo do usuário
     * @param isMudarSenha (Boolean)
     * @param email E-mail do usuário (Login do Sistema) 
     * @param senha Senha de acesso ao sistema
     * @param dataCriacao Data em que este registro foi criado
     */
    public UsuarioModel(Long id, String nome, String email, Boolean isMudarSenha, String senha, LocalDateTime dataCriacao) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setIsMudarSenha(isMudarSenha);
		this.setSenha(senha);
		this.setDataCriacao(dataCriacao==null?LocalDateTime.now():dataCriacao);
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
		this.nome = nome.trim().toUpperCase();
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
	/** @return isMudarSenha (Boolean) */
	public Boolean isMudarSenha() {
		return isMudarSenha;
	}
	/** @return isMudarSenha (Boolean) */
	public Boolean getMudarSenha() {
		return isMudarSenha();
	}
	/** @param isMudarSenha (Boolean) */
	public void setIsMudarSenha(Boolean isMudarSenha) {
		this.isMudarSenha = isMudarSenha;
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
