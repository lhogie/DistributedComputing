package distributed_computing;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.InetAddress;

public class Peer implements Externalizable {
    int listening_port = Agent.DEFAULT_PORT;
    InetAddress ipAddress;
    String nickname;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ipAddress);
        out.writeInt(listening_port);
        out.writeUTF(nickname);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.ipAddress = (InetAddress) in.readObject();
        this.listening_port = in.readInt();
        this.nickname = in.readUTF();
    }

    @Override
    public String toString() {
        return nickname;
    }
}
