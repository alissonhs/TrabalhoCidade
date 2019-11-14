/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.contracts;

import javafx.beans.property.BooleanProperty;

/**
 *
 * @author alissonhs
 */
public interface LookUpControllerInterface extends FormControllerInterface{
    public void setLookUp(LookUpControllerInterface lkp);
    
    public BooleanProperty hasActiveLookUp();
}
