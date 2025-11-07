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

        if (args.length < 2) {
            System.err.println("Missing arguments NICKNAME IP_ADDRESS");
            return;
        }

        agent.nickname = args[0];
        agent.ipAddress = InetAddress.getByName(args[1]);
        System.out.println("My IP address is: " + agent.ipAddress);
        agent.processUserInputFromStdIn();
    }
}
