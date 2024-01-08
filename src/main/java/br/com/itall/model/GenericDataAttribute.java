package br.com.itall.model;

/**
 * Objeto utilizado pela classe GenericModel<br>
 * Contém informações sobre cada atributo/campo de tabela.<br>
 * Obs.: Os métodos <b><i>setters</i></b> desta classe só podem ser utilizados pelo <b><i>constructor</i></b>.
 * @author MarcosVP
 * @since 04/01/2024
 * @version 1.01.0
 *  
 */
public class GenericDataAttribute {
	private String fieldName;
	private String attributeName;
	private Integer fieldLen;
	private boolean isId;
	private boolean isToInsert;
	private boolean isToUpdate;
	private boolean isNotNull;
	
	/** Construtor
	 *  @param fieldName (String) Nome do campo no banco de dados.
	 *  @param attributeName (String) Nome do atributo na classe.
	 *  @param fieldLen (Integer) Tamanho do campo.
	 *  @param isId (boolean) Indica se é identificador.
	 *  @param isToInsert (boolean = true) Indica se é campo que entrará na inclusão de novos dados.
	 *  @param isToUpdate (boolean = true) Indica se é campo que entrará na alteração de novos dados.
	 *  @param isNotNull (boolean = false) Indica que o campo não irá aceitar <i>NOT NULL</i>. 
	 */
	public GenericDataAttribute(String fieldName
			                   ,String attributeName
			                   ,Integer fieldLen
			                   ,boolean isId
			                   ,boolean isToInsert
			                   ,boolean isToUpdate
			                   ,boolean isNotNull
			                   ){
		setFieldName(fieldName);
		setAttributeName(attributeName);
		setFieldLen(fieldLen);
		setId(isId);
		setToInsert(isToInsert);
		setToUpdate(isToUpdate);
		setNotNull(isNotNull);
	}

	/** @return fieldName (String)*/					public String getFieldName() 						{ return fieldName;					 }
	/** @param fieldName (String)*/						private void setFieldName(String fieldName)			{ this.fieldName = fieldName;		 }
	/** @return attributeName (String)*/				public String getAttributeName() 					{ return attributeName;				 }
	/** @param attributeName (String)*/					private void setAttributeName(String attributeName)	{ this.attributeName = attributeName;}
	/** @return isId (boolean)*/						public boolean isId() 								{ return isId; 						 }
	/** @param isId (boolean)*/ 						private void setId(boolean isId) 					{ this.isId = isId; 				 }
	/** @return isToInsert (boolean) 
	 *  Indica que é para colocar no insert padrão*/	public boolean isToInsert() 						{ return isToInsert; 				 }
	/** @param isToInsert (boolean) default true */		private void setToInsert(boolean isToInsert)		{ this.isToInsert = isToInsert; 	 }
	/** @return isToUpdate (boolean)
	 *  Indica que é para colocar no update padrão*/	public boolean isToUpdate() 						{ return isToUpdate; 				 }
	/** @param isToUpdate (boolean) default true */		private void setToUpdate(boolean isToUpdate)		{ this.isToUpdate = isToUpdate; 	 }
	/** @return fieldLen (Integer) */					public Integer getFieldLen() 						{ return fieldLen;					 }
	/** @param fieldLen (Integer) */					public void setFieldLen(Integer fieldLen) 			{ this.fieldLen = fieldLen;			 }
	/** @return isNotNull (boolean) */					public boolean isNotNull() 							{ return isNotNull;					 }
	/** @param isNotNull (boolean) default false*/		public void setNotNull(boolean isNotNull) 			{ this.isNotNull = isNotNull;		 }
	
}
