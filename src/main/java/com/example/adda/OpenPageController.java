package com.example.adda;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OpenPageController {
    @FXML
    public Pane pnSignUp;

    @FXML
    public Pane pnSignIn;
    @FXML
    public Button btnSignUp;

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




    //image back btn Handel...
    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        regName.setText("");
        regPass.setText("");
        regEmail.setText("");
    }



    // SIgnIn and SignUP button handel....
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnSignUp) {
            new FadeTransition().play();
            pnSignUp.toFront();
        }
        if (event.getSource().equals(getStarted)) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        loginNotifier.setOpacity(0);
        userName.setText("");
        passWord.setText("");

    }


//registration part...

    public  void registration(ActionEvent evt)
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
                    String name, userName, password ,email, phn , gender;
                    //User newUser = new User();
                    name = regFirstName.getText();
                    userName = regName.getText();
                    password = String.valueOf(regPass.getText());
                    email = regEmail.getText();
                    phn = regPhoneNo.getText();

                    if(male.isSelected())
                    {
                        gender= "Male";
                    }
                    else
                    {
                        gender= "Female";
                    }
                    //users.add(newUser);

                    PreparedStatement pst;
                    String query = "INSERT INTO `adda`( `a_name`, `a_userName`, `a_password`, `a_email`, `a_phn`,`a_gender`) VALUES (?,?,?,?,?,?)";
                    try{
                        pst = MyConnection.getConnection().prepareStatement(query);
                        pst.setString(1, name);
                        pst.setString(2, userName);
                        pst.setString(3, password);
                        pst.setString(4, email);
                        pst.setString(5, phn);
                        pst.setString(6,gender);
                        pst.executeUpdate();


                        if(pst.executeUpdate() > 0)
                        {
                            System.out.println("Add New User");
                        }
                    }
                    catch (SQLException e) {
                        Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    goBack.setOpacity(1);
                    success.setOpacity(1);
                    makeDefault();
                    if(controlRegLabel.getOpacity() == 1)
                    {
                        controlRegLabel.setOpacity(0);
                    }
                    if(nameExists.getOpacity() == 1)
                    {
                        nameExists.setOpacity(0);
                    }

                }
                else
                {
                    checkEmail.setOpacity(1);
                    setOpacity(nameExists, goBack, controlRegLabel, success);
                }
            }
            else {
                nameExists.setOpacity(1);
                setOpacity(success, goBack, controlRegLabel, checkEmail);
            }

        }
        else {
            controlRegLabel.setOpacity(1);
            setOpacity(success, goBack, nameExists, checkEmail);
        }

    }

    private void setOpacity(Label a, Label b, Label c, Label d) {
        if(a.getOpacity() == 1 || b.getOpacity() == 1 || c.getOpacity() == 1 || d.getOpacity() == 1) {
            a.setOpacity(0);
            b.setOpacity(0);
            c.setOpacity(0);
            d.setOpacity(0);
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
        setOpacity(controlRegLabel, checkEmail, nameExists);
    }

    //User-name check
    public boolean checkUser(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = true;
        String query = "SELECT * FROM `adda` WHERE `a_userName` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if(rs.next())
            {
                checkUser = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkUser;
    }

   //Check Email...

    public boolean checkEmail(String email)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkEmail = true;
        String query = "SELECT * FROM `adda` WHERE `a_email` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if(rs.next())
            {
                checkEmail = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkEmail;
    }



    public void loginAction(ActionEvent ae) {
        ResultSet rs;

        PreparedStatement ps;

        String uname = userName.getText();
        String pass = passWord.getText();

        String query = "SELECT * FROM `adda` WHERE `a_userName` =? AND `a_password` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);

            ps.setString(1, uname);
            ps.setString(2, pass);

            rs = ps.executeQuery();

            if(rs.next())
            {
                changeWindow();
            }
            else{
                loginNotifier.setOpacity(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpenPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeWindow() {
        try {
            String uname =userName.getText();
            Stage stage = (Stage) userName.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("chatRoom.fxml")));
            stage.setScene(new Scene(root, 330, 560));
            Image image = new Image(new File("src/main/resources/com/example/icons/addaLogo.png").toURI().toString());
            stage.getIcons().add(image);

            stage.setTitle(uname + "");
            stage.setOnCloseRequest(event -> System.exit(0));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



