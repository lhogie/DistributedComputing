package distributed_computing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable {

    private final Agent agent;
    Set<Long> alreadyReceivedMessages = new HashSet<>();

    public Server(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void run()  {
        final var buf = new byte[65535];
        final var p = new DatagramPacket(buf, buf.length);

        while (true) {
            try {
                agent.socket.receive(p);
                var actualData = Arrays.copyOf(buf, p.getLength()); // extract datagram data
                Message newMsg = Message.decodeUsingJavaSerialization(actualData);

                agent.peers.addAll(newMsg.route);

                if (newMsg.version != Message.version) {
                    System.err.println("warning: " + newMsg.route.get(0) + " has version " + newMsg.version + ", while our version is " + Message.version);
                }
                else{
                    if (! alreadyReceivedMessages.contains(newMsg.ID)) {
                        alreadyReceivedMessages.add(newMsg.ID);

                        for (var relay : newMsg.route) {
                            synchronized (agent.peers){
                                agent.peers.add(relay);
                            }
                        }

                        agent.client.broadcast(newMsg);
                        System.out.println(newMsg);
                    }
                    else{
//                        System.err.println("dropping message " + newMsg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
