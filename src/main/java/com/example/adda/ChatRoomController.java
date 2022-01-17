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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static com.example.adda.OpenPageController.*;


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
 public Label online;
 @FXML
 public Button chatBtn;
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


 String Name1,Password1,email1,phone1,gender1;
 public void info()
 {
  try {
   PreparedStatement pst;
   ResultSet rs;
   String username = userName;
   String query = "SELECT `a_name`, `a_password`, `a_email`, `a_phn`, `a_gender` FROM `adda` WHERE a_userName =?";
   pst = MyConnection.getConnection().prepareStatement(query);
   pst.setString(1, username);
   rs = pst.executeQuery();

   if(rs.next()==true)
   {
    this.Name1 = rs.getString(1);
    this.Password1 = rs.getString(2);
    this.email1 = rs.getString(3);
    this. phone1 = rs.getString(3);
    this. gender1 = rs.getString(3);



   }
   else
   {
    System.out.println("delete info plz");

   }
  }
  catch (SQLException ex)
  {
   ex.printStackTrace();
  }


 }



 @FXML
 private void handleSendEvent(MouseEvent event) {
  send();
  /*for (User user : users) {
   System.out.println(user.name);
  }
*/

 }

 public void send() {
  String msg = msgField.getText();
  writer.println(OpenPageController.userName  +" : " + msg);
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

    info();

    fullName.setText(Name1);
    fullName.setOpacity(1);
    email.setText(email1);
    email.setOpacity(1);
    phoneNo.setText(phone1);
    gender.setText(gender1);

 }


 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
  info();
  showProPic.setStroke(Color.valueOf("#90a4ae"));
  Image image;
  if (gender1.equalsIgnoreCase("Male")) {
   image = new Image(new File("src/main/resources/com/example/icons/user.png").toURI().toString(), false);
  } else {
   image = new Image(new File("src/main/resources/com/example/icons/female.png").toURI().toString(), false);
   proImage.setImage(image);
  }
  showProPic.setFill(new ImagePattern(image));
  clientName.setText(String.valueOf(userName));
  connectSocket();
 }


 private void connectSocket() {
  try {
   socket = new Socket("127.0.0.1", 44444);
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


 @Override
 public void run() {
  try {
   while (true) {
    String msg = reader.readLine();
    String[] tokens = msg.split(" ");
    String cmd = tokens[0];
    System.out.println(cmd);
    StringBuilder fulmsg = new StringBuilder();
    for(int i = 1; i < tokens.length; i++) {
     fulmsg.append(tokens[i]);
    }
    System.out.println(fulmsg);
    if (cmd.equalsIgnoreCase(OpenPageController.userName + ":")) {
     continue;
    } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
     break;
    }
    msgRoom.appendText(msg + "\n");
   }
   reader.close();
   writer.close();
   socket.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}



