package LineNavigation;

import java.awt.Point;
import java.util.ArrayList;

public class Graph {
	private Node startNode;
	private Node destinationNode;

	public Graph() {

	}

	public Graph(Node StartNode) {
		this.startNode = StartNode;
	}

	public Node searchNode(String name) {
		if (name.equals(""))
			return null;
		else
			return searchNode(name, startNode);
	}

	private Node searchNode(String name, Node temp) {
		if (temp.name.equals(name))
			return temp;
		else {
			temp.visited = true;
			for (int i = 0; i < temp.edges.size(); i++) {
				if (temp.edges.get(i).destination.visited == false) {
					Node tempsearch = searchNode(name,
							temp.edges.get(i).destination);
					if (tempsearch != null)
						return tempsearch;
				}
			}
		}
		return null;
	}

	public Node searchNode(Point p) {
		if (p != null)
			return searchNode(p, startNode);
		else
			return null;
	}

	private Node searchNode(Point p, Node temp) {
		if (temp.position.x == p.x && temp.position.y == p.y)
			return temp;
		else {
			temp.visited = true;
			for (int i = 0; i < temp.edges.size(); i++) {
				if (temp.edges.get(i).destination.visited == false) {
					Node tempsearch = searchNode(p,
							temp.edges.get(i).destination);
					if (tempsearch != null)
						return tempsearch;
				}
			}
		}
		return null;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		if (startNode != null)
			this.startNode = startNode;
	}

	public void setDestinationNode(Node destiantion) {
		if (destiantion != null)
			this.destinationNode = destiantion;
	}

	public void createScenario() {
		Node A = new Node("A", new Point(10, 142));
		Node B = new Node("B", new Point(10, 121));
		Node C = new Node("C", new Point(55, 121));
		Node D = new Node("D", new Point(81, 121));
		Node E = new Node("E", new Point(149, 121));
		Node F = new Node("F", new Point(190, 121));
		Node G = new Node("G", new Point(236, 121));
		Node H = new Node("H", new Point(264, 121));
		Node I = new Node("I", new Point(55, 80));
		Node J = new Node("J", new Point(115, 80));
		Node K = new Node("K", new Point(149, 80));
		Node L = new Node("L", new Point(190, 80));
		Node M = new Node("M", new Point(236, 80));
		Node N = new Node("N", new Point(10, 36));
		Node O = new Node("O", new Point(55, 36));
		Node P = new Node("P", new Point(81, 36));
		Node Q = new Node("Q", new Point(115, 36));
		Node R = new Node("R", new Point(149, 36));
		Node S = new Node("S", new Point(190, 36));
		Node T = new Node("T", new Point(10, 10));
		Node U = new Node("U", new Point(115, 10));
		Node V = new Node("V", new Point(236, 10));

		A.addEdge(new Edge(B, 21));
		B.addEdge(new Edge(A, 21));
		B.addEdge(new Edge(C, 45));
		B.addEdge(new Edge(N, 85));
		C.addEdge(new Edge(B, 21));
		C.addEdge(new Edge(D, 26));
		C.addEdge(new Edge(I, 41));
		D.addEdge(new Edge(C, 26));
		D.addEdge(new Edge(E, 68));
		D.addEdge(new Edge(P, 85));
		E.addEdge(new Edge(D, 68));
		E.addEdge(new Edge(F, 41));
		E.addEdge(new Edge(K, 41));
		F.addEdge(new Edge(E, 41));
		F.addEdge(new Edge(K, 46));
		F.addEdge(new Edge(L, 41));
		G.addEdge(new Edge(F, 46));
		G.addEdge(new Edge(H, 28));
		G.addEdge(new Edge(M, 41));
		H.addEdge(new Edge(G, 28));
		I.addEdge(new Edge(C, 41));
		I.addEdge(new Edge(O, 44));
		J.addEdge(new Edge(K, 34));
		K.addEdge(new Edge(J, 34));
		K.addEdge(new Edge(E, 41));
		K.addEdge(new Edge(L, 41));
		K.addEdge(new Edge(R, 44));
		L.addEdge(new Edge(F, 41));
		L.addEdge(new Edge(K, 41));
		L.addEdge(new Edge(M, 46));
		M.addEdge(new Edge(G, 41));
		M.addEdge(new Edge(L, 46));
		M.addEdge(new Edge(V, 70));
		N.addEdge(new Edge(B, 85));
		N.addEdge(new Edge(O, 45));
		N.addEdge(new Edge(T, 25));
		O.addEdge(new Edge(I, 44));
		O.addEdge(new Edge(N, 45));
		O.addEdge(new Edge(P, 25));
		P.addEdge(new Edge(D, 85));
		P.addEdge(new Edge(O, 25));
		P.addEdge(new Edge(Q, 34));
		Q.addEdge(new Edge(P, 34));
		Q.addEdge(new Edge(R, 34));
		Q.addEdge(new Edge(U, 25));
		R.addEdge(new Edge(K, 44));
		R.addEdge(new Edge(Q, 34));
		R.addEdge(new Edge(S, 41));
		S.addEdge(new Edge(R, 41));
		T.addEdge(new Edge(N, 25));
		U.addEdge(new Edge(Q, 25));
		V.addEdge(new Edge(M, 70));
	}

