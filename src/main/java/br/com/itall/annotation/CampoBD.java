package br.com.itall.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotação que define que um atributo é um campo do Banco de Dados.<br>
 * Nesta anotação serão permitidos alguns parâmetros que definirão<br>
 * comportamentos específicos da aplicação.
 *  @author MarcosVP
 *  @since 04/01/2024
 *  @version 1.01.0
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface CampoBD {
	
	/** @return field_name (String) Nome do campo no banco de dados.*/
    String field_name() default ""; 
	
    /** @return field_len (int) Tamanho do campo no banco de dados. Usado para limitar os campos nas páginas.*/
    int field_len() default 0; 
	
    /** @return field_len_min (int) Tamanho mínimo do campo. Usado para validar o campo nas páginas*/
    int field_len_min() default 0;
	
    /** @return is_id (boolean) Sinaliza se o campo é um ID.*/
    boolean is_id() default false;
	
    /** @return is_to_insert (boolean) Sinaliza se o campo será tratado automaticamente nas inclusões.*/
    boolean is_to_insert() default true;
	
    /** @return is_to_update (boolean) Sinaliza se o campo será tratado automaticamente nas alterações.*/
    boolean is_to_update() default true;
    
    /** @return is_not_null (boolean) Sinaliza se o campo <b>NÃO></b> permitirá null.*/
    boolean is_not_null() default false;
}
