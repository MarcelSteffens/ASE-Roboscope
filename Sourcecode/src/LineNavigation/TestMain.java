package LineNavigation;

import lejos.nxt.Button;

public class TestMain {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// Navigator n = new Navigator();
		// char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		// 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V' };
		// int i = 0;
		// Thread.sleep(200);
		// while (!Button.ENTER.isPressed()) {
		// LCD.drawString("Start Node", 5, 4);
		// LCD.drawString("" + letters[i], 5, 5);
		// if (Button.LEFT.isPressed()) {
		// i--;
		// if (i < 0)
		// i = letters.length - 1;
		// }
		// Thread.sleep(100);
		// if (Button.RIGHT.isPressed()) {
		// i++;
		// if (i > letters.length-1)
		// i = 0;
		// }
		// Thread.sleep(100);
		// LCD.clear();
		// }
		// i = 0;
		// // graph.setStartNode(graph.searchnode(letters[i]));
		// Thread.sleep(500);
		// while (!Button.ENTER.isPressed()) {
		// LCD.drawString("Destination Node", 5, 4);
		// LCD.drawString("" + letters[i], 5, 5);
		// if (Button.LEFT.isPressed()) {
		// i--;
		// if (i < 0)
		// i = letters.length - 1;
		// }
		// Thread.sleep(100);
		// if (Button.RIGHT.isPressed()) {
		// i++;
		// if (i > letters.length-1)
		// i = 0;
		// }
		// Thread.sleep(100);
		// LCD.clear();
		// }
		// graph.setDestiationNode(graph.searchnode(letters[i]));

		// ---------- controller test----------\\
		// Controller c = new Controller();
		// while (!Button.ESCAPE.isPressed()) {
		// LCD.clear();
		// if (Button.LEFT.isPressed()) {
		// LCD.clear();
		// LCD.drawString("clockwise", 1, 1);
		// c.turnAngle(90);
		// }
		// if (Button.RIGHT.isPressed()) {
		// LCD.clear();
		// LCD.drawString("anti-clockwise", 1, 1);
		// c.turnAngle(-90);
		// }
		// if (Button.ENTER.isPressed()) {
		// LCD.clear();
		// LCD.drawString("fahre", 1, 1);
		// c.calibrateSensors();
		// c.travelEdge(new Edge(null, 0));
		// }
		// }

		// --------------lineFollower test--------\\

		Controller c = new Controller();
		while (!Button.ESCAPE.isPressed()) {
			if (Button.ENTER.isPressed()) {
				c.calibrateSensors();
				c.travelEdge();
				// c.turnAngle(90);
				// c.findLine();
				// c.travelEdge();
			}
			c.stop();
		}
		// ------------- graph test------------\\
		// Graph g = new Graph();
		// g.createScenario();
		// ArrayList<Edge> al = g.getShortestpath(g.startNode,
		// g.destinationNode);
		// System.out.println(al);
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}
}