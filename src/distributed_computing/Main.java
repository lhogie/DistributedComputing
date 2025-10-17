package distributed_computing;

import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
        throws Exception {
        var agent = new Agent();

        var peerFile = Path.of("peers.txt");
        System.out.println("loading " + peerFile.toAbsolutePath());

        for (var line : Files.readAllLines(peerFile)) {
            var p = new Peer();
            p.ipAddress = InetAddress.getByName(line);
            agent.peers.add(p);
        }

        System.out.println(agent.peers.size() + " peer(s) found");


        Scanner input = new Scanner(System.in);

        for (;;){
            System.out.println("> ");

            while (input.hasNextLine()) {
                var s = input.nextLine();
                agent.client.broadcast(s);
            }
        }
    }


}
