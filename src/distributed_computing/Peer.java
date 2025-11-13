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
    int version;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ipAddress);
        out.writeInt(listening_port);
        out.writeUTF(nickname);
        out.writeInt(version);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.ipAddress = (InetAddress) in.readObject();
        this.listening_port = in.readInt();
        this.nickname = in.readUTF();
        this.version = in.readInt();
    }

    @Override
    public String toString() {
        return nickname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Peer peer = (Peer) obj;
        return ipAddress != null && ipAddress.equals(peer.ipAddress);
    }

    @Override
    public int hashCode() {
        return ipAddress != null ? ipAddress.hashCode() : 0;
    }
}
