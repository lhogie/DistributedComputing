package distributed_computing;

import java.util.List;

public class KillService implements Service {

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public void run(List<String> parameters) {
        System.exit(1);
    }
}
