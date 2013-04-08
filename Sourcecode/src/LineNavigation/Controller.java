package LineNavigation;

import java.awt.Point;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;

public class Controller {
	private int travelSpeed;
	private int maxTravelSpeed;

	private Point actPosition;
	private int actHeading;
	private static final int HEADING_NORTH = 1;
	private static final int HEADING_EAST = 2;
	private static final int HEADING_SOUTH = 3;
	private static final int HEADING_WEST = 4;

	// initialize the motor
	private RegulatedMotor leftMotor = Motor.B;
	private RegulatedMotor rightMotor = Motor.A;

	// initialize the lightSensors
	private LightSensor rightLightSensor = new LightSensor(SensorPort.S1);
	private LightSensor leftLightSensor = new LightSensor(SensorPort.S2);

	// calibrated value of the black line
	private static int blackLineColorValue;
	// calibrated value of the white space
	private static int whiteColorValue;

	private static final int variance = 8;

	/**
	 * constructor to default values and starts the calibration
	 * 
	 */
	public Controller() {
		maxTravelSpeed = 900;
		travelSpeed = 300;
		leftMotor.setSpeed(travelSpeed);
		rightMotor.setSpeed(travelSpeed);

		// calibrateSensors();
	}

	@SuppressWarnings("deprecation")
	public boolean travelEdge() {
		while ((Button.ESCAPE.isPressed() != true)
				&& !(((rightLightSensor.readValue() > blackLineColorValue
						- variance && rightLightSensor.readValue() < blackLineColorValue
						+ variance) && (leftLightSensor.readValue() > blackLineColorValue
						- variance && leftLightSensor.readValue() < blackLineColorValue
						+ variance)))) {

			LCD.drawInt(blackLineColorValue, 14, 1);
			LCD.drawInt(whiteColorValue, 0, 1);
			LCD.drawInt(rightLightSensor.readValue(), 0, 0);
			LCD.drawInt(leftLightSensor.readValue(), 14, 0);

			if (rightLightSensor.readValue() > blackLineColorValue - variance
					&& rightLightSensor.readValue() < blackLineColorValue
							+ variance) {
				leftMotor.backward();
				rightMotor.stop();
			} else if (leftLightSensor.readValue() > blackLineColorValue
					- variance
					&& leftLightSensor.readValue() < blackLineColorValue
							+ variance) {
				leftMotor.stop();
				rightMotor.forward();
			} 
			// else {
			// leftMotor.backward();
			// rightMotor.forward();
			// }
		}

		// actPosition = e.destination.position;
		return true;
	}

	public void setTravelSpeed(int speed) {
		if (speed > maxTravelSpeed)
			speed = 900;
		this.travelSpeed = speed;
	}

	public double getTravelSpeed() {
		return travelSpeed;
	}

	public double getMaxTravelSpeed() {
		return maxTravelSpeed;
	}

	public void stop() {
		leftMotor.stop();
		rightMotor.stop();
	}

	public void findLine() {
		double angle = 10;
		while (true) {
			turnAngle((int) angle, false);
			if ((leftLightSensor.readValue() < blackLineColorValue + variance)
					&& (leftLightSensor.readValue() > blackLineColorValue
							- variance))
				return;
			else if ((rightLightSensor.readValue() < blackLineColorValue
					+ variance)
					&& (rightLightSensor.readValue() > blackLineColorValue
							- variance))
				return;
			angle += 15;
			angle *= -1;
			if (angle > 45)
				throw new MovementfailedException("line not found");
		}
	}

	/**
	 * rotates a specific angle
	 * 
	 * @param angle
	 *            angle > 0 -> clockwise | angle < 0 -> anti-clockwise
	 */
	public void turnAngle(int angle) {
		turnAngle(angle, false);
	}

	private void turnAngle(int angle, boolean immediateReturn) {
		int rotateSpeed = 50;
		final float wheelSizeDiameter = 2.4f;
		float turnRatio = (11.0f / wheelSizeDiameter);

		leftMotor.setSpeed(Math.round(rotateSpeed * turnRatio));
		rightMotor.setSpeed(Math.round(rotateSpeed * turnRatio));

		int rotateAngleLeft = (int) (angle * turnRatio);
		int rotateAngleRight = (int) (angle * turnRatio);

		leftMotor.rotate(-rotateAngleLeft, true);
		rightMotor.rotate(-rotateAngleRight, immediateReturn);

		if (!immediateReturn)
			while (isMoving())
				Thread.yield();
	}

	private boolean isMoving() {
		return leftMotor.isMoving() || rightMotor.isMoving();
	}

	@SuppressWarnings("deprecation")
	public void calibrateSensors() {
		LCD.clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (Button.ENTER.isPressed() != true) {
			LCD.drawString("place the left ", 0, 0);
			LCD.drawString("sensor on the", 0, 1);
			LCD.drawString("line and", 0, 2);
			LCD.drawString("the right on", 0, 3);
			LCD.drawString("the white", 0, 4);
			LCD.drawString("Press enter button", 0, 6);
			LCD.drawInt(leftLightSensor.readValue(), 0, 7);
			LCD.drawInt(rightLightSensor.readValue(), 14, 7);
			if (Button.ESCAPE.isPressed())
				System.exit(1);
		}
		LCD.clear();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		blackLineColorValue = leftLightSensor.readValue();
		whiteColorValue = rightLightSensor.readValue();
	}

	public void turnToPoint(Node destination) {
		if (actPosition != null && actHeading != 0) {
			int goHeading = 0;
			if (actPosition.x < destination.position.x) {
				goHeading = HEADING_EAST;
			} else if (actPosition.x > destination.position.x) {
				goHeading = HEADING_WEST;
			} else if (actPosition.y < destination.position.y) {
				goHeading = HEADING_NORTH;
			} else if (actPosition.y > destination.position.y) {
				goHeading = HEADING_SOUTH;
			} else
				goHeading = 0;
			switch (actHeading) {
			case 1:
				switch (goHeading) {
				case HEADING_NORTH:
					break;
				case HEADING_EAST:
					turnAngle(90);
					break;
				case HEADING_SOUTH:
					turnAngle(180);
					break;
				case HEADING_WEST:
					turnAngle(270);
				}
				break;
			case 2:
				switch (goHeading) {
				case HEADING_NORTH:
					turnAngle(270);
					break;
				case HEADING_EAST:
					break;
				case HEADING_SOUTH:
					turnAngle(90);
					break;
				case HEADING_WEST:
					turnAngle(180);
					break;
				}
				break;
			case 3:
				switch (goHeading) {
				case HEADING_NORTH:
					turnAngle(180);
					break;
				case HEADING_EAST:
					turnAngle(270);
					break;
				case HEADING_SOUTH:
					break;
				case HEADING_WEST:
					turnAngle(90);
					break;
				}
				break;
			case 4:
				switch (goHeading) {
				case HEADING_NORTH:
					turnAngle(90);
					break;
				case HEADING_EAST:
					turnAngle(180);
					break;
				case HEADING_SOUTH:
					turnAngle(270);
					break;
				case HEADING_WEST:
					break;
				}
				break;
			}
		}
	}
}
