package br.com.itall.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
/**
 * Anotação que define que um atributo é um campo do Banco de Dados
 * 
 *  @author MarcosVP
 *  @since 04/01/2024
 *  @version 1.01.0
 *  
 */
public @interface CampoBD {
    String field_name() default ""; 
    int field_len() default 0; 
    int field_len_min() default 0;
    boolean is_id() default false;
    boolean is_to_insert() default true;
    boolean is_to_update() default true;
}
