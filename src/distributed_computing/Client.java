package distributed_computing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {
    final Agent agent;

    public Client(Agent agent){
        this.agent = agent;
    }

    public void broadcast(String s) {
        for (var p : agent.peers){
            try{
                send(s, p);
            }
            catch (IOException err){
                System.err.println("cannot send to " + p);
            }
        }
    }

    private void send(String s, Peer recipient)
        throws IOException
    {
        var data = s.getBytes();
        var d = new DatagramPacket(data, data.length);
        d.setAddress(recipient.ipAddress);
        d.setPort(recipient.listening_port);
        agent.socket.send(d);
    }
}
