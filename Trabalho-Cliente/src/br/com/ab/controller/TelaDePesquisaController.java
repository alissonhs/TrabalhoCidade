package br.com.ab.controller;

import br.com.ab.dao.TableModelInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author alissonhs
 */
public class TelaDePesquisaController implements Initializable {

    @FXML
    private BorderPane pnPesquisa;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Object> tblDados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    // Esta variável guardará uma instância de 
    // TableModelInterface
    private TableModelInterface tm;

    public void configure(TableModelInterface tm) {
        this.tm = tm;
        this.start();
    }

    private void start() {
        this.tblDados.getItems().clear();
        this.tblDados.getColumns().clear();
        this.tblDados.getColumns().addAll(this.tm.getCols());
    }

    @FXML
    private void pesquisarClicked(ActionEvent event) {
        this.tblDados.getItems().clear();
        this.tblDados.getItems().addAll(
                FXCollections.observableArrayList(
                        this.tm.pesquisar(txtPesquisa.getText())
                )
        );
    }
    public Parent getLayout() {
        return this.pnPesquisa;
    }

    public TableView<Object> getTblDados() {
        return tblDados;
    }
    
}
