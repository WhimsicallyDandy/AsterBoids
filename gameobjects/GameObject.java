package gameobjects;

import backend.GameApp;
import util.*;


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
	private Vector velocity;
	private Vector accel;
	
	private float spriteRotationSpeed;
	private float objectRotationSpeed;
	private float spriteAngle;
	private float objectAngle;
	
	private boolean canRender = true;
	private boolean canMove = true;
	private boolean canRotate = true;
	
	/* CONSTRUCTORS */
	public GameObject() throws SlickException {
		position = new Position(GameApp.SCREEN_WIDTH, GameApp.SCREEN_HEIGHT);
		sprite = new Image(S_DEFAULT);
		hitbox = new Hitbox(position, sprite);
	}
	public GameObject(float x, float y, float rotationSpeed, String imgsrc) throws SlickException {
		position = new Position(x, y);
		spriteRotationSpeed = rotationSpeed;
		objectRotationSpeed = rotationSpeed;
		sprite = new Image(imgsrc);
		hitbox = new Hitbox(position, sprite);
		spriteAngle = sprite.getRotation();
		velocity = new Vector(0, 0);
		accel = new Vector(0, 0);
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
		spriteAngle = clamp360(spriteAngle);
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
	
	// moves the object by its velocity
	public void travel() {
		addX(velocity.getXVec());
		addY(velocity.getYVec());
	}
	
	// clamps within 360
	public float clamp360(float a) {
		while (a<0) {a+=360;}
		return a%360;
	}
	
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
	public void setX(float x) {position.setX(x);}
	public void setY(float y) {position.setY(y);}
	
	public void setPos(float x, float y) {position.setPos(x, y);}
	public void setPos(Position p) {position.setPos(p);}
	
	public void addX(float x) {position.addX(x);}
	public void addY(float y) {position.addY(y);}
	
	public void setSpriteAngle(float angle) {this.spriteAngle = angle;}
	public void addSpriteAngle(float angle) {this.spriteAngle += angle;}
	
	public void setVelAngle(float angle) {objectAngle=angle;}
	public void addVelAngle(float angle) {objectAngle+=angle;}
	
	public void setVelMag(float m) {velocity.setMagnitude(m);}
	public void addVelMag(float m) {velocity.addMagnitude(m);}
	
	public void setVelocity(float m, float a) {velocity.setVector(m, a);}
	public void setVelocity(Vector v) {velocity = new Vector(v);}
	
	public void accelerate() {velocity.addVector(accel);}
	//public void accelerate(Vector v) {velocity.addVector(v);}
	public void setAcceleration(float m, float a) {accel.setVector(m, a);}
	public void setAcceleration(Vector v) {accel.setVector(v);}
	public void addAcceleration(float m, float a) {accel.addVector(m, a);}
	public void addAcceleration(Vector v) {accel.addVector(v);}
	
	public void setXSpeed(float x) {velocity.setXVec(x);}
	public void setYSpeed(float y) {velocity.setYVec(y);}
	
	public void setSpriteRotationSpeed(float rotationSpeed) {this.spriteRotationSpeed = rotationSpeed;}
	
	/* Getters */
	public float getX() {return position.getX();}
	public float getY() {return position.getY();}
	public Position getPos() {return position;}
	
	public float getXSpeed() {return velocity.getXVec();}
	public float getYSpeed() {return velocity.getYVec();}
	public float getVelMag() {return velocity.getMagnitude();}
	public Vector getVelocity() {return new Vector(velocity);}
	
	public float getXAccel() {return accel.getXVec();}
	public float getYAccel() {return accel.getYVec();}
	public float getAccelMag() {return accel.getMagnitude();}
	
	public float getSpriteAngle() {return spriteAngle;}
	public float getObjectAngle() {return objectAngle;}
	public float getVelocityAngle() {return velocity.getAngle();}
	
	
	// (float)Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2))
	/*
	public float getXspeed() {
		return (float)Math.cos((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	public float getVerticalVelocity() {
		return (float)Math.sin((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	*/
}
