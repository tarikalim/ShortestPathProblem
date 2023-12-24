import java.util.*;

public class LimitedDepthFirstSearch {
    private Map<String, Map<String, Integer>> adjacencyList; // Grafiği tutan komşuluk listesi
    public List<String> shortestPath; // En kısa yolun tutulacağı liste
    private int shortestDistance; // En kısa yolun uzunluğu
    private boolean depthLimitExceeded; // Derinlik sınırının aşılıp aşılmadığını belirten bayrak

    // Yapıcı fonksiyon, grafı alır ve sınıfı başlatır
    public LimitedDepthFirstSearch(Map<String, Map<String, Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList; // Verilen grafın komşuluk listesini alır
        shortestPath = new ArrayList<>(); // En kısa yol için boş liste oluşturur
        shortestDistance = Integer.MAX_VALUE; // En kısa yol uzunluğunu maksimum olarak başlatır
    }

    // Başlangıç ve bitiş şehirleri arasındaki en kısa yolu bulur
    public List<String> findShortestPath(String startCity, String endCity) {
        List<String> currentPath = new ArrayList<>(); // Geçici yol listesi oluşturur
        currentPath.add(startCity); // Başlangıç şehrini geçici yola ekler
        depthLimitedSearch(startCity, endCity, 0, currentPath); // Sınırlı derinlikte DFS algoritmasını başlatır
        return shortestPath; // En kısa yolu döndürür
    }

    // Sınırlı derinlikte DFS algoritması
    private void depthLimitedSearch(String startCity, String endCity, int currentDepth, List<String> currentPath) {
        if (startCity.equals(endCity)) { // Eğer başlangıç şehri bitiş şehriyse
            int distance = calculateTotalDistance(currentPath); // Mevcut yolun uzunluğunu hesaplar
            if (distance < shortestDistance) { // Eğer bu yol daha kısa ise
                shortestDistance = distance; // En kısa yol uzunluğunu günceller
                shortestPath = new ArrayList<>(currentPath); // En kısa yolu günceller
            }
            return; // DFS'den çıkar
        }

        int depthLimit = 4; // Sınırlı derinlik
        if (currentDepth == depthLimit) { // Eğer derinlik sınırına ulaşılmışsa
            if (!depthLimitExceeded) { // Eğer derinlik sınırı aşılmamışsa
                depthLimitExceeded = true; // Derinlik sınırı aşıldı olarak işaretlenir
            }
            return; // DFS'den çıkar
        }

        Map<String, Integer> neighbors = adjacencyList.get(startCity); // Mevcut şehrin komşularını alır
        for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) { // Her bir komşu için
            String nextCity = neighbor.getKey(); // Komşu şehir alınır
            int distance = neighbor.getValue(); // Şehirler arası mesafe alınır

            if (distance != 99999 && !currentPath.contains(nextCity)) { // Eğer bu yol geçerli ve daha önce ziyaret edilmemişse
                currentPath.add(nextCity); // Geçici yola komşu şehir eklenir
                depthLimitedSearch(nextCity, endCity, currentDepth + 1, currentPath); // DFS algoritması tekrar çağırılır
                currentPath.remove(currentPath.size() - 1); // Geçici yoldan son eklenen şehir çıkarılır
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
