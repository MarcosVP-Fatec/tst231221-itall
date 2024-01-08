package br.com.itall.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.itall.tool.Email;
import br.com.itall.tool.Numero;
import br.com.itall.tool.Texto;
import br.com.itall.tool.annotation.CampoBD;
import br.com.itall.tool.annotation.TabelaBD;

/**
 * Extensão dos Models para controle dos atributos que são campos no banco de
 * dados.
 * 
 * @author MarcosVP
 * @since 04/01/2024
 * @version 1.01.0
 */
public abstract class GenericModel {

	private Map<String, GenericDataAttribute> __fields;
	private GenericTableAttribute __table;
	private Map<String, Integer> __fieldSizes;

	/**
	 * Construtor padrão
	 */
	public GenericModel() {

		/**
		 * Adiciona informações da tabela
		 */
		String tableName = "";

		for (Annotation anota : this.getClass().getAnnotations()) {
			if (anota.annotationType().equals(TabelaBD.class)) {
				tableName = ((TabelaBD) anota).name();
			}
		}
		if (tableName.isEmpty())
			tableName = this.getClass().getSimpleName();

		__table = new GenericTableAttribute(tableName);

		/**
		 * Adiciona os campos anotados na lista de campos
		 */
		if (__fields == null) {

			initFields();

		}

	}

	/**
	 * Método que retorna uma lista de todos os nomes dos campos<br>
	 * desta entidade no banco de dados.
	 * 
	 * @return List&lt;String&gt;
	 */
	public List<String> getFields() {
		return getFields(false);
	}

	/**
	 * Método que retorna uma lista de todos os nomes dos campos<br>
	 * desta entidade no banco de dados, exceto o que for "id".
	 * 
	 * @return List&lt;String&gt;
	 */
	public List<String> getFieldsIgnoreId() {
		return getFields(true);
	}

	/**
	 * @param ignoreId (boolean) Indica se é para ignorar o campo de id.
	 * @return List&lt;String&gt;
	 */
	private List<String> getFields(boolean ignoreId) {
		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
		List<String> retorno = new ArrayList<String>();
		for (GenericDataAttribute dado : lista) {
			if (!ignoreId || !dado.isId())
				retorno.add(dado.getFieldName());
		}
		return retorno;
	}

	/**
	 * Método que retorna uma lista de atributos que são atribuídos no insert.
	 * @return List&lt;String&gt;
	 */
	private List<GenericDataAttribute> getAttributsToInsert() {
		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
		List<GenericDataAttribute> retorno = new ArrayList<GenericDataAttribute>();
		for (GenericDataAttribute atributo : lista) {
			if (atributo.isToInsert())
				retorno.add(atributo);
		}
		return retorno;
	}

	/**
	 * Método que retorna uma lista de atributos que são atribuídos no update.
	 * @return List&lt;String&gt;
	 */
	private List<GenericDataAttribute> getAttributsToUpdate() {
		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
		List<GenericDataAttribute> retorno = new ArrayList<GenericDataAttribute>();
		for (GenericDataAttribute atributo : lista) {
			if (atributo.isToUpdate())
				retorno.add(atributo);
		}
		return retorno;
	}

	/**
	 * Método que retorna o atributo do id.
	 * @return GenericDataAttribute
	 */
	private GenericDataAttribute getAttributId() {
		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
		for (GenericDataAttribute atributo : lista) {
			if (atributo.isId())
				return atributo;
		}
		return null;
	}

	/**
	 * Método que retorna uma List dos atributos que representam campos no banco de
	 * dados.
	 * 
	 * @return List&lt;String&gt;
	 */
	public List<String> getAttribs() {
		return new ArrayList<String>(__fields.keySet());
	}

	/**
	 * Método que retorna uma MappedClass(atributo,valor) com tamanhos dos
	 * campos<br>
	 * Este objeto será transmitido para as páginas e será utilizado como
	 * informação<br>
	 * de limites de campos.
	 * 
	 * @return Map&lt;Strig,Integer&gt;
	 */
	public Map<String, Integer> getFieldSizes() {
		return this.__fieldSizes;
	}

	/**
	 * Retorna o nome da tabela no banco de dados com tratamentos necessários<br>
	 * para leitura em SQL.
	 * 
	 * @return (String) Nome da tabela no banco de dados
	 */
	public String getParseTableName() {
		return __table.getTableName();
	}

