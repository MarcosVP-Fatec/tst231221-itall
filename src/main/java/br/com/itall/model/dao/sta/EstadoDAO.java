package br.com.itall.model.dao.sta;

import java.util.ArrayList;
import java.util.List;

import br.com.itall.model.entity.sta.EstadoModel;

/**
 * DAO da entidade EstadoModel<br>
 * Este DAO serve inicialmente para fornecer dados para as páginas, porém<br>
 * a tabela ainda não foi criada.
 * 
 * @author MarcosVP
 * @since 06/01/2024
 * @version 1.01.0
 * @see br.com.itall.model.entity.cad.UsuarioModel
 */
//TODO - Finalizar quando a tabela estados for criada
public class EstadoDAO {

	static List<EstadoModel> estados;
	
	/**
	 * Retorna todos os estados cadastrados
	 * @return List&lt;EstadoModel&gt;
	 */
	public static List<EstadoModel> findAll() {
		if (estados == null) {
			
			estados = new ArrayList<EstadoModel>();
			
			estados.add(new EstadoModel("SP","São Paulo"));
			estados.add(new EstadoModel("RJ","Rio de Janeiro"));
			estados.add(new EstadoModel("MG","Minas Gerais"));
			estados.add(new EstadoModel("ES","Espírito Santo"));
			estados.add(new EstadoModel("RO","Rondônia"));
			estados.add(new EstadoModel("AC","Acre"));
			estados.add(new EstadoModel("AM","Amazonas"));
			estados.add(new EstadoModel("RR","Roraima"));
			estados.add(new EstadoModel("PA","Pará"));
			estados.add(new EstadoModel("AP","Amapá"));
			estados.add(new EstadoModel("TO","Tocantins"));
			estados.add(new EstadoModel("MA","Maranhão"));
			estados.add(new EstadoModel("PI","Piauí"));
			estados.add(new EstadoModel("CE","Ceará"));
			estados.add(new EstadoModel("RN","Rio Grande do Norte"));
			estados.add(new EstadoModel("PB","Paraíba"));
			estados.add(new EstadoModel("PE","Pernambuco"));
			estados.add(new EstadoModel("AL","Alagoas"));
			estados.add(new EstadoModel("SE","Sergipe"));
			estados.add(new EstadoModel("BA","Bahia"));
			estados.add(new EstadoModel("PR","Paraná"));
			estados.add(new EstadoModel("SC","Santa Catarina"));
			estados.add(new EstadoModel("RS","Rio Grande do Sul"));
			estados.add(new EstadoModel("MS","Mato Grosso do Sul"));
			estados.add(new EstadoModel("MT","Mato Grosso"));
			estados.add(new EstadoModel("GO","Goiás"));
			estados.add(new EstadoModel("DF","Distrito Federal"));
			estados.add(new EstadoModel("XX","Outros"));

		}
		
		return estados;
	}
	
}
