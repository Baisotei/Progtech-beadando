package com.mycompany.progtech;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.EredmenyDAO;
import model.EredmenyDAOFactory;
import model.Lep;
import model.Tabla;

public class FXMLController implements Initializable {

    private Tabla sakk;
    private int lepesszamlalo = 0;

    private int sorkimutato(String nev) {
        return nev.charAt(4) - 48;
    }

    private int oszlopkimutato(String nev) {
        return nev.charAt(5) - 48;
    }

    private void frissites(Tabla akt) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (akt.getSajat()[i][j] == 1) {
                    //  tabla.get(i).get(j).setStyle("-fx-background-image: url(fekete.png)");
                    Image im = new Image(getClass().getResource("/kepek/feher.png").toExternalForm());
                    //tabla.get(i).get(j).setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                     ImageView iv = new ImageView();
                     iv.setImage(im);
                     tabla.get(i).get(j).getChildren().add(iv);
                   
                }
                if (akt.getSajat()[i][j] == 2) {
                      Image im = new Image(getClass().getResource("/kepek/fekete.png").toExternalForm());
                    //tabla.get(i).get(j).setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                     ImageView iv = new ImageView();
                     iv.setImage(im);
                     tabla.get(i).get(j).getChildren().add(iv);
                }
                if (akt.getSajat()[i][j] == 9) {
                    tabla.get(i).get(j).setStyle("-fx-background-color: red");
                }
            }
        }
    }

    private EredmenyDAOFactory daoFactory;

    @FXML
    private List<List<StackPane>> tabla;

    /*
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    tabla.get(i).get(j).setStyle("-fx-background-color: white");
                }
                if (i % 2 != 0 && j % 2 != 0) {
                    tabla.get(i).get(j).setStyle("-fx-background-color: white");
                }
                if (i % 2 == 0 && j % 2 != 0) {
                    tabla.get(i).get(j).setStyle("-fx-background-color: black");
                }
                if (i % 2 != 0 && j % 2 == 0) {
                    tabla.get(i).get(j).setStyle("-fx-background-color: black");
                }
            }
        }

        daoFactory = EredmenyDAOFactory.getInstance();
        sakk = new Tabla();
        EredmenyDAO ed = daoFactory.createEredmenyDAO();
        ed.createEredmeny("Béla");

    }

    @FXML
    private void kethuszar(MouseEvent event) {

        StackPane p = (StackPane) event.getSource();

        if (lepesszamlalo == 0) {
            sakk.felvisz1(sorkimutato(p.getId()), oszlopkimutato(p.getId()));
            lepesszamlalo++;
        } else if (lepesszamlalo == 1) {
            if (sakk.felvisz2(sorkimutato(p.getId()), oszlopkimutato(p.getId()))) {
                lepesszamlalo++;
            }

        } else if (lepesszamlalo % 2 == 0) {

            Lep x = new Lep(sakk.getA(), sakk.getB(), sorkimutato(p.getId()), oszlopkimutato(p.getId()), sakk.getSajat());

            if (x.lepes()) {
                sakk.felvisz(sorkimutato(p.getId()), oszlopkimutato(p.getId()), 0);
                lepesszamlalo++;
            }

            /* else  if(x.vanemeglepes()==false){System.out.println("A masodik jatekos nyert!");}*/
        } else {
            Lep x = new Lep(sakk.getC(), sakk.getD(), sorkimutato(p.getId()), oszlopkimutato(p.getId()), sakk.getSajat());
            if (x.lepes()) {
                sakk.felvisz(sorkimutato(p.getId()), oszlopkimutato(p.getId()), 1);
                lepesszamlalo++;
            }
            /*else if(x.vanemeglepes()==false){System.out.println("A elso jatekos nyert!");}*/
        }
        if (lepesszamlalo % 2 == 0) {
            Lep x = new Lep(sakk.getC(), sakk.getD(), sorkimutato(p.getId()), oszlopkimutato(p.getId()), sakk.getSajat());
            if (x.vanemeglepes() == false) {
                //    System.out.println("A elso jatekos nyert!");
                try {
                    Stage stage;
                    Parent root;

                    stage = (Stage) tabla.get(0).get(0).getScene().getWindow();
                    FXMLLoader fl1 = new FXMLLoader(getClass().getResource("/fxml/nyert.fxml"));
                    root = fl1.load();
                    fl1.<NyertController>getController().kinyert("Az első játékos nyert!");
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(NyertController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Lep x = new Lep(sakk.getA(), sakk.getB(), sorkimutato(p.getId()), oszlopkimutato(p.getId()), sakk.getSajat());
            if (x.vanemeglepes() == false) {

                //System.out.println("A masodik jatekos nyert!");
                try {
                    Stage stage;
                    Parent root;

                    stage = (Stage) tabla.get(0).get(0).getScene().getWindow();
                    FXMLLoader fl1 = new FXMLLoader(getClass().getResource("/fxml/nyert.fxml"));
                    root = fl1.load();
                    fl1.<NyertController>getController().kinyert("A második játékos nyert!");
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(NyertController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        frissites(sakk);

    }
}
