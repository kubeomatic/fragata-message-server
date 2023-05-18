package br.com.clusterlab.service;

import lombok.Data;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Data
public class Message {
    private String server_bind;
//    static Logger logger = LoggerFactory.getLogger(Message.class);

    public String SendRequest(String payload)
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect(this.getServer_bind());
            socket.send(payload.getBytes(ZMQ.CHARSET),0);
            byte[] reply = socket.recv(0);
            String payback = new String(reply,ZMQ.CHARSET);
//            logger.info("Received " + payback);
//            System.out.println("Received " + payback);
            return payback;
        }
    }
}
