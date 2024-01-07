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

import br.com.itall.annotation.CampoBD;
import br.com.itall.annotation.TabelaBD;
import br.com.itall.tool.Email;
import br.com.itall.tool.Numero;
import br.com.itall.tool.Texto;

/**
 * Extensão dos Models para controle dos atributos que são campos no banco de dados.
 * 
 *  @author MarcosVP
 *  @since 04/01/2024
 *  @version 1.01.0
 */
public abstract class GenericModel {
	
	private static Map<String, GenericDataAttribute> __fields;
	private GenericTableAttribute __table;
	private static Map<String, Integer> __fieldSizes;
	
    /**
     * Construtor padrão
     */
	public GenericModel() {
    	
        /**
         * Adiciona informações da tabela
         */
		String tableName = ""; 

        for (Annotation anota : this.getClass().getAnnotations() ) {
            if (anota.annotationType().equals(TabelaBD.class)) {
                tableName = ( (TabelaBD) anota).name();
            }
        }
    	if (tableName.isEmpty()) tableName = this.getClass().getSimpleName();

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
	 * @return List&lt;String&gt;
	 */
	public List<String> getFields(){
		return getFields(false);
	}
	
	/**
	 * Método que retorna uma lista de todos os nomes dos campos<br>
	 * desta entidade no banco de dados, exceto o que for "id".
	 * @return List&lt;String&gt;
	 */
	public List<String> getFieldsIgnoreId(){
		return getFields(true);
	}

	/**
	 * @param ignoreId (boolean) Indica se é para ignorar o campo de id.
	 * @return List&lt;String&gt; 
	 */
	private List<String> getFields(boolean ignoreId){
		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
		List<String> retorno = new ArrayList<String>();
		for (GenericDataAttribute dado : lista) {
			if (!ignoreId || !dado.isId()) retorno.add(dado.getFieldName());
		}
		return retorno;
	}
    
	/**
	 * Método que retorna uma lista de atributos que são atribuídos no insert.
	 * @return List&lt;String&gt; 
	 */
	private List<GenericDataAttribute> getAttributsToInsert(){
	List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
	List<GenericDataAttribute> retorno = new ArrayList<GenericDataAttribute>();
	for (GenericDataAttribute atributo : lista) {
		if ( atributo.isToInsert() ) retorno.add(atributo);
	}
	return retorno;
}

//TODO ???	
//	private List<String> getFieldsToInsert(){
//		List<GenericDataAttribute> lista = new ArrayList<GenericDataAttribute>(__fields.values());
//		List<String> retorno = new ArrayList<String>();
//		for (GenericDataAttribute dado : lista) {
//			if ( dado.isToInsert() ) retorno.add(dado.getFieldName());
//		}
//		return retorno;
//	}

	/**
	 * Método que retorna uma List dos atributos que representam campos no banco de dados.
	 * @return List<String>
	 */
	public List<String> getAttribs(){
		return new ArrayList<String>(__fields.keySet());
	}

	/**
	 * Método que retorna uma MappedClass(atributo,valor) com tamanhos dos campos<br>
	 * Este objeto será transmitido para as páginas e será utilizado como informação<br>
	 * de limites de campos. 
	 * 
	 * @return Map<Strig,Integer>
	 */
	public Map<String, Integer> getFieldSizes(){
		return __fieldSizes;
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
	 * Método que retorna um PreparedStatement com a query SQL para realizar o insert<br>
	 * 
	 * @param conn (Connection)
	 * @return (PreparedStatement) <br>
	 * Exemplo: insert into minha_tabela<br> 
	 *                     (campo1, campo2, campo3) values (?,?,?)
	 * @throws SQLException Tratamento de erros ao preparar o SQL.
	 * @throws SecurityException Tratamento de erros de segurança.
	 * @throws NoSuchMethodException Tratamento de erros de método inexistente. 
	 * @throws InvocationTargetException  Tratamento de erros de Runtime ao executar o método.
	 * @throws IllegalArgumentException Tratamento de erros de argumentos inválidos. 
	 * @throws IllegalAccessException Tratamento de erros de acesso. 
	 * 
	 */
	public PreparedStatement generateSqlInsert(Connection conn) 
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		StringBuffer qry = new StringBuffer("insert into " + this.getParseTableName());
		StringBuffer val = new StringBuffer("");
		boolean isFirst = true;
		String margem;
		int seq = 0;
		List<Object> listValues = new ArrayList<Object>();
		
		for (GenericDataAttribute var : getAttributsToInsert() ) {
			
			margem = "\n  /*" + Numero.padL(++seq,3) + "*/   ";
			
			qry.append( margem + (isFirst?"( ":", ") );
			val.append( Texto.padR( margem + (isFirst?"( ?":", ?") , 50 ) + " /*"+var.getFieldName()+"*/");
			isFirst = false;
			qry.append(var.getFieldName());
			
			//Gera a lista de valores que serão inseridos mais abaixo
			listValues.add( methodGetFromField(var.getAttributeName()).invoke(this) );
			
		}
		
		qry.append("\n            ) values"+val.toString()+"\n            )");
		
		PreparedStatement ps = conn.prepareStatement(qry.toString(), Statement.RETURN_GENERATED_KEYS);
		
		seq = 0;
		for (Object valor : listValues) {
			seq++;
			switch (valor.getClass().getSimpleName()) {
			case "String"			: ps.setString( seq ,(String) valor);
				break;
			case "Long"				: ps.setLong( seq ,(Long) valor);
				break;
			case "Integer"			: ps.setInt( seq , (Integer) valor);
				break;
			case "LocalDateTime"	: ps.setTimestamp( seq , Timestamp.valueOf( (LocalDateTime) valor) );
				break;
			case "LocalDate"		: ps.setDate( seq , java.sql.Date.valueOf( (LocalDate) valor) );
				break;
			case "Date"				: ps.setDate( seq , new java.sql.Date( ((java.util.Date) valor).getTime() ) );
				break;
			case "BigDecimal"		: ps.setBigDecimal( seq , (BigDecimal) valor );
				break;
			case "Email"			: ps.setString( seq , ((Email) valor).getDescription() );
				break;
			case "Short"			: ps.setShort( seq ,(Short) valor);
				break;
			case "Character"		: ps.setString( seq , String.valueOf(valor) );
				break;
			case "Boolean"			: ps.setBoolean( seq , (Boolean) valor );
				break;
				
			default					: ps.setObject( seq , valor );
				break;
			}
		}
		
		return ps;
		
	}
	
	/**
	 * Gera o método Get através do nome do atributo
	 * Método reflexivo.
	 * 
	 * @param nomeAtributo (String)
	 * @throws SecurityException Tratamento de erros de segurança;
	 * @throws NoSuchMethodException Tratamento de erros de método inexistente.
	 */
	private Method methodGetFromField(String nomeAtributo) throws NoSuchMethodException, SecurityException {
		return this.getClass()
				   .getMethod( ( nomeAtributo.equals("is") 
						       ? nomeAtributo 
							   : "get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.substring(1) ) 
						     );
	}
	
	/**
	 * Método que é executado uma vez para cada classe e que instancia os atributos padrões<br>
	 * do Model.
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

                __fields.put( campo.getName() 
                		    , new GenericDataAttribute( anotacao.field_name().isEmpty() ? campo.getName() : anotacao.field_name()
                		    		                  , campo.getName()
                		    		                  , anotacao.field_len()
                			  	                      , anotacao.is_id()
                			  	                      , anotacao.is_id() ? false : anotacao.is_to_insert()
                			  	                      , anotacao.is_to_update()
                				                      ) 
                		    );
                
                //Parâmetro field_len da anotação
                if (anotacao.field_len() > 0) {
                	__fieldSizes.put(campo.getName().toUpperCase()+"_FIELD_LEN",anotacao.field_len());
                }
                
                //Parâmetro field_len_min da anotação
                if (anotacao.field_len_min() > 0) {
                	__fieldSizes.put(campo.getName().toUpperCase()+"_FIELD_LEN_MIN",anotacao.field_len_min());
                }

            }
        }
		
	}
}
