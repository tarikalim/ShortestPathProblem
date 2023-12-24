import java.util.*;

public class BreathFirstSearch {
    private Map<String, Map<String, Integer>> adjacencyList; // Grafın komşuluk listesi

    public BreathFirstSearch(Map<String, Map<String, Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList; // Verilen grafın komşuluk listesini alır
    }

    // İç içe sınıf, en kısa yol sonucunu ve mesafesini taşır
    public class ShortestPathResult {
        private List<String> shortestPath; // En kısa yolun tutulacağı liste
        private int distance; // En kısa yolun uzunluğu

        public ShortestPathResult(List<String> shortestPath, int distance) {
            this.shortestPath = shortestPath; // En kısa yol listesi
            this.distance = distance; // En kısa yol uzunluğu
        }

        public List<String> getShortestPath() {
            return shortestPath; // En kısa yolu döndürür
        }

        public int getDistance() {
            return distance; // En kısa yol uzunluğunu döndürür
        }
    }

    // BFS algoritması kullanarak başlangıç ve bitiş arasındaki en kısa yolu bulur
    public ShortestPathResult shortestPathBFS(String start, String end) {
        Queue<String> queue = new LinkedList<>(); // BFS'de kullanılacak kuyruk
        Map<String, String> parentMap = new HashMap<>(); // Her düğümün ebeveyn düğümünü tutan harita
        Map<String, Integer> distanceMap = new HashMap<>(); // Her düğüme olan mesafeyi tutan harita

        queue.offer(start); // Başlangıç düğümünü kuyruğa ekler
        parentMap.put(start, null); // Başlangıç düğümünün ebeveynini null olarak belirler
        distanceMap.put(start, 0); // Başlangıç düğümüne olan mesafeyi 0 olarak belirler

        while (!queue.isEmpty()) {
            String current = queue.poll(); // Kuyruktan bir düğüm çıkarılır
            if (current.equals(end)) { // Eğer bu düğüm hedef düğümse
                return new ShortestPathResult(constructPath(parentMap, start, end), distanceMap.get(end)); // En kısa yol sonucunu ve mesafesini döndürür
            }

            Map<String, Integer> neighbors = adjacencyList.getOrDefault(current, new HashMap<>()); // Mevcut düğümün komşularını alır
            for (String neighbor : neighbors.keySet()) { // Her bir komşu için
                int distanceToNeighbor = neighbors.get(neighbor); // Mevcut düğüme olan mesafeyi alır
                int totalDistance = distanceMap.get(current) + distanceToNeighbor; // Toplam mesafeyi hesaplar
                if (distanceToNeighbor != 99999 && (!distanceMap.containsKey(neighbor) || totalDistance < distanceMap.get(neighbor))) {
                    // Eğer bu komşu daha önce ziyaret edilmemişse veya yeni yol daha kısa ise
                    queue.offer(neighbor); // Komşuyu kuyruğa ekler
                    parentMap.put(neighbor, current); // Komşunun ebeveynini günceller
                    distanceMap.put(neighbor, totalDistance); // Mesafeyi günceller
                }
            }
        }
        return null; // Hedef düğüme ulaşılamazsa null döndürür
    }

    // En kısa yolu oluşturur
    private List<String> constructPath(Map<String, String> parentMap, String start, String end) {
        List<String> shortestPath = new ArrayList<>(); // En kısa yol listesi
        String current = end; // Hedef düğümden başlar
        while (current != null) { // Ebeveyn düğümü null olana kadar devam eder
            shortestPath.add(current); // Düğümü yola ekler
            current = parentMap.get(current); // Ebeveyn düğümü alır
        }
        Collections.reverse(shortestPath); // Yolu ters çevirir (başlangıçtan hedefe)
        return shortestPath; // En kısa yolu döndürür
    }
}
