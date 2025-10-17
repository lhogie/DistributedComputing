package distributed_computing;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

public class Agent {
    final DatagramSocket socket;
    final Set<Peer> peers = new HashSet<Peer>();
    final Client client = new Client(this);
    final Server server = new Server();

    public final static int DEFAULT_PORT = 5674;

    Agent() throws SocketException {
        this.socket = new DatagramSocket(DEFAULT_PORT);
    }

    public void offer(String s) {
        client.broadcast(s);
    }
}
