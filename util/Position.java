package util;

/**
 * Position for all GameObjects and perhaps some UI elements
 * set new position, calculate position for other positions and coordinates
 * @author ngellie
 *
 */
public class Position {
	private float x;
	private float y;
	
	/* CONSTRUCTORS */
	/**
	 * constructor for creating a position, from another or from coordinates
	 * @param x: x coord
	 * @param y: y coord
	 */
	public Position(float x, float y) {
		setPos(x, y);
	}
	public Position(Position p) {
		setPos(p);
	}
	
	/* METHODS */
	/**
	 * Calculates euclidean distance to other coordinate or position
	 * @param ox: coordinate x
	 * @param oy: coordinate y
	 * @return distance between two points
	 */
	public float dist(float ox, float oy) {
		return (float)Math.sqrt(Math.pow(x-ox, 2) + Math.pow(y-oy, 2));
	}
	public float dist(Position p) {
		return (float)Math.sqrt(Math.pow(x-p.getX(), 2) + Math.pow(y-p.getY(), 2));
	}
	
	/* getters and setters */
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void setPos(Position p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	public void addX(float x) {
		this.x += x;
	}
	public void addY(float y) {
		this.y += y;
	}
}
