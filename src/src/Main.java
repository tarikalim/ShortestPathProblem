import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static City firstCity = null;
    private static City secondCity = null;
    private static boolean secondCitySelected = false;


    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        Map<String, Map<String, Integer>> adjacencyList = dataReader.readDataFromFile("C:\\Users\\Tarik\\IdeaProjects\\ShortestPathProblem\\src\\src\\cities.txt");

        int canvasWidth = 800;
        int canvasHeight = 800;
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.clear(Color.LIGHT_GRAY);
        StdDraw.setScale(0, canvasWidth);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text((double) canvasHeight / 2, 790, "1-Left click for selecting start city and target city.");
        StdDraw.text((double) canvasHeight / 2, 770, "2-Press 'D' to calculate distance with DFS or press 'B' to calculate distance with BFS.");
        StdDraw.text((double) canvasHeight / 2, 750, "Press 'E' for exit");


        Map<String, City> cities = new HashMap<>();
        int minDistance = 75;

        for (String cityName : adjacencyList.keySet()) {
            City city = new City(cityName);
            int x, y;
            do {
                x = (int) (Math.random() * (canvasWidth - 2 * minDistance) + minDistance);
                y = (int) (Math.random() * (canvasHeight - 2 * minDistance) + minDistance);
            } while (isTooClose(x, y, cities, minDistance));

            city.setX(x);
            city.setY(y);
            cities.put(cityName, city);
        }

        for (City city : cities.values()) {
            int x = city.getX();
            int y = city.getY();

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x, y, 10);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, y - 20, city.getName());
        }

        while (true) {
            if (StdDraw.isMousePressed() && !secondCitySelected) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();

                for (City city : cities.values()) {
                    int x = city.getX();
                    int y = city.getY();
                    if (Math.abs(mouseX - x) < 10 && Math.abs(mouseY - y) < 10) {
                        if (firstCity == null) {
                            firstCity = city;
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.filledCircle(x, y, 10);
                        } else {
                            secondCity = city;
                            secondCitySelected = true;
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.filledCircle(x, y, 10);
                            break;
                        }
                    }
                }
            }

            if (StdDraw.hasNextKeyTyped()) {
                char keyTyped = StdDraw.nextKeyTyped();
                if (keyTyped == 'd') {
                    if (firstCity != null && secondCity != null && secondCitySelected) {
                        LimitedDepthFirstSearch dfs = new LimitedDepthFirstSearch(adjacencyList);
                        List<String> shortestPath = dfs.findShortestPath(firstCity.getName(), secondCity.getName());
                        int totalDistance = dfs.getShortestDistance();

                        for (int i = 0; i < shortestPath.size() - 1; i++) {
                            String city1 = shortestPath.get(i);
                            String city2 = shortestPath.get(i + 1);

                            int x1 = cities.get(city1).getX();
                            int y1 = cities.get(city1).getY();
                            int x2 = cities.get(city2).getX();
                            int y2 = cities.get(city2).getY();

                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.filledCircle(x1, y1, 10);
                            StdDraw.line(x1, y1, x2, y2);
                            StdDraw.line(x1, y1, x2, y2);
                        }
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text((double) canvasWidth / 2, 20, "Shortest Distance: " + totalDistance + " km");
                    }

                } else if (keyTyped == 'b') {
                    if (firstCity != null && secondCity != null && secondCitySelected) {
                        BreathFirstSearch bfs = new BreathFirstSearch(adjacencyList);
                        BreathFirstSearch.ShortestPathResult shortestPathResult = bfs.shortestPathBFS(firstCity.getName(), secondCity.getName());
                        int totalDistance = shortestPathResult.getDistance();
                        List<String> shortestPath = shortestPathResult.getShortestPath();

                        for (int i = 0; i < shortestPath.size() - 1; i++) {
                            String city1 = shortestPath.get(i);
                            String city2 = shortestPath.get(i + 1);

                            int x1 = cities.get(city1).getX();
                            int y1 = cities.get(city1).getY();
                            int x2 = cities.get(city2).getX();
                            int y2 = cities.get(city2).getY();

                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.filledCircle(x1, y1, 10);
                            StdDraw.line(x1, y1, x2, y2);
                            StdDraw.line(x1, y1, x2, y2);
                        }
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text((double) canvasWidth / 2, 20, "Shortest Distance: " + totalDistance + " km");
                    }

                } else if (keyTyped == 'e') {
                    System.exit(0);

                }

            }
            StdDraw.pause(100);
        }
    }

    private static boolean isTooClose(int x, int y, Map<String, City> cities, int minDistance) {
        for (City city : cities.values()) {
            int cityX = city.getX();
            int cityY = city.getY();
            if (Math.abs(x - cityX) < minDistance && Math.abs(y - cityY) < minDistance) {
                return true;
            }
        }
        return false;
    }
}
