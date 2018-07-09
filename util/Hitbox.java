package util;

import org.newdawn.slick.Image;

/**
 * Collision info for GameObjects
 * Origin is centred, instead at top and left
 * @author ngellie
 *
 */
public class Hitbox {
	private Position position;
	private float width;
	private float height;
	
	/* Constructor */
	public Hitbox(float x, float y, float width, float height) {
		position = new Position(x, y);
		this.width = width;
		this.height = height;
	}
	public Hitbox(float x, float y, Image img) {
		position.setPos(x, y);
		width = img.getWidth();
		height = img.getHeight();
	}
	public Hitbox(Position p, Image img) {
		position = new Position(p);
		width = img.getWidth();
		height = img.getHeight();
	}
	public Hitbox(Hitbox hb) {
		position.setPos(hb.getPos());
		width = hb.getWidth();
		height = hb.getHeight();
	}
	
	/* METHODS */
	/**
	 * determines if this hitbox collides with another
	 * @param h another hitbox
	 * @return true or false
	 */
	public boolean collides(Hitbox h) {
		/* as long as the horizontal lines don't overlap, and the vertical lines don't overlap, you're good */
		return !(getRight() < h.getLeft()
				|| getLeft() > h.getRight()
				|| getTop() > h.getBottom()
				|| getBottom() < h.getTop());
	}
	
	/* Setters */
	public void setPos(Position p) {
		position.setPos(p);
	}
	public void setPos(float x, float y) {
		setX(x);
		setY(y);
	}
	public void setX(float x) {
		position.setX(x);
	}
	public void setY(float y) {
		position.setY(y);
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	/* Getters */
	public float getLeft() {
		return position.getX()-width/2;
	}
	public float getRight() {
		return position.getX()+width/2;
	}
	public float getTop() {
		return position.getY()-height/2;
	}
	public float getBottom() {
		return position.getY()+height/2;
	}
	public Position getPos() {
		return position;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	
}
