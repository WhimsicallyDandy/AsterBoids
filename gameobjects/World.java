package gameobjects;

import backend.GameApp;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/** Handles the main world for the player, which it interacts with
 * 
 * @author ngellie
 *
 */
public class World {
	
	/* CONSTANTS */
	
	/* VARIABLES */
	private Player player;
	
	/* CONSTRUCTOR */
	public World() throws SlickException {
		player = new Player(GameApp.SCREEN_WIDTH/2, GameApp.SCREEN_HEIGHT/2);
	}
	/* METHODS */
	
	public void update(Input input, int delta) throws SlickException {
		if (input.isKeyPressed(Input.KEY_R)) {player = new Player(GameApp.SCREEN_WIDTH/2, GameApp.SCREEN_HEIGHT/2);}
		player.update(input, delta);
		
	}
	public void render(Graphics g) {
		g.drawString("player at |x, "+player.getX()+"|y, "+player.getY()+"|", 20, 20);
		g.drawString("Player sprite angle: "+player.getSpriteAngle(), 20, 40);
		g.drawString("Player velocity angle: "+player.getVelocityAngle(), 20, 60);
		g.drawString("player velocity = "+player.getVelocity(), 20, 80);
		player.render();
	}
	
	/* Helper Methods */
	
	/* Setters */
	
	/* Getters */
	
	
}
