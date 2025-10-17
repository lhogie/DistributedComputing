package distributed_computing;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

public class Agent {
    final DatagramSocket socket;
    final Set<Peer> peers = new HashSet<Peer>();
    final Client client = new Client(this);
    final Server server = new Server(this);

    public final static int DEFAULT_PORT = 5674;

    Agent() throws IOException {
        this.socket = new DatagramSocket(DEFAULT_PORT);

        var serverThread = new Thread(server);
        serverThread.start();
    }



    public void offer(String s) {
        client.broadcast(s);
    }
}