	/**
	 * Método que retorna um PreparedStatement com a query SQL para realizar o
	 * insert<br>
	 * 
	 * @param conn (Connection)
	 * @return (PreparedStatement) <br>
	 *         Exemplo: insert into minha_tabela<br>
	 *         (campo1, campo2, campo3) values (?,?,?)
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws SecurityException Lançada pelo <i><b>SecurityManager</b></i> para indicar uma violação de segurança.
	 * @throws NoSuchMethodException Lançada quando um método específico não pode ser encontrado (Reflexão em Java).
	 * @throws InvocationTargetException Lançada por um método ou construtor quando invocados (Reflexão em Java).
	 * @throws IllegalArgumentException Lançada para indicar que um método recebeu um argumento ilegal ou inapropriado.
	 * @throws IllegalAccessException Lançada quando um aplicativo tenta obter um campo ou invocar um método, reflexivamente, cuja origem (método em execução) não tem acesso à definição da classe, campo, método ou construtor especificado.
	 * 
	 */
	public PreparedStatement generateSqlInsert(Connection conn) throws SQLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		StringBuffer qry = new StringBuffer("insert into " + this.getParseTableName());
		StringBuffer val = new StringBuffer("");
		boolean isFirst = true;
		String margem;
		int seq = 0;
		List<Object> listValues = new ArrayList<Object>();

		List<GenericDataAttribute> atributos = getAttributsToInsert();
		if (atributos.size() == 0) throw new RuntimeException("Não há atributos para INSERT no Model.");
		for (GenericDataAttribute var : getAttributsToInsert()) {
			
			Object ori = methodGetFromField(var.getAttributeName()).invoke(this);
			
			if (ori != null) {

				margem = "\n  /*" + Numero.padL(++seq, 3) + "*/   ";

				qry.append(margem + (isFirst ? "( " : ", "));
				val.append(Texto.padR(margem + (isFirst ? "( ?" : ", ?"), 50) + " /*" + var.getFieldName() + "*/");
				isFirst = false;
				qry.append(var.getFieldName());

				// Gera a lista de valores que serão inseridos mais abaixo
				listValues.add(ori);

			}

		}

		qry.append("\n            ) values" + val.toString() + "\n            )");

		PreparedStatement ps = conn.prepareStatement(qry.toString(), Statement.RETURN_GENERATED_KEYS);

		this.parserSets(ps, listValues);

