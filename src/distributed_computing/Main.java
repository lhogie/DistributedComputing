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
        agent.processUserInputFromStdIn();
    }
}
