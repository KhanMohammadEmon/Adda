package com.example.adda;

import javafx.animation.FadeTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


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

    @FXML
    public Button getStarted;

    @FXML
    public TextField regName;
    @FXML
    public TextField regPass;
    @FXML
    public TextField regEmail;
    @FXML
    public TextField regFirstName;
    @FXML
    public TextField regPhoneNo;
    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    public Label controlRegLabel;
    @FXML
    public Label success;
    @FXML
    public Label goBack;
    @FXML
    public TextField userName;
    @FXML
    public TextField passWord;
    @FXML
    public Label loginNotifier;
    @FXML
    public Label nameExists;
    @FXML
    public Label checkEmail;

    public static String username, password, gender;

    public  static ArrayList<User> users = new ArrayList<User>();
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

    public  void registration()
    {
        if(!regName.getText().equalsIgnoreCase("")
            && !regPass.getText().equalsIgnoreCase("")
                && !regEmail.getText().equalsIgnoreCase("")
                && !regFirstName.getText().equalsIgnoreCase("")
                && !regPhoneNo.getText().equalsIgnoreCase("")
                && (male.isSelected() || female.isSelected())

        )
        {
            if(checkUser(regName.getText()))
            {
                if(checkEmail(regEmail.getText()))
                {
                    User newUser = new User();
                    newUser.name = regName.getText();
                    newUser.password = regPass.getText();
                    newUser.email = regEmail.getText();
                    newUser.fullName = regFirstName.getText();
                    newUser.phoneNo = regPhoneNo.getText();

                    if(male.isSelected())
                    {
                        newUser.gender= "Male";
                    }
                    else
                    {
                        newUser.gender= "Female";
                    }
                    users.add(newUser);
                    goBack.setOpacity(1);
                    success.setOpacity(1);
                    makeDefault();
                }
            }
        }

    }




    private void setOpacity(Label controlRegLabel, Label checkEmail, Label nameExists) {
        controlRegLabel.setOpacity(0);
        checkEmail.setOpacity(0);
        nameExists.setOpacity(0);
    }

    private void makeDefault() {
        regName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFirstName.setText("");
        regPhoneNo.setText("");
        male.setSelected(true);
        setOpacity(controlRegLabel, checkEmail, nameExists); // need learn about setOpacity....
    }

    private boolean checkEmail(String email) {
        for(User user : users) {
            if(user.email.equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUser(String username)
    {
        for (User user: users) {
            if(user.email.equalsIgnoreCase(username))
            {
                return false;
            }

        }
        return true;
    }
}
