package distributed_computing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {
    final Agent agent;

    public Client(Agent agent){
        this.agent = agent;
    }

    public void broadcast(Message msg) {
        addLocalPeerInformationToRoute(msg);

        for (var p : agent.peers) {
            try{
                sendMessage(msg, p);
            }
            catch (IOException err){
                System.err.println("cannot send to " + p);
            }
        }
    }

    private void send(Message msg, Peer recipient)
        throws IOException
    {
        addLocalPeerInformationToRoute(msg);
        sendMessage(msg, recipient);
    }

    private void sendMessage(Message msg, Peer recipient) throws IOException {
        var data = msg.encodeUsingJavaSerialization();
        var d = new DatagramPacket(data, data.length);
        d.setAddress(recipient.ipAddress);
        d.setPort(recipient.listening_port);
        agent.socket.send(d);
    }

    private void addLocalPeerInformationToRoute(Message msg) {
        msg.route.add(agent.localPeer());
    }
}
