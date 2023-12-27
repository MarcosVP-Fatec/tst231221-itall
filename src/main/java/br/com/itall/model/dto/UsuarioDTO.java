package br.com.itall.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

import br.com.itall.tool.Data;

/**
 * DTO de Usuários
 * Os parâmetros foram criados de acordo com o tratamento do JSP
 * 
 * @author MarcosVP
 * @since 26/12/2023
 * @version 1.01.0
 * 
 */
public class UsuarioDTO {
	
    private Long id;
    private String nome;
    private String email;
    private Date dataCriacao;

    /**
     * Construtor 
     *  
     * @param id (Long)
     * @param nome (String)
     * @param email (String)
     * @param dataCriacao (LocalDate)
     * 
     */
    public UsuarioDTO(Long id, String nome, String email, LocalDateTime dataCriacao) {
    	setId(id);
    	setNome(nome);
    	setEmail(email);
    	setDataCriacao(dataCriacao);
	}
    
    /** @return id (Long) */
	public Long getId() 									{ return id;    					}
	/** @param id Long */
	public void setId(Long id) 								{ this.id = id; 					}
	/** @return nome (String) */
	public String getNome() 								{ return nome;						}
	/** @param nome (String) */
	public void setNome(String nome) 						{ this.nome = nome; 				}
	/** @return email (String) */
	public String getEmail() 								{ return email;						}
	/** @param email (String) */
	public void setEmail(String email) 						{ this.email = email;				}
	/** @return dataCriacao (Date) */
	public Date getDataCriacao() 							{ return dataCriacao; 				}
	/**
	 * Faz a transformação automática para o tipo Date
	 * @param dataCriacao (LocalDateTime)
	 */
	public void setDataCriacao(LocalDateTime dataCriacao) 	{ this.dataCriacao = Data.localDateTimeToDate(dataCriacao);	}

}
