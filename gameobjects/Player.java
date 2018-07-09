package gameobjects;

import backend.GameApp;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The Player, the ship the user controls. can rotate, accelerate, shoot, and maybe more
 * @author ngellie
 *
 */
public class Player extends GameObject {
	
	/* CONSTANTS */
	private static String S_PLAYER = "res/spaceship.png";
	private static float SPEED_DUMMY_PLAYER = 1/2f;
	private static float SPEED_ROTATE = (float) (1000.0/2f);
	private static float ACCEL = 1/500f;
	private static float BRAKE = 1/50f;
	private static float MAX_VELOCITY = 3/4f;
	private static float FRICTION = 1/6000f;
	private static float INIT_ANGLE = 0;
	
	/* Variables */
	
	
	/* CONSTRUCTORS */
	
	public Player(float x, float y) throws SlickException {
		super(x, y, SPEED_ROTATE, S_PLAYER);
		setVelAngle(getAngle());
	}
	
	/* METHODS */
	
	public void update(Input input, int delta) {
		super.update(input, delta);
	}
	
	// false left, true right. might be better with enums in the future
	public void move(Input input, int delta) {
		// rotate ship with left and right
		if (input.isKeyDown(Input.KEY_LEFT)) {
			rotate(true, delta);
		}			
		if (input.isKeyDown(Input.KEY_RIGHT))  {
			rotate(false, delta);
		}			
		// if activated, move ship forward, limit max speed
		if (input.isKeyDown(Input.KEY_UP)) {
			addVelocity(delta*ACCEL);
		// else, slow it down until it stops.
		} else {
			addVelocity(-delta*FRICTION);
		}
		// hit the brakes!!!
		if (input.isKeyDown(Input.KEY_DOWN)) {
			addVelocity(-delta*BRAKE);
			
		}
		
		clampVelocity();
		correctMovement(input);
		
		addX(delta*getHorizontalVelocity());
		addY(delta*getVerticalVelocity());
	}
	
	/* Helper Methods */
	// takes the angle in 360 degrees and calculates with radian
	//private float calcXspeed() {
		//return -(getHorizontalRatio()*velocity);
	//}
	//private float calcYspeed() {
		//return -(getVerticalRatio()*velocity);
	//}
	/** debugging, corrects and sets positions accordingly */
	private void correctMovement(Input input) {
		if (input.isKeyDown(Input.KEY_A)) {
			setAngle(270);
		}
		if (input.isKeyPressed(Input.KEY_D)) {
			setAngle(90);
		}
	}
	
	/** makes sure velocity is within bounds */
	private void clampVelocity() {
		// makes sure velocity isn't below 0 (no going backwards)
		if (getVelocity()<0) {setVelocity(0);}
		// makes sure velocity isn't above top speed (what about boosts?)
		if (getVelocity()>MAX_VELOCITY) {setVelocity(MAX_VELOCITY);}
		
	}
	
	/* Setters */
	
	/* Getters */
	
	
}
