import java.util.*;

public class LimitedDepthFirstSearch {
    private Map<String, Map<String, Integer>> adjacencyList;
    public List<String> shortestPath;
    private int shortestDistance;
    private boolean depthLimitExceeded;


    public LimitedDepthFirstSearch(Map<String, Map<String, Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        shortestPath = new ArrayList<>();
        shortestDistance = Integer.MAX_VALUE;
    }


    public List<String> findShortestPath(String startCity, String endCity) {
        List<String> currentPath = new ArrayList<>();
        currentPath.add(startCity);
        depthLimitedSearch(startCity, endCity, 0, currentPath);
        return shortestPath;
    }

    private void depthLimitedSearch(String startCity, String endCity, int currentDepth, List<String> currentPath) {
        if (startCity.equals(endCity)) {
            int distance = calculateTotalDistance(currentPath);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = new ArrayList<>(currentPath);
            }
            return;
        }

        int depthLimit = 4;
        if (currentDepth == depthLimit) {
            if (!depthLimitExceeded) {
                depthLimitExceeded = true;
            }
            return;
        }

        Map<String, Integer> neighbors = adjacencyList.get(startCity);
        for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
            String nextCity = neighbor.getKey();
            int distance = neighbor.getValue();

            if (distance != 99999 && !currentPath.contains(nextCity)) {
                currentPath.add(nextCity);
                depthLimitedSearch(nextCity, endCity, currentDepth + 1, currentPath);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private int calculateTotalDistance(List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String currentCity = path.get(i);
            String nextCity = path.get(i + 1);
            totalDistance += adjacencyList.get(currentCity).get(nextCity);
        }
        return totalDistance;
    }

    public int getShortestDistance() {
        return shortestDistance;
    }


}
