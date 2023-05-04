package br.com.clusterlab.service;

import lombok.Data;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Data
public class Message {
    private String server_bind;

    public String SendRequest(String payload)
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect(this.getServer_bind());
            socket.send(payload.getBytes(ZMQ.CHARSET),0);
            byte[] reply = socket.recv(0);
            String payback = new String(reply,ZMQ.CHARSET);
            System.out.println("Received " + payback);
            return payback;
        }
    }
}
