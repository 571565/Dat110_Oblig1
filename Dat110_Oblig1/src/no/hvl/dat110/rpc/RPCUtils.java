package no.hvl.dat110.rpc;

import java.util.Arrays;

public class RPCUtils {

    public static byte[] marshallString(byte rpcid, String str) {

        // TODO: marshall RPC identifier and string into byte array

        //Convert the input string to a byte array
        byte[] strByte = str.getBytes();

        //Make the encoded array with the rpcdi and length of string
        byte[] encoded = new byte[strByte.length + 1];

        encoded[0] = rpcid;

        for (int i = 1; i < encoded.length; i++) encoded[i] = strByte[i - 1];


        return encoded;
    }

    public static String unmarshallString(byte[] data) {

        //Create a stringbuilder for appending to a string
        StringBuilder decoded = new StringBuilder();

        // TODO: unmarshall String contained in data into decoded

        //Add depent all the data from bytes into chars
        for (int i = 1; i < data.length; i++) decoded.append((char) data[i]);

        return decoded.toString();
    }

    public static byte[] marshallVoid(byte rpcid) {


        // TODO: marshall RPC identifier in case of void type

        byte[] encoded = new byte[1];
        encoded[0] = rpcid;

        return encoded;

    }

    public static void unmarshallVoid(byte[] data) {

        // TODO: unmarshall void type

        //This really does nothing
        if (data != null)
            for (int i = 1; i < data.length; i++) data[i - 1] = data[i];
    }

    public static byte[] marshallBoolean(byte rpcid, boolean b) {

        byte[] encoded = new byte[2];

        encoded[0] = rpcid;

        if (b) {
            encoded[1] = 1;
        } else {
            encoded[1] = 0;
        }

        return encoded;
    }

    public static boolean unmarshallBoolean(byte[] data) {

        return (data[1] > 0);

    }

    public static byte[] marshallInteger(byte rpcid, int x) {

        byte[] encoded;

        // TODO: marshall RPC identifier and string into byte array

        encoded = new byte[5];
        encoded[0] = rpcid;
        encoded[1] = (byte) ((x >> 24) & 0xff);
        encoded[2] = (byte) ((x >> 16) & 0xff);
        encoded[3] = (byte) ((x >> 8) & 0xff);
        encoded[4] = (byte) ((x) & 0xff);

        return encoded;
    }

    public static int unmarshallInteger(byte[] data) {

        System.out.println(Arrays.toString(data));

        return (data[1] << 24) & 0xff000000 |
                (data[2] << 16) & 0x00ff0000 |
                (data[3] << 8) & 0x0000ff00 |
                (data[4]) & 0x000000ff;

    }
}