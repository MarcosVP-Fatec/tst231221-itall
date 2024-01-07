package br.com.itall.model.dao.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.tool.Texto;

/**
 * Objeto que trata o SQL para o PreparedStatement rodar um update <br>
 * Em uma tabela somente para os campos que tiveram alteração. <br>
 * Ao instanciar este objeto ele já faz o tratamento
 * 
 * @author MarcosVP
 * @since 27/12/2023
 * @version 1.01.0
 * 
 */
public class SqlPreparedStatementUpdate {
	
	private String sqlExpression = "";
	private List<String> methods = new ArrayList<String>();
	private List<Object> valores = new ArrayList<Object>();
	
	/**
	 * Objeto que trata o SQL para o PreparedStatement rodar um update <br>
	 * Vide o método principal.
	 * 
	 * @param origem (Object) 
	 * @param destino (Object) 
	 * @param sql (String)
	 * @return SqlPreparedStatementUpdate
	 * @throws IllegalAccessException Vide método principal
	 * @throws IllegalArgumentException Vide método principal
	 * @throws InvocationTargetException Vide método principal
	 * @throws NoSuchMethodException Vide método principal
	 * @throws SecurityException Vide método principal
	 * @see #get(Object, Object, String, Object)
	 */
	public static SqlPreparedStatementUpdate get(Object origem, Object destino, String sql) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return get(origem, destino, sql, null);
	}

	/**
	 * 
	 * Objeto que trata o SQL para o PreparedStatement rodar um update <br>
	 * Somente para os campos que tiveram alteração. <br>
	 * Ao instanciar este objeto ele já faz o tratamento necessário<br>
	 * Feito inicialmente para a entidade UsuarioModel
	 * Ajustes podem ser necessários para novas implementações.
	 * Os dois primeiros parâmetros precisam ser da mesma entidade. 
	 * 
	 * @author MarcosVP
	 * @since 27/12/2023
	 * @version 1.01.0
	 * 
	 * @param origem (Object) Objeto da Entidade que está com as alterações
	 * @param destino (Object) Objeto da Entidade do Banco de Dados.
	 * @param sql Expressão SQL do UPDATE (Ex.: 'update usuario (SET) where id = ?')
	 * @param id (Object) Utilizado para informar o id [Opcional]
	 * @return SqlPreparedStatementUpdate Tratamento de erros da preparação do SQL.
	 * @throws IllegalAccessException Tratamento de chamadas inválidas em <i>runtime</i>.
	 * @throws IllegalArgumentException Tratamento de argumentos inválidos transmitidos em <i>runtime</i>.
	 * @throws InvocationTargetException Tratamento de erros na identificação de métodos.
	 * @throws NoSuchMethodException Tratamento de tentativa de evocar um método inexistente.
	 * @throws SecurityException Tratamento de segurança
	 */
	public static SqlPreparedStatementUpdate get(Object origem, Object destino, String sql, Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return new SqlPreparedStatementUpdate(origem, destino, sql, id);
	}
	
	
	/**
	 * Trata as expressões que serão alteradas no SQL.
	 * Se nenhum campo for alterado retorna null para tratar depois no app.
	 * 
	 * @author MarcosVP
	 * @since 27/12/2023
	 * @param origem Entidade de Origem
	 * @param destino Entidade de Destino
	 * @param sql Expressão SQL que contenha (SET)
	 * @return String SQL tratado
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private SqlPreparedStatementUpdate(Object origem, Object destino, String sql, Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		@SuppressWarnings("unchecked")
		List<String> listaAttrib = (List<String>) destino.getClass().getMethod("getAttribs").invoke(destino);
		@SuppressWarnings("unchecked")
		List<String> listaFields = (List<String>) destino.getClass().getMethod("getFields").invoke(destino);
		
		StringBuffer sbCampos = new StringBuffer();
		
		for (Iterator<String> it = ((Collection<String>) listaAttrib ).iterator(); it.hasNext();) {
			
			String nomeAtrib = (String) it.next();
			
			final Method metodo = UsuarioModel.class.getMethod("get" + Character.toUpperCase(nomeAtrib.charAt(0)) + nomeAtrib.substring(1));

			Object ori = metodo.invoke(origem);
			Object des = metodo.invoke(destino);
			
			if (ori == null ) ori = des; //Isto garante que não haverá alteração para esta coluna
			
			//Só trata ser forem da mesma classe e valores diferentes
			if ( ori.getClass().equals( des.getClass() ) && !ori.toString().equals(des.toString())) {

				final String nomeCampo = listaFields.get(listaAttrib.indexOf(nomeAtrib));
				sbCampos.append(nomeCampo+" = ?, ");
				methodsAndValues(ori, methods, valores);
				
			}
		}
		
		// O id não é obrigatório, pois podem haver mais cláusulas no where.
		if (id != null) {
			methodsAndValues(id, methods, valores);
		}
		
		String sCampos = sbCampos.toString();
		
		sCampos = sCampos.substring(0,sCampos.length()-2);
		setSqlExpression(sql.replace("(SET)", "set "+sCampos+" "));
		setValores(valores);
	}
	
	/**
	 * Método que executa todos os setters de acordo com o que foi apurado<br>
	 * automaticamente antes.<br>
	 * Exemplo:<br>
	 *  	    final String sql = "update usuario (nome, email, senha, data_criacao) values (?,?,?,?)";
	 *			UsuarioModel usuDestino = findById(usuario.getId());<br>
	 *			SqlPreparedStatementUpdate psup = SqlPreparedStatementUpdate.get(usuario, usuDestino, sql);
	 *			conectar();
	 *			PreparedStatement ps = getConexao().prepareStatement(psup.getSqlExpression(), Statement.RETURN_GENERATED_KEYS);
	 *			psu.multiSets(ps)
	 * 
	 * @param ps PreparedStatement
	 * @throws SQLException Tratamento de Erros de execução SQL
	 * @throws SecurityException Tratamento de erros
	 * @throws NoSuchMethodException Tratamento de Falha ao Identificar o método
	 * @throws InvocationTargetException Tratamento ao executar método dinamicamente
	 * @throws IllegalArgumentException Tratamento de argumentos ilegais
	 * @throws IllegalAccessException Tratamento de acesso ilegal.
	 */
	public void multiSets(PreparedStatement ps) throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int i = 0; i < this.valores.size(); i++) {

			String nomeMetodoSet = this.methods.get(i);
			Object valor = this.valores.get(i);
			
			//Necessário desviar porque há problemas na montagem do método para Long
			switch (nomeMetodoSet) {
			case "setLong":
				ps.setLong(i+1, (long) valor);
				break;

			default:
				//Obter o método setter da classe PS usando reflexão
				Method metodo = PreparedStatement.class.getMethod(nomeMetodoSet, int.class, valor.getClass() );
				//Executa o setter na instância da classe
				metodo.invoke(ps, i+1, valor);
				break;
			}
			
		}
	}
	
	/**
	 * Função que identifica qual o método set do PreparedStatement será utilizado<br>
	 * de acordo com o tipo da classe.
	 * @author MarcosVP
	 * @since 27/12/2023
	 * @version 1.01.0 
	 */
	private void methodsAndValues(Object valor, List<String> methods, List<Object> valores) {
		Object valorAjustado = null;
		String method;
		switch (valor.getClass().getSimpleName().toUpperCase()) {
		case "STRING": 			method = "setString";
		break;
		case "LONG": 			method = "setLong";
		break;
		case "INT": 			method = "setInt";
		break;
		case "LOCALDATETIME": 	method = "setTimestamp"; valorAjustado = Timestamp.valueOf((LocalDateTime) valor);
		break;
		case "LOCALDATE": 		method = "setDate"; 
		break;
		case "TIMESTAMP": 		method = "setTimestamp";
		break;
		case "DATE": 			method = "setDate";
		break;
		case "BIGDECIMAL": 		method = "setBigDecimal";
		break;
		case "DOUBLE": 			method = "setDouble";
		break;
		case "FLOATE": 			method = "setFloate";
		break;
		case "SHORT": 			method = "setShort";
		break;
		case "BOOLEAN": 		method = "setBoolean";
		break;
		case "NULL": 			method = "setNull";
		break;
		case "EMAIL":			method = "setString";
		break;
		default:
			final String msg = "nomeSetMetodo(Object valor) não preparado para valor do tipo " + valor.getClass().getSimpleName();
			Texto.logConsole(msg);
			throw new RuntimeException(msg);
		}
		
		if (valorAjustado == null) {
			valores.add(valor);
		} else {
			valores.add(valorAjustado);
		}
		
		methods.add(method);
	}

	/**
	 * @return the cSqlExpression
	 */
	public String getSqlExpression() {
		return sqlExpression;
	}

	/**
	 * @param cSqlExpression the cSqlExpression to set
	 */
	public void setSqlExpression(String cSqlExpression) {
		this.sqlExpression = cSqlExpression;
	}

	/**
	 * @return the methods
	 */
	public List<String> getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	/**
	 * @return the valores
	 */
	public List<Object> getValores() {
		return valores;
	}

	/**
	 * @param valores the valores to set
	 */
	public void setValores(List<Object> valores) {
		this.valores = valores;
	}
	
	@Override
	public String toString() {
		StringBuffer txt = new StringBuffer("SQL    : " + getSqlExpression()+"\n"+
	                                        "METHODS: " + getMethods() + "\n" +
				                            "VALUES : ");
		String virgula = "";
		for (Object valor : getValores()) {
			txt.append(virgula+valor);
			virgula = ", ";
		}
		
		return txt.toString();
	}

}
