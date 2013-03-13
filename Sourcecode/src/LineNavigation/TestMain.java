package LineNavigation;

import java.awt.Point;
import java.util.ArrayList;

public class TestMain {
	public static void main(String[] args) {
		// Navigator n = new Navigator();
		// n.ShortestPath(start, destination)
		Node A = new Node("A", new Point(1, 1));
		Node B = new Node("B", new Point(2, 2));
		Node C = new Node("C", new Point(3, 3));
		Node D = new Node("D", new Point(4, 4));
		A.addEdge(new Edge(B, 2));
		A.addEdge(new Edge(D, 10));
		A.addEdge(new Edge(C, 3));
		B.addEdge(new Edge(C, 5));
		C.addEdge(new Edge(B, 5));
		B.addEdge(new Edge(A, 10));
		C.addEdge(new Edge(B, 10));
		C.addEdge(new Edge(A, 5));
		C.addEdge(new Edge(D, 45));
		// System.out.println(A);
		Graph g = new Graph();
		// g.setStartNode(A);
		// System.out.println(g.searchNode("D"));
		ArrayList<Edge> al = g.getShortestpath(A, D);
		System.out.println(al);
	}
}