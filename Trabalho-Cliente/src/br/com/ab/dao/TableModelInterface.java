
package br.com.ab.dao;

import java.util.ArrayList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author drink
 */
public interface TableModelInterface {
    /**
     * retorna uma lista com as colunas que você deseja exiber na table
     * @return 
     */
    public ArrayList<TableColumn<Object,Object>> getCols();
    /**
     * Retorna uma lista de objetos conforme o parametro de pesquisa
     * @param param
     * @return 
     */
    public ArrayList<Object> pesquisar(String param);
    
}
