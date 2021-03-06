package LineNavigation;

import java.awt.Point;
import java.util.ArrayList;

public class Node {
	public String name;
	public Point position;
	public ArrayList<Edge> edges;
	public boolean visited = false;

	public Node() {
		position = null;
		edges = new ArrayList<Edge>();
	}

	public Node(String name, Point position) {
		edges = new ArrayList<Edge>();
		if (name.equals(""))
			throw new IllegalArgumentException();
		else
			this.name = name;

		if (position.equals(null))
			throw new NullPointerException();
		else
			this.position = position;
	}

	public void addEdge(Edge edge) {
		if (edge == null)
			throw new NullPointerException();
		else
			edges.add(edge);
	}

	public String toString() {
		String s = "";
		s += name + "(" + position.x + "|" + position.y + ") ->";
		for (int i = 0; i < edges.size(); i++) {
			s += edges.get(i);
		}
		return s;
	}
}
