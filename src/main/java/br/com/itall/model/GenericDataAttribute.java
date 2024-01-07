package br.com.itall.model;

/**
 * Objeto utilizado pela classe GenericModel<br>
 * Contém informações sobre cada atributo/campo de tabela.<br>
 * Obs.: Os métodos <b>setters</b> desta classe só podem ser utilizados pelo <b>constructor</b>.
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
	
	/** Construtor
	 *  @param fieldName (String) Nome do campo no banco de dados.
	 *  @param attributeName (String) Nome do atributo na classe.
	 *  @param fieldLen (Integer) Tamanho do campo.
	 *  @param isId (boolean) Indica se é identificador.
	 *  @param isToInsert (boolean = true) Indica se é campo que entrará na inclusão de novos dados.
	 *  @param isToUpdate (boolean = true) Indica se é campo que entrará na alteração de novos dados.
	 */
	public GenericDataAttribute(String fieldName
			                   ,String attributeName
			                   ,Integer fieldLen
			                   ,boolean isId
			                   ,boolean isToInsert
			                   ,boolean isToUpdate) {
		setFieldName(fieldName);
		setAttributeName(attributeName);
		setFieldLen(fieldLen);
		setId(isId);
		setToInsert(isToInsert);
		setToUpdate(isToUpdate);
	}

	/** @return fieldName (String)*/					public String getFieldName() 						{ return fieldName;					 }
	/** @param fieldName (String)*/						private void setFieldName(String fieldName)			{ this.fieldName = fieldName;		 }
	/** @return attributeName (String)*/				public String getAttributeName() 					{ return attributeName;				 }
	/** @param attributeName (String)*/					private void setAttributeName(String attributeName)	{ this.attributeName = attributeName;}
	/** @return isId (boolean)*/						public boolean isId() 								{ return isId; 						 }
	/** @param isId (boolean)*/ 						private void setId(boolean isId) 					{ this.isId = isId; 				 }
	/** @return isToInsert (boolean) 
	 *  Indica que é para colocar no insert padrão*/	public boolean isToInsert() 						{ return isToInsert; 				 }
	/** @param isToInsert (boolean) default True */		private void setToInsert(boolean isToInsert)		{ this.isToInsert = isToInsert; 	 }
	/** @return isToUpdate (boolean)
	 *  Indica que é para colocar no update padrão*/	public boolean isToUpdate() 						{ return isToUpdate; 				 }
	/** @param isToUpdate (boolean) default True */		private void setToUpdate(boolean isToUpdate)		{ this.isToUpdate = isToUpdate; 	 }
	/** @return fieldLen (Integer) */					public Integer getFieldLen() 						{ return fieldLen;					 }
	/** @param fieldLen (Integer) */					public void setFieldLen(Integer fieldLen) 			{ this.fieldLen = fieldLen;			 }
	
	
	
}
