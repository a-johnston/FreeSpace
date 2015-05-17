package lambda.freespace;

import java.util.ArrayList;
import java.util.List;

/**
 * The World class is provided by the FreeSpace runtime as a way of accessing the virtual world
 */
public class World {
    private List<Zone> zones;
    public World() {
        zones = new ArrayList<Zone>();
    }
}
