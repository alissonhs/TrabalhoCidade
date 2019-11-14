
package cidadeestado;

import br.com.ab.contracts.ICriteria;
import br.com.ab.dao.DaoInterface;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.model.Cidade;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author drink
 */
public class FalseDaoCidade implements 
        TableModelInterface, 
        DaoInterface
        {

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object,Object>> cols = 
                new ArrayList<>();
        
        TableColumn<Object,Object> cnome =
                new TableColumn<>("Nome Cidade");
        
        cnome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
                );
        cols.add(cnome);
        return cols;        
    }

    @Override
    public ArrayList<Object> pesquisar(String param) {
        ArrayList<Object> lista = new ArrayList<>();
        Cidade palotina = new Cidade();
        palotina.setId(1);
        palotina.setNome("PALOTINA");
        lista.add(palotina);
        Cidade maripa = new Cidade();
        maripa.setId(2);
        maripa.setNome("MARIPA");
        lista.add(maripa);
        
        return lista;
    }

    @Override
    public ArrayList<Object> getByCriterios(ICriteria c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
