package backend;

/**
 * Eleanor did the code I modified it to not be whatever this is
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import gameobjects.World;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class GameApp extends BasicGame {
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    /** internal game speed */
    public static final int DELTA = 1000;
    
    private World world;

    public GameApp() {    	
        super("AsterBoids");
    }

    @Override
    public void init(GameContainer gc)
    		throws SlickException {
    	world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    		throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        // exits the game
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {gc.exit();}
        // update the world
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    		throws SlickException {
    	world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    		throws SlickException {
        AppGameContainer app = new AppGameContainer(new GameApp());
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}