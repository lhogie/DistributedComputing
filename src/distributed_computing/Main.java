package distributed_computing;

import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
        throws Exception {

        System.out.println("The version my JVM is: " + System.getProperty("java.version"));

        var agent = new Agent();

        agent.nickname = args.length == 0 ? "foobar" : args[0];
        agent.processUserInputFromStdIn();
    }
}
