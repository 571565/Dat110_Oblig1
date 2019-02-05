package no.hvl.dat110.messaging.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageConfig;
import no.hvl.dat110.messaging.MessagingClient;
import no.hvl.dat110.messaging.MessagingServer;

public class TestMessaging {

    @Test
    public void test() {

        byte[] clientsent = {1, 2, 3, 4, 5};

        Thread server = new Thread(() -> {

            System.out.println("Messaging server - start");

            MessagingServer server1 = new MessagingServer(MessageConfig.MESSAGINGPORT);

            Connection connection = server1.accept();

            Message request = connection.receive();

            byte[] serverreceived = request.getData();

            Message reply = new Message(serverreceived);
            connection.send(reply);
            connection.close();
            server1.stop();

            System.out.println("Messaging server - stop");
            assertArrayEquals(clientsent, serverreceived);


        });

        Thread client = new Thread(() -> {

            System.out.println("Messaging client - start");

            MessagingClient client1 = new MessagingClient(MessageConfig.MESSAGINGHOST, MessageConfig.MESSAGINGPORT);
            Connection connection = client1.connect();

            Message message1 = new Message(clientsent);
            connection.send(message1);

            Message message2 = connection.receive();
            byte[] clientreceived = message2.getData();
            connection.close();

            System.out.println("Messaging client - stop");
            assertArrayEquals(clientsent, clientreceived);
        });

        server.start();
        client.start();

        try {
            server.join();
            client.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
