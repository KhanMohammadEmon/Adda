package com.example.adda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ChatRoomController {

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
private void handleSendEvent(MouseEvent event)
{

}

@FXML
private void sendMessageByKey(KeyEvent event)
{

}

@FXML
private void handleProfileBtn(ActionEvent event)
{

}


}
