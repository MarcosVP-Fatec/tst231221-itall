package br.com.itall.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Biblioteca de funções para tratamento de arquivos em disco (I/O)
 * 
 * @author MarcosVP
 * @since 03/01/2024
 * @version 1.01.0
 */
public class Arquivo {

	/**
	 * Lê um arquivo em disco e retorna o seu conteúdo.
	 * Não usar esta função para arquivos muito grandes.
	 * 
	 * @param pathArq (String) Caminho e nome do arquivo
	 * @return (String) Conteúdo lido.
	 */
	public static String fileRead( String pathArq ) {
		
        try {
    	 	
        	final String nomeArq = pathArq; 
            File arq = new File(nomeArq);
            if (!arq.exists()) {
            	Texto.logConsoleErro("Arquivo não localizado: " + pathArq);
            	return "";
            }
            
            BufferedReader handleR = new BufferedReader(new FileReader(arq));
            StringBuffer buffer = new StringBuffer();
            String linha;
            while ((linha = handleR.readLine()) != null) {
                buffer.append(linha).append("\n");
            }
            handleR.close();
            return handleR.toString();

        } catch (IOException e) {
        	Texto.logConsoleErro("Leitura do arquivo: " + pathArq);
            return "";
        }
        
	}
	
	/**
	 * Altera o conteúdo texto dentro de um arquivo
	 *  
	 * @param pathArq (String) Caminho e nome do arquivo
	 * @param from (String) Texto que será substituído
	 * @param to (String) Texto que será inserido
	 * @return boolean Se deu certo ou não.
	 */
	public static boolean fileReplace( String pathArq, String from, String to) {
		
        try {
            // Leitura do arquivo
            File arq = new File(pathArq);
            if (!arq.exists() || from == null || to == null) return false;
            
            String atual = fileRead(pathArq);
            String novo = atual.toString().replaceAll(from, to);
            
            if (novo.equals(atual)) return true;

            BufferedWriter handleW = new BufferedWriter(new FileWriter(pathArq));
            handleW.write(novo);
            handleW.close();

        } catch (IOException e) {
        	Texto.logConsoleErro(e.getMessage());
        }
        
        return true;
		
	}
	
}
