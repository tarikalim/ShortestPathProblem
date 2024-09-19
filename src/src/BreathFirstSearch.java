import java.util.*;

public class BreathFirstSearch {
    private Map<String, Map<String, Integer>> adjacencyList;

    public BreathFirstSearch(Map<String, Map<String, Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }


    public class ShortestPathResult {
        private List<String> shortestPath;
        private int distance;

        public ShortestPathResult(List<String> shortestPath, int distance) {
            this.shortestPath = shortestPath;
            this.distance = distance;
        }

        public List<String> getShortestPath() {
            return shortestPath;
        }

        public int getDistance() {
            return distance;
        }
    }

    public ShortestPathResult shortestPathBFS(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer> distanceMap = new HashMap<>();

        queue.offer(start);
        parentMap.put(start, null);
        distanceMap.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                return new ShortestPathResult(constructPath(parentMap, start, end), distanceMap.get(end));
            }

            Map<String, Integer> neighbors = adjacencyList.getOrDefault(current, new HashMap<>());
            for (String neighbor : neighbors.keySet()) {
                int distanceToNeighbor = neighbors.get(neighbor);
                int totalDistance = distanceMap.get(current) + distanceToNeighbor;
                if (distanceToNeighbor != 99999 && (!distanceMap.containsKey(neighbor) || totalDistance < distanceMap.get(neighbor))) {

                    queue.offer(neighbor);
                    parentMap.put(neighbor, current);
                    distanceMap.put(neighbor, totalDistance);
                }
            }
        }
        return null;
    }


    private List<String> constructPath(Map<String, String> parentMap, String start, String end) {
        List<String> shortestPath = new ArrayList<>();
        String current = end;
        while (current != null) {
            shortestPath.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
