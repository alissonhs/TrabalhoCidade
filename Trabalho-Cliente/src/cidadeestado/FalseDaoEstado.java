package cidadeestado;

import br.com.ab.dao.TableModelInterface;
import br.com.ab.model.Estado;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author drink
 */
public class FalseDaoEstado implements TableModelInterface {

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object,Object>> cols = new
            ArrayList<>();
        
        TableColumn<Object,Object> nome = new 
            TableColumn<>("Nome estado");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cols.add(nome);
        
        TableColumn<Object,Object> uf = new 
            TableColumn<>("Sigla");
        uf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        cols.add(uf);
        
        return cols;
        
        
    }

    @Override
    public ArrayList<Object> pesquisar(String param) {
        ArrayList<Object> lista = new ArrayList<>();
        
        Estado pr = new Estado();
        pr.setId(1);
        pr.setNome("PARANÁ");
        pr.setUf("PR");
        lista.add(pr);
        
        Estado ms = new Estado();
        ms.setId(2);
        ms.setNome("MATO GROSSO DO SUL");
        ms.setUf("MS");
        lista.add(ms);
        
        return lista;        
    }
    
    
    
}
