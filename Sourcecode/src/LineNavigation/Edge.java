package LineNavigation;

public class Edge {
	public Node destination;
	public int weight;

	// public boolean passable;

	public Edge(Edge e) {
		this.destination = e.destination;
		this.weight = e.weight;
	}

	public Edge(Node Destination, int weight) {
		this.destination = Destination;
		this.weight = weight;
	}

	public Edge() {
		this.destination = null;
		this.weight = 0;
	}

	@Override
	public String toString() {
		return "(" + destination.name + "|" + weight + ")";
	}

}
