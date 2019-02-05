package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Connection {

    private DataOutputStream outStream; // for writing bytes to the TCP connection
    private DataInputStream inStream; // for reading bytes from the TCP connection
    private Socket socket; // socket for the underlying TCP connection

    public Connection(Socket socket) {

        try {
            this.socket = socket;
            outStream = new DataOutputStream(socket.getOutputStream());
            inStream = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            System.out.println("Connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void send(Message message) {

        //Create a byte array of the message by encapsulating it
        byte[] messageToSend = message.encapsulate();

        try {
            //Write it to the outputstream
            outStream.write(messageToSend);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message receive() {

        Message message = new Message();
        byte[] recvbuf = new byte[MessageConfig.SEGMENTSIZE];


        try {
            //Read that said number of bytes and add them to the recvbuffer
            for (int i = 0; i < MessageConfig.SEGMENTSIZE; i++) recvbuf[i] = inStream.readByte();

            //Create a message of this given data
            message.decapsulate(recvbuf);
        } catch (IOException e) {
            //e.printStackTrace();
        }

        //Return message from the recieved data
        return message;

    }

    // close the connection by closing streams and the underlying socket
    public void close() {

        try {
            outStream.close();
            inStream.close();

            socket.close();
        } catch (IOException ex) {

            System.out.println("Connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}