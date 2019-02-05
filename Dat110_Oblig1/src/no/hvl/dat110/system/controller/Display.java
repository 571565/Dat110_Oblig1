package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

    private byte RPCID = 1;

    public void write(String message) {
        // TODO
        // implement marshalling, call and unmarshalling for write RPC method

        //Marshall message with rpcid
        byte[] byteMessage = RPCUtils.marshallString(RPCID, message);

        //Get response and unmarshall it to readable text
        byte[] reply = rmiclient.call(byteMessage);
        String replyUnmarshalled = RPCUtils.unmarshallString(reply);

        System.out.println("CONTROLLER:" + replyUnmarshalled);
    }
}
