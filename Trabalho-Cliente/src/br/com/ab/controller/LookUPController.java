/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.controller;

import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.dao.TableModelInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author alissonhs
 */
public class LookUPController implements Initializable, LookUpControllerInterface {

    @FXML
    private AnchorPane apLookUp;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSelecionar;

    @FXML
    private TableView<Object> tblDados;

    @FXML
    private TextField txtPesquisar;
    
    private TableModelInterface tm;
    private Object selecionado;
    private boolean closeRequested;


    @FXML
    void cancelar(ActionEvent event) {
        this.selecionado = null;   
        this.closeRequested = true;
    }

    @FXML
    void pesquisarClicked(ActionEvent event) {
        this.tblDados.getItems().clear();
        this.tblDados.getItems().addAll(tm.pesquisar(txtPesquisar.getText()));
    }

    @FXML
    void selecionar(ActionEvent event) {
        this.selecionado = tblDados.getSelectionModel().getSelectedItem();
        this.closeRequested = true;
    }

    public boolean isCloseRequested() {
        return closeRequested;
    }
    
    public void configurar(TableModelInterface tm,String titulo){
        this.tm = tm;
        
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @Override
    public Parent getLayout() {
        return this.apLookUp;
    }

    @Override
    public void setModel(Object model) {
      
    }

    @Override
    public Object getModel() {
        return selecionado;
    }

    @Override
    public void inicializar() {
        this.closeRequested = false;
        this.btnSelecionar.setDisable(true);
        this.tblDados.getItems().clear();
        this.tblDados.getColumns().clear();
        this.tblDados.getColumns().addAll(tm.getCols());
        this.tblDados.getSelectionModel().selectedIndexProperty().addListener((observable, oldvalue, newValue)->{
            if(newValue!=null){
                this.btnSelecionar.setDisable(false);
            }else{
                this.btnSelecionar.setDisable(true);
            }
        });
               
                
    }

    @Override
    public void setLookUp(LookUpControllerInterface lkp) {
        
    }

    @Override
    public BooleanProperty hasActiveLookUp() {
        return null;    
    }
    
}
