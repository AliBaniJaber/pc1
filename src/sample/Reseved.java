package sample;

//import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Reseved extends Thread{
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];
    UDPClient udp_client ;
    javafx.scene.control.TextArea chat;



    public Reseved(UDPClient udp_client, javafx.scene.control.TextArea chat) {
        try
        {
            this.udp_client=udp_client;
            this.chat=chat;
        }
        catch (Exception p )
        {


        }

    }

    @Override
    public void run() {
      //  chat.setText("i am ready"+"\n");
        while (true)
        {
            try {

                DatagramPacket res = new DatagramPacket(receiveData, receiveData.length);
                udp_client.clientSocket.receive(res);
                String msg=new String(res.getData(),0,res.getLength());
                if(msg.contains("i am ready")){
                    InetAddress IPAddress = InetAddress.getByName(udp_client.ipdes);
                    String sentence ="ok i am ready";

                    sendData = sentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sentence.length(), IPAddress,udp_client.desPort);
                    udp_client.clientSocket.send(sendPacket);

                }
                String all =chat.getText();
                all=all+"\n"+msg;
                chat.setText(all);
                //chat.append(all);


            }
            catch (Exception r){

            }


        }
    }
}
