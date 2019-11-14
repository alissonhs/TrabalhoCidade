/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.contracts;

import javafx.scene.Parent;

/**
 *
 * @author drink
 */
public interface FormControllerInterface {
    /*
    Retorna o layout do formulário
    */
    public Parent getLayout();
    /*
    Atribui o modelo ao formulário
    */
    public void setModel(Object model);
    /*
    retorna o modelo do formulário
    */
    public Object getModel();
    
    public void inicializar();
    
}
