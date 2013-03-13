package LineNavigation;

import java.util.ArrayList;

public class Navigator {

	private ArrayList<Edge> path;

	private Controller Mover;
	private Graph scenario;

	public Navigator() {
		scenario = new Graph();
		Mover = new Controller();

		scenario.createScenario();
	}

	public void ShortestPath(Node start, Node destination) {
		if (scenario == null)
			throw new NullPointerException("no szenario created!");
		else {
			path = scenario.getShortestpath(start, destination);
		}
	}	

	public boolean drivePath(ArrayList<Edge> e) {
		for (int i = 0; i < e.size(); i++) {
			try {
				Mover.turnToPoint(e.get(i).destination);
				Mover.findLine();
				Mover.travelEdge(e.get(i));
			} catch (MovementfailedException e1) {
				return false;
			}
		}
		return true;
	}
}