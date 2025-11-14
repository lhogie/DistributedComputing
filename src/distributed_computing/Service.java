package distributed_computing;

import java.util.List;

public abstract class Service {
    String name;

    public Service(String name){
        this.name = name;
    }

    public abstract void run(List<String> parameters);
}