	public ArrayList<Edge> getShortestpath(Node start, Node destination) {
		setStartNode(start);
		setDestinationNode(destination);
		if (start == destination)
			return new ArrayList<Edge>();
		else {
			ArrayList<Edge> al = new ArrayList<Edge>();
			start.visited = true;
			al.addAll(getShortestPath(start, destination,
					new ArrayList<Edge>(), new Edge(new Node(), 0)));
			return al;
		}
	}

	/**
	 * help-method to get the shortest path with recursion
	 * 
	 * @param temp
	 * @param destination
	 * @param weight
	 * @param possiblePaths
	 * @return
	 */
	private ArrayList<Edge> getShortestPath(Node temp, Node destination,
			ArrayList<Edge> possiblePaths, Edge previous) {
		if (destination.name == temp.name) {
			ArrayList<Edge> al = new ArrayList<Edge>();
			al.add(previous);
			return al;
		} else {
			possiblePaths = addPaths(temp, previous, possiblePaths);
			Edge shortest = getShortestEdge(possiblePaths);
			shortest.destination.visited = true;
			possiblePaths.remove(shortest);
			ArrayList<Edge> al = new ArrayList<Edge>();
			al.addAll(getShortestPath(shortest.destination, destination,
					possiblePaths, shortest));
			al.add(previous);
			return al;
		}
	}

	private ArrayList<Edge> deleteDoubleEdges(ArrayList<Edge> e) {
		ArrayList<Edge> al = new ArrayList<Edge>(e);

		return al;
	}

	/**
	 * Adds all non visited Edges of the Node temp to the Arraylist
	 * 
	 * @param temp
	 * @param edges
	 * @return
	 */
	private ArrayList<Edge> addPaths(Node temp, Edge e, ArrayList<Edge> edges) {
		for (int i = 0; i < temp.edges.size(); i++) {
			if (temp.edges.get(i).destination.visited != true) {
				temp.edges.get(i).weight += e.weight;
				edges.add(temp.edges.get(i));
			}
		}
		return edges;
	}

	/**
	 * calculate the shortest path
	 * 
	 * @param edges
	 * @return the shortest edge in the arrayList
	 */
	private Edge getShortestEdge(ArrayList<Edge> edges) {
		if (edges.size() > 1) {
			Edge shortest = edges.get(0);
			for (int i = 1; i < edges.size(); i++) {
				if (edges.get(i).weight < shortest.weight)
					shortest = edges.get(i);
			}
			return shortest;
		} else if (edges.size() == 1) {
			return edges.get(0);
		}
		return null;
	}
}
