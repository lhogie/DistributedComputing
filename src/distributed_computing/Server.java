package distributed_computing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Arrays;

public class Server implements Runnable {

    private final Agent agent;

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
                var newMsg = Message.decodeUsingJavaSerialization(actualData);
                System.out.println(newMsg);
            } catch (IOException e) {
                System.err.println("error while listening: " + e.getMessage());
            }
        }
    }
}
