/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progtech;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Eredmeny;
import model.EredmenyDAO;
import model.EredmenyDAOFactory;

/**
 * FXML Controller class
 *
 * @author Baisotei
 */
public class NyertController implements Initializable {

    @FXML
    private Label nyertes;

    @FXML
    private TextField nevbekero;

    @FXML
    private TextField adatbaziseredmeny;

    private EredmenyDAOFactory fact;
    
    @FXML
    private Button adatbazisparancs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fact = EredmenyDAOFactory.getInstance();
    }

    public void kinyert(String s) {
        nyertes.setText(s);
    }

    @FXML
    private void keresesvhozza(ActionEvent event) {
        EredmenyDAO ed = fact.createEredmenyDAO();

        if (ed.searchEredmeny(nevbekero.getText())) {
            Eredmeny e = ed.readEredmeny(nevbekero.getText());
            ed.updateEredmeny(e);
            adatbaziseredmeny.setText("Eddig " + e.getEredmeny()  + " alkalommal nyert! Gratulálok!");
        } else {
            ed.createEredmeny(nevbekero.getText());
            Eredmeny e = ed.readEredmeny(nevbekero.getText());
            ed.updateEredmeny(e);
            adatbaziseredmeny.setText("Eddig " + e.getEredmeny()  + " alkalommal nyert! Gratulálok!");
        }
        
        adatbazisparancs.setDisable(true);
    }
}