		return ps;

	}

	/**
	 * Método que retorna um PreparedStatement com a query SQL para realizar o
	 * update<br>
	 * 
	 * @param conn (Connection)
	 * @param origem (Object) Objeto Model com os dados novos.
	 * @param destino (Object) Objeto Model atual no BD.
	 * @return (PreparedStatement) <br>
	 *         Exemplo: update minha_tabela<br>
	 *         (campo1, campo2, campo3) values (?,?,?)
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws SecurityException Lançada pelo <i><b>SecurityManager</b></i> para indicar uma violação de segurança.
	 * @throws NoSuchMethodException Lançada quando um método específico não pode ser encontrado (Reflexão em Java).
	 * @throws InvocationTargetException Lançada por um método ou construtor quando invocados (Reflexão em Java).
	 * @throws IllegalArgumentException Lançada para indicar que um método recebeu um argumento ilegal ou inapropriado.
	 * @throws IllegalAccessException Lançada quando um aplicativo tenta obter um campo ou invocar um método, reflexivamente, cuja origem (método em execução) não tem acesso à definição da classe, campo, método ou construtor especificado.
	 * 
	 */
	public PreparedStatement generateSqlUpdate(Connection conn
			                                  ,Object origem
			                                  ,Object destino
			                                  )
           throws SQLException, IllegalAccessException,
			      IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		StringBuffer qry = new StringBuffer("\nupdate " + this.getParseTableName() + " set id=id"); //Este primeiro set garante que não dará erro se não houver alterações.
		List<Object> listValues = new ArrayList<Object>();

		for (GenericDataAttribute var : getAttributsToUpdate()) {

			final Method metodo = methodGetFromField(var.getAttributeName());
			Object ori = metodo.invoke(origem);
			Object des = metodo.invoke(destino);
			
			if (ori == null ) ori = des; //Isto garante que não haverá alteração para esta coluna

			// Só considerar se forem da mesma classe com valores diferentes.
			// Então entra na lista de valores que serão alterados mais abaixo
			if (   ori != null 
				&& (des == null || ori.getClass().equals( des.getClass() ) && !ori.toString().equals(des.toString())) 
			   ){

				qry.append( "\n   , "
						  + var.getFieldName()
						  + " = ?"
						  );
				
				listValues.add(ori);
			}

		}

		qry.append("\n where id = ?");
		listValues.add(methodGetFromField(getAttributId().getAttributeName()).invoke(this));

		PreparedStatement ps = conn.prepareStatement(qry.toString(), Statement.RETURN_GENERATED_KEYS);
		
		this.parserSets(ps, listValues);

		return ps;

	}

	/**
	 * Método que realiza os <b><i>PreparedStatement Setters</i></b> de uma lista
	 * @param ps (PreparedStatement)
	 * @param listValues (List&lt;Object&gt;)
	 */
	private void parserSets(PreparedStatement ps, List<Object> listValues) {
		
		int seq = 0;
		String nomeDoAtributo = "";
		
		try {
			
			for ( Object valor : listValues) {
				seq++;
				nomeDoAtributo = valor.getClass().getSimpleName();
				switch (nomeDoAtributo) {
				case "String":
					ps.setString(seq, (String) valor);
					break;
				case "Long":
					ps.setLong(seq, (Long) valor);
					break;
				case "Integer":
					ps.setInt(seq, (Integer) valor);
					break;
				case "LocalDateTime":
					ps.setTimestamp(seq, Timestamp.valueOf((LocalDateTime) valor));
					break;
				case "LocalDate":
					ps.setDate(seq, java.sql.Date.valueOf((LocalDate) valor));
					break;
				case "Date":
					ps.setDate(seq, new java.sql.Date(((java.util.Date) valor).getTime()));
					break;
				case "BigDecimal":
					ps.setBigDecimal(seq, (BigDecimal) valor);
					break;
				case "Email":
					ps.setString(seq, ((Email) valor).getDescription());
					break;
				case "Short":
					ps.setShort(seq, (Short) valor);
					break;
				case "Character":
					ps.setString(seq, String.valueOf(valor));
					break;
				case "Boolean":
					ps.setBoolean(seq, (Boolean) valor);
					break;

				default:
					ps.setObject(seq, valor);
					break;
				}
			}
			
		} catch (Exception e) {
			Texto.logConsoleErro(String.format("Falha na análise do atributo \"%s\" e sequência (%d).", nomeDoAtributo, seq));
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Gera o método Get através do nome do atributo Método reflexivo.
	 * 
	 * @param nomeAtributo (String)
	 * @throws SecurityException Lançada pelo <i><b>SecurityManager</b></i> para indicar uma violação de segurança.
	 * @throws NoSuchMethodException Lançada quando um método específico não pode ser encontrado (Reflexão em Java).
	 */
	private Method methodGetFromField(String nomeAtributo) throws NoSuchMethodException, SecurityException {
		return this.getClass().getMethod((nomeAtributo.equals("is") ? nomeAtributo
				: "get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.substring(1)));
	}

	/**
	 * Método que é executado uma vez para cada classe e que instancia<br>
	 * as configurações dos campos pela anotação @CampoBD no Model.
	 * 
	 * @author MarcosVP
	 * @since 06/01/2024
	 * 
	 */
	private void initFields() {

		__fields = new HashMap<>();
		__fieldSizes = new HashMap<>();

		for (Field campo : this.getClass().getDeclaredFields()) {

			if (campo.isAnnotationPresent(CampoBD.class)) {

				final CampoBD anotacao = campo.getAnnotation(CampoBD.class);

				__fields.put(campo.getName(),
						new GenericDataAttribute( anotacao.field_name().isEmpty() ? campo.getName() : anotacao.field_name()
								                , campo.getName()
								                , anotacao.field_len()
								                , anotacao.is_id()
								                , anotacao.is_id() ? false : anotacao.is_to_insert()
								                , anotacao.is_to_update()
								                , anotacao.is_not_null()
								                )
						);

				// Parâmetro field_len da anotação
				if (anotacao.field_len() > 0) {
					__fieldSizes.put(campo.getName().toUpperCase() + "_FIELD_LEN", anotacao.field_len());
				}

				// Parâmetro field_len_min da anotação
				if (anotacao.field_len_min() > 0) {
					__fieldSizes.put(campo.getName().toUpperCase() + "_FIELD_LEN_MIN", anotacao.field_len_min());
				}

			}
		}

	}
}
