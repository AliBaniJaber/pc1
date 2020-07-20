package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer {


    public static void main(String args[]) {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];


        while (true) {
            try {
                DatagramSocket serverSocket = new DatagramSocket(9876);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println(sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String sentenceE = inFromUser.readLine();
                sendData = sentenceE.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } catch (Exception e) {
            }
        }
    }
}
