package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;


public class UDPClient {
    String ipdes;
    int desPort;
    String ipSrc;
    int srcPort;
    DatagramSocket clientSocket;
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];

    public UDPClient(String ipSrc,int srcPort){
        try
        {
        this.ipSrc=ipSrc;
        this.srcPort=srcPort;
        this.clientSocket = new DatagramSocket(null);
        InetSocketAddress address = new InetSocketAddress(this.ipSrc, this.srcPort);
        this.clientSocket.bind(address);

        }
        catch (Exception rr)
        {

        }


    }

    public  int login(String ip_dest,int port_dest){
        try {
            this.ipdes=ip_dest;
            this.desPort=port_dest;
            InetAddress IPAddress = InetAddress.getByName(ipdes);
            String sentence = "i am ready";
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,desPort);
            clientSocket.send(sendPacket);
           // sendData=new byte[1028];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String rcv = new String(receivePacket.getData());

            System.out.println(rcv);
            if (rcv.contains("ok i am ready")){
                return 1;
            }
          return -1;
        }
        catch (Exception e){
            return -1;
        }
        //return 1;
    }

    public  void disconnect(){

        this.clientSocket.close();


    }

    public static void main(String args[])  {


        while (true) {
          //try {
              /*BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress IPAddress = InetAddress.getByName("192.168.0.109");

              byte[] sendData = new byte[1024];
              byte[] receiveData = new byte[1024];
              String sentence = inFromUser.readLine();
              sendData = sentence.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9393);
              clientSocket.send(sendPacket);
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String modifiedSentence = new String(receivePacket.getData());
              System.out.println("F:" + modifiedSentence);
          }
          catch (Exception e ){

          }*/
              //int x =login("192.168.0.109",9393,"192.168.0.102",9000);

        }
    }

}
