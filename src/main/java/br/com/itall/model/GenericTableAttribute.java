package br.com.itall.model;

/**
 * Objeto utilizado pela classe GenericModel<br>
 * Contém informações sobre a tabela no banco de dados
 * 
 * @author MarcosVP
 * @since 04/01/2024
 * @version 1.01.0
 *  
 */
public class GenericTableAttribute {
	private String tableName;
	
	/** Construtor
	 * @param name (String) Nome da tabela no banco de dados
	 */
	public GenericTableAttribute(String name) {
		setTableName(name);
	}

	/** @return (String) tableName */ public String getTableName() 		    { return tableName;		}
	/** @param (String) tableName  */ private void setTableName(String name){ this.tableName = name;}
	
}
