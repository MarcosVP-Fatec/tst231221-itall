package br.com.itall.service.implement.cad;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dao.cad.ClienteDAO;
import br.com.itall.model.dto.cad.ClienteDTO;
import br.com.itall.model.entity.cad.ClienteModel;
import br.com.itall.service.cad.ClienteService;

/**
 * Implementação nº 1 dos Serviços de ClienteModel
 * @author MarcosVP
 * @since 02/01/2024
 * @version 1.01.0
 */
public class ClienteServiceImpl1 implements ClienteService {
	
	private ClienteDAO clienteDAO;
	
	/** Básico */
	public ClienteServiceImpl1() { this.init();	}
	/** Injeção das dependências do serviço */
	public void init() {
		this.clienteDAO = new ClienteDAO();
	}

	@Override
	public ClienteModel clienteAltInc(ClienteModel cliente, boolean isInc) throws Exception {
		
		final String tipo = isInc ? "inclusão" : "alteração";
		try {

			if (cliente == null) throw new RuntimeException(String.format("Nenhum cliente foi informado para esta %s!", tipo));
			
			if (isInc) {
				
				if (cliente.getId() != null) throw new RuntimeException(String.format("Tentativa de incluir cliente com identificador informado: Id %s", cliente.getId()));
				
			} else {
				
				if (cliente.getId() == null) throw new RuntimeException("Tentativa de alterar cliente sem identificador informado.");
				
			}
			
			if (cliente.getNome().isEmpty()) throw new RuntimeException("Tentativa de gravar cliente com \"nome\" em branco!");
			if (cliente.getSobrenome().isEmpty()) throw new RuntimeException("Tentativa de gravar cliente com \"sobrenome\" em branco!");
			if (cliente.getSexo().isEmpty()) throw new RuntimeException("Tentativa de gravar cliente com \"sexo\" em branco!");

			if (!cliente.getEmail().getDescription().isEmpty()) {
				if (!cliente.getEmail().isValid()) {
					throw new RuntimeException(cliente.getEmail().toMessages());
				} else {
					if (isInc) {
						if (clienteDAO.existByEmail(cliente.getEmail().getDescription())) throw new RuntimeException(String.format("O e-mail informado já existe: %s", cliente.getEmail().getDescription()));
					} else {
						if (clienteDAO.existByEmailOderUser(cliente.getEmail().getDescription(),cliente.getId())) throw new RuntimeException(String.format("O e-mail informado já existe para outro cliente: %s", cliente.getEmail().getDescription()));
					}
				}
			}
			
			if (isInc) {
				return clienteDAO.inc(cliente);
			} else {
				return clienteDAO.alt(cliente);
			}
			
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());

		} catch (Exception e) {
			final String msg = String.format("Falha inesperada na %s do cliente: %s", tipo, e.getMessage());
			try {e.printStackTrace();} finally {}
			throw new Exception(msg);
		}
		
	}
	
	/**
	 * Lista todos os clientes em ordem alfabética
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	@Override
	public List<ClienteDTO> listAllClientes(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			return clienteDAO.listarTodosClientes();
		} catch (Exception e) {
			throw new Exception(String.format("Erro na pesquisa de clientes: %s",e.getMessage()));
		}
		
	}

	@Override
	public ClienteModel clienteDel(Long id) throws Exception {
		ClienteModel cliente = null;
		try {
			if (id == null || id == 0) throw new RuntimeException("O identificador do cliente não foi informado!");
			cliente = clienteDAO.findById(id);
			
			if (cliente == null) throw new RuntimeException(String.format("Cliente não localizado: Id %d", id));
			
			clienteDAO.del(id);
			
		} catch (Exception e) {
			final String msg = String.format("Falha inesperada ao excluir cliente: %s", e.getMessage());
			try {e.printStackTrace();} finally {}
			throw new Exception(msg);
		}
		return cliente;
	}

	@Override
	public ClienteModel findById(Long id) throws SQLException {
		return clienteDAO.findById(id);
	}

}
