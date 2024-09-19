
# Shortest Path Finder Between Cities

This project is designed to find the shortest path between two selected cities on a map. 
It uses graph algorithms, primarily Depth-First Search (DFS) and Breadth-First Search (BFS), 
to compute the shortest distance between cities.

## Project Overview

The goal of this project is to find the shortest path between two cities on a map, 
using either DFS or BFS algorithms. The user can interact with the graphical interface, 
select two cities, and calculate the shortest distance between them.

## Technologies Used

- **Java**
- **StdDraw Library**: Used to create the graphical interface and handle user interactions.
- **DFS and BFS Algorithms**: Used to compute the shortest path between cities.

## Installation and Setup

### Requirements
- Java 8 or higher
- StdDraw Library

### Running the Project
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/tarikalim/ShortestPathProblem.git
   ```
2. Open the project in your preferred Java IDE.
3. Set cloned "stdlib.jar" file as a library. 
4. Run the `Main` class to start the application.

## How to Use

1. When the application starts, cities are randomly placed on the map.
2. Use the left mouse click to select the **start city** and the **target city**.
3. After selecting the cities:
    - Press **`D`** to calculate the shortest path using **DFS**.
    - Press **`B`** to calculate the shortest path using **BFS**.
4. The shortest path and distance between the selected cities will be displayed on the screen.
5. Press **`E`** to exit the application.

## City Data

The city distance data should be provided in a file (`cities.txt`) in the following format:

```plaintext
City1,City2,City3,...
City1,0,50,100,...
City2,50,0,70,...
...
```

- The first row contains the names of the cities.
- Each subsequent row represents a city, where the first value is the city's name, and the following values represent the distances to other cities.
- A value of `99999` represents no direct connection between two cities.

## Algorithms

### Limited Depth First Search (DFS)
This is a depth-limited version of the DFS algorithm. It searches for the shortest path between two cities within a certain depth limit. If no path is found within the limit or there are no connections, the search will not produce a result.

### Breadth First Search (BFS)
The BFS algorithm uses a queue-based approach to find the shortest path between cities. It calculates distances between connected cities and provides the shortest route.

## Screenshots
### Limited Depth First Search (DFS)![BFS](/images/BFS.png)
### Breadth First Search (BFS)
![DFS](/images/DFS.png)
