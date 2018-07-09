package gameobjects;

import backend.GameApp;
import util.Position;
import util.Hitbox;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;


/** 
 * The standard abstract object that represents everything that appears in the game
 * perhaps not UI tho maybe that's different (nah probs same)
 * @author ngellie
 *
 */
public class GameObject {
	
	/* Constants */
	public static final String S_DEFAULT = "res/shield.png";
	private static final float SPEED_DEFAULT = 1;
	public static final float DEGREES_360 = 360f;
	public static final float DEGREE_OFFSET = -90f;
	
	/* Variables for movement, data and such */
	private Position position;
	private Image sprite;
	private Hitbox hitbox;
	private float rotSpeed;
	private float angle;
	private float velocity;
	private float velAngle;
	private boolean canRender = true;
	private boolean canMove = true;
	private boolean canRotate = true;
	
	/* CONSTRUCTORS */
	public GameObject() throws SlickException {
		position = new Position(GameApp.SCREEN_WIDTH, GameApp.SCREEN_HEIGHT);
		sprite = new Image(S_DEFAULT);
		hitbox = new Hitbox(position, sprite);
	}
	public GameObject(float x, float y, float rotSpeed, String imgsrc ) throws SlickException {
		position = new Position(x, y);
		this.rotSpeed = rotSpeed;
		sprite = new Image(imgsrc);
		hitbox = new Hitbox(position, sprite);
		angle = sprite.getRotation();
	}
	
	/* METHODS */
	
	/**
	 * If it can move, it moves, updates hitbox position
	 * @param input the Player's keystrokes
	 * @param delta milliseconds since last frame
	 */
	public void update(Input input, int delta) {
		if (canMove) {move(input, delta);}
		hitbox.setPos(position);
		// keeps angle within 360 at all times
		angle = (angle<0)?angle+DEGREES_360:angle%DEGREES_360;
		sprite.setRotation(angle);
		
	}
	
	/**
	 * renders the GameObject's sprite on the screen
	 */
	public void render() {
		if (canRender) {sprite.draw(hitbox.getLeft(), hitbox.getTop());}
	}
	
	/* Helper Methods */
	public void move(Input input, int delta) {}
	
	// slick is weird with angles
	public void rotate(boolean right) {
		if (canRotate) {
			if (right) {
				addAngle(rotSpeed);
			} else {
				addAngle(-rotSpeed);
			}
		}
	}

	/* Setters */
	public void setX(float x) {
		position.setX(x);
	}
	public void setY(float y) {
		position.setY(y);
	}
	public void setPos(float x, float y) {
		position.setPos(x, y);
	}
	public void setPos(Position p) {
		position.setPos(p);
	}
	public void addX(float x) {
		position.addX(x);
	}
	public void addY(float y) {
		position.addY(y);
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public void addAngle(float angle) {
		this.angle += angle;
	}
	public void setVelAngle(float angle) {
		velAngle = angle;
	}
	public void addVelAngle(float angle) {
		velAngle += angle;
	}
	public void setVelocity(float v) {
		velocity = v;
	}
	public void addVelocity(float v) {
		velocity += v;
	}
	// rotate but with delta inclusion
	public void rotate(boolean direction, int delta) {
		if (canRotate) {
			if (direction) {
				addAngle(-rotSpeed*delta/GameApp.DELTA);
			} else {
				addAngle(rotSpeed*delta/GameApp.DELTA);
			}
		}
		//this.angle += angle*delta;
	}
	public void setRotationSpeed(float rotationSpeed) {
		rotSpeed = rotationSpeed;
	}
	
	/* Getters */
	public Position getPos() {
		return position;
	}
	public float getX() {
		return position.getX();
	}
	public float getY() {
		return position.getY();
	}
	public float getAngle() {return angle;}
	public float getVelocity() {return velocity;}
	
	public float getHorizontalVelocity() {
		return (float)Math.cos((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	public float getVerticalVelocity() {
		return (float)Math.sin((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	
	public float getHorizontalRatio() {
		return (float)Math.cos((angle-DEGREE_OFFSET)*Math.PI/180);
	}
	public float getVerticalRatio() {
		return (float)Math.sin((angle-DEGREE_OFFSET)*Math.PI/180);
	}
}
