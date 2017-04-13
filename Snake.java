/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;


//remove redundancies

public class Snake extends GameObj {
	public static int SIZE = 10;
	private int length = 1;
	public Direction direction = Direction.RIGHT;
	public int xy[][] = new int[2] [GameCourt.TOTAL_BLOCKS];
	float [] cs = Color.RGBtoHSB(0, 80, 0, null);
	public Color snakeGreen = Color.getHSBColor(cs[0], cs[1], cs[2]);

	public Snake(int courtWidth, int courtHeight) {
		super(0, 0, 0, 0, SIZE, SIZE, courtWidth,
				courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				g.setColor(Color.BLACK);
				g.fillRect(xy[0][i], xy[1][i], width, height);
			}
			else {
				if (i % 2 == 1) {
					g.setColor(snakeGreen);
					g.fillRect(xy[0][i], xy[1][i], width, height);
				} else if (i % 2 == 0) {
					g.setColor(Color.black);
					g.fillRect(xy[0][i], xy[1][i], width, height);
				} else {
					g.setColor(Color.black);
					g.fillRect(xy[0][i], xy[1][i], width, height);
				}
			}
		}
	}

	public void extend(int incr) {
		length += incr;
		int xx = xy[0][length - incr - 1];
		int yy = xy[1][length - incr - 1];
		for (int i = length - incr; i < length; i++) {
			xy[0][i] = xx;
			xy[1][i] = yy;
		}
	}

	public int getLength() {
		return length;
	}

	public int getCoverage() {
		int coverage = 1;
		for (int i = 1; i < length; i++) {
			if ((xy[0][i] != xy[0][i - 1]) || (xy[0][i] != xy[1][i - 1])) {	
				coverage++;
			}
		}
		return coverage;
	}

	public boolean isOB() {
		if (xy[0][0] < 0)
			return true;
		if (xy[0][0] > max_x)
			return true;
		if (xy[1][0] < 0)
			return true;
		if (xy[1][0] > max_y)
			return true;
		return false;
	}

	@Override
	public void move(){
		for (int i = (length-1); i > 0; i--) {
			xy[0][i] = xy[0][(i - 1)];
			xy[1][i] = xy[1][(i - 1)];
		}

		if (direction.equals(Direction.LEFT)) {
			xy[0][0] -= SIZE;
		}

		if (direction.equals(Direction.RIGHT)) {
			xy[0][0] += SIZE;
		}

		if (direction.equals(Direction.UP)) {
			xy[1][0] -= SIZE;
		}

		if (direction.equals(Direction.DOWN)) {
			xy[1][0] += SIZE;
		}
		clip();
	}

	public boolean intersects(GameObj obj){
		return (xy[0][0] >= obj.pos_x && xy[0][0] < (obj.pos_x + obj.width)
				&& xy[1][0] >= obj.pos_y && xy[1][0] < (obj.pos_y + obj.height) );

	}

	public boolean selfIntersect () {
		for (int i = 1; i < length; i++) {
			if (xy[0][i] == xy[0][0] && xy[1][i] == xy[1][0]) return true;
		}
		return false;
	}

	public boolean foodOn (int foodX, int foodY ) {
		for (int i = 0; i < length; i++) {
			if (xy[0][i] == foodX && xy[1][i] == foodY) return true;
		}
		return false;
	}

	public void dieColor(Graphics g) {
		for (int i = 0; i < length; i++) {
			g.setColor(Color.RED);
			g.fillRect(xy[0][i], xy[1][i], width, height);
		}
	}

	public void pauseColor(Graphics g) {
		for (int i = 0; i < length; i++) {
			g.setColor(Color.BLACK);
			g.fillRect(xy[0][i], xy[1][i], width, height);
		}
	}

	public int getX(int i) {
		int ex = xy[0][i];
		return ex;
	}

	public int getY(int i) {
		int why = xy[1][i];
		return why;
	}

	public static int getSize() {
		return SIZE;
	}

	public boolean equals(Snake s) {
		if (this.getLength() != s.getLength()) 
			return false;
		for (int i = 0; i < this.getLength(); i++) {
			if (this.getX(i) != s.getX(i) || this.getY(i) != s.getY(i))
				return false;
		}
		return true;
	}
}
