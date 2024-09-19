import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataReader {
    private Map<String, Map<String, Integer>> adjacencyList;

    public DataReader() {
        adjacencyList = new HashMap<>();
    }

    public Map<String, Map<String, Integer>> readDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String[] cityNames = br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String city = values[0];

                Map<String, Integer> edges = new HashMap<>();
                for (int i = 1; i < values.length; i++) {
                    edges.put(cityNames[i], Integer.parseInt(values[i]));
                }
                adjacencyList.put(city, edges);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adjacencyList;
    }
}
