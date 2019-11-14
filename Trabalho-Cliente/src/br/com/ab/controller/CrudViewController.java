/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.controller;

import br.com.ab.contracts.CrudInterface;
import br.com.ab.contracts.FormControllerInterface;
import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.dao.DaoInterface;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;  
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author alissonhs
 */
public class CrudViewController implements Initializable,
        CrudInterface{

    @FXML
    private AnchorPane apCrud;
    @FXML
    private Label lblTitulo;
    @FXML
    private Insets x1;
    @FXML
    private Button btNovo;
    @FXML
    private Button btEditar;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btRemover;
    @FXML
    private StackPane spContainer;
    
    private DaoInterface dao;
    private TelaDePesquisaController pc;
    private FormControllerInterface fc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void novo(ActionEvent event) {
        this.spContainer.getChildren().clear();
        this.spContainer.getChildren().add(fc.getLayout());
        this.fc.inicializar();
        this.btNovo.setDisable(true);
        this.btEditar.setDisable(true);
        this.btnSalvar.setDisable(false);
        this.btCancelar.setDisable(false);
        this.btRemover.setDisable(true);
    }

    @FXML
    private void editar(ActionEvent event) {
        Object item = pc.getTblDados().getSelectionModel()
                                      .getSelectedItem();
        novo(event);
        fc.setModel(item);        
    }

    @FXML
    private void salvar(ActionEvent event) {
        dao.salvar(fc.getModel());
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setContentText("Salvo com Sucesso!");
        msg.showAndWait();
        inicializar();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        inicializar();
    }

    @FXML
    private void remover(ActionEvent event) {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION, 
                "Deseja Remover?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> resposta = msg.showAndWait();
        if (resposta.get().equals(ButtonType.YES)){
            Object item = pc.getTblDados().getSelectionModel()
                                      .getSelectedItem();
            dao.remover(item);
            Alert rem = new Alert(Alert.AlertType.INFORMATION);
            rem.setContentText("Removido com sucesso!");
            rem.showAndWait();
            inicializar();
        }
    }

    @Override
    public void configurar(DaoInterface dao,
            TelaDePesquisaController pc, 
            FormControllerInterface fc, String titulo) {
        
        this.lblTitulo.setText(titulo);
        this.dao = dao;
        this.pc = pc;
        this.fc = fc;
        
        if (fc instanceof LookUpControllerInterface){
            ((LookUpControllerInterface)fc).hasActiveLookUp().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    btCancelar.setDisable(true);
                    btnSalvar.setDisable(true);
                    
                }else{
                    btCancelar.setDisable(!true);
                    btnSalvar.setDisable(!true);
                }
                
            });
            
        }
        
          pc.getTblDados()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        //está selecionado
                        btEditar.setDisable(false);
                        btRemover.setDisable(false);
                    } else {
                        // não está selecionado
                        btEditar.setDisable(true);
                        btRemover.setDisable(!false);
                    }
                });
        
        this.inicializar();
    }

    @Override
    public Parent getLayout() {
        return this.apCrud;
    }

    private void inicializar() {
        this.spContainer.getChildren().clear();
        this.spContainer.getChildren().add(pc.getLayout());
      
                btNovo.setDisable(false);
                btEditar.setDisable(true);
                btnSalvar.setDisable(true);
                btCancelar.setDisable(true);
    }
    
}
