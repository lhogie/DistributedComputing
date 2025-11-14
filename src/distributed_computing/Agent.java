package distributed_computing;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Agent {
    final DatagramSocket socket;
    final Set<Peer> peers = new HashSet<Peer>();
    final Set<Service> services = new HashSet<>();
    final Client client = new Client(this);
    final Server server = new Server(this);
    public InetAddress ipAddress;
    String nickname;

    public final static int DEFAULT_PORT = 5674;

    Agent() throws IOException {
        this.socket = new DatagramSocket(DEFAULT_PORT);

        services.add(new ChatService());


        loadPeerFile();
        var serverThread = new Thread(server);
        serverThread.start();
    }

    public void loadPeerFile() throws IOException {
        var peerFile = Path.of("peers.txt");
        System.out.println("loading " + peerFile.toAbsolutePath());

        for (var line : Files.readAllLines(peerFile)) {
            var p = new Peer();
            p.ipAddress = InetAddress.getByName(line);
            peers.add(p);
        }

        System.out.println(peers.size() + " peer(s) found");
    }

    public  void processUserInputFromStdIn() {
        Scanner input = new Scanner(System.in);

        for (;;){
            System.out.println("> ");

            while (input.hasNextLine()) {
                var msg = new Message("chat", localPeer());
                msg.serviceParameters.add(input.nextLine());
                client.broadcast(msg);
            }
        }
    }

    public Peer localPeer() {
        var localPeerInformation = new Peer();
        localPeerInformation.listening_port = Agent.DEFAULT_PORT;
        localPeerInformation.ipAddress = ipAddress;
        localPeerInformation.nickname = nickname;
        localPeerInformation.version = Message.version;
        return localPeerInformation;
    }

    public Service findService(String serviceName) {
        for (var service : services){
            if (service.name.equals(serviceName)){
                return service;
            }
        }

        return null; // no service with that name could be found
    }
}
