package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.lab9.MyTrieSet;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private final List<Node> nodes;
    private final Map<Point, Node> points;
    private final Map<String, List<Node>> cleanNames;
    private final MyTrieSet trieSet;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();
        points = new HashMap<>();
        cleanNames = new HashMap<>();
        trieSet = new MyTrieSet();

        for (Node node : nodes) {
            if (!neighbors(node.id()).isEmpty()) {
                points.put(new Point(node.lon(), node.lat()), node);
            }

            if (node.name() != null) {
                String name = cleanString(node.name());
                trieSet.add(name);

                if (!cleanNames.containsKey(name)) {
                    cleanNames.put(name, new ArrayList<>());
                }
                cleanNames.get(name).add(node);
            }
        }
    }

    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     *
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        KDTree kdTree = new KDTree(new ArrayList<>(points.keySet()));
        Point closest = kdTree.nearest(lon, lat);
        return points.get(closest).id();
    }

    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     *
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> match = trieSet.keysWithPrefix(cleanString(prefix));
        List<String> fullNames = new ArrayList<>();

        for (String cleanName : match) {
            String fullName = cleanNames.get(cleanName).get(0).name();
            fullNames.add(fullName);
        }

        return fullNames;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     *
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> locations = new ArrayList<>();
        String cleanLocationName = cleanString(locationName);

        for (Node node : cleanNames.get(cleanLocationName)) {
            Map<String, Object> location = new HashMap<>();
            location.put("lat", node.lat());
            location.put("lon", node.lon());
            location.put("name", node.name());
            location.put("id", node.id());
            locations.add(location);
        }

        return locations;
    }

}
