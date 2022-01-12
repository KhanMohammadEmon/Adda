package com.example.adda;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.adda.OpenPageController.users;

public class ChatRoomController extends Thread implements Initializable {

 @FXML
 public TextArea msgRoom;
 @FXML
 public Pane chat;
 @FXML
 public TextField msgField;
 @FXML
 public Label clientName;
 @FXML
 public Button profileBtn;
 @FXML
 public Circle showProPic;
 @FXML
 public ImageView proImage;
 @FXML
 public Label fullName;
 @FXML
 public Label email;
 @FXML
 public Label phoneNo;
 @FXML
 public Label gender;
 @FXML
 public Pane profile;
 @FXML
 public TextField fileChoosePath;

 private FileChooser fileChooser;
 private File filePath;
 public boolean toggleChat = false, toggleProfile = false;

 BufferedReader reader;
 PrintWriter writer = null;
 Socket socket;


 @FXML
 private void handleSendEvent(MouseEvent event) {
  send();
  for (User user : users) {
   System.out.println(user.name);
  }

 }

 public void send() {
  String msg = msgField.getText();
  writer.println(OpenPageController.username + ": " + msg);
  msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
  msgRoom.appendText("Me: " + msg + "\n");
  msgField.setText("");
  if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
   System.exit(0);
  }
 }

 @FXML
 private void sendMessageByKey(KeyEvent event) {
  if (event.getCode().toString().equals("ENTER")) {
   send();
  }
 }

 @FXML
 private void handleProfileBtn(ActionEvent event) {

  if (event.getSource().equals(profileBtn) && !toggleProfile) {
   new FadeTransition().play();
   profile.toFront();
   chat.toBack();
   toggleProfile = true;
   toggleChat = false;
   profileBtn.setText("Back");
   setProfile();
  } else if (event.getSource().equals(profileBtn) && toggleProfile) {
   new FadeTransition().play();
   chat.toFront();
   toggleProfile = false;
   toggleChat = false;
   profileBtn.setText("Profile");
  }

 }

 private void setProfile() {
  for (User user : users) {
   if (OpenPageController.username.equalsIgnoreCase(user.name)) {
    fullName.setText(user.fullName);
    fullName.setOpacity(1);
    email.setText(user.email);
    email.setOpacity(1);
    phoneNo.setText(user.phoneNo);
    gender.setText(user.gender);
   }
  }
 }


 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
  showProPic.setStroke(Color.valueOf("#90a4ae"));
  Image image;
  if (OpenPageController.gender.equalsIgnoreCase("Male")) {
   image = new Image(new File("src/main/resources/com/example/icons/user.png").toURI().toString(), false);
  } else {
   image = new Image(new File("src/main/resources/com/example/icons/female.png").toURI().toString(), false);
   proImage.setImage(image);
  }
  showProPic.setFill(new ImagePattern(image));
  clientName.setText(OpenPageController.username);
  connectSocket();
 }


 private void connectSocket() {
  try {
   socket = new Socket("127.0.0.1", 4444);
   System.out.println("Socket is connected with server!");
   reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   writer = new PrintWriter(socket.getOutputStream(), true);
   this.start();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }


 public boolean saveControl = false;


 @FXML
 public void chooseImageButton(ActionEvent event) {
  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
  fileChooser = new FileChooser();
  fileChooser.setTitle("Open Image");
  this.filePath = fileChooser.showOpenDialog(stage);
  fileChoosePath.setText(filePath.getPath());
  saveControl = true;
 }


 @FXML
 public void saveImage() {
  if (saveControl) {
   //try {
    /*BufferedImage bufferedImage = ImageIO.read(filePath);
    Image image = SwingFXUtils.toFXImage(bufferedImage, null);*/

    Image image = new Image(new File(String.valueOf(filePath)).toURI().toString());
    proImage.setImage(image);
    showProPic.setFill(new ImagePattern(image));
    saveControl = false;
    fileChoosePath.setText("");
   //} catch (IOException e) {
    //System.err.println(e.getMessage());
  // }
  }

 }
}



