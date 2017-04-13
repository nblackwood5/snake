/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A game object that adds length to the snake when touched
 */

public class Food extends GameObj {

	public static int SIZE = 10;
	public static int INIT_X = GameCourt.COURT_WIDTH / 2 - SIZE;
	public static int INIT_Y = GameCourt.COURT_HEIGHT / 2 - SIZE;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public int inc = 1;

	public Food(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
				courtHeight);
	}
	
	public int getIncr() {
		return inc; 
	}
	
	public int getSize() {
		return SIZE; 
	}
	
	public void changeSize(int c) { 
		SIZE = c;
	}

}
