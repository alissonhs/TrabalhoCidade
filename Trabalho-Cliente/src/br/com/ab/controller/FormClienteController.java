/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.controller;

import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.model.Cidade;
import br.com.ab.model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author alissonhs
 */
public class FormClienteController implements Initializable, LookUpControllerInterface {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

     @FXML
    private AnchorPane apcliente;

    @FXML
    private Button btnLookUp;

    @FXML
    private TextField edtnome;
    
    @FXML
    private TextField edtCidade;

    private LookUPController lkp;
   
    private BooleanProperty hasActiveLookUp;
    
    private Cliente model;

    @FXML
    void buscarEstado(ActionEvent event) {
        StackPane parent =  (StackPane) apcliente.getParent();
        parent.getChildren().add(lkp.getLayout());
        lkp.inicializar();
        this.hasActiveLookUp.setValue(Boolean.TRUE);
      
        Task<Object> lookup = new Task() {
            @Override
            protected Object call() throws Exception {
                while(!lkp.isCloseRequested()){
                    Thread.sleep(500);
                }
                return lkp.getModel();
            }
        };
        lookup.setOnSucceeded((evt)->{
            if (lookup.getValue()!=null){
                  model.setCidade((Cidade)lookup.getValue());
                  this.setModel(this.getModel());
            }
        this.hasActiveLookUp.setValue(Boolean.FALSE);
        parent.getChildren().remove(lkp.getLayout());
        });
        
        new Thread(lookup).start();
    }
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.hasActiveLookUp = new SimpleBooleanProperty(false);
    }    
     

    @Override
    public Parent getLayout() {
        return this.apcliente;
    }

    @Override
    public void setModel(Object model) {
       this.model = (Cliente) model;
        edtnome.setText(this.model.getNome());
        if (this.model.getCidade() != null)
        edtCidade.setText(this.model.getCidade().getNome());
    }

    @Override
    public Object getModel() {
       this.model.setNome(edtnome.getText());
        return this.model;
    }

    @Override
    public void inicializar() {
        this.setModel(new Cliente());
    }

    @Override
    public void setLookUp(LookUpControllerInterface lkp) {
        this.lkp = (LookUPController) lkp;
    }

    @Override
    public BooleanProperty hasActiveLookUp() {
       return this.hasActiveLookUp; 
    }
    
}
