package com.example.adda;

import javafx.animation.FadeTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class OpenPageController {
    @FXML
    public Pane pnSignUp;

    @FXML
    public Pane pnSignIn;
    @FXML
    public Button btnSignUp;
    @FXML
    public Button logInBtn;

    @FXML
    public ImageView btnBack;


    //image back btn Handel...
    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
    }

    @FXML
    private void registration(ActionEvent event) {

    }

    //sign Up Button Handel...

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnSignUp) {
            new FadeTransition().play();
            pnSignUp.toFront();
        }

    }

//logIn btn Handel....
    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        if (event.getSource() == logInBtn) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chatRoom.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Stage stage = new Stage();
            stage.setTitle("ADDA");
            stage.setScene(scene);
            stage.show();

        }
    }


    }
