
package br.com.ab.dao;

import br.com.ab.contracts.ICriteria;
import java.util.ArrayList;

/**
 *
 * @author drink
 */
public interface DaoInterface {
    /**
     * Retorna uma lista de objetos conforme o critério
     * @param c String Critérios
     * @return 
     */
    public ArrayList<Object> getByCriterios(ICriteria c);
    /**
     * Salva um objeto qualquer
     * @param o Objeto
     */
    public void salvar(Object o);
    /**
     * Remove um objeto qualquer
     * @param o 
     */
    public void remover(Object o);
    /**
     * Retorna um Objeto pelo ID
     * @param id id do objeto
     * @return 
     */
    public Object getById(long id);
    
    
}
