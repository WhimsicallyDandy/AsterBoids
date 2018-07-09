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
	
	private float spriteRotationSpeed;
	private float objectRotationSpeed;
	private float spriteAngle;
	
	private float velocity;
	private float velAngle;
	
	private float xAccel;
	private float yAccel;
	
	private float xSpeed;
	private float ySpeed;
	
	
	private boolean canRender = true;
	private boolean canMove = true;
	private boolean canRotate = true;
	
	/* CONSTRUCTORS */
	public GameObject() throws SlickException {
		position = new Position(GameApp.SCREEN_WIDTH, GameApp.SCREEN_HEIGHT);
		sprite = new Image(S_DEFAULT);
		hitbox = new Hitbox(position, sprite);
	}
	public GameObject(float x, float y, float rotationSpeed, String imgsrc ) throws SlickException {
		position = new Position(x, y);
		spriteRotationSpeed = rotationSpeed;
		objectRotationSpeed = rotationSpeed;
		sprite = new Image(imgsrc);
		hitbox = new Hitbox(position, sprite);
		spriteAngle = sprite.getRotation();
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
		spriteAngle = (spriteAngle<0)?spriteAngle+DEGREES_360:spriteAngle%DEGREES_360;
		sprite.setRotation(spriteAngle);
		
	}
	
	/**
	 * renders the GameObject's sprite on the screen
	 */
	public void render() {
		if (canRender) {sprite.draw(hitbox.getLeft(), hitbox.getTop());}
	}
	
	/* Helper Methods */
	// moves the object. currently blank, for abstraction reasons

	public void move(Input input, int delta) {}
	
	// slick is weird with angles
	public void turnObject(boolean right) {
		if (canRotate) {
			if (right) {
				addVelAngle(objectRotationSpeed);
			} else {
				addVelAngle(-objectRotationSpeed);
			}
		}
	}
	public void turnObject(boolean right, int delta) {
		if (canRotate) {
			if (right) {
				addVelAngle(objectRotationSpeed*delta/GameApp.DELTA);
			} else {
				addVelAngle(-objectRotationSpeed*delta/GameApp.DELTA);
			}
		}
	}
	
	// rotates the sprite
	public void rotateSprite(boolean direction) {
		if (canRotate) {
			if (direction) {
				addSpriteAngle(-spriteRotationSpeed);
			} else {
				addSpriteAngle(spriteRotationSpeed);
			}
		}
	}
	public void rotateSprite(boolean direction, int delta) {
		if (canRotate) {
			if (direction) {
				addSpriteAngle(-spriteRotationSpeed*delta/GameApp.DELTA);
			} else {
				addSpriteAngle(spriteRotationSpeed*delta/GameApp.DELTA);
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
	public void setSpriteAngle(float angle) {
		this.spriteAngle = angle;
	}
	public void addSpriteAngle(float angle) {
		this.spriteAngle += angle;
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
	public void accelerate(float v) {
		velocity += v;
	}
	
	public void setXSpeed(float x) {
		xSpeed = x;
	}
	public void setYSpeed(float y) {
		ySpeed = y;
	}
	
	public void setSpriteRotationSpeed(float rotationSpeed) {
		this.spriteRotationSpeed = rotationSpeed;
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
	public float getSpriteAngle() {return spriteAngle;}
	
	// returns the absolute value of the directional velocity
	public float getVelocity() {return velocity;}
	// (float)Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2))
	
	public float getXSpeed() {
		return xSpeed;
	}
	public float getYSpeed() {
		return ySpeed;
	}
	public float getVelocityAngle() {return velAngle;}
	/*
	public float getXspeed() {
		return (float)Math.cos((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	public float getVerticalVelocity() {
		return (float)Math.sin((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	*/
}
