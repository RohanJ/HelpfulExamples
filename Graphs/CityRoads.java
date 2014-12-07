import java.util.*;

//Implements Dijkstra's Algorithm

class Vertex implements Comparable<Vertex> {
	public final int city;
	public List<Edge> adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(int city) {
		this.city = city;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	@Override
	public String toString() {
		return String.format("City: %s", this.city);
	}
}

class Edge {
	private final Vertex dest;
	private final double weight;

	public Edge(Vertex dest, double weight) {
		this.dest = dest;
		this.weight = weight;
	}

	public Vertex getDest() { return this.dest; }
	public double getWeight() { return this.weight; }

	@Override
	public String toString() {
		return String.format("{dest: %d, weight: %f}", this.dest.city, this.weight);
	}
}

public class CityRoads {

	public static void main(String[] args) {
		testRoads();
	}

	public static void testRoads() {
		int[] firstCityRoads = {1,2,3,2,5};
		int[] secondCityRoads = {3,3,4,4,6};
		findShortestPath(/* N = */ 7, /* homeCity = */ 1, /* destCity = */ 4, firstCityRoads, secondCityRoads);
	}



	private static void findShortestPath(int N, int homeCity, int destCity, int[] firstCityRoads, int[] secondCityRoads) {
		HashMap<Integer, Vertex> vertexMap = generateGraphWithAdjancies(firstCityRoads, secondCityRoads);
		SOP(vertexMap);
		for(Map.Entry<Integer, Vertex> entry: vertexMap.entrySet()) {
			SOP(entry.getKey());
			SOP(entry.getValue().adjacencies);
			SOP("====");
		}

		computePaths(vertexMap, homeCity);
		List<Vertex> path = getShortestPathTo(vertexMap.get(destCity));

		SOP(String.format("Path from %d to %d: ", homeCity, destCity) + path);
		SOP("Number to roads to take: " + (path.size() - 1));
	}

	private static HashMap<Integer, Vertex> generateGraphWithAdjancies(int[] firstCityRoads, int[] secondCityRoads) {
		if (firstCityRoads.length != secondCityRoads.length) { return null; }

		HashMap<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>();
		//for bi-directionalism
		mapAdjancies(vertexMap, firstCityRoads, secondCityRoads);
		mapAdjancies(vertexMap, secondCityRoads, firstCityRoads);

		return vertexMap;
	}

	private static void mapAdjancies(HashMap<Integer, Vertex> vertexMap, int[] firstCities, int[] secondCities) {
		for (int i = 0 ; i < firstCities.length; i++) {
			ArrayList<Edge> adj = new ArrayList<Edge>();
			adj.add(new Edge(new Vertex(secondCities[i]),1));

			if (vertexMap.get(firstCities[i]) == null ){
				Vertex vertex = new Vertex(firstCities[i]);
				vertex.adjacencies = adj;
				vertexMap.put(firstCities[i], vertex);
			} else {
				//combine adjacencies
				Vertex orig = vertexMap.get(firstCities[i]);
				orig.adjacencies.addAll(adj);
				vertexMap.put(firstCities[i], orig);
			}
		}
	}

	private static void computePaths(HashMap<Integer, Vertex> vertexMap, int homeCity) {
		Vertex startCity = vertexMap.get(homeCity);
		startCity.minDistance = 0;

		PriorityQueue<Vertex> priQ = new PriorityQueue<Vertex>();
		priQ.add(startCity);

		while(!priQ.isEmpty()) {
			Vertex curr = priQ.poll();
			for (Edge e : curr.adjacencies) {
				Vertex next = vertexMap.get(e.getDest().city);
				double weight = e.getWeight();
				double distanceThroughCurr = curr.minDistance + weight;
				if (distanceThroughCurr < next.minDistance) {
					priQ.remove(next);
					next.minDistance = distanceThroughCurr;
					next.previous = curr;
					priQ.add(next);
				}
			}
		}
	}

	private static List<Vertex> getShortestPathTo(Vertex dest) {
		List<Vertex> path  = new ArrayList<Vertex>();
		for (Vertex vertex = dest; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}

		Collections.reverse(path);
		return path;
	}

	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}
