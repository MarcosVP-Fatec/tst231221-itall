package br.com.itall.model.entity.cad;

import java.time.LocalDateTime;

import br.com.itall.model.GenericModel;
import br.com.itall.tool.Email;
import br.com.itall.tool.Texto;
import br.com.itall.tool.annotation.CampoBD;
import br.com.itall.tool.annotation.TabelaBD;

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
@TabelaBD(name = "usuarios")
public class UsuarioModel extends GenericModel {

	@CampoBD(is_id = true)
	private Long id;
    
    @CampoBD(field_len = 80)
    private String nome;
    
    @CampoBD(field_len = 255)
    private Email email;
    
    @CampoBD(field_len = 80, field_len_min = 3)
    private String senha;
    
    @CampoBD(field_name = "data_criacao", is_to_update = false)
    private LocalDateTime dataCriacao;
    
    /** Atributos transientes utilizados para alterar a senha. */
    private Boolean isMudarSenha;
    private String senhaAnterior;

    /** Básico */
    public UsuarioModel() {}
    /**
     * Parametrizado
     * @param id (Long) Identificador único
     * @param nome (String) Nome completo do usuário
     * @param email (String) E-mail do usuário (Login do Sistema) 
     * @param isMudarSenha (Boolean) - Transiente para indicar que vai mudar a senha
     * @param senha (String) Senha de acesso ao sistema
     * @param dataCriacao (LocalDateTime) Data em que este registro foi criado
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
		this.email = ( email == null ? null : Email.get(email) );
	}
    /** @return senha (String) */
	public String getSenha() {
		return senha;
	}
    /** @param senha (String) */
	public void setSenha(String senha) {
		this.senha = (senha == null ? null : senha);
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

	/** @return senhaAnterior (String) */
	public String getSenhaAnterior() {
		return senhaAnterior;
	}
	/** @param senhaAnterior (String) 
	 * 	@return UsuarioModel
	 */
	public UsuarioModel setSenhaAnterior(String senhaAnterior) {
		this.senhaAnterior = senhaAnterior;
		return this;
	}

}
