package distributed_computing;

import java.util.List;

public class KillService extends Service {
    public KillService(){
        super("kill");
    }

    @Override
    public void run(List<String> parameters) {
        System.exit(1);
    }
}
