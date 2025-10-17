package distributed_computing;

import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args)
        throws Exception {
        var agent = new Agent();

        for (var line : Files.readAllLines(Path.of("peers.txt"))) {
            var p = new Peer();
            p.ipAddress = InetAddress.getByName(line);
            agent.peers.add(p);
        }


        for (;;){
            var s = System.console().readLine();
            agent.offer(s);
        }
    }
}
