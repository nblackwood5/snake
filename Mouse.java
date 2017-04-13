import java.awt.Color;
import java.awt.Graphics;

public class Mouse extends Food {
	public Mouse(int courtWidth, int courtHeight) {
		super(courtWidth, courtHeight);
		changeSize(10);
		this.width = SIZE;
		this.height = SIZE;
		this.inc = 5;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(pos_x, pos_y, width, height);
	}
}
