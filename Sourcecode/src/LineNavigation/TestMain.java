package LineNavigation;

import java.util.ArrayList;

public class TestMain {
	public static void main(String[] args) {
		// Navigator n = new Navigator();
		// n.ShortestPath(start, destination)
		Graph g = new Graph();
		g.createScenario();
		ArrayList<Edge> al = g.getShortestpath(g.startNode,g.destinationNode);
		System.out.println(al);
	}
}