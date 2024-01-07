package br.com.itall.model.dto.cad;

import br.com.itall.model.entity.cad.ClienteModel;

/**
* DTO de Clientes
* Os parâmetros foram criados de acordo com o tratamento do JSP
* 
* @author MarcosVP
* @since 02/01/2024
* @version 1.01.0
* 
*/
public class ClienteDTO extends ClienteModel{

    /**
     * Construtor Básico
     */
    public ClienteDTO() { super(); }

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
	public ClienteDTO(Long id, String nome, String sobrenome, String email, String cidade, String estado, String telefone) {
		setId(id);
		setNome(nome);
		setSobrenome(sobrenome);
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
}
