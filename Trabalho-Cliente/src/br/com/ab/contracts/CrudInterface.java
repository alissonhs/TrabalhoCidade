package br.com.ab.contracts;

import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.DaoInterface;
import javafx.scene.Parent;

/**
 *
 * @author drink
 */
public interface CrudInterface {
    //Configurar a tela de crud
    public void configurar(
            DaoInterface dao,
            TelaDePesquisaController pc,
            FormControllerInterface fc,
            String titulo
    );
    //retornar o layout do crud
    public Parent getLayout();
}
