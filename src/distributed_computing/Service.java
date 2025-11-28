package distributed_computing;

import java.util.List;

public interface Service {
        String getName();
        void run(List<String> parameters);
}
