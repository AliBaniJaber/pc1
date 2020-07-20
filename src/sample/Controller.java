package sample;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
import javafx.application.Platform;

//import javax.print.attribute.standard.JobOriginatingUserName;
//import javax.swing.*;
//import org.controlsfx.control.textfield.AutoCompletionBinding;

//import org.controlsfx.control.textfield.TextFields;

public class Controller implements Initializable {
    @FXML
    Button login;
    @FXML
    Button send;
    DropShadow shadow = new DropShadow(10, Color.WHITE);
    @FXML
    TextField IP_SRC;
    @FXML
    TextField IP_DEST;
    @FXML
    TextField PORT_NUMBER_DEST;
    @FXML
    TextField PORT_NUMBER_SEC;
    @FXML
    TextField USERNAME;
    @FXML
    TextField dataToSend;
    //@FXML
    //ImageView image;
    UDPClient udp_client=null;

    public void desconect(){

       try{ udp_client.disconnect();}
       catch (Exception e) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("ERROR ");
           alert.setHeaderText("conection  ");
           alert.setContentText("not found conection");
           alert.showAndWait();
        }
       udp_client=null;
       login.setDisable(false);
        PORT_NUMBER_SEC.setDisable(false);
        IP_SRC.setDisable(false);
        IP_DEST.setDisable(false);
        PORT_NUMBER_DEST.setDisable(false);
        USERNAME.setDisable(false);
        desconect.setDisable(true);
        stop.setDisable(true);
        run.setDisable(false);
        login.setDisable(true);
        run.setDisable(false);
        stop.setDisable(true);
        login.setDisable(true);


    }
    @FXML
    Button  desconect;
    @FXML
    TextArea chat;
    @FXML
    Button run;
    @FXML
    Button stop;

    public void runchat()
    {


        try
        {


            if(udp_client==null)
            {
                udp_client=new UDPClient(IP_SRC.getText() , Integer.parseInt(PORT_NUMBER_SEC.getText()));
            }
           IP_SRC.setDisable(true);
           PORT_NUMBER_SEC.setDisable(true);
           USERNAME.setDisable(true);
           stop.setDisable(false);
           run.setDisable(true);
           login.setDisable(false);
            Reseved reseved=new Reseved(udp_client , chat);
            reseved.start();

        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR ");
            alert.setHeaderText("Wrong port number ");
            alert.setContentText("You must re-enter correctly");
            alert.showAndWait();
        }
    }
    public void stopchat()
    {
      udp_client.disconnect();
        PORT_NUMBER_SEC.setDisable(false);
        IP_SRC.setDisable(false);
        IP_DEST.setDisable(false);
        PORT_NUMBER_DEST.setDisable(false);
        USERNAME.setDisable(false);
        login.setDisable(false);
        stop.setDisable(true);
        run.setDisable(false);
        login.setDisable(true);
    }


    public void Actions()
    {
            String ip_src=IP_SRC.getText();
            String ip_des=IP_DEST.getText();
            int port_src=0;
            int port_dest=0;
          try
          {
              port_src = Integer.parseInt(PORT_NUMBER_SEC.getText());

              port_dest = Integer.parseInt(PORT_NUMBER_DEST.getText());
          }
          catch (Exception e)
          {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("ERROR ");
              alert.setHeaderText("Wrong port number ");
              alert.setContentText("You must re-enter correctly");
              alert.showAndWait();
          }
        int  resut = udp_client.login(ip_des,port_dest);
        if (resut==1){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ok ");
            alert.setHeaderText("ok ");
            alert.setContentText("ok");
            alert.showAndWait();
            PORT_NUMBER_SEC.setDisable(true);
            IP_SRC.setDisable(true);
            IP_DEST.setDisable(true);
            PORT_NUMBER_DEST.setDisable(true);
            USERNAME.setDisable(true);
            login.setDisable(true);
            desconect.setDisable(false);
            stop.setDisable(true);

        }
        else
        {
            ///
        }
    }
    @FXML
   public void sendMsg()
    {
        byte sendData[];//=new byte[1024];


      try
      {
          InetAddress IPAddress = InetAddress.getByName(IP_DEST.getText());
          String sentence = dataToSend.getText();
          sendData = sentence.getBytes();
          DatagramPacket sendPacket = new DatagramPacket(sendData, sentence.length(), IPAddress,Integer.parseInt( PORT_NUMBER_DEST.getText()));
          udp_client.clientSocket.send(sendPacket);
          String chat_msges=chat.getText();

          chat_msges=chat_msges+"\n"+USERNAME.getText() +" : "+sentence;
          chat.clear();
          chat.appendText(chat_msges);


      }
      catch (Exception e)
      {
         Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("ERROR ");
          alert.setHeaderText(" try ");
          alert.setContentText("connt send msg ");
          alert.showAndWait();

      }
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stop.setDisable(true);
        login.setDisable(true);
         /*   IP_DEST.setText("192.168.43.86");
            IP_SRC.setText("192.168.43.86");
            PORT_NUMBER_DEST.setText("8585");
            PORT_NUMBER_SEC.setText("7575");
            USERNAME.setText("aLI");*/

        login.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            //login.setEffect(shadow);
            login.setStyle("-fx-background-color: white;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-text-fill: #DB8400");

        });

        login.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            login.setEffect(null);
            login.setStyle("-fx-background-color: #DB8400;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-text-fill: white");





        });

        send.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            //login.setEffect(shadow);
            send.setStyle("-fx-background-color: white;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-text-fill: #DB8400");
        });

        send.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            send.setEffect(null);
            send.setStyle("-fx-background-color: #DB8400;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-text-fill: white");
        });
        //login.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));








    }
}


