package distributed_computing;

import java.util.List;

public class ChatService extends Service {
    public ChatService(){
        super("chat");
    }

    @Override
    public void run(List<String> parameters) {
        for (var s : parameters){
            System.out.println(s);
        }
    }
}
