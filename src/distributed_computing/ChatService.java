package distributed_computing;

import java.util.List;

public class ChatService implements Service {

    @Override
    public String getName() {
        return "chat";
    }

    @Override
    public void run(List<String> parameters) {
        for (var s : parameters){
            System.out.println(s);
        }
    }
}
