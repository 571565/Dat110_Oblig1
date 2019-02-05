package no.hvl.dat110.messaging;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class Message {

    private byte[] payload;

    public Message(byte[] payload) {

        if (payload.length >= MessageConfig.SEGMENTSIZE) {
            this.payload = new byte[127];
            System.arraycopy(payload, 0, this.payload, 0, this.payload.length);
        } else {
            this.payload = payload;
        }
    }

    public Message() {
        super();
    }

    public byte[] getData() {
        return this.payload;
    }

    public byte[] encapsulate() {

        //Create an byte array with the maximum size
        byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
        int size = payload.length;

        //Set the first byte as the length of the whole message
        encoded[0] = (byte) size;

        //Copy array from payload into encoded array
        System.arraycopy(payload, 0, encoded, 1, size);

        return encoded;
    }

    public void decapsulate(byte[] received) {

        //Find the length of the data recieved
        int length = (int) received[0];

        //Create an empty decoded array with length of the first
        byte[] decoded = new byte[length];

        //Copy the array from recieved into the decoded one
        System.arraycopy(received, 1, decoded, 0, length);

        this.payload = decoded;
    }
}
