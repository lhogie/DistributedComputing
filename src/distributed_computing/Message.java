package distributed_computing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message implements Externalizable {
    String content;
    String sender;
    long ID = rand.nextLong();
    static int version = 1;

    List<Peer> route = new ArrayList<>();

    static Random rand = new Random();

    public byte[] encodeUsingJavaSerialization() {
        var baos = new ByteArrayOutputStream();

        try {
            var oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message decodeUsingJavaSerialization(byte[] bytes) {
        try {
            return (Message) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
        }
        catch (IOException | ClassNotFoundException e){
           throw new IllegalStateException(e);
        }
    }

    @Override
    public String toString() {
        return sender + " says: "+ content + "\nroute: " + route;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(ID);
        out.writeUTF(content);
        out.writeUTF(sender);
        out.writeInt(version);
        out.writeObject(route);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.ID = in.readLong();
        this.content = in.readUTF();
        this.sender = in.readUTF();
        this.version = in.readInt();
        this.route = (List<Peer>) in.readObject();
    }
}
