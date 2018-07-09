package util;

/** holds 2d vector information */
public class Vector {
	private float magnitude;
	private float angle;
	
	/* CONSTRUCTORS */
	public Vector() {
		magnitude = 0;
		angle = 0;
	}
	public Vector(Vector v) {
		magnitude = v.getMagnitude();
		angle = v.getAngle();
	}
	public Vector(float magnitude, float angle) {
		this.magnitude = magnitude;
		this.angle = angle;
	}
	
	
	
	/* Helper Methods */
	
	// add vector to this one
	public void addVector(Vector v) {
		float xTemp = v.getXVec() + getXVec();
		float yTemp = v.getYVec() + getYVec();
		setVec(xTemp, yTemp);
	}
	public void addVector(float m, float a) {
		// find the x and y coordinates of the addition
		float xTemp = (float)Math.cos(a)*m + getXVec();
		float yTemp = (float)Math.sin(a)*m + getYVec();
		// calculate new magnitude and angle
		setVec(xTemp, yTemp);
	}
	// maybe a fancy one with any number of vectors
	
	// returns a new vector from addition
	public Vector projectVector(Vector v) {
		Vector babby = new Vector();
		babby.addVector(this);
		babby.addVector(v);
		return babby;
	}
	
	// fixes up magnitude and angles for adding calculation
	private void setVec(float x, float y) {
		// trig stuff yay
		magnitude = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		angle = 90-(float)Math.asin(Math.abs(y/magnitude));
		// determines correct angle based on x and y directions
		if (x<0 && y<0) {
			angle+=180;
		} else {
			if (x<0) {
				angle=360-angle;
			} else if (y<0) {
				angle=180-(90-angle);
			}
		}
	}
	
	// keeps angle within 0-360
	private float clamp360(float a) {
		while (a<0) {
			a+=360;
		}
		return a%360;
	}
	
	// Getters
	public float getMagnitude() {return magnitude;}
	public float getAngle() {return angle;}
	
	public float getXVec() {return (float)Math.cos(angle)*magnitude;}
	public float getYVec() {return (float)Math.sin(angle)*magnitude;}
	
	// Setters
	public void setMagnitude(float m) {magnitude = m;}
	public void addMagnitude(float m) {magnitude += m;}
	
	public void setXVec(float x) {
		// creates a new vector of just the x component, and subtracts it from the vector
		addVector(new Vector(getXVec(), (angle<=180)?270:90));
		// adds the fresh x component to the vector
		addVector(new Vector(x, (Math.signum(x)>0)?90:270));
	}
	public void setYVec(float y) {
		// same as above
		addVector(new Vector(getYVec(), (90<angle && angle<=270)?0:180));
		addVector(new Vector(y, (Math.signum(y)>0)?180:0));
	}
	
	public void setAngle(float a) {angle = clamp360(a);}
	public void addAngle(float a) {
		angle += a;
		angle = clamp360(angle);
	}
	
}
