package br.com.itall.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotação que define que uma classe é uma tabela no Banco de Dados
 * 
 *  @author MarcosVP
 *  @since 05/01/2024
 *  @version 1.01.0
 *  
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface TabelaBD {
    String name(); 
}
